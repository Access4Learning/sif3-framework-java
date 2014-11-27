/*
 * QueueResource.java
 * Created: 05/04/2014
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

package sif3.infra.rest.resource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.header.HeaderValues;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.header.ResponseHeaderConstants;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.persist.model.SIF3Session;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.ErrorDetails;
import sif3.infra.common.env.mgr.DirectProviderEnvironmentManager;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.QueueCollectionType;
import sif3.infra.common.model.QueueType;
import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.DateUtils;
import au.com.systemic.framework.utils.FileReaderWriter;
import au.com.systemic.framework.utils.StringUtils;

/*
 * http://localhost:9080/SIF3InfraREST/sif3/queues/69df9d79-8e01-43e8-825f-d0dc1775761b
 */

/**
 * This class defines the Queue Connector Resource. Since this is a direct environment at this stage it won't actually implement the resource,
 * rather "dummy it out". This means it will only print the request to the debug log but it will then return NOT_SUPPORTED error to
 * the caller.
 * 
 * @author Joerg Huber
 */
@Path("/queues{mimeType:(\\.[^/]*?)?}")
public class QueueResource extends InfraResource
{
	private String deleteMessageId = null;
	
	/* Below variables are for testing purposes only */
	private ObjectFactory infraObjectFactory = new ObjectFactory();
	private static QueueType dummyQueue = null;
	private static int numGetMessages = 0;
	private static Integer numEventsTillNoMsg  = null;
	private static HashMap<String, String> messageIDMap = new HashMap<String, String>(); // key=consumerID, value=lastMsgId
	private static List<EventMsg> events = null;
	private static int currentEvent = 0;
	/* End Testing variables */
	

	public QueueResource(@Context UriInfo uriInfo,
            			 @Context HttpHeaders requestHeaders,
            			 @Context Request request,
            			 @MatrixParam("deleteMessageId") String deleteMessageId,
			             @PathParam("mimeType") String mimeType)
	{
		super(uriInfo, requestHeaders, request, "", null, null);
		this.deleteMessageId = deleteMessageId;
	    setURLPostfixMediaType(mimeType);
	    logger.debug("URL Postfix mimeType: '"+mimeType+"'");
	}

	/*----------------------*/
	/*-- Abstract Methods --*/
	/*----------------------*/

	/* (non-Javadoc)
     * @see sif3.infra.rest.resource.BaseResource#getEnvironmentManager()
     */
    @Override
    public EnvironmentManager getEnvironmentManager()
    {
	    return DirectProviderEnvironmentManager.getInstance();
    }

	/*-----------------------------*/
	/*-- Queue Interface Methods --*/
	/*-----------------------------*/
    
    /*
     * Get all Queues for this environment.
     */
	@GET
//Let everything through and then deal with it when needed. 
//@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllQueues()
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Get Queues (REST GET - Plural)");
		}
		
		if (isTestMode())
		{
			ErrorDetails error = validSession();
			if (error != null) // Not allowed to access!
			{
				return makeErrorResponse(error, ResponseAction.QUERY);
			}
			QueueCollectionType queues = infraObjectFactory.createQueueCollectionType();
			if ((dummyQueue != null))
			{
				queues.getQueue().add(dummyQueue);
			}
			return makeResponse(queues, Status.OK.getStatusCode(), false, ResponseAction.QUERY, getInfraMarshaller());				
		}
		else
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Queues not supported.", "This DIRECT Environment implementation does not support queues, yet."), ResponseAction.QUERY);
		}
	} 
	
    /*
     * Get a specific Queues for this environment.
     */
	@GET
	@Path("{queueID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
//  Let everything through and then deal with it when needed. 
//  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getQueue(@PathParam("queueID") String queueID,
							 @PathParam("mimeType") String mimeType)
	{
	    setURLPostfixMediaType(mimeType);
		if (logger.isDebugEnabled())
		{
			logger.debug("Get Queue by Queue ID (REST GET - Single): "+queueID+" and URL Postfix mime type = '"+mimeType+"'");
		}
		if (isTestMode())
		{
			ErrorDetails error = validSession();
			if (error != null) // Not allowed to access!
			{
				return makeErrorResponse(error, ResponseAction.QUERY);
			}
			if ((dummyQueue != null) && (dummyQueue.getId().equals(queueID)))
			{
				return makeResponse(dummyQueue, Status.OK.getStatusCode(), false, ResponseAction.QUERY, getInfraMarshaller());				
			}
			else
			{
				return makeErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Queues with ID = "+queueID+" does not exist."), ResponseAction.QUERY);				
			}
		}
		else
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Queues not supported.", "This DIRECT Environment implementation does not support queues, yet."), ResponseAction.QUERY);
		}
	} 

    /*
     * Create a Queues for this environment.
     */
	@POST
