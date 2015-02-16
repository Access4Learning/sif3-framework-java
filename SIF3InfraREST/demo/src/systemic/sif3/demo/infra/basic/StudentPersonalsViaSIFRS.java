/*
 * StudentPersonalsViaSIFRS.java
 * Created: 06/08/2013
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

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBElement;

import sif.dd.au30.model.StudentCollectionType;

import au.com.systemic.framework.utils.FileReaderWriter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;

/**
 * @author Joerg Huber
 *
 */
public class StudentPersonalsViaSIFRS extends BaseDemo
{
//	private final static String OUTPUT_ENV_FILE_NAME = "C:/DEV/eclipseWorkspace/SIF3InfraREST/TestData/xml/output/StudentPersonals.xml";
	private final static String OUTPUT_ENV_FILE_NAME = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST/TestData/xml/output/StudentPersonals.xml";	

	private static final String AUTH_TOKEN = "Basic MmU1ZGQzY2EyODJmYzhkZGIzZDA4ZGNhY2M0MDdlOGE6MDJiY2VjZjdlYjA5N2U3NzgzMTk1ZjBlZDJhNmEwNmIK";	

	private static URI getBaseURI()
	{
		return UriBuilder.fromUri("http://rest3api.sifassociation.org/api/solutions/auTestSolution/requestsConnector/StudentPersonals").build();
	}

	@SuppressWarnings("unchecked")
    private static void getAllStudents(WebResource service)
	{
		ClientResponse response = setRequestHeader(service, AUTH_TOKEN).accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
		boolean hasEntity = printClientResponse(response);
		if (hasEntity)
		{
			String entity = response.getEntity(String.class);
			FileReaderWriter.writeContentToFile(entity, OUTPUT_ENV_FILE_NAME);
			System.out.println("Data written to: "+OUTPUT_ENV_FILE_NAME);
			
			// Try to put this into the JAXB generates StudentPersonals Object.
			JAXBElement<StudentCollectionType> elem = (JAXBElement<StudentCollectionType>) unmarshalFromXML(entity, StudentCollectionType.class);

			System.out.println("Number of Students: " + elem.getValue().getStudentPersonal().size());
			for (int i = 0; i < elem.getValue().getStudentPersonal().size(); i++)
			{
				System.out.println("Student " + (i + 1) + " RefID: " + elem.getValue().getStudentPersonal().get(i).getRefId());
			}
		}
	}
	
	public static void main(String[] args)
	{
		ClientConfig config = new DefaultClientConfig();
		
		// Create the Client Service
		Client client = Client.create(config);
		
		// Add logging of header information. Print info to System out.
		client.addFilter(new LoggingFilter(System.out));
		
		// Retrieve connector to resource
		WebResource service = client.resource(getBaseURI());

		// Un-comment each action as you like to use it. 
		getAllStudents(service);
		
	}
}
