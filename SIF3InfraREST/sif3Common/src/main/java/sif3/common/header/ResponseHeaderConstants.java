/*
h * ResponseHeaderConstants.java
 * Created: 03/09/2013
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

package sif3.common.header;

import javax.ws.rs.core.HttpHeaders;

import sif3.common.CommonConstants;

/**
 * @author Joerg Huber
 *
 */
public class ResponseHeaderConstants
{
	/*
	 * Names of valid REST Response Header Fields.
	 */
	public static final String HDR_MESSAGE_ID = "messageId";
	public static final String HDR_AUTH_TOKEN = HttpHeaders.AUTHORIZATION;
	public static final String HDR_ENVIRONMENT_URI = "environmentURI";
	public static final String HDR_GENERATOR_ID = "generatorId";
	public static final String HDR_IF_NOT_MATCH = HttpHeaders.IF_NONE_MATCH;
	public static final String HDR_ETAG = HttpHeaders.ETAG;
	public static final String HDR_CONTENT_LENGTH = HttpHeaders.CONTENT_LENGTH;
	public static final String HDR_LOCATION = HttpHeaders.LOCATION;
	public static final String HDR_PROVIDER_ID = "providerId";
	public static final String HDR_DATE_TIME = "timestamp";
	public static final String HDR_MESSAGE_TYPE = "messageType"; // RESPONSE, EVENT, ERROR
	public static final String HDR_REQUEST_ID = "requestId";
	public static final String HDR_RESPONSE_ACTION = "responseAction"; // CREATE, UPDATE, DELETE, QUERY
	public static final String HDR_REL_SERVICE_PATH = "relativeServicePath";
	public static final String HDR_SERVICE_TYPE = "serviceType"; // OBJECT, FUNCTION, UTILITY, SERVICEPATH, XQUERYTEMPLATE

	/* Paging and navigation related properties */
	public static final String HDR_NAVIGATION_ID = CommonConstants.PagingResponseProperty.navigationId.name(); //"navigationId"; 
	public static final String HDR_PAGE_SIZE = CommonConstants.PagingResponseProperty.navigationPageSize.name(); //"navigationPageSize";
	public static final String HDR_PAGE_NO = CommonConstants.PagingResponseProperty.navigationPage.name(); //"navigationPage";
	public static final String HDR_TOTAL_ITEMS = CommonConstants.PagingResponseProperty.navigationCount.name(); //"navigationCount";
	public static final String HDR_LAST_PAGE_NO = CommonConstants.PagingResponseProperty.navigationLastPage.name(); //"navigationLastPage";

	/* Changes Since Header names */
	public static final String HDR_CHANGES_SINCE_MARKER = CommonConstants.CHANGES_SINCE_MARKER_NAME; // "changesSinceMarker"
	
	/*---------------------------------------------------------*/
	/*-- Name of fields that are specific for Event Requests --*/
	/*---------------------------------------------------------*/
	public static final String HDR_SERVICE_NAME= "serviceName"; // i.e. StudentPersonals
	public static final String HDR_UPDATE_TYPE = "replacement"; // FULL, PARTIAL (see HeaderValues.UpdateType)
	public static final String HDR_CONTEXT_ID = "contextId";
	public static final String HDR_ZONE_ID = "zoneId";
	public static final String HDR_EVENT_ACTION = "eventAction"; // CREATE, UPDATE, DELETE (see HeaderValues.EventAction)
	public static final String HDR_CONSUMER_ID = "connectionId"; // Used by getMessage() on queue to identify the thread that queries
    public static final String HDR_FINGERPRINT = "fingerprint";

	
	// NON SIF specific but of interest
	public static final String HDR_CONNECTION = "Connection";
	public static final String HDR_KEEP_ALIVE = "Keep-Alive"; 

	
	 public static final String[] HEADER_NAME_ARRAY = 
	 {
		 HDR_MESSAGE_ID,
		 HDR_AUTH_TOKEN, 
		 HDR_ENVIRONMENT_URI,
		 HDR_GENERATOR_ID, 
		 HDR_IF_NOT_MATCH, 
		 HDR_NAVIGATION_ID, 
		 HDR_ETAG, 
		 HDR_CONTENT_LENGTH, 
		 HDR_PROVIDER_ID, 
		 HDR_PAGE_SIZE,
		 HDR_PAGE_NO,
		 HDR_TOTAL_ITEMS,
		 HDR_LAST_PAGE_NO,
		 HDR_MESSAGE_TYPE,
		 HDR_DATE_TIME,
		 HDR_REQUEST_ID,
		 HDR_RESPONSE_ACTION,
		 HDR_LOCATION,
		 HDR_REL_SERVICE_PATH,
		 HDR_SERVICE_TYPE,
		 HDR_CONNECTION,
		 HDR_KEEP_ALIVE,
		 
		 //Changes Since fields
		 HDR_CHANGES_SINCE_MARKER,
		 
		// Event & Queue Related Fields
		HDR_ZONE_ID,
		HDR_CONTEXT_ID,
		HDR_EVENT_ACTION,
		HDR_UPDATE_TYPE,
		HDR_SERVICE_NAME,
		HDR_CONSUMER_ID,
		HDR_FINGERPRINT,
	 };

}