//Let everything through and then deal with it when needed. 
//@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createQueue(String payload)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Create Queue (REST POST - Single): "+payload);
		}
		if (isTestMode())
		{
			ErrorDetails error = validSession();
			if (error != null) // Not allowed to access!
			{
				return makeErrorResponse(error, ResponseAction.CREATE);
			}
			try
			{
				QueueType inputQueue = (QueueType)getInfraUnmarshaller().unmarshal(payload, QueueType.class, getRequestMediaType());
				QueueType outputQueue = createQueue(inputQueue);
				
				return makeResponse(outputQueue, Status.CREATED.getStatusCode(), false, ResponseAction.CREATE, getInfraMarshaller());
			}
			catch (UnmarshalException ex)
			{
				logger.error("Failed to unmarshal payload into an QueueType: "+ ex.getMessage(), ex);
				logger.error("Queue Payload: "+ payload);
				return makeErrorResponse( new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal queue payload: "+ ex.getMessage()), ResponseAction.CREATE);
			}
      catch (UnsupportedMediaTypeExcpetion ex)
      {
        logger.error("Failed to unmarshal payload into an QueueType: "+ ex.getMessage(), ex);
        logger.error("Queue Payload: "+ payload);
        return makeErrorResponse( new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Failed to unmarshal queue payload: "+ ex.getMessage()), ResponseAction.CREATE);
      }
		}
		else
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Queues not supported.", "This DIRECT Environment implementation does not support queues, yet."), ResponseAction.CREATE);
		}
	} 

	/*
	 * Delete a specific queue.
	 */
	@DELETE
	@Path("{queueID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
//  Let everything through and then deal with it when needed. 
//  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response removeQueue(@PathParam("queueID") String queueID,
			 				    @PathParam("mimeType") String mimeType)
	{
	    setURLPostfixMediaType(mimeType);
		if (logger.isDebugEnabled())
		{
			logger.debug("Remove Queue (REST DELETE - Single) with queueID = "+queueID+" and URL Postfix mime type = '"+mimeType+"'");
		}
		if (isTestMode())
		{
			ErrorDetails error = validSession();
			if (error != null) // Not allowed to access!
			{
				return makeErrorResponse(error, ResponseAction.DELETE);
			}
			if ((dummyQueue != null) && (dummyQueue.getId().equals(queueID)))
			{
				dummyQueue = null;
				numGetMessages = 0;
				messageIDMap = new HashMap<String, String>();
				return makeResopnseWithNoContent(false, ResponseAction.DELETE);				
			}
			else
			{
				return makeErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Queues with ID = "+queueID+" does not exist."), ResponseAction.DELETE);				
			}
		}
		else
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Queues not supported.", "This DIRECT Environment implementation does not support queues, yet."), ResponseAction.DELETE);
		}
	}
		
    /*
     * Get next available message from the given queue and remove the one indicated by the matrix parameter.
     */
	@GET
	@Path("{queueID}/messages{mimeType:(\\.[^/]*?)?}")
