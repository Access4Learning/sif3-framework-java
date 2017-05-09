/*
 * TestJAXBUtils.java
 * Created: 29/08/2013
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

package sif3.infra.common.test.utils;

import javax.xml.bind.JAXBElement;

import sif.dd.au30.model.StudentPersonalType;
import sif3.common.exception.MarshalException;
import sif3.common.exception.UnmarshalException;
import sif3.common.utils.JAXBUtils;
import sif3.infra.common.model.AlertCollectionType;
import sif3.infra.common.model.AlertType;
import sif3.infra.common.model.ApplicationInfoType;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.EnvironmentTypeType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.ProductIdentityType;
import au.com.systemic.framework.utils.FileReaderWriter;

/**
 * @author Joerg Huber
 *
 */
public class TestJAXBUtils
{
	private ObjectFactory objFactory = new ObjectFactory();
	
//	private final static String BASE_PATH = "C:/DEV/lunaWorkspace/SIF3InfraREST";
    private final static String BASE_PATH = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST";
	
	private final static String INPUT_ENV_FILE_NAME_XML  = BASE_PATH + "/TestData/xml/input/environment_large.xml";
	private final static String OUTPUT_ENV_FILE_NAME_XML = BASE_PATH + "/TestData/xml/output/environment_large.xml";
	private final static String INPUT_ENV_FILE_NAME_JSON  = BASE_PATH + "/TestData/xml/input/environment_large.json";
	private final static String OUTPUT_ENV_FILE_NAME_JSON = BASE_PATH + "/TestData/xml/output/environment_large.json";
    
    private final static String INPUT_STUDENT_FILE_NAME_XML  = BASE_PATH + "/TestData/xml/input/UnicodeStudent.xml";
	private final static String OUTPUT_STUDENT_FILE_NAME_JSON = BASE_PATH + "/TestData/xml/output/UnicodeStudent.xml";
	
	
	private final static String TEST_STUDENT =
	"<StudentPersonal RefId=\"24ed508e1ed04bba82198233efa55859\">" +
	  "<LocalId>98765</LocalId>" +  
	  "<PersonInfo>" +     
	    "<Name Type=\"LGL\">" + 
	        "<FamilyName>Huber</FamilyName>" + 
	        "<GivenName>J�RG</GivenName>" + 
	     "</Name>" + 
	   "</PersonInfo>" + 
	   "<MostRecent>" + 
	       "<SchoolLocalId></SchoolLocalId>" + 
	       "<YearLevel></YearLevel>" +   
	  "</MostRecent>" + 
	"</StudentPersonal>";

	private void printEnvironment(EnvironmentType env)
	{
	    System.out.println("Environment Object unmarshalled:");
	    System.out.println("   ID           : " + env.getId());
	    System.out.println("   Type         : " + env.getType());
	    System.out.println("   Session Token: " + env.getSessionToken());
	    System.out.println("   SolutionID   : " + env.getSolutionId());
	    System.out.println("   Default Zone : " + env.getDefaultZone());
	    System.out.println("   Auth Method  : " + env.getAuthenticationMethod());
	    System.out.println("   Consumer Name: " + env.getConsumerName());
	    System.out.println("   AppInfo: ");
	    System.out.println("      App Key      : " + env.getApplicationInfo().getApplicationKey());
	    System.out.println("      Infra Version: " + env.getApplicationInfo().getSupportedInfrastructureVersion());
	    System.out.println("      Data Model   : " + env.getApplicationInfo().getDataModelNamespace());
//      System.out.println("      DM Version   : " + env.getApplicationInfo().getSupportedDataModelVersion());
	    System.out.println("      Transport    : " + env.getApplicationInfo().getTransport());
	    if (env.getApplicationInfo().getApplicationProduct() != null)
	    {
	        System.out.println("      App Product: ");      
	        System.out.println("         Vendor Name    : " + env.getApplicationInfo().getApplicationProduct().getVendorName());      
	        System.out.println("         Product Name   : " + env.getApplicationInfo().getApplicationProduct().getProductName());      
	        System.out.println("         Product Version: " + env.getApplicationInfo().getApplicationProduct().getProductVersion());      
	    }
    
//    <infrastructureServices>
//      <infrastructureService name="environment">http://rest3api.sifassociation.org/api/solutions/auTestSolution/environments/5b72f2d4-7a83-4297-a71f-8b5fb26cbf14</infrastructureService>
//      <infrastructureService name="requestsConnector">http://rest3api.sifassociation.org/api/solutions/auTestSolution/requestsConnector</infrastructureService>
//      <infrastructureService name="provisionRequests">http://rest3api.sifassociation.org/api/solutions/auTestSolution/provisionRequests</infrastructureService>
//      <infrastructureService name="queues">http://rest3api.sifassociation.org/api/solutions/auTestSolution/queues</infrastructureService>
//      <infrastructureService name="subscriptions">http://rest3api.sifassociation.org/api/solutions/auTestSolution/subscriptions</infrastructureService>
//    </infrastructureServices>
	}
	
	
	private EnvironmentType getEnvironment()
	{
		EnvironmentType env = objFactory.createEnvironmentType();
		env.setType(EnvironmentTypeType.DIRECT);
		env.setSolutionId("testSolution");
		env.setAuthenticationMethod("Basic");
		env.setConsumerName("SAMPLE AU Subscriber");
		
		ApplicationInfoType appInfo = new ApplicationInfoType();
		appInfo.setApplicationKey("TEST_CODE");
		appInfo.setSupportedInfrastructureVersion("3.0");
		appInfo.setDataModelNamespace("http://www.SIFinfo.org/au/datamodel/1.3");
//		appInfo.setSupportedDataModelVersion("3.0");
		appInfo.setTransport("REST");
		
		ProductIdentityType appProd = new ProductIdentityType();
		appProd.setProductName("Test Driver");
		appProd.setProductVersion("0.1alpha");
		appProd.setVendorName("Systemic Pty Ltd");
		appInfo.setApplicationProduct(appProd);
		
		env.setApplicationInfo(appInfo);

		return env;
	}
	
