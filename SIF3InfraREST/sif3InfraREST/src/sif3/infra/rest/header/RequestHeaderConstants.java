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

package sif3.infra.rest.header;

import javax.ws.rs.core.HttpHeaders;

/**
 * @author Joerg Huber
 *
 */
public class RequestHeaderConstants
{
	/*
	 * Names of valid REST Request Header Values.
	 */
	public static final String HDR_AUTH_TOKEN = HttpHeaders.AUTHORIZATION;
	public static final String HDR_GENERATOR_ID = "generatorId";
	public static final String HDR_IF_NOT_MATCH = HttpHeaders.IF_NONE_MATCH;
	public static final String HDR_NAVIGATION_ID = "navigationId";
	public static final String HDR_MESSAGE_TYPE = "messageType";
	
	public static final String[] HEADER_NAME_ARRAY = {HDR_AUTH_TOKEN, HDR_GENERATOR_ID, HDR_IF_NOT_MATCH, HDR_NAVIGATION_ID, HDR_MESSAGE_TYPE};

}
