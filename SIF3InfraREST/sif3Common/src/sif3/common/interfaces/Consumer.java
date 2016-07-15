/*
 * Consumer.java
 * Created: 23/09/2013
 *
 * Copyright 2013 Systemic Pty Ltd
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

import java.util.List;

import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderValues.QueryIntention;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.model.CustomParameters;
import sif3.common.model.PagingInfo;
import sif3.common.model.ZoneContextInfo;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;

/**
 * This class defines the methods a consumer must implement to fit with this framework. The interface is independent from the 
 * Data Model and underlying infrastructure components. It defines the core function SIF3 specifies for a consumer.<br/><br/>
 * 
 * Note:<br/>
 * Because this framework allows to be run under Java 6 some of the types in various methods use "Object" instead of the  template 
 * notation. This is because Java 6 doesn't allow a 'new T()' and therefore the interface avoids the template notation to not break 
 * the implementation where a constructor for an Object might be required. This may change in future versions of the framework.
 * 
 * @author Joerg Huber
 */
public interface Consumer extends DataModelLink
{
	/*------------------------------*/
	/*-- Single Object Operations --*/
	/*------------------------------*/

	/**
	 * This method creates the given object for the list of environments, zones and contexts. 
	 * 
	 * @param data The object that shall be created.
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
	public List<Response> createSingle(Object data, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;
  
    /**
     * This method updates the object given by its resourceID.
     * 
     * @param data The actual object (i.e. Student). It holds the values of the object that need to be updated. It can either hold the
     *             full object or only parts of the object that needs updating. 
     * @param resourceID The Id of the object to be updated.
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
     *         service in the default zone and context. The object was updated in the default zone and context.
     * 
	 * @throws IllegalArgumentException One of the parameters is invalid.
	 * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
     */
	public List<Response> updateSingle(Object data, String resourceID, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;
  
	/**
	 * Removed the object with the given resourceId.
	 * 
	 * @param resourceID The Id of the object to be removed.
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
	public List<Response> deleteSingle(String resourceID, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;
  
	/*----------------------------*/
	/*-- Bulk Object Operations --*/
	/*----------------------------*/

	/**
	 * This method will create many objects in one call. The 'data' parameter is a collection-style object that is defined in the data
	 * model (i.e. StudentPersonals which is a collection of StudentPersonal).
	 * 
	 * @param data The 'collection' object. Each object in that collection will be created.
	 * @param zoneCtxList If this List is null or empty then it is assumed that the operation is performed for the default Zone and Context for the 
	 *                    connected. If this list is not empty then the action is performed for all Zone and Context listed. If a given 
	 *                    environment has more than one zone and/or context and the operation shall be performed in any number of them 
	 *                    then each combination must be a separate entry in this list.
	 * @param requestType Indicating if the createMany request is synchronous (IMMEDIATE) or if it shall be shall executed asynchronously (DELAYED).
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
	public List<BulkOperationResponse<CreateOperationStatus>> createMany(Object data, List<ZoneContextInfo> zoneCtxList, RequestType requestType, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;
  
	/**
	 * This method will update many objects in one call. The 'data' parameter is a collection-style object that is defined in the data
	 * model (i.e. StudentPersonals which is a collection of StudentPersonal). Each object in the collection can either hold the
     * full object or only parts of the object that needs updating.
	 * 
	 * @param data The 'collection' object. Each object in that collection will be updated.
	 * @param zoneCtxList If this List is null or empty then it is assumed that the operation is performed for the default Zone and Context for the 
	 *                    connected. If this list is not empty then the action is performed for all Zone and Context listed. If a given 
	 *                    environment has more than one zone and/or context and the operation shall be performed in any number of them 
	 *                    then each combination must be a separate entry in this list.
	 * @param requestType Indicating if the updateMany request is synchronous (IMMEDIATE) or if it shall be shall executed asynchronously (DELAYED).
	 * @param customParameters Custom HTTP Header fields and Custom URL Query parameters that will be added to the request.
	 *                         If any of the HTTP header fields correspond to a SIF pre-defined HTTP header that is managed
	 *                         by this framework then the value in this parameter will be overridden with the internally
	 *                         managed HTTP header field. The same applies to the URL Query parameters. This parameter can
	 *                         be null.
	 *                       
	 * @return A list of responses corresponding to the List of envZoneCtx.  If the envZoneCtx was null then only one response will be returned for the
     *         service in the default zone and context. The update of data was in performed in the default zone and context.
	 *         
	 * @throws IllegalArgumentException One of the parameters is invalid.
	 * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
	 */
	public List<BulkOperationResponse<OperationStatus>> updateMany(Object data, List<ZoneContextInfo> zoneCtxList, RequestType requestType, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;
  
	/**
	 * This method removes all objects in the resourceIDs list in one hit.
	 * 
	 * @param resourceIDs A list of resourceId for the objects to be removed.
	 * @param zoneCtxList If this List is null or empty then it is assumed that the operation is performed for the default Zone and Context for the 
	 *                    connected. If this list is not empty then the action is performed for all Zone and Context listed. If a given 
	 *                    environment has more than one zone and/or context and the operation shall be performed in any number of them 
	 *                    then each combination must be a separate entry in this list.
	 * @param requestType Indicating if the deleteMany request is synchronous (IMMEDIATE) or if it shall be shall executed asynchronously (DELAYED).
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
	public List<BulkOperationResponse<OperationStatus>> deleteMany(List<String> resourceIDs, List<ZoneContextInfo> zoneCtxList, RequestType requestType, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;

	/*----------------------*/
	/*-- Query Operations --*/
	/*----------------------*/

	/**
	 * This method returns an object with the given resourceId.
	 * 
	 * @param resourceID The Id of the object to be returned.
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
     *         service in the default zone and context.
	 *         
	 * @throws IllegalArgumentException One of the parameters is invalid.
	 * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
	 */
	public List<Response> retrieveByPrimaryKey(String resourceID, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;
  	
	/**
	 * This method is used to retrieve any number of objects. This is achieved in terms of 'paging' through the list of objects. The consumer
	 * is expected to provide paging information to tell the provider which objects in the list shall be returned. The first page has
	 * the number 0.
	 * 
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
	 * 
	 * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then only one response will be returned for the
     *         service in the default zone and context.
	 *         
	 * @throws UnsupportedQueryException The query provided with this request is not supported (NOT YET IMPLEMENTED FUNCTIONALITY)
	 * @throws PersistenceException Some data could not be retrieved. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
	 * 
	 */
	public List<Response> retrieve(PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, RequestType requestType, QueryIntention queryIntention, CustomParameters customParameters) throws PersistenceException, UnsupportedQueryException, ServiceInvokationException;
  
	/*-------------------------------*/
	/*-- Other required Operations --*/
	/*-------------------------------*/
	
	/**
     * Will invoke the REST HEAD call to retrieve some information about a service. It will not return a payload as per HTTP 
     * Specification of the HEAD method. Besides a status code and an optional status message only a set of HTTP Header properties
     * will be set in the returned responses. These HTTP Header properties can be retrieved as part of the returned response 
     * object (response.getHdrProperties()).<br/>
     * Because this method almost mirrors the HTTP GET for the root object service all parameters that would make up the retrieve() 
     * method in this class are supported. The exception is the requestType and queryIntention parameter that are allowed in the 
     * retrieve() method. They do not make any sense for this method and are therefore omitted.
     * 
     * @param pagingInfo Page information to be set for the provider to determine which results to return.
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
     *         information from the service in the default zone and context.
     *         The main bit of useful information returned by this call are the HTTP Header properties that can be retrieved with the
     *         response.getHdrProperties() method. 
     *         
     * @throws UnsupportedQueryException The query provided with this request is not supported (NOT YET IMPLEMENTED FUNCTIONALITY)
     * @throws PersistenceException Some data could not be retrieved. An error log entry is performed and the message of the exceptions holds some info.
     * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
     * 
     */
    public List<Response> getServiceInfo(PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters) throws PersistenceException, UnsupportedQueryException, ServiceInvokationException;

	
	/** Call this at shut down time. */
	public void finalise();
}