	private String getEnvironmentFromFile(String fileName)
	{
	  return FileReaderWriter.getFileContent(fileName);
	}
	
	private void writeEnvironmentToFile(String envStr, String fileName)
  {
    FileReaderWriter.writeContentToFile(envStr, fileName);
  }
	
	private String getEnvironmentXMLFile()
	{
	  return getEnvironmentFromFile(INPUT_ENV_FILE_NAME_XML);
	}
	
  private String getEnvironmentJSONFile()
  {
    return getEnvironmentFromFile(INPUT_ENV_FILE_NAME_JSON);
  }
  
  private EnvironmentType getEnvironmentFromXMLFile() throws UnmarshalException
  {
    return (EnvironmentType) JAXBUtils.unmarshalFromXMLIntoObject(getEnvironmentXMLFile(), EnvironmentType.class);
  }
  
    private void writeEnvironmentToXMLFile(EnvironmentType environment) throws MarshalException
    {
        writeEnvironmentToFile(JAXBUtils.marshalToXML(objFactory.createEnvironment(environment)), OUTPUT_ENV_FILE_NAME_XML);
    }

  private EnvironmentType getEnvironmentFromJSONFile() throws UnmarshalException
  {
    return (EnvironmentType) JAXBUtils.unmarshalFromJSONIntoObject(getEnvironmentJSONFile(), EnvironmentType.class);
  }

    private void writeEnvironmentToJSONFile(EnvironmentType environment) throws MarshalException
    {
        writeEnvironmentToFile(JAXBUtils.marshalToJSON(objFactory.createEnvironment(environment)), OUTPUT_ENV_FILE_NAME_JSON);
    }
  
	private void testLists() throws MarshalException
	{
		AlertType alert = objFactory.createAlertType();
		alert.setLevel("ERROR");
		alert.setReporter("Joerg");
		alert.setCode(401l);
		alert.setDescription("This is a test Alert XML");
		alert.setError("Error found");
		System.out.println("Single Alert:\n" + JAXBUtils.marshalToXML(objFactory.createAlert(alert)));

		AlertCollectionType alerts = objFactory.createAlertCollectionType();
		alerts.getAlert().add(alert);
		alert = objFactory.createAlertType();
		alert.setLevel("WARNING");
		alert.setReporter("Peter");
		// alert.setCode(401l);
		alert.setDescription("This is a test Alert XML for a Warning");
		// alert.setError("");
		alerts.getAlert().add(alert);

		System.out.println("Multiple Alerts:\n" + JAXBUtils.marshalToXML(objFactory.createAlerts(alerts)));
	}
	
	private String testToXML() throws MarshalException
	{
		EnvironmentType env = getEnvironment();
		JAXBElement<EnvironmentType> envJAXB = objFactory.createEnvironment(env);
		
		//JAXBElement<Environment> envJAXB = new JAXBElement<Environment>(new QName("http://www.sifassociation.org/infrastructure/3.0", "environment"), Environment.class, null, env);

		return JAXBUtils.marshalToXML(envJAXB);
	}
	
	 private String testToJSON() throws MarshalException
	 {
	    EnvironmentType env = getEnvironment();
	    JAXBElement<EnvironmentType> envJAXB = objFactory.createEnvironment(env);
	    
	    return JAXBUtils.marshalToJSON(envJAXB);
	 }
	
