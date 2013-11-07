/*
 * AuthenticationUtils.java
 * Created: 27/08/2013
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

package sif3.common.utils;

import java.nio.charset.Charset;

import com.sun.jersey.core.util.Base64;

/**
 * This calls provides a few useful methods for SIF3 specific authentication functions.
 * 
 * @author Joerg Huber
 *
 */
public class AuthenticationUtils
{
	/* Defines valid authentication methods for SIF 3.0 */
	public enum AUTHENTICATION_METHOD {Basic, SIF_HMACSHA256};
	
	
	/**
	 * This method returns a basic authentication token with the prefix "Basic ". The user name and password is base64 encoded as described in
	 * the method base64Encode() method.
	 * 
	 * @param username The username to encode into the basic authentication token.
	 * @param password The password to encode into the basic authentication token.
	 * 
	 * @return The full basic authentication token with a "Basic " prefix.
	 */
	public static String getBasicAuthToken(String username, String password)
	{
		return AUTHENTICATION_METHOD.Basic.toString()+" "+base64Encode(username, password);
	}
	
	/**
	 * Performs a base64 encoding on the following string: username:password. The encoded string is then returned.
	 * 
	 * @param username The username to encode into the base64 string.
	 * @param password The password to encode into the base64 string.
	 * 
	 * @return The base64 encoded username:password string.
	 */
	public static String base64Encode(String username, String password)
	{
		return new String(Base64.encode(username+":"+password), Charset.forName("ASCII"));
	}
}
