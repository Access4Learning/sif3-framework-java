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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import sif3.common.model.AuthenticationInfo;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;
import au.com.systemic.framework.utils.StringUtils;

/**
 * This calls provides a few useful methods for SIF3 specific authentication functions.
 * 
 * @author Joerg Huber
 *
 */
public class AuthenticationUtils
{
	/*----------------------*/
	/*-- Encoding Methods --*/
	/*----------------------*/
	
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
		return AuthenticationMethod.Basic.toString()+" "+base64Encode(username, password);
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
//		return new String(Base64.encode(username+":"+password), Charset.forName("ASCII"));
		String token = username+":"+password;
		return new String(Base64.encodeBase64(token.getBytes()), Charset.forName("ASCII"));
	}
	
	/**
	 * This method returns a HMAC-SHA256 hashed authentication token with the prefix "SIF_HMACSHA256 ". The token is
	 * generated based on the given date string (in ISO8601). The password is used to hash the username and date 
	 * string using HMAC-SHA256 algorithm. The final result is bas64 encoded. The exact algorithm is described in the
	 * SIF3 specification.
	 * 
	 * @param username Username to use for the creation of the authentication token.
	 * @param password Password to be used to hash the authentication token with HMAC-SHA256
	 * @param dateAsISO8601 Date in ISO8601 format (i.e 2014-01-14T09:48:42.942Z).
	 * 
	 * @return See desc.
	 */
	public static String getSIFHMACSHA256Token(String username, String password, String dateAsISO8601)
	{
//		return AuthenticationMethod.SIF_HMACSHA256.toString()+" "+new String(Base64.encode(username+":"+hmacsha256Base64(username+":"+dateAsISO8601, password)), Charset.forName("ASCII"));
		String token = username+":"+hmacsha256Base64(username+":"+dateAsISO8601, password);
		return AuthenticationMethod.SIF_HMACSHA256.toString()+" "+new String(Base64.encodeBase64(token.getBytes()), Charset.forName("ASCII"));
	}
	
	/**
	 * Generates a token where the given message is hashed with the given key based on the HMAC-SHA256 algorithm. The 
	 * hash is then base64 encoded and returned.
	 * 
	 * @param message The text to be hashed.
	 * @param key The key to use for the hash.
	 * 
	 * @return see desc.
	 */
	public static String hmacsha256Base64(String message, String key)
	{
//		return new String(Base64.encode(hmacsha256AsBytes(message, key)), Charset.forName("ASCII"));
		return new String(Base64.encodeBase64(hmacsha256AsBytes(message, key)), Charset.forName("ASCII"));
	}
	
	/**
	 * Generates a token where the given message is hashed with the given key based on the HMAC-SHA256 algorithm.
	 * The result is returned as a string and not as a binary.
	 * 
	 * @param message The text to be hashed.
	 * @param key The key to use for the hash.
	 * 
	 * @return see desc.
	 */
	public static String hmacsha256AsString(String message, String key)
	{
		return new String(hmacsha256AsBytes(message, key), Charset.forName("ASCII"));
	}
	
	/**
	 * Generates a byte array representing the hash for the given message and the given key based on the HMAC-SHA256 
	 * algorithm.
	 * 
	 * @param message The text to be hashed.
	 * @param key The key to use for the hash.
	 * 
	 * @return see desc.
	 */
	public static byte[] hmacsha256AsBytes(String message, String key)
	{
		try
		{
			Mac sha256HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
			sha256HMAC.init(secretKey);

			return sha256HMAC.doFinal(message.getBytes());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/*----------------------*/
	/*-- Decoding Methods --*/
	/*----------------------*/
	
	/**
	 * Extracts the token from the full authentication token. The full authentication token is the
	 * base64 encoded token with the prefix of Basic or SIF_HMACSHA256 separated by a blank. If the fullToken
	 * is null or empty then null is returned. Also if there is no " " found (which separates the method from the token)
	 * then the fullToken is returned.
	 * 
	 * @param fullToken The full token including the prefix of 'Basic' or 'SIF_HMACSHA256'
	 * 
	 * @return The token without the prefix.
	 */
	public static String extractAuthToken(String fullToken)
	{
		if (StringUtils.notEmpty(fullToken))
		{
			fullToken = fullToken.trim();
			int splitPos = fullToken.indexOf(" ");
			return  (splitPos>0) ? fullToken.substring(splitPos+1) : fullToken;
		}
		return null;
	}
	
	/**
	 * This method extracts the authentication method from the full authentication token. The full authentication token
	 * is the base64 encoded token with the prefix of Basic or SIF_HMACSHA256 separated by a blank. If the fullToken
	 * is null or empty then null is returned. Also if there is no " " found (which separates the method from the token)
	 * then null is returned.
	 * 
	 * @param fullToken The full token including the prefix of 'Basic' or 'SIF_HMACSHA256'
	 * 
	 * @return The prefix of the fullToken. Will be either 'Basic' or 'SIF_HMACSHA256'
	 */
	public static AuthenticationMethod extractAuthenticationMethod(String fullToken)
	{
		if (StringUtils.notEmpty(fullToken))
		{
			fullToken = fullToken.trim();
			int splitPos = fullToken.indexOf(" ");
			String authMethodStr = (splitPos>0) ? fullToken.substring(0, splitPos) : null;
			try
			{
				return  (authMethodStr != null) ? AuthenticationMethod.valueOf(authMethodStr) : null;
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	/**
	 * This method assumes that the given token is a base64 encoded token. It will base64 decode it first. It will now
	 * assume that the token has the following form: leftSide:rightSide. This method will return the string array (max 
	 * size of 2) with the string[0]=leftSide and string[1]=rightSide. If there is no ':' found then the string array
	 * has only size=1 where string[0]=token (original string base64 decoded). If null is passed to this method then 
	 * null is returned.
	 * 
	 * @param token The token from which the left and right side shall be extracted.
	 * 
	 * @return See desc
	 */
	public static String[] splitValuesFromBase64(String token)
	{
		if (token != null)
		{
//			String decodedStr = new String(Base64.decode(token), Charset.forName("ASCII"));
			String decodedStr = new String(Base64.decodeBase64(token), Charset.forName("ASCII"));
			int splitPos = decodedStr.indexOf(":");
			
			return ((splitPos>0) ? new String[]{decodedStr.substring(0, splitPos), decodedStr.substring(splitPos+1)} : new String[]{decodedStr});			
		}
		
		return null;
	}
	
	/**
	 * This method takes the full authentication token. This is the token that has the form:
	 *   ("Basic"|"SIF_HMACSHA256")" "<base64EncodedString> where the <base64EncodedString> must be of the format:
	 *   <usreToken>:<some_string>.
	 * The returned AuthenticationInfo has the following Structure:
	 * 
	 * AuthenticationInfo.authMethod="Basic"|"SIF_HMACSHA256"
	 * AuthenticationInfo.userToken=<userToken>
	 * AuthenticationInfo.password=<some_string>
	 * 
	 * Note: If authMethod=SIF_HMACSHA256 then the AuthenticationInfo.password is not the clear text password rather the hashed token
	 *       according to the SIF3 spec.
	 * 
	 * @param fullBase64Token See desc.
	 * 
	 * @return See Desc
	 */
	public static AuthenticationInfo getPartsFromAuthToken(String fullBase64Token)
	{
		fullBase64Token = fullBase64Token.trim();
		int splitPos = fullBase64Token.indexOf(" ");
		String authMethodStr = (splitPos>0) ? fullBase64Token.substring(0, splitPos) : null;
		String encodedToken = (splitPos>0) ? fullBase64Token.substring(splitPos+1) : fullBase64Token;
		String[] splitValues = splitValuesFromBase64(encodedToken);
		
		return new AuthenticationInfo(authMethodStr, splitValues[0], splitValues[1]);
	}
}
