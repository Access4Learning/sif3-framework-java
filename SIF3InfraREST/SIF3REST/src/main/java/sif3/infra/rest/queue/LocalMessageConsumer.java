/*
 * LocalConsumerQueue.java
 * Created: 06/05/2014
 *
 * Copyright 2014-2018 Systemic Pty Ltd
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

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.interfaces.DelayedConsumer;
import sif3.common.interfaces.EventConsumer;
import sif3.common.interfaces.MinimalConsumer;
import sif3.common.model.PagingInfo;
import sif3.common.model.PayloadMetadata;
import sif3.common.model.QueryCriteria;
import sif3.common.model.SIFEvent;
import sif3.common.model.StringPayload;
import sif3.common.model.ZoneContextInfo;
import sif3.common.model.delayed.DelayedResponseReceipt;
import sif3.common.model.job.PhaseInfo;
import sif3.common.utils.JAXBUtils;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.job.PhaseDataResponse;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.rest.consumer.AbstractFunctionalServiceConsumer;
import sif3.infra.rest.consumer.AbstractNamedQueryConsumer;
import sif3.infra.rest.mapper.InfraDataModelMapper;
import sif3.infra.rest.queue.types.DelayedBaseInfo;
import sif3.infra.rest.queue.types.ErrorInfo;
import sif3.infra.rest.queue.types.EventInfo;
import sif3.infra.rest.queue.types.QueueMessage;
import sif3.infra.rest.queue.types.ResponseInfo;


/**
 * This class allows the subscriber to consume messages in a multi-threaded manner according to the
 * producer-consumer design pattern.<p>
 * 
 * @author Joerg Huber
 *
 */
public class LocalMessageConsumer implements Runnable
{

    protected final Logger logger = LoggerFactory.getLogger(getClass());

	private LocalConsumerQueue localQueue;
	private String consumerID;
	private MinimalConsumer minimalConsumer;
	private InfraDataModelMapper infraMapper = null;
    private boolean shutdownFlag = false;
    private EnvironmentInfo environmentInfo;

	/**
	 * This method initialises a Consumer to be able to receive and process messages from the local event queue. The 'consumer' parameter is 
	 * required by the local consumer as it invokes the consumer's methods for processing events or delayed responses.
	 * 
	 * @param localQueue The local queue on which this consumer will be listening on.
	 * @param consumerID A name of the consumer. Mainly needed for nice debug and error reporting.
	 * @param minimalConsumer An instance of consumer that will process the message.
	 * @param environmentInfo Environment info of the consumer for which the consumer queue config applies.
	 */
	public LocalMessageConsumer(LocalConsumerQueue localQueue, String consumerID, MinimalConsumer minimalConsumer, EnvironmentInfo environmentInfo)
	{
		this.localQueue = localQueue;
		this.consumerID = consumerID;
		this.minimalConsumer = minimalConsumer;
		this.environmentInfo = environmentInfo;
		this.infraMapper = new InfraDataModelMapper(this.environmentInfo);
	}
	
