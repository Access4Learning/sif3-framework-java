/*
 * RESTHeaderProperties.java
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

package sif3.infra.rest.header;

import java.util.HashMap;

import sif3.common.header.TransportHeaderProperties;
import au.com.systemic.framework.utils.StringUtils;

/**
 * Ideally all methods in this class should use the constants from the RequestHeaderConstants and ResponseHeaderConstants class to 
 * set or retrieve header properties by the property name.
 * 
 * @author Joerg Huber
 */
public class RESTHeaderProperties implements TransportHeaderProperties
{	
	private HashMap<String, String> hdrProperties = new HashMap<String, String>();
	
	public RESTHeaderProperties() {}
	
	public RESTHeaderProperties(HashMap<String, String> hdrProperties) 
	{
		this.hdrProperties = createCopy(hdrProperties);
	}
	
	/* (non-Javadoc)
	 * @see sif3.infra.common.header.TransportHeaderProperties#setHeaderProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public void setHeaderProperty(String hdrPropertyName, String hdrPropertyValue)
	{
		if (StringUtils.notEmpty(hdrPropertyName))
		{
			if (hdrPropertyValue == null)
			{
				hdrProperties.remove(hdrPropertyName);
			}
			else
			{
				hdrProperties.put(hdrPropertyName, hdrPropertyValue);
			}
		}
	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.header.TransportHeaderProperties#setHeaderProperties(java.util.HashMap)
	 */
	@Override
	public void setHeaderProperties(HashMap<String, String> hdrProperties)
	{
		this.hdrProperties = createCopy(hdrProperties);
	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.header.TransportHeaderProperties#getHeaderProperty(java.lang.String)
	 */
	@Override
	public String getHeaderProperty(String hdrPropertyName)
	{
		return hdrProperties.get(hdrPropertyName);
	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.header.TransportHeaderProperties#getHeaderProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public String getHeaderProperty(String hdrPropertyName, String hdrDefaultValue)
	{
		String value = hdrProperties.get(hdrPropertyName);
		return (value == null) ? hdrDefaultValue : value;
	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.header.TransportHeaderProperties#getHeaderProperties()
	 */
	@Override
	public HashMap<String, String> getHeaderProperties()
	{
		return createCopy(this.hdrProperties);
	}
	
	@Override
    public String toString()
    {
	    return "RESTHeaderProperties [hdrProperties=" + this.hdrProperties + "]";
    }

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private HashMap<String, String> createCopy(HashMap<String, String> hdrProperties)
	{
		HashMap<String, String> copyProps = new HashMap<String, String>();
		if (hdrProperties != null)
		{
			copyProps.putAll(hdrProperties);
		}
		return copyProps;
	}
}
