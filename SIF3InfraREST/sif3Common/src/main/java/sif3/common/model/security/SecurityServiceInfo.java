/*
 * SecuirtyServiceInfo.java
 * Created: 13 Apr 2017
 *
 * Copyright 2017 Systemic Pty Ltd
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

import sif3.common.CommonConstants.AuthenticationType;

/**
 * @author Joerg Huber
 *
 */
public class SecurityServiceInfo extends SecurityServiceKey implements Serializable
{
    private static final long serialVersionUID = -2826196563878296341L;

    private String httpHeaderValue = null;
    private String xmlValue = null;
    private boolean twoPhase = Boolean.FALSE;
    private transient AuthenticationType authenticationType = AuthenticationType.Other;
    
    public SecurityServiceInfo()
    {
        this(null, null, null, null, Boolean.FALSE, AuthenticationType.Other);
    }

    public SecurityServiceInfo(String authenticationMethod, String adapterType, String httpHeaderValue, String xmlValue, boolean twoPhase, AuthenticationType authenticationType)
    {
        super(authenticationMethod, adapterType);
        setHttpHeaderValue(httpHeaderValue);
        setXmlValue(xmlValue);
        setTwoPhase(twoPhase);
        this.authenticationType = authenticationType;
    }

    public String getHttpHeaderValue()
    {
        return httpHeaderValue;
    }
    
    public void setHttpHeaderValue(String httpHeaderValue)
    {
        this.httpHeaderValue = httpHeaderValue;
    }
    
    public String getXmlValue()
    {
        return xmlValue;
    }
    
    public void setXmlValue(String xmlValue)
    {
        this.xmlValue = xmlValue;
    }
    
    public boolean getTwoPhase()
    {
        return twoPhase;
    }
    
    public void setTwoPhase(boolean twoPhase)
    {
        this.twoPhase = twoPhase;
    }
    
    public AuthenticationType getAuthenticationType()
    {
        return authenticationType;
    }

    @Override
    public String toString()
    {
        return "SecurityServiceInfo [httpHeaderValue=" + httpHeaderValue + ", xmlValue=" + xmlValue
                + ", twoPhase=" + twoPhase + ", authenticationType=" + authenticationType + "]";
    }

}
