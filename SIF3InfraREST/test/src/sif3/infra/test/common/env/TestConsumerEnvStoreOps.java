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

package sif3.infra.test.common.env;

import sif3.common.exception.MarshalException;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.env.EnvironmentStore;
import sif3.infra.common.env.ConsumerEnvironmentStoreOperations;
import sif3.infra.common.model.EnvironmentType;


/**
 * @author Joerg Huber
 *
 */
public class TestConsumerEnvStoreOps
{
	private EnvironmentStore envStore = null;
	private ConsumerEnvironmentStoreOperations envOps = null;
	
	private static final String SOLUTION_NAME="auTestSolution";
  private static final String SERVICE_NAME="StudentConsumer";
	
	public TestConsumerEnvStoreOps()
	{
		envStore = EnvironmentStore.getInstance(SERVICE_NAME);
		envOps = new ConsumerEnvironmentStoreOperations(envStore);
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
	
	private void testExistInputEnvironment()
	{
		System.out.println("Input XML for "+SOLUTION_NAME+" exists: "+envOps.existInputEnvironment(SOLUTION_NAME));
	}
	
	private void testExistOutputEnvironment()
	{
		System.out.println("Output XML for "+SOLUTION_NAME+" exists: "+envOps.existOutputEnvironment(SOLUTION_NAME));
	}
	
	private void testLoadInputEnvironment()
	{
		printEnvironment(envOps.loadInputEnvironmentData(SOLUTION_NAME));
	}
	
	private void testLoadOutputEnvironment()
	{
		printEnvironment(envOps.loadOutputEnvironmentData(SOLUTION_NAME));
	}

	private void testStoreOutputEnvironment()
	{
		System.out.println("Environment Stored: "+envOps.storeOutputEnvironmentData("joergSolution", envOps.loadInputEnvironmentData(SOLUTION_NAME)));
	}
	
	private void testRemoveOutputEnvironment()
	{
		System.out.println("Environment Removed: "+envOps.removeOutputEnvironmentData("joergSolution"));
	}

	public static void main(String[] args)
	{
		TestConsumerEnvStoreOps tester = new TestConsumerEnvStoreOps();
		
		System.out.println("Start Testing ConsumerEnvironmentStoreOperations...");
		
//		tester.testExistInputEnvironment();
//		tester.testExistOutputEnvironment();
//		tester.testLoadInputEnvironment();
//		tester.testLoadOutputEnvironment();
//		tester.testStoreOutputEnvironment();
		tester.testRemoveOutputEnvironment();
		
		System.out.println("End Testing ConsumerEnvironmentStoreOperations.");
	}
}
