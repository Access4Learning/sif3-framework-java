/*
 * PESCNamingStrategy.java
 * Created: 19 Oct 2020
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

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;

/**
 * This behaviour of this class is almost identical to the standard FasterXML Jackson UpperCamelCaseStrategy with the 
 * exception that the JAXB object property called 'value' must remain lower case regardless of the JAXB object naming 
 * convention according to the PESC mapping rules. This is a little bit an oddity but has been adopted as such in SIF.
 * 
 * @author Joerg Huber
 *
 */
public class PESCNamingStrategy extends PropertyNamingStrategyBase implements Serializable
{
    private static final long serialVersionUID = 792160238827407446L;
    
    /**
     * Converts all property names to Upper Camel Case except the property called 'value' which remains lower case. 
     * 
     * For example, "userName" would be converted to "UserName" while "value' will remain "value".
     *
     * @param input property name.
     * 
     * @return input converted to upper camel case with the exception of the property named "value" which will remain
     *         lower case.
     */
    @Override
    public String translate(String input) 
    {
        if (input == null || input.length() == 0)
        {
            return input; // garbage in, garbage out
        }
        StringBuilder sb = new StringBuilder(input);

        // Replace first letter with upper-case equivalent
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        
        // Check if we deal with the special case of "Value" which must always be lower case "value".
        String returnName = sb.toString();
        return ("Value".equals(returnName)) ? "value" : returnName;
    }
}
