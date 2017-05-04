/*
 * AbstractSecurityService.java
 * Created: 15/12/2014
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
package sif3.common.security;

import java.util.HashMap;
import java.util.Map;

import au.com.systemic.framework.utils.AdvancedProperties;
import sif3.common.interfaces.SecurityService;
import sif3.common.model.RequestMetadata;
import sif3.common.model.security.TokenCoreInfo;
import sif3.common.model.security.TokenInfo;

/**
 * All custom security services that shall be used for 'Bearer' security must extend this abstract Security Service. This will enforce the
 * implementation of specific constructors that are used within the Security Factory to instantiate a given security service.<br/><br/>
 * 
 * This class is only intended to be used by providers to reconcile security tokens with an external security service. Consumer will use
 * the AbstractConsumerSecurityService instead.<br/><br/>
 * 
 * <b>Note</b><br/>
 * As of January 2015 SIF 3.0.1 does not officially specify any other security mechanism except 'Basic' and 'SIF_HMACSH256'. If a SIF 3
 * implementation using this framework chooses to use a custom security service such as OAuth and therefore implements such a service and
 * links it with this framework, then this is done at the developper's own risk that it may not integrate with other SIF 3 systems/services. 
 * 
 * @author Joerg Huber
 *
 */
public abstract class AbstractSecurityService implements SecurityService
{
    private static final String PROP_PREFIX = "security.service.property.";
  
	/* The consumer's properties. Used to access the property file within the security service implementation. */
	private AdvancedProperties properties = null;
	private Map<String, String> securityServiceParameters = null;

    /**
	 * Only a provider needs to implement this method. If the security service is used by a consumer it might 'null out' this method (i.e. return 
	 * false).<br/><br/>
	 * 
	 * This method return true if the given 'securityToken' is a valid token for the given security service. The security service can be any service
	 * such as OAuth, LDAP, ActiveDirectory, OpenID etc. It is up to the implementor of this interface to interact with the appropriate security
	 * service to determine if the given securityToken is valid. Reasons for a token to not be valid include but are not limited to, expired tokens, 
	 * incorrect tokens, not authenticated tokens etc.
	 *     
	 * @param securityToken The token that shall be validated against a given security service such as LDAP, OAuth, Active Directory, etc.
     * @param requestMetadata Metadata that has been sourced from a request. The environmentID property is always null because the token is
     *                        not yet authenticated and therefore the environment is not yet determined. 
	 * 
	 * @return TRUE if the token is known and valid to the security server and not expired. If a token is expired then FALSE should be returned.
	 */
	public abstract boolean validateToken(String securityToken, RequestMetadata requestMetadata);
  
  	/**
	 * Only a provider needs to implement this method. If the security service is used by a consumer it might 'null out' this method (i.e. return 
	 * null).<br/><br/>
	 * 
  	 * This method may contact the security server which can be an OAuth server, and LDAP server a Active Directory etc. In return it will provide 
  	 * information that relate to the securityToken such as: <br/>
  	 * a) Information about the application and/or user of that securityToken (appUserInfo property populated in the TokenInfo) or
  	 * b) Information about the SIF environment or SIF session the securityToken relates to. This would be the case for already existing SIF 
  	 *    Environments.
  	 * Further an expire date might be set for the securityToken if the token has expired. If the securityToken does not expire then the 
  	 * expire date is null in the returned TokenInfo object.
  	 * 
  	 * @param securityToken The security token for which the TokenInfo shall be returned.
     * @param requestMetadata Metadata that has been sourced from a request. The environmentID property is always null because the token is
     *                        not yet authenticated and therefore the environment is not yet determined.
  	 * 
  	 * @return See Desc. It is expected that this method only returns either the environmentKey or the SIF environment ID or the SIF session token but
  	 *         not all of these at the same time.
  	 */
  	public abstract TokenInfo getTokenInfo(String securityToken, RequestMetadata requestMetadata);

  	/**
	 * All consumers should implement this method. Providers only need to implement this method if the participate in a BROKERED environment.
	 * If the provider is a DIRECT provider then it might 'null out' this method (i.e. return null).<br/><br/>
	 * 
  	 * This method may contact the security server which can be an OAuth server, and LDAP server an Active Directory etc. It will then
  	 * retrieve a security token and optionally a expire date for that token based on the 'coreInfo' and optionally the 'password' given
  	 * to this method. In return the TokenInfo object which will have the 'token' property set (the security token). Optional the 
  	 * 'tokenExpiryDate' may be set if the token has an expire date. If the 'tokenExpiryDate' is null it is assumed that the returned 
  	 * security token won't expire. For additional details on the returned token 
  	 * @see sif3.common.interfaces.SecurityService#createToken(sif3.common.model.security.TokenCoreInfo, java.lang.String).
  	 * 
     * @param coreInfo Information about the consumer/provider that might be used to generate a security token by the external security service.
     *                 In most cases it would at least need the application key.
     * @param password It is very likely that some sort of password will be required to generate a security token.
  	 * 
  	 * @return @see sif3.common.interfaces.SecurityService#createToken(sif3.common.model.security.TokenCoreInfo, java.lang.String).
  	 */
  	public abstract TokenInfo generateToken(TokenCoreInfo coreInfo, String password);

  	
	/**
	 * This is the constructor called by the framework to instantiate a concrete security service implementation.
	 *
	 * @param properties Properties file for the given adapter.
	 * @param securityServiceParameters Name/Value (name=key) map of parameters that are listed in the SIF3_SEC_SERVICE_PARAM table for
	 *                                  the given security service.
	 */
	public AbstractSecurityService(AdvancedProperties properties, Map<String, String> securityServiceParameters)
	{
		super();
		this.properties = properties;
		this.securityServiceParameters = securityServiceParameters;

	}

	public AdvancedProperties getServiceProperties()
	{
		return properties;
	}
  
    public Map<String, String> getSecurityServiceParameters()
    {
        return securityServiceParameters;
    }

	/*-----------------------*/
	/*-- Interface Methods --*/
	/*-----------------------*/

	@Override
	public final boolean validate(String securityToken, RequestMetadata requestMetadata)
	{
		return validateToken(securityToken, requestMetadata);
	}

	@Override
	public final TokenInfo getInfo(String securityToken, RequestMetadata requestMetadata)
	{
		return getTokenInfo(securityToken, requestMetadata);
	}

    @Override
    public final TokenInfo createToken(TokenCoreInfo coreInfo, String password)
    {
        if (coreInfo != null)
        {
            if (coreInfo.getOtherInfo() == null)
            {
                coreInfo.setOtherInfo(new HashMap<String, String>());
            }
            AdvancedProperties props = getServiceProperties();
            for (Object key : props.getProperties().keySet())
            {
                String propName = (String)key;
                if (propName.startsWith(PROP_PREFIX))
                {
                    coreInfo.getOtherInfo().put(propName.replace(PROP_PREFIX, ""), props.getPropertyAsString(propName, null));
                }
            }
        }        
        return generateToken(coreInfo, password);
    }
}
