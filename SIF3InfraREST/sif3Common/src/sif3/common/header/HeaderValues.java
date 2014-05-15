/*
 * HeaderValues.java
 * Created: 19/11/2013
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

/**
 * @author Joerg Huber
 *
 */
public class HeaderValues
{
	/**
	 * Valid Message Types. These values will be used in the "messageType" response header field.
	 */
	public enum MessageType {RESPONSE, EVENT, ERROR};

	/**
	 * Valid Event Types. These values will be used in appropriate response header fields.
	 */
	public enum EventAction {CREATE, UPDATE, DELETE};

	/**
	 * Valid values for the "replacement" header field in an update event.
	 */
	public enum UpdateType {FULL, PARTIAL};

	/**
	 * Valid Response Types. These values will be used in the "responseAction" response header field.
	 */
	public enum ResponseAction {CREATE, UPDATE, DELETE, QUERY};
	
	/**
	 * Valid values for the "methodOverride" header field in the PUT (Update) request.
	 */
	public enum MethodType {DELETE, UPDATE};
	
	/**
	 * Valid values for the "serviceType" header field in the request.
	 */
	public enum ServiceType {OBJECT, FUNCTION, UTILITY, SERVICEPATH, XQUERYTEMPLATE};

	/**
	 * Valid values for the "requestType" header field in the request.
	 */
	public enum RequestType {IMMEDIATE, DELAYED};
}
