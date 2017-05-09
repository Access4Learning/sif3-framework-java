/*
 * AttributeValue.java
 * Created: 4 Apr 2017
 *
 * Copyright 2017 Systemic Pty Ltd
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

package sif3.common.model;

import java.io.Serializable;

/**
 * This is a simple POJO to represent a string based attribute/value object.
 * 
 * @author Joerg Huber
 */
public class AttributeValue implements Serializable
{
    private static final long serialVersionUID = 8905008772095616682L;
    
    private String attribute = null;
    private String value = null;
    
    public AttributeValue()
    {
        this(null, null);
    }
    
    public AttributeValue(String attribute, String value)
    {
        super();
        setAttribute(attribute);
        setValue(value);
    }

    public String getAttribute()
    {
        return attribute;
    }
    
    public void setAttribute(String attribute)
    {
        this.attribute = attribute;
    }
    
    public String getValue()
    {
        return value;
    }
    
    public void setValue(String value)
    {
        this.value = value;
    }
    
    @Override
    public String toString()
    {
        return "AttributeValue [attribute=" + attribute + ", value=" + value + "]";
    }
    
    
}
