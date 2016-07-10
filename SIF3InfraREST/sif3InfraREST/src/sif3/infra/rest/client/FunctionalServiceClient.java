/*
 * Crown Copyright © Department for Education (UK) 2016
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

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.MethodType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.URLQueryParameter;
import sif3.common.persist.model.SIF3PhaseState;
import sif3.common.ws.Response;
import sif3.infra.common.interfaces.ClientEnvironmentManager;
import sif3.infra.common.model.StateType;
import sif3.infra.common.utils.ServiceUtils;

public class FunctionalServiceClient extends ObjectServiceClient
{
    /**
     * Constructor
     * 
     * @param clientEnvMgr
     *            Session manager to access the clients session information.
     * @param baseURI
     *            The base URI of this client. All URIs are for all other calls are relative to this
     *            base URL.
     * @param requestMediaType
     *            Media type of the request. It will be validated against the supported media types
     *            of the given dmMarshaller.
     * @param responseMediaType
     *            Media type of the response. It will be validated against the supported media types
     *            of the given dmUnmarshaller.
     * @param dmMarshaller
     *            Marshaller to marshal the payload of this client to appropriate representations.
     *            This marshaller must be valid for the data model used with this client.
     * @param dmUnmarshaller
     *            Unmarshaller to unmarshal the payload of this client to appropriate
     *            representations. This unmarshaller must be valid for the data model used with this
     *            client.
     * @param secureConnection
     *            TRUE: Use HTTPS, FALSE use HTTP.
     * @param useCompression
     *            TRUE: Payloads (request & response) shall be compressed before sending or
     *            de-compressed at the time of receiving. FALSE: No compression is used.
     */
    public FunctionalServiceClient(ClientEnvironmentManager clientEnvMgr, URI baseURI,
            MediaType requestMediaType, MediaType responseMediaType, MarshalFactory dmMarshaller,
            UnmarshalFactory dmUnmarshaller, boolean secureConnection, boolean useCompression)
    {
        super(clientEnvMgr, baseURI, requestMediaType, responseMediaType, dmMarshaller,
                dmUnmarshaller, secureConnection, useCompression);
    }

    /**
     * This constructor will default the media type of the marshaller (request) and unmarshaller
     * (response) and the default service type of OBJECT.
     * 
     * @param clientEnvMgr
     *            Session manager to access the clients session information.
     * @param baseURI
     *            The base URI of this client. All URIs are for all other calls are relative to this
     *            base URL.
     * @param dmMarshaller
     *            Marshaller to marshal the payload of this client to appropriate representations.
     *            This marshaller must be valid for the data model used with this client.
     * @param dmUnmarshaller
     *            Unmarshaller to unmarshal the payload of this client to appropriate
     *            representations. This unmarshaller must be valid for the data model used with this
     *            client.
     * @param secureConnection
     *            TRUE: Use HTTPS, FALSE use HTTP.
     * @param useCompression
     *            TRUE: Payloads (request & response) shall be compressed before sending or
     *            de-compressed at the time of receiving. FALSE: No compression is used.
     */
    public FunctionalServiceClient(ClientEnvironmentManager clientEnvMgr, URI baseURI,
            MarshalFactory dmMarshaller, UnmarshalFactory dmUnmarshaller, boolean secureConnection,
            boolean useCompression)
    {
        super(clientEnvMgr, baseURI, dmMarshaller, dmUnmarshaller, secureConnection,
                useCompression);
    }

    /**
     * Builds a URI for a functional service, overloads the method of the same name in
     * {@link sif3.infra.rest.client.BaseClient BaseClient}
     * 
     * @param svc
     *            The prefix of the URL, contains the protocol, domain, port number, etc.
     * @param relURI
     *            The functional service name (plural)
     * @param resourceID
     *            The GUID of a resource as a string
     * @param phaseName
     *            The name of the phase to access
     * @param zone
     *            The zone for the request URI
     * @param ctx
     *            The context for the request URI
     * @param urlQueryParams
     *            Any additional query parameters to include
     * @return The built request URI as a WebResource
     */
    protected WebResource buildURI(WebResource svc, String relURI, String resourceID,
            String phaseName, SIFZone zone, SIFContext ctx, URLQueryParameter urlQueryParams)
    {
        svc = buildURI(svc, relURI, resourceID, zone, ctx, urlQueryParams);
        UriBuilder uriBuilder = svc.getUriBuilder();
        uriBuilder.path(phaseName);

        return svc.uri(uriBuilder.build());
    }

    /**
     * Builds a URI for a functional service, overloads the method of the same name in
     * {@link sif3.infra.rest.client.BaseClient BaseClient}
     * 
     * @param svc
     *            The prefix of the URL, contains the protocol, domain, port number, etc.
     * @param relURI
     *            The functional service name (plural)
     * @param resourceID
     *            The GUID of a resource as a string
     * @param phaseName
     *            The name of the phase to access
     * @param statesMethod
     *            A {@link sif3.common.header.HeaderValues.MethodType MethodType} instance that is
     *            used to decide if the end of the URI should be /states or /states/state (i.e. in
     *            the case of a create request).
     * @param zone
     *            The zone for the request URI
     * @param ctx
     *            The context for the request URI
     * @param urlQueryParams
     *            Any additional query parameters to include
     * @return The built request URI as a WebResource
     */
    protected WebResource buildURI(WebResource svc, String relURI, String resourceID,
            String phaseName, MethodType statesMethod, SIFZone zone, SIFContext ctx,
            URLQueryParameter urlQueryParams)
    {
        svc = buildURI(svc, relURI, resourceID, zone, ctx, urlQueryParams);
        UriBuilder uriBuilder = svc.getUriBuilder();
        uriBuilder.path(phaseName);
        uriBuilder.path("states");
        if (statesMethod == MethodType.POST)
        {
            uriBuilder.path("state");
        }

        return svc.uri(uriBuilder.build());
    }

    /*--------------------------*/
    /*-- Operations on Phases --*/
    /*--------------------------*/
    /**
     * Will invoke the REST POST method. This expects the payload to have already been marshaled to
     * a string before this is called, and appropriate format (mediaType) information added to
     * 'hdrProperties'. If there are any errors then the ServiceInvokationException is raised and
     * the error is logged.
     * 
     * @param relURI
     *            A relative URI to the baseURI given to the constructor of this class. It is
     *            appended to the baseURI as is.
     * @param resourceID
     *            The refid of the job (functional service instance) to send the request to.
     * @param phaseName
     *            The name of the phase to send the request to.
     * @param payload
     *            The payload in its marshaled string representation.
     * @param hdrProperties
     *            Header Properties to be added to the header of the request.
     * @param urlQueryParams
     *            URL query parameters to be added to the request. It is assumed that these are
     *            custom URL query parameters. They are conveyed to the provider unchanged. URL
     *            query parameter names are case sensitive. This parameter can be null.
     * @param zone
     *            The zone for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT zone.
     * @param context
     *            The context for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT context.
     * 
     * @return Response Object holding appropriate values and results of the call.
     * 
     * @throws ServiceInvokationException
     *             Any underlying errors occurred such failure to invoke actual web-service etc.
     */
    public Response createToPhase(String relURI, String resourceID, String phaseName,
            String payload, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams,
            SIFZone zone, SIFContext context) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, relURI, resourceID, phaseName, zone, context,
                    urlQueryParams);

            if (logger.isDebugEnabled())
            {
                logger.debug("createToPhase: Payload to send:\n" + payload);
            }

            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true,
                    true).post(ClientResponse.class, payload);

            return setResponse(service, response, String.class, hdrProperties, zone, context, true,
                    Status.CREATED, Status.CONFLICT);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'createToPhase' service (REST POST) on URI "
                    + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }

    /**
     * Will invoke the REST GET method (A POST with the header changed). A payload may be supplied
     * to identify what information might be required from the get request. The payload must have
     * already been marshaled to a string before this is called, and appropriate format (mediaType)
     * information added to 'hdrProperties'. If there are any errors then the
     * ServiceInvokationException is raised and the error is logged.
     * 
     * @param relURI
     *            A relative URI to the baseURI given to the constructor of this class. It is
     *            appended to the baseURI as is.
     * @param resourceID
     *            The refid of the job (functional service instance) to send the request to.
     * @param phaseName
     *            The name of the phase to send the request to.
     * @param payload
     *            The payload in its marshaled string representation.
     * @param hdrProperties
     *            Header Properties to be added to the header of the request.
     * @param urlQueryParams
     *            URL query parameters to be added to the request. It is assumed that these are
     *            custom URL query parameters. They are conveyed to the provider unchanged. URL
     *            query parameter names are case sensitive. This parameter can be null.
     * @param zone
     *            The zone for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT zone.
     * @param context
     *            The context for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT context.
     * 
     * @return Response Object holding appropriate values and results of the call.
     * 
     * @throws ServiceInvokationException
     *             Any underlying errors occurred such failure to invoke actual web-service etc.
     */
    public Response retrieveToPhase(String relURI, String resourceID, String phaseName,
            String payload, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams,
            SIFZone zone, SIFContext context) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, relURI, resourceID, phaseName, zone, context,
                    urlQueryParams);
            hdrProperties = addAuthenticationHdrProps(hdrProperties);

            // ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true,
            // false).get(ClientResponse.class);
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE,
                    HeaderValues.MethodType.GET.name());
            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true,
                    false).post(ClientResponse.class, payload);

            return setResponse(service, response, String.class, hdrProperties, zone, context, true,
                    Status.OK, Status.NOT_MODIFIED);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'retrieveToPhase' service (REST GET) on URI "
                    + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }

    /**
     * Will invoke the REST PUT method. A payload may be supplied, but must have already been
     * marshaled to a string before this is called, and appropriate format (mediaType) information
     * added to 'hdrProperties'. If there are any errors then the ServiceInvokationException is
     * raised and the error is logged.
     * 
     * @param relURI
     *            A relative URI to the baseURI given to the constructor of this class. It is
     *            appended to the baseURI as is.
     * @param resourceID
     *            The refid of the job (functional service instance) to send the request to.
     * @param phaseName
     *            The name of the phase to send the request to.
     * @param payload
     *            The payload in its marshaled string representation.
     * @param hdrProperties
     *            Header Properties to be added to the header of the request.
     * @param urlQueryParams
     *            URL query parameters to be added to the request. It is assumed that these are
     *            custom URL query parameters. They are conveyed to the provider unchanged. URL
     *            query parameter names are case sensitive. This parameter can be null.
     * @param zone
     *            The zone for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT zone.
     * @param context
     *            The context for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT context.
     * 
     * @return Response Object holding appropriate values and results of the call.
     * 
     * @throws ServiceInvokationException
     *             Any underlying errors occurred such failure to invoke actual web-service etc.
     */
    public Response updateToPhase(String relURI, String resourceID, String phaseName,
            String payload, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams,
            SIFZone zone, SIFContext context) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, relURI, resourceID, phaseName, zone, context,
                    urlQueryParams);

            if (logger.isDebugEnabled())
            {
                logger.debug("updateToPhase: Payload to send:\n" + payload);
            }

            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true,
                    true).put(ClientResponse.class, payload);

            return setResponse(service, response, String.class, hdrProperties, zone, context, true,
                    Status.OK, Status.NO_CONTENT);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'updateToPhase' service (REST PUT) on URI "
                    + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }

    /**
     * Will invoke the REST DELETE method (a PUT with the header changed). A payload may be
     * supplied, but must have already been marshaled to a string before this is called, and
     * appropriate format (mediaType) information added to 'hdrProperties'. If there are any errors
     * then the ServiceInvokationException is raised and the error is logged.
     * 
     * @param relURI
     *            A relative URI to the baseURI given to the constructor of this class. It is
     *            appended to the baseURI as is.
     * @param resourceID
     *            The refid of the job (functional service instance) to send the request to.
     * @param phaseName
     *            The name of the phase to send the request to.
     * @param payload
     *            The payload in its marshaled string representation.
     * @param hdrProperties
     *            Header Properties to be added to the header of the request.
     * @param urlQueryParams
     *            URL query parameters to be added to the request. It is assumed that these are
     *            custom URL query parameters. They are conveyed to the provider unchanged. URL
     *            query parameter names are case sensitive. This parameter can be null.
     * @param zone
     *            The zone for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT zone.
     * @param context
     *            The context for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT context.
     * 
     * @return Response Object holding appropriate values and results of the call.
     * 
     * @throws ServiceInvokationException
     *             Any underlying errors occurred such failure to invoke actual web-service etc.
     */
    public Response deleteToPhase(String relURI, String resourceID, String phaseName,
            String payload, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams,
            SIFZone zone, SIFContext context) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, relURI, resourceID, phaseName, zone, context,
                    urlQueryParams);

            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true,
                    true).delete(ClientResponse.class, payload);

            return setResponse(service, response, String.class, hdrProperties, zone, context, true,
                    Status.OK, Status.NO_CONTENT);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'deleteToPhase' service (REST DELETE) on URI "
                    + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }

    /*--------------------------*/
    /*-- Operations on States --*/
    /*--------------------------*/

    /**
     * Will invoke the REST POST method. A state must be provided which will be marshaled before it
     * is sent as the request payload. Appropriate format (mediaType) information will be added to
     * 'hdrProperties'. If there are any errors then the ServiceInvokationException is raised and
     * the error is logged.
     * 
     * @param relURI
     *            A relative URI to the baseURI given to the constructor of this class. It is
     *            appended to the baseURI as is.
     * @param resourceID
     *            The refid of the job (functional service instance) to send the request to.
     * @param phaseName
     *            The name of the phase to send the request to.
     * @param state
     *            The state to marshal and send to as the payload of the request.
     * @param hdrProperties
     *            Header Properties to be added to the header of the request.
     * @param urlQueryParams
     *            URL query parameters to be added to the request. It is assumed that these are
     *            custom URL query parameters. They are conveyed to the provider unchanged. URL
     *            query parameter names are case sensitive. This parameter can be null.
     * @param zone
     *            The zone for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT zone.
     * @param context
     *            The context for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT context.
     * 
     * @return Response Object holding appropriate values and results of the call.
     * 
     * @throws ServiceInvokationException
     *             Any underlying errors occurred such failure to invoke actual web-service etc.
     */
    public Response createToState(String relURI, String resourceID, String phaseName,
            SIF3PhaseState state, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams,
            SIFZone zone, SIFContext context) throws ServiceInvokationException
    {
        WebResource service = getService();
        try
        {
            service = buildURI(service, relURI, resourceID, phaseName, MethodType.POST, zone,
                    context, urlQueryParams);

            String payload = getDataModelMarshaller().marshal(ServiceUtils.marshal(state), getRequestMediaType());

            if (logger.isDebugEnabled())
            {
                logger.debug("createToState: Payload to send:\n" + payload);
            }

            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true,
                    true).post(ClientResponse.class, payload);

            return setResponse(service, response, StateType.class, hdrProperties, zone, context,
                    true, Status.CREATED, Status.CONFLICT);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'createToState' service (REST POST) on URI "
                    + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }
}