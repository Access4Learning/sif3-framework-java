/*
 * QueryProvider.java
 * Created: 05/01/2015
 *
 * Copyright 2015-2018 Systemic Pty Ltd
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

import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIFException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;

/**
 * This interface is an extension to the Provider interface to also allow object queries.
 *
 * @author Ben Carter
 */
public interface QueryProvider extends Provider
{

	/**
	 * This method is used to retrieve any number of objects, filtered by the service path. Each predicate within the 
	 * criteria holds a parent object and it's key. This method uses 'paging' in the same way as Provider.retrieve.
	 * The number of records to be returned are determined by the query criteria as well as information within the
	 * paging info parameter. If the data set to be returned is considered too large by the provider (implementation 
	 * dependent) then a DataTooLargeException must be raised. This exception is then translated into an appropriate
	 * HTTP Status within the framework to meet the SIF Specification.
	 * 
	 * @param queryCriteria The criteria to use when filtering results
	 * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
	 * @param context The Context for which the objects shall be returned. Can be Null (default Zone)
	 * @param pagingInfo Page information to determine which results to return. Null = Return all (NOT RECOMMENDED!).
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * @param customResponseParams Values to be returned as part of the response. These are generally just HTTP Header fields. If a developer
     *                             sets the HTTP Header of a well defined SIF3 HTTP Header (i.e. providerId, timestamp) then the framework
     *                             may override these with its own value to ensure the correct use and workings of the framework. It is the
     *                             developer who will populate the object. When it is passed to this method it not null but empty.
	 * 
	 * @return Object Plural Type containing list of objects
	 * 
	 * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is 
	 *                              performed and the  message of the exceptions holds some info.
	 * @throws UnsupportedQueryException The query provided with this request is not supported (NOT YET IMPLEMENTED FUNCTIONALITY)
	 * @throws DataTooLargeException If the data that shall be returned is too large due to the query criteria or
	 *                               values in the paging info.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
	 */
	public Object retrieveByServicePath(QueryCriteria queryCriteria, 
			                            SIFZone zone, 
			                            SIFContext context, 
			                            PagingInfo pagingInfo, 
			                            RequestMetadata metadata, 
			                            ResponseParameters customResponseParams) 
			         throws PersistenceException, UnsupportedQueryException, DataTooLargeException, SIFException;

	/**
	 * This method is used to retrieve data based on the 'Query By Example' (QBE) concept. All objects that match the
	 * exampleObject parameter shall be returned. The exampleObject is a single SIF Object type such as 
	 * StudentPersonalType where as the returned data is a collection of the same SIF Object type such as 
	 * StudentPersonalCollectionType as defined in the data model.
	 * 
	 * @param exampleObject The example data model object. This must be the single object type.
	 * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
	 * @param context The Context for which the objects shall be returned. Can be Null (default Zone)
	 * @param pagingInfo Page information to determine which results to return. Null = Return all (NOT RECOMMENDED!).
	 * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * @param customResponseParams Values to be returned as part of the response. These are generally just HTTP Header fields. If a developer
     *                             sets the HTTP Header of a well defined SIF3 HTTP Header (i.e. providerId, timestamp) then the framework
     *                             may override these with its own value to ensure the correct use and workings of the framework. It is the
     *                             developer who will populate the object. When it is passed to this method it not null but empty.
	 * 
	 * @return Object Plural Type containing list of objects.
	 * 
	 * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is 
	 *                              performed and the  message of the exceptions holds some info.
	 * @throws UnsupportedQueryException The query provided with this request is not supported (NOT YET IMPLEMENTED FUNCTIONALITY)
	 * @throws DataTooLargeException If the data that shall be returned is too large due to the query criteria or
	 *                               values in the paging info.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
	 */
	public Object retrieveByQBE(Object exampleObject, 
            					SIFZone zone, 
            					SIFContext context, 
            					PagingInfo pagingInfo, 
            					RequestMetadata metadata,
            					ResponseParameters customResponseParams) 
            		throws PersistenceException, UnsupportedQueryException, DataTooLargeException, SIFException;
}
