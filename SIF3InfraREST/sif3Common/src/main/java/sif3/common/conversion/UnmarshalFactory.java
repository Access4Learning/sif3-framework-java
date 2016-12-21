/*
 * UnmarshalFactory.java
 * Created: 08/09/2013
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

package sif3.common.conversion;

import javax.ws.rs.core.MediaType;

import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;

/**
 * This class provides the interface for the Unmarshal Factories. Because the SIF3 infrastructure is independent from the data model
 * all operations in this framework have been written with the assumption that the data model is unknown. For the framework to be 
 * functional, though, most lower level implementations require some marshal/unmarshal of objects to either XML or JSON. This interface
 * defines that functionality, so that the low level classes can perform these operations without the knowledge of the actual data model.
 * 
 * @author Joerg Huber
 *
 */
public interface UnmarshalFactory extends MediaTypeOperations
{
	/**
	 * Unmarshal the given XML string into an object of type Class<?>. If anything goes wrong with that unmarshal method then
	 * a UnmarshalException is returned.
	 * 
	 * @param payload The XML string to be unmarshalled into an object. 
	 * @param clazz The object type to unmarshal into.
	 * 
	 * @return See description.
	 * 
	 * @throws UnmarshalException Failure to unmarshal the given XML string into an object.
	 * @throws UnsupportedMediaTypeExcpetion If XML is not a supported format.
	 */
	public Object unmarshalFromXML(String payload, Class<?> clazz) throws UnmarshalException, UnsupportedMediaTypeExcpetion;
	
	/**
	 * Unmarshal the given JSON string into an object of type Class<?>. If anything goes wrong with that unmarshal method then
	 * a UnmarshalException is returned.
	 * 
	 * @param payload The JSON string to be unmarshalled into an object. 
	 * @param clazz The object type to unmarshal into.
	 * 
	 * @return See description.
	 * 
	 * @throws UnmarshalException Failure to unmarshal the given JSON string into an object.
	 * @throws UnsupportedMediaTypeExcpetion If JSON is not a supported format.
	 */
	public Object unmarshalFromJSON(String payload, Class<?> clazz) throws UnmarshalException, UnsupportedMediaTypeExcpetion;
	
	/**
	 * Wrapper Method for the above two methods. This method can be called if the MediaType is known and based on what the input string
	 * is (XML or JSON). This unmarshal method must only support 'application/xml and application/json.
	 * 
	 * @param payload The string to marshal into an object.
	 * @param clazz The object type to unmarshal into.
	 * @param mediaType Indicates what the object shall be unmarshalled from (JSON or XML).
	 * 
	 * @return See description.
	 * 
	 * @throws UnmarshalException Failure to unmarshal the given string into an object.
	 * @throws UnsupportedMediaTypeExcpetion If the media type requested is not a supported format.
	 */
	public Object unmarshal(String payload, Class<?> clazz, MediaType mediaType) throws UnmarshalException, UnsupportedMediaTypeExcpetion;
}
