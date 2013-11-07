/*
 * InfraUnmarshalFactory.java
 * Created: 08/09/2013
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

package sif3.infra.common.conversion;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.UnmarshalException;
import sif3.common.utils.JAXBUtils;
import sif3.infra.common.conversion.InfraObjectEnum.InfraModel;
import sif3.infra.common.model.AlertCollectionType;
import sif3.infra.common.model.AlertType;
import sif3.infra.common.model.CodeSetCollectionType;
import sif3.infra.common.model.CodeSetType;
import sif3.infra.common.model.CreateResponseType;
import sif3.infra.common.model.DeleteRequestType;
import sif3.infra.common.model.DeleteResponseType;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.ErrorType;
import sif3.infra.common.model.NamespaceCollectionType;
import sif3.infra.common.model.NamespaceType;
import sif3.infra.common.model.ProviderCollectionType;
import sif3.infra.common.model.ProviderType;
import sif3.infra.common.model.ProvisionRequestType;
import sif3.infra.common.model.QueueCollectionType;
import sif3.infra.common.model.QueueType;
import sif3.infra.common.model.SubscriptionCollectionType;
import sif3.infra.common.model.SubscriptionType;
import sif3.infra.common.model.UpdateResponseType;
import sif3.infra.common.model.XqueryCollectionType;
import sif3.infra.common.model.XqueryType;
import sif3.infra.common.model.ZoneCollectionType;
import sif3.infra.common.model.ZoneType;

/**
 * Implementation of an unmarshal Factory for all Infrastructure Model Objects. JAXB has been used to create Infrastructure POJOs and 
 * this Factory uses JAXB's methods to unmarshal POJOs from XML. At this point in time (October 2013) JSON is not yet supported.
 * 
 * @author Joerg Huber
 *
 */
