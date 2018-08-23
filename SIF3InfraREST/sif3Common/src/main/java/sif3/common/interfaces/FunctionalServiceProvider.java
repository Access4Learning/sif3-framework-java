/*
 * FunctionalServiceProvider.java
 * Created: 07/06/2018
 *
 * Copyright 2018 Systemic Pty Ltd
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

package sif3.common.interfaces;

import javax.ws.rs.core.MediaType;

import sif3.common.CommonConstants.JobState;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIFException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.job.PhaseInfo;
import sif3.common.ws.job.PhaseDataRequest;
import sif3.common.ws.job.PhaseDataResponse;

/**
 * This class defines the methods a functional service provider must implement to fit with this framework. It defines the core function 
 * SIF3 specifies for a functional service provider.<br/>
 * Note that each method has a number of parameters but they all have the following three given parameters. The zone, 
 * the context and metadata. The first two relate to the standard SIF concept of zone and context. The metadata parameter
 * however is additional info that may be provided by a consumer with each request. This can be typical HTTP header 
 * fields such as generatorId, queryIntention etc. Please refer to the SIF 3 Infrastructure Service documentation what 
 * these fields mean as well as where they might be used. It is important to note that most of the properties in the 
 * metadata could be null and therefore implementations must take care how they are used.<br/><br/>
 * 
 * Note:<br/>
 * Because this framework used to run under Java 6 some of the types in various methods use "Object" instead of the template 
 * notation. This is because Java 6 doesn't allow a 'new T()' and therefore the interface avoids the template notation to not break 
 * the implementation where a constructor for an Object might be required. This may change in future versions of the framework.
 * Further it requires some objects form the Infrastructure data model but the sif3-infra-model module is not available in this 
 * common module the type 'Object' is used in a couple of places. Please refer to the documentation of the appropriate method
 * for more details on what exact type shall be expected in these methods.
 * 
 * @author Joerg Huber
 */
public interface FunctionalServiceProvider extends DataModelLink
{
	/*---------------------------*/
	/*-- Single Job Operations --*/
	/*---------------------------*/

	/**
	 * This method creates the given job with the data provided in the given zone and context. If the job cannot be created then either
	 * an exception is raised or null is returned.
	 * 
	 * @param jobData The data of the actual job to be created. It may or may not hold the jobID and the provider may or may not accept it.
	 *             It is up to the implementation to make that decision. The final jobID is returned as part of the returned job.
	 * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
	 * @param context The Context for which the job shall be created. Can be Null (default Zone)
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * @param customResponseParams Values to be returned as part of the response. These are generally just HTTP Header fields. If a developer
     *                             sets the HTTP Header of a well defined SIF3 HTTP Header (i.e. providerId, timestamp) then the framework
     *                             may override these with its own value to ensure the correct use and workings of the framework. It is the
     *                             developer who will populate the object. When it is passed to this method it not null but empty.
     * 
	 * @return The job that is created. It may hold additional data than the one provided. This will be of type JobType as defined
     *         infrastructure data model.
	 * 
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
	 */
	public Object createJob(Object jobData, SIFZone zone, SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams) 
	        throws IllegalArgumentException, PersistenceException, SIFException;

	/**
     * Removed the job with the given jobID in the given zone for the given context.
     * 
     * @param jobID The Id of the job to be removed.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the job shall be removed. Can be Null (default Zone)
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * @param customResponseParams Values to be returned as part of the response. These are generally just HTTP Header fields. If a developer
     *                             sets the HTTP Header of a well defined SIF3 HTTP Header (i.e. providerId, timestamp) then the framework
     *                             may override these with its own value to ensure the correct use and workings of the framework. It is the
     *                             developer who will populate the job. When it is passed to this method it not null but empty.
	 * 
	 * @return TRUE: Job is removed. FALSE: Job does not exist.
	 * 
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
     *                      
	 */
	public boolean deleteJob(String jobID, SIFZone zone, SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams) 
	        throws IllegalArgumentException, PersistenceException, SIFException;

