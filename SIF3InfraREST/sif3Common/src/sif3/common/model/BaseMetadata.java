/*
 * BaseMetadata.java
 * Created: 09/09/2014
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

import sif3.common.header.HeaderProperties;

/**
 * This class is mainly intended to be used to store information extracted from a HTTP request and pass it along 
 * to an object provider. The properties of this class are generally retrieved either from the HTTP Headers or 
 * URL Query Parameter from a HTTP request.
 * 
 * @author Joerg Huber
 *
 */
public class BaseMetadata implements Serializable
{
    private static final long serialVersionUID = 4812586181544050954L;
   
    private String generatorID = null;  // This is an optional field and might be provided by the consumer.
    
    // The following two fields are early adopters for proposed SIF 3.1 inclusions. Should only be used once officially
    // released. They may or may not be set. Until the official release they may not be set at all and if they are they
    // may only be available in a DIRECT environment but NOT through a BROKERED environment.
    private String applicationKey = null; // The applicationKey from the HTTP header field 'applicationKey' set by the consumer. 
 	private String authentictedUser = null; // The user Token from the HTTP header field 'authentictedUser' set by the consumer. 

 	private RequestParameters requestParameters = new RequestParameters();
 	
 	/**
 	 * Constructor.
 	 *  
 	 * @param queryParams Available URL query parameters of the request. Can be null.
 	 * @param httpHeaderParams Available HTTP header parameters of the request. Can be null. The name of the header fields
 	 *                         must be in lower case.
 	 */
 	public BaseMetadata(URLQueryParameter queryParams, HeaderProperties httpHeaderParams)
 	{
 		super();
 		requestParameters.setHttpHeaderParams(httpHeaderParams);
 		requestParameters.setQueryParams(queryParams);
 	}
 	
	public String getGeneratorID()
    {
    	return this.generatorID;
    }

	public void setGeneratorID(String generatorID)
    {
    	this.generatorID = generatorID;
    }

	/**
	 * IMPORTANT:<br>
	 * This value is set by the HTTP Header called 'applicationKey'. This is set by the consumer and if set
	 * it will be passed to the provider unaltered. Nevertheless it is optional and therefore might not be available.
	 * The SIF3 Framework will set this value if it is in the HTTP header. If it cannot find it in there it will
	 * attempt to get the environment's applicationKey and set it with that value. In such a case the applicationKey is
	 * the consumer's applicationKey if the provider acts in a DIRECT environment where as in a BROKERED environment
	 * it will be the provider's applicationKey.
	 * 
	 * @return See Desc. Can be null in some cases where the applicationKey cannot yet be determined (i.e before
	 *         request is fully authenticated)
	 */
	public String getApplicationKey()
	{
		return applicationKey;
	}

	public void setApplicationKey(String applicationKey)
	{
		this.applicationKey = applicationKey;
	}

	/**
	 * Returns the value of the HTTP Header 'authentictedUser'. This is set by the consumer and if set it will be 
	 * passed to the provider unaltered. Nevertheless it is optional and therefore might not be available (i.e. set to
	 * null).
	 * 
	 * @return See desc.
	 */
	public String getAuthentictedUser()
	{
		return authentictedUser;
	}

	public void setAuthentictedUser(String authentictedUser)
	{
		this.authentictedUser = authentictedUser;
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
    	return requestParameters.getURLQueryParameter(parameterName);
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
    	return requestParameters.getHTTPParameter(parameterName);
    }
    
    /**
     * This method returns all HTTP headers.
     * 
     * @return See desc.
     */
    public HeaderProperties getHTTPParameters()
    {
    	return requestParameters.getHttpHeaderParams();
    }
    
    /**
     * This method returns the value of the given parameter as a String. This value can either be in a HTTP header field 
     * or a URL query parameter. If a value exists in both (URL query parameter and HTTP header) then the HTTP header will
     * take precedence and be returned. If the parameter does not exist then null is returned.
     * 
     * @param paramterName Name of the HTTP header or URL query parameter for which the value shall be returned.
     * 
     * @return See desc.
     */
    public String getRequestParameter(String paramterName)
    {
    	String value =  getHTTPParameter(paramterName);
    	if (value == null)
    	{
    		value = getURLQueryParameter(paramterName);
    	}
    	return value;
    }
	
	@Override
    public String toString()
    {
	    return "BaseMetadata [generatorID=" + this.generatorID + ", applicationKey="
	            + this.applicationKey + ", authentictedUser=" + this.authentictedUser
	            + ", requestParameters=" + this.requestParameters + "]";
    }
	
}
