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
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.exception.UnmarshalException;
import sif3.common.header.HeaderValues;
import sif3.common.header.TransportHeaderProperties;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.BaseResponse;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.model.CreateResponseType;
import sif3.infra.common.model.CreateType;
import sif3.infra.common.model.DeleteIdType;
import sif3.infra.common.model.DeleteRequestType;
import sif3.infra.common.model.DeleteResponseType;
import sif3.infra.common.model.DeleteStatus;
import sif3.infra.common.model.ErrorType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.UpdateResponseType;
import sif3.infra.common.model.UpdateType;
import sif3.infra.rest.header.RESTHeaderProperties;
import sif3.infra.rest.header.RequestHeaderConstants;
import sif3.infra.rest.header.ResponseHeaderConstants;
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
 * Each client call must use this class to ensure that all the things that make a SIF3 REST call valid are guaranteed.<br/><br/>
 * 
 * Note:<br/>
 * This class is the REST Client Implementation. Even though it is called 'Interface' it is not a proper interface. There is a
 * possibility that later versions of this framework will rename this class but since it is fully abstracted in the lower levels of
 * this framework and not exposed to the higher levels such a change will most likely not impact the services written by developers.
 * 
 * @author Joerg Huber
 */
public class ClientInterface
{
	protected final Logger logger = Logger.getLogger(getClass());

	private URI baseURI = null;
	private ClientConfig config = null;
	private Client client = null;
	private WebResource service = null;
	private MediaType mediaType = MediaType.APPLICATION_XML_TYPE;
	private MarshalFactory marshaller = null;
	private UnmarshalFactory unmarshaller = null;
	
	// This marshaller/unmarshaller are used in a number of operations where the response/request deals with Infrastructure Model Object: ErrorType, CreateResponseType etc.
	private InfraUnmarshalFactory infraUnmarshaller = new InfraUnmarshalFactory();
	private InfraMarshalFactory infraMarshaller = new InfraMarshalFactory();
	private ObjectFactory infraObjectFactory = new ObjectFactory();

	/**
	 * Constructor<br/>
	 * 
	 * @param baseURI The base URI of this client. All URIs are for all other calls are relative to this base URL.
	 * @param mediaType XML or JSOn are the expected media types. They must be supported by the given marshaller and unmarshaller.
	 * @param marshaller Marshaller to marshal the payload of this client to appropriate representations. This marshaller must be valid
	 *                   for the data model used with this client.
	 * @param unmarshaller Unmarshaller to unmarshal the payload of this client to appropriate representations. This unmarshaller 
	 *                     must be valid for the data model used with this client.
	 * @param secureConnection TRUE: Use HTTPS, FALSE use HTTP.
	 */
	public ClientInterface(URI baseURI, MediaType mediaType, MarshalFactory marshaller, UnmarshalFactory unmarshaller, boolean secureConnection)
	{
		super();

		this.baseURI = baseURI;
		this.mediaType = mediaType;
		this.marshaller = marshaller;
		this.unmarshaller = unmarshaller;
		
		ClientConfigMgr cltCfgMgr = new ClientConfigMgr();		
		this.config = cltCfgMgr.getClientConfig(secureConnection);

		// Create the Client Service
		this.client = Client.create(config);

		// Retrieve connector to resource
		this.service = client.resource(getBaseURI());
		
		logger.debug("Base URI for Call is: "+getBaseURI().toASCIIString());
	}

	/**
	 * This constructor will default to XML as media type and default service type of OBJECT
	 * 
	 * @param baseURI The base URI of this client. All URIs are for all other calls are relative to this base URL.
	 * @param marshaller Marshaller to marshal the payloads of this client to appropriate representations. This marshaller must be valid
	 *                   for the data model used with this client.
	 * @param unmarshaller Unarshaller to unmarshal the payloads of this client to appropriate representations. This unmarshaller 
	 *                     must be valid for the data model used with this client.
	 */
	public ClientInterface(URI baseURI, MarshalFactory marshaller, UnmarshalFactory unmarshaller, boolean secureConnection)
	{
		this(baseURI, MediaType.APPLICATION_XML_TYPE, marshaller, unmarshaller, secureConnection);
	}

