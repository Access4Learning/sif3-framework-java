/*
 * MultiOperationStatusList.java
 * Created: 07/12/2015
 *
 * Copyright 2015 Systemic Pty Ltd
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

package sif3.common.ws.model;

import java.io.Serializable;
import java.util.List;

import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;

/**
 * This class abstracts the data structure of a SIF 3.x Bulk Operation Response Object. These bulk operations return a list 
 * style object where each element in the list holds either a status or an error message. Since there could be issues with
 * marshalling and unmarshalling of SIF 3.x objects into this object as well as any other internal errors that may occur
 * during standard process flow this POJO style object holds an additional error element. This error element is an overall
 * error relation to the POJO. For details see getError() method. 
 * 
 * @author Joerg Huber
 *
 */
public class MultiOperationStatusList<E extends OperationStatus> implements Serializable
{
    private static final long serialVersionUID = 360186323764301036L;
    
	private ErrorDetails error = null;
	private List<E> operationStatuses = null;

	/**
	 * This is an overall error if anything is wrong with the MultiOperationStatusList (i.e. SIF Object could not be marshaled
	 * into this object, data load errors etc.). Please note that if there is an error it must be assumed that the Operation Status
	 * list that this class contains cannot be relied upon and should be assumed as invalid.
	 * 
	 * @return The error object containing information about the problem with this object and its data.
	 */
    public ErrorDetails getError()
	{
		return error;
	}

	public void setError(ErrorDetails error)
	{
		this.error = error;
	}
	
	public boolean hasError()
	{
		return (error != null);
	}

	public List<E> getOperationStatuses()
    {
    	return this.operationStatuses;
    }

	public void setOperationStatuses(List<E> operationStatuses)
    {
    	this.operationStatuses = operationStatuses;
    }

	@Override
    public String toString()
    {
	    return "MultiOperationStatusList [error=" + error
	            + ", operationStatuses=" + operationStatuses + "]";
    }
}
