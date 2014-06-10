/*
 * EnvironmentClient.java
 * Created: 24/04/2014
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

import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;
import sif3.common.persist.model.SIF3Session;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.model.EnvironmentType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class implements the REST client for the environment connector as defined in the SIF3 Spec.
 * 
 * @author Joerg Huber
 *
 */
public class EnvironmentClient extends BaseClient
{
	private EnvironmentInfo envInfo = null;
	private SIF3Session sif3Session = null;
	
	/**
	 * Creates a client object to deal with environment functions. The environmentURI can have different forms depending on the
	 * function required. When an environment is created the environmentURI should be the base URI given to the consumer/provider
	 * to call the environment provider. In case where an environment shall be retrieved or deleted then the environmentURI would be
	 * whatever has been returned by the 'createEnvironment()' method in the 'environment' infrastructure connector. This should be
	 * something of the form environments/<environemnt-id>
	 * 
	 * @param environmentURI The URI for which one of the methods in this class shall be applied.
	 * @param envInfo Environment Info
	 * @param sif3Session The session for which the methods shall be called. Can be null in case where the environment
	 *                    is not yet created. In this case only the methods 'createEnvironment()' will work.
	 */
	public EnvironmentClient(URI environmentURI, EnvironmentInfo envInfo, SIF3Session sif3Session)
	{
		super(environmentURI, envInfo.getMediaType(), new InfraMarshalFactory(), new InfraUnmarshalFactory(), envInfo.getSecureConnection());
		this.envInfo = envInfo;
		this.sif3Session = sif3Session;
	}
	
	/**
	 * Calls the HTTP(S) POST operation on the environmentURI given to this constructor with the environment values given in the
	 * template parameter.
	 * 
	 * @param template A 'Input Environment' based on which the create will be requested from the environment provider.
	 * 
	 * @return A response from the environment provider with an complete environment.
	 * 
	 * @throws ServiceInvokationException
	 */
	public Response createEnvironment(EnvironmentType template) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, null);
			String payloadStr = getDataModelMarshaller().marschal(template, getMediaType());

			if (logger.isDebugEnabled())
			{
				logger.debug("createEnvironment: Payload to send:\n"+payloadStr);
			}
			ClientResponse response = setRequestHeaderAndMediaTypes(service, getAuthenticationHdr(envInfo.getAuthMethod(), envInfo.getEnvironmentKey().getApplicationKey(), envInfo.getPassword()), true).post(ClientResponse.class, payloadStr);

			return setResponse(service, response, EnvironmentType.class, Status.CREATED, Status.CONFLICT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'createEnvironment' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}
	
	/**
	 * Returns the response which should hold an environment based on the URI given to the constructor of this class. 
	 * 
	 * @return See desc.
	 * 
	 * @throws ServiceInvokationException
	 */
	public Response getEnvironment() throws ServiceInvokationException
	{

		WebResource service = getService();
		try
		{
			service = buildURI(service, null);
			ClientResponse response = setRequestHeaderAndMediaTypes(service, getAuthenticationHdr(envInfo.getAuthMethod(), sif3Session.getSessionToken(), sif3Session.getPassword()), true).get(ClientResponse.class);

			return setResponse(service, response, EnvironmentType.class, Status.OK, Status.NOT_MODIFIED);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'getEnvironment' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	/**
	 * This method will remove an environment given by the URI in the constructor. If the environment is removed it will return
	 * true. In all other cases false will be returned and one has to assume the environment may still exist on the 
	 * environment provider.
	 * 
	 * @return See desc.
	 */
	public boolean removeEnvironment() 
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, null);
		    ClientResponse remoteResponse = setRequestHeaderAndMediaTypes(service, getAuthenticationHdr(envInfo.getAuthMethod(), sif3Session.getSessionToken(), sif3Session.getPassword()), true).delete(ClientResponse.class);

		    Response response = setResponse(service, remoteResponse, null, Status.NO_CONTENT);    
		    if (response.hasError())
		    {
		      logger.error("An error has been returned in removing the environment with ID = " + sif3Session.getEnvironmentID() + " form location: " + response.getResourceURI());
		      logger.error("Returned Error Data:\n" + response.getError());
		      return false;
		    }
		    else
		    {
		      logger.info("Environment with ID = " + sif3Session.getEnvironmentID() + " has been removed from remote location.");
		      return true;
		    }
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'removeEnvironment' service (REST DELETE) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			return false;
		}
	}
	
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private HeaderProperties getAuthenticationHdr(AuthenticationMethod authenticationMethod, String username, String password)
	{
		HeaderProperties hdrProps = new HeaderProperties();

		// First create the properties for the authentication header.
		ClientUtils.setAuthenticationHeader(hdrProps, authenticationMethod, username, password);

		// Set the remaining header fields for this type of request
		hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_REQUEST_TYPE, RequestType.IMMEDIATE.name());

		return hdrProps;
	}

}
