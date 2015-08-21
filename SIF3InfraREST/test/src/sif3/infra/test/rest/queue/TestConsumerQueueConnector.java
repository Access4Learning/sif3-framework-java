/*
 * TestConsumerQueueConnector.java
 * Created: 29/04/2014
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

package sif3.infra.test.rest.queue;

import java.net.URI;

import sif3.infra.common.env.types.AdapterEnvironmentStore;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.env.types.ConsumerEnvironment.ConnectorName;
import sif3.infra.rest.queue.connectors.ConsumerQueueConnector;

/**
 * @author Joerg Huber
 *
 */
public class TestConsumerQueueConnector
{
//	private static final String CONSUMER_PROP_FILE_NAME = "StudentConsumer";
//	private static final String CONSUMER_PROP_FILE_NAME = "BrokeredAttTrackerConsumer";
	
	// Local setup
//	private static final String SESSION_TOKEN = "4a19bb4b-64f0-48cb-889f-d0fb747a0d6c";
//	private static final String ENV_ID = "ffc04a2e-4149-4eda-a4c9-40a8ebc2e32b";
//	private static final String ZONE="auSchoolTestingZone";
//	private static final String PWD="Password1";
//	private static final String QUEUE_CONNECTOR_URI = "http://localhost:9080/SIF3InfraREST/sif3/queues";

	//Broker setup
	private static final String CONSUMER_PROP_FILE_NAME = "QueueTestConsumer";
//	private static final String SESSION_TOKEN = "c743ce4c-8826-42ef-bcf0-1504980027cd";
//	private static final String ENV_ID = "acc75216-9ff8-4d4f-93c8-de100c63aa72";
//	private static final String ZONE="demo";
//	private static final String PWD="QueueTest1";
	private static final String QUEUE_CONNECTOR_URI = "https://australia.hostedzone.com/svcs/systemicDemo/queues";
	
	
	private ConsumerQueueConnector connector = null;

	private AdapterEnvironmentStore store = new AdapterEnvironmentStore(CONSUMER_PROP_FILE_NAME);

	public TestConsumerQueueConnector()
	{
		ConsumerEnvironment envInfo = (ConsumerEnvironment)store.getEnvironment();
		envInfo.addConnectorBaseURI(ConnectorName.queues, getQueueConnectorURI());

		connector = new ConsumerQueueConnector();
	}
	
	
	/*
	 * Just fake a SIF3Session.
	 */
//	private SIF3Session getSIF3Session()
//	{
//		SIF3Session session = new SIF3Session();
//		session.setSessionToken(SESSION_TOKEN);
//		session.setPassword(PWD);
//		session.setDefaultZone(new SIFZone(ZONE, true));
//		session.setEnvironmentID(ENV_ID);
//		
//		return session;
//	}
	
	private URI getQueueConnectorURI()
	{
		try
		{
			return new URI(QUEUE_CONNECTOR_URI);
		}
		catch (Exception ex)
		{
			return null;
		}
	}
	
	private void testSyncStart()
	{
		if (connector.syncQueuesAtStartup())
		{
			System.out.println("Queues are synced up.");
		}
		else
		{
			System.out.println("Failed to Sync Queues. See error log entries.");
		}
	}
	
	private void testSyncStop()
	{
		connector.syncQueuesAtShutDown();	
	}

	public static void main(String[] args)
	{
		System.out.println("Start Testing TestConsumerQueueConnector...");
		TestConsumerQueueConnector tester = new TestConsumerQueueConnector();

		tester.testSyncStart();
		tester.testSyncStop();

		System.out.println("End Testing TestConsumerQueueConnector.");
	}
}
