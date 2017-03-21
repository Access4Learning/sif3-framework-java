/*
 * SubscriptionClient.java
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

import javax.ws.rs.core.Response.Status;

import sif3.common.CommonConstants;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderProperties;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.types.ConsumerEnvironment.ConnectorName;
import sif3.infra.common.interfaces.ClientEnvironmentManager;
import sif3.infra.common.model.SubscriptionCollectionType;
import sif3.infra.common.model.SubscriptionType;
import au.com.systemic.framework.utils.StringUtils;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class implements the REST client for the subscription connector as defined in the SIF3 Spec.
 * 
 * @author Joerg Huber
 *
 */
public class SubscriptionClient extends BaseClient
{
	public SubscriptionClient(ClientEnvironmentManager clientEnvMgr)
	{
		super(clientEnvMgr, clientEnvMgr.getEnvironmentInfo().getConnectorBaseURI(ConnectorName.subscriptions), clientEnvMgr.getEnvironmentInfo().getMediaType(), clientEnvMgr.getEnvironmentInfo().getMediaType(), new InfraMarshalFactory(), new InfraUnmarshalFactory(), clientEnvMgr.getEnvironmentInfo().getSecureConnection(), clientEnvMgr.getEnvironmentInfo().getCompressionEnabled());
	}
	
	public Response getSubscriptions() throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, null);
			HeaderProperties hdrProperties = getHeaderProperties();		
			ClientResponse clientResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, false).get(ClientResponse.class);

			return setResponse(service, clientResponse, SubscriptionCollectionType.class, hdrProperties, null, null, false, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'getSubscriptions' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}
	
	public Response getSubscription(String subscriptionID) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, null, subscriptionID, null, null, null);
			HeaderProperties hdrProperties = getHeaderProperties();		
			ClientResponse clientResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, false).get(ClientResponse.class);

			return setResponse(service, clientResponse, SubscriptionType.class, hdrProperties, null, null, false, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'getSubscription' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}
	
	/**
	 * @param subscriptionInfo All properties except the id can be set. Optional properties are ZoneId and ContexID. If 
	 *                         they are not set (empty or null) then the Default Zone/Context is assumed. If any other
	 *                         element is missing then the provider/broker will report an error back which will be mapped
	 *                         to a ServiceInvokationException. In this case it must be assumed that the subscription is
	 *                         not created.
	 */
	public Response subscribe(SubscriptionType subscriptionInfo) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			// Check if mandatory parameters are set.
			if (subscriptionInfo == null)
			{
				throw new IllegalArgumentException("Parameter subscriptionInfo for method subscribe is null.");
			}			
			if (StringUtils.isEmpty(subscriptionInfo.getQueueId()))
			{
				throw new IllegalArgumentException("Property queueID in subscriptioninfo for method subscribe is empty or null.");
			}			
			if (StringUtils.isEmpty(subscriptionInfo.getServiceName()))
			{
				throw new IllegalArgumentException("Property serviceName in subscriptioninfo for method subscribe is empty or null.");
			}			
			if (StringUtils.isEmpty(subscriptionInfo.getServiceType()))
			{
				throw new IllegalArgumentException("Property serviceType in subscriptioninfo for method subscribe is empty or null. Must be of value 'OBJECT', 'FUNCTION' or 'UTILITY'");
			}
			
			// OK all good now...
			if (StringUtils.isEmpty(subscriptionInfo.getZoneId()))
			{
				// Set default zone
				subscriptionInfo.setZoneId(getSIF3Session().getDefaultZone().getId());
			}
			if (StringUtils.isEmpty(subscriptionInfo.getContextId()))
			{
				// Set default context
				subscriptionInfo.setContextId(CommonConstants.DEFAULT_CONTEXT_NAME);
			}
		
			service = buildURI(service, null);
			HeaderProperties hdrProperties = getHeaderProperties();		
			String payloadStr = getInfraMarshaller().marshal(subscriptionInfo, getRequestMediaType());
			if (logger.isDebugEnabled())
			{
				logger.debug("subscribe: Payload to send:\n"+payloadStr);
			}
			ClientResponse clientResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, true).post(ClientResponse.class, payloadStr);
			return setResponse(service, clientResponse, SubscriptionType.class, hdrProperties, null, null, false, Status.CREATED, Status.CONFLICT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'subscribe' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}
	
	/**
	 * This method will attempt to remove the subscription given by the subscriptionID from the environment of this consumer.
	 * 
	 * @param subscriptionID ID of the subscription to remove. The ID is the value given by the response from any of the subscribe() methods.
	 * 
	 * @return Response Object holding appropriate values and results of the call. See SIF3 Spec for details of the response to a delete 
	 *                  operation.
	 * 
	 * @throws ServiceInvokationException Service could not be invoked or executed due to environmental issues (server not found etc). In this
	 *                                    case one has to assume the subscription is NOT deleted.
	 */
	public Response unsubscribe(String subscriptionID) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, null, subscriptionID, null, null, null);
			HeaderProperties hdrProperties = getHeaderProperties();		
		    ClientResponse clientResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, false).delete(ClientResponse.class);

			return setResponse(service, clientResponse, null, hdrProperties, null, null, false, Status.NO_CONTENT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'unsubscribe' service (REST DELETE) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/

	/*
	 * This method sets all header properties for queue related request as specified by the SIF3 Spec.
	 * 
	 * @return
	 */
	private HeaderProperties getHeaderProperties()
	{
        // Add Authentication info to existing header properties
        return createAuthenticationHdr(false, null);
	}

}
