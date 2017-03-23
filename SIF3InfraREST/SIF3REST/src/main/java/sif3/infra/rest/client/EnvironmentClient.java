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
import sif3.common.model.security.TokenInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.interfaces.ClientEnvironmentManager;
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
	
	/**
	 * Creates a client object to deal with environment functions. The environmentURI can have different forms depending on the
	 * function required. When an environment is created the environmentURI should be the base URI given to the consumer/provider
	 * to call the environment provider. In case where an environment shall be retrieved or deleted then the environmentURI would be
	 * whatever has been returned by the 'createEnvironment()' method in the 'environment' infrastructure connector. This should be
	 * something of the form environments/<environemnt-id>
	 * 
     * @param clientEnvMgr Session manager to access the clients session information.
	 * @param environmentURI The URI for which one of the methods in this class shall be applied.
	 * @param envInfo Environment Info
	 */
	public EnvironmentClient(ClientEnvironmentManager clientEnvMgr, URI environmentURI, EnvironmentInfo envInfo)
	{
		super(clientEnvMgr, environmentURI, envInfo.getMediaType(), envInfo.getMediaType(), new InfraMarshalFactory(), new InfraUnmarshalFactory(), envInfo.getSecureConnection(), envInfo.getCompressionEnabled());
		this.envInfo = envInfo;
	}
	
	/**
	 * Calls the HTTP(S) POST operation on the environmentURI given to this constructor with the environment values given in the
	 * template parameter.
	 * 
	 * @param template A 'Input Environment' based on which the create will be requested from the environment provider.
	 * @param tokenInfo In a create where external security services are used we must get the token info here to pass
	 *                  into appropriate headers.
	 * 
	 * @return A response from the environment provider with an complete environment.
	 * 
	 * @throws ServiceInvokationException
	 */
	public Response createEnvironment(EnvironmentType template, TokenInfo tokenInfo) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, null);
			String payloadStr = getDataModelMarshaller().marshal(template, getRequestMediaType());

			if (logger.isDebugEnabled())
			{
				logger.debug("createEnvironment: Payload to send:\n"+payloadStr);
			}
			
			SIF3Session pseudoSIF3Session = null;
			if (tokenInfo != null) // copy security info to tempSession
			{
			    pseudoSIF3Session = new SIF3Session();
			    pseudoSIF3Session.setSecurityToken(tokenInfo.getToken());
			    pseudoSIF3Session.setSecurityTokenExpiry(tokenInfo.getTokenExpiryDate());
			}
			HeaderProperties requestHdrProps = createAuthenticationHdr(true, pseudoSIF3Session);
			ClientResponse response = setRequestHeaderAndMediaTypes(service, requestHdrProps, true, true, true).post(ClientResponse.class, payloadStr);

			if (envInfo.getEnvCreateConflictIsError())
			{
				return setResponse(service, response, EnvironmentType.class, requestHdrProps, null, null, false, Status.CREATED);
			}
			else // Allow the 'Conflict' HTTP Status to be treated as a valid behaviour.
			{
				return setResponse(service, response, EnvironmentType.class, requestHdrProps, null, null, false, Status.CREATED, Status.CONFLICT);
			}
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
	 * This method will use the pseudoSIF3Session if it is not null. A pseudoSession should only be given for the
	 * situation where a consumer must connect to an already existing environment but the consumer does not have it yet
	 * in the workstore.
	 * 
     * @param pseudoSIF3Session A pseudo session. All it holds is information about authentication and a session token for a
     *                          existing environment to be retrieved.
	 * @return See desc.
	 * 
	 * @throws ServiceInvokationException
	 */
	public Response getEnvironment(SIF3Session pseudoSIF3Session) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, null);

			// Since we have a pseudo session we must check if there is a need to pass along a TokenInfo. Only needed if
			// Authentication method is 'Bearer' which also means that the security token should be set in the pseudo session!
			HeaderProperties requestHdrProps = createAuthenticationHdr(false, pseudoSIF3Session);
			ClientResponse response = setRequestHeaderAndMediaTypes(service, requestHdrProps, true, true, false).get(ClientResponse.class);                

			return setResponse(service, response, EnvironmentType.class, requestHdrProps, null, null, false, Status.OK, Status.NOT_MODIFIED);
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
			HeaderProperties requestHdrProps = createAuthenticationHdr(false, null);
		    ClientResponse remoteResponse = setRequestHeaderAndMediaTypes(service, requestHdrProps, true, true, false).delete(ClientResponse.class);

		    Response response = setResponse(service, remoteResponse, null, requestHdrProps, null, null, false, Status.NO_CONTENT);    
		    if (response.hasError())
		    {
		      logger.error("An error has been returned in removing the environment with ID = " + getSIF3Session().getEnvironmentID() + " form location: " + response.getResourceURI());
		      logger.error("Returned Error Data:\n" + response.getError());
		      return false;
		    }
		    else
		    {
		      logger.info("Environment with ID = " + getSIF3Session().getEnvironmentID() + " has been removed from remote location.");
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
}
