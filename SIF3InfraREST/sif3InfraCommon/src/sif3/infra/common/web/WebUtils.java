/*
 * WebUtils.java
 * Created: 30/10/2012
 *
 * Copyright 2012 Systemic Pty Ltd
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
package sif3.infra.common.web;

import javax.naming.InitialContext;


/**
 * This class is a utility to access values from the web.xml file. It can only be used in that context and for web deployments.
 * 
 * @author Joerg Huber
 */
public class WebUtils
{
  public static final String SERVICE_PROPERTY_FILE_TAG = "SERVICE_PROPERTY_FILE";
  
  public static WebUtils instance = null;
  
  private InitialContext ctx = null;
  
  /**
   * Ensures we deal with a singleton. Web.xml and its context only needs to be created once within a deployment and is static thereafter.
   * This method ensures that this static nature is utilised.
   * 
   * @return An instance of this class.
   */
  public static WebUtils getInstance()
  {
    if (instance == null)
    {
      instance = new WebUtils();
    }
    return instance;
  }

  /**
   * Returns the value of the 'SERVICE_PROPERTY_FILE' parameter in the web.xml. Null is returned if it doesn't exist. It must exist for
   * this framework to initialise and work properly. Refer to the Developer's guide for more details.
   * 
   * @return See desc.
   */
  public String getServicePropertyFileName()
  {
    return getStringValue(SERVICE_PROPERTY_FILE_TAG, null);
  }
  
  /*---------------------*/
  /*-- Private Methods --*/
  /*---------------------*/
  private String getStringValue(String propName, String defaultValue)
  {
    try
    {
      String value = (String)ctx.lookup("java:comp/env/"+propName);
      //System.out.println("Value for "+propName+" = '"+value+"'");
      return (value == null) ? defaultValue : value.trim();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return defaultValue;
    }
  }
  
  private WebUtils()
  {
    try
    {
      ctx = new InitialContext();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
}
