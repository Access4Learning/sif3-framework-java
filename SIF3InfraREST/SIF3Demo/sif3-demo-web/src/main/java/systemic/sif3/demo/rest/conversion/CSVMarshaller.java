/*
 * CSVMarshaller.java
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

import sif3.common.conversion.MarshalFactory;
import sif3.common.exception.MarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;

/**
 * Implementation of a Marshal Factory for CSV Payloads.
 * 
 * @author Joerg Huber
 *
 */
public class CSVMarshaller implements MarshalFactory
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

	//List of supported media types.
	private static final HashSet<MediaType> supportedMediaTypes = new HashSet<MediaType>(Arrays.asList(MediaType.TEXT_PLAIN_TYPE));
	
	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.MarshalFactory#marshalToXML(java.lang.Object)
	 */
	@Override
  public String marshalToXML(Object obj) throws MarshalException, UnsupportedMediaTypeExcpetion
  {
		return obj.toString();
  }

	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.MarshalFactory#marshalToJSON(java.lang.Object)
	 */
	@Override
	public String marshalToJSON(Object obj) throws MarshalException, UnsupportedMediaTypeExcpetion
	{
		return obj.toString();
	}

	/* (non-Javadoc)
	 * @see sif3.infra.common.conversion.MarshalFactory#marschal(java.lang.Object, javax.ws.rs.core.MediaType)
	 */
	@Override
	public String marshal(Object obj, MediaType mediaType) throws MarshalException, UnsupportedMediaTypeExcpetion
	{
		if (isSupported(mediaType))
		{
			return obj.toString();
		}
		// If we get here then we deal with an unknown media type
		throw new UnsupportedMediaTypeExcpetion("Unsupported media type: " + mediaType + ". Cannot marshal the given input to this media type.");
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
