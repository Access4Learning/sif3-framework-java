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

import java.net.URI;
import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;

import au.com.systemic.framework.utils.DateUtils;
import au.com.systemic.framework.utils.StringUtils;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.MessageType;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.header.ResponseHeaderConstants;
import sif3.common.model.AuthenticationInfo;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.URLQueryParameter;
import sif3.common.model.delayed.DelayedRequestReceipt;
import sif3.common.model.security.TokenCoreInfo;
import sif3.common.model.security.TokenInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.common.security.AbstractSecurityService;
import sif3.common.security.BearerSecurityFactory;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.BaseResponse;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.interfaces.ClientEnvironmentManager;
import sif3.infra.common.model.ErrorType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.rest.mapper.InfraDataModelMapper;

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
    protected final Logger logger = LoggerFactory.getLogger(getClass());

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
	private boolean useCompression = false;
	private ClientEnvironmentManager clientEnvMgr = null;
	
	private InfraDataModelMapper infraMapper = new InfraDataModelMapper();

	/**
     * Constructor<br/>
	 * 
	 * @param clientEnvMgr This manager is used to update session related information of the client. This is mainly the case were
	 *                     external security services are used and therefore the authorisation header may need to be generated for
	 *                     as session after it has expired. In this case the session information of the client must be updated.
	 */
	public BaseClient(ClientEnvironmentManager clientEnvMgr)
	{
		super();
        setClientEnvMgr(clientEnvMgr);
	}
	
	/**
	 * Constructor<br/>
	 * 
     * @param clientEnvMgr This manager is used to update session related information of the client. This is mainly the case were
     *                     external security services are used and therefore the authorisation header may need to be generated for
     *                     as session after it has expired. In this case the session information of the client must be updated.
	 * @param baseURI The base URI of this client. All URIs are for all other calls are relative to this base URL.
	 * @param requestMediaType Media type of the request. It will be validated against the supported media types of the given dmMarshaller.
	 * @param responseMediaType Media type of the response. It will be validated against the supported media types of the given dmUnmarshaller.
	 * @param dmMarshaller Marshaller to marshal the payload of this client to appropriate representations. This marshaller must be valid
	 *                   for the data model used with this client.
	 * @param dmUnmarshaller Unmarshaller to unmarshal the payload of this client to appropriate representations. This unmarshaller 
	 *                     must be valid for the data model used with this client.
	 * @param secureConnection TRUE: Use HTTPS, FALSE use HTTP.
	 * @param useCompression TRUE: Payloads (request & response) shall be compressed before sending or de-compressed at the
	 *                             time of receiving.
	 *                       FALSE: No compression is used.
	 */
	public BaseClient(ClientEnvironmentManager clientEnvMgr, URI baseURI, MediaType requestMediaType, MediaType responseMediaType, MarshalFactory dmMarshaller, UnmarshalFactory dmUnmarshaller, boolean secureConnection, boolean useCompression)
	{
		super();

		setClientEnvMgr(clientEnvMgr);
		setDataModelMarshaller(dmMarshaller);
		setDataModelUnmarshaller(dmUnmarshaller);
		setRequestMediaType(requestMediaType, dmMarshaller);
		setResponseMediaType(responseMediaType, dmUnmarshaller);
		setUseCompression(useCompression);
		configureClienService(baseURI, secureConnection, getUseCompression());
		
		logger.debug("Base URI for Call is: "+getBaseURI().toASCIIString());
	}

	/**
	 * This constructor will default the media type of the marshaller (request) and unmarshaller (response) and the default service type of OBJECT.
	 * 
     * @param clientEnvMgr This manager is used to update session related information of the client. This is mainly the case were
     *                     external security services are used and therefore the authorisation header may need to be generated for
     *                     as session after it has expired. In this case the session information of the client must be updated.
	 * @param baseURI The base URI of this client. All URIs are for all other calls are relative to this base URL.
	 * @param dmMarshaller Marshaller to marshal the payload of this client to appropriate representations. This marshaller must be valid
	 *                   for the data model used with this client.
	 * @param dmUmarshaller Unmarshaller to unmarshal the payload of this client to appropriate representations. This unmarshaller 
	 *                     must be valid for the data model used with this client.
	 * @param secureConnection TRUE: Use HTTPS, FALSE use HTTP.
	 * @param useCompression TRUE: Payloads (request & response) shall be compressed before sending or de-compressed at the
	 *                             time of receiving.
	 *                       FALSE: No compression is used.
	 */
	public BaseClient(ClientEnvironmentManager clientEnvMgr, URI baseURI, MarshalFactory dmMarshaller, UnmarshalFactory dmUmarshaller, boolean secureConnection, boolean useCompression)
	{
		this(clientEnvMgr, baseURI, ((dmMarshaller != null) ? dmMarshaller.getDefault() : null), ((dmUmarshaller != null) ? dmUmarshaller.getDefault() : null), dmMarshaller, dmUmarshaller, secureConnection, useCompression);
	}
	
	/**
     * @return the clientEnvMgr
     */
    protected ClientEnvironmentManager getClientEnvMgr()
    {
        return clientEnvMgr;
    }
    
    protected SIF3Session getSIF3Session()
    {
        return getClientEnvMgr().getSIF3Session();
    }

	protected InfraDataModelMapper getInfraMapper()
	{
		return infraMapper;
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
	
	protected void configureClienService(URI baseURI, boolean secureConnection, boolean useCompression)
	{
		createConfig(secureConnection);

		// Set URI which will also create the actual client and service fir this class
		createServiceForURI(baseURI, useCompression);		
	}
	
	public boolean getUseCompression()
    {
    	return this.useCompression;
    }

	public void setUseCompression(boolean useCompression)
    {
    	this.useCompression = useCompression;
    }

    /**
     * @param clientEnvMgr the clientEnvMgr to set
     */
    private void setClientEnvMgr(ClientEnvironmentManager clientEnvMgr)
    {
        this.clientEnvMgr = clientEnvMgr;
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
	 * method to the service. Further will also add some "default" header properties such MessageID, timestamp if it is not set yet, and
	 * if required a request ID. For it to be a valid SIF3 service it is expected that the authentication token is already part
	 * of the given header properties. It WON'T be added as part of this method.
	 * 
	 * @param service The service to which media type and header properties shall be added.
	 * @param hdrProperties A set of defined header properties. Should really hold the authentication related properties!
	 * @param requestType The request type to be set in the HTTP headers.
	 * @param includeRequestID TRUE: Add a generated request ID header property
	 * @param includeFingerprint TRUE: Fingerprint will be retrieved from current session. Note for a provider this will be
	 *                                 the provider's fingerprint! This is generally not desired for events. In this case
	 *                                 this parameter should be set to FALSE.
	 *                           FALSE: Fingerprint from the current session will not be added to the HTTP headers.
	 * @hasPayload TRUE: The request will contain a payload. Required for compression header settings
	 *             FALSE: The request is payload free.
	 * 
	 * @return A builder class on which a HTTP operation can be invoked on.
	 */
	protected Builder setRequestHeaderAndMediaTypes(WebResource service, HeaderProperties hdrProperties, RequestType requestType, boolean includeRequestID, boolean includeFingerprint, boolean hasPayload)
	{
	    String charEncoding = getClientEnvMgr().getEnvironmentInfo().getCharsetEncoding();
		Builder builder = service.type(addEncoding(getRequestMediaType(), charEncoding)).accept(addEncoding(getResponseMediaType(), charEncoding));
		
		// Set some specific SIF HTTP header. First ensure that we have a valid header property structure
		if (hdrProperties == null)
		{
			hdrProperties = new HeaderProperties();
		}
		
		// Always set the requestId and messageId.
		hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_MESSAGE_ID, UUIDGenerator.getUUID());
		//builder = builder.header(RequestHeaderConstants.HDR_MESSAGE_ID, UUIDGenerator.getUUID());
		
		// Set the request type.
		hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_REQUEST_TYPE, ((requestType == null) ? RequestType.IMMEDIATE.name() : requestType.name()));
		
		// Add fingerprint to HTTP Header if it is known and not yet set. Note for events this value might already be set, so we
		// should not override it! This should be indicated with the includeFingerprint parameter that would be set to false!
		if (includeFingerprint)
		{
    		if (hdrProperties.getHeaderProperty(RequestHeaderConstants.HDR_FINGERPRINT) == null)
    		{
        		if ((getClientEnvMgr().getSIF3Session() != null) && (getClientEnvMgr().getSIF3Session().getFingerprint() != null))
        		{
        		    hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_FINGERPRINT, getClientEnvMgr().getSIF3Session().getFingerprint());
        		}
    		}
		}
		
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
		
		// Compression related headers
		if (getUseCompression())
		{
			// Request encoding
			if (hasPayload)
			{
				hdrProperties.setHeaderProperty(HttpHeaders.CONTENT_ENCODING, HeaderValues.EncodingType.gzip.name());
			}
			
			//Accepted encodings for response
			hdrProperties.setHeaderProperty(HttpHeaders.ACCEPT_ENCODING, HeaderValues.ACCEPT_ENCODING_ALL);	
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
	
	/**
	 * This method is a convenience method to above. It will default the request type to IMMEDATE otherwise it behaves just like above method.
	 * 
	 * @param service The service to which media type and header properties shall be added.
	 * @param hdrProperties A set of defined header properties. Should really hold the authentication related properties!
	 * @param includeRequestID TRUE: Add a generated request ID header property
     * @param includeFingerprint TRUE: Fingerprint will be retrieved from current session. Note for a provider this will be
     *                                 the provider's fingerprint! This is generally not desired for events. In this case
     *                                 this parameter should be set to FALSE.
     *                           FALSE: Fingerprint from the current session will not be added to the HTTP headers.
	 * @hasPayload TRUE: The request will contain a payload. Required for compression header settings
	 *             FALSE: The request is payload free.
	 * 
	 * @return A builder class on which a HTTP operation can be invoked on.
	 */
	protected Builder setRequestHeaderAndMediaTypes(WebResource service, HeaderProperties hdrProperties, boolean includeRequestID, boolean includeFingerprint, boolean hasPayload)
	{
		return setRequestHeaderAndMediaTypes(service, hdrProperties, RequestType.IMMEDIATE, includeRequestID, includeFingerprint, hasPayload);
	}

	protected HeaderProperties createAuthenticationHdr(boolean isEnvCreate, SIF3Session pseudoSIF3Session)
	{
		AuthenticationInfo authInfo = getAuthenicationInfo(isEnvCreate, pseudoSIF3Session);
		HeaderProperties hdrProps = new HeaderProperties();
		if (authInfo != null) // all good
		{
			// First create the properties for the authentication header.
			ClientUtils.setAuthenticationHeader(hdrProps, authInfo.getAuthMethod(), authInfo.getUserToken(), authInfo.getPassword());
		}		

		return hdrProps;
	}

	protected HeaderProperties extractHeaderInfo(ClientResponse clientResponse)
	{
		HeaderProperties hdrProps = new HeaderProperties();
		MultivaluedMap<String, String> respHdrProps =  clientResponse.getHeaders();
		
		for (String hdrName : respHdrProps.keySet())
		{
			hdrProps.setHeaderProperty(hdrName, respHdrProps.getFirst(hdrName));
		}
				
		return hdrProps;
	}
	
	/*
     * Convenience method for calls that do not support DELAYED request/responses.
     */
    protected Response setResponse(WebResource service, ClientResponse clientResponse, Class<?> returnObjectClass, HeaderProperties requestHdrProps, SIFZone zone, SIFContext context, boolean zoneCtxSupported, Status... successStatusCodes)
    {
        return setResponse(service, clientResponse, returnObjectClass, requestHdrProps, zone, context, RequestType.IMMEDIATE, zoneCtxSupported, successStatusCodes);
    }
	
	protected Response setResponse(WebResource service, ClientResponse clientResponse, Class<?> returnObjectClass, HeaderProperties requestHdrProps, SIFZone zone, SIFContext context, RequestType requestType, boolean zoneCtxSupported, Status... successStatusCodes)
	{
		Response response = new Response();
		setBaseResponseData(response, clientResponse, requestHdrProps, zone, context, zoneCtxSupported, requestType, service.getURI().toString());
		response.setResourceURI(service.getURI());
		response.setDataObjectType(returnObjectClass);

		// Check if HTTP header messageType == ERROR
		boolean isErrorMessageType = MessageType.ERROR.name().equals(response.getHdrProperties().getHeaderProperty(ResponseHeaderConstants.HDR_MESSAGE_TYPE));
		
		if (isSuccessStatusCode(clientResponse.getStatusInfo().getStatusCode(), successStatusCodes) && !isErrorMessageType)
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

	/*
	 * This method cannot set the serviceName and serviceType in the Delayed Response Receipt property. It must be set by the caller of this method as this
	 * is the place where the values are known.
	 * 
	 *  @param zoneCtxSupported Indicates if the service supports zone and context (i.e. OBJECT and SERVICEPATH but not Infrastructure
	 *                          services such as environment connector). TRUE = support for Zone and Context FALSE otherwise.
	 */
	protected void setBaseResponseData(BaseResponse response, ClientResponse clientResponse, HeaderProperties requestHdrProps, SIFZone zone, SIFContext context, boolean zoneCtxSupported, RequestType requestType, String requestURI)
	{
	    SIF3Session sif3Session = (zoneCtxSupported) ? getSIF3Session() : null;
	    	    
	    // Note: sif3Session can be null if we create an environment! In this case we cannot retrieve a default zone or context! It is not required anyway
	    //       because create an environment is not done for a zone or context.
	    
	    SIFZone actualZone = (sif3Session != null) ? sif3Session.getZone(zone) : zone;
	    SIFContext actualContext = (sif3Session != null) ? sif3Session.getContext(context) : context;
	    
//		response.setStatus(clientResponse.getClientResponseStatus().getStatusCode());
        response.setStatus(clientResponse.getStatusInfo().getStatusCode());
//		response.setStatusMessage(clientResponse.getClientResponseStatus().getReasonPhrase());
        response.setStatusMessage(clientResponse.getStatusInfo().getReasonPhrase());
		response.setMediaType(clientResponse.getType());
		response.setContentLength(clientResponse.getLength());
		response.setZone(actualZone);
		response.setContext(actualContext);

		// Extract header properties.
		response.setHdrProperties(extractHeaderInfo(clientResponse));
		
		if (logger.isDebugEnabled())
		{
			logger.debug("HTTP Headers of Response: "+response.getHdrProperties());
		}
		response.setHasEntity(clientResponse.hasEntity() && (clientResponse.getStatusInfo().getStatusCode() != Status.NO_CONTENT.getStatusCode()) && (clientResponse.getStatusInfo().getStatusCode() != Status.ACCEPTED.getStatusCode()));		

		if ((requestType != null) && (requestType == RequestType.DELAYED)) // set delayed receipt info
		{
		    DelayedRequestReceipt delayedReceipt = new DelayedRequestReceipt();
		    delayedReceipt.setContext(actualContext);
		    delayedReceipt.setZone(actualZone);
		    
		    //Since this framework is being used, we know that the requestID is set in the request header properties.
		    delayedReceipt.setRequestGUID(requestHdrProps.getHeaderProperty(RequestHeaderConstants.HDR_REQUEST_ID));
		    delayedReceipt.setRequestDate(new Date());
		    
		    // The following three properties are not known at this time: ServiceName &  ServiceType
		    delayedReceipt.setRequestURI(requestURI);
		    
		    response.setDelayedReceipt(delayedReceipt);
		}
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
			
			response.setError(getInfraMapper().toErrorFromSIFErrorString(errorStr, getInfraResponseMediaType(getResponseMediaType()), new ErrorDetails(clientResponse.getStatus(), clientResponse.getStatusInfo().getReasonPhrase())));
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
		ClientConfigMgr cltCfgMgr = new ClientConfigMgr(getClientEnvMgr().getEnvironmentInfo().getNoCertificateCheck());		
		this.config = cltCfgMgr.getClientConfig(secureConnection);
	}
	
	private void createServiceForURI(URI baseURI, boolean useCompression)
	{
		// This is also going to change the service.
		this.baseURI = baseURI;
		
		// Create the Client Service
		this.client = Client.create(config);
		
		if (useCompression)
		{
			logger.debug("Set GZIP Compression for client.");
			this.client.addFilter(new com.sun.jersey.api.client.filter.GZIPContentEncodingFilter(true));
		}

		// Retrieve connector to resource
		this.service = client.resource(getBaseURI());
	}

	/*
	 * Determine which media type to use in the unmarshalling of a response. Intended to be used when a response is received that is a
	 * infrastructure message (i.e. Error Response, Environment Response, Queue Response etc.). In this case the response must be unmarshalled
	 * with the infrastructure unmarshaller but the indicatedMediaType is set for a data model object which could be other than XML or JSON.
	 * In this case we must unmarshal the response in the default infrastructure media type or we will get an unmarshal exception.
	 */
	private MediaType getInfraResponseMediaType(MediaType indicatedMediaType)
	{
		return getInfraUnmarshaller().isSupported(indicatedMediaType) ? indicatedMediaType : getInfraUnmarshaller().getDefault();
	}
	
	private MediaType addEncoding(MediaType mediaType, String charEncoding)
	{
	    if (StringUtils.notEmpty(charEncoding))
	    {
	       return MediaType.valueOf(mediaType.toString()+"; charset="+charEncoding);
	    }
	    else // no charEncoding desired.
	    {
	        return mediaType;
	    }
	}

	/*
	 * By default this method tries to create an AuthenticationInfo object based on the information that is in the current SIF3 Session
	 * of the client. It will take the userName, sessionToken etc from there. If there is no session available (i.e. first time the client 
	 * connects and therefore must create an environment first) then information will be taken from the appropriate property file for the 
	 * creation of authentication info object.
	 * 
 	 * If the parameter isEnvCreateRequest = true then it is assumed that it s the first time that a security token is created. Also a SIF3 Session
	 * does not yet exist. In this case the AuthenticationInfo is populated but no data is updated in a cache or a data store relation to this
	 * client since it has not yet a session.
	 * In all other cases the cache and the data store relating to the SIF3 session is updated if the security token or its expire date have changed. 
	 * Such an update is only expected to occur for 'Bearer' authentication where an external security service is contacted and potentially expire
	 * dates change for such security tokens.
	 * If standard SIF 'Basic' or 'SIF_HMACSHA256' is used then no data needs to be updated as everything is managed in-memory and no external
	 * security sources are involved.
	 * 
	 * There is one odd case to consider as well where we need to get an existing environment. In that case we don't have a
	 * sif3Session and are not in a create environment operation. In such a case we may have the sessionToken for the environment
	 * to sync with in the TokenInfo even if we don't use external security services. This is the only way to avoid to many 
	 * confusing parameters to this method.
	 * 
	 * This method will populate the properties in the AuthenticationInfo. After this call the AuthenticationInfo can be used to generate
	 * HTTP Header data using the AuthenticationUtils class (getFullBase64Token() method).
	 * 
	 * If anything fails then null is returned. An error will already be logged.
	 */
	private AuthenticationInfo getAuthenicationInfo(boolean isEnvCreateRequest, SIF3Session pseudoSIF3Session)
//    private AuthenticationInfo getAuthenicationInfo(boolean isEnvCreateRequest, TokenInfo tokenInfo)
	{
		EnvironmentInfo envInfo = getClientEnvMgr().getEnvironmentInfo();
		String pwd = envInfo.getPassword();

	    String user = null;
	    if (isEnvCreateRequest)
	    {
	        user = envInfo.getEnvironmentKey().getApplicationKey();
	    }
	    else 
	    {
	        // it is not a createEnv request but if we may have a pseudoSIF3Session  then it should have a sessionToken populated
	        if (pseudoSIF3Session == null) // we must have a session
	        {
	            user =  getSIF3Session().getSessionToken();
	        }
	        else // use tempSIF3Session session
	        {
	            user = pseudoSIF3Session.getSessionToken();
	        }
	    }
		
		AuthenticationInfo authInfo = null;
		if (envInfo.getAuthMethod() != AuthenticationMethod.Bearer)
		{
            authInfo = new AuthenticationInfo(envInfo.getAuthMethod(), user, pwd);
		}
		else // Use Bearer Authentication => potentially requires external security service.
		{
		    TokenInfo tokenInfo = null;
		    if (pseudoSIF3Session != null) // we don't have a real session. Use pseudoSIF3Session and extract token info from there.
		    {
		        tokenInfo = new TokenInfo(pseudoSIF3Session.getSecurityToken(), pseudoSIF3Session.getSecurityTokenExpiry());
		    }
            authInfo = getBearerAuthorisationInfo(isEnvCreateRequest, tokenInfo);
		}
		
		return authInfo;
	}
	
	/*
	 * This method will check if a security token must be retrieved from an external security service. If so it will attempt to do so and populate the 
	 * AuthenticationInfo accordingly. If a SIF3 Session exists then it will be updated if the security token or the expire data has changed. If null is 
	 * returned then there is an issue in retrieving a security token. In this case a request should not be made to the provider.
	 * 
	 * If the parameter isEnvCreateRequest = true then it is assumed that this method is used to create an environment. In such
	 * a case there is no SIF3 session available and a TokenInfo cannot be retrieved from there. Therefore the given tokenInfo
	 * parameter shall be used and it is not necessary to call the external security service as it is assume that it has already
	 * been called because the TokenInfo is given. In this case the AuthenticationInfo is populated but no data is updated in a 
	 * cache or a data store relation to this client since it has not yet a session.
	 * In all other cases the cache and the data store relating to the SIF3 session is updated if the security token or its expire date have changed.
	 *  
	 * If anything fails then null is returned and an error is logged.
	 */
	private AuthenticationInfo getBearerAuthorisationInfo(boolean isEnvCreateRequest, TokenInfo tokenInfo)
	{
		boolean retrieveToken = false;
		if (!isEnvCreateRequest) // we should have a SIF3 Session
		{
		    if (tokenInfo == null) // don't use an already created token
		    {
        		SIF3Session sif3Session = getSIF3Session();
        		if (StringUtils.notEmpty(sif3Session.getSecurityToken())) // we already have a token. This is not the first time we do a call
        		{
        			if (sif3Session.getSecurityTokenExpiry() != null) // token may already be expired
        			{
        				long now = (new Date()).getTime();
        				if (sif3Session.getSecurityTokenExpiry().getTime() < now) // expired date! => Renew token
        				{
        					retrieveToken = true;
        				}
        			}
        		}
        		else // we don't have a token yet! => get one
        		{
        			retrieveToken = true;
        		}
		    } // else => tokenInfo is not null so we must use it. No need to retrieve one.
		} 
		else // isEnvCreateRequest == true
		{
		    // First time access an external token should be given. No need to get one. But we do a check here.
		    if ((tokenInfo == null) || (StringUtils.isEmpty(tokenInfo.getToken())))
		    {
                logger.error("An environment shall be created using external security but no security toke is given to the createEnvironment() method.");
                return null;
		    }
		}
		
		EnvironmentInfo envInfo = getClientEnvMgr().getEnvironmentInfo();
		if (retrieveToken) // We must get a token from that service
		{
			AbstractSecurityService securityService = BearerSecurityFactory.getSecurityService(getClientEnvMgr().getServiceProperties());
			if (securityService != null)
			{
    			TokenCoreInfo coreInfo = new TokenCoreInfo();
    			if (isEnvCreateRequest)
    			{
    				coreInfo.setAppUserInfo(envInfo.getEnvironmentKey());
    				coreInfo.setConsumerName(envInfo.getAdapterName());
    			}
    			else
    			{
        			coreInfo.setAppUserInfo(getSIF3Session());
        			coreInfo.setConsumerName(getSIF3Session().getAdapterName());
        			coreInfo.setEnvironmentID(getSIF3Session().getEnvironmentID());
        			coreInfo.setSessionToken(getSIF3Session().getSessionToken());    				
    			}
    			
    			// Now call the security service and if successful update appropriate data
    			TokenInfo newTokenInfo = securityService.createToken(coreInfo, envInfo.getPassword());
    			if ((newTokenInfo != null) && StringUtils.notEmpty(newTokenInfo.getToken())) // we just got a valid token => save it in the sif3Session
    			{
    				AuthenticationInfo authInfo = new AuthenticationInfo(envInfo.getAuthMethod(), newTokenInfo.getToken(), envInfo.getPassword(), newTokenInfo.getTokenExpiryDate());
    				if (!isEnvCreateRequest) // not initial request => Update session!
    				{
        				getSIF3Session().setSecurityToken(newTokenInfo.getToken());
        				getSIF3Session().setSecurityTokenExpiry(newTokenInfo.getTokenExpiryDate());
        				if (!getClientEnvMgr().updateSessionSecurityInfo(getSIF3Session().getSessionToken(), newTokenInfo.getToken(), newTokenInfo.getTokenExpiryDate()))
        				{
                            logger.error("Failed to update session with new security token from the security service. Check previous error log entries for details.");
                            return null;    				    
        				}
    				}
    				
    				// If we get here then all is successful and we return the authentication info.
    				return authInfo;
    			}
    			else // failed to retrieve token
    			{
    				logger.error("Failed to retrieve a security token from the security service. Check previous error log entries for details.");
    				return null;
    			}
			}
			else // failed to get security service implementation from BearerSecurityFactory
			{
				logger.error("Failed to retrieve a security service implementation. Check previous error log entries for details.");
				return null;				
			}
		}
		else // nothing to do in terms of creating a new security token. We can use the existing values of the SIF3 Session or the tokenInfo
		{
		    if (tokenInfo != null)
		    {
                return new AuthenticationInfo(envInfo.getAuthMethod(), tokenInfo.getToken(), envInfo.getPassword(), tokenInfo.getTokenExpiryDate());
		    }
		    else // we get it from the session.
		    {
		        return new AuthenticationInfo(envInfo.getAuthMethod(), getSIF3Session().getSecurityToken(), envInfo.getPassword(), getSIF3Session().getSecurityTokenExpiry());
		    }
		}
	}
}
