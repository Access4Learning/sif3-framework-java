/*
 * DataModelResource.java Created: 24/09/2013 Copyright 2013 Systemic Pty Ltd
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package sif3.infra.rest.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import sif3.common.exception.BadRequestException;
import sif3.common.exception.ForbiddenException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIF3Exception;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.ws.ErrorDetails;
import sif3.infra.rest.provider.BaseJobProvider;

/**
 * This is the generic implementation of all Service resources (i.e. functional
 * services).
 * 
 * This class the assumption that the base URI for all service providers is of
 * the form:<br/>
 * http://<baseURI>/services/<ServiceName>...<br/>
 * <br/>
 * 
 * It must be ensured that in all the environments managed with this framework
 * that the "service" connector URI follows this structure.
 */
@Path("/services/{infraObjectNamePlural:([^\\./]*)}{mimeType:(\\.[^/]*?)?}")
public class ServiceResource extends InfraObjectResource {
	
	public ServiceResource(@Context UriInfo uriInfo, @Context HttpHeaders requestHeaders, @Context Request request, @PathParam("infraObjectNamePlural") String infraObjectNamePlural, @PathParam("mimeType") String mimeType, @MatrixParam("zoneId") String zoneID, @MatrixParam("contextId") String contextID) {
		super(uriInfo, requestHeaders, request, "services", infraObjectNamePlural, mimeType, zoneID, contextID);
	}

	// ------------ //
	// -- Jobs -- //
	// ------------ //
	@Override
	@POST
	@Path("{infraObjectNameSingle:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response createSingle(String payload, @PathParam("infraObjectNameSingle") String infraObjectNameSingle, @PathParam("mimeType") String mimeType) {
		return super.createSingle(payload, infraObjectNameSingle, mimeType);
	}

	@Override
	@POST
	public Response createMany(String payload) {
		return super.createMany(payload);
	}

	@Override
	@GET
	@Path("{resourceID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response getSingle(@PathParam("resourceID") String resourceID, @PathParam("mimeType") String mimeType) {
		return super.getSingle(resourceID, mimeType);
	}

	@Override
	@GET
	public Response getMany() {
		return super.getMany();
	}

	@Override
	@PUT
	@Path("{resourceID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response updateSingle(String payload, @PathParam("resourceID") String resourceID, @PathParam("mimeType") String mimeType) {
		throw new UnsupportedOperationException("Update operations not supported on functional services");
	}

	@Override
	@PUT
	public Response updateMany(String payload) {
		throw new UnsupportedOperationException("Update operations not supported on functional services");
	}

	@Override
	@DELETE
	@Path("{resourceID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response removeSingle(@PathParam("resourceID") String resourceID, @PathParam("mimeType") String mimeType) {
		return super.removeSingle(resourceID, mimeType);
	}

	@Override
	@DELETE
	public Response removeMany(String payload) {
		return super.removeMany(payload);
	}

