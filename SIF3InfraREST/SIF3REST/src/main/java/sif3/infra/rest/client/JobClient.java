/*
 * JobClient.java
 * Created: 20 Feb 2018
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

package sif3.infra.rest.client;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.URLQueryParameter;
import sif3.common.model.job.JobCreateRequestParameter;
import sif3.common.model.job.PhaseInfo;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.common.ws.job.PhaseDataRequest;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.types.ConsumerEnvironment.ConnectorName;
import sif3.infra.common.interfaces.ClientEnvironmentManager;
import sif3.infra.common.model.DeleteIdType;
import sif3.infra.common.model.DeleteRequestType;
import sif3.infra.common.model.InitializationType;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.PhaseStateType;
import sif3.infra.common.model.StateCollectionType;
import sif3.infra.common.model.StateType;

/**
 * @author Joerg Huber
 *
 */
public class JobClient extends BaseClient
{
    private String jobNamePlural = null;
    private String jobNameSingular = null;

    /**
     * Constructor
     * 
     * @param clientEnvMgr Session manager to access the clients session information.
     * @param jobNamePlural The name of the Job. This must match the value of an entry in the SIF3_JOB_TEMPLATE with the JOB_URL_NAME column.
     *                      It is also the equivalent to the Plural Form of the Job Name in the Job URL.
     * @param jobNameSingular The name of the Job in its singular form. Only needed for the createJob operation.
     */
    public JobClient(ClientEnvironmentManager clientEnvMgr, String jobNamePlural, String jobNameSingular)
    {
        super(clientEnvMgr, clientEnvMgr.getEnvironmentInfo().getConnectorBaseURI(ConnectorName.servicesConnector), clientEnvMgr.getEnvironmentInfo().getMediaType(), clientEnvMgr.getEnvironmentInfo().getMediaType(), new InfraMarshalFactory(), new InfraUnmarshalFactory(), clientEnvMgr.getEnvironmentInfo().getSecureConnection(), clientEnvMgr.getEnvironmentInfo().getCompressionEnabled());
        setJobNamePlural(jobNamePlural);
        setJobNameSingular(jobNameSingular);
    }

    public String getJobNamePlural()
    {
        return jobNamePlural;
    }

    public void setJobNamePlural(String jobNamePlural)
    {
        this.jobNamePlural = jobNamePlural;
    }

    public String getJobNameSingular()
    {
        return jobNameSingular;
    }

    public void setJobNameSingular(String jobNameSingular)
    {
        this.jobNameSingular = jobNameSingular;
    }


    /*----------------------------------*/
    /*-- Create Job Object Operations --*/
    /*----------------------------------*/
    
