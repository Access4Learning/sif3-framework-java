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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SchemaInfo;
import sif3.common.ws.ErrorDetails;
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
//	/* Below variables are for testing purposes only */
//	private static Boolean testMode = null;
//	/* End Testing variables */
	
	private MarshalFactory marshaller = new InfraMarshalFactory();
	private UnmarshalFactory unmarshaller = new InfraUnmarshalFactory();

	public InfraResource(UriInfo uriInfo, HttpHeaders requestHeaders, Request request, String servicePrefixPath, String zoneID, String contextID)
	{
		super(uriInfo, requestHeaders, request, servicePrefixPath, zoneID, contextID);
		determineMediaTypes(marshaller, unmarshaller, true);
		
		if (logger.isDebugEnabled())
		{
			logger.debug("Request Media & Schema Info : " + getRequestPayloadMetadata());
			logger.debug("Response Media & Schema Info: " + getInfraResponsePayloadMetadata());
		}
	}
	
	public MarshalFactory getInfraMarshaller()
	{
		return this.marshaller;
	}

	public UnmarshalFactory getInfraUnmarshaller()
	{
		return this.unmarshaller;
	}

	@Override
    public MarshalFactory getMarshaller()
    {
	    return getInfraMarshaller();
    }

	@Override
    public UnmarshalFactory getUnmarshaller()
    {
	    return getInfraUnmarshaller();
    }

    public Response makeInfraErrorResponse(ErrorDetails error, ResponseAction responseAction, ResponseParameters customResponseParams)
    {
        return makeErrorResponse(error, responseAction, customResponseParams, getFWInfraSchemaInfo());    
    }
    
    public SchemaInfo getFWInfraSchemaInfo()
    {
        return getEnvironmentManager().getEnvironmentInfo().getInfraPayloadMetadata().getSchemaInfo();
    }

//	protected boolean isTestMode()
//	{
//		if (testMode == null)
//		{
//			AdvancedProperties props = getEnvironmentManager().getServiceProperties();
//			testMode = props.getPropertyAsBool("resource.testmode", false);
//		}
//		return testMode;
//	}

}