	/*----------------------------*/
	/*-- Bulk Object Operations --*/
	/*----------------------------*/
	
	/*-- None Required. All managed under the hood of the framework. --*/

	/*----------------------------------------------*/
    /*-- Phase Operation invoked by the consumer. --*/
    /*----------------------------------------------*/

    /**
     * This method is used to retrieve data from a given phase. This can be any data. It is up to the implementation of the
     * functional service to know what that data is for a given phase. This framework is agnostic to that data. The returned
     * value is a String that must represent the "marshalled" version of the data in the format indicated by the "returnMimeType".
     * Because the data that can be returned as part of a phase might be a collection, the paging parameter is provided. If the 
     * data to be returned is considered too large by the provider (implementation dependent) then a DataTooLargeException 
     * must be raised. This exception is then translated into an appropriate HTTP Status within the framework to meet the 
     * SIF Specification.
     * 
     * @param phaseInfo The JobID and Phase Name for which the phase operation applies. Neither property is empty or null.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the data shall be returned. Can be Null (default Zone)
     * @param pagingInfo Page information to determine which results to return. Null = Return all (NOT RECOMMENDED!).
     * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * @param customResponseParams Values to be returned as part of the response. These are generally just HTTP Header fields. If a developer
     *                             sets the HTTP Header of a well defined SIF3 HTTP Header (i.e. providerId, timestamp) then the framework
     *                             may override these with its own value to ensure the correct use and workings of the framework. It is the
     *                             developer who will populate the object. When it is passed to this method it not null but empty.
     * @param returnMimeType The mime type the response data is in. It is expected that the consumer provides that and the provider
     *                       should attempt to marshal the data to the given mime type and return the resulting string as
     *                       part of this call. If the provider cannot marshal the data to the requested mime type then an
     *                       UnsupportedMediaTypeExcpetion must be raised.
     *                       
     * @return The response data (result set) with its mime type. It can be null indicating no or no further data available. 
     *         The returned string should be the marshalled value of the result set and it should be in the mime type as 
     *         indicated in the returnMimeType parameter. The status of the return value will be set by the framework and will be
     *         either HTTP 200 (OK) when data is returned or HTTP 204 (No Content) if there is no further data available.
     *                       
     * @throws UnsupportedMediaTypeExcpetion The provider cannot support the requested return media type.
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
     * @throws DataTooLargeException If the data that shall be returned is too large due to the values in the paging info.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
     */
    public PhaseDataResponse retrieveDataFromPhase(PhaseInfo phaseInfo, 
                                                   SIFZone zone, 
                                                   SIFContext context, 
                                                   PagingInfo pagingInfo, 
                                                   RequestMetadata metadata, 
                                                   ResponseParameters customResponseParams, 
                                                   MediaType returnMimeType)
            throws PersistenceException, UnsupportedMediaTypeExcpetion, DataTooLargeException, SIFException;
    
