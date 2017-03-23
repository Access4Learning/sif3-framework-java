/*
 * RequestMetadata.java
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

import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.QueryIntention;


/**
 * @author Joerg Huber
 *
 */
public class RequestMetadata extends BaseMetadata
{
    private static final long serialVersionUID = 1477907327110025038L;
    
    private QueryIntention queryIntention = null; // May only be used in HTTP GET requests

    // Refer to javadoc for the getSourceName() method.
    private String sourceName = null; 
    
    // Refer to javadoc for the getEnvironmentID() method.
    private String environmentID = null; // Only useful in DIRECT environments
    
    // Introduced in SIF 3.2.1. Uniquely identifies the consumer. 
    // In a brokered environment this value will be set by the broker. 
    // In a direct environment this framework will assign the value according to the environment of the consumer regardless of
    // what the consumer has set this header for. This will ensure that a consumer cannot pretend to be someone else.
    private String fingerprint = null;
    
    public RequestMetadata(URLQueryParameter queryParams, HeaderProperties httpHeaderParams)
    {
    	super(queryParams, httpHeaderParams);
    }
    
	public QueryIntention getQueryIntention()
    {
    	return this.queryIntention;
    }
	
	public void setQueryIntention(QueryIntention queryIntention)
    {
    	this.queryIntention = queryIntention;
    }
	
	/**
	 * IMPORTANT:<br>
     * This value is assigned by the broker in a brokered environment. It is something that identifies the source
     * of the request. It could be the 'applicationKey' of the consumer's environment but this is not mandated.
     * It might be a different value and should not be used to make 'critical' decisions about the provider's 
     * behaviour.
     *
     * In a DIRECT environment the SIF3 Framework implementation will assign the 'applicationKey' of the consumer's
     * environment. This is an arbitrary decision that is true for this framework. Similar as with the brokered
     * environment a provider should not base 'critical' decisions based on this value.
     * 
     * @return See desc.
	*/
	public String getSourceName()
    {
    	return this.sourceName;
    }
	
	public void setSourceName(String sourceName)
    {
    	this.sourceName = sourceName;
    }

	/**
	 * IMPORTANT:<br/>
	 * The environmentID is not stored in a HTTP header of the request. It could be a useful bit of data for the 
	 * provider in a DIRECT environment as it uniquely identifies the consumer's environment. This is a property
	 * that is provided through this SIF3 Framework only. It is NOT a SIF 3.x specified header or property in any
	 * way.<br/>
	 * If the provider is acting in a BROKERED environment then the environmentID in this property is the ID of 
	 * the provider's environment to the broker and is always the same.<br/><br/>
	 * 
	 * It is therefore important that this value is only used as a 'decision' making property in a DIRECT environment.
	 *            
	 * @return See Desc. Can be null in some cases where the environment cannot yet be determined (i.e before
	 *         request is fully authenticated). 
	 */
    public String getEnvironmentID()
    {
    	return this.environmentID;
    }

	public void setEnvironmentID(String environmentID)
    {
    	this.environmentID = environmentID;
    }

    public String getFingerprint()
    {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint)
    {
        this.fingerprint = fingerprint;
    }

    @Override
    public String toString()
    {
        return "RequestMetadata [queryIntention=" + queryIntention + ", sourceName=" + sourceName
                + ", environmentID=" + environmentID + ", fingerprint=" + fingerprint
                + ", toString()=" + super.toString() + "]";
    }
}
