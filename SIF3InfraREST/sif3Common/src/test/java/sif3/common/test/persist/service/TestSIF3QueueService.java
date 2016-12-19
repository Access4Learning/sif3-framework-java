/*
 * TestSIF3QueueService.java
 * Created: 21/04/2014
 *
 * Copyright 2014 Systemic Pty Ltd
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

import sif3.common.CommonConstants;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.persist.model.SIF3Queue;
import sif3.common.persist.service.SIF3QueueService;

/**
 * @author Joerg Huber
 *
 */
public class TestSIF3QueueService extends ServiceBaseTest
{
	private SIF3QueueService service = new SIF3QueueService();

	private static final AdapterType ADAPTER_TYPE = AdapterType.CONSUMER;

	private static final String ENVIRONMENT_ID = "22d7a78a-cde3-4757-8ce3-db471ecd807a";
	private static final String QUEUE_ID       = "3a8dda78-7654-4abc-8cfd-db40912df1ac";
//	private static final String QUEUE_ID       = "ff8dd543-0954-476f-89fd-dd23912dfc23";
	private static final String MESSAGE_URI = "http://localhost:9080/SIF3InfraREST/sif3/queues/"+QUEUE_ID+"/messages";

	private void testGetQueue() throws Exception
	{
		System.out.println("Get queue with id = "+QUEUE_ID+": "+service.getQueue(QUEUE_ID, ADAPTER_TYPE));
	}
	
	private void testGetQueuesForEnvironment() throws Exception
	{
		System.out.println("Get queues for environment id = "+ENVIRONMENT_ID+": "+service.getQueuesForEnvironment(ENVIRONMENT_ID, ADAPTER_TYPE));
	}
	
	private void testGetQueuesByCriteria() throws Exception
	{
//		System.out.println("Get queues for Criteria: "+service.getQueuesByCriteria(ENVIRONMENT_ID, ADAPTER_TYPE, "TEST-JOERG", null, "StudentPersonals", "OBJECT"));
//		System.out.println("Get queues for Criteria: "+service.getQueuesByCriteria(ENVIRONMENT_ID, ADAPTER_TYPE, null, null, "StudentPersonals", "OBJECT"));
//		System.out.println("Get queues for Criteria: "+service.getQueuesByCriteria(ENVIRONMENT_ID, ADAPTER_TYPE, "TEST-JOERG", null, null, null));
		System.out.println("Get queues for Criteria: "+service.getQueuesByCriteria(ENVIRONMENT_ID, ADAPTER_TYPE, null, null, null, null));
	}

	private void testRemoveQueue() throws Exception
	{
		System.out.println("Remove queue with id = "+QUEUE_ID+": "+service.removeQueue(QUEUE_ID, ADAPTER_TYPE));		
	}

	private void testRemoveQueuesForEnvironment() throws Exception
	{
		System.out.println("Remove queues for environment with id = "+ENVIRONMENT_ID+": "+service.removeAllQueueForEnvironment(ENVIRONMENT_ID, ADAPTER_TYPE));		
	}

	private void testSaveQueue() throws Exception
	{
		SIF3Queue queue = new SIF3Queue();
		queue.setQueueID(QUEUE_ID);
		queue.setAdapterType(ADAPTER_TYPE.name());
		queue.setEnvironmentID(ENVIRONMENT_ID);
		queue.setQueueType(CommonConstants.QueuePollingType.IMMEDIATE.name());
		queue.setMessageURI(MESSAGE_URI);
		queue.setMaxConsumers(3);
		queue.setName("TestQueue-22");
		queue.setZoneID("TEST-JOERG");
//		queue.setServiceName("StudentPersonals");
//		queue.setServiceType("OBJECT");
		
		System.out.println("Created/Updated Queue = "+service.saveQueue(queue));		
	}

	public static void main(String[] args)
    {
		System.out.println("================================== Start TestSIF3QueueService ===============================");
		try
        {
			TestSIF3QueueService tester = new TestSIF3QueueService();
//			tester.testGetQueue();
//			tester.testGetQueuesForEnvironment();
//			tester.testRemoveQueue();
//			tester.testRemoveQueuesForEnvironment();
//			tester.testSaveQueue();
			tester.testGetQueuesByCriteria();
       
        	tester.shutdown();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
		System.out.println("================================== End TestSIF3QueueService ===============================");
    }
}
