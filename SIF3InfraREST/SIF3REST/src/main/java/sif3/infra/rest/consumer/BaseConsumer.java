/*
 * BaseConsumer.java
 * Created: 12 Jul 2018
 *
 * Copyright 2018 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License. 
 */

package sif3.infra.rest.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.StringUtils;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.QueryIntention;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.interfaces.MinimalConsumer;
import sif3.common.model.ACL.AccessRight;
import sif3.common.model.ACL.AccessType;
import sif3.common.model.CustomParameters;
import sif3.common.model.PayloadMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.model.ZoneContextInfo;
import sif3.common.model.delayed.DelayedRequestReceipt;
import sif3.common.model.delayed.DelayedResponseReceipt;
import sif3.common.persist.model.SIF3Session;
import sif3.common.ws.BaseResponse;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.rest.queue.LocalConsumerQueue;
import sif3.infra.rest.queue.LocalMessageConsumer;
import sif3.infra.rest.queue.QueueReaderInfo;

/**
 * It is expected that all consumer implementations extend this class. It has a set of abstract methods that need to be implemented by
 * a specific consumer class. It is not expected that a developer extends this class. There are a set of standard Abstract Consumers the
 * developer will implement. This class however forms the base of these Abstract Consumer classes.
 * 
 * @author Joerg Huber
 *
 */
public abstract class BaseConsumer implements MinimalConsumer
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /* Below variables are for testing purposes only */
    private static Boolean testMode = null;
    /* End Testing variables */

    private boolean checkACL = true;

    /* The next two properties are used for delayed responses or events */ 
    private LocalConsumerQueue localConsumerQueue = null;
