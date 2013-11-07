/*
 * DataModelMarshalFactoryUS.java
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
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import sif.dd.us30.conversion.DataModelObjectEnumUS.DataModel;
import sif.dd.us30.model.Student;
import sif.dd.us30.model.Students;
import sif3.common.conversion.MarshalFactory;
import sif3.common.exception.MarshalException;
import sif3.common.utils.JAXBUtils;
import au.com.systemic.framework.utils.Timer;

/**
 * @author Joerg Huber
 *
 */
public class DataModelMarshalFactoryUS implements MarshalFactory
{
	protected final Logger logger = Logger.getLogger(getClass());

//	private ObjectFactory objFactory = new ObjectFactory();

	@Override
    public String marshalToXML(Object obj) throws MarshalException
    {
		Timer timer = new Timer();
		timer.start();
		try
		{
			DataModel dataModel = DataModelObjectEnumUS.getDataModelEnum(obj);
			if (dataModel != null)
			{
				switch (dataModel)
				{
				case Students:
				{
					JAXBElement<Students> students = new JAXBElement<Students>(new QName("", "students"), Students.class, null, (Students)obj);
					return JAXBUtils.marshalToXML(students);
				}
				case Student:
				{
					JAXBElement<Student> student = new JAXBElement<Student>(new QName("", "student"), Student.class, null, (Student)obj);
					return JAXBUtils.marshalToXML(student);
				}
				}
			}
	
			// If we get here then we could not marshal because the object type is invalid or null.
			return null;
		}
		finally
		{
			timer.finish();
			if (logger.isDebugEnabled())
			{
				logger.debug("Time taken to marshal "+obj.getClass().getSimpleName()+" to XML: "+timer.timeTaken()+"ms");
			}
		}
    }

	@Override
    public String marshalToJSON(Object obj) throws MarshalException
    {
		Timer timer = new Timer();
		timer.start();
		try
		{
			DataModel dataModel = DataModelObjectEnumUS.getDataModelEnum(obj);
			if (dataModel != null)
			{
				// TODO: JH - Implement from JSON marshaller
			}
			logger.warn("Marshal to JSON not supported, yet");
			throw new MarshalException("Marshal Object to JSON not implemented, yet");
		}
		finally
		{
			timer.finish();
			if (logger.isDebugEnabled())
			{
				logger.debug("Time taken to marshal "+obj.getClass().getSimpleName()+" to JSON: "+timer.timeTaken()+"ms");
			}
		}
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
