/*
 * BaseClient.java
 * Created: 20/03/2014
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

import java.awt.TrayIcon.MessageType;
import java.net.URI;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.header.HeaderProperties;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.header.ResponseHeaderConstants;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.URLQueryParameter;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.BaseResponse;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.model.ErrorType;
import sif3.infra.common.model.ObjectFactory;
import au.com.systemic.framework.utils.DateUtils;
import au.com.systemic.framework.utils.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;

/**
 * This class is a core client class to deal with REST clients for SIF3. It takes care of all the little things that define the
 * SIF3 REST transport for all the operations stated in the SIF3 spec. It abstracts the finer details on how a URL is constructed,
 * what header fields are required on the request as well as the response. It takes all of this internal processing and SIF3 
 * infrastructure management away from the higher levels of this framework.<br/><br/>
 * 
 * Each actual client interface implementation must extends this class to ensure that all the things that make a SIF3 REST call valid 
 * are guaranteed.<br/><br/>
 * 
 * Note:<br/>
 * This class is the REST Client Implementation.
 *
 * @author Joerg Huber
 */
public abstract class BaseClient
{
	protected final Logger logger = Logger.getLogger(getClass());

	private URI baseURI = null;
	private ClientConfig config = null;
	private Client client = null;
	private WebResource service = null;
//	private MediaType mediaType = MediaType.APPLICATION_XML_TYPE;
	private MediaType requestMediaType = MediaType.APPLICATION_XML_TYPE;
	private MediaType responseMediaType = MediaType.APPLICATION_XML_TYPE;
	private MarshalFactory dmMarshaller = null;
	private UnmarshalFactory dmUnmarshaller = null;
	
	// This marshaller/unmarshaller are used in a number of operations where the response/request deals with Infrastructure Model Object: ErrorType, CreateResponseType etc.
	private InfraUnmarshalFactory infraUnmarshaller = new InfraUnmarshalFactory();
	private InfraMarshalFactory infraMarshaller = new InfraMarshalFactory();
	private ObjectFactory infraObjectFactory = new ObjectFactory();

	public BaseClient()
	{
		super();
	}
	
	/**
	 * Constructor<br/>
	 * 
	 * @param baseURI The base URI of this client. All URIs are for all other calls are relative to this base URL.
	 * @param requestMediaType Media type of the request. It will be validated against the supported media types of the given dmMarshaller.
	 * @param responseMediaType Media type of the response. It will be validated against the supported media types of the given dmUnmarshaller.
	 * @param dmMarshaller Marshaller to marshal the payload of this client to appropriate representations. This marshaller must be valid
	 *                   for the data model used with this client.
	 * @param dmUnmarshaller Unmarshaller to unmarshal the payload of this client to appropriate representations. This unmarshaller 
	 *                     must be valid for the data model used with this client.
	 * @param secureConnection TRUE: Use HTTPS, FALSE use HTTP.
	 */
	public BaseClient(URI baseURI, MediaType requestMediaType, MediaType responseMediaType, MarshalFactory dmMarshaller, UnmarshalFactory dmUnmarshaller, boolean secureConnection)
	{
		super();

		setDataModelMarshaller(dmMarshaller);
		setDataModelUnmarshaller(dmUnmarshaller);
		setRequestMediaType(requestMediaType, dmMarshaller);
		setResponseMediaType(responseMediaType, dmUnmarshaller);
		configureClienService(baseURI, secureConnection);
		
		logger.debug("Base URI for Call is: "+getBaseURI().toASCIIString());
	}

	/**
	 * This constructor will default the media type of the marshaller (request) and unmarshaller (response) and the default service type of OBJECT.
	 * 
	 * @param baseURI The base URI of this client. All URIs are for all other calls are relative to this base URL.
	 * @param dmMarshaller Marshaller to marshal the payload of this client to appropriate representations. This marshaller must be valid
	 *                   for the data model used with this client.
	 * @param dmUmarshaller Unmarshaller to unmarshal the payload of this client to appropriate representations. This unmarshaller 
	 *                     must be valid for the data model used with this client.
	 * @param secureConnection TRUE: Use HTTPS, FALSE use HTTP.
	 */
	public BaseClient(URI baseURI, MarshalFactory dmMarshaller, UnmarshalFactory dmUmarshaller, boolean secureConnection)
	{
		this(baseURI, ((dmMarshaller != null) ? dmMarshaller.getDefault() : null), ((dmUmarshaller != null) ? dmUmarshaller.getDefault() : null), dmMarshaller, dmUmarshaller, secureConnection);
	}