    /**
     * This method should perform a "create" operation for the data given in the "PhaseDataRequest" parameter. This can be any data. 
     * It is up to the implementation of the functional service to know what that data is for a given phase. This framework is agnostic 
     * to that data. The data is given in its raw form as received by the framework. The mimeType property of the PhaseDataRequest
     * parameter shall be used by the provider to unmarshal the data into a useful data structure. The response to this operation 
     * may or may not return any data. If no data is returned (PhaseDataResponse == null) then the consumer will retrieve a HTTP 
     * Status of the appropriate operation, in this case a HTTP Status of 201 (Created). If any other status shall be returned, 
     * even if there is no data available it is up to the implementer to set the appropriate Status in the PhaseDataResponse.status 
     * property.<br/>
     * In case of any failures one of the listed exception must be raised which will translate into a standard SIF Error Message
     * to be returned to the consumer. If no exception is raised the framework assumes that all is ok and the data of the
     * PhaseDataResponse is returned. If data is available it is expected to be marshalled into the Mime Type requested in the 
     * returnMimeType parameter. The mime type of the response data shall also be set in the PhaseDataResponse. If not set it will
     * attempt to use the mime type of returnMimeType. If that is not available it will assume the same mime type as in the request.<br/><br/>
     * The PhaseDataRequest.data can be null if no data is passed to this method. This is a valid scenario according to the
     * SIF Specification.
     *  
     * @param phaseInfo The JobID and Phase Name for which the phase operation applies. Neither property is empty or null.
     * @param phaseData The data and its mime type that is given to the operation of this phase. That the data property of this
     *                  parameter can be null in cases where no data is provided to this phase. This is entirely valid according
     *                  to the SIF Specification. If data is provided (as a String) then the mime type is set as well, by the consumer,
     *                  to indicate the format of the data. The provider can us this mime type to unmarshal the data into the 
     *                  appropriate internal structure.
     * @param useAdvisory If new IDs for the created data shall be allocated or used as given. In some cases that may not be applicable
     *                    but if it is then this parameter indicates the expected behaviour.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the objects shall be created. Can be Null (default Zone)
     * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * @param customResponseParams Values to be returned as part of the response. These are generally just HTTP Header fields. If a developer
     *                             sets the HTTP Header of a well defined SIF3 HTTP Header (i.e. providerId, timestamp) then the framework
     *                             may override these with its own value to ensure the correct use and workings of the framework. It is the
     *                             developer who will populate the object. When it is passed to this method it not null but empty.
     * @param returnMimeType The mime type the PhaseDataResponse.data is in. It is expected that the consumer provides that and 
     *                       the provider if it expects data to be returned as part of this operation. The provider should attempt 
     *                       to marshal the PhaseDataResponse.data to the given mime type and return the resulting data as
     *                       part of this call. If the provider cannot marshal the data to the requested mime type then an
     *                       UnsupportedMediaTypeExcpetion must be raised.
     * 
     * @return The response data of this operation with its mime type. It can be null if no data shall be returned. In this case
     *         the HTTP Status of 201 (Created) will be returned to the consumer. The returned PhaseDataResponse.data string if not
     *         null, should be the marshalled into the mime type as indicated in the returnMimeType parameter. See also description of
     *         this method about the handling of the PhaseDataResponse.mimeType property.
     *         
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
     * @throws UnsupportedMediaTypeExcpetion The provider cannot support the requested media type.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
     */
    public PhaseDataResponse createDataInPhase(PhaseInfo phaseInfo,
                                               PhaseDataRequest phaseData,
                                               boolean useAdvisory, 
                                               SIFZone zone, 
                                               SIFContext context, 
                                               RequestMetadata metadata, 
                                               ResponseParameters customResponseParams, 
                                               MediaType returnMimeType) 
            throws PersistenceException, UnsupportedMediaTypeExcpetion, SIFException;

