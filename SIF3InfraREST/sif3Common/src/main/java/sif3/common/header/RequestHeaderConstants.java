/*
 * RequestHeaderConstants.java
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
public class RequestHeaderConstants
{	
	/*----------------------------------------------------*/
	/*-- Name of fields that are valid for all requests --*/
	/*----------------------------------------------------*/
	
	public static final String HDR_MESSAGE_ID = "messageId";
	public static final String HDR_AUTH_TOKEN = HttpHeaders.AUTHORIZATION;
	public static final String HDR_GENERATOR_ID = "generatorId";
	public static final String HDR_MESSAGE_TYPE = "messageType"; // RESPONSE, EVENT, ERROR
	public static final String HDR_SERVICE_TYPE = "serviceType"; // OBJECT, FUNCTION, UTILITY, SERVICEPATH, XQUERYTEMPLATE
	public static final String HDR_DATE_TIME = "timestamp";
	public static final String HDR_SOURCE_NAME = "sourceName";
    public static final String HDR_FINGERPRINT = "fingerprint";
	
	/*------------------------------- Early SIF 3.1.0 proposed header fields -------------------------------*/
	/*-- Experimental as of January 2015: Use with care because it is not officially part of SIF 3.x, yet --*/
	/*------------------------------------------------------------------------------------------------------*/
	public static final String HDR_APPLICATION_KEY = "applicationKey"; 
	public static final String HDR_AUTHENTICATED_USER = "authenticatedUser";
	//public static final String HDR_INSTANCE_ID = "instanceId";
	
	/*--------------------------------------------------------*/
	/*-- Name of fields that are specific for CRUD Requests --*/
	/*--------------------------------------------------------*/
	public static final String HDR_IF_NOT_MATCH = HttpHeaders.IF_NONE_MATCH;
	public static final String HDR_NAVIGATION_ID = CommonConstants.PagingRequestProperty.navigationId.name(); //"navigationId";
	public static final String HDR_PAGE_SIZE = CommonConstants.PagingRequestProperty.navigationPageSize.name(); //"navigationPageSize";
	public static final String HDR_PAGE_NO = CommonConstants.PagingRequestProperty.navigationPage.name(); //"navigationPage";
	public static final String HDR_REQUEST_ID = "requestId";
    public static final String HDR_REQUEST_ACTION = "requestAction"; // CREATE, UPDATE, DELETE, QUERY
	
	public static final String HDR_QUEUE_ID = "queueId";
	public static final String HDR_ADVISORY = "mustUseAdvisory"; //true, false
	public static final String HDR_METHOD_OVERRIDE = "methodOverride"; //DELETE, UPDATE
	public static final String HDR_REQUEST_TYPE = "requestType"; //IMMEDIATE, DELAYED
	public static final String HDR_QUERY_INTENTION = "queryIntention"; //ALL, ONE-OFF, NO-CACHING

	/*---------------------------------------------------------*/
	/*-- Name of fields that are specific for Event Requests --*/
	/*---------------------------------------------------------*/
	public static final String HDR_SERVICE_NAME= "serviceName"; // i.e. StudentPersonals
	public static final String HDR_UPDATE_TYPE = "replacement"; // FULL, PARTIAL (see HeaderValues.UpdateType)
	public static final String HDR_CONTEXT_ID = "contextId";
	public static final String HDR_ZONE_ID = "zoneId";
	public static final String HDR_EVENT_ACTION = "eventAction"; // CREATE, UPDATE, DELETE (see HeaderValues.EventAction)
	public static final String HDR_CONSUMER_ID = "connectionId"; // Used by getMessage() on queue to identify the thread that queries 
	
	public static final String[] HEADER_NAME_ARRAY = 
	{
		// General Fields
		HDR_MESSAGE_ID,
		HDR_AUTH_TOKEN, 
		HDR_GENERATOR_ID, 
		HDR_MESSAGE_TYPE,
		HDR_SERVICE_TYPE,
		HDR_DATE_TIME,
		HDR_SOURCE_NAME,
		HDR_FINGERPRINT,
		
		// Early adopted field (not yet officially supported)
		HDR_APPLICATION_KEY,
		HDR_AUTHENTICATED_USER,
		
		// CRUD Request Fields
		HDR_IF_NOT_MATCH, 
		HDR_NAVIGATION_ID,
		HDR_PAGE_SIZE,
		HDR_PAGE_NO,
		HDR_REQUEST_ID,
		HDR_REQUEST_ACTION,
		HDR_QUEUE_ID,
		HDR_ADVISORY,
		HDR_METHOD_OVERRIDE,
		HDR_REQUEST_TYPE,
		HDR_QUERY_INTENTION,

		// Event & Queue Related Fields
		HDR_ZONE_ID,
		HDR_CONTEXT_ID,
		HDR_EVENT_ACTION,
		HDR_UPDATE_TYPE,
		HDR_SERVICE_NAME,
		HDR_CONSUMER_ID,
	};

}
