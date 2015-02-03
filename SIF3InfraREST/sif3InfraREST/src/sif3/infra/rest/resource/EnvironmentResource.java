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
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.model.AuthenticationInfo;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.security.TokenInfo;
import sif3.common.persist.model.AppEnvironmentTemplate;
import sif3.common.persist.model.SIF3Session;
import sif3.common.ws.ErrorDetails;
import sif3.infra.common.env.mgr.DirectProviderEnvironmentManager;
import sif3.infra.common.env.mgr.ProviderManagerFactory;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.common.model.EnvironmentType;
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
@Path("/environments{mimeType:(\\.[^/]*?)?}")
public class EnvironmentResource extends InfraResource
{  
	// Needed in some cases where an environment is created.
	private String sessionToken = null;

	public EnvironmentResource(@Context UriInfo uriInfo,
			                   @Context HttpHeaders requestHeaders,
			                   @Context Request request,
				               @PathParam("mimeType") String mimeType)
	{
		super(uriInfo, requestHeaders, request, "", null, null);
		logger.debug("URL Postfix mimeType: '"+mimeType+"'");
	}
	
	/*
	 * Mainly needed at creation of an environment
	 */
	@Override
	public String getTokenFromAuthToken()
	{
		return (sessionToken == null) ? super.getTokenFromAuthToken() : sessionToken;
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
    	return ProviderManagerFactory.getEnvironmentManager();
    }
		
