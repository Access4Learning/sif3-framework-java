/*
 * InfraResource.java Created: 11/09/2013
 * 
 * Copyright 2013 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package sif3.infra.rest.resource;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;

/**
 * This is the class to be used (extended) by all infrastructure resources. It holds some infrastructure specific knowledge and 
 * operations.
 * 
 * @author Joerg Huber
 */
public abstract class InfraResource extends BaseResource
{
	private MarshalFactory marshaller = new InfraMarshalFactory();
	private UnmarshalFactory unmarshaller = new InfraUnmarshalFactory();

	public InfraResource(UriInfo uriInfo, HttpHeaders requestHeaders, Request request, String servicePrefixPath, String zoneID, String contextID)
	{
		super(uriInfo, requestHeaders, request, servicePrefixPath, zoneID, contextID);
	}

	public MarshalFactory getMarshaller()
	{
		return this.marshaller;
	}

	public UnmarshalFactory getUnmarshaller()
	{
		return this.unmarshaller;
	}

}
