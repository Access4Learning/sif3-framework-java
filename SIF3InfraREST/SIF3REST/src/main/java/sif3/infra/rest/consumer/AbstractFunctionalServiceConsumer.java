/*
 * AbstractFunctionalServiceConsumer.java
 * Created: 13 Jul 2018
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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import au.com.systemic.framework.utils.Timer;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.QueryIntention;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.interfaces.DelayedConsumer;
import sif3.common.interfaces.EventConsumer;
import sif3.common.interfaces.FunctionalServiceConsumer;
import sif3.common.model.ACL.AccessRight;
import sif3.common.model.ACL.AccessType;
import sif3.common.model.CustomParameters;
import sif3.common.model.EventMetadata;
import sif3.common.model.PagingInfo;
import sif3.common.model.PayloadMetadata;
import sif3.common.model.QueryCriteria;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.model.URLQueryParameter;
import sif3.common.model.ZoneContextInfo;
import sif3.common.model.delayed.DelayedResponseReceipt;
import sif3.common.model.job.JobCreateRequestParameter;
import sif3.common.model.job.PhaseInfo;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.common.ws.job.PhaseDataRequest;
import sif3.common.ws.job.PhaseDataResponse;
import sif3.common.ws.model.MultiOperationStatusList;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;
import sif3.infra.rest.client.JobClient;

/**
 * This is the core class that a developer will use to implement their functional service consumers. Each consumer for each functional
 * service MUST extend this class. It forms the link between the high level consumer implementation and the low level infrastructure 
 * functions which this class abstracts.<br/>
 * This class  has some similarities to the Object service classes (AbstractConsumer and AbstarctEventConsumer). However it adds
 * required functional service operations such a phase and state functions that are only applicable for functional service. Since
 * functional services operate on the Job Object a number of assumptions can be made that aren't valid for the abstract consumer that
 * works with SIF3 DM objects. This class makes use of the knowledge of the Job Object and therefore can implement a number of
 * methods that are otherwise left to the developer (i.e. Eventing, Changes Since etc).
 * Functional Services have a number of methods that are specific for them such as phase operations. These methods are stubbed out
 * in this implementations similar to CRUD operations of the abstract consumer that works with Object Services.<br/>
 * It is assumed that the ConsumerLoader.initialise() method has been called before any methods of this class are called.If not then 
 * the behaviour of this class is not defined. In fact each call to any method of this class will first test if initialisation has 
 * succeeded and no action in any of the top level methods will be executed if the ConsumerLoader.initialise() hasn't been called before.
 * 
 * @author Joerg Huber
 */
