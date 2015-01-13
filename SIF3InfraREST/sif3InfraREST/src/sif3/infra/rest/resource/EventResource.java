/*
 * EventResource.java
 * Created: 21/03/2014
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

package sif3.infra.rest.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.ws.ErrorDetails;
import sif3.infra.common.env.mgr.DirectProviderEnvironmentManager;
import sif3.infra.common.interfaces.EnvironmentManager;

/**
 * This class defines the Event Connector Resource. Since this is a direct environment at this stage it won't actually implement the resource,
 * rather "dummy it out". This means it will only print the request to the debug log but it will then return NOT_SUPPORTED error to
 * the caller.
 * 
 * @author Joerg Huber
 *
 */
@Path("/eventsConnector{mimeType:(\\.[^/]*?)?}")
public class EventResource extends BaseResource
{
	public EventResource(@Context UriInfo uriInfo,
			             @Context HttpHeaders requestHeaders,
			             @Context Request request,
			             @PathParam("mimeType") String mimeType)
	{
		super(uriInfo, requestHeaders, request, "", null, null);
    logger.debug("URL Postfix mimeType: '"+mimeType+"'");
	}

	/*----------------------*/
	/*-- Abstract Methods --*/
	/*----------------------*/

	/* (non-Javadoc)
     * @see sif3.infra.rest.resource.BaseResource#getEnvironmentManager()
     */
    @Override
    public EnvironmentManager getEnvironmentManager()
    {
	    return DirectProviderEnvironmentManager.getInstance();
    }
   
    // -------------------------------------------------//
	// -- POST Section: This is the C(reate) in CRUD. --//
	// -------------------------------------------------//
	@POST
//Let everything through and then deal with it when needed. 
//@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createEvent(String eventPayload)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Create Event (REST POST) with input data: " + eventPayload);
		}
		
		return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Events not supported.", "This DIRECT Environment implementation does not support events, yet."), ResponseAction.CREATE);
	}
}
