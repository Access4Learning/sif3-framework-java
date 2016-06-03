/*
 * TestQueueClient.java
 * Created: 03/04/2014
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

package sif3.infra.test.rest.client;

import java.net.URI;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.header.ResponseHeaderConstants;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment.ConnectorName;
import sif3.infra.common.model.QueueType;
import sif3.infra.rest.client.MessageClient;
import sif3.infra.rest.client.QueueClient;
import sif3.infra.rest.consumer.ConsumerLoader;

/**
 * @author Joerg Huber
 *
 */
public class TestQueueClient
{
//	private static final String PROP_FILE_NAME="StudentConsumer";
//	private AdapterEnvironmentStore store = new AdapterEnvironmentStore(PROP_FILE_NAME);

    // Local
    private static final String CONSUMER_ID = "StudentConsumer";

    // Broker
  //private static final String CONSUMER_ID = "BrokeredAttTrackerConsumer";

	// Brokered
//	private static final String QUEUE_CONNECTOR_URI = "https://australia.hostedzone.com/svcs/systemicDemo/queues";
//	private static final String SESSION_TOKEN = "1a47dae9-579b-4aa5-8048-608b06c611cb";
//	private static final String ZONE="demo";
//	private static final String PWD="DemoSIS1";
//	private static final String QUEUE_ID="f8dccdcb-f496-469b-bf2a-626e9f7afffb";

	// Direct
	private static final String QUEUE_CONNECTOR_URI = "http://localhost:9080/SIF3InfraREST/sif3/queues";
//	private static final String SESSION_TOKEN = "f2658b59-435b-4a8d-9f76-c2400b6655c1";
	private static final String ZONE="auSchoolTestingZone";
//	private static final String PWD="Password1";
	private static final String QUEUE_ID="1edf60b9-3432-4e94-b6cf-c3fd9074385d";

	private void printResponse(Response response) throws Exception
	{
		if (response.hasError())
		{
			System.out.println("Error Occured Invoking REST service.");
		}
		else
		{
			System.out.println("Invoking REST service succeeded.");				
		}
		System.out.println("Full Response Data:\n"+response);
		if (!response.hasError())
		{
		    if (response.getDataObject() != null)
		    {
    			if (!response.hasError())
    			{
    				if (response.getDataObjectType().getSimpleName().equals(String.class.getSimpleName()))
    				{
    					System.out.println("Data Object Data:\n"+response.getDataObject());
    				}
    				else
    				{
    					InfraMarshalFactory marshaller = new InfraMarshalFactory(); 
    					System.out.println("Data Object Data:\n"+marshaller.marshalToXML(response.getDataObject()));
    				}
    			}
		    }
		    else
		    {
		        System.out.println("No Data Object returned.");
		    }
		}
		else // valid in case where NO_CONTENT is returned
		{
			System.out.println("NO Object Data.");
		}
	}
	
	private QueueType getQueue(Response response) throws Exception
	{
		if (!response.hasError())
		{
			return (QueueType)response.getDataObject();
		}
		else
		{
			return null;
		}
	}
		
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
	
	/*
	 * Just fake a SIF3Session.
	 */
//	private SIF3Session getSIF3Session()
//	{
//		SIF3Session session = new SIF3Session();
//		session.setSessionToken(SESSION_TOKEN);
//		session.setPassword(PWD);
//		session.setDefaultZone(new SIFZone(ZONE, true));
//		
//		return session;
//	}

	private QueueClient getClient()
	{
        ConsumerEnvironmentManager.getInstance().getEnvironmentInfo().addConnectorBaseURI(ConnectorName.queues, getQueueConnectorURI());
		return new QueueClient(ConsumerEnvironmentManager.getInstance());
	}
	
	private void testGetQueue(QueueClient clt, QueueType queue) throws Exception
	{
		System.out.println("Get Single Queue: "+queue.getId());
		
		if (StringUtils.notEmpty(queue.getId()))
		{
			printResponse(clt.getQueue(queue.getId()));
		}
		else
		{
			System.out.println("Queue ID is null. Cannot retrieve queue details. No action taken.");
		}
		System.out.println("Get Queue done!");
	}
	