    /**
     * This method should perform an "update" operation for the data given in the "PhaseDataRequest" parameter. This can be any data. 
     * It is up to the implementation of the functional service to know what that data is for a given phase. This framework is agnostic 
     * to that data. The data is given in its raw form as received by the framework. The mimeType property of the PhaseDataRequest
     * parameter shall be used by the provider to unmarshal the data into a useful data structure. The response to this operation 
     * may or may not return any data. If no data is returned (PhaseDataResponse == null) then the consumer will retrieve a HTTP 
     * Status of the appropriate operation, in this case a HTTP Status of 200 (Ok). If any other status shall be returned, 
     * even if there is no data available it is up to the implementer to set the appropriate Status in the PhaseDataResponse.status 
     * property.<br/>
     * In case of any failures one of the listed exception must be raised which will translate into a standard SIF Error Message
     * to be returned to the consumer. If no exception is raised the framework assumes that all is ok and the data of the
     * PhaseDataResponse is returned. If data is available it is expected to be marshalled into the Mime Type requested in the 
     * returnMimeType parameter. The mime type of the response data shall also be set in the PhaseDataResponse. If not set it will
     * attempt to use the mime type of returnMimeType. If that is not available it will assume the same mime type as in the request.<br/><br/>
     * The PhaseDataRequest.data can be null if no data is passed to this method. This is a valid scenario according to the
     * SIF Specification.
     * 
     * @param phaseInfo The JobID and Phase Name for which the phase operation applies. Neither property is empty or null.
     * @param phaseData The data and its mime type that is given to the operation of this phase. That the data property of this
     *                  parameter can be null in cases where no data is provided to this phase. This is entirely valid according
     *                  to the SIF Specification. If data is provided (as a String) then the mime type is set as well, by the consumer,
     *                  to indicate the format of the data. The provider can us this mime type to unmarshal the data into the 
     *                  appropriate internal structure.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the data shall be updated. Can be Null (default Zone)
     * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * @param customResponseParams Values to be returned as part of the response. These are generally just HTTP Header fields. If a developer
     *                             sets the HTTP Header of a well defined SIF3 HTTP Header (i.e. providerId, timestamp) then the framework
     *                             may override these with its own value to ensure the correct use and workings of the framework. It is the
     *                             developer who will populate the object. When it is passed to this method it not null but empty.
     * @param returnMimeType The mime type the PhaseDataResponse.data is in. It is expected that the consumer provides that and 
     *                       the provider if it expects data to be returned as part of this operation. The provider should attempt 
     *                       to marshal the PhaseDataResponse.data to the given mime type and return the resulting data as
     *                       part of this call. If the provider cannot marshal the data to the requested mime type then an
     *                       UnsupportedMediaTypeExcpetion must be raised.
     * 
     * @return The response data of this operation with its mime type. It can be null if no data shall be returned. In this case
     *         the HTTP Status of 200 (Ok) will be returned to the consumer. The returned PhaseDataResponse.data string if not
     *         null, should be the marshalled into the mime type as indicated in the returnMimeType parameter. See also description of
     *         this method about the handling of the PhaseDataResponse.mimeType property.
     *         
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
     * @throws UnsupportedMediaTypeExcpetion The provider cannot support the requested media type.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
     */
    public PhaseDataResponse updateDataInPhase(PhaseInfo phaseInfo, 
                                               PhaseDataRequest phaseData,
                                               SIFZone zone, 
                                               SIFContext context, 
                                               RequestMetadata metadata, 
                                               ResponseParameters customResponseParams, 
                                               MediaType returnMimeType) 
            throws PersistenceException, UnsupportedMediaTypeExcpetion, SIFException;

