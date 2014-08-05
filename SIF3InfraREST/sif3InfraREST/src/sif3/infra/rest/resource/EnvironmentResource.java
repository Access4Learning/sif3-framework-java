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
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import sif3.common.CommonConstants;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.model.AuthenticationInfo;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.URLQueryParameter;
import sif3.common.persist.model.AppEnvironmentTemplate;
import sif3.common.ws.ErrorDetails;
import sif3.infra.common.env.mgr.DirectProviderEnvironmentManager;
import sif3.infra.common.env.mgr.ProviderManagerFactory;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.common.model.ApplicationInfoType;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.ProductIdentityType;
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
  /* Name of query parameters for payload free environment creation */
  private enum EnvironmentQueryParams {solutionId, applicationKey, userToken, instanceId, authenticationMethod, consumerName, supportedInfrastructureVersion, dataModelNamespace, transport, productName};
  
	// Needed in some cases where an environment is created.
	private String sessionToken = null;

	public EnvironmentResource(@Context UriInfo uriInfo,
			                   @Context HttpHeaders requestHeaders,
			                   @Context Request request,
				               @PathParam("mimeType") String mimeType)
	{
		super(uriInfo, requestHeaders, request, "", null, null);
	    setURLPostfixMediaType(mimeType);
	    logger.debug("URL Postfix mimeType: '"+mimeType+"'");
	}
	
	/*
	 * Mainly needed at creation of an environment
	 */
	@Override
	public String getSessionToken()
	{
		return (sessionToken == null) ? super.getSessionToken() : sessionToken;
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
//	    return DirectProviderEnvironmentManager.getInstance();
    }
		
    // -------------------------------------------------//
	// -- POST Section: This is the C(reate) in CRUD. --//
	// -------------------------------------------------//
	@POST
	@Path("environment{mimeType:(\\.[^/]*?)?}")
