/*
 * EnumToJSON.java
 * Created: 12 Jun 2020
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
import java.lang.reflect.Method;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * JAXB Enums cater for the fact that the enum string and its actual value are different (e.g. PROVISION_REQUESTS("provisionRequests")).
 * So the actual output in the XML/JSON should not be the enum.name() (=PROVISION_REQUESTS) rather the enum.value() (=provisionRequests).
 * The default implementation of fasterxml uses the enum.name() method for serialisation. JAXB however has an additional
 * method for its enums called 'value()' which can be accessed via enum.value(). This enum.value() will return the correct
 * representation (=provisionRequests) of the enum. This class is used to overwrite the default fasterxml behaviour and check
 * if the enum indeed has a 'value()' method and if so it will use it. If it doesn't have such a method like none-JAXB enums
 * it will simply use the name() method.
 * 
 * @author Joerg Huber
 *
 */
public class EnumToJSON extends JsonSerializer<Enum> 
{
    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(Enum value, JsonGenerator jgen, SerializerProvider serializers) throws IOException
    {
       jgen.writeString((value == null)  ? null : getValue(value));
    }
    
    @Override
    public Class<Enum> handledType() 
    {
        return Enum.class;
    }
    
    private String getValue(Enum enumValue)
    {
        // JAXB Enums have a method called value(). This is what we really need. If it hasn't then we simply try to call the name()
        // method to ensure it all works with standard enums as well.
        try
        {
            Method valueMethod  = enumValue.getClass().getMethod("value");
            return (String)valueMethod.invoke(enumValue);
        }
        catch (Exception ex) // there was no "value" field so we simple return the name();
        {
            return enumValue.name();
        }
    }
}
