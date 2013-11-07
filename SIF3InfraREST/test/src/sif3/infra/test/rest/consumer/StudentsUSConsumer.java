/*
 * StudentsUSConsumer.java
 * Created: 11/10/2013
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

package sif3.infra.test.rest.consumer;

import java.util.List;

import sif.dd.us30.conversion.DataModelMarshalFactoryUS;
import sif.dd.us30.conversion.DataModelUnmarshalFactoryUS;
import sif3.common.model.PagingInfo;
import sif3.common.ws.Response;
import systemic.sif3.demo.rest.consumer.StudentsConsumer;

/**
 * @author Joerg Huber
 *
 */
public class StudentsUSConsumer
{
	private DataModelUnmarshalFactoryUS unmarshaller = new DataModelUnmarshalFactoryUS();
	private DataModelMarshalFactoryUS marshaller = new DataModelMarshalFactoryUS();

	private static final String CONSUMER_ID = "StudentConsumer";

	private void printResponses(List<Response> responses)
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
							System.out.println("Data Object Response "+i+": "+marshaller.marshalToXML(response.getDataObject()));
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

	private StudentsConsumer getConsumer()
	{
		return new StudentsConsumer(CONSUMER_ID, marshaller,  unmarshaller);
	}

	private void getStudents(StudentsConsumer consumer)
	{
		System.out.println("Start 'Get All Students' in all connected environments...");
		try
		{
			List<Response> responses = consumer.retrieve(new PagingInfo(5, 0), null);
			System.out.println("Responses from attempt to Get All Students:");
			printResponses(responses);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Finished 'Get All Students' in all connected environments...");
	}

	public static void main(String[] args)
	{
		StudentsUSConsumer tester = new StudentsUSConsumer();
		System.out.println("Start Testing StudentsConsumer...");

		StudentsConsumer consumer = tester.getConsumer();
		
		tester.getStudents(consumer);
//		tester.createStudent(consumer);
//		tester.removeStudent(consumer);
//		tester.getStudent(consumer);
//		tester.updateStudent(consumer);
//		tester.createStudents(consumer);
//		tester.deleteStudents(consumer);

		System.out.println("Finalise Consumer (i.e. disconnect and remove environment).");
		consumer.finalise();

		System.out.println("End Testing StudentsConsumer.");
	}

	
	
}
