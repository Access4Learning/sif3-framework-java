/*
 * BaseResource.java
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
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import sif3.common.CommonConstants;
import sif3.common.conversion.MarshalFactory;
import sif3.common.exception.MarshalException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.header.ResponseHeaderConstants;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.model.AuthenticationInfo;
import sif3.common.model.PagingInfo;
//import sif3.common.model.QueryMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.URLQueryParameter;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.persist.model.SIF3Session;
import sif3.common.utils.AuthenticationUtils;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.mgr.DirectProviderEnvironmentManager;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.common.model.CreateResponseType;
import sif3.infra.common.model.CreateType;
import sif3.infra.common.model.CreatesType;
import sif3.infra.common.model.DeleteIdType;
import sif3.infra.common.model.DeleteRequestType;
import sif3.infra.common.model.DeleteResponseType;
import sif3.infra.common.model.DeleteStatus;
import sif3.infra.common.model.DeleteStatusCollection;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.ErrorType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.UpdateResponseType;
import sif3.infra.common.model.UpdateType;
import sif3.infra.common.model.UpdatesType;
import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.DateUtils;
import au.com.systemic.framework.utils.StringUtils;

/**
 * This is a utility class. All REST resource style class should extend this class. It provides many common functions that are
 * required to deal successfully with SIF3 style REST resources. This utility class helps to maintain request and response
 * properties as well as forming the correctly structured response for many SIF3 REST operations. It fully abstracts many low level
 * SIF3 infrastructure concepts and it also provides a number of useful methods to easily access various properties of either a
 * request or a response.
 *  
 * @author Joerg Huber
 */
public abstract class BaseResource
{
	protected final Logger logger = Logger.getLogger(getClass());
	
	private enum PostFixMimeType {XML, JSON};
	
	private static final String HTTPS_SCHEMA = "https";

	private boolean allOK = true;

	private UriInfo uriInfo;
	private Request request;
	private HttpHeaders requestHeaders;
	private ObjectFactory infraObjectFactory = new ObjectFactory();
	private InfraMarshalFactory infraMarshaller = new InfraMarshalFactory();
	private InfraUnmarshalFactory infraUnmarshaller = new InfraUnmarshalFactory();
	private HeaderProperties hdrProperties = new HeaderProperties();
	private SIFZone sifZone = null;
	private SIFContext sifContext = null;
	private boolean isSecure = false;
	private String relativeServicePath = null; 
	private AuthenticationInfo authInfo = null;
	private URLQueryParameter queryParameters= null;
	private MediaType requestMediaType = null;
	private MediaType responseMediaType = null;
	private MediaType urlPostfixMimeType = null;

  /* Metadata extracted from URI relating to query */ 
//	private QueryMetadata queryMetadata;
	
	public abstract EnvironmentManager getEnvironmentManager();
		
	/**
	 * Constructor. Initialises the REST resource with a number of important properties that are needed throughout the SIF3 REST
	 * resources. If a resource is accessed for the first time then this method will ensure that all environmental components such
	 * as the environment store, property files etc are initialised as necessary.
	 * 
	 * @param uriInfo The URI of the request in its original form.
	 * @param requestHeaders All headers of the original request.
	 * @param request The actual request in its raw form.
	 */
	public BaseResource(UriInfo uriInfo, HttpHeaders requestHeaders, Request request, String servicePrefixPath, String zoneID, String contextID)
	{
		this.uriInfo = uriInfo;
		this.request = request;
		this.requestHeaders = requestHeaders;
		extractHeaderProperties(requestHeaders);
		extractQueryParameters(uriInfo);
		setSecure(HTTPS_SCHEMA.equalsIgnoreCase(getUriInfo().getBaseUri().getScheme()));
		setRelativeServicePath(getUriInfo().getPath(), servicePrefixPath);
		extractAuthTokenInfo();
		
	    if (StringUtils.notEmpty(zoneID))
	    {
	      setSifZone(new SIFZone(zoneID));
	    }
	    
	    if (StringUtils.notEmpty(contextID))
	    {
	      setSifContext(new SIFContext(contextID));
	    }
		
		// Some debug output
		if (logger.isDebugEnabled())
		{
			logger.debug("Full URI           : " + getUriInfo().getRequestUri().toString());
			logger.debug("Base URI           : " + getUriInfo().getBaseUri().toString());
			logger.debug("Relative URI       : " + getUriInfo().getPath());
			logger.debug("Rel. Service Path  : " + getRelativeServicePath());
			logger.debug("Protocol           : " + getUriInfo().getBaseUri().getScheme());
			logger.debug("SIF3 Req. Headers  : " + getHeaderProperties());
			logger.debug("URL Query Params   : " + getQueryParameters());
			logger.debug("Hdr Req Media Type : " + getRequestMediaType());
			logger.debug("Hdr Resp Media Type: " + getResponseMediaType());
			logger.debug("Zone ID            : " + getSifZone());
			logger.debug("ContextID          : " + getSifContext());
			logger.debug("Resource Init ok   : " + allOK);
		}
	}

