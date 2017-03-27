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
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlType;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.stream.StreamSource;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;
import org.codehaus.jettison.mapped.SimpleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.Timer;
import sif3.common.exception.MarshalException;
import sif3.common.exception.UnmarshalException;

/**
 * @author Joerg Huber
 *
 */
public class JAXBUtils
{	
	protected final static Logger logger = LoggerFactory.getLogger(JAXBUtils.class);

	/* Make JAXBContext a singleton. Otherwise creating the JAXBContext every time is very slow! */
	private static HashMap<String, JAXBContext> jaxbCtx = new HashMap<String, JAXBContext>();

	private static String JSON_VALUE_KEY = "#text";
	private static String JSON_ATTRIBUTE_KEY = "@";

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
	 * Unmarshal a JSON object represented as a string into a Model object
	 * 
	 * @param jsonStr The JSON String to unmarshal
	 * @param clazz Indicates the class of the object to un-marshal into.
	 * 
	 * @return See desc.
	 * 
	 * @throws UnmarshalException Failed to unmarshal the string. See message of exception for more details.
	 */
	public static <T> T unmarshalFromJSONIntoObject(String jsonStr, Class<T> clazz) throws UnmarshalException
	{
		Timer timer = null;
		if (logger.isDebugEnabled())
		{
			timer = new Timer();
			timer.start();
		}
		JAXBElement<T> elem = unmarshalFromJSON(jsonStr, clazz);
		if (logger.isDebugEnabled())
		{
			timer.finish();
			logger.debug("Time taken to unmarshal " + clazz.getSimpleName() + " from JSON: " + timer.timeTaken() + "ms");
		}

		return (elem != null) ? elem.getValue() : null;
	}

	/**
	 * Returns the string version of the JSON presentation of the given JAXBElement.
	 * 
	 * @param object The object to be marshaled to a String.
	 * 
	 * @return See desc.
	 * 
	 * @throws MarshalException Failure to marshal the given object to a JSON string.
	 */
	public static String marshalToJSON(JAXBElement<?> object) throws MarshalException
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
	        Class<?> clazz = object.getValue().getClass();
			Marshaller marshaller = getContext(clazz).createMarshaller();

			synchronized (marshaller)
			{
				marshaller.marshal(object, getJSONStreamWriter(clazz, sw));
			}
		}
		catch (JAXBException ex)
		{
			throw new MarshalException("Failed to marshal to JSON: " + ex.getMessage(), ex);
		}

		if (logger.isDebugEnabled())
		{
			timer.finish();
			logger.debug("Time taken to marshal " + object.getValue().getClass().getSimpleName()+ " to JSON: " + timer.timeTaken() + "ms");
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
				elem = unmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr)), clazz);
			}

		}
		catch (JAXBException ex)
		{
			throw new UnmarshalException("Failed to unmarshal the given XML string into a JAXB Object of type "+clazz.getSimpleName(), ex);
		}

		return elem;
	}

	private static <T> JAXBElement<T> unmarshalFromJSON(String jsonStr, Class<T> clazz) throws UnmarshalException
	{
		JAXBElement<T> elem = null;
		try
		{
			Unmarshaller unmarshaller = getContext(clazz).createUnmarshaller();
			synchronized (unmarshaller)
			{
				elem = unmarshaller.unmarshal(getJSONStreamReader(jsonStr, clazz), clazz);
			}
		}
		catch (Exception ex)
		{
			throw new UnmarshalException("Failed to unmarshal the given JSON string into a JAXB Object of type " + clazz.getSimpleName(), ex);
		}

		return elem;
	}

	private static XMLStreamWriter getJSONStreamWriter(Class<?> clazz, Writer writer)
	{
		Configuration jsonConfiguration = getJSONConfiguration(clazz);
		MappedNamespaceConvention jsonConvention = new MappedNamespaceConvention(jsonConfiguration);
		MappedXMLStreamWriter result = new MappedXMLStreamWriter(jsonConvention, writer);
		result.setValueKey(JSON_VALUE_KEY);
		return result;
	}

	private static XMLStreamReader getJSONStreamReader(String jsonStr, Class<?> clazz) throws JSONException, XMLStreamException
	{
		Configuration jsonConfiguration = getJSONConfiguration(clazz);
		MappedNamespaceConvention jsonConvention = new MappedNamespaceConvention(jsonConfiguration);
		JSONObject jsonObject = new JSONObject(jsonStr);
		MappedXMLStreamReader result = new MappedXMLStreamReader(jsonObject, jsonConvention);
		result.setValueKey(JSON_VALUE_KEY);
		return result;
	}

	private static Configuration getJSONConfiguration(Class<?> clazz)
	{
		Configuration result = new Configuration();
		Map<String, String> namespaceMapping = new HashMap<String, String>();
		String namespace = getObjectNamespace(clazz);
		if (namespace != null)
		{
			namespaceMapping.put(namespace, ""); // Default namespace
		}
		namespaceMapping.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
		result.setXmlToJsonNamespaces(namespaceMapping);
		result.setAttributeKey(JSON_ATTRIBUTE_KEY);
		result.setTypeConverter(new SimpleConverter());
		return result;
	}

	private static String getObjectNamespace(Class<?> clazz)
	{
		String result = null;
		XmlType typeAnnotation = clazz.getAnnotation(XmlType.class);
		if (typeAnnotation != null)
		{
			result = typeAnnotation.namespace();
		}
		return result;
	}

    private synchronized static JAXBContext getContext(Class<?> clazz) throws JAXBException
    {
//    	JAXBContext ctx = jaxbCtx.get(clazz.getSimpleName());
        String className = clazz.getName();
        JAXBContext ctx = jaxbCtx.get(className);
		if (ctx == null)
		{
			logger.debug("No context for "+className+" exists yet. Create and add it to context map.");
			ctx = JAXBContext.newInstance(clazz);
			jaxbCtx.put(className, ctx);
		}
		
		return ctx;
    }
}