	/**
	 * Required for this class to run in its own thread.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	//@Override
    public void run()
    {
        try
        {
            consume();
        }
        catch (InterruptedException ex)
        {
            logger.error("Thread interupted - application shutting down...", ex);
        }
    }
	
    public void shutdown()
    {
        logger.debug("Shutdown message received for LocalConsumerConsumer: "+consumerID);
        localQueue.shutdown();
        shutdownFlag = true;
    }

	/*-----------------*/
	/* Private methods */
	/*-----------------*/
	/*
	 * This method will run in an infinite loop and try to retrieve messages from the local queue. Once
	 * a message is retrieved it will determine if it will be sent to appropriate consumer thread for it to be processed.
	 */
	private void consume() throws InterruptedException
	{
		while (!shutdownFlag)
		{
            try 
            {
    			QueueMessage message = localQueue.blockingPull();
    			if (message != null)
    			{
    			    // Check what type of message it is.
    			    switch (message.getMessageType())
                    {
                        case EVENT:
                        {
                            processEvent((EventInfo)message);
                            break;
                        }
                        case RESPONSE:
                        {
                        	processResponse((ResponseInfo)message);
                            break;
                        }
                        case ERROR:
                        {                   
                        	processError((ErrorInfo)message);
                            break;
                        }
                    }
    			}
    			else
    			{
    			    if (!shutdownFlag)
    			    {
                        logger.error("LocalMessage Consumer " + consumerID + " has encountered a problem receiving an message from its local consumer queue.");    			        
    			    }
    			}
            }
            catch (Exception ex)
            {
                if ((ex != null) && (ex instanceof InterruptedException))
                {
                    throw (InterruptedException) ex;
                }
                else
                {
                    // Error should already have been logged. Just wait and try again
                    logger.error("LocalMessage Consumer " + consumerID + " has encountered a problem receiving an message from its local consumer queue.", ex);
                }
            }
		}
        logger.debug("LocalMessage Consumer " + consumerID + " stopped reading messages. Shutdown flag: "+shutdownFlag);
	}
	
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
    private void processEvent(EventInfo eventInfo)
	{
        logger.debug(consumerID + " has receive an event from its local consumer queue ID: "  + eventInfo.getMessageQueueReaderID());
        EventConsumer<?> eventConsumer = (EventConsumer<?>)minimalConsumer;
        Object eventPayload = null;
        if (eventInfo.getServiceType() == ServiceType.FUNCTIONAL) 
        {
            eventPayload = makeFunctionalServiceObject(eventInfo.getPayload(), eventInfo.getPayloadMetadata(), false, true);
        }
        else
        {
            eventPayload = makeDataModelObject(minimalConsumer, eventInfo.getPayload(), eventInfo.getPayloadMetadata());
        }
        if (eventPayload instanceof ErrorDetails)
        {
            logger.error("Failed to create and send actual event to event consumer (" + eventConsumer.getClass().getSimpleName() + "): " + eventPayload);
            logger.error("Event Data Info for failed event:\n" + eventInfo);
        }
        else
        {
            // Create actual event Object
            SIFEvent event = eventConsumer.createEventObject(eventPayload, eventInfo.getEventAction(), eventInfo.getUpdateType());
            event.setMetadata(eventInfo.getMetadata());
            event.setFingerprint(eventInfo.getFingerprint());
            
            List<ZoneContextInfo> limitToZoneCtxList = new ArrayList<ZoneContextInfo>();
            limitToZoneCtxList.add(new ZoneContextInfo(eventInfo.getZone(), eventInfo.getContext()));
            event.setLimitToZoneCtxList(limitToZoneCtxList);

            // Send event to actual event consumer.
//            eventConsumer.onEvent(event, eventInfo.getZone(), eventInfo.getContext(), eventInfo.getEventMetadata(), eventInfo.getMessageQueueReaderID(), consumerID);
            eventConsumer.onEvent(event, eventInfo.getEventMetadata(), eventInfo.getMessageQueueReaderID(), consumerID);
        }
	}

