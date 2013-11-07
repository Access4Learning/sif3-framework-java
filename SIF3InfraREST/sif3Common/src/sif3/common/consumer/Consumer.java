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

package sif3.common.consumer;

import java.util.List;

import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.EnvironmentZoneContextInfo;
import sif3.common.model.PagingInfo;
import sif3.common.ws.BulkOperationResponse;
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
public interface Consumer
{
	/**
	 * This method creates the given object for the list of environments, zones and contexts. 
	 * 
	 * @param data The object that shall be created.
	 * @param envZoneCtxList If this List is null or empty then it is assumed that the operation is performed for ALL environments this consumer is connected
	 *                       to. It is performed for the default Zone and Context of each environment. If this list is not empty then the action is only 
	 *                       performed for the environments in the list and the Zone and Context listed. If a given environment has more than one zone and/or 
	 *                       context then each combination must be a separate entry in this list.
	 * 
	 * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then the list of responses holds a response for each 
	 *         environment this consumer is connected to. The creation of data was in each environment's default zone and context.
	 * 
	 * @throws IllegalArgumentException One of the parameters is invalid.
	 * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
	 */
	public List<Response> createSingle(Object data, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;
  
    /**
     * This method updates the object given by its resourceID.
     * 
     * @param data The actual object (i.e. Student). It holds the values of the object that need to be updated. It can either hold the
     *             full object or only parts of the object that needs updating. 
     * @param resourceID The Id of the object to be updated.
     * @param envZoneCtxList If this List is null or empty then it is assumed that the operation is performed for ALL environments this consumer is connected
	 *                       to. It is performed for the default Zone and Context of each environment. If this list is not empty then the action is only 
	 *                       performed for the environments in the list and the Zone and Context listed. If a given environment has more than one zone and/or 
	 *                       context then each combination must be a separate entry in this list.
	 *                       
     * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then the list of responses holds a response for each 
	 *         environment this consumer is connected to. The creation of data was in each environment's default zone and context.
     * 
	 * @throws IllegalArgumentException One of the parameters is invalid.
	 * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
     */
	public List<Response> updateSingle(Object data, String resourceID, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;
  
	/**
	 * Removed the object with the given resourceId.
	 * 
	 * @param resourceID The Id of the object to be removed.
	 * @param envZoneCtxList If this List is null or empty then it is assumed that the operation is performed for ALL environments this consumer is connected
	 *                       to. It is performed for the default Zone and Context of each environment. If this list is not empty then the action is only 
	 *                       performed for the environments in the list and the Zone and Context listed. If a given environment has more than one zone and/or 
	 *                       context then each combination must be a separate entry in this list.
	 *                       
	 * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then the list of responses holds a response for each 
	 *         environment this consumer is connected to. The creation of data was in each environment's default zone and context.
	 * 
	 * @throws IllegalArgumentException One of the parameters is invalid.
	 * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
	 */
	public List<Response> deleteSingle(String resourceID, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;
  
	/**
	 * This method will create many objects in one call. The 'data' parameter is a collection-style object that is defined in the data
	 * model (i.e. StudentPersonals which is a collection of StudentPersonal).
	 * 
	 * @param data The 'collection' object. Each object in that collection will be created.
	 * @param envZoneCtxList If this List is null or empty then it is assumed that the operation is performed for ALL environments this consumer is connected
	 *                       to. It is performed for the default Zone and Context of each environment. If this list is not empty then the action is only 
	 *                       performed for the environments in the list and the Zone and Context listed. If a given environment has more than one zone and/or 
	 *                       context then each combination must be a separate entry in this list.
	 * 
	 * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then the list of responses holds a response for each 
	 *         environment this consumer is connected to. The creation of data was in each environment's default zone and context.
	 *         
	 * @throws IllegalArgumentException One of the parameters is invalid.
	 * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
	 */
	public List<BulkOperationResponse> createMany(Object data, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;
  
	/**
	 * This method will update many objects in one call. The 'data' parameter is a collection-style object that is defined in the data
	 * model (i.e. StudentPersonals which is a collection of StudentPersonal). Each object in the collection can either hold the
   * full object or only parts of the object that needs updating.
	 * 
	 * @param data The 'collection' object. Each object in that collection will be updated.
	 * @param envZoneCtxList If this List is null or empty then it is assumed that the operation is performed for ALL environments this consumer is connected
	 *                       to. It is performed for the default Zone and Context of each environment. If this list is not empty then the action is only 
	 *                       performed for the environments in the list and the Zone and Context listed. If a given environment has more than one zone and/or 
	 *                       context then each combination must be a separate entry in this list.
	 *                       
	 * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then the list of responses holds a response for each 
	 *         environment this consumer is connected to. The creation of data was in each environment's default zone and context.
	 *         
	 * @throws IllegalArgumentException One of the parameters is invalid.
	 * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
	 */
	public List<BulkOperationResponse> updateMany(Object data, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;
  
	/**
	 * This method removes all objects in the resourceIDs list in one hit.
	 * 
	 * @param resourceIDs A list of resourceId for the objects to be removed.
	 * @param envZoneCtxList If this List is null or empty then it is assumed that the operation is performed for ALL environments this consumer is connected
	 *                       to. It is performed for the default Zone and Context of each environment. If this list is not empty then the action is only 
	 *                       performed for the environments in the list and the Zone and Context listed. If a given environment has more than one zone and/or 
	 *                       context then each combination must be a separate entry in this list.
	 * 
	 * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then the list of responses holds a response for each 
	 *         environment this consumer is connected to. The creation of data was in each environment's default zone and context.
	 *         
	 * @throws IllegalArgumentException One of the parameters is invalid.
	 * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
	 */
	public List<BulkOperationResponse> deleteMany(List<String> resourceIDs, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;

	/**
	 * This method returns an object with the given resourceId.
	 * 
	 * @param resourceID The Id of the object to be returned.
	 * @param envZoneCtxList If this List is null or empty then it is assumed that the operation is performed for ALL environments this consumer is connected
	 *                       to. It is performed for the default Zone and Context of each environment. If this list is not empty then the action is only 
	 *                       performed for the environments in the list and the Zone and Context listed. If a given environment has more than one zone and/or 
	 *                       context then each combination must be a separate entry in this list.
	 * 
	 * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then the list of responses holds a response for each 
	 *         environment this consumer is connected to. The creation of data was in each environment's default zone and context.
	 *         
	 * @throws IllegalArgumentException One of the parameters is invalid.
	 * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
	 */
	public List<Response> retrievByPrimaryKey(String resourceID, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException;
  	
	/**
	 * This method is used to retrieve any number of objects. This is achieved in terms of 'paging' through the list of objects. The consumer
	 * is expected to provide paging information to tell the provider which objects in the list shall be returned. The first page has
	 * the number 0.
	 * 
	 * @param pagingInfo Page information to be set for the provider to determine which results to return.
	 * @param envZoneCtxList If this List is null or empty then it is assumed that the operation is performed for ALL environments this consumer is connected
	 *                       to. It is performed for the default Zone and Context of each environment. If this list is not empty then the action is only 
	 *                       performed for the environments in the list and the Zone and Context listed. If a given environment has more than one zone and/or 
	 *                       context then each combination must be a separate entry in this list.
	 * 
	 * @return A list of responses corresponding to the List of envZoneCtx. If the envZoneCtx was null then the list of responses holds a response for each 
	 *         environment this consumer is connected to. The creation of data was in each environment's default zone and context.
	 *         
	 * @throws UnsupportedQueryException The query provided with this request is not supported (NOT YET IMPLEMENTED FUNCTIONALITY)
	 * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
	 * 
	 */
	public List<Response> retrieve(PagingInfo pagingInfo, List<EnvironmentZoneContextInfo> envZoneCtxList) throws PersistenceException, UnsupportedQueryException, ServiceInvokationException;
  
	/** Call this at shut down time. */
	public void finalise();
}