    /**
     * This method should perform a "delete" operation for the data given in the "PhaseDataRequest" parameter. This can be any data. 
     * It is up to the implementation of the functional service to know what that data is for a given phase. This framework is agnostic 
     * to that data. The data is given in its raw form as received by the framework. The mimeType property of the PhaseDataRequest
     * parameter shall be used by the provider to unmarshal the data into a useful data structure. The response to this operation 
     * may or may not return any data. If no data is returned (PhaseDataResponse == null) then the consumer will retrieve a HTTP 
     * Status of the appropriate operation, in this case a HTTP Status of 204 (No Content). If any other status shall be returned, 
     * even if there is no data available it is up to the implementer to set the appropriate Status in the PhaseDataResponse.status 
     * property.<br/>
     * In case of any failures one of the listed exception must be raised which will translate into a standard SIF Error Message
     * to be returned to the consumer. If no exception is raised the framework assumes that all is ok and the data of the
     * PhaseDataResponse is returned. If data is available it is expected to be marshalled into the Mime Type requested in the 
     * returnMimeType parameter. The mime type of the response data shall also be set in the PhaseDataResponse. If not set it will
     * attempt to use the mime type of returnMimeType. If that is not available it will assume the same mime type as in the request.<br/><br/>
     * The PhaseDataRequest.data can be null if no data is passed to this method. This is a valid scenario according to the
     * SIF Specification.
     * 
     * @param phaseInfo The JobID and Phase Name for which the phase operation applies. Neither property is empty or null.
     * @param phaseData The data and its mime type that is given to the operation of this phase. That the data property of this
     *                  parameter can be null in cases where no data is provided to this phase. This is entirely valid according
     *                  to the SIF Specification. If data is provided (as a String) then the mime type is set as well, by the consumer,
     *                  to indicate the format of the data. The provider can us this mime type to unmarshal the data into the 
     *                  appropriate internal structure.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the data shall be removed. Can be Null (default Zone)
     * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * @param customResponseParams Values to be returned as part of the response. These are generally just HTTP Header fields. If a developer
     *                             sets the HTTP Header of a well defined SIF3 HTTP Header (i.e. providerId, timestamp) then the framework
     *                             may override these with its own value to ensure the correct use and workings of the framework. It is the
     *                             developer who will populate the object. When it is passed to this method it not null but empty.
     * @param returnMimeType The mime type the PhaseDataResponse.data is in. It is expected that the consumer provides that and 
     *                       the provider if it expects data to be returned as part of this operation. The provider should attempt 
     *                       to marshal the PhaseDataResponse.data to the given mime type and return the resulting data as
     *                       part of this call. If the provider cannot marshal the data to the requested mime type then an
     *                       UnsupportedMediaTypeExcpetion must be raised.
     * 
     * @return The response data of this operation with its mime type. It can be null if no data shall be returned. In this case
     *         the HTTP Status of 204 (No Content) will be returned to the consumer. The returned PhaseDataResponse.data string if not
     *         null, should be the marshalled into the mime type as indicated in the returnMimeType parameter. See also description of
     *         this method about the handling of the PhaseDataResponse.mimeType property.
     *         
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
     * @throws UnsupportedMediaTypeExcpetion The provider cannot support the requested media type.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
     */
    public PhaseDataResponse deleteDataInPhase(PhaseInfo phaseInfo, 
                                               PhaseDataRequest phaseData,
                                               SIFZone zone, 
                                               SIFContext context, 
                                               RequestMetadata metadata, 
                                               ResponseParameters customResponseParams, 
                                               MediaType returnMimeType) 
            throws PersistenceException, UnsupportedMediaTypeExcpetion, SIFException;
	
	/*----------------------------------------------------------------------------------*/
    /*-- Phase Status Operation invoked by consumer and may need actions by provider. --*/
    /*----------------------------------------------------------------------------------*/
	
	/**
	 * This method is called on the provider when a consumer calls a state change for a phase. It is assumed that underlying
	 * housekeeping functionality related to the framework is already done. This call is simply to inform the provider that the
	 * change has happen. It allows the provider to perform some additional work that may relate to such a phase state change.
	 * Such a phase state change may mean that the provider has to do some work or move to the next phase. It is up to the
	 * provider of this functional service to determine what need to be done. Calls to methods listed in the
	 * "Operations required by Provider to manage job and phase states" section in this class may be invoked as part of this
	 * method implementation.
	 * 
     * @param phaseInfo The job ID and phase name for which a phase change was requested.
     * @param newState The new state of the phase.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the request applies. Can be Null (default Zone)
     * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * @param customResponseParams Values to be returned as part of the response. These are generally just HTTP Header fields. If a developer
     *                             sets the HTTP Header of a well defined SIF3 HTTP Header (i.e. providerId, timestamp) then the framework
     *                             may override these with its own value to ensure the correct use and workings of the framework. It is the
     *                             developer who will populate the object. When it is passed to this method it not null but empty.
	 * 
	 * @throws PersistenceException
	 * @throws SIFException
	 */
    public void phaseStateUpdatedByConsumer(PhaseInfo phaseInfo,
                                            PhaseState newState, 
                                            SIFZone zone, 
                                            SIFContext context, 
                                            RequestMetadata metadata, 
                                            ResponseParameters customResponseParams) 
            throws PersistenceException, SIFException;
	
	/*-----------------------------------------*/
    /*-- Special Operations (i.e. HTTP HEAD) --*/
    /*-----------------------------------------*/
	
