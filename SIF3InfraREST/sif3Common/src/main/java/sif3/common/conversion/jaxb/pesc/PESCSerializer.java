/*
 * PESCSerializer.java
 * Created: 2 Jun 2020
 *
 * Copyright 2020 Systemic Pty Ltd
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

package sif3.common.conversion.jaxb.pesc;

import java.io.Serializable;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;

import au.com.systemic.framework.utils.Timer;
import sif3.common.conversion.jackson.serializer.EnumToJSON;
import sif3.common.conversion.jackson.serializer.GregorianCalendarToJSON;
import sif3.common.conversion.jackson.serializer.PESCNamingStrategy;
import sif3.common.conversion.jackson.serializer.StringToJSON;
import sif3.common.exception.MarshalException;

/**
 * This class provides configuration for a PESC serialiser. It adheres to the specification as outlined in PESC JSON specification
 * for producing JSON.
 * 
 * @author Joerg Huber
 *
 */
public class PESCSerializer implements Serializable
{
    protected final static Logger logger = LoggerFactory.getLogger(PESCSerializer.class);
    private static final long serialVersionUID = 3919042726570296711L;
    
    // We only need to create this once.
    private static PESCNamingStrategy pescNamingStrategy = new PESCNamingStrategy();
    
    private ObjectMapper pescMapper = getPESCMarshalObjectMapper();

    /*
     * This ensures that JAXBElement marshalling doesn't produce all other properties of the JAXBElement. Only to be
     * used with marshalling.
     */
    public static interface JAXBElementToJSON 
    {
        @JsonValue
        Object getValue();
    }
    
    public PESCSerializer()
    {
        super();
        setPESCMapper(getPESCMarshalObjectMapper());
    }

    /**
     * Marshals a JAXBObject into a JSON string according to the PESC JSON specification. The result is stored as part of the returned
     * StringWriter/ If the jaxbObject is null then null is returned. If there are any other failure in marshalling then a 
     * MarshalException is thrown.
     * 
     * @param jaxbObject The object to be marshaled to a String.
     * 
     * @return See desc.
     * 
     * @throws MarshalException Failure to marshal the given object to a JSON string.
     */
    public StringWriter marshalToJSON(JAXBElement<?> jaxbObject) throws MarshalException
    {
        if (jaxbObject == null)
        {
            return null;
        }
        
        StringWriter jsonStr = new StringWriter();
        marshalToJSON(jaxbObject, jsonStr);

        return jsonStr;
    }

    /**
     * Same as above method but the string writer is already initialised by caller.
     *  
     * @param jaxbObject The object to be marshaled to a String.
     * @param sw String writer where the result of the marshalling is written to.
     * 
     * 
     * @throws MarshalException Failure to marshal the given object to a JSON string.
     */
    public void marshalToJSON(JAXBElement<?> jaxbObject, StringWriter sw) throws MarshalException
    {
        Timer timer = new Timer();
        timer.start();

        ObjectMapper mapper = getPESCMapper();
        
        // Note: To be JSON compliant with PESC we must wrap the final JSON with the object name (e.g. StudentPersonal)
        //       Using JAXB the value of that object name is in the outermost JAXBElement under the property 
        //       jaxbElement.getName().getLocalPart(). This name also helps to determine if the object to marshal uses
        //       upper-case or lower-case element names.
        String objectName = jaxbObject.getName().getLocalPart();
        mapper.setPropertyNamingStrategy(Character.isUpperCase(objectName.charAt(0)) ? pescNamingStrategy : PropertyNamingStrategy.LOWER_CAMEL_CASE);
        if (logger.isDebugEnabled())
        {
            logger.debug("Object Name: " + objectName+" => Use "+mapper.getPropertyNamingStrategy().toString());
        }

        try
        {
            // Add the wrapper
            sw.write("{\"" + objectName + "\":");
            sw.append(mapper.writeValueAsString(jaxbObject));
            sw.append("}");
            if (logger.isDebugEnabled())
            {
                logger.debug("PESC-JSON for " + jaxbObject.getClass().getSimpleName() + ":\n" + sw.toString());
            }
        }
        catch (Exception ex)
        {
            logger.error("Failed to convert the given object (" + jaxbObject.getClass().getSimpleName() + ") to PESC-JSON: " + ex.getMessage(), ex);
            throw new MarshalException("Failed to convert the given object (" + jaxbObject.getClass().getSimpleName() + ") to PESC-JSON: " + ex.getMessage(), ex);
        }
        timer.finish();
        logger.debug("Time taken to marshal " + jaxbObject.getClass().getSimpleName() + " to JSON: " + timer.timeTaken() + "ms");
    }

    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    private ObjectMapper getPESCMapper()
    {
        return this.pescMapper;
    }

    private void setPESCMapper(ObjectMapper pescMapper)
    {
        this.pescMapper = pescMapper;
    }

    /*
     * This is only to be used for marshalling an object to JSON according to PESC.
     */
    public ObjectMapper getPESCMarshalObjectMapper()
    {        
        //
        // This section sets up properties and config for marshaling JAXB object to JSON
        //
        ObjectMapper objectMapper = new ObjectMapper();
        
        /* com.fasterxml.jackson module usage and properties*/
//        objectMapper.enable(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT); // Pretty print - will not be set in final code but for now it is better readable
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY); // will avoid elements with null value be part of output.
    
        
        // This is an interesting one. It is a way to deal with JAXBElement wrapped values (most of them!).
        objectMapper.addMixIn(JAXBElement.class, JAXBElementToJSON.class);

        // This is to enforce that empty string becomes "" as per PESC. Jackson default behaviour is to set it to null.
        SimpleModule s = new SimpleModule();
        s.addSerializer(String.class, new StringToJSON());
        objectMapper.registerModule(s);
    
        // This converts the dates to a string required. Deals correctly where a date is made of a year only.
        s = new SimpleModule();
        s.addSerializer(XMLGregorianCalendar.class, new GregorianCalendarToJSON());
        objectMapper.registerModule(s);
        
        // Deals with JAXB Enums classes where additional info is available
        s = new SimpleModule();
        s.addSerializer(Enum.class, new EnumToJSON());
        objectMapper.registerModule(s);

        // Config on how to map Date/Calendar
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
    
        return objectMapper;
    }
}
