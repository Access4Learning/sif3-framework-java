/*
 * URLQueryParameter.java Created: 07/07/2014
 * 
 * Copyright 2014 Systemic Pty Ltd
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
package sif3.common.model;

import java.io.Serializable;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

/**
 * Utility class used to manage URL query parameters. This class simply uses an underlying hash map
 * to hold attribute value pairs that can be used to add or extract URL query parameters from a URI.
 * In essence this class helps extracting and adding URL query parameters (everything after the '?'
 * in a URL).
 * 
 * @author Joerg Huber
 */
public class URLQueryParameter implements Serializable
{
	private static final long serialVersionUID = 3100928378209405659L;

	private Map<String, String> queryParams = null; // new HashMap<String, String>();

	public URLQueryParameter()
	{
	}

	public URLQueryParameter(Map<String, String> attributeValueMap)
	{
		setQueryParams(attributeValueMap);
	}

	public URLQueryParameter(MultivaluedMap<String, String> attributeValueMap)
	{
		setQueryParams(attributeValueMap);
	}

	public Map<String, String> getQueryParams()
	{
		return queryParams;
	}

	public void setQueryParams(Map<String, String> queryParams)
	{
		this.queryParams = queryParams;
	}

	/**
	 * This is a convenience method to deal with the JAX-RS implementation of multivalued maps that
	 * is used to manage URL query parameters. This method will copy the values in the
	 * MultivaluedMap across to a simple Map which also means that there is a limitation that each
	 * query Parameter can only occur ONCE! Only the first value of each unique query parameter name
	 * will be picked up.
	 * 
	 * @param queryParams The Multivalued Map holding query parameters.
	 */
	public void setQueryParams(MultivaluedMap<String, String> queryParams)
	{
		if (queryParams != null)
		{
			for (String attr : queryParams.keySet())
			{
				setQueryParam(attr, queryParams.getFirst(attr));
			}
		}
	}

	/**
	 * This method sets a given query parameter (name) to the given value. The value will not be URL encoded.
	 * 
	 * @param name
	 * @param value
	 */
	public void setQueryParam(String name, String value)
	{
	    setQueryParam(name, value, false);
	}
	
    /**
     * This method sets a given query parameter (name) to the given value and will URL encode it if urlEncoded is true.
     * 
     * @param name
     * @param value
     * @param urlEncoded TRUE: will URL encode the value first. FALSE: value remains untouched.
     */
    public void setQueryParam(String name, String value, boolean urlEncoded)
    {
        if (queryParams == null)
        {
            queryParams = new HashMap<String, String>();
        }
        
        try
        { 
            queryParams.put(name, (urlEncoded && (value != null)) ? URLEncoder.encode(value, "UTF-8") : value);
        }
        catch (Exception ex) {}
    }
	
	
	/**
	 * This method removes the given parameter form the list of URL query parameters. If the name is
	 * null or does not exits in the list of URL query parameters then no action is taken.
	 * 
	 * @param name
	 */
	public void removeQueryParam(String name)
	{
		if ((name != null) && (queryParams != null))
		{
			queryParams.remove(name);
		}
	}

	/**
	 * Convenience method. Returns the value of the query parameter with the given name. If it
	 * doesn't exist then null is returned.
	 * 
	 * @param name The name of the query parameter for which the value shall be returned.
	 * 
	 * @return See desc.
	 */
	public String getQueryParam(String name)
	{
		if (queryParams != null)
		{
			return queryParams.get(name);
		}

		return null;
	}

    /**
     * This method returns the value of the given URL query parameter as a string. If no URL query parameter with that name
     * exists then null is returned. The parameterName is case sensitive. The returned string is in its raw format if 
     * urlDecode= false otherwise URL decoding is applied before it is returned.
     * 
     * @param name Name of the URL query parameter for which the value shall be returned.
     * @param urlDecode TRUE: value will be URL decoded. FALSE: value remains in its raw format.
     * 
     * @return See desc.
     */
    public String getQueryParam(String name, boolean urlDecode)
    {
        if (queryParams != null)
        {
            try
            {
                return (urlDecode) ? URLDecoder.decode(queryParams.get(name), "UTF-8") : queryParams.get(name);
            }
            catch (Exception ex) // return raw value
            {
                return queryParams.get(name);
            }
        }

        return null;
    }

	@Override
	public String toString()
	{
		return (queryParams == null) ? null : queryParams.toString();
	}
}
