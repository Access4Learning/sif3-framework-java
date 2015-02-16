/*
 * ClientInterface.java Created: 04/09/2013
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.model.PagingInfo;
//import sif3.common.model.QueryMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.infra.common.model.CreateResponseType;
import sif3.infra.common.model.CreateType;
import sif3.infra.common.model.DeleteIdType;
import sif3.infra.common.model.DeleteRequestType;
import sif3.infra.common.model.DeleteResponseType;
import sif3.infra.common.model.DeleteStatus;
import sif3.infra.common.model.UpdateResponseType;
import sif3.infra.common.model.UpdateType;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * This class is a core client class to deal with the CRUD interface (request connector)for SIF3. It takes care of all the little things 
 * that define the SIF3 REST transport for all the operations stated in the SIF3 spec for CRUD Clients. It abstracts the finer details on 
 * how a URL is constructed, what header fields are required on the request as well as the response. It takes all of this internal processing 
 * and SIF3 infrastructure management away from the higher levels of this framework.<br/><br/>
 * 
 * Each client call must use this class to ensure that all the things that make a SIF3 REST call valid are guaranteed.<br/><br/>
 * 
 * Note:<br/>
 * This class is the REST Client Implementation for a standard CRUD Consumer of SIF OBJECT services. Even though it is called 'Interface' 
 * it is not a proper interface. There is a possibility that later versions of this framework will rename this class but since it is fully 
 * abstracted in the lower levels of this framework and not exposed to the higher levels such a change will most likely not impact the 
 * services written by developers.
 * 
 * @author Joerg Huber
 */
public class ClientInterface extends BaseClient
{
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
	public ClientInterface(URI baseURI, MediaType requestMediaType, MediaType responseMediaType, MarshalFactory dmMarshaller, UnmarshalFactory dmUnmarshaller, boolean secureConnection)
	{
		super(baseURI, requestMediaType, responseMediaType, dmMarshaller, dmUnmarshaller, secureConnection);
	}

	/**
	 * This constructor will default the media type of the marshaller (request) and unmarshaller (response) and the default service type of OBJECT.
	 * 
	 * @param baseURI The base URI of this client. All URIs are for all other calls are relative to this base URL.
	 * @param dmMarshaller Marshaller to marshal the payload of this client to appropriate representations. This marshaller must be valid
	 *                   for the data model used with this client.
	 * @param dmUnmarshaller Unmarshaller to unmarshal the payload of this client to appropriate representations. This unmarshaller 
	 *                     must be valid for the data model used with this client.
	 * @param secureConnection TRUE: Use HTTPS, FALSE use HTTP.
	 */
	public ClientInterface(URI baseURI, MarshalFactory dmMarshaller, UnmarshalFactory dmUnmarshaller, boolean secureConnection)
	{
		super(baseURI, dmMarshaller, dmUnmarshaller, secureConnection);
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
	 * @param returnObjectClass The class type into which the object shall be unmarshalled into. The final object is stored in the
	 *                          returned Response object.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * 
	 * @return The Response to this GET call. See the Response class for details of the content of this object.
	 * 
	 * @throws ServiceInvokationException An internal error occurred. An error is logged.
	 */
	public Response getSingle(String relURI, String resourceID, HeaderProperties hdrProperties, Class<?> returnObjectClass, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, resourceID, zone, context);
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true).get(ClientResponse.class);

			return setResponse(service, response, returnObjectClass, Status.OK, Status.NOT_MODIFIED);
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
	public Response createSingle(String relURI, Object payload, HeaderProperties hdrProperties, Class<?> returnObjectClass, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, null, zone, context);
			String payloadStr = getDataModelMarshaller().marshal(payload, getRequestMediaType());

