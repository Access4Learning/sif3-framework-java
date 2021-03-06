/*
 * TestEnvStore.java
 * Created: 27/08/2013
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

package sif3.infra.common.test.env;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.MarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.persist.common.HibernateUtil;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.env.ops.ConsumerEnvironmentStoreOperations;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.JobType;


/**
 * @author Joerg Huber
 *
 */
public class TestConsumerEnvStoreOps extends TestBase
{
	private static final AdapterType CONSUMER = AdapterType.CONSUMER;
	private static final sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType DIRECT = sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType.DIRECT;
	
	private ConsumerEnvironmentStoreOperations envOps = null;
	
	private static final String TEMPLATE_FILE_NAME="devLocal.xml";
	private static final String JOB_TEMPLATE_FILE_NAME="rolloverStudentJob.xml";
	private static final String SESSION_TOKEN="12341234-5678-4123-1234-123412345678";
	//private static final String SESSION_TOKEN="e88da655-9ca6-41c3-aa21-ec467ed84437";
	//private static final String SESSION_TOKEN="98154bc3-d4b3-427d-bf40-58ed9705f6de";
	
	private static final String ENV_ID="ABCD1234-5678-4ABC-ABCD-ABCD12345678";
	//private static final String ENV_ID="cdefe8d2-875f-4948-887b-32be15d541a7";
	//private static final String ENV_ID="c97da483-a0c4-4c2d-81db-1855e8eff518";
	
	private static final String SERVICE_NAME="StudentConsumer";
	
	public TestConsumerEnvStoreOps()
	{
//        HibernateUtil.initialise(null);
        super();
		envOps = new ConsumerEnvironmentStoreOperations(SERVICE_NAME);
	}
	
	
	private void testExistTemplateEnvironment()
	{
		System.out.println("Template XML for "+TEMPLATE_FILE_NAME+" exists: "+envOps.existEnvironmentTemplate(TEMPLATE_FILE_NAME, CONSUMER, DIRECT));
	}
	
    private void testExistJobTemplate()
    {
        System.out.println("Job Template XML for "+JOB_TEMPLATE_FILE_NAME+" exists: "+envOps.existJobTemplate(JOB_TEMPLATE_FILE_NAME, CONSUMER, DIRECT));
    }

	
//	private void testExistWorkstoreEnvBySessionTK() throws IllegalArgumentException, PersistenceException
//	{
//		System.out.println("Workstore XML for Session Token "+SESSION_TOKEN+" exists: "+envOps.existEnvInWorkstoreBySessionToken(SESSION_TOKEN));
//	}
	
	private void testLoadTemplateEnvironment()
	{
	    printInfraObjectAsXML(envOps.loadTemplateEnvironmentData(TEMPLATE_FILE_NAME, CONSUMER, DIRECT));
	}
	
	private void testLoadJobTemplate()
	{
	    printInfraObjectAsXML(envOps.loadJobTemplateData(JOB_TEMPLATE_FILE_NAME, CONSUMER, DIRECT));
    }

	
//	private void testLoadWorkstoreEnvBySessionTK()
//	{
//		printEnvironment(envOps.loadWorkstoreEnvBySessionToken(SESSION_TOKEN));
//	}


	private void testStoreEnvironmentToWorkstore()
	{
		EnvironmentType env = envOps.loadTemplateEnvironmentData(TEMPLATE_FILE_NAME, CONSUMER, DIRECT);
		env.setConsumerName("TestConsumer");
		env.setSolutionId("test");
		env.getApplicationInfo().setApplicationKey("MY_APP");
		env.setAuthenticationMethod("SAML");
		env.setId(ENV_ID);
		env.setSessionToken(SESSION_TOKEN);
//		SIF3Session session = new SIF3Session(new EnvironmentKey("test", "MY_APP"));
//		session.setAdapterName(SERVICE_NAME);
//		session.setSessionToken(SESSION_TOKEN);
//		session.setEnvironmentID(ENV_ID);
//		session.setAdapterType(AdapterType.CONSUMER.name());
		System.out.println("Environment Stored: "+envOps.createOrUpdateSession(env, null));
	}
	
	private void testRemoveEnvFromWorkstoreBySessionTK()
	{
		System.out.println("Environment Removed: "+envOps.removeEnvFromWorkstoreBySessionToken(SESSION_TOKEN));
	}

	public static void main(String[] args)
	{
		TestConsumerEnvStoreOps tester = new TestConsumerEnvStoreOps();
		
		try
		{
			System.out.println("Start Testing ConsumerEnvironmentStoreOperations...");
			
//			tester.testExistTemplateEnvironment();
//          tester.testExistJobTemplate();
//			tester.testExistWorkstoreEnvBySessionTK();
//			tester.testLoadTemplateEnvironment();
	         tester.testLoadJobTemplate();
//			tester.testLoadWorkstoreEnvBySessionTK();
//			tester.testStoreEnvironmentToWorkstore();
//			tester.testRemoveEnvFromWorkstoreBySessionTK();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
		    tester.shutdown();
		}
		System.out.println("End Testing ConsumerEnvironmentStoreOperations.");
	}
}
