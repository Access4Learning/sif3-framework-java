/*
 * InfraObjectEnum.java
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

import org.apache.log4j.Logger;

/**
 * Simple enumeration of all valid Infrastructure POJOs that have been generated through JAXB and the Infrastructure XSD. On top of this
 * a hand full of convenience methods are provided to convert an object by its name or a string to an appropriate enumeration value
 * of this class.
 * 
 * @author Joerg Huber
 *
 */
public class InfraObjectEnum
{
	protected static final Logger logger = Logger.getLogger(InfraObjectEnum.class);

	public enum InfraModel
	{
		QueueCollectionType,
		CreateResponseType,
		ZoneType,
		EnvironmentType,
		DeleteResponseType,
		UpdateResponseType,
		AlertCollectionType,
		SubscriptionType,
		ProviderCollectionType,
		ProviderType,
		CodeSetType,
		NamespaceType,
		NamespaceCollectionType,
		CodeSetCollectionType,
		ZoneCollectionType,
		XqueryCollectionType,
		XqueryType,
		QueueType,
		DeleteRequestType,
		AlertType,
		ProvisionRequestType,
		ErrorType,
		SubscriptionCollectionType,
	};
	
	/**
	 * Returns the infrastructure enumeration for a given object by means of determining the class name. This is a convenience method that
	 * deals with error handling instead of having these check all over the higher level code of the framework.
	 * 
	 * @param obj The object for which the Infrastructure Class shall be returned.
	 * 
	 * @return See desc.
	 */
	public static InfraModel getInfraModelEnum(Object obj)
	{
		if (obj != null)
		{
			return getInfraModelEnum(obj.getClass().getSimpleName());
		}
		return null;
	}
	
	/**
   * Returns the infrastructure enumeration for a given name that should correspond to a name of an Infrastructure POJO as generated
   * by JAXB and the XSD. If the input string does not correspond to any infrastructure POJO name then null is returned. This is a 
   * convenience method that deals with error handling instead of having these check all over the higher level code of the framework.
   * 
	 * @param infraModelStr The name of the POJO class as a string
	 * 
	 * @return See desc
	 */
	public static InfraModel getInfraModelEnum(String infraModelStr)
	{
		try
		{
			return InfraModel.valueOf(infraModelStr);
		}
		catch (Exception ex)
		{
			logger.error("Try to convert '"+infraModelStr+"' into a InfraObjectEnum.InfraModel enumeration failed: "+ex.getMessage());
			return null;
		}
	}
	
}
