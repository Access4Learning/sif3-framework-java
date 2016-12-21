/*
 * TestAppEnvTemplateService.java
 * Created: 30/06/2014
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

import sif3.common.model.EnvironmentKey;
import sif3.common.persist.model.AppEnvironmentTemplate;
import sif3.common.persist.service.AppEnvironmentService;

/**
 * @author Joerg Huber
 *
 */
public class TestAppEnvTemplateService extends ServiceBaseTest
{
//	private static final String SOLUTION_ID = "test";
  private static final String SOLUTION_ID = null;
	private static final String APPLICATION_KEY = "TEST_SIS";
//	private static final String USER_TOKEN = "jhuber:test";
  private static final String USER_TOKEN = null;
	private static final String INSTANCE_ID = null;
//	private static final String INSTANCE_ID = "IPAD";
	
	
	private static final EnvironmentKey ENV_KEY = new EnvironmentKey(SOLUTION_ID, APPLICATION_KEY, USER_TOKEN, INSTANCE_ID); 

	
	private AppEnvironmentService service = new AppEnvironmentService();

	public void testGetEnvironmentTemplate() throws Exception
	{
	  AppEnvironmentTemplate appEnvTemplate = service.getEnvironmentTemplate(ENV_KEY);
		System.out.println("Application Environment Template Info: "+appEnvTemplate);
	}
	
	public static void main(String[] args)
    {
		System.out.println("================================== Start TestAppEnvTemplateService ===============================");
    try
    {
      TestAppEnvTemplateService tester = new TestAppEnvTemplateService();
      tester.testGetEnvironmentTemplate();

      tester.shutdown();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
		System.out.println("================================== End TestAppEnvTemplateService ===============================");
    }
}
