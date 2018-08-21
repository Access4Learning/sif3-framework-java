/*
 * TestAppEnvTemplateService.java
 * Created: 21/12/2017
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

package sif3.common.test.persist.service;

import java.util.Date;
import java.util.List;

import au.com.systemic.framework.utils.DateUtils;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.model.PagingInfo;
import sif3.common.persist.model.SIF3Job;
import sif3.common.persist.model.SIF3JobEvent;
import sif3.common.persist.service.JobService;
import sif3.common.utils.UUIDGenerator;

/**
 * @author Joerg Huber
 *
 */
public class TestJobService extends ServiceBaseTest
{
    private static final String JOB_ID = "aaaaaaaa-bbbb-4ccc-dddd-eeeeeeeeeeee";
    
	private JobService service = new JobService();

	public void testGetJobTemplate(long templateID) throws Exception
	{
	    System.out.println("Job Template Info: "+service.getJobTemplate(templateID));
	}
	
    public void testGetJobTemplateForURLNameAndAdpater(String jobURLName, AdapterType adapterType) throws Exception
    {
        System.out.println("Job Template Info: "+service.getJobTemplateForAdapter(jobURLName, adapterType));
    }

    //----------------------//
    //-- JOB related Test --//
    //----------------------//
    public void testGetJob(long internalID) throws Exception
    {
        System.out.println("Job Info for internalID = "+internalID+":\n"+service.getJob(internalID));
    }
    
    public void testGetJobByUUID(String uuid, AdapterType adapterType) throws Exception
    {
        System.out.println("Job Info for uuid/adapterType = "+uuid+"/" + adapterType+":\n"+service.getJobByUUID(uuid, adapterType));
    }
    
    public void testCreateJob() throws Exception
    {
        SIF3Job job = new SIF3Job();
//        job.setJobID(JOB_ID);
        job.setJobID(UUIDGenerator.getUUID());
        job.setServiceName("rolloverStudent");
        job.setEnvironmentID("545c31ac-85c7-4360-bd63-24d425be1532");
        job.setAdapterType(AdapterType.ENVIRONMENT_PROVIDER.name());
        job.setJobXML("<job id=\""+job.getJobID()+"\"></job>");
        job.setFingerprint("3ae742dc-0c74-444f-b761-560a9fec5178");
        job.setTimeoutPeriod("P3D");
        
        System.out.println("Newly Created Job:\n"+service.createJob(job));
    }
    
    public void testUpdateJob() throws Exception
    {
        SIF3Job job = service.getJob(36);
        job.setZoneID("TestZone");
        job.setContextID("DEFAULT");
        job.setInternalID(36);
        
        System.out.println("Updated Job:\n"+service.updateJob(job));
    }

    public void testRemoveJob(long internalID) throws Exception
    {
        service.removeJob(internalID);
        System.out.println("Job with internalID = "+internalID+" removed.");        
    }

    //----------------------------//
    //-- JOB Event related Test --//
    //---------------------------//
    public void testGetJobEvent(long internalID) throws Exception
    {
        System.out.println("Job Event Info for internalID = "+internalID+":\n"+service.getJobEvent(internalID));
    }
    
    public void testGetJobEventsByUUID(String uuid, AdapterType adapterType, EventAction eventType, Boolean notYetPublished) throws Exception
    {
        System.out.println("Job Event List for uuid/adapterType/eventType/notYetPublised = "+uuid+"/" + adapterType+"/" + eventType+"/" + notYetPublished+":\n"+service.getJobEventsByUUID(uuid, adapterType, eventType, notYetPublished));
    }
    
    public void testCreateJobEvent() throws Exception
    {
        SIF3JobEvent jobEvent = new SIF3JobEvent();
        jobEvent.setJobID(JOB_ID);
//        jobEvent.setJobID(UUIDGenerator.getUUID());
        jobEvent.setEnvironmentID("545c31ac-85c7-4360-bd63-24d425be1532");
        jobEvent.setAdapterType(AdapterType.ENVIRONMENT_PROVIDER.name());
        jobEvent.setEventType("U");
        jobEvent.setJobXML("<job id=\""+jobEvent.getJobID()+"\"></job>");
        jobEvent.setToFingerPrintOnly(Boolean.TRUE);
        jobEvent.setFingerprint("3ae742dc-0c74-444f-b761-560a9fec5178");
        jobEvent.setServiceName("RollowverStudents");
        
        // The following should be overriden with defaults!
        jobEvent.setPublished(Boolean.TRUE); // Should be overriden to false!
        jobEvent.setPublishedDate(DateUtils.stringToDate("01/02/2018", DateUtils.DISP_LONG_YEAR));
        
        System.out.println("Newly Created Event:\n"+service.createJobEvent(jobEvent));
    }

    public void testMarkJobEventAsPublished(long internalID) throws Exception
    {
        service.markJobEventAsPublished(internalID);
        System.out.println("Job Event with internalID = "+internalID+" marked as updated.");        
    }