	/**
	 * This method retrieves information about the root level service (i.e. .../StudentPersonals). The information that is returned is
	 * put into HTTP Headers in the response. This method is representing the HTTP HEAD functionality. It pretends to be the same
	 * as the retrieve() method without any payload to be returned, though. It is up to the implementation if an actual paged query shall
	 * be performed as part of this call. It can but doesn't have to. All standard checks as with the corresponding retrieve() method
	 * are being performed (i.e. check ACLs, sessionToken etc.).
	 * 
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the information shall be returned. Can be Null (default Zone)
     * @param pagingInfo Page information to determine which results to return. Null = Return all (NOT RECOMMENDED!).
     * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * 
     * @return Header Properties that shall be returned as HTTP Headers to the caller. Note this can be null or any number of
     *         custom HTTP headers. Generally one would expect that to be null.
     * 
     * @throws UnsupportedQueryException The query provided with this request is not supported (NOT YET IMPLEMENTED FUNCTIONALITY)
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
     * @throws DataTooLargeException If the data that shall be returned is too large due to the values in the paging info.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
	 */
	public HeaderProperties getServiceInfo(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) 
	        throws PersistenceException, UnsupportedQueryException, DataTooLargeException, SIFException;
	
	/*---------------------------------------------------------------------*/
	/*-- Operations required by Provider to manage job and phase states. --*/
	/*---------------------------------------------------------------------*/	
	
	/**
     * This method updates the overall Job state for the given job. It is intended to be used by the provider only. If events 
     * are turned on it will create the appropriate event.
     * 
     * @param jobID The ID of the Job to be updated. If Job does not exist then no action is taken.
     * @param newState The new state of the job. If null then no action is taken.
     * 
     * @throws PersistenceException Job can not be accessed or updated due to a DB access issue.
	 */
	public void updateJobState(String jobID, JobState newState) 
	        throws PersistenceException;

	
    /**
     * This method updates the state for the given phase of the given job. If the job or phase doesn't exist then no action 
     * is taken. If events are turned on it will create the appropriate event.
     * 
     * @param jobID The ID of the Job to be updated. If Job does not exist then no action is taken.
     * @param phaseName The phase for which the state shall be updated. If phase doesn't exist then no action is taken.
     * @param newState The new state of the job. If null then no action is taken.
     * 
     * @throws PersistenceException Job can not be accessed or updated due to a DB access issue.
     */
    public void updatePhaseState(String jobID, String phaseName, PhaseState newState) 
            throws PersistenceException;

    /**
     * This method updates the overall Job status as well as the status of the given phase. This is a convenience method, so that
     * a provider can update a phase state which in turn may also need an update to the overall job state. Using this method
     * is more efficient then using two separate methods to update job and a phase state separately. It also reduces the number
     * of events that will be created in case events are turned on. This method will only create on final event.
     * 
     * @param jobID The ID of the Job to be updated. If Job does not exist then no action is taken.
     * @param newJobState The new state of the job. If null then the state will not be updated.
     * @param phaseName The phase for which the state shall be updated. If phase doesn't exist then no action is taken.
     * @param newPhaseState The new state of the given phase. If null then the state will not be updated.
     * 
     * @throws PersistenceException Job can not be accessed or updated due to a DB access issue.
     */
    public void updateJobStateAndPhaseState(String jobID, JobState newJobState, String phaseName, PhaseState newPhaseState) 
            throws PersistenceException;

    
    /*-------------------------------*/
    /*-- Other required Operations --*/
    /*-------------------------------*/ 
	/**
	 * This method is called when a provider shuts down. Can be used to clean-up internally held resources etc. 
     */
	public void finalise();
	
    /**
     * This method checks if the given state represents a Job End State as listed in the provider properties file in
     * the property called 'job.endStates'. If it is then TRUE is returned otherwise FALSE is returned.
     * 
     * @param state The state to test against the list of possible end states.
     * 
     * @return TRUE: It is an end state. FALSE it is not an end state.
     */
    public boolean isJobEndState(JobState state);
}