    // -------------------------------------------------//
	// -- POST Section: This is the C(reate) in CRUD. --//
	// -------------------------------------------------//
	@POST
	@Path("environment{mimeType:(\\.[^/]*?)?}")
//  Let everything through and then deal with it when needed. 
//  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createEnvironment(String payload, @PathParam("mimeType") String mimeType)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Create Environment (REST POST) with URL Postfix mimeType = '" + mimeType + "' and input data: " + payload);
		}
		
		try
		{
			EnvironmentType inputEnv = null;
			AuthenticationInfo authInfo = getAuthInfo();
		  
			// The method below will set the bearer token info if the authentication method is 'Bearer'
			// Any failures with the method below will throw a VerifyError exception
			TokenInfo tokenInfo = getBearerTokenInfo(authInfo);
		  
			if (StringUtils.notEmpty(payload))
			{
				inputEnv = (EnvironmentType) getInfraUnmarshaller().unmarshal(payload, EnvironmentType.class, getRequestMediaType());
			}
			else // check if we have the environment information as URL query parameters
			{
				if (authInfo.getAuthMethod() != AuthenticationInfo.AuthenticationMethod.Bearer)
				{
					// authInfo should have an application key here at this stage if Basic or SIF_HMACSHA256
					inputEnv = makeEnvironmentFromQueryParams(getQueryParameters(), authInfo.getUserToken(), true);
				}
				else // Bearer Token => We must get info about environment from TokenInfo or Query Parameters
				{
					inputEnv = makeEnvironmentForBearerToken(getQueryParameters(), tokenInfo);
				}
			}

			ArrayList<String> envError = envDataValid(inputEnv); 
			if (envError != null)
			{
				return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Cannot create Consumer Environment.", "Missing or invalid data: "+envError), ResponseAction.CREATE);
			}

			ProviderEnvironment envInfo = getEnvironment();
			EnvironmentKey envKey = new EnvironmentKey(inputEnv.getSolutionId(), inputEnv.getApplicationInfo().getApplicationKey(), inputEnv.getUserToken(), inputEnv.getInstanceId());
			
			// Get the environment template for this environment key. This way we get the password to use.
			AppEnvironmentTemplate appEnvTemplate = getDirectEnvironmentManager().getTemplateInfo(envKey);
			if (appEnvTemplate == null) // not good. No template known for this environment key
			{
				ErrorDetails error = new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "No environment template known for the given environment: "+envKey);
				return makeErrorResponse(error, ResponseAction.CREATE);       				
			}
			
			// check if initial Authentication Token is valid for this environment template. 
			// No validation of token required if it is bearer token because it was validated in getBearerTokenInfo()
			// at the top of this method.
			if (authInfo.getAuthMethod() != AuthenticationMethod.Bearer)
			{
				ErrorDetails errors = validateNoneBearerAuthToken(appEnvTemplate.getApplicationKey(), appEnvTemplate.getPassword());
				if (errors != null) // we had an issue with the authentication => return error.
				{
					return makeErrorResponse(errors, ResponseAction.CREATE);       
				}
			}
			
			// Here we are ok with authentication. Check if there is an environment for this application already.
			EnvironmentType environment = getDirectEnvironmentManager().getEnvironmentFromWorkstore(envKey, tokenInfo, isSecure());
			if (environment != null) // we have an environment already
			{
	  			// Ensure local session token is set for later authentication
				this.sessionToken = environment.getSessionToken();
				
				// Note: This is an invalid state as far as the SIF Specification is concerned. We return HTTP Status 409
				//       and for the time being return the environment that already exists. This might be wrong but it is
				//       not clearly stated in the SIF Specification what should be returned with a 409 error. As far
				//       as the framework is concerned we won't load a session into the session store.
				
				// Check if the response shall contain an error message rather than the environment XML.
				if (envInfo.getEnvCreateConflictIsError())
				{
					return makeErrorResponse(new ErrorDetails(Status.CONFLICT.getStatusCode(), Status.CONFLICT.getReasonPhrase(), "An environment '"+envInfo.getEnvironmentName()+"' for consumer '"+inputEnv.getConsumerName()+"' already exists. Cannot create it again."), ResponseAction.CREATE);
				}
				else
				{
			        return createEnvResponse(environment, Status.CONFLICT.getStatusCode(), ResponseAction.CREATE);					
				}
			}
			
			// If we get to this point then we don't have an environment/session yet. Create it the environment store and also add it to 
			// the ClientEnvironmentManager.
		    environment = getDirectEnvironmentManager().createOrUpdateEnvironment(inputEnv, tokenInfo, isSecure());
		      
		    if (environment == null) // We had a problem. Error logged
		    {
		        return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create environment for '"+envInfo.getEnvironmentName()+"' for consumer '"+inputEnv.getConsumerName()+"'.", "Internal System error. Please contact your system administrator."), ResponseAction.CREATE);
		    }
		    else // Ensure local session token is set for later authentication
		    {
				this.sessionToken = environment.getSessionToken();
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
	    catch (UnsupportedMediaTypeExcpetion ex)
	    {
	    	logger.error("Failed to unmarshal payload into an environment: "+ ex.getMessage(), ex);
	    	logger.error("Environment Payload: "+ payload);
	    	return makeErrorResponse( new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Failed to unmarshal environment payload: "+ ex.getMessage()), ResponseAction.CREATE);
	    }
	    catch (VerifyError ex)
	    {
	    	logger.error("Security Token issue. See previous error log entries.", ex);
	    	logger.error("Environment Payload: "+ payload);
	    	return makeErrorResponse( new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Not authorized.", ex.getMessage()), ResponseAction.CREATE);
	    }	    
	    catch (Exception ex)
	    {
	    	logger.error("Failed to create/retrieve environment session: "+ ex.getMessage(), ex);
	    	logger.error("Environment Payload: "+ payload);
	    	return makeErrorResponse( new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal environment payload: "+ ex.getMessage()), ResponseAction.CREATE);
	    }
	}

	// -----------------------------------------------------------------//
	// -- GET Section: This is the R(ead) in CRUD. Get By Resource ID --//
	// -----------------------------------------------------------------//
	@GET
	@Path("{id: [a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[14][a-fA-F0-9]{3}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}{mimeType:(\\.[^/]*?)?}")
//  Let everything through and then deal with it when needed. 
//  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getEnvironment(@PathParam("id") String id,
								   @PathParam("mimeType") String mimeType)
	{
		logger.debug("Retrieve Environment with URL Postfix mimeType = '" + mimeType + "' and id = " + id);	
				
		TokenInfo tokenInfo = null;
		try
		{
			tokenInfo = getBearerTokenInfo(getAuthInfo());
		}
		catch (VerifyError ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Not Authorized.", ex.getMessage()), ResponseAction.QUERY);
		}
		
		// token already validated by getBearerTokenInfo() method above
		ErrorDetails errors = validSession(getAuthInfo(), false, tokenInfo);
		if (errors == null)
		{
  			// If we get here then a session exists for the given security token and is now in the session 
			// cache. Now get the actual Environment from the store.
			try
		    {
				SIF3Session sif3Session = getSIF3SessionForRequest();
				EnvironmentType environment = getDirectEnvironmentManager().reloadEnvironmentBySessionToken(sif3Session.getSessionToken(), tokenInfo, isSecure());					
				
				// Also ensure that the id matches the ID of the environment otherwise we have a bit of a mixup
	  			if (!id.equals(environment.getId()))
	  			{
	  				errors = new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Environment with id "+id+" does not exist.");
	  				return makeErrorResponse(errors, ResponseAction.QUERY);
	  			}
	  			
	  			return createEnvResponse(environment, Status.OK.getStatusCode(), ResponseAction.QUERY);
		    }
		    catch (Exception ex)
		    {
		    	logger.error("Failed to retrieve environment with ID = "+id+": "+ ex.getMessage(), ex);
		    	return makeErrorResponse( new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Error accessing environment store: "+ ex.getMessage()), ResponseAction.QUERY);
		    }
		}
		else
		{
			logger.debug("Error Found: "+errors);
			return makeErrorResponse(errors, ResponseAction.QUERY);
		}
	}

	// ---------------------------------------------------//
	// -- DELETE Section: This is the D(elete) in CRUD. --//
	// ---------------------------------------------------//
	@DELETE
	@Path("{id: [a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[14][a-fA-F0-9]{3}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}}{mimeType:(\\.[^/]*?)?}")
//  Let everything through and then deal with it when needed. 
//  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteEnvironment(@PathParam("id") String id,
									  @PathParam("mimeType") String mimeType)
	{
		logger.debug("Delete Environment with URL Postfix mimeType = '" + mimeType + "' and id = " + id);
    
		TokenInfo tokenInfo = null;
		try
		{
			tokenInfo = getBearerTokenInfo(getAuthInfo());
		}
		catch (VerifyError ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Not Authorized.", ex.getMessage()), ResponseAction.DELETE);
		}
		
		// token already validated by getBearerTokenInfo() method above
		ErrorDetails errors = validSession(getAuthInfo(), false, tokenInfo);
		if (errors == null)
		{
		    try
		    {
	  			// If we get here then a session exists for the given security token and is now in the session 
				// cache. Now get the actual Environment from the store.
				SIF3Session sif3Session = getSIF3SessionForRequest();
	  			EnvironmentType environment = getDirectEnvironmentManager().reloadEnvironmentBySessionToken(sif3Session.getSessionToken(), tokenInfo, isSecure());
	  			
	  			// Also ensure that the id matches the ID of the environment otherwise we have a bit of a mixup
	  			if (!id.equals(environment.getId()))
	  			{
					errors = new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Environment with id "+id+" does not exist. No action taken.");
					return makeErrorResponse(errors, ResponseAction.DELETE);
	  			}
	  			
				if (getDirectEnvironmentManager().removeEnvironmentBySessionToken(sif3Session.getSessionToken(), true))
				{
					return makeResopnseWithNoContent(false, ResponseAction.DELETE);
				}
				else
				{
					logger.error("Environment with id "+id+" couldn't be deleted. See error log for details.");
					errors = new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Environment with id "+id+" couldn't be deleted.");
					return makeErrorResponse(errors, ResponseAction.DELETE);
				}
		    }
		    catch (Exception ex)
		    {
		    	logger.error("Failed to retrieve & remove environment with ID = "+id+": "+ ex.getMessage(), ex);
		    	return makeErrorResponse( new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Error accessing environment store: "+ ex.getMessage()), ResponseAction.DELETE);
		    }			
		}
		else
		{
			logger.debug("Error Found: "+errors);
			return makeErrorResponse(errors, ResponseAction.DELETE);
		}
	}

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private ProviderEnvironment getEnvironment()
	{
		return (ProviderEnvironment)getEnvironmentManager().getEnvironmentInfo();
	}
	
	/*
	 * Convenience Method.
	 */
	private DirectProviderEnvironmentManager getDirectEnvironmentManager()
	{
		return (DirectProviderEnvironmentManager)getEnvironmentManager();
	}	

	private ArrayList<String> envDataValid(EnvironmentType environment)
	{
		ArrayList<String> errors = new ArrayList<String>();
		if (environment == null)
		{
			errors.add("No environment data provided.");
		}
		else
		{
			//checkValue(environment.getSolutionId(), "solutionId", errors);
			//checkValue(environment.getAuthenticationMethod(), "authenticationMethod", errors);
			//checkValue(environment.getConsumerName(), "consumerName", errors);

			if (environment.getApplicationInfo() != null)
			{
				checkValue(environment.getApplicationInfo().getApplicationKey(), "applicationInfo/applicationKey", errors);
				//checkValue(environment.getApplicationInfo().getSupportedInfrastructureVersion(), "applicationInfo/supportedInfrastructureVersion", errors);
				//checkValue(environment.getApplicationInfo().getDataModelNamespace(), "applicationInfo/dataModelNamespace", errors);
				//checkValue(environment.getApplicationInfo().getTransport(), "applicationInfo/transport", errors);
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
		return makeResponse(environment, statusCode, false, responseAction, getInfraMarshaller());
	}
	
	private void checkValue(String value, String elementName, ArrayList<String> errors)
	{
		if (StringUtils.isEmpty(value))
		{
			errors.add(elementName);
		}
	}
}
