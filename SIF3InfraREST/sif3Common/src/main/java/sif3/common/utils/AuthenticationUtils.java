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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.model.AuthenticationInfo;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;

/**
 * This class provides a few useful methods for SIF3 specific authentication functions. If the authentication method is 'Basic' or 
 * 'SIF_HMACSHA256' then the authentication token has the structure as defined in the SIF specification, meaning it is base64 encoded and 
 * can be split on the ':' that separates the applicationKey/sessionToken and the password/HMAC Hash. If the authentication token is 'Bearer' 
 * then it is assumed that the token is managed by a security mechanism outside of the SIF Specification and therefore no assumptions about 
 * the structure of the token can be made. In this case the methods in this class will not attempt to further split the token into any
 * components or encode/decode it in any way. For details please refer to documentation in each method. 
 * 
 * @author Joerg Huber
 *
 */
public class AuthenticationUtils
{
	protected final static Logger logger = LoggerFactory.getLogger(AuthenticationUtils.class);

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
		return AuthenticationMethod.Basic.name()+" "+base64Encode(username, password);
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
	public static String getSIFHMACSHA256AuthToken(String username, String password, String dateAsISO8601)
	{
		String token = username+":"+hmacsha256Base64(username+":"+dateAsISO8601, password);
		return AuthenticationMethod.SIF_HMACSHA256.name()+" "+new String(Base64.encodeBase64(token.getBytes()), Charset.forName("ASCII"));
	}

	/**
	 * Returns the full token with the authentication method prefixed.
	 * 
	 * @param token The token to be used got the final authentication token.
	 * @param password Optional. Not currently used.
	 * 
	 * @return The Bearer Authentication Token of the form "Bearer <token>".
	 */
	public static String getBearerAuthToken(String token, String password)
	{
		return AuthenticationMethod.Bearer.name()+" "+ token;
	}

	
	/**
	 * This method create the full authentication token of the format:<br/>
	 * If authentication method is Basic or SIF_HMACSHA256: <AuthMethod>" "base64Encode(<userToken>:<password>)<br/>
	 * If authentication method is Bearer: Bearer <userToken>. Meaning that the userToken is untouched, i.e. not base64 encoded.<br/>
	 * <br/>
	 * If the authInfo is null then null is returned.
	 * 
	 * @param authInfo  The values to use to create the full authentication token based on the components
	 *                  in this parameter
	 * 
	 * @return See desc
	 */
	public static String getFullBase64Token(AuthenticationInfo authInfo)
	{
		if (authInfo == null)
		{
			return null;
		}
		if (authInfo.getAuthMethod() != AuthenticationInfo.AuthenticationMethod.Bearer)
		{
			return authInfo.getAuthMethod().name() + " " + base64Encode(authInfo.getUserToken(), authInfo.getPassword());
		}
		else // It is Bearer so leave token untouched!
		{
			return getBearerAuthToken(authInfo.getUserToken(), null);
		}
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
		String token = username+":"+password;
		return new String(Base64.encodeBase64(token.getBytes()), Charset.forName("ASCII"));
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
		catch (Exception ex)
		{
			logger.error("Generating the HMACSHA256 hash has failed: "+ex.getMessage(), ex);
			return null;
		}
	}
	
	/*----------------------*/
	/*-- Decoding Methods --*/
	/*----------------------*/
	
	/**
	 * Extracts the token from the full authentication token. The full authentication token is the security token with the prefix of 
	 * Basic, SIF_HMACSHA256 or Bearer separated by a blank. If the fullToken is null or empty then null is returned. Also if there is 
	 * no " " found (which separates the method from the token) then the fullToken is returned.
	 * 
	 * @param fullToken The full token including the prefix of 'Basic', 'SIF_HMACSHA256' or 'Bearer'
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
	 * This method extracts the authentication method from the full authentication token. The full authentication token is the security 
	 * token with the prefix of Basic, SIF_HMACSHA256 or Bearer separated by a blank. If the fullToken is null or empty then null is returned. 
	 * Also if there is no " " found (which separates the method from the token) then null is returned.
	 * 
	 * @param fullToken The full token including the prefix of 'Basic', 'SIF_HMACSHA256' or 'Bearer'. (See AuthenticationMethod enum)
	 * 
	 * @return The prefix of the fullToken. Will be one of the values of the AuthenticationMethod enum. If an invalid auth method
	 *         is used then null is returned.
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
				logger.error(authMethodStr+" is an invalid authentication method. Valid values are Basic, SIF_HMACSHA256 and Bearer.");
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
			String decodedStr = new String(Base64.decodeBase64(token), Charset.forName("ASCII"));
			int splitPos = decodedStr.indexOf(":");
			
			return ((splitPos>0) ? new String[]{decodedStr.substring(0, splitPos), decodedStr.substring(splitPos+1)} : new String[]{decodedStr});			
		}
		
		return null;
	}
	
	/**
	 * This method takes the full authentication token. This is the token that has one of the two forms:<br/>
	 *   ("Basic"|"SIF_HMACSHA256")" "<base64EncodedString> where the <base64EncodedString> must be of the format:
	 *   <usreToken>:<some_string>.<br/>
   *   "Bearer "<securityToken><br/><br/>
	 * <br/>
	 * If the token does not follow one of these structures, i.e. cannot be split into these components then it is invalid and null 
	 * is returned. If the token can be split successfully then the returned AuthenticationInfo has the following Structure:<br/><br/>
	 * 
	 * For "Basic"|"SIF_HMACSHA256":<br/>
	 * AuthenticationInfo.authMethod="Basic"|"SIF_HMACSHA256"<br/>
	 * AuthenticationInfo.userToken=<userToken><br/>
	 * AuthenticationInfo.password=<some_string><br/><br/>
	 * 
	 * For "Bearer":<br/>
	 * AuthenticationInfo.authMethod="Bearer"<br/>
	 * AuthenticationInfo.userToken=<securityToken><br/>
	 * AuthenticationInfo.password=null<br/><br/>
	 * 
	 * Note: If authMethod=SIF_HMACSHA256 then the AuthenticationInfo.password is not the clear text password rather the hashed token
	 *       according to the SIF3 spec.
	 * 
	 * @param fullBaseToken See desc.
	 * 
	 * @return See Desc
	 */
	public static AuthenticationInfo getPartsFromAuthToken(String fullBaseToken)
	{
		fullBaseToken = fullBaseToken.trim();
		int splitPos = fullBaseToken.indexOf(" ");
		String authMethodStr = (splitPos>0) ? fullBaseToken.substring(0, splitPos) : null;
		String securityToken = (splitPos>0) ? fullBaseToken.substring(splitPos+1) : fullBaseToken;
		
		// Check if we have a Bearer authentication method.
		if (AuthenticationInfo.AuthenticationMethod.Bearer.name().equals(authMethodStr))
		{
			return new AuthenticationInfo(AuthenticationInfo.AuthenticationMethod.Bearer, securityToken, null);
		}
		else // it should be Basic or SIF_HMACSHA256
		{
	  		String[] splitValues = splitValuesFromBase64(securityToken);
	  		
	  		if (splitValues.length == 2) // all good
	  		{
	  			return new AuthenticationInfo(authMethodStr, splitValues[0], splitValues[1]);
	  		}
	  		else // we could not split the token into userToken and password
	  		{
	  			logger.error("The token of the authenticathen method "+authMethodStr+" is not a base64 encoded string with a ':' separating two components as expected by the SIF sepcification.");
	  			return null;
	  		}
		}
	}
}
