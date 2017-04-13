/*
 * SecurityServiceKey.java
 * Created: 4 Apr 2017
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

/**
 * This class is a simple POJO that has the "key" information that identifies a external security service. It can be used to build
 * hash related data structures.
 * 
 * @author Joerg Huber
 *
 */
public class SecurityServiceKey implements Serializable
{
    private static final long serialVersionUID = 7603992349542893735L;
    
    private String authenticationMethod = null;
    private String adapterType = null;
          
    public SecurityServiceKey()
    {
        this(null, null);
    }

    public SecurityServiceKey(String authenticationMethod, String adapterType)
    {
        super();
        setAuthenticationMethod(authenticationMethod);
        setAdapterType(adapterType);
    }
    
    public String getAuthenticationMethod()
    {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(String authenticationMethod)
    {
        this.authenticationMethod = authenticationMethod != null ? authenticationMethod.toLowerCase() : null;
    }

    public String getAdapterType()
    {
        return adapterType;
    }

    public void setAdapterType(String adapterType)
    {
        this.adapterType = adapterType != null ? adapterType.toLowerCase() : null;
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adapterType == null) ? 0 : adapterType.hashCode());
        result = prime * result + ((authenticationMethod == null) ? 0 : authenticationMethod.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        SecurityServiceKey other = (SecurityServiceKey) obj;
        if (adapterType == null)
        {
            if (other.adapterType != null)
            {
                return false;
            }
        }
        else if (!adapterType.equals(other.adapterType))
        {
            return false;
        }
        if (authenticationMethod == null)
        {
            if (other.authenticationMethod != null)
            {
                return false;
            }
        }
        else if (!authenticationMethod.equals(other.authenticationMethod))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "SecurityServiceKey [authenticationMethod=" + authenticationMethod + ", adapterType="
                + adapterType + "]";
    }
}
