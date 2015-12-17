/*
 * DelayedConsumer.java
 * Created: 1 Nov 2015
 *
 * Copyright 2015 Systemic Pty Ltd
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

import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.delayed.DelayedResponseReceipt;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.model.MultiOperationStatusList;

/**
 * This class defines the methods a consumer must implement to fit with this framework if it wishes to use the DELAYED 
 * request/response message pattern as defined by the SIF 3.x Specification. The interface is independent from the 
 * Data Model and underlying infrastructure components. It defines the methods to enable the delayed delivery of
 * CRUD operation requests (not events!).<br/><br/>
 * 
 * Note:<br/>
 * Because this framework allows to be run under Java 6 some of the types in various methods use "Object" instead of the  template 
 * notation. This is because Java 6 doesn't allow a 'new T()' and therefore the interface avoids the template notation to not break 
 * the implementation where a constructor for an Object might be required. This may change in future versions of the framework.
 *
 * @author Joerg Huber
 *
 */
public interface DelayedConsumer
{
    /**
     * This method is called by the framework whenever there is a DELAYED CREATE response on the message queue for that
     * consumer.
     * 
     * @param statusList The list of statuses. There is an entry for each object that has been created on the provider.
     * @param receipt Metadata information about the response relating to the original request. This includes but is not
     *                limited to the zoneId, contextId, original request ID etc. as well as all HTTP Headers of the response.
     */
	public void onCreateMany(MultiOperationStatusList<CreateOperationStatus> statusList, DelayedResponseReceipt receipt);
	
	/**
	 * This method is called by the framework whenever there is a DELAYED UPDATE response on the message queue for that
     * consumer.
     * 
     * @param statusList The list of statuses. There is an entry for each object that has been updated on the provider.
     * @param receipt Metadata information about the response relating to the original request. This includes but is not
     *                limited to the zoneId, contextId, original request ID etc. as well as all HTTP Headers of the response.
	 */
	public void onUpdateMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt);
	
	/**
     * This method is called by the framework whenever there is a DELAYED DELETE response on the message queue for that
     * consumer.
     * 
     * @param statusList The list of statuses. There is an entry for each object that has been deleted on the provider.
     * @param receipt Metadata information about the response relating to the original request. This includes but is not
     *                limited to the zoneId, contextId, original request ID etc. as well as all HTTP Headers of the response.
	 */
	public void onDeleteMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt);
	
	/**
     * This method is called by the framework whenever there is a DELAYED response to a Query (excluding SERVICEPATH) on the 
     * message queue for that consumer. This can be query results for standard OBJECT services, QBE type queries, Dynamic
     * Queries (not yet supported by this framework) etc.
     * 
	 * @param dataObject A collection type SIF Object. Each consumer class is responsible for dealing with a particular SIF 
	 *                   Object and its related collection type object. This parameter is that consumers collection type object
	 *                   (i.e. StudentPersonalCollectionType).
	 * @param pagingInfo The paging information relating to the query result that is returned. Because a consumer may request
	 *                   query results in pages it is necessary to pass that paging information back to the consumer as part
	 *                   of this call. This may allow the consumer to determine how many pages it may expect as well as if it
	 *                   has paged through all results.
     * @param receipt Metadata information about the response relating to the original request. This includes but is not
     *                limited to the zoneId, contextId, original request ID etc. as well as all HTTP Headers of the response.
	 */
	public void onQuery(Object dataObject, PagingInfo pagingInfo, DelayedResponseReceipt receipt);
	
	/**
	 * This method is specifically to pass query results for DELAYED SERVICEPATH requests to the consumer.
	 *  
	 * @param dataObject A collection type SIF Object. Each consumer class is responsible for dealing with a particular SIF 
     *                   Object and its related collection type object. This parameter is that consumers collection type object
     *                   (i.e. StudentPersonalCollectionType).
	 * @param queryCriteria The Query Criteria that relates to the original SERVICEPATH request (i.e. SchoolInfos = <refId>).
     * @param pagingInfo The paging information relating to the query result that is returned. Because a consumer may request
     *                   query results in pages it is necessary to pass that paging information back to the consumer as part
     *                   of this call. This may allow the consumer to determine how many pages it may expect as well as if it
     *                   has paged through all results.
     * @param receipt Metadata information about the response relating to the original request. This includes but is not
     *                limited to the zoneId, contextId, original request ID etc. as well as all HTTP Headers of the response.
	 */
	public void onServicePath(Object dataObject, QueryCriteria queryCriteria, PagingInfo pagingInfo, DelayedResponseReceipt receipt);
	
	/**
	 * Whenever DELAYED requests are issued they can throw errors the same way as IMMEDIATE requests can. These errors are
	 * put on the consumer's queue like and other DELAYED response. This method is called by the framework each time an
	 * ERROR message is read from the consumer's queue. Also if anything fails in processing any DELAYED messages that would
	 * otherwise be dealt with a method of this class then this method is called to ensure that the consumer is informed about
	 * any issues.
	 * 
	 * @param error The error details.
     * @param receipt Metadata information about the response relating to the original request. This includes but is not
     *                limited to the zoneId, contextId, original request ID etc. as well as all HTTP Headers of the response.
	 */
	public void onError(ErrorDetails error, DelayedResponseReceipt receipt);
}