	public UriInfo getUriInfo()
    {
    	return this.uriInfo;
    }

	public Request getRequest()
    {
    	return this.request;
    }

	public AdvancedProperties getServiceProperties()
	{
		return getEnvironmentManager().getServiceProperties();
	}


	public String getRelativeServicePath()
	{
		return relativeServicePath;
	}

	public void setRelativeServicePath(String relativeServicePath, String servicePrefixPath)
	{
		if (StringUtils.notEmpty(servicePrefixPath))
		{
			this.relativeServicePath = relativeServicePath.replaceAll(servicePrefixPath, "");
		}
		else
		{
			this.relativeServicePath = relativeServicePath;
		}
	}

	public boolean isInitialised()
	{
		return allOK;
	}

	public HeaderProperties getHeaderProperties()
    {
    	return this.hdrProperties;
    }

	/*
	 * If the request media type is not set it will try to get the media type from the URL Postfix. If thait is not set either then XML is returned
	 */
	public MediaType getRequestMediaType()
    {
    	return ((this.requestMediaType != null) ? this.requestMediaType : (this.urlPostfixMimeType != null ? this.urlPostfixMimeType : MediaType.APPLICATION_XML_TYPE));
    }

  /*
   * If the response media type is not set it will try to get the media type from the URL Postfix. If thait is not set either then XML is returned
   */
	public MediaType getResponseMediaType()
    {
    	return ((this.responseMediaType != null) ? this.responseMediaType : (this.urlPostfixMimeType != null ? this.urlPostfixMimeType : MediaType.APPLICATION_XML_TYPE));
    }

	public HttpHeaders getRequestHeaders()
    {
    	return this.requestHeaders;
    }
	
//	public QueryMetadata getQueryMetadata()
//    {
//    	return this.queryMetadata;
//    }
//
//	public void setQueryMetadata(QueryMetadata queryMetadata)
//    {
//    	this.queryMetadata = queryMetadata;
//    }

	public SIFZone getSifZone()
	{
		return sifZone;
	}

	public void setSifZone(SIFZone sifZone)
	{
		this.sifZone = sifZone;
	}

	public SIFContext getSifContext()
	{
		return sifContext;
	}

	public void setSifContext(SIFContext sifContext)
	{
		this.sifContext = sifContext;
	}

	public boolean isSecure()
    {
    	return this.isSecure;
    }

	public void setSecure(boolean isSecure)
    {
    	this.isSecure = isSecure;
    }

  public URLQueryParameter getQueryParameters()
  {
    return queryParameters;
  }

  public void setQueryParameters(URLQueryParameter queryParameters)
  {
    this.queryParameters = queryParameters;
  }

  /*--------------------------------*/
	/*-- Some handy Utility Methods --*/
	/*--------------------------------*/
	/**
	 * If the client is valid then the ErrorDetails returned is null. If there is something wrong, then the ErrorDetails will be 
	 * set accordingly and returned. Checks include if authentication token is valid for an environment and if the access
	 * rights for the requested service are at expected levels.
	 * 
	 * @param serviceName Service for which the access rights shall be checked.
	 * @param right The access right (QUERY, UPDATE etc) that shall be checked for.
	 * @param accessType The access level (SUPPORTED, APPROVED, etc) that must be met for the given service and right.
	 * 
	 * @return See desc
	 */
	public ErrorDetails validClient(String serviceName, AccessRight right, AccessType accessType)
	{
	    // There must be a session/environment for that client otherwise an error would have been returned with previous check
	    ProviderEnvironment envInfo = getProviderEnvironment();
    
	    // Check session details like authentication and authorisation
		ErrorDetails error = validSession();
		
		if (error != null)
		{
			return error;
		}

		// Check if DELAYED requests are supported. Right now this direct environment does not support it.=> Return an error.
		if (getEnvironmentManager().getEnvironmentType() == sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType.DIRECT)
		{
			if (extractRequestType() == RequestType.DELAYED)
			{
				return new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Environment Provider: DELAYED requests are not supported with this DIRECT Environment");
			}
		}
		
		if (envInfo.getCheckACL())
		{
			// We must have a valid session for that request otherwise it would have failed in validSession() method.
			SIF3Session sif3Session = getSIF3SessionForRequest();
			SIFContext context = getSifContext();
			SIFZone zone = getSifZone();
			
			//Check access rights
			if (!sif3Session.hasAccess(right, accessType, serviceName, zone, context))
			{
				String zoneID = (zone == null) ? "Default" : zone.getId();
				String contextID = (context == null) ? "Default" : context.getId();
				error = new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Not authorized.", right.name()+ " access is not set to "+accessType.name()+" for the service "+serviceName+" and the given zone ("+zoneID+") and context ("+contextID+") in the environment "+sif3Session.getEnvironmentName(), "Provider side check.");			
			}
		}
		return error;
	}
	