	public MarshalFactory getDataModelMarshaller()
    {
    	return this.dmMarshaller;
    }

	public UnmarshalFactory getDataModelUnmarshaller()
    {
    	return this.dmUnmarshaller;
    }

	public ObjectFactory getInfraObjectFactory()
    {
    	return this.infraObjectFactory;
    }

	public MarshalFactory getInfraMarshaller()
    {
    	return this.infraMarshaller;
    }

	public UnmarshalFactory getInfraUnmarshaller()
    {
    	return this.infraUnmarshaller;
    }

	public URI getBaseURI()
	{
		return baseURI;
	}

	public Client getClient()
	{
		return client;
	}

	public WebResource getService()
	{
		return service;
	}

	public MediaType getRequestMediaType()
	{
		return requestMediaType;
	}

	/*
	 * Sets the media type of the request. If the media type is null it will use the marshaller default media type. If the marhaller is null
	 * as well then the requestMediaType will be set to null.
	 */
	public void setRequestMediaType(MediaType mediaType, MarshalFactory marshaller)
	{
		if ((mediaType == null) && (marshaller != null))
		{
			this.requestMediaType = marshaller.getDefault();
		}
		this.requestMediaType = mediaType;
	}
	
	public MediaType getResponseMediaType()
	{
		return responseMediaType;
	}

	/*
	 * Sets the media type of the response. If the media type is null it will use the unmarshaller default media type. If the unmarshaller is null
	 * as well then the responseMediaType will be set to null.
	 */
	public void setResponseMediaType(MediaType mediaType, UnmarshalFactory unmarshaller)
	{
		if ((mediaType == null) && (unmarshaller != null))
		{
			this.responseMediaType = unmarshaller.getDefault();
		}
		this.responseMediaType = mediaType;
	}

	protected ClientConfig getConfig()
    {
    	return this.config;
    }

	protected void setDataModelUnmarshaller(UnmarshalFactory dmUnmarshaller)
    {
    	this.dmUnmarshaller = dmUnmarshaller;
    }

	protected void setDataModelMarshaller(MarshalFactory dmMarshaller)
    {
    	this.dmMarshaller = dmMarshaller;
    }
	
	protected void configureClienService(URI baseURI, boolean secureConnection)
	{
		createConfig(secureConnection);

		// Set URI which will also create the actual client and service fir this class
		createServiceForURI(baseURI);		
	}
	
	/*-----------------------*/
	/*-- Protected Methods --*/
	/*-----------------------*/
	protected WebResource buildURI(WebResource svc, String relURI, String resourceID, SIFZone zone, SIFContext ctx, URLQueryParameter urlQueryParams)
	{
		UriBuilder uriBuilder = svc.getUriBuilder();
		if (StringUtils.notEmpty(relURI))
		{
			uriBuilder.path(relURI);
		}
		if (StringUtils.notEmpty(resourceID))
		{
			uriBuilder.path(resourceID);
		}
		if ((zone != null) && (StringUtils.notEmpty(zone.getId())))
		{
			uriBuilder.matrixParam("zoneId", zone.getId());
		}
		if ((ctx != null) && (StringUtils.notEmpty(ctx.getId())))
		{
			uriBuilder.matrixParam("contextId", ctx.getId());
		}
		
		//Add custom URL Query Parameters.
		if ((urlQueryParams != null) && (urlQueryParams.getQueryParams() != null))
		{
			for (String paramName : urlQueryParams.getQueryParams().keySet())
			{
				uriBuilder = uriBuilder.queryParam(paramName, urlQueryParams.getQueryParam(paramName));
			}
		}

		return svc.uri(uriBuilder.build());
	}

