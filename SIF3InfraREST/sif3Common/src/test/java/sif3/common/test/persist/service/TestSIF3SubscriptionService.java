/*
 * TestSIF3SubscriptionService.java
 * Created: 22/04/2014
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

import sif3.common.CommonConstants.AdapterType;
import sif3.common.persist.model.SIF3Subscription;
import sif3.common.persist.service.SIF3SubscriptionService;

/**
 * @author Joerg Huber
 *
 */
public class TestSIF3SubscriptionService extends ServiceBaseTest
{
	private SIF3SubscriptionService service = new SIF3SubscriptionService();

	private static final AdapterType ADAPTER_TYPE = AdapterType.CONSUMER;
	private static final String ENVIRONMENT_ID  = "22d7a78a-cde3-4757-8ce3-db471ecd807a";
//	private static final String QUEUE_ID        = "3a8dda78-7654-4abc-8cfd-db40912df1ac";
	private static final String QUEUE_ID        = "ff8dd543-0954-476f-89fd-dd23912dfc23";
//	private static final String SUBSCRIPTION_ID = "9087aa45-ddff-4908-f3e4-dd40098bba31";
//	private static final String SUBSCRIPTION_ID = "10fe3a23-874a-4097-dfe4-7360e98bba6c";
	private static final String SUBSCRIPTION_ID = "ee34ad25-09ed-44ee-bc45-908eda235cb4";

    public void testGetSubscription() throws Exception
    {
    	System.out.println("Subscription for ID = "+SUBSCRIPTION_ID+": "+service.getSubscription(SUBSCRIPTION_ID, ADAPTER_TYPE));
    }

    public void testGetSubscriptionsForQueue() throws Exception
    {
    	System.out.println("Subscription for Queue ID = "+QUEUE_ID+": "+service.getSubscriptionsForQueue(QUEUE_ID, ADAPTER_TYPE));
    }

    public void testGetSubscriptionsForEnvironment() throws Exception
    {
    	System.out.println("Subscription for Environment ID = "+ENVIRONMENT_ID+": "+service.getSubscriptionsForEnvironment(ENVIRONMENT_ID, ADAPTER_TYPE));
    }
	
    public void tesRemoveSubscription() throws Exception
    {
		System.out.println("Remove subscription with id = "+SUBSCRIPTION_ID+": "+service.removeSubscription(SUBSCRIPTION_ID, ADAPTER_TYPE));    	
    }

    public void testRemoveAllSubscriptionsForQueue() throws Exception
    {
    	System.out.println("Remove Subscriptions for Queue ID = "+QUEUE_ID+": "+service.removeAllSubscriptionsForQueue(QUEUE_ID, ADAPTER_TYPE));
    }

    public void testRemoveAllSubscriptionsForEnvironment() throws Exception
    {
    	System.out.println("Remove Subscriptions for Environment ID = "+ENVIRONMENT_ID+": "+service.removeAllSubscriptionsForEnvironment(ENVIRONMENT_ID, ADAPTER_TYPE));
    }

    public void testSaveSubscription() throws Exception
    {
    	SIF3Subscription subscr = new SIF3Subscription();
    	subscr.setSubscriptionID(SUBSCRIPTION_ID);
    	subscr.setAdapterType(ADAPTER_TYPE.name());
//    	subscr.setZoneID("Joerg-WA-TEST");
    	subscr.setZoneID("WA-TEST");
//    	subscr.setServiceName("StudentPersonals");
    	subscr.setServiceName("SchoolInfos");
    	subscr.setQueueID(QUEUE_ID);
//    	subscr.setQueueID("1234");
    	subscr.setServiceType("OBJECT");
    	
		System.out.println("Created/Updated Subscription = "+service.saveSubscription(subscr));		
    }
    
    
    public static void main(String[] args)
    {
		System.out.println("================================== Start TestSIF3SubscriptionService ===============================");
		try
        {
			TestSIF3SubscriptionService tester = new TestSIF3SubscriptionService();
			tester.testGetSubscription();
//			tester.testGetSubscriptionsForEnvironment();
//			tester.tesRemoveSubscription();
//			tester.testRemoveAllSubscriptionsForQueue();
//			tester.testRemoveAllSubscriptionsForEnvironment();
//			tester.testSaveSubscription();
//			tester.testGetSubscriptionsForQueue();
       
        	tester.shutdown();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
		System.out.println("================================== End TestSIF3SubscriptionService ===============================");
    }

}
