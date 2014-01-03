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

package sif3.infra.rest.header;

import javax.ws.rs.core.HttpHeaders;

/**
 * @author Joerg Huber
 *
 */
public class ResponseHeaderConstants
{
	/*
	 * Names of valid REST Response Header Fields.
	 */
	public static final String HDR_AUTH_TOKEN = HttpHeaders.AUTHORIZATION;
	public static final String HDR_GENERATOR_ID = "generatorId";
	public static final String HDR_IF_NOT_MATCH = HttpHeaders.IF_NONE_MATCH;
	public static final String HDR_NAVIGATION_ID = "navigationId";	
	public static final String HDR_ETAG = HttpHeaders.ETAG;
	public static final String HDR_CONTENT_LENGTH = HttpHeaders.CONTENT_LENGTH;
	public static final String HDR_LOCATION = HttpHeaders.LOCATION;
	public static final String HDR_PROVIDER_ID = "providerId";
	public static final String HDR_PAGE_SIZE = "navigationPageSize";
	public static final String HDR_PAGE_NO = "navigationPage";
	public static final String HDR_TOTAL_ITEMS = "navigationItemCount";
	public static final String HDR_LAST_PAGE_NO = "navigationLastPage";
	public static final String HDR_DATE_TIME = "messageDateTime";
	public static final String HDR_MESSAGE_TYPE = "messageType";
	public static final String HDR_REQUEST_ID = "requestId";
	public static final String HDR_RESPONSE_ACTION = "responseAction";
	public static final String HDR_REL_SERVICE_PATH = "relativeServicePath";
	
	 public static final String[] HEADER_NAME_ARRAY = 
	 {
	   HDR_AUTH_TOKEN, 
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
	   HDR_REL_SERVICE_PATH
	 };

}
