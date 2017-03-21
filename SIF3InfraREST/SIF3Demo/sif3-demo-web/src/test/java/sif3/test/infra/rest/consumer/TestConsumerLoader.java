/*
 * TestConsumerLoader.java
 * Created: 10/05/2014
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

package sif3.test.infra.rest.consumer;

import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.rest.consumer.ConsumerLoader;

/**
 * @author Joerg Huber
 *
 */
public class TestConsumerLoader
{
	// Local
//	private static final String CONSUMER_ID = "StudentConsumer";

	// Broker
	private static final String CONSUMER_ID = "BrokeredAttTrackerConsumer";
//	private static final String CONSUMER_ID = "QueueTestConsumer";

	
	  public void stopService(String serviceID)
	  {
	    System.out.println("Shutting down "+serviceID);
	    ConsumerLoader.shutdown();
	  }
	  
//	  public void startService(final String serviceID, String consumerPropFileName) throws Exception
//	  {
//	    System.out.println(serviceID+" startService() called...");
//	    System.out.println(serviceID+": Installing shutdown hook");
//	    final TestConsumerLoader tmpSvc = this;
//	    Runtime.getRuntime().addShutdownHook(new Thread()
//	    {
//	      public void run()
//	      {
//	        tmpSvc.stopService(serviceID);
//	      }
//	    });
//
//	    if (ConsumerLoader.initialise(consumerPropFileName))
//	    {
//	      System.out.println(serviceID+"initialised successfully.");
//	    }
//
//	    System.out.println(serviceID+" is running (Press Ctrl-C to stop)");
//	  }
	
	
	public static void main(String[] args)
	{
		System.out.println("Start Testing TestConsumerLoader...");

		if (ConsumerLoader.initialise(CONSUMER_ID))
		{
			System.out.println("Consumer loaded successfully. Environment Data:\n"+ConsumerEnvironmentManager.getInstance().getEnvironmentInfo());
		}
		
        // Put this agent to a blocking wait.....
        try
        {
            Object semaphore = new Object();
            synchronized (semaphore) // this will block until CTRL-C is pressed.
            {
                System.out.println("==================================================\nTestConsumerLoader is running (Press Ctrl-C to stop)\n==================================================");
                semaphore.wait();
            }
        }
        catch (Exception ex)
        {
            System.out.println("Blocking wait in TestConsumerLoader interrupted: " + ex.getMessage());
            ex.printStackTrace();
        }
        
		ConsumerLoader.shutdown();
		System.out.println("End Testing TestConsumerLoader.");
	}
}
