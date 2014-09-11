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


/**
 * @author Joerg Huber
 *
 */
public class RequestMetadata extends BaseMetadata
{
    private static final long serialVersionUID = 1477907327110025038L;
    
    private String queryIntention = null; // May only be used in HTTP GET requests
    private String navigationID = null; // May only be used in HTTP GET requests
    private String sourceName = null; // May only be used in brokered environment! Broker will assign this.
    
    public RequestMetadata()
    {
    	super();
    }
    
	public String getQueryIntention()
    {
    	return this.queryIntention;
    }
	
	public void setQueryIntention(String queryIntention)
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

	@Override
    public String toString()
    {
	    return "RequestMetadata [queryIntention=" + this.queryIntention + ", navigationID="
	            + this.navigationID + ", sourceName=" + this.sourceName + ", toString()="
	            + super.toString() + "]";
    }
}
