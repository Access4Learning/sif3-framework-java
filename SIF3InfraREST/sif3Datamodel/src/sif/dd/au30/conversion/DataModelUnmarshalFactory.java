/*
 * DataModelUnmarshalFactory.java Created: 23/09/2013
 * 
 * Copyright 2013 Systemic Pty Ltd
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
package sif.dd.au30.conversion;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.UnmarshalException;
import sif3.common.utils.JAXBUtils;

public class DataModelUnmarshalFactory implements UnmarshalFactory
{
	protected final Logger logger = Logger.getLogger(getClass());

	@Override
	public Object unmarshalFromXML(String payload, Class<?> clazz) throws UnmarshalException
	{
		Object result = null;
		try
		{
			result = JAXBUtils.unmarshalFromXMLIntoObject(payload, clazz);
		}
		catch (Exception e)
		{
			logger.error("An error occurred unmarshalling object from XML", e);
		}
		return result;
	}

	@Override
	public Object unmarshalFromJSON(String payload, Class<?> clazz) throws UnmarshalException
	{
		// TODO: JH - Implement from JSON unmarshaller
		logger.warn("Unmarshal from JSON not supported, yet");
		throw new UnmarshalException("Unmarshal Object from JSON not implemented, yet");
	}

	@Override
	public Object unmarschal(String payload, Class<?> clazz, MediaType mediaType) throws UnmarshalException
	{
		if (mediaType != null)
		{
			if (mediaType.equals(MediaType.APPLICATION_XML_TYPE))
			{
				return unmarshalFromXML(payload, clazz);
			}
			else if (mediaType.equals(MediaType.TEXT_XML_TYPE)) //assume XML
			{
				return unmarshalFromXML(payload, clazz);
			}
			else if (mediaType.equals(MediaType.APPLICATION_JSON_TYPE))
			{
				return unmarshalFromJSON(payload, clazz);
			}
			else if (mediaType.equals(MediaType.TEXT_PLAIN_TYPE)) // assume XML
			{
				return unmarshalFromXML(payload, clazz);
			}
			else //TODO: JH - Temporary bit to resolve issue with Broker's Event Media Type. Remove this later
			{
				return unmarshalFromXML(payload, clazz);
			}
		}

		// If we get here then we deal with an unknown media type
		throw new UnmarshalException("Unsupported media type: " + mediaType + ". Cannot unmarshal the given input from this media type.");
	}

}