	private void testFromXML() throws UnmarshalException, MarshalException
	{
	  String inputEnvXML = FileReaderWriter.getFileContent(INPUT_ENV_FILE_NAME_XML);
	  System.out.println("File content:\n"+inputEnvXML);
	  	   
		EnvironmentType env = (EnvironmentType) JAXBUtils.unmarshalFromXMLIntoObject(inputEnvXML, EnvironmentType.class);
		printEnvironment(env);
		
		String ouputEnvXML = JAXBUtils.marshalToXML(objFactory.createEnvironment(env));
		FileReaderWriter.writeContentToFile(ouputEnvXML, OUTPUT_ENV_FILE_NAME_XML);
		System.out.println("Unmarshalled Environment stored in "+OUTPUT_ENV_FILE_NAME_XML);		
	}
	
//	 private void testFromJSON() throws UnmarshalException, MarshalException
//	  {
//	    String inputEnvXML = FileReaderWriter.getFileContent(INPUT_ENV_FILE_NAME);
//	    System.out.println("File content:\n"+inputEnvXML);
//	         
//	    EnvironmentType env = (EnvironmentType) JAXBUtils.unmarshalFromXMLIntoObject(inputEnvXML, EnvironmentType.class);
//	    printEnvironment(env);
//	    
//	    String ouputEnvXML = JAXBUtils.marshalToXML(objFactory.createEnvironment(env));
//	    FileReaderWriter.writeContentToFile(ouputEnvXML, OUTPUT_ENV_FILE_NAME);
//	    System.out.println("Unmarshalled Environment stored in "+OUTPUT_ENV_FILE_NAME);   
//	  }
			
	
    private void testXMLToObjectToXML() throws UnmarshalException, MarshalException
    {
        EnvironmentType env = getEnvironmentFromXMLFile(); // get environment from file and converts it to object
        writeEnvironmentToXMLFile(env); // converts environment object to XML and writes it to a file.
    }
	
	private void testJSONToObjectToJSON() throws UnmarshalException, MarshalException
	{
    EnvironmentType env = getEnvironmentFromJSONFile(); // get environment from file and converts it to object
    writeEnvironmentToJSONFile(env); //  converts environment object to JSON and writes it to a file.    	    
	}

	private void testXMLToObjectToJSON() throws UnmarshalException, MarshalException
	{
	    EnvironmentType env = getEnvironmentFromXMLFile(); // get environment from file and converts it to object
	    writeEnvironmentToJSONFile(env); //  converts environment object to JSON and writes it to a file.    
	}

    private void testJSONToObjectToXML() throws UnmarshalException, MarshalException
    {
        EnvironmentType env = getEnvironmentFromJSONFile(); // get environment from file and converts it to object
        writeEnvironmentToXMLFile(env); // converts environment object to XML and writes it to a  file.
    }  

	private void testStudentUnicodeString() throws UnmarshalException, MarshalException
	{
	    StudentPersonalType student = (StudentPersonalType)JAXBUtils.unmarshalFromXMLIntoObject(TEST_STUDENT, StudentPersonalType.class);
	    System.out.println("Student Name: " + student.getPersonInfo().getName().getGivenName().getValue());
	}

    private void testStudentUnicodeFile() throws UnmarshalException, MarshalException
    {
        String inputStudentXML = FileReaderWriter.getFileContent(INPUT_STUDENT_FILE_NAME_XML, "UTF-8");
        System.out.println("File content:\n"+inputStudentXML);
        
        StudentPersonalType student = (StudentPersonalType) JAXBUtils.unmarshalFromXMLIntoObject(inputStudentXML, StudentPersonalType.class);
        System.out.println("Student Name: "+ student.getPersonInfo().getName().getGivenName().getValue());
    }
	
	public static void main(String[] args)
	{

		TestJAXBUtils tester = new TestJAXBUtils();
		System.out.println("Start Testing JAXBUtils...");
		try
		{
//			String envStr = tester.testToXML();
//			System.out.println("Environment XML:\n" + envStr);
//
//			tester.testFromXML();
//
//			System.out.println("\n\nTest some Lists:");
//			tester.testLists();
			
//		  String envStrJSON = tester.testToJSON();
//      System.out.println("Environment JSON:\n" + envStrJSON);
		  
		  
		  tester.testXMLToObjectToXML();
//		  tester.testXMLToObjectToJSON();
//			tester.testJSONToObjectToJSON();
//			tester.testJSONToObjectToXML();
//		  tester.testStudentUnicodeString();
//		  tester.testStudentUnicodeFile();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("End Testing JAXBUtils.");
	}
}
