/*
 * ExternalSecurityService.java
 * Created: 31/03/2017
 *
 * Copyright 2017 Systemic Pty Ltd
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
package sif3.common.persist.model;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants.AuthenticationType;
import sif3.common.model.security.SecurityServiceInfo;
import sif3.common.security.AbstractSecurityService;

public class ExternalSecurityService extends SecurityServiceInfo implements Serializable
{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final long serialVersionUID = -5309104809529450455L;

    private long externalSecurityServiceID;
    private String implementationClass = null;
    private Collection<SecurityServiceParameter> parameters = new ArrayList<SecurityServiceParameter>();
    
    // The properties below are runtime properties. They are not read or maintained in the DB!
    private transient Constructor<?> constructor = null;

    public ExternalSecurityService()
    {
        this(null, null, null, null, Boolean.FALSE, AuthenticationType.Other);
    }

    public ExternalSecurityService(String authenticationMethod, String adapterType, String httpHeaderValue, String xmlValue, boolean twoPhase, AuthenticationType authenticationType)
    {
        super(authenticationMethod, adapterType, httpHeaderValue, xmlValue, twoPhase, authenticationType);
    }
    
    public ExternalSecurityService(SecurityServiceInfo securityServiceInfo)
    {
        this(securityServiceInfo.getAuthenticationMethod(), securityServiceInfo.getAdapterType(), securityServiceInfo.getHttpHeaderValue(), securityServiceInfo.getXmlValue(), securityServiceInfo.getTwoPhase(), securityServiceInfo.getAuthenticationType());
    }
    
    public long getExternalSecurityServiceID()
    {
        return externalSecurityServiceID;
    }

    public void setExternalSecurityServiceID(long externalSecurityServiceID)
    {
        this.externalSecurityServiceID = externalSecurityServiceID;
    }

    public String getImplementationClass()
    {
        return implementationClass;
    }

    public void setImplementationClass(String implementationClass)
    {
        if (StringUtils.notEmpty(implementationClass)) // we found a security class. => gather info about it.
        {
            this.implementationClass = implementationClass;
            Class<?> clazz;
            try
            {
                clazz = Class.forName(implementationClass);
                this.constructor = clazz.getConstructor(new Class[] { AdvancedProperties.class, Map.class });
            }
            catch (Exception ex)
            {
                logger.error("Failed to find or Configure Class "+implementationClass+" as listed in the SIF3_EXT_SECURITY_SERVICE: "+ex.getMessage(), ex);
            }
        }
        else
        {
            this.implementationClass = null;
        }
    }

    public Collection<SecurityServiceParameter> getParameters()
    {
        return parameters;
    }

    public void setParameters(Collection<SecurityServiceParameter> parameters)
    {
        this.parameters = parameters;
    }

    public Constructor<?> getConstructor()
    {
        return constructor;
    }
    
    public AbstractSecurityService getSecurityService(AdvancedProperties properties)
    {
        try
        {
            Map<String, String> serviceParams = new HashMap<String, String>();
            if (getParameters()!= null)
            {
                for (SecurityServiceParameter param : getParameters())
                {
                    serviceParams.put(param.getParameterName(), param.getParameterValue());
                }
            }
            
            Object classObj = getConstructor().newInstance(new Object[] { properties, serviceParams });
        
            if (classObj instanceof AbstractSecurityService)
            {
                return (AbstractSecurityService) classObj;
            }
            else
            {
                logger.error("The class " + classObj.getClass().getSimpleName() + " does not extend AbstractSecurityService. This adapter cannot use this security service implementation.");
                return null;
            }
        }
        catch (Exception ex)
        {
            logger.error("Failed to create an instance of the Class "+getImplementationClass()+": " + ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public String toString()
    {
        return "ExternalSecurityService [externalSecurityServiceID=" + externalSecurityServiceID
                + ", implementationClass=" + implementationClass + ", parameters=" + parameters
                + ", constructor=" + constructor + ", toString()=" + super.toString() + "]";
    }
}
