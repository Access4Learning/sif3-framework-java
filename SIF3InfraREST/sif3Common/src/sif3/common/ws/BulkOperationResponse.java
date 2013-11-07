/*
 * BulkOperationResponse.java
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

import java.util.List;

/**
 * The HTTP response of all Bulk Operations (Create, Update, Delete) in SIF3 have certain structure. This class is an abstract 
 * representation response, so that developers don't need to know the low level infrastructure details of a Bulk Response.
 * The Bulk Response returns a status or an error for each object in the original bulk request. This class is a list of the responses
 * to each object in the original bulk request.
 * 
 * @author Joerg Huber
 *
 */
public class BulkOperationResponse extends BaseResponse
{
    private static final long serialVersionUID = -8767410410574237898L;

    private List<OperationStatus> operationStatuses = null;

	public List<OperationStatus> getOperationStatuses()
    {
    	return this.operationStatuses;
    }

	public void setOperationStatuses(List<OperationStatus> operationStatuses)
    {
    	this.operationStatuses = operationStatuses;
    }

	@Override
    public String toString()
    {
	    return "BulkOperationResponse [operationStatuses=" + this.operationStatuses
	            + ", toString()=" + super.toString() + "]";
    }
}