	// ------------ //
	// -- Phases -- //
	// ------------ //
	@POST
	@Path("{resourceID:([^\\.]*)}/phases/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response createToPhase(String payload, @PathParam("resourceID") String resourceID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Create to phase " + phaseName + " (REST POST) with URL Postfix mimeType = '" + mimeType + "' and input data: " + payload);
		}

		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.CREATE), AccessType.APPROVED, false);
		if (error != null) {
			return makeErrorResponse(error, ResponseAction.CREATE);
		}

		BaseJobProvider provider = (BaseJobProvider) getProvider();
		if (provider == null) {
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.CREATE);
		}

		// Ignore the marshaller/unmarshaller - just reflect the contentType and
		// accept header information
		determineMediaTypes(null, null, false);

		try {
			String returnPayload = provider.createToPhase(resourceID, phaseName, payload, getRequestMediaType(), getResponseMediaType(), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false));
			return makeResponse(returnPayload, Status.CREATED.getStatusCode(), false, ResponseAction.CREATE, null);
		} catch (PersistenceException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create phase for job " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.CREATE);
		} catch (UnmarshalException ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the data. Problem reported: " + ex.getMessage()), ResponseAction.CREATE);
		} catch (UnsupportedMediaTypeException ex) {
			return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal the data. Problem reported: " + ex.getMessage()), ResponseAction.CREATE);
		} catch (UnsupportedQueryException ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Unsupported operation. Problem reported: " + ex.getMessage()), ResponseAction.CREATE);
		} catch (BadRequestException ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Bad request. Problem reported: " + ex.getMessage()), ResponseAction.CREATE);
		} catch (ForbiddenException ex) {
			return makeErrorResponse(new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", AccessRight.CREATE.name() + " access is not set to " + AccessType.APPROVED.name() + " for the " + infraObjectNamePlural + " functional service", "Provider side check."), ResponseAction.CREATE);
		} catch (SIF3Exception ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Unexpected error. Problem reported: " + ex.getMessage()), ResponseAction.CREATE);
		}
	}

	@GET
	@Path("{resourceID:([^\\.]*)}/phases/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response retrieveToPhase(@PathParam("resourceID") String resourceID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Get phase " + phaseName + " by Job ID (REST GET): " + resourceID + " and URL Postfix mimeType = '" + mimeType + "'");
		}

		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.QUERY), AccessType.APPROVED, false);
		if (error != null) {
			return makeErrorResponse(error, ResponseAction.QUERY);
		}

		BaseJobProvider provider = (BaseJobProvider) getProvider();
		if (provider == null) {
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.CREATE);
		}

		// Ignore the marshaller/unmarshaller - just reflect the contentType and
		// accept header information
		determineMediaTypes(null, null, false);

		try {
			Object returnObj = provider.retrieveToPhase(resourceID, phaseName, getResponseMediaType(), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false));
			if (returnObj != null) {
				return makeResponse(returnObj, Status.OK.getStatusCode(), false, ResponseAction.QUERY, null);
			} else {
				return makeErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + " does not exist."), ResponseAction.QUERY);
			}
		} catch (PersistenceException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve " + provider.getSingleObjectClassInfo().getObjectName() + " for resource ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY);
		} catch (IllegalArgumentException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY);
		} catch (ForbiddenException ex) {
			return makeErrorResponse(new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", AccessRight.CREATE.name() + " access is not set to " + AccessType.APPROVED.name() + " for the " + infraObjectNamePlural + " functional service", "Provider side check."), ResponseAction.QUERY);
		} catch (SIF3Exception ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY);
		}
	}

	@PUT
	@Path("{resourceID:([^\\.]*)}/phases/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response updateToPhase(String payload, @PathParam("resourceID") String resourceID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Update to phase " + phaseName + " (REST POST) with URL Postfix mimeType = '" + mimeType + "' and input data: " + payload);
		}

		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.UPDATE), AccessType.APPROVED, false);
		if (error != null) {
			return makeErrorResponse(error, ResponseAction.UPDATE);
		}

		BaseJobProvider provider = (BaseJobProvider) getProvider();
		if (provider == null) {
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.CREATE);
		}

		// Ignore the marshaller/unmarshaller - just reflect the contentType and
		// accept header information
		determineMediaTypes(null, null, false);

		try {
			String returnObj = provider.updateToPhase(resourceID, phaseName, payload, getRequestMediaType(), getResponseMediaType(), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false));
			return makeResponse(returnObj, Status.OK.getStatusCode(), false, ResponseAction.UPDATE, null);
		} catch (PersistenceException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.UPDATE);
		} catch (IllegalArgumentException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.UPDATE);
		} catch (UnmarshalException ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the given data to " + provider.getSingleObjectClassInfo().getObjectName() + ". Problem reported: " + ex.getMessage()), ResponseAction.UPDATE);
		} catch (UnsupportedMediaTypeException ex) {
			return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal the given data to " + provider.getSingleObjectClassInfo().getObjectName() + ". Problem reported: " + ex.getMessage()), ResponseAction.UPDATE);
		} catch (ForbiddenException ex) {
			return makeErrorResponse(new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", AccessRight.CREATE.name() + " access is not set to " + AccessType.APPROVED.name() + " for the " + infraObjectNamePlural + " functional service", "Provider side check."), ResponseAction.UPDATE);
		} catch (SIF3Exception ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.UPDATE);
		}
	}

	@DELETE
	@Path("{resourceID:([^\\.]*)}/phases/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response deleteToPhase(String payload, @PathParam("resourceID") String resourceID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Delete to phase " + phaseName + " (REST POST) with URL Postfix mimeType = '" + mimeType + "' and input data: " + payload);
		}

		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.DELETE), AccessType.APPROVED, false);
		if (error != null) {
			return makeErrorResponse(error, ResponseAction.DELETE);
		}

		BaseJobProvider provider = (BaseJobProvider) getProvider();
		if (provider == null) {
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.CREATE);
		}

		// Ignore the marshaller/unmarshaller - just reflect the contentType and
		// accept header information
		determineMediaTypes(null, null, false);

		try {
			String returnObj = provider.deleteToPhase(resourceID, phaseName, payload, getRequestMediaType(), getResponseMediaType(), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false));
			return makeResponse(returnObj, Status.OK.getStatusCode(), false, ResponseAction.UPDATE, null);
		} catch (PersistenceException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.DELETE);
		} catch (IllegalArgumentException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.DELETE);
		} catch (ForbiddenException ex) {
			return makeErrorResponse(new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", AccessRight.CREATE.name() + " access is not set to " + AccessType.APPROVED.name() + " for the " + infraObjectNamePlural + " functional service", "Provider side check."), ResponseAction.DELETE);
		} catch (SIF3Exception ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.DELETE);
		}
	}

	/*------------------*/
	/*-- HEAD Methods --*/
	/*------------------*/

	/*
	 * HEAD Method for root service ie. .../infraObjectNamePlural. This is the
	 * only fully supported HEAD method that returns all sort of things about the
	 * service including custom HTTP headers if set by the provider.
	 */
	@HEAD
	public Response getServiceInfo() {
		return super.getServiceInfo();
	}

	/*
	 * HEAD Method for single object service: Return basic info only and check
	 * ACL.
	 */
	@HEAD
	@Path("{resourceID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response getSingleObjectServiceInfo() {
		return super.getSingleObjectServiceInfo();
	}
}