    private void processResponse(ResponseInfo responseInfo)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug(consumerID + " has receive a DELAYED RESPONSE from its local consumer queue ID: "  + responseInfo.getMessageQueueReaderID());
            logger.debug(responseInfo.toString());
        }
        
        DelayedConsumer delayedConsumer = (DelayedConsumer)minimalConsumer; 
        DelayedResponseReceipt delayedReceipt = extractDelayedReceiptInfo(responseInfo);
        
        if (responseInfo.getResponseAction() == null) // this is not good and something is terribly wrong.
        {
            // Because we cannot decide what the payload is due to the missing responseAction we put the payload into an error message
            // and send it to the onError() method of the consumer. We also log an error.
            logger.error("Received a Response without a 'responseAction'. Cannot decide what to do with payload. Send message to error handler of consumer.");
            ErrorDetails error = new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Received Response from Message Queue without 'responseAction' HTTP Header. Payload set in 'description' property of this error object.", responseInfo.getPayload(), "LOCAL MESSAGE PROCESSING");
            delayedConsumer.onError(error, delayedReceipt);
            return;
        }
        
        boolean isPhaseObject = StringUtils.notEmpty(responseInfo.getPhaseName());
        
        // If we get here we know what to do with the payload.
        switch (responseInfo.getResponseAction())
        {
        	case CREATE:
        	{
        	    if (responseInfo.getServiceType() == ServiceType.FUNCTIONAL)
        	    {
        	        if (!isPhaseObject)
        	        {
                        delayedConsumer.onCreateMany(infraMapper.toStatusListFromSIFCreateString(responseInfo.getPayload(), responseInfo.getPayloadMetadata()), delayedReceipt);                             	            
        	        }
        	        else
        	        {
        	            AbstractFunctionalServiceConsumer fsc = (AbstractFunctionalServiceConsumer)minimalConsumer;
        	            fsc.processDelayedPhaseCreate(new PhaseInfo(responseInfo.getResourceID(), responseInfo.getPhaseName()), 
        	                                          new PhaseDataResponse(responseInfo.getPayload(), responseInfo.getPayloadMetadata(), Status.OK), 
        	                                          delayedReceipt);
        	        }
        	    }
        	    else
        	    {
        	        delayedConsumer.onCreateMany(infraMapper.toStatusListFromSIFCreateString(responseInfo.getPayload(), responseInfo.getPayloadMetadata()), delayedReceipt);
        	    }
        	    break;
        	}
        	case DELETE:
        	{
                if (responseInfo.getServiceType() == ServiceType.FUNCTIONAL)
                {
                    if (!isPhaseObject)
                    {
                        delayedConsumer.onDeleteMany(infraMapper.toStatusListFromSIFDeleteString(responseInfo.getPayload(), responseInfo.getPayloadMetadata()), delayedReceipt);
                    
                    }
                    else
                    {
                        AbstractFunctionalServiceConsumer fsc = (AbstractFunctionalServiceConsumer)minimalConsumer;
                        fsc.processDelayedPhaseDelete(new PhaseInfo(responseInfo.getResourceID(), responseInfo.getPhaseName()), 
                                                      new PhaseDataResponse(responseInfo.getPayload(), responseInfo.getPayloadMetadata(), Status.OK), 
                                                      delayedReceipt);
                    }
                }
                else
                {
                    delayedConsumer.onDeleteMany(infraMapper.toStatusListFromSIFDeleteString(responseInfo.getPayload(), responseInfo.getPayloadMetadata()), delayedReceipt);
                }
        		break;
        	}
        	case UPDATE:
        	{
                if (responseInfo.getServiceType() == ServiceType.FUNCTIONAL)
                {
                    if (isPhaseObject)
                    {
                        AbstractFunctionalServiceConsumer fsc = (AbstractFunctionalServiceConsumer)minimalConsumer;
                        fsc.processDelayedPhaseUpdate(new PhaseInfo(responseInfo.getResourceID(), responseInfo.getPhaseName()), 
                                                      new PhaseDataResponse(responseInfo.getPayload(), responseInfo.getPayloadMetadata(), Status.OK), 
                                                      delayedReceipt);
                    }
                    else
                    {
                        logger.error("Received a DELAYED UPDATE Response for Jobs of Functional Services. This is an invalid operation. Data ignored.");
                    }
                }
                else
                {
                    delayedConsumer.onUpdateMany(infraMapper.toStatusListFromSIFUpdateString(responseInfo.getPayload(), responseInfo.getPayloadMetadata()), delayedReceipt);
                }
        		break;
        	}
        	case QUERY:
        	{
        	    Object payloadObject = null;
        	    
                switch (responseInfo.getServiceType())
                {
                    case OBJECT: // no code here as it is the same as the SERVICEPATH below!
                    case SERVICEPATH:
                    {
                        payloadObject = makeDataModelObject(minimalConsumer, responseInfo.getPayload(), responseInfo.getPayloadMetadata());
                        break;
                    }
                    case FUNCTIONAL:
                    {
                        payloadObject = makeFunctionalServiceObject(responseInfo.getPayload(), responseInfo.getPayloadMetadata(), isPhaseObject, false);
                        break;
                    }
                    case XQUERYTEMPLATE:
                    {
                        // Payload must remain a string. So there is no action required for this service type!
                        break;
                    }
                    default:
                    {
                        logger.error("Received a Query Response for a Servic Type ("+responseInfo.getServiceType()+") that is not yet supported with this framework. Ignore message:\n"+responseInfo);
                    }
                }
        	    
        	    if (payloadObject instanceof ErrorDetails)
        	    {
        	        // Something is not good at all. We send the error to the consumer.
        	        delayedConsumer.onError((ErrorDetails)payloadObject, delayedReceipt);        	        
        	    }

        	    // If we get here then the payload is good and we continue extracting stuff...
                PagingInfo paging = new PagingInfo(responseInfo.getHttpHeaders(), null);
                
        	    switch (responseInfo.getServiceType())
        		{
        			case OBJECT:
        			{
        			    delayedConsumer.onQuery(payloadObject, paging, delayedReceipt);
        				break;
        			}
        			case SERVICEPATH:
        			{
        			    delayedConsumer.onServicePath(payloadObject, new QueryCriteria(responseInfo.getUrlService()), paging, delayedReceipt);
        				break;
        			}
                    case XQUERYTEMPLATE:
                    {
                        AbstractNamedQueryConsumer nqc = (AbstractNamedQueryConsumer)minimalConsumer;
                        nqc.processDelayedNamedQuery(new StringPayload(responseInfo.getPayload(), responseInfo.getPayloadMetadata()), paging, delayedReceipt);

                        break;
                    }
        			case FUNCTIONAL:
        			{
                        if (!isPhaseObject)
                        {
                            delayedConsumer.onQuery(payloadObject, paging, delayedReceipt);
                        }
                        else
                        {
                            AbstractFunctionalServiceConsumer fsc = (AbstractFunctionalServiceConsumer)minimalConsumer;
                            fsc.processDelayedPhaseQuery(new PhaseInfo(responseInfo.getResourceID(), responseInfo.getPhaseName()), 
                                                         new PhaseDataResponse(responseInfo.getPayload(), responseInfo.getPayloadMetadata(), Status.OK), 
                                                         paging, delayedReceipt);
                        }
        			    break;
        			}
        			default:
        			{
        				logger.error("Received a Query Response for a Servic Type ("+responseInfo.getServiceType()+") that is not yet supported with this framework. Ignore message:\n"+responseInfo);
        			}
        		}
        		break;
        	}
            case HEAD:
            {
                logger.error("Received a Delayed HEAD Response. That is not yet supported with this framework. Ignore message:\n"+responseInfo);                
            }
        }
    }

    private void processError(ErrorInfo errorInfo)
    {
        logger.debug(consumerID + " has receive a DELAYED ERROR from its local consumer queue ID: "  + errorInfo.getMessageQueueReaderID());
        DelayedConsumer delayedConsumer = (DelayedConsumer)minimalConsumer;
        
        // If an error was returned then it is already unmarshalled and set in the errorInfo object. If it is null though
        // then we set a dummy value...
        ErrorDetails errorDetails = errorInfo.getErrorDetail();
        if (errorDetails == null) // create dummy error as it seems something is wrong
        {
            errorDetails = new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not extract error from payload.");
        }
        delayedConsumer.onError(errorDetails, extractDelayedReceiptInfo(errorInfo));
    }
    
    /*
     * This method attempts to create the Datamodel object for the payload. This may fail due to various reasons. In this case the
     * returned object of this method is not a Datamodel object but an ErrorDetails object. The caller of this method MUST
     * check if the returned object is of type ErrorDetails first before doing anything with it.
     */
    private Object makeDataModelObject(MinimalConsumer miniConsumer, String payload, PayloadMetadata payloadMetadata)
    {
        try
        {
            return miniConsumer.getUnmarshaller().unmarshal(payload, miniConsumer.getMultiObjectClassInfo().getObjectType(), payloadMetadata.getMimeType(), payloadMetadata.getSchemaType());
        }
        catch (UnmarshalException ex)
        {
            return new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not unmarshal Datamodel payload into SIF Object: "+ex.getMessage()+". See error description for payload details.", payload);
        }
        catch (UnsupportedMediaTypeExcpetion ex)
        {
            return new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal Datamodel payload into SIF Object (unsupported media type): "+ex.getMessage()+". See error description for payload details.", payload);
        }
        catch (Exception ex)
        {
            return new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal Could not unmarshal Datamodel payload into SIF Object: "+ex.getMessage()+". See error description for payload details.", payload);
        }   
    }

    /*
     * Will only be called for Job Queries. So we know that it must be a JobCollection object. The other usage is from Phase Operations.
     * In this case we know nothing about the data model and therefore we return the raw data (string).
     */
    private Object makeFunctionalServiceObject(String payload, PayloadMetadata payloadMetadata, boolean isPhaseObject, boolean isEvent)
    {
        InfraUnmarshalFactory infraUnmarshaller = new InfraUnmarshalFactory();
        if (isEvent || !isPhaseObject) // The object is a Job Collection object
        {
            try
            {
                // Potentially map to framework Infrastructure version.
                payload = JAXBUtils.mapNamespaceVersion(payload, environmentInfo.getBaseInfraNamespace(), environmentInfo.getFrameworkInfraVersion());
               
                return infraUnmarshaller.unmarshal(payload, JobCollectionType.class, payloadMetadata.getMimeType(), payloadMetadata.getSchemaType());
            }
            catch (UnmarshalException ex)
            {
                return new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not unmarshal Job Collection payload into SIF Object: "+ex.getMessage()+". See error description for payload details.", payload, "Consumer");
            }
            catch (UnsupportedMediaTypeExcpetion ex)
            {
                return new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal  Job Collection payload into SIF Object (unsupported media type): "+ex.getMessage()+". See error description for payload details.", payload, "Consumer");
            }
            catch (Exception ex)
            {
                return new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal Could not unmarshal  Job Collection payload into SIF Object: "+ex.getMessage()+". See error description for payload details.", payload, "Consumer");
            }   
        }
        else // must be delayed response to Phase Operation. Cannot unmarshal as phase data model is unknown.
        {
            return payload;
        }
    }

    
    private DelayedResponseReceipt extractDelayedReceiptInfo(DelayedBaseInfo delayedInfo)
    {
    	DelayedResponseReceipt receipt = new DelayedResponseReceipt();
        
        receipt.setRequestGUID(delayedInfo.getRequestGUID());
        receipt.setZone(delayedInfo.getZone());
        receipt.setContext(delayedInfo.getContext());
    	receipt.setServiceName(delayedInfo.getServiceName());
        receipt.setResourceID(delayedInfo.getResourceID());
        receipt.setPhaseName(delayedInfo.getPhaseName());
    	receipt.setServiceType(delayedInfo.getServiceType());
    	receipt.setRequestedAction(delayedInfo.getResponseAction());
    	receipt.setRelativeRequestURI(delayedInfo.getFullRelativeURL());
    	receipt.setMsgReadID(delayedInfo.getMessageQueueReaderID());
    	receipt.setConsumerID(consumerID);
    	receipt.setQueryParams(delayedInfo.getQueryParameters());
    	receipt.setHttpHeaders(delayedInfo.getHttpHeaders());
    	
    	return receipt;
    }

}
