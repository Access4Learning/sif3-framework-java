/*
 * SecurityServiceParameter.java
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

public class SecurityServiceParameter implements Serializable //, Comparable<SecurityServiceParameter>
{
    private static final long serialVersionUID = -7587054623306008525L;

    private long parameterID;
    private String parameterName;
    private String parameterValue;
    
    public SecurityServiceParameter()
    {
        super();
    }
    
    public long getParameterID()
    {
        return parameterID;
    }
    
    public void setParameterID(long parameterID)
    {
        this.parameterID = parameterID;
    }
    
    public String getParameterName()
    {
        return parameterName;
    }
    
    public void setParameterName(String parameterName)
    {
        this.parameterName = parameterName;
    }
    
    public String getParameterValue()
    {
        return parameterValue;
    }
    
    public void setParameterValue(String parameterValue)
    {
        this.parameterValue = parameterValue;
    }

    @Override
    public String toString()
    {
        return "SecurityServiceParameter [parameterID=" + parameterID + ", parameterName=" + parameterName
                + ", parameterValue=" + parameterValue + "]";
    }
}
