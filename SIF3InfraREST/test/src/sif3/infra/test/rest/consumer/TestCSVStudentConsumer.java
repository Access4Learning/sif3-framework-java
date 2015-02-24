/*
 * TestStudentPersonalConsumer.java Created: 24/09/2013
 * 
 * Copyright 2013 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package sif3.infra.test.rest.consumer;

import java.util.List;

import javax.ws.rs.core.MediaType;

import sif3.common.ws.Response;
import sif3.infra.rest.consumer.ConsumerLoader;
import systemic.sif3.demo.rest.consumer.CSVStudentConsumer;
import systemic.sif3.demo.rest.conversion.CSVUnmarshaller;
import au.com.systemic.framework.utils.FileReaderWriter;

/**
 * @author Joerg Huber
 * 
 */
public class TestCSVStudentConsumer
{
//	private final static String PATH = "/Users/crub/dev/nsip/Users/crub/dev/nsip/sif3-framework-java-dev";
	private final static String PATH = "C:/DEV/eclipseWorkspace";
//	private final static String PATH = "C:/Development/GitHubRepositories/SIF3InfraRest";
  
	private final static String CSV_FILE_NAME = PATH + "/SIF3InfraREST/TestData/csv/input/CSVStudents.csv";
	private static final String CONSUMER_ID = "StudentConsumer";
	
	private void printResponses(List<Response> responses, CSVStudentConsumer consumer)
	{
		try
		{
			if (responses != null)
			{
				int i = 1;
				for (Response response : responses)
				{
					System.out.println("Response "+i+":\n"+response);
					if (response.hasError())
					{
						System.out.println("Error for Response "+i+": "+response.getError());
					}
					else // We should have a student personal
					{
						if (response.getHasEntity())
						{
							System.out.println("Data Object Response "+i+": "+consumer.getMarshaller().marshal(response.getDataObject(), consumer.getResponseMediaType()));
						}
						else
						{
							System.out.println("Data Object Response "+i+": No Data Returned. Respnse Status = "+response.getStatus()+" ("+response.getStatusMessage()+")");							
						}
					}
					i++;
				}
			}
			else
			{
				System.out.println("Responses from attempt to create Student: null");				
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private String getCSV(CSVUnmarshaller unmarshaller)
	{
		String inputData = FileReaderWriter.getFileContent(CSV_FILE_NAME);
		System.out.println("File content:\n" + inputData);
		try
		{
			return (String)unmarshaller.unmarshal(inputData, String.class, MediaType.TEXT_PLAIN_TYPE);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	private void createCSV(CSVStudentConsumer consumer)
	{
		System.out.println("Start 'Create CSV' in all connected environments...");
		String csvData = getCSV((CSVUnmarshaller)consumer.getUnmarshaller());
		try
		{
			List<Response> responses = consumer.createSingle(csvData, null);
			System.out.println("Responses from attempt to Create CSV:");
			printResponses(responses, consumer);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Finished 'Create CSV' in all connected environments...");
	}
	
	private CSVStudentConsumer getConsumer()
	{
		return new CSVStudentConsumer();
	}

	public static void main(String[] args)
	{
		TestCSVStudentConsumer tester = new TestCSVStudentConsumer();
		System.out.println("Start Testing CSVStudentConsumer...");

		if (ConsumerLoader.initialise(CONSUMER_ID))
		{
			CSVStudentConsumer consumer = tester.getConsumer();
 
  			//tester.createCSV(consumer);
  
  			System.out.println("Finalise Consumer (i.e. disconnect and remove environment).");
  			consumer.finalise();
		}
		
		ConsumerLoader.shutdown();
		System.out.println("End Testing CSVStudentConsumer.");
	}

}
