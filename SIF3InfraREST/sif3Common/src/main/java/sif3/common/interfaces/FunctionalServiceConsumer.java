/*
 * FunctionalServiceConsumer.java
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

package sif3.common.interfaces;

import java.util.List;

import javax.ws.rs.core.MediaType;

import sif3.common.CommonConstants.PhaseState;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderValues.QueryIntention;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.model.CustomParameters;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ZoneContextInfo;
import sif3.common.model.job.JobCreateRequestParameter;
import sif3.common.model.job.PhaseInfo;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.common.ws.job.PhaseDataRequest;

/**
 * This interface defines the core CRUD operations required by a functional service provider. Functional Services deal with Job Objects
 * as well as phase objects. While operations and data model are defined in the SIF Infrastructure specification and can be specific in
 * this interface in regards to types, phase operation objects are entirely implementation specific. Hence many phase operations only
 * deal with "raw" data, that being strings and the appropriate mime type. It is up to the final implementation class to marshal or
 * unmarshal the phase data objects.
 * 
 * @author Joerg Huber
 */
public interface FunctionalServiceConsumer extends MinimalConsumer
{
    /*---------------------------*/
    /*-- Single Job Operations --*/
    /*---------------------------*/
    
    /**
     * This method creates the job for the list of zones and contexts. 
     * 
     * @param createJobRequest Data (parameters) for the job to be craeted.
     * @param zoneCtxList If this List is null or empty then it is assumed that the operation is performed for the default Zone and Context for the 
     *                    connected. If this list is not empty then the action is performed for all Zone and Context listed. If a given 
     *                    environment has more than one zone and/or context and the operation shall be performed in any number of them 
     *                    then each combination must be a separate entry in this list.
     * @param customParameters Custom HTTP Header fields and Custom URL Query parameters that will be added to the request.
     *                         If any of the HTTP header fields correspond to a SIF pre-defined HTTP header that is managed
     *                         by this framework then the value in this parameter will be overridden with the internally
     *                         managed HTTP header field. The same applies to the URL Query parameters. This parameter can
     *                         be null.
     * 
     * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then only one response will be returned for the
     *         service in the default zone and context. The object was created in the default zone and context.
     * 
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
     * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
     */
    public List<Response> createJob(JobCreateRequestParameter createJobRequest, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;

    /**
     * Removed the job with the given jobId.
     * 
     * @param jobID The Id of the job to be removed.
     * @param zoneCtxList If this List is null or empty then it is assumed that the operation is performed for the default Zone and Context for the 
     *                    connected. If this list is not empty then the action is performed for all Zone and Context listed. If a given 
     *                    environment has more than one zone and/or context and the operation shall be performed in any number of them 
     *                    then each combination must be a separate entry in this list.
     * @param customParameters Custom HTTP Header fields and Custom URL Query parameters that will be added to the request.
     *                         If any of the HTTP header fields correspond to a SIF pre-defined HTTP header that is managed
     *                         by this framework then the value in this parameter will be overridden with the internally
     *                         managed HTTP header field. The same applies to the URL Query parameters. This parameter can
     *                         be null.
     *                       
     * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then only one response will be returned for the
     *         service in the default zone and context. The object was deleted in the default zone and context.
     * 
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
     * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
     */
    public List<Response> deleteJob(String jobID, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;

    /*-------------------------*/
    /*-- Bulk Job Operations --*/
    /*-------------------------*/
    
    /**
     * This method will create many jobs in one call. The 'createMultipleJobsRequest' is a list of job parameters. For each entry in that list
     * a job create request will be issued. It is assumed that all jobs are of the same type. This method must create job refIDs and expects 
     * the provider to accept them. MustUseAdvisory must be set to true.
     * 
     * @param createMultipleJobsRequest The list of job parameter data.
     * @param zoneCtxList If this List is null or empty then it is assumed that the operation is performed for the default Zone and Context for the 
     *                    connected. If this list is not empty then the action is performed for all Zone and Context listed. If a given 
     *                    environment has more than one zone and/or context and the operation shall be performed in any number of them 
     *                    then each combination must be a separate entry in this list.
     * @param requestType Indicating if the createJobs request is synchronous (IMMEDIATE) or if it shall be shall executed asynchronously (DELAYED).
     * @param customParameters Custom HTTP Header fields and Custom URL Query parameters that will be added to the request.
     *                         If any of the HTTP header fields correspond to a SIF pre-defined HTTP header that is managed
     *                         by this framework then the value in this parameter will be overridden with the internally
     *                         managed HTTP header field. The same applies to the URL Query parameters. This parameter can
     *                         be null.
     * 
     * @return A list of responses corresponding to the List of envZoneCtx.  If the envZoneCtx was null then only one response will be returned for the
     *         service in the default zone and context. The creation of data was in performed in the default zone and context.
     *         
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
     * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
     */
    public List<BulkOperationResponse<CreateOperationStatus>> createJobs(List<JobCreateRequestParameter> createMultipleJobsRequest, List<ZoneContextInfo> zoneCtxList, RequestType requestType, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;

    /**
     * This method removes all jobs in the jobIDs list in one hit.
     * 
     * @param jobIDs A list of jobID for the jobs to be removed.
     * @param zoneCtxList If this List is null or empty then it is assumed that the operation is performed for the default Zone and Context for the 
     *                    connected. If this list is not empty then the action is performed for all Zone and Context listed. If a given 
     *                    environment has more than one zone and/or context and the operation shall be performed in any number of them 
     *                    then each combination must be a separate entry in this list.
     * @param requestType Indicating if the deleteJobs request is synchronous (IMMEDIATE) or if it shall be shall executed asynchronously (DELAYED).
     * @param customParameters Custom HTTP Header fields and Custom URL Query parameters that will be added to the request.
     *                         If any of the HTTP header fields correspond to a SIF pre-defined HTTP header that is managed
     *                         by this framework then the value in this parameter will be overridden with the internally
     *                         managed HTTP header field. The same applies to the URL Query parameters. This parameter can
     *                         be null.
     * 
     * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then only one response will be returned for the
     *         service in the default zone and context. The deletion of data was in performed in the default zone and context.
     *         
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
     * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
     */
    public List<BulkOperationResponse<OperationStatus>> deleteJobs(List<String> jobIDs, List<ZoneContextInfo> zoneCtxList, RequestType requestType, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;

    /*----------------------*/
    /*-- Phase Operations --*/
    /*----------------------*/
    /**
     * This method is used to retrieve data from a given phase. This can be any data. It is up to the implementation of the
     * functional service to know what that data is for a given phase. This framework is agnostic to that data.<br/><br/>
     * 
     * In case of an immediate request the response.dataObject property will hold the raw payload as a string and the
     * response.dataObjectType will be String.class. The string value must represent the "marshalled" version of the 
     * data in the format indicated by the "returnMimeType". <br/><br/>
     * 
     * In case of a delayed request the these two properties are null and the response.delayedReceipt property will be 
     * populated accordingly.<br/><br/>
     * 
     * Because the data that can be returned as part of a phase might be a collection, the paging parameter can be provided. 
     * If the data to be returned is considered too large by the provider (implementation dependent) then an appropriate error is 
     * returned (HTTP Status 413 - Response too large).
     * 
     * @param phaseInfo Hold the jobID and phase name of the job from where the data shall be retrieved. If the parameter or
     *                  any of its properties is null/empty then an IllegalArgumentException will be thrown.
     * @param returnMimeType The mime type the response data is in. It is expected that the consumer provides that and the provider
     *                       should attempt to marshal the data to the given mime type and return the resulting string as
     *                       part of this call. If the provider cannot marshal the data to the requested mime type then an
     *                       appropriate error is returned to this consumer (HTTP Status 400 - Bad Request).
     * @param pagingInfo Page information to determine which results to return. Null = Return all (NOT RECOMMENDED! Might be rejected
     *                   by provider.).
     * @param queryIntention Indicating what the intention of the query and follow-up queries is. Can be set to null which
     *                       will default to 'ONE-OFF'
     * @param zone The Zone for which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the the request is being issued. Can be Null (default Zone)
     * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
     * @param customParameters Custom HTTP Header fields and Custom URL Query parameters that will be added to the request.
     *                         If any of the HTTP header fields correspond to a SIF pre-defined HTTP header that is managed
     *                         by this framework then the value in this parameter will be overridden with the internally
     *                         managed HTTP header field. The same applies to the URL Query parameters. This parameter can
     *                         be null.
     *                       
     * @return Response Object holding appropriate values and results of the call. Because the framework is agnostic to the
     *         data that is returned for a phase the Response.dataObjectType will be set to "String" and the Response.dataObject
     *         will hold the string representation of the returned payload. It is up to the caller of this method to potentially
     *         marshal that payload into an appropriate object.
     *         
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     * @throws IllegalArgumentException One of the parameters is invalid. See description of parameters.
     */
    public Response retrieveDataFromPhase(PhaseInfo phaseInfo,
                                          MediaType returnMimeType, 
                                          PagingInfo pagingInfo,
                                          QueryIntention queryIntention,
                                          SIFZone zone, 
                                          SIFContext context, 
                                          RequestType requestType, 
                                          CustomParameters customParameters)
            throws IllegalArgumentException, ServiceInvokationException;

    /**
     * This method should perform a "create" operation for the data given in the "phaseData.data" (string). This can be any data. It 
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
     * @param phaseInfo Hold the jobID and phase name of the job where the data shall be created. If the parameter or
     *                  any of its properties is null/empty then an IllegalArgumentException will be thrown.
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
     * @param zone The Zone for which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the the request is being issued. Can be Null (default Zone)
     * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
     * @param customParameters Custom HTTP Header fields and Custom URL Query parameters that will be added to the request.
     *                         If any of the HTTP header fields correspond to a SIF pre-defined HTTP header that is managed
     *                         by this framework then the value in this parameter will be overridden with the internally
     *                         managed HTTP header field. The same applies to the URL Query parameters. This parameter can
     *                         be null.
     * 
     * @return Response Object holding appropriate values of the call. The response may contain data (response.dataObject) that will be
     *         with a response.dataObjectType=String.class. The consumer is expected to use the response.mediaType to unmarshal the data
     *         into its internal structure. If the status is set to 204 then no content is returned which is also a valid scenario.
     *         
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     * @throws IllegalArgumentException One of the parameters is invalid. See description of parameters.
     */
    public Response createDataInPhase(PhaseInfo phaseInfo, 
                                      PhaseDataRequest phaseDataRequest,
                                      MediaType returnMimeType,
                                      boolean useAdvisory, 
                                      SIFZone zone, 
                                      SIFContext context, 
                                      RequestType requestType,
                                      CustomParameters customParameters) 
            throws IllegalArgumentException, ServiceInvokationException;

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
     * In case of a delayed request the the response.dataObject and response.dataObjectType are null and the response.delayedReceipt 
     * property will be populated accordingly.<br/><br/>
     * 
     * In case of a failure an appropriate error message is returned in the Response Object. If there is no error returned it is
     * assumed that this method completed successfully and the appropriate HTTP status is set. 
     * 
     * @param phaseInfo Hold the jobID and phase name of the job where the data shall be updated. If the parameter or
     *                  any of its properties is null/empty then an IllegalArgumentException will be thrown.
     * @param phaseData The data and its mime type that is given to the operation of this phase. The data property of this
     *                  parameter can be null in cases where no data is provided to this phase. This is entirely valid according
     *                  to the SIF Specification. If data is provided (as a String) then the mime type is set as well, by the consumer,
     *                  to indicate the format of the data. The provider can us this mime type to unmarshal the data into the 
     *                  appropriate internal structure.
     * @param returnMimeType The mime type the response data is in. It is expected that the consumer provides that and the provider
     *                       should attempt to marshal the data to the given mime type and return the resulting string as
     *                       part of this call. If the provider cannot marshal the data to the requested mime type then an
     *                       appropriate error is returned to this consumer (HTTP Status 400 - Bad Request).
     * @param zone The Zone for which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the the request is being issued. Can be Null (default Zone)
     * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
     * @param customParameters Custom HTTP Header fields and Custom URL Query parameters that will be added to the request.
     *                         If any of the HTTP header fields correspond to a SIF pre-defined HTTP header that is managed
     *                         by this framework then the value in this parameter will be overridden with the internally
     *                         managed HTTP header field. The same applies to the URL Query parameters. This parameter can
     *                         be null.
     * 
     * @return Response Object holding appropriate values of the call. The response may contain data (response.dataObject) that will be
     *         with a response.dataObjectType=String.class. The consumer is expected to use the response.mediaType to unmarshal the data
     *         into its internal structure.
     *         
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     * @throws IllegalArgumentException One of the parameters is invalid. See description of parameters.
     */
    public Response updateDataInPhase(PhaseInfo phaseInfo, 
                                      PhaseDataRequest phaseDataRequest,
                                      MediaType returnMimeType,
                                      SIFZone zone, 
                                      SIFContext context, 
                                      RequestType requestType,
                                      CustomParameters customParameters) 
            throws IllegalArgumentException, ServiceInvokationException;
            
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
     *                  any of its properties is null/empty then an IllegalArgumentException will be thrown.
     * @param phaseData The data and its mime type that is given to the operation of this phase. The data property of this
     *                  parameter can be null in cases where no data is provided to this phase. This is entirely valid according
     *                  to the SIF Specification. If data is provided (as a String) then the mime type is set as well, by the consumer,
     *                  to indicate the format of the data. The provider can us this mime type to unmarshal the data into the 
     *                  appropriate internal structure.
     * @param returnMimeType The mime type the response data is in. It is expected that the consumer provides that and the provider
     *                       should attempt to marshal the data to the given mime type and return the resulting string as
     *                       part of this call. If the provider cannot marshal the data to the requested mime type then an
     *                       appropriate error is returned to this consumer (HTTP Status 400 - Bad Request).
     * @param zone The Zone for which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the the request is being issued. Can be Null (default Zone)
     * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
     * @param customParameters Custom HTTP Header fields and Custom URL Query parameters that will be added to the request.
     *                         If any of the HTTP header fields correspond to a SIF pre-defined HTTP header that is managed
     *                         by this framework then the value in this parameter will be overridden with the internally
     *                         managed HTTP header field. The same applies to the URL Query parameters. This parameter can
     *                         be null.
     * 
     * @return Response Object holding appropriate values of the call. The response may contain data (response.dataObject) that will be
     *         with a response.dataObjectType=String.class. The consumer is expected to use the response.mediaType to unmarshal the data
     *         into its internal structure.
     *         
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     * @throws IllegalArgumentException One of the parameters is invalid. See description of parameters.
     */
    public Response deleteDataInPhase(PhaseInfo phaseInfo, 
                                      PhaseDataRequest phaseDataRequest,
                                      MediaType returnMimeType,
                                      SIFZone zone, 
                                      SIFContext context, 
                                      RequestType requestType,
                                      CustomParameters customParameters) 
            throws IllegalArgumentException, ServiceInvokationException;

            /*----------------------------*/
    /*-- Phase State Operations --*/
    /*----------------------------*/
    /**
     * This method attempts to update the state of a given phase for a job. <br/>
     * An error message will be part of the returned Response for the following cases:<br/>
     * - Job doesn't exist.<br/>
     * - If the phase or state is invalid.<br/>
     * - Consumer doesn't have appropriate permissions for this operation.<br/>
     * - Potentially the job is no longer 'update-able' because it has reached an end state (eg. COMPLETED or FAILED).<br/>
     * 
     * @param phaseInfo Hold the jobID and phase name of the job where the state shall be updated. If the parameter or
     *                  any of its properties is null/empty then an IllegalArgumentException will be thrown.
     * @param newState The value of the new state for the given phase. If null then an IllegalArgumentException will be thrown.
     * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
     * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
     * 
     * @return Response Object holding appropriate values and results of the call. In this case it will be a StateType as defined
     *                  in the infrastructure data model. 
     * 
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     * @throws IllegalArgumentException One of the parameters is invalid. See description of parameters.
     */
    public Response updatePhaseState(PhaseInfo phaseInfo, PhaseState newState, SIFZone zone, SIFContext context, CustomParameters customParameters) throws IllegalArgumentException, ServiceInvokationException;

    /**
     * This method attempts to retrieve the state(s) of the given phase. The returned object in the response will be of
     * type StateCollectionType as defined in the infrastructure data model.<br/>
     * An error message will be part of the returned Response for the following cases:<br/>
     * - Job doesn't exist.<br/>
     * - If the phase is invalid.<br/>
     * - Consumer doesn't have appropriate permissions for this operation.<br/>
     * 
     * @param phaseInfo Hold the jobID and phase name of the job where the states shall be retrieved. If the parameter or
     *                  any of its properties is null/empty then an IllegalArgumentException will be thrown.
     * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
     * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
     * 
     * @return Response Object holding appropriate values and results of the call. In this case it will be a StateCollectionType as defined
     *                  in the infrastructure data model.
     * 
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     * @throws IllegalArgumentException One of the parameters is invalid. See description of parameters.
     */
    public Response getPhaseStates(PhaseInfo phaseInfo, SIFZone zone, SIFContext context, CustomParameters customParameters) throws IllegalArgumentException, ServiceInvokationException;
    
}
