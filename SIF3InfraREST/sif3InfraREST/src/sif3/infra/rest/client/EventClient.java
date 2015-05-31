/*
 * EventClient.java
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

package sif3.infra.rest.client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import sif3.common.CommonConstants;
import sif3.common.conversion.MarshalFactory;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.persist.model.SIF3Session;
import sif3.common.ws.BaseResponse;
import sif3.infra.common.env.types.ConsumerEnvironment.ConnectorName;
import sif3.infra.common.env.types.ProviderEnvironment;
import au.com.systemic.framework.utils.StringUtils;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

/**
 * This class implements the function(s) required to publish REST SIF3 Events to a environment provider and/or broker. The event interface
 * is quite simple as it only allows to publish events but no other functions.
 *  
 * @author Joerg Huber
 *
 */
public class EventClient extends BaseClient
{
	private boolean allOK = true;
	private ProviderEnvironment providerEnvironment;
	private SIF3Session sif3Session;
	private String serviceName;
	
	/*
	 * @param providerEnvironment Environment information. Used to lookup event connector, baseURI etc, so that an event is sent to
	 *                            the correct location and using the correct protocol.
	 * @param sif3Session The active SIF3 session to use to create the authentication token.
	 * @param serviceName The serviceName for which the event is. This is generally the SIF Object name such as StudentPersonals (plural form!)
	 */
	public EventClient(ProviderEnvironment providerEnvironment, MediaType requestMediaType, MediaType responseMediaType, SIF3Session sif3Session, String serviceName, MarshalFactory dmMarshaller)
	{
		super();
		if (providerEnvironment == null)
		{
			logger.error("Provider Environment not valid/initialised. Cannot send events.");
			allOK = false;
		}
		if (sif3Session == null)
		{
			logger.error("No active SIF3 Session available. Provider might not be connected. Cannot send events.");
			allOK = false;
		}
		if (StringUtils.isEmpty(serviceName))
		{
			logger.error("No Service Name given for events. Cannot send events.");
			allOK = false;
		}
		URI eventConnector = providerEnvironment.getConnectorBaseURI(ConnectorName.eventsConnector);
		if (eventConnector == null)
		{
			logger.error("Event Connector not defined in environment. Cannot send events.");
			allOK = false;
		}
		
		// if we get here we should be ok.
		if (allOK)
		{
			this.providerEnvironment = providerEnvironment;
			this.sif3Session = sif3Session;
			this.serviceName = serviceName;
			setDataModelMarshaller(dmMarshaller);
//			setRequestMediaType(providerEnvironment.getMediaType(), null);
//			setResponseMediaType(providerEnvironment.getMediaType(), null);
			setRequestMediaType(requestMediaType, dmMarshaller);
			setResponseMediaType(responseMediaType, null);
			configureClienService(eventConnector, providerEnvironment.getSecureConnection());
		}
	}
	
	
    /**
     * This method will send the payload, which must be a multi-object (list object), to the event connector. This will then issue a HTTP POST
     * to the underlying event connector.
     * 
	 * @param event The event to be sent.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * @param customHdrFields Custom HTTP header fields to be added to the request.
	 *
	 * @return BaseResponse Object holding appropriate values and results of the call. This call won't return any data model objects, just
	 *                      status and/or an error message.
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
	public BaseResponse sendEvents(SIFEvent<?> event, SIFZone zone, SIFContext context, HeaderProperties customHdrFields) throws ServiceInvokationException
	{
		if (allOK) // Only send events if all is fine.
		{
			// Do we have valid event data to send?
			if ((event == null) || (event.getSIFObjectList() == null))
			{
				logger.debug("No event data given. No events sent.");
				BaseResponse response =  new BaseResponse();
				response.setHasEntity(false);
				response.setStatus(Status.ACCEPTED.getStatusCode());
				response.setStatusMessage(Status.ACCEPTED.getReasonPhrase());
				return response;
			}
	
			// If we get here it appears that all is good and valid. Try to send event now.
			WebResource service = getService();
			
			try
			{
				// Don't set zone & context here. They are header parameters in the case of events.
			    String payloadStr = getDataModelMarshaller().marshal(event.getSIFObjectList(), getRequestMediaType());
	
//				if (logger.isDebugEnabled())
//				{
//					logger.debug("sendEvents: Payload to send:\n"+payloadStr);
//				}
				HeaderProperties headerProps = getEventHeaders(event.getEventAction(),	event.getUpdateType(), zone, context, customHdrFields);
				
				Builder builder = setRequestHeaderAndMediaTypes(service, headerProps, false);
				logger.debug("Send Event with payload size: "+payloadStr.length());
				ClientResponse response = builder.post(ClientResponse.class, payloadStr);
				logger.debug("Receive Event Response Status: "+response.getStatus());
	
				return setEventResponse(service, response);
			}
			catch (Exception ex)
			{
				String errorMsg = "Failed to invoke 'sendEvents' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
				logger.error(errorMsg);
				throw new ServiceInvokationException(errorMsg, ex);
			}
		}
		else
		{
			return createClientErrorResponse(Status.PRECONDITION_FAILED, "This event client has not been initialised successfully.");
		}
	}

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/

	/*
	 * This method sets all header properties for events as specified by the SIF3 Spec.
	 * 
	 * @param zone The zone for this event. Can be null.
	 * @param context The context for this event. Can be null.
	 * @return
	 */
	private HeaderProperties getEventHeaders(EventAction eventAction, UpdateType updateType, SIFZone zone, SIFContext context, HeaderProperties overrideHdrFields)
	{
		// Set the override header fields
		HeaderProperties hdrProperties = (overrideHdrFields != null) ? new HeaderProperties(overrideHdrFields.getHeaderProperties()) : new HeaderProperties();

		// Add properties for the authentication header.
		ClientUtils.setAuthenticationHeader(hdrProperties, providerEnvironment.getAuthMethod(), sif3Session.getSessionToken(), sif3Session.getPassword());

		// Add event specific properties
		hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_MESSAGE_TYPE, HeaderValues.MessageType.EVENT.name());
		hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_EVENT_ACTION, eventAction.name());
		hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, HeaderValues.ServiceType.OBJECT.name());
		hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_NAME, serviceName);
						
		if (eventAction == EventAction.UPDATE)
		{
			if (updateType == null)
			{
				hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_UPDATE_TYPE, providerEnvironment.getDefaultUpdateType().name());
			}
			else
			{
				hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_UPDATE_TYPE, updateType.name());
			}
		}	
		
		if ((zone == null) || (zone.getIsDefault()) || StringUtils.isEmpty(zone.getId())) // Assume default zone!
		{
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_ZONE_ID, sif3Session.getDefaultZone().getId());			
		}
		else
		{
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_ZONE_ID, zone.getId());			
		}

		if ((context == null) || (context.getIsDefault()) || StringUtils.isEmpty(context.getId())) // Assume default context!
		{
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_CONTEXT_ID, CommonConstants.DEFAULT_CONTEXT_NAME);
		}
		else
		{
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_CONTEXT_ID, context.getId());
		}
		
		return hdrProperties;
	}
	
	private BaseResponse setEventResponse(WebResource service, ClientResponse clientResponse)
	{
		return setResponse(service, clientResponse, null, Status.ACCEPTED);
	}
}
