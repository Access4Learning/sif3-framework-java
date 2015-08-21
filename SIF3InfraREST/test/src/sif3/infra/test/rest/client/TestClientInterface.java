/*
 * TestClientInterface.java
 * Created: 05/09/2013
 *
 * Copyright 2013 Systemic Pty Ltd
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

import sif.dd.au30.model.StudentPersonalType;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.header.HeaderProperties;
import sif3.common.model.URLQueryParameter;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.rest.client.ClientInterface;
import sif3.infra.rest.consumer.ConsumerLoader;


/**
 * @author Joerg Huber
 *
 */
public class TestClientInterface
{
  private MarshalFactory marshaller = new InfraMarshalFactory();
  private UnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
  
  // Local
  private static final String CONSUMER_ID = "StudentConsumer";

  // Broker
//private static final String CONSUMER_ID = "BrokeredAttTrackerConsumer";

  //local Host
  private static final String BASE_URL = "http://localhost:9080/SIF3InfraREST/sif3/requests";
//  private static final String AUTH_TOKEN = "Basic ZDk1OTc4NTFhNmFlNDNkNzg1MDE2NTI4MDAwNzA2NWE6UGFzc3dvcmQx=";
  private static final String REF_ID = "dd107c44-a861-4a47-b385-d63bee26679c";
 
  private void testGetSingle()
	{
		try
		{
			System.out.println("Connect to: "+BASE_URL);
			ClientInterface client = new ClientInterface(ConsumerEnvironmentManager.getInstance(), new URI(BASE_URL), marshaller, unmarshaller, false, false);

			HeaderProperties hdrProps = new HeaderProperties();
//			hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, AUTH_TOKEN);
			URLQueryParameter urlQueryParams = new URLQueryParameter();
			
			Response response = client.getSingle("StudentPersonals", REF_ID, hdrProps, urlQueryParams, StudentPersonalType.class, null, null);
			
			if (response.hasError())
			{
				System.out.println("Error Occured retrieving student.");
			}
			else
			{
				System.out.println("Retrieving student succeeded.");				
			}
			System.out.println(response);
			if (!response.hasError())
			{
				InfraMarshalFactory marshaller = new InfraMarshalFactory(); 
				System.out.println(marshaller.marshalToXML(response.getDataObject()));
			}
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		TestClientInterface tester = new TestClientInterface();
	    
	    if (ConsumerLoader.initialise(CONSUMER_ID))
	    {
	        System.out.println("Start Testing TestClientInterface...");
	    
	        tester.testGetSingle();
	    
	        System.out.println("End Testing TestClientInterface.");
	        
	        ConsumerLoader.shutdown();
	    }
	    else
	    {
	        System.out.println("Failed to initialse tester!");
	    }
	}
}
