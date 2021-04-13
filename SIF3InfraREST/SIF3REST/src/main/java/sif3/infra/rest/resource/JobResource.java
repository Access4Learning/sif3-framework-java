/*
 * JobResource.java
 * Created: 30 Jan 2018
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

package sif3.infra.rest.resource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants;
import sif3.common.CommonConstants.JobState;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.ResourceNotFoundException;
import sif3.common.exception.SIFException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.header.ResponseHeaderConstants;
import sif3.common.interfaces.ChangesSinceProvider;
import sif3.common.interfaces.FunctionalServiceProvider;
import sif3.common.model.ACL.AccessRight;
import sif3.common.model.ACL.AccessType;
import sif3.common.model.ChangedSinceInfo;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.model.job.PhaseInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.job.PhaseDataRequest;
import sif3.common.ws.job.PhaseDataResponse;
import sif3.infra.common.env.mgr.ProviderManagerFactory;
import sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType;
import sif3.infra.common.env.types.ExtendedJobInfo;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.common.interfaces.ProviderJobManager;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobStateType;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.PhaseStateType;
import sif3.infra.common.model.PhaseType;
import sif3.infra.common.model.RightType;
import sif3.infra.common.model.RightsType;
import sif3.infra.common.model.StateCollectionType;
import sif3.infra.common.model.StateType;
import sif3.infra.rest.provider.ProviderFactory;

/**
  * This is the resource implementation of the Job Service that covers the end-points for functional services. It caters for the actual
  * Job Object, the phase end-points as well as for the status end-points. It implements all the functions required by the SIF3 specification
  * for a functional service provider.<br/><br/>
  * 
  * Developers are not expected to use this class to write providers. It is a full and generic implementation. The developers are
  * expected to extend the BaseJobProvider and then configure the rest in the provider's property file and job-template tables as
  * described in the Developer's Guide. This class uses property files and the job template tables to correctly invoke specific
  * JobProvider classes. Please refer to the developer's guide for detail on which classes need to be implemented to write a functional
  * service provider.<br/><br/>
  * 
  * This class makes one assumption though, and that is that the base URI for all job providers is of the form:<br/>
  * http://<baseURI>/services/<jobName>...<br/><br/>
  * 
  * It must be ensured that in all the environments managed with this framework that the "services" connector URI follows this 
  * structure.
  * 
  * @author Joerg Huber
  */
@Path("/services/{jobNamePlural:([^\\./]*)}{mimeType:(\\.[^/]*?)?}")
public class JobResource extends InfraResource
{
    private String jobNamePlural = null; // This is the same as the JOB_URL_NAME in the SIF3_JOB_TEMPLATE table.
    private FunctionalServiceProvider fsProvider = null;

    /**
     * @param uriInfo URI of service.
     * @param requestHeaders Headers
     * @param request Original request object
     * @param zoneID Matrix Parameter Zone ID
     * @param contextID Matrix Parameter Context ID
     */
    public JobResource(@Context UriInfo uriInfo,
                       @Context HttpHeaders requestHeaders,
                       @Context Request request,
                       @PathParam("jobNamePlural") String jobNamePlural,
                       @PathParam("mimeType") String mimeType,
                       @MatrixParam(CommonConstants.MATRIX_ZONE_ID) String zoneID,
                       @MatrixParam(CommonConstants.MATRIX_CONTEX_ID) String contextID)
    {
        super(uriInfo, requestHeaders, request, "services", zoneID, contextID);

        setJobNamePlural(jobNamePlural);
        if (logger.isDebugEnabled())
        {
            logger.debug("Job Service to use: "+getJobNamePlural());
            logger.debug("Request Media & Schema Info: " + getRequestPayloadMetadata());
            logger.debug("Response Media & Schema Info DM: " + getDmResponsePayloadMetadata());
            logger.debug("Response Media & Schema Info Infra: " + getInfraResponsePayloadMetadata());
        }
        
        setFSProvider((FunctionalServiceProvider) ProviderFactory.getInstance().getProvider(new ModelObjectInfo(this.jobNamePlural, null)));
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
        return ProviderManagerFactory.getEnvironmentManager();
    }
    
    //---------------------------//
    //-- Job End-Point Section --//
    //---------------------------//
    
    // -------------------------------------------------//
    // -- POST Section: This is the C(reate) in CRUD. --//
    // -------------------------------------------------//
    /*
     * Creates a single Job.
     */
    @POST
    @Path("{jobNameSingle:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response createJob(String payload, @PathParam("jobNameSingle") String jobNameSingle, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Create Single Job "+jobNameSingle+" (REST POST) with URL Postfix mimeType = '" + mimeType + "' and input data: " + payload);
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();
        ErrorDetails error = validClient(getJobNamePlural(), ServiceType.FUNCTIONAL, getRight(AccessRight.CREATE), AccessType.APPROVED, false, true);
        if (error != null) // Not allowed to access!
        {
            return makeInfraErrorResponse(error, ResponseAction.CREATE, responseParam);
        }
        
        // Get the payload and marshal it into an object. From there we can retrieve potential initialisation parameters.
        if (payload == null) // something is wrong!
        {
            error = new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Missing Job Data", "No Job Data provided in request for Job "+getJobNamePlural(), "Provider");
            return makeInfraErrorResponse(error, ResponseAction.CREATE, responseParam);  
        }
        
        JobType payloadJob = null;
        try
        {
            payload = mapToFrameworkInfraVersion(payload);
            payloadJob = (JobType)getInfraUnmarshaller().unmarshal(payload, JobType.class, getRequestPayloadMetadata().getMimeType(), getRequestPayloadMetadata().getSchemaType());
        }
        catch (Exception ex)
        {
            logger.error("Failed to unmarshal payload into an Job Data: "+ ex.getMessage()+"\n Offending payload:\n"+payload, ex);
            return makeInfraErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal job payload: "+ ex.getMessage()), ResponseAction.CREATE, responseParam);
        }    
        
