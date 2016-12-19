/*
 * CreateOperationStatus.java
 * Created: 20/03/2014
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

package sif3.common.ws;

/**
 * @author Joerg Huber
 *
 */
public class CreateOperationStatus extends OperationStatus
{
    private static final long serialVersionUID = -272473940476813431L;
    
    private String advisoryID = null;

    public CreateOperationStatus() 
    {
    	this(null, null, 0, null);
    }
    
    /**
     * Constructor
     * 
     * @param resourceID The Id of the resource for which the status is returned.
     * @param status The status of the operation. This is most likely a HTTP status code as defined for each operation in SIF3.
     */
    public CreateOperationStatus(String resourceID, String advisoryID, int status) 
    {
    	this(resourceID, advisoryID, status, null);
    }
    
    /**
     * Constructor
     * 
     * @param resourceID The Id of the resource for which the status is returned.
     * @param status The status of the operation. This is most likely a HTTP status code as defined for each operation in SIF3.
     * @param error If the status code indicates an error (HTTP status of 4xx or 5xx) then this property will hold the actual error details.
     */
    public CreateOperationStatus(String resourceID, String advisoryID, int status, ErrorDetails error) 
    {
    	super(resourceID, status, error);
    	this.advisoryID = advisoryID;
    }

	public String getAdvisoryID()
    {
    	return this.advisoryID;
    }

	public void setAdvisoryID(String advisoryID)
    {
    	this.advisoryID = advisoryID;
    }

	@Override
    public String toString()
    {
	    return "CreateOperationStatus [advisoryID=" + this.advisoryID + ", toString()="
	            + super.toString() + "]";
    }

}
