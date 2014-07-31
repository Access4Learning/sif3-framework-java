/*
 * URLQueryParameter.java
 * Created: 07/07/2014
 *
 * Copyright 2014 Systemic Pty Ltd
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
 * Utility class used to manage URL query parameters. This class simply uses an undelying hash map to hold attrbut value pairs that can be
 * used to add or extract URL query paramters from a URI. In essence this class helps extracting and adding URL query parameters (everything 
 * after the '?' in a URL).
 * 
 * @author Joerg Huber
 */
public class URLQueryParameter implements Serializable
{
  private static final long serialVersionUID = 3100928378209405659L;

  private Map<String, String> queryParams = null; //new HashMap<String, String>();
  
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
   * This is a conveniece method to deal with the JAX-RS implementation of multivalued maps that is used to manage URL query parameters. This
   * method will copy the values in the MultivaluedMap across to a simple Map which also means that there is a limitation that each query 
   * paramter can only occur ONCE! Only the first value of each unique query parameter name will be picked up.
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
   * This method sets a given query parameter (name) to the given value.
   * @param name
   * @param value
   */
  public void setQueryParam(String name, String value)
  {
    if (queryParams == null)
    {
      queryParams = new HashMap<String, String>();
    }
    queryParams.put(name, value);
  }
  
  /**
   * Convenience method. Returns the value of the query parameter with the given name. If it doesn't exist then null is returned.
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

  @Override
  public String toString()
  {
    return (queryParams == null) ? null : queryParams.toString();
  }  
}
