/*
 * UUIDGenerator.java
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

package sif3.common.utils;


/**
 * This is a utility class that helps generate UUID as defined in SIF 3.0. One could use java.util.UUID.randomUUID() directly but in case
 * the implementation needs to change it can be done here in one place rather then all over the code base.
 *
 * @author Joerg Huber
 * 
 */
public class UUIDGenerator
{

  /**
   * This method returns a UUID as defined in SIF3.
   * 
   * @return See desc.
   */
	public static final String getUUID()
	{
		return java.util.UUID.randomUUID().toString();
	}
	
	/**
	 * This method returns a UUID as defined in SIF3 but has the dashes removed.
	 * 
   * @return See desc.
	 */
	public static final String getUUIDWithoutDashes()
	{
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * This method returns a GUID as defined in SIF2.x but the alpha characters can be of mixed case.
	 *  
	 * @return See desc.
	 */
	public static final String getSIF2GUIDMixedCase()
	{
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}
	
  /**
   * This method returns a GUID as defined in SIF2.x.
   *  
   * @return See desc.
   */
	public static final String getSIF2GUIDUpperCase()
	{
		return java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	 /**
   * This method converts a SIF2.x GUID into a SIF3 UUID. This is done my means of adding dashes ('-') at the correct positions. It will not
   * alter the case of any characters, though.
   *  
   * @return See desc.
   */
	public static final String sif2GUIDToSIF3UUID(String sif2GUID)
	{
		StringBuffer sif3UUID = new StringBuffer("");
		sif3UUID.append(sif2GUID.substring(0,8)).append("-").append(sif2GUID.substring(8,12)).append("-").append(sif2GUID.substring(12,16)).append("-").append(sif2GUID.substring(16,20)).append("-").append(sif2GUID.substring(20,32));
		return sif3UUID.toString();
		
	}
	
  /**
   * This method converts a SIF3UUID into a SIF2.x GUID. This is done my means of removing dashes ('-') from the UUID. It will not
   * alter the case of any characters, though.
   *  
   * @return See desc.
   */
	public static final String sif3UUIDToSIF2GUID(String sif3UUID)
	{
		return sif3UUID.replace("-", "");
	}
	
	/*
     * Demonstration and self test of class
     */
    public static void main(String args[])
    {
//        for (int i = 0; i < 100; i++)
//        {
//            System.out.println("Random UUID = " + UUIDGenerator.getUUID());
//        }
//        for (int i = 0; i < 100; i++)
//        {
//            System.out.println("Random SIF 2.x GUID = " + UUIDGenerator.getSIF2GUIDMixedCase());
//        }
        
        String sif3UUID = UUIDGenerator.getUUID();
        System.out.println("SIF3 UUID = "+sif3UUID+ " mapped to SIF2 GUID: "+UUIDGenerator.sif3UUIDToSIF2GUID(sif3UUID));
        String sif2GUID = UUIDGenerator.getSIF2GUIDMixedCase();
        System.out.println("SIF2 GUID = "+sif2GUID+ " mapped to SIF3 UUID: "+UUIDGenerator.sif2GUIDToSIF3UUID(sif2GUID));
    }
}
