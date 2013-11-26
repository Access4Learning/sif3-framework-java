/*
 * DataModelResource.java
 * Created: 24/09/2013
 *
 * Copyright 2013 Systemic Pty Ltd
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

package sif3.infra.rest.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import sif3.common.conversion.ModelObjectInfo;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.provider.Provider;
import sif3.common.provider.ProviderFactory;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import au.com.systemic.framework.utils.StringUtils;

/**
 * This is the generic implementation of all Object resources. It implements all the functions required by the SIF3 specification
 * for a provider of Data Model Objects. It makes use of the provider property file to determine what the valid objects are.<br/><br/>
 * 
 * Developers are not expected to use this class to write providers. It is a full and generic implementation. The developers are
 * expected to extend the BaseProvider and then configure the rest in the provider's property file. These two bits are then utilised
 * by this generic resource to correctly invoke the final provider classes. Please refer to the developer's guide for detail on
 * which classes need to be implemented to write a provider.<br/><br/>
 * 
 * This class makes one assumption though, and that is that the base URI for all object providers is of the form:<br/>
 * http://<baseURI>/requests/<ObjectName>...<br/><br/>
 * 
 * It must be ensured that in all the environments managed with this framework that the "request" connector URI follows this 
 * structure.
 * 
 * @author Joerg Huber
 */
@Path("/requests/{dmObjectNamePlural}")
public class DataModelResource extends BaseResource
{
	private String dmObjectNamePlural = null; // This is also expected to be the key into the provider factory.
	private static ProviderFactory factory = null;
	private Provider provider = null;

	/**
	 * Initailises an Object Provider Resource. All the parameters are automatically injected by the Jersey Framework.
	 * 
	 * @param uriInfo Extracted from the request.
	 * @param requestHeaders Extracted from the request.
	 * @param request Extracted from the request.
	 * @param dmObjectNamePlural Extracted from the request.
	 * @param zoneID Extracted from the request (Matrix Parameter).
	 * @param contextID Extracted from the request (Matrix Parameter).
	 */
    public DataModelResource(@Context UriInfo uriInfo,
			                 @Context HttpHeaders requestHeaders,
			                 @Context Request request,
			                 @PathParam("dmObjectNamePlural") String dmObjectNamePlural,
			                 @MatrixParam("zoneId") String zoneID,
			                 @MatrixParam("contextId") String contextID)
    {
	    super(uriInfo, requestHeaders, request, dmObjectNamePlural);
	    this.dmObjectNamePlural = dmObjectNamePlural;
	    
	    if (StringUtils.notEmpty(zoneID))
	    {
	      setSifZone(new SIFZone(zoneID));
	    }
	    
	    if (StringUtils.notEmpty(contextID))
	    {
	      setSifContext(new SIFContext(contextID));
	    }

	    System.out.println("DM Name = "+dmObjectNamePlural+"    Zone ID = "+zoneID+"     Context ID = "+contextID);
	    
	    // Initialise Factory if it is accessed for the first time.
	    if (factory == null)
	    {
	    	factory = ProviderFactory.getFactory(getServiceProperties());
	    }
	    
	    provider = factory.getProvider(new ModelObjectInfo(dmObjectNamePlural, null));
    }
    
