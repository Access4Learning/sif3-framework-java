/*
 * ObjectServiceClient.java Created: 04/09/2013
 * 
 * Copyright 2013 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package sif3.infra.rest.client;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.model.URLQueryParameter;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.common.ws.model.MultiOperationStatusList;
import sif3.infra.common.interfaces.ClientEnvironmentManager;
import sif3.infra.common.model.DeleteIdType;
import sif3.infra.common.model.DeleteRequestType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class is a core client class for all Object Services and their CRUD operations (request connector) for SIF3. It takes care of 
 * all the little things that define the SIF3 REST transport for all the Object Service operations stated in the SIF3 spec. 
 * It abstracts the finer details on how a URL is constructed, what header fields are required on the request as well as the response.
 * It takes all of this internal processing and SIF3 infrastructure management away from the higher levels of this framework.<br/><br/>
 * 
 * @author Joerg Huber
 */
public class ObjectServiceClient extends BaseClient
{
	/**
	 * Constructor<br/>
	 * 
     * @param clientEnvMgr Session manager to access the clients session information.
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
	public ObjectServiceClient(ClientEnvironmentManager clientEnvMgr, URI baseURI, MediaType requestMediaType, MediaType responseMediaType, MarshalFactory dmMarshaller, UnmarshalFactory dmUnmarshaller, boolean secureConnection, boolean useCompression)
	{
		super(clientEnvMgr, baseURI, requestMediaType, responseMediaType, dmMarshaller, dmUnmarshaller, secureConnection, useCompression);
	}

	/**
	 * This constructor will default the media type of the marshaller (request) and unmarshaller (response) and the default service type of OBJECT.
	 * 
     * @param clientEnvMgr Session manager to access the clients session information.
	 * @param baseURI The base URI of this client. All URIs are for all other calls are relative to this base URL.
	 * @param dmMarshaller Marshaller to marshal the payload of this client to appropriate representations. This marshaller must be valid
	 *                   for the data model used with this client.
	 * @param dmUnmarshaller Unmarshaller to unmarshal the payload of this client to appropriate representations. This unmarshaller 
	 *                     must be valid for the data model used with this client.
	 * @param secureConnection TRUE: Use HTTPS, FALSE use HTTP.
	 * @param useCompression TRUE: Payloads (request & response) shall be compressed before sending or de-compressed at the
	 *                             time of receiving.
	 *                       FALSE: No compression is used.
	 */
	public ObjectServiceClient(ClientEnvironmentManager clientEnvMgr, URI baseURI, MarshalFactory dmMarshaller, UnmarshalFactory dmUnmarshaller, boolean secureConnection, boolean useCompression)
	{
		super(clientEnvMgr, baseURI, dmMarshaller, dmUnmarshaller, secureConnection, useCompression);
	}

	/*----------------------------------*/
	/*-- Operations on Single Objects --*/
	/*----------------------------------*/
	