	public URI getBaseURI()
	{
		return baseURI;
	}

	public void setBaseURI(URI baseURI)
	{
		this.baseURI = baseURI;
	}

	public Client getClient()
	{
		return client;
	}

	public WebResource getService()
	{
		return service;
	}

	public MediaType getMediaType()
	{
		return mediaType;
	}

	public void setMediaType(MediaType mediaType)
	{
		this.mediaType = mediaType;
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
	public Response getSingle(String relURI, String resourceID, TransportHeaderProperties hdrProperties, Class<?> returnObjectClass, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		try
		{
			service = buildURI(service, relURI, resourceID, zone, context);
			ClientResponse response = setRequestHeaderAndMediaTypes(hdrProperties).get(ClientResponse.class);

			return setResponse(response, returnObjectClass, Status.OK, Status.NOT_MODIFIED);
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
	 * @throws ServiceInvokationException Any underlying errors occurred such as unable to marshal the object into its mediatype, 
	 *                                    failure to invoke actual web-service etc. 
	 */
	public Response createSingle(String relURI, Object payload, TransportHeaderProperties hdrProperties, Class<?> returnObjectClass, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		try
		{
			service = buildURI(service, relURI, null, zone, context);
			String payloadStr = marshaller.marschal(payload, mediaType);

			// TODO: JH - Temporary fix to get Sandbox to work. Remove namespace from string. Remove line below once Sandbox is updated
			payloadStr = payloadStr.replace(" xmlns=\"http://www.sifassociation.org/infrastructure/3.0\"", "");
			ClientResponse response = setRequestHeaderAndMediaTypes(hdrProperties).post(ClientResponse.class, payloadStr);

			return setResponse(response, returnObjectClass, Status.CREATED, Status.CONFLICT);
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
	public Response updateSingle(String relURI, String resourceID, Object payload, TransportHeaderProperties hdrProperties, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		try
		{
			service = buildURI(service, relURI, resourceID, zone, context);
			String payloadStr = marshaller.marschal(payload, mediaType);

			// TODO: JH - Temporary fix to get Sandbox to work. Remove namespace from string. Remove line below once Sandbox is updated
			payloadStr = payloadStr.replace(" xmlns=\"http://www.sifassociation.org/infrastructure/3.0\"", "");
			ClientResponse response = setRequestHeaderAndMediaTypes(hdrProperties).put(ClientResponse.class, payloadStr);

			return setResponse(response, null, Status.NO_CONTENT);
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
	public Response removeSingle(String relURI, String resourceID, TransportHeaderProperties hdrProperties, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		try
		{
			service = buildURI(service, relURI, resourceID, zone, context);
		    ClientResponse response = setRequestHeaderAndMediaTypes(hdrProperties).delete(ClientResponse.class);

			return setResponse(response, null, Status.NO_CONTENT);
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
	public Response getMany(String relURI, PagingInfo pagingInfo, TransportHeaderProperties hdrProperties, Class<?> returnObjectClass, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		try
		{
			service = buildURI(service, relURI, null, zone, context);
		    if (pagingInfo != null)
			{
				QueryMetadata query = new QueryMetadata();
				query.setPagingInfo(pagingInfo);
				service = service.queryParams(query.getQueryParametersAsMultivaluedMap());
			}
			
			ClientResponse response = setRequestHeaderAndMediaTypes(hdrProperties).get(ClientResponse.class);

			return setResponse(response, returnObjectClass, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT);
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
	public BulkOperationResponse createMany(String relURI, Object payload, TransportHeaderProperties hdrProperties, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		try
		{
			service = buildURI(service, relURI, null, zone, context);
		    String payloadStr = marshaller.marschal(payload, mediaType);

			// TODO: JH - Temporary fix to get Sandbox to work. Remove namespace from string. Remove line below once Sandbox is updated
			payloadStr = payloadStr.replace(" xmlns=\"http://www.sifassociation.org/infrastructure/3.0\"", "");
			ClientResponse response = setRequestHeaderAndMediaTypes(hdrProperties).post(ClientResponse.class, payloadStr);

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
	public BulkOperationResponse updateMany(String relURI, Object payload, TransportHeaderProperties hdrProperties, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
		try
		{
			service = buildURI(service, relURI, null, zone, context);

			String payloadStr = marshaller.marschal(payload, mediaType);

			// TODO: JH - Temporary fix to get Sandbox to work. Remove namespace from string. Remove line below once Sandbox is updated
			payloadStr = payloadStr.replace(" xmlns=\"http://www.sifassociation.org/infrastructure/3.0\"", "");

			// Set specific header so that PUT method knows that an UPDATE and not a DELETE is required! 
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE, HeaderValues.MethodType.UPDATE.name());

																																					ClientResponse response = setRequestHeaderAndMediaTypes(hdrProperties).put(ClientResponse.class, payloadStr);

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
	 * This invokes the REST DELETE call. This method is used to delete many objects in one call as defined by the SIF3 spec. The
	 * returned list of responses equate to one response per object in the given payload. The order of the responses is the same as the
	 * order in the original payload. The first response in the BulkOperationResponse list is the response to the create of the first
	 * object in the payload etc.<br/><br/>
	 * 
	 * There is an issue with java.net.HttpURLConnection where it doesn't allow an payload for the DELETE! So currently the implementation of
	 * the removeMany fakes such a behaviour and actually calls individual DELETEs. This is not ideal but until a work around is figured out
	 * that is what this method does.
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
	public BulkOperationResponse removeMany(String relURI, List<String> resourceIDs, TransportHeaderProperties hdrProperties, SIFZone zone, SIFContext context) throws ServiceInvokationException
	{
    /*-----------------------------------------------------------------------------------------------------------------------------
     * Below is the dummy implementation (now commented out). It is what would be done if delete is called one by one because
     * bulk-deletes are not allowed with the HTTP DELETE. Just Google for "java.net.ProtocolException: HTTP method DELETE doesn't 
     * support output" for details. This is the exception thrown with the code below.
     -----------------------------------------------------------------------------------------------------------------------------*/
/*    
		BulkOperationResponse bulkResponse = new BulkOperationResponse();
		bulkResponse.setStatus(Status.OK.getStatusCode());
		bulkResponse.setStatusMessage(Status.OK.getReasonPhrase());
		bulkResponse.setMediaType(getMediaType());
		bulkResponse.setContentLength(-1);
		bulkResponse.setHasEntity(true);		

		bulkResponse.setOperationStatuses(new ArrayList<OperationStatus>());
		int i = 0;
		if (resourceIDs != null)
		{
			for (String resourceID : resourceIDs)
			{
				Response singleOpResponse = removeSingle(relURI, resourceID, hdrProperties, zone, context);
				if (i == 0)
				{
					bulkResponse.setHdrProperties(singleOpResponse.getHdrProperties());					
				}
				bulkResponse.getOperationStatuses().add(new OperationStatus(resourceID, singleOpResponse.getStatus(), singleOpResponse.getError()));
				
				//need to 'reset' the service with the base URL
				this.service = client.resource(getBaseURI());
			}
		}
		
		return bulkResponse;
*/		
    /*-----------------------------------------------------------------------------------------------------------------------------
     * End original code (now commented out).
     -----------------------------------------------------------------------------------------------------------------------------*/
		try
		{
      service = buildURI(service, relURI, null, zone, context);
			
			//Convert List of resources to DeletesTypes
			DeleteRequestType deleteRequest = infraObjectFactory.createDeleteRequestType();
			deleteRequest.setDeletes(infraObjectFactory.createDeleteIdCollection());
			
			if (resourceIDs != null)
			{
			  for (String resourceID : resourceIDs)
			  {
			    DeleteIdType id = infraObjectFactory.createDeleteIdType();
			    id.setId(resourceID);
			    deleteRequest.getDeletes().getDelete().add(id);
			  }
			}
			String payloadStr = infraMarshaller.marschal(deleteRequest, mediaType);
			
			// TODO: JH - Temporary fix to get Sandbox to work. Remove namespace from string. Remove line below once Sandbox is updated
			payloadStr = payloadStr.replace(" xmlns=\"http://www.sifassociation.org/infrastructure/3.0\"", "");
			//System.out.println("Delete Payload: "+payloadStr);
			
	    // Set specific header so that PUT method knows that a DELETE and not an UPDATE is required! 
			hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE, HeaderValues.MethodType.DELETE.name());
			
			ClientResponse cltResponse = setRequestHeaderAndMediaTypes(hdrProperties).put(ClientResponse.class, payloadStr);
			
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
	private WebResource buildURI(WebResource svc, String relURI, String resourceID, SIFZone zone, SIFContext ctx)
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

		return svc.uri(uriBuilder.build());
	}
		
	private Builder setRequestHeaderAndMediaTypes(TransportHeaderProperties hdrProperties)
	{
		//System.out.println("MediaType: "+getMediaType());
		Builder builder = getService().type(getMediaType()).accept(getMediaType());
		
		// Always set the requestId.
		builder = builder.header(RequestHeaderConstants.HDR_REQUEST_ID, UUIDGenerator.getUUID());

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
	
	private BulkOperationResponse setCreateBulkResponse(ClientResponse clientResponse)
	{
		BulkOperationResponse response = new BulkOperationResponse();
		setBaseResponseData(response, clientResponse);
		if (clientResponse.getClientResponseStatus().getStatusCode() == Status.CREATED.getStatusCode())
		{
			if (response.getHasEntity())
			{
				String payload = clientResponse.getEntity(String.class);
				try
				{						
					//Because CreateResponseType is a Infrastructure thing we must ensure we use the Infrastructure Unmarshaller
					CreateResponseType createManyResponse = (CreateResponseType)infraUnmarshaller.unmarschal(payload, CreateResponseType.class, getMediaType());
					if (createManyResponse == null)// this is strange. So set the unmarshalled value.
					{
						response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload. See error description for payload details.", payload));							
					}
					else
					{
						response.setOperationStatuses(new ArrayList<OperationStatus>());
						for (CreateType createStatus : createManyResponse.getCreates().getCreate())
						{
							OperationStatus opStatus = new OperationStatus();
							opStatus.setResourceID(createStatus.getId());
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

  private BulkOperationResponse setDeleteBulkResponse(ClientResponse clientResponse)
	{
		BulkOperationResponse response = new BulkOperationResponse();
		setBaseResponseData(response, clientResponse);
		if (clientResponse.getClientResponseStatus().getStatusCode() == Status.OK.getStatusCode())
		{
			if (response.getHasEntity())
			{
				String payload = clientResponse.getEntity(String.class);
				try
				{						
					//Because DeleteResponseType is a Infrastructure thing we must ensure we use the Infrastructure Unmarshaller
					DeleteResponseType deleteManyResponse = (DeleteResponseType)infraUnmarshaller.unmarschal(payload, DeleteResponseType.class, getMediaType());
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

	private BulkOperationResponse setUpdateBulkResponse(ClientResponse clientResponse)
	{
		BulkOperationResponse response = new BulkOperationResponse();
		setBaseResponseData(response, clientResponse);
		if (clientResponse.getClientResponseStatus().getStatusCode() == Status.OK.getStatusCode())
		{
			if (response.getHasEntity())
			{
				String payload = clientResponse.getEntity(String.class);
				try
				{						
					//Because UpdateResponseType is a Infrastructure thing we must ensure we use the Infrastructure Unmarshaller
					UpdateResponseType updateManyResponse = (UpdateResponseType)infraUnmarshaller.unmarschal(payload, UpdateResponseType.class, getMediaType());
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

	private Response setResponse(ClientResponse clientResponse, Class<?> returnObjectClass, Status... successStatusCodes)
	{
		Response response = new Response();
		setBaseResponseData(response, clientResponse);
		response.setResourceURI(getService().getURI());
		response.setDataObjectType(returnObjectClass);

		if (isSuccessStatusCode(clientResponse.getClientResponseStatus().getStatusCode(), successStatusCodes))
		{
			if (response.getHasEntity())
			{
				String payload = clientResponse.getEntity(String.class);
				if (returnObjectClass != null)
				{
					try
					{
						response.setDataObject(unmarshaller.unmarschal(payload, returnObjectClass, getMediaType()));
						if (response.getDataObject() == null)// this is strange. So set the unmarshalled value.
						{
							response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload. See error description for payload details.", payload));
						}
					}
					catch (UnmarshalException ex)
					{
						response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload: "+ex.getMessage()+". See error description for payload details.", payload));
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

	private TransportHeaderProperties extractHeaderInfo(ClientResponse clientResponse)
	{
		TransportHeaderProperties hdrProps = new RESTHeaderProperties();
		MultivaluedMap<String, String> respHdrProps = clientResponse.getHeaders();
		if ((respHdrProps != null))
		{
			for (String propName : ResponseHeaderConstants.HEADER_NAME_ARRAY)
			{
				String hdrValue = respHdrProps.getFirst(propName); // there should only be one value for each of these properties
				if (hdrValue != null)
				{
					hdrProps.setHeaderProperty(propName, hdrValue);
				}
			}
		}
		return hdrProps;
	}
	
	private ErrorDetails convertFromErrorType(ErrorType errorType)
	{
		if (errorType == null)
		{
			return null;
		}
		return new ErrorDetails((int)errorType.getCode(), errorType.getMessage(), errorType.getDescription(), errorType.getScope());
	}
	
	private void setBaseResponseData(BaseResponse response, ClientResponse clientResponse)
	{
		response.setStatus(clientResponse.getClientResponseStatus().getStatusCode());
		response.setStatusMessage(clientResponse.getClientResponseStatus().getReasonPhrase());
		response.setMediaType(clientResponse.getType());
		response.setContentLength(clientResponse.getLength());

		// Extract header properties.
		response.setHdrProperties(extractHeaderInfo(clientResponse));
		response.setHasEntity(clientResponse.hasEntity() && (clientResponse.getClientResponseStatus().getStatusCode() != Status.NO_CONTENT.getStatusCode()));		
	}
	
	private void setErrorResponse(BaseResponse response, ClientResponse clientResponse)
	{
		if (response.getHasEntity()) // Attempt to unmarshal the error
		{
			String errorStr = clientResponse.getEntity(String.class);
			try
			{
				//Because ErrorType is a Infrastructure thing we must ensure we use the Infrastructure Unmarshaller
				ErrorType error = (ErrorType) infraUnmarshaller.unmarschal(errorStr, ErrorType.class, getMediaType());
				if (error == null) // this is strange. So set the unmarshalled value.
				{
					response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload into ErrorType object. See error description for payload details.", errorStr));
				}
				else
				{
					response.setError(convertFromErrorType(error));
				}
			}
			catch (UnmarshalException ex)
			{
				response.setError(new ErrorDetails(response.getStatus(), "Could not unmarshal payload into ErrorType object: "+ex.getMessage()+". See error description for payload details.", errorStr));
			}
		}
		else // It appears we have an error but no content. So create an error object with custom message.
		{
			response.setError(new ErrorDetails(response.getStatus(), "Error occured: " + response.getStatusMessage(), "No Content in web-service response."));
		}
	}
	
	private boolean isSuccessStatusCode(int statusCode, Status... successStatusCodes)
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
	
	private int toInt(String intStr)
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
}
