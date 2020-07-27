/*
 * AbstractNamedQueryConsumer.java
 * Created: 18 Dec 2018
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
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import au.com.systemic.framework.utils.Timer;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.QueryIntention;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.header.ResponseHeaderConstants;
import sif3.common.interfaces.DelayedConsumer;
import sif3.common.interfaces.NamedQueryConsumer;
import sif3.common.model.ACL.AccessRight;
import sif3.common.model.ACL.AccessType;
import sif3.common.model.CustomParameters;
import sif3.common.model.PagingInfo;
import sif3.common.model.PayloadMetadata;
import sif3.common.model.QueryCriteria;
import sif3.common.model.QueryTemplateInfo;
import sif3.common.model.SchemaInfo;
import sif3.common.model.ServiceInfo;
import sif3.common.model.StringPayload;
import sif3.common.model.URLQueryParameter;
import sif3.common.model.ZoneContextInfo;
import sif3.common.model.delayed.DelayedResponseReceipt;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.common.ws.model.MultiOperationStatusList;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.rest.client.NameQueryClient;

/**
 * @author Joerg Huber
 *
 */
public abstract class AbstractNamedQueryConsumer extends BaseConsumer implements NamedQueryConsumer, DelayedConsumer, Runnable
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /*---------------------------------------------------------------------*/
    /* Abstract method relating to Named Query Service Type functionality. */
    /*---------------------------------------------------------------------*/
    /**
     * Must return the name of the Named Query Service as it is known in the Environment. It is also the equivalent to 
     * the corresponding segment in the Named Query Service URL (eg. .../<requestConnector>/<ServiceURLName>).<br/><br/>
     * 
     * Example<br/>
     * https://sif3hub.edu.au/requests/StudentsInYear
     * 
     * @return A not null or empty value. The name of the named query service.
     */
    public abstract String getNamedQueryName();
    
    /*---------------------------------------------------------------------*/
    /* Abstract method relating to DELAYED request response functionality. */
    /*---------------------------------------------------------------------*/
    
    /**
     * This method is called when a delayed named query response is retrieved from the consumer's queue.<br/><br/>
     * 
     * @param responseData Holds data that is returned. Data can be empty. The data is in its raw format, that being a string. Because
     *                     the framework is data model agnostic it cannot make any assumptions what the data is. It is an entirely
     *                     implementation specific piece of knowledge. Hence only the string is passed to the consumer. It is up to the
     *                     consumer to unmarshal the string into a suitable structure. For the unmarshal operation the mime type and 
     *                     optionally the schema information in this parameter should be used. It indicates what the data's mime type is.
     * @param pagingInfo The paging information relating to the query result that is returned. Because a consumer may request
     *                   query results in pages it is necessary to pass that paging information back to the consumer as part
     *                   of this call. This may allow the consumer to determine how many pages it may expect as well as if it
     *                   has paged through all results.
     * @param receipt Metadata information about the response relating to the original request. This includes but is not
     *                limited to the zoneId, contextId, original request ID etc. as well as all HTTP Headers of the response.
     *                Another important piece of information held in this parameter is the service name which is the name of
     *                the named query as it appears in the environment.
     */
    public abstract void processDelayedNamedQuery(StringPayload responseData, PagingInfo pagingInfo, DelayedResponseReceipt receipt);

    /**
     * Constructor.
     */
    public AbstractNamedQueryConsumer()
    {
        super();
    }
    
    /**
     * Convenience method. This is the method the actual consumer should be calling. It will call the appropriate low level
     * methods to translate it to the correct internal methods. The actual consumer should not call the retrieve() method.
     * 
     * @param namedQueryParameters Holds all parameters that are applicable for the named query. The HashMap is made of name/value pairs.
     *                             It is important that the values of the parameters should be URL encoded. This parameter can be null where 
     *                             there are no Named Query parameters required.
     * @param returnPayloadMetadata The mime type and optional schema info the response data is expected to be returned as. 
     *                              It is expected that the consumer provides that and the provider should attempt to marshal the data
     *                              to the given mime type, potentially using the given schema info, and return the resulting string
     *                              in the requested format. If the provider cannot marshal the data to the requested mime type then an
     *                              appropriate error is returned to this consumer (eg. HTTP Status 415 - Unsupported Media Type).
     *                              If this parameter is NULL the framework will simply use the getResponseMediaType() method to 
     *                              determine the response mime type. The getResponseMediaType() method, if not overridden, returns the 
     *                              value of the env.mediaType property of the consumer's property.
     * @param pagingInfo Page information to be set for the provider to determine which results to return.
     * @param zoneCtxList If this List is null or empty then it is assumed that the operation is performed for the default Zone and Context for the 
     *                    connected. If this list is not empty then the action is performed for all Zone and Context listed. If a given 
     *                    environment has more than one zone and/or context and the operation shall be performed in any number of them 
     *                    then each combination must be a separate entry in this list.
     * @param requestType Indicating if the retrieve request is synchronous (IMMEDIATE) or if it shall be shall executed asynchronously (DELAYED).
     * @param queryIntention Indicating what the intention of the query and follow-up queries is. Can be set to null which
     *                       will default to 'ONE-OFF'
     * @param customParameters Custom HTTP Header fields and Custom URL Query parameters that will be added to the request.
     *                         If any of the HTTP header fields correspond to a SIF pre-defined HTTP header that is managed
     *                         by this framework then the value in this parameter will be overridden with the internally
     *                         managed HTTP header field. The same applies to the URL Query parameters. This parameter can
     *                         be null.
     *                         IMPORTANT: DO NOT SET THE NAMED QUERY PARAMETERS IN THIS PARAMTER. THEY SHOULD BE SET IN THE namedQueryParameters PARAMETER.
     * 
     * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then only one response will be returned for the
     *         service in the default zone and context. Because the framework is agnostic to the data that is returned for a named query the 
     *         Response.dataObjectType will be set to "String" and the Response.dataObject will hold the string representation of the returned payload. 
     *         It is up to the caller of this method to potentially marshal that payload into an appropriate object which should match the mime type of the
     *         returnMimeType.
     *         
     * @throws PersistenceException Some data could not be retrieved. An error log entry is performed and the message of the exceptions holds some info.
     * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
     * 
     */
    public List<Response> retrieveDataFromNamedQuery(HashMap<String, String> namedQueryParameters, 
                                                     PayloadMetadata returnPayloadMetadata, 
                                                     PagingInfo pagingInfo, 
                                                     List<ZoneContextInfo> zoneCtxList, 
                                                     RequestType requestType, 
                                                     QueryIntention queryIntention, 
                                                     CustomParameters customParameters)
            throws PersistenceException, ServiceInvokationException
    {
        if (StringUtils.isEmpty(getNamedQueryName())) // means that getNamedQueryName() is not implemented correctly.
        {
            throw new ServiceInvokationException("getNamedQueryName() method of Named QUery Consumer must return the name of the named query. It returned null or empty string.");
        }

        Timer timer = new Timer();
        timer.start();

        // If mime type is not set in the returnPayload we use the framework datamodel mime type.
        returnPayloadMetadata.setMimeType((returnPayloadMetadata.getMimeType() == null) ? getDataModelResponsePayloadMetadata().getMimeType() : returnPayloadMetadata.getMimeType());
        QueryTemplateInfo queryInfo = new QueryTemplateInfo(getNamedQueryName(), namedQueryParameters);
        URLQueryParameter urlQueryParameters = customParameters != null ? customParameters.getQueryParams() : null;
        List<Response> responses = new ArrayList<Response>();
        
        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
            return responses;
        }
        
        // Set default set of HTTP Header fields
        HeaderProperties hdrProps = getHeaderProperties(false, ServiceType.XQUERYTEMPLATE, customParameters); 
        
        // Add query intention to headers.
        addQueryIntentionToHeaders(hdrProps, queryIntention);
        
        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(getNamedQueryName(), ServiceType.XQUERYTEMPLATE, AccessRight.QUERY, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
            
            if (error == null) //all good => Send request
            {
                Response response = getClient(getConsumerEnvironment()).retrieveDataFromNamedQuery(queryInfo, returnPayloadMetadata, pagingInfo, hdrProps, urlQueryParameters, zoneCtx.getZone(), zoneCtx.getContext(), requestType);

                // Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
                finaliseDelayedReceipt(response.getDelayedReceipt(), getNamedQueryName(), ServiceType.XQUERYTEMPLATE, ResponseAction.QUERY);
                responses.add(response);
            }
            else //pretend to have received a 'fake' error Response
            {
                responses.add(createErrorResponse(error));
            }
        }                   

        timer.finish();
        logger.debug("Time taken to call and process 'retrieve all' for "+getNamedQueryName()+": "+timer.timeTaken()+"ms");
        return responses;
    }
    
    /** 
     * While this method is available it is strongly advised to use the retrieveDataFromNamedQuery() method. Mentioned method is more intuitive. However if a consumer
     * implementation prefers to use this method it should be used the following way to ensure the correct behaviour of named query service calls.<br/><br/>
     * 
     * Populate all named query parameters in the customParameters.queryParams properties. Please ensure that the query parameter values are URL encoded.<br/>
     * Do NOT populate the named query name into the customParameters.queryParams. It is not necessary as that value is retrieved from the abstract method
     * getNamedQueryName(). It is also not intuitive on how to set the mime type of the response. The framework will simply look up what the consumer's properties
     * file states (env.mediaType). This behaviour can be altered by means of overriding the getResponseMediaType() method.  
     * 
     * (non-Javadoc)
     * @see sif3.common.interfaces.MinimalConsumer#retrieve(sif3.common.model.PagingInfo, java.util.List, sif3.common.header.HeaderValues.RequestType, sif3.common.header.HeaderValues.QueryIntention, sif3.common.model.CustomParameters)
     */
    @Override
    public List<Response> retrieve(PagingInfo pagingInfo, 
                                   List<ZoneContextInfo> zoneCtxList,
                                   RequestType requestType, 
                                   QueryIntention queryIntention,
                                   CustomParameters customParameters)
            throws PersistenceException, ServiceInvokationException
    {
        return retrieveDataFromNamedQuery(null, null, pagingInfo, zoneCtxList, requestType, queryIntention, customParameters);
    }
    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.DelayedConsumer#onQuery(java.lang.Object, sif3.common.model.PagingInfo, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void onQuery(Object dataObject, PagingInfo pagingInfo, DelayedResponseReceipt receipt)
    {
        // All the necessary data is in the receipt parameter to construct the StringPayload. The headers should hold the
        // mime type and the dataObject should be of type string.
        if (dataObject != null) // only then it is worth passing it to the actual consumer
        {
            // The data type should be a String. If not then we have a problem.
            if (dataObject instanceof String)
            {
                PayloadMetadata payloadMetadata = new PayloadMetadata();
                        
                // Let's get the mime type. This should be in the HTTP Headers
                payloadMetadata.setMimeType(MediaType.valueOf(receipt.getHttpHeaders().getHeaderProperty(HttpHeaders.CONTENT_TYPE)));
                
                // Get schema info. Should be in the HTTP Headers
                String schemaInfoStr = receipt.getHttpHeaders().getHeaderProperty(ResponseHeaderConstants.HDR_CONTENT_PROFILE);
                if (StringUtils.notEmpty(schemaInfoStr))
                {
                    payloadMetadata.setSchemaInfo(new SchemaInfo(schemaInfoStr));
                }
                
                processDelayedNamedQuery(new StringPayload((String)dataObject, payloadMetadata), pagingInfo, receipt);
            }
            else // something is wrong. All we can do is log it and treat it as a delayed error
            {
                // We have received a job query response but the data is not of type JobCollectionType. This is not good. We log an error an
                // also call the onError method to indicate to the consumer that some odd stuff has come through.
                processDelayedError(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Invalid Data", "A delayed response to a Name Query has been received but the data is of type "+dataObject.getClass().getSimpleName()+" instead of String.", "Consumer"), receipt);
            }
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
    
    /*----------------------------*/
    /*-- Other required methods --*/
    /*----------------------------*/
    /**
     * This method is all that is needed to run the subscriber in its own thread.
     */
    @Override
    public final void run()
    {
//      logger.debug("============================\n Start "+getConsumerName()+" worker thread.\n======================================");
    }

    
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
     * It returns all XQUERYTEMPLATE services where the consumer has QUERY as approved in the ACL. Note XQUERYTEMPLATE services do only have the
     * QUERY right.
     */
    protected final List<ServiceInfo> getAllApprovedCRUDServices()
    {
        List<ServiceInfo> allServices = new ArrayList<ServiceInfo>();
        
        // Get all Functional Services. Note the service name is the name of the functional service
        allServices.addAll(getAllApprovedServicesForRights(getNamedQueryName(), ServiceType.XQUERYTEMPLATE, AccessRight.QUERY));
        
        return filterApprovedCRUDServices(allServices);
    }   


    /*----------------------------------------------------------------------------------------------*/
    /* Methods that don't need an implementation because they are not applicable for Named Queries. */
    /*----------------------------------------------------------------------------------------------*/
    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.MinimalConsumer#retrievByPrimaryKey(java.lang.String, java.util.List, sif3.common.model.CustomParameters)
     */
    @Override
    public List<Response> retrievByPrimaryKey(String resourceID, 
                                              List<ZoneContextInfo> zoneCtxList,
                                              CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        //There is no get data by its resoutceID (primary key) for named queries available, so we do not need to implement anything here.
        return null;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.MinimalConsumer#getServiceInfo(sif3.common.model.PagingInfo, java.util.List, sif3.common.model.CustomParameters)
     */
    @Override
    public List<Response> getServiceInfo(PagingInfo pagingInfo, 
                                         List<ZoneContextInfo> zoneCtxList,
                                         CustomParameters customParameters)
            throws PersistenceException, ServiceInvokationException
    {
        //There is no ServicePath functionality for named query services, so we do not need to implement anything here.
        return null;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMarshaller()
     */
    @Override
    public MarshalFactory getMarshaller()
    {
        // Named Query are data model agnostic, so we cannot set a marhaller here.
        return null;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getUnmarshaller()
     */
    @Override
    public UnmarshalFactory getUnmarshaller()
    {
        // Named Query are data model agnostic, so we cannot set a unmarhaller here.
        return null;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getSingleObjectClassInfo()
    {
        // Named Queries only have list of objects. Further the the objects can be anything and therefore there is no
        // need to implement this method.
        return new ModelObjectInfo(null, null);
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
        //This must be the same name as used in the URL or as listed in the Environment XML/JSON.
        return new ModelObjectInfo(getNamedQueryName(), null);
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DelayedConsumer#onCreateMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void onCreateMany(MultiOperationStatusList<CreateOperationStatus> statusList, DelayedResponseReceipt receipt)
    {
        //There is no delayed create functionality for named query services, so we do not need to implement anything here.
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DelayedConsumer#onUpdateMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void onUpdateMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt)
    {
        //There is no delayed update functionality for named query services, so we do not need to implement anything here.
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DelayedConsumer#onDeleteMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void onDeleteMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt)
    {
        //There is no delayed delete functionality for named query services, so we do not need to implement anything here.
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DelayedConsumer#onServicePath(java.lang.Object, sif3.common.model.QueryCriteria, sif3.common.model.PagingInfo, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void onServicePath(Object dataObject, QueryCriteria queryCriteria, PagingInfo pagingInfo, DelayedResponseReceipt receipt)
    {
        //There is no ServicePath functionality for named query services, so we do not need to implement anything here.
    }
    
    /*-----------------*/
    /* Private Methods */
    /*-----------------*/
    private NameQueryClient getClient(ConsumerEnvironment envInfo) throws IllegalArgumentException
    {
        return new NameQueryClient(ConsumerEnvironmentManager.getInstance());
    }
}
