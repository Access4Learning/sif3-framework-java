/*
 * TestGZIPUtils.java
 * Created: 18/06/2015
 *
 * Copyright 2015 Systemic Pty Ltd
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

package systemic.framework.utils.test;

import au.com.systemic.framework.utils.FileReaderWriter;
import au.com.systemic.framework.utils.GZIPUtil;

/**
 * @author Joerg Huber
 *
 */
public class TestGZIPUtils
{
	private final static String BASE_PATH = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraRest/TestData";
	private final static String INPUT_PATH = BASE_PATH+"/inputData/";
	private final static String OUTPUT_PATH = BASE_PATH+"/outputData/";
	
	private final static String EVENT_FILE = "events";
	private final static String STUDENT_FILE = "StudentPersonals";

	private String readStringFileContent(String fileNameWithoutPath)
	{
		return FileReaderWriter.getFileContent(INPUT_PATH+fileNameWithoutPath);
	}
	
	private byte[] readFileContentAsByte(String fileNameWithoutPath)
	{
		return FileReaderWriter.getFileBytes(INPUT_PATH+fileNameWithoutPath);
	}

	private void writeFileContent(byte[] fileContent, String fileNameWithoutPath) throws Exception
	{
		FileReaderWriter.createFile(OUTPUT_PATH+fileNameWithoutPath, fileContent);
	}
	
	public void testZIPString() throws Exception
	{
		System.out.println("Start testZIPString()...");
		String data = readStringFileContent(STUDENT_FILE+".xml");
		if (data != null)
		{
			byte[] zippedData = GZIPUtil.gzipStringToBytes(data);
			writeFileContent(zippedData, STUDENT_FILE+".gzip");
		}
		else
		{
			System.out.println("No data could be read from file.");
		}
		System.out.println("End testZIPString().");
	}
	
	public void testUnZIPToString() throws Exception
	{
		System.out.println("Start testUnZIPToString()...");
		byte[] data = readFileContentAsByte(STUDENT_FILE+".gzip");
		if (data != null)
		{
			String unzippedData = GZIPUtil.ungzipStringFromBytes(data);
//			System.out.println("Data read from ZIP File:\n"+unzippedData);
		}
		else
		{
			System.out.println("No data could be read from file.");
		}
		System.out.println("End testUnZIPToString().");
	}
	
	public static void main(String[] args)
	{
		System.out.println("Start Testing GZIP Utils...");
		TestGZIPUtils tester = new TestGZIPUtils();
		try
		{
			tester.testZIPString();
			tester.testUnZIPToString();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("End Testing GZIP Utils");
	}
}
