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
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.utils.JAXBUtils;

/**
 * Implementation of an unmarshal Factory for all Infrastructure Model Objects. JAXB has been used to create Infrastructure POJOs and 
 * this Factory uses JAXB's methods to unmarshal POJOs from XML. At this point in time (May 2014) JSON is not yet supported.
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
	public Object unmarshalFromXML(String payload, Class<?> clazz) throws UnmarshalException, UnsupportedMediaTypeExcpetion
	{
	  Object result = null;
    try
    {
      result = JAXBUtils.unmarshalFromXMLIntoObject(payload, clazz);
    }
    catch (Exception e)
    {
      logger.error("An error occurred unmarshalling object from XML", e);
      throw new UnmarshalException("An error occurred unmarshalling object from XML", e);
    }
    return result;	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.UnmarshalFactory#unmarshalFromJSON(java.lang.String, java.lang.Class)
	 */
	@Override
	public Object unmarshalFromJSON(String payload, Class<?> clazz) throws UnmarshalException, UnsupportedMediaTypeExcpetion
	{
    Object result = null;
    try
    {
      result = JAXBUtils.unmarshalFromJSONIntoObject(payload, clazz);
    }
    catch (Exception e)
    {
      logger.error("An error occurred unmarshalling object from XML", e);
      throw new UnmarshalException("An error occurred unmarshalling object from XML", e);
    }
    return result;
	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.UnmarshalFactory#unmarschal(java.lang.String, java.lang.Class, javax.ws.rs.core.MediaType)
	 */
	@Override
	public Object unmarshal(String payload, Class<?> clazz, MediaType mediaType) throws UnmarshalException, UnsupportedMediaTypeExcpetion
	{
		if (mediaType != null)
		{
			if (MediaType.APPLICATION_XML_TYPE.isCompatible(mediaType) || MediaType.TEXT_XML_TYPE.isCompatible(mediaType)  ||	MediaType.TEXT_PLAIN_TYPE.isCompatible(mediaType))
			{
				return unmarshalFromXML(payload, clazz);
			}
			else if (MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType))
			{
				return unmarshalFromJSON(payload, clazz);
			}
		}

		// If we get here then we deal with an unknown media type
		throw new UnsupportedMediaTypeExcpetion("Unsupported media type: " + mediaType + ". Cannot unmarshal the given input from this media type.");
	}
}
