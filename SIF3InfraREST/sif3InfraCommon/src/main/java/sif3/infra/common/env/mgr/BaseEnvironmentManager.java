/*
 * BaseEnvironmentManager.java
 * Created: 9 Jan 2018
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

package sif3.infra.common.env.mgr;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.persist.model.JobTemplate;
import sif3.infra.common.env.ops.AdapterBaseEnvStoreOperations;
import sif3.infra.common.interfaces.JobManager;
import sif3.infra.common.model.JobType;

/**
 * This class has a few hand and common methods used by all Environment Manager classes.
 * 
 * @author Joerg Huber
 *
 */
public class BaseEnvironmentManager
{
    /* The Job Manager that is applicable for this Environment Manager */
    private JobManager jobManager = null;

    public JobManager getJobManager()
    {
        return jobManager;
    }

    protected void setJobManager(JobManager jobManager)
    {
        this.jobManager = jobManager;
    }

    protected JobType getJobTemplate(String urlName, AdapterType adapterType, AdapterBaseEnvStoreOperations envStoreOps)
    {
        JobTemplate jobTemplate = getJobManager().getJobTemplateInfo(urlName);
        
        if (jobTemplate != null)
        {
            return envStoreOps.loadJobTemplateData(jobTemplate.getTemplateFileName(), getJobManager().getAdapterType(), getJobManager().getEnvironmentType());
        }
        
        // If we get here then no Job Template or Job Data has been found. Error already logged.
        return null;
    }
}
