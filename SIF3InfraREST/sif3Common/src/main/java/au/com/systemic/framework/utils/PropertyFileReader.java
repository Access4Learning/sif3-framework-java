/*
 * PropertyFileReader.java
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

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Joerg Huber
 */
public class PropertyFileReader
{
  protected final static Logger logger = LoggerFactory.getLogger(PropertyFileReader.class);
  
  private String propFileNameFull = null;

  public PropertyFileReader(String fileNameWithoutExt)
  {
	  propFileNameFull = fileNameWithoutExt+".properties";
  }
  
  /**
   * This method attempts to read a property file that must be stored somewhere within the classpath.
   * 
   * @return The content of the property file in a Properties object. Null is  returned if the file 
   *         cannot be found or the file has an invalid content (eg. binary file insetead of ascii file).
   */
  public Properties getProperties()
  {
    Properties properties = null;
    try
    {
      properties = new Properties();
      properties.load(PropertyFileReader.class.getClassLoader().getResourceAsStream(propFileNameFull));
    }
    catch (IOException ex)
    {
      logger.error("Error reading content of file " + propFileNameFull + " .", ex);
      properties = null;
    }
    catch (Exception ex)
    {
      logger.error("Error accessing " + propFileNameFull + " " + ex.getMessage(), ex);
      properties = null;
    }
    return properties;
  }
}