//  Let everything through and then deal with it when needed. 
//  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getNextMessage(@PathParam("queueID") String queueID,
			    				   @PathParam("mimeType") String mimeType)
	{
	    setURLPostfixMediaType(mimeType);
		if (logger.isDebugEnabled())
		{
			logger.debug("Get Message from Queue (REST GET - Single): "+queueID +", URL Postfix mime type = '"+mimeType+"' and Remove message with ID = "+getDeleteMessageId());
		}
		if (isTestMode())
		{
			ErrorDetails error = validSession();
			
			String consumerID = getHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_CONSUMER_ID);
			consumerID = consumerID == null ? "" : consumerID;
			logger.debug("Consumer ID = "+consumerID+" requested next message");
			if (error != null) // Not allowed to access!
			{
				return makeErrorResponse(error, ResponseAction.QUERY);
			}
			if ((dummyQueue != null) && (dummyQueue.getId().equals(queueID)))
			{
				// Ensure we request the deletion of the top message
				if (getDeleteMessageId() != null)
				{
					// Remove message and get next
					String lastMsgID = messageIDMap.get(consumerID);
					if (getDeleteMessageId().equals(lastMsgID))
					{
					  Response response = getMessage(consumerID);
					  if (response != null)
					  {
					    return response;
					  }
					  else
					  {
			              messageIDMap.put(consumerID, null);
			              return makeResopnseWithNoContent(false, ResponseAction.QUERY);
					    
					  }
					}
					else // message id doesn't match
					{
						return makeErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Delete Message ID = "+getDeleteMessageId()+" is not the last delivered message."), ResponseAction.QUERY);										
					}
				}
				else // deleted Message ID not set. Return next message but don't increase message count
				{
					return getMessage(consumerID);
				}
			}
			else
			{
				return makeErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Queues with ID = "+queueID+" does not exist."), ResponseAction.QUERY);				
			}
		} 
		else
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Queues not supported.", "This DIRECT Environment implementation does not support queues, yet."), ResponseAction.QUERY);
		}
	}
		
	/*
	 * Delete a specific queue.
	 */
	@DELETE
	@Path("{queueID}/messages/{deleteMessageId:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
//  Let everything through and then deal with it when needed. 
//  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response removeMessageFromQueue(@PathParam("queueID") String queueID, 
			                               @PathParam("deleteMessageId") String deleteMessageId,
						 				   @PathParam("mimeType") String mimeType)
	{
	    setURLPostfixMediaType(mimeType);
		setDeleteMessageId(deleteMessageId);
		if (logger.isDebugEnabled())
		{
			String consumerID = getHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_CONSUMER_ID);
			logger.debug("Remove Message from Queue (REST DELETE - Single) with queueID = "+queueID+", URL Postfix mime type = '"+mimeType+"' and message with ID = "+getDeleteMessageId()+" requested by consumer = "+consumerID);
		}
		return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Queues not supported.", "This DIRECT Environment implementation does not support queues, yet."), ResponseAction.DELETE);
	} 

	public String getDeleteMessageId()
    {
    	return this.deleteMessageId;
    }

	public void setDeleteMessageId(String deleteMessageId)
    {
    	this.deleteMessageId = deleteMessageId;
    }


	/*------------------------*/
	/*-- Testing stuff only --*/
	/*------------------------*/
	private QueueType createQueue(QueueType inputQueue)
	{
		SIF3Session sif3Session = getEnvironmentManager().getSessionBySessionToken(getSessionToken());
		dummyQueue = inputQueue;
		dummyQueue.setCreated(Calendar.getInstance());
		dummyQueue.setId(UUIDGenerator.getUUID());
		dummyQueue.setOwnerId(sif3Session.getEnvironmentID());
		dummyQueue.setLastAccessed(Calendar.getInstance());
		dummyQueue.setLastModified(Calendar.getInstance());
		dummyQueue.setIdleTimeout(0L);
		dummyQueue.setMinWaitTime(30L);
		dummyQueue.setMessageCount(0L);
		dummyQueue.setQueueUri(getUriInfo().getBaseUri().toString()+"queues/"+inputQueue.getId()+"/messages");
		
		return dummyQueue;
	}
	
	private Response getMessage(String consumerID)
	{
	  EventMsg event = null;
	  if (events == null)
	  {
	    loadEventData();
	  }
	  
	  if (numEventsTillNoMsg == null)
	  {
	    setNumEventsTillNoMsg();
	  }

	  if ((events == null) || (events.size() == 0))// unable to load events file or no events in file => stop here
	  {
	    return null;
	  }
	  
	  // Check if we need to return No Message
    numGetMessages++;
    if ((numEventsTillNoMsg == 0) || ((numGetMessages % numEventsTillNoMsg) == 0))
    {
      return null;
    }

	  // If we get here then a message can be returned.
	  event = events.get(currentEvent++);
	  if (currentEvent >= events.size()) // reset if we reach the end of the list
	  {
	    currentEvent = 0;
	  }
	  
		String lastMsgID = UUIDGenerator.getUUID();
		messageIDMap.put(consumerID, lastMsgID);

		dummyQueue.setLastAccessed(Calendar.getInstance());
		String payload = event.getMessage();
		ResponseBuilder response = null;
		if (payload != null)
		{
			response = Response.status(Status.OK).entity(payload);
			response = response.header(ResponseHeaderConstants.HDR_CONTENT_LENGTH, payload.length());
			response = response.header(ResponseHeaderConstants.HDR_MESSAGE_TYPE, HeaderValues.MessageType.EVENT.name());
			response = response.header(ResponseHeaderConstants.HDR_EVENT_ACTION, EventAction.UPDATE.name());
			response = response.header(ResponseHeaderConstants.HDR_UPDATE_TYPE, UpdateType.FULL.name());
			response = response.header(ResponseHeaderConstants.HDR_SERVICE_TYPE, HeaderValues.ServiceType.OBJECT.name());
			response = response.header(ResponseHeaderConstants.HDR_SERVICE_NAME, event.getServiceName());
			response = response.header(ResponseHeaderConstants.HDR_MESSAGE_ID, lastMsgID);
		}
		else
		{
			response = Response.status(Status.NO_CONTENT);
		}
		
		// Date & Time format must be: YYYY-MM-DDTHH:mm:ssZ (i.e. 2013-08-12T12:13:14Z)
		response = response.header(ResponseHeaderConstants.HDR_DATE_TIME, DateUtils.nowAsISO8601());
		response = response.header(ResponseHeaderConstants.HDR_PROVIDER_ID, getProviderID());
		
		return response.build();		
	}
	
	private void loadEventData()
	{
		AdvancedProperties props = getEnvironmentManager().getServiceProperties();
		String eventFileName = props.getPropertyAsString("event.message.file", null);
		
		if (eventFileName != null)
		{	  
			String eventStr = FileReaderWriter.getFileContent(eventFileName);
			String[] eventArray = StringUtils.splitter(eventStr, "###", false);
			if (eventArray != null)
			{
			  events = new ArrayList<EventMsg>();
			  for (int i=0; i<eventArray.length; i=i+2)
			  {
			    // First enrty is serviceName, second entry is actual message
			    events.add(new EventMsg(eventArray[i].trim(), eventArray[i+1].trim()));
			  }
			  currentEvent = 0;
			}
		  logger.debug("Loaded eventFileName. Total Events = "+events.size());

		}
	}
	
	private void setNumEventsTillNoMsg()
	{
	  AdvancedProperties props = getEnvironmentManager().getServiceProperties();
	  numEventsTillNoMsg = props.getPropertyAsInt("event.numUntilNoMsg", 0);
	  logger.debug("Loaded Propery event.numUntilNoMsg. Value = "+numEventsTillNoMsg);
	}
	
	/*
	 * For internal testing only 
	 */
  private final class EventMsg
  {
    private String serviceName = null;
    private String message = null;
    
    public EventMsg(String serviceName, String message) 
    {
      this.serviceName = serviceName;
      this.message = message;
    }
    
    public String getServiceName()
    {
      return serviceName;
    }

    public String getMessage()
    {
      return message;
    }
  }
	
}