    private QueueType testGetQueue(QueueClient clt) throws Exception
    {
        System.out.println("Get Single Queue: " + QUEUE_ID);

        if (StringUtils.notEmpty(QUEUE_ID))
        {
            Response response = clt.getQueue(QUEUE_ID);
            printResponse(response);
            return getQueue(response);
        }
        else
        {
            System.out.println("Queue ID is null. Cannot retrieve queue details. No action taken.");
            return null;
        }
    }
	
	private void testGetQueues(QueueClient clt) throws Exception
	{
		System.out.println("Get Queues...");
		printResponse(clt.getQueues());
		System.out.println("Get Queues done!");
	}

	private QueueType testCreateImmediateQueue(QueueClient clt) throws Exception
	{
		System.out.println("Create Immediate Queue...");
		Response response = clt.createImmediateQueue("Immediate Queue - Test Joerg", 3);
		printResponse(response);
		return getQueue(response);
	}

	private QueueType testCreateLongPolloingQueue(QueueClient clt) throws Exception
	{
		System.out.println("Create Immediate Queue...");
		Response response = clt.createLongPollingQueue("Long Polling Queue - Test Joerg", 3, 120);
		printResponse(response);
		return getQueue(response);
	}

	private void testRemoveQueue(QueueClient clt, QueueType queue) throws Exception
	{
		System.out.println("Remove Queue: "+queue.getId());
		if (StringUtils.notEmpty(queue.getId()))
		{
			printResponse(clt.removeQueue(queue.getId()));
		}
		else
		{
			System.out.println("Queue ID is null. Cannot delete. No action taken.");
		}
		
		System.out.println("Remove done!");
	}

	private String testGetNextMessage(QueueType queue, String deleteMsgID) throws Exception
	{
		System.out.println("Get Next Message...");
		MessageClient clt = new MessageClient(ConsumerEnvironmentManager.getInstance(), new URI(queue.getQueueUri())); 
		
		Response response = clt.getMessage(deleteMsgID, "Thread-1");
		printResponse(response);
		String nextMsgID = response.getHdrProperties().getHeaderProperty(ResponseHeaderConstants.HDR_MESSAGE_ID);
		System.out.println("Next Available message is: "+nextMsgID);
		return nextMsgID;
	}

	private String testGetNextMessages(QueueType queue) throws Exception
	{
		System.out.println("Get Next Message...");
		String deleteMsgID = null;
		MessageClient clt = new MessageClient(ConsumerEnvironmentManager.getInstance(), new URI(queue.getQueueUri())); 
		for (int i = 0; i<10; i++)
		{
			Response response = clt.getMessage(deleteMsgID, "Thread-1");
			deleteMsgID = response.getHdrProperties().getHeaderProperty(ResponseHeaderConstants.HDR_MESSAGE_ID);
			System.out.println("Returned message ID is: "+deleteMsgID);
		}
		return deleteMsgID;
	}

	private void testRemoveMessage(QueueType queue, String deleteMsgID) throws Exception
	{
		System.out.println("Remove Message...");
		MessageClient clt = new MessageClient(ConsumerEnvironmentManager.getInstance(), new URI(queue.getQueueUri())); 
		printResponse(clt.removeMessage(deleteMsgID, "Thread-1"));
		System.out.println("Remove Message done.");
	}

	
	public static void main(String[] args)
	{
		try
		{
			String nextMesgID = null;
			TestQueueClient tester = new TestQueueClient();
		    
		    System.out.println("Start Testing TestQueueClient...");
		    
            if (ConsumerLoader.initialise(CONSUMER_ID))
            {
    		    QueueClient clt = tester.getClient();
//    		    QueueType queue = tester.testCreateImmediateQueue(clt);
    		    QueueType queue = tester.testCreateLongPolloingQueue(clt);
//                tester.testGetQueues(clt);
//                QueueType queue = tester.testGetQueue(clt);
//                tester.testGetQueue(clt, queue);
//                tester.testRemoveQueue(clt, queue);
                tester.testGetQueue(clt, queue);
                nextMesgID = tester.testGetNextMessage(queue, nextMesgID);
                nextMesgID = tester.testGetNextMessage(queue, nextMesgID);
//              nextMesgID = tester.testGetNextMessages(queue);
//              tester.testGetQueue(clt, queue);
                tester.testRemoveMessage(queue, nextMesgID);
                ConsumerLoader.shutdown();
            }
            else
            {
                System.out.println("Failed to initialse tester!");
            }
		    
		    System.out.println("End Testing TestQueueClient.");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	
}