    public void testRemoveJobEvent(long internalID) throws Exception
    {
        service.removeJobEvent(internalID);
        System.out.println("Job Event with internalID = "+internalID+" removed.");        
    }

    public void testRemoveOlderThanJobEvents() throws Exception
    {
        String dateStr = "07/06/2018 11:00:00";
        Date olderThan = DateUtils.stringToDate(dateStr, DateUtils.DISP_DATE_TIME_SEC);
        service.removeJobEvents(olderThan, AdapterType.ENVIRONMENT_PROVIDER);
        System.out.println("Job Events older than "+dateStr+" removed.");        
    }

    public void testRetrieveJobEventsSince() throws Exception
    {
        String dateStr = "08/06/2018 00:00:00";
        Date changesSince = DateUtils.stringToDate(dateStr, DateUtils.DISP_DATE_TIME_SEC);
        List<SIF3JobEvent> jobEvents = service.retrieveJobEventsSince(changesSince, "RolloverStudents", "3ae742dc-0c74-444f-b761-560a9fec5178","auSchoolTestingZone", null, new PagingInfo(5, 2), AdapterType.ENVIRONMENT_PROVIDER);
        System.out.println("Job Events: "+jobEvents);        
    }

    public void testRemoveExpiredJobs() throws Exception
    {
        service.removeExpiredJobs(AdapterType.ENVIRONMENT_PROVIDER);
        System.out.println("Expired Jobs removed.");        
    }
    
    public void testRemoveJobsWithoutChangeSince() throws Exception
    {
        String dateStr = "07/06/2018 00:00:00";
        Date olderThan = DateUtils.stringToDate(dateStr, DateUtils.DISP_DATE_TIME_SEC);
        service.removeJobsWithoutChangeSince(olderThan, AdapterType.ENVIRONMENT_PROVIDER);
        System.out.println("Job with last updated older than "+dateStr+" removed.");        
    }

    public void testRemoveCreatedJobsWithoutChangeSince() throws Exception
    {
        String dateStr = "07/06/2018 00:00:00";
        Date olderThan = DateUtils.stringToDate(dateStr, DateUtils.DISP_DATE_TIME_SEC);
        service.removeCreatedJobsWithoutChangeSince(olderThan, AdapterType.ENVIRONMENT_PROVIDER);
        System.out.println("Job with creation date older than "+dateStr+" removed.");        
    }

    public void testRemoveExpiredAndStaleJobs() throws Exception
    {
        String dateStr = "07/06/2018 00:00:00";
        Date olderThan = DateUtils.stringToDate(dateStr, DateUtils.DISP_DATE_TIME_SEC);
        service.removeExpiredAndStaleJobs(olderThan, AdapterType.ENVIRONMENT_PROVIDER);
        System.out.println("Job expired or stale jobs older than "+dateStr+" removed.");        
    }

    
    public static void main(String[] args)
    {
		System.out.println("================================== Start TestJobService ===============================");
        try
        {
            TestJobService tester = new TestJobService();
//            tester.testGetJobTemplate(1);
//            tester.testGetJobTemplate(999);
//            tester.testGetJobTemplateForURLNameAndAdpater("RolloverStudents", AdapterType.ENVIRONMENT_PROVIDER);
//            tester.testGetJobTemplateForURLNameAndAdpater("TestNotExist", AdapterType.CONSUMER);
//            tester.testGetJobTemplateForURLNameAndAdpater(null, AdapterType.CONSUMER);
            
//            tester.testGetJob(1);
//            tester.testGetJobByUUID("123-456", AdapterType.CONSUMER);
//            tester.testGetJobByUUID("123-456", AdapterType.ENVIRONMENT_PROVIDER);
//            tester.testGetJobEvent(1);
//            tester.testGetJobEventsByUUID(JOB_ID, AdapterType.ENVIRONMENT_PROVIDER, null, null);
//            tester.testGetJobEventsByUUID(JOB_ID, AdapterType.ENVIRONMENT_PROVIDER, EventAction.UPDATE, null);
//            tester.testGetJobEventsByUUID(JOB_ID, AdapterType.ENVIRONMENT_PROVIDER, EventAction.UPDATE, Boolean.FALSE);
//            tester.testGetJobEventsByUUID(JOB_ID, AdapterType.ENVIRONMENT_PROVIDER, null, Boolean.FALSE);
//            tester.testCreateJobEvent();
//            tester.testMarkJobEventAsPublished(2);
//            tester.testRemoveJobEvent(3);
//            tester.testCreateJob();
//            tester.testUpdateJob();
//            tester.testRemoveJob(7);
//            tester.testRemoveOlderThanJobEvents();
//            tester.testRemoveExpiredJobs();
//            tester.testRemoveJobsWithoutChangeSince();
//            tester.testRemoveCreatedJobsWithoutChangeSince();
//            tester.testRemoveExpiredAndStaleJobs();
            tester.testRetrieveJobEventsSince();

            tester.shutdown();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
		System.out.println("================================== End TestJobService ===============================");
    }
}
