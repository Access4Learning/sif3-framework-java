/*
 * InfraMarshalFactory.java
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

import sif3.common.conversion.MarshalFactory;
import sif3.common.exception.MarshalException;
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
import sif3.infra.common.model.ObjectFactory;
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
 * Implementation of a Marshal Factory for all Infrastructure Model Objects. JAXB has been used to create Infrastructure POJOs and this
 * Factory uses JAXB's methods to marshal POJOs to XML. At this point in time (October 2013) JSON is not yet supported.
 * 
 * @author Joerg Huber
 *
 */
public class InfraMarshalFactory implements MarshalFactory
{
	protected final Logger logger = Logger.getLogger(getClass());
	
	private ObjectFactory objFactory = new ObjectFactory();
	
	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.MarshalFactory#marshalToXML(java.lang.Object)
	 */
	@Override
	public String marshalToXML(Object obj) throws MarshalException
	{
		InfraModel infraModel = InfraObjectEnum.getInfraModelEnum(obj);
		if (infraModel != null)
		{
			 switch (infraModel)
			 {
			 	case QueueCollectionType:
			 	{ 		
			 		return JAXBUtils.marshalToXML(objFactory.createQueues((QueueCollectionType)obj));
			 	}
			 	case CreateResponseType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createCreateResponse((CreateResponseType)obj));
			 	}

			 	case ZoneType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createZone((ZoneType)obj));
			 	}

			 	case EnvironmentType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createEnvironment((EnvironmentType)obj));
			 	}

			 	case DeleteResponseType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createDeleteResponse((DeleteResponseType)obj));
			 	}

			 	case UpdateResponseType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createUpdateResponse((UpdateResponseType)obj));
			 	}

			 	case AlertCollectionType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createAlerts((AlertCollectionType)obj));
			 	}

			 	case SubscriptionType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createSubscription((SubscriptionType)obj));
			 	}

			 	case ProviderCollectionType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createProviders((ProviderCollectionType)obj));
			 	}

			 	case ProviderType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createProvider((ProviderType)obj));
			 	}

			 	case CodeSetType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createCodeSet((CodeSetType)obj));
			 	}

			 	case NamespaceType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createNamespace((NamespaceType)obj));
			 	}

			 	case NamespaceCollectionType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createNamespaces((NamespaceCollectionType)obj));
			 	}

			 	case CodeSetCollectionType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createCodeSets((CodeSetCollectionType)obj));
			 	}

			 	case ZoneCollectionType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createZones((ZoneCollectionType)obj));
			 	}

			 	case XqueryCollectionType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createXquerys((XqueryCollectionType)obj));
			 	}

			 	case XqueryType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createXquery((XqueryType)obj));
			 	}

			 	case QueueType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createQueue((QueueType)obj));
			 	}

			 	case DeleteRequestType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createDeleteRequest((DeleteRequestType)obj));
			 	}

			 	case AlertType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createAlert((AlertType)obj));
			 	}

			 	case ProvisionRequestType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createProvisionRequest((ProvisionRequestType)obj));
			 	}

			 	case ErrorType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createError((ErrorType)obj));
			 	}

			 	case SubscriptionCollectionType:
			 	{
			 		return JAXBUtils.marshalToXML(objFactory.createSubscriptions((SubscriptionCollectionType)obj));
			 	}
			 }
		}
		
		// If we get here then we could not marshal because the object type is invalid or null.
		return null;
	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.MarshalFactory#marshalToJSON(java.lang.Object)
	 */
	@Override
	public String marshalToJSON(Object obj) throws MarshalException
	{
    InfraModel infraModel = InfraObjectEnum.getInfraModelEnum(obj);
    if (infraModel != null)
    {
      // TODO: JH - Implement from JSON marshaller
    }
		logger.warn("Marshal to JSON not supported, yet");
    throw new MarshalException("Marshal Object to JSON not implemented, yet");
	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.MarshalFactory#marschal(java.lang.Object, javax.ws.rs.core.MediaType)
	 */
	@Override
	public String marschal(Object obj, MediaType mediaType) throws MarshalException
	{
		if (mediaType != null)
		{
			if (mediaType.equals(MediaType.APPLICATION_XML_TYPE))
			{
				return marshalToXML(obj);
			}
			else if (mediaType.equals(MediaType.APPLICATION_JSON_TYPE))
			{
				return marshalToJSON(obj);
			} 
		}
		
		// If we get here then we deal with an unknown media type
		throw new MarshalException("Unsupported media type: "+mediaType+". Cannot marshal the given input to this media type.");
	}
}
