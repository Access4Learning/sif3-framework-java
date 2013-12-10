/*
 * DataModelMarshalFactory.java
 * Created: 23/09/2013
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
package sif.dd.au30.conversion;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import sif.dd.au30.conversion.DataModelObjectEnum.DataModel;
import sif.dd.au30.model.ObjectFactory;
import sif.dd.au30.model.StudentCollectionType;
import sif.dd.au30.model.StudentPersonalType;
import sif3.common.conversion.MarshalFactory;
import sif3.common.exception.MarshalException;
import sif3.common.utils.JAXBUtils;

//TODO: JH - Need to complete this factory
public class DataModelMarshalFactory implements MarshalFactory
{
	protected final Logger logger = Logger.getLogger(getClass());

	private ObjectFactory objFactory = new ObjectFactory();
	
	@Override
	public String marshalToXML(Object obj) throws MarshalException
	{
		DataModel dataModel = DataModelObjectEnum.getDataModelEnum(obj);
		if (dataModel != null)
		{
			switch (dataModel)
			{
			case StudentCollectionType:
			{
				return JAXBUtils.marshalToXML(objFactory.createStudentPersonals((StudentCollectionType) obj));
			}
			case StudentPersonalType:
			{
				return JAXBUtils.marshalToXML(objFactory.createStudentPersonal((StudentPersonalType) obj));
			}
			}
		}

		// If we get here then we could not marshal because the object type is invalid or null.
		return null;
	}

	@Override
	public String marshalToJSON(Object obj) throws MarshalException
	{
		DataModel dataModel = DataModelObjectEnum.getDataModelEnum(obj);
		if (dataModel != null)
		{
			// TODO: JH - Implement from JSON marshaller
		}
		logger.warn("Marshal to JSON not supported, yet");
		throw new MarshalException("Marshal Object to JSON not implemented, yet");
	}

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
		throw new MarshalException("Unsupported media type: " + mediaType + ". Cannot marshal the given input to this media type.");
	}
}
