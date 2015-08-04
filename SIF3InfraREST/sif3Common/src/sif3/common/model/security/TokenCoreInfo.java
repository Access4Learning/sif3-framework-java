/*
 * TokenCoreInfo.java
 * Created: 14 Jul 2015
 *
 * Copyright 2015 Systemic Pty Ltd
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

package sif3.common.model.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import sif3.common.model.EnvironmentKey;

/**
 * This is a base class to hold security token metadata. It does not hold the actual security token or expire date thereof rather data about
 * the token. 
 * 
 * @author Joerg Huber
 *
 */
public class TokenCoreInfo implements Serializable
{
    private static final long serialVersionUID = 1602817747697949894L;
    
    // Data known about application and/or user.
    private EnvironmentKey appUserInfo = null;
    private String consumerName = null;
    private String dataModelNamespace = null;

    // Data known about existing SIF Environment and SIF Session Token
    private String environmentID = null;
    private String sessionToken = null;

    // Attribute value list with other information about security token.
    private Map<String, String> otherInfo = new HashMap<String, String>();

    public TokenCoreInfo()
    {
        this(null);
    }
    
    // Will initialise with the given info.
    public TokenCoreInfo(TokenCoreInfo coreInfo)
    {
        super();
        if (coreInfo != null)
        {
            setAppUserInfo(coreInfo.getAppUserInfo());
            setConsumerName(coreInfo.getConsumerName());
            setDataModelNamespace(coreInfo.getDataModelNamespace());
            setEnvironmentID(coreInfo.getEnvironmentID());
            setSessionToken(coreInfo.getSessionToken());
            setOtherInfo(coreInfo.getOtherInfo());
        }
    }

    /**
     * @return the appUserInfo
     */
    public EnvironmentKey getAppUserInfo()
    {
        return appUserInfo;
    }

    /**
     * @param appUserInfo the appUserInfo to set
     */
    public void setAppUserInfo(EnvironmentKey appUserInfo)
    {
        this.appUserInfo = appUserInfo;
    }

    /**
     * @return the consumerName
     */
    public String getConsumerName()
    {
        return consumerName;
    }

    /**
     * @param consumerName the consumerName to set
     */
    public void setConsumerName(String consumerName)
    {
        this.consumerName = consumerName;
    }

    /**
     * @return the dataModelNamespace
     */
    public String getDataModelNamespace()
    {
        return dataModelNamespace;
    }

    /**
     * @param dataModelNamespace the dataModelNamespace to set
     */
    public void setDataModelNamespace(String dataModelNamespace)
    {
        this.dataModelNamespace = dataModelNamespace;
    }

    /**
     * @return the environmentID
     */
    public String getEnvironmentID()
    {
        return environmentID;
    }

    /**
     * @param environmentID the environmentID to set
     */
    public void setEnvironmentID(String environmentID)
    {
        this.environmentID = environmentID;
    }

    /**
     * @return the sessionToken
     */
    public String getSessionToken()
    {
        return sessionToken;
    }

    /**
     * @param sessionToken the sessionToken to set
     */
    public void setSessionToken(String sessionToken)
    {
        this.sessionToken = sessionToken;
    }

    /**
     * @return the otherInfo
     */
    public Map<String, String> getOtherInfo()
    {
        return otherInfo;
    }

    /**
     * @param otherInfo the otherInfo to set
     */
    public void setOtherInfo(Map<String, String> otherInfo)
    {
        this.otherInfo = otherInfo;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "TokenCoreInfo [appUserInfo=" + appUserInfo + ", consumerName=" + consumerName
                + ", dataModelNamespace=" + dataModelNamespace + ", environmentID=" + environmentID
                + ", sessionToken=" + sessionToken + ", otherInfo=" + otherInfo + "]";
    }
}
