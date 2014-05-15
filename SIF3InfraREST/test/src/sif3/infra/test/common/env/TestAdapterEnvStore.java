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

import sif3.infra.common.env.types.AdapterEnvironmentStore;


/**
 * @author Joerg Huber
 *
 */
public class TestAdapterEnvStore
{
	private AdapterEnvironmentStore testGetEnvironmentStore(String propFileName)
	{
		AdapterEnvironmentStore envStore = new AdapterEnvironmentStore(propFileName);
		System.out.println("Adapter Environment Store for "+propFileName+":\n"+envStore);
		return envStore;
	}
	
	
	
	public static void main(String[] args)
	{
		TestAdapterEnvStore tester = new TestAdapterEnvStore();
		
		System.out.println("Start Testing AdapterEnvironmentStore...");
		
		//tester.testGetEnvironmentStore("StudentProvider");
    	tester.testGetEnvironmentStore("StudentConsumer");
		
		System.out.println("End Testing AdapterEnvironmentStore.");
	}
}
