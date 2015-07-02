/*
 * QueryConsumer.java
 * Created: 06/06/2015
 *
 * Copyright 2015 Systemic Pty Ltd
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
import sif3.common.exception.ServiceInvokationException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderValues.QueryIntention;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.model.CustomParameters;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.ZoneContextInfo;
import sif3.common.ws.Response;

/**
 * This interface defines the various query methods that form part of SIF 3.x for a consumer. If a consumer
 * is required to implement not only the SIF 3.x CRUD method but also some or all of the SIF 3.x query
 * mechanism (excluding xQuery) then the consumer should implement this interface.
 *
 * @author Joerg Huber
 */
public interface QueryConsumer
{
	/**
	 * This method is used to retrieve any number of objects, filtered by the service path. Each predicate within the 
	 * criteria holds a parent object and it's key. This method uses 'paging' in the same way as the consumer's.
	 * retrieve method. Also all other standard parameters such as zone, context, query intention etc are part of
	 * this method.
	 * 
	 * The number of records to be returned are determined by the query criteria as well as information within the
	 * paging info parameter. If the data set to be returned is considered too large by the provider (implementation 
	 * dependent) then a DataTooLargeException must be raised. This exception is then translated into an appropriate
	 * HTTP Status within the framework to meet the SIF Specification.
	 * 
	 * @param queryCriteria The criteria to use when filtering results.
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
	 * @return A list of responses with one response for each zone/context combination. The data in the response is a ‘collection-type’ style 
	 *         object as defined in the data model (i.e. StudentPersonals).
	 *         
	 * @throws UnsupportedQueryException The query provided with this request is not supported (NOT YET IMPLEMENTED FUNCTIONALITY)
	 * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions holds some info.
	 * @throws ServiceInvokationException Service on provider could not be executed. See error log for details.
	 */
	public List<Response> retrieveByServicePath(QueryCriteria queryCriteria, 
												PagingInfo pagingInfo, 
												List<ZoneContextInfo> zoneCtxList, 
												RequestType requestType, 
												QueryIntention queryIntention, 
												CustomParameters customParameters) throws PersistenceException, UnsupportedQueryException, ServiceInvokationException;

	/**
	 * This method is used to retrieve data based on the 'Query By Example' (QBE) concept. All objects that match the
	 * exampleObject parameter shall be returned. The exampleObject is a single SIF Object type such as 
	 * StudentPersonalType where as the returned data is a collection of the same SIF Object type such as 
	 * StudentPersonalCollectionType as defined in the data model.
	 * 
	 * @param exampleObject The example data model object. This must be the single object type.
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
	 * @return A list of responses with one response for each zone/context combination. The data in the response is a 'collection-type' style 
	 *         object as defined in the data model (i.e. StudentPersonals).
	 * 
	 * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is 
	 *                              performed and the  message of the exceptions holds some info.
	 * @throws UnsupportedQueryException The query provided with this request is not supported (NOT YET IMPLEMENTED FUNCTIONALITY)
	 * @throws DataTooLargeException If the data that shall be returned is too large due to the query criteria or
	 *                               values in the paging info.
	 */
	public List<Response> retrieveByQBE(Object exampleObject, 
            							PagingInfo pagingInfo, 
            							List<ZoneContextInfo> zoneCtxList, 
            							RequestType requestType, 
            							QueryIntention queryIntention, 
            							CustomParameters customParameters) throws PersistenceException, UnsupportedQueryException, ServiceInvokationException;
}