public class InfraUnmarshalFactory implements UnmarshalFactory
{
	protected final Logger logger = Logger.getLogger(getClass());

	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.UnmarshalFactory#unmarshalFromXML(java.lang.String, java.lang.Class)
	 */
	@Override
	public Object unmarshalFromXML(String payload, Class<?> clazz) throws UnmarshalException
	{
		InfraModel infraModel = InfraObjectEnum.getInfraModelEnum(clazz.getSimpleName());
		if (infraModel != null)
		{
			 switch (infraModel)
			 {
			 	case QueueCollectionType:
			 	{
					return (QueueCollectionType) JAXBUtils.unmarshalFromXMLIntoObject(payload, QueueCollectionType.class);
			 	}
			 	case CreateResponseType:
			 	{
					return (CreateResponseType) JAXBUtils.unmarshalFromXMLIntoObject(payload, CreateResponseType.class);
			 	}

			 	case ZoneType:
			 	{
					return (ZoneType) JAXBUtils.unmarshalFromXMLIntoObject(payload, ZoneType.class);
			 	}

			 	case EnvironmentType:
			 	{	
					return (EnvironmentType) JAXBUtils.unmarshalFromXMLIntoObject(payload, EnvironmentType.class);
			 	}

			 	case DeleteResponseType:
			 	{
					return (DeleteResponseType) JAXBUtils.unmarshalFromXMLIntoObject(payload, DeleteResponseType.class);
			 	}

			 	case UpdateResponseType:
			 	{
					return (UpdateResponseType) JAXBUtils.unmarshalFromXMLIntoObject(payload, UpdateResponseType.class);
			 	}

			 	case AlertCollectionType:
			 	{
					return (AlertCollectionType) JAXBUtils.unmarshalFromXMLIntoObject(payload, AlertCollectionType.class);
			 	}

			 	case SubscriptionType:
			 	{
					return (SubscriptionType) JAXBUtils.unmarshalFromXMLIntoObject(payload, SubscriptionType.class);
			 	}

			 	case ProviderCollectionType:
			 	{
					return (ProviderCollectionType) JAXBUtils.unmarshalFromXMLIntoObject(payload, ProviderCollectionType.class);
			 	}

			 	case ProviderType:
			 	{
					return (ProviderType) JAXBUtils.unmarshalFromXMLIntoObject(payload, ProviderType.class);
			 	}

			 	case CodeSetType:
			 	{
					return (CodeSetType) JAXBUtils.unmarshalFromXMLIntoObject(payload, CodeSetType.class);
			 	}

			 	case NamespaceType:
			 	{
					return (NamespaceType) JAXBUtils.unmarshalFromXMLIntoObject(payload, NamespaceType.class);
			 	}

			 	case NamespaceCollectionType:
			 	{
					return (NamespaceCollectionType) JAXBUtils.unmarshalFromXMLIntoObject(payload, NamespaceCollectionType.class);
			 	}

			 	case CodeSetCollectionType:
			 	{
					return (CodeSetCollectionType) JAXBUtils.unmarshalFromXMLIntoObject(payload, CodeSetCollectionType.class);
			 	}

			 	case ZoneCollectionType:
			 	{
					return (ZoneCollectionType) JAXBUtils.unmarshalFromXMLIntoObject(payload, ZoneCollectionType.class);
			 	}

			 	case XqueryCollectionType:
			 	{
					return (XqueryCollectionType) JAXBUtils.unmarshalFromXMLIntoObject(payload, XqueryCollectionType.class);
			 	}

			 	case XqueryType:
			 	{
					return (XqueryType) JAXBUtils.unmarshalFromXMLIntoObject(payload, XqueryType.class);
			 	}

			 	case QueueType:
			 	{
					return (QueueType) JAXBUtils.unmarshalFromXMLIntoObject(payload, QueueType.class);
			 	}

			 	case DeleteRequestType:
			 	{
					return (DeleteRequestType) JAXBUtils.unmarshalFromXMLIntoObject(payload, DeleteRequestType.class);
			 	}

			 	case AlertType:
			 	{
					return (AlertType) JAXBUtils.unmarshalFromXMLIntoObject(payload, AlertType.class);
			 	}

			 	case ProvisionRequestType:
			 	{
					return (ProvisionRequestType) JAXBUtils.unmarshalFromXMLIntoObject(payload, ProvisionRequestType.class);
			 	}

			 	case ErrorType:
			 	{
					return (ErrorType) JAXBUtils.unmarshalFromXMLIntoObject(payload, ErrorType.class);
			 	}

			 	case SubscriptionCollectionType:
			 	{
					return (SubscriptionCollectionType) JAXBUtils.unmarshalFromXMLIntoObject(payload, SubscriptionCollectionType.class);
			 	}
			 }
		}
		
		// If we get here then we could not unmarshal because the object type is invalid or null.
		return null;
	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.UnmarshalFactory#unmarshalFromJSON(java.lang.String, java.lang.Class)
	 */
	@Override
	public Object unmarshalFromJSON(String payload, Class<?> clazz) throws UnmarshalException
	{
    InfraModel infraModel = InfraObjectEnum.getInfraModelEnum(clazz.getSimpleName());
    if (infraModel != null)
    {
      // TODO: JH - Implement from JSON unmarshaller
    }
    logger.warn("Unmarshal from JSON not supported, yet");
    throw new UnmarshalException("Unmarshal Object from JSON not implemented, yet");
	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.UnmarshalFactory#unmarschal(java.lang.String, java.lang.Class, javax.ws.rs.core.MediaType)
	 */
	@Override
	public Object unmarschal(String payload, Class<?> clazz, MediaType mediaType) throws UnmarshalException
	{
		if (mediaType != null)
		{
			if (mediaType.equals(MediaType.APPLICATION_XML_TYPE))
			{
				return unmarshalFromXML(payload, clazz);
			}
			else if (mediaType.equals(MediaType.APPLICATION_JSON_TYPE))
			{
				return unmarshalFromJSON(payload, clazz);
			} 
		}
		
		// If we get here then we deal with an unknown media type
		throw new UnmarshalException("Unsupported media type: "+mediaType+". Cannot unmarshal the given input from this media type.");
	}
}
