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

package sif3.infra.test.common.utils;

import sif3.common.utils.JSONUtils;
import au.com.systemic.framework.utils.FileReaderWriter;

/**
 * @author Joerg Huber
 *
 */
public class TestJSONUtils
{
	
//	private final static String INPUT_XML_FILE_NAME  = "C:/DEV/eclipseWorkspace/SIF3InfraREST/TestData/xml/input/environment_play.xml";

	private final static String INPUT_XML_FILE_NAME  = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST/TestData/xml/input/environment_play.xml";

	private final static String TEST_STR = "<environment xmlns=\"http://www.sifassociation.org/infrastructure/3.0.1\" type=\"DIRECT\">"+
	                                       "<provisionedZones>"+
	                                          "<provisionedZone id=\"auSchoolTestingZone\">"+
      	                                       "<services>"+
      	                                          "<service name=\"alerts\" contextId=\"DEFAULT\" type=\"UTILITY\">"+
      	                                             "<rights>"+
      	                                                "<right type=\"QUERY\">APPROVED</right>"+
      	                                                "<right type=\"CREATE\">APPROVED</right>"+
      	                                             "</rights>"+
      	                                          "</service>"+   
      	                                       "</services>"+
      	                                    "</provisionedZone>"+
                                         "</provisionedZones>"+
                                         "</environment>";
	
//    private final static String TEST_STR = "<services xmlns=\"http://www.sifassociation.org/infrastructure/3.0.1\">"
//                                             + "<rights>"
//                                               + "<right type=\"QUERY\">APPROVED</right>"
//                                               + "<right type=\"CREATE\">APPROVED</right>"
//                                             + "</rights>"
//                                         + "</services>";
	
//	 private final static String TEST_STR = "<StudentPersonal xmlns=\"http://www.sifinfo.org/au/infrastructure/2.x\">"+
//	                                            "<AlertMessages>"+
//	                                                 "<AlertMessage type=\"Other\">Alert Msg 1</AlertMessage>"+
//	                                                 "<AlertMessage type=\"Educational\">Educationla Alert Message 1</AlertMessage>"+
//	                                            "</AlertMessages>"+
//	                                         "</StudentPersonal>";

//   private final static String TEST_STR = "<StudentPersonal>"+
//                                             "<AlertMessages>"+
//                                                  "<AlertMessage type=\"Other\">Alert Msg 1</AlertMessage>"+
//                                                  "<AlertMessage type=\"Educational\">Educationla Alert Message 1</AlertMessage>"+
//                                             "</AlertMessages>"+
//                                          "</StudentPersonal>";
	
  public void testToJSONFromFile() throws Exception
  {
    String inputXML = FileReaderWriter.getFileContent(INPUT_XML_FILE_NAME);
    System.out.println("File content:\n"+inputXML);
    
    System.out.println("JSON:\n"+JSONUtils.toJSON(inputXML));
  }
			
  public void testToJSONFromString() throws Exception
  {
    System.out.println("String content:\n"+TEST_STR);
    System.out.println("JSON:\n"+JSONUtils.toJSON(TEST_STR));
  }

  public void testFromJSONFromFile() throws Exception
  {
    String inputXML = FileReaderWriter.getFileContent(INPUT_XML_FILE_NAME);
    String inputJSON = JSONUtils.toJSON(inputXML);
    System.out.println("JSON:\n"+inputJSON);
    System.out.println("XML:\n"+JSONUtils.fromJSON(inputJSON));
  }
  
  public void testFromJSONFromString() throws Exception
  {
    String inputJSON = JSONUtils.toJSON(TEST_STR);
    System.out.println("JSON:\n"+inputJSON);
    System.out.println("XML:\n"+JSONUtils.fromJSON(inputJSON));
  }

  public static void main(String[] args)
	{

		TestJSONUtils tester = new TestJSONUtils();
		System.out.println("Start Testing JSONUtils...");
		try
		{
//		  tester.testToJSONFromString();
//		  tester.testFromJSONFromString();
//			tester.testToJSONFromFile();
			tester.testFromJSONFromFile();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("End Testing JSONUtils.");
	}
}
