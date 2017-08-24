/*
 * TestHibernatUtils.java
 * Created: 16 May 2017
 *
 * Copyright 2017 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License. 
 */

package sif3.common.test.utils;

import java.util.Properties;

import sif3.common.persist.common.HibernateUtil;

/**
 * @author Joerg Huber
 *
 */
public class TestHibernateUtils
{
    
    public boolean initialiseTest(Properties properties)
    {
        return HibernateUtil.initialise(properties);
    }    
    
    public void shutdownTest()
    {
        HibernateUtil.shutdown();
    }
    
    public Properties getProperties()
    {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.password", "");
        properties.setProperty("hibernate.c3p0.max_size", "25");
        
        return properties;
    }
    
    public static void main(String[] args)
    {

        TestHibernateUtils tester = new TestHibernateUtils();
        System.out.println("Start Testing TestHibernateUtils...");
        try
        {
//            System.out.println("Initilised Hibernate: "+tester.initialiseTest(tester.getProperties()));
            System.out.println("Initilised Hibernate: "+tester.initialiseTest(null));
            
            tester.shutdownTest();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("End Testing TestHibernateUtils.");
    }
}
