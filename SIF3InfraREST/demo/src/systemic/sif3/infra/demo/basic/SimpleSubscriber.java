/*
 * SimpleSubscriber.java
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

package systemic.sif3.infra.demo.basic;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBElement;

import systemic.sif.dd.jaxb.StudentCollectionType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @author Joerg Huber
 *
 */
public class SimpleSubscriber extends BaseDemo
{
	private static final String TEST_STRING =
	"<StudentPersonals>"+
	"	<StudentPersonal RefId=\"f04be500-9725-4b3d-82bb-4334be6be920\">"+
	"	  <LocalId>XW123</LocalId>"+
	"	  <PersonInfo>"+     
	"	    <Name Type=\"LGL\">"+       
	"	        <FamilyName>Smith</FamilyName>"+      
	"	        <GivenName>John</GivenName>"+       
	"	     </Name>"+ 
	"	   </PersonInfo>"+
	"	   <MostRecent>"+
	"	       <SchoolLocalId></SchoolLocalId>"+    
	"	       <YearLevel></YearLevel>"+  
	"	  </MostRecent>"+
	"	</StudentPersonal>"+
	"  <StudentPersonal RefId=\"661a1d74-b11b-4269-9391-2cdcee7043db\"> "+
	"<LocalId>XW123</LocalId>  "+
	"<PersonInfo>     "+
	"  <Name Type=\"LGL\">       "+
	"      <FamilyName>Wiechers</FamilyName>"+      
	"      <GivenName>Xavier</GivenName>"+       
	"   </Name> "+
	" </PersonInfo>"+
	" <MostRecent>"+
	"     <SchoolLocalId></SchoolLocalId>"+    
	"     <YearLevel></YearLevel>  "+
	"</MostRecent>"+
	"</StudentPersonal>"+ 	
	"</StudentPersonals>";
		        
		        
	/*---------------------*/
	/*-- Utility Methods --*/
	/*---------------------*/
	private static URI getBaseURI()
	{
		return UriBuilder.fromUri("http://siftraining.dd.com.au/api").build();
	}
	
	@SuppressWarnings("unchecked")
    private static void getAllStudents(WebResource service)
	{
		service = service.path("StudentPersonals");
		// String authTokenStep2 = getAuthToken(SESSION_TOKEN);
		ClientResponse response = service.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
		boolean hasEntity = printClientResponse(response);
		if (hasEntity)
		{
			String entity = response.getEntity(String.class);

			// Try to put this into the JAXB generates StudentPersonals Object.
			JAXBElement<StudentCollectionType> elem = (JAXBElement<StudentCollectionType>) unmarshalFromXML(entity, StudentCollectionType.class);
			// JAXBElement<StudentCollectionType> elem = (JAXBElement<StudentCollectionType>)unmarshalFromXML(TEST_STRING, StudentCollectionType.class);

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
		//client.addFilter(new LoggingFilter(System.out));
		
		// Retrieve connector to resource
		WebResource service = client.resource(getBaseURI());

		// Un-comment each action as you like to use it. 
		getAllStudents(service);
		
	}

}
