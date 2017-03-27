/*
 * CSVUnmarshaller.java
 * Created: 27/01/2015
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

package systemic.sif3.demo.rest.conversion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;

/**
 * Implementation of an unmarshal Factory for all CSV Payloads.
 * 
 * @author Joerg Huber
 *
 */
public class CSVUnmarshaller implements UnmarshalFactory
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

	//List of supported media types.
	private static final HashSet<MediaType> supportedMediaTypes = new HashSet<MediaType>(Arrays.asList(MediaType.TEXT_PLAIN_TYPE));

	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.UnmarshalFactory#unmarshalFromXML(java.lang.String, java.lang.Class)
	 */
	@Override
	public Object unmarshalFromXML(String payload, Class<?> clazz) throws UnmarshalException, UnsupportedMediaTypeExcpetion
	{
		return payload.toString();
	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.UnmarshalFactory#unmarshalFromJSON(java.lang.String, java.lang.Class)
	 */
	@Override
	public Object unmarshalFromJSON(String payload, Class<?> clazz) throws UnmarshalException, UnsupportedMediaTypeExcpetion
	{
		return payload.toString();
	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.UnmarshalFactory#unmarschal(java.lang.String, java.lang.Class, javax.ws.rs.core.MediaType)
	 */
	@Override
	public Object unmarshal(String payload, Class<?> clazz, MediaType mediaType) throws UnmarshalException, UnsupportedMediaTypeExcpetion
	{
		if (isSupported(mediaType))
		{
			return payload;
		}

		// If we get here then we deal with an unknown media type
		throw new UnsupportedMediaTypeExcpetion("Unsupported media type: " + mediaType + ". Cannot unmarshal the given input from this media type.");
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.conversion.MediaTypeOperations#getDefault()
	 */
	@Override
    public MediaType getDefault()
    {
	    return MediaType.TEXT_PLAIN_TYPE;
    }

	/*
	 * (non-Javadoc)
	 * @see sif3.common.conversion.MediaTypeOperations#isSupported(javax.ws.rs.core.MediaType)
	 */
	@Override
    public boolean isSupported(MediaType mediaType)
    {
		if (mediaType != null)
		{
			Set<MediaType> mediaTypes = getSupportedMediaTypes();
			for (Iterator<MediaType> iter = mediaTypes.iterator(); iter.hasNext();)
			{
				if (mediaType.isCompatible(iter.next()))
				{
					return true;
				}
			}
		}
		
		return false;
    }
	
	/* (non-Javadoc)
     * @see sif3.common.conversion.MediaTypeOperations#getSupportedMediaTypes()
     */
    @Override
    public Set<MediaType> getSupportedMediaTypes()
    {
	    return supportedMediaTypes;
    }
}
