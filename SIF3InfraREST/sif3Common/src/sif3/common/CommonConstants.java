/*
 * CommonConstants.java
 * Created: 05/12/2013
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

package sif3.common;

import sif3.common.model.SIFContext;

/**
 * This class holds the values of some system wide constants.
 * 
 * @author Joerg Huber
 */
public class CommonConstants
{
	/* Name of environment property file without the file extension */
	public static final String ENV_PROP_FILE_NAME = "environment";

	/*-----------------------------------------------------------*/
	/* Name of some standard property of adapter property files. */
	/*-----------------------------------------------------------*/

	/* Base name of event frequency property */
	public static final String FREQ_PROPERTY = "event.frequency";

	/* Base name of event event startup delay property */
	public static final String EVENT_DELAY_PROPERTY = "event.startup.delay";

	/* Base name of event max objects per event property */
	public static final String EVENT_MAX_OBJ = "event.maxObjects";
    
	/* Base name of job timeout frequency property */
    public static final String TIMEOUT_FREQ_PROPERTY = "job.timeout.frequency";
    
    /* Base name of job binding property */
    public static final String JOB_BINDING_PROPERTY = "job.binding";

	/*-----------------------------------------------------------*/
	/* Event related constants.                                  */
	/*-----------------------------------------------------------*/

	/* Provider Event Constants */
	
	/* Value to indicate no events to be sent by a provider. */
	public static final int NO_EVENT = 0;
		
	/* Default startup delay for events in seconds. */
	public static final int DEFAULT_EVENT_DELAY = 5;

	/* Consumer Event Constants */
	public static final int DEFAULT_POLL_FREQ = 60;
	
	public static final int DEFAULT_LONGPOLL_WAIT = 120;
	
	/*-----------------------------------------------------------*/
    /* Job related constants.                                    */
    /*-----------------------------------------------------------*/
	
	/* Value to indicate that job timeout is disabled. */
    public static final int NO_TIMEOUT = 0;
    
    /* Default timeout interval */
    public static final int DEFAULT_TIMEOUT_FREQ = 60;
    
    /* Default binding property */
    public static final boolean DEFAULT_JOB_BINDING = true;
	
	/*-----------------------------------------------------------*/
	/* Default values and other important constants.             */
	/*-----------------------------------------------------------*/

	/* Millisecond multiplier. Ensures that we get that right everywhere :-) */
	public static final int MILISEC = 1000;
	
	/* REST transport String */
	public static final String REST_TRANSPORT_STR = "REST";

	/* SOAP transport String: Future Use */
	public static final String SOAP_TRANSPORT_STR = "SOAP";

	/*-----------------------------------------------------------*/
	/* Constants defined by SIF3 Spec.                           */
	/*-----------------------------------------------------------*/
	public static final String DEFAULT_CONTEXT_NAME = "DEFAULT";
	
	/* default context */
	public static final SIFContext DEFAULT_CONTEXT = new SIFContext(DEFAULT_CONTEXT_NAME, true);

	/*----------------------------------------------------------------------*/
	/* Paging related Enum Types that can be either header or query params. */
	/*----------------------------------------------------------------------*/
	/*
	 * Valid properties that can be set on the HTTP request as header or url  parameters for paging
	 */
	public static enum PagingRequestProperty {navigationPageSize, navigationPage, navigationId}; // , queryIntention};

	/*
	 * Valid properties that can be set on the HTTP response: Header properties  only!
	 */
	public static enum PagingResponseProperty {navigationPageSize, navigationPage, navigationCount, navigationLastPage, navigationId};
	
	/*
	 * Value to indicate the first page.
	 */
	public static int FIRST_PAGE = 1;
	
	/*----------------------------------------------------------*/
	/* System Wide Enum Types that are not part of any Headers */
	/*----------------------------------------------------------*/
	
	/*
	 * Commonly used to distinguish functionality between Consumer & Provider
	 */
	public enum AdapterType {CONSUMER, PROVIDER, ENVIRONMENT_PROVIDER};
	
	/*
	 * Supported Queue Strategy for Framework. Currently only CONSUMER_LEVEL is implemented
	 */
	public enum QueueStrategy {ADAPTER_LEVEL, ZONE_LEVEL, OBJECT_LEVEL};

	/*
	 * Polling Type on Queues
	 */
	public enum QueuePollingType {IMMEDIATE, LONG};
	
	/*
     * Job states (for functional services)
     */
	public enum JobState {NOTSTARTED, INPROGRESS, COMPLETED, FAILED}

	/*
     * Phase states (for functional services)
     */
    public enum PhaseState {NOTAPPLICABLE, NOTSTARTED, PENDING, SKIPPED, INPROGRESS, COMPLETED, FAILED}

    /*------------------------------------------------------------------------------------*/
	/* URL Query Parameter names in relation to security (special case for SIF Express) --*/
	/*------------------------------------------------------------------------------------*/
	public static final String ACCESS_TOKEN      = "access_token";
	public static final String AUTH_METHOD       = "authenticationMethod"; // Indicates the authentication method to use with access token	
	public static final String ISO8601_TIMESTAMP = "timestamp";	// If SIF_HMACHSHA256 is used a timestamp in ISO8601 has to be provided.
	
    /*-------------------------------------------------------------*/
    /* URL Query Parameter names as defined by SIF Specification --*/
    /*-------------------------------------------------------------*/
    public static final String CHANGES_SINCE_MARKER_NAME = "changesSinceMarker";
	

	/*---------------------------*/
	/* Matrix Parameter names  --*/
	/*---------------------------*/
	public static final String MATRIX_DELETE_MSGID = "deleteMessageId"; // For message client.
	

	/*----------------------------------------------------------------*/
	/* HTTP Status that are not listed in the Response.Status class --*/
	/*----------------------------------------------------------------*/
	public static final int RESPONSE_TOO_LARGE = 413;
	
}
