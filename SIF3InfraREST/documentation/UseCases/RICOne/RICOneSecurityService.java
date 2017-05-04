/*
 * RICOneSecurityService.java
 * Created: 26 Jul 2016
 *
 * Copyright 2016 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License. 
 */

package systemic.sif3.demo.security;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import riconeapi.common.Authenticator;
import riconeapi.models.authentication.DecodedToken;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.RequestMetadata;
import sif3.common.model.security.TokenCoreInfo;
import sif3.common.model.security.TokenInfo;
import sif3.common.security.AbstractSecurityService;

/**
 * @author Joerg Huber
 *
 */
public class RICOneSecurityService extends AbstractSecurityService
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final static String AOUTH_URL_PROP_NAME = "ricOne.authUrl";
    /**
     * @param properties
     */
    public RICOneSecurityService(AdvancedProperties properties, Map<String, String> securityServiceParameters)
    {
        super(properties, securityServiceParameters);
        if (logger.isDebugEnabled())
        {
            logger.debug("RICOneSecurityService Constructor called with property: " + getServiceProperties());
        }
        
    }

    /* 
     * Because we use this class from a consumer only we can null out the method.
     * 
     * (non-Javadoc)
     * @see sif3.common.security.AbstractSecurityService#validateToken(java.lang.String, sif3.common.model.RequestMetadata)
     */
    @Override
    public boolean validateToken(String securityToken, RequestMetadata requestMetadata)
    {
        return false;
    }

    /* (non-Javadoc)
     * @see sif3.common.security.AbstractSecurityService#getTokenInfo(java.lang.String, sif3.common.model.RequestMetadata)
     */
    @Override
    public TokenInfo getTokenInfo(String securityToken, RequestMetadata requestMetadata)
    {
        Authenticator ricOneAuthenticator = Authenticator.getInstance();
        DecodedToken decodedToken = ricOneAuthenticator.getDecodedToken(securityToken);
        
        TokenInfo tokenInfo = new TokenInfo(securityToken, decodedToken.getExp());
        tokenInfo.setAppUserInfo(new EnvironmentKey(null, decodedToken.getApplication_id()));
        
        return tokenInfo;
    }

    /* 
     * Contacts the RICOne OAuth server and retrieves a new token. It then extracts info for this token an places it into the
     * returned TokenInfo object
     * 
     * (non-Javadoc)
     * @see sif3.common.security.AbstractSecurityService#generateToken(sif3.common.model.security.TokenCoreInfo, java.lang.String)
     */
    @Override
    public TokenInfo generateToken(TokenCoreInfo coreInfo, String password)
    {
        Authenticator ricOneAuthenticator = null;
        
        logger.debug("Generate a Token from OAuth Server...");
        try
        {
            // Create a token from the RICOne OAuth Server
            ricOneAuthenticator = new Authenticator(coreInfo.getOtherInfo().get(AOUTH_URL_PROP_NAME), 
                                                    coreInfo.getAppUserInfo().getApplicationKey(), 
                                                    password);

            // Lets convert the token to something useful
            return getTokenInfo(ricOneAuthenticator.getToken(), null);
        }
        catch (Exception ex)
        {
            logger.error("Failed to create Authenticator to RICOne OAuth Server (No security token generated): "+ex.getMessage(), ex);
            return null;
        }

    }

}
