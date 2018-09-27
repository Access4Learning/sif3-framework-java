/*
 * JobEventData.java
 * Created: 11 Sep 2018
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

package sif3.infra.rest.provider.functional;

import java.io.Serializable;

import sif3.infra.common.model.JobType;

/**
 * This is a helper POJO to manage the publishing of Job Events.
 * 
 * @author Joerg Huber
 */
public class JobEventData implements Serializable
{
    private static final long serialVersionUID = -1008848372047662458L;
    
    private long internalJobID = -1; // needed for updating job event table
    private boolean consumerEvent = true; // required to filter jobs for audit and non audit zones
    private JobType jobData = null; // actual job SIF Data Object.

    public long getInternalJobID()
    {
        return internalJobID;
    }
    
    public void setInternalJobID(long internalJobID)
    {
        this.internalJobID = internalJobID;
    }
    
    public boolean isConsumerEvent()
    {
        return consumerEvent;
    }
    
    public void setConsumerEvent(boolean consumerEvent)
    {
        this.consumerEvent = consumerEvent;
    }
    
    public JobType getJobData()
    {
        return jobData;
    }
    
    public void setJobData(JobType jobData)
    {
        this.jobData = jobData;
    }
    
    @Override
    public String toString()
    {
        return "JobEventData [internalJobID=" + internalJobID + ", consumerEvent=" + consumerEvent
                + ", jobData=" + jobData + "]";
    }
}
