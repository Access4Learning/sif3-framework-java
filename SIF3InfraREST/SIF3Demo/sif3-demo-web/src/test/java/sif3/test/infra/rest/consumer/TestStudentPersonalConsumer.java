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

package sif3.test.infra.rest.consumer;

import java.util.ArrayList;
import java.util.List;

import sif.dd.au30.conversion.DataModelUnmarshalFactory;
import sif.dd.au30.model.NameOfRecordType;
import sif.dd.au30.model.ObjectFactory;
import sif.dd.au30.model.StudentPersonalCollectionType;
import sif.dd.au30.model.StudentPersonalType;
import sif3.common.CommonConstants;
import sif3.common.header.HeaderValues.QueryIntention;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.model.CustomParameters;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServicePathPredicate;
import sif3.common.model.ZoneContextInfo;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.rest.consumer.ConsumerLoader;
import systemic.sif3.demo.rest.consumer.StudentPersonalConsumer;
import au.com.systemic.framework.utils.FileReaderWriter;
import au.com.systemic.framework.utils.Timer;

/**
 * @author Joerg Huber
 * 
 */
public class TestStudentPersonalConsumer
{
//	private final static String PATH = "/Users/crub/dev/nsip/Users/crub/dev/nsip/sif3-framework-java-dev";
//	private final static String PATH = "C:/DEV/lunaWorkspace";
	private final static String PATH = "C:/Development/GitHubRepositories/SIF3InfraRest";
  
	private final static String SINGLE_STUDENT_FILE_NAME = PATH + "/SIF3InfraREST/TestData/xml/input/StudentPersonal.xml";
	private final static String MULTI_STUDENT_FILE_NAME = PATH + "/SIF3InfraREST/TestData/xml/input/StudentPersonals5.xml";
//	private static final String CONSUMER_ID = "SecureStudentConsumer";
	private static final String CONSUMER_ID = "StudentConsumer";
//    private static final String CONSUMER_ID = "HITSStudentConsumer";
//	private static final String CONSUMER_ID = "BrokeredAttTrackerConsumer";
//	private static final String CONSUMER_ID = "QueueTestConsumer";

	
	private static final RequestType REQUEST_TYPE = RequestType.IMMEDIATE;
	
	private static ObjectFactory auDMObjects = new ObjectFactory();
	
