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

package sif3.infra.rest.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import sif3.common.CommonConstants;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.BadRequestException;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.ForbiddenException;
import sif3.common.exception.NotFoundException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.interfaces.FunctionalServiceProvider;
import sif3.common.interfaces.Provider;
import sif3.common.model.AuthenticationInfo;
import sif3.common.model.PagingInfo;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.persist.model.SIF3Session;
import sif3.common.persist.service.SIF3BindingService;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.infra.common.env.mgr.ProviderManagerFactory;
import sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.StateType;
import sif3.infra.rest.provider.BaseFunctionalServiceProvider;
import sif3.infra.rest.provider.ProviderFactory;

/**
 * This is the generic implementation of all Service resources (i.e. functional services).
 * 
 * This class the assumption that the base URI for all service providers is of the form:
 * 
 * http://<baseURI>/services/<ServiceName>...
 * 
 * It must be ensured that in all the environments managed with this framework that the "service"
 * connector URI follows this structure.
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
@Path("/services/{infraObjectNamePlural:([^\\./]*)}{mimeType:(\\.[^/]*?)?}")
public class ServiceResource extends InfraResource
{

    protected String             infraObjectNamePlural = null;
    protected Provider           provider              = null;
    protected SIF3BindingService sif3BindingService    = new SIF3BindingService();

    /**
     * Initialises an Provider Resource. All the parameters are automatically injected by the Jersey
     * Framework.
     * 
     * @param uriInfo
     *            Extracted from the request.
     * @param requestHeaders
     *            Extracted from the request.
     * @param request
     *            Extracted from the request.
     * @param infraObjectNamePlural
     *            Extracted from the request.
     * @param mimeType
     *            The mime type postfix that might be set on the request URL.
     * @param zoneID
     *            Extracted from the request (Matrix Parameter).
     * @param contextID
     *            Extracted from the request (Matrix Parameter).
     */
    public ServiceResource(@Context UriInfo uriInfo, @Context HttpHeaders requestHeaders,
            @Context Request request,
            @PathParam("infraObjectNamePlural") String infraObjectNamePlural,
            @PathParam("mimeType") String mimeType, @MatrixParam("zoneId") String zoneID,
            @MatrixParam("contextId") String contextID)
    {
        super(uriInfo, requestHeaders, request, "services", zoneID, contextID);

        this.infraObjectNamePlural = infraObjectNamePlural;

        if (logger.isDebugEnabled())
        {
            logger.debug("Service to use: " + infraObjectNamePlural);
        }

        provider = ProviderFactory.getInstance()
                .getProvider(new ModelObjectInfo(this.infraObjectNamePlural, null));
        if (provider != null)
        {
            determineMediaTypes(provider.getMarshaller(), provider.getUnmarshaller(), false);
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("Request Media Type : " + getRequestMediaType());
            logger.debug("Response Media Type: " + getResponseMediaType());
        }
    }

    /*----------------------*/
    /*-- Abstract Methods --*/
    /*----------------------*/

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.resource.BaseResource#getEnvironmentManager()
     */
    @Override
    public EnvironmentManager getEnvironmentManager()
    {
        return ProviderManagerFactory.getEnvironmentManager();
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.resource.BaseResource#getMarshaller()
     */
    @Override
    public MarshalFactory getMarshaller()
    {
        return (getProvider() != null) ? getProvider().getMarshaller() : null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.resource.BaseResource#getUnmarshaller()
     */
    @Override
    public UnmarshalFactory getUnmarshaller()
    {
        return (getProvider() != null) ? getProvider().getUnmarshaller() : null;
    }

    // -------------------------------------------------//
    // -- POST Section: This is the C(reate) in CRUD. --//
    // -------------------------------------------------//
    @POST
    @Path("{infraObjectNameSingle:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response createSingle(String payload,
            @PathParam("infraObjectNameSingle") String infraObjectNameSingle,
            @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Create Single " + infraObjectNameSingle
                    + " (REST POST) with URL Postfix mimeType = '" + mimeType + "' and input data: "
                    + payload);
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();

        ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.CREATE),
                AccessType.APPROVED, false, false);
        if (error != null) // Not allowed to access!
        {
            return makeErrorResponse(error, ResponseAction.CREATE, responseParam);
        }

        Provider provider = getProvider();
        if (provider == null)
        {
            // error already logged but we must return an error response for the caller
            return makeNoProviderError(ResponseAction.CREATE, responseParam);
        }

        try
        {
            JobType job = (JobType) provider.createSingle(
                    provider.getUnmarshaller().unmarshal(payload,
                            provider.getSingleObjectClassInfo().getObjectType(),
                            getRequestMediaType()),
                    getAdvisory(), getSifZone(), getSifContext(),
                    getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);

            if (getJobBinding())
            {
                sif3BindingService.bind(job.getId(), getOwnerId());
            }

            return makeResponse(job, Status.CREATED.getStatusCode(), false, ResponseAction.CREATE,
                    responseParam, provider.getMarshaller());
        }
        catch (PersistenceException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (UnmarshalException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (UnsupportedMediaTypeException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.CREATE, responseParam);
        }
    }

    @POST
    public Response createMany(String payload)
    {
        // Check what is really required: GET (QBE functionality) or POST (Create
        // functionality)
        boolean isQBE = HeaderValues.MethodType.GET.name().equalsIgnoreCase(getSIFHeaderProperties()
                .getHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE));

        if (logger.isDebugEnabled())
        {
            if (isQBE)
            {
                logger.debug("QBE on " + infraObjectNamePlural
                        + " (REST POST, method OVERRIDE=GET) with input data: " + payload);
            }
            else
            {
                logger.debug("Create Many " + infraObjectNamePlural
                        + " (REST POST) with input data: " + payload);
            }
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();

        ErrorDetails error = validClient(infraObjectNamePlural,
                ((isQBE) ? getRight(AccessRight.QUERY) : getRight(AccessRight.CREATE)),
                AccessType.APPROVED, true, true);
        if (error != null) // Not allowed to access!
        {
            logger.debug("Error Found: " + error);
            return makeErrorResponse(error,
                    ((isQBE) ? ResponseAction.QUERY : ResponseAction.CREATE), responseParam);
        }

        Provider provider = getProvider();
        if (provider == null)
        {
            // error already logged but we must return an error response for the caller
            return makeNoProviderError(((isQBE) ? ResponseAction.QUERY : ResponseAction.CREATE),
                    responseParam);
        }

        try
        {
            if (isQBE)
            {
                throw new UnsupportedQueryException("QBE in functional services is not supported.");
            }
        }
        catch (UnsupportedQueryException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }

        return createMany(provider, payload);
    }

    // --------------------------------------------------------//
    // -- GET Section: This is the R(ead) in CRUD for Lists. --//
    // --------------------------------------------------------//
    @GET
    @Path("{resourceID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response getSingle(@PathParam("resourceID") String resourceID,
            @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Get Resource by Resource ID (REST GET - Single): " + resourceID
                    + " and URL Postfix mimeType = '" + mimeType + "'");
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();

        ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.QUERY),
                AccessType.APPROVED, false, true);
        if (error != null) // Not allowed to access!
        {
            return makeErrorResponse(error, ResponseAction.QUERY, responseParam);
        }

        Provider provider = getProvider();
        if (provider == null)
        {
            // error already logged but we must return an error response for the caller
            return makeNoProviderError(ResponseAction.QUERY, responseParam);
        }

        try
        {
            Object returnObj = provider.retrieveByPrimaryKey(resourceID, getSifZone(),
                    getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false),
                    responseParam);

            if (returnObj != null)
            {
                if (getJobBinding() && !sif3BindingService.isBound(resourceID, getOwnerId()))
                {
                    throw new ForbiddenException(
                            "Object requested does not belong to this consumer.");
                }

                return makeResponse(returnObj, Status.OK.getStatusCode(), false,
                        ResponseAction.QUERY, responseParam, provider.getMarshaller());
            }
            else
            {
                throw new NotFoundException("Could not find object with id '" + resourceID + "'.");
            }
        }
        catch (NotFoundException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }
        catch (PersistenceException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }
        catch (ForbiddenException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }
        catch (IllegalArgumentException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.QUERY, responseParam);
        }
    }

    @GET
    public Response getMany()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Get List (REST GET - Plural)");
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();

        ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.QUERY),
                AccessType.APPROVED, true, true);
        if (error != null) // Not allowed to access!
        {
            return makeErrorResponse(error, ResponseAction.QUERY, responseParam);
        }

        Provider provider = getProvider();
        if (provider == null)
        {
            // error already logged but we must return an error response for the caller
            return makeNoProviderError(ResponseAction.QUERY, responseParam);
        }

        PagingInfo pagingInfo = getPagingInfo();

        try
        {
            Object returnObj = provider.retrieve(getSifZone(), getSifContext(), pagingInfo,
                    getRequestMetadata(getSIF3SessionForRequest(), true), responseParam);

            if (getJobBinding())
            {
                JobCollectionType jobs = (JobCollectionType) returnObj;
                AuthenticationInfo auth = getAuthInfo();
                Iterator<JobType> itr = jobs.getJob().iterator();
                while (itr.hasNext())
                {
                    JobType job = itr.next();
                    if (!sif3BindingService.isBound(job.getId(), auth.getUserToken()))
                    {
                        itr.remove();
                    }
                }
            }

            return makePagedResponse(returnObj, pagingInfo, false, responseParam,
                    provider.getMarshaller());
        }
        catch (PersistenceException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }
        catch (IllegalArgumentException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }
        catch (UnsupportedQueryException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }
        catch (DataTooLargeException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.QUERY, responseParam);
        }
    }

    // ----------------------------------------------------------//
    // -- PUT Section: This is the U(pdate) in CRUD for Lists. --//
    // ----------------------------------------------------------//
    @PUT
    @Path("{resourceID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response updateSingle(String payload, @PathParam("resourceID") String resourceID,
            @PathParam("mimeType") String mimeType)
    {
        try
        {
            throw new UnsupportedQueryException(
                    "Update operations not supported on functional services");
        }
        catch (UnsupportedQueryException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY,
                    getInitialCustomResponseParameters());
        }
    }

    @PUT
    public Response updateMany(String payload)
    {
        // Check what is really required: DELETE or UPDATE
        boolean doDelete = HeaderValues.MethodType.DELETE.name()
                .equalsIgnoreCase(getSIFHeaderProperties()
                        .getHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE));

        if (logger.isDebugEnabled())
        {
            if (doDelete)
            {
                logger.debug("Delete Collection " + infraObjectNamePlural
                        + " (REST PUT, method OVERRIDE=DELETE) with input data: " + payload);
            }
            else
            {
                logger.debug("Update Collection " + infraObjectNamePlural
                        + " (REST PUT) with input data: " + payload);
            }
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();

        ErrorDetails error = validClient(infraObjectNamePlural,
                ((doDelete) ? getRight(AccessRight.DELETE) : getRight(AccessRight.UPDATE)),
                AccessType.APPROVED, true, true);
        if (error != null) // Not allowed to access!
        {
            logger.debug("Error Found: " + error);
            return makeErrorResponse(error,
                    ((doDelete) ? ResponseAction.DELETE : ResponseAction.UPDATE), responseParam);
        }

        Provider provider = getProvider();
        if (provider == null)
        {
            // error already logged but we must return an error response for the caller
            return makeNoProviderError(((doDelete) ? ResponseAction.DELETE : ResponseAction.UPDATE),
                    responseParam);
        }

        return (doDelete) ? deleteMany(provider, payload) : updateMany(provider, payload);
    }

    // -------------------------------------------------------------//
    // -- DELETE Section: This is the D(elete) in CRUD for Lists. --//
    // -------------------------------------------------------------//
    @DELETE
    @Path("{resourceID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response removeSingle(@PathParam("resourceID") String resourceID,
            @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug(
                    "Remove Single " + infraObjectNamePlural + " (REST DELETE) with resourceID = "
                            + resourceID + " and URL Postfix mimeType = '" + mimeType + "'.");
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();

        ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.DELETE),
                AccessType.APPROVED, false, true);
        if (error != null) // Not allowed to access!
        {
            logger.debug("Error Found: " + error);
            return makeErrorResponse(error, ResponseAction.DELETE, responseParam);
        }

        Provider provider = getProvider();
        if (provider == null)
        {
            // error already logged but we must return an error response for the caller
            return makeNoProviderError(ResponseAction.DELETE, responseParam);
        }

        try
        {
            if (getJobBinding() && !sif3BindingService.isBound(resourceID, getOwnerId()))
            {
                throw new ForbiddenException(
                        "If the object exists it does not belong to this consumer.");
            }

            if (provider.deleteSingle(resourceID, getSifZone(), getSifContext(),
                    getRequestMetadata(getSIF3SessionForRequest(), false), responseParam))
            {
                if (getJobBinding())
                {
                    sif3BindingService.unbind(resourceID);
                }

                return makeResopnseWithNoContent(false, ResponseAction.DELETE, responseParam);
            }
            else
            {
                throw new NotFoundException("Could not find object with id '" + resourceID + "'.");

            }
        }
        catch (NotFoundException ex)
        {
            return makeExceptionError(ex, ResponseAction.DELETE, responseParam);
        }
        catch (PersistenceException ex)
        {
            return makeExceptionError(ex, ResponseAction.DELETE, responseParam);
        }
        catch (ForbiddenException ex)
        {
            return makeExceptionError(ex, ResponseAction.DELETE, responseParam);
        }
        catch (IllegalArgumentException ex)
        {
            return makeExceptionError(ex, ResponseAction.DELETE, responseParam);
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.DELETE, responseParam);
        }
    }

    @DELETE
    public Response removeMany(String payload)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Delete Collection " + infraObjectNamePlural
                    + " (REST DELETE) with input data: " + payload);
        }
        ErrorDetails error = new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(),
                "Operation not supported.",
                "Use HTTP PUT with header field '" + RequestHeaderConstants.HDR_METHOD_OVERRIDE
                        + "' set to " + HeaderValues.MethodType.DELETE.name() + " instead.");
        return makeErrorResponse(error, ResponseAction.DELETE,
                getInitialCustomResponseParameters());
    }

    // ------------ //
    // -- Phases -- //
    // ------------ //
    @POST
    @Path("{resourceID:([^\\.]*)}/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response createToPhase(String payload, @PathParam("resourceID") String resourceID,
            @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug(
                    "Create to phase " + phaseName + " (REST POST) with URL Postfix mimeType = '"
                            + mimeType + "' and input data: " + payload);
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();

        ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.CREATE),
                AccessType.APPROVED, false, false);
        if (error != null)
        {
            return makeErrorResponse(error, ResponseAction.CREATE, responseParam);
        }

        FunctionalServiceProvider provider = (FunctionalServiceProvider) getProvider();
        if (provider == null)
        {
            // error already logged but we must return an error response for the caller
            return makeNoProviderError(ResponseAction.CREATE, responseParam);
        }

        // Ignore the marshaller/unmarshaller - just reflect the contentType and
        // accept header information
        determineMediaTypes(null, null, false);

        try
        {
            if (getJobBinding() && !sif3BindingService.isBound(resourceID, getOwnerId()))
            {
                throw new ForbiddenException("Object requested does not belong to this consumer.");
            }

            String returnPayload = provider.createToPhase(resourceID, phaseName, payload,
                    getRequestMediaType(), getResponseMediaType(), getSifZone(), getSifContext(),
                    getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
            return makeResponse(returnPayload, Status.CREATED.getStatusCode(), false,
                    ResponseAction.CREATE, responseParam, null);
        }
        catch (PersistenceException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (UnmarshalException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (UnsupportedMediaTypeException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (UnsupportedQueryException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (BadRequestException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (ForbiddenException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.CREATE, responseParam);
        }
    }

    @GET
    @Path("{resourceID:([^\\.]*)}/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response retrieveToPhase(String payload, @PathParam("resourceID") String resourceID,
            @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Get phase " + phaseName + " by Job ID (REST GET): " + resourceID
                    + " and URL Postfix mimeType = '" + mimeType + "'");
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();

        ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.QUERY),
                AccessType.APPROVED, false, false);
        if (error != null)
        {
            return makeErrorResponse(error, ResponseAction.QUERY, responseParam);
        }

        Provider p = getProvider();
        Response errResponse = checkProvider(p, responseParam);
        if (errResponse != null)
        {
            return errResponse;
        }
        BaseFunctionalServiceProvider provider = (BaseFunctionalServiceProvider) p;

        // Ignore the marshaller/unmarshaller - just reflect the contentType and
        // accept header information
        determineMediaTypes(null, null, false);

        try
        {
            if (getJobBinding() && !sif3BindingService.isBound(resourceID, getOwnerId()))
            {
                throw new ForbiddenException("Object requested does not belong to this consumer.");
            }

            Object returnObj = provider.retrieveToPhase(resourceID, phaseName, payload,
                    getRequestMediaType(), getResponseMediaType(), getSifZone(), getSifContext(),
                    getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
            if (returnObj != null)
            {
                return makeResponse(returnObj, Status.OK.getStatusCode(), false,
                        ResponseAction.QUERY, responseParam, null);
            }
            else
            {
                throw new NotFoundException("Could not find object with id '" + resourceID + "'.");
            }
        }
        catch (NotFoundException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }
        catch (PersistenceException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }
        catch (IllegalArgumentException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }
        catch (ForbiddenException ex)
        {
            return makeExceptionError(ex, ResponseAction.QUERY, responseParam);
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.QUERY, responseParam);
        }
    }

    @PUT
    @Path("{resourceID:([^\\.]*)}/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response updateToPhase(String payload, @PathParam("resourceID") String resourceID,
            @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug(
                    "Update to phase " + phaseName + " (REST POST) with URL Postfix mimeType = '"
                            + mimeType + "' and input data: " + payload);
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();

        ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.UPDATE),
                AccessType.APPROVED, false, false);
        if (error != null)
        {
            return makeErrorResponse(error, ResponseAction.UPDATE, responseParam);
        }

        Provider p = getProvider();
        Response errResponse = checkProvider(p, responseParam);
        if (errResponse != null)
        {
            return errResponse;
        }
        BaseFunctionalServiceProvider provider = (BaseFunctionalServiceProvider) p;

        // Ignore the marshaller/unmarshaller - just reflect the contentType and
        // accept header information
        determineMediaTypes(null, null, false);

        try
        {
            if (getJobBinding() && !sif3BindingService.isBound(resourceID, getOwnerId()))
            {
                throw new ForbiddenException("Object requested does not belong to this consumer.");
            }

            String returnObj = provider.updateToPhase(resourceID, phaseName, payload,
                    getRequestMediaType(), getResponseMediaType(), getSifZone(), getSifContext(),
                    getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);

            return makeResponse(returnObj, Status.OK.getStatusCode(), false, ResponseAction.UPDATE,
                    responseParam, null);
        }
        catch (PersistenceException ex)
        {
            return makeExceptionError(ex, ResponseAction.UPDATE, responseParam);
        }
        catch (IllegalArgumentException ex)
        {
            return makeExceptionError(ex, ResponseAction.UPDATE, responseParam);
        }
        catch (UnmarshalException ex)
        {
            return makeExceptionError(ex, ResponseAction.UPDATE, responseParam);
        }
        catch (UnsupportedMediaTypeException ex)
        {
            return makeExceptionError(ex, ResponseAction.UPDATE, responseParam);
        }
        catch (ForbiddenException ex)
        {
            return makeExceptionError(ex, ResponseAction.UPDATE, responseParam);
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.UPDATE, responseParam);
        }
    }

    @DELETE
    @Path("{resourceID:([^\\.]*)}/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response deleteToPhase(String payload, @PathParam("resourceID") String resourceID,
            @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug(
                    "Delete to phase " + phaseName + " (REST POST) with URL Postfix mimeType = '"
                            + mimeType + "' and input data: " + payload);
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();

        ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.DELETE),
                AccessType.APPROVED, false, false);
        if (error != null)
        {
            return makeErrorResponse(error, ResponseAction.DELETE, responseParam);
        }

        Provider p = getProvider();
        Response errResponse = checkProvider(p, responseParam);
        if (errResponse != null)
        {
            return errResponse;
        }
        BaseFunctionalServiceProvider provider = (BaseFunctionalServiceProvider) p;

        // Ignore the marshaller/unmarshaller - just reflect the contentType and
        // accept header information
        determineMediaTypes(null, null, false);

        try
        {
            if (getJobBinding() && !sif3BindingService.isBound(resourceID, getOwnerId()))
            {
                throw new ForbiddenException("Object requested does not belong to this consumer.");
            }

            String returnObj = provider.deleteToPhase(resourceID, phaseName, payload,
                    getRequestMediaType(), getResponseMediaType(), getSifZone(), getSifContext(),
                    getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
            return makeResponse(returnObj, Status.OK.getStatusCode(), false, ResponseAction.UPDATE,
                    responseParam, null);
        }
        catch (PersistenceException ex)
        {
            return makeExceptionError(ex, ResponseAction.DELETE, responseParam);
        }
        catch (IllegalArgumentException ex)
        {
            return makeExceptionError(ex, ResponseAction.DELETE, responseParam);
        }
        catch (ForbiddenException ex)
        {
            return makeExceptionError(ex, ResponseAction.DELETE, responseParam);
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.DELETE, responseParam);
        }
    }

    // ------------ //
    // -- States -- //
    // ------------ //
    @POST
    @Path("{resourceID:([^\\.]*)}/{phaseName:([^\\.]*)}/states/state{mimeType:(\\.[^/]*?)?}")
    public Response createToState(String payload, @PathParam("resourceID") String resourceID,
            @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug(
                    "Create to state on " + phaseName + " (REST POST) with URL Postfix mimeType = '"
                            + mimeType + "' and input data: " + payload);
        }

        ResponseParameters responseParam = getInitialCustomResponseParameters();

        ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.UPDATE),
                AccessType.APPROVED, false, false);
        if (error != null)
        {
            return makeErrorResponse(error, ResponseAction.CREATE, responseParam);
        }

        Provider p = getProvider();
        Response errResponse = checkProvider(p, responseParam);
        if (errResponse != null)
        {
            return errResponse;
        }
        BaseFunctionalServiceProvider provider = (BaseFunctionalServiceProvider) p;

        try
        {
            if (getJobBinding() && !sif3BindingService.isBound(resourceID, getOwnerId()))
            {
                throw new ForbiddenException("Object requested does not belong to this consumer.");
            }

            StateType state = provider.createToState(resourceID, phaseName,
                    provider.getUnmarshaller().unmarshal(payload, StateType.class,
                            getRequestMediaType()),
                    getSifZone(), getSifContext(),
                    getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);

            return makeResponse(state, Status.CREATED.getStatusCode(), false, ResponseAction.CREATE,
                    responseParam, provider.getMarshaller());
        }
        catch (PersistenceException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (UnmarshalException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (UnsupportedMediaTypeException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (ForbiddenException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.DELETE, responseParam);
        }
    }

    /*----------------------------------------------------------------------*/
    /*-- HEAD Methods: Only the root Object service provides full support --*/
    /*----------------------------------------------------------------------*/
    /*
     * HEAD Method for root service ie. .../infraObjectNamePlural. This is the only fully supported
     * HEAD method that returns all sort of things about the service including custom HTTP headers
     * if set by the provider.
     */
    @HEAD
    public Response getServiceInfo()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Get Service Info (REST HEAD)");
        }

        ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.QUERY),
                AccessType.APPROVED, true, true);
        if (error != null) // Not allowed to access!
        {
            return makeResponse(null, error.getErrorCode(), true, ResponseAction.HEAD,
                    getInitialCustomResponseParameters(), null);
        }

        Provider provider = getProvider();
        if (provider == null)
        {
            // error already logged but we must return an error response for the caller
            return makeNoProviderError(ResponseAction.HEAD, getInitialCustomResponseParameters());
        }

        PagingInfo pagingInfo = null;
        try
        {
            pagingInfo = getPagingInfo();
            HeaderProperties defaultCustomHeaders = getInitialCustomResponseParameters()
                    .getHttpHeaderParams();
            HeaderProperties customHeaders = provider.getServiceInfo(getSifZone(), getSifContext(),
                    pagingInfo, getRequestMetadata(getSIF3SessionForRequest(), true));
            if (customHeaders != null)
            {
                // Copy customHeaders to defaultCustomHeaders to ensure the correct override order.
                defaultCustomHeaders.addHeaderProperties(customHeaders);
            }

            if (logger.isDebugEnabled())
            {
                logger.debug("Custom headers to be returned from 'getServiceInfo()' method:\n"
                        + customHeaders);
            }

            ResponseParameters responseParams = new ResponseParameters(defaultCustomHeaders);
            return makePagedResponse(null, pagingInfo, false, responseParams, null);
        }
        catch (PersistenceException ex)
        {
            return makeExceptionError(ex, ResponseAction.HEAD,
                    getInitialCustomResponseParameters());
        }
        catch (IllegalArgumentException ex)
        {
            return makeExceptionError(ex, ResponseAction.HEAD,
                    getInitialCustomResponseParameters());
        }
        catch (UnsupportedQueryException ex)
        {
            return makeExceptionError(ex, ResponseAction.HEAD,
                    getInitialCustomResponseParameters());
        }
        catch (DataTooLargeException ex)
        {
            return makeExceptionError(ex, ResponseAction.HEAD,
                    getInitialCustomResponseParameters());
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.HEAD,
                    getInitialCustomResponseParameters());
        }
    }

    /*
     * HEAD Method for single object service: Return basic info only and check ACL.
     */
    @HEAD
    @Path("{resourceID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
    public Response getSingleObjectServiceInfo()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("Get Single Object Service Info (REST HEAD)");
        }
        ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.QUERY),
                AccessType.APPROVED, false, true);
        if (error != null) // Not allowed to access!
        {
            return makeResponse(null, error.getErrorCode(), true, ResponseAction.QUERY,
                    getInitialCustomResponseParameters(), null);
        }
        return makeResponse(null, Status.NO_CONTENT.getStatusCode(), false, ResponseAction.QUERY,
                getInitialCustomResponseParameters(), null);
    }

    /*------------------------*/
    /*-- Overridden Methods --*/
    /*------------------------*/
    @Override
    public SIFZone getSifZone()
    {
        SIFZone sifZone = super.getSifZone();
        if (sifZone == null) // default zone => Get default zone from session
        {
            SIF3Session session = getSIF3SessionForRequest();
            if (session != null)
            {
                sifZone = session.getDefaultZone();
            }
        }

        return sifZone;
    }

    @Override
    public SIFContext getSifContext()
    {
        SIFContext sifContext = super.getSifContext();
        if (sifContext == null) // Default Context
        {
            sifContext = CommonConstants.DEFAULT_CONTEXT;
        }

        return sifContext;
    }

    /*---------------------*/
    /*-- protected Methods --*/
    /*---------------------*/
    protected Provider getProvider()
    {
        if (provider == null)
        {
            // No provider known for this Object Type! This is an issue and needs to be logged.
            logger.error("No Provider found for name " + infraObjectNamePlural);
        }
        return provider;
    }

    protected boolean getAdvisory()
    {
        return Boolean.valueOf(getSIFHeaderProperties()
                .getHeaderProperty(RequestHeaderConstants.HDR_ADVISORY, "false"));
    }

    /*
     * This method is a helper to determine what the actual access right is. If a provider is a
     * direct provider an access right is the actual right of the consumer as set in the environment
     * ACL. If the provider is in a brokered environment its right is the ACL in relation to the
     * broker. In such a case the right is simply 'PROVIDE'.
     */
    protected AccessRight getRight(AccessRight directEnvRight)
    {
        // If we are in a brokered environment then the access right must be
        // PROVIDE. In a DIRECT environment the access right must be QUERY.
        return getProviderEnvironment().getEnvironmentType() == EnvironmentType.DIRECT
                ? directEnvRight : AccessRight.PROVIDE;
    }

    protected PagingInfo getPagingInfo()
    {
        PagingInfo pagingInfo = new PagingInfo(getSIFHeaderProperties(), getQueryParameters());
        if (pagingInfo.getPageSize() <= PagingInfo.NOT_DEFINED)
        {
            // page size not defined. Pass null to provider.
            pagingInfo = null;
        }
        else
        {
            // ensure that initial values are not overridden in case we need them later
            pagingInfo = pagingInfo.clone();
        }
        return pagingInfo;
    }

    private Response updateMany(Provider provider, String payload)
    {
        ResponseParameters responseParam = getInitialCustomResponseParameters();
        try
        {
            if (pretendDelayed())
            {
                // Simply send a response with status of 202
                return makeDelayedAcceptResponse(ResponseAction.UPDATE);
            }
            else
            {
                List<OperationStatus> statusList = provider.updateMany(
                        provider.getUnmarshaller().unmarshal(
                                payload, provider.getMultiObjectClassInfo().getObjectType(),
                                getRequestMediaType()),
                        getSifZone(), getSifContext(),
                        getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);

                if (statusList != null)
                {
                    return makeUpdateMultipleResponse(statusList, Status.OK, responseParam);
                }
                else
                {
                    return makeErrorResponse(
                            new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                                    "Failed to update "
                                            + provider.getMultiObjectClassInfo().getObjectName()
                                            + " (Bulk Operation). Contact your System Administrator."),
                            ResponseAction.UPDATE, responseParam);
                }
            }
        }
        catch (PersistenceException ex)
        {
            return makeExceptionError(ex, ResponseAction.UPDATE, responseParam);
        }
        catch (UnmarshalException ex)
        {
            return makeExceptionError(ex, ResponseAction.UPDATE, responseParam);
        }
        catch (UnsupportedMediaTypeException ex)
        {
            return makeExceptionError(ex, ResponseAction.UPDATE, responseParam);
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.UPDATE, responseParam);
        }
    }

    private Response deleteMany(Provider provider, String payload)
    {
        ResponseParameters responseParam = getInitialCustomResponseParameters();
        try
        {
            if (pretendDelayed())
            {
                // Simply send a response with status of 202
                return makeDelayedAcceptResponse(ResponseAction.DELETE);
            }
            else
            {
                List<OperationStatus> statusList = null;
                List<String> ids = getResourceIDsFromDeleteRequest(payload);
                try
                {
                    if (getJobBinding())
                    {
                        statusList = new ArrayList<OperationStatus>();
                        for (String objectId : ids)
                        {
                            if (sif3BindingService.isBound(objectId))
                            {
                                if (provider.deleteSingle(objectId, getSifZone(), getSifContext(),
                                        getRequestMetadata(getSIF3SessionForRequest(), false),
                                        responseParam))
                                {
                                    statusList.add(new OperationStatus(objectId, 200));
                                    sif3BindingService.unbind(objectId);
                                }
                                else
                                {
                                    statusList.add(new OperationStatus(objectId, 404,
                                            new ErrorDetails(404, "Job with GUID = " + objectId
                                                    + " does not exist.")));
                                }
                            }
                            else
                            {
                                statusList.add(new OperationStatus(objectId, 403, new ErrorDetails(
                                        403,
                                        "Consumer does not own object with " + objectId + ".")));
                            }
                        }
                    }
                    else
                    {
                        statusList = provider.deleteMany(ids, getSifZone(), getSifContext(),
                                getRequestMetadata(getSIF3SessionForRequest(), false),
                                responseParam);
                    }
                }
                catch (Exception e)
                {
                    return makeErrorResponse(
                            new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                                    "Unexpected request failure due to " + e.getMessage()),
                            ResponseAction.CREATE, responseParam);
                }

                if (statusList != null)
                {
                    return makeDeleteMultipleResponse(statusList, Status.OK, responseParam);
                }
                else
                {
                    return makeErrorResponse(
                            new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                                    "Failed to delete "
                                            + provider.getMultiObjectClassInfo().getObjectName()
                                            + " (Bulk Operation). Contact your System Administrator."),
                            ResponseAction.DELETE, responseParam);
                }
            }
        }
        catch (UnmarshalException ex)
        {
            return makeExceptionError(ex, ResponseAction.DELETE, responseParam);
        }
        catch (UnsupportedMediaTypeException ex)
        {
            return makeExceptionError(ex, ResponseAction.DELETE, responseParam);
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.DELETE, responseParam);
        }
    }

    private Response createMany(Provider provider, String payload)
    {
        ResponseParameters responseParam = getInitialCustomResponseParameters();
        try
        {
            if (pretendDelayed())
            {
                // Simply send a response with status of 202
                return makeDelayedAcceptResponse(ResponseAction.CREATE);
            }
            else
            {
                List<CreateOperationStatus> statusList = provider.createMany(
                        provider.getUnmarshaller().unmarshal(
                                payload, provider.getMultiObjectClassInfo().getObjectType(),
                                getRequestMediaType()),
                        getAdvisory(), getSifZone(), getSifContext(),
                        getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);

                if (statusList != null)
                {
                    try
                    {
                        if (getJobBinding())
                        {
                            AuthenticationInfo auth = getAuthInfo();
                            for (CreateOperationStatus status : statusList)
                            {
                                if (status.getError() != null)
                                {
                                    continue;
                                }
                                sif3BindingService.bind(status.getResourceID(),
                                        auth.getUserToken());
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        return makeErrorResponse(
                                new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                                        "Unexpected request failure due to " + e.getMessage()),
                                ResponseAction.CREATE, responseParam);
                    }

                    return makeCreateMultipleResponse(statusList, Status.CREATED, responseParam);
                }
                else
                {
                    return makeErrorResponse(
                            new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                                    "Failed to create "
                                            + provider.getMultiObjectClassInfo().getObjectName()
                                            + " (Bulk Operation). Contact your System Administrator."),
                            ResponseAction.CREATE, responseParam);
                }
            }
        }
        catch (PersistenceException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (UnmarshalException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (UnsupportedMediaTypeException ex)
        {
            return makeExceptionError(ex, ResponseAction.CREATE, responseParam);
        }
        catch (Exception ex)
        {
            return makeUnexpectedError(ex, ResponseAction.CREATE, responseParam);
        }
    }

    private Response checkProvider(Provider p, ResponseParameters responseParam)
    {
        if (p == null)
        {
            return makeErrorResponse(
                    new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(),
                            "No provider for " + infraObjectNamePlural + " available."),
                    ResponseAction.CREATE, responseParam);
        }
        if (!(p instanceof BaseFunctionalServiceProvider))
        {
            return makeErrorResponse(
                    new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(),
                            "A provider for " + infraObjectNamePlural
                                    + " found but not of an expected type."),
                    ResponseAction.CREATE, responseParam);
        }
        return null;
    }

    private boolean getJobBinding()
    {
        return getServiceProperties().getPropertyAsBool(CommonConstants.JOB_BINDING_PROPERTY,
                infraObjectNamePlural, CommonConstants.DEFAULT_JOB_BINDING);
    }

    private Response makeNoProviderError(ResponseAction action, ResponseParameters responseParam)
    {
        int status = Status.SERVICE_UNAVAILABLE.getStatusCode();
        String msg = "No Provider for " + infraObjectNamePlural + " available.";
        ErrorDetails error = new ErrorDetails(status, msg, "Provider side check.");

        logger.debug(msg);

        return makeErrorResponse(error, action, responseParam);
    }

    private Response makeExceptionError(PersistenceException e, ResponseAction action,
            ResponseParameters responseParam)
    {
        int status = e.getStatus();
        String msg = "Operation failed due to persistence error in provider "
                + provider.getMultiObjectClassInfo().getObjectName() + ". Problem reported: "
                + e.getMessage();
        ErrorDetails error = new ErrorDetails(status, msg, "Provider side check.");

        logger.debug(msg, e);

        return makeErrorResponse(error, action, responseParam);
    }

    private Response makeExceptionError(UnmarshalException e, ResponseAction action,
            ResponseParameters responseParam)
    {
        int status = e.getStatus();
        String msg = "Could not unmarshal data sent to provider "
                + provider.getMultiObjectClassInfo().getObjectName() + ". Problem reported: "
                + e.getMessage();
        ErrorDetails error = new ErrorDetails(status, msg, "Provider side check.");

        logger.debug(msg, e);

        return makeErrorResponse(error, action, responseParam);
    }

    private Response makeExceptionError(UnsupportedMediaTypeException e, ResponseAction action,
            ResponseParameters responseParam)
    {
        int status = e.getStatus();
        String msg = "Could not unmarshal data sent to provider "
                + provider.getMultiObjectClassInfo().getObjectName() + ". Problem reported: "
                + e.getMessage();
        ErrorDetails error = new ErrorDetails(status, msg, "Provider side check.");

        logger.debug(msg, e);

        return makeErrorResponse(error, action, responseParam);
    }

    private Response makeExceptionError(UnsupportedQueryException e, ResponseAction action,
            ResponseParameters responseParam)
    {
        int status = e.getStatus();
        String msg = "Provider " + provider.getMultiObjectClassInfo().getObjectName()
                + " does not support this request. Detail: " + e.getMessage();
        ErrorDetails error = new ErrorDetails(status, msg, "Provider side check.");

        logger.debug(msg);

        return makeErrorResponse(error, action, responseParam);
    }

    private Response makeExceptionError(BadRequestException e, ResponseAction action,
            ResponseParameters responseParam)
    {
        int status = e.getStatus();
        String msg = "Bad request to provider " + provider.getMultiObjectClassInfo().getObjectName()
                + ". Problem reported: " + e.getMessage();
        ErrorDetails error = new ErrorDetails(status, msg, "Provider side check.");

        logger.debug(msg, e);

        return makeErrorResponse(error, action, responseParam);
    }

    private Response makeExceptionError(ForbiddenException e, ResponseAction action,
            ResponseParameters responseParam)
    {
        int status = e.getStatus();
        String msg = "Consumer is not authorized to issue the requested operation. Problem reported: "
                + e.getMessage();
        ErrorDetails error = new ErrorDetails(status, msg, "Provider side check.");

        logger.debug(msg, e);

        return makeErrorResponse(error, action, responseParam);
    }

    private Response makeExceptionError(IllegalArgumentException e, ResponseAction action,
            ResponseParameters responseParam)
    {
        int status = Status.INTERNAL_SERVER_ERROR.getStatusCode();
        String msg = "Error in the arguments of the request. Problem reported: " + e.getMessage();
        ErrorDetails error = new ErrorDetails(status, msg, "Provider side check.");

        logger.debug(msg, e);

        return makeErrorResponse(error, action, responseParam);
    }

    private Response makeExceptionError(DataTooLargeException e, ResponseAction action,
            ResponseParameters responseParam)
    {
        int status = e.getStatus();
        String msg = "Data too large. Problem reported: " + e.getMessage();
        ErrorDetails error = new ErrorDetails(status, msg, "Provider side check.");

        logger.debug(msg, e);

        return makeErrorResponse(error, action, responseParam);
    }

    private Response makeExceptionError(NotFoundException e, ResponseAction action,
            ResponseParameters responseParam)
    {
        int status = e.getStatus();
        String msg = "Count not find requested object. Problem reported: " + e.getMessage();
        ErrorDetails error = new ErrorDetails(status, msg, "Provider side check.");

        logger.debug(msg);

        return makeErrorResponse(error, action, responseParam);
    }

    private Response makeUnexpectedError(Exception e, ResponseAction action,
            ResponseParameters responseParam)
    {
        int status = Status.BAD_REQUEST.getStatusCode();
        String msg = "Unexpected error occurred in provider "
                + provider.getMultiObjectClassInfo().getObjectName() + ". Problem reported: "
                + e.getMessage();
        ErrorDetails error = new ErrorDetails(status, msg);

        logger.debug(msg, e);

        return makeErrorResponse(error, action, responseParam);
    }

    private String getOwnerId() throws NotFoundException
    {
        HeaderProperties headers = getSIFHeaderProperties();
        String ownerId = null;

        switch (getProviderEnvironment().getEnvironmentType())
        {
        case DIRECT:
            // Application key is either in header or in user token
            ownerId = headers.getHeaderProperty(RequestHeaderConstants.HDR_APPLICATION_KEY,
                    getAuthInfo().getUserToken());
            break;
        case BROKERED:
            // Application key must have been moved into sourceName property
            ownerId = headers.getHeaderProperty(RequestHeaderConstants.HDR_SOURCE_NAME);
            break;
        }

        if (ownerId == null)
        {
            throw new NotFoundException("Could not identify consumer.");
        }
        return ownerId;
    }
}