        try
        {
            JobType finalJob = createJobInternal(payloadJob, responseParam, true);
            return makeResponse(finalJob, false, Status.CREATED.getStatusCode(), false, ResponseAction.CREATE, responseParam, getInfraMarshaller(), getFWInfraSchemaInfo());  

        }
        catch (SIFException ex)
        {
            if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
            {
                ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
            }
            return makeInfraErrorResponse(ex.getErrorDetails(), ResponseAction.CREATE, responseParam);
        }      
    }

    /*
     * Creates a multiple Jobs.
     */
    @POST
    public Response createJobs(String payload)
    {
        // Check what is really required: GET (QBE functionality) or POST (Create functionality)
        boolean isQBE = HeaderValues.MethodType.GET.name().equalsIgnoreCase(getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE));

        if (logger.isDebugEnabled())
        {
            if (isQBE)
            {
                logger.debug("QBE on "+getJobNamePlural()+" (REST POST, method OVERRIDE=GET) with input data: " + payload);
            }
            else
            {
                logger.debug("Create Multiple Jobs "+getJobNamePlural()+" (REST POST) with input data: " + payload);
            }
        }
        
        ResponseParameters responseParam = getInitialCustomResponseParameters();
        if (isQBE) // not supported for Job Services in this framework.
        {
            return makeInfraErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Query By Example (QBE) not supported.", "Query By Example (QBE) not supported for "+getJobNamePlural()+".", "Provider"), ResponseAction.QUERY, responseParam);           
        }
        
        ErrorDetails error = validClient(getJobNamePlural(), ServiceType.FUNCTIONAL, getRight(AccessRight.CREATE), AccessType.APPROVED, true, true);
        if (error != null) // Not allowed to access!
        {
            return makeInfraErrorResponse(error, ResponseAction.CREATE, responseParam);
        }

        // Get the payload and marshal it into an object. From there we can retrieve potential initialisation parameters.
        if (payload == null) // something is wrong!
        {
            error = new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Missing Job Data", "No Job Data provided in request for Job "+getJobNamePlural(), "Provider");
            return makeInfraErrorResponse(error, ResponseAction.CREATE, responseParam);  
        }

        JobCollectionType payloadJobs = null;
        try
        {
            payload = mapToFrameworkInfraVersion(payload);
            payloadJobs = (JobCollectionType)getInfraUnmarshaller().unmarshal(payload, JobCollectionType.class, getRequestPayloadMetadata().getMimeType(), getRequestPayloadMetadata().getSchemaType());
        }
        catch (Exception ex)
        {
            logger.error("Failed to unmarshal payload into an Job Collection Data: "+ ex.getMessage()+"\n Offending payload:\n"+payload, ex);
            return makeInfraErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal job payload: "+ ex.getMessage()), ResponseAction.CREATE, responseParam);
        }
        
        // Iterate through each job and do the same work as with a single job create request
        ArrayList<CreateOperationStatus> statusList = new ArrayList<CreateOperationStatus>();
        for (JobType payloadJob : payloadJobs.getJob())
        {
            try
            {
                JobType finalJob = createJobInternal(payloadJob, responseParam, true);
                
                //If we get here all is good. Make an entry in the statusList
                statusList.add(new CreateOperationStatus(payloadJob.getId(), finalJob.getId(), Status.CREATED.getStatusCode()));
            }
            catch (SIFException ex)
            {
                // Add error to status list
                if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
                {
                    ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
                }
                statusList.add(new CreateOperationStatus(payloadJob.getId(), payloadJob.getId(), ex.getErrorDetails().getErrorCode(), ex.getErrorDetails()));
            }
        }
        
        return makeCreateMultipleResponse(statusList, Status.OK, responseParam);        
    }
    
    // ----------------------------------------------//
    // -- GET Section: This is the R(ead) in CRUD. --//
    // ----------------------------------------------//
    @GET
    @Path("{jobID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response getJob(@PathParam("jobID") String jobID, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Get Job by ID (REST GET - Single): "+jobID+" and URL Postfix mimeType = '"+mimeType+"'");
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();
        ErrorDetails error = validClient(getJobNamePlural(), ServiceType.FUNCTIONAL, getRight(AccessRight.QUERY), AccessType.APPROVED, false, true);
        if (error != null) // Not allowed to access!
        {
            return makeInfraErrorResponse(error, ResponseAction.QUERY, responseParam);
        }
        
        try
        {
            JobType job = getEnvironmentManager().getJobManager().retrieveJob(jobID);
            if (job == null)
            {
                return makeInfraErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Job not found.", getJobNamePlural()+" Job with jobID = "+jobID+" does not exist.", "Provider"), ResponseAction.QUERY, responseParam);
            }
            else
            {
                return makeResponse(job, false, Status.OK.getStatusCode(), false, ResponseAction.QUERY, responseParam, getInfraMarshaller(), getFWInfraSchemaInfo());  
            }
        }
        catch (PersistenceException ex)
        {
            return makeInfraErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve Job.", "Failed to retrieve Job for "+getJobNamePlural()+" and JobID = "+jobID+". Problem reported: "+ex.getMessage(), "Provider"), ResponseAction.QUERY, responseParam);
        }
    }
    
    @GET
    public Response getJobs()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Get Jobs (REST GET - Plural)");
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();
        ErrorDetails error = validClient(getJobNamePlural(), ServiceType.FUNCTIONAL, getRight(AccessRight.QUERY), AccessType.APPROVED, true, true);
        if (error != null) // Not allowed to access!
        {
            return makeInfraErrorResponse(error, ResponseAction.QUERY, responseParam);
        }
        
        PagingInfo pagingInfo = null;
        try
        {
            pagingInfo = getPagingInfo();
            if (pagingInfo == null)
            {
                throw new DataTooLargeException("Paging Information required.", "navigationPage and/or navigationPageSize HTTP Header(s) missing. Both must be set for this oprartion.", "Provider ("+getJobNamePlural()+")");
            }

            if (pretendDelayed())
            {
                // Simply send a response with status of 202
                return makeDelayedAcceptResponse(ResponseAction.QUERY);
            }
            else
            {

                RequestMetadata requestMetadata = getRequestMetadata(getSIF3SessionForRequest(), true);
                ChangesSinceProvider provider = (ChangesSinceProvider)getFSProvider();
                if (provider == null) // error already logged but we must return an error response for the caller
                {
                    return makeInfraErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Missing Functional Service Provider", "No Provider for "+getJobNamePlural()+" available.", "Provider"), ResponseAction.QUERY , responseParam);
                }
                
                // We need to check if the changesSinceMarker has been provided.
                String changesSinceMarker = getChangesSinceMarker();
                if (changesSinceMarker != null) // We have a ChangesSince request
                {
                    if (provider.changesSinceSupported())
                    {
                        //Get new changes since marker if page = first page
                        String newChangesSinceMarker = null;
                        if (pagingInfo.getCurrentPageNo() == CommonConstants.FIRST_PAGE)
                        {
                            newChangesSinceMarker = provider.getLatestOpaqueMarker(getNotNullSIFZone(), getNotNullSIFContext(), pagingInfo, getRequestMetadata(getSIF3SessionForRequest(), true));
                        }
                        
                        // Return the results.
                        Object returnObj = provider.getChangesSince(getNotNullSIFZone(), getNotNullSIFContext(), pagingInfo, new ChangedSinceInfo(changesSinceMarker), getRequestMetadata(getSIF3SessionForRequest(), true), responseParam);

                        // Check if we have pagingInfo parameter and if so if the navigationID is set. If it is not set we set it to the value of the
                        // newChangesSinceMarker. Consumer can use this to identify which query the provider ran in subsequent paged queries.
                        if (StringUtils.isEmpty(pagingInfo.getNavigationId()) && (newChangesSinceMarker != null))
                        {
                            pagingInfo.setNavigationId(newChangesSinceMarker);
                        }
                        
                        // Add changes since marker to response
                        if (newChangesSinceMarker != null)
                        {
                            responseParam.addHTTPHeaderParameter(ResponseHeaderConstants.HDR_CHANGES_SINCE_MARKER, newChangesSinceMarker);
                        }

                        return makePagedResponse(returnObj, false, pagingInfo, false, ResponseAction.QUERY, responseParam,  getInfraMarshaller()); 
                    }
                    else // changes since is not supported => Error
                    {
                        return makeInfraErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Provider for "+getJobNamePlural()+" does not support 'ChangesSince' functionality."), ResponseAction.QUERY, responseParam);                                                      
                    }
                }
                else // Standard request/response
                {
                    JobCollectionType jobs = getEnvironmentManager().getJobManager().retrieveJobs(getJobNamePlural(), requestMetadata.getFingerprint(), getNotNullSIFZone(), getNotNullSIFContext(), pagingInfo);
                    return makePagedResponse(jobs, false, pagingInfo, false, ResponseAction.QUERY, responseParam, getInfraMarshaller());
                }
            }
        }
        catch (PersistenceException | IllegalArgumentException  ex)
        {
            return makeInfraErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not retrieve jobs.", "Failed to retrieve "+getJobNamePlural()+" with Paging Information: "+pagingInfo+". Problem reported: "+ex.getMessage(), "Provider"), ResponseAction.QUERY, responseParam);         
        }
        catch (SIFException ex)
        {
            if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
            {
                ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
            }
            return makeInfraErrorResponse(ex.getErrorDetails(), ResponseAction.QUERY, responseParam);
        }
    }
    
    // ---------------------------------------------------//
    // -- DELETE Section: This is the D(elete) in CRUD. --//
    // ---------------------------------------------------//
    @DELETE
    @Path("{jobID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response removeJob(@PathParam("jobID") String jobID, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Remove Job "+getJobNamePlural()+" (REST DELETE) for Job with ID = "+jobID + " and URL Postfix mimeType = '" + mimeType + "'.");
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();
        ErrorDetails error = validClient(getJobNamePlural(), ServiceType.FUNCTIONAL, getRight(AccessRight.DELETE), AccessType.APPROVED, false, true);
        if (error != null) // Not allowed to access!
        {
            return makeInfraErrorResponse(error, ResponseAction.DELETE, responseParam);
        }

        try
        {
            if (removeJobInternal(jobID, responseParam, true))
            {
                return makeResopnseWithNoContent(false, false, ResponseAction.DELETE, responseParam);
            }
            else
            {
                return makeInfraErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Job not found.", getJobNamePlural()+" Job with jobID = "+jobID+" does not exist.", "Provider"), ResponseAction.DELETE, responseParam);
            }
        }
        catch (SIFException ex)
        {
            if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
            {
                ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
            }
            return makeInfraErrorResponse(ex.getErrorDetails(), ResponseAction.DELETE, responseParam);
        }      
     }
    
    @DELETE
    /*
     * NOTE: 
     * This method is not really implemented as DELETE is not supported with a payload. See PUT method for details about the way
     * a Bulk-DELETE is implemented according to SIF3 Spec. 
     */
    public Response removeJobs(String payload)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Delete Collection of Jobs "+getJobNamePlural()+" (REST DELETE) with input data: " + payload);
        }
        ErrorDetails error = new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Operation not supported.", "Use HTTP PUT with header field '"+RequestHeaderConstants.HDR_METHOD_OVERRIDE+"' set to "+HeaderValues.MethodType.DELETE.name()+" instead.");
        return makeInfraErrorResponse(error, ResponseAction.DELETE, getInitialCustomResponseParameters());
    }

    // ------------------------------------------------//
    // -- PUT Section: This is the U(pdate) in CRUD. --//
    // ------------------------------------------------//
    @PUT
    @Path("{jobID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response updateJob(String payload, @PathParam("jobID") String jobID, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Update Single Job "+getJobNamePlural()+" (REST PUT) for Job with ID = "+jobID+", URL Postfix mimeType = "+mimeType+"' and input data: " + payload);
        }
        
        ErrorDetails error = new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "The Job Object cannot be updated.", "Use updates to Phases or Status of a Job instead.");
        return makeInfraErrorResponse(error, ResponseAction.UPDATE, getInitialCustomResponseParameters());
    }
    
    @PUT
    public Response updateJobs(String deletePayload)
    {
        // Check what is really required: DELETE or UPDATE
        boolean doDelete = HeaderValues.MethodType.DELETE.name().equalsIgnoreCase(getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE));
      
        if (logger.isDebugEnabled())
        {
            if (doDelete) // This is for delete of jobs.
            {
                logger.debug("Delete Jobs "+getJobNamePlural()+" (REST PUT, method OVERRIDE=DELETE) with input data: " + deletePayload);
            }
            else
            {
                ErrorDetails error = new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "The Job Objects cannot be updated.", "Use updates to Phases or Status of Job(s) instead.");
                return makeInfraErrorResponse(error, ResponseAction.UPDATE, getInitialCustomResponseParameters());
            }
        }
        
        // If we get here the DELETE for a job collection is requested.
        ResponseParameters responseParam = getInitialCustomResponseParameters();
        ErrorDetails error = validClient(getJobNamePlural(), ServiceType.FUNCTIONAL, getRight(AccessRight.DELETE), AccessType.APPROVED, true, true);
        if (error != null) // Not allowed to access!
        {
            return makeInfraErrorResponse(error, ResponseAction.DELETE , responseParam);
        }
        
        List<String> jobIDs = null;
        try
        {
            jobIDs = getResourceIDsFromDeleteRequest(deletePayload);
        }
        catch (Exception ex)
        {
            logger.error("Failed to unmarshal delete job payload into an JobID List: "+ ex.getMessage()+"\n Offending payload:\n"+deletePayload, ex);
            return makeInfraErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal delete job payload: "+ ex.getMessage()), ResponseAction.DELETE, responseParam);
        }

        // Iterate through each job and do the same work as with a single job create request
        ArrayList<OperationStatus> statusList = new ArrayList<OperationStatus>();
        for (String jobID : jobIDs)
        {
            try
            {
                if (removeJobInternal(jobID, responseParam, true))
                {
                    statusList.add(new OperationStatus(jobID, Status.NO_CONTENT.getStatusCode()));
                }
                else
                {
                    statusList.add(new OperationStatus(jobID, Status.NOT_FOUND.getStatusCode(), new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Job not found.", getJobNamePlural()+" Job with jobID = "+jobID+" does not exist.", "Provider")));
                }
            }
            catch (SIFException ex)
            {
                // Add error to status list
                if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
                {
                    ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
                }
                statusList.add(new OperationStatus(jobID, ex.getErrorDetails().getErrorCode(), ex.getErrorDetails()));
            }
        }
        
        return makeDeleteMultipleResponse(statusList, Status.OK, responseParam);        
    }
    
    
    // -------------------//
    // -- HEAD Section: --//
    // -------------------//
    
    /*
     * HEAD Method for root service ie. .../service/{jobNames}. This is the only fully supported HEAD method that returns
     * all sort of things about the service including custom HTTP headers if set by the provider.
     */
    @HEAD
    public Response getServiceInfo()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Get Service Info (REST HEAD)");
        }
    
        ErrorDetails error = validClient(getJobNamePlural(), ServiceType.FUNCTIONAL, getRight(AccessRight.QUERY), AccessType.APPROVED, true, true);
        if (error != null) // Not allowed to access!
        {
            return makeResponse(null, false, error.getErrorCode(), true, ResponseAction.HEAD, getInitialCustomResponseParameters(), null, getFWInfraSchemaInfo());
        }
        
        FunctionalServiceProvider provider = getFSProvider();
        if (provider == null) // error already logged but we must return an error response for the caller
        {
            return makeResponse(null, false, Status.SERVICE_UNAVAILABLE.getStatusCode(), true, ResponseAction.HEAD, getInitialCustomResponseParameters(), null, getFWInfraSchemaInfo());
        }
    
        PagingInfo pagingInfo = null;
        try
        {
            pagingInfo = getPagingInfo();
            HeaderProperties defaultCustomHeaders = getInitialCustomResponseParameters().getHttpHeaderParams();
            RequestMetadata requestMetadata = getRequestMetadata(getSIF3SessionForRequest(), true);
            
            HeaderProperties customHeaders = provider.getServiceInfo(getNotNullSIFZone(), getNotNullSIFContext(), pagingInfo, requestMetadata);
            if (customHeaders != null)
            {
                // Copy customHeaders to defaultCustomHeaders to ensure the correct override order.
                defaultCustomHeaders.addHeaderProperties(customHeaders);
            }
            
            if (logger.isDebugEnabled())
            {
                logger.debug("Custom headers to be returned from 'getServiceInfo()' method:\n"+customHeaders);
            }

            // We know that the BaseFunctionalServiceProvider implements the ChangesSince Interface. Call appropriate methods here.
            // Check if provider supports Changes Since and if so we need to get the latest opaque changes since marker.
            ChangesSinceProvider csProvider = (ChangesSinceProvider)provider;
            if (csProvider.changesSinceSupported())
            {
                defaultCustomHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_CHANGES_SINCE_MARKER, csProvider.getLatestOpaqueMarker(getNotNullSIFZone(), getNotNullSIFContext(), pagingInfo, requestMetadata));
            }
            
            ResponseParameters responseParams = new ResponseParameters(defaultCustomHeaders);
            return makePagedResponse(null, false, pagingInfo, false, ResponseAction.HEAD, responseParams, null);
        }
        catch (PersistenceException ex)
        {
            return makeResponse(null, false, Status.INTERNAL_SERVER_ERROR.getStatusCode(), true, ResponseAction.HEAD, getInitialCustomResponseParameters(), null, getFWInfraSchemaInfo());
        }
        catch (IllegalArgumentException ex)
        {
            return makeResponse(null, false, Status.INTERNAL_SERVER_ERROR.getStatusCode(), true, ResponseAction.HEAD, getInitialCustomResponseParameters(), null, getFWInfraSchemaInfo());
        }
        catch (SIFException ex)
        {
            //HEAD cannot return payload. Only get status code and return that.
            return makeResponse(null, false, ex.getErrorDetails().getErrorCode(), true, ResponseAction.HEAD, getInitialCustomResponseParameters(), null, getFWInfraSchemaInfo());
        }
    }

    //-----------------------------//
    //-- Phase End-Point Section --//
    //-----------------------------//

    /*
     * Retrieve Operation of a Phase. Note that the returned payload is an object defined in a binding document. Most likely a DM Object but not
     * necessarily. Permissions must be checked against the Job & Phase. Also ensure that only the owner can request that operation which
     * means the fingerprint must match!
     */
    @GET
    @Path("{jobID:([^\\.]*)}/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response retrievePhaseObjects(@PathParam("jobID") String jobID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("\nRetrive Operation (HTTP GET) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID +"\nURL Postfix mimeType: " + mimeType);
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();
        
        //Let's get the full job data
        ExtendedJobInfo jobInfo = null;
        try
        {
            jobInfo = getFullJobInfo(jobID, true);
        }
        catch (SIFException ex)
        {
            if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
            {
                ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
            }
            ex.getErrorDetails().setDescription("Cannot retrieve data for phase "+phaseName+". Reason: "+ex.getErrorDetails().getDescription());
            return makeErrorResponse(ex.getErrorDetails(), ResponseAction.QUERY, responseParam, getResponseSchemaInfo(true, null));
        }
        
        FunctionalServiceProvider provider = getFSProvider();
        if (provider == null) // error already logged but we must return an error response for the caller
        {
            return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Missing Functional Service Provider", "No Provider for "+getJobNamePlural()+" available.", "Provider"), ResponseAction.QUERY, responseParam, getResponseSchemaInfo(true, null));
        }
        
        RequestMetadata requestMetadata = getRequestMetadata(getSIF3SessionForRequest(), true);
        ErrorDetails error = checkPhasePermission(jobInfo, phaseName, AccessRight.QUERY, provider, requestMetadata.getFingerprint(), true, false);
        if (error != null) // Not allowed to access!
        {
            return makeErrorResponse(error, ResponseAction.QUERY, responseParam, getResponseSchemaInfo(true, null));
        }
        
        // If all of the above is ok then we can start the real work...
        PagingInfo pagingInfo = null;
        try
        {
            pagingInfo = getPagingInfo();
            if (pretendDelayed())
            {
                // Simply send a response with status of 202
                return makeDelayedAcceptResponse(ResponseAction.QUERY);
            }
            else
            {
                PhaseDataResponse response = provider.retrieveDataFromPhase(new PhaseInfo(jobID, phaseName), getNotNullSIFZone(), getNotNullSIFContext(), pagingInfo, requestMetadata, responseParam, getFirstResponsePayloadMetadata(true));

                return makePagedResponse((response != null ? response.getData() : null), true, pagingInfo, false, ResponseAction.QUERY, responseParam, null);
            }
        }
        catch (PersistenceException ex)
        {
            return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not retrieve data from Job Phase.", "Failed to retrieve data for Job Phase "+getJobNamePlural()+"/"+phaseName+" with Paging Information: "+pagingInfo+" (Job ID = "+jobID+"). Problem reported: "+ex.getMessage(), "Provider ("+provider.getClass().getSimpleName()+")"), ResponseAction.QUERY, responseParam, getResponseSchemaInfo(true, null));         
        }
        catch (UnsupportedMediaTypeExcpetion ex)
        {
            return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not retrieve data from Job Phase.", "Failed to retrieve data for Job Phase "+getJobNamePlural()+"/"+phaseName+" with Paging Information: "+pagingInfo+" (Job ID = "+jobID+"). Requested Mime type "+getResponseMediaType(true).toString()+" is not supported: "+ex.getMessage(), "Provider ("+provider.getClass().getSimpleName()+")"), ResponseAction.QUERY, responseParam, getResponseSchemaInfo(true, null));           
        }
        catch (SIFException ex)
        {
            if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
            {
                ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
            }
            return makeErrorResponse(ex.getErrorDetails(), ResponseAction.QUERY, responseParam, getResponseSchemaInfo(true, null));
        }
    }

    /*
     * Creates Operation of a Phase. Note that the payload is an object defined in a binding document. Most likely a DM Object but not
     * necessarily. Data given in the payload is either to be created or is used to create implied data.
     * Permissions must be checked against the Job & Phase. Also ensure that only the owner can request that operation which
     * means the fingerprint must match!
     */
    @POST
    @Path("{jobID:([^\\.]*)}/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response createPhaseObjects(String payload, @PathParam("jobID") String jobID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("\nCreate Operation (HTTP POST) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID +"\nURL Postfix mimeType: " + mimeType + "\nPhase Payload:\n" + payload);
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();
        
        //Let's get the full job data
        ExtendedJobInfo jobInfo = null;
        try
        {
            jobInfo = getFullJobInfo(jobID, true);
        }
        catch (SIFException ex)
        {
            if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
            {
                ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
            }
            ex.getErrorDetails().setDescription("Cannot create data for phase "+phaseName+". Reason: "+ex.getErrorDetails().getDescription());
            return makeErrorResponse(ex.getErrorDetails(), ResponseAction.CREATE, responseParam, getResponseSchemaInfo(true, null));
        }
        
        FunctionalServiceProvider provider = getFSProvider();
        if (provider == null) // error already logged but we must return an error response for the caller
        {
            return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Missing Functional Service Provider", "No Provider for "+getJobNamePlural()+" available.", "Provider"), ResponseAction.CREATE, responseParam, getResponseSchemaInfo(true, null));
        }
        
        RequestMetadata requestMetadata = getRequestMetadata(getSIF3SessionForRequest(), false);
        ErrorDetails error = checkPhasePermission(jobInfo, phaseName, AccessRight.CREATE, provider, requestMetadata.getFingerprint(), true, false);
        if (error != null) // Not allowed to access!
        {
            return makeErrorResponse(error, ResponseAction.CREATE, responseParam, getResponseSchemaInfo(true, null));
        }
        
        // If all of the above is ok then we can start the real work...
        try
        {
            PhaseDataResponse response = provider.createDataInPhase(new PhaseInfo(jobID, phaseName), new PhaseDataRequest(payload, getRequestPayloadMetadata()), getAdvisory(), getNotNullSIFZone(), getNotNullSIFContext(), requestMetadata, responseParam, getFirstResponsePayloadMetadata(true));
            Status finalStatus = Status.CREATED;
            if ((response != null) && (response.getStatus() != null))
            {
                finalStatus = response.getStatus();
            }
            
            return makeResponse((response != null ? response.getData() : null), true, finalStatus.getStatusCode(), false, ResponseAction.CREATE, responseParam, null, getResponseSchemaInfo(true, null));
        }
        catch (PersistenceException ex)
        {
            return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not create data in Job Phase.", "Failed to create data for Job Phase "+getJobNamePlural()+"/"+phaseName+" (Job ID = "+jobID+"). Problem reported: "+ex.getMessage(), "Provider ("+provider.getClass().getSimpleName()+")"), ResponseAction.CREATE, responseParam, getResponseSchemaInfo(true, null));         
        }
        catch (UnsupportedMediaTypeExcpetion ex)
        {
            return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not create data in Job Phase.", "Failed to create data for Job Phase "+getJobNamePlural()+"/"+phaseName+" (Job ID = "+jobID+"). Requested Mime type "+getResponseMediaType(true).toString()+" is not supported: "+ex.getMessage(), "Provider ("+provider.getClass().getSimpleName()+")"), ResponseAction.CREATE, responseParam, getResponseSchemaInfo(true, null));           
        }
        catch (SIFException ex)
        {
            if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
            {
                ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
            }
            return makeErrorResponse(ex.getErrorDetails(), ResponseAction.CREATE, responseParam, getResponseSchemaInfo(true, null));
        }
    }
    
    /*
     * Update Operation of a Phase. Note that the payload is an object defined in a binding document. Most likely a DM Object but not
     * necessarily. Data given in the payload is either to be updated or is used to update implied data. This operation must also
     * cover the delete as with standard Object services.
     * Permissions must be checked against the Job & Phase. Also ensure that only the owner can request that operation which
     * means the fingerprint must match!
     */
    @PUT
    @Path("{jobID:([^\\.]*)}/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response updatePhaseObjects(String payload, @PathParam("jobID") String jobID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        // Check what is really required: DELETE or UPDATE
        boolean doDelete = HeaderValues.MethodType.DELETE.name().equalsIgnoreCase(getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE));

        if (logger.isDebugEnabled())
        {
            if (doDelete) // This is for delete of jobs.
            {
                logger.debug("\nDelete Operation (HTTP PUT, method OVERRODE=DELETE) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID +"\nURL Postfix mimeType: " + mimeType + "\nPhase Payload:\n" + payload);
            }
            else
            {
                logger.debug("\nUpdate Operation (HTTP PUT) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID +"\nURL Postfix mimeType: " + mimeType + "\nPhase Payload:\n" + payload);
            }
        }
        AccessRight right = doDelete ? AccessRight.DELETE : AccessRight.UPDATE;
        ResponseAction responseAction = doDelete ? ResponseAction.DELETE : ResponseAction.UPDATE;

        ResponseParameters responseParam = getInitialCustomResponseParameters();
        
        //Let's get the full job data
        ExtendedJobInfo jobInfo = null;
        try
        {
            jobInfo = getFullJobInfo(jobID, true);
        }
        catch (SIFException ex)
        {
            if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
            {
                ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
            }
            ex.getErrorDetails().setDescription("Cannot "+right.name()+" data for phase "+phaseName+". Reason: "+ex.getErrorDetails().getDescription());
            return makeErrorResponse(ex.getErrorDetails(), responseAction, responseParam, getResponseSchemaInfo(true, null));
        }
        
        FunctionalServiceProvider provider = getFSProvider();
        if (provider == null) // error already logged but we must return an error response for the caller
        {
            return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Missing Functional Service Provider", "No Provider for "+getJobNamePlural()+" available.", "Provider"), responseAction, responseParam, getResponseSchemaInfo(true, null));
        }
        
        RequestMetadata requestMetadata = getRequestMetadata(getSIF3SessionForRequest(), false);
        ErrorDetails error = checkPhasePermission(jobInfo, phaseName, right, provider, requestMetadata.getFingerprint(), true, false);
        if (error != null) // Not allowed to access!
        {
            return makeErrorResponse(error, responseAction, responseParam, getResponseSchemaInfo(true, null));
        }
        
        // If all of the above is ok then we can start the real work...
        try
        {
            Status finalStatus = null;
            PhaseDataResponse response = null;
            if(doDelete)
            {
                response = provider.deleteDataInPhase(new PhaseInfo(jobID, phaseName), new PhaseDataRequest(payload, getRequestPayloadMetadata()), getNotNullSIFZone(), getNotNullSIFContext(), requestMetadata, responseParam, getFirstResponsePayloadMetadata(true));
                finalStatus = Status.NO_CONTENT;
            }
            else
            {
                response = provider.updateDataInPhase(new PhaseInfo(jobID, phaseName), new PhaseDataRequest(payload, getRequestPayloadMetadata()), getNotNullSIFZone(), getNotNullSIFContext(), requestMetadata, responseParam, getFirstResponsePayloadMetadata(true));                
                finalStatus = Status.OK;
            }
            
            if ((response != null) && (response.getStatus() != null))
            {
                finalStatus = response.getStatus();
            }
            
            return makeResponse((response != null ? response.getData() : null), true, finalStatus.getStatusCode(), false, responseAction, responseParam, null, getResponseSchemaInfo(true, null));
        }
        catch (PersistenceException ex)
        {
            return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not "+responseAction.name()+" data in Job Phase.", "Failed to "+responseAction.name()+" data for Job Phase "+getJobNamePlural()+"/"+phaseName+" (Job ID = "+jobID+"). Problem reported: "+ex.getMessage(), "Provider ("+provider.getClass().getSimpleName()+")"), responseAction, responseParam, getResponseSchemaInfo(true, null));         
        }
        catch (UnsupportedMediaTypeExcpetion ex)
        {
            return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not "+responseAction.name()+" data in Job Phase.", "Failed to "+responseAction.name()+" data for Job Phase "+getJobNamePlural()+"/"+phaseName+" (Job ID = "+jobID+"). Requested Mime type "+getResponseMediaType(true).toString()+" is not supported: "+ex.getMessage(), "Provider ("+provider.getClass().getSimpleName()+")"), responseAction, responseParam, getResponseSchemaInfo(true, null));           
        }
        catch (SIFException ex)
        {
            if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
            {
                ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
            }
            return makeErrorResponse(ex.getErrorDetails(), responseAction, responseParam, getResponseSchemaInfo(true, null));
        }
    }
    
    /*
     * NOTE: 
     * This method is not really implemented as DELETE is not supported with a payload. See PUT method for details about the way
     * a DELETE is implemented according to SIF3 Spec. 
     */
    @DELETE
    @Path("{jobID:([^\\.]*)}/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response removePhaseObjects(String payload, @PathParam("jobID") String jobID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("\nDelete Operation (HTTP DELETE) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID +"\nURL Postfix mimeType: " + mimeType + "\nPhase Payload:\n" + payload);
        }

        ErrorDetails error = new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Operation not supported.", "Use HTTP PUT with header field '"+RequestHeaderConstants.HDR_METHOD_OVERRIDE+"' set to "+HeaderValues.MethodType.DELETE.name()+" instead.");
        return makeInfraErrorResponse(error, ResponseAction.DELETE, getInitialCustomResponseParameters());
    }
    
    //------------------------------//
    //-- States End-Point Section --//
    //------------------------------//

    /*
     * Retrieve States of a Phase. Note that the returned payload is an collection of states. Ensure that only the owner can request that 
     * operation which means the fingerprint must match!
     */
    @GET
    @Path("{jobID:([^\\.]*)}/{phaseName:([^\\.]*)}/states{mimeType:(\\.[^/]*?)?}")
    public Response retrievePhaseStates(@PathParam("jobID") String jobID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("\nRetrive States (HTTP GET) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID +"\nURL Postfix mimeType: " + mimeType);
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();
        
        //Let's get the full job data
        ExtendedJobInfo jobInfo = null;
        try
        {
            jobInfo = getFullJobInfo(jobID, true);
        }
        catch (SIFException ex)
        {
            if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
            {
                ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
            }
            ex.getErrorDetails().setDescription("Failed to retrieve states for phase "+phaseName+". Reason: "+ex.getErrorDetails().getDescription());
            return makeInfraErrorResponse(ex.getErrorDetails(), ResponseAction.QUERY, responseParam);
        }
        
        RequestMetadata requestMetadata = getRequestMetadata(getSIF3SessionForRequest(), true);
        ErrorDetails error = checkPhasePermission(jobInfo, phaseName, AccessRight.QUERY, null, requestMetadata.getFingerprint(), false, true);
        if (error != null) // Not allowed to access!
        {
            return makeInfraErrorResponse(error, ResponseAction.QUERY, responseParam);
        }

        try
        {
            StateCollectionType states = getStatesOfPhase(jobInfo, phaseName);
            return makeResponse(states, false, Status.OK.getStatusCode(), false, ResponseAction.QUERY, responseParam, getInfraMarshaller(), getFWInfraSchemaInfo());  
        }
        catch (PersistenceException ex)
        {
            return makeInfraErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to access Job.", "Failed to access Job for "+getJobNamePlural()+" and JobID = "+jobID+". Problem reported: "+ex.getMessage(), "Provider"), ResponseAction.QUERY, responseParam);
        }
    }

    /*
     * Creates a state for the selected phase and job. Note this should "add" a new state of the state list for the given job and phase.
     * Note that the payload must be a State Object as defined infrastructure data model. Permissions must be checked against the Job, 
     * Phase and State. Also ensure that only the owner can request that operation which means the fingerprint must match!
     */
    @POST
    @Path("{jobID:([^\\.]*)}/{phaseName:([^\\.]*)}/states/state{mimeType:(\\.[^/]*?)?}")
    public Response createPhaseState(String payload, @PathParam("jobID") String jobID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("\nCreate State (HTTP POST) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID +"\nURL Postfix mimeType: " + mimeType + "\nPayload:\n" + payload);
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();
        
        //Let's get the full job data
        ExtendedJobInfo jobInfo = null;
        try
        {
            jobInfo = getFullJobInfo(jobID, true);
        }
        catch (SIFException ex)
        {
            if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
            {
                ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
            }
            ex.getErrorDetails().setDescription("Failed to add state to phase "+phaseName+". Reason: "+ex.getErrorDetails().getDescription());
            return makeInfraErrorResponse(ex.getErrorDetails(), ResponseAction.CREATE, responseParam);
        }
        
        FunctionalServiceProvider provider = getFSProvider();
        if (provider == null) // error already logged but we must return an error response for the caller
        {
            return makeInfraErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Missing Functional Service Provider", "No Provider for "+getJobNamePlural()+" available.", "Provider"), ResponseAction.CREATE, responseParam);
        }

        RequestMetadata requestMetadata = getRequestMetadata(getSIF3SessionForRequest(), false);
        ErrorDetails error = checkPhasePermission(jobInfo, phaseName, AccessRight.CREATE, provider, requestMetadata.getFingerprint(), true, true);
        if (error != null) // Not allowed to access!
        {
            return makeInfraErrorResponse(error, ResponseAction.CREATE, responseParam);
        }
        
        // Get the payload and marshal it into an object. From there we can retrieve potential initialisation parameters.
        if (payload == null) // something is wrong!
        {
            error = new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Missing State Data", "No State Data provided in request for Job "+getJobNamePlural(), "Provider");
            return makeInfraErrorResponse(error, ResponseAction.CREATE, responseParam);  
        }
        
        StateType state = null;
        try
        {
            payload = mapToFrameworkInfraVersion(payload);
            state = (StateType)getInfraUnmarshaller().unmarshal(payload, StateType.class, getRequestPayloadMetadata().getMimeType(), getRequestPayloadMetadata().getSchemaType());
            if (state.getType() == null) // not a valid value! Most likely invalid state string received.
            {
                return makeInfraErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Invalid state recieved.","The consumer has provided an invlaid phase state. Valid values are: "+getValidPhaseStates(),"Provider"), ResponseAction.CREATE, responseParam);                
            }
        }
        catch (Exception ex)
        {
            logger.error("Failed to unmarshal payload into an State Type Data: "+ ex.getMessage()+"\n Offending payload:\n"+payload, ex);
            return makeInfraErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal StateType payload: "+ ex.getMessage(), null, "Provider"), ResponseAction.CREATE, responseParam);
        }    
        
        // If all of the above is ok then we can start the real work...
        try
        {
            // will update DB but also values in jobInfo
            StateType newState = addPhaseStateByConsumer(jobInfo, phaseName, state); 
            if (newState != null) // we had a state update
            {
                // pass it to the specific provider class for additional processing.
                try
                {
                   provider.phaseStateUpdatedByConsumer(new PhaseInfo(jobID, phaseName), PhaseState.valueOf(newState.getType().value()), getNotNullSIFZone(), getNotNullSIFContext(),  getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
                }
                catch (PersistenceException ex)
                {
                    makeInfraErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update state to phase.",  getJobNamePlural()+": Failed to add state to phase "+phaseName+" for and JobID = "+jobID+".", "Provider ("+provider.getClass().getSimpleName()+")"), ResponseAction.CREATE, responseParam);
                }
                catch (SIFException ex)
                {
                    makeInfraErrorResponse(ex.getErrorDetails(), ResponseAction.CREATE, responseParam);
                }
    
                // Finally create response...
                return makeResponse(newState, false, Status.CREATED.getStatusCode(), false, ResponseAction.CREATE, responseParam, getInfraMarshaller(), getFWInfraSchemaInfo());
            }
            else
            {
                return makeInfraErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(),"Failed to add state to phase.",  getJobNamePlural()+": Failed to add state to phase "+phaseName+" for and JobID = "+jobID+".", "Provider"), ResponseAction.CREATE, responseParam);                
            }
        }
        catch (SIFException ex)
        {
            if (StringUtils.isEmpty(ex.getErrorDetails().getScope()))
            {
                ex.getErrorDetails().setScope("Provider ("+getJobNamePlural()+")");
            }
            return makeInfraErrorResponse(ex.getErrorDetails(), ResponseAction.CREATE, responseParam);
        }
    }

    //--------------------------------------------------------//
    //-- SIF 3.x Spec. Unsupported States End-Point Section --//
    //--------------------------------------------------------//

    /*
     * Retrieve a State of a Phase and job is not supported as per SIF3.x specification because states don't have a refID.
     */
    @GET
    @Path("{jobID:([^\\.]*)}/{phaseName:([^\\.]*)}/states/{stateID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response retrievePhaseState(@PathParam("jobID") String jobID, @PathParam("phaseName") String phaseName, @PathParam("stateID") String stateID, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("\nGet State (HTTP GET) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID+"\nState ID: "+ stateID  +"\nURL Postfix mimeType: " + mimeType);
        }

        return makeInfraErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "A particular State of a Phase cannot be retrievd."), ResponseAction.QUERY, getInitialCustomResponseParameters());
    }

    
    /*
     * Batch creates of states is not allowed according to the SIF3.x specification
     */
    @POST
    @Path("{jobID:([^\\.]*)}/{phaseName:([^\\.]*)}/states{mimeType:(\\.[^/]*?)?}")
    public Response createPhaseStates(String payload, @PathParam("jobID") String jobID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("\nCreate States (HTTP POST) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID +"\nURL Postfix mimeType: " + mimeType + "\nPayload:\n" + payload);
        }

        return makeInfraErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Batch States creation of a Phase not allowed."), ResponseAction.CREATE, getInitialCustomResponseParameters());
    }

    /*
     * Update a state for the selected phase and job is not supported as per SIF3.x specification because states don't have a refID.
     */
    @PUT
    @Path("{jobID:([^\\.]*)}/{phaseName:([^\\.]*)}/states{mimeType:(\\.[^/]*?)?}")
    public Response updatePhaseStates(String payload, @PathParam("jobID") String jobID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("\nUpdate States (HTTP POST) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID +"\nURL Postfix mimeType: " + mimeType + "\nPayload:\n" + payload);
        }

        return makeInfraErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "The States of a Phase cannot be updated."), ResponseAction.UPDATE, getInitialCustomResponseParameters());
    }

    /*
     * Update a state for the selected phase and job is not supported as per SIF3.x specification because states don't have a refID.
     */
    @PUT
    @Path("{jobID:([^\\.]*)}/{phaseName:([^\\.]*)}/states/{stateID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response updatePhaseState(@PathParam("jobID") String jobID, @PathParam("phaseName") String phaseName, @PathParam("stateID") String stateID, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("\nUpdate a State (HTTP UPDATE) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID+"\nState ID: "+ stateID  +"\nURL Postfix mimeType: " + mimeType);
        }

        return makeInfraErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "A State of a Phase cannot be updated."), ResponseAction.UPDATE, getInitialCustomResponseParameters());
    }

    /*
     * Delete states for the selected phase and job is not supported as per SIF3.x specification because states don't have a refID.
     */
    @DELETE
    @Path("{jobID:([^\\.]*)}/{phaseName:([^\\.]*)}/states{mimeType:(\\.[^/]*?)?}")
    public Response removePhaseStates(String payload, @PathParam("jobID") String jobID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("\nDelete States (HTTP DELETE) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID +"\nURL Postfix mimeType: " + mimeType + "\nPayload:\n" + payload);
        }

        return makeInfraErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "The States of a Phase cannot be deleted."), ResponseAction.DELETE, getInitialCustomResponseParameters());
    }

    /*
     * Delete a state for the selected phase and job is not supported as per SIF3.x specification because states don't have a refID.
     */
    @DELETE
    @Path("{jobID:([^\\.]*)}/{phaseName:([^\\.]*)}/states/{stateID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response removePhaseState(@PathParam("jobID") String jobID, @PathParam("phaseName") String phaseName, @PathParam("stateID") String stateID, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("\nDelete a State (HTTP DELETE) for Phase: "+phaseName+ "\nFunctional Service: "+getJobNamePlural()+"\nJob ID: "+ jobID+"\nState ID: "+ stateID  +"\nURL Postfix mimeType: " + mimeType);
        }

        return makeInfraErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "A State of a Phase cannot be deleted."), ResponseAction.DELETE, getInitialCustomResponseParameters());
    }

    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    private String getJobNamePlural()
    {
        return jobNamePlural;
    }

    private void setJobNamePlural(String jobNamePlural)
    {
        this.jobNamePlural = jobNamePlural;
    }
    
    /*
     * This method is a helper to determine what the actual access right is. If a provider is a direct provider an access right is the actual
     * right of the consumer as set in the environment ACL. If the provider is in a brokered environment its right is the ACL in relation
     * to the broker. In such a case the right is simply 'PROVIDE'.
     */
    private AccessRight getRight(AccessRight directEnvRight)
    {
        // If we are in a brokered environment then the access right must be PROVIDE. In a DIRECT environment the access right must be QUERY.
        return getProviderEnvironment().getEnvironmentType() == EnvironmentType.DIRECT ? directEnvRight : AccessRight.PROVIDE;
    }

    private FunctionalServiceProvider getFSProvider()
    {
        if (fsProvider == null) // No provider known for this Object Type! This is an issue and needs to be logged.
        {
            logger.error("No Provider known for the object with the name: "+getJobNamePlural());
        }
        return fsProvider;
    }
    
    private void setFSProvider(FunctionalServiceProvider fsProvider)
    {
        this.fsProvider = fsProvider;
    }

    private JobType getJobDataFromTemplate() throws PersistenceException
    {
        // First we get the job template
        JobType job = getEnvironmentManager().getJobTemplate(getJobNamePlural());
        if (job == null) // not good. No such job exists in job template store
        {
            String errorMsg = "No Job Template with the name " + getJobNamePlural() + " exists. Needs to be configured in appropriate tables first.";
            logger.error(errorMsg);
            throw new PersistenceException(errorMsg);
        }
        
        // If we get here all good. Populate initialisation parameters.
        Calendar now = Calendar.getInstance();
        job.setCreated(now);
        return job;
    }

    private JobType createJobInternal(JobType payloadJob, ResponseParameters responseParam, boolean consumerRequested) throws SIFException
    {
        // Get the template for the job to be created and populate missing bits.
        JobType jobFromTemplate = null;
        try
        {
            jobFromTemplate = getJobDataFromTemplate();
            
            // Set the initialisation values
            jobFromTemplate.setInitialization(payloadJob.getInitialization());
            
            //Check if we need to allocate a jobId.
            jobFromTemplate.setId(getAdvisory() ? payloadJob.getId() : UUIDGenerator.getUUID());
            
            jobFromTemplate.setState(JobStateType.NOTSTARTED); // Default to not started.
        }
        catch (PersistenceException ex)
        {
            throw new SIFException(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "No Job Template Data found for "+getJobNamePlural(), ex.getMessage(), "Provider"));
        }
        
        // pass it to the specific provider class for additional processing.
        FunctionalServiceProvider provider = getFSProvider();
        if (provider == null) // error already logged but we must return an error response for the caller
        {
            throw new SIFException(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Missing Functional Service Provider", "No Provider for "+getJobNamePlural()+" available.", "Provider"));
        }
    
        RequestMetadata requestMetadata = getRequestMetadata(getSIF3SessionForRequest(), false);
        try
        {
            jobFromTemplate = (JobType)provider.createJob(jobFromTemplate, getNotNullSIFZone(), getNotNullSIFContext(), requestMetadata, responseParam);
        }
        catch (PersistenceException ex)
        {
            throw new SIFException(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create Job.", "Failed to create Job for "+getJobNamePlural()+". Problem reported: "+ex.getMessage(), "Provider"));
        }

        SIF3Session sif3Session = getSIF3SessionForRequest();
        
        // Finally save it in the SIF3_JOB Table and SIF3_JOB_EVENT table
        try
        {
            String envID = null;
            if (!isBrokeredEnvironment()) // In a DIRECT environment we have access to the consumer envID.
            {
                envID = sif3Session.getEnvironmentID();
            }
            
            // Save the job data to the appropriate tables.
            getProviderJobManager().saveNewJob(jobFromTemplate, getJobNamePlural(), getNotNullSIFZone(), getNotNullSIFContext(), envID, requestMetadata.getFingerprint(), consumerRequested);
            
            return jobFromTemplate;
        }
        catch (PersistenceException ex)
        {
          throw new SIFException(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create Job.", "Failed to create Job. Problem reported: "+ex.getMessage(), "Provider"));
        }
    }

    private boolean removeJobInternal(String jobID, ResponseParameters responseParam, boolean consumerRequested) throws SIFException
    {
        // pass it to the specific provider class for additional processing.
        FunctionalServiceProvider provider = getFSProvider();
        if (provider == null) // error already logged but we must return an error response for the caller
        {
            throw new SIFException(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Missing Functional Service Provider", "No Provider for "+getJobNamePlural()+" available.", "Provider"));
        }
    
        // We must pass it to provider regardless if framework knows about job or not.
        boolean deletedInProvider = false;
        try
        {
            deletedInProvider = provider.deleteJob(jobID, getNotNullSIFZone(), getNotNullSIFContext(), getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
        }
        catch (PersistenceException ex)
        {
            throw new SIFException(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to remove Job.", "Failed to remove Job for "+getJobNamePlural()+" and JobID = "+jobID+". Problem reported: "+ex.getMessage(), "Provider"));
        }
        
        // Job may no longer be available in provider but we may still hold it in the JOB table. If so, we must remove it and make
        // appropriate entry in JOB_EVENT table. If the job doesn't exist there either we return false, otherwise we return true.
        // Finally remove it in the SIF3_JOB Table and make entry in SIF3_JOB_EVENT table
        boolean deletedInJobTable = false;
        try
        {
            deletedInJobTable = getProviderJobManager().removeJob(jobID, consumerRequested);

        }
        catch (PersistenceException ex)
        {
          throw new SIFException(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to remove Job.", "Failed to remove Job with jobID = "+jobID+". Problem reported: "+ex.getMessage(), "Provider"));
        }

        return deletedInProvider || deletedInJobTable;
    }
    
    /*
     * @param jobInfo The job data. MUST NOT BE NULL.
     * @param phaseName The phase for which the permission check shall be performed.
     * @param right The permission required.
     * @param provider Can be null if checkForEndstate == false.
     * @param fingerprint the fingerprint of the consumer.
     * @param checkForEndstate TRUE => provider is required. It will check if the given job is in an end state. FALSE no end state check
     *                         is performed.
     * @param checkPhaseStatePermission Indicates if permissions on the phase itself must be checked (FALSE) or permission on the
     *                                  Phase State must be checked (TRUE)
     * 
     * @return ErrorDetails not null then it holds the reason why permission check failed.
     */
    private ErrorDetails checkPhasePermission(ExtendedJobInfo jobInfo, String phaseName, AccessRight right, FunctionalServiceProvider provider, String fingerprint, boolean checkForEndstate, boolean checkPhaseStatePermission)
    {
        ErrorDetails error = validateSession(false); // Check if authenticated
        if (error != null) // Not allowed to access!
        {
            return error;
        }
        
        if (checkForEndstate)
        {
            if (isJobInEndState(jobInfo.getDBJob().getCurrentState(), provider))
            {
                return new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Job Phase cannot be modifed.",  getJobNamePlural()+" with Job ID = "+jobInfo.getDBJob().getJobID()+" is in state " +jobInfo.getDBJob().getCurrentState()+ " and can no longer be modified (End State)", "Provider");
            }
        }
        
        //Check if service is valid
        SIF3Session session = getSIF3SessionForRequest();
        SIFZone zone = getNotNullSIFZone();
        SIFContext context = getNotNullSIFContext();
        ServiceInfo serviceInfo = session.getServiceInfoForService(zone, context, getJobNamePlural(), ServiceType.FUNCTIONAL);
        if (serviceInfo == null) // Found no service for the given functional service name.
        {
            return new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Access Denied.", "Consumer is not authorized to access the functional service '"+getJobNamePlural()+"'.", "Provider side check."); 
        }
        try
        {
            if (checkPhaseStatePermission)
            {
                if (!hasConsumerPhaseStatusRightForJob(jobInfo, getJobNamePlural(), phaseName, zone, context, fingerprint, right, AccessType.APPROVED))
                {
                    return new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", right.name()+ " access is not set to "+AccessType.APPROVED.name()+" for states of phase "+phaseName+" of the functional service "+getJobNamePlural()+" and the given zone ("+zone.getId()+"), context ("+context.getId()+") and Job ("+jobInfo.getDBJob().getJobID()+").", "Provider side check.");    
                }                
            }
            else
            {
                if (!hasConsumerPhaseRightForJob(jobInfo, getJobNamePlural(), phaseName, zone, context, right, AccessType.APPROVED))
                {
                    return new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", right.name()+ " access is not set to "+AccessType.APPROVED.name()+" for phase "+phaseName+" of the functional service "+getJobNamePlural()+" and the given zone ("+zone.getId()+"), context ("+context.getId()+") and Job ("+jobInfo.getDBJob().getJobID()+").", "Provider side check.");    
                }
            }
        }
        catch (ResourceNotFoundException ex)
        {
            return ex.getErrorDetails();
        }
        
        // If we get here then all checks are passed!
        return null;
    }
    
    /*
     * This method checks if a consumer identified by serviceName, zone, context and fingerprint has the given access right for
     * the phase status for the job given by its jobID. If it has then TRUE is returned otherwise FALSE is returned. Also if any
     * errors occur at the lower level (e.g. DB) and the right cannot be retrieved then false is returned. 
     * 
     * @param jobInfo The job for which the access right shall be determined. MUST NOT BE NULL!
     * @param serviceName The functional service for which the jobId is applicable. MUST NOT BE NULL!
     * @param phaseName The phase for which the status rights shall be checked. MUST NOT BE NULL!
     * @param zone The zone in which the job is applicable. MUST NOT BE NULL!
     * @param context The context in which the job is applicable. MUST NOT BE NULL!
     * @param fingerprint The fingerprint of the consumer making the request. Can be null.
     * @param right The right to check. MUST NOT BE NULL!
     * @param accessType The value of the right to check. MUST NOT BE NULL!
     * 
     * @return TRUE: The consumer has the access requested. FALSE: Consumer has not the required access right.
     * 
     * @throws ResourceNotFoundException The given jobID is not applicable for the given parameters.
     */
    private boolean hasConsumerPhaseStatusRightForJob(ExtendedJobInfo jobInfo, String serviceName, String phaseName, SIFZone zone, SIFContext context, String fingerprint, AccessRight right, AccessType accessType) throws ResourceNotFoundException
    {
        if (isConsumerValid(jobInfo, serviceName, zone, context, fingerprint))
        {
            PhaseType phase = getPhase(jobInfo.getXMLJob(), phaseName);
            if (phase == null) // no phase found => return false
            {
                return false;
            }
            
            return hasRight(phase.getStatesRights(), right, accessType);
        }
        else
        {
            throw new ResourceNotFoundException("Job does not exist.", "No Job with ID = "+jobInfo.getDBJob().getJobID()+" found for service = "+serviceName+" in zone = "+zone.getId()+" and context = "+context.getId()+". Fingerprint is "+fingerprint+".", "Provider");                
        }
    }

    /*
     * This method checks if a consumer identified by serviceName, zone and context has the given access right for
     * the phase for the job given by its jobID. If it has then TRUE is returned otherwise FALSE is returned. Also if any
     * errors occur at the lower level (e.g. DB) and the right cannot be retrieved then false is returned. 
     * 
     * @param jobInfo The job for which the access right shall be determined. MUST NOT BE NULL!
     * @param serviceName The functional service for which the jobId is applicable. MUST NOT BE NULL!
     * @param phaseName The phase for which the status rights shall be checked. MUST NOT BE NULL!
     * @param zone The zone in which the job is applicable. MUST NOT BE NULL!
     * @param context The context in which the job is applicable. MUST NOT BE NULL!
     * @param right The right to check. MUST NOT BE NULL!
     * @param accessType The value of the right to check. MUST NOT BE NULL!
     * 
     * @return TRUE: The consumer has the access requested. FALSE: Consumer has not the required access right.
      * 
     * @throws ResourceNotFoundException The given jobID is not applicable for the given parameters.
    */
    private boolean hasConsumerPhaseRightForJob(ExtendedJobInfo jobInfo, String serviceName, String phaseName, SIFZone zone, SIFContext context, AccessRight right, AccessType accessType) throws ResourceNotFoundException
    {
        PhaseType phase = getPhase(jobInfo.getXMLJob(), phaseName);
        if (phase == null) // no phase found => return false
        {
            return false;
        }
        
        return hasRight(phase.getRights(), right, accessType);
    }
  
    /*
     * This method adds the state to a job phase state. This method can be called by the provider or a consumer through the appropriate
     * service end-point (POST .../{serviceName}/{jobId}/{phaseName}/states/state). In case where the update is requested by a consumer it 
     * is expected that any tests weather the job is valid for the given consumer is already done. This method will only use the jobID to
     * determine which job to update. If the job or phase doesn't exist then no action is taken. Again it is expected that appropriate 
     * tests are already performed. If events are enabled it will also add the appropriate job event to the event table.
     * The following properties will be defaulted to:
     * - Created: Current Date & Time
     * - LastModifed:  Current Date & Time
     * - ID: If it was null then it will be set to a UUID.
     * After a successful call to this method the DBJob and XML Job in the jobInfo hold the latest values (eg. state)
     * 
     * @param jobInfo The job to be updated. Cannot be null.
     * @param phaseName The name of the phase for which the state shall be set. Cannot be null
     * @param newState The new state of the phase.
     * 
     * @return The state created. This may have additional properties set (see default values in description). If null is returned then
     *         the state could not be added.
     * 
     * @throws SIFException Job can not be accessed or updated due to a DB access issue.
     */
    private StateType addPhaseStateByConsumer(ExtendedJobInfo jobInfo, String phaseName, StateType newState) throws SIFException
    {
        PhaseType phase = getPhase(jobInfo.getXMLJob(), phaseName);
        if (phase != null) // add the state
        {
            StateType state = addStateToPhase(phase, newState);
            try
            {
                getProviderJobManager().updateJob(jobInfo, true);
            }
            catch (PersistenceException ex)
            {
               throw new SIFException(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update state to phase.",  getJobNamePlural()+": Failed to add state to phase "+phaseName+" for and JobID = "+jobInfo.getDBJob().getJobID()+".", "Provider"));
            }

            return state;
        }
        
        return null;
    }
    
    /*
     * This method returns a State List for the given Job and Phase. This method can be called by the provider or a consumer through 
     * the appropriate service end-point (GET .../{serviceName}/{jobId}/{phaseName}/states). In case where the list is requested by a consumer 
     * it is expected that any tests weather the job is valid for the given consumer is already done. This method will only use the jobID to
     * determine which job to use. If the job or phase doesn't exist then an empty list is returned.
     * 
     * @param jobInfo The the Job to be retrieved.
     * @param phaseName The name of the phase for which the status list shall be retrieved.
     * 
     * @return See desc.
     * 
     * @throws PersistenceException Job can not be accessed due to a DB access issue.
     */
    private StateCollectionType getStatesOfPhase(ExtendedJobInfo jobInfo, String phaseName) throws PersistenceException
    {
        PhaseType phase = getPhase(jobInfo.getXMLJob(), phaseName);
        if (phase != null) // add the state
        {
            StateCollectionType states = phase.getStates();
            if (states == null)
            {
                states = new StateCollectionType();
            }
            
            return states;
        }
        
        // If we get here then we haven't found an appropriate job of phase.
        return new StateCollectionType();
    }

    private boolean isConsumerValid(ExtendedJobInfo job, String serviceName, SIFZone zone, SIFContext context, String fingerprint)
    {
        // Let'scheck if the fingerprint is given and if so it must match
        boolean allGood = true;
        if (StringUtils.notEmpty(fingerprint))
        {
            allGood = allGood && fingerprint.equals(job.getDBJob().getFingerprint());
        }
        
        if (allGood) // lets check the rest: Is it the correct consumer in the right zone & context?
        {
            allGood = allGood && (serviceName.equals(job.getDBJob().getServiceName()) && zone.getId().equals(job.getDBJob().getZoneID()) && context.getId().equals(job.getDBJob().getContextID()));
        }
        
        return allGood;
    }

    private PhaseType getPhase(JobType job, String phaseName)
    {
        // Start iterating through the phases until it matches.
        if (job.getPhases() == null) // no phases known
        {
            return null;
        }
        
        for (PhaseType phase : job.getPhases().getPhase())
        {
            if (phase.getName().equals(phaseName)) // found the applicable phase
            {
                return phase;
            }
        }
        
        // if we get here then we haven't found the correct phase
        return null;
    }
    
    private StateType addStateToPhase(PhaseType phase, StateType newState)
    {
        if (phase != null) // add the state
        {
            StateCollectionType states = phase.getStates();
            if (states == null)
            {
                states = new StateCollectionType();
                phase.setStates(states);
            }
            
            StateType state = new StateType();
            state.setCreated(Calendar.getInstance());
            state.setLastModified(Calendar.getInstance());
            state.setId(StringUtils.isEmpty(newState.getId()) ? UUIDGenerator.getUUID() : newState.getId());
            state.setType(newState.getType());
            states.getState().add(state);
            
            return state;
        }
        
        return null;
    }

    private boolean hasRight(RightsType rights, AccessRight right, AccessType accessType)
    {
        if (rights == null)
        {
            return false;
        }
        
        for (RightType rightType : rights.getRight())
        {
            if (rightType.getType().equals(right.name()) && rightType.getValue().name().equals(accessType.name())) // Found match!
            {
                return true;
            }
        }
        
        // if we get here then we haven't found the correct right
        return false;
    }

    private ProviderJobManager getProviderJobManager()
    {
        return (ProviderJobManager)getEnvironmentManager().getJobManager();
    }

    private ExtendedJobInfo getFullJobInfo(String jobID, boolean xmlMustBeAvailable) throws SIFException, ResourceNotFoundException 
    {
        try
        {
            ExtendedJobInfo jobInfo = getProviderJobManager().getJobInfo(jobID);
            if (jobInfo != null)
            {
                if (xmlMustBeAvailable && !jobInfo.isXMLValid()) // we need the XML but it is invalid!
                {
                    throw new SIFException(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve job.",  getJobNamePlural()+": Failed to retrieve Job with ID = "+jobID+". The XML associated with the job is invalid.", "Provider");                   
                }
            }
            else
            {
                throw new ResourceNotFoundException("Job does not exist.",  getJobNamePlural()+": Job with ID = "+jobID+" does not exist.", "Provider");
            }
            
            return jobInfo;
            
        }
        catch (PersistenceException ex)
        {
            throw new SIFException(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve job.",  getJobNamePlural()+": Failed to retrieve Job with ID = "+jobID+". Error reported: "+ex.getMessage(), "Provider");
        }
    }

    private boolean isJobInEndState(String state, FunctionalServiceProvider provider)
    {
        return provider.isJobEndState(JobState.valueOf(state));
    }

    private String getValidPhaseStates()
    {
        PhaseStateType[] states = PhaseStateType.values();
        String statesStr = "";
        for (PhaseStateType state : states)
        {
            statesStr = statesStr + ", "+state.value();
        }
        
        return statesStr.substring(2);
    }
}
