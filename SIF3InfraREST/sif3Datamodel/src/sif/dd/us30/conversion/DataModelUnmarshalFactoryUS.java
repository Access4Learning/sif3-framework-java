/*
 * DataModelUnmarshalFactoryUS.java
 * Created: 11/10/2013
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

package sif.dd.us30.conversion;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import sif.dd.us30.conversion.DataModelObjectEnumUS.DataModel;
import sif.dd.us30.model.Student;
import sif.dd.us30.model.Students;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.UnmarshalException;
import sif3.common.utils.JAXBUtils;
import au.com.systemic.framework.utils.Timer;

/**
 * @author Joerg Huber
 *
 */
public class DataModelUnmarshalFactoryUS  implements UnmarshalFactory
{
	protected final Logger logger = Logger.getLogger(getClass());

	/* (non-Javadoc)
     * @see sif3.common.conversion.UnmarshalFactory#unmarshalFromXML(java.lang.String, java.lang.Class)
     */
    @Override
    public Object unmarshalFromXML(String payload, Class<?> clazz) throws UnmarshalException
    {
		Timer timer = new Timer();
		timer.start();
		try
		{
			DataModel dataModel = DataModelObjectEnumUS.getDataModelEnum(clazz.getSimpleName());
			if (dataModel != null)
			{
				switch (dataModel)
				{
				case Students:
				{
					logger.debug("Response as String: "+payload);
					return (Students) JAXBUtils.unmarshalFromXMLIntoObject(payload, Students.class);
				}
				case Student:
				{
					return (Student) JAXBUtils.unmarshalFromXMLIntoObject(payload, Student.class);
				}
				}
			}
	
			// If we get here then we could not unmarshal because the object type is invalid or null.
			return null;
		}
		finally
		{
			timer.finish();
			if (logger.isDebugEnabled())
			{
				logger.debug("Time taken to unmarshal "+clazz.getSimpleName()+" from XML: "+timer.timeTaken()+"ms");
			}
		}
    }

	/* (non-Javadoc)
     * @see sif3.common.conversion.UnmarshalFactory#unmarshalFromJSON(java.lang.String, java.lang.Class)
     */
    @Override
    public Object unmarshalFromJSON(String payload, Class<?> clazz) throws UnmarshalException
    {
		Timer timer = new Timer();
		timer.start();
		try
		{
			DataModel dataModel = DataModelObjectEnumUS.getDataModelEnum(clazz.getSimpleName());
			if (dataModel != null)
			{
				// TODO: JH - Implement from JSON unmarshaller
			}
			logger.warn("Unmarshal from JSON not supported, yet");
			throw new UnmarshalException("Unmarshal Object from JSON not implemented, yet");
		}
		finally
		{
			timer.finish();
			if (logger.isDebugEnabled())
			{
				logger.debug("Time taken to unmarshal "+clazz.getSimpleName()+" from JSON: "+timer.timeTaken()+"ms");
			}
		}
    }

	/* (non-Javadoc)
     * @see sif3.common.conversion.UnmarshalFactory#unmarschal(java.lang.String, java.lang.Class, javax.ws.rs.core.MediaType)
     */
    @Override
    public Object unmarschal(String payload, Class<?> clazz, MediaType mediaType)
            throws UnmarshalException
    {
		if (mediaType != null)
		{
			if (MediaType.APPLICATION_XML_TYPE.isCompatible(mediaType) || 
				MediaType.TEXT_XML_TYPE.isCompatible(mediaType)  || 
				MediaType.TEXT_PLAIN_TYPE.isCompatible(mediaType))
			{
				return unmarshalFromXML(payload, clazz);
			}
			else if (MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType))
			{
				return unmarshalFromJSON(payload, clazz);
			}
		}

		// If we get here then we deal with an unknown media type
		throw new UnmarshalException("Unsupported media type: " + mediaType + ". Cannot unmarshal the given input from this media type.");
    }

}