	/**
	 * This method checks if the given userToken & password match the authentication method set in the environment. The authentication token 
	 * from the request header is already split into its component and is used to compare against the values passed to this method. If all 
	 * checks pass successfully then null is returned otherwise and ErrorDetails record is returned that holds appropriate error information
	 * to be sent back to the client. 
	 * 
//	 * @param envInfo The environment with all the information to be used to validate the userToken & password against.
	 * @param userToken The userToken to compare against. This might be used to compare against an expected user. 
	 * @param password The password to compare against. This might be used to compare against an expected password.
	 * 
	 * @return See desc.
	 */
	public ErrorDetails validateAuthToken(String userToken, String password)
	{
		// Check if authentication method matches the authentication method of the environment for which it is.
		if (getAuthInfo().getAuthMethod() == null)
		{
			return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Not authorized. No Authentication Method set.", "Choose between Basic & SIF_HMACSHA256 as Authentication Method. Refer to SIF3 Specification for details.");
		}

//		if (envInfo.getAuthMethod() != getAuthInfo().getAuthMethod())
//		{
//			return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Not authorized. Authentication Method in Request doesn't match Authentication Method of environment.");
//		}

		if (StringUtils.isEmpty(getAuthInfo().getPassword()))
		{
			return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Not authorized. Password Invalid.", "Password is not provided.");
		}

		// Ok we have an session for that session Token. Now we need to check if it is a properly authenticated token.
		//String authToken = getAuthTokenFromHeader();
		String authToken = AuthenticationUtils.getFullBase64Token(getAuthInfo());
		String newAuthToken = null;
		if (getAuthInfo().getAuthMethod() == AuthenticationInfo.AuthenticationMethod.Basic)
		{
			// Create authentication token and compare if it matches
			newAuthToken = AuthenticationUtils.getBasicAuthToken(userToken, password);			
		}
	    if (getAuthInfo().getAuthMethod() == AuthenticationInfo.AuthenticationMethod.Bearer) // Experimental to get SIF Basic to work
	    {
	      // Create authentication token and compare if it matches
	      newAuthToken = AuthenticationUtils.getBearerAuthToken(userToken, password);      
	    }
		else if (getAuthInfo().getAuthMethod() == AuthenticationInfo.AuthenticationMethod.SIF_HMACSHA256)
		{
			// Get the timestamp which is required for the hashing.
			String timestamp = extractTimestampFromHeader();
			if (StringUtils.notEmpty(timestamp))
			{
				newAuthToken = AuthenticationUtils.getSIFHMACSHA256Token(userToken, password, timestamp);
			}
			else
			{
				return new ErrorDetails( Status.UNAUTHORIZED.getStatusCode(), "Not authorized. Timestamp missing in request.", "For SIF_HMACSHA256 authentication the HTTP request must have a timestamp. Refer to SIF3 Specification for details.");
			}
		}
	    //System.out.println("Auth Token from Header/Query Parameter: "+authToken);
	    //System.out.println("Auth Token To Compare with: "+newAuthToken);
	    
		if (!authToken.equals(newAuthToken))
		{
			return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Not authorized. authorization token in request doesn't match expected authorization token in session.");
		}

		return null;
	}
	