    /**
     * Will invoke the REST POST method. Before the call it will retrieve the job template with the name of the jobName parameter in the
     * constructor of this class. It will add the appropriate values of the createJobRequest parameter to the POST request. If there are any 
     * errors then the ServiceInvokationException is raised and the error is logged.
     * This method will create job refIDs and expects the provider to accept them. MustUseAdvisory will be set to true. The
     * assigned JobID is in the createJobRequest.jobID property.
     * 
     * @param createJobRequest Values to be used to produce a Create Job request such as HTTP headers etc.
     *                         Values that are null are defaulted according to the SIF Specification.<br/>
     *                         - initParams = null => No initialisation parameters will be set in Job Object.<br/>
     *                         - initPhaseName = null => No phase name will be set in the Job Object initialisation section. This must match
     *                                                   a phase name that is part of the job phases.<br/>
     *                                  
     * @param hdrProperties Header Properties to be added to the header of the POST request.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
     * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
     * 
     * @return Response Job Object holding appropriate values and results of the call. 
     * 
     * @throws ServiceInvokationException Any underlying errors occurred such as unable to marshal the object into its media
     *                                    type, failure to invoke actual web-service etc. 
     */
    public Response createJob(JobCreateRequestParameter createJobRequest, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException
    {
        // Let's initialise the Job Template with the given info.
        JobType job = initJobData(createJobRequest);

        WebResource service = getService();
        try
        {
            String payloadStr = getInfraMarshaller().marshal(job, getRequestMediaType());
            if (logger.isDebugEnabled())
            {
                logger.debug("createJob: Payload to send:\n"+payloadStr);
            }

            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural(), getJobNameSingular());
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, ServiceType.FUNCTIONAL.name());
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_ADVISORY, "true");
            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, true).post(ClientResponse.class, payloadStr);

            return setResponse(service, response, JobType.class, hdrProperties, zone, context, true, Status.CREATED, Status.CONFLICT);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'createJob' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }
    
    /**
     * This method will create a collection of Job request in one HTTP request. For each element in the provided list of
     * JobCreateRequestParameter one element in the job collection will be created. The final collection is then sent to the service
     * connector as specified in the SIF Specification.<br/>
     * It is assumed that all jobs are of the same type, indicated with the  jobName parameter in the constructor of this class. Each job
     * element follows the same defaulting as listed in the createJob() method.
     * This method will create job refIDs and expects the provider to accept them. MustUseAdvisory will be set to true.
     * 
     * @param createMultipleJobsRequest Values to be used to produce a Create Job request such as HTTP headers etc.
     *                                  Values that are null are defaulted according to the SIF Specification.<br/>
     *                                  - initParams = null => No initialisation parameters will be set in Job Object.<br/>
     *                                  - initPhaseName = null => No phase name will be set in the Job Object initialisation section. 
     *                                                            This must match a phase name that is part of the job phases.<br/>
     *                                  
     * @param hdrProperties Header Properties to be added to the header of the POST request.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
     * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
     * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
     * 
     * @return Response Job Object holding appropriate values and results of the call. Note that the payload of the response is NOT
     *                  a job object but a list of "create" responses with JobRefIds as defined in the SIF Specification. If more details
     *                  about each job shall be retrieved then it is up to the caller of this method to call the getJob() method for each
     *                  jobID returned in this response.
     * 
     * @throws ServiceInvokationException Any underlying errors occurred such as unable to marshal the object into its media
     *                                    type, failure to invoke actual web-service etc. 
     */
    public BulkOperationResponse<CreateOperationStatus> createJobs(List<JobCreateRequestParameter> createMultipleJobsRequest, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context, RequestType requestType) throws ServiceInvokationException
    {
        // Let's iterate through the jobs...
        JobCollectionType jobs = new JobCollectionType();
        for (JobCreateRequestParameter jobParams : createMultipleJobsRequest)
        {
            // Let's initialise the Job Template with the given info. There is the possibility that this is null where no initialisation 
            // is required. We should create it and set it, so that a jobId can be assigned.
            if (jobParams == null)
            {
                jobParams = new JobCreateRequestParameter();
            }
            jobs.getJob().add(initJobData(jobParams));
        }

        WebResource service = getService();
        try
        {
            String payloadStr = getInfraMarshaller().marshal(jobs, getRequestMediaType());
            if (logger.isDebugEnabled())
            {
                logger.debug("createJob: Payload to send:\n"+payloadStr);
            }

            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural());
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, ServiceType.FUNCTIONAL.name());
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_ADVISORY, "true");
            addDelayedInfo(hdrProperties, zone, context, getJobNamePlural(), ServiceType.FUNCTIONAL, requestType);
            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, requestType, true, true, true).post(ClientResponse.class, payloadStr);
 
            return setCreateBulkResponse(service, response, zone, context, requestType, hdrProperties);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'createJobs' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }
    
    /*---------------------------------*/
    /*-- Query Job Object Operations --*/
    /*---------------------------------*/
    
    /**
     * This method will retrieve a Job given by its jobID. This call will invoke the REST GET call. The object will 
     * be unmarshalled into the Job Object and be stored as part of the returned Response object. If there are any internal 
     * errors then a ServiceInvokationException will be raised and the error is logged.
     * 
     * @param jobID The Id of the job to be returned. If the job doesn't exist the appropriate status is set in the returned Response object as defined 
     *              by the SIF3 spec.
     * @param hdrProperties Header Properties to be added to the header of the GET request.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
     * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
     * 
     * @return The Response to this GET call. See the Response class for details of the content of this object.
     * 
     * @throws ServiceInvokationException An internal error occurred. An error is logged.
     */
    public Response getJob(String jobID, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural(), jobID);
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, ServiceType.FUNCTIONAL.name());
            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, false).get(ClientResponse.class);

            return setResponse(service, response, JobType.class, hdrProperties, zone, context, true, Status.OK, Status.NOT_MODIFIED);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'getJob' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }
    
    /**
     * This method will retrieve a list of Jobs in the given zone and context. It will invoke the REST GET call (Query). Because no jobID is 
     * provided it will automatically return a list of jobs (collection). Additional parameters of this method indicate what part of the 
     * collection shall be returned (pagingInfo) as well as what zone and context this query shall apply to.
     * 
     * @param pagingInfo Page information to be set for the provider to determine which results to return.
     * @param hdrProperties Header Properties to be added to the header of the GET request.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
     * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
     * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
     * 
     * @return Response Object holding appropriate values and results of the call. This is either a job collection or an error object.
     * 
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
    public Response getJobs(PagingInfo pagingInfo, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context, RequestType requestType) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural());
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, ServiceType.FUNCTIONAL.name());
            addPagingInfoToHeaders(pagingInfo, hdrProperties);
            addDelayedInfo(hdrProperties, zone, context, getJobNamePlural(), ServiceType.FUNCTIONAL, requestType);
            
            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, requestType, true, true, false).get(ClientResponse.class);

            return setResponse(service, response, JobCollectionType.class, hdrProperties, zone, context, requestType, true, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT, Status.ACCEPTED);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'getJobs' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }
    
    /*----------------------------------*/
    /*-- Delete Job Object Operations --*/
    /*----------------------------------*/
    
    /**
     * Removes the Job with the given 'resourceID'. Before the call it will add the 'hdrProperties' to the header of the DELETE request. 
     * If there are any errors then the ServiceInvokationException is raised and the error is logged.
     * 
     * @param jobID The Job Id of the job to be deleted. If the job doesn't exist the appropriate status is set in the
     *              returned Response object as defined by the SIF3 spec.
     * @param hdrProperties Header Properties to be added to the header of the DELETE request.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
     * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
     * 
     * @return Response Object holding appropriate values and results of the call. 
     * 
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
    public Response removeJob(String jobID, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural(), jobID);
            
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, false).delete(ClientResponse.class);

            return setResponse(service, response, null, hdrProperties, zone, context, true, Status.NO_CONTENT);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'removeJob' service (REST DELETE) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }
    
    /**
     * This method removes all the Jobs given by the 'resourceIDs' parameter. This method is used to delete many jobs in one call as 
     * defined by the SIF3 spec. The returned list of responses equate to one response per object in the given payload.<br/><br/>
     * 
     * There is an issue with java.net.HttpURLConnection where it doesn't allow an payload for the HTTP DELETE operation. So currently 
     * the implementation of the removeMany fakes such a behaviour and actually calls the HTTP PUT with a HTTP Header called 'methodOverride' as
     * specified in the SIF 3.x specification.
     * 
     * @param jobIDs A list of jobs given by their IDs to be deleted.
     * @param hdrProperties Header Properties to be added to the header of the DELETE request.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
     * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
     * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
     * 
     * @return Response Object holding appropriate values and results of the call. 
     * 
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
    public BulkOperationResponse<OperationStatus> removeJobs(List<String> jobIDs, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context, RequestType requestType) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural());
            
            //Convert List of resources to DeletesTypes
            DeleteRequestType deleteRequest = getInfraObjectFactory().createDeleteRequestType();
            deleteRequest.setDeletes(getInfraObjectFactory().createDeleteIdCollection());
            
            if (jobIDs != null)
            {
                for (String resourceID : jobIDs)
                {
                    DeleteIdType id = getInfraObjectFactory().createDeleteIdType();
                    id.setId(resourceID);
                    deleteRequest.getDeletes().getDelete().add(id);
                }
            }
            String payloadStr = getInfraMarshaller().marshal(deleteRequest, getRequestMediaType());
            
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            addDelayedInfo(hdrProperties, zone, context, getJobNamePlural(), ServiceType.FUNCTIONAL, requestType);

            // Set specific header so that PUT method knows that a DELETE and not an UPDATE is required! 
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE, HeaderValues.MethodType.DELETE.name());
            
            if (logger.isDebugEnabled())
            {
                logger.debug("removeJobs: Payload to send:\n"+payloadStr);
            }
            ClientResponse cltResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, requestType, true, true, true).put(ClientResponse.class, payloadStr);
            
            return setDeleteBulkResponse(service, cltResponse, zone, context, requestType, hdrProperties);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'removeJobs' service (REST DELETE) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }
    
    /**
     * Will invoke the REST HEAD call. It will be invoked on the Job service. It will not return a payload as per 
     * HTTP Specification of the HEAD method. It will return a number of HTTP Header fields though. These can be retrieved as 
     * part of the returned response object. Because this method almost mirrors the HTTP GET for the getJobs() service all 
     * parameters that would make up the getJobs() method in this class are supported. The exception is the requestType parameter 
     * that are allowed in the getJobs() method. This parameter does not make any sense for this method and is therefore omitted.
     * 
     * @param pagingInfo Page information to be set for the provider to determine which results to return.
     * @param hdrProperties Header Properties to be added to the header of the GET request.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
     * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
     * 
     * @return Response Object holding appropriate values and results of the call. 
     * 
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
    public Response getServiceInfo(PagingInfo pagingInfo, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural());
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, ServiceType.FUNCTIONAL.name());
            addPagingInfoToHeaders(pagingInfo, hdrProperties);
            
            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, RequestType.IMMEDIATE, true, true, false).head();

            return setResponse(service, response, null, hdrProperties, zone, context, RequestType.IMMEDIATE, true, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT, Status.ACCEPTED);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'getServiceInfo' Job service (REST HEAD) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }        
    }

    /*----------------------------------------------------------------------*/
    /*-- Update Job Object Operations: NOT ALLOWED according to SIF 3.2.1 --*/
    /*----------------------------------------------------------------------*/
 
    /*--------------------------*/
    /*-- Job Phase Operations --*/
    /*--------------------------*/
    /**
     * This method is used to retrieve data from a given phase. This can be any data. It is up to the implementation of the
     * functional service to know what that data is for a given phase. This framework is agnostic to that data. The returned
     * value is a String that must represent the "marshalled" version of the data in the format indicated by the "returnMimeType".
     * Because the data that can be returned as part of a phase might be a collection, the paging parameter can be provided. If the 
     * data to be returned is considered too large by the provider (implementation dependent) then an appropriate error is 
     * returned (HTTP Status 413 - Response too large).
     * 
     * @param phaseInfo Hold the jobID and phase name of the job from where the data shall be retrieved. If the parameter or
     *                  any of its properties must not be null/empty.
     * @param returnMimeType The mime type the response data is in. It is expected that the consumer provides that and the provider
     *                       should attempt to marshal the data to the given mime type and return the resulting string as
     *                       part of this call. If the provider cannot marshal the data to the requested mime type then an
     *                       appropriate error is returned to this consumer (HTTP Status 400 - Bad Request).
     * @param pagingInfo Page information to determine which results to return. Null = Return all (NOT RECOMMENDED! Might be rejected
     *                   by provider.).
     * @param hdrProperties Header Properties to be added to the header of the request. Can be null.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the the request is being issued. Can be Null (default Zone)
     * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
     *                       
     * @return Response Object holding appropriate values and results of the call. Because the framework is agnostic to the
     *         data that is returned for a phase the Response.dataObjectType will be set to "String" and the Response.dataObject
     *         will hold the string representation of the returned payload. It is up to the caller of this method to potentially
     *         marshal that payload into an appropriate object.
     *         
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
    public Response retrieveDataFromPhase(PhaseInfo phaseInfo, MediaType returnMimeType, PagingInfo pagingInfo, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context, RequestType requestType)
            throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural(), phaseInfo.getJobID(), phaseInfo.getPhaseName());
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, ServiceType.FUNCTIONAL.name());
            addPagingInfoToHeaders(pagingInfo, hdrProperties);
            addDelayedInfo(hdrProperties, zone, context, getJobNamePlural(), ServiceType.FUNCTIONAL, requestType);
            
            ClientResponse response = setRequestHeaderAndMediaTypes(service, returnMimeType, returnMimeType, hdrProperties, requestType, true, true, false).get(ClientResponse.class);

            return setResponse(service, response, String.class, hdrProperties, zone, context, requestType, true, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT, Status.ACCEPTED);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'retrieveDataFromPhase' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }
    
    /**
     * This method should perform a "create" operation for the data given in the "data" parameter. This can be any data. It 
     * is up to the implementation of the functional service to know what that data is for a given phase. This framework is agnostic 
     * to that data. The data is given in its raw form as received by the framework. The phaseData.mimeType shall be used
     * by the provider to unmarshal the data into a useful data structure. It is important to note that there might be no return data
     * for a phase operation (response.dataObject == null). This is indicated with a HTTP status of 204 (No content). However if data 
     * is returned then response.dataObject will hold the data in its string representation and the response.dataObjectType will be 
     * set to String.class. It is expected that the consumer uses the  response.mediaType to unmarshal the data to a structure suitable 
     * for the consumer. It is also expected that the response.mediaType would be of the same mime type as indicated by the consumer 
     * in the returnMimeType parameter.<br/><br/>
     * 
     * In case of a delayed request the the response.dataObject and response.dataObjectType are null and the response.delayedReceipt 
     * property will be populated accordingly.<br/><br/>
     * 
     * In case of a failure an appropriate error message is returned in the Response Object. If there is no error returned it is
     * assumed that this method completed successfully and the appropriate HTTP status is set. 
     * 
     * @param phaseInfo Hold the jobID and phase name of the job from where the data shall be created. If the parameter or
     *                  any of its properties must not be null/empty.
     * @param phaseData The data and its mime type that is given to the operation of this phase. The data property of this
     *                  parameter can be null in cases where no data is provided to this phase. This is entirely valid according
     *                  to the SIF Specification. If data is provided (as a String) then the mime type is set as well, by the consumer,
     *                  to indicate the format of the data. The provider can us this mime type to unmarshal the data into the 
     *                  appropriate internal structure.
     * @param returnMimeType The mime type the response data is in. It is expected that the consumer provides that and the provider
     *                       should attempt to marshal the data to the given mime type and return the resulting string as
     *                       part of this call. If the provider cannot marshal the data to the requested mime type then an
     *                       appropriate error is returned to this consumer (HTTP Status 400 - Bad Request).
     * @param useAdvisory If new IDs for the created data shall be allocated or used as given. In some cases that may not be applicable
     *                    but if it is then this parameter indicates the expected behaviour.
     * @param hdrProperties Header Properties to be added to the header of the request. Can be null.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the the request is being issued. Can be Null (default Zone)
     * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
     * 
     * @return Response Object holding appropriate values of the call. The response may contain data (response.dataObject) that will be
     *         with a response.dataObjectType=String.class. The consumer is expected to use the response.mediaType to unmarshal the data
     *         into its internal structure. If the status is set to 204 then no content is returned which is also a valid scenario.
     *         
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
    public Response createDataInPhase(PhaseInfo phaseInfo, 
                                      PhaseDataRequest phaseDataRequest,
                                      MediaType returnMimeType, 
                                      boolean useAdvisory, 
                                      HeaderProperties hdrProperties, 
                                      URLQueryParameter urlQueryParams, 
                                      SIFZone zone, 
                                      SIFContext context, 
                                      RequestType requestType) 
            throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural(), phaseInfo.getJobID(), phaseInfo.getPhaseName());
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_ADVISORY, String.valueOf(useAdvisory));
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, ServiceType.FUNCTIONAL.name());
            addDelayedInfo(hdrProperties, zone, context, getJobNamePlural(), ServiceType.FUNCTIONAL, requestType);
            
            ClientResponse response = null;
            if ((phaseDataRequest != null) && (phaseDataRequest.getData() != null))
            {
                response = setRequestHeaderAndMediaTypes(service, phaseDataRequest.getMimeType(), returnMimeType, hdrProperties, requestType, true, true, true).post(ClientResponse.class, phaseDataRequest.getData());
            }
            else
            {
                response = setRequestHeaderAndMediaTypes(service, null, returnMimeType, hdrProperties, requestType, true, true, false).post(ClientResponse.class);
            }
 
            return setResponse(service, response, String.class, hdrProperties, zone, context, requestType, true, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT, Status.ACCEPTED, Status.CREATED);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'createDataInPhase' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }

    /**
     * This method should perform an "update" operation for the data given in the "phaseData.data" (string). This can be any data. It 
     * is up to the implementation of the functional service to know what that data is for a given phase. This framework is agnostic 
     * to that data. The data is given in its raw form as received by the framework. The phaseData.mimeType shall be used
     * by the provider to unmarshal the data into a useful data structure. It is important to note that there might be no return data
     * for a phase operation (response.dataObject == null). However if data is returned then response.dataObject will hold the data in 
     * its string representation and the response.dataObjectType will be set to String.class. It is expected that the consumer uses 
     * the response.mediaType to unmarshal the data to a structure suitable for the consumer. It is also expected that the 
     * response.mediaType would be of the same mime type as indicated by the consumer in the returnMimeType parameter.<br/><br/>
     *  
     * @param phaseInfo Hold the jobID and phase name of the job where the data shall be updated. If the parameter or
     *                  any of its properties must not be null/empty.
     * @param phaseData The data and its mime type that is given to the operation of this phase. The data property of this
     *                  parameter can be null in cases where no data is provided to this phase. This is entirely valid according
     *                  to the SIF Specification. If data is provided (as a String) then the mime type is set as well, by the consumer,
     *                  to indicate the format of the data. The provider can us this mime type to unmarshal the data into the 
     *                  appropriate internal structure.
     * @param returnMimeType The mime type the response data is in. It is expected that the consumer provides that and the provider
     *                       should attempt to marshal the data to the given mime type and return the resulting string as
     *                       part of this call. If the provider cannot marshal the data to the requested mime type then an
     *                       appropriate error is returned to this consumer (HTTP Status 400 - Bad Request).
     * @param hdrProperties Header Properties to be added to the header of the request. Can be null.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the the request is being issued. Can be Null (default Zone)
     * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
     * 
     * @return Response Object holding appropriate values of the call. The response may contain data (response.dataObject) that will be
     *         with a response.dataObjectType=String.class. The consumer is expected to use the response.mediaType to unmarshal the data
     *         into its internal structure.
     *         
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
    public Response updateDataInPhase(PhaseInfo phaseInfo, 
                                      PhaseDataRequest phaseDataRequest,
                                      MediaType returnMimeType, 
                                      HeaderProperties hdrProperties, 
                                      URLQueryParameter urlQueryParams, 
                                      SIFZone zone, 
                                      SIFContext context, 
                                      RequestType requestType) 
            throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural(), phaseInfo.getJobID(), phaseInfo.getPhaseName());
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, ServiceType.FUNCTIONAL.name());
            addDelayedInfo(hdrProperties, zone, context, getJobNamePlural(), ServiceType.FUNCTIONAL, requestType);
            
            ClientResponse response = null;
            if ((phaseDataRequest != null) && (phaseDataRequest.getData() != null))
            {
                response = setRequestHeaderAndMediaTypes(service, phaseDataRequest.getMimeType(), returnMimeType, hdrProperties, requestType, true, true, true).put(ClientResponse.class, phaseDataRequest.getData());
            }
            else
            {
                response = setRequestHeaderAndMediaTypes(service, null, returnMimeType, hdrProperties, requestType, true, true, false).put(ClientResponse.class);
            }
 
            return setResponse(service, response, String.class, hdrProperties, zone, context, requestType, true, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT, Status.ACCEPTED);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'updateDataInPhase' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }

    /**
     * This method should perform a "delete" operation for the data given in the "phaseData.data" (string). This can be any data. It 
     * is up to the implementation of the functional service to know what that data is for a given phase. This framework is agnostic 
     * to that data. The data is given in its raw form as received by the framework. The phaseData.mimeType shall be used
     * by the provider to unmarshal the data into a useful data structure. It is important to note that there might be no return data
     * for a phase operation (response.dataObject == null). However if data is returned then response.dataObject will hold the data in 
     * its string representation and the response.dataObjectType will be set to String.class. It is expected that the consumer uses 
     * the response.mediaType to unmarshal the data to a structure suitable for the consumer. It is also expected that the 
     * response.mediaType would be of the same mime type as indicated by the consumer in the returnMimeType parameter.<br/><br/>
     * 
     * In case of a delayed request the the response.dataObject and response.dataObjectType are null and the response.delayedReceipt 
     * property will be populated accordingly.<br/><br/>
     * 
     * In case of a failure an appropriate error message is returned in the Response Object. If there is no error returned it is
     * assumed that this method completed successfully and the appropriate HTTP status is set. 
     * 
     * @param phaseInfo Hold the jobID and phase name of the job where the data shall be deleted. If the parameter or
     *                  any of its properties must not be null/empty.
     * @param phaseData The data and its mime type that is given to the operation of this phase. The data property of this
     *                  parameter can be null in cases where no data is provided to this phase. This is entirely valid according
     *                  to the SIF Specification. If data is provided (as a String) then the mime type is set as well, by the consumer,
     *                  to indicate the format of the data. The provider can us this mime type to unmarshal the data into the 
     *                  appropriate internal structure.
     * @param returnMimeType The mime type the response data is in. It is expected that the consumer provides that and the provider
     *                       should attempt to marshal the data to the given mime type and return the resulting string as
     *                       part of this call. If the provider cannot marshal the data to the requested mime type then an
     *                       appropriate error is returned to this consumer (HTTP Status 400 - Bad Request).
     * @param hdrProperties Header Properties to be added to the header of the request. Can be null.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the the request is being issued. Can be Null (default Zone)
     * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
     * 
     * @return Response Object holding appropriate values of the call. The response may contain data (response.dataObject) that will be
     *         with a response.dataObjectType=String.class. The consumer is expected to use the response.mediaType to unmarshal the data
     *         into its internal structure.
     *         
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
    public Response deleteDataInPhase(PhaseInfo phaseInfo, 
                                      PhaseDataRequest phaseDataRequest,
                                      MediaType returnMimeType, 
                                      HeaderProperties hdrProperties, 
                                      URLQueryParameter urlQueryParams, 
                                      SIFZone zone, 
                                      SIFContext context, 
                                      RequestType requestType) 
            throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural(), phaseInfo.getJobID(), phaseInfo.getPhaseName());
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            
            // Set specific header so that PUT method knows that a DELETE and not an UPDATE is required! 
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE, HeaderValues.MethodType.DELETE.name());
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, ServiceType.FUNCTIONAL.name());
            addDelayedInfo(hdrProperties, zone, context, getJobNamePlural(), ServiceType.FUNCTIONAL, requestType);
            
            ClientResponse response = null;
            if ((phaseDataRequest != null) && (phaseDataRequest.getData() != null))
            {
                response = setRequestHeaderAndMediaTypes(service, phaseDataRequest.getMimeType(), returnMimeType, hdrProperties, requestType, true, true, true).put(ClientResponse.class, phaseDataRequest.getData());
            }
            else
            {
                response = setRequestHeaderAndMediaTypes(service, null, returnMimeType, hdrProperties, requestType, true, true, false).put(ClientResponse.class);
            }
 
            return setResponse(service, response, String.class, hdrProperties, zone, context, requestType, true, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT, Status.ACCEPTED);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'deleteDataInPhase' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }
    
    /*--------------------------------*/
    /*-- Job Phase State Operations --*/
    /*--------------------------------*/
    
    /**
     * This method attempts to update the state of a given phase for a job. <br/>
     * An error message will be part of the returned Response for the following cases:<br/>
     * - Job doesn't exist.<br/>
     * - If the phase or state is invalid.<br/>
     * - Consumer doesn't have appropriate permissions for this operation.<br/>
     * - Potentially the job is no longer 'update-able' because it has reached an end state (eg. COMPLETED or FAILED).<br/>
     * 
     * @param phaseInfo Hold the jobID and phase name of the job where the state shall be updated. If the parameter or
     *                  any of its properties must not be null/empty.
     * @param newState The value of the new state for the given phase. If null then no action is taken.
     * @param hdrProperties Header Properties to be added to the header of the request.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
     * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
     * 
     * @return Response Object holding appropriate values and results of the call. 
     * 
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
    public Response updatePhaseState(PhaseInfo phaseInfo, PhaseState newState, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            // Create the phase state object
            StateType newPhaseState = new StateType();
            newPhaseState.setType(PhaseStateType.valueOf(newState.name()));
            String payloadStr = getInfraMarshaller().marshal(newPhaseState, getRequestMediaType());
            if (logger.isDebugEnabled())
            {
                logger.debug("Phase State: Payload to send:\n"+payloadStr);
            }

            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural(), phaseInfo.getJobID(), phaseInfo.getPhaseName(), "states", "state");
            
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, ServiceType.FUNCTIONAL.name());

            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, true).post(ClientResponse.class, payloadStr);

            return setResponse(service, response, StateType.class, hdrProperties, zone, context, true, Status.CREATED, Status.CONFLICT);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'updatePhaseState' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }
    
    /**
     * This method attempts to retrieve the state(s) of the given phase. The returned object in the response will be of
     * type StateCollectionType as defined in the infrastructure data model.<br/>
     * An error message will be part of the returned Response for the following cases:<br/>
     * - Job doesn't exist.<br/>
     * - If the phase is invalid.<br/>
     * - Consumer doesn't have appropriate permissions for this operation.<br/>
     * 
     * @param phaseInfo Hold the jobID and phase name of the job where the states shall be retrieved. If the parameter or
     *                  any of its properties must not be null/empty.
     * @param phaseName Name of the phase for which the state(s) shall be retrieved. If null then an error will be returned.
     * @param hdrProperties Header Properties to be added to the header of the request.
     * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
     *                       names are case sensitive. This parameter can be null.
     * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
     * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
     * 
     * @return Response Object holding appropriate values and results of the call. 
     * 
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
    public Response getPhaseStates(PhaseInfo phaseInfo, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            // Create the phase state object

            service = buildURI(service, zone, context, urlQueryParams, getJobNamePlural(), phaseInfo.getJobID(), phaseInfo.getPhaseName(), "states");
            
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, ServiceType.FUNCTIONAL.name());

            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, true).get(ClientResponse.class);

            return setResponse(service, response, StateCollectionType.class, hdrProperties, zone, context, true, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT, Status.ACCEPTED);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'getPhaseStates' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }
    
    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    private JobType initJobData(JobCreateRequestParameter jobInitRequest) throws ServiceInvokationException
    {
        // First we get the job template
        JobType job = getClientEnvMgr().getJobTemplate(getJobNamePlural());
        if (job == null) // not good. No such job exists in job template store
        {
            String errorMsg = "No Job Template with the name " + getJobNamePlural() + " exists. Needs to be configured in appropriate tables first.";
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg);
        }
        
        // If we get here all good. Populate initialisation parameters.
        if ((jobInitRequest != null) && (StringUtils.notEmpty(jobInitRequest.getInitPhaseName()) || (jobInitRequest.getInitParams() != null)))
        {
            InitializationType initSection = new InitializationType();
            if (StringUtils.notEmpty(jobInitRequest.getInitPhaseName()))
            {
                initSection.setPhaseName(jobInitRequest.getInitPhaseName().trim());
            }
            if (jobInitRequest.getInitParams() != null)
            {
                //TODO: JH - Figure out on how to pass parameters along...
                /*                
                <payload xsi:type="propertiesType" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                    <property name="contextId">future</property>
                    <property name="initiator">user1</property>
                    <property name="from">vendora</property>
                    <property name="to">3pi</property>
                    <property name="year">2018</property>
                </payload>
                */
//                JobInitPayload payload = new JobInitPayload();
//                for (AttributeValue attrValue : jobInitRequest.getInitParams())
//                {
//                    Property property = new Property();
//                    property.setNameAndValue(attrValue.getAttribute(), attrValue.getValue());
//                    
//                    payload.getProperties().add(property);
//                }
//                
//                initSection.setPayload(payload.getInitPayload());
            }
            job.setInitialization(initSection);
        }
        job.setId(UUIDGenerator.getUUID());
        jobInitRequest.setJobID(job.getId());
        
        return job;
    }
}
