
package sif3.infra.rest.resource;

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

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.BadRequestException;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.ForbiddenException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIF3Exception;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.header.ResponseHeaderConstants;
import sif3.common.interfaces.ChangesSinceProvider;
import sif3.common.interfaces.Provider;
import sif3.common.interfaces.QueryProvider;
import sif3.common.model.ChangedSinceInfo;
import sif3.common.model.PagingInfo;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.persist.model.SIF3Session;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.infra.common.env.mgr.ProviderManagerFactory;
import sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.common.model.StateType;
import sif3.infra.rest.provider.BaseFunctionalServiceProvider;
import sif3.infra.rest.provider.ProviderFactory;

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
public class ServiceResource extends InfraResource {

	protected String infraObjectNamePlural = null;
	protected Provider provider = null;

	/**
	 * Initialises an Provider Resource. All the parameters are automatically
	 * injected by the Jersey Framework.
	 * 
	 * @param uriInfo
	 *          Extracted from the request.
	 * @param requestHeaders
	 *          Extracted from the request.
	 * @param request
	 *          Extracted from the request.
	 * @param infraObjectNamePlural
	 *          Extracted from the request.
	 * @param mimeType
	 *          The mime type postfix that might be set on the request URL.
	 * @param zoneID
	 *          Extracted from the request (Matrix Parameter).
	 * @param contextID
	 *          Extracted from the request (Matrix Parameter).
	 */
	public ServiceResource(@Context UriInfo uriInfo, @Context HttpHeaders requestHeaders, @Context Request request, @PathParam("infraObjectNamePlural") String infraObjectNamePlural, @PathParam("mimeType") String mimeType, @MatrixParam("zoneId") String zoneID, @MatrixParam("contextId") String contextID) {
		super(uriInfo, requestHeaders, request, "services", zoneID, contextID);

		this.infraObjectNamePlural = infraObjectNamePlural;

		if (logger.isDebugEnabled()) {
			logger.debug("Service to use: " + infraObjectNamePlural);
		}

		provider = ProviderFactory.getInstance().getProvider(new ModelObjectInfo(this.infraObjectNamePlural, null));
		if (provider != null) {
			determineMediaTypes(provider.getMarshaller(), provider.getUnmarshaller(), false);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Request Media Type : " + getRequestMediaType());
			logger.debug("Response Media Type: " + getResponseMediaType());
		}
	}

