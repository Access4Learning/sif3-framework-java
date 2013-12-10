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

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import sif3.common.conversion.MarshalFactory;
import sif3.common.exception.MarshalException;
import sif3.common.exception.UnmarshalException;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.header.TransportHeaderProperties;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.EnvironmentStore;
import sif3.infra.common.model.CreateResponseType;
import sif3.infra.common.model.CreateType;
import sif3.infra.common.model.CreatesType;
import sif3.infra.common.model.DeleteIdType;
import sif3.infra.common.model.DeleteRequestType;
import sif3.infra.common.model.DeleteResponseType;
import sif3.infra.common.model.DeleteStatus;
import sif3.infra.common.model.DeleteStatusCollection;
import sif3.infra.common.model.ErrorType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.UpdateResponseType;
import sif3.infra.common.model.UpdateType;
import sif3.infra.common.model.UpdatesType;
import sif3.infra.common.web.WebUtils;
import sif3.infra.rest.env.ProviderEnvironmentManager;
import sif3.infra.rest.header.RESTHeaderProperties;
import sif3.infra.rest.header.RequestHeaderConstants;
import sif3.infra.rest.header.ResponseHeaderConstants;
import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.DateUtils;
import au.com.systemic.framework.utils.PropertyManager;
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
	
	private static final String HTTPS_SCHEMA = "https";

	private PropertyManager propertyManager = PropertyManager.getInstance();
	private boolean allOK = false;

	private UriInfo uriInfo;
	private Request request;
	private MediaType mediaType = MediaType.APPLICATION_XML_TYPE;
	private HttpHeaders requestHeaders;
	private String servicePropFileName;
	private ProviderEnvironmentManager envMgr = null;
	private ObjectFactory infraObjectFactory = new ObjectFactory();
	private InfraMarshalFactory infraMarshaller = new InfraMarshalFactory();
	private InfraUnmarshalFactory infraUnmarshaller = new InfraUnmarshalFactory();
	private TransportHeaderProperties hdrProperties = new RESTHeaderProperties();
	private SIFZone sifZone = null;
	private SIFContext sifContext = null;
	private String serviceName = null;
	private boolean isSecure = false;

	/* Metadata extracted from URI relating to query */ 
	private QueryMetadata queryMetadata;
	
	/**
	 * Constructor. Initialises the REST resource with a number of important properties that are needed throughout the SIF3 REST
	 * resources. If a resource is accessed for the first time then this method will ensure that all environmental components such
	 * as the environment store, property files etc are initialised as necessary.
	 * 
	 * @param uriInfo The URI of the request in its original form.
	 * @param requestHeaders All headers of the original request.
	 * @param request The actual request in its raw form.
	 */
	public BaseResource(UriInfo uriInfo, HttpHeaders requestHeaders, Request request, String serviceName)
	{
		this.uriInfo = uriInfo;
		this.request = request;
		this.requestHeaders = requestHeaders;
		this.serviceName = serviceName;
		this.servicePropFileName = WebUtils.getInstance().getServicePropertyFileName();
		extractHeaderProperties(requestHeaders);
		setSecure(HTTPS_SCHEMA.equalsIgnoreCase(getUriInfo().getBaseUri().getScheme()));	

		// initialise the environment store
		envMgr = ProviderEnvironmentManager.getInstance(EnvironmentStore.getInstance(servicePropFileName));
		
		// Check if we need to initialise the service properties. This should only happens once.
		if (StringUtils.notEmpty(servicePropFileName))
		{
			if (!propertyManager.isLoaded(servicePropFileName))
			{
				logger.debug("Load Service Property File: " + this.servicePropFileName + ".properties.");
				propertyManager.loadPropertyFile(servicePropFileName);
			}
			allOK = true;
		}
		else
		{
			logger.error("Can not retrieve the Servcie Property File name from web.xml");
			allOK = false;
		}

		// Some debug output
		if (logger.isDebugEnabled())
		{
			logger.debug("Requested Resource: " + getClass().getSimpleName()+" - "+getServiceName());
			logger.debug("Full URI          : " + getUriInfo().getRequestUri().toString());
			logger.debug("Base URI          : " + getUriInfo().getBaseUri().toString());
			logger.debug("Relative URI      : " + getUriInfo().getPath());
			logger.debug("Protocol          : " + getUriInfo().getBaseUri().getScheme());
			logger.debug("SIF3 Req. Headers : " + getHeaderProperties());
			logger.debug("Request Media Type: " + getMediaType());
			logger.debug("Resource Init ok  : " + allOK);
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
		return propertyManager.getProperties(servicePropFileName);
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public boolean isInitialised()
	{
		return allOK;
	}

	public TransportHeaderProperties getHeaderProperties()
    {
    	return this.hdrProperties;
    }

	public MediaType getMediaType()
    {
    	return this.mediaType;
    }

	public HttpHeaders getRequestHeaders()
    {
    	return this.requestHeaders;
    }
	
	public String getServicePropFileName()
    {
    	return this.servicePropFileName;
    }

	public QueryMetadata getQueryMetadata()
    {
    	return this.queryMetadata;
    }

	public void setQueryMetadata(QueryMetadata queryMetadata)
    {
    	this.queryMetadata = queryMetadata;
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

	/*--------------------------------*/
	/*-- Some handy Utility Methods --*/
	/*--------------------------------*/
	/**
	 * If the client is valid then the ErrorDetails returned is null. If there is something wrong, then the ErrorDetails will be 
	 * set accordingly and returned.
	 * 
	 * @return See desc
	 */
	public ErrorDetails validClient()
	{
		ErrorDetails error = null;
		// we must have a authentication token and there must be an environment with that authentication token
		String authToken = getAuthToken();
		
		if (authToken != null)
		{
			if (!envMgr.existsEnvironmentForAuthToken(authToken))
			{
				error = new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Not authorized. You can only access your own environment", "Environment may need to be created first for this client.");									
			}
		}
		else
		{
			error = new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Authorization Token must be provided");				
		}
		return error;
	}
	
	public String getAuthToken()
	{
		return getHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN);
	}
	
	public String getProviderID()
	{
		return envMgr.getEnvironmentStore().getEnvironments().getAdapterName();
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
	 */
	public List<String> getResourceIDsFromDeleteRequest(String deletePayload) throws UnmarshalException
	{
    List<String> resourceIDs = new ArrayList<String>();
    if (deletePayload != null)
    {
      DeleteRequestType deletes = (DeleteRequestType)infraUnmarshaller.unmarschal(deletePayload, DeleteRequestType.class, getMediaType());
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
		response = response.header(ResponseHeaderConstants.HDR_MESSAGE_TYPE, (isError) ? HeaderValues.ResponseType.ERROR.name() : HeaderValues.ResponseType.RESPONSE.name());					
		response = response.header(ResponseHeaderConstants.HDR_RESPONSE_ACTION, responseAction.name());
		response = response.header(ResponseHeaderConstants.HDR_SERVICE_NAME, getServiceName());

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
	public Response makeCreateMultipleResponse(List<OperationStatus> operationStatusList, Status overallStatus)
	{
		CreateResponseType createManyResponse = infraObjectFactory.createCreateResponseType();
		createManyResponse.setCreates(new CreatesType());
		List<CreateType> creates = createManyResponse.getCreates().getCreate();
		
		for (OperationStatus opStatus : operationStatusList)
		{
			CreateType createType = new CreateType();
			createType.setId(opStatus.getResourceID());
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

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private void extractHeaderProperties(HttpHeaders requestHeaders)
	{
		hdrProperties = new RESTHeaderProperties(); //ensure it is clean, ie. not holding values from a previous call.
		for (String hdrName : RequestHeaderConstants.HEADER_NAME_ARRAY)
		{
			String hdrValue = requestHeaders.getRequestHeaders().getFirst(hdrName);
			if (StringUtils.notEmpty(hdrValue))
			{
				hdrProperties.setHeaderProperty(hdrName, hdrValue);
			}
		}
		
		// Also try to get the media type
		String mediaTypeStr = requestHeaders.getRequestHeaders().getFirst("Content-Type");
		try
		{
			mediaType = MediaType.valueOf(mediaTypeStr);
		}
		catch (Exception ex)
		{
			logger.error("Failed to convert media type '"+mediaTypeStr+"' from request header (Content-Type) to MediaType class. Default to APPLICATION_XML");
			mediaType = MediaType.APPLICATION_XML_TYPE;
		}		
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
			if (data != null)
			{
				String payload = marshaller.marschal(data, getMediaType());
				response = Response.status(status).entity(payload);
				response = response.header(ResponseHeaderConstants.HDR_CONTENT_LENGTH, payload.length());
			}
			else
			{
				response = Response.status(Status.NO_CONTENT);
			}
			
			// Date & Time format must be: YYYY-MM-DDTHH:mm:ssZ (i.e. 2013-08-12T12:13:14Z)
			response = response.header(ResponseHeaderConstants.HDR_DATE_TIME, DateUtils.nowAsISO8601());
			response = response.header(ResponseHeaderConstants.HDR_PROVIDER_ID, getProviderID());
			response = response.header(ResponseHeaderConstants.HDR_MESSAGE_TYPE, (isError) ? HeaderValues.ResponseType.ERROR.name() : HeaderValues.ResponseType.RESPONSE.name());					
			response = response.header(ResponseHeaderConstants.HDR_RESPONSE_ACTION, responseAction.name());
			response = response.header(ResponseHeaderConstants.HDR_SERVICE_NAME, getServiceName());
			if (pagingInfo != null)
			{
				response = response.header(ResponseHeaderConstants.HDR_LAST_PAGE_NO, pagingInfo.getMaxPages());
				response = response.header(ResponseHeaderConstants.HDR_PAGE_NO, pagingInfo.getCurrentPageNo());
				response = response.header(ResponseHeaderConstants.HDR_PAGE_SIZE, pagingInfo.getPageSize());
				response = response.header(ResponseHeaderConstants.HDR_TOTAL_ITEMS, pagingInfo.getTotalObjects());
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
	}

}