	/**
	 * This method will retrieve the object given by its resourceID. This call will invoke the REST GET call. The object will 
	 * be unmarshalled into the returnObjectClass and be stored as part of the returned Response object. If there are any internal 
	 * errors then a ServiceInvokationException will be raised and the error is logged.
	 * 
	 * @param relURI A relative URI to the baseURI given to the constructor of this class. It is appended to the baseURI as is.
	 * @param resourceID The Id of the object to be returned. If the object doesn't exist the appropriate status is set in the
	 *                   returned Response object as defined by the SIF3 spec.
	 * @param hdrProperties Header Properties to be added to the header of the GET request.
	 * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
	 *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
	 *                       names are case sensitive. This parameter can be null.
	 * @param returnObjectClass The class type into which the object shall be unmarshalled into. The final object is stored in the
	 *                          returned Response object.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * 
	 * @return The Response to this GET call. See the Response class for details of the content of this object.
	 * 
	 * @throws ServiceInvokationException An internal error occurred. An error is logged.
	 */
	public Response getSingle(String relURI, String resourceID, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, Class<?> returnObjectClass, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, resourceID, zone, context, urlQueryParams);
			hdrProperties = addAuthenticationHdrProps(hdrProperties);
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, false).get(ClientResponse.class);

			return setResponse(service, response, returnObjectClass, hdrProperties, zone, context, true, Status.OK, Status.NOT_MODIFIED);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'getSingle' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	/**
	 * Will invoke the REST POST method. Before the call it will marshal the 'payload' into appropriate format (mediaType) and add the 
	 * 'hdrProperties' to the header of the POST request. If there are any errors then the ServiceInvokationException is raised and the
	 * error is logged.
	 * 
	 * @param relURI A relative URI to the baseURI given to the constructor of this class. It is appended to the baseURI as is.
	 * @param payload The payload in its object form. The marshaller given in the constructor of this method is used to convert the 
	 *                payload to appropriate format (mediaType).
	 * @param hdrProperties Header Properties to be added to the header of the POST request.
	 * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
	 *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
	 *                       names are case sensitive. This parameter can be null.
	 * @param returnObjectClass The class type into which the object shall be unmarshalled into. The final object is stored in the
	 *                          returned Response object.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as unable to marshal the object into its media
	 *                                    type, failure to invoke actual web-service etc. 
	 */
	public Response createSingle(String relURI, Object payload, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, Class<?> returnObjectClass, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, null, zone, context, urlQueryParams);
			String payloadStr = getDataModelMarshaller().marshal(payload, getRequestMediaType());

			if (logger.isDebugEnabled())
			{
				logger.debug("createSingle: Payload to send:\n"+payloadStr);
			}
			
			hdrProperties = addAuthenticationHdrProps(hdrProperties);
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, true).post(ClientResponse.class, payloadStr);

			return setResponse(service, response, returnObjectClass, hdrProperties, zone, context, true, Status.CREATED, Status.CONFLICT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'createSingle' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	/**
	 * Will invoke the REST PUT method. Before the call it will marshal the 'payload' into appropriate format (mediaType) and add the 
	 * 'hdrProperties' to the header of the PUT request. If there are any errors then the ServiceInvokationException is raised and the
	 * error is logged.
	 * 
	 * @param relURI A relative URI to the baseURI given to the constructor of this class. It is appended to the baseURI as is.
	 * @param resourceID The Id of the object to be updated. If the object doesn't exist the appropriate status is set in the
	 *                   returned Response object as defined by the SIF3 spec.
	 * @param payload The payload in its object form. The marshaller given in the constructor of this method is used to convert the 
	 *                payload to appropriate format (mediaType).
	 * @param hdrProperties Header Properties to be added to the header of the PUT request.
	 * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
	 *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
	 *                       names are case sensitive. This parameter can be null.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as unable to marshal the object into its media type, 
	 *                                    failure to invoke actual web-service etc. 
	 */
	public Response updateSingle(String relURI, String resourceID, Object payload, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, resourceID, zone, context, urlQueryParams);
			String payloadStr = getDataModelMarshaller().marshal(payload, getRequestMediaType());

			if (logger.isDebugEnabled())
			{
				logger.debug("updateSingle: Payload to send:\n"+payloadStr);
			}
			
			hdrProperties = addAuthenticationHdrProps(hdrProperties);
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, true).put(ClientResponse.class, payloadStr);

			return setResponse(service, response, null, hdrProperties, zone, context, true, Status.NO_CONTENT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'updateSingle' service (REST PUT) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	/**
	 * Will invoke the REST DELETE method for the object given by its resourceId. Before the call it will add the 
	 * 'hdrProperties' to the header of the DELETE request. If there are any errors then the ServiceInvokationException is raised and 
	 * the error is logged.
	 * 
	 * @param relURI A relative URI to the baseURI given to the constructor of this class. It is appended to the baseURI as is.
	 * @param resourceID The Id of the object to be deleted. If the object doesn't exist the appropriate status is set in the
	 *                   returned Response object as defined by the SIF3 spec.
	 * @param hdrProperties Header Properties to be added to the header of the DELETE request.
	 * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
	 *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
	 *                       names are case sensitive. This parameter can be null.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
	 */
	public Response removeSingle(String relURI, String resourceID, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, resourceID, zone, context, urlQueryParams);
			
			hdrProperties = addAuthenticationHdrProps(hdrProperties);
		    ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true, false).delete(ClientResponse.class);

			return setResponse(service, response, null, hdrProperties, zone, context, true, Status.NO_CONTENT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'removeSingle' service (REST DELETE) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	/*---------------------*/
	/*-- Bulk Operations --*/
	/*---------------------*/
	/**
	 * Will invoke the REST GET call (Query). Because no resourceID is provided it will automatically return a list of objects (collection).
	 * Additional parameters of this method indicate what part of the collection shall be returned (pagingInfo) as well as what
	 * zone and context this query shall apply to.
	 * 
	 * @param relURI A relative URI to the baseURI given to the constructor of this class. It is appended to the baseURI as is.
	 * @param serviceName The "raw" service name. For object services that would be the SIF Object name, for Service Paths this must be
	 *                    the service path name as in the Environment ACL (i.e. schools/{}/students).
	 * @param serviceType Currently this should be OBJECT or SERVICPATH.
	 * @param pagingInfo Page information to be set for the provider to determine which results to return.
	 * @param hdrProperties Header Properties to be added to the header of the GET request.
	 * @param returnObjectClass The class type into which the object shall be unmarshalled into. The final object is stored in the
	 *                          returned Response object.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
	 */
	public Response getMany(String relURI, String serviceName, ServiceType serviceType, PagingInfo pagingInfo, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, Class<?> returnObjectClass, SIFZone zone, SIFContext context, RequestType requestType) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, null, zone, context, urlQueryParams);
			hdrProperties = addAuthenticationHdrProps(hdrProperties);
			addPagingInfoToHeaders(pagingInfo, hdrProperties);
			addDelayedInfo(hdrProperties, zone, context, serviceName, serviceType, requestType);
			
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, requestType, true, true, false).get(ClientResponse.class);

			return setResponse(service, response, returnObjectClass, hdrProperties, zone, context, requestType, true, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT, Status.ACCEPTED);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'getMany' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}
	
	/**
	 * Will invoke the REST POST call but with a 'methodOverride' for a 'GET' to enable a payload to be transfered for a
	 * Query style operation. This method is used for Query By Example requests. All parameters except the 'exampleObject'
	 * are used in the same manner as with the getMany() method in this class. A list of objects (collection) is returned
	 * as part of this method. Additional parameters of this method indicate what part of the collection shall be 
	 * returned (pagingInfo) as well as what zone and context this query shall apply to.
	 * 
	 * @param relURI A relative URI to the baseURI given to the constructor of this class. It is appended to the baseURI as is.
	 * @param serviceName The "raw" service name. This must be the SIF Object name such as StudentPersonals.
	 * @param exampleObject The example data model object. This must be the single object type.
	 * @param pagingInfo Page information to be set for the provider to determine which results to return.
	 * @param hdrProperties Header Properties to be added to the header of the GET request.
	 * @param returnObjectClass The class type into which the object shall be unmarshalled into. The final object is stored in the
	 *                          returned Response object.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
	 */
	public Response getByQBE(String relURI, String serviceName, Object exampleObject, PagingInfo pagingInfo, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, Class<?> returnObjectClass, SIFZone zone, SIFContext context, RequestType requestType) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, null, zone, context, urlQueryParams);
		    String payloadStr = getDataModelMarshaller().marshal(exampleObject, getRequestMediaType());

			hdrProperties = addAuthenticationHdrProps(hdrProperties);
			addPagingInfoToHeaders(pagingInfo, hdrProperties);
			addDelayedInfo(hdrProperties, zone, context, serviceName, ServiceType.OBJECT, requestType);


			// Set specific header so that POST method knows that a GET and not an CREATE is required! 
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE, HeaderValues.MethodType.GET.name());

			if (logger.isDebugEnabled())
			{
				logger.debug("getByQBE: Payload to send:\n"+payloadStr);
			}
			
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, requestType, true, true, true).post(ClientResponse.class, payloadStr);

			return setResponse(service, response, returnObjectClass, hdrProperties, zone, context, requestType, true, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT, Status.ACCEPTED);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'getByQBE' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	/**
	 * This invokes the REST POST call. This method is used to create many objects in one call as defined by the SIF3 spec. The
	 * returned list of responses equate to one response per object in the given payload. The order of the responses is the same as the
	 * order in the original payload. The first response in the BulkOperationResponse list is the response to the create of the first
	 * object in the payload etc.
	 * 
	 * @param relURI A relative URI to the baseURI given to the constructor of this class. It is appended to the baseURI as is.
	 * @param serviceName The "raw" service name. This must be the SIF Object name such as StudentPersonals.
	 * @param payload The payload in its object form. The marshaller given in the constructor of this method is used to convert the 
	 *                payload to appropriate format (mediaType).
	 * @param hdrProperties Header Properties to be added to the header of the POST request.
	 * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
	 *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
	 *                       names are case sensitive. This parameter can be null.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
	 */
	public BulkOperationResponse<CreateOperationStatus> createMany(String relURI, String serviceName, Object payload, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context, RequestType requestType) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, null, zone, context, urlQueryParams);
		    String payloadStr = getDataModelMarshaller().marshal(payload, getRequestMediaType());

			if (logger.isDebugEnabled())
			{
				logger.debug("createMany: Payload to send:\n"+payloadStr);
			}
			
			hdrProperties = addAuthenticationHdrProps(hdrProperties);
			addDelayedInfo(hdrProperties, zone, context, serviceName, ServiceType.OBJECT, requestType);
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, requestType, true, true, true).post(ClientResponse.class, payloadStr);

			return setCreateBulkResponse(service, response, zone, context, requestType, hdrProperties);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'createMany' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	/**
	 * This invokes the REST PUT call. This method is used to update many objects in one call as defined by the SIF3 spec. The
	 * returned list of responses equate to one response per object in the given payload. The order of the responses is the same as the
	 * order in the original payload. The first response in the BulkOperationResponse list is the response to the create of the first
	 * object in the payload etc.
	 * 
	 * @param relURI A relative URI to the baseURI given to the constructor of this class. It is appended to the baseURI as is.
	 * @param serviceName The "raw" service name. This must be the SIF Object name such as StudentPersonals.
	 * @param payload The payload in its object form. The marshaller given in the constructor of this method is used to convert the 
	 *                payload to appropriate format (mediaType).
	 * @param hdrProperties Header Properties to be added to the header of the PUT request.
	 * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
	 *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
	 *                       names are case sensitive. This parameter can be null.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
	 */
	public BulkOperationResponse<OperationStatus> updateMany(String relURI, String serviceName, Object payload, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context, RequestType requestType) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, null, zone, context, urlQueryParams);

			String payloadStr = getDataModelMarshaller().marshal(payload, getRequestMediaType());
			if (logger.isDebugEnabled())
			{
				logger.debug("updateMany: Payload to send:\n"+payloadStr);
			}

			hdrProperties = addAuthenticationHdrProps(hdrProperties);			
			addDelayedInfo(hdrProperties, zone, context, serviceName, ServiceType.OBJECT, requestType);

			// Set specific header so that PUT method knows that an UPDATE and not a DELETE is required! 
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE, HeaderValues.MethodType.UPDATE.name());																																			
			
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, requestType, true, true, true).put(ClientResponse.class, payloadStr);

			return setUpdateBulkResponse(service, response, zone, context, requestType, hdrProperties);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'updateMany' service (REST PUT) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}
	
	/**
	 * This invokes the REST PUT call. This method is used to delete many objects in one call as defined by the SIF3 spec. The
	 * returned list of responses equate to one response per object in the given payload. The order of the responses is the same as the
	 * order in the original payload. The first response in the BulkOperationResponse list is the response to the create of the first
	 * object in the payload etc.<br/><br/>
	 * 
	 * There is an issue with java.net.HttpURLConnection where it doesn't allow an payload for the HTTP DELETE operation. So currently 
	 * the implementation of the removeMany fakes such a behaviour and actually calls the HTTP PUT with a HTTP Header called 'methodOverride' as
	 * specified in the SIF 3.x specification.
	 * 
	 * @param relURI A relative URI to the baseURI given to the constructor of this class. It is appended to the baseURI as is.
	 * @param serviceName The "raw" service name. This must be the SIF Object name such as StudentPersonals.
	 * @param resourceIDs A list of resourceId for the objects to be deleted.
	 * @param hdrProperties Header Properties to be added to the header of the DELETE request.
	 * @param urlQueryParams URL query parameters to be added to the request. It is assumed that these are custom
	 *                       URL query parameters. They are conveyed to the provider unchanged. URL query parameter
	 *                       names are case sensitive. This parameter can be null.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
	 */
	public BulkOperationResponse<OperationStatus> removeMany(String relURI, String serviceName, List<String> resourceIDs, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context, RequestType requestType) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, null, zone, context, urlQueryParams);
			
			//Convert List of resources to DeletesTypes
			DeleteRequestType deleteRequest = getInfraObjectFactory().createDeleteRequestType();
			deleteRequest.setDeletes(getInfraObjectFactory().createDeleteIdCollection());
			
			if (resourceIDs != null)
			{
			  for (String resourceID : resourceIDs)
			  {
			    DeleteIdType id = getInfraObjectFactory().createDeleteIdType();
			    id.setId(resourceID);
			    deleteRequest.getDeletes().getDelete().add(id);
			  }
			}
			String payloadStr = getInfraMarshaller().marshal(deleteRequest, getRequestMediaType());
			
			hdrProperties = addAuthenticationHdrProps(hdrProperties);
			addDelayedInfo(hdrProperties, zone, context, serviceName, ServiceType.OBJECT, requestType);

			// Set specific header so that PUT method knows that a DELETE and not an UPDATE is required! 
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE, HeaderValues.MethodType.DELETE.name());
			
			if (logger.isDebugEnabled())
			{
				logger.debug("removeMany: Payload to send:\n"+payloadStr);
			}
			ClientResponse cltResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, requestType, true, true, true).put(ClientResponse.class, payloadStr);
			
			return setDeleteBulkResponse(service, cltResponse, zone, context, requestType, hdrProperties);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'removeMany' service (REST DELETE) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	/*-----------------------------------*/
    /*-- Get Service Infor (HTTP HEAD) --*/
    /*-----------------------------------*/
    /**
     * Will invoke the REST HEAD call. It will not return a payload as per HTTP Specification of the HEAD method. It will
     * return a number of HTTP Header fields though. These can be retrieved as part of the returned response object. Because
     * this method almost mirrors the HTTP GET for the root object service all parameters that would make up the getMany()
     * method in this class are supported. The exception is the returnObjectClass, serviceType and requestType parameter 
     * that are allowed in the getMany() method. They do not make any sense for this method and are therefore omitted.
     * 
     * @param relURI A relative URI to the baseURI given to the constructor of this class. It is appended to the baseURI as is.
     * @param serviceName The "raw" service name. For object services that would be the SIF Object name, for Service Paths this must be
     *                    the service path name as in the Environment ACL (i.e. schools/{}/students).
     * @param pagingInfo Page information to be set for the provider to determine which results to return.
     * @param hdrProperties Header Properties to be added to the header of the GET request.
     * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
     * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
     * 
     * @return Response Object holding appropriate values and results of the call. 
     * 
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
    public Response getServiceInfo(String relURI, String serviceName, PagingInfo pagingInfo, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, relURI, null, zone, context, urlQueryParams);
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            addPagingInfoToHeaders(pagingInfo, hdrProperties);
            
            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, RequestType.IMMEDIATE, true, true, false).head();

            return setResponse(service, response, null, hdrProperties, zone, context, RequestType.IMMEDIATE, true, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT, Status.ACCEPTED);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'getServiceInfo' service (REST HEAD) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }

	
	
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private BulkOperationResponse<CreateOperationStatus> setCreateBulkResponse(WebResource service, ClientResponse clientResponse, SIFZone zone, SIFContext context, RequestType requestType, HeaderProperties requestHeaders)
	{
		BulkOperationResponse<CreateOperationStatus> response = new BulkOperationResponse<CreateOperationStatus>();
		setBaseResponseData(response, clientResponse, requestHeaders, zone, context, true, requestType, service.getURI().toString());
		if ((clientResponse.getStatusInfo().getStatusCode() == Status.CREATED.getStatusCode()) || 
			(clientResponse.getStatusInfo().getStatusCode() == Status.ACCEPTED.getStatusCode()) ||
			(clientResponse.getStatusInfo().getStatusCode() == Status.NO_CONTENT.getStatusCode()))
		{
			if (response.getHasEntity())
			{
				String payload = clientResponse.getEntity(String.class);
				MultiOperationStatusList<CreateOperationStatus> statusList = getInfraMapper().toStatusListFromSIFCreateString(payload, getResponseMediaType());
				response.setError(statusList.getError());
				response.setOperationStatuses(statusList.getOperationStatuses());

//				try
//				{						
//					//Because CreateResponseType is a Infrastructure thing we must ensure we use the Infrastructure Unmarshaller
//					CreateResponseType createManyResponse = (CreateResponseType)getInfraUnmarshaller().unmarshal(payload, CreateResponseType.class, getResponseMediaType());
//					if (createManyResponse == null)// this is strange. So set the unmarshalled value.
//					{
//						response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload. See error description for payload details.", payload));							
//					}
//					else
//					{
//						response.setOperationStatuses(new ArrayList<CreateOperationStatus>());
//						for (CreateType createStatus : createManyResponse.getCreates().getCreate())
//						{
//							CreateOperationStatus opStatus = new CreateOperationStatus();
//							opStatus.setResourceID(createStatus.getId());
//							opStatus.setAdvisoryID(createStatus.getAdvisoryId());
//							opStatus.setStatus(toInt(createStatus.getStatusCode()));
//							opStatus.setError(convertFromErrorType(createStatus.getError()));
//							response.getOperationStatuses().add(opStatus);
//						}
//					}
//				}
//				catch (UnmarshalException ex)
//				{
//					response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload: "+ex.getMessage()+". See error description for payload details.", payload));
//				}
//				catch (UnsupportedMediaTypeExcpetion ex)
//				{
//					response.setError(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal payload (unsupported media type): "+ex.getMessage()+". See error description for payload details.", payload));
//				}
			}			
		}
		else// We are dealing with an error case.
		{
			setErrorResponse(response, clientResponse);
		}
		
		if (logger.isDebugEnabled())
		{
			logger.debug("Response from REST Call:\n"+response);
		}
		return response;
	}

	private BulkOperationResponse<OperationStatus> setDeleteBulkResponse(WebResource service, ClientResponse clientResponse, SIFZone zone, SIFContext context, RequestType requestType, HeaderProperties requestHeaders)
	{
		BulkOperationResponse<OperationStatus> response = new BulkOperationResponse<OperationStatus>();
		setBaseResponseData(response, clientResponse, requestHeaders, zone, context, true, requestType, service.getURI().toString());
		if ((clientResponse.getStatusInfo().getStatusCode() == Status.OK.getStatusCode()) || 
			(clientResponse.getStatusInfo().getStatusCode() == Status.ACCEPTED.getStatusCode()) ||
			(clientResponse.getStatusInfo().getStatusCode() == Status.NO_CONTENT.getStatusCode()))
		{
			if (response.getHasEntity())
			{
				String payload = clientResponse.getEntity(String.class);
				MultiOperationStatusList<OperationStatus> statusList = getInfraMapper().toStatusListFromSIFDeleteString(payload, getResponseMediaType());
				response.setError(statusList.getError());
				response.setOperationStatuses(statusList.getOperationStatuses());
				
//				try
//				{						
//					//Because DeleteResponseType is a Infrastructure thing we must ensure we use the Infrastructure Unmarshaller
//					DeleteResponseType deleteManyResponse = (DeleteResponseType)getInfraUnmarshaller().unmarshal(payload, DeleteResponseType.class, getResponseMediaType());
//					if (deleteManyResponse == null)// this is strange. So set the unmarshalled value.
//					{
//						response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload. See error description for payload details.", payload));							
//					}
//					else
//					{
//						response.setOperationStatuses(new ArrayList<OperationStatus>());
//						for (DeleteStatus deleteStatus : deleteManyResponse.getDeletes().getDelete())
//						{
//							OperationStatus opStatus = new OperationStatus();
//							opStatus.setResourceID(deleteStatus.getId());
//							opStatus.setStatus(toInt(deleteStatus.getStatusCode()));
//							opStatus.setError(convertFromErrorType(deleteStatus.getError()));
//							response.getOperationStatuses().add(opStatus);
//						}
//					}
//				}
//				catch (UnmarshalException ex)
//				{
//					response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload: "+ex.getMessage()+". See error description for payload details.", payload));
//				}
//				catch (UnsupportedMediaTypeExcpetion ex)
//				{
//					response.setError(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal payload (unsupported media type): "+ex.getMessage()+". See error description for payload details.", payload));
//				}
			}			
		}
		else// We are dealing with an error case.
		{
			setErrorResponse(response, clientResponse);
		}
		
		if (logger.isDebugEnabled())
		{
			logger.debug("Response from REST Call:\n"+response);
		}
		return response;
	}

	private BulkOperationResponse<OperationStatus> setUpdateBulkResponse(WebResource service, ClientResponse clientResponse, SIFZone zone, SIFContext context, RequestType requestType, HeaderProperties requestHeaders)
	{
		BulkOperationResponse<OperationStatus> response = new BulkOperationResponse<OperationStatus>();
		setBaseResponseData(response, clientResponse, requestHeaders, zone, context, true, requestType, service.getURI().toString());
		if ((clientResponse.getStatusInfo().getStatusCode() == Status.OK.getStatusCode()) || 
			(clientResponse.getStatusInfo().getStatusCode() == Status.ACCEPTED.getStatusCode()) ||
			(clientResponse.getStatusInfo().getStatusCode() == Status.NO_CONTENT.getStatusCode()))
		{
			if (response.getHasEntity())
			{
				String payload = clientResponse.getEntity(String.class);
				MultiOperationStatusList<OperationStatus> statusList = getInfraMapper().toStatusListFromSIFUpdateString(payload, getResponseMediaType());
				response.setError(statusList.getError());
				response.setOperationStatuses(statusList.getOperationStatuses());
			}			
		}
		else// We are dealing with an error case.
		{
			setErrorResponse(response, clientResponse);
		}
		
		if (logger.isDebugEnabled())
		{
			logger.debug("Response from REST Call:\n"+response);
		}
		return response;
	}
	
	private void addPagingInfoToHeaders(PagingInfo pagingInfo, HeaderProperties hdrProperties)
	{	
		if (pagingInfo != null)
		{
		  	Map<String, String> queryParameters = pagingInfo.getRequestValues();
			for (String key : queryParameters.keySet())
			{
			  hdrProperties.setHeaderProperty(key, queryParameters.get(key));
			}
		}
	}
	
	private void addDelayedInfo(HeaderProperties hdrProperties, SIFZone zone, SIFContext context, String serviceName, ServiceType serviceType, RequestType requestType)
	{
		if (requestType == RequestType.DELAYED)
		{
			ServiceInfo serviceInfo = getSIF3Session().getServiceInfoForService(zone, context, serviceName, serviceType);
			if (serviceInfo != null)
			{
				if ((serviceInfo.getRemoteQueueInfo() != null) && (serviceInfo.getRemoteQueueInfo().getQueueID() != null))
				{
					hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_QUEUE_ID, serviceInfo.getRemoteQueueInfo().getQueueID());
				}
				else // should not be the case if all is called properly but you never know...
				{
					logger.error("No SIF Queue configured environment with Service Name = "+serviceName+", Service Type = "+serviceType+", Zone = "+zone.getId()+" and Context = "+context.getId());
				}
			}
			else // should not be the case if all is called properly but you never know... 
			{
				logger.error("No valid service listed in environment ACL for Service Name = "+serviceName+", Service Type = "+serviceType+", Zone = "+zone.getId()+" and Context = "+context.getId());
			}
		}
	}
	
	/*
	 * This method will add the authentication header properties to the given set of header properties. The final set of header
	 * properties is then returned.
	 */
    private HeaderProperties addAuthenticationHdrProps(HeaderProperties hdrProperties)
    {
        if (hdrProperties == null)
        {
            hdrProperties = new HeaderProperties();
        }
        
        // Add Authentication info to existing header properties
        hdrProperties.addHeaderProperties(createAuthenticationHdr(false, null));
        
        return hdrProperties;
    }
    
//    /*
//     * Caller MUST ensure that the requestHdrProperties are not null. They really shouldn't since this class is only called
//     * by the AbstractConsumer which must set a few header properties anyway.
//     */
//    private boolean isDelayedRequest(HeaderProperties requestHdrProperties)
//    {
//        String requestType = requestHdrProperties.getHeaderProperty(RequestHeaderConstants.HDR_REQUEST_TYPE);
//    
//        if (requestType != null)
//        {
//            return HeaderValues.RequestType.valueOf(requestType) == RequestType.DELAYED;
//        }
//
//        // Nothing found => Assume IMMEDIATE    
//        return false;
//    }
//    
//    /*
//     * Caller MUST ensure that the requestHdrProperties are not null. They really shouldn't since this class is only called
//     * by the AbstractConsumer which must set a few header properties anyway.
//     */
//    private ServiceType getServiceTypeFromRequestHeaders(HeaderProperties requestHdrProperties)
//    {
//        String serviceType = requestHdrProperties.getHeaderProperty(RequestHeaderConstants.HDR_SERVICE_NAME);
//        
//        if (serviceType != null)
//        {
//            return HeaderValues.ServiceType.valueOf(serviceType);
//        }
//        
//        return null;
//    }
}