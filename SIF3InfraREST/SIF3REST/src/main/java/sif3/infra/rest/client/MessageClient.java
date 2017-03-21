/*
 * MessageClient.java
 * Created: 12/04/2014
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

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import sif3.common.CommonConstants;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.interfaces.ClientEnvironmentManager;
import au.com.systemic.framework.utils.StringUtils;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class implements the REST client for the queue connector but only for the "message" related operations. The implementation meets SIF3 Spec.
 * 
 * @author Joerg Huber
 *
 */
public class MessageClient extends BaseClient
{
	public MessageClient(ClientEnvironmentManager clientEnvMgr, URI queueURI)
	{
		super(clientEnvMgr, queueURI, clientEnvMgr.getEnvironmentInfo().getMediaType(), clientEnvMgr.getEnvironmentInfo().getMediaType(), new InfraMarshalFactory(), new InfraUnmarshalFactory(), clientEnvMgr.getEnvironmentInfo().getSecureConnection(), clientEnvMgr.getEnvironmentInfo().getCompressionEnabled());
	}

	/*
	 * Get message and remove old one.
	 */
	public Response getMessage(String removeMsgID, int consumerInstanceID) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildMessageURI(service, removeMsgID, true);
			HeaderProperties hdrProperties = getHeaderProperties(consumerInstanceID);
			logger.debug("HTTP GET Next Message...");
		    ClientResponse clientResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, false).get(ClientResponse.class);
			logger.debug("HTTP GET Next Message received.");
		    
		    //OK: Message Returned, NO_CONTENT: No message on queue: Both are valid returns
		    return setResponse(service, clientResponse, String.class, hdrProperties, null, null, false, Status.OK, Status.NO_CONTENT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'getMessage' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
			//ex.printStackTrace();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}
	
	/*
	 * Remove old message.
	 */
	public Response removeMessage(String removeMsgID, int consumerInstanceID) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildMessageURI(service, removeMsgID, false);
			HeaderProperties hdrProperties = getHeaderProperties(consumerInstanceID);
		    ClientResponse clientResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, false).delete(ClientResponse.class);

			return setResponse(service, clientResponse, null, hdrProperties, null, null, false, Status.NO_CONTENT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'removeMessage' service (REST DELETE) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private WebResource buildMessageURI(WebResource svc, String removeMsgID, boolean useMatrix)
	{
		UriBuilder uriBuilder = svc.getUriBuilder();
		if (StringUtils.notEmpty(removeMsgID))
		{
			if (useMatrix)
			{
				uriBuilder.matrixParam(CommonConstants.MATRIX_DELETE_MSGID, removeMsgID);
			}
			else
			{
				uriBuilder.path(removeMsgID);
			}
		}
		return svc.uri(uriBuilder.build());
	}

	/*
	 * This method sets all header properties for queue related request as specified by the SIF3 Spec.
	 * 
	 * @param sif3Session TSession information. Required for authentication token creation.
	 * @param consumerInstanceID The id of the consumer instance.
	 * @return
	 */
	private HeaderProperties getHeaderProperties(int consumerInstanceID)
	{
	    // Add Authentication info to existing header properties
		HeaderProperties hdrProperties = createAuthenticationHdr(false, null);
//		if (StringUtils.notEmpty(consumerInstanceID))
//		{
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_CONSUMER_ID, String.valueOf(consumerInstanceID));
//		}
		return hdrProperties;
	}
}
