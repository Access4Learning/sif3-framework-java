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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.DateUtils;
import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.MediaTypeOperations;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.MarshalException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.QueryIntention;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.header.ResponseHeaderConstants;
import sif3.common.model.AuthenticationInfo;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.model.URLQueryParameter;
import sif3.common.model.security.TokenInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.common.security.AbstractSecurityService;
import sif3.common.security.BearerSecurityFactory;
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
import sif3.infra.common.model.ApplicationInfoType;
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
import sif3.infra.common.model.ProductIdentityType;
import sif3.infra.common.model.UpdateResponseType;
import sif3.infra.common.model.UpdateType;
import sif3.infra.common.model.UpdatesType;
import sif3.infra.rest.resource.audit.AuditableResource;

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
    protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	/* Below variables are for testing purposes only */
    private static Boolean testMode = null;
    /* End Testing variables */
	
	private enum PostFixMimeType {XML, JSON};
	
	/* Name of query parameters for payload free environment creation */
	private enum EnvironmentQueryParams {solutionId, applicationKey, userToken, instanceId, authenticationMethod, consumerName, supportedInfrastructureVersion, dataModelNamespace, transport, productName};
	
	private static final String HTTPS_SCHEMA = "https";
	private static final String NOT_AUTHORIZED = "Not Authorized.";

	private boolean allOK = true;

	private UriInfo uriInfo;
	private Request request;
	private ObjectFactory infraObjectFactory = new ObjectFactory();
	private InfraMarshalFactory infraMarshaller = new InfraMarshalFactory();
	private InfraUnmarshalFactory infraUnmarshaller = new InfraUnmarshalFactory();
	private HeaderProperties sifHdrProperties = new HeaderProperties();
	private HeaderProperties allHTTPHdrProperties = new HeaderProperties();
	private SIFZone sifZone = null;
	private SIFContext sifContext = null;
	private boolean isSecure = false;
	private String relativeServicePath = null; 
	private AuthenticationInfo authInfo = null;
	private URLQueryParameter queryParameters= null;
	private MediaType requestMediaType = null;
	private MediaType responseMediaType = null;
	private MediaType urlPostfixMimeType = null;
	private AbstractSecurityService securityService = null;
	
	public abstract EnvironmentManager getEnvironmentManager();

	/**
	 * The marshaller of the data model. The BaseResource class needs access in various places to the data model's marshaller functions.
	 * Since the BaseResource also deals with infrastructure data model (i.e. for errors) it must have a way to distinguish between the
	 * data models. For some resources (i.e. EnvironmentResource) the data model marshaller is the same as the infrastructure marshaller.
	 * 
	 * @return Data Model Marshaller.
	 */
	public abstract MarshalFactory getMarshaller();
	
	/**
	 * The unmarshaller of the data model. The BaseResource class needs access in various places to the data model's unmarshaller functions.
	 * Since the BaseResource also deals with infrastructure data model (i.e. for errors) it must have a way to distinguish between the
	 * data models. For some resources (i.e. EnvironmentResource) the data model unmarshaller is the same as the infrastructure unmarshaller.
	 * 
	 * @return Data Model Marshaller.
	 */
	public abstract UnmarshalFactory getUnmarshaller();

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
		setURLMediaTypeFromPath(uriInfo.getPath());
		extractSIFHeaderProperties(requestHeaders);
		extractAllHTTPHeaderProperties(requestHeaders);
		extractQueryParameters();
		setSecure(HTTPS_SCHEMA.equalsIgnoreCase(getUriInfo().getBaseUri().getScheme()));
        setRelativeServicePath(getUriInfo().getRequestUri().toString(), getUriInfo().getBaseUri().toString(), servicePrefixPath);
		extractAuthTokenInfo();
		
	    if (StringUtils.notEmpty(zoneID))
	    {
	      setSifZone(new SIFZone(zoneID));
	    }
	    
	    if (StringUtils.notEmpty(contextID))
	    {
	      setSifContext(new SIFContext(contextID));
	    }
	    if (getAuthInfo() == null)
	    {
	    	logger.error("No authetication information found.");
	    	allOK = false;
	    }
	    else
	    {
		    if ((authInfo.getAuthMethod() == AuthenticationInfo.AuthenticationMethod.Bearer))
			{
		        setSecurityService(BearerSecurityFactory.getSecurityService(getServiceProperties()));
			}
	    }
		
		// Some debug output
		if (logger.isDebugEnabled())
		{
			logger.debug("Full URI           : " + getUriInfo().getRequestUri().toString());
			logger.debug("Base URI           : " + getUriInfo().getBaseUri().toString());
			logger.debug("Relative URI       : " + getUriInfo().getPath());
			logger.debug("Rel. Service Path  : " + getRelativeServicePath());
			logger.debug("Protocol           : " + getUriInfo().getBaseUri().getScheme());
			logger.debug("SIF3 Req. Headers  : " + getSIFHeaderProperties());
			logger.debug("All HTTP Headers   : " + getAllHTTPHeaderProperties());
			logger.debug("URL Query Params   : " + getQueryParameters());
			logger.debug("Zone ID            : " + getSifZone());
			logger.debug("ContextID          : " + getSifContext());
			logger.debug("Security Service   : " + ((getSecurityService() == null) ? null : getSecurityService().getClass().getSimpleName()));
			logger.debug("Resource Init ok   : " + allOK);
		}
		
		// If auditing is enabled then we must give the resource to the 'auditor' for it to extract information about the sif3 session, zones etc.
		if (AuditableResource.isAuditing())
		{
			AuditableResource.setResource(this);
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

	public void setRelativeServicePath(String fullURI, String baseURI, String servicePrefixPath)
	{
	    String removeURIPart = (StringUtils.notEmpty(servicePrefixPath)) ? baseURI+servicePrefixPath : baseURI;
	    this.relativeServicePath = fullURI.replaceAll(removeURIPart, "");
	}

	public boolean isInitialised()
	{
		return allOK;
	}

	public HeaderProperties getSIFHeaderProperties()
    {
    	return this.sifHdrProperties;
    }
	
	/**
	 * Returns all HTTP Headers and their value. The name of a particular header is in always in lower case!
	 * 
	 * @return All the available HTTP header properties of the request. See also desc for more info.
	 */
	public HeaderProperties getAllHTTPHeaderProperties()
    {
    	return this.allHTTPHdrProperties;
    }

	/*
	 * If the request media type is not set it will try to get the media type from the URL Postfix. If that is not set either then XML is returned
	 */
	public MediaType getRequestMediaType()
    {
		return this.requestMediaType;
    }

  /*
   * If the response media type is not set it will try to get the media type from the URL Postfix. If that it is 
   * not set either then XML is returned
   */
	public MediaType getResponseMediaType()
    {
		return this.responseMediaType;
    }

	public AbstractSecurityService getSecurityService()
    {
    	return this.securityService;
    }

	public void setSecurityService(AbstractSecurityService securityService)
    {
    	this.securityService = securityService;
    }

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
	 * This method returns the userToken form the given Authorisation token. This can either be a sessionToken (Basic, 
	 * SIF_HMACSH256) or a securityToken (Bearer). If no authorisation information is available then null is returned.
	 * 
	 * @return the token held in the userToken property or null if no Authentication information is available.
	 */
	public String getTokenFromAuthToken()
	{
	  if (getAuthInfo() != null)
	  {
	    return getAuthInfo().getUserToken();
	  }
	  
	  // If we get here then we don't have any authorisation info => Invalid state
	  return null;
	}
	
	public String getProviderID()
	{
		return getEnvironmentManager().getEnvironmentInfo().getAdapterName();
	}
	
	/*---------------------------------*/
	/*-- Security Validation Methods --*/
	/*---------------------------------*/

	/**
	 * This method combines the validSession() and validateBearerSession() method into a higher level, so that the caller doesn't have to know
	 * which one to invoke, rather have the code here to determine it automatically as it is possible in most cases.
	 * 
     * @param autoCreateAllowed There are times where a environment must not be created automatically even if the 
     *                          provider's property would allow this. A typical case is where we attempt to retrieve
     *                          an environment by its ID. In such case we do not create it if it doesn't exist.
     *
	 * @return Null if all is fine, ErrorDetails otherwise.
	 */
	protected ErrorDetails validateSession(boolean autoCreateAllowed)
	{
	    AuthenticationInfo authInfo = getAuthInfo();
		if ((authInfo == null) || (authInfo.getUserToken() == null))
		{
			return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "No or invalid Authorization Token provided");				
		}
	    if (authInfo.getAuthMethod() != AuthenticationMethod.Bearer) // Basic or SIF_HMACSHA256
	    {
	    	return validSession(authInfo, false, null);
	    }
	    else
	    {
	    	return validateBearerSession(authInfo, autoCreateAllowed);
	    }
	}
	
	/**
	 * This method will attempt to load a SIF3 Session from the session cache managed in the environment manager.
	 * The SIF3 Session to be returned is based on the Authorisation HTTP header information available.
	 * 
	 * @return SIF3Session if available.
	 */
	public SIF3Session getSIF3SessionForRequest()
	{
		String token = getTokenFromAuthToken();

		if (token != null)
		{
			if (getAuthInfo().getAuthMethod() == AuthenticationMethod.Bearer)
			{
				return getEnvironmentManager().getSessionBySecurityToken(token);
			}
			else // Basic, SIF_HMACSHA256
			{
				return getEnvironmentManager().getSessionBySessionToken(token);
			}
		}

		return null;
	}

	/*------------------------------------------*/
	/*-- Public Methods for Response Creation --*/
	/*------------------------------------------*/
	
	/**
	 * Converts the ErrorDetails into a REST Response according to the SIF3 Specification. 
	 * 
	 * @param error Error Information to be put into the REST Response.
	 * @param responseAction Action to be set in the appropriate HTTP header.
     * @param customResponseParams headers that might be added to the response. Can be null.
	 * 
	 * @return REST Response Object.
	 */
	public Response makeErrorResponse(ErrorDetails error, ResponseAction responseAction, ResponseParameters customResponseParams)
	{
		return makeResponse(makeError(error), error.getErrorCode(), true, responseAction, customResponseParams, infraMarshaller);	
	}
	
	/**
	 * Create a HTTP Response without any content. It will set some header properties as defined in the SIF3 spec.
	 *  
	 * @param isError TRUE: Indicates that the Response is an error response otherwise it is assumed it is a standard response.
	 * 
	 * @return A HTTP Response to be sent back to the client.
	 */
	public Response makeResopnseWithNoContent(boolean isError, ResponseAction responseAction, ResponseParameters customResponseParams)
	{
		return makeFullResponse(null, Status.NO_CONTENT.getStatusCode(), null, isError, responseAction, customResponseParams, null);
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
	public Response makeResponse(Object data, int status, boolean isError, ResponseAction responseAction, ResponseParameters customResponseParams, MarshalFactory marshaller)
	{
		return makeFullResponse(data, status, null, isError, responseAction, customResponseParams, marshaller);
	}
	
	/**
	 * This method creates a HTTP response that adheres to the SIF3 specification. Paging Information given are added to the
	 * appropriate headers in the response. 
	 * 
	 * @param data The data (payload) that shall be put into the response.
	 * @param pagingInfo Paging Information to be added to the response header.
	 * @param isError Indicator if the response is an error or a standard response.
     * @param customResponseParams Set of custom http headers to be added to the response.
	 * @param marshaller The marshaller that converts the 'data' into a valid media type.
	 * 
	 * @return A HTTP Response to be sent back to the client.
	 */
    public Response makePagedResponse(Object data, PagingInfo pagingInfo, boolean isError, ResponseParameters customResponseParams, MarshalFactory marshaller)
	{
		return makeFullResponse(data, Status.OK.getStatusCode(), pagingInfo, isError, ResponseAction.QUERY, customResponseParams, marshaller);
	}
	
	/*
	 * Only used for testing purposes. So we make it protected.
	 */
	protected Response makeDelayedAcceptResponse(ResponseAction responseAction)
	{
	    return makeFullResponse(null, Status.ACCEPTED.getStatusCode(), null, false, responseAction, null, null);
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
	public Response makeCreateMultipleResponse(List<CreateOperationStatus> operationStatusList, Status overallStatus, ResponseParameters customResponseParams)
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
		return makeResponse(createManyResponse, overallStatus.getStatusCode(), false, ResponseAction.CREATE, customResponseParams, infraMarshaller);
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
	public Response makeUpdateMultipleResponse(List<OperationStatus> operationStatusList, Status overallStatus, ResponseParameters customResponseParams)
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
		return makeResponse(updateManyResponse, overallStatus.getStatusCode(), false, ResponseAction.UPDATE, customResponseParams, infraMarshaller);
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
	public Response makeDeleteMultipleResponse(List<OperationStatus> operationStatusList, Status overallStatus, ResponseParameters customResponseParams)
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
		return makeResponse(deleteManyResponse, overallStatus.getStatusCode(), false, ResponseAction.DELETE, customResponseParams, infraMarshaller);
	}

	/*-----------------------*/
	/*-- Protected Methods --*/
	/*----------------------*/
	protected ProviderEnvironment getProviderEnvironment()
	{
		return (ProviderEnvironment)getEnvironmentManager().getEnvironmentInfo();
	}

	/*
	 * This method processes the low level SIF3 DeleteRequest message and returns its content in a nice and easy to handle 
	 * data structure.
	 * 
	 * @param deletePayload The SIF3 Infrastructure Delete Request Message.
	 * 
	 * @return A list of ID that are requested to be deleted.
	 * 
	 * @throws UnmarshalException If the payload is invalid and cannot be marshaled into its predefined structure.
	 * @throws UnsupportedMediaTypeExcpetion 
	 */
	protected List<String> getResourceIDsFromDeleteRequest(String deletePayload) throws UnmarshalException, UnsupportedMediaTypeExcpetion
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
	 * This method returns a default ResponseParamtere object. It has the httpHeaderParams property defaulted to the values listed
	 * in the "adapter.custom.response.headers" property of the providers property file. This method will never return null.
	 * 
	 * @return S
	 */
	protected ResponseParameters getInitialCustomResponseParameters()
	{
	    return new ResponseParameters(getProviderEnvironment().getCustomResponseHeaders());
	}   

	/*---------------------------------*/
	/*-- Security Validation Methods --*/
	/*---------------------------------*/
	/**
	 * If the client is valid then the ErrorDetails returned is null. If there is something wrong, then the ErrorDetails will be 
	 * set accordingly and returned. Checks include if authentication token is valid for an environment and if the access
	 * rights for the requested service are at expected levels.
	 * 
	 * @param serviceName Service for which the access rights shall be checked.
	 * @param right The access right (QUERY, UPDATE etc) that shall be checked for.
	 * @param accessType The access level (SUPPORTED, APPROVED, etc) that must be met for the given service and right.
	 * @param allowDelayed TRUE then the request operation allows delayed requests. In a DIRECT environment it is not supported at
	 *                     all and this parameter will be ignored. FALSE if delayed requests are not allowed.
     * @param autoCreateAllowed There are times where a environment must not be created automatically even if the 
     *                          provider's property would allow this. A typical case is where we attempt to retrieve
     *                          an environment by its ID. In such case we do not create it if it doesn't exist.
	 * 
	 * @return See desc
	 */
	protected ErrorDetails validClient(String serviceName, AccessRight right, AccessType accessType, boolean allowDelayed, boolean autoCreateAllowed)
	{
	    ErrorDetails error = validateSession(autoCreateAllowed);
		if (error != null)
		{
			return error;
		}

		// If we get to this point then all validation so far has succeeded and we do have a valid session
		// in the session cache and workstore (DB).
		
		// Check if DELAYED requests are supported. Right now this direct environment does not support it.=> Return an error.
		if (isDirectEnvironment())
		{
		    if (!isTestMode()) // in test mode we allow DELAYED requests and deal with it at the actual object provider
		    {
		        if (extractRequestType() == RequestType.DELAYED) 
		        {
	                return new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Environment Provider: DELAYED requests are not supported with this DIRECT Environment");		            
		        }
		    }
		}
		else  // in a Brokered environment only the 'multiple' object operations are supporting delayed.
		{
			if (extractRequestType() == RequestType.DELAYED)
			{
    			if (!allowDelayed)
    			{
    				return new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Environment Provider: DELAYED requests are not allowed for this operation");				
    			}
			}
		}
		
		if (getProviderEnvironment().getCheckACL())
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
				error = new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", right.name()+ " access is not set to "+accessType.name()+" for the service "+serviceName+" and the given zone ("+zoneID+") and context ("+contextID+") in the environment "+sif3Session.getEnvironmentName(), "Provider side check.");			
			}
		}
		return error;
	}

	/**
	 * This method checks if the information in the authentication token matches an existing session and if the token is an
	 * authorised/authenticated token. If all tests succeed then null is returned, otherwise and ErrorDetails object is returned
	 * that holds all the required error information and status.
	 * 
	 * @param validateBearerToken TRUE: Bearer Token will be validated against security service.
	 *                            FALSE: Bearer Token is assumed to already be validated.
	 * @param tokenInfo The token Info if authentication method is Bearer. In all other cases this parameter
	 *                  is null and session token info should be used from the AuthInfo object.
	 * 
	 * @return See desc
	 */
	protected ErrorDetails validSession(AuthenticationInfo authInfo, boolean validateBearerToken, TokenInfo tokenInfo)
	{
		// we must have a authentication token and there must be an environment with that authentication token
		if ((authInfo != null) && (authInfo.getUserToken() != null))
		{
			SIF3Session sif3Session = getSIF3SessionForRequest();
			if (sif3Session == null) // not in cached session store, yet. => Attempt to load it
			{
				if (isBrokeredEnvironment())
				{
					// In a brokered environment the provider environment manager must really have the session already loaded for 
					// the provider, otherwise we may have a real problem and things are in an inconsistent state. We should return 
					// an error as it appears a request with an invalid sessionToken tries to access the provider.
					String errStr = "Provider's sessionToken doesn't match the request's sessionToken, or the provider has no session initialised.";
					logger.error(errStr+" See previous error log entries for details.");
					return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, errStr);								
				}
				else
				{
					// In a direct environment the session for the given session token might not yet be loaded in the environment provider. 
					// In this case we attempt to load it and then compare if everything is fine.
				    try
				    {
				    	EnvironmentType environment = null;
				    	if (authInfo.getAuthMethod() != AuthenticationMethod.Bearer) // Basic or SIF_HMACSHA256
				    	{
				    		environment = ((DirectProviderEnvironmentManager)getEnvironmentManager()).reloadEnvironmentBySessionToken(authInfo.getUserToken(), tokenInfo, isSecure());
				    	}
				    	else // Bearer Token 
				    	{
				    		environment = ((DirectProviderEnvironmentManager)getEnvironmentManager()).reloadEnvironmentForSecurityToken(tokenInfo, isSecure());
				    	}
				    	// If we have no environment then there is no environment for that session token
						if (environment == null)
						{
							String errorStr = "No environment exits for the given security token = "+authInfo.getUserToken()+". Ensure that environment is created first.";
					    	logger.error(errorStr);
					    	return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, errorStr);			
						}
						
						//Now I should have a session in the store.
						sif3Session = getSIF3SessionForRequest();
				    }
				    catch (Exception ex)
				    {
				    	logger.error("Failed to retrieve environment for security token = "+authInfo.getUserToken()+": "+ ex.getMessage(), ex);
				    	return new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Error accessing environment store: "+ ex.getMessage());
				    }
				}
			}
			
			// Do the full validation of Auth Token.
			return validateAuthTokenWithSession(sif3Session, validateBearerToken);
		}
		else // we have no or an invalid authorisation token
		{
			return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "No or invalid Authorization Token provided");				
		}
	}

	/*
	 * This method validates the user token and password against the two valid SIF authentication methods of
	 * Basic or SIF_HMACSHA256. If the authentication method should be set to bearer and this method is 
	 * called then null is returned meaning that no validation is performed and it is assumed all is good.
	 * This means that the bearer token must already be validated external to this method. Practically this
	 * method should not be called if the authentication method is bearer. Future changes to this method may
	 * return an error in such a case. 
	 */
	protected ErrorDetails validateNoneBearerAuthToken(String userToken, String password)
	{
		if (getAuthInfo().getAuthMethod() == null)
		{
			return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "No Authentication Method set.", "Choose between Basic, SIF_HMACSHA256 or Bearer as Authentication Method. Refer to SIF3 Specification for details.");
		}

		if ((getAuthInfo().getAuthMethod() == AuthenticationInfo.AuthenticationMethod.Bearer))
		{
			// It is bearer token and it is already validated. Nothing else to do here.
			return null;
		}

	    if (StringUtils.isEmpty(getAuthInfo().getPassword()))
	    {
	    	return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "Not authorized. Password Invalid.", "Password is not provided.");
	    }
	    
	    String authToken = AuthenticationUtils.getFullBase64Token(getAuthInfo());
	    String newAuthToken = null;
	    
	    if (getAuthInfo().getAuthMethod() == AuthenticationInfo.AuthenticationMethod.Basic)
	    {
	    	// Create authentication token and compare if it matches
	    	newAuthToken = AuthenticationUtils.getBasicAuthToken(userToken, password);      
	    }
	    else // It is SIF_HMACSHA256
	    {
			// Get the timestamp which is required for the hashing.
			String timestamp = getTimestampFromRequest();
			if (StringUtils.notEmpty(timestamp))
			{
				newAuthToken = AuthenticationUtils.getSIFHMACSHA256AuthToken(userToken, password, timestamp);
			}
			else
			{
				return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "For SIF_HMACSHA256 authentication the HTTP request must have a timestamp in the HTTP header. Refer to SIF3 Specification for details.");
			}
	    }
	    
	    //System.out.println("Auth Token from Header/Query Parameter: "+authToken);
	    //System.out.println("Auth Token To Compare with: "+newAuthToken);  
	    if (!authToken.equals(newAuthToken))
	    {
	    	return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "Authorization token in request doesn't match expected authorization token in session.");
	    }

		// If we get here all validation has succeeded. We do not return any error.
		return null;
	}	

	/*
	 * This method will attempt to validate the given securityToken against an external security service. This method is only called
	 * if the Authorisation HTTP header is of type 'Bearer'. VerifyError is thrown if there are any issues accessing the security service.
	 */
	protected boolean validateBearerWithSecurityService(String securityToken) throws VerifyError
	{
	    if (getSecurityService() != null)
	    {
	    	return getSecurityService().validate(securityToken, getRequestMetadata(null, false));
	    }
	    else // No security service known => report error
	    {
	    	throw new VerifyError("No security service known to validate Bearer Token.");
	    }
	}

	/*-------------------------------*/
    /*-- Protected Utility Methods --*/
    /*-------------------------------*/

	/*
	 * This methods attempts to extract some request information that should be passed to the provider. There is a distinct set
	 * of HTTP Header Parameters set (mostly optional) by the consumer that are allowed to be visible to the provider. The SIF
	 * Specification states which HTTP Header fields they are.
	 * 
	 * SIF Simple consideration:
	 * A number of HTTP Header fields can be set as URL Query Parameter instead if SIF Simple is used. In that case we also
	 * need to check if some of these consumer fields are on the URL instead of the HTTP Header. As always if a field should be
	 * stated on the URL as well as the HTTP Header, the HTTP Header will take precedence. 
	 * 
	 * @param sif3Session The session information of the requester if known. Can be null.
	 */
	protected RequestMetadata getRequestMetadata(SIF3Session sif3Session, boolean isQuery)
	{
		RequestMetadata metadata = new RequestMetadata(getQueryParameters(), getAllHTTPHeaderProperties());
		
		// Get values from HTTP Header.
		metadata.setGeneratorID(getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_GENERATOR_ID));
		metadata.setApplicationKey(getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_APPLICATION_KEY));
		metadata.setAuthentictedUser(getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_AUTHENTICATED_USER));

		// Brokered: SourceName and Fingerprint are in HTTP Headers and are set by the Broker
		if (isBrokeredEnvironment())
		{
			metadata.setSourceName(getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_SOURCE_NAME));
			
			//Or is it a URL query parameter
			if (metadata.getSourceName() == null)
			{
				metadata.setSourceName(getQueryParameters().getQueryParam(RequestHeaderConstants.HDR_SOURCE_NAME));
			}
			
	        metadata.setFingerprint(getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_FINGERPRINT));
	        
            //Or is it a URL query parameter
            if (metadata.getFingerprint() == null)
            {
                metadata.setFingerprint(getQueryParameters().getQueryParam(RequestHeaderConstants.HDR_FINGERPRINT));
            }

		}
		// Direct: We set the SourceName to the consumer's application key if available and the fingerprint to the appropriate
		//         value as in the session of this consumer. These values cannot be set by consumers, so that they cannot
		//         pretend to be someone else.
		else
		{
			if (sif3Session != null)
			{
				metadata.setSourceName(sif3Session.getApplicationKey());
				metadata.setFingerprint(sif3Session.getFingerprint());
			}
		}
		
		if (sif3Session != null)
		{
			metadata.setEnvironmentID(sif3Session.getEnvironmentID());
		}

		// If a query type request is made then we may have a query intention set!
		if (isQuery)
		{
			metadata.setQueryIntention(QueryIntention.valueOfHTTPHeader(getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_QUERY_INTENTION)));

			// It could be on the URL parameter
			if (metadata.getQueryIntention() == null)
			{
				metadata.setQueryIntention(QueryIntention.valueOfHTTPHeader(getQueryParameters().getQueryParam(RequestHeaderConstants.HDR_QUERY_INTENTION)));
			}
			
			// If still not set then we default it to ONE-OFF
			if (metadata.getQueryIntention() == null)
			{
				metadata.setQueryIntention(QueryIntention.ONE_OFF);
				logger.debug("Query Intention not set. Default to ONE-OFF.");
			}
		}
		
		// In case of SIF Simple most of the values could be URL Query Parameters. Use them if not yet set by HTTP Header
		if (metadata.getGeneratorID() == null)
		{
			metadata.setGeneratorID(getQueryParameters().getQueryParam(RequestHeaderConstants.HDR_GENERATOR_ID));
		}
		
		if (metadata.getApplicationKey() == null)
		{
			metadata.setApplicationKey(getQueryParameters().getQueryParam(RequestHeaderConstants.HDR_APPLICATION_KEY));
		}
		if (metadata.getAuthentictedUser() == null)
		{
			metadata.setAuthentictedUser(getQueryParameters().getQueryParam(RequestHeaderConstants.HDR_AUTHENTICATED_USER));
		}
		
		// We have not set the applicationKey in the HTTP header or the URL then we retrieve it from the session.
		// In such a case we have the following two options:
		//     a) In a DIRECT environment this will be the applicationKey from the consumer. 
		//     b) In a BROKERED environment it is the applicationKey of the provider as seen by the broker.
		if (metadata.getApplicationKey() == null)
		{
			if (sif3Session != null)
			{
				metadata.setApplicationKey(sif3Session.getApplicationKey());
			}
		}

		return metadata;
	}

	/*
	 * VerifyError is thrown if anything is not valid or not working. The exception message holds the details.
	 * If everything is ok and the authentication method is Bearer then the TokenInfo is returned,
	 */
	protected TokenInfo getBearerTokenInfo(AuthenticationInfo authInfo) throws VerifyError
	{
		if (authInfo == null)
		{
			throw new VerifyError("No authentication info found. Cannot authorize consumer.");
		}
		if ((authInfo.getAuthMethod() == AuthenticationMethod.Bearer))
		{
			if (validateBearerWithSecurityService(authInfo.getUserToken()))
			{
				// Now, what info can we get about the token
				TokenInfo tokenInfo = getSecurityService().getInfo(authInfo.getUserToken(), getRequestMetadata(null, false));
				if (tokenInfo == null)
				{
					throw new VerifyError("No information about Bearer Token can be retrieved.");
				}
				else
				{
					return tokenInfo;
				}
			}
			else // invalid bearer token
			{
				throw new VerifyError("Bearer Token validation with security service returned \"Not Authorized\".");
			}
		}
		else // It is a standard Basic or SIF_HMACSHA256 authentication and therefore no further action is required.
		{
			return null;
		}
	}

	
	/*
	 * This method checks if the given path has a postfix of '.xml', '.json' etc all case insensitive.
	 * If a postfix does exist it is extracted and converted to a proper type. If no postfix is given then
	 * the mime type of XML is assumed.
	 */
	private void setURLMediaTypeFromPath(String urlPostfixMimeTypeStr)
	{
	    if (StringUtils.isEmpty(urlPostfixMimeTypeStr))
	    {
	        urlPostfixMimeType = null;
	    }
	    else
	    {
	        // get the position of the last '.'.
	        int pos = urlPostfixMimeTypeStr.lastIndexOf(".");
	        if ((pos == -1)) // no '.' found, so there is no postfix set! Assume XML
	        {
	            urlPostfixMimeType = null;
	        }
	        else
	        {  
	            if ((pos > -1) && (pos + 1 < urlPostfixMimeTypeStr.length())) // "." found. Get everything after the "."
	            {
	                urlPostfixMimeTypeStr = urlPostfixMimeTypeStr.substring(pos+1);
	            }
			  
	            // Start comparing for valid types
	            PostFixMimeType mimeType = null;
	            try
	            {
	                mimeType = PostFixMimeType.valueOf(urlPostfixMimeTypeStr.trim().toUpperCase());
	            }
	            catch (Exception ex)
	            {
	                logger.error("Failed to convert URL Postfix Mime Type '"+urlPostfixMimeTypeStr+"' to XML or JSON.");
	                mimeType = null;
	            }
			  
	            if (mimeType != null)
	            {
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
	                }
	            }
	        }
	    }
	}
	
	/* 
	 * ApplicationKey can be part of the url query parameters or it could be in the tokenInfo. If it is in either
	 * in the tokenInfo then it will take precedence of query parameters because the tokenInfo is based on security
	 * service information and is more trust worthy.
	 */
	protected EnvironmentType makeEnvironmentForBearerToken(URLQueryParameter urlParams, TokenInfo tokenInfo)
	{
		// Use what is available from the Query Params.
		EnvironmentType env = makeEnvironmentFromQueryParams(urlParams, null, false);
		
		// Now get TokenInfo and overwrite possible query parameter values.
		if (StringUtils.notEmpty(tokenInfo.getAppUserInfo().getApplicationKey()))
		{
			env.getApplicationInfo().setApplicationKey(tokenInfo.getAppUserInfo().getApplicationKey());
		}
		if (StringUtils.notEmpty(tokenInfo.getAppUserInfo().getSolutionID()))
		{
			env.setSolutionId(tokenInfo.getAppUserInfo().getSolutionID());
		}
		if (StringUtils.notEmpty(tokenInfo.getAppUserInfo().getUserToken()))
		{
			env.setUserToken(tokenInfo.getAppUserInfo().getUserToken());
		}
		if (StringUtils.notEmpty(tokenInfo.getAppUserInfo().getInstanceID()))
		{
			env.setInstanceId(tokenInfo.getAppUserInfo().getInstanceID());
		}
		if (StringUtils.notEmpty(tokenInfo.getConsumerName()))
		{
			env.setConsumerName(tokenInfo.getConsumerName());
		}
		if (StringUtils.notEmpty(tokenInfo.getDataModelNamespace()))
		{
			env.getApplicationInfo().setDataModelNamespace(tokenInfo.getDataModelNamespace());
		}
				
		return env;
	}
	
	/* 
	 * ApplicationKey can be part of the url query parameters or it could be in the authorisation header or access token. If it is in either
	 * in the authorisation header or as an access token then it must be passed to this method in the "applicationKey' parameter.
	 */
	protected EnvironmentType makeEnvironmentFromQueryParams(URLQueryParameter urlParams, String applicationKey, boolean appKeyRequired)
	{
		ObjectFactory infraObjectFactory = new ObjectFactory();
		EnvironmentType env = infraObjectFactory.createEnvironmentType();
		
		env.setApplicationInfo(new ApplicationInfoType());
		env.getApplicationInfo().setTransport(CommonConstants.REST_TRANSPORT_STR); // default!

		if (StringUtils.notEmpty(applicationKey))
		{
			env.getApplicationInfo().setApplicationKey(applicationKey);
		}
		
		if (urlParams != null)
		{
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
						// only use URL parameter if it is  not yet set
						if (StringUtils.isEmpty(env.getApplicationInfo().getApplicationKey()))
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

			if (appKeyRequired)
			{
				// ensure that we have at least the application key!
				if (env.getApplicationInfo().getApplicationKey() != null)
				{
					return env;
				}
				else
				// log an error and return null
				{
					logger.error("At least the application key must be provided. Set it as URL paramater or use HTTP Authorization Header as stated in the SIF Specification.");
					return null;
				}
			}
			else
			{
				return env;
			}
		}

		return env;
	}

	/*
	 * This method should be called in the constructors of each resource. It is intended to determine the media type of the request as well as
	 * the response. The media types are being determined firstly on the HTTP header and secondly on the URL Postfix.
	 */
	protected void determineMediaTypes(MarshalFactory marshaller, UnmarshalFactory unmarshaller, boolean useDefaults)
	{
		// Get values from HTTP headers
		requestMediaType  = getMediaTypeFromStr(getAllHTTPHeaderProperties().getHeaderProperty(HttpHeaders.CONTENT_TYPE.toLowerCase()), HttpHeaders.CONTENT_TYPE);
		responseMediaType = getMediaTypeFromStr(getAllHTTPHeaderProperties().getHeaderProperty(HttpHeaders.ACCEPT.toLowerCase()), HttpHeaders.ACCEPT);

		if (logger.isDebugEnabled())
		{
			logger.debug("Request HTTP Header "+HttpHeaders.CONTENT_TYPE+": "+requestMediaType);
			logger.debug("Request HTTP Header "+HttpHeaders.ACCEPT+": "+responseMediaType);
			logger.debug("URL Mime Type: " + urlPostfixMimeType);
		}
		
		requestMediaType = validateAndExtractMimeType(unmarshaller, requestMediaType, urlPostfixMimeType, useDefaults);

		// set it to the value of the request if it is not set or wildcard
		if ((responseMediaType == null) || (responseMediaType.isWildcardType()))
		{
			responseMediaType = requestMediaType;
		}

		responseMediaType = validateAndExtractMimeType(marshaller, responseMediaType, urlPostfixMimeType, useDefaults);
	}
	
    protected boolean isBrokeredEnvironment()
    {
        return (getEnvironmentManager().getEnvironmentType() == sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType.BROKERED);
    }

    protected boolean isDirectEnvironment()
    {
        return (getEnvironmentManager().getEnvironmentType() == sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType.DIRECT);
    }	
    
    /*
     * This is a helper method to test delayed request responses with a direct provider pretending to be able to deal with delayed 
     * requests. It will only work if resource.testmode=true and env.type=DIRECT in the provider's property file and the HTTP Header 
     * called RequestType is set to DELAYED. In this case TRUE is returned.
     */
    protected boolean pretendDelayed()
    {
        if (isDirectEnvironment())
        {
            if (isTestMode())
            {
                if (extractRequestType() == RequestType.DELAYED)
                {
                    return true;
                }
            }
        }
        
        return false;    
    }
    
    /*
     * This method checks if the resource.testmode in the provider's property file is set to TRUE.
     */
    protected boolean isTestMode()
    {
        if (testMode == null)
        {
            AdvancedProperties props = getEnvironmentManager().getServiceProperties();
            testMode = props.getPropertyAsBool("resource.testmode", false);
        }
        return testMode;
    }


	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private void extractSIFHeaderProperties(HttpHeaders requestHeaders)
	{
		sifHdrProperties = new HeaderProperties(); //ensure it is clean, ie. not holding values from a previous call.
		for (String hdrName : RequestHeaderConstants.HEADER_NAME_ARRAY)
		{
			String hdrValue = requestHeaders.getRequestHeaders().getFirst(hdrName);
			if (StringUtils.notEmpty(hdrValue))
			{
				sifHdrProperties.setHeaderProperty(hdrName, hdrValue);
			}
		}
	}

	private void extractAllHTTPHeaderProperties(HttpHeaders requestHeaders)
	{
		allHTTPHdrProperties = new HeaderProperties(); //ensure it is clean, ie. not holding values from a previous call.
		MultivaluedMap<String, String> headerMap = requestHeaders.getRequestHeaders();
		
		for (String hdrName : headerMap.keySet())
		{
			allHTTPHdrProperties.setHeaderProperty(hdrName, headerMap.getFirst(hdrName));
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
	 * This method attempts to validate the given media type against the given media type operations. If it is unable to determine and validate
	 * the media type from the HTTP Header or URL postfix and no defaults ought to be used then it will automatically default it to application/xml.
	 * This will, in most cases, be just fine. Only where a data model is used that is not XML based then we will get unsupported media type 
	 * exceptions. This exception will be caught accordingly in the higher levels of the framework.
	 */
	private MediaType validateAndExtractMimeType(MediaTypeOperations marshalOps, MediaType mediaTypeToTest, MediaType urlMediaType, boolean useDefaults)
	{
		if (marshalOps == null)
		{
			// the best we can do in this situation is to use the mediaTypeToTest or urlMediaType without
			// any further checks.
			return (mediaTypeToTest == null) ? urlMediaType : mediaTypeToTest;
		}
		
		// Check and validate the media type according to the marshal operations
		if (!marshalOps.isSupported(mediaTypeToTest)) // Not supported => try the URL postfix
		{
			// Check if we have a URL media type 
			if (urlPostfixMimeType != null)
			{
				if (marshalOps.isSupported(urlMediaType))
    			{
					return urlMediaType;
    			}
			}
			
			// We have no or an unsupported media type
			return (useDefaults) ? marshalOps.getDefault() : MediaType.APPLICATION_XML_TYPE;
		}
		else // supported but it might be a wildcard. In this case we take either the default or the url media type if available
		{
			if (mediaTypeToTest.isWildcardSubtype())
			{
				return (urlPostfixMimeType != null) ?  urlPostfixMimeType : marshalOps.getDefault();
			}
			else
			{
				return mediaTypeToTest;
			}
		}		
	}
	
	private void extractQueryParameters()
	{
		setQueryParameters(new URLQueryParameter(getUriInfo().getQueryParameters()));
	}

	/*--------------------------------------------------*/
	/*-- Private Methods related to response creation --*/
	/*--------------------------------------------------*/
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
	
	private Response makeFullResponse(Object data, int status, PagingInfo pagingInfo, boolean isError, ResponseAction responseAction, ResponseParameters customResponseParams, MarshalFactory marshaller)
	{
		ResponseBuilder response = null;
		HeaderProperties allHeaders = ((customResponseParams != null) && (customResponseParams.getHttpHeaderParams() != null)) ? customResponseParams.getHttpHeaderParams() : new HeaderProperties();   
		try
		{
			// Special case to avoid infinite loop: We deal with an error and the Status Code is of UNSUPPORTED_MEDIA_TYPE. This means we attempted
			// to response with a media type that is not supported, so we cannot return an Error Message Object because the marshaling will fail due
			// to the fact that the media type is not supported. All we can do is return a status code only.
			if (isError && (status == Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode()))
			{
				response = Response.status(Status.UNSUPPORTED_MEDIA_TYPE);
				
				// Set the media types that are accepted by this object provider. Note this may not be the 'marshaller' given to this method since 
				// it could be the 'infrastructure' data model in case of an error! The getMarshaller() method is the one that gives access to
				// the actual data model marshaller and this is the one to use in setting the accepted media types.
				Set<MediaType> mediaTypes = getMarshaller().getSupportedMediaTypes();
				for (Iterator<MediaType> iter = mediaTypes.iterator(); iter.hasNext();)
				{
					response = response.header(HttpHeaders.ACCEPT, iter.next());					
				}
			}
			else // ok we do not deal with a unsupported media type.
			{
				if (data != null)
				{
					// We may deal with an error here. We must ensure that the media type for the response is valid.
					MediaType finalMediaType = getResponseMediaType();
					if (isError)// deal with the error => marshaller is the infrastructure marshaller. 
					{
						// If the client wants a media type that is not ok with the infra structure marshaller then the best we can do,
						// and hope the client can recover, is to use the marshaller's default media type.
						finalMediaType = (marshaller.isSupported(finalMediaType)) ?  finalMediaType : marshaller.getDefault();
					}
					String payload = marshaller.marshal(data, finalMediaType);
					//System.out.println("==============================\nRespopnse Payload:\n"+payload+"\nSize String= "+payload.length()+"\nSize Bytes = "+payload.getBytes("UTF-8").length+"\n==============================");				
					
					//May want to reconsider if we really care about the content length...
//                    byte[] content = (getProviderEnvironment().getCharsetEncoding() != null) ? payload.getBytes(getProviderEnvironment().getCharsetEncoding()) : payload.getBytes();
//                    response = Response.status(status).entity(content);
//                    allHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_CONTENT_LENGTH, String.valueOf(content.length));

					response = Response.status(status).entity(payload);
					
                    allHeaders.setHeaderProperty(HttpHeaders.CONTENT_TYPE, finalMediaType.toString());
				}
				else // we have no data
				{
				    // For HEAD responses we may have an error code but would not have a body. We must return the error code
				    if (isError)
				    {
				        response = Response.status(status);
				    }
				    else // we don't deal with an error.
				    {
    				    // Special case where we have a query that doesn't return any results.
    					if ((responseAction != null) && (responseAction == ResponseAction.QUERY) && (status != Status.ACCEPTED.getStatusCode()))
    					{
    						// We had a query that returned no data. According to SIF 3.x this must return status of 204 (No Content)
    						response = Response.status(Status.NO_CONTENT);
    					}
    					else
    					{
    						response = Response.status(status);
    					}
				    }
				}
			}
			
			// Date & Time format must be: YYYY-MM-DDTHH:mm:ssZ (i.e. 2013-08-12T12:13:14Z)
            allHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_DATE_TIME, DateUtils.nowAsISO8601());
            allHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_PROVIDER_ID, getProviderID());
            allHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_MESSAGE_TYPE, (isError) ? HeaderValues.MessageType.ERROR.name() : HeaderValues.MessageType.RESPONSE.name());
            allHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_RESPONSE_ACTION, responseAction.name());
            allHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_REL_SERVICE_PATH, getRelativeServicePath());
            allHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_SERVICE_TYPE, getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE));

			if (pagingInfo != null)
			{
		        Map<String, String> responseParameters = pagingInfo.getResponseValues();
		        for (String key : responseParameters.keySet())
		        {
		            allHeaders.setHeaderProperty(key, responseParameters.get(key));
		        }
			}

			// Mirror requestId if available
			String requestID = getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_REQUEST_ID);
			if (requestID != null)
			{
                allHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_REQUEST_ID, requestID);
			}
			else // ensure that it is removed as it might be set by the customHeaders and we do not want some faked requestIDs
			{
			    allHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_REQUEST_ID, null);
			}
			
			// Only for direct environments we return the environmentURL. In brokered environments it is done by the broker.
			if (isDirectEnvironment())
			{
				// Only if we have a session then we can return a environmentURI otherwise we cannot determine
				// the environmentID for the given client.
				SIF3Session sif3Session = getSIF3SessionForRequest();
				if (sif3Session != null)
				{
					ProviderEnvironment envInfo = (ProviderEnvironment)getEnvironmentManager().getEnvironmentInfo();
					String baseURIStr = isSecure() ? envInfo.getSecureConnectorBaseURI().toString() : envInfo.getConnectorBaseURI().toString();
					StringBuilder envURLStr = new StringBuilder(baseURIStr).append("/environments/").append(sif3Session.getEnvironmentID());
					allHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_ENVIRONMENT_URI, envURLStr.toString());
//					if (sif3Session.getFingerprint() != null)
//					{
					    allHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_FINGERPRINT, sif3Session.getFingerprint());
