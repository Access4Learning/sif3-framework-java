/*
 * MarshalFactory.java
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

import sif3.common.CommonConstants.SchemaType;
import sif3.common.exception.MarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;

/**
 * This class provides the interface for the Marshal Factories. Because the SIF3 infrastructure is independent from the data model
 * all operations in this framework have been written with the assumption that the data model is unknown. For the framework to be 
 * functional, though, most lower level implementations require some marshal/unmarshal of objects to either XML or JSON. This interface
 * defines that functionality, so that the low level classes can perform these operations without the knowledge of the actual data model.
 * 
 * @author Joerg Huber
 *
 */
public interface MarshalFactory extends MediaTypeOperations
{
	/**
	 * Marshal the given object to XML and returns the resulting XML as a String. If anything goes wrong with that marshal method then
	 * a MarshalException is returned.
	 * 
	 * @param obj The object to be marshaled to XML.
	 * 
	 * @return See description.
	 * 
	 * @throws MarshalException Failure to marshal the object to XML.
	 * @throws UnsupportedMediaTypeExcpetion If XML is not a supported format.
	 */
	public String marshalToXML(Object obj) throws MarshalException, UnsupportedMediaTypeExcpetion;
	
	/**
	 * Marshal the given object to JSON and returns the resulting JSON as a String. If anything goes wrong with that marshal method then
	 * a MarshalException is returned. This method assumes that the JSON is in Goessner notation.<br/><br/>
     * 
     * Note:<br/>
     * This method is for backward compatibility. Ideally the method with the SchemaType  should be used.
	 * 
	 * @param obj The object to be marshaled to JSON.
	 * 
	 * @return See description.
	 * 
	 * @throws MarshalException Failure to marshal the object to JSON.
     * @throws UnsupportedMediaTypeExcpetion If JSON is not a supported format.
	 */
	@Deprecated
	public String marshalToJSON(Object obj) throws MarshalException, UnsupportedMediaTypeExcpetion;
	
    /**
     * Marshal the given object to JSON and returns the resulting JSON as a String. If anything goes wrong with that marshal method then
     * a MarshalException is returned. Since SIF 3.3 there are two supported JSON notations. Goessner (default) and PESC (new).
     * The jsonSchema parameter indicates which of the two notations shall be used. If the value is null then Goessner is assumed.
     * 
     * @param obj The object to be marshaled to JSON.
     * @param jsonSchema Indicates if Goessner or PESC notation shall be used.
     * 
     * @return See description.
     * 
     * @throws MarshalException Failure to marshal the object to JSON.
     * @throws UnsupportedMediaTypeExcpetion If JSON is not a supported format.
     */
    public String marshalToJSON(Object obj, SchemaType jsonSchema) throws MarshalException, UnsupportedMediaTypeExcpetion;

    /**
	 * Wrapper Method for the above two methods. This method can be called if the MediaType is known and based on which either XML or
	 * JSON is required. This marshal method must only support 'application/xml and application/json. This method assumes that the 
	 * JSON is in Goessner notation.<br/><br/>
     * 
     * Note:<br/>
     * This method is for backward compatibility. Ideally the method with the SchemaType  should be used.
	 * 
	 * @param obj The object to be marshaled
	 * @param mediaType Indicates what the object shall be marshaled into (JSON or XML).
	 * 
	 * @return The String representation of the object.
	 * 
	 * @throws MarshalException Failure to marshal the object.
     * @throws UnsupportedMediaTypeExcpetion If the media type requested is not a supported format.
	 */
    @Deprecated
    public String marshal(Object obj, MediaType mediaType) throws MarshalException, UnsupportedMediaTypeExcpetion;

    /**
     * Wrapper Method for the above two methods. This method can be called if the MediaType is known and based on which either XML or
     * JSON is required. This marshal method must only support 'application/xml and application/json. Since SIF 3.3 there are two 
     * supported JSON notations. Goessner (default) and PESC (new). The jsonSchema parameter indicates which of the two notations 
     * shall be used if the mediaType is application/json. If the value is null then Goessner is assumed. If mediaType is 
     * anything else then the jsonSchema parameter can be ignored.
     * 
     * @param obj The object to be marshaled
     * @param mediaType Indicates what the object shall be marshaled into (JSON or XML).
     * @param jsonSchema Indicates if Goessner or PESC notation shall be used if mediaType indicates JSON.
     * 
     * @return The String representation of the object.
     * 
     * @throws MarshalException Failure to marshal the object.
     * @throws UnsupportedMediaTypeExcpetion If the media type requested is not a supported format.
     */
    public String marshal(Object obj, MediaType mediaType, SchemaType jsonSchema) throws MarshalException, UnsupportedMediaTypeExcpetion;

}