public abstract class AbstractFunctionalServiceConsumer extends BaseConsumer implements FunctionalServiceConsumer, DelayedConsumer, EventConsumer<JobCollectionType>, Runnable
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public static final ModelObjectInfo JOBS_INFO = new ModelObjectInfo("jobs", JobCollectionType.class);
    public static final ModelObjectInfo JOB_INFO  = new ModelObjectInfo("job", JobType.class);

    private MarshalFactory marshaller = new InfraMarshalFactory();
    private UnmarshalFactory unmarshaller = new InfraUnmarshalFactory();

    /*--------------------------------------------------------------------*/
    /* Abstract method relating to Functional Service Type functionality. */
    /*--------------------------------------------------------------------*/
    /**
     * Must return the name of the Functional Service. This must match the value of an entry in the 
     * SIF3_JOB_TEMPLATE with the JOB_URL_NAME column. It is also the equivalent to the corresponding segment in
     * in the Functional Service URL (eg. .../serviceConnector/jobs/<ServiceURLNamePlural>).<br/><br/>
     * 
     * Example<br/>
     * https://sif3hub.edu.au/services/jobs/endofyearrollovers
     * 
     * @return A not null or empty value. The name of the functional service in its plural form.
     */
    public abstract String getServiceURLNamePlural();
    
    /**
     * Must return the name of the Functional Service. This must match the value of an entry in the 
     * SIF3_JOB_TEMPLATE with the JOB_URL_NAME column. It is also the equivalent to the corresponding segment in
     * in the Functional Service URL (eg. .../serviceConnector/jobs/<ServiceURLNamePlural>/<ServiceURLNameSingular>).<br/><br/>
     * 
     * Example<br/>
     * https://sif3hub.edu.au/services/jobs/endofyearrollovers/endofyearrollover (note there is no 's' in the singular form)
     * 
     * @return A not null or empty value. The name of the functional service in its singular form.
     */
    public abstract String getServiceURLNameSingular();
       
    /*---------------------------------------------------------------------*/
    /* Abstract method relating to DELAYED request response functionality. */
    /*---------------------------------------------------------------------*/
 
    /*----------------------------*/
    /*-- Delayed Job Operations --*/
    /*----------------------------*/
    
    /**
     * This method is called when a delayed create response for jobs is retrieved from the consumer's queue.<br/><br/>
     * 
     * @see sif3.common.interfaces.DelayedConsumer#onCreateMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
     * 
     * @param statusList See onCreateMany() method in DelayedConsumer class.
     * @param receipt See onCreateMany() method in DelayedConsumer class.
     */
    public abstract void processDelayedJobsCreate(MultiOperationStatusList<CreateOperationStatus> statusList, DelayedResponseReceipt receipt);
    
    /**
     * This method is called when a delayed delete jobs response is retrieved from the consumer's queue.<br/><br/>
     * 
     * @see sif3.common.interfaces.DelayedConsumer#onDeleteMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
     * 
     * @param statusList See onDeleteMany() method in DelayedConsumer class.
     * @param receipt See onDeleteMany() method in DelayedConsumer class.
     */
    public abstract void processDelayedJobsDelete(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt);
    
    /**
     * This method is called when a delayed jobs query response is retrieved from the consumer's queue.<br/><br/>
     * 
     * @see sif3.common.interfaces.DelayedConsumer#onQuery(java.lang.Object, sif3.common.model.PagingInfo, sif3.common.model.delayed.DelayedResponseReceipt)
     * 
     * @param jobs See onQuery() method in DelayedConsumer class.
     * @param pagingInfo See onQuery() method in DelayedConsumer class.
     * @param receipt See onQuery() method in DelayedConsumer class.
     */
    public abstract void processDelayedJobsQuery(JobCollectionType jobs, PagingInfo pagingInfo, DelayedResponseReceipt receipt);
    
    /*------------------------------*/
    /*-- Delayed Phase Operations --*/
    /*------------------------------*/
    
    /**
     * This method is called when a delayed phase query response is retrieved from the consumer's queue.<br/><br/>
     * 
     * @param phaseInfo Holds the jobID and phase name for which the delayed response is for.
     * @param phaseDataResponse Holds data that is returned. Data can be empty. The data is in its raw format, that being a string. Because
     *                          the framework is data model agnostic it cannot make any assumptions what the data is. It is an entirely
     *                          implementation specific piece of knowledge. Hence only the string is passed to the consumer. It is up to the
     *                          consumer to unmarshal the string into a suitable structure. For the unmarshal operation the mime type in this
     *                          parameter should be used. It indicates what the data's mime type is.
     * @param pagingInfo The paging information relating to the query result that is returned. Because a consumer may request
     *                   query results in pages it is necessary to pass that paging information back to the consumer as part
     *                   of this call. This may allow the consumer to determine how many pages it may expect as well as if it
     *                   has paged through all results.
     * @param receipt Metadata information about the response relating to the original request. This includes but is not
     *                limited to the zoneId, contextId, original request ID etc. as well as all HTTP Headers of the response.
     */
    public abstract void processDelayedPhaseQuery(PhaseInfo phaseInfo, PhaseDataResponse phaseDataResponse, PagingInfo pagingInfo, DelayedResponseReceipt receipt);
    
    /**
     * This method is called when a delayed phase create response is retrieved from the consumer's queue.<br/><br/>
     * 
     * @param phaseInfo Holds the jobID and phase name for which the delayed response is for.
     * @param phaseDataResponse Holds data that is returned. Data can be empty. The data is in its raw format, that being a string. Because
     *                          the framework is data model agnostic it cannot make any assumptions what the data is. It is an entirely
     *                          implementation specific piece of knowledge. Hence only the string is passed to the consumer. It is up to the
     *                          consumer to unmarshal the string into a suitable structure. For the unmarshal operation the mime type in this
     *                          parameter should be used. It indicates what the data's mime type is.
     * @param receipt Metadata information about the response relating to the original request. This includes but is not
     *                limited to the zoneId, contextId, original request ID etc. as well as all HTTP Headers of the response.
     */
    public abstract void processDelayedPhaseCreate(PhaseInfo phaseInfo, PhaseDataResponse phaseDataResponse, DelayedResponseReceipt receipt);
    
    /**
     * This method is called when a delayed phase update response is retrieved from the consumer's queue.<br/><br/>
     * 
     * @param phaseInfo Holds the jobID and phase name for which the delayed response is for.
     * @param phaseDataResponse Holds data that is returned. Data can be empty. The data is in its raw format, that being a string. Because
     *                          the framework is data model agnostic it cannot make any assumptions what the data is. It is an entirely
     *                          implementation specific piece of knowledge. Hence only the string is passed to the consumer. It is up to the
     *                          consumer to unmarshal the string into a suitable structure. For the unmarshal operation the mime type in this
     *                          parameter should be used. It indicates what the data's mime type is.
     * @param receipt Metadata information about the response relating to the original request. This includes but is not
     *                limited to the zoneId, contextId, original request ID etc. as well as all HTTP Headers of the response. 
     */
    public abstract void processDelayedPhaseUpdate(PhaseInfo phaseInfo, PhaseDataResponse phaseDataResponse, DelayedResponseReceipt receipt);

    /**
     * This method is called when a delayed phase delete response is retrieved from the consumer's queue.<br/><br/>
     * 
     * @param phaseInfo Holds the jobID and phase name for which the delayed response is for.
     * @param phaseDataResponse Holds data that is returned. Data can be empty. The data is in its raw format, that being a string. Because
     *                          the framework is data model agnostic it cannot make any assumptions what the data is. It is an entirely
     *                          implementation specific piece of knowledge. Hence only the string is passed to the consumer. It is up to the
     *                          consumer to unmarshal the string into a suitable structure. For the unmarshal operation the mime type in this
     *                          parameter should be used. It indicates what the data's mime type is.
     * @param receipt Metadata information about the response relating to the original request. This includes but is not
     *                limited to the zoneId, contextId, original request ID etc. as well as all HTTP Headers of the response. 
     */
    public abstract void processDelayedPhaseDelete(PhaseInfo phaseInfo, PhaseDataResponse phaseDataResponse, DelayedResponseReceipt receipt);

    /*------------------------------------------------------*/
    /* Abstract method relating to Job Event functionality. */
    /*------------------------------------------------------*/
    /**
     * This method is called when a consumer service has received a Job event. This class does implement the actual onEvent() 
     * method from the event interface. It may do some additional work for house keeping purpose so the original onEvent() id processed
     * as part of this class but then this method is called so that the actual consumer can do its work as required.
     * 
     * @param sifEvent The event data that has been received and shall be processed by the consumer. This parameter also holds
     *                 the zone and context in the limitToZoneCtxList property. It will always only hold one entry in that
     *                 list. So the zone can be retrieved with the following call: sifEvent.getLimitToZoneCtxList().get(0).getZone().
     *                 However for simplicity reasons the zone and context is already extracted and passed to this method in the
     *                 zone anc context parameter.
     * @param zone The zone from which the event was received from. The framework ensures that this is never null.
     * @param context The context from which the event was received from. The framework ensures that this is never null.
     * @param metadata Additional metadata that is known for the event. Typical values include custom HTTP headers, sourceName etc.
     * @param msgReadID The ID of the SIF queue reader. It is informative only and is only of use where there are multiple concurrent 
     *                  subscribers on a message queue.
     * @param consumerID The consumer ID that has been used to receive the event from the event queue. It is informative
     *                   only and is only of use where there are multiple event subscribers enabled.
     */
    public abstract void processJobEvent(SIFEvent<JobCollectionType> sifEvent, SIFZone zone, SIFContext context, EventMetadata metadata, String msgReadID, String consumerID);

    /*-------------------------*/
    /* Implementation of Class */
    /*-------------------------*/

    /**
     * Constructor.
     */
    public AbstractFunctionalServiceConsumer()
    {
        super();
    }

    /*------------------------------------*/
    /* DataModelLink Interface Methods. --*/
    /*------------------------------------*/
    
    /*
     * This DataModelLink is configured for the Job Object, which is an infrastructure object. Because Functional Services are not
     * Object Services and therefore we know what object we deal with.
     */
    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMarshaller()
     */
    @Override
    public MarshalFactory getMarshaller()
    {
        return marshaller;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getUnmarshaller()
     */
    @Override
    public UnmarshalFactory getUnmarshaller()
    {
        return unmarshaller;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getSingleObjectClassInfo()
    {
        return JOB_INFO;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
        return JOBS_INFO;
    }

    /*------------------------------------*/
    /* MinimalConsumer Interface Methods. --*/
    /*------------------------------------*/

    /* (non-Javadoc)
     * @see sif3.common.interfaces.MinimalConsumer#retrievByPrimaryKey(java.lang.String, java.util.List, sif3.common.model.CustomParameters)
     */
    @Override
    public List<Response> retrievByPrimaryKey(String resourceID, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");
        nullJobNameMethodCheck(getServiceURLNameSingular(), "getServiceURLNameSingular()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        List<Response> responses = new ArrayList<Response>();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return responses;
        }
        
        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(AccessRight.QUERY, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), null);
            if (error == null) //all good
            {
                responses.add(getClient(getConsumerEnvironment()).getJob(resourceID, getHeaderProperties(false, customParameters), urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext()));
            }
            else //pretend to have received a 'fake' error Response
            {
                responses.add(createErrorResponse(error));
            }
        }                   

        timer.finish();
        logger.debug("Time taken to call and process 'retrieve by job by jobID key' for "+getServiceURLNamePlural()+"/"+resourceID+": "+timer.timeTaken()+"ms");
        return responses;
    }
    
    /*
     * Convenience method. The same as above but but uses JobId in method name which is more intuitive than the generic
     * interface method..
     */
    public List<Response> retrievByJobID(String jobID, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        return retrievByPrimaryKey(jobID, zoneCtxList, customParameters);
    }

    /*
     * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
     */
    public List<Response> retrievByJobId(String resourceID, List<ZoneContextInfo> zoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        return retrievByPrimaryKey(resourceID, zoneCtxList, null);
    }


    /* (non-Javadoc)
     * @see sif3.common.interfaces.MinimalConsumer#retrieve(sif3.common.model.PagingInfo, java.util.List, sif3.common.header.HeaderValues.RequestType, sif3.common.header.HeaderValues.QueryIntention, sif3.common.model.CustomParameters)
     */
    @Override
    public List<Response> retrieve(PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, RequestType requestType, QueryIntention queryIntention, CustomParameters customParameters)
            throws PersistenceException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        List<Response> responses = new ArrayList<Response>();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return responses;
        }
        
        // Set default set of HTTP Header fields
        HeaderProperties hdrProps = getHeaderProperties(false, customParameters);
        
        // Add query intention to headers.
        addQueryIntentionToHeaders(hdrProps, queryIntention);
        
        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(AccessRight.QUERY, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
            if (error == null) //all good => Send request
            {
                Response response = getClient(getConsumerEnvironment()).getJobs(pagingInfo, hdrProps, urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext(), requestType);

                // Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
                finaliseDelayedReceipt(response.getDelayedReceipt(), getServiceURLNamePlural(), ServiceType.FUNCTIONAL, ResponseAction.QUERY);
                responses.add(response);
            }
            else //pretend to have received a 'fake' error Response
            {
                responses.add(createErrorResponse(error));
            }
        }                   

        timer.finish();
        logger.debug("Time taken to call and process 'retrieve all Jobs' for "+getServiceURLNamePlural()+": "+timer.timeTaken()+"ms");
        return responses;
    }

    /*
     * Convenience method. The same as above but but uses JobId in method name which is more intuitive than the generic
     * interface method..
     */
    public List<Response> retrieveJobs(PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, RequestType requestType, QueryIntention queryIntention, CustomParameters customParameters)
            throws PersistenceException, ServiceInvokationException
    {
        return retrieve(pagingInfo, zoneCtxList, requestType, queryIntention, customParameters);
    }

    /*
     * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
     */
    public List<Response> retrieveJobs(PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, RequestType requestType, QueryIntention queryIntention)
            throws PersistenceException, ServiceInvokationException
    {
        return retrieve(pagingInfo, zoneCtxList, requestType, queryIntention, null);
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.MinimalConsumer#getServiceInfo(sif3.common.model.PagingInfo, java.util.List, sif3.common.model.CustomParameters)
     */
    @Override
    public List<Response> getServiceInfo(PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws PersistenceException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");
        
        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        List<Response> responses = new ArrayList<Response>();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return responses;
        }
        
        // Set default set of HTTP Header fields
        HeaderProperties hdrProps = getHeaderProperties(false, customParameters);
        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(AccessRight.QUERY, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), RequestType.IMMEDIATE);
            if (error == null) //all good => Send request
            {
                Response response = getClient(getConsumerEnvironment()).getServiceInfo(pagingInfo, hdrProps, urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext());

                // Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
                finaliseDelayedReceipt(response.getDelayedReceipt(), getServiceURLNamePlural(), ServiceType.FUNCTIONAL, ResponseAction.HEAD);
                responses.add(response);
            }
            else //pretend to have received a 'fake' error Response
            {
                responses.add(createErrorResponse(error));
            }
        }                   

        timer.finish();
        logger.debug("Time taken to call and process 'retrieve service info' for "+getServiceURLNamePlural()+": "+timer.timeTaken()+"ms");
        return responses;
    }

    /*
     * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
     */
    public List<Response> getServiceInfo(PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList) throws PersistenceException, ServiceInvokationException
    {
        return getServiceInfo(pagingInfo, zoneCtxList, null);
    }
    
    /*
     * (non-Javadoc)
     * @see sif3.common.consumer.MinimalConsumer#finalise()
     */
    @Override
    public void finalise()
    {
        // Clean up stuff for this consumer
        
        // Clean up the BaseConsumer
        super.finalise();

        // Clean up the specific consumer implementation
        shutdown();
    }

    /*--------------------------------------*/
    /* DelayedConsumer Interface Methods. --*/
    /*--------------------------------------*/

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DelayedConsumer#onCreateMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void onCreateMany(MultiOperationStatusList<CreateOperationStatus> statusList, DelayedResponseReceipt receipt)
    {
        processDelayedJobsCreate(statusList, receipt);
    }


    /* (non-Javadoc)
     * @see sif3.common.interfaces.DelayedConsumer#onDeleteMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void onDeleteMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt)
    {
        processDelayedJobsDelete(statusList, receipt);
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DelayedConsumer#onQuery(java.lang.Object, sif3.common.model.PagingInfo, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void onQuery(Object dataObject, PagingInfo pagingInfo, DelayedResponseReceipt receipt)
    {
        if (dataObject != null)
        {
            if (dataObject instanceof JobCollectionType)
            {
                processDelayedJobsQuery((JobCollectionType)dataObject, pagingInfo, receipt);
            }
            else
            {
                // We have received a job query response but the data is not of type JobCollectionType. This is not good. We log an error an
                // also call the onError method to indicate to the consumer that some odd stuff has come through.
                processDelayedError(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Invalid Data", "A delayed response to a Job Query has been received but the data is of type "+dataObject.getClass().getSimpleName()+" instead of JobCollectiontype.", "Consumer"), receipt);
            }
        }
        else
        {
            processDelayedJobsQuery(null, pagingInfo, receipt);
        }
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DelayedConsumer#onError(sif3.common.ws.ErrorDetails, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void onError(ErrorDetails error, DelayedResponseReceipt receipt)
    {
        processDelayedError(error, receipt);
    }

    /*
     * The following methods are not applicable for Job Objects. No implementation required.
     */
    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.DelayedConsumer#onServicePath(java.lang.Object, sif3.common.model.QueryCriteria, sif3.common.model.PagingInfo, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void onServicePath(Object dataObject, QueryCriteria queryCriteria, PagingInfo pagingInfo, DelayedResponseReceipt receipt)
    {
        //There is no ServicePath functionality for functional services or job object defined, so we do not need to implement anything here.
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DelayedConsumer#onUpdateMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void onUpdateMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt)
    {
        //Job Objects cannot be updated. No implementation needed here.
    }

    /*------------------------------------*/
    /* EventConsumer Interface Methods. --*/
    /*------------------------------------*/
   
    @Override
    public void onEvent(SIFEvent<JobCollectionType> sifEvent, EventMetadata metadata, String msgReadID, String consumerID)
    {
        if (sifEvent != null)
        {
            // We know from the framework that zone and context is never null. For the time being we just log the event.
            processJobEvent(sifEvent, sifEvent.getLimitToZoneCtxList().get(0).getZone(), sifEvent.getLimitToZoneCtxList().get(0).getContext(), metadata, msgReadID, consumerID);
        }
    }

    @Override
    public SIFEvent<JobCollectionType> createEventObject(Object sifObjectList, EventAction eventAction, UpdateType updateType)
    {
        if (sifObjectList != null)
        {
            if (sifObjectList instanceof JobCollectionType)
            {
                int size = ((JobCollectionType)sifObjectList).getJob().size();
                return new SIFEvent<JobCollectionType>((JobCollectionType)sifObjectList, eventAction, updateType, size);
            }
            else
            {
                logger.error("The given event data is not of type JobCollectionType as expected. Cannot create event object. Return null");
            }
        }
        else
        {
            logger.error("The given job event data is null. Cannot create job event object. Return null");          
        }
        return null; // if something is wrong then we get here.
    }

    /*------------------------------------------------*/
    /* FunctionalServiceConsumer Interface Methods. --*/
    /*------------------------------------------------*/

    /*--------------------*/
    /*-- Job Operations --*/
    /*--------------------*/

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceConsumer#createJob(sif3.common.model.job.JobCreateRequestParameter, java.util.List, sif3.common.model.CustomParameters)
     */
    @Override
    public List<Response> createJob(JobCreateRequestParameter createJobRequest, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");
        nullJobNameMethodCheck(getServiceURLNameSingular(), "getServiceURLNameSingular()");
        
        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        List<Response> responses = new ArrayList<Response>();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return responses;
        }

        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(AccessRight.CREATE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), null);
            if (error == null) //all good
            {
                responses.add(getClient(getConsumerEnvironment()).createJob(createJobRequest, getHeaderProperties(true, customParameters), urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext()));
            }
            else //pretend to have received a 'fake' error Response
            {
                responses.add(createErrorResponse(error));
            }
        }                   

        timer.finish();
        logger.debug("Time taken to call and process 'createJob' for "+getServiceURLNamePlural()+": "+timer.timeTaken()+"ms");
        return responses;
    }

    /*
     * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
     */
    public List<Response> createJob(JobCreateRequestParameter createJobRequest, List<ZoneContextInfo> zoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        return createJob(createJobRequest, zoneCtxList, null);
    }

    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceConsumer#deleteJob(java.lang.String, java.util.List, sif3.common.model.CustomParameters)
     */
    @Override
    public List<Response> deleteJob(String jobID, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        List<Response> responses = new ArrayList<Response>();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return responses;
        }

        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(AccessRight.DELETE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), null);
            if (error == null) //all good
            {
                responses.add(getClient(getConsumerEnvironment()).removeJob(jobID, getHeaderProperties(false, customParameters), urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext()));
            }
            else //pretend to have received a 'fake' error Response
            {
                responses.add(createErrorResponse(error));
            }
        }                   

        timer.finish();
        logger.debug("Time taken to call and process 'delete job by jobID' for "+getServiceURLNamePlural()+"/"+jobID+": "+timer.timeTaken()+"ms");
        return responses;
    }

    /*
     * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
     */
    public List<Response> deleteJob(String jobID, List<ZoneContextInfo> zoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        return deleteJob(jobID, zoneCtxList, null);
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceConsumer#createJobs(java.util.List, java.util.List, sif3.common.header.HeaderValues.RequestType, sif3.common.model.CustomParameters)
     */
    @Override
    public List<BulkOperationResponse<CreateOperationStatus>> createJobs(List<JobCreateRequestParameter> createMultipleJobsRequest,
                                                                         List<ZoneContextInfo> zoneCtxList, 
                                                                         RequestType requestType,
                                                                         CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        List<BulkOperationResponse<CreateOperationStatus>> responses = new ArrayList<BulkOperationResponse<CreateOperationStatus>>();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return responses;
        }

        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(AccessRight.CREATE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
            if (error == null) //all good => Send request.
            {
                BulkOperationResponse<CreateOperationStatus> response = getClient(getConsumerEnvironment()).createJobs(createMultipleJobsRequest, getHeaderProperties(true, customParameters), urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext(), requestType);

                // Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
                finaliseDelayedReceipt(response.getDelayedReceipt(), getServiceURLNamePlural(), ServiceType.FUNCTIONAL, ResponseAction.CREATE);
                responses.add(response);
            }
            else //pretend to have received a 'fake' error Response
            {
                responses.add(makeBulkErrorResponseForCreates(error));
            }
        }                   

        timer.finish();
        logger.debug("Time taken to call and process 'createJobs' for "+getServiceURLNamePlural()+": "+timer.timeTaken()+"ms");
        return responses;
    }
    
    /*
     * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
     */
    public List<BulkOperationResponse<CreateOperationStatus>> createJobs(List<JobCreateRequestParameter> createMultipleJobsRequest,
                                                                         List<ZoneContextInfo> zoneCtxList, 
                                                                         RequestType requestType)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        return createJobs(createMultipleJobsRequest, zoneCtxList, requestType, null);
    }


    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceConsumer#deleteJobs(java.util.List, java.util.List, sif3.common.header.HeaderValues.RequestType, sif3.common.model.CustomParameters)
     */
    @Override
    public List<BulkOperationResponse<OperationStatus>> deleteJobs(List<String> jobIDs,
                                                                   List<ZoneContextInfo> zoneCtxList, 
                                                                   RequestType requestType,
                                                                   CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        List<BulkOperationResponse<OperationStatus>> responses = new ArrayList<BulkOperationResponse<OperationStatus>>();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return responses;
        }

        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(AccessRight.DELETE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
            if (error == null) //all good => Send request
            {
                BulkOperationResponse<OperationStatus> response = getClient(getConsumerEnvironment()).removeJobs(jobIDs, getHeaderProperties(false, customParameters), urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext(), requestType);

                // Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
                finaliseDelayedReceipt(response.getDelayedReceipt(), getMultiObjectClassInfo().getObjectName(), ServiceType.FUNCTIONAL, ResponseAction.DELETE);
                responses.add(response);
            }
            else //pretend to have received a 'fake' error Response
            {
                responses.add(makeBulkErrorResponse(error));
            }
        }

        timer.finish();
        logger.debug("Time taken to call and process 'removeJobs' for "+getServiceURLNamePlural()+": "+timer.timeTaken()+"ms");
        return responses;
    }

    /*
     * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
     */
    public List<BulkOperationResponse<OperationStatus>> deleteJobs(List<String> jobIDs,
                                                                   List<ZoneContextInfo> zoneCtxList, 
                                                                   RequestType requestType)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        return deleteJobs(jobIDs, zoneCtxList, requestType, null);
    }

    /*----------------------*/
    /*-- Phase Operations --*/
    /*----------------------*/
    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceConsumer#retrieveDataFromPhase(sif3.common.model.job.PhaseInfo, javax.ws.rs.core.MediaType, sif3.common.model.PagingInfo, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.header.HeaderValues.RequestType, sif3.common.model.CustomParameters)
     */
    @Override
    public Response retrieveDataFromPhase(PhaseInfo phaseInfo, 
                                          PayloadMetadata returnPayloadMetadata,
                                          PagingInfo pagingInfo,
                                          QueryIntention queryIntention,
                                          SIFZone zone, 
                                          SIFContext context, 
                                          RequestType requestType,
                                          CustomParameters customParameters)
            throws IllegalArgumentException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        Response response = new Response();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return response;
        }
        zone = getFinalZone(zone);
        context = getFinalContext(context);
        
        ErrorDetails error = checkAccessToFunctionalService(getServiceURLNamePlural(), zone, context, requestType);
        if (error == null) //all good => Send request
        {
            // Set default set of HTTP Header fields
            HeaderProperties hdrProps = getHeaderProperties(false, customParameters);
            
            // Add query intention to headers.
            addQueryIntentionToHeaders(hdrProps, queryIntention);

            response = getClient(getConsumerEnvironment()).retrieveDataFromPhase(phaseInfo, returnPayloadMetadata, pagingInfo, hdrProps, urlQueryParameter, zone, context, requestType);

            // Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
            finaliseDelayedReceipt(response.getDelayedReceipt(), getServiceURLNamePlural(), ServiceType.FUNCTIONAL, ResponseAction.QUERY);
        }
        else //pretend to have received a 'fake' error Response
        {
            response.setError(error);
        }
        
        timer.finish();
        logger.debug("Time taken to call and process 'retrive data from phase for job "+getServiceURLNamePlural()+"/"+phaseInfo.getJobID()+"/"+phaseInfo.getPhaseName()+": "+timer.timeTaken()+"ms");

        return response;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceConsumer#createDataInPhase(sif3.common.model.job.PhaseInfo, sif3.common.ws.job.PhaseDataRequest, javax.ws.rs.core.MediaType, boolean, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.header.HeaderValues.RequestType, sif3.common.model.CustomParameters)
     */
    @Override
    public Response createDataInPhase(PhaseInfo phaseInfo, 
                                      PhaseDataRequest phaseDataRequest,
                                      PayloadMetadata returnPayloadMetadata,
                                      boolean useAdvisory, 
                                      SIFZone zone, 
                                      SIFContext context,
                                      RequestType requestType, 
                                      CustomParameters customParameters)
            throws IllegalArgumentException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        Response response = new Response();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return response;
        }
        zone = getFinalZone(zone);
        context = getFinalContext(context);
        
        ErrorDetails error = checkAccessToFunctionalService(getServiceURLNamePlural(), zone, context, requestType);
        if (error == null) //all good => Send request
        {
            response = getClient(getConsumerEnvironment()).createDataInPhase(phaseInfo, phaseDataRequest, returnPayloadMetadata, useAdvisory, getHeaderProperties(true, customParameters), urlQueryParameter, zone, context, requestType);

            // Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
            finaliseDelayedReceipt(response.getDelayedReceipt(), getServiceURLNamePlural(), ServiceType.FUNCTIONAL, ResponseAction.CREATE);
        }
        else //pretend to have received a 'fake' error Response
        {
            response.setError(error);
        }
        
        timer.finish();
        logger.debug("Time taken to call and process 'create data in phase for job "+getServiceURLNamePlural()+"/"+phaseInfo.getJobID()+"/"+phaseInfo.getPhaseName()+": "+timer.timeTaken()+"ms");

        return response;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceConsumer#updateDataInPhase(sif3.common.model.job.PhaseInfo, sif3.common.ws.job.PhaseDataRequest, javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.header.HeaderValues.RequestType, sif3.common.model.CustomParameters)
     */
    @Override
    public Response updateDataInPhase(PhaseInfo phaseInfo, 
                                      PhaseDataRequest phaseDataRequest,
                                      PayloadMetadata returnPayloadMetadata,
                                      SIFZone zone, 
                                      SIFContext context, 
                                      RequestType requestType,
                                      CustomParameters customParameters)
            throws IllegalArgumentException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        Response response = new Response();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return response;
        }
        zone = getFinalZone(zone);
        context = getFinalContext(context);
        
        ErrorDetails error = checkAccessToFunctionalService(getServiceURLNamePlural(), zone, context, requestType);
        if (error == null) //all good => Send request
        {
            response = getClient(getConsumerEnvironment()).updateDataInPhase(phaseInfo, phaseDataRequest, returnPayloadMetadata, getHeaderProperties(false, customParameters), urlQueryParameter, zone, context, requestType);

            // Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
            finaliseDelayedReceipt(response.getDelayedReceipt(), getServiceURLNamePlural(), ServiceType.FUNCTIONAL, ResponseAction.UPDATE);
        }
        else //pretend to have received a 'fake' error Response
        {
            response.setError(error);
        }
        
        timer.finish();
        logger.debug("Time taken to call and process 'update data in phase for job "+getServiceURLNamePlural()+"/"+phaseInfo.getJobID()+"/"+phaseInfo.getPhaseName()+": "+timer.timeTaken()+"ms");

        return response;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceConsumer#deleteDataInPhase(sif3.common.model.job.PhaseInfo, sif3.common.ws.job.PhaseDataRequest, javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.header.HeaderValues.RequestType, sif3.common.model.CustomParameters)
     */
    @Override
    public Response deleteDataInPhase(PhaseInfo phaseInfo, 
                                      PhaseDataRequest phaseDataRequest,
                                      PayloadMetadata returnPayloadMetadata,
                                      SIFZone zone, 
                                      SIFContext context, 
                                      RequestType requestType,
                                      CustomParameters customParameters)
            throws IllegalArgumentException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        Response response = new Response();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return response;
        }
        zone = getFinalZone(zone);
        context = getFinalContext(context);
        
        ErrorDetails error = checkAccessToFunctionalService(getServiceURLNamePlural(), zone, context, requestType);
        if (error == null) //all good => Send request
        {
            response = getClient(getConsumerEnvironment()).deleteDataInPhase(phaseInfo, phaseDataRequest, returnPayloadMetadata, getHeaderProperties(false, customParameters), urlQueryParameter, zone, context, requestType);

            // Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
            finaliseDelayedReceipt(response.getDelayedReceipt(), getServiceURLNamePlural(), ServiceType.FUNCTIONAL, ResponseAction.DELETE);
        }
        else //pretend to have received a 'fake' error Response
        {
            response.setError(error);
        }
        
        timer.finish();
        logger.debug("Time taken to call and process 'delete data in phase for job "+getServiceURLNamePlural()+"/"+phaseInfo.getJobID()+"/"+phaseInfo.getPhaseName()+": "+timer.timeTaken()+"ms");

        return response;
    }

    /*----------------------------*/
    /*-- Phase State Operations --*/
    /*----------------------------*/
    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceConsumer#updatePhaseState(sif3.common.model.job.PhaseInfo, sif3.common.CommonConstants.PhaseState, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.CustomParameters)
     */
    @Override
    public Response updatePhaseState(PhaseInfo phaseInfo, 
                                     PhaseState newState, 
                                     SIFZone zone,
                                     SIFContext context, 
                                     CustomParameters customParameters)
            throws IllegalArgumentException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        Response response = new Response();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return response;
        }
        zone = getFinalZone(zone);
        context = getFinalContext(context);
        
        ErrorDetails error = checkAccessToFunctionalService(getServiceURLNamePlural(), zone, context, RequestType.IMMEDIATE);
        if (error == null) //all good => Send request
        {
            response = getClient(getConsumerEnvironment()).updatePhaseState(phaseInfo, newState, getHeaderProperties(false, customParameters), urlQueryParameter, zone, context);
        }
        else //pretend to have received a 'fake' error Response
        {
            response.setError(error);
        }
        
        timer.finish();
        logger.debug("Time taken to call and process 'update phase state for job "+getServiceURLNamePlural()+"/"+phaseInfo.getJobID()+"/"+phaseInfo.getPhaseName()+": "+timer.timeTaken()+"ms");

        return response;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceConsumer#getPhaseStates(sif3.common.model.job.PhaseInfo, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.CustomParameters)
     */
    @Override
    public Response getPhaseStates(PhaseInfo phaseInfo, 
                                   SIFZone zone, 
                                   SIFContext context,
                                   CustomParameters customParameters)
            throws IllegalArgumentException, ServiceInvokationException
    {
        nullJobNameMethodCheck(getServiceURLNamePlural(), "getServiceURLNamePlural()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
        Response response = new Response();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return response;
        }
        zone = getFinalZone(zone);
        context = getFinalContext(context);
        
        ErrorDetails error = checkAccessToFunctionalService(getServiceURLNamePlural(), zone, context, RequestType.IMMEDIATE);
        if (error == null) //all good => Send request
        {
            response = getClient(getConsumerEnvironment()).getPhaseStates(phaseInfo, getHeaderProperties(false, customParameters), urlQueryParameter, zone, context);
        }
        else //pretend to have received a 'fake' error Response
        {
            response.setError(error);
        }
        
        timer.finish();
        logger.debug("Time taken to call and process 'get phase states for job "+getServiceURLNamePlural()+"/"+phaseInfo.getJobID()+"/"+phaseInfo.getPhaseName()+": "+timer.timeTaken()+"ms");

        return response;
    }
    
    /*----------------------------------------*/
    /* Implemented Method for Multi-threading */
    /*----------------------------------------*/

    /**
     * This method is all that is needed to run the subscriber in its own thread.
     */
    @Override
    public final void run()
    {
//      logger.debug("============================\n Start "+getConsumerName()+" worker thread.\n======================================");
    }
    
    /*----------------------------*/
    /*-- Other required methods --*/
    /*----------------------------*/
    /**
     * This method is is called when the async processor is initialised. It passes all subscription services for the
     * given types of service across all zones for the connected environment to this method. It allows the specific
     * async consumer implementation to remove some of the subscription services it is not interested in. Basically it allows
     * the implementor to filter out un-needed services before the this consumer subscribes to the local queues. Only
     * delayed responses for the returned list of service info will then be received by the particular service. Most standard
     * implementations would not require any overriding of this method. If a specific implementation wishes to filter out
     * some of the environment provided subscriptions then the sub-class of this class should override this method.
     * 
     * @param allServices A list of services for this that is allowed to subscribe delayed responses across the environment of this consumer.
     * 
     * @return The final list of services for which this consumer class shall subscribe to.
     */
    public List<ServiceInfo> filterApprovedCRUDServices(List<ServiceInfo> allServices)
    {
        return allServices;
    }
    
    /*
     * Returns all CRUD services for which this consumer has access to. This should be a list of different zones, contexts and service types.
     * It returns all FUNCTIONAL services where the consumer has CREATE, DELETE or QUERY as approved in the ACL. Note Functional services do not
     * have UPDATE rights.
     */
    protected final List<ServiceInfo> getAllApprovedCRUDServices()
    {
        List<ServiceInfo> allServices = new ArrayList<ServiceInfo>();
        
        // Get all Functional Services. Note the service name is the name of the functional service
        allServices.addAll(getAllApprovedServicesForRights(getServiceURLNamePlural(), ServiceType.FUNCTIONAL, AccessRight.CREATE, AccessRight.DELETE, AccessRight.QUERY));
        
        return filterApprovedCRUDServices(allServices);
    }   

    /**
     * This method is is called when the event processor is initialised. It passes all subscription services for the
     * given FUNCTIONAL service across all zones for the connected environment to this method. It allows the specific FUNCTIONAL
     * event consumer implementation to remove some of the subscription services it is not interested in. Basically it allows
     * the implementor to filter out un-needed subscriptions before the event processor subscribes to the queues. Only
     * events for the returned list of service info will then be received by the particular OBJECT service. Most standard
     * implementations would not require any overriding of this method. If a specific implementation wishes to filter out
     * some of the environment provided subscriptions then the sub-class of this class should override this method.
     * 
     * @param envEventServices A list of services for this that is allowed to subscribe to events across the environment of this consumer.
     * 
     * @return The final list of services for which this consumer class shall subscribe to.
     */
    public List<ServiceInfo> filterEventServices(List<ServiceInfo> envEventServices)
    {
        return envEventServices;
    }
    
    /*
     * Return all FUNCTIONAL services that have the right of SUBSCRIBE is set to APPROVED in the ACL.
     */
    protected final List<ServiceInfo> getEventServices()
    {
        //Note the service name is the name of the functional service
        return filterEventServices(getAllApprovedServicesForRights(getServiceURLNamePlural(), ServiceType.FUNCTIONAL, AccessRight.SUBSCRIBE));
    }

    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    private JobClient getClient(ConsumerEnvironment envInfo) throws IllegalArgumentException
    {
        URI baseURI = envInfo.getConnectorBaseURI(ConsumerEnvironment.ConnectorName.servicesConnector);
        if (baseURI == null)
        {
            logger.error(ConsumerEnvironment.ConnectorName.servicesConnector.toString()+" not defined for environment "+envInfo.getEnvironmentName());
            return null;
        }
        else
        {
            return new JobClient(ConsumerEnvironmentManager.getInstance(),getServiceURLNamePlural(), getServiceURLNameSingular());
        }
    }
    
    private HeaderProperties getHeaderProperties(boolean isCreateOperation, CustomParameters customParameters)
    {
      return getHeaderProperties(isCreateOperation, ServiceType.FUNCTIONAL, customParameters);
    }
    
    /*
     * Will perform hasAccess() and requestTypeSupported() checks. This is a convenience method, so that not each operation has to
     * call the two methods sequentially and manage all the flow.
     */
    private ErrorDetails allClientChecks(AccessRight right, AccessType accessType, SIFZone zone, SIFContext context, RequestType requestType) 
    {
      return allClientChecks(getServiceURLNamePlural(), ServiceType.FUNCTIONAL, right, accessType, zone, context, requestType);
    }

    private void nullJobNameMethodCheck(String serviceURLName, String methodName) throws IllegalArgumentException
    {
        if (StringUtils.isEmpty(serviceURLName))
        {
            throw new IllegalArgumentException(methodName+" method not implemented correctly. Returns null or empty which is invalid.");
        }
    }
}
