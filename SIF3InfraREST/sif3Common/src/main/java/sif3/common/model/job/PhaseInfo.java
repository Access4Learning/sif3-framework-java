/*
 * PhaseInfo.java
 * Created: 19 Jun 2018
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

/**
 * @author Joerg Huber
 *
 */
public class PhaseInfo implements Serializable
{
    private static final long serialVersionUID = 5445852424920539917L;
    
    private String jobID = null;
    private String phaseName = null;
    
    public PhaseInfo()
    {
        this(null, null);
    }

    public PhaseInfo(String jobID)
    {
        this(jobID, null);
    }
    
    public PhaseInfo(String jobID, String phaseName)
    {
        super();
        setJobID(jobID);
        setPhaseName(phaseName);
    }

    
    public String getJobID()
    {
        return jobID;
    }
    
    public void setJobID(String jobID)
    {
        this.jobID = jobID;
    }
    
    public String getPhaseName()
    {
        return phaseName;
    }
    
    public void setPhaseName(String phaseName)
    {
        this.phaseName = phaseName;
    }

    @Override
    public String toString()
    {
        return "PhaseInfo [jobID=" + jobID + ", phaseName=" + phaseName + "]";
    }

}
