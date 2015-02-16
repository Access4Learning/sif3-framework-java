/*
 * TestMarshallers.java
 * Created: 26/09/2013
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

package sif3.dm.test.conversion;

import sif.dd.au30.conversion.DataModelMarshalFactory;
import sif.dd.au30.conversion.DataModelUnmarshalFactory;
import sif.dd.au30.model.StudentPersonalType;
import sif3.common.utils.JAXBUtils;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.test.common.utils.TestJAXBUtils;
import au.com.systemic.framework.utils.FileReaderWriter;
import au.com.systemic.framework.utils.Timer;

/**
 * @author Joerg Huber
 *
 */
public class TestMarshallers
{
//	private final static String INPUT_STUDENT_FILE_NAME  = "C:/DEV/eclipseWorkspace/SIF3InfraREST/TestData/xml/input/StudentPersonal.xml";
	private final static String INPUT_STUDENT_FILE_NAME  = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST/TestData/xml/input/StudentPersonal.xml";

	private DataModelUnmarshalFactory unmarshaller = new DataModelUnmarshalFactory();
	private DataModelMarshalFactory marshaller = new DataModelMarshalFactory();


	private void testPerformance()
	{
		try
		{
			String inputEnvXML = FileReaderWriter.getFileContent(INPUT_STUDENT_FILE_NAME);
			Timer timer = new Timer();
			timer.start();
			StudentPersonalType student = (StudentPersonalType)unmarshaller.unmarshalFromXML(inputEnvXML, StudentPersonalType.class);
			timer.finish();
			System.out.println("Time Taken to unmarshal: "+timer.timeTaken()+"ms");
			
			timer.start();
			String ouputEnvXML = marshaller.marshalToXML(student);
			timer.finish();
			System.out.println("Time Taken to marshal: "+timer.timeTaken()+"ms");
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{

		TestMarshallers tester = new TestMarshallers();
		System.out.println("Start Testing TestMarshallers...");
		try
		{
			tester.testPerformance();
			tester.testPerformance();
			tester.testPerformance();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("End Testing TestMarshallers.");
	}
}
