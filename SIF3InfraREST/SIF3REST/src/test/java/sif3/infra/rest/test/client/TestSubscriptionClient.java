/*
 * TestSubscriptionClient.java
 * Created: 13/04/2014
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

package sif3.infra.rest.test.client;

import java.net.URI;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment.ConnectorName;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.ServiceTypeType;
import sif3.infra.common.model.SubscriptionType;
import sif3.infra.rest.client.SubscriptionClient;
import sif3.infra.rest.consumer.ConsumerLoader;

/**
 * @author Joerg Huber
 *
 */
public class TestSubscriptionClient
{
//	private static final String PROP_FILE_NAME="StudentConsumer";
//	private AdapterEnvironmentStore store = new AdapterEnvironmentStore(PROP_FILE_NAME);

	  // Local
	  private static final String CONSUMER_ID = "StudentConsumer";

	  // Broker
	//private static final String CONSUMER_ID = "BrokeredAttTrackerConsumer";

	
	// Brokered
//	private static final String CONNECTOR_URI = "https://australia.hostedzone.com/svcs/systemicDemo/subscriptions";
//	private static final String SESSION_TOKEN = "1a47dae9-579b-4aa5-8048-608b06c611cb";
//	private static final String ZONE="demo";
//	private static final String PWD="DemoSIS1";
//	private static final String QUEUE_ID="f8dccdcb-f496-469b-bf2a-626e9f7afffb";

	// Direct
	private static final String CONNECTOR_URI = "http://localhost:9080/SIF3InfraREST/sif3/subscriptions";
//	private static final String SESSION_TOKEN = "4a19bb4b-64f0-48cb-889f-d0fb747a0d6c";
	private static final String ZONE="auSchoolTestingZone";
//	private static final String PWD="Password1";
	private static final String QUEUE_ID="c59666ec-d92b-48b3-bd2a-4634ea06c5e5";

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
			if (response.getDataObjectType() != null)
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
			else // valid in case where NO_CONTENT is returned
			{
				System.out.println("NO Object Data.");
			}
		}
	}
	
	private SubscriptionType getSubscription(Response response) throws Exception
	{
		if (!response.hasError())
		{
			return (SubscriptionType)response.getDataObject();
		}
		else
		{
			return null;
		}
	}
		
	private URI getConnectorURI()
	{
		try
		{
			return new URI(CONNECTOR_URI);
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

	private SubscriptionClient getClient()
	{
	    ConsumerEnvironmentManager.getInstance().getEnvironmentInfo().addConnectorBaseURI(ConnectorName.subscriptions, getConnectorURI());
		return new SubscriptionClient(ConsumerEnvironmentManager.getInstance());
	}
	
	private void testGetSubscription(SubscriptionClient clt, SubscriptionType subscription) throws Exception
	{
		System.out.println("Get Single Subscription: "+subscription.getId());
		
		if (StringUtils.notEmpty(subscription.getId()))
		{
			printResponse(clt.getSubscription(subscription.getId()));
		}
		else
		{
			System.out.println("Subscription ID is null. Cannot retrieve Subscription details. No action taken.");
		}
		System.out.println("Get Subscription done!");
	}
	
	private void testGetSubscriptions(SubscriptionClient clt) throws Exception
	{
		System.out.println("Get Subscriptions...");
		printResponse(clt.getSubscriptions());
		System.out.println("Get Subscriptions done!");
	}

	private SubscriptionType testCreateSubscription(SubscriptionClient clt) throws Exception
	{
		System.out.println("Create Subscription...");
		SubscriptionType subscription = new ObjectFactory().createSubscriptionType();
		subscription.setQueueId(QUEUE_ID);
//		subscription.setServiceName("StudentPersonals");
		subscription.setServiceName("SchoolInfos");
		subscription.setServiceType(ServiceTypeType.OBJECT);
		//subscription.setZoneId(ZONE);
		
		Response response = clt.subscribe(subscription);
		printResponse(response);
		System.out.println("Create Subscription Done.");
		return getSubscription(response);
	}

	private void testRemoveSubscription(SubscriptionClient clt, SubscriptionType subscription) throws Exception
	{
		System.out.println("Remove Subscription: "+subscription.getId());
		if (StringUtils.notEmpty(subscription.getId()))
		{
			printResponse(clt.unsubscribe(subscription.getId()));
		}
		else
		{
			System.out.println("Subscription ID is null. Cannot delete. No action taken.");
		}
		
		System.out.println("Remove done!");
	}

	public static void main(String[] args)
	{
		try
		{
			TestSubscriptionClient tester = new TestSubscriptionClient();		    
		    System.out.println("Start Testing TestSubscriptionClient...");

            if (ConsumerLoader.initialise(CONSUMER_ID))
            {
                SubscriptionClient clt = tester.getClient();
                SubscriptionType subscription = tester.testCreateSubscription(clt);
                tester.testGetSubscription(clt, subscription);
                tester.testRemoveSubscription(clt, subscription);

                ConsumerLoader.shutdown();
            }
            else
            {
                System.out.println("Failed to initialse tester!");
            }
		    
		    System.out.println("End Testing TestSubscriptionClient.");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	
}
