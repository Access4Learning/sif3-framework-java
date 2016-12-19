/*
 * TestDateUtils.java
 * Created: 19/11/2013
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

package systemic.framework.utils.test;

import au.com.systemic.framework.utils.DateUtils;

/**
 * @author Joerg Huber
 *
 */
public class TestDateUtils
{
	private static void delay(long millisec)
	{
		try 
		{
		    Thread.sleep(millisec);
		} 
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}		
	}
	
	public static void main(String[] args)
	{
		System.out.println("Current Date & Time in ISO 8601: " + DateUtils.nowAsISO8601());
		System.out.println("Current Date & Time in ISO 8601 with Sec Fractions: " + DateUtils.nowAsISO8601withSecFraction());
		delay(120);
		System.out.println("Current Date & Time in ISO 8601 with Sec Fractions: " + DateUtils.nowAsISO8601withSecFraction());
		delay(200);
		System.out.println("Current Date & Time in ISO 8601 with Sec Fractions: " + DateUtils.nowAsISO8601withSecFraction());
		delay(1500);
		System.out.println("Current Date & Time in ISO 8601: " + DateUtils.nowAsISO8601());
		delay(10);
		System.out.println("Current Date & Time in ISO 8601 with Sec Fractions: " + DateUtils.nowAsISO8601withSecFraction());
		delay(100);
		System.out.println("Current Date & Time in ISO 8601 with Sec Fractions: " + DateUtils.nowAsISO8601withSecFraction());
		delay(200);
		System.out.println("Current Date & Time in ISO 8601 with Sec Fractions: " + DateUtils.nowAsISO8601withSecFraction());
	}
}