//    private ExecutorService service = null;
    
    private QueueReaderInfo<LocalMessageConsumer> service = null;

    /*-------------------------------------------------------------*/
    /* Abstract method relating to general Consumer functionality. */
    /*-------------------------------------------------------------*/

    /**
     * This method is called when a consumer service is shut down. It can be used to free up internally allocated resources
     * as well as clean-up other things.
     */
    public abstract void shutdown();

    /**
     * This method is called when a delayed error response is retrieved from the consumer's queue.<br/><br/>
     * 
     * @see sif3.common.interfaces.DelayedConsumer#onError(sif3.common.ws.ErrorDetails, sif3.common.model.delayed.DelayedResponseReceipt)
     * 
     * @param error See onError() method in DelayedConsumer class.
     * @param receipt See onError() method in DelayedConsumer class.
     */
    public abstract void processDelayedError(ErrorDetails error, DelayedResponseReceipt receipt);

    /**
     * Constructor.
     */
    public BaseConsumer()
    {
        super();
        
        // Set some properties at this stage for simplicity reasons.
        checkACL = getConsumerEnvironment().getCheckACL();
    }
    
    /*
     * This method should never be called by an actual consumer. It is purely there fore housekeeping and is only
     * called by the ComsumerLoader to initialise consumer with internal queues and threads. It is "protected" because it is
     * called from the ConsumerLoader.
     * 
     * This method cannot be part of the constructor otherwise all local queues are created over and over again as the consumer
     * classes are instantiated by an adapter.
     */
    protected void initLocalConsumerQueues()
    {
        if (getConsumerEnvironment().getEventsEnabled() || getConsumerEnvironment().getDelayedEnabled())
        {
            logger.debug("Events and/or Delayed Responses enabled => start local consumer queue for "+getConsumerName());
            createLocalConsumerQueue();
        }
        else
        {
            logger.debug("Events AND Delayed Responses are disabled. Local consumer queues and threads are not started.");
        } 
    }
    

    /*-------------------------------*/
    /* Useful 'Housekeeping' methods */
    /*-------------------------------*/
    /**
     * @return Returns the actual Class Name of this consumer
     */
    public String getConsumerName()
    {
        return getClass().getSimpleName();
    }
      
    /**
     * Utility method. Mainly used for useful logging messages.
     * 
     * @return Returns the Adapter Name as defined in the adapter.id property of the consumer property file concatenated with the 
     *         Consumer Name (class name)
     */
    public String getPrettyName()
    {
        return getConsumerEnvironment().getAdapterName()+" - " + getConsumerName();
    }

    /**
     * Utility method to easily retrieve the consumer environment configuration.
     * 
     * @return See desc
     */
    public ConsumerEnvironment getConsumerEnvironment()
    {
      return (ConsumerEnvironment)ConsumerEnvironmentManager.getInstance().getEnvironmentInfo();
    }
    
    /**
     * Utility method to easily retrieve the property file content for a consumer.
     * 
     * @return See desc
     */
    public AdvancedProperties getServiceProperties()
    {
      return ConsumerEnvironmentManager.getInstance().getServiceProperties();
    }

    /**
     * @return Returns the Service Name.
     */
    public String getServiceName()
    {
        return getMultiObjectClassInfo().getObjectName();
    }

    /*------------------------------------------------------------------------------------------------------------------------
     * Start of 'Dynamic' HTTP Header Field override section
     * 
     * The following set of methods are used for a more configurable way how some HTTP header parameters are set.
     * By default the following HTTP Header fields are retrieved from the consumer's property file and put in corresponding
     * HTTP Header Fields:
     * 
     * Property                      HTTP Header                            Method
     * ------------------------------------------------------------------------------------------------
     * adapter.generator.id          generatorId
     * env.application.key           applicationKey
     * env.userToken                 authenticatedUser
     * 
     * env.mediaType.dm          -.
     * env.mediaType.charset.dm  -'-> Content-Type, Accept            -.
     * env.schema.dm.domain      -.                                    |-> DataModelPayloadMetadata
     * env.schema.dm.version      |-> content-profile, accept-profile -' 
     * env.schema.dm.json.type   -'
     * 
     * env.mediaType.infra          -.
     * env.mediaType.charset.infra  -'-> Content-Type, Accept            -.
     * env.schema.infra.version     -.                                    |-> InfraPayloadMetadata
     * env.schema.infra.json.type   -'-> content-profile, accept-profile -'
     * adapter.mustUseAdvisoryIDs    mustUseAdvisory
     * adapter.compression.enabled   Content-Encoding, Accept-Encoding
     * 
     * Only properties that are not null or empty string will be set in the corresponding HTTP Header.
     *
     * There are situations where and application may need a more 'dynamic' behaviour where the above values are determined
     * at runtime, based on other circumstances and therefore these properties must be retrieved from an other source than the
     * consumer's property file. In such a case the methods below can be overwritten to make them dynamic and controlled by
     * the implementation rather than driven by the consumer's property file. If any of the methods below is overwritten then
     * the value of the over riding method is set in the corresponding HTTP Header field if the return value of the method 
     * is not null or an empty string.
     *------------------------------------------------------------------------------------------------------------------------*/
    
    /**
     * This method returns the value of the adapter.generator.id property from the consumer's property file. If that
     * needs to be overridden by a specific implementation then the specific sub-class should override this method.
     * 
     * @return The adapter.generator.id property from the consumer's property file
     */
    public String getGeneratorID()
    {
        return getConsumerEnvironment().getGeneratorID();
    }
    
    /**
     * This method returns the value of the env.application.key property from the consumer's property file. If that
     * needs to be overridden by a specific implementation then the specific sub-class should override this method.
     * 
     * @return The env.application.key property from the consumer's property file
     */
    public String getApplicationKey()
    {
        return getConsumerEnvironment().getEnvironmentKey().getApplicationKey();
    }

    /**
     * This method returns the value of the env.userToken property from the consumer's property file. If that
     * needs to be overridden by a specific implementation then the specific sub-class should override this method.
     * 
     * @return The env.userToken property from the consumer's property file
     */
    public String getAuthentictedUser()
    {
        return getConsumerEnvironment().getEnvironmentKey().getUserToken();
    }
    
    /**
     * This method returns the values from the consumer's property file for:<br/><br/>
     * - env.mediaType.dm<br/>
     * - env.mediaType.charset.dm<br/>
     * - env.schema.dm.domain,env.schema.dm.version,env.schema.dm.json.type (used for schema negotiation)<br/>
     * <br/>
     * These values are all encapsulated in the PayloadMetadata object. If any of these values need to be overridden by a 
     * specific implementation then the specific consumer sub-class should override this method.
     * 
     * @return See description
     */
    public PayloadMetadata getDataModelRequestPayloadMetadata()
    {
        return getConsumerEnvironment().getDataModelPayloadMetadata();
    }
    
    public PayloadMetadata getDataModelResponsePayloadMetadata()
    {
        return getConsumerEnvironment().getDataModelPayloadMetadata();
    }
    
    /**
     * This method returns the values from the consumer's property file for:<br/><br/>
     * - env.mediaType.infra<br/>
     * - env.mediaType.charset.infra<br/>
     * - env.schema.infra.version,env.schema.infra.json.type (used for schema negotiation)<br/>
     * <br/>
     * These values are all encapsulated in the PayloadMetadata object. If any of these values need to be overridden by a 
     * specific implementation then the specific consumer sub-class should override this method.
     * 
     * @return See description
     */
    public PayloadMetadata getInfraRequestPayloadMetadata()
    {
        return getConsumerEnvironment().getInfraPayloadMetadata();
    }
    
    public PayloadMetadata getInfraResponsePayloadMetadata()
    {
        return getConsumerEnvironment().getInfraPayloadMetadata();
    }
   
    /**
     * This method returns the value of the adapter.mustUseAdvisoryIDs property from the consumer's property file. If that
     * needs to be overridden by a specific implementation then the specific sub-class should override this method.
     * 
     * @return The adapter.mustUseAdvisoryIDs property from the consumer's property file
     */
    public boolean getMustUseAdvisory()
    {
        return getConsumerEnvironment().getUseAdvisory();
    }

    /**
     * This method returns the value of the adapter.compression.enabled property from the consumer's property file. If 
     * that needs to be overridden by a specific implementation then the specific sub-class should override this method.
     * 
     * @return The adapter.compression.enabled property from the consumer's property file
     */
    public boolean getCompressionEnabled()
    {
        return getConsumerEnvironment().getCompressionEnabled();
    }

    /*------------------------------------------------------------------------------------------------------------------------
     * End of 'Dynamic' HTTP Header Field override section
     *-----------------------------------------------------------------------------------------------------------------------*/ 

    /*------------------------------*/
    /* Some Getter & Setter methods */
    /*------------------------------*/
    
    public final LocalConsumerQueue getLocalConsumerQueue()
    {
      return localConsumerQueue;
    }

    public final void setLocalConsumerQueue(LocalConsumerQueue localConsumerQueue)
    {
      this.localConsumerQueue = localConsumerQueue;
    }
    
    /*-------------------*/
    /* Interface Methods */
    /*-------------------*/
    /*
     * (non-Javadoc)
     * @see sif3.common.consumer.Consumer#finalise()
     */
    @Override
    public void finalise()
    {
        logger.debug("Shut down all Local Consumer Thread Pool for "+getConsumerName());
        if (service != null)
        {
            for (LocalMessageConsumer localReader : service.getLinkedClasses())
            {
                localReader.shutdown();
            }
    
            service.getService().shutdown();
        }
        shutdown();
    }


    /*------------------------------------------------------------------------------------*/
    /*-- Methods for Local Queues and Threads for Delayed Response and Event management --*/
    /*------------------------------------------------------------------------------------*/

    /**
     * Only creates the local consumer queue if it doesn't already exist. The queue (new or existing) is then returned.
     * 
     * @return See desc.
     */
    public final LocalConsumerQueue createLocalConsumerQueue()
    {
        if (getLocalConsumerQueue() == null)
        {
            // Create local queue with the capacity indicated with the consumer config
            logger.debug("Create Local Queue for "+getConsumerName());

            // Use the local queue as a trigger of threads rather than actual queueing of messages. Use 1 as the minimum
            setLocalConsumerQueue(new LocalConsumerQueue(1, getClass().getSimpleName() + "LocalQueue", getClass().getSimpleName()));
            startListenerThreads();
        }
        return getLocalConsumerQueue();
    }

    /*
     * Will initialise the threads and add them to the local consumer queue.
     */
    private void startListenerThreads()
    {
        // Start up all consumers for this subscriber.
        int numThreads = getNumOfConsumerThreads();
        logger.debug("Start "+numThreads+" "+getConsumerName()+" threads.");
        logger.debug("Total number of threads before starting Local Queue for "+getConsumerName()+" "+Thread.activeCount());
        
        service = new QueueReaderInfo<LocalMessageConsumer>(Executors.newFixedThreadPool(numThreads), new ArrayList<LocalMessageConsumer>());
        for (int i = 0; i < numThreads; i++)
        {
            String consumerID = getConsumerName()+" "+(i+1);
            logger.debug("Start Consumer "+consumerID);
            LocalMessageConsumer consumer = new LocalMessageConsumer(getLocalConsumerQueue(), consumerID, this);
            service.getLinkedClasses().add(consumer);
            service.getService().execute(consumer);
       }
        logger.debug(numThreads+" "+getConsumerName()+" initilaised and started.");
        logger.debug("Total number of threads after starting Local Queue for "+getConsumerName()+" "+Thread.activeCount());
    }

    private final int getNumOfConsumerThreads()
    {
        return getServiceProperties().getPropertyAsInt("consumer.local.workerThread", getClass().getSimpleName(), 1);
    }

    /*--------------------------------------------------------------------------------*/
    /*-- Protected Methods to be used by Abstract Consumers that extend this class. --*/
    /*--------------------------------------------------------------------------------*/

    protected SIF3Session getSIF3Session()
    {
      return ConsumerEnvironmentManager.getInstance().getSIF3Session();
    }

    protected HeaderProperties getHeaderProperties(boolean isCreateOperation, HeaderValues.ServiceType serviceType, CustomParameters customParameters) 
    {
       HeaderProperties hdrProps = new HeaderProperties();
       
       // First we add all Custom HTTP Headers. We add SIF defined HTTP header later. This will also ensure that we
       // will override custom properties with SIF defined properties.
       if ((customParameters != null) && (customParameters.getHttpHeaderParams() != null))
       {
           hdrProps = customParameters.getHttpHeaderParams();
       }
        
       // Now we set SIF defined HTTP headers...
            
       // Set the remaining header fields for this type of request
       if (isCreateOperation)
       {
          hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_ADVISORY, (getMustUseAdvisory() ? "true" : "false"));
       }
       hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, serviceType.name());
       
       // Set values of consumer property file or their overridden value. Note the setHeaderProperty() method will do the check
       // for null, so no need to do this here.
       hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_APPLICATION_KEY, getApplicationKey());
       hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTHENTICATED_USER, getAuthentictedUser());
       hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_GENERATOR_ID, getGeneratorID());
            
       return hdrProps;
    }

    protected ErrorDetails hasAccess(String serviceName, ServiceType serviceType, AccessRight right, AccessType accessType, SIFZone zone, SIFContext context)
    {
        ErrorDetails error = null;
        if (checkACL)
        {
            if (!getSIF3Session().hasAccess(right, accessType, serviceName, serviceType, zone, context))
            {
                String zoneID = (zone == null) ? "Default" : zone.getId();
                String contextID = (context == null) ? "Default" : context.getId();
                error = new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", right.name()+ " access is not set to "+accessType.name()+" for the service " + serviceName +" and the given zone ("+zoneID+") and context ("+contextID+") in the environment "+getSIF3Session().getEnvironmentName(), "Client side check.");         
            }
        }
        return error;
    }
    
    protected ErrorDetails allClientChecks(String serviceName, ServiceType serviceType, AccessRight right, AccessType accessType, SIFZone zone, SIFContext context, RequestType requestType)
    {
        ErrorDetails error = hasAccess(serviceName, serviceType, right, accessType, zone, context);
        if ((error == null) && (requestType != null))
        {
            error = requestTypeEnabled(requestType);
        }   
        return error;
    }
    
    protected ErrorDetails checkAccessToFunctionalService(String serviceName, SIFZone zone, SIFContext context, RequestType requestType)
    {
        ErrorDetails error =  null;
        ServiceInfo serviceInfo = getSIF3Session().getServiceInfoForService(zone, context, serviceName, ServiceType.FUNCTIONAL);
        if (serviceInfo == null) // Found no service for the given functional service name.
        {
            error = new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Access Denied.", "Consumer is not authorized to access the functional service '"+serviceName+"'.", "Consumer side check."); 
        }

        if ((error == null) && (requestType != null))
        {
            error = requestTypeEnabled(requestType);
        }   
        return error;
    }

    protected ErrorDetails requestTypeEnabled(RequestType requestType)
    {
        ErrorDetails error = null;
        
        if ((requestType == RequestType.DELAYED) && (!getConsumerEnvironment().getDelayedEnabled()))
        {
            error = new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Client side Check: DELAYED requests are not enabled.");
        }
        return error;
    }

    protected BulkOperationResponse<CreateOperationStatus> makeBulkErrorResponseForCreates(ErrorDetails error)
    {
        BulkOperationResponse<CreateOperationStatus> response = new BulkOperationResponse<CreateOperationStatus>();
        setErrorDetails(response, error);
        return response;    
    }
    
    protected BulkOperationResponse<OperationStatus> makeBulkErrorResponse(ErrorDetails error)
    {
        BulkOperationResponse<OperationStatus> response = new BulkOperationResponse<OperationStatus>();
        setErrorDetails(response, error);
        return response;    
    }

    protected Response createErrorResponse(ErrorDetails error)
    {
        Response response = new Response();
        setErrorDetails(response, error);
        return response;    
    }
    
    /*
     * This method sets the remaining properties in the receipt for delayed responses. There are a few fields that cannot be set at the ObjectServiceClient as
     * they are not known or cannot be determined in there but are well known in the abstract consumer.
     */
    protected void finaliseDelayedReceipt(DelayedRequestReceipt delayedReceipt, String serviceName, ServiceType serviceType, ResponseAction requestedAction)
    {
        if (delayedReceipt != null)
        {
            //delayedReceipt.setRequestDate(requestDate);
            delayedReceipt.setServiceName(serviceName);
            delayedReceipt.setServiceType(serviceType);
            delayedReceipt.setRequestedAction(requestedAction);
        }
    }

    protected void nullMethodCheck(Object objectToCheck, String methodName) throws IllegalArgumentException
    {
        if (objectToCheck == null)
        {
            throw new IllegalArgumentException(methodName+" method not implemented correctly. Returns null which is invalid.");
        }
    }

    /*
     * This method checks if the test.testmode in the consumer's property file is set to TRUE.
     */
    protected boolean isTestMode()
    {
        if (testMode == null)
        {
            AdvancedProperties props = getServiceProperties();
            testMode = props.getPropertyAsBool("test.testmode", false);
        }
        return testMode;
    }

    protected List<ZoneContextInfo> getFinalZoneCtxList( List<ZoneContextInfo> zoneCtxList, SIF3Session sif3Session)
    {
        List<ZoneContextInfo> finalZoneContextList = null;
        
        if (zoneCtxList == null)
        {
            finalZoneContextList = new ArrayList<ZoneContextInfo>();
        }
        else
        {
            finalZoneContextList = zoneCtxList;
        }
        
        if (finalZoneContextList.size() == 0) //add default context and zone
        {
            // Set zone and context to null which will ensure that the matrix params won't be set and therefore the provider will assume default context & zone
            finalZoneContextList.add(new ZoneContextInfo((SIFZone)null, (SIFContext)null));
        }
        
        // Check all entries and if 'null' is used as zone or context then we assign the default.
        for (ZoneContextInfo zoneCtxInfo : finalZoneContextList)
        {
            // If zone or zone ID is null then we set the default zone.
            if ((zoneCtxInfo.getZone() == null) || StringUtils.isEmpty(zoneCtxInfo.getZone().getId()))
            {
                zoneCtxInfo.setZone(null); // won't set matrix parameter which means default zone
            }
            // If context or context ID is null then we set the default zone.
            if ((zoneCtxInfo.getContext() == null) || StringUtils.isEmpty(zoneCtxInfo.getContext().getId()))
            {
                zoneCtxInfo.setContext(null); // won't set matrix parameter which means default context
            }
        }
        
        return finalZoneContextList;
    }

    protected SIFZone getFinalZone(SIFZone zone)
    {
        // If zone or zone ID is null then we set the default zone. This means set it to null which defaults to the default zone
        if ((zone == null) || StringUtils.isEmpty(zone.getId()))
        {
            return null; // won't set matrix parameter which means default zone
        }
        
        return zone;
    }
    
    protected SIFContext getFinalContext(SIFContext context)
    {
        // If context or context ID is null then we set the default context. This means set it to null which defaults to the default context
        if ((context == null) || StringUtils.isEmpty(context.getId()))
        {
            return null; // won't set matrix parameter which means default context
        }
        
        return context;
    }
    
    protected void addQueryIntentionToHeaders(HeaderProperties hdrProps, QueryIntention queryIntention)
    {
        // Ensure query Intention is not null. if so default to ONE-OFF as per SIF 3.x spec.
        queryIntention = (queryIntention == null) ? QueryIntention.ONE_OFF : queryIntention;
        
        // Add query intention to headers.
        hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_QUERY_INTENTION, queryIntention.getHTTPHeaderValue());
    }

    
    /*
     * Returns all services for which this consumer has APPROVE access to at least on of the rights listed. This should be a list of different zones,
     * contexts and service types.
     */
    protected final List<ServiceInfo> getAllApprovedServicesForRights(String serviceName, ServiceType serviceType, AccessRight... rights)
    {
        SIF3Session sif3Session = ConsumerEnvironmentManager.getInstance().getSIF3Session();
        List<ServiceInfo> allServices = new ArrayList<ServiceInfo>(); 
        
        // Get Services for given type and name
        List<ServiceInfo> services = sif3Session.getServiceInfoForService(serviceName, serviceType);
        for (ServiceInfo serviceInfo : services)
        {
            // Check if any of the given rights states APPROVED
            if (rights != null)
            {
                for (AccessRight right : rights)
                {
                    if (serviceInfo.getRights().hasRight(right, AccessType.APPROVED))
                    {
                        allServices.add(serviceInfo); //found one.
                        
                        // we can stop as the service has at least one of the rights approved.
                        break;
                    }
                }
            }
        }
        return allServices;
    }   

    /*-------------------*/
    /* Private Methods --*/
    /*-------------------*/
    
    private void setErrorDetails(BaseResponse response, ErrorDetails errorDetails)
    {
        response.setStatus(errorDetails.getErrorCode());
        response.setStatusMessage(errorDetails.getMessage());
        response.setError(errorDetails);
        response.setContentLength(0);
        response.setHasEntity(false);
    }
}
