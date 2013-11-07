/*
 * OperationStatus.java
 * Created: 28/09/2013
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

package sif3.common.ws;

import java.io.Serializable;

/**
 * This class is used in the responses for Bulk operations in SIF3. The response for each object in a bulk operation returns the 
 * resourceID for which the response is for, the status of the operation (success, error code) and in case of an error the actual
 * error details. This class abstracts all of that so that the higher levels of this framework don't need to deal with low level
 * infrastructure messages.
 * 
 * @author Joerg Huber
 *
 */
public class OperationStatus implements Serializable
{
    private static final long serialVersionUID = -6306835738956301003L;
    
    private String resourceID = null;
    private int status = 0;
    private ErrorDetails error = null; // is optional. Only set if there was an error with a resource operation.
    
    public OperationStatus() 
    {
    	this(null, 0, null);
    }
    
    /**
     * Constructor
     * 
     * @param resourceID The Id of the resouce for which the status is returned.
     * @param status The status of the operation. This is most likely a HTTP status code as defined for each operation in SIF3.
     */
    public OperationStatus(String resourceID, int status) 
    {
    	this(resourceID, status, null);
    }
    
    /**
     * Constructor
     * 
     * @param resourceID The Id of the resouce for which the status is returned.
     * @param status The status of the operation. This is most likely a HTTP status code as defined for each operation in SIF3.
     * @param error If the status code indicates an error (HTTP status of 4xx or 5xx) then this porperty will hold the actual error details.
     */
    public OperationStatus(String resourceID, int status, ErrorDetails error) 
    {
    	this.resourceID = resourceID;
    	this.status = status;
    	this.error = error;
    }
    
	public String getResourceID()
    {
    	return this.resourceID;
    }
	
	public void setResourceID(String resourceID)
    {
    	this.resourceID = resourceID;
    }
	
	public int getStatus()
    {
    	return this.status;
    }
	
	public void setStatus(int status)
    {
    	this.status = status;
    }
	
	public ErrorDetails getError()
    {
    	return this.error;
    }
	
	public void setError(ErrorDetails error)
    {
    	this.error = error;
    }

	@Override
    public String toString()
    {
	    return "OperationStatus [resourceID=" + this.resourceID + ", status=" + this.status
	            + ", error=" + this.error + "]";
    }
}