	/**
	 * This method checks if the information in the authentication token matches an existing session and if the token is an
	 * authorised/authenticated token. If all tests succeed then null is returned, otherwise and ErrorDetails object is returned
	 * that holds all the required error information and status.
	 * 
	 * @return See desc
	 */
	public ErrorDetails validSession()
	{
//		ProviderEnvironment envInfo = getProviderEnvironment();
		
		// we must have a authentication token and there must be an environment with that authentication token
		String sessionToken = getSessionToken();
		
		if (sessionToken != null)
		{
			SIF3Session sif3Session = getEnvironmentManager().getSessionBySessionToken(sessionToken);
			if (sif3Session == null) // not in session store, yet. => Attempt to load it
			{
				if (getEnvironmentManager().getEnvironmentType() == sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType.BROKERED)
				{
					// In a brokered environment the provider environment manager must really have the session already loaded for 
					// the provider, otherwise we may have a real problem and things are in an inconsistent state. We should return 
					// an error as it appears a request with an invalid sessionToken tries to access the provider.
					String errStr = "Provider's sessionToken doesn't match the request's sessoionToken, or the provider has no session initialised.";
					logger.error(errStr+" See previous error log entries for details.");
					return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Not authorized.", errStr);								
				}
				else
				{
					// In a direct environment the session for the given session token might not yet be loaded in the environment provider. 
					// In this case we attempt to load it and then compare if everything is fine.
				    try
				    {
				    	EnvironmentType environment = ((DirectProviderEnvironmentManager)getEnvironmentManager()).reloadEnvironmentBySessionToken(sessionToken, isSecure());

				    	// If we have no environment then there is no environment for that session token
						if (environment == null)
						{
							String errorStr = "No environment exits for the given sessionToken = "+sessionToken+". Ensure that environment is created first.";
					    	logger.error(errorStr);
					    	return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Not authorized.", errorStr);			
						}
						
						//Now I should have a session in the store.
						sif3Session = getEnvironmentManager().getSessionBySessionToken(sessionToken);
				    }
				    catch (Exception ex)
				    {
				    	logger.error("Failed to retrieve environment for session token = "+sessionToken+": "+ ex.getMessage(), ex);
				    	return new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Error accessing environment store: "+ ex.getMessage());
				    }
				}
			}
			
			// Do the full validation of Auth Token.
//			return validateAuthToken(envInfo, sessionToken, sif3Session.getPassword());
			return validateAuthToken(sessionToken, sif3Session.getPassword());
		}
		else // we have no or an invalid authorisation token
		{
			return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "No or invalid Authorization Token provided");				
		}
	}
		
	public String getSessionToken()
	{
	  if (getAuthInfo() != null)
	  {
	    return getAuthInfo().getUserToken();// This is the sessionToken
	  }
	  
	  // If we get here then we don't have a sessionToken
	  return null;
	}
	
	public String getProviderID()
	{
		return getEnvironmentManager().getEnvironmentInfo().getAdapterName();
	}
	
	/**
	 * This method processes the low level SIF3 DeleteRequest message and returns its content in a nice and easy to handle 
	 * data structure.
	 * 
	 * @param deletePayload The SIF3 Infrastructure Delete Request Message.
	 * 
	 * @return A list of ID that are requested to be deleted.
	 * 
	 * @throws UnmarshalException If the payload is invalid and cannot be marshalled into its predefined structure.
	 * @throws UnsupportedMediaTypeExcpetion 
	 */
	public List<String> getResourceIDsFromDeleteRequest(String deletePayload) throws UnmarshalException, UnsupportedMediaTypeExcpetion
	{
    List<String> resourceIDs = new ArrayList<String>();
    if (deletePayload != null)
    {
      DeleteRequestType deletes = (DeleteRequestType)infraUnmarshaller.unmarshal(deletePayload, DeleteRequestType.class, getRequestMediaType());
      if ((deletes.getDeletes() != null) && (deletes.getDeletes().getDelete() != null))
      {
        for (DeleteIdType id : deletes.getDeletes().getDelete())
        {
          resourceIDs.add(id.getId());
        }
      }
    }
	  return resourceIDs;
	}
	
	/**
	 * Converts the ErrorDetails into a REST Response according to the SIF3 Specification. 
	 * 
	 * @param error Error Information to be put into the REST Response.
	 * 
	 * @return REST Response Object.
	 */
	public Response makeErrorResponse(ErrorDetails error, ResponseAction responseAction)
	{
		return makeResponse(makeError(error), error.getErrorCode(), true, responseAction, infraMarshaller);	
	}
	
	/**
	 * Create a HTTP Response without any content. It will set some header properties as defined in the SIF3 spec.
	 *  
	 * @param isError TRUE: Indicates that the Response is an error response otherwise it is assumed it is a standard response.
	 * 
	 * @return A HTTP Response to be sent back to the client.
	 */
	public Response makeResopnseWithNoContent(boolean isError, ResponseAction responseAction)
	{
		ResponseBuilder response = Response.noContent();
		response = response.header(ResponseHeaderConstants.HDR_PROVIDER_ID, getProviderID());
		response = response.header(ResponseHeaderConstants.HDR_DATE_TIME, DateUtils.nowAsISO8601());
		response = response.header(ResponseHeaderConstants.HDR_MESSAGE_TYPE, (isError) ? HeaderValues.MessageType.ERROR.name() : HeaderValues.MessageType.RESPONSE.name());					
		response = response.header(ResponseHeaderConstants.HDR_RESPONSE_ACTION, responseAction.name());
		response = response.header(ResponseHeaderConstants.HDR_REL_SERVICE_PATH, getRelativeServicePath());
		response = response.header(ResponseHeaderConstants.HDR_SERVICE_TYPE, hdrProperties.getHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE));

		// Mirror requestId if available
		String requestID = hdrProperties.getHeaderProperty(RequestHeaderConstants.HDR_REQUEST_ID);
		if (requestID != null)
		{
			response = response.header(ResponseHeaderConstants.HDR_REQUEST_ID, requestID);				
		}
		return response.build();
	}
	
	/**
	 * This method creates a HTTP response that adheres to the SIF3 specification. 
	 * 
	 * @param data The data (payload) that shall be put into the response.
	 * @param status The status of the response.
	 * @param isError Indicator if the response is an error or a standard response.
	 * @param marshaller The marshaller that converts the 'data' into a valid media type.
	 * 
	 * @return A HTTP Response to be sent back to the client.
	 */
	public Response makeResponse(Object data, int status, boolean isError, ResponseAction responseAction, MarshalFactory marshaller)
	{
		return makeFullResponse(data, status, null, isError, responseAction, marshaller);
	}
	
	/**
	 * This method creates a HTTP response that adheres to the SIF3 specification. Paging Information given are added to the
	 * appropriate headers in the response. 
	 * 
	 * @param data The data (payload) that shall be put into the response.
	 * @param pagingInfo Paging Information to be added to the response header.
	 * @param isError Indicator if the response is an error or a standard response.
	 * @param marshaller The marshaller that converts the 'data' into a valid media type.
	 * 
	 * @return A HTTP Response to be sent back to the client.
	 */
	public Response makePagedResponse(Object data, PagingInfo pagingInfo, boolean isError, MarshalFactory marshaller)
	{
		return makeFullResponse(data, Status.OK.getStatusCode(), pagingInfo, isError, ResponseAction.QUERY, marshaller);
	}

	/**
	 * This method creates a response to a Bulk Create request. The payload of this response is defined in the SIF3 Specification.
	 * This method ensures that the payload follows the low level Infrastructure definition of the response.
	 * 
	 * @param operationStatusList List of status for each object that was originally requested to be created.
	 * @param overallStatus Overall status of the operation as specified in the SIF3 Spec.
	 * 
	 * @return A HTTP Response to be sent back to the client.
	 */
	public Response makeCreateMultipleResponse(List<CreateOperationStatus> operationStatusList, Status overallStatus)
	{
		CreateResponseType createManyResponse = infraObjectFactory.createCreateResponseType();
		createManyResponse.setCreates(new CreatesType());
		List<CreateType> creates = createManyResponse.getCreates().getCreate();
		
		for (CreateOperationStatus opStatus : operationStatusList)
		{
			CreateType createType = new CreateType();
			createType.setId(opStatus.getResourceID());
			createType.setAdvisoryId(opStatus.getAdvisoryID());
			createType.setStatusCode(Integer.toString(opStatus.getStatus()));
			if (opStatus.getError() != null)
			{
				createType.setError(makeError(opStatus.getError()));
			}	
			creates.add(createType);
		}
		return makeResponse(createManyResponse, overallStatus.getStatusCode(), false, ResponseAction.CREATE, infraMarshaller);
	}
	
	/**
	 * This method creates a response to a Bulk Update request. The payload of this response is defined in the SIF3 Specification.
	 * This method ensures that the payload follows the low level Infrastructure definition of the response.
	 * 
	 * @param operationStatusList List of status for each object that was originally requested to be updated.
	 * @param overallStatus Overall status of the operation as specified in the SIF3 Spec.
	 * 
	 * @return A HTTP Response to be sent back to the client.
	 */
	public Response makeUpdateMultipleResponse(List<OperationStatus> operationStatusList, Status overallStatus)
	{
		UpdateResponseType updateManyResponse = infraObjectFactory.createUpdateResponseType();
		updateManyResponse.setUpdates(new UpdatesType());
		List<UpdateType> updates = updateManyResponse.getUpdates().getUpdate();
		
		for (OperationStatus opStatus : operationStatusList)
		{
			UpdateType updateType = new UpdateType();
			updateType.setId(opStatus.getResourceID());
			updateType.setStatusCode(Integer.toString(opStatus.getStatus()));
			if (opStatus.getError() != null)
			{
				updateType.setError(makeError(opStatus.getError()));
			}	
			updates.add(updateType);
		}
		return makeResponse(updateManyResponse, overallStatus.getStatusCode(), false, ResponseAction.UPDATE, infraMarshaller);
	}

	/**
	 * This method creates a response to a Bulk Delete request. The payload of this response is defined in the SIF3 Specification.
	 * This method ensures that the payload follows the low level Infrastructure definition of the response.
	 * 
	 * @param operationStatusList List of status for each object that was originally requested to be deleted.
	 * @param overallStatus Overall status of the operation as specified in the SIF3 Spec.
	 * 
	 * @return A HTTP Response to be sent back to the client.
	 */
	public Response makeDeleteMultipleResponse(List<OperationStatus> operationStatusList, Status overallStatus)
	{
		DeleteResponseType deleteManyResponse = infraObjectFactory.createDeleteResponseType();
		deleteManyResponse.setDeletes(new DeleteStatusCollection());
		List<DeleteStatus> deletes = deleteManyResponse.getDeletes().getDelete();
		
		for (OperationStatus opStatus : operationStatusList)
		{
			DeleteStatus deleteStatus = new DeleteStatus();
			deleteStatus.setId(opStatus.getResourceID());
			deleteStatus.setStatusCode(Integer.toString(opStatus.getStatus()));
			if (opStatus.getError() != null)
			{
				deleteStatus.setError(makeError(opStatus.getError()));
			}	
			deletes.add(deleteStatus);
		}
		return makeResponse(deleteManyResponse, overallStatus.getStatusCode(), false, ResponseAction.DELETE, infraMarshaller);
	}

	/*-----------------------*/
	/*-- Protected Methods --*/
	/*----------------------*/
	protected ProviderEnvironment getProviderEnvironment()
	{
		return (ProviderEnvironment)getEnvironmentManager().getEnvironmentInfo();
	}

	protected SIF3Session getSIF3SessionForRequest()
	{
		String sessionToken = getSessionToken();

		if (sessionToken != null)
		{
			return getEnvironmentManager().getSessionBySessionToken(sessionToken);
		}

		return null;
	}
	
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
//	private String getAuthTokenFromHeader()
//	{
//		return getHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN);
//	}

	private void extractHeaderProperties(HttpHeaders requestHeaders)
	{
		hdrProperties = new HeaderProperties(); //ensure it is clean, ie. not holding values from a previous call.
		for (String hdrName : RequestHeaderConstants.HEADER_NAME_ARRAY)
		{
			String hdrValue = requestHeaders.getRequestHeaders().getFirst(hdrName);
			if (StringUtils.notEmpty(hdrValue))
			{
				hdrProperties.setHeaderProperty(hdrName, hdrValue);
			}
		}
		
		// Try to get the request and response media type
		requestMediaType  = getMediaTypeFromStr(requestHeaders.getRequestHeaders().getFirst(HttpHeaders.CONTENT_TYPE), HttpHeaders.CONTENT_TYPE);
		if (requestMediaType != null)
		{
		  // If it is not one of the accpted types then set it to null
		  if (!(requestMediaType.isCompatible(MediaType.APPLICATION_XML_TYPE) ||
		       requestMediaType.isCompatible(MediaType.TEXT_XML_TYPE) ||
		       requestMediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE)))
		  {
		    requestMediaType = null;
		  }
		}
		
		responseMediaType = getMediaTypeFromStr(requestHeaders.getRequestHeaders().getFirst(HttpHeaders.ACCEPT), HttpHeaders.ACCEPT);
		if ((responseMediaType == null) || responseMediaType.isWildcardSubtype()) // not really specified, so we assume the same as request
		{
		  responseMediaType = requestMediaType;
		}
		// If it is not one of the accpted types then set it to value of request
		else if (!(responseMediaType.isCompatible(MediaType.APPLICATION_XML_TYPE) ||
		           responseMediaType.isCompatible(MediaType.TEXT_XML_TYPE) ||
		           responseMediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE)))
    {
		  responseMediaType = requestMediaType;
    }
	}

	private MediaType getMediaTypeFromStr(String mediaTypeStr, String headerName)
	{
		if (StringUtils.isEmpty(mediaTypeStr))
		{
			return null;
		}
		try
		{
			return MediaType.valueOf(mediaTypeStr);
		}
		catch (Exception ex) // invalid mime type. Return null.
		{
			logger.info("Failed to convert media type '" + mediaTypeStr + "' from request header '" + headerName + "' to MediaType class.");
			return null;
		}
	}
	
	/*
	 * The URL postfix mime type can be of the form '.xml', 'xml', '.json' etc. all case insensitive. If null or an empty value is given then
	 * a default of XML is returned.
	 */
	protected void setURLPostfixMediaType(String urlPostfixMimeTypeStr)
	{
	  if (StringUtils.isEmpty(urlPostfixMimeTypeStr))
	  {
		  urlPostfixMimeType = MediaType.APPLICATION_XML_TYPE;
	  }
	  else
	  {
		  // get the position of the last '.'.
		  int pos = urlPostfixMimeTypeStr.lastIndexOf(".");
		  if ((pos > -1) && (pos + 1 < urlPostfixMimeTypeStr.length())) // "." found. Get everything after the "."
		  {
			  urlPostfixMimeTypeStr = urlPostfixMimeTypeStr.substring(pos+1);
		  }
		  
		  // Start comparing for valid types
		  PostFixMimeType mimeType = PostFixMimeType.XML;
		  try
		  {
		    mimeType = PostFixMimeType.valueOf(urlPostfixMimeTypeStr.trim().toUpperCase());
		  }
		  catch (Exception ex)
		  {
		    logger.error("Failed to convert URL Postfix Mime Type '"+urlPostfixMimeTypeStr+"' to XML or JSON. Default to Media Type will be APPLICATION_XML");
		    mimeType = PostFixMimeType.XML;
		  }
		  
		  switch (mimeType)
		  {
		    case XML: 
		    	{
		    		urlPostfixMimeType = MediaType.APPLICATION_XML_TYPE; 
		    		break;
		    	}
		    case JSON: 
		    	{
		    		urlPostfixMimeType = MediaType.APPLICATION_JSON_TYPE;
		    		break;
		    	}
		    default:
		    {
		    	urlPostfixMimeType = MediaType.APPLICATION_XML_TYPE; 
	    		break;
		    }
		  }
	  }
	}
	
  private void extractQueryParameters(UriInfo uriInfo)
  {
    setQueryParameters(new URLQueryParameter(getUriInfo().getQueryParameters()));
  }

	private ErrorType makeError(ErrorDetails error)
	{
		ErrorType sifError = infraObjectFactory.createErrorType();
		sifError.setId(UUIDGenerator.getUUID());
		sifError.setCode(error.getErrorCode());
		sifError.setDescription(error.getDescription());
		sifError.setMessage(error.getMessage());
		sifError.setScope(error.getScope());
		
		return sifError;
	}
	
	private Response makeFullResponse(Object data, int status, PagingInfo pagingInfo, boolean isError, ResponseAction responseAction, MarshalFactory marshaller)
	{
		ResponseBuilder response = null;
		try
		{
		  // Special case to avoid infinite loop: We deal with an error and the Status Code is of UNSUPPORTED_MEDIA_TYPE. This means we attempted
		  // to response with a media type that is not suppored, so we cannot return an Error Message Object because the marshalling will fail due
		  // to the fact that the media type is not supported. All we can do is return a status code only.
		  if (isError && (status == Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode()))
		  {
		    response = Response.status(Status.UNSUPPORTED_MEDIA_TYPE);
		  }
		  else // ok we do not deal with a unsupported media type.
		  {
  			if (data != null)
  			{
  				String payload = marshaller.marshal(data, getResponseMediaType());
  				response = Response.status(status).entity(payload);
  				response = response.header(ResponseHeaderConstants.HDR_CONTENT_LENGTH, payload.length());
  				response = response.header(HttpHeaders.CONTENT_TYPE,  getResponseMediaType());
  			}
  			else
  			{
  				response = Response.status(Status.NO_CONTENT);
  			}
		  }
			
			// Date & Time format must be: YYYY-MM-DDTHH:mm:ssZ (i.e. 2013-08-12T12:13:14Z)
			response = response.header(ResponseHeaderConstants.HDR_DATE_TIME, DateUtils.nowAsISO8601());
			response = response.header(ResponseHeaderConstants.HDR_PROVIDER_ID, getProviderID());
			response = response.header(ResponseHeaderConstants.HDR_MESSAGE_TYPE, (isError) ? HeaderValues.MessageType.ERROR.name() : HeaderValues.MessageType.RESPONSE.name());					
			response = response.header(ResponseHeaderConstants.HDR_RESPONSE_ACTION, responseAction.name());
			response = response.header(ResponseHeaderConstants.HDR_REL_SERVICE_PATH, getRelativeServicePath());
			response = response.header(ResponseHeaderConstants.HDR_SERVICE_TYPE, hdrProperties.getHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE));
			if (pagingInfo != null)
			{
        Map<String, String> responseParameters = pagingInfo.getResponseValues();
        for (String key : responseParameters.keySet())
        {
          response = response.header(key, responseParameters.get(key));
        }
//				response = response.header(ResponseHeaderConstants.HDR_LAST_PAGE_NO, pagingInfo.getMaxPages());
//				response = response.header(ResponseHeaderConstants.HDR_PAGE_NO, pagingInfo.getCurrentPageNo());
//				response = response.header(ResponseHeaderConstants.HDR_PAGE_SIZE, pagingInfo.getPageSize());
//				response = response.header(ResponseHeaderConstants.HDR_TOTAL_ITEMS, pagingInfo.getTotalObjects());
			}

			// Mirror requestId if available
			String requestID = hdrProperties.getHeaderProperty(RequestHeaderConstants.HDR_REQUEST_ID);
			if (requestID != null)
			{
				response = response.header(ResponseHeaderConstants.HDR_REQUEST_ID, requestID);				
			}
			
			return response.build();		
		}
		catch (MarshalException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to marshal "+data.getClass().getSimpleName()+": "+ex.getMessage()), responseAction);
		}
    catch (UnsupportedMediaTypeExcpetion ex)
    {
      return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Failed to marshal "+data.getClass().getSimpleName()+" into unsupported media type '"+getResponseMediaType()+"'."), responseAction);
    }
	}
		
	protected AuthenticationInfo getAuthInfo()
	{
		return authInfo;
	}

	private void setAuthInfo(AuthenticationInfo authInfo)
	{
		this.authInfo = authInfo;
	}

	private void extractAuthTokenInfo()
	{
		String authToken = getHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN);
		if (StringUtils.notEmpty(authToken))
		{
			setAuthInfo(AuthenticationUtils.getPartsFromAuthToken(authToken));
		}
		else 	// Ok no header info. Do we have something on the query parameters
		{
		  setAuthInfo(getAccessToken());
		}
		
	}

	private RequestType extractRequestType()
	{
		String requestType = getHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_REQUEST_TYPE);
		if (StringUtils.notEmpty(requestType))
		{
			try
			{
				return RequestType.valueOf(requestType);
			}
			catch (Exception ex)
			{
				logger.error("Invalid request type of '"+requestType+"' in request header. Should be DELAYED or IMMEDIATE. Assume IMMEDIATE!");
				return RequestType.IMMEDIATE;
			}
		}
		else
		{
			logger.info("Missing request header '"+RequestHeaderConstants.HDR_REQUEST_TYPE+" not set. Assume IMMEDIATE");
			return RequestType.IMMEDIATE;
		}
	}

  private AuthenticationInfo getAccessToken()
  {
    String accessTokenStr = getQueryParameters().getQueryParam(CommonConstants.ACCESS_TOKEN);
    if (StringUtils.notEmpty(accessTokenStr))
    {
      // Add "Bearer " in front of the accessToken and then treat it just like it would have been in the header
      return AuthenticationUtils.getPartsFromAuthToken(AuthenticationMethod.Bearer.name()+" "+accessTokenStr.trim());
    }
    return null;
  }
	
	private String extractTimestampFromHeader()
	{
		return getHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_DATE_TIME);
	}
}
