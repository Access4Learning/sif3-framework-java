/*
 * EnvironmentResource.java
 * Created: 11/09/2013
 *
 * Copyright 2013 Systemic Pty Ltd
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

import java.util.ArrayList;

import javax.ws.rs.Consumes;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import sif3.common.exception.UnmarshalException;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.utils.AuthenticationUtils;
import sif3.common.ws.ErrorDetails;
import sif3.infra.common.env.EnvironmentStore;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.rest.env.ProviderEnvironmentManager;
import au.com.systemic.framework.utils.StringUtils;

/*
 * http://localhost:9080/SIF3InfraREST/sif3/environments/69df9d79-8e01-43e8-825f-d0dc1775761b
 */

/**
 * This class implements the Environment Resource with all required operations as defined by the SIF3 spec. This is an Infrastructure
 * resource and is not exposed to higher levels of this framework. It is expected that most of this functionality may reside in the
 * Environment Provider if a brokered environment is used but in DIRECT environments the provider must implement a basic Environment
 * Provider, hence the need for this class.
 * 
 * @author Joerg Huber
 */
@Path("/environments")
public class EnvironmentResource extends InfraResource
{
	private ProviderEnvironmentManager envMgr = null;
	
	private static final String SERVICE_NAME = "environments";

	public EnvironmentResource(@Context UriInfo uriInfo,
			                   @Context HttpHeaders requestHeaders,
			                   @Context Request request)
	{
		super(uriInfo, requestHeaders, request, SERVICE_NAME);
	    envMgr = ProviderEnvironmentManager.getInstance(EnvironmentStore.getInstance(getServicePropFileName()));
	}

