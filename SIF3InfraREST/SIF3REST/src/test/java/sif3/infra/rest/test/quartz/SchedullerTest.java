/*
 * SchedullerTest.java
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

import sif3.infra.common.env.types.AdapterEnvironmentStore;
import sif3.infra.common.env.types.ProviderEnvironment;

/**
 * @author Joerg Huber
 *
 */
public class SchedullerTest
{
    private static final String PROP_FILE_NAME = "StudentProvider";
    
    public static void main(String[] args)
    {
        
        try
        {
            AdapterEnvironmentStore envStore =  new AdapterEnvironmentStore(PROP_FILE_NAME);
            JobHouseKeeping houseKeeper = new JobHouseKeeping();
            houseKeeper.start((ProviderEnvironment)envStore.getEnvironment());
            Thread.sleep(60L * 1000L); // run for one minute
            houseKeeper.shutdown();
//            JobDetail job = JobBuilder.newJob(JobHouseKeeping.class).withIdentity("job1", "group1").build();
//
//            CronTrigger trigger = TriggerBuilder.newTrigger()
//                    .withIdentity("trigger1", "group1")
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
//                    .build();
//
//                Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//                scheduler.start();
//                scheduler.scheduleJob(job, trigger);
//                
//                Thread.sleep(60L * 1000L); // run for one minute
//                
//                scheduler.shutdown(true);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
