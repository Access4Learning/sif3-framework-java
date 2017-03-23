/*
 * QueueClient.java
 * Created: 03/04/2014
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

import sif3.common.CommonConstants.QueuePollingType;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderProperties;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.types.ConsumerEnvironment.ConnectorName;
import sif3.infra.common.interfaces.ClientEnvironmentManager;
import sif3.infra.common.model.QueueCollectionType;
import sif3.infra.common.model.QueueType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class implements the REST client for the queue connector excluding the "message" related operations. The implementation meets SIF3 Spec.
 *
 * @author Joerg Huber
 *
 */
public class QueueClient extends BaseClient
{
	public QueueClient(ClientEnvironmentManager clientEnvMgr)
	{
		super(clientEnvMgr, clientEnvMgr.getEnvironmentInfo().getConnectorBaseURI(ConnectorName.queues), clientEnvMgr.getEnvironmentInfo().getMediaType(), clientEnvMgr.getEnvironmentInfo().getMediaType(), new InfraMarshalFactory(), new InfraUnmarshalFactory(), clientEnvMgr.getEnvironmentInfo().getSecureConnection(), clientEnvMgr.getEnvironmentInfo().getCompressionEnabled());
	}
	
	public Response getQueues() throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, null);
			HeaderProperties hdrProperties = getHeaderProperties();		
			ClientResponse clientResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, false).get(ClientResponse.class);

			return setResponse(service, clientResponse, QueueCollectionType.class, hdrProperties, null, null, false, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'getQueues' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}
	
	public Response getQueue(String queueID) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, null, queueID, null, null, null);
			HeaderProperties hdrProperties = getHeaderProperties();		
			ClientResponse clientResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, false).get(ClientResponse.class);

			return setResponse(service, clientResponse, QueueType.class, hdrProperties, null, null, false, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'getQueue' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}
	
	/**
	 * This method will create a queue of the type IMMEDIATE. This means that when the getMessage() method is called it will return 
	 * immediately with a response as defined by the SIF3 Spec.
	 * 
	 * @param queueName Queue Name. This is NOT the ID of the queue rather a suggested 'Pretty Name' for display, logging and reporting
	 *                              purposes. The actual queue ID is returned as part of the response to this method and should be maintained
	 *                              by the consumer for future references and calls to any other methods in this class that require a queueID.
	 * @param numConcurrentConnections Number of suggested concurrent connections that queue may expect when getMessage() is called.
	 *                                 Default will be 1. The number provided here is only a suggestion to the queue provider and might
	 *                                 be reduced by the queue provider, possibly down to a value of 1 if the queue provider doesn't support 
	 *                                 concurrent connections. The consumer should check the response of this method for the actual value 
	 *                                 of this property before it attempts to build concurrent connections.  
	 * 
	 * @return A Response that will hold a QueueType object or an Error. If no error is reported then the returned QueueType will hold
	 *         all remaining elements that make a queue according to the SIF3 Specification.
	 */
	public Response createImmediateQueue(String queueName, int numConcurrentConnections) throws ServiceInvokationException
	{
		return createQueue(queueName, QueuePollingType.IMMEDIATE, numConcurrentConnections, 0);
	}
	
	/**
	 * This method will create a queue of the type LONG. This means that when the getMessage() method is called and there are no messages then
	 * the connection will be kept open for a number of seconds (LONG) until it will be closed. If a message should arrive on the queue during
	 * this open connection time it will be returned to the caller immediately and the connection will be closed. For further details, please
	 * refer to the SIF3 Specification.
	 * 
	 * @param queueName Queue Name. This is NOT the ID of the queue rather a suggested 'Pretty Name' for display, logging and reporting
	 *                              purposes. The actual queue ID is returned as part of the response to this method and should be maintained
	 *                              by the consumer for future references and calls to any other methods in this class that require a queueID.
	 * @param numConcurrentConnections Number of suggested concurrent connections that queue may expect when getMessage() is called.
	 *                                 Default will be 1. The number provided here is only a suggestion to the queue provider and might
	 *                                 be reduced by the queue provider, possibly down to a value of 1 if the queue provider doesn't support 
	 *                                 concurrent connections. The consumer should check the response of this method for the actual value 
	 *                                 of this property before it attempts to build concurrent connections.
	 * @param longPollInSec The number of seconds the connection shall be kept open if no message is available.
	 * 
	 * @return A Response that will hold a QueueType object or an Error. If no error is reported then the returned QueueType will hold
	 *         all remaining elements that make a queue according to the SIF3 Specification.
	 */
	public Response createLongPollingQueue(String queueName, int numConcurrentConnections, int longPollInSec) throws ServiceInvokationException
	{
		return createQueue(queueName, QueuePollingType.LONG, numConcurrentConnections, longPollInSec);
	}

	/**
	 * This method will attempt to remove the queue given by the queueID from the environment of this consumer.
	 * 
	 * @param queueID ID of the queue to remove. The ID is the value given by the response from any of the create<ABC>Queue() methods.
	 * 
	 * @return Response Object holding appropriate values and results of the call. See SIF3 Spec for details of the response to a delete 
	 *                  operation.
	 * 
	 * @throws ServiceInvokationException Service could not be invoked or executed due to environmental issues (server not found etc). In this
	 *                                    case one has to assume the queue is NOT deleted.
	 */
	public Response removeQueue(String queueID) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, null, queueID, null, null, null);
			HeaderProperties hdrProperties = getHeaderProperties();		
		    ClientResponse clientResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, false).delete(ClientResponse.class);

			return setResponse(service, clientResponse, null, hdrProperties, null, null, false, Status.NO_CONTENT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'removeQueue' service (REST DELETE) on URI " + service.getURI() + ": " + ex.getMessage();
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
	 * @param sif3Session TSession information. Required for authentication token creation.
	 * @return
	 */
	private HeaderProperties getHeaderProperties()
	{
        // Add Authentication info to existing header properties
        return createAuthenticationHdr(false, null);
	}

	/*
	 * longPollInSec Parameter is only used if pollingType=LONG
	 */
	private Response createQueue(String queueName, QueuePollingType pollingType, int numConcurrentConnections, long longPollInSec) throws ServiceInvokationException
	{
		QueueType inputQueue = getInfraObjectFactory().createQueueType();
		inputQueue.setName(queueName);
		inputQueue.setPolling(pollingType.name());
		if (pollingType == QueuePollingType.LONG)
		{
			inputQueue.setIdleTimeout(longPollInSec);
		}
		inputQueue.setMaxConcurrentConnections((numConcurrentConnections <= 0) ? 1L : numConcurrentConnections);
		
		// Now attempt to create queue
		WebResource service = getService();
		try
		{
			service = buildURI(service, null);
			HeaderProperties hdrProperties = getHeaderProperties();		
			String payloadStr = getInfraMarshaller().marshal(inputQueue, getRequestMediaType());
			if (logger.isDebugEnabled())
			{
				logger.debug("createQueue: Payload to send:\n"+payloadStr);
			}
			ClientResponse clientResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, true).post(ClientResponse.class, payloadStr);
			return setResponse(service, clientResponse, QueueType.class, hdrProperties, null, null, false, Status.CREATED, Status.CONFLICT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'createQueue' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}
}
