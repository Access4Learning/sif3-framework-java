/*
 * ResponseParameters.java
 * Created: 27/05/2016
 *
 * Copyright 2016 Systemic Pty Ltd
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

import sif3.common.header.HeaderProperties;

/**
 * This class is intended to be a base class for some response parameter management such as HTTP Header fields.<br/><br/>
 * 
 * The main intend of this class is to allow providers to pass custom HTTP header fields to a consumer in a response.
 * The SIF Specification allows for this, so that functionality is provided in the SIF3 Framework through that class. 
 * 
 * @author Joerg Huber
 */
public class ResponseParameters implements Serializable
{
    private static final long serialVersionUID = -2592549908492220258L;

    private HeaderProperties httpHeaderParams = new HeaderProperties();

    /**
     * Constructor.
     */
    public ResponseParameters()
    {
        this(null);
    }

    /**
     * Constructor.
     *  
     * @param httpHeaderParams Available HTTP header parameters of the response. Can be null.
     */
    public ResponseParameters(HeaderProperties httpHeaderParams)
    {
        super();
        setHttpHeaderParams(httpHeaderParams);
    }

    public HeaderProperties getHttpHeaderParams()
    {
        return httpHeaderParams;
    }

    /**
     * Sets the HTTP Headers to the required values.
     * 
     * @param httpHeaderParams If null then all existing values will be removed.
     */
    public void setHttpHeaderParams(HeaderProperties httpHeaderParams)
    {
        if (httpHeaderParams == null)
        {
            this.httpHeaderParams = new HeaderProperties();
        }
        else
        {
            this.httpHeaderParams = httpHeaderParams;
        }
    }

    /*-------------------------*/
    /*-- Convenience Methods --*/
    /*-------------------------*/
    
    /**
     * This method adds the given parameter and value to the list of HTTP header parameters. If there is already a parameter
     * with that name then it will be overridden. The parameterName is case insensitive, so 'Param1' and 'param1' as the 
     * parameter name are treated as the same parameters. This behaviour matches the HTTP specification. If any of the
     * two parameters is empty or null then they are not added to the list of HTTP Headers parameters.
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

    @Override
    public String toString()
    {
        return "ResponseParameters [httpHeaderParams=" + httpHeaderParams + "]";
    }
}
