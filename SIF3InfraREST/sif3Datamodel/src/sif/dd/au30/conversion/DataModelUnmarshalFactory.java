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

import sif.dd.au30.conversion.DataModelObjectEnum.DataModel;
import sif.dd.au30.model.StudentCollectionType;
import sif.dd.au30.model.StudentPersonalType;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.UnmarshalException;
import sif3.common.utils.JAXBUtils;

// TODO: JH - Need to complete this factory
public class DataModelUnmarshalFactory implements UnmarshalFactory
{
	protected final Logger logger = Logger.getLogger(getClass());

	@Override
	public Object unmarshalFromXML(String payload, Class<?> clazz) throws UnmarshalException
	{
		DataModel dataModel = DataModelObjectEnum.getDataModelEnum(clazz.getSimpleName());
		if (dataModel != null)
		{
			switch (dataModel)
			{
			case StudentCollectionType:
			{
				return (StudentCollectionType) JAXBUtils.unmarshalFromXMLIntoObject(payload, StudentCollectionType.class);
			}
			case StudentPersonalType:
			{
				return (StudentPersonalType) JAXBUtils.unmarshalFromXMLIntoObject(payload, StudentPersonalType.class);
			}
			}
		}

		// If we get here then we could not unmarshal because the object type is invalid or null.
		return null;
	}

	@Override
	public Object unmarshalFromJSON(String payload, Class<?> clazz) throws UnmarshalException
	{
		DataModel dataModel = DataModelObjectEnum.getDataModelEnum(clazz.getSimpleName());
		if (dataModel != null)
		{
			// TODO: JH - Implement from JSON unmarshaller
		}
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
			else if (mediaType.equals(MediaType.APPLICATION_JSON_TYPE))
			{
				return unmarshalFromJSON(payload, clazz);
			}
		}

		// If we get here then we deal with an unknown media type
		throw new UnmarshalException("Unsupported media type: " + mediaType + ". Cannot unmarshal the given input from this media type.");
	}

}
