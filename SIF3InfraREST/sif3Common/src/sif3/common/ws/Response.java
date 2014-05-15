/*
 * Response.java
 * Created: 04/09/2013
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

import java.net.URI;

/**
 * The HTTP response of all Operations (Create, Update, Delete, Query), that are not Bulk Operations, have a certain structure in SIF3. 
 * This class is an abstract representation of such a response, so that developers don't need to know the low level infrastructure 
 * details of a response. It also allows to abstract the actual transport (REST or SOAP). The higher levels of this framework only
 * deal with this Response class rather than the low level HTTP response and the SIF3 infrastructure details.
 * 
 * @author Joerg Huber
 *
 */
public class Response extends BaseResponse
{
	private static final long serialVersionUID = 8453625887340172126L;

	private Class<?> dataObjectType = null;
	private Object dataObject = null;
	private URI resourceURI = null;
	
	public Object getDataObject()
	{
		return dataObject;
	}

	public void setDataObject(Object dataObject)
	{
		this.dataObject = dataObject;
	}

	public Class<?> getDataObjectType()
    {
    	return this.dataObjectType;
    }

	public void setDataObjectType(Class<?> dataObjectType)
    {
    	this.dataObjectType = dataObjectType;
    }

	public URI getResourceURI()
    {
    	return this.resourceURI;
    }

	public void setResourceURI(URI resourceURI)
    {
    	this.resourceURI = resourceURI;
    }

	@Override
    public String toString()
    {
	    return "Response [dataObjectType=" + this.dataObjectType + ", dataObject="
	            + this.dataObject + ", resourceURI=" + this.resourceURI + ", toString()="
	            + super.toString() + "]";
    }
}
