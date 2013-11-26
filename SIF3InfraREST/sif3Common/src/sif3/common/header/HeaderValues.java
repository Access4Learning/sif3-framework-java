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
	 * Valid Response Types. These values will be used in appropriate response header fields.
	 */
	public enum ResponseType {RESPONSE, EVENT, ERROR};

	/**
	 * Valid Event Types. These values will be used in appropriate response header fields.
	 */
	public enum EventAction {CREATE, UPDATE, DELETE};

	/**
	 * Valid Response Types. These values will be used in appropriate response header fields.
	 */
	public enum ResponseAction {CREATE, UPDATE, DELETE, QUERY};
}
