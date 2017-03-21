/*
 * TestAuthUtils.java
 * Created: 07/01/2014
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

package sif3.common.test.utils;

import java.util.Date;

import sif3.common.model.AuthenticationInfo;
import sif3.common.utils.AuthenticationUtils;
import au.com.systemic.framework.utils.DateUtils;

/**
 * @author Joerg Huber
 *
 */
public class TestAuthUtils
{
	private void testGetBasicAuthToken()
	{
//		System.out.println("Basic Auth String: "+AuthenticationUtils.getBasicAuthToken("new", "guest"));	
    System.out.println("Basic Auth String: "+AuthenticationUtils.getBasicAuthToken("2f47fd0c-fbc5-4a6e-a779-de7633624a67", "Password1"));  
	}

//	 private void testGetBearerAuthToken()
//	  {
//	    System.out.println("Basic Auth String: "+AuthenticationUtils.getBearerAuthToken("2f47fd0c-fbc5-4a6e-a779-de7633624a67", "Password1"));  
//	  }

	
	private void testHMACSHA256()
	{
//		String message = "Message";
//		String key = "secret";
//		String message = "new:2013-06-22T23:52-07";
//		String key = "guest";
		String message = "TestSIS:2015-01-12T13:10:00.000Z";
		String key = "guest";

		long startTime = (new Date()).getTime();
		String hash = AuthenticationUtils.hmacsha256Base64(message, key);
		long endTime = (new Date()).getTime();

		System.out.println("HMACSHA256 String: " + hash + " Time taken to compute: "+(endTime-startTime)+" millis.");
	}

	private void testHMACSHA256Stress()
	{
		String message = "new:";
		String key = "guest";

		long startTime = (new Date()).getTime();
		for (int i=0; i<1000; i++)
		{
			String hash = AuthenticationUtils.hmacsha256Base64(message+i, key);
			System.out.println("HMACSHA256 String for "+(message+i)+": " + hash);			
		}
		
		long endTime = (new Date()).getTime();

		System.out.println("Time taken to compute: "+(endTime-startTime)+" millis.");
	}
	
	private void testHMACSHA256Token()
	{
//		String username = "TestSIS";
		String username = "d394e915-ebb0-4c22-b4ec-8a0abed239df";
		String password = "password";
//		String username = "new";
//		String password = "guest";
///		String now = "2013-06-22T23:52-07";
		String now = DateUtils.getISO8601withSecFraction(new Date());

		long startTime = (new Date()).getTime();
		String authToken = AuthenticationUtils.getSIFHMACSHA256AuthToken(username, password, now);
		long endTime = (new Date()).getTime();

		System.out.println("HMACSHA256 Auth Token for username/password/date="+username+"/"+password+"/"+now+": " + authToken + " Time taken to compute: "+(endTime-startTime)+" millis.");
		System.out.println("Time taken to compute: "+(endTime-startTime)+" millis.");
	}
	
	private void testHMACSHA256TokenStress()
	{
		String username = "new";
		String password = "guest";
//		String now = "2013-06-22T23:52-07";
		int numRuns = 1000;

		// Initial calculation. Run this before timer starts so that initialisation overhead is removed.
		String now = DateUtils.getISO8601withSecFraction(new Date());
		String authToken = AuthenticationUtils.getSIFHMACSHA256AuthToken(username, password, now);
		
		// Start actual test
		long startTime = (new Date()).getTime();
		for (int i=0; i<numRuns; i++)
		{
			now = DateUtils.getISO8601withSecFraction(new Date());
			authToken = AuthenticationUtils.getSIFHMACSHA256AuthToken(username, password, now);
			System.out.println("HMACSHA256 Auth Token for username/password/date="+username+"/"+password+"/"+now+": " + authToken);
		}
		long endTime = (new Date()).getTime();
		long timeTaken = (endTime-startTime);
		
		System.out.println("Time taken to compute "+numRuns+" tokens: "+timeTaken+" millis. Avg time per token: "+((double)timeTaken/(double)numRuns)+" millis");
	}
	
	private void testExtractMethods()
	{
		String[] splitValues = null;
		String token = null;
		String username = "new";
		String password = "guest";
		String now = DateUtils.getISO8601withSecFraction(new Date());

		System.out.println("");
		String authToken = AuthenticationUtils.getSIFHMACSHA256AuthToken(username, password, now);
		System.out.println("HMACSHA256 Auth Token for username/password/date: "+username+"/"+password+"/"+now+": " + authToken);
		token = AuthenticationUtils.extractAuthToken(authToken);		
		System.out.println("Auth Method: '"+AuthenticationUtils.extractAuthenticationMethod(authToken)+"' Token: '"+token+"'");
		splitValues = AuthenticationUtils.splitValuesFromBase64(token);
		System.out.println("Left Side: '"+splitValues[0]+"'   Right Side: '"+splitValues[1]+"'");
		
		System.out.println("");
		authToken = AuthenticationUtils.getBasicAuthToken(username, password);
		System.out.println("Basic Auth Token for username/password: "+username+"/"+password+": " + authToken);
		token = AuthenticationUtils.extractAuthToken(authToken);		
		System.out.println("Auth Method: '"+AuthenticationUtils.extractAuthenticationMethod(authToken)+"' Token: '"+token+"'");	
		splitValues = AuthenticationUtils.splitValuesFromBase64(token);
		System.out.println("Left Side: '"+splitValues[0]+"'   Right Side: '"+splitValues[1]+"'");

	   System.out.println("");
	   AuthenticationInfo authInfo = new AuthenticationInfo(AuthenticationInfo.AuthenticationMethod.Bearer, "xyz-abc-123", null);
	   authToken = AuthenticationUtils.getFullBase64Token(authInfo);
     System.out.println("Bearer Auth Token: " + authToken);
	   token = AuthenticationUtils.extractAuthToken(authToken);    
	   System.out.println("Auth Method: '"+AuthenticationUtils.extractAuthenticationMethod(authToken)+"' Token: '"+token+"'"); 
	   System.out.println("All parts of Auth Token: "+AuthenticationUtils.getPartsFromAuthToken(authToken));
	}
	
	private void testGetPartsFromAuthToken()
	{
		String username = "new";
		String password = "guest";
		String now = DateUtils.getISO8601withSecFraction(new Date());
		String authToken = AuthenticationUtils.getSIFHMACSHA256AuthToken(username, password, now);

		System.out.println("HMACSHA256 Auth Token for username/password/date="+username+"/"+password+"/"+now+": " + authToken + ".");
		AuthenticationInfo authInfo = AuthenticationUtils.getPartsFromAuthToken(authToken);
		System.out.println("Parts of Auth Token: "+authInfo);
	}
	
	public static void main(String[] args)
	{

		TestAuthUtils tester = new TestAuthUtils();
		System.out.println("Start Testing TestAuthUtils...");
		try
		{
//			tester.testGetBasicAuthToken();
//			tester.testGetBearerAuthToken();
//			tester.testHMACSHA256();
			//tester.testHMACSHA256Stress();
			tester.testHMACSHA256Token();
			//tester.testHMACSHA256TokenStress();
			tester.testExtractMethods();
			//tester.testGetPartsFromAuthToken();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("End Testing TestAuthUtils.");
	}
}