	protected WebResource buildURI(WebResource svc, String relURI)
	{
		return buildURI(svc, relURI, null, null, null, null);
	}
		
	/**
	 * This method will set the valid/accepted media type for this service. It will then add all header properties given to this
	 * method to the service. It will also add some "default" header properties such MessageID, timestamp if it is not set yet, and
	 * if required a request ID. For it to be a valid SIF3 service it is expected that the authentication token is already part
	 * of the given header properties. It WON'T be added as part of this method.
	 * 
	 * @param service The service to which media type and header properties shall be added.
	 * @param hdrProperties A set of defined header properties. Should really hold the authentication token!
	 * @param includeRequestID TRUE: Add a generated request ID header property
	 * 
	 * @return A builder class on which a HTTP operation can be invoked on.
	 */
	protected Builder setRequestHeaderAndMediaTypes(WebResource service, HeaderProperties hdrProperties, boolean includeRequestID)
	{
//		System.out.println("Client: Request MediaType: "+getRequestMediaType());
//		System.out.println("Client Response MediaType: "+getResponseMediaType());
		
		Builder builder = service.type(getRequestMediaType()).accept(getResponseMediaType());
		
		// Set some specific SIF HTTP header. First ensure that we have a valid header property structure
		if (hdrProperties == null)
		{
			hdrProperties = new HeaderProperties();
		}
		
		// Always set the requestId and messageId.
		hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_MESSAGE_ID, UUIDGenerator.getUUID());
		//builder = builder.header(RequestHeaderConstants.HDR_MESSAGE_ID, UUIDGenerator.getUUID());
		