	private void printResponses(List<Response> responses, StudentPersonalConsumer consumer)
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
						else // in delayed we may have delayed receipt
						{
							System.out.println("Data Object Response "+i+": No Data Returned. Response Status = "+response.getStatus()+" ("+response.getStatusMessage()+")");
							System.out.println("Delayed Receipt: "+response.getDelayedReceipt());
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
	
	private StudentPersonalType getStudent(DataModelUnmarshalFactory unmarshaller)
	{
		String inputEnvXML = FileReaderWriter.getFileContent(SINGLE_STUDENT_FILE_NAME, "UTF-8");
		//System.out.println("File content:\n" + inputEnvXML);
		try
		{
			return (StudentPersonalType)unmarshaller.unmarshalFromXML(inputEnvXML, StudentPersonalType.class);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	private StudentPersonalCollectionType getStudents(DataModelUnmarshalFactory unmarshaller)
	{
		String inputEnvXML = FileReaderWriter.getFileContent(MULTI_STUDENT_FILE_NAME, "UTF-8");
		//System.out.println("File content:\n" + inputEnvXML);
		try
		{
			return (StudentPersonalCollectionType)unmarshaller.unmarshalFromXML(inputEnvXML, StudentPersonalCollectionType.class);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	private StudentPersonalConsumer getConsumer()
	{
		return new StudentPersonalConsumer();
	}

	private void createStudent(StudentPersonalConsumer consumer)
	{
		System.out.println("Start 'Create Student' in all connected environments...");
		StudentPersonalType student = getStudent((DataModelUnmarshalFactory)consumer.getUnmarshaller());
		try
		{
			List<ZoneContextInfo> zoneCtxList = new ArrayList<ZoneContextInfo>();
//			zoneCtxList.add(new ZoneContextInfo(new SIFZone("auRolloverTestingZone"), null));
			List<Response> responses = consumer.createSingle(student, zoneCtxList);
//			List<Response> responses = consumer.createSingle(student, null);
			System.out.println("Responses from attempt to Create Student:");
			printResponses(responses, consumer);
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
		StudentPersonalCollectionType students = getStudents((DataModelUnmarshalFactory)consumer.getUnmarshaller());
		try
		{
//            List<BulkOperationResponse<CreateOperationStatus>> responses = consumer.createMany(students, null, RequestType.IMMEDIATE);
			List<BulkOperationResponse<CreateOperationStatus>> responses = consumer.createMany(students, null, RequestType.DELAYED);
			if (responses != null)
			{
				int i = 1;
				for (BulkOperationResponse<CreateOperationStatus> response : responses)
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
//	      List<BulkOperationResponse<OperationStatus>> responses = consumer.deleteMany(resourceIDs, null, REQUEST_TYPE);
	      List<BulkOperationResponse<OperationStatus>> responses = consumer.deleteMany(resourceIDs, null, RequestType.IMMEDIATE);
//	      List<BulkOperationResponse<OperationStatus>> responses = consumer.deleteMany(resourceIDs, null, RequestType.DELAYED);
	      if (responses != null)
	      {
	        int i = 1;
	        for (BulkOperationResponse<OperationStatus> response : responses)
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
		StudentPersonalType student = getStudent((DataModelUnmarshalFactory)consumer.getUnmarshaller());
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
	

	private void updateStudents(StudentPersonalConsumer consumer)
	{
	    System.out.println("Start 'Update Students - Bulk Operation' in all connected environments...");
	    StudentPersonalCollectionType students = getStudents((DataModelUnmarshalFactory)consumer.getUnmarshaller());
	    try
	    {
	      List<BulkOperationResponse<OperationStatus>> responses = consumer.updateMany(students, null, RequestType.IMMEDIATE);
//          List<BulkOperationResponse<OperationStatus>> responses = consumer.updateMany(students, null, RequestType.DELAYED);
	      if (responses != null)
	      {
	        int i = 1;
	        for (BulkOperationResponse<OperationStatus> response : responses)
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
	        System.out.println("Responses from attempt to update Students: null");        
	      }
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	    System.out.println("Finished 'Update Students' in all connected environments...");
	}

	private void getStudents(StudentPersonalConsumer consumer, boolean printRepsonse)
	{
		if (printRepsonse)
		{
			System.out.println("Start 'Get All Students' in all connected environments...");
		}
		try
		{
			CustomParameters params = new CustomParameters();
			
			// Set some HTTP Header fields
			params.addHTTPHeaderParameter(RequestHeaderConstants.HDR_GENERATOR_ID, "Ignore This");
			params.addHTTPHeaderParameter("GenID", "This should not be ignored");
			params.addHTTPHeaderParameter("customHdr", "Go all the way to Provider");
			
			// Set some custom URL query Params
			params.addURLQueryParameter("ChangedSince", "01/05/2015");
			params.addURLQueryParameter("myURLQueryParam", "show in provider");

			List<ZoneContextInfo> envZoneCtxList = null;
			envZoneCtxList = new ArrayList<ZoneContextInfo>();
//			envZoneCtxList.add(new ZoneContextInfo(new SIFZone("auRolloverTestingZone"), null));
//			envZoneCtxList.add(new ZoneContextInfo(new SIFZone("auSchoolTestingZone"),  (SIFContext)null));
//			envZoneCtxList.add(new ZoneContextInfo((SIFZone)null, (SIFContext)null));
//			envZoneCtxList.add(new ZoneContextInfo((SIFZone)null, new SIFContext("secure")));
//            envZoneCtxList.add(new ZoneContextInfo((SIFZone)null, CommonConstants.DEFAULT_CONTEXT));

//			List<Response> responses = consumer.retrieve(new PagingInfo(100), envZoneCtxList, RequestType.DELAYED, QueryIntention.NO_CACHE, params);
			List<Response> responses = consumer.retrieve(new PagingInfo(5, 1), envZoneCtxList, RequestType.IMMEDIATE, QueryIntention.NO_CACHE, params);
//            List<Response> responses = consumer.retrieve(new PagingInfo(10, 0), envZoneCtxList, REQUEST_TYPE, QueryIntention.NO_CACHE, params);
//			List<Response> responses = consumer.retrieve(new PagingInfo(5, 17), envZoneCtxList, REQUEST_TYPE);
//			List<Response> responses = consumer.retrieve(null, envZoneCtxList, REQUEST_TYPE);
			if (printRepsonse)
			{
				System.out.println("Responses from attempt to Get All Students:");
				printResponses(responses, consumer);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		if (printRepsonse)
		{
			System.out.println("Finished 'Get All Students' in all connected environments...");
		}
	}
	
	private void getStudentsByServicePath(String parent, String value, StudentPersonalConsumer consumer)
	{
		QueryCriteria criteria = new QueryCriteria();
		criteria.addPredicate(new ServicePathPredicate(parent, value));
		System.out.println("Start 'Get All Students By Service Path' in all connected environments...");
		try
		{
			// Get all students for a service path cirteria. Get 5 students per page (i.e page 1). 
			List<Response> responses = consumer.retrieveByServicePath(criteria, new PagingInfo(5, 1), null, REQUEST_TYPE, QueryIntention.ALL, null);
//			List<Response> responses = consumer.retrieveByServicePath(criteria, new PagingInfo(5, 1), null, RequestType.DELAYED, QueryIntention.ALL, null);
			System.out.println("Responses from attempt to Get All Students for '" + criteria + "': ");
			printResponses(responses, consumer);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Finished 'Get All Students By Service Path' in all connected environments...");
	}
	
	private void getStudentsByQBE(StudentPersonalConsumer consumer)
	{
		StudentPersonalType student = auDMObjects.createStudentPersonalType();
		student.setPersonInfo(auDMObjects.createPersonInfoType());
        NameOfRecordType name = new NameOfRecordType();
        name.setFamilyName(auDMObjects.createBaseNameTypeFamilyName("%an%"));
		student.getPersonInfo().setName(name);

		CustomParameters params = new CustomParameters();

		// Set some HTTP Header fields
		params.addHTTPHeaderParameter(RequestHeaderConstants.HDR_GENERATOR_ID, "Ignore This");
		params.addHTTPHeaderParameter("GenID", "This should not be ignored");
		params.addHTTPHeaderParameter("customHdr", "Go all the way to Provider");
		
		// Set some custom URL query Params
		params.addURLQueryParameter("ChangedSince", "01/05/2015");
		params.addURLQueryParameter("myURLQueryParam", "show in provider");

		List<ZoneContextInfo> envZoneCtxList = null;
//		envZoneCtxList = new ArrayList<ZoneContextInfo>();
//		envZoneCtxList.add(new ZoneContextInfo(new SIFZone("auRolloverTestingZone"), null));
//		envZoneCtxList.add(new ZoneContextInfo(new SIFZone("auSchoolTestingZone"),  null));
//		envZoneCtxList.add(new ZoneContextInfo((SIFZone)null, (SIFContext)null));
//		envZoneCtxList.add(new ZoneContextInfo((SIFZone)null, new SIFContext("secure")));
		
		System.out.println("Start 'Get Students By Example' in all connected environments...");
		try
		{
			// Get all students by example. Get 5 students per page (i.e page 1). 
			List<Response> responses = consumer.retrieveByQBE(student, new PagingInfo(5, 1), envZoneCtxList, REQUEST_TYPE, QueryIntention.ALL, params);
//			List<Response> responses = consumer.retrieveByQBE(student, new PagingInfo(5, 1), envZoneCtxList, RequestType.DELAYED, QueryIntention.ALL, params);
			System.out.println("Responses from attempt to Get Students By Example': ");
			printResponses(responses, consumer);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Finished 'Get Students By Example' in all connected environments...");
	}


	private void getStudent(StudentPersonalConsumer consumer)
	{
		System.out.println("Start 'Get Student' in all connected environments...");
		try
		{
			List<ZoneContextInfo> envZoneCtxList = new ArrayList<ZoneContextInfo>();
//			envZoneCtxList.add(new ZoneContextInfo(new SIFZone("auRolloverTestingZone"), null));
//			envZoneCtxList.add(new ZoneContextInfo(new SIFZone("zone123"),  new SIFContext("abc")));
//			envZoneCtxList.add(new ZoneContextInfo((SIFZone)null, (SIFContext)null));
//      	envZoneCtxList.add(new ZoneContextInfo((SIFZone)null, new SIFContext("secure")));
			
            List<Response> responses = consumer.retrievByPrimaryKey("24ed508e1ed04bba82198233efa55859", envZoneCtxList);
//            List<Response> responses = consumer.retrievByPrimaryKey("164da5d9bcbf4cf8a058ba0b0efde9ba", envZoneCtxList);
			
//			List<Response> responses = consumer.retrievByPrimaryKey("24ed508e1ed04bba82198233efa55859", null);
			System.out.println("Responses from attempt to Get Student:");
			printResponses(responses, consumer);
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
	
	
	private void performanceTest(StudentPersonalConsumer consumer)
	{
		System.out.println("Start Performance Test...");
		Timer timer = new Timer();
		timer.start();
		for (int i = 0; i <= 100; i++)
		{
			getStudents(consumer, false);
		}
		
		timer.finish();
		System.out.println("End Performance Test with Compression "+(consumer.getCompressionEnabled() ? "ON" : "OFF")+". Time taken: "+timer.timeTaken()+"ms");
	}
	
	private void getServiceInfo(StudentPersonalConsumer consumer, boolean printRepsonse)
	{
		if (printRepsonse)
		{
			System.out.println("Start 'Get StudentPersonal Service Info (HTTP HEAD)' in all connected environments...");
		}
		try
		{
			CustomParameters params = new CustomParameters();
			
			// Set some HTTP Header fields
			params.addHTTPHeaderParameter(RequestHeaderConstants.HDR_GENERATOR_ID, "Ignore This");
			params.addHTTPHeaderParameter("GenID", "This should not be ignored");
			params.addHTTPHeaderParameter("customHdr", "Go all the way to Provider");
			
			// Set some custom URL query Params
			params.addURLQueryParameter("ChangedSince", "01/05/2015");
			params.addURLQueryParameter("myURLQueryParam", "show in provider");

			List<ZoneContextInfo> envZoneCtxList = null;
//			envZoneCtxList = new ArrayList<ZoneContextInfo>();
//			envZoneCtxList.add(new ZoneContextInfo(new SIFZone("auRolloverTestingZone"), null));
//			envZoneCtxList.add(new ZoneContextInfo(new SIFZone("auSchoolTestingZone"),  null));
//			envZoneCtxList.add(new ZoneContextInfo((SIFZone)null, (SIFContext)null));
//			envZoneCtxList.add(new ZoneContextInfo((SIFZone)null, new SIFContext("secure")));

			List<Response> responses = consumer.getServiceInfo(new PagingInfo(10, 0), envZoneCtxList, params);
			if (printRepsonse)
			{
				System.out.println("Responses from attempt to Get All Students:");
				printResponses(responses, consumer);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		if (printRepsonse)
		{
			System.out.println("Finished 'Get All Students' in all connected environments...");
		}
	}
	
	public static void main(String[] args)
	{
		TestStudentPersonalConsumer tester = new TestStudentPersonalConsumer();
		System.out.println("Start Testing StudentPersonalConsumer...");

		if (ConsumerLoader.initialise(CONSUMER_ID))
		{
            System.out.println("Consumer loaded successfully. Environment Data:\n"+ConsumerEnvironmentManager.getInstance().getEnvironmentInfo());
		
			StudentPersonalConsumer consumer = tester.getConsumer();
  		
//            tester.getStudent(consumer);
            tester.getStudents(consumer, true);
//            tester.getStudentsByServicePath("SchoolInfos", "24ed508e1ed04bba82198233efa55859", consumer);
//            tester.getStudentsByServicePath("TeachingGroups", "64A309DA063A2E35B359D75101A8C3D1", consumer);
//            tester.getStudentsByServicePath("RoomInfos", "24ed508e1ed04bba82198233efa55859", consumer);
//            tester.createStudent(consumer);
//            tester.createStudents(consumer);
//            tester.removeStudent(consumer);
//            tester.deleteStudents(consumer);
//            tester.updateStudent(consumer);
//            tester.updateStudents(consumer);
//            tester.getStudentsByQBE(consumer);

//            tester.getServiceInfo(consumer, true);

//            tester.performanceTest(consumer);
  
			// Put this agent to a blocking wait.....
			if (true)
			{
    			try
    			{
    				Object semaphore = new Object();
    				synchronized (semaphore) // this will block until CTRL-C is pressed.
    				{
    					System.out.println("==================================================\nTestStudentPersonalConsumer is running (Press Ctrl-C to stop)\n==================================================");
    					semaphore.wait();
    				}
    			}
    			catch (Exception ex)
    			{
    				System.out.println("Blocking wait in TestStudentPersonalConsumer interrupted: " + ex.getMessage());
    				ex.printStackTrace();
    			}
			}

			System.out.println("Finalise Consumer (i.e. disconnect and remove environment).");
			consumer.finalise();
		}
		
		ConsumerLoader.shutdown();
		System.out.println("End Testing StudentPersonalConsumer.");
	}

}
