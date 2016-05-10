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

import java.util.Date;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.MarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.model.EnvironmentKey;
import sif3.common.persist.service.SIF3SessionService;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.env.ops.DirectProviderEnvStoreOps;
import sif3.infra.common.model.ApplicationInfoType;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.ProductIdentityType;

/**
 * @author Joerg Huber
 *
 */
public class TestDirectProviderEnvStoreOps
{
	private static final long MINUTE = 1000*60;
	private static final AdapterType PROVIDER = AdapterType.ENVIRONMENT_PROVIDER;
//	private static final sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType DIRECT = sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType.DIRECT;
	
	private DirectProviderEnvStoreOps envOps = null;
	private SIF3SessionService service = new SIF3SessionService();
	
	private static final String TEMPLATE_FILE_NAME="systemicDemoBrokerResponse.xml";
	private static final String SERVICE_NAME="StudentProvider";

	private static ObjectFactory objFactory = new ObjectFactory();
	
	private static final String SOLUTION_NAME="systemicDemo";
	private static final String APPLICATION_KEY="DemoFramework";

	private static final String SESSION_TOKEN="d4e10ac8-018a-42b6-9483-358c247e015b";
	
	private static final String ENV_ID="279cce2e-1334-45fb-b011-cb498bfdeea0";
	
	private static final boolean USE_HTTPS = false;

	public TestDirectProviderEnvStoreOps()
	{
		envOps = new DirectProviderEnvStoreOps(SERVICE_NAME);
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
      catch (UnsupportedMediaTypeException ex)
      {
        ex.printStackTrace();
      }
		}
	}

	private EnvironmentType getInputEnvironment()
	{
		EnvironmentType env = objFactory.createEnvironmentType();
		env.setSolutionId(SOLUTION_NAME);
		env.setAuthenticationMethod("Basic");
		env.setConsumerName("SAMPLE AU Provider");
		
		ApplicationInfoType appInfo = new ApplicationInfoType();
		appInfo.setApplicationKey(APPLICATION_KEY);
		appInfo.setSupportedInfrastructureVersion("3.0");
		appInfo.setDataModelNamespace("http://www.SIFinfo.org/au/datamodel/1.3");
		appInfo.setTransport("REST");
		
		ProductIdentityType appProd = new ProductIdentityType();
		appProd.setProductName("Test Driver: Provider");
		appProd.setProductVersion("0.1alpha");
		appProd.setVendorName("Systemic Pty Ltd");
		appInfo.setApplicationProduct(appProd);
		
		env.setApplicationInfo(appInfo);
		
		return env;
	}

	
	private void testExistEnvironmentTemplate()
	{
		System.out.println("Does "+TEMPLATE_FILE_NAME+" exist in template directory: "+envOps.existEnvironmentTemplate(TEMPLATE_FILE_NAME));
	}
	
	private void testLoadEnvironmentTemplate()
	{
		printEnvironment(envOps.loadEnvironmentFromTemplate(TEMPLATE_FILE_NAME));
	}

	private void testExistEnvInWorkstore()
	{
		try
        {
	        System.out.println("Does Environment Exist: "+envOps.existEnvInWorkstore(new EnvironmentKey(SOLUTION_NAME, APPLICATION_KEY, "", null)));
        }
        catch (Exception ex)
        {
	        ex.printStackTrace();
        }
	}
	
	private void testLoadEnvironmentFromWorkstore()
	{
		try
        {
	        printEnvironment(envOps.loadEnvironmentFromWorkstore(new EnvironmentKey(SOLUTION_NAME, APPLICATION_KEY, null, null), null, USE_HTTPS));
        }
        catch (Exception ex)
        {
	        ex.printStackTrace();
        }
	}

	private void testRemoveEnvFromWorkstoreByEnvID()
	{
		System.out.println("Environment ID "+ENV_ID+" deleted: "+envOps.removeEnvFromWorkstoreByEnvID(ENV_ID));
	}

	private void testRemoveEnvFromWorkstoreBySessionToken()
	{
		System.out.println("Environment with SessionToken = "+ENV_ID+" deleted: "+envOps.removeEnvFromWorkstoreBySessionToken(SESSION_TOKEN));
	}

	private void testCreateEnvironment()
	{
		EnvironmentType inputEnv = envOps.loadEnvironmentFromTemplate(TEMPLATE_FILE_NAME); // use this if BROKERED
//		EnvironmentType inputEnv = getInputEnvironment(); // use this for DIRECT
		printEnvironment(envOps.createEnvironmentAndSession(inputEnv, null, USE_HTTPS));
	}

	private void testCreateSession()
	{
		EnvironmentType inputEnv = envOps.loadEnvironmentFromTemplate(TEMPLATE_FILE_NAME); // use this if BROKERED
//		EnvironmentType inputEnv = getInputEnvironment(); // use this for DIRECT
		
		System.out.println("Session:\n" + envOps.createSession(inputEnv, null, USE_HTTPS));
	}
	
	private void testUpdateSessionSecurityInfo()
	{
		long expireTime = MINUTE;
		try
		{
			String securityToken = "oauth-123";
			Date securityExpiryDate = new Date(((new Date()).getTime())+expireTime);
			boolean success = envOps.updateSessionSecurityInfo(SESSION_TOKEN, securityToken, securityExpiryDate);
			if (success)
			{
				System.out.println(envOps.getSIF3SessionBySessionToken(SESSION_TOKEN, PROVIDER, service));
			}
			else
			{
				System.out.println("Failed to update Security Info for Session: "+SESSION_TOKEN);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		TestDirectProviderEnvStoreOps tester = new TestDirectProviderEnvStoreOps();
		
		System.out.println("Start Testing ProviderEnvironmentStoreOperations...");
		
//		tester.testExistEnvironmentTemplate();
//		tester.testLoadEnvironmentTemplate();
//		tester.testExistEnvInWorkstore();
//		tester.testLoadEnvironmentFromWorkstore();
//		tester.testCreateEnvironment();
//		tester.testCreateSession();
//		tester.testRemoveEnvFromWorkstoreByEnvID();
//		tester.testRemoveEnvFromWorkstoreBySessionToken();
		tester.testUpdateSessionSecurityInfo();
		
		System.out.println("End Testing ProviderEnvironmentStoreOperations.");
	}
}
