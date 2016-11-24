/*
 * SecurityService.java
 * Created: 12/12/2014
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
package sif3.common.interfaces;

import sif3.common.model.RequestMetadata;
import sif3.common.model.security.TokenCoreInfo;
import sif3.common.model.security.TokenInfo;

/**
 * This interface is a generic interface to enable the SIF framework to use any security (authentication and authorisation) approach to validate
 * consumers in a DIRECT environment.<br/><br/>
 * 
 * Note<br/>
 * For the initial release of this functionality this flexibility of security validation is only offered for a DIRECT environment. In future it 
 * might be expanded to a BROKERED environment as well. As of December 2014 the SIF Specification does not allow this flexible security validation.
 * It might be included in future releases. Therefore care must be taken in using this feature with this framework as it might not be compatible 
 * with SIF for the time being. <br/><br/> 
 * 
 * If an alternate security implementation to 'Basic' and 'SIF_HMACSHA256' as specified in the SIF Specification is required then this interface 
 * must be implemented. It is the implementers responsibility to connect to their security server to validate and return information about
 * the security token passed to each method in this interface. It is also required that a default constructor must exist (Constructor without 
 * parameters) for a specific implementation of this interface for it to be used by the SIF3 Framework.   
 *   
 * @author Joerg Huber
 */
public interface SecurityService
{
	/**
	 * This method return true if the given 'securityToken' is a valid token for the given security service. The security service can be any service
	 * such as OAuth, LDAP, ActiveDirectory, OpenID etc. It is up to the implementor of this interface to interact with the appropriate security
	 * service to determine if the given securityToken is valid. Reasons for a token to not be valid include but are not limited to, expired tokens, 
	 * incorrect tokens, not authenticated tokens etc.<br/>
	 * This method will be used by the provider in a DIRECT environment to authenticate/validate a consumer.
	 *     
	 * @param securityToken The token that shall be validated against a given security service such as LDAP, OAuth, Active Directory, etc.
	 * @param requestMetadata Metadata that has been sourced from a request. The environmentID property is always null because the token is
     *                        not yet authenticated and therefore the environment is not yet determined. 
	 * 
	 * @return TRUE if the token is known and valid to the security server and not expired. If a token is expired then FALSE should be returned.
	 */
	public boolean validate(String securityToken, RequestMetadata requestMetadata);

	/**
	 * This method may contact the security server which can be an OAuth server, and LDAP server a Active Directory etc. In return it will provide 
	 * information that relate to the securityToken such as: <br/>
	 * a) Information about the application and/or user of that securityToken (appUserInfo property populated in the TokenInfo) or<br/>
	 * b) Information about the SIF environment or SIF session the securityToken relates to. This would be the case for already existing SIF
	 *    Environments.<br/>
	 * Further an expire date might be set for the securityToken if the token has expired. If the securityToken does not expire then the 
	 * expire date is null in the returned TokenInfo object.<br/>
     * This method will be used by the provider in a DIRECT environment to get information about a consumer's security token.
	 * 
	 * @param securityToken The security token for which the TokenInfo shall be returned.
	 * @param requestMetadata Metadata that has been sourced from a request. The environmentID property is always null because the token is
     *                        not yet authenticated and therefore the environment is not yet determined. 
	 * 
	 * @return See Desc. It is expected that this method only returns either the environmentKey or the SIF environment ID or the SIF session token but
	 *         not all of these at the same time.
	 */
	public TokenInfo getInfo(String securityToken, RequestMetadata requestMetadata);
	
	/**
	 * This method may contact the security server which can be an OAuth server, and LDAP server a Active Directory etc. to generate a 
	 * security token based on the given 'coreInfo'. It is expected that any consumer or a provider in a BROKERED environment calls this 
	 * method to retrieve a security token which it will use as the authorisation token in all SIF requests to a provider or broker.
	 *  
	 * @param coreInfo Information about the consumer/provider that might be used to generate a security token by the external security service.
	 *                 In most cases it would at least need the application key.
	 * @param password It is very likely that some sort of password will be required to generate a security token.
	 * 
	 * @return A TokenInfo object which will have the 'token' property set (the security token). Optional the 'tokenExpiryDate' may be set
	 *         if the token has an expire date. If the 'tokenExpiryDate' is null it is assumed that the returned security token won't expire.
	 *         The returned token should only be a token without any authentication method as a prefix. For example the token may be
	 *         "ZjI2NThiNTktNDM1Yi00YThkLTlmNzYtYzI0MDBiNjY1NWMxOlBhc3N3b3JkMQ". It should not hold the authentication method such as 'Bearer'
	 *         (i.e. not look like this: "Bearer ZjI2NThiNTktNDM1Yi00YThkLTlmNzYtYzI0MDBiNjY1NWMxOlBhc3N3b3JkMQ"). The SIF3 Framework will
	 *         manage the authentication method.
	 */
	public TokenInfo createToken(TokenCoreInfo coreInfo, String password);
}
