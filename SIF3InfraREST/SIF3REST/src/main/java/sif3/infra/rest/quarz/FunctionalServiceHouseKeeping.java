/*
 * JobHouseKeeping.java
 * Created: 21 Jun 2018
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

package sif3.infra.rest.quarz;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.DateUtils;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.persist.service.JobService;
import sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType;
import sif3.infra.common.env.types.ProviderEnvironment;

/**
 * @author Joerg Huber
 *
 */
public class FunctionalServiceHouseKeeping extends QuartzBase implements Job
{
    private static final long serialVersionUID = 9162279022523606724L;
    
    public static final String JOB_NAME = "FunctionalServiceHouseKeeping";
    public static final String TRIGGER_NAME = "FunctionalServiceHouseKeeping-Trigger";
    public static final String ENV_PARAM_NAME = "ENV_DATA";
    
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private Scheduler scheduler = null;
    
    /**
     * This method schedules the FunctionalServiceHouseKeeping Quartz Job based on the data given in the 'env' parameter.
     * 
     * @param env Holds data required to schedule and execute the Quartz job.
     */
    public boolean start(ProviderEnvironment env)
    {
        try
        {
            JobDetail job = JobBuilder.newJob(FunctionalServiceHouseKeeping.class).withIdentity(JOB_NAME, QuartzBase.PROVIDER_GROUP).build();

            // Store the provider environment setup in the job.
            job.getJobDataMap().put(ENV_PARAM_NAME, env);

            // Build the Cron Trigger
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(TRIGGER_NAME, QuartzBase.PROVIDER_GROUP)
                    .withSchedule(CronScheduleBuilder.cronSchedule(env.getJobHousekeepingCron()))
                    .build();

            setScheduler(new StdSchedulerFactory().getScheduler());
            getScheduler().start();
            
            // Pass the Quartz Job with its parameters and the trigger to the Quartz Scheduler.
            getScheduler().scheduleJob(job, trigger);
            return true;
        }
        catch (Exception ex)
        {
            logger.error("Failed to schedule FunctionalServiceHouseKeeping Job: "+ex.getMessage());
            return false;
        }        
    }
    
    public void shutdown()
    {
        logger.debug("Shutting down JobHouseKeeping...");
        try
        {
            if (scheduler != null)
            {
                scheduler.shutdown(true);
            }
        }
        catch (Exception ex)
        {
            logger.error("Failed to properly shut down the FunctionalServiceHouseKeeping Job: "+ex.getMessage());
        }
    }
    
    /* (non-Javadoc)
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext jobctx) throws JobExecutionException
    {
        JobKey jobKey = jobctx.getJobDetail().getKey();
        logger.debug("JobHouseKeeping says: " + jobKey + " executing at " + new Date()+". Number of Threads: "+ Thread.activeCount());
        ProviderEnvironment env = (ProviderEnvironment)jobctx.getJobDetail().getJobDataMap().get(ENV_PARAM_NAME);
        
        try
        {
            JobService svc = new JobService();
            
            // Determine the date for job events to remove
            Date now = new Date();
            Date oldEventDate = DateUtils.getDateWithAddedDays(now, -env.getJobEventKeepDays());
            svc.removeJobEvents(oldEventDate, getAdapterType(env));
            
            // Determine the date for "stale" jobs.
            Date staleJobDate = DateUtils.getDateWithAddedDays(now, -env.getJobKeepDays());
            svc.removeExpiredAndStaleJobs(staleJobDate, getAdapterType(env));
        }
        catch (Exception ex)
        {
            logger.error("Failed to run all Functional Services Housekeeping jobs. Error reported: "+ex.getMessage(), ex);
        }
    }

    private Scheduler getScheduler()
    {
        return scheduler;
    }

    private void setScheduler(Scheduler scheduler)
    {
        this.scheduler = scheduler;
    }
    
    private AdapterType getAdapterType(ProviderEnvironment env)
    {
        return env.getEnvironmentType() == EnvironmentType.BROKERED ? AdapterType.PROVIDER : AdapterType.ENVIRONMENT_PROVIDER;
    }
}
