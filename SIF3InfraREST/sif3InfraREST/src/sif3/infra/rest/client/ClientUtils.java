/*
 * ClientUtils.java
 * Created: 17/03/2014
 *
 * Copyright 2014 Systemic Pty Ltd
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

package sif3.infra.rest.client;

import sif3.common.header.HeaderProperties;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;
import sif3.common.utils.AuthenticationUtils;
import au.com.systemic.framework.utils.DateUtils;

/**
 * @author Joerg Huber
 *
 */
public class ClientUtils
{
	/**
	 * This method will add the authentication header properties as defined by the SIF spec.
	 * 
	 * @param hdrProps A not null TransportHeaderProperties object where authentication properties will be added to. If some authentication
	 *                 related properties should already exist then they will be over overwritten.
	 * @param authenticationMethod The authentication method to set in the header properties.
	 * @param username Username to use to generate the authentication token for the header properties.
	 * @param password Password to use to generate the authentication token for the header properties.
	 */
	public static synchronized void setAuthenticationHeader(HeaderProperties hdrProps, AuthenticationMethod authenticationMethod, String username, String password)
	{
		switch (authenticationMethod)
		{
			case Basic:
			{
				hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, AuthenticationUtils.getBasicAuthToken(username, password));
				break;
			}
			case SIF_HMACSHA256:
			{
				String iso8601Date = DateUtils.nowAsISO8601withSecFraction();
				hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, AuthenticationUtils.getSIFHMACSHA256AuthToken(username, password, iso8601Date));
				hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_DATE_TIME, iso8601Date);			
				break;
			}
			case Bearer:
			{
				hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, AuthenticationUtils.getBearerAuthToken(username, password));
				break;
			}
		}
		
//		if (authenticationMethod == AuthenticationMethod.Basic)
//		{
//			hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, AuthenticationUtils.getBasicAuthToken(username, password));
//		}
//		else if (authenticationMethod == AuthenticationMethod.SIF_HMACSHA256)
//		{
//			String iso8601Date = DateUtils.nowAsISO8601withSecFraction();
//			hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, AuthenticationUtils.getSIFHMACSHA256Token(username, password, iso8601Date));
//			hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_DATE_TIME, iso8601Date);			
//		}

	}
}
