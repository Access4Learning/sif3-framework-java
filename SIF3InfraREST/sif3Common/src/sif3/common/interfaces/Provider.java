/*
 * Provider.java
 * Created: 29/09/2013
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

import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.OperationStatus;

/**
 * This class defines the methods a provider must implement to fit with this framework. The interface is independent from the 
 * Data Model and underlying infrastructure components. It defines the core function SIF3 specifies for a provider.<br/>
 * Note that each method has a number of parameters but they all have the following three given parameters. The zone, 
 * the context and metadata. The first two relate to the standard SIF concept of zone and context. The metadata parameter
 * however is additional info that may be provided by a consumer with each request. This can be typical HTTP header 
 * fields such as generatorId, queryIntention etc. Please refer to the SIF 3 Infrastructure Service documentation what 
 * these fields mean as well as where they might be used. It is important to note that most of the properties in the 
 * metadata could be null and therefore implementations must take care how they are used.<br/><br/>
 * 
 * Note:<br/>
 * Because this framework allows to be run under Java 6 some of the types in various methods use "Object" instead of the  template 
 * notation. This is because Java 6 doesn't allow a 'new T()' and therefore the interface avoids the template notation to not break 
 * the implementation where a constructor for an Object might be required. This may change in future versions of the framework.
 * 
 * @author Joerg Huber
 */
public interface Provider extends DataModelLink
{
	/*------------------------------*/
	/*-- Single Object Operations --*/
	/*------------------------------*/

	/**
	 * This method returns the object with the given resourceID in the location defined by the zone and context. If the object with the
	 * given resourceID doesn't exist then null is returned.
	 * 
	 * @param resourceID The resourceID of the object that shall be returned.
	 * @param zone Can be Null (default Zone)
	 * @param context Can be Null (default Context)
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
	 * 
	 * @return The entity for this resourceID. Null if entity with given resourceID does not exist.
	 * 
	 * @throws IllegalArgumentException One of the parameters is invalid.
	 * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
	 *                              message of the exceptions holds some info.
	 */
	public Object retrievByPrimaryKey(String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException;

	/**
	 * This method creates the given object with the data provided in the given zone and context. If the object cannot be created then either
	 * an exception is raised or null is returned.
	 * 
	 * @param data The data of the actual Object to be created. It may or may not hold the resourceID and the provider may or may not accept it.
	 *             It is up to the implementation to make that decision. The final resourceID is returned as part of the returned object.
	 * @param useAdvisory TRUE: RefId/UUID of object should be set and should be used by provider. FALSE: Provider must allocate RefId/UUID
	 * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
	 * @param context The Context for which the object shall be created. Can be Null (default Zone)
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
	 * 
	 * @return The object that is created. It may hold additional data than the one provided.
	 * 
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
	 */
	public Object createSingle(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException;

	/**
     * This method updates the object given by its resourceID in the given zone for the given context.
     * 
     * @param data The actual object (i.e. Student). It holds the values of the object that need to be updated. It can either hold the
     *             full object or only parts of the object that needs updating. 
     * @param resourceID The Id of the object to be updated.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the object shall be updated. Can be Null (default Zone)
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
	 * 
	 * @return TRUE: Entity is updated. FALSE: Entity does not exist.
	 * 
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
	 */
	public boolean updateSingle(Object data, String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException;

	/**
     * Removed the object with the given resourceId in the given zone for the given context.
     * 
     * @param resourceID The Id of the object to be removed.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the object shall be removed. Can be Null (default Zone)
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
	 * 
	 * @return TRUE: Entity is removed. FALSE: Entity does not exist.
	 * 
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
	 */
	public boolean deleteSingle(String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException;

	/*----------------------------*/
	/*-- Bulk Object Operations --*/
	/*----------------------------*/

	/**
     * This method is used to retrieve any number of objects. This is achieved in terms of 'paging' through the list of objects. The consumer
     * is expected to provide paging information to tell the provider which objects in the list shall be returned. The first page has
     * the number 0. The number of records to be returned are determined by the information within the
	 * paging info parameter. If the data set to be returned is considered too large by the provider (implementation 
	 * dependent) then a DataTooLargeException must be raised. This exception is then translated into an appropriate
	 * HTTP Status within the framework to meet the SIF Specification.
     * 
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the objects shall be returned. Can be Null (default Zone)
     * @param pagingInfo Page information to determine which results to return. Null = Return all (NOT RECOMMENDED!).
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * 
     * @throws UnsupportedQueryException The query provided with this request is not supported (NOT YET IMPLEMENTED FUNCTIONALITY)
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
	 * @throws DataTooLargeException If the data that shall be returned is too large due to the values in the paging info.
	 */
	public Object retrieve(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException;
	
	/**
     * This method will create many objects in one call. The 'data' parameter is a collection-style object that is defined in the data
     * model (i.e. StudentPersonals which is a collection of StudentPersonal).
     * 
     * @param data The 'collection' object. Each object in that collection will be created.
     * @param useAdvisory TRUE: RefId/UUID of object should be set and should be used by provider. FALSE: Provider must allocate RefId/UUID
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the objects shall be created. Can be Null (default Zone)
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
	 * 
	 * @return List with Status and IDs for each created object, or Status and Error for each object. Null if there was an unknown error. 
	 * 
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
	 */
	public List<CreateOperationStatus> createMany(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException;

	/**
     * This method will update many objects in one call. The 'data' parameter is a collection-style object that is defined in the data
     * model (i.e. StudentPersonals which is a collection of StudentPersonal). Each object in the collection can either hold the
     * full object or only parts of the object that needs updating.
     * 
     * @param data The 'collection' object. Each object in that collection will be updated.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the objects shall be updated. Can be Null (default Zone)
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
	 * 
     * @return List with Status and IDs for each updated object, or Status and Error for each object. Null if there was an unknown error. 
	 * 
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
	 */
	public List<OperationStatus> updateMany(Object data, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException;

	/**
     * This method removes all objects in the resourceIDs list in one hit.
     * 
     * @param resourceIDs A list of resourceId for the objects to be removed.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the objects shall be removed. Can be Null (default Zone)
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
	 * 
     * @return List with Status and IDs for each removed object, or Status and Error for each object. Null if there was an unknown error. 
	 * 
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
	 */
	public List<OperationStatus> deleteMany(List<String> resourceIDs, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException;

	/*-------------------------------*/
	/*-- Other required Operations --*/
	/*-------------------------------*/	
	
	/**
	 * This method is called when a provider shuts down. Can be used to clean-up internally held resources etc. 
     */
	public void finalise();
}
