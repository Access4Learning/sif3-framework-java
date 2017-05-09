/*
 * GUIDGenerator.java
 *
 * Copyright 2003-2014 Systemic Pty Ltd
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
package au.com.systemic.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Joerg Huber
 */
public class GUIDGenerator
{   
    protected static Logger logger = LoggerFactory.getLogger(GUIDGenerator.class);
    /**
     * Method to generate the random GUID that is 32 characters long made of HEX code and in upper case. It will follow the RFC4122 with
     * the exception that the returned GUIDs have upper case only and the '-' removed.
     */
    public static String getRandomGUID()
    {
    	return java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /*
     * Demonstration and self test of class
     */
    public static void main(String args[])
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.println("RandomGUID=" + GUIDGenerator.getRandomGUID());
        }
    }

}
