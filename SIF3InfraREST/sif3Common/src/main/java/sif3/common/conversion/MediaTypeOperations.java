/*
 * MediaTypeOperations.java
 * Created: 28/01/2015
 *
 * Copyright 2015 Systemic Pty Ltd
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

import java.util.Set;

import javax.ws.rs.core.MediaType;

/**
 * This interface defines some methods that are required by Marshallers and Unmarshallers to verify the valid mime/media types they
 * support as well as what their default behaviour is.
 * 
 * @author Joerg Huber
 */
public interface MediaTypeOperations
{
	/**
	 * This method returns true if the concrete implementation can "deal" with the given media/mime type. Otherwise false is returned.
	 * If null is passed to this method then false shall be returned.
	 * 
	 * @param mediaType The media type to test for support by the concrete implementation of this interface methods.
	 * 
	 * @return TRUE or FALSE.
	 */
	public boolean isSupported(MediaType mediaType);
	
	/**
	 * This method shall return the default mime/media type the concrete implementation will assume if something is not specified of
	 * wrongly specified. For example if an unsupported media type is used (see isSupported() method in this interface) then the implementation 
	 * of the concrete class may assume the media type given by this method. Should not return null as that behaviour may not be defined.
	 * 
	 * @return See desc.
	 */
	public MediaType getDefault();
	
	/**
	 * This method returns a set of mime/media types a marshaller/unmarshaller may support. This should never return null. If a
	 * marshaller/unmarshaller should not support any mime/media types (unlikely scenario) then an empty set rather than null
	 * shall be returned.
	 * 
	 * @return See desc.
	 */
	public Set<MediaType> getSupportedMediaTypes();
}
