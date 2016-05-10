
package sif3.infra.rest.client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderProperties;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.URLQueryParameter;
import sif3.common.ws.Response;
import sif3.infra.common.interfaces.ClientEnvironmentManager;

public class FunctionalServiceClient extends ObjectServiceClient {

	public FunctionalServiceClient(ClientEnvironmentManager clientEnvMgr, URI baseURI, MediaType requestMediaType, MediaType responseMediaType, MarshalFactory dmMarshaller, UnmarshalFactory dmUnmarshaller, boolean secureConnection, boolean useCompression) {
		super(clientEnvMgr, baseURI, requestMediaType, responseMediaType, dmMarshaller, dmUnmarshaller, secureConnection, useCompression);
	}

	public FunctionalServiceClient(ClientEnvironmentManager clientEnvMgr, URI baseURI, MarshalFactory dmMarshaller, UnmarshalFactory dmUnmarshaller, boolean secureConnection, boolean useCompression) {
		super(clientEnvMgr, baseURI, dmMarshaller, dmUnmarshaller, secureConnection, useCompression);
	}

	protected WebResource buildURI(WebResource svc, String relURI, String resourceID, String phaseName, SIFZone zone, SIFContext ctx, URLQueryParameter urlQueryParams) {
		svc = buildURI(svc, relURI, resourceID, zone, ctx, urlQueryParams);
		UriBuilder uriBuilder = svc.getUriBuilder();
		if (StringUtils.notEmpty(phaseName)) {
			uriBuilder.path("phases");
			uriBuilder.path(phaseName);
		}

		return svc.uri(uriBuilder.build());
	}

	/*--------------------------*/
	/*-- Operations on Phases --*/
	/*--------------------------*/

	public Response createToPhase(String relURI, String resourceID, String phaseName, String payload, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException {
		WebResource service = getService();
		try {
			service = buildURI(service, relURI, resourceID, phaseName, zone, context, urlQueryParams);

			if (logger.isDebugEnabled()) {
				logger.debug("createToPhase: Payload to send:\n" + payload);
			}

			hdrProperties = addAuthenticationHdrProps(hdrProperties);
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true).post(ClientResponse.class, payload);

			return setResponse(service, response, String.class, hdrProperties, zone, context, true, Status.CREATED, Status.CONFLICT);
		} catch (Exception ex) {
			String errorMsg = "Failed to invoke 'createToPhase' service (REST POST) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	public Response retrieveToPhase(String relURI, String resourceID, String phaseName, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException {
		WebResource service = getService();
		try {
			service = buildURI(service, relURI, resourceID, phaseName, zone, context, urlQueryParams);
			hdrProperties = addAuthenticationHdrProps(hdrProperties);
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, false).get(ClientResponse.class);

			return setResponse(service, response, String.class, hdrProperties, zone, context, true, Status.OK, Status.NOT_MODIFIED);
		} catch (Exception ex) {
			String errorMsg = "Failed to invoke 'retrieveToPhase' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	public Response updateToPhase(String relURI, String resourceID, String phaseName, String payload, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException {
		WebResource service = getService();
		try {
			service = buildURI(service, relURI, resourceID, phaseName, zone, context, urlQueryParams);

			if (logger.isDebugEnabled()) {
				logger.debug("updateToPhase: Payload to send:\n" + payload);
			}

			hdrProperties = addAuthenticationHdrProps(hdrProperties);
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true).put(ClientResponse.class, payload);

			return setResponse(service, response, String.class, hdrProperties, zone, context, true, Status.OK, Status.NO_CONTENT);
		} catch (Exception ex) {
			String errorMsg = "Failed to invoke 'updateToPhase' service (REST PUT) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}

	public Response deleteToPhase(String relURI, String resourceID, String phaseName, String payload, HeaderProperties hdrProperties, URLQueryParameter urlQueryParams, SIFZone zone, SIFContext context) throws ServiceInvokationException {
		WebResource service = getService();
		try {
			service = buildURI(service, relURI, resourceID, phaseName, zone, context, urlQueryParams);

			hdrProperties = addAuthenticationHdrProps(hdrProperties);
			ClientResponse response = setRequestHeaderAndMediaTypes(service, hdrProperties, true, true).delete(ClientResponse.class, payload);

			return setResponse(service, response, String.class, hdrProperties, zone, context, true, Status.OK, Status.NO_CONTENT);
		} catch (Exception ex) {
			String errorMsg = "Failed to invoke 'deleteToPhase' service (REST DELETE) on URI " + service.getURI() + ": " + ex.getMessage();
			logger.error(errorMsg);
			throw new ServiceInvokationException(errorMsg, ex);
		}
	}
}