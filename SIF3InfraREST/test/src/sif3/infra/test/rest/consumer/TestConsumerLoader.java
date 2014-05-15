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

package sif3.infra.test.rest.consumer;

import sif3.infra.rest.consumer.ConsumerLoader;

/**
 * @author Joerg Huber
 *
 */
public class TestConsumerLoader
{
	// Local
	private static final String CONSUMER_ID = "StudentConsumer";

	// Broker
//	private static final String CONSUMER_ID = "QueueTestConsumer";

	public static void main(String[] args)
	{
		System.out.println("Start Testing TestConsumerLoader...");

		if (ConsumerLoader.initialise(CONSUMER_ID))
		{
			System.out.println("Consumer loaded successfully.");
		}
		
		
		ConsumerLoader.shutdown();
		System.out.println("End Testing TestConsumerLoader.");
	}
}
