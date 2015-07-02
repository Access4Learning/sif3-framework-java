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

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.header.HeaderProperties;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.model.URLQueryParameter;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.rest.client.ClientInterface;


/**
 * @author Joerg Huber
 *
 */
public class TestClientInterface
{
  private MarshalFactory marshaller = new InfraMarshalFactory();
  private UnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
  
  //atTestSolution on SIF-RS
//  private static final String BASE_URL = "http://rest3api.sifassociation.org/api";
//  private static final String AUTH_TOKEN = "Basic MmU1ZGQzY2EyODJmYzhkZGIzZDA4ZGNhY2M0MDdlOGE6MDJiY2VjZjdlYjA5N2U3NzgzMTk1ZjBlZDJhNmEwNmI=";
//  private static final String ENV_ID = "5b72f2d4-7a83-4297-a71f-8b5fb26cbf14";
  
  //local Host
  private static final String BASE_URL = "http://localhost:9080/SIF3InfraREST/sif3";
  private static final String AUTH_TOKEN = "Basic ZDk1OTc4NTFhNmFlNDNkNzg1MDE2NTI4MDAwNzA2NWE6UGFzc3dvcmQx=";
  private static final String ENV_ID = "cfa1aece-8b75-49eb-a469-11e3ee4fa369";
 
  private void testGetSingle()
	{
		try
		{
			System.out.println("Connect to: "+BASE_URL);
			ClientInterface client = new ClientInterface(new URI(BASE_URL), marshaller, unmarshaller, false, false);

			HeaderProperties hdrProps = new HeaderProperties();
			hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, AUTH_TOKEN);
			URLQueryParameter urlQueryParams = new URLQueryParameter();
			
			Response response = client.getSingle("environments", ENV_ID, hdrProps, urlQueryParams, EnvironmentType.class, null, null);
			
			if (response.hasError())
			{
				System.out.println("Error Occured retrieving environment.");
			}
			else
			{
				System.out.println("Retrieving environment succeeded.");				
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
	    
	    System.out.println("Start Testing TestClientInterface...");
	    
	    tester.testGetSingle();
	    
	    System.out.println("End Testing TestClientInterface.");
	}
}
