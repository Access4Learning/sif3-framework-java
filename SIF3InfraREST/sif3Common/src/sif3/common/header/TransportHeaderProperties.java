/*
 * TransportHeaderProperties.java
 * Created: 03/09/2013
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

package sif3.common.header;

import java.util.HashMap;

/**
 * This class provides an interface into the various header properties that need to be dealt with in SIF3. It is transport independent.
 * There is the possibility that different header properties are required for REST and/or SOAP. With this interface it makes that
 * distinction transparent.
 * 
 * @author Joerg Huber
 *
 */
public interface TransportHeaderProperties
{
	/**
	 * Stores the value of a header property. If the property with that name is already set then it will be overwritten. If the 
	 * 'hdrPropertyValue' is null then then the property with the name 'hdrPropertyName' will be removed. If the 'hdrPropertyName' is
	 * null or empty string then no action is taken.
	 * 
	 * @param hdrPropertyName Header property name.
	 * @param hdrPropertyValue Header property value.
	 */
	public void setHeaderProperty(String hdrPropertyName, String hdrPropertyValue);
	
	/**
	 * Stores all values of the hashmap. 
	 * 
	 * @param hdrProperties Hashmap with the KEY=Property Name; VALUE=Property Value
	 */
	public void setHeaderProperties(HashMap<String, String> hdrProperties);
	
	/**
	 * Returns the value of a header property. If the property with that name does not exist then null is returned.
	 * 
	 * @param hdrPropertyName Header property name.
	 * @return Header property value. Null if it doesn't exist.
	 */
	public String getHeaderProperty(String hdrPropertyName);
	
	/**
	 * Returns the value of a header property. If the property with that name does not exist or is null then the default 
	 * value is returned.
	 * 
	 * @param hdrPropertyName Header property name.
	 * @param hdrDefaultValue Value to return if no property for the given hdrPropertyName exists.
	 * 
	 * @return Header property value. 'hdrDefaultValue' if it doesn't exist.
	 */
	public String getHeaderProperty(String hdrPropertyName, String hdrDefaultValue);
	
	/**
	 * Returns all header properties. A true copy of the header properties stored within this class is returned. 
	 * 
	 * @return Hashmap with the KEY=Property Name; VALUE=Property Value
	 */
	public HashMap<String, String> getHeaderProperties();

}
