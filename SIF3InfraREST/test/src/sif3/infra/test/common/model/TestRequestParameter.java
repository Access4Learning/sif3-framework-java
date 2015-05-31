/*
 * TestRequestParameter.java
 * Created: 26/05/2015
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

package sif3.infra.test.common.model;

import sif3.common.header.HeaderProperties;
import sif3.common.model.RequestParameters;


/**
 * @author Joerg Huber
 *
 */
public class TestRequestParameter
{
	public static void main(String[] args)
	{
		RequestParameters reqParams = new RequestParameters();
		reqParams.addHTTPHeaderParameter("TestParamMixedCase", "ABCdef");
		reqParams.addHTTPHeaderParameter("testparamMixedCase", "abcDEF");
		
		System.out.println("Is HTTP Param equal to 'abcDEF': "+"abcDEF".equals(reqParams.getHTTPParameter("testparammixedcase")));

		reqParams.addURLQueryParameter("TestParamMixedCase", "efgHIJ");
		reqParams.addURLQueryParameter("TestParammixedcase", "EFGhij");

		System.out.println("Is URL Query Param equal to 'efgHIJ': "+"efgHIJ".equals(reqParams.getURLQueryParameter("TestParamMixedCase")));
		System.out.println("Is URL Query Param equal to 'EFGhij': "+"EFGhij".equals(reqParams.getURLQueryParameter("TestParammixedcase")));

		System.out.println("Is Param using HTTP and equal to 'abcDEF': "+"abcDEF".equals(reqParams.getRequestParameter("TestParammixedcase")));
	
		System.out.println("Exists HTTP 'TestParamMixedCase': "+reqParams.existHTTPParameter("TestParamMixedCase"));
		System.out.println("Exists URL Query 'TestParamMixedCase': "+reqParams.existURLQueryParameter("TestParamMixedCase"));
		System.out.println("Exists URL Query 'testparamMixedCase': "+reqParams.existURLQueryParameter("testparamMixedCase"));
		
		System.out.println("All Values\n"+reqParams);
		
		reqParams.setHttpHeaderParams(null);
		System.out.println("All Values\n"+reqParams);
		
		HeaderProperties hdrProps = new HeaderProperties();
		hdrProps.setHeaderProperty("TestName", "value1");
		hdrProps.setHeaderProperty("Testname", "value2");
		hdrProps.setHeaderProperty("TestName3", "value3");
		hdrProps.setHeaderProperty("TestName4", "value4");
		reqParams.setHttpHeaderParams(hdrProps);
		System.out.println("All Values\n"+reqParams);
		
	}
}
