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

import java.util.ArrayList;
import java.util.List;

import sif.dd.au30.conversion.DataModelMarshalFactory;
import sif.dd.au30.conversion.DataModelUnmarshalFactory;
import sif.dd.au30.model.StudentCollectionType;
import sif.dd.au30.model.StudentPersonalType;
import sif3.common.exception.UnmarshalException;
import sif3.common.model.EnvironmentZoneContextInfo;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.Response;
import systemic.sif3.demo.rest.consumer.StudentPersonalConsumer;
import au.com.systemic.framework.utils.FileReaderWriter;

/**
 * @author Joerg Huber
 * 
 */
public class TestStudentPersonalConsumer
{
	// private final static String SINGLE_STUDENT_FILE_NAME = "C:/DEV/eclipseWorkspace/SIF3InfraREST/TestData/xml/input/StudentPersonal.xml";
	// private final static String MULTI_STUDENT_FILE_NAME = "C:/DEV/eclipseWorkspace/SIF3InfraREST/TestData/xml/input/StudentPersonals5.xml";
	private final static String SINGLE_STUDENT_FILE_NAME = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST/TestData/xml/input/StudentPersonal.xml";
	private final static String MULTI_STUDENT_FILE_NAME = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST/TestData/xml/input/StudentPersonals5.xml";
	private static final String CONSUMER_ID = "StudentConsumer";
	
	private DataModelUnmarshalFactory unmarshaller = new DataModelUnmarshalFactory();
	private DataModelMarshalFactory marshaller = new DataModelMarshalFactory();
	
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
	
	private StudentPersonalType getStudent()
	{
		String inputEnvXML = FileReaderWriter.getFileContent(SINGLE_STUDENT_FILE_NAME);
		//System.out.println("File content:\n" + inputEnvXML);
		try
		{
			return (StudentPersonalType)unmarshaller.unmarshalFromXML(inputEnvXML, StudentPersonalType.class);
		}
		catch (UnmarshalException ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	private StudentCollectionType getStudents()
	{
		String inputEnvXML = FileReaderWriter.getFileContent(MULTI_STUDENT_FILE_NAME);
		//System.out.println("File content:\n" + inputEnvXML);
		try
		{
			return (StudentCollectionType)unmarshaller.unmarshalFromXML(inputEnvXML, StudentCollectionType.class);
		}
		catch (UnmarshalException ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	private StudentPersonalConsumer getConsumer()
	{
		return new StudentPersonalConsumer(CONSUMER_ID, marshaller,  unmarshaller);
	}

	private void createStudent(StudentPersonalConsumer consumer)
	{
		System.out.println("Start 'Create Student' in all connected environments...");
		StudentPersonalType student = getStudent();
		try
		{
			List<Response> responses = consumer.createSingle(student, null);
			System.out.println("Responses from attempt to Create Student:");
			printResponses(responses);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Finished 'Create Student' in all connected environments...");
	}

	private void createStudents(StudentPersonalConsumer consumer)
	{
		System.out.println("Start 'Create Students (Multi)' in all connected environments...");
		StudentCollectionType students = getStudents();
		try
		{
			List<BulkOperationResponse> responses = consumer.createMany(students, null);
			if (responses != null)
			{
				int i = 1;
				for (BulkOperationResponse response : responses)
				{
					System.out.println("Response "+i+":\n"+response);
					if (response.hasError())
					{
						System.out.println("Error for Response "+i+": "+response.getError());
					}
					else // We should have a student personal
					{
						System.out.println("Student Response "+i+": "+response.getOperationStatuses());
					}
					i++;
				}
			}
			else
			{
				System.out.println("Responses from attempt to create Students: null");				
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Finished 'Create Students (Multi)' in all connected environments...");
	}

	 private void deleteStudents(StudentPersonalConsumer consumer)
	  {
	    System.out.println("Start 'Delete Students (Multi)' in all connected environments...");
	    ArrayList<String> resourceIDs = new ArrayList<String>();
	    resourceIDs.add(UUIDGenerator.getSIF2GUIDUpperCase());
		resourceIDs.add(UUIDGenerator.getSIF2GUIDUpperCase());
		resourceIDs.add(UUIDGenerator.getSIF2GUIDUpperCase());
		resourceIDs.add(UUIDGenerator.getSIF2GUIDUpperCase());
		resourceIDs.add(UUIDGenerator.getSIF2GUIDUpperCase());
	    
	    try
	    {
	      List<BulkOperationResponse> responses = consumer.deleteMany(resourceIDs, null);
	      if (responses != null)
	      {
	        int i = 1;
	        for (BulkOperationResponse response : responses)
	        {
	          System.out.println("Response "+i+":\n"+response);
	          i++;
	        }
	      }
	      else
	      {
	        System.out.println("Responses from attempt to delete Students: null");        
	      }
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	    System.out.println("Finished 'Delete Students (Multi)' in all connected environments...");
	  }

	
	private void updateStudent(StudentPersonalConsumer consumer)
	{
		System.out.println("Start 'Update Student' in all connected environments...");
		StudentPersonalType student = getStudent();
		student.setRefId(UUIDGenerator.getSIF2GUIDUpperCase());
		try
		{
			List<Response> responses = consumer.updateSingle(student, student.getRefId(), null);
			System.out.println("Responses from attempt to update Student:"+responses);			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Finished 'Update Student' in all connected environments...");
	}
	
	
	private void getStudents(StudentPersonalConsumer consumer)
	{
		System.out.println("Start 'Get All Students' in all connected environments...");
		try
		{
			List<Response> responses = consumer.retrieve(new PagingInfo(5, 17), null);
			System.out.println("Responses from attempt to Get All Students:");
			printResponses(responses);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Finished 'Get All Students' in all connected environments...");
	}

	private void getStudent(StudentPersonalConsumer consumer)
	{
		System.out.println("Start 'Get Student' in all connected environments...");
		try
		{
			List<EnvironmentZoneContextInfo> envZoneCtxList = new ArrayList<EnvironmentZoneContextInfo>();
			envZoneCtxList.add(new EnvironmentZoneContextInfo("devLocal", new SIFZone("zoneABC"), new SIFContext("ctx123")));
			List<Response> responses = consumer.retrievByPrimaryKey("24ed508e1ed04bba82198233efa55859", envZoneCtxList);
			System.out.println("Responses from attempt to Get Student:");
			printResponses(responses);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Finished 'Get Student' in all connected environments...");
	}

	private void removeStudent(StudentPersonalConsumer consumer)
	{
		System.out.println("Start 'Remove Student' in all connected environments...");
		try
		{
			List<Response> responses = consumer.deleteSingle("1234", null);
			System.out.println("Responses from attempt to Remove Student:"+responses);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Finished 'Remove Student' in all connected environments...");
	}
	
	public static void main(String[] args)
	{
		TestStudentPersonalConsumer tester = new TestStudentPersonalConsumer();
		System.out.println("Start Testing StudentPersonalConsumer...");

		StudentPersonalConsumer consumer = tester.getConsumer();
		
		tester.getStudents(consumer);
//		tester.createStudent(consumer);
//		tester.removeStudent(consumer);
//		tester.getStudent(consumer);
//		tester.updateStudent(consumer);
//		tester.createStudents(consumer);
//		tester.deleteStudents(consumer);

		System.out.println("Finalise Consumer (i.e. disconnect and remove environment).");
		consumer.finalise();

		System.out.println("End Testing StudentPersonalConsumer.");
	}

}
