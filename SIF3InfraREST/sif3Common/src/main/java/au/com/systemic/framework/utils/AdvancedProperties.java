/*
 * AdvancedProperties.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Joerg Huber
 *
 */
public class AdvancedProperties
{
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private String propFileNameFull = null;
	private Properties props = null;

	public AdvancedProperties(String fileNameWithoutExt)
	{
		propFileNameFull = fileNameWithoutExt + ".properties";
		PropertyFileReader reader = new PropertyFileReader(fileNameWithoutExt);
		props = reader.getProperties();
		logger.debug("Loaded property file: "+getPropFileNameFull());
	}

	public String getPropFileNameFull()
    {
    	return this.propFileNameFull;
    }

	public Properties getProperties()
	{
		return props;
	}
	
	public boolean isLoaded()
	{
	  return props != null;
	}
	
	/*-----------------------------------------------------------*/
	/*-- Handy and commonly used getter methods for properties --*/
	/*-----------------------------------------------------------*/
	/**
	 * Returns the given property as a boolean object. If it doesn't exist or is not a boolean then null is
	 * returned.
	 */
	public Boolean getPropertyAsBool(String propertyName)
	{
		if (props != null)
		{
			String stringVal = props.getProperty(propertyName);
			if (stringVal != null)
			{
				try
				{
					return Boolean.valueOf(stringVal.trim());
				}
				catch (Exception ex)
				{}
			}
		}
		return null;
	}

	/**
	 * Returns the given property as a boolean. If it doesn't exist or is not a boolean then the default 
	 * value is returned.
	 */
	public boolean getPropertyAsBool(String propertyName, boolean defaultValue)
	{
		Boolean bool = getPropertyAsBool(propertyName);
		return (bool == null) ? defaultValue : bool.booleanValue();
	}
	
	/**
	 * This method attempts to return the property "mainPropertyName.subPropertyName". If it doesn't exist or is not 
	 * set (null/empty) then an attempt is made to just read the "mainPropertyName". If that doesn't exist or is not 
	 * set (null/empty) then the default value is returned.
	 * 
	 * @param mainPropertyName
	 * @param subPropertyName
	 * @param defaultValue
	 * 
	 * @return See desc.
	 */
	public boolean getPropertyAsBool(String mainPropertyName, String subPropertyName, boolean defaultValue)
	{
		Boolean boolValue= getPropertyAsBool(mainPropertyName+"."+subPropertyName);
		return (boolValue != null) ? boolValue : getPropertyAsBool(mainPropertyName, defaultValue);
	}

	
	/**
	 * Returns the given property as a Integer object. If it doesn't exist or is not an Integer then null is
	 * returned.
	 */
	public Integer getPropertyAsInt(String propertyName)
	{
		if (props != null)
		{
			String stringVal = props.getProperty(propertyName);
			if (stringVal != null)
			{
				try
				{
					return Integer.valueOf(stringVal.trim());
				}
				catch (Exception ex)
				{}
			}
		}
		return null;
	}

	/**
	 * Returns the given property as a int. If it doesn't exist or is not an Integer then the default value
	 * is returned.
	 */
	public int getPropertyAsInt(String propertyName, int defaultValue)
	{
		Integer integer = getPropertyAsInt(propertyName);
		return (integer == null) ? defaultValue : integer.intValue();
	}

	/**
	 * This method attempts to return the property "mainPropertyName.subPropertyName". If it doesn't exist or is not 
	 * set (null/empty) then an attempt is made to just read the "mainPropertyName". If that doesn't exist or is not 
	 * set (null/empty) then the default value is returned.
	 * 
	 * @param mainPropertyName
	 * @param subPropertyName
	 * @param defaultValue
	 * 
	 * @return See desc.
	 */
	public int getPropertyAsInt(String mainPropertyName, String subPropertyName, int defaultValue)
	{
		Integer intValue= getPropertyAsInt(mainPropertyName+"."+subPropertyName);
		return (intValue != null) ? intValue.intValue() : getPropertyAsInt(mainPropertyName, defaultValue);
	}
	
	/**
	 * Returns the given property as a String. If it doesn't exist or is empty then the default value is returned.
	 */
	public String getPropertyAsString(String propertyName, String defaultValue)
	{
		if (props != null)
		{
			String value = props.getProperty(propertyName);
			return (StringUtils.isEmpty(value) ? defaultValue : value);
		}
		return defaultValue;
	}
	
	/**
	 * This method attempts to return the property "mainPropertyName.subPropertyName". If it doesn't exist or is not 
	 * set (null/empty) then an attempt is made to just read the "mainPropertyName". If that doesn't exist or is not 
	 * set (null/empty) then the default value is returned.
	 * 
	 * @param mainPropertyName
	 * @param subPropertyName
	 * @param defaultValue
	 * 
	 * @return See desc.
	 */
	public String getPropertyAsString(String mainPropertyName, String subPropertyName, String defaultValue)
	{
		String value= getPropertyAsString(mainPropertyName+"."+subPropertyName, null);
		return (value != null) ? value : getPropertyAsString(mainPropertyName, defaultValue);
	}
	
	/**
	 * This method reads the given property and then attempts to split the value at the commas. This allows to read a comma separated
	 * property value. The result is returned as a List of Strings. If the property doesn't exist or there is no value defined then
	 * an empty list is returned.
	 * 
	 * @param propertyName
	 * 
	 * @return See desc.
	 */
	public List<String> getFromCommaSeparated(String propertyName)
	{
		List<String> list = new ArrayList<String>();
        String classListStr = getPropertyAsString(propertyName, null);
        
        if (classListStr != null)
        {
        	String classNames[] = classListStr.split(",");
            for (int i = 0; i < classNames.length; i++)
            {
            	list.add(classNames[i].trim());
            }
        }
		
		return list;
	}

  @Override
  public String toString()
  {
    return "AdvancedProperties [propFileNameFull=" + propFileNameFull + ", props=" + props + "]";
  }
	
	
}
