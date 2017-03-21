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
import sif3.common.ws.BaseResponse;
import sif3.infra.common.env.types.ConsumerEnvironment.ConnectorName;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.interfaces.ClientEnvironmentManager;
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
	private String serviceName;
	
	/**
	 * This method initialises the Event Client. As part of the initialisation it will also check a number of environment
	 * properties to ensure that the event client can function properly. If anything is incorrect an error will be logged
	 * and all methods of this class will have no effect (i.e. are ignored when calling).
	 * 
     * @param clientEnvMgr Session manager to access the clients session information.
	 * @param serviceName The serviceName for which the event is. This is generally the SIF Object name such as StudentPersonals (plural form!)
	 * @param useCompression TRUE: Payload (request & response) shall be compressed before sending or de-compressed at the
	 *                             time of receiving.
	 *                       FALSE: No compression is used.
	 */
	public EventClient(ClientEnvironmentManager clientEnvMgr, MediaType requestMediaType, MediaType responseMediaType, String serviceName, MarshalFactory dmMarshaller, boolean useCompression)
	{
		super(clientEnvMgr);
		if ((getClientEnvMgr() == null) || (getClientEnvMgr().getEnvironmentInfo() == null))
		{
			logger.error("Provider Environment not valid/initialised. Cannot send events.");
			allOK = false;
		}
		if (getSIF3Session() == null)
		{
			logger.error("No active SIF3 Session available. Provider might not be connected. Cannot send events.");
			allOK = false;
		}
		if (StringUtils.isEmpty(serviceName))
		{
			logger.error("No Service Name given for events. Cannot send events.");
			allOK = false;
		}
		URI eventConnector = getProviderEnvironment().getConnectorBaseURI(ConnectorName.eventsConnector);
		if (eventConnector == null)
		{
			logger.error("Event Connector not defined in environment. Cannot send events.");
			allOK = false;
		}
		
		// if we get here we should be ok.
		if (allOK)
		{
			this.serviceName = serviceName;
			setDataModelMarshaller(dmMarshaller);
			setRequestMediaType(requestMediaType, dmMarshaller);
			setResponseMediaType(responseMediaType, null);
			setUseCompression(useCompression);
			configureClienService(eventConnector, getProviderEnvironment().getSecureConnection(), getUseCompression());
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
				response.setZone(getFullZone(zone));
				response.setContext(getFullContext(context));
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
				HeaderProperties headerProps = getEventHeaders(event.getEventAction(),	event.getUpdateType(), event.getFingerprint(), zone, context, customHdrFields);
				
				Builder builder = setRequestHeaderAndMediaTypes(service, headerProps, false, false, true);
				logger.debug("Send Event with payload size: "+payloadStr.length());
				ClientResponse response = builder.post(ClientResponse.class, payloadStr);
				logger.debug("Receive Event Response Status: "+response.getStatus());
	
				return setEventResponse(service, response, headerProps, zone, context);
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

	private ProviderEnvironment getProviderEnvironment()
	{
	    return (ProviderEnvironment)getClientEnvMgr().getEnvironmentInfo();
	}
	
	/*
	 * This method sets all header properties for events as specified by the SIF3 Spec.
	 * 
	 * @param zone The zone for this event. Can be null.
	 * @param context The context for this event. Can be null.
	 * @return
	 */
	private HeaderProperties getEventHeaders(EventAction eventAction, UpdateType updateType, String fingerprint, SIFZone zone, SIFContext context, HeaderProperties overrideHdrFields)
	{
		// Set the override header fields
		HeaderProperties hdrProperties = (overrideHdrFields != null) ? new HeaderProperties(overrideHdrFields.getHeaderProperties()) : new HeaderProperties();

		// Add properties for the authentication header.
		hdrProperties.addHeaderProperties(createAuthenticationHdr(false, null));
		
		// Add event specific properties
		hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_MESSAGE_TYPE, HeaderValues.MessageType.EVENT.name());
		hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_EVENT_ACTION, eventAction.name());
		hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, HeaderValues.ServiceType.OBJECT.name());
		hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_NAME, serviceName);
						
		if (eventAction == EventAction.UPDATE)
		{
			if (updateType == null)
			{
				hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_UPDATE_TYPE, getProviderEnvironment().getDefaultUpdateType().name());
			}
			else
			{
				hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_UPDATE_TYPE, updateType.name());
			}
		}	
		
		if (StringUtils.notEmpty(fingerprint))
		{
		    hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_FINGERPRINT, fingerprint);
		}
		    
		if ((zone == null) || (zone.getIsDefault()) || StringUtils.isEmpty(zone.getId())) // Assume default zone!
		{
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_ZONE_ID, getSIF3Session().getDefaultZone().getId());			
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
	
	private BaseResponse setEventResponse(WebResource service, ClientResponse clientResponse, HeaderProperties requestHdrProps, SIFZone zone, SIFContext context)
	{
		return setResponse(service, clientResponse, null, requestHdrProps, getFullZone(zone), getFullContext(context), true, Status.ACCEPTED);
	}
	
	private SIFZone getFullZone(SIFZone zone)
	{
		if ((zone == null) || (zone.getIsDefault()) || StringUtils.isEmpty(zone.getId())) // Assume default zone!
		{
			return new SIFZone(getSIF3Session().getDefaultZone().getId(), true);			
		}
		else
		{
			return zone;
		}
	}
	
	private SIFContext getFullContext(SIFContext context)
	{
		if ((context == null) || (context.getIsDefault()) || StringUtils.isEmpty(context.getId())) // Assume default context!
		{
			return new SIFContext(CommonConstants.DEFAULT_CONTEXT_NAME, true);
		}
		else
		{
			return context;
		}
	}
}
