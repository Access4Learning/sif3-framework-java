/*
 * InfraDataModelMapper.java 
 * Created: 07/12/2015
 * 
 * Copyright 2015 Systemic Pty Ltd
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
package sif3.infra.rest.mapper;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.model.MultiOperationStatusList;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.model.CreateResponseType;
import sif3.infra.common.model.CreateType;
import sif3.infra.common.model.DeleteResponseType;
import sif3.infra.common.model.DeleteStatusType;
import sif3.infra.common.model.ErrorType;
import sif3.infra.common.model.UpdateResponseType;
import sif3.infra.common.model.UpdateType;

/**
 * This class is mainly responsible to map Infrastructure Data Model objects into a corresponding Framework friendly data model object.
 * The core intention of the framework is to abstract the SIF infrastructure from the developer. There are any places within the framework
 * where communication with SIF Infrastructure XSD related objects is required. These are always mapped to a more user friendly and
 * mime type independent data object that is given to a developer. This class maps some of these SIF Infrastructure objects to a developer
 * friendly objects. 
 * 
 * @author Joerg Huber
 */
public class InfraDataModelMapper implements Serializable
{
    private static final long serialVersionUID = -6153907819365178L;
    
	private InfraUnmarshalFactory infraUnmarshaller = new InfraUnmarshalFactory();

	public ErrorDetails toErrorFromSIFErrorString(String payload, MediaType mediaType, ErrorDetails defaultError)
	{
		ErrorDetails errorDetails = new ErrorDetails();
		try
		{
			//Because ErrorType is a Infrastructure thing we must ensure we use a valid Infrastructure Unmarshaller Media Type
			ErrorType error = (ErrorType)getInfraUnmarshaller().unmarshal(payload, ErrorType.class, mediaType);
			if (error == null) // this is strange. So set the unmarshalled value.
			{
				errorDetails = new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not unmarshal payload into ErrorType object. See error description for payload details.", payload);
			}
			else
			{
				if (StringUtils.isEmpty(error.getMessage()) && StringUtils.isEmpty(error.getDescription()) && (error.getCode() <= 0))
				{
					// It appears that we could not get a useful error from the entity string for whatever reason. Return default error.
					errorDetails = defaultError;
				}
				else
				{
					errorDetails = convertFromErrorType(error);
				}
			}
		}
		catch (UnmarshalException ex)
		{
			errorDetails = new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not unmarshal payload into ErrorType object: "+ex.getMessage()+". See error description for payload details.", payload);
		}
		catch (UnsupportedMediaTypeExcpetion ex)
		{
			errorDetails = new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal payload into ErrorType object (unsupported media type): "+ex.getMessage()+". See error description for payload details.", payload);
		}
		catch (Exception ex)
		{
			errorDetails = new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal payload into ErrorType object: "+ex.getMessage()+". See error description for payload details.", payload);
		}	
		
		return errorDetails;
	}

