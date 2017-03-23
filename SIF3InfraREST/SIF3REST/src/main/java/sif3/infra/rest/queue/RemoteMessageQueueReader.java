/*
 * RemoteMessageQueueReader.java
 * Created: 08/05/2014
 *
 * Copyright 2014 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package sif3.infra.rest.queue;

import java.net.URI;

import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.MessageType;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.header.ResponseHeaderConstants;
import sif3.common.model.EventMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.URIPathInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.common.ws.Response;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.rest.client.MessageClient;
import sif3.infra.rest.queue.types.DelayedBaseInfo;
import sif3.infra.rest.queue.types.ErrorInfo;
import sif3.infra.rest.queue.types.EventInfo;
import sif3.infra.rest.queue.types.QueueInfo;
import sif3.infra.rest.queue.types.ResponseInfo;

/**
 * This class is the actual reader on the remote SIF Queue. It deals with all the logic that applies to SIF Message Queues and how
 * efficient reading from these queues can be achieved. It implements the full queue reading process as defined in the SIF3 Specification.
 * This class can be multi-threaded as defined by the SIF3 Spec. Additionally this implementation will not fully process the
 * received message rather it will push it to a local queue where actual consumer classes will be listening on. This allows the
 * actual consumers to be either single threads or multi-threads with out this class needing the knowledge how consumer are
 * implemented.<br/><br/>
 * 
 * Note: It doesn't implement the full queue connector, just the "getMessage" part of the queue connector.
 * 
 * @author Joerg Huber
 *
 */
