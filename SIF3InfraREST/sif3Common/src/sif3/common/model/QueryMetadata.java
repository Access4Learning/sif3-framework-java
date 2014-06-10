/*
 * QueryMetadata.java
 * Created: 03/10/2013
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

package sif3.common.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;


/**
 * Utility class used by the provider to extract URL parameters relating to paging information, query information etc. In essence this 
 * class helps extracting and adding URL query parameters (everything after the '?' in a URL).
 * @author Joerg Huber
 *
 */
public class QueryMetadata implements Serializable
{
	private static final long serialVersionUID = 17813336262L;

	public static final String NUM_PAGE_TAG = "page";
	public static final String NUM_REC_PER_PAGE_TAG = "pageSize";

	/* Paging Information */
	private PagingInfo pagingInfo = null;

	public QueryMetadata()
	{
	}

	public QueryMetadata(Map<String, String> attributeValueMap)
	{
		setValues(attributeValueMap);
	}

	public QueryMetadata(MultivaluedMap<String, String> attributeValueMap)
	{
		setValues(attributeValueMap);
	}

	public PagingInfo getPagingInfo()
	{
		return this.pagingInfo;
	}

	public void setPagingInfo(PagingInfo pagingInfo)
	{
		this.pagingInfo = pagingInfo;
	}

	public void setValue(String tagName, String value)
	{
		if (tagName != null)
		{
			if (NUM_PAGE_TAG.equals(tagName))
			{
				if (value != null)
				{
					if (getPagingInfo() == null)
					{
						setPagingInfo(new PagingInfo());
					}
					getPagingInfo().setCurrentPageNo(toInt(value, getPagingInfo().getCurrentPageNo()));
				}
			}
			else if (NUM_REC_PER_PAGE_TAG.equals(tagName))
			{
				if (value != null)
				{
					if (getPagingInfo() == null)
					{
						setPagingInfo(new PagingInfo());
					}
					getPagingInfo().setPageSize(toInt(value, getPagingInfo().getPageSize()));
				}
			}
		}
	}

	/**
	 * This method sets all the properties in this class for which a value is found in the given attributeValueMap. Only properties for 
	 * which a value is found are set. All other properties remain untouched. Note that only properties for which there is a TAG constant 
	 * defined at the top of this class will be considered. If the Map is null then no action is taken.
	 * <p>
	 * 
	 * @param attributeValueMap The attribute (key) - value map from which the properties of this object shall be set.
	 */
	public void setValues(Map<String, String> attributeValueMap)
	{
		if (attributeValueMap != null)
		{
			for (String attr : attributeValueMap.keySet())
			{
				setValue(attr, attributeValueMap.get(attr));
			}
		}
	}

	/**
	 * This method behaves exactly the same as setValues(Map) above except the Map here is a MultivaluedMap that is used in REST URI paramters. 
	 * It is a pure convenience method.
	 * <p>
	 * 
	 * @param attributeValueMap
	 */
	public void setValues(MultivaluedMap<String, String> attributeValueMap)
	{
		if (attributeValueMap != null)
		{
			for (String attr : attributeValueMap.keySet())
			{
				setValue(attr, attributeValueMap.getFirst(attr));
			}
		}
	}

	/**
	 * Returns a hash map of all values in this class that are NOT NULL. The key values of the hash map are the parameter names and the values 
	 * in the hash map are the values of the parameter. If no values are set in this class then an empty hash map is returned. <p><p>
	 * 
	 * This is a pure convenience method.<p><p>
	 *
	 * Example:
	 * <p>HashMap[page]: 2
	 * <p>HashMap[pageSize]: 10<p><p>
	 * 
	 * @return See desc.
	 */
	public Map<String, String> getQueryParameters()
	{
		HashMap<String, String> params = new HashMap<String, String>();
		if (getPagingInfo() != null)
		{
			params.put(NUM_PAGE_TAG, String.valueOf(getPagingInfo().getCurrentPageNo()));
			params.put(NUM_REC_PER_PAGE_TAG, String.valueOf(getPagingInfo().getPageSize()));
		}
		return params;
	}


	@Override
	public String toString()
	{
		return "QueryMetadata [pagingInfo=" + this.pagingInfo + "]";
	}

	/*---------------------*/
	/*-- Private methods --*/
	/*---------------------*/
	private Integer toInteger(String value)
	{
		try
		{
			return Integer.valueOf(value);
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	private int toInt(String value, int defaultValue)
	{
		Integer intValue = toInteger(value);
		return (intValue == null) ? defaultValue : intValue;
	}

}
