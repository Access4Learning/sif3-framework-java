/*
 * GregorianCalendarToJSON.java
 * Created: 16 Mar 2020
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

package sif3.common.conversion.jackson.serializer;

import java.io.IOException;

import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * This class provides the fasterxml.jackson custom serialiser for the XMLGregorianCalendar class.
 * 
 * @author Joerg Huber
 *
 */
public class GregorianCalendarToJSON extends JsonSerializer<XMLGregorianCalendar> 
{
    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(XMLGregorianCalendar value, JsonGenerator jgen, SerializerProvider serializers) throws IOException
    {
        if (value != null)
        {
            jgen.writeString(value.toXMLFormat());
        }
    }
    
    @Override
    public Class<XMLGregorianCalendar> handledType() 
    {
        return XMLGregorianCalendar.class;
    }
}