public class RemoteMessageQueueReader implements Runnable
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

	private QueueInfo queueInfo = null;
    private ConsumerEnvironmentManager consumerEvnMgr = null;
	private SIF3Session sif3Session = null;
	private int readerID;
	private String lastMsgeID = null;
	private int waitTime = 0; // milliseconds

	private MessageClient client = null;

	/**
	 * Constructs a RemoteMessageQueueReader for the queue identified through the queueListenerInfo parameter for the given session and consumer 
	 * configuration.
	 * 
	 * @param queueInfo
	 *            Holds all the information for this reader to identify what SIF queue to read from and where to distribute messages to.
	 * @param readerID
	 *            A string identifying the ID of this reader. Since it is expected that readers are running in multiple threads each 
	 *            reader should have its own id to identify it for logging purpose.
	 */
	public RemoteMessageQueueReader(QueueInfo queueInfo, int readerID) throws ServiceInvokationException
	{
		super();
		try
		{
			this.queueInfo = queueInfo;
	        consumerEvnMgr = ConsumerEnvironmentManager.getInstance();
	        sif3Session = consumerEvnMgr.getSIF3Session();
			this.readerID = readerID;

			// Get the wait time between get message calls once a no message is returned. Timeout is  the max from what the queue indicates
			// and what the configuration states.
			waitTime = Math.max(longToInt(getQueueInfo().getQueue().getWaitTime()),  getQueueInfo().getRemoteQueueConfig().getPollFrequency()) * CommonConstants.MILISEC;

			// Initialise message client. Only needs to be done once, so we do this here.
			client = new MessageClient(getConsumerEvnMgr(), new URI(getQueueInfo().getQueue().getMessageURI()));
		}
		catch (Exception ex)
		{
			logger.error("Failed do create a QueueReader: " + ex.getMessage(), ex);
			client = null;
			throw new ServiceInvokationException(ex);
		}
	}

	public void shutdown()
	{
		// nothing to do at the moment
		logger.debug("Shutdown Message Reader wit ID = " + getReaderID() + " for queue = " + getQueueInfo().getQueue().getName());
	}
	
	/* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
    	logger.debug("Message Queue Reader "+getReaderID()+" starts reading messages for queue "+getQueueInfo().getQueue().getName()+"...");
    	startReading();
    }

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
    /*
     * This is the actual core methods. It connect to the SIF Message queue and starts reading messages from it. 
     */
	private void startReading()
	{
		if (client != null) // indicating all good
		{
			while (true)
			{
				try
				{
					Response response = getClient().getMessage(getLastMsgeID(), getReaderID());
					setLastMsgeID(response); // ensure that the next loop iteration we will remove the current message

					if (isNoMessageResponse(response)) // no more messages available
					{
						waitBeforeGetNext(); // Wait until query the queue next time.
					}
					else if (isError(response))
					{
						waitBeforeGetNext(); // Wait until query the queue next time.
					}
					else
					{
						logger.debug("Message Reader '" + getReaderID() + "' (ThreadID:"+Thread.currentThread().getId()+") has receive a message from queue: " + getQueueInfo().getQueue().getName() + ". Message ID = " + getLastMsgeID());
						processMessage(response);
					}
				}
				catch (ServiceInvokationException ex)
				{
					// Error should already have been logged. Just wait and try again
					waitBeforeGetNext();	  
				}
			}
		}
	}

	private boolean isNoMessageResponse(Response response)
	{
		if (response.getStatus() == Status.NO_CONTENT.getStatusCode())
		{
			// Remove last msgID since this is just a normal status message
			setMsgIDToNull();
			return true;
		}
		return false;
	}

	/*
	 * Checks if an error is returned. If so it will log it, remove the lastMsg id and return true. Otherwise false is returned.
	 * An error here means an issue with the actual message connector (i.e. cannot connect, authentication etc). This is different
	 * to having retrieved an error response message from the queue. The two differ in the following way:
	 * A proper error message from a queue should have some mandatory HTTP headers. If they are there we can assume it is a proper
	 * error message from a queue otherwise we assume it is an issue with the message client and its connection. The fields to look
	 * out for are:
	 *  - responseaction
	 *  - relativeservicepath
	 *  - servicetype
	 */
	private boolean isError(Response response)
	{
		if (response.hasError()) // An error message has been received.
		{
			if (StringUtils.isEmpty(getHeaderValue(response, ResponseHeaderConstants.HDR_REL_SERVICE_PATH)))
			{
				// There is nothing we can do and it appears to be an error with the message client and its comms with the queue (i.e Auth invalid)
				logger.error("Error received:\n" + response.getError());
				
				// We must also set the lastMsgID to null!
				setMsgIDToNull();
				return true;
			}
			
			// We seem to have a relative service path which means the error is a genuine error message from a queue to a delayed request.
			return false;
		}
		return false;
	}

	private void waitBeforeGetNext()
	{
		logger.debug("\n==========================\n"+getReaderID()+" for queue "+getQueueInfo().getQueue().getName()+ " will wait for "+getWaitTime()/CommonConstants.MILISEC+" seconds before attempting to get next message."+"\n==========================");
		try
		{
			Object semaphore = new Object();
			synchronized (semaphore)
			{
				semaphore.wait(getWaitTime());
			}
		}
		catch (Exception ex)
		{
			logger.error("Blocking wait in Message Reader '" + getReaderID() + "' for queue: " + getQueueInfo().getQueue().getName() + " interrupted: " + ex.getMessage(), ex);
		}
	}

	/*
	 * This is the main method that deals with processing a message that has been received.
	 */
	private void processMessage(Response response)
	{
		String responseType = getHeaderValue(response, ResponseHeaderConstants.HDR_MESSAGE_TYPE);
		if (StringUtils.isEmpty(responseType)) // not good!
		{
			logger.error("Message received with unknown message type (null or empty). Cannot process response:\n" + response);
			return; // attempt to process next message
		}

		// All good. Check what response type we are dealing with
		MessageType messageType = MessageType.EVENT;
		try
		{
			messageType = MessageType.valueOf(responseType);
		}
		catch (Exception ex)
		{
			logger.error("Message received with unknown message type: '" + responseType + "'. Cannot process response:\n" + response);
			return; // attempt to process next message
		}
		
		switch (messageType)
		{
			case EVENT:
			{
				processEvent(response);
				break;
			}
			case RESPONSE:
			{
				processResponse(response);
				break; // attempt to process next message
			}
			case ERROR:
			{
				processError(response);
				break; // attempt to process next message
			}
		}
	}
	
	private void processResponse(Response response)
	{
		try
		{
			if (logger.isDebugEnabled())
			{
				logger.debug("RESPONSE Message Received:\n"+response);
			}
			
			//Populate ResponseInfo Object with base data.
			ResponseInfo responseInfo = new ResponseInfo();
			setResponseBaseData(responseInfo, response, MessageType.RESPONSE);
			
			// Is there a subscription for this RESPONSE type
			LocalConsumerQueue localQueue = getQueueInfo().getLocalConsumerQueue(responseInfo.getZone().getId(), responseInfo.getContext().getId(), responseInfo.getServiceName(), responseInfo.getServiceType().name());
			
			if (localQueue == null) // There is a response for which there is no registered consumer.
			{
				logger.info("Received a DELAYED Response for which there is no consumer registered. Discard the following RESPONSE:\n" + response);
			}
			else // Create event object and send it to eventConsumer
			{
				logger.debug(getReaderID()+": Attempts to push DELAYED Response to local queue...");
				localQueue.blockingPush(responseInfo);
				logger.debug(getReaderID()+": DELAYED Response successfully pushed to local queue");
			}	
		}
		catch (Exception ex)
		{
			logger.error("Error occured during the processing of a DELAYED RESPONSE message from the queue: " + getQueueInfo().getQueue().getName() + ". See previous error log entries for details: " + ex.getMessage(), ex);
		}
	}

	
	private void processError(Response response)
	{
		try
		{
			if (logger.isDebugEnabled())
			{
				logger.debug("ERROR Message Received:\n"+response);
			}

			//Populate ErrorInfo Object with base data.
			ErrorInfo errorInfo = new ErrorInfo();
			setResponseBaseData(errorInfo, response, MessageType.ERROR);
			
			// Is there a subscription for this Delayed ERROR type
			LocalConsumerQueue localQueue = getQueueInfo().getLocalConsumerQueue(errorInfo.getZone().getId(), errorInfo.getContext().getId(), errorInfo.getServiceName(), errorInfo.getServiceType().name());
			
			if (localQueue == null) // There is an ERROR for which there is no registered consumer.
			{
				logger.info("Received a DELAYED Error for which there is no consumer registered. Discard the following Error:\n" + response);
			}
			else // Create event object and send it to eventConsumer
			{
				logger.debug(getReaderID()+": Attempts to push DELAYED Error to local queue...");
				localQueue.blockingPush(errorInfo);
				logger.debug(getReaderID()+": DELAYED Error successfully pushed to local queue");
			}	
		}
		catch (Exception ex)
		{
			logger.error("Error occured during the processing of a DELAYED ERROR message from the queue: " + getQueueInfo().getQueue().getName() + ". See previous error log entries for details: " + ex.getMessage(), ex);
		}
	}


	/*
	 * baseInfo must not be null.
	 */
	private void setResponseBaseData(DelayedBaseInfo baseInfo, Response response, MessageType messageType)
	{
		//First get all the info out from the relativeServicePath
		URIPathInfo pathInfo = new URIPathInfo(getHeaderValue(response, ResponseHeaderConstants.HDR_REL_SERVICE_PATH));
		
		baseInfo.setMessageType(messageType);
		baseInfo.setRequestGUID(getHeaderValue(response, ResponseHeaderConstants.HDR_REQUEST_ID));
		baseInfo.setServiceType(pathInfo.getServiceType());
		baseInfo.setPayload((String)response.getDataObject());
		baseInfo.setMediaType(response.getMediaType());
		baseInfo.setZone(getSif3Session().getZone(pathInfo.getZone()));
		baseInfo.setContext(getSif3Session().getContext(pathInfo.getContext()));
//      baseInfo.setZone(getZone(pathInfo.getZone() != null ? pathInfo.getZone().getId() : null));
//      baseInfo.setContext(getContext(pathInfo.getContext()!= null ? pathInfo.getContext().getId() : null));
		baseInfo.setMessageQueueReaderID(getQueueReaderID());
		baseInfo.setFullRelativeURL(pathInfo.getOriginalURLString());
		baseInfo.setServiceName(pathInfo.getServiceName());
		baseInfo.setUrlService(pathInfo.getUrlService());
		baseInfo.setResponseAction(getResponseAction(response));
		baseInfo.setHttpHeaders(response.getHdrProperties());
		baseInfo.setQueryParameters(pathInfo.getQueryParams());
	}
	
	
	private void processEvent(Response response)
	{
		try
		{
			if (logger.isDebugEnabled())
			{
				logger.debug(getQueueReaderID()+": EVENT Message Received:\n"+response);
			}
			SIFZone zone = getZone(response);
			SIFContext context = getContext(response);
			String serviceName = getHeaderValue(response, ResponseHeaderConstants.HDR_SERVICE_NAME);
			String serviceType = getHeaderValue(response, ResponseHeaderConstants.HDR_SERVICE_TYPE);

			// Is there a subscription for this event?
			LocalConsumerQueue localQueue = getQueueInfo().getLocalConsumerQueue(zone.getId(), context.getId(), serviceName, serviceType);
			if (localQueue == null) // There is an event for which there is no registered consumer.
			{
				logger.info("Received an event for which there is no consumer registered. Discard the following Event:\n" + response);
			}
			else // Create event object and send it to eventConsumer
			{
				String eventPayload = (String)response.getDataObject();
				EventAction eventAction = getEventAction(response);
				UpdateType updateType = null;
				if ((eventAction != null) && (eventAction == EventAction.UPDATE))
				{
					updateType = getUpdateType(response);
				}
				
				EventMetadata metadata = new EventMetadata(response.getHdrProperties());
				
				// Set the generator ID in its specific property for easy access.
				metadata.setGeneratorID(getHeaderValue(response, ResponseHeaderConstants.HDR_GENERATOR_ID));
				
				//TODO: JH - Do we need applicationKey and authenticatedUser HTTP header here?				
				
				EventInfo eventInfo = new EventInfo(eventPayload, response.getMediaType(), eventAction, updateType, zone, context, metadata, getQueueReaderID());
				eventInfo.setFingerprint(getHeaderValue(response, ResponseHeaderConstants.HDR_FINGERPRINT));
				
				logger.debug(getQueueReaderID()+": Attempts to push Event to local queue...");
				localQueue.blockingPush(eventInfo);
				logger.debug(getQueueReaderID()+": Event successfully pushed to local queue");
			}
		}
		catch (Exception ex)
		{
			logger.error("Error occured during the processing of a message from the queue: " + getQueueInfo().getQueue().getName() + ". See previous error log entries for details: " + ex.getMessage(), ex);
		}
	}

	private EventAction getEventAction(Response response)
	{
		String eventAction = getHeaderValue(response, ResponseHeaderConstants.HDR_EVENT_ACTION);
		if (StringUtils.notEmpty(eventAction))
		{
			try
			{
				return EventAction.valueOf(eventAction);
			}
			catch (Exception ex)
			{
				logger.error("Received an event with an invalid event action: '" + eventAction + "':\n" + response);
				return null;
			}
		}
		else
		{
			logger.error("Received an event with no event action set in the response header:\n" + response);
			return null;
		}
	}

	private UpdateType getUpdateType(Response response)
	{
		String updateType = getHeaderValue(response, ResponseHeaderConstants.HDR_UPDATE_TYPE);
		if (StringUtils.notEmpty(updateType))
		{
			try
			{
				return UpdateType.valueOf(updateType);
			}
			catch (Exception ex)
			{
				logger.error("Received an update event with an invalid update type: '" + updateType + "':\n" + response);
				return null;
			}
		}
		else
		{
			logger.error("Received an update event with no update type set in the response header:\n" + response);
			return null;
		}
	}
	
	private ResponseAction getResponseAction(Response response)
	{
		String value = getHeaderValue(response, ResponseHeaderConstants.HDR_RESPONSE_ACTION);
		if (StringUtils.notEmpty(value))
		{
			try
			{
				return ResponseAction.valueOf(value);
			}
			catch (Exception ex)
			{
				logger.error("Received an response action with an invalid type: '" + value + "':\n" + response);
				return null;
			}
		}
		else
		{
			logger.error("Received an response without a response action response HTTP header:\n" + response);
			return null;
		}
	}
	

	private SIFZone getZone(Response response)
	{
		return getZone(getHeaderValue(response, ResponseHeaderConstants.HDR_ZONE_ID));
	}

	private SIFContext getContext(Response response)
	{
		return getContext(getHeaderValue(response, ResponseHeaderConstants.HDR_CONTEXT_ID));
	}
	
	/*
	 * Utility method to get the SIF Zone Object based on zone ID. If zoneID is empty then the Default Zone is returned.
	 */
	private SIFZone getZone(String zoneID)
	{
		if (StringUtils.isEmpty(zoneID)) // use default zone
		{
			return getSif3Session().getDefaultZone();
		}
		else
		{
			if (getSif3Session().getDefaultZone().getId().equals(zoneID))
			{
				return getSif3Session().getDefaultZone();
			}
			else
			{
				return new SIFZone(zoneID, false);
			}
		}		
	}

	/*
	 * Utility method to get the SIF Context Object based on context ID. If contextID is empty then the Default Context is returned.
	 */
	private SIFContext getContext(String contextID)
	{
		if (StringUtils.isEmpty(contextID)) // use default context
		{
			return CommonConstants.DEFAULT_CONTEXT;
		}
		else
		{
			if (CommonConstants.DEFAULT_CONTEXT_NAME.equals(contextID))
			{
				return CommonConstants.DEFAULT_CONTEXT;
			}
			else
			{
				return new SIFContext(contextID, false);
			}
		}
	}

	/*
	 * returns the value of the HTTP header given by is name or null if it doesn't exist.
	 */
	private String getHeaderValue(Response response, String hdrName)
	{
		return response.getHdrProperties().getHeaderProperty(hdrName);
	}

	private int longToInt(Long value)
	{
		return (value == null) ? 0 : value.intValue();
	}

	private QueueInfo getQueueInfo()
	{
		return queueInfo;
	}

    private ConsumerEnvironmentManager getConsumerEvnMgr()
    {
        return consumerEvnMgr;
    }

    private SIF3Session getSif3Session()
    {
        return sif3Session;
    }
    
	private MessageClient getClient()
	{
		return client;
	}

	private int getReaderID()
	{
		return readerID;
	}
	
	/*
	 * Returns a String ID of the Queue Name and consumerInstance Id. Used for logging an debugging.
	 */
	private String getQueueReaderID()
	{
		return getQueueInfo().getQueue().getName()+"_"+getReaderID();
	}

	private String getLastMsgeID()
	{
		return lastMsgeID;
	}

	private void setLastMsgeID(Response response)
	{
		this.lastMsgeID = response.getHdrProperties().getHeaderProperty(ResponseHeaderConstants.HDR_MESSAGE_ID);
	}
	
	private void setMsgIDToNull()
	{
		this.lastMsgeID =  null;
	}

	private int getWaitTime()
	{
		return waitTime;
	}

}