    // -------------------------------------------------//
	// -- POST Section: This is the C(reate) in CRUD. --//
	// -------------------------------------------------//
	@POST
	@Path("environment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createEnvironment(String payload)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Create Environment (REST POST) with input data: " + payload);
		}
		
		try
		{
			EnvironmentType inputEnv = (EnvironmentType) getUnmarshaller().unmarschal(payload, EnvironmentType.class, getMediaType());

			ArrayList<String> envError = envDataValid(inputEnv); 
			if (envError != null)
			{
				return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Cannot create Consumer Environment.", "Missing or invalid data: "+envError), ResponseAction.CREATE);
			}

			String envName = inputEnv.getSolutionId();			
			String consumerID = inputEnv.getConsumerName();

			String userName = null;
			String password = null;
			
			ProviderEnvironment providerEnv = (ProviderEnvironment)envMgr.getEnvironmentStore().getEnvironments().getEnvironment(envName);
			if (providerEnv == null)
			{
				return makeErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Environment with solutionId = '"+envName+"' not supported. Ensure that you provide a valid solutionId."), ResponseAction.CREATE);
			}
			
			ConsumerEnvironment consumerEnv = providerEnv.getConsumer(consumerID);
			if (consumerEnv == null)
			{
				// is 'any allowed'
				if (providerEnv.getAllowAny())
				{
					userName = providerEnv.getAllowAnyUserName();
					password = providerEnv.getAllowAnyPassword();

				}
				else // Report an error because it is an invalid consumer for this environment
				{
					return makeErrorResponse(new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Consumer '"+consumerID+"' is not authorised to access this environment."), ResponseAction.CREATE);
				}
			}
			else // valid consumer found
			{
				userName = consumerEnv.getUserName();
				password = consumerEnv.getPassword();
			}

			//check Auth Token if it is a valid client. At this stage we only have the initial auth token.
			if (!AuthenticationUtils.getBasicAuthToken(userName, password).equals(getAuthToken())) // not allowed credentials
			{
				return makeErrorResponse(new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Invalid authorisation token provided."), ResponseAction.CREATE);
			}
			
			// If we get here then all the credentials are fine.
			// Check if the environment is valid for this provider and consumer
			if (!envMgr.isEnvironmentSupportedForConsumer(envName, consumerID, false))
			{
				return makeErrorResponse(new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Consumer '"+consumerID+"' is not authorised to access this environment with solutionId = '"+envName+"'."), ResponseAction.CREATE);
			}
			
			// Check if an environment does already exist. if so we need to see if we return an error or if we shall just return the environment
			EnvironmentType environment = envMgr.getEnvironmentForConsumer(envName, consumerID);
			if (environment != null)
			{		  
				//TODO: JH - Confirm if we shall return it
				return createEnvResponse(environment, Status.CONFLICT.getStatusCode(), ResponseAction.CREATE);
			}
			
			// if it doesn't exist then create it the environment store and also add it to the EnvironmentManager.
			environment = envMgr.createEnvironment(inputEnv, userName, password, providerEnv.getMediaType());
			
			if (environment == null) // We had a problem. Error logged
			{
				return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create environment with solutionId = '"+envName+"' for consumer '"+consumerID+"'.", "Internal System error. Please contact your system administrator."), ResponseAction.CREATE);
			}
			
			//if we get here then all is fine and we can return the environment.
			return createEnvResponse(environment, Status.CREATED.getStatusCode(), ResponseAction.CREATE);
		}
		catch (UnmarshalException ex)
		{
			logger.error("Failed to unmarshal payload into an environment: "+ ex.getMessage(), ex);
			logger.error("Environment Payload: "+ payload);
			return makeErrorResponse( new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal environment payload: "+ ex.getMessage()), ResponseAction.CREATE);
		}
	}

	// -----------------------------------------------------------------//
	// -- GET Section: This is the R(ead) in CRUD. Get By Resource ID --//
	// -----------------------------------------------------------------//
	@GET
	@Path("{id: [a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[14][a-fA-F0-9]{3}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getEnvironment(@PathParam("id") String id)
	{
		logger.debug("Retrieve Environment with id = " + id);
		
		//check if the caller is valid, i.e. has provided a authentication token.
		ErrorDetails error = validClient();
		if (error == null)
		{
			// If I get here then the client has an environment. Load and return it.
			EnvironmentType environment = envMgr.loadEnvironmentByAuthToken(getAuthToken());
			
			// Also ensure that the id matches the ID of the environment otherwise we have a bit of a mixup
			if (!id.equals(environment.getId()))
			{
				error = new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Environment with id "+id+" does not exist.");
				return makeErrorResponse(error, ResponseAction.QUERY);
			}
			
			return createEnvResponse(environment, Status.OK.getStatusCode(), ResponseAction.QUERY);
		}
		else
		{
			logger.debug("Error Found: "+error);
			return makeErrorResponse(error, ResponseAction.QUERY);
		}
	}

	// ---------------------------------------------------//
	// -- DELETE Section: This is the D(elete) in CRUD. --//
	// ---------------------------------------------------//
	@DELETE
	@Path("{id: [a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[14][a-fA-F0-9]{3}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteEnvironment(@PathParam("id") String id)
	{
		logger.debug("Delete Environment with id = " + id);
		
		//check if the caller is valid, i.e. has provided a authentication token.
		ErrorDetails error = validClient();
		if (error == null)
		{
			// If I get here then the client has an environment. Load and return it.
			EnvironmentType environment = envMgr.loadEnvironmentByAuthToken(getAuthToken());
			
			// Also ensure that the id matches the ID of the environment otherwise we have a bit of a mixup
			if (!id.equals(environment.getId()))
			{
				error = new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Environment with id "+id+" does not exist. No action taken.");
				return makeErrorResponse(error, ResponseAction.DELETE);
			}
			
			if (envMgr.removeEnvironmentByAuthToken(getAuthToken()))
			{
				return makeResopnseWithNoContent(false, ResponseAction.DELETE);
			}
			else
			{
				logger.error("Environment with id "+id+" couldn't be deleted. See error log for details/");
				error = new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Environment with id "+id+" couldn't be deleted.");
				return makeErrorResponse(error, ResponseAction.DELETE);
			}
		}
		else
		{
			logger.debug("Error Found: "+error);
			return makeErrorResponse(error, ResponseAction.DELETE);
		}
	}

	/*---------------------*/
	/*-- Private Methods --*/
  /*---------------------*/
  private ArrayList<String> envDataValid(EnvironmentType environment)
  {
	  ArrayList<String> errors = new ArrayList<String>();
	  if (environment == null)
	  {
		  errors.add("No environment data provided.");
	  }
	  else
	  {
		  checkValue(environment.getType(),"type", errors);
		  checkValue(environment.getSolutionId(),"solutionId", errors);
		  checkValue(environment.getAuthenticationMethod(),"authenticationMethod", errors);
		  checkValue(environment.getConsumerName(),"consumerName", errors);
		  
		  if (environment.getApplicationInfo() != null)
		  {
			  checkValue(environment.getApplicationInfo().getApplicationKey(),"applicationInfo/applicationKey", errors);
			  checkValue(environment.getApplicationInfo().getSupportedInfrastructureVersion(),"applicationInfo/supportedInfrastructureVersion", errors);
			  checkValue(environment.getApplicationInfo().getSupportedDataModel(),"applicationInfo/supportedDataModel", errors);
			  checkValue(environment.getApplicationInfo().getSupportedDataModelVersion(),"applicationInfo/supportedDataModelVersion", errors);
			  checkValue(environment.getApplicationInfo().getTransport(),"applicationInfo/transport", errors);			  
		  }
		  else
		  {
			  errors.add("applicationInfo");
		  }
	  }
	  if (errors.size() == 0)
	  {
		  return null;
	  }
	  else
	  {
		  return errors;
	  }
  }

	private Response createEnvResponse(EnvironmentType environment, int statusCode, ResponseAction responseAction)
	{
		return makeResponse(environment, statusCode, false, responseAction, getMarshaller());
	}
	
	private void checkValue(String value, String elementName, ArrayList<String> errors)
	{
		if (StringUtils.isEmpty(value))
		{
			errors.add(elementName);
		}
	}
}
