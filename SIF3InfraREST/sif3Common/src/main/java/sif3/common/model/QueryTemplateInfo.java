/*
 * QueryTemplateInfo.java
 * Created: 22 Nov 2018
 *
 * Copyright 2018 Systemic Pty Ltd
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
import java.util.HashMap;

/**
 * This class abstracts the information required for XQUERYTEMPLATE services. These services require a name of a 
 * named query and an optional list of parameters. A parameter is a simple attribute/value pair. These query parameters and 
 * the query name are translated into URL query parameters as part of the actual XQUERYTEMPLATE service call and therefore
 * are case-sensitive as per HTTP specification.
 * 
 * @author Joerg Huber
 */
public class QueryTemplateInfo implements Serializable
{
    private static final long serialVersionUID = 6698207359734736517L;
    
    private String queryName = null;
    private HashMap<String, String> queryParameters = new HashMap<String, String>();
    
    public QueryTemplateInfo()
    {
        this(null, null);
    }
    
    public QueryTemplateInfo(String queryName)
    {
        this(queryName, null);
    }
    
    /**
     * Note that the values in the queryParameters are a hashmap. The name of the query parameter is the key into the hashmap
     * and the value of the parameter is the value in the hashmap.
     * 
     * @param queryName The name of the XQUERYTEMPLATE service.
     * @param queryParameters Hashmap of parameters. ParameterName=KEY, ParameterValue=VALUE
     */
    public QueryTemplateInfo(String queryName, HashMap<String, String> queryParameters)
    {
        super();
        setQueryName(queryName);
        setQueryParameters(queryParameters);
    }

    public String getQueryName()
    {
        return queryName;
    }

    public void setQueryName(String queryName)
    {
        this.queryName = queryName;
    }

    public HashMap<String, String> getQueryParameters()
    {
        return queryParameters;
    }

    public void setQueryParameters(HashMap<String, String> queryParameters)
    {
        if (queryParameters != null)
        {
            this.queryParameters = queryParameters;
        }
    }
    
    /**
     * This method will add a parameter to the list of Query Parameters. If a query parameter with the given name already exists
     * it will be overwritten with the value of parameterValue parameter. If the parameterName is empty or null then no
     * action is taken.
     * 
     * @param parameterName The name of the query parameter
     * @param parameterValue The value of the query parameter
     */
    public void addQueryParameter(String parameterName, String parameterValue)
    {
        getQueryParameters().put(parameterName, parameterValue);
    }

    /**
     * This method returns the value of the given query parameter. If no parameter with the given value exists then null is
     * returned.
     *  
     * @param parameterName Name of the query parameter for which the value shall be returned.
     * 
     * @return See desc.
     */
    public String getQueryParameterValue(String parameterName)
    {
        return getQueryParameters().get(parameterName);
    }
    
    /**
     * This method removes the given query parameter. If no parameter with the given value exists then no action is performed.
     *  
     * @param parameterName Name of the query parameter to be removed.
     */
    public void removeQueryParamter(String parameterName)
    {
        getQueryParameters().remove(parameterName);
    }

    @Override
    public String toString()
    {
        return "QueryTemplateInfo [queryName=" + queryName + ", queryParameters=" + queryParameters + "]";
    }
}