	public MultiOperationStatusList<CreateOperationStatus> toStatusListFromSIFCreateString(String payload, MediaType mediaType)
	{
		MultiOperationStatusList<CreateOperationStatus> statusList = new MultiOperationStatusList<CreateOperationStatus>();  
		try
		{						
			//Because CreateResponseType is a Infrastructure thing we must ensure we use the Infrastructure Unmarshaller
			CreateResponseType createManyResponse = (CreateResponseType)getInfraUnmarshaller().unmarshal(payload, CreateResponseType.class, mediaType);
			if (createManyResponse == null)// this is strange. So set the unmarshalled value.
			{
				statusList.setError(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not unmarshal multi-create payload. See error description for payload details.", payload));
			}
			else
			{
				statusList.setOperationStatuses(new ArrayList<CreateOperationStatus>());
				for (CreateType createStatus : createManyResponse.getCreates().getCreate())
				{
					CreateOperationStatus opStatus = new CreateOperationStatus();
					opStatus.setResourceID(createStatus.getId());
					opStatus.setAdvisoryID(createStatus.getAdvisoryId());
					opStatus.setStatus(toInt(createStatus.getStatusCode()));
					opStatus.setError(convertFromErrorType(createStatus.getError()));
					statusList.getOperationStatuses().add(opStatus);
				}
			}
		}
		catch (UnmarshalException ex)
		{
			statusList.setError(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not unmarshal multi-create payload: "+ex.getMessage()+". See error description for payload details.", payload));
		}
		catch (UnsupportedMediaTypeExcpetion ex)
		{
			statusList.setError(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal multi-create payload (unsupported media type): "+ex.getMessage()+". See error description for payload details.", payload));
		}
		catch (Exception ex)
		{
			statusList.setError(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal multi-create payload: "+ex.getMessage()+". See error description for payload details.", payload));
		}
		
		return statusList;
	}

	public MultiOperationStatusList<OperationStatus> toStatusListFromSIFUpdateString(String payload, MediaType mediaType)
	{
		MultiOperationStatusList<OperationStatus> statusList = new MultiOperationStatusList<OperationStatus>();  
		try
		{						
			//Because UpdateResponseType is a Infrastructure thing we must ensure we use the Infrastructure Unmarshaller
			UpdateResponseType updateManyResponse = (UpdateResponseType)getInfraUnmarshaller().unmarshal(payload, UpdateResponseType.class, mediaType);
			if (updateManyResponse == null)// this is strange. So set the unmarshalled value.
			{
				statusList.setError(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not unmarshal multi-update payload. See error description for payload details.", payload));							
			}
			else
			{
				statusList.setOperationStatuses(new ArrayList<OperationStatus>());
				for (UpdateType updateStatus : updateManyResponse.getUpdates().getUpdate())
				{
					OperationStatus opStatus = new OperationStatus();
					opStatus.setResourceID(updateStatus.getId());
					opStatus.setStatus(toInt(updateStatus.getStatusCode()));
					opStatus.setError(convertFromErrorType(updateStatus.getError()));
					statusList.getOperationStatuses().add(opStatus);
				}
			}
		
		}
		catch (UnmarshalException ex)
		{
			statusList.setError(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not unmarshal multi-update payload: "+ex.getMessage()+". See error description for payload details.", payload));
		}
		catch (UnsupportedMediaTypeExcpetion ex)
		{
			statusList.setError(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal multi-update payload (unsupported media type): "+ex.getMessage()+". See error description for payload details.", payload));
		}
		catch (Exception ex)
		{
			statusList.setError(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal multi-update payload: "+ex.getMessage()+". See error description for payload details.", payload));
		}
		
		return statusList;
	}

	public MultiOperationStatusList<OperationStatus> toStatusListFromSIFDeleteString(String payload, MediaType mediaType)
	{
		MultiOperationStatusList<OperationStatus> statusList = new MultiOperationStatusList<OperationStatus>();  
		try
		{						
			//Because DeleteResponseType is a Infrastructure thing we must ensure we use the Infrastructure Unmarshaller
			DeleteResponseType deleteManyResponse = (DeleteResponseType)getInfraUnmarshaller().unmarshal(payload, DeleteResponseType.class, mediaType);
			if (deleteManyResponse == null)// this is strange. So set the unmarshalled value.
			{
				statusList.setError(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not unmarshal multi-delete payload. See error description for payload details.", payload));							
			}
			else
			{
				statusList.setOperationStatuses(new ArrayList<OperationStatus>());
				for (DeleteStatusType deleteStatus : deleteManyResponse.getDeletes().getDelete())
				{
					OperationStatus opStatus = new OperationStatus();
					opStatus.setResourceID(deleteStatus.getId());
					opStatus.setStatus(toInt(deleteStatus.getStatusCode()));
					opStatus.setError(convertFromErrorType(deleteStatus.getError()));
					statusList.getOperationStatuses().add(opStatus);
				}
			}

		}
		catch (UnmarshalException ex)
		{
			statusList.setError(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Could not unmarshal multi-delete payload: "+ex.getMessage()+". See error description for payload details.", payload));
		}
		catch (UnsupportedMediaTypeExcpetion ex)
		{
			statusList.setError(new ErrorDetails(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), "Could not unmarshal multi-delete payload (unsupported media type): "+ex.getMessage()+". See error description for payload details.", payload));
		}
		catch (Exception ex)
		{
			statusList.setError(new ErrorDetails(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to unmarshal multi-delete payload: "+ex.getMessage()+". See error description for payload details.", payload));
		}
		
		return statusList;
	}
	
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/

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

	protected ErrorDetails convertFromErrorType(ErrorType errorType)
	{
		if (errorType == null)
		{
			return null;
		}
		return new ErrorDetails((int)errorType.getCode(), errorType.getMessage(), errorType.getDescription(), errorType.getScope());
	}	
	
	private InfraUnmarshalFactory getInfraUnmarshaller()
	{
		return infraUnmarshaller;
	}
}
