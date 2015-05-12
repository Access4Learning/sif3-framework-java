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

import sif3.common.header.HeaderValues.QueryIntention;


/**
 * @author Joerg Huber
 *
 */
public class RequestMetadata extends BaseMetadata
{
    private static final long serialVersionUID = 1477907327110025038L;
    
    private QueryIntention queryIntention = null; // May only be used in HTTP GET requests
    private String navigationID = null; // May only be used in HTTP GET requests
    private String sourceName = null; // May only be used in brokered environment! Broker will assign this.
    
    // The following is not stored in a HTTP header of the request. It is a useful bit of data for the provider
    // in a DIRECT environment as it uniquely identifies the consumer's environment. In a BROKERED environment this
    // will always be the environment ID of the provider in relation to the broker.
    private String environmentID = null; // Only useful in DIRECT environments
    
	public RequestMetadata()
    {
    	super();
    }
    
	public QueryIntention getQueryIntention()
    {
    	return this.queryIntention;
    }
	
	public void setQueryIntention(QueryIntention queryIntention)
    {
    	this.queryIntention = queryIntention;
    }
	
	public String getNavigationID()
    {
    	return this.navigationID;
    }
	
	public void setNavigationID(String navigationID)
    {
    	this.navigationID = navigationID;
    }
	
	public String getSourceName()
    {
    	return this.sourceName;
    }
	
	public void setSourceName(String sourceName)
    {
    	this.sourceName = sourceName;
    }

	/**
	 * IMPORTANT: This value is only really of any use in a DIRECT environment where this environmentID is the
	 *            environmentID of the consumer. In a BROKERED environment this ID is the ID of the provider
	 *            environment to the broker and is always the same. 
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

	@Override
    public String toString()
    {
	    return "RequestMetadata [queryIntention=" + this.queryIntention + ", navigationID="
	            + this.navigationID + ", sourceName=" + this.sourceName + ", environmentID="
	            + this.environmentID + ", toString()=" + super.toString() + "]";
    }
}
