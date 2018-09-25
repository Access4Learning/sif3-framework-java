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

package sif3.infra.rest.test.quartz;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import sif3.infra.common.env.types.ProviderEnvironment;

/**
 * @author Joerg Huber
 *
 */
public class JobHouseKeeping implements Job
{
//    private String message = null;
    private Scheduler scheduler = null;
    
//    public JobHouseKeeping(String message)
//    {
//        try
//        {
//            JobDetail job = JobBuilder.newJob(JobHouseKeeping.class).withIdentity("job1", "group1").build();
//
//            CronTrigger trigger = TriggerBuilder.newTrigger()
//                    .withIdentity("trigger1", "group1")
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
//                    .build();
//
//                setScheduler(new StdSchedulerFactory().getScheduler());
//                getScheduler().start();
//                getScheduler().scheduleJob(job, trigger);
//
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//    }
    
    public void start(ProviderEnvironment env)
    {
//        setMessage(message);
        try
        {
            JobDetail job = JobBuilder.newJob(JobHouseKeeping.class).withIdentity("job1", "group1").build();
            JobDataMap dataMap = job.getJobDataMap();
            dataMap.put("ENV_DATA", env);

            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
                    .build();

                setScheduler(new StdSchedulerFactory().getScheduler());
                getScheduler().start();
                getScheduler().scheduleJob(job, trigger);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }        
    }
    
    public void shutdown()
    {
        System.out.println("Shutting down JobHouseKeeping...");
        try
        {
            if (scheduler != null)
            {
                scheduler.shutdown(true);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /* (non-Javadoc)
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext jobctx) throws JobExecutionException
    {
        JobKey jobKey = jobctx.getJobDetail().getKey();
        ProviderEnvironment env = (ProviderEnvironment)jobctx.getJobDetail().getJobDataMap().get("ENV_DATA");
        
        System.out.println("JobHouseKeeping says: " + jobKey + " executing at " + new Date()+". Number of Threads: "+ Thread.activeCount());
        System.out.println("Environment Data Available: " + env);
    }
    
    
//    public String getMessage()
//    {
//        return message;
//    }
//
//    public void setMessage(String message)
//    {
//        this.message = message;
//    }

    public Scheduler getScheduler()
    {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler)
    {
        this.scheduler = scheduler;
    }


}
