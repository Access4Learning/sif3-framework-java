/*
 * SubscriptionResource.java
 * Created: 13/04/2014
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

import java.util.HashMap;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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

import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.ErrorDetails;
import sif3.infra.common.env.mgr.DirectProviderEnvironmentManager;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.SubscriptionCollectionType;
import sif3.infra.common.model.SubscriptionType;

/*
 * http://localhost:9080/SIF3InfraREST/sif3/subscriptions/69df9d79-8e01-43e8-825f-d0dc1775761b
 */

/**
 * This class defines the Subscription Connector Resource. Since this is a direct environment at this stage it won't actually implement the resource,
 * rather "dummy it out". This means it will only print the request to the debug log but it will then return NOT_SUPPORTED error to
 * the caller.
 * 
 * @author Joerg Huber
 */
@Path("/subscriptions{mimeType:(\\.[^/]*?)?}")
public class SubscriptionResource extends InfraResource
{
	/* Below variables are for testing purposes only */
	private ObjectFactory infraObjectFactory = new ObjectFactory();
	private static HashMap<String, SubscriptionType> subscriptionsSet = new HashMap<String, SubscriptionType>();
	/* End Testing variables */

	public SubscriptionResource(@Context UriInfo uriInfo,
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

	/*-----------------------------*/
	/*-- Queue Interface Methods --*/
	/*-----------------------------*/
    
    /*
     * Get all Subscriptions for this environment.
     */
	@GET
//Let everything through and then deal with it when needed. 
//@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllSubscriptions()
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Get Subscriptions (REST GET - Plural)");
		}
		if (isTestMode())
		{
			// The call below means that this test mode only works for Basic and SIF_HMACSHA256
			ErrorDetails error = validSession(getAuthInfo(), false, null);
			if (error != null) // Not allowed to access!
			{
				return makeInfraErrorResponse(error, ResponseAction.QUERY, null);
			}
			SubscriptionCollectionType subscriptions = infraObjectFactory.createSubscriptionCollectionType();
			for (SubscriptionType subscription : subscriptionsSet.values())
			{
				subscriptions.getSubscription().add(subscription);
			}
			return makeResponse(subscriptions, false, Status.OK.getStatusCode(), false, ResponseAction.QUERY, null, getInfraMarshaller(), getFWInfraSchemaInfo());				
		}
		else
		{
			return makeInfraErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Subscriptions not supported.", "This DIRECT Environment implementation does not support subscriptions, yet."), ResponseAction.QUERY, null);
		}
	} 
	
    /*
     * Get a specific subscription for this environment.
     */
	@GET
	@Path("{subscriptionID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
//  Let everything through and then deal with it when needed. 
//  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSubscription(@PathParam("subscriptionID") String subscriptionID,
                                    @PathParam("mimeType") String mimeType)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Get Subscription by subscription ID (REST GET - Single): "+subscriptionID+" and URL Postfix mimeType = '" + mimeType + "'");
		}
		if (isTestMode())
		{
			// The call below means that this test mode only works for Basic and SIF_HMACSHA256
			ErrorDetails error = validSession(getAuthInfo(), false, null);
			if (error != null) // Not allowed to access!
			{
				return makeInfraErrorResponse(error, ResponseAction.QUERY, null);
			}
			SubscriptionType subscription = subscriptionsSet.get(subscriptionID);
			if (subscription != null)
			{
				return makeResponse(subscription, false, Status.OK.getStatusCode(), false, ResponseAction.QUERY, null, getInfraMarshaller(), getFWInfraSchemaInfo());				
			}
			else
			{
				return makeInfraErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Subscription with ID = "+subscriptionID+" does not exist."), ResponseAction.QUERY, null);				
			}
		}
		else
		{
			return makeInfraErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Subscriptions not supported.", "This DIRECT Environment implementation does not support subscriptions, yet."), ResponseAction.QUERY, null);
		}
	} 

    /*
     * Create a Subscription for this environment.
     */
	@POST
//Let everything through and then deal with it when needed. 
//@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createSubscription(String payload)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Create Subscription (REST POST - Single): "+payload);
		}
		if (isTestMode())
		{
			// The call below means that this test mode only works for Basic and SIF_HMACSHA256
			ErrorDetails error = validSession(getAuthInfo(), false, null);
			if (error != null) // Not allowed to access!
			{
				return makeInfraErrorResponse(error, ResponseAction.CREATE, null);
			}
			try
			{
				SubscriptionType inputSubscription = (SubscriptionType)getInfraUnmarshaller().unmarshal(payload, SubscriptionType.class, getRequestPayloadMetadata().getMimeType(), getRequestPayloadMetadata().getSchemaType());
				inputSubscription.setId(UUIDGenerator.getUUID());
				subscriptionsSet.put(inputSubscription.getId(), inputSubscription);
				
				return makeResponse(inputSubscription, false, Status.CREATED.getStatusCode(), false, ResponseAction.CREATE, null, getInfraMarshaller(), getFWInfraSchemaInfo());
			}
			catch (UnmarshalException ex)
			{
				logger.error("Failed to unmarshal payload into an SubscriptionType: "+ ex.getMessage(), ex);
				logger.error("Subscription Payload: "+ payload);
				return makeInfraErrorResponse( new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal subscription payload: "+ ex.getMessage()), ResponseAction.CREATE, null);
			}
			catch (UnsupportedMediaTypeExcpetion ex)
			{
				logger.error("Failed to unmarshal payload into an SubscriptionType: "+ ex.getMessage(), ex);
				logger.error("Subscription Payload: "+ payload);
				return makeInfraErrorResponse( new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Failed to unmarshal subscription payload: "+ ex.getMessage()), ResponseAction.CREATE, null);
			}
		}
		else
		{
			return makeInfraErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Subscriptions not supported.", "This DIRECT Environment implementation does not support subscriptions, yet."), ResponseAction.CREATE, null);
		}
	} 

	/*
	 * Delete a specific queue.
	 */
	@DELETE
	@Path("{subscriptionID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
//  Let everything through and then deal with it when needed. 
//  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response removeSubscription(@PathParam("subscriptionID") String subscriptionID,
                                       @PathParam("mimeType") String mimeType)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Remove Subscription (REST DELETE - Single) with subscriptionID = "+subscriptionID+" and URL Postfix mimeType = '" + mimeType + "'");
		}
		if (isTestMode())
		{
			// The call below means that this test mode only works for Basic and SIF_HMACSHA256
			ErrorDetails error = validSession(getAuthInfo(), false, null);
			if (error != null) // Not allowed to access!
			{
				return makeInfraErrorResponse(error, ResponseAction.DELETE, null);
			}
			SubscriptionType subscription = subscriptionsSet.get(subscriptionID);
			if (subscription != null)
			{
				subscriptionsSet.remove(subscriptionID);
				return makeResopnseWithNoContent(false, false, ResponseAction.DELETE, null);				
			}
			else
			{
				return makeInfraErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Subscription with ID = "+subscriptionID+" does not exist."), ResponseAction.DELETE, null);				
			}
		}
		else
		{
			return makeInfraErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Subscriptions not supported.", "This DIRECT Environment implementation does not support subscriptions, yet."), ResponseAction.DELETE, null);
		}
	}
}