	// -------------------------------------------------//
	// -- POST Section: This is the C(reate) in CRUD. --//
	// -------------------------------------------------//
	@POST
	@Path("{dmObjectNameSingle}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createSingle(String payload, @PathParam("dmObjectNameSingle") String dmObjectNameSingle)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Create Single "+dmObjectNameSingle+" (REST POST) with input data: " + payload);
		}
		
		ErrorDetails error = validClient();
		if (error != null) // Not allowed to access!
		{
			return makeErrorResponse(error, ResponseAction.CREATE);
		}
		
		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for "+dmObjectNamePlural+" available."), ResponseAction.CREATE);			
		}
	
		try
		{
			Object returnObj = provider.createSingle(provider.getUnmarshaller().unmarschal(payload, provider.getSingleObjectClassInfo().getObjectType(), getMediaType()), getSifZone(), getSifContext());

			return makeResponse(returnObj, Status.CREATED.getStatusCode(), false, ResponseAction.CREATE, provider.getMarshaller());
		}
		catch (PersistenceException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create "+provider.getSingleObjectClassInfo().getObjectName()+". Problem reported: "+ex.getMessage()), ResponseAction.CREATE);			
		}
		catch (UnmarshalException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the given data to "+provider.getSingleObjectClassInfo().getObjectName()+". Problem reported: "+ex.getMessage()), ResponseAction.CREATE);			
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createMany(String payload)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Create Many "+dmObjectNamePlural+" (REST POST) with input data: " + payload);
		}
		
		ErrorDetails error = validClient();
		if (error != null) // Not allowed to access!
		{
			return makeErrorResponse(error, ResponseAction.CREATE);
		}
		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for "+dmObjectNamePlural+" available."), ResponseAction.CREATE);			
		}
	
		try
		{
			List<OperationStatus> statusList = provider.createMany(provider.getUnmarshaller().unmarschal(payload, provider.getMultiObjectClassInfo().getObjectType(), getMediaType()), getSifZone(), getSifContext());
			
			if (statusList != null)
			{
				return makeCreateMultipleResponse(statusList, Status.CREATED);
			}
			else
			{
				return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create "+provider.getMultiObjectClassInfo().getObjectName()+" (Bulk Operation). Contact your System Administrator."), ResponseAction.CREATE);
			}			
		}
		catch (PersistenceException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to create "+provider.getMultiObjectClassInfo().getObjectName()+" (Bulk Operation). Problem reported: "+ex.getMessage()), ResponseAction.CREATE);			
		}
		catch (UnmarshalException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the given data to "+provider.getMultiObjectClassInfo().getObjectName()+". Problem reported: "+ex.getMessage()), ResponseAction.CREATE);			
		}
	}

	// --------------------------------------------------------//
	// -- GET Section: This is the R(ead) in CRUD for Lists. --//
	// --------------------------------------------------------//
	@GET
	@Path("{resourceID}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSingle(@PathParam("resourceID") String resourceID)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Get Resource by Resoucre ID (REST GET - Single): "+resourceID);
		}
		
		ErrorDetails error = validClient();
		if (error != null) // Not allowed to access!
		{
			return makeErrorResponse(error, ResponseAction.QUERY);
		}

		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for "+dmObjectNamePlural+" available."), ResponseAction.QUERY);			
		}
	
		try
		{
			Object returnObj = provider.retrievByPrimaryKey(resourceID, getSifZone(), getSifContext());
			
			if (returnObj != null)
			{
				return makeResponse(returnObj, Status.OK.getStatusCode(), false, ResponseAction.QUERY, provider.getMarshaller());
			}
			else
			{
				return makeErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), provider.getSingleObjectClassInfo().getObjectName()+" with resouce ID = "+resourceID+" does not exist."), ResponseAction.QUERY);
			}
		}
		catch (PersistenceException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve "+provider.getSingleObjectClassInfo().getObjectName()+" for resource ID = "+resourceID+". Problem reported: "+ex.getMessage()), ResponseAction.QUERY);			
		}
		catch (IllegalArgumentException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve "+provider.getSingleObjectClassInfo().getObjectName()+" with resouce ID = "+resourceID+". Problem reported: "+ex.getMessage()), ResponseAction.QUERY);			
		}
	}

	@GET
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getMany()
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Get List (REST GET - Plural)");
		}
		
		ErrorDetails error = validClient();
		if (error != null) // Not allowed to access!
		{
			return makeErrorResponse(error, ResponseAction.QUERY);
		}
		
		setQueryMetadata(new QueryMetadata(getUriInfo().getQueryParameters()));
		logger.debug(getQueryMetadata());

		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for "+dmObjectNamePlural+" available."), ResponseAction.QUERY);			
		}
	
		PagingInfo pagingInfo = (getQueryMetadata().getPagingInfo() == null) ? null : getQueryMetadata().getPagingInfo().clone();
		try
		{
			Object returnObj = provider.retrieve(getSifZone(), getSifContext(), pagingInfo);
			return makePagedResponse(returnObj, pagingInfo, false, provider.getMarshaller());
		}
		catch (PersistenceException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve "+provider.getMultiObjectClassInfo().getObjectName()+" with Paging Information: "+pagingInfo+". Problem reported: "+ex.getMessage()), ResponseAction.QUERY);			
		}
		catch (IllegalArgumentException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve "+provider.getMultiObjectClassInfo().getObjectName()+" with Paging Information: "+pagingInfo+". Problem reported: "+ex.getMessage()), ResponseAction.QUERY);			
		}
		catch (UnsupportedQueryException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to retrieve "+provider.getMultiObjectClassInfo().getObjectName()+" with Paging Information: "+pagingInfo+". Problem reported: "+ex.getMessage()), ResponseAction.QUERY);			
		}
	}


	// ----------------------------------------------------------//
	// -- PUT Section: This is the U(pdate) in CRUD for Lists. --//
	// ----------------------------------------------------------//
	@PUT
	@Path("{resourceID}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateSingle(String payload, @PathParam("resourceID") String resourceID)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Update Single "+dmObjectNamePlural+" (REST PUT) with resourceID = "+resourceID+" and input data: " + payload);
		}
		
		ErrorDetails error = validClient();
		if (error != null) // Not allowed to access!
		{
			return makeErrorResponse(error, ResponseAction.UPDATE);
		}

		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for "+dmObjectNamePlural+" available."), ResponseAction.UPDATE);			
		}
	
		try
		{
			if (provider.updateSingle(provider.getUnmarshaller().unmarschal(payload, provider.getSingleObjectClassInfo().getObjectType(), getMediaType()), resourceID, getSifZone(), getSifContext()))
			{
				return makeResopnseWithNoContent(false, ResponseAction.UPDATE);
			}
			else
			{
				return makeErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), provider.getSingleObjectClassInfo().getObjectName()+" with resouce ID = "+resourceID+" does not exist."), ResponseAction.UPDATE);
			}
		}
		catch (PersistenceException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update "+provider.getSingleObjectClassInfo().getObjectName()+" with resouce ID = "+resourceID+". Problem reported: "+ex.getMessage()), ResponseAction.UPDATE);			
		}
		catch (IllegalArgumentException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update "+provider.getSingleObjectClassInfo().getObjectName()+" with resouce ID = "+resourceID+". Problem reported: "+ex.getMessage()), ResponseAction.UPDATE);			
		}
		catch (UnmarshalException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the given data to "+provider.getSingleObjectClassInfo().getObjectName()+". Problem reported: "+ex.getMessage()), ResponseAction.UPDATE);			
		}
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateMany(String payload)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Update Collection "+dmObjectNamePlural+" (REST PUT) with input data: " + payload);
		}
		
		ErrorDetails error = validClient();
		if (error != null) // Not allowed to access!
		{
			logger.debug("Error Found: "+error);
			return makeErrorResponse(error, ResponseAction.UPDATE);
		}

		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for "+dmObjectNamePlural+" available."), ResponseAction.UPDATE);			
		}
	
		try
		{
			List<OperationStatus> statusList = provider.updateMany(provider.getUnmarshaller().unmarschal(payload, provider.getMultiObjectClassInfo().getObjectType(), getMediaType()), getSifZone(), getSifContext());
			
			if (statusList != null)
			{
				return makeUpdateMultipleResponse(statusList, Status.OK);
			}
			else
			{
				return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update "+provider.getMultiObjectClassInfo().getObjectName()+" (Bulk Operation). Contact your System Administrator."), ResponseAction.UPDATE);
			}			
		}
		catch (PersistenceException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to update "+provider.getMultiObjectClassInfo().getObjectName()+" (Bulk Operation). Problem reported: "+ex.getMessage()), ResponseAction.UPDATE);			
		}
		catch (UnmarshalException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the given data to "+provider.getMultiObjectClassInfo().getObjectName()+". Problem reported: "+ex.getMessage()), ResponseAction.UPDATE);			
		}
	}

	// -------------------------------------------------------------//
	// -- DELETE Section: This is the D(elete) in CRUD for Lists. --//
	// -------------------------------------------------------------//
	@DELETE
	@Path("{resourceID}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response removeSingle(@PathParam("resourceID") String resourceID)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Remove Single "+dmObjectNamePlural+" (REST DELETE) with resourceID = "+resourceID);
		}
		
		ErrorDetails error = validClient();
		if (error != null) // Not allowed to access!
		{
			logger.debug("Error Found: "+error);
			return makeErrorResponse(error, ResponseAction.DELETE);
		}

		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for "+dmObjectNamePlural+" available."), ResponseAction.DELETE);			
		}
	
		try
		{
			if (provider.deleteSingle(resourceID, getSifZone(), getSifContext()))
			{
				return makeResopnseWithNoContent(false, ResponseAction.DELETE);
			}
			else
			{
				return makeErrorResponse(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), provider.getSingleObjectClassInfo().getObjectName()+" with resouce ID = "+resourceID+" does not exist."), ResponseAction.DELETE);
			}
		}
		catch (PersistenceException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete "+provider.getSingleObjectClassInfo().getObjectName()+" with resouce ID = "+resourceID+". Problem reported: "+ex.getMessage()), ResponseAction.DELETE);			
		}
		catch (IllegalArgumentException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete "+provider.getSingleObjectClassInfo().getObjectName()+" with resouce ID = "+resourceID+". Problem reported: "+ex.getMessage()), ResponseAction.DELETE);			
		}
	}
	
	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response removeMany(String payload)
	{
		if (logger.isDebugEnabled())
		{
			logger.debug("Delete Collection "+dmObjectNamePlural+" (REST DELETE) with input data: " + payload);
		}
		
		ErrorDetails error = validClient();
		if (error != null) // Not allowed to access!
		{
			logger.debug("Error Found: "+error);
			return makeErrorResponse(error, ResponseAction.DELETE);
		}

		Provider provider = getProvider();
		if (provider == null) // error already logged but we must return an error response for the caller
		{
			return makeErrorResponse(new ErrorDetails(Status.SERVICE_UNAVAILABLE.getStatusCode(), "No Provider for "+dmObjectNamePlural+" available."), ResponseAction.DELETE);			
		}
	
		try
		{
			List<OperationStatus> statusList = provider.deleteMany(getResourceIDsFromDeleteRequest(payload), getSifZone(), getSifContext());
			
			if (statusList != null)
			{
				return makeUpdateMultipleResponse(statusList, Status.OK);
			}
			else
			{
				return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete "+provider.getMultiObjectClassInfo().getObjectName()+" (Bulk Operation). Contact your System Administrator."), ResponseAction.DELETE);
			}			
		}
		catch (PersistenceException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to delete "+provider.getMultiObjectClassInfo().getObjectName()+" (Bulk Operation). Problem reported: "+ex.getMessage()), ResponseAction.DELETE);			
		}
		catch (UnmarshalException ex)
		{
			return makeErrorResponse(new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Could not unmarshal the given data to DeleteRequestType. Problem reported: "+ex.getMessage()), ResponseAction.DELETE);			
		}
	}
	
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private Provider getProvider()
	{
		if (provider == null) // No provider known for this Object Type! This is an issue and needs to be logged.
		{
			logger.error("No Provider known for the object with the name: "+dmObjectNamePlural);
		}
		return provider;
	}

}