			if (logger.isDebugEnabled())
			{
				logger.debug("createSingle: Payload to send:\n"+payloadStr);
			}
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true).post(ClientResponse.class, payloadStr);

			return setResponse(service, response, returnObjectClass, Status.CREATED, Status.CONFLICT);
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
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as unable to marshal the object into its media type, 
	 *                                    failure to invoke actual web-service etc. 
	 */
	public Response updateSingle(String relURI, String resourceID, Object payload, HeaderProperties hdrProperties, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, resourceID, zone, context);
			String payloadStr = getDataModelMarshaller().marshal(payload, getRequestMediaType());

			if (logger.isDebugEnabled())
			{
				logger.debug("updateSingle: Payload to send:\n"+payloadStr);
			}
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true).put(ClientResponse.class, payloadStr);

			return setResponse(service, response, null, Status.NO_CONTENT);
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
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
	 */
	public Response removeSingle(String relURI, String resourceID, HeaderProperties hdrProperties, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, resourceID, zone, context);
		    ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true).delete(ClientResponse.class);

			return setResponse(service, response, null, Status.NO_CONTENT);
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
	 * @param pagingInfo Page information to be set for the provider to determine which results to return.
	 * @param hdrProperties Header Properties to be added to the header of the GET request.
	 * @param returnObjectClass The class type into which the object shall be unmarshalled into. The final object is stored in the
	 *                          returned Response object.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
	 */
	public Response getMany(String relURI, PagingInfo pagingInfo, HeaderProperties hdrProperties, Class<?> returnObjectClass, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, null, zone, context);
			if (pagingInfo != null)
			{
//				QueryMetadata query = new QueryMetadata();
//				query.setPagingInfo(pagingInfo);
//				Map<String, String> queryParameters = query.getQueryParameters();
			  	Map<String, String> queryParameters = pagingInfo.getRequestValues();
				for (String key : queryParameters.keySet())
				{
				  hdrProperties.setHeaderProperty(key, queryParameters.get(key));
				  //service = service.queryParam(key, queryParameters.get(key));
				}
				
				//service = service.queryParams(query.getQueryParametersAsMultivaluedMap());
			}
			
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true).get(ClientResponse.class);

			return setResponse(service, response, returnObjectClass, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'getMany' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
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
	 * @param payload The payload in its object form. The marshaller given in the constructor of this method is used to convert the 
	 *                payload to appropriate format (mediaType).
	 * @param hdrProperties Header Properties to be added to the header of the POST request.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
	 */
	public BulkOperationResponse<CreateOperationStatus> createMany(String relURI, Object payload, HeaderProperties hdrProperties, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, null, zone, context);
		    String payloadStr = getDataModelMarshaller().marshal(payload, getRequestMediaType());

			if (logger.isDebugEnabled())
			{
				logger.debug("createMany: Payload to send:\n"+payloadStr);
			}
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true).post(ClientResponse.class, payloadStr);

			return setCreateBulkResponse(response);
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
	 * @param payload The payload in its object form. The marshaller given in the constructor of this method is used to convert the 
	 *                payload to appropriate format (mediaType).
	 * @param hdrProperties Header Properties to be added to the header of the PUT request.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
	 */
	public BulkOperationResponse<OperationStatus> updateMany(String relURI, Object payload, HeaderProperties hdrProperties, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, null, zone, context);

			String payloadStr = getDataModelMarshaller().marshal(payload, getRequestMediaType());
			if (logger.isDebugEnabled())
			{
				logger.debug("updateMany: Payload to send:\n"+payloadStr);
			}

			// Set specific header so that PUT method knows that an UPDATE and not a DELETE is required! 
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE, HeaderValues.MethodType.UPDATE.name());																																			
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true).put(ClientResponse.class, payloadStr);

			return setUpdateBulkResponse(response);
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
	 * @param resourceIDs A list of resourceId for the objects to be deleted.
	 * @param hdrProperties Header Properties to be added to the header of the DELETE request.
	 * @param zone The zone for which this operation shall be invoked. Can be null which indicates the DEFAULT zone.
	 * @param context The context for which this operation shall be invoked. Can be null which indicates the DEFAULT context.
	 * 
	 * @return Response Object holding appropriate values and results of the call. 
	 * 
	 * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
	 */
	public BulkOperationResponse<OperationStatus> removeMany(String relURI, List<String> resourceIDs, HeaderProperties hdrProperties, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		WebResource service = getService();
		try
		{
			service = buildURI(service, relURI, null, zone, context);
			
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
			
			// Set specific header so that PUT method knows that a DELETE and not an UPDATE is required! 
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE, HeaderValues.MethodType.DELETE.name());
			
			if (logger.isDebugEnabled())
			{
				logger.debug("removeMany: Payload to send:\n"+payloadStr);
			}
			ClientResponse cltResponse = setRequestHeaderAndMediaTypes(service, hdrProperties, true).put(ClientResponse.class, payloadStr);
			
			return setDeleteBulkResponse(cltResponse);
		}
		catch (Exception ex)
		{
			String errorMsg = "Failed to invoke 'removeMany' service (REST DELETE) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private BulkOperationResponse<CreateOperationStatus> setCreateBulkResponse(ClientResponse clientResponse)
	{
		BulkOperationResponse<CreateOperationStatus> response = new BulkOperationResponse<CreateOperationStatus>();
		setBaseResponseData(response, clientResponse);
		if (clientResponse.getClientResponseStatus().getStatusCode() == Status.CREATED.getStatusCode())
		{
			if (response.getHasEntity())
			{
				String payload = clientResponse.getEntity(String.class);
				try
				{						
					//Because CreateResponseType is a Infrastructure thing we must ensure we use the Infrastructure Unmarshaller
					CreateResponseType createManyResponse = (CreateResponseType)getInfraUnmarshaller().unmarshal(payload, CreateResponseType.class, getResponseMediaType());
					if (createManyResponse == null)// this is strange. So set the unmarshalled value.
					{
						response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload. See error description for payload details.", payload));							
					}
					else
					{
						response.setOperationStatuses(new ArrayList<CreateOperationStatus>());
						for (CreateType createStatus : createManyResponse.getCreates().getCreate())
						{
							CreateOperationStatus opStatus = new CreateOperationStatus();
							opStatus.setResourceID(createStatus.getId());
							opStatus.setAdvisoryID(createStatus.getAdvisoryId());
							opStatus.setStatus(toInt(createStatus.getStatusCode()));
							opStatus.setError(convertFromErrorType(createStatus.getError()));
							response.getOperationStatuses().add(opStatus);
						}
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

	private BulkOperationResponse<OperationStatus> setDeleteBulkResponse(ClientResponse clientResponse)
	{
		BulkOperationResponse<OperationStatus> response = new BulkOperationResponse<OperationStatus>();
		setBaseResponseData(response, clientResponse);
		if (clientResponse.getClientResponseStatus().getStatusCode() == Status.OK.getStatusCode())
		{
			if (response.getHasEntity())
			{
				String payload = clientResponse.getEntity(String.class);
				try
				{						
					//Because DeleteResponseType is a Infrastructure thing we must ensure we use the Infrastructure Unmarshaller
					DeleteResponseType deleteManyResponse = (DeleteResponseType)getInfraUnmarshaller().unmarshal(payload, DeleteResponseType.class, getResponseMediaType());
					if (deleteManyResponse == null)// this is strange. So set the unmarshalled value.
					{
						response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload. See error description for payload details.", payload));							
					}
					else
					{
						response.setOperationStatuses(new ArrayList<OperationStatus>());
						for (DeleteStatus deleteStatus : deleteManyResponse.getDeletes().getDelete())
						{
							OperationStatus opStatus = new OperationStatus();
							opStatus.setResourceID(deleteStatus.getId());
							opStatus.setStatus(toInt(deleteStatus.getStatusCode()));
							opStatus.setError(convertFromErrorType(deleteStatus.getError()));
							response.getOperationStatuses().add(opStatus);
						}
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

	private BulkOperationResponse<OperationStatus> setUpdateBulkResponse(ClientResponse clientResponse)
	{
		BulkOperationResponse<OperationStatus> response = new BulkOperationResponse<OperationStatus>();
		setBaseResponseData(response, clientResponse);
		if (clientResponse.getClientResponseStatus().getStatusCode() == Status.OK.getStatusCode())
		{
			if (response.getHasEntity())
			{
				String payload = clientResponse.getEntity(String.class);
				try
				{						
					//Because UpdateResponseType is a Infrastructure thing we must ensure we use the Infrastructure Unmarshaller
					UpdateResponseType updateManyResponse = (UpdateResponseType)getInfraUnmarshaller().unmarshal(payload, UpdateResponseType.class, getResponseMediaType());
					if (updateManyResponse == null)// this is strange. So set the unmarshalled value.
					{
						response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload. See error description for payload details.", payload));							
					}
					else
					{
						response.setOperationStatuses(new ArrayList<OperationStatus>());
						for (UpdateType updateStatus : updateManyResponse.getUpdates().getUpdate())
						{
							OperationStatus opStatus = new OperationStatus();
							opStatus.setResourceID(updateStatus.getId());
							opStatus.setStatus(toInt(updateStatus.getStatusCode()));
							opStatus.setError(convertFromErrorType(updateStatus.getError()));
							response.getOperationStatuses().add(opStatus);
						}
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

}
