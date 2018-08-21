/*
 * JobRequestParameter.java
 * Created: 22 Feb 2018
 *
 * Copyright 2018 Systemic Pty Ltd
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

package sif3.common.model.job;

import java.io.Serializable;
import java.util.List;

import sif3.common.model.AttributeValue;

/**
 * @author Joerg Huber
 *
 */
public class JobCreateRequestParameter implements Serializable
{
    private static final long serialVersionUID = -2397727515320241805L;
    
    private String jobID = null;
    private String initPhaseName = null;
    private List<AttributeValue> initParams = null;
    
    public JobCreateRequestParameter()
    {
        this(null, null);
    }

    public JobCreateRequestParameter(String initPhaseName, List<AttributeValue> initParams)
    {
        super();
        setInitParams(initParams);
        setInitPhaseName(initPhaseName);
    }
    
    public String getJobID()
    {
        return jobID;
    }

    public void setJobID(String jobID)
    {
        this.jobID = jobID;
    }

     public String getInitPhaseName()
    {
        return initPhaseName;
    }
    
    public void setInitPhaseName(String initPhaseName)
    {
        this.initPhaseName = initPhaseName;
    }
    
    public List<AttributeValue> getInitParams()
    {
        return initParams;
    }
    
    public void setInitParams(List<AttributeValue> initParams)
    {
        this.initParams = initParams;
    }
    
    @Override
    public String toString()
    {
        return "JobCreateRequestParameter [jobID=" + jobID + ", initPhaseName=" + initPhaseName
                + ", initParams=" + initParams + "]";
    }
}
