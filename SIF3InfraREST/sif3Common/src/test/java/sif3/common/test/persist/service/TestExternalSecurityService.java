/*
 * TestExternalSecurityService.java
 * Created: 31/03/2017
 *
 * Copyright 2017 Systemic Pty Ltd
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

import java.util.List;

import sif3.common.persist.service.ExternalSecurityService;

/**
 * @author Joerg Huber
 *
 */
public class TestExternalSecurityService extends ServiceBaseTest
{
	
	private ExternalSecurityService service = new ExternalSecurityService();

	public void testGetAllServices() throws Exception
	{
	    List<sif3.common.persist.model.ExternalSecurityService> secServices = service.getSecurityServices();
		System.out.println("Exetrnal Security Services:\n"+secServices);
	}
	
    public static void main(String[] args)
    {
        System.out.println("================================== Start TestExternalSecurityService ===============================");
        try
        {
            TestExternalSecurityService tester = new TestExternalSecurityService();
            tester.testGetAllServices();

            tester.shutdown();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("================================== End TestExternalSecurityService ===============================");
    }
}