//					}
				}
			}
			else // ensure that environmentURI header is not set by customHeaders and fakes some back door.
			{
			    allHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_ENVIRONMENT_URI, null);
			}
			
			// Set all HTTP Headers
			for (String headerName : allHeaders.getHeaderProperties().keySet())
			{
			    response = response.header(headerName, allHeaders.getHeaderProperty(headerName));
			}
			
			return response.build();		
		}
		catch (MarshalException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to marshal "+data.getClass().getSimpleName()+": "+ex.getMessage()), responseAction, customResponseParams);
		}
		catch (UnsupportedMediaTypeExcpetion ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Failed to marshal "+data.getClass().getSimpleName()+" into unsupported media type '"+getResponseMediaType()+"'."), responseAction, customResponseParams);
		}
//        catch (UnsupportedEncodingException ex)
//        {
//            return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Failed to marshal "+data.getClass().getSimpleName()+" into unsupported media type encoding '"+getResponseMediaType()+"'."), responseAction);
//        }
	}
		
	/*----------------------------------------------------------*/
	/*-- Private Methods related to authentication & security --*/
	/*----------------------------------------------------------*/
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
		String authToken = getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN);
		if (StringUtils.notEmpty(authToken))
		{
			setAuthInfo(AuthenticationUtils.getPartsFromAuthToken(authToken));
		}
		else if (getProviderEnvironment().getAllowAuthOnURL()) 	// Ok no header info. Do we have something on the query parameters and is it allowed to be on query parameter
		{
		  setAuthInfo(getAccessToken());
		}
	}
	
	/*
	 * This method checks if the given authentication token and method matches the values set in the session. 
	 * The authentication token from the request header is already split into its component and is used to 
	 * compare against the values passed to this method. If all checks pass successfully then null is 
	 * returned otherwise and ErrorDetails record is returned that holds appropriate error  information to 
	 * be sent back to the client. 
	 * 
	 * @param sif3Session The SIF3 Session against which the given authentication token shall be validated.
	 * @param validateBearer TRUE: Bearer Token must be validated against security service. FALSE validation required. This assumes that the
	 *                       caller of this method has already validated the bearer token.
	 * 
	 * @return See desc.
	 */
	private ErrorDetails validateAuthTokenWithSession(SIF3Session sif3Session, boolean validateBearerToken)
	{
		if (getAuthInfo().getAuthMethod() == null)
		{
			return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "Choose between Basic, SIF_HMACSHA256 or Bearer as Authentication Method. Refer to SIF3 Specification for details.");
		}

		// Check if the Authentication Method matches the session's mandated authentication method.
		if (getAuthInfo().getAuthMethod() != sif3Session.getAuthenticationMethod())
		{
            return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "Invalid authentication method. Authentication method must be "+sif3Session.getAuthenticationMethod().name());	    
		}
		
		if (getAuthInfo().getAuthMethod() != AuthenticationInfo.AuthenticationMethod.Bearer) // It is Basic or SIF_HMACSHA256
		{
			return validateNoneBearerAuthToken(getAuthInfo().getUserToken(), sif3Session.getPassword());
		}
		else // we must validate session against bearer token.
		{
			if (validateBearerToken)
			{
				try
				{
					if (!validateBearerWithSecurityService(getAuthInfo().getUserToken()))
					{
						return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "Bearer Token validation with security service returned \"Not Authorized\".");
					}
				}
				catch (VerifyError ex)
				{
					return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, ex.getMessage());
				}
			}
			
			// If we get here we assume that the bearer token is all valid. now we can validate the session
			// against it.
			return validateBearerTokenAgainstSession(getAuthInfo().getUserToken(), sif3Session);
		}
	}
	
	/*
	 * This method deals specifically with the case where the authentication method is 'Bearer'. 
	 * The algorithm in this case is reasonably complex and roughly follows the algorithm below:
	 * 
	 * First we check if there is a sif3 session in the session cache that matches the security token.
	 * - If there is none then we attempt to get it from the workstore (DB). 
	 * - If there is a sif3 session now then 
	 *      - If not expired all is fine and we are done.
	 *      - If expired then we must do the following:
	 *           - Get latest token info from security server and see if there is a new expire date and if the token
	 *             is still for the same session.
	 *           - If it is for the same session and has a future new expire date => Update cache and workstore (DB)
	 *           - If it is for the same session but old expire date => return error and remove from session cache
	 *           - If it is for a different session then we have to ??? (not sure what to do here yet)
	 * - If there is no session then we assume that there is no environment associated with the given security 
	 *   token. Now we do the following:
	 *      - Check if there is an already existing session available for the securityToken. We need to access the
	 *        DB for that.
	 *        - if there is one then update the DB with the latest securityToken info and we are done 
	 *        - if there isn't then
	 *           - If we have autoCreate = true (i.e. automatically create environment)
	 *             - Validate security token with security server.
	 *             - if valid then
	 *                - Get latest token info from security server
	 *                - At this point we should get some environment key information from the token and 
	 *                  use it to create environment
	 *             - if not valid => return error (not authorised)
	 *           - If autoCreate = false then we return error (not authorised)
	 * 
	 * At the end of this method we either have returned an error or a sif3 session is now in the
	 * workstore (DB) AND the cache.
	 * 
	 * @param autoCreateAllowed There are times where a environment must not be created automatically even if the 
	 *                          provider's property would allow this. A typical case is where we attempt to retrieve
	 *                          an environment by its ID. In such case we do not create it if it doesn't exist.
	 */
	private ErrorDetails validateBearerSession(AuthenticationInfo authInfo, boolean autoCreateAllowed)
	{
		String errorStr = null;
		SIF3Session sif3Session = getSIF3SessionForRequest();
		if (sif3Session == null) // not in cached session store, yet. => Attempt to load it from workstore
		{
			if (isBrokeredEnvironment())
			{
				// In a brokered environment the provider environment manager must really have the session already loaded for 
				// the provider, otherwise we may have a real problem and things are in an inconsistent state. We should return 
				// an error as it appears a request with an invalid sessionToken tries to access the provider.
				errorStr = "Provider's security token doesn't match the providers request's sif3 session, or the provider has no session initialised.";
				logger.error(errorStr+" See previous error log entries for details.");
				return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, errorStr);								
			}
			else
			{	
				// In a direct environment the session for the given session token might not yet be loaded in the environment provider. 
				// In this case we attempt to load it and then compare if everything is fine.
				DirectProviderEnvironmentManager envMgr = ((DirectProviderEnvironmentManager)getEnvironmentManager());
			    try
			    {
			    	TokenInfo tokenInfo = new TokenInfo(authInfo.getUserToken());
			    	EnvironmentType environment = envMgr.reloadEnvironmentForSecurityToken(tokenInfo, isSecure());
			    	
			    	// If there is no environment then there are two potential reasons:
			    	// 1) It really does not exist
			    	// 2) There is an environment but for a different bearer token because a previous one has expired or was
			    	//    re-generated! To cover this case we must get the info for the token and then attempt to reload it that
			    	//    way. Only if it still doesn't exist we can say for sure that there is no environment for the bearer token.
			    	if (environment == null)
			    	{
			    	    logger.debug("No envionment found yet => Attempt get bearer token info and reload environment from there...");
                        tokenInfo = getBearerTokenInfo(authInfo);
                        ErrorDetails errors = createOrLoadEnvByTokenInfo(tokenInfo, envMgr, (autoCreateAllowed && getProviderEnvironment().getAutoCreateEnvironment()));
                        if (errors != null)
                        {
                            return errors;
                        }
			    	}
			    }
			    catch (VerifyError ex)
			    {
			    	logger.error("Bearer Token security issue: "+ ex.getMessage(), ex);
			    	return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, ex.getMessage());
			    }	    
				catch (Exception ex)
			    {
					errorStr ="Error accessing environment store. Failed to retrieve environment for security token = "+authInfo.getUserToken()+": "+ ex.getMessage();
			    	logger.error(errorStr, ex);
			    	return new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Error accessing environment store: "+ ex.getMessage(), errorStr);
			    }
			}
		}
		
		// At this point there should be a sif3 session otherwise an error would already be returned. Also the
		// security token is validated.
		sif3Session = getSIF3SessionForRequest();
		
		// Just in case and for robustness we check the session anyway. Wrong configurations may have unexpected
		// side effects and we do not want exceptions being propagated to the client.
		if (sif3Session == null)
		{
			return new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Error accessing client session.", "There is a problem with the session or environment for the client. Contact your system administrator.");
		}
		
		// Validate the token expire date against the session. If an environment was created further up the
		// it won't be expired at this point. But if we had an existing environment/session it may have
		// expired since it was last accessed.
		if (isDirectEnvironment())
		{
			if (isSessionBearerExpired(sif3Session)) //is token expired
			{
				try
				{
					TokenInfo tokenInfo = getBearerTokenInfo(authInfo);
					
					// Now this should have potential expire date
					if (tokenInfo.getTokenExpiryDate() != null)
					{
						DirectProviderEnvironmentManager envMgr = ((DirectProviderEnvironmentManager)getEnvironmentManager());

						long now = (new Date()).getTime();
					    if (tokenInfo.getTokenExpiryDate().getTime() >= now) // future date => update session
					    {					    	
					    	//Update sif3Session in cache and workstore.
					    	envMgr.updateSessionSecurityInfo(sif3Session.getSessionToken(), tokenInfo.getToken(), tokenInfo.getTokenExpiryDate());
					    }
					    else // expired token => remove from session cache
					    {
					    	envMgr.removeEnvironmentBySessionToken(sif3Session.getSessionToken(), false);

					    	// Report error.
					    	errorStr = "Bearer Token = "+authInfo.getUserToken()+" is expired.";
							logger.error(errorStr);
						    return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, errorStr);					    	
					    }
					}
				}
				catch (VerifyError ex)
				{
					logger.error("Bearer Token security issue: "+ ex.getMessage(), ex);
				    return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, ex.getMessage());
				}
			}
		}
		else // Brokered environment => Bearer Authentication not yet supported.
		{
		    //TODO: JH - Add code once External Security is enabled for brokered providers.
			errorStr = "Bearer Token security not yet supported for Brokered Environment Provider.";
			logger.error(errorStr);
		    return new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), errorStr);
		}
		
        // At this point we should have a SIF3Session which we can check to see if Authentication Method matches the Authorisation header
        // Check if the Authentication Method matches the session's mandated authentication method.
        if (getAuthInfo().getAuthMethod() != sif3Session.getAuthenticationMethod())
        {
            return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "Invalid authentication method. Authentication method must be "+sif3Session.getAuthenticationMethod().name());     
        }
	
		// If we get to this point then all validations have succeeded.
		return null;
	}

	/*
	 * This method validates a security token (Bearer) against a SIF3 session. While doing so it will
	 * NOT attempt to load a session from the session store. It is assumed that the sif3 session given to 
	 * this method is retrieved from the session cache and therefore exists. It will only validate against 
	 * the given sif3 session. It follows the steps below:
	 * - Check if the security token in the session matches the one in the tokenInfo (just in case!). 
	 * - If it doesn't match then we have a real issue. Something is inconsistent => return error (not authorised)
	 * - If it does and it is not expired in session => All fine
	 * - If it does but expired => return error
	 */
	private ErrorDetails validateBearerTokenAgainstSession(String securityToken, SIF3Session sif3Session)
	{
		String errorStr = null;
		if (sif3Session != null)
		{
			if (StringUtils.notEmpty(sif3Session.getSecurityToken()))
			{
				if (sif3Session.getSecurityToken().equals(securityToken))
				{
					// If we have expire date then we must check if it is still valid.
					if (sif3Session.getSecurityTokenExpiry() != null) // check if it is expired
					{
						long now = (new Date()).getTime();
						if (sif3Session.getSecurityTokenExpiry().getTime() <= now)
						{
							errorStr = "Security token in session cache is expired.";	
						}
					}
				}
				else // should not really happen!
				{
					errorStr = "Cached session's security token doesn't match given security token. Internal error.";
				}
			}
			else
			{
				errorStr = "Cached session has no associated security token. Internal error.";
			}
		}
		else
		{
			errorStr = "No valid session exits in the session cache. Internal error.";
		}
		
		// Return appropriate values to caller.
		if (errorStr != null)
		{
			logger.error(errorStr);
			return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, errorStr);
		}
		else // all good...
		{
			return null;
		}
	}
	
	protected boolean isSessionBearerExpired(SIF3Session sif3Session)
	{
	  if (StringUtils.notEmpty(sif3Session.getSecurityToken()))
	  {
	    if (sif3Session.getSecurityTokenExpiry() != null)
	    {
	      long now = (new Date()).getTime();
	      return sif3Session.getSecurityTokenExpiry().getTime() < now;
	    }
	    else // not expired since no expire date is set
	    {
	      return false;
	    }
	  }
	  else // No security Token given => Not valid
	  {
	    logger.debug("sif3Session has no security token associated => assume expired and return true");
	    return true;
	  }
	}

	/*
	 * This method attempts to retrieve the URL Query Parameter called 'accessToken' which may be used for SIF Simple. It will then
	 * attempt to also get the authentication method from the URL Query Parameters. If it is provided it will be used, assuming it has a valid
	 * value, otherwise a lookup to the provider's config file will be performed to determine the default accessToken authentication method. 
	 * If this is set then this will be used otherwise it is defaulted to 'Bearer'.
	 */
	private AuthenticationInfo getAccessToken()
	{
	    String accessTokenStr = getQueryParameters().getQueryParam(CommonConstants.ACCESS_TOKEN);

	    if (StringUtils.notEmpty(accessTokenStr))
	    {
	    	// Check if we have an Authentication Method as well
		    String authMethodStr = getQueryParameters().getQueryParam(CommonConstants.AUTH_METHOD);
		    AuthenticationMethod authMethod = null;
		    
		    if (StringUtils.notEmpty(authMethodStr))
		    {
		        try
		        {
		          authMethod = AuthenticationMethod.valueOf(authMethodStr.trim());
		        }
		        catch (Exception ex) // invalid value is provided. Ignore it!
		        {
		        	authMethod = null; // this will ensure that we use what is in the provider's config file
		        }
	    	}

	    	if (authMethod == null) // check what is in the provider's config file
	    	{
	    		authMethod = getProviderEnvironment().getAccessTokenAuthMethod();
	    	}
	    	
		    // Create a string that looks like if it is in the Authorization HTTP Header
		    return AuthenticationUtils.getPartsFromAuthToken(authMethod.name()+" "+accessTokenStr.trim());
	    }
	    return null;
	}

	private RequestType extractRequestType()
	{
		String requestType = getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_REQUEST_TYPE);
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
			if (logger.isDebugEnabled())
			{
				logger.debug("Request header '"+RequestHeaderConstants.HDR_REQUEST_TYPE+"' not set. Assume IMMEDIATE");
			}
			return RequestType.IMMEDIATE;
		}
	}
	
	private String getTimestampFromRequest()
	{
		// Try to get it from HTTP Header (standard behaviour)
		String timestampStr = getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_DATE_TIME);
		
		// If it is null we may have it as a URL Query Parameter
		if (timestampStr == null)
		{
			timestampStr = getQueryParameters().getQueryParam(CommonConstants.ISO8601_TIMESTAMP);
		}
		
		return timestampStr;
	}
	
	/*
	 * This method will either create a environment or load an exiting environment based on the information
	 * provided in the 'tokenInfo' parameter. It will do this through the EnvironmentManger to ensure that
	 * the loaded environment is also added properly to the session cache. 
	 * This method must only be used if the authentication type is 'Bearer'. It must be checked before calling
	 * this method.
	 * 
	 * The algorithm used to determine if an existing environment shall be loaded or a new one be created is as 
	 * followed:
	 * - If tokenInfo.sessionToken is set then load that session and return null
	 * - if tokenInfo.environmentID is set then load that session and return null
	 * - If all the above failed then lookup the tokenInfo.appUserInfo info.
	 *   - If appUserInfo is available then load environment for the appUserInfo.
	 *     - If none is found = > create new environment with data from tokenInfo and return null
	 *     - If one is found all is good and we don't need to create one and return null
	 *   - appUserInfo is not available => log error and return ErrorDetails 
	 */
	private ErrorDetails createOrLoadEnvByTokenInfo(TokenInfo tokenInfo, DirectProviderEnvironmentManager envMgr, boolean allowCreate)
	{
		if (tokenInfo == null) // should not be the case but for robustness...
		{
			return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "No Bearer Security Token available (token info is null)");
		}
		try
		{
			EnvironmentType environment = null;
			if (StringUtils.notEmpty(tokenInfo.getSessionToken()))
			{
				environment = envMgr.reloadEnvironmentBySessionToken(tokenInfo.getSessionToken(), tokenInfo, isSecure());
				if (environment == null) // that is a problem.
				{
					return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "Secuirty token = "+tokenInfo.getToken()+" is associated with sessionToken = "+tokenInfo.getSessionToken()+". No such environment exits.");					
				}		
			}
			else if (StringUtils.notEmpty(tokenInfo.getEnvironmentID()))
			{
				environment = envMgr.getEnvironmentByEnvironmentID(tokenInfo.getEnvironmentID(), tokenInfo, isSecure());
				if (environment == null) // that is a problem.
				{
					return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "Secuirty token = "+tokenInfo.getToken()+" is associated with environmentID = "+tokenInfo.getEnvironmentID()+". No such environment exits.");					
				}		
			}
			else // Use application Info
			{
				// First check if we have at least the application key
				if (tokenInfo.getAppUserInfo() == null)
				{
					return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, "No environment data available to create environment (Application/User info is null)");
				}
				
				// Attempt to load the environment.
				environment = envMgr.getEnvironmentByEnvKey(tokenInfo.getAppUserInfo(), tokenInfo, isSecure());
				if (environment == null) // try to create it
				{
				    if (allowCreate) 
				    {
                        logger.debug("Attempt to automatically create environment for security token: "+authInfo.getUserToken());
                        EnvironmentType inputEnvironment = makeEnvironmentForBearerToken(null, tokenInfo);
                        environment = envMgr.createOrUpdateEnvironment(inputEnvironment, tokenInfo, isSecure());
                        if (environment == null) // failed to create environment
                        {
                            return new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create environment for '"+tokenInfo.getAppUserInfo()+"' for consumer '"+tokenInfo.getConsumerName()+"'.", "Internal System error. Please contact your system administrator.");
                        }
				    }
				    else // don't create environment automatically
				    {
	                    String errorStr = "No environment exits for the given security token = "+authInfo.getUserToken()+". Ensure that environment is created first.";
	                    logger.error(errorStr);
	                    return new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), NOT_AUTHORIZED, errorStr);
				    }
				}
			}
		}
		catch (Exception ex)
		{
			return new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve or create an environment for security token = "+tokenInfo.getToken(), ex.getMessage());
		}
		
		// If we get here then all validations and operations succeeded. An environment is created (or existed) and
		// is now loaded with its associated session.
		return null;
	}
}