	/*----------------------*/
	/*-- Abstract Methods --*/
	/*----------------------*/

	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.resource.BaseResource#getEnvironmentManager()
	 */
	@Override
	public EnvironmentManager getEnvironmentManager() {
		return ProviderManagerFactory.getEnvironmentManager();
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.resource.BaseResource#getMarshaller()
	 */
	@Override
	public MarshalFactory getMarshaller() {
		return (getProvider() != null) ? getProvider().getMarshaller() : null;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.resource.BaseResource#getUnmarshaller()
	 */
	@Override
	public UnmarshalFactory getUnmarshaller() {
		return (getProvider() != null) ? getProvider().getUnmarshaller() : null;
	}

	// -------------------------------------------------//
	// -- POST Section: This is the C(reate) in CRUD. --//
	// -------------------------------------------------//
	@POST
	@Path("{infraObjectNameSingle:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response createSingle(String payload, @PathParam("infraObjectNameSingle") String infraObjectNameSingle, @PathParam("mimeType") String mimeType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Create Single " + infraObjectNameSingle + " (REST POST) with URL Postfix mimeType = '" + mimeType + "' and input data: " + payload);
		}

		ResponseParameters responseParam = getInitialCustomResponseParameters();
		
		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.CREATE), AccessType.APPROVED, false, false);
		if (error != null) // Not allowed to access!
		{
			return makeErrorResponse(error, ResponseAction.CREATE, responseParam);
		}

		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error
													 // response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.CREATE, responseParam);
		}

		try {
			Object returnObj = provider.createSingle(provider.getUnmarshaller().unmarshal(payload, provider.getSingleObjectClassInfo().getObjectType(), getRequestMediaType()), getAdvisory(), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
			
			return makeResponse(returnObj, Status.CREATED.getStatusCode(), false, ResponseAction.CREATE, responseParam, provider.getMarshaller());
		} catch (PersistenceException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create " + provider.getSingleObjectClassInfo().getObjectName() + ". Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		} catch (UnmarshalException ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the given data to " + provider.getSingleObjectClassInfo().getObjectName() + ". Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		} catch (UnsupportedMediaTypeException ex) {
			return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal the given data to " + provider.getSingleObjectClassInfo().getObjectName() + ". Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		}
	}

	@POST
	public Response createMany(String payload) {
		// Check what is really required: GET (QBE functionality) or POST (Create
		// functionality)
		boolean isQBE = HeaderValues.MethodType.GET.name().equalsIgnoreCase(getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE));

		if (logger.isDebugEnabled()) {
			if (isQBE) {
				logger.debug("QBE on " + infraObjectNamePlural + " (REST POST, method OVERRIDE=GET) with input data: " + payload);
			} else {
				logger.debug("Create Many " + infraObjectNamePlural + " (REST POST) with input data: " + payload);
			}
		}
		
		ResponseParameters responseParam = getInitialCustomResponseParameters();

		ErrorDetails error = validClient(infraObjectNamePlural, ((isQBE) ? getRight(AccessRight.QUERY) : getRight(AccessRight.CREATE)), AccessType.APPROVED, true, true);
		if (error != null) // Not allowed to access!
		{
			logger.debug("Error Found: " + error);
			return makeErrorResponse(error, ((isQBE) ? ResponseAction.QUERY : ResponseAction.CREATE), responseParam);
		}

		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error
													 // response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ((isQBE) ? ResponseAction.QUERY : ResponseAction.CREATE), responseParam);
		}

		return (isQBE) ? queryByQBE(provider, payload) : createMany(provider, payload);
	}

	// --------------------------------------------------------//
	// -- GET Section: This is the R(ead) in CRUD for Lists. --//
	// --------------------------------------------------------//
	@GET
	@Path("{resourceID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response getSingle(@PathParam("resourceID") String resourceID, @PathParam("mimeType") String mimeType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Get Resource by Resource ID (REST GET - Single): " + resourceID + " and URL Postfix mimeType = '" + mimeType + "'");
		}
		
		ResponseParameters responseParam = getInitialCustomResponseParameters();

		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.QUERY), AccessType.APPROVED, false, true);
		if (error != null) // Not allowed to access!
		{
			return makeErrorResponse(error, ResponseAction.QUERY, responseParam);
		}

		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error
													 // response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.QUERY, responseParam);
		}

		try {
			Object returnObj = provider.retrieveByPrimaryKey(resourceID, getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);

			if (returnObj != null) {
				return makeResponse(returnObj, Status.OK.getStatusCode(), false, ResponseAction.QUERY, responseParam, provider.getMarshaller());
			} else {
				return makeErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + " does not exist."), ResponseAction.QUERY, responseParam);
			}
		} catch (PersistenceException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve " + provider.getSingleObjectClassInfo().getObjectName() + " for resource ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY, responseParam);
		} catch (IllegalArgumentException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY, responseParam);
		}
	}

	@GET
	public Response getMany() {
		if (logger.isDebugEnabled()) {
			logger.debug("Get List (REST GET - Plural)");
		}
		
		ResponseParameters responseParam = getInitialCustomResponseParameters();

		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.QUERY), AccessType.APPROVED, true, true);
		if (error != null) // Not allowed to access!
		{
			return makeErrorResponse(error, ResponseAction.QUERY, responseParam);
		}

		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.QUERY, responseParam);
		}

		PagingInfo pagingInfo = null;
		try
		{
	        pagingInfo = getPagingInfo();
		    if (pretendDelayed())
		    {
		        // Simply send a response with status of 202
		        return makeDelayedAcceptResponse(ResponseAction.QUERY);
		    }
		    else
		    {
		        // We need to check if the request is for "changes since" functionality. This is the case if the provider implements
		        // the ChangesSinceProvider and the changesSinceMarker has been provided. If either if these criterias are not met
		        // then we must assume that a standard request to get a list of objects is required.
		        ChangesSinceProvider csProvider = getChangesSinceProvider(provider);
		        String changesSinceMarker = getChangesSinceMarker();
		        if ((csProvider != null) && (changesSinceMarker != null)) // We have a ChangesSince request and all is in order
		        {
		            if (csProvider.changesSinceSupported())
		            {
                        //Get new changes since marker if page = first page or no paging info
		                HeaderProperties customHeaders = null;
		                String newChangesSinceMarker = null;
		                if ((pagingInfo == null) || (pagingInfo.getCurrentPageNo() == CommonConstants.FIRST_PAGE))
		                {
		                    newChangesSinceMarker = csProvider.getLatestOpaqueMarker(getSifZone(), getSifContext(), pagingInfo, getRequestMetadata(getSIF3SessionForRequest(), true));
                            customHeaders = new HeaderProperties();
		                    customHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_CHANGES_SINCE_MARKER, newChangesSinceMarker);
		                }
		                
		                // Return the results.
                        Object returnObj = csProvider.getChangesSince(getSifZone(), getSifContext(), pagingInfo, new ChangedSinceInfo(changesSinceMarker), getRequestMetadata(getSIF3SessionForRequest(), true), responseParam);

                        // Check if we have pagingInfo parameter and if so if the navigationID is set. If it is not set we set it to the value of the
                        // newChangesSinceMarker. Consumer can use this to identify which query the provider ran in subsequent paged queries.
                        if ((pagingInfo != null) && (StringUtils.isEmpty(pagingInfo.getNavigationId()) && (newChangesSinceMarker != null)))
                        {
                            pagingInfo.setNavigationId(newChangesSinceMarker);
                        }

                        return makePagedResponse(returnObj, pagingInfo, false, responseParam, provider.getMarshaller()); 
		                
		            }
		            else // changes since is not supported => Error
		            {
                        return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Provider for "+infraObjectNamePlural+" does not support 'ChangesSince' functionality."), ResponseAction.QUERY, responseParam);                                 		                
		            }
		        }
		        else // It is a standard request and/or provider
		        {
		            if (changesSinceMarker != null) // Provider is a standard provider but changesSince marker is provided => Error
		            {
		                return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Provider for "+infraObjectNamePlural+" does not support 'ChangesSince' functionality."), ResponseAction.QUERY, responseParam);          		                
		            }
		            else // All good.
		            {
		                Object returnObj = provider.retrieve(getSifZone(), getSifContext(), pagingInfo, getRequestMetadata(getSIF3SessionForRequest(), true), responseParam);
		                return makePagedResponse(returnObj, pagingInfo, false, responseParam, provider.getMarshaller());	
		            }
		        }
		    }
		}
		catch (PersistenceException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve "+provider.getMultiObjectClassInfo().getObjectName()+" with Paging Information: "+pagingInfo+". Problem reported: "+ex.getMessage()), ResponseAction.QUERY, responseParam);			
		}
		catch (IllegalArgumentException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve "+provider.getMultiObjectClassInfo().getObjectName()+" with Paging Information: "+pagingInfo+". Problem reported: "+ex.getMessage()), ResponseAction.QUERY, responseParam);			
		}
		catch (UnsupportedQueryException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Failed to retrieve "+provider.getMultiObjectClassInfo().getObjectName()+" with Paging Information: "+pagingInfo+". Problem reported: "+ex.getMessage()), ResponseAction.QUERY, responseParam);			
		}
		catch (DataTooLargeException ex)
		{
			return makeErrorResponse(new ErrorDetails(CommonConstants.RESPONSE_TOO_LARGE, "Failed to retrieve " + provider.getMultiObjectClassInfo().getObjectName() + " with Paging Information: " + pagingInfo + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY, responseParam);
		}
	}

	// ----------------------------------------------------------//
	// -- PUT Section: This is the U(pdate) in CRUD for Lists. --//
	// ----------------------------------------------------------//
	@PUT
	@Path("{resourceID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response updateSingle(String payload, @PathParam("resourceID") String resourceID, @PathParam("mimeType") String mimeType) {
		throw new UnsupportedOperationException("Update operations not supported on functional services");
	}

	@PUT
	public Response updateMany(String payload) {
		// Check what is really required: DELETE or UPDATE
		boolean doDelete = HeaderValues.MethodType.DELETE.name().equalsIgnoreCase(getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_METHOD_OVERRIDE));
	  
		if (logger.isDebugEnabled())
		{
			if (doDelete)
			{
				logger.debug("Delete Collection "+infraObjectNamePlural+" (REST PUT, method OVERRIDE=DELETE) with input data: " + payload);
			}
			else
			{
				logger.debug("Update Collection "+infraObjectNamePlural+" (REST PUT) with input data: " + payload);		    
			}
		}
		
		ResponseParameters responseParam = getInitialCustomResponseParameters();
		
		ErrorDetails error = validClient(infraObjectNamePlural, ((doDelete) ? getRight(AccessRight.DELETE) : getRight(AccessRight.UPDATE)), AccessType.APPROVED, true, true);
		if (error != null) // Not allowed to access!
		{
			logger.debug("Error Found: "+error);
			return makeErrorResponse(error, ((doDelete) ? ResponseAction.DELETE : ResponseAction.UPDATE), responseParam);
		}

		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for "+infraObjectNamePlural+" available."), ((doDelete) ? ResponseAction.DELETE : ResponseAction.UPDATE), responseParam);			
		}
	
		return (doDelete) ? deleteMany(provider, payload) : updateMany(provider, payload);
	}

	// -------------------------------------------------------------//
	// -- DELETE Section: This is the D(elete) in CRUD for Lists. --//
	// -------------------------------------------------------------//
	@DELETE
	@Path("{resourceID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response removeSingle(@PathParam("resourceID") String resourceID, @PathParam("mimeType") String mimeType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Remove Single " + infraObjectNamePlural + " (REST DELETE) with resourceID = " + resourceID + " and URL Postfix mimeType = '" + mimeType + "'.");
		}
		
		ResponseParameters responseParam = getInitialCustomResponseParameters();

		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.DELETE), AccessType.APPROVED, false, true);
		if (error != null) // Not allowed to access!
		{
			logger.debug("Error Found: " + error);
			return makeErrorResponse(error, ResponseAction.DELETE, responseParam);
		}

		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error
													 // response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.DELETE, responseParam);
		}

		try {
			if (provider.deleteSingle(resourceID, getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false), responseParam)) {
				return makeResopnseWithNoContent(false, ResponseAction.DELETE, responseParam);
			} else {
				return makeErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + " does not exist."), ResponseAction.DELETE, responseParam);
			}
		} catch (PersistenceException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.DELETE, responseParam);
		} catch (IllegalArgumentException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.DELETE, responseParam);
		}
	}

	@DELETE
	public Response removeMany(String payload)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Delete Collection "+infraObjectNamePlural+" (REST DELETE) with input data: " + payload);
		}
		ErrorDetails error = new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "Operation not supported.", "Use HTTP PUT with header field '"+RequestHeaderConstants.HDR_METHOD_OVERRIDE+"' set to "+HeaderValues.MethodType.DELETE.name()+" instead.");
		return makeErrorResponse(error, ResponseAction.DELETE, getInitialCustomResponseParameters());
	}

	/*----------------------------------------------------------------------*/
	/*-- HEAD Methods: Only the root Object service provides full support --*/
	/*----------------------------------------------------------------------*/
	/*
	 * HEAD Method for root service ie. .../infraObjectNamePlural. This is the
	 * only fully supported HEAD method that returns all sort of things about the
	 * service including custom HTTP headers if set by the provider.
	 */
	@HEAD
  public Response getServiceInfo()
  {
      if (logger.isDebugEnabled())
      {
          logger.debug("Get Service Info (REST HEAD)");
      }
  
      ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.QUERY), AccessType.APPROVED, true, true);
      if (error != null) // Not allowed to access!
      {
          return makeResponse(null, error.getErrorCode(), true, ResponseAction.HEAD, getInitialCustomResponseParameters(), null);
      }
      
      Provider provider = getProvider();
      if (provider == null) // error already logged but we must return an error response for the caller
      {
          return makeResponse(null, Status.SERVICE_UNAVAILABLE.getStatusCode(), true, ResponseAction.HEAD, getInitialCustomResponseParameters(), null);
      }
  
      PagingInfo pagingInfo = null;
      try
      {
          pagingInfo = getPagingInfo();
          HeaderProperties defaultCustomHeaders = getInitialCustomResponseParameters().getHttpHeaderParams();
          HeaderProperties customHeaders = provider.getServiceInfo(getSifZone(), getSifContext(), pagingInfo, getRequestMetadata(getSIF3SessionForRequest(), true));
          if (customHeaders != null)
          {
              // Copy customHeaders to defaultCustomHeaders to ensure the correct override order.
              defaultCustomHeaders.addHeaderProperties(customHeaders);
          }
          
          if (logger.isDebugEnabled())
          {
              logger.debug("Custom headers to be returned from 'getServiceInfo()' method:\n"+customHeaders);
          }

          //Check if provider supports Changes Since and if so we need to get the latest opaque changes since marker.
          ChangesSinceProvider csProvider = getChangesSinceProvider(provider);
          if (csProvider != null)
          {
              if (csProvider.changesSinceSupported())
              {
                  defaultCustomHeaders.setHeaderProperty(ResponseHeaderConstants.HDR_CHANGES_SINCE_MARKER, csProvider.getLatestOpaqueMarker(getSifZone(), getSifContext(), pagingInfo, getRequestMetadata(getSIF3SessionForRequest(), true)));
              }
              
          }

          ResponseParameters responseParams = new ResponseParameters(defaultCustomHeaders);
          return makePagedResponse(null, pagingInfo, false, responseParams, null);
      }
      catch (PersistenceException ex)
      {
          return makeResponse(null, Status.INTERNAL_SERVER_ERROR.getStatusCode(), true, ResponseAction.HEAD, getInitialCustomResponseParameters(), null);
      }
      catch (IllegalArgumentException ex)
      {
          return makeResponse(null, Status.INTERNAL_SERVER_ERROR.getStatusCode(), true, ResponseAction.HEAD, getInitialCustomResponseParameters(), null);
      }
      catch (UnsupportedQueryException ex)
      {
          return makeResponse(null, Status.BAD_REQUEST.getStatusCode(), true, ResponseAction.HEAD, getInitialCustomResponseParameters(), null);
      }
      catch (DataTooLargeException ex)
      {
          return makeResponse(null, CommonConstants.RESPONSE_TOO_LARGE, true, ResponseAction.HEAD, getInitialCustomResponseParameters(), null);
      } 
  }

	/*
	 * HEAD Method for single object service: Return basic info only and check
	 * ACL.
	 */
	@HEAD
	@Path("{resourceID:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
  public Response getSingleObjectServiceInfo()
  {
      if (logger.isDebugEnabled())
      {
          logger.debug("Get Single Object Service Info (REST HEAD)");
      }
      ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.QUERY), AccessType.APPROVED, false, true);
      if (error != null) // Not allowed to access!
      {
          return makeResponse(null, error.getErrorCode(), true, ResponseAction.QUERY, getInitialCustomResponseParameters(), null);
      }
      return makeResponse(null, Status.NO_CONTENT.getStatusCode(), false, ResponseAction.QUERY, getInitialCustomResponseParameters(), null);
  }

	/*------------------------*/
	/*-- Overridden Methods --*/
	/*------------------------*/
	@Override
	public SIFZone getSifZone() {
		SIFZone sifZone = super.getSifZone();
		if (sifZone == null) // default zone => Get default zone from session
		{
			SIF3Session session = getSIF3SessionForRequest();
			if (session != null) {
				sifZone = session.getDefaultZone();
			}
		}

		return sifZone;
	}

	@Override
	public SIFContext getSifContext() {
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
	protected Provider getProvider() {
		if (provider == null) // No provider known for this Object Type! This is an
													 // issue and needs to be logged.
		{
			logger.error("No Provider known for the object with the name: " + infraObjectNamePlural);
		}
		return provider;
	}

	protected boolean getAdvisory() {
		return Boolean.valueOf(getSIFHeaderProperties().getHeaderProperty(RequestHeaderConstants.HDR_ADVISORY, "false"));
	}

	/*
	 * This method is a helper to determine what the actual access right is. If a
	 * provider is a direct provider an access right is the actual right of the
	 * consumer as set in the environment ACL. If the provider is in a brokered
	 * environment its right is the ACL in relation to the broker. In such a case
	 * the right is simply 'PROVIDE'.
	 */
	protected AccessRight getRight(AccessRight directEnvRight) {
		// If we are in a brokered environment then the access right must be
		// PROVIDE. In a DIRECT environment the access right must be QUERY.
		return getProviderEnvironment().getEnvironmentType() == EnvironmentType.DIRECT ? directEnvRight : AccessRight.PROVIDE;
	}

	protected PagingInfo getPagingInfo() {
		PagingInfo pagingInfo = new PagingInfo(getSIFHeaderProperties(), getQueryParameters());
		if (pagingInfo.getPageSize() <= PagingInfo.NOT_DEFINED) // page size not
																														 // defined. Pass
																														 // null to
																														 // provider.
		{
			pagingInfo = null;
		} else {
			pagingInfo = pagingInfo.clone(); // ensure that initial values are not
																			 // overridden in case we need them later,
		}
		return pagingInfo;
	}

	/*
	 * Can return null if the changesSinceMarker is not given. In this case we can
	 * also assume that the call is not for a changeSince request.
	 */
	protected String getChangesSinceMarker() {
		return getQueryParameters().getQueryParam(CommonConstants.CHANGES_SINCE_MARKER_NAME);
	}

	/*
	 * If the given provider implements the ChangesSinceProvider then a casted
	 * provider is returned otherwise null is returned.
	 */
	protected ChangesSinceProvider getChangesSinceProvider(Provider provider) {
		if (ChangesSinceProvider.class.isAssignableFrom(provider.getClass())) {
			return (ChangesSinceProvider) provider;
		} else {
			return null;
		}
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
    	    	List<OperationStatus> statusList = provider.updateMany(provider.getUnmarshaller().unmarshal(payload, provider.getMultiObjectClassInfo().getObjectType(), getRequestMediaType()), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
    	      
    	    	if (statusList != null)
    	    	{
    	    		return makeUpdateMultipleResponse(statusList, Status.OK, responseParam);
    	    	}
    	    	else
    	    	{
    	    		return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update "+provider.getMultiObjectClassInfo().getObjectName()+" (Bulk Operation). Contact your System Administrator."), ResponseAction.UPDATE, responseParam);
    	    	}
	        }
	    }
	    catch (PersistenceException ex)
	    {
	    	return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update "+provider.getMultiObjectClassInfo().getObjectName()+" (Bulk Operation). Problem reported: "+ex.getMessage()), ResponseAction.UPDATE, responseParam);      
	    }
	    catch (UnmarshalException ex)
	    {
	    	return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the given data to "+provider.getMultiObjectClassInfo().getObjectName()+". Problem reported: "+ex.getMessage()), ResponseAction.UPDATE, responseParam);      
	    }
	    catch (UnsupportedMediaTypeException ex)
	    {
	    	return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal the given data to "+provider.getSingleObjectClassInfo().getObjectName()+". Problem reported: "+ex.getMessage()), ResponseAction.UPDATE, responseParam);     
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
    			List<OperationStatus> statusList = provider.deleteMany(getResourceIDsFromDeleteRequest(payload), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
          
    			if (statusList != null)
    			{
    				return makeDeleteMultipleResponse(statusList, Status.OK, responseParam);
    			}
    			else
    			{
    				return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete " + provider.getMultiObjectClassInfo().getObjectName() + " (Bulk Operation). Contact your System Administrator."), ResponseAction.DELETE, responseParam);
    			}
	        }
		}
		catch (PersistenceException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(),  "Failed to delete " + provider.getMultiObjectClassInfo().getObjectName()  + " (Bulk Operation). Problem reported: " + ex.getMessage()), ResponseAction.DELETE, responseParam);
		}
		catch (UnmarshalException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the given data to DeleteRequestType. Problem reported: " + ex.getMessage()), ResponseAction.DELETE, responseParam);
		}
		catch (UnsupportedMediaTypeException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal the given data to DeleteRequestType. Problem reported: " + ex.getMessage()), ResponseAction.DELETE, responseParam);
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
    			List<CreateOperationStatus> statusList = provider.createMany(provider.getUnmarshaller().unmarshal(payload, provider.getMultiObjectClassInfo().getObjectType(), getRequestMediaType()), getAdvisory(), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
    			
    			if (statusList != null)
    			{
    				return makeCreateMultipleResponse(statusList, Status.CREATED, responseParam);
    			}
    			else
    			{
    				return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create "+provider.getMultiObjectClassInfo().getObjectName()+" (Bulk Operation). Contact your System Administrator."), ResponseAction.CREATE, responseParam);
    			}	
	        }
		}
		catch (PersistenceException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create "+provider.getMultiObjectClassInfo().getObjectName()+" (Bulk Operation). Problem reported: "+ex.getMessage()), ResponseAction.CREATE, responseParam);			
		}
		catch (UnmarshalException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the given data to "+provider.getMultiObjectClassInfo().getObjectName()+". Problem reported: "+ex.getMessage()), ResponseAction.CREATE, responseParam);			
		}
		catch (UnsupportedMediaTypeException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal the given data to "+provider.getSingleObjectClassInfo().getObjectName()+". Problem reported: "+ex.getMessage()), ResponseAction.CREATE, responseParam);     
		}
	}

	private Response queryByQBE(Provider provider, String payload)
	{
        ResponseParameters responseParam = getInitialCustomResponseParameters();
		
		if (provider == null || !QueryProvider.class.isAssignableFrom(provider.getClass()))
		{
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "The " + provider.getMultiObjectClassInfo().getObjectName() + " does not support QBE style queries."), ResponseAction.QUERY, responseParam);
		}

        PagingInfo pagingInfo = null;
		try
		{
	        pagingInfo = getPagingInfo();
            if (pretendDelayed())
            {
                // Simply send a response with status of 202
                return makeDelayedAcceptResponse(ResponseAction.QUERY);
            }
            else
            {
                Object returnObj = QueryProvider.class.cast(provider).retrieveByQBE(provider.getUnmarshaller().unmarshal(payload, provider.getSingleObjectClassInfo().getObjectType(), getRequestMediaType()), getSifZone(), getSifContext(), pagingInfo, getRequestMetadata(getSIF3SessionForRequest(), true), responseParam);
			
                return makePagedResponse(returnObj, pagingInfo, false, responseParam, provider.getMarshaller());
	        }
		}
		catch (PersistenceException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "(QBE) Failed to retrieve " + provider.getMultiObjectClassInfo().getObjectName()  + " with Paging Information: " + pagingInfo + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY, responseParam);
		}
		catch (UnmarshalException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "(QBE) Could not unmarshal the given data to payload. Problem reported: " + ex.getMessage()), ResponseAction.QUERY, responseParam);
		}
		catch (IllegalArgumentException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "(QBE) Failed to retrieve " + provider.getMultiObjectClassInfo().getObjectName() + " with Paging Information: " + pagingInfo + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY, responseParam);
		}
		catch (UnsupportedQueryException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "(QBE) Failed to retrieve " + provider.getMultiObjectClassInfo().getObjectName() + " with Paging Information: " + pagingInfo + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY, responseParam);
		}
		catch (DataTooLargeException ex)
		{
			return makeErrorResponse(new ErrorDetails(CommonConstants.RESPONSE_TOO_LARGE, "(QBE) Failed to retrieve " + provider.getMultiObjectClassInfo().getObjectName() + " with Paging Information: " + pagingInfo + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY, responseParam);
		}
		catch (UnsupportedMediaTypeException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "(QBE) Could not unmarshal the given data to "+provider.getSingleObjectClassInfo().getObjectName()+". Problem reported: "+ex.getMessage()), ResponseAction.QUERY, responseParam);     
		}
	}

	// ------------ //
	// -- Phases -- //
	// ------------ //
	@POST
	@Path("{resourceID:([^\\.]*)}/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response createToPhase(String payload, @PathParam("resourceID") String resourceID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Create to phase " + phaseName + " (REST POST) with URL Postfix mimeType = '" + mimeType + "' and input data: " + payload);
		}
		
		ResponseParameters responseParam = getInitialCustomResponseParameters();

		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.CREATE), AccessType.APPROVED, false, false);
		if (error != null) {
			return makeErrorResponse(error, ResponseAction.CREATE, responseParam);
		}

		BaseFunctionalServiceProvider provider = (BaseFunctionalServiceProvider) getProvider();
		if (provider == null) {
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.CREATE, responseParam);
		}

		// Ignore the marshaller/unmarshaller - just reflect the contentType and
		// accept header information
		determineMediaTypes(null, null, false);

		try {
			String returnPayload = provider.createToPhase(resourceID, phaseName, payload, getRequestMediaType(), getResponseMediaType(), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
			return makeResponse(returnPayload, Status.CREATED.getStatusCode(), false, ResponseAction.CREATE, responseParam, null);
		} catch (PersistenceException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create phase for job " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		} catch (UnmarshalException ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the data. Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		} catch (UnsupportedMediaTypeException ex) {
			return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal the data. Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		} catch (UnsupportedQueryException ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Unsupported operation. Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		} catch (BadRequestException ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Bad request. Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		} catch (ForbiddenException ex) {
			return makeErrorResponse(new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", AccessRight.CREATE.name() + " access is not set to " + AccessType.APPROVED.name() + " for the " + infraObjectNamePlural + " functional service", "Provider side check."), ResponseAction.CREATE, responseParam);
		} catch (SIF3Exception ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Unexpected error. Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		}
	}

	@GET
	@Path("{resourceID:([^\\.]*)}/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response retrieveToPhase(String payload, @PathParam("resourceID") String resourceID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Get phase " + phaseName + " by Job ID (REST GET): " + resourceID + " and URL Postfix mimeType = '" + mimeType + "'");
		}
		
		ResponseParameters responseParam = getInitialCustomResponseParameters();

		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.QUERY), AccessType.APPROVED, false, false);
		if (error != null) {
			return makeErrorResponse(error, ResponseAction.QUERY, responseParam);
		}

		BaseFunctionalServiceProvider provider = (BaseFunctionalServiceProvider) getProvider();
		if (provider == null) {
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.CREATE, responseParam);
		}

		// Ignore the marshaller/unmarshaller - just reflect the contentType and
		// accept header information
		determineMediaTypes(null, null, false);

		try {
			Object returnObj = provider.retrieveToPhase(resourceID, phaseName, payload, getRequestMediaType(), getResponseMediaType(), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
			if (returnObj != null) {
				return makeResponse(returnObj, Status.OK.getStatusCode(), false, ResponseAction.QUERY, responseParam, null);
			} else {
				return makeErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + " does not exist."), ResponseAction.QUERY, responseParam);
			}
		} catch (PersistenceException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve " + provider.getSingleObjectClassInfo().getObjectName() + " for resource ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY, responseParam);
		} catch (IllegalArgumentException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY, responseParam);
		} catch (ForbiddenException ex) {
			return makeErrorResponse(new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", AccessRight.CREATE.name() + " access is not set to " + AccessType.APPROVED.name() + " for the " + infraObjectNamePlural + " functional service", "Provider side check."), ResponseAction.QUERY, responseParam);
		} catch (SIF3Exception ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.QUERY, responseParam);
		}
	}

	@PUT
	@Path("{resourceID:([^\\.]*)}/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response updateToPhase(String payload, @PathParam("resourceID") String resourceID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Update to phase " + phaseName + " (REST POST) with URL Postfix mimeType = '" + mimeType + "' and input data: " + payload);
		}
		
		ResponseParameters responseParam = getInitialCustomResponseParameters();

		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.UPDATE), AccessType.APPROVED, false, false);
		if (error != null) {
			return makeErrorResponse(error, ResponseAction.UPDATE, responseParam);
		}

		BaseFunctionalServiceProvider provider = (BaseFunctionalServiceProvider) getProvider();
		if (provider == null) {
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.CREATE, responseParam);
		}

		// Ignore the marshaller/unmarshaller - just reflect the contentType and
		// accept header information
		determineMediaTypes(null, null, false);

		try {
			String returnObj = provider.updateToPhase(resourceID, phaseName, payload, getRequestMediaType(), getResponseMediaType(), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
			return makeResponse(returnObj, Status.OK.getStatusCode(), false, ResponseAction.UPDATE, responseParam, null);
		} catch (PersistenceException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.UPDATE, responseParam);
		} catch (IllegalArgumentException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.UPDATE, responseParam);
		} catch (UnmarshalException ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the given data to " + provider.getSingleObjectClassInfo().getObjectName() + ". Problem reported: " + ex.getMessage()), ResponseAction.UPDATE, responseParam);
		} catch (UnsupportedMediaTypeException ex) {
			return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal the given data to " + provider.getSingleObjectClassInfo().getObjectName() + ". Problem reported: " + ex.getMessage()), ResponseAction.UPDATE, responseParam);
		} catch (ForbiddenException ex) {
			return makeErrorResponse(new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", AccessRight.CREATE.name() + " access is not set to " + AccessType.APPROVED.name() + " for the " + infraObjectNamePlural + " functional service", "Provider side check."), ResponseAction.UPDATE, responseParam);
		} catch (SIF3Exception ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.UPDATE, responseParam);
		}
	}

	@DELETE
	@Path("{resourceID:([^\\.]*)}/{phaseName:([^\\.]*)}{mimeType:(\\.[^/]*?)?}")
	public Response deleteToPhase(String payload, @PathParam("resourceID") String resourceID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Delete to phase " + phaseName + " (REST POST) with URL Postfix mimeType = '" + mimeType + "' and input data: " + payload);
		}
		
		ResponseParameters responseParam = getInitialCustomResponseParameters();

		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.DELETE), AccessType.APPROVED, false, false);
		if (error != null) {
			return makeErrorResponse(error, ResponseAction.DELETE, responseParam);
		}

		BaseFunctionalServiceProvider provider = (BaseFunctionalServiceProvider) getProvider();
		if (provider == null) {
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.CREATE, responseParam);
		}

		// Ignore the marshaller/unmarshaller - just reflect the contentType and
		// accept header information
		determineMediaTypes(null, null, false);

		try {
			String returnObj = provider.deleteToPhase(resourceID, phaseName, payload, getRequestMediaType(), getResponseMediaType(), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);
			return makeResponse(returnObj, Status.OK.getStatusCode(), false, ResponseAction.UPDATE, responseParam, null);
		} catch (PersistenceException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.DELETE, responseParam);
		} catch (IllegalArgumentException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.DELETE, responseParam);
		} catch (ForbiddenException ex) {
			return makeErrorResponse(new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", AccessRight.CREATE.name() + " access is not set to " + AccessType.APPROVED.name() + " for the " + infraObjectNamePlural + " functional service", "Provider side check."), ResponseAction.DELETE, responseParam);
		} catch (SIF3Exception ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete " + provider.getSingleObjectClassInfo().getObjectName() + " with resouce ID = " + resourceID + ". Problem reported: " + ex.getMessage()), ResponseAction.DELETE, responseParam);
		}
	}

	// ------------ //
	// -- States -- //
	// ------------ //
	@POST
	@Path("{resourceID:([^\\.]*)}/{phaseName:([^\\.]*)}/states/state{mimeType:(\\.[^/]*?)?}")
	public Response createToState(String payload, @PathParam("resourceID") String resourceID, @PathParam("phaseName") String phaseName, @PathParam("mimeType") String mimeType) {
		if (logger.isDebugEnabled()) {
			logger.debug("Create to state on " + phaseName + " (REST POST) with URL Postfix mimeType = '" + mimeType + "' and input data: " + payload);
		}
		
		ResponseParameters responseParam = getInitialCustomResponseParameters();

		ErrorDetails error = validClient(infraObjectNamePlural, getRight(AccessRight.UPDATE), AccessType.APPROVED, false, false);
		if (error != null) {
			return makeErrorResponse(error, ResponseAction.CREATE, responseParam);
		}

		BaseFunctionalServiceProvider provider = (BaseFunctionalServiceProvider) getProvider();
		if (provider == null) {
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for " + infraObjectNamePlural + " available."), ResponseAction.CREATE, responseParam);
		}

		try {
			StateType state = provider.createToState(resourceID, phaseName, provider.getUnmarshaller().unmarshal(payload, StateType.class, getRequestMediaType()), getSifZone(), getSifContext(), getRequestMetadata(getSIF3SessionForRequest(), false), responseParam);

			return makeResponse(state, Status.CREATED.getStatusCode(), false, ResponseAction.CREATE, responseParam, provider.getMarshaller());
		} catch (PersistenceException ex) {
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create State object. Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		} catch (UnmarshalException ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal data. Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		} catch (UnsupportedMediaTypeException ex) {
			return makeErrorResponse(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal data due to unsupported media type. Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		} catch (SIF3Exception ex) {
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Unexpected error. Problem reported: " + ex.getMessage()), ResponseAction.CREATE, responseParam);
		}
	}
}
