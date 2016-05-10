/*
 * BaseDemo.java
 * Created: 04/08/2013
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

package systemic.sif3.demo.infra.basic;

import java.io.StringReader;
import java.nio.charset.Charset;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.core.util.Base64;

/**
 * @author Joerg Huber
 *
 */
public class BaseDemo
{
	private static final String CONSUMER_SECRET = "guest";

	public static String getAuthToken(String userToken)
	{
		String authToken = "Basic "+new String(Base64.encode(userToken+":"+CONSUMER_SECRET), Charset.forName("ASCII"));
		System.out.println("Authorisation Token for user token = '"+userToken+"': '"+authToken+"'");
		return authToken;
	}
	
	public static boolean printClientResponse(ClientResponse response)
	{
		System.out.println("Status: " + response.getStatus());
		System.out.println("Client Response Status Code: " + response.getStatusInfo().getStatusCode());
		System.out.println("Client Response Status Message: " + response.getStatusInfo().getReasonPhrase());
		System.out.println("Media Type: " + response.getType());
		System.out.println("Content Length: " + response.getLength());
		System.out.println("Has Entity: " + response.hasEntity());
		System.out.println("Headers: " + response.getHeaders());
		
		return response.hasEntity() && (response.getStatusInfo().getStatusCode()!=Status.NO_CONTENT.getStatusCode());
	}
	
	public static Builder setRequestHeader(WebResource service, String authToken)
	{
		System.out.println("Auth Token to use: "+authToken);
		return service.header(HttpHeaders.AUTHORIZATION, authToken); 
	}
	
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 public static JAXBElement unmarshalFromXML(String xmlStr, Class clazz)
	  {
	    JAXBElement elem = null;
	    try
	    {
	      Unmarshaller unmarshaller = JAXBContext.newInstance(clazz).createUnmarshaller();
	      elem = (JAXBElement)unmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr)), clazz);
	    }
	    catch (JAXBException ex)
	    {
	      ex.printStackTrace();
	    }
	    
	    return elem;
	  }
}