//  Let everything through and then deal with it when needed. 
//  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createEnvironment(String payload,
            						@PathParam("mimeType") String mimeType)
	{
	    setURLPostfixMediaType(mimeType);
		if (logger.isDebugEnabled())
		{
			logger.debug("Create Environment (REST POST) with URL Postfix mimeType = '" + mimeType + "' and input data: " + payload);
		}
		
		try
		{
		  EnvironmentType inputEnv = null;
		  if (StringUtils.notEmpty(payload))
		  {
		    inputEnv = (EnvironmentType) getInfraUnmarshaller().unmarshal(payload, EnvironmentType.class, getRequestMediaType());
		  }
		  else // check if we have the environment information as URL query parameters
		  {
		    AuthenticationInfo authInfo = getAuthInfo(); // we should have an application key here at this stage
		    inputEnv = makeEnvironmentFromQueryParams(getQueryParameters(), authInfo.getUserToken());
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
			ErrorDetails errors = validateAuthToken(appEnvTemplate.getApplicationKey(), appEnvTemplate.getPassword());
			if (errors != null) // we had an issue with the authentication => return error.
			{
				return makeErrorResponse(errors, ResponseAction.CREATE);       
			}
			
			// Here we are ok with authentication. Check if there is an environment for this application already.
			EnvironmentType environment = getDirectEnvironmentManager().getEnvironmentFromWorkstore(envKey, isSecure());
			if (environment != null) // we have an environment already
			{
	  			// Ensure local session token is set for later authentication
				this.sessionToken = environment.getSessionToken();
				
		        //TODO: JH - Confirm if we shall return it
		        return createEnvResponse(environment, Status.CONFLICT.getStatusCode(), ResponseAction.CREATE);
			}
			
			// If we get to this point then we don't have an environment/session yet. Create it the environment store and also add it to 
			// the ClientEnvironmentManager.
		    environment = getDirectEnvironmentManager().createOrUpdateEnvironment(inputEnv, isSecure());
		      
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
	    setURLPostfixMediaType(mimeType);
		logger.debug("Retrieve Environment with URL Postfix mimeType = '" + mimeType + "' and id = " + id);	
				
		// Ok there is a session for this environment. Validate if the user is the one that owns the environment	
		ErrorDetails errors = validSession();
		if (errors == null)
		{
			// Ok. All good. There is a session for the given session token. It has been loaded if it wasn't. Now get the actual
			// Environment from the store.
			try
		    {
				EnvironmentType environment = getDirectEnvironmentManager().reloadEnvironmentBySessionToken(getSessionToken(), isSecure());

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
	    setURLPostfixMediaType(mimeType);
		logger.debug("Delete Environment with URL Postfix mimeType = '" + mimeType + "' and id = " + id);
    
		// check if authentication token is valid for this environment. This time round we should have a proper authentication 
		// token based on sessionToken
		ErrorDetails errors = validSession();
		if (errors == null)
		{
		    try
		    {
	  			// If I get here then the client has an environment. Load and return it.
	  		  	String sessionToken = getSessionToken();
	  		  
	  		  	// Ensure we reload and return the latest. Access the session store for this.
	  			EnvironmentType environment = getDirectEnvironmentManager().reloadEnvironmentBySessionToken(sessionToken, isSecure());
	  			
	  			// Also ensure that the id matches the ID of the environment otherwise we have a bit of a mixup
	  			if (!id.equals(environment.getId()))
	  			{
					errors = new ErrorDetails(Status.NOT_FOUND.getStatusCode(), "Environment with id "+id+" does not exist. No action taken.");
					return makeErrorResponse(errors, ResponseAction.DELETE);
	  			}
	  			
				if (getDirectEnvironmentManager().removeEnvironmentBySessionToken(sessionToken))
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
	
	/* 
	 * ApplicationKey can be part of the url query parameters or it could be in the authorisation header or access token. If it is in either
	 * in the authorization header or as an access token then it must be passed to this method in the "applicationKey' parameter.
	 */
	private EnvironmentType makeEnvironmentFromQueryParams(URLQueryParameter urlParams, String applicationKey)
	{
	  if (urlParams != null)
	  {
	    ObjectFactory infraObjectFactory = new ObjectFactory();
	    EnvironmentType env = infraObjectFactory.createEnvironmentType();
	    env.setApplicationInfo(new ApplicationInfoType());
	    env.getApplicationInfo().setApplicationKey(applicationKey);
	    env.getApplicationInfo().setTransport(CommonConstants.REST_TRANSPORT_STR); // default!
	    
	    for (EnvironmentQueryParams urlParam : EnvironmentQueryParams.values())
	    {
	      String value = urlParams.getQueryParam(urlParam.name());
	      if (StringUtils.notEmpty(value))
	      {
  	      switch (urlParam)
  	      {
  	        case solutionId:
  	          env.setSolutionId(value);
  	          break;
  	        case applicationKey: // ensure that URL parameter has lowest precedence.
  	          if (StringUtils.isEmpty(env.getApplicationInfo().getApplicationKey())) // only use URL parameter if it is not yet set
  	          {
  	            env.getApplicationInfo().setApplicationKey(value);
  	          }
  	          break;
  	        case userToken:
              env.setUserToken(value);
  	          break;
  	        case instanceId:
              env.setInstanceId(value);
  	          break;
  	        case authenticationMethod:
  	          env.setAuthenticationMethod(value);
  	          break;
  	        case consumerName:
  	          env.setConsumerName(value);
  	          break;
  	        case supportedInfrastructureVersion:
  	          env.getApplicationInfo().setSupportedInfrastructureVersion(value);
  	          break;
  	        case dataModelNamespace:
  	          env.getApplicationInfo().setDataModelNamespace(value);
  	          break;
  	        case transport:
  	          env.getApplicationInfo().setTransport(value);
  	          break;
  	        case productName:
  	          if (env.getApplicationInfo().getApplicationProduct() == null)
  	          {
  	            env.getApplicationInfo().setApplicationProduct(new ProductIdentityType());
  	          }
  	          env.getApplicationInfo().getApplicationProduct().setProductName(value);
  	          break;
  	      }
	      }
	    }
	    
	    //ensure that we have at least the application key!
	    if (env.getApplicationInfo().getApplicationKey() != null)
	    {
	      return env;
	    }
	    else // log an error and return null
	    {
	      logger.error("At least the application key must be provided. Set it as URL paramater or use HTTP Authorization Header as stated in the SIF Specification.");
	      return null;
	    }
	  }
	  else
	  {
	    return null;
	  }
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
