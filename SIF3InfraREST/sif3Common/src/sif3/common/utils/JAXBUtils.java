/*
 * JAXBUtils.java
 * Created: 27/08/2013
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

package sif3.common.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import sif3.common.exception.MarshalException;
import sif3.common.exception.UnmarshalException;
import au.com.systemic.framework.utils.Timer;

/**
 * @author Joerg Huber
 *
 */
public class JAXBUtils
{	
	protected static final Logger logger = Logger.getLogger(JAXBUtils.class);

	/* Make JAXBContext a singleton. Otherwise creating the JAXBContext every time is very slow! */
	private static HashMap<String, JAXBContext> jaxbCtx = new HashMap<String, JAXBContext>();
	
    /**
	 * This method unmarshals the given XML String into an object of the class indicated with the 'clazz' parameter.
	 *  
	 * @param xmlStr The XML String to un-marshal.
	 * @param clazz Indicates the class of the object to un-marshal into.
	 * 
	 * @return If no exception is thrown then the  object of type 'clazz'  is returned.
	 * 
	 * @throws UnmarshalException Failed to unmarshal the string. See message of exception for more details.
     */
	public static Object unmarshalFromXMLIntoObject(String xmlStr, Class<?> clazz) throws UnmarshalException
	{
		Timer timer = null;	
		if (logger.isDebugEnabled())
		{
			timer = new Timer();
			timer.start();
		}
		JAXBElement<?> elem = unmarshalFromXML(xmlStr, clazz);
		if (logger.isDebugEnabled())
		{
			timer.finish();
			logger.debug("Time taken to unmarshal "+clazz.getSimpleName()+" from XML: "+timer.timeTaken()+"ms");
		}
		
		return (elem != null) ? elem.getValue() : null;
	}

	/**
	 * Returns the string version of the XML presentation of the given JAXBElement.
	 * 
	 * @param object The object to be marshaled to a String.
	 * 
	 * @return See desc.
	 * 
	 * @throws MarshalException Failure to marshal the given object to a XML string.
	 */
    public static String marshalToXML(JAXBElement<?> object) throws MarshalException
    {
		Timer timer = null;	
		if (logger.isDebugEnabled())
		{
			timer = new Timer();
			timer.start();
		}
		StringWriter sw = new StringWriter();
		try
		{
			Marshaller marshaller = getContext(object.getValue().getClass()).createMarshaller();

			// Don't generate <?xml version="1.0" encoding="UTF-8" standalone="yes"?> in output
			marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);

			// Format output nicely
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// marshaller.marshal(personInfo, sw);
			synchronized(marshaller)
			{
				marshaller.marshal(object, sw);
			}
		}
		catch (JAXBException e)
		{
			throw new MarshalException("Failed to marshal to XML: " + e.getMessage(), e);
		}

		if (logger.isDebugEnabled())
		{
			timer.finish();
			logger.debug("Time taken to marshal "+object.getValue().getClass().getSimpleName()+" to XML: "+timer.timeTaken()+"ms");
		}

		return sw.toString();
    }

    /**
     * This is a convenience method so that JAXB Contexts can be initialised at any time. This proves to be
     * particular useful to avoid lengthy startup times for some classes when they hit the marshaller and
     * unmarshaller methods for the first time.
     * 
     * @param clazz The class for which the JAXBContext shall be configured.
     */
    public static synchronized void initCtx(Class<?> clazz)
    {
    	try
    	{
    		getContext(clazz);
    	}
    	catch (Exception ex)
    	{
    		logger.error("Failed to initialise JAXBContext for "+clazz.getSimpleName()+": "+ex.getLocalizedMessage());
    	}
    }
    
	/*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/	
	
	/*
	 * This method un-marshals the given XML String into a JXABElement object with the content being of the class indicated with the 'clazz' parameter.
	 * To retrieve the actual object of the JAXBElement simply use the getValue() method on the returned JAXBElement.
	 *  
	 * @param xmlStr The XML String to un-marshal.
	 * @param clazz Indicates the class of the object to un-marshal into.
	 * 
	 * @return If no exception is thrown then the JAXBElement with an object of type 'clazz' as its value is returned.
	 * 
	 * @throws UnmarshalException Failed to unmarshal the string. See message of exception for more details.
	 */
    private static JAXBElement<?> unmarshalFromXML(String xmlStr, Class<?> clazz) throws UnmarshalException
	{
		JAXBElement<?> elem = null;
		try
		{
			Unmarshaller unmarshaller = getContext(clazz).createUnmarshaller();
			synchronized(unmarshaller)
			{
				elem = (JAXBElement<?>) unmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr)), clazz);
			}

		}
		catch (JAXBException ex)
		{
			throw new UnmarshalException("Failed to unmarshal the given XML string into a JAXB Object of type "+clazz.getSimpleName(), ex);
		}

		return elem;
	}

    
    private synchronized static JAXBContext getContext(Class<?> clazz) throws JAXBException
    {
    	JAXBContext ctx = jaxbCtx.get(clazz.getSimpleName());
		if (ctx == null)
		{
			logger.debug("No context for "+clazz.getSimpleName()+" exists yet. Create and add it to context map.");
			ctx = JAXBContext.newInstance(clazz);
			jaxbCtx.put(clazz.getSimpleName(), ctx);
		}
		
		return ctx;
    }
}
