/*
 * TestProviderEnvStoreOps.java
 * Created: 17/09/2013
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

package sif3.infra.test.common.env;

import sif3.common.exception.MarshalException;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.env.EnvironmentStore;
import sif3.infra.common.env.ProviderEnvironmentStoreOperations;
import sif3.infra.common.model.ApplicationInfoType;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.ProductIdentityType;

/**
 * @author Joerg Huber
 *
 */
public class TestProviderEnvStoreOps
{
	private static ObjectFactory objFactory = new ObjectFactory();
	private EnvironmentStore envStore = null;
	private ProviderEnvironmentStoreOperations envOps = null;
	
	private static final String SOLUTION_NAME="devLocal";
	private static final String CONSUMER_NAME="MyMobileApp";
	
	public TestProviderEnvStoreOps()
	{
		envStore = EnvironmentStore.getInstance("StudentProvider");
		envOps = new ProviderEnvironmentStoreOperations(envStore);
	}

	private void printEnvironment(EnvironmentType env)
	{
		if (env == null)
		{
			System.out.println("Environment is null!");
		}
		else
		{
			try
			{
				InfraMarshalFactory marshaller = new InfraMarshalFactory();
				System.out.println("Environment as XML:\n"+marshaller.marshalToXML(env));
			}
			catch (MarshalException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	private EnvironmentType getInputEnvironment()
	{
		EnvironmentType env = objFactory.createEnvironmentType();
		env.setType("DIRECT");
//		env.setSolutionId("auTestSolution");
    env.setSolutionId(SOLUTION_NAME);
		env.setAuthenticationMethod("Basic");
		env.setConsumerName("SAMPLE AU Subscriber");
		
		ApplicationInfoType appInfo = new ApplicationInfoType();
//		appInfo.setApplicationKey("StudentConsumer");
    appInfo.setApplicationKey(CONSUMER_NAME);
		appInfo.setSupportedInfrastructureVersion("3.0");
		appInfo.setSupportedDataModel("SIF-AU");
		appInfo.setSupportedDataModelVersion("3.0");
		appInfo.setTransport("REST");
		
		ProductIdentityType appProd = new ProductIdentityType();
		appProd.setProductName("Test Driver");
		appProd.setProductVersion("0.1alpha");
		appProd.setVendorName("Systemic Pty Ltd");
		appInfo.setApplicationProduct(appProd);
		
		env.setApplicationInfo(appInfo);
		
		return env;
	}

	
	private void testExistEnvironmentTemplate()
	{
		System.out.println("Does "+SOLUTION_NAME+" exist in template directory: "+envOps.existEnvironmentTemplate(SOLUTION_NAME));
	}
	
	private void testLoadEnvironmentTemplate()
	{
		printEnvironment(envOps.loadEnvironmentFromTemplate(SOLUTION_NAME));
	}

	private void testLoadEnvironmentForConsumer()
	{
		printEnvironment(envOps.loadEnvironmentForConsumer(SOLUTION_NAME, CONSUMER_NAME, false));
	}

	private void testRemoveEnvironmentForConsumer()
	{
		System.out.println("Environmen "+SOLUTION_NAME+" for consumer "+CONSUMER_NAME+" deleted: "+envOps.deleteEnvironmentForConsumer(SOLUTION_NAME, CONSUMER_NAME));
	}

	private void testCreateEnvironmentForConsumer()
	{
		EnvironmentType inputEnv = getInputEnvironment();
		printEnvironment(envOps.createAndStoreEnvForConsumer(inputEnv, false));
	}

	public static void main(String[] args)
	{
		TestProviderEnvStoreOps tester = new TestProviderEnvStoreOps();
		
		System.out.println("Start Testing ProviderEnvironmentStoreOperations...");
		
//		tester.testExistEnvironmentTemplate();
//		tester.testLoadEnvironmentTemplate();
//		tester.testLoadEnvironmentForConsumer();
		tester.testCreateEnvironmentForConsumer();
//		tester.testRemoveEnvironmentForConsumer();
		
		System.out.println("End Testing ProviderEnvironmentStoreOperations.");
	}
}
