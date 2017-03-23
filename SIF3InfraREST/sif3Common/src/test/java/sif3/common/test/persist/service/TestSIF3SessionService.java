/*
 * TestSIF3SessionService.java
 * Created: 04/02/2014
 *
 * Copyright 2014 Systemic Pty Ltd
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

package sif3.common.test.persist.service;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.model.EnvironmentKey;
import sif3.common.persist.model.SIF3Session;
import sif3.common.persist.service.SIF3SessionService;
import au.com.systemic.framework.utils.FileReaderWriter;

/**
 * @author Joerg Huber
 *
 */
public class TestSIF3SessionService extends ServiceBaseTest
{
	private static final String SOLUTION_ID = "TEST";
	private static final String APPLICATION_KEY = "SIS_TEST";
	private static final String USER_TOKEN = "jhuber:test";
	private static final String INSTANCE_ID = null;
//	private static final String INSTANCE_ID = "1234567890-0987654321";
	
	private static final String SESSION_TOKEN = "fa224931-9425-45e1-a7a1-432eb178e76e";
	private static final String ENVIRONMENT_ID = "431889c2-97dc-48bd-9acb-72b9c30b044c";
	private static final String SECURITY_TOKEN = "OAUTH-TOKEN:1234";
//	private static final String ENVIRONMENT_ID = "1f8d7a78-74f1-4757-8ce3-db471ecdf1eb";
	private static final AdapterType ADAPTER_TYPE = AdapterType.CONSUMER;
	
	private static final EnvironmentKey ENV_KEY = new EnvironmentKey(SOLUTION_ID, APPLICATION_KEY, USER_TOKEN, INSTANCE_ID); 

	private final static String INPUT_ENV_FILE_NAME  = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST/TestData/xml/input/environment_large.xml";
	
	private SIF3SessionService service = new SIF3SessionService();

	public void testGetSessionBySolutionAppkeyUserInst() throws Exception
	{
		SIF3Session session = service.getSessionBySolutionAppkeyUserInst(ENV_KEY, ADAPTER_TYPE);
		System.out.println("Session: "+session);
	}
	
	public void testCreateNewSession() throws Exception
	{
		//SIF3Session session = service.createNewSession(SOLUTION_ID, APPLICATION_KEY, SIF3Session.EnvironmentType.CONSUMER, USER_TOKEN, null);
		SIF3Session session = service.createNewSession(ENV_KEY, ADAPTER_TYPE);
		System.out.println("Session: "+session);
	}

	public void testCreateNewSessionWithXML() throws Exception
	{
		String inputEnvXML = FileReaderWriter.getFileContent(INPUT_ENV_FILE_NAME);
		SIF3Session session = new SIF3Session(ENV_KEY);
		session.setAdapterType(ADAPTER_TYPE.name());
		session.setPassword("testPWD");
		session.setEnvironmentXML(inputEnvXML);
		
		SIF3Session newSession = service.createNewSession(session);
		System.out.println("Session: "+newSession);
	}

	public void testGetSessionByEnvID() throws Exception
	{
		SIF3Session session = service.getSessionByEnvID(ENVIRONMENT_ID, ADAPTER_TYPE);
		System.out.println("Session: "+session);
	}

	public void testGetSessionBySessionToken() throws Exception
	{
		SIF3Session session = service.getSessionBySessionToken(SESSION_TOKEN, ADAPTER_TYPE);
		System.out.println("Session: "+session);
	}

  public void testGetSessionBySecurityToken() throws Exception
  {
    SIF3Session session = service.getSessionBySecurityToken(SECURITY_TOKEN, ADAPTER_TYPE);
    System.out.println("Session: "+session);
  }

  public void testRemoveSessionByEnvironmentID() throws Exception
	{
		service.removeSessionByEnvironmentID(ENVIRONMENT_ID, ADAPTER_TYPE);
		System.out.println("Session Removed.");
	}

	public void testSave() throws Exception
	{
		SIF3Session session = service.getSessionBySessionToken(SESSION_TOKEN, ADAPTER_TYPE);
		session.setAdapterName("MyConsumer");
		service.save(session);
		System.out.println("Session: "+session);
	}

	public static void main(String[] args)
    {
		System.out.println("================================== Start TestSIF3SessionService ===============================");
		try
        {
			TestSIF3SessionService tester = new TestSIF3SessionService();
//			tester.testGetSessionBySolutionAppkeyUserInst();
//			tester.testCreateNewSession();
			//tester.testCreateNewSessionWithXML();
			tester.testGetSessionByEnvID();
//			tester.testGetSessionBySessionToken();
//			tester.testGetSessionBySecurityToken();
//			tester.testRemoveSessionByEnvironmentID();
			//tester.testSave();
       
        	tester.shutdown();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
		System.out.println("================================== End TestSIF3SessionService ===============================");
    }
}
