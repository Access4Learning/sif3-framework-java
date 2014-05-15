/*
 * EnvironmentDemo.java
 * Created: 31/07/2013
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

package systemic.sif3.demo.infra.basic;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import sif3.infra.common.model.ApplicationInfoType;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.ProductIdentityType;


/**
 * @author Joerg Huber
 *
 */
public class EnvironmentDemo
{
	  private static ObjectFactory objFactory = new ObjectFactory();
	  
	  private static String getEnvironmentAsXML(EnvironmentType env)
	  {

		StringWriter sw = new StringWriter();
		try
		{
			Marshaller marshaller = JAXBContext.newInstance(env.getClass()).createMarshaller();

			// Don't generate <?xml version="1.0" encoding="UTF-8" standalone="yes"?> in output
			marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);

			// Format output nicely
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// marshaller.marshal(personInfo, sw);
			//JAXBElement<Environment> envJAXB = new JAXBElement<Environment>(new QName("http://www.sifassociation.org/infrastructure/3.0", "environment"), Environment.class, null, env);
			
			JAXBElement<EnvironmentType> envJAXB = objFactory.createEnvironment(env);
				
			marshaller.marshal(envJAXB, sw);
		}
		catch (JAXBException e)
		{
			throw new RuntimeException(e);
		}

		return sw.toString();
	  }
	
	
	private static void printEnvironmentXML()
	{
		EnvironmentType env = objFactory.createEnvironmentType();
		env.setSolutionId("testSolution");
		env.setAuthenticationMethod("Basic");
		env.setConsumerName("SAMPLE AU Subscriber");
		
		ApplicationInfoType appInfo = new ApplicationInfoType();
		appInfo.setApplicationKey("TEST_CODE");
		appInfo.setSupportedInfrastructureVersion("3.0");
//		appInfo.setSupportedDataModel("SIF-AU");
		appInfo.setDataModelNamespace("http://www.SIFinfo.org/au/datamodel/1.3");
		appInfo.setTransport("REST");
		
		ProductIdentityType appProd = new ProductIdentityType();
		appProd.setProductName("Test Driver");
		appProd.setProductVersion("0.1alpha");
		appProd.setVendorName("Systemic Pty Ltd");
		appInfo.setApplicationProduct(appProd);
		
		env.setApplicationInfo(appInfo);
		
		System.out.println("Environment XML:\n"+getEnvironmentAsXML(env));
	}
	
	public static void main(String[] args)
	{
		printEnvironmentXML(); 
	}
}