		// Sometimes the request ID is not required (i.e. events)
		if (includeRequestID)
		{
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_REQUEST_ID, UUIDGenerator.getUUID());
			//builder = builder.header(RequestHeaderConstants.HDR_REQUEST_ID, UUIDGenerator.getUUID());
		}
		
		// timestamp header must be set but only if it is not set, yet. If the authentication method is SIF_HMACSHA256
		// then this property should already be set! Don't override because it is critical to the hash of the authentication token. 
		if (hdrProperties.getHeaderProperty(RequestHeaderConstants.HDR_DATE_TIME) == null) // not set yet
		{
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_DATE_TIME, DateUtils.nowAsISO8601());
			//builder = builder.header(RequestHeaderConstants.HDR_DATE_TIME, DateUtils.nowAsISO8601());
		}

		if (logger.isDebugEnabled())
		{
			logger.debug("Final set of HTTP Headers to be used in request: "+hdrProperties);
		}
		
		if ((hdrProperties != null) && (hdrProperties.getHeaderProperties() != null) && (!hdrProperties.getHeaderProperties().isEmpty()))
		{
			HashMap<String, String> hdrMap = hdrProperties.getHeaderProperties();
			for (String hdrPropertyName : hdrMap.keySet())
			{
				String hdrPropertyValue = hdrMap.get(hdrPropertyName);
				if (StringUtils.notEmpty(hdrPropertyValue))
				{
					builder = builder.header(hdrPropertyName, hdrPropertyValue);
				}
			}
		}

		return builder;
	}
	
	protected HeaderProperties extractHeaderInfo(ClientResponse clientResponse)
	{
//		HeaderProperties hdrProps = new HeaderProperties();
//		MultivaluedMap<String, String> respHdrProps = clientResponse.getHeaders();
//
//		if ((respHdrProps != null))
//		{
//			for (String propName : ResponseHeaderConstants.HEADER_NAME_ARRAY)
//			{
//				String hdrValue = respHdrProps.getFirst(propName); // there should only be one value for each of these properties
//				if (hdrValue != null)
//				{
//					hdrProps.setHeaderProperty(propName, hdrValue);
//				}
//			}
//		}

		HeaderProperties hdrProps = new HeaderProperties();
		MultivaluedMap<String, String> respHdrProps =  clientResponse.getHeaders();
		
		for (String hdrName : respHdrProps.keySet())
		{
			hdrProps.setHeaderProperty(hdrName, respHdrProps.getFirst(hdrName));
		}		
		
		return hdrProps;
	}
	
	protected Response setResponse(WebResource service, ClientResponse clientResponse, Class<?> returnObjectClass, Status... successStatusCodes)
	{
		Response response = new Response();
		setBaseResponseData(response, clientResponse);
		response.setResourceURI(service.getURI());
		response.setDataObjectType(returnObjectClass);

		// Check if HTTP header messageType == ERROR
		boolean isErrorMessageType = MessageType.ERROR.name().equals(response.getHdrProperties().getHeaderProperty(ResponseHeaderConstants.HDR_MESSAGE_TYPE));
		
		if (isSuccessStatusCode(clientResponse.getClientResponseStatus().getStatusCode(), successStatusCodes) && !isErrorMessageType)
		{
			if (response.getHasEntity())
			{
				String payload = clientResponse.getEntity(String.class);
				if (logger.isDebugEnabled())
				{
					logger.debug("Returned Payload:\n"+payload);
				}
				if (returnObjectClass != null)
				{
					// There is a special case. If the 'returnObjectClass' is of type String.class then we don't want any unmarshal being 
					// performed. We just want the raw payload string as it has been received.
					if (returnObjectClass.getSimpleName().equals(String.class.getSimpleName()))
					{
						response.setDataObject(payload);
					}
					else
					{
						try
						{
							// We must use the actual data model response type in the unmarshaller.
							response.setDataObject(getDataModelUnmarshaller().unmarshal(payload, returnObjectClass, getResponseMediaType()));
							if (response.getDataObject() == null)// this is strange. So set the unmarshalled value.
							{
								response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload. See error description for payload details.", payload));
							}
						}
						catch (UnmarshalException ex)
						{
							response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload: "+ex.getMessage()+". See error description for payload details.", payload));
						}
						catch (UnsupportedMediaTypeExcpetion ex)
						{
							response.setError(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal payload (unsupported media type): "+ex.getMessage()+". See error description for payload details.", payload));
						}
					}
				}
				else // Strange. We have an entity but we returnObjectClass is null.
				{
					String errorMsg = "The response has data but the caller did not provide a 'returnObjectClass' type to unmarshal it. Consider this an error. Value stored in 'description' of error response.";
					logger.error(errorMsg);
					response.setError(new ErrorDetails(response.getStatus(), errorMsg, payload));
				}
			}
		}
		else // We are dealing with an error case.
		{
			setErrorResponse(response, clientResponse);
		}
		
		if (logger.isDebugEnabled())
		{
			logger.debug("Response from REST Call:\n"+response);
		}
		return response;
	}

	
	protected void setBaseResponseData(BaseResponse response, ClientResponse clientResponse)
	{
		response.setStatus(clientResponse.getClientResponseStatus().getStatusCode());
		response.setStatusMessage(clientResponse.getClientResponseStatus().getReasonPhrase());
		response.setMediaType(clientResponse.getType());
		response.setContentLength(clientResponse.getLength());

		// Extract header properties.
		response.setHdrProperties(extractHeaderInfo(clientResponse));
		
		if (logger.isDebugEnabled())
		{
			logger.debug("HTTP Headers of Response: "+response.getHdrProperties());
		}
		response.setHasEntity(clientResponse.hasEntity() && (clientResponse.getClientResponseStatus().getStatusCode() != Status.NO_CONTENT.getStatusCode()));		
	}
	
	protected void setErrorResponse(BaseResponse response, ClientResponse clientResponse)
	{
		if (response.getHasEntity()) // Attempt to unmarshal the error
		{
			String errorStr = clientResponse.getEntity(String.class);
			if (logger.isDebugEnabled())
			{
				logger.debug("Returned Error Payload:\n"+errorStr);
			}
			try
			{
				//Because ErrorType is a Infrastructure thing we must ensure we use a valid Infrastructure Unmarshaller Media Type
				ErrorType error = (ErrorType) infraUnmarshaller.unmarshal(errorStr, ErrorType.class, getInfraResponseMediaType(getResponseMediaType()));
				if (error == null) // this is strange. So set the unmarshalled value.
				{
					response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload into ErrorType object. See error description for payload details.", errorStr));
				}
				else
				{
					if (StringUtils.isEmpty(error.getMessage()) && StringUtils.isEmpty(error.getDescription()) && (error.getCode() <= 0))
					{
						// It appears that we could not get a useful error from the entity string for whatever reason. Return something hopefully
						// more useful from the low level response
						response.setError(new ErrorDetails(clientResponse.getStatus(), clientResponse.getClientResponseStatus().getReasonPhrase()));
					}
					else
					{
						response.setError(convertFromErrorType(error));
					}
				}
			}
			catch (UnmarshalException ex)
			{
				response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload into ErrorType object: "+ex.getMessage()+". See error description for payload details.", errorStr));
			}
			catch (UnsupportedMediaTypeExcpetion ex)
			{
				response.setError(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal payload into ErrorType object (unsupported media type): "+ex.getMessage()+". See error description for payload details.", errorStr));
			}
		}
		else // It appears we have an error but no content. So create an error object with custom message.
		{
			response.setError(new ErrorDetails(response.getStatus(), "Error occured: " + response.getStatusMessage(), "No Content in web-service response."));
		}
	}

	/*
	 * This error response is a "faked" error response. It is not generated by the server rather on the client before the web-service
	 * is invoked. The main intent of this method is that a client can perform tests and report errors before an actual invocation to
	 * a web-service method is done.
	 */
	protected BaseResponse createClientErrorResponse(Status status, String description)			
	{
		BaseResponse response = new BaseResponse();
		response.setStatus(status.getStatusCode());
		response.setHasEntity(true); // Error is an entity
		response.setStatusMessage(status.getReasonPhrase());
		response.setError(new ErrorDetails(status.getStatusCode(), status.getReasonPhrase(), description));
		return response;
	}

	
	protected ErrorDetails convertFromErrorType(ErrorType errorType)
	{
		if (errorType == null)
		{
			return null;
		}
		return new ErrorDetails((int)errorType.getCode(), errorType.getMessage(), errorType.getDescription(), errorType.getScope());
	}

	
	protected boolean isSuccessStatusCode(int statusCode, Status... successStatusCodes)
	{
	  if (successStatusCodes != null)
	  {
	    for (Status validStatus : successStatusCodes)
	    {
	      if (statusCode == validStatus.getStatusCode())
	      {
	        return true;
	      }
	    }
	  }
	  
	  return false;
	}
	
	protected int toInt(String intStr)
	{
		try
		{
			return Integer.valueOf(intStr);
		}
		catch (Exception ex)
		{
			return 0;
		}
	}
	/*--------------------------------------------------------------------------------------------------------------*/
	/*-- Private Setters, so that they can only be set at initialisation of constructor but not overridden later. --*/
	/*--------------------------------------------------------------------------------------------------------------*/
	private void createConfig(boolean secureConnection)
	{
		ClientConfigMgr cltCfgMgr = new ClientConfigMgr();		
		this.config = cltCfgMgr.getClientConfig(secureConnection);
	}
	
	private void createServiceForURI(URI baseURI)
	{
		// This is also going to change the service.
		this.baseURI = baseURI;
		
		// Create the Client Service
		this.client = Client.create(config);

		// Retrieve connector to resource
		this.service = client.resource(getBaseURI());
	}

	/*
	 * Determine which media type to use in the unmarshalling of a response. Intended to be used when a response is received that is a
	 * infrastructure message (i.e. Error Response, Environment Response, Queue Response etc.). In this case the response must be unmarshalled
	 * with the infrastructure unmarshaller but the indicatedMediaType is set for a data model object which could be other than XML or JSON.
	 * In this case we must unmarshal the response in the default infrastructure media type or we will get an unmrashal excpetion.
	 */
	private MediaType getInfraResponseMediaType(MediaType indicatedMediaType)
	{
		return getInfraUnmarshaller().isSupported(indicatedMediaType) ? indicatedMediaType : getInfraUnmarshaller().getDefault();
	}
	
}
