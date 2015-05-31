/*
 * HeaderProperties.java
 * Created: 03/09/2013
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

package sif3.common.header;

import java.util.HashMap;

import au.com.systemic.framework.utils.StringUtils;

/**
 * This is a helper class to manage all header properties that are SIF3 requests and responses. Simple setters and getters are provided as 
 * well as some other useful methods to manage header properties for SIF3.
 * 
 * Ideally all methods in this class should use the constants from the RequestHeaderConstants and ResponseHeaderConstants class to 
 * set or retrieve header properties by the property name. As per HTTP specification HTTP header names are case insensitive
 * and therefore this class ignores the case of HTTP header names.
 * 
 * @author Joerg Huber
 */
public class HeaderProperties
{	
	private HashMap<String, String> hdrProperties = new HashMap<String, String>();
	
	public HeaderProperties() {}
	
	public HeaderProperties(HashMap<String, String> hdrProperties) 
	{
		this.hdrProperties = createCopy(hdrProperties);
	}
	
	/**
	 * Stores the value of a header property. If the property with that name is already set then it will be overwritten. If the 
	 * 'hdrPropertyValue' is null then then the property with the name 'hdrPropertyName' will be removed. If the 'hdrPropertyName' is
	 * null or empty string then no action is taken.
	 * 
	 * @param hdrPropertyName Header property name. Case insensitive!
	 * @param hdrPropertyValue Header property value.
	 */
	public void setHeaderProperty(String hdrPropertyName, String hdrPropertyValue)
	{
		if (StringUtils.notEmpty(hdrPropertyName))
		{
			if (hdrPropertyValue == null)
			{
				hdrProperties.remove(hdrPropertyName.toLowerCase());
			}
			else
			{
				hdrProperties.put(hdrPropertyName.toLowerCase(), hdrPropertyValue);
			}
		}
	}

	/**
	 * Stores all values of the hashmap. 
	 * 
	 * @param hdrProperties Hashmap with the KEY=Property Name; VALUE=Property Value
	 */
	public void setHeaderProperties(HashMap<String, String> hdrProperties)
	{
		this.hdrProperties = createCopy(hdrProperties);
	}

	/**
	 * Returns the value of a header property. If the property with that name does not exist then null is returned.
	 * 
	 * @param hdrPropertyName Header property name. Case insensitive!
	 * @return Header property value. Null if it doesn't exist.
	 */
	public String getHeaderProperty(String hdrPropertyName)
	{
		return (hdrPropertyName != null) ? hdrProperties.get(hdrPropertyName.toLowerCase()) : null;
	}

	/**
	 * Returns the value of a header property. If the property with that name does not exist or is null then the default 
	 * value is returned.
	 * 
	 * @param hdrPropertyName Header property name. Case insensitive!
	 * @param hdrDefaultValue Value to return if no property for the given hdrPropertyName exists.
	 * 
	 * @return Header property value. 'hdrDefaultValue' if it doesn't exist.
	 */
	public String getHeaderProperty(String hdrPropertyName, String hdrDefaultValue)
	{
		if (hdrPropertyName != null)
		{
			String value = hdrProperties.get(hdrPropertyName.toLowerCase());
			return (value == null) ? hdrDefaultValue : value;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Returns all header properties. A true copy of the header properties stored within this class is returned. 
	 * 
	 * @return Hashmap with the KEY=Property Name; VALUE=Property Value
	 */
	public HashMap<String, String> getHeaderProperties()
	{
		return createCopy(this.hdrProperties);
	}
	
	@Override
    public String toString()
    {
	    return "HeaderProperties [hdrProperties=" + this.hdrProperties + "]";
    }

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private HashMap<String, String> createCopy(HashMap<String, String> hdrProperties)
	{
		HashMap<String, String> copyProps = new HashMap<String, String>();
		if (hdrProperties != null)
		{
			for (String hdrName : hdrProperties.keySet())
			{
				copyProps.put(hdrName.toLowerCase(), hdrProperties.get(hdrName));
			}
		}
		return copyProps;
	}
}
