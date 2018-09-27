/*
 * BaseRequestParameter.java
 * Created: 22 Feb 2018
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

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.header.HeaderProperties;

/**
 * Most consumer and provider end-points use a given set of parameters such as zone, context, HTTP headers and URL query parameters. This class
 * encapsulates the set of parameters. It allows to define interfaces, classes etc with methods that use this encapsulated parameters rather than
 * having a large list of individual parameters. It is intended that this class will replace the class RequestParameter that is beiong used at the moment
 *
 * 
 * @author Joerg Huber
 *
 */
public class BaseRequestParameter extends RequestParameters implements Serializable
{
    private static final long serialVersionUID = -725442773873032692L;

    private SIFZone zone = null;
    private SIFContext context = null;
    private URLQueryParameter queryParams = new URLQueryParameter();
    private HeaderProperties httpHeaderParams = new HeaderProperties();

    public BaseRequestParameter()
    {
        this(null, null, null, null);
    }

    public BaseRequestParameter(SIFZone zone, SIFContext context, URLQueryParameter queryParams, HeaderProperties httpHeaderParams)
    {
        super();
        setHttpHeaderParams(httpHeaderParams);
        setQueryParams(queryParams);
        setZone(zone);
        setContext(context);
    }
    
    public URLQueryParameter getQueryParams()
    {
        return this.queryParams;
    }
    
    public SIFZone getZone()
    {
        return zone;
    }

    public void setZone(SIFZone zone)
    {
        this.zone = zone;
    }

    public SIFContext getContext()
    {
        return context;
    }

    public void setContext(SIFContext context)
    {
        this.context = context;
    }

    /**
     * This method sets the URL query parameters that are managed within this class. If null is passed in and there are
     * already query parameters set through calls of other methods within this class then all URL query parameters are
     * removed. If the queryParams is not null then the current URL query parameters are entirely replaced with the given
     * queryParams.
     * 
     * @param queryParams See desc.
     */
    public void setQueryParams(URLQueryParameter queryParams)
    {
        if (queryParams != null)
        {
            this.queryParams = queryParams;
        }
        else
        {
            this.queryParams = new URLQueryParameter();
        }
    }

    public HeaderProperties getHttpHeaderParams()
    {
        return this.httpHeaderParams;
    }
    
    /**
     * This method sets the HTTP header parameters that are managed within this class. If null is passed in and there are
     * already HTTP header parameters set through calls of other methods within this class then all HTTP header parameters 
     * are removed. If the httpHeaderParams is not null then the current HTTP header parameters are entirely replaced with 
     * the given httpHeaderParams.
     *  
     * @param httpHeaderParams The HTTP headers to be used within this class. See desc.
     */
    public void setHttpHeaderParams(HeaderProperties httpHeaderParams)
    {
        if (httpHeaderParams != null)
        {
            this.httpHeaderParams = httpHeaderParams;
        }
        else
        {
            this.httpHeaderParams = new HeaderProperties();
        }
    }

    /**
     * This method adds the given parameter and value to the list of URL query parameters. If there is already a parameter
     * with that name then it will be overridden. The parameterName is case sensitive, so 'Param1' and 'param1' as the 
     * parameter name are treated as two different parameters. This behaviour matches the HTTP specification. If any of the
     * two parameters is empty or null then they are not added to the list of URL query parameters.
     * 
     * @param parameterName The name of the URL query parameter.
     * @param value The value of the URL query parameter.
     */
    public void addURLQueryParameter(String parameterName, String value)
    {
        if (StringUtils.notEmpty(parameterName) && StringUtils.notEmpty(value))
        {
            getQueryParams().setQueryParam(parameterName, value);
        }
    }
    
    /**
     * This method will remove the given URL query parameter from the list of URL query parameters. If there is no such 
     * parameter in the current list or the parameterName is null then no action is taken. The parameterName is case 
     * sensitive.
     * 
     * @param parameterName The name of the parameter to be removed.
     */
    public void removeURLQueryParameter(String parameterName)
    {
        getQueryParams().removeQueryParam(parameterName); //null check is done in the removeQueryParam() method
    }
    
    /**
     * This method returns the value of the given URL query parameter as a string. If no URL query parameter with that name
     * exists then null is returned. The parameterName is case sensitive.
     * 
     * @param parameterName Name of the URL query parameter for which the value shall be returned.
     * 
     * @return See desc.
     */
    public String getURLQueryParameter(String parameterName)
    {
        return getQueryParams().getQueryParam(parameterName); //null check is done in the getQueryParam() method
    }
    
    /**
     * This method return true if a given URL query parameter is already part of the list. If it doesn't exist then false
     * is returned.
     * 
     * @param parameterName The URL query parameter to check for.
     * 
     * @return See desc.
     */
    public boolean existURLQueryParameter(String parameterName)
    {
        return getURLQueryParameter(parameterName) != null;
    }
    
    /**
     * This method adds the given parameter and value to the list of HTTP header parameters. If there is already a parameter
     * with that name then it will be overridden. The parameterName is case insensitive, so 'Param1' and 'param1' as the 
     * parameter name are treated as the same parameters. This behaviour matches the HTTP specification. If any of the
     * two parameters is empty or null then they are not added to the list of URL query parameters.
     * 
     * @param parameterName The name of the HTTP header parameter.
     * @param value The value of the HTTP header parameter.
     */
    public void addHTTPHeaderParameter(String parameterName, String value)
    {
        getHttpHeaderParams().setHeaderProperty(parameterName, value);
    }
    
    /**
     * This method will remove the given HTTP header parameter from the list of HTTP headers. If there is no such parameter
     * in the current list or the parameterName is null then no action is taken. The parameterName is case insensitive.
     * 
     * @param parameterName The name of the parameter to be removed.
     */
    public void removeHTTPHeaderParameter(String parameterName)
    {
        getHttpHeaderParams().setHeaderProperty(parameterName, null);
    }
    
    /**
     * This method returns the value of the given HTTP header field as a string. If no HTTP header field with that name
     * exists then null is returned. The parameterName is case insensitive.
     * 
     * @param parameterName Name of the HTTP header field for which the value shall be returned.
     * 
     * @return See desc.
     */
    public String getHTTPParameter(String parameterName)
    {
        return getHttpHeaderParams().getHeaderProperty(parameterName);
    }
    
    /**
     * This method return true if a given HTTP header parameter is already part of the list. If it doesn't exist then false
     * is returned.
     * 
     * @param parameterName The HTTP header parameter to check for.
     * 
     * @return See desc.
     */
    public boolean existHTTPParameter(String parameterName)
    {
        return getHTTPParameter(parameterName) != null;
    }

    /**
     * This method returns the value of the given parameter as a String. This value can either be in a HTTP header field 
     * or a URL query parameter. If a value exists in both (URL query parameter and HTTP header) then the HTTP header will
     * take precedence and be returned. If the parameter does not exist then null is returned.
     * 
     * @param parameterName Name of the HTTP header or URL query parameter for which the value shall be returned.
     * 
     * @return See desc.
     */
    public String getRequestParameter(String parameterName)
    {
        String value =  getHTTPParameter(parameterName);
        if (value == null)
        {
            value = getURLQueryParameter(parameterName);
        }
        return value;
    }
    
    @Override
    public String toString()
    {
        return "BaseRequestParameter [zone=" + zone + ", context=" + context + ", queryParams="
                + queryParams + ", httpHeaderParams=" + httpHeaderParams + "]";
    }
}
