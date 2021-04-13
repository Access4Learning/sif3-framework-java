/*
 * StringToJSON.java
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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * This is a serialiser class required by fasterxml.jackson to convert a String object to a string value for JSON serialisation. 
 * While fasterxml.jackson does have a string serialiser built in but its default behaviour is to represent and empty string as null. 
 * PESC however requires an empty string to be serialised into "". This class overrides the default behaviour to follow the PESC 
 * definition. 
 * 
 * @author Joerg Huber
 *
 */
public class StringToJSON  extends JsonSerializer<String> 
{
    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider serializers) throws IOException
    {
        jgen.writeString(((value != null) && (value.length() == 0)) ? "" : value);
    }
    
    @Override
    public Class<String> handledType() 
    {
        return String.class;
    }
}
