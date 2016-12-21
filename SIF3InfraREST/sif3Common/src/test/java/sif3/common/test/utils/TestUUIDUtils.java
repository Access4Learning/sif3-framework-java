/*
 * TestUUIDUtils.java
 * Created: 01/09/2013
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

package sif3.common.test.utils;

import java.util.Date;

import sif3.common.utils.UUIDGenerator;


/**
 * @author Joerg Huber
 *
 */
public class TestUUIDUtils
{
	private final int SIZE = 1000000;
	
	private void testStressSIF2ToSIF3()
	{
		String[] sif2GUIDs = new String[SIZE];
		for (int i=0; i<SIZE; i++)
		{
			sif2GUIDs[i] = UUIDGenerator.getSIF2GUIDUpperCase();
		}
		
		System.out.println("Start converting...");
		long startTime = (new Date()).getTime();
		for (int i=0; i<SIZE; i++)
		{
			sif2GUIDs[i] = UUIDGenerator.sif2GUIDToSIF3UUID(sif2GUIDs[i]);
		}	
		long endTime = (new Date()).getTime();
		
		System.out.println("Time taken to convert "+SIZE+" SIF2 GUIDs to SIF3 UUIDs: "+((endTime-startTime)/1000d)+"secs or "+(((endTime-startTime)*1000d)/SIZE)+" microsec per conversion");
	}
	
	private void testStressSIF3ToSIF2()
	{
		String[] sif3GUIDs = new String[SIZE];
		for (int i=0; i<SIZE; i++)
		{
			sif3GUIDs[i] = UUIDGenerator.getUUID();
		}
		
		System.out.println("Start converting...");
		long startTime = (new Date()).getTime();
		for (int i=0; i<SIZE; i++)
		{
			sif3GUIDs[i] = UUIDGenerator.sif3UUIDToSIF2GUID(sif3GUIDs[i]);
		}	
		long endTime = (new Date()).getTime();
		
		System.out.println("Time taken to convert "+SIZE+" SIF3 UUIDs to SIF2 GUIDs: "+((endTime-startTime)/1000d)+"secs or "+(((endTime-startTime)*1000d)/SIZE)+" microsec per conversion");
		
	}

	public static void main(String[] args)
	{

		TestUUIDUtils tester = new TestUUIDUtils();
		System.out.println("Start Testing TestUUIDUtils...");
		try
		{
			tester.testStressSIF2ToSIF3();
			tester.testStressSIF3ToSIF2();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("End Testing TestUUIDUtils.");
	}
}
