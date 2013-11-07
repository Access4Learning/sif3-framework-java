/*
 * BaseResponse.java
 * Created: 28/09/2013
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

package sif3.common.ws;

import java.io.Serializable;

import javax.ws.rs.core.MediaType;

import sif3.common.header.TransportHeaderProperties;

/**
 * This is a utility class. All HTTP responses (over REST or SOAP) have a number of fields set. This class encapsulates some of these
 * common fields. It is not intended to be used on its own rather as a base to more concrete responses. This class is more of a 
 * super class.
 * 
 * @author Joerg Huber
 *
 */
public class BaseResponse implements Serializable
{
    private static final long serialVersionUID = 8140925322367680380L;

	private int status = -1;
	private String statusMessage = null;
	private boolean hasEntity = false;
	private ErrorDetails error = null;
	private TransportHeaderProperties hdrProperties = null;
	private MediaType mediaType = null;
	private int contentLength = -1;
	
	/**
	 * Get the HTTP status.
	 * 
	 * @return See Desc.
	 */
	public int getStatus()
    {
    	return this.status;
    }
	
	public void setStatus(int status)
    {
    	this.status = status;
    }
	
	/**
	 * Get the full status message as returned in the HTTP response.
	 * 
   * @return See Desc.
	 */
	public String getStatusMessage()
    {
    	return this.statusMessage;
    }
	
	public void setStatusMessage(String statusMessage)
    {
    	this.statusMessage = statusMessage;
    }
	
	/**
	 * The HTTP entity indicator.
	 * 
   * @return See Desc.
	 */
	public boolean getHasEntity()
    {
    	return this.hasEntity;
    }
	
	public void setHasEntity(boolean hasEntity)
    {
    	this.hasEntity = hasEntity;
    }
	
	/**
	 * Framework specific: In this framework an error is encapsulated into this type. It is also aligned with the SIF3 error message
	 * structure to easily allow interoperability if SIF3 error messages.
	 * 
	 * @return See desc.
	 */
	public ErrorDetails getError()
    {
    	return this.error;
    }
	
	public void setError(ErrorDetails error)
    {
    	this.error = error;
    }
	
	/**
	 * Encapsulates a selected set (user defined what that set is) of HTTP header properies in a nice format.
	 * 
	 * @return See desc.
	 */
	public TransportHeaderProperties getHdrProperties()
    {
    	return this.hdrProperties;
    }
	
	public void setHdrProperties(TransportHeaderProperties hdrProperties)
    {
    	this.hdrProperties = hdrProperties;
    }
	
	/**
	 * HTTP media type.
	 * 
	 * @return See desc.
	 */
	public MediaType getMediaType()
    {
    	return this.mediaType;
    }
	
	public void setMediaType(MediaType mediaType)
    {
    	this.mediaType = mediaType;
    }
	
	/**
	 * Standard HTTP Header property that indicates the length in bytes of the content, if any, returned.
	 * 
   * @return See desc.
	 */
	public int getContentLength()
    {
    	return this.contentLength;
    }
	
	public void setContentLength(int contentLength)
    {
    	this.contentLength = contentLength;
    }
	
	/**
	 * Conevenicne method to check if the error details property is set.
	 * 
	 * @return See desc.
	 */
	public boolean hasError()
	{
		return (error != null);
	}

	@Override
    public String toString()
    {
	    return "BaseResponse [status=" + this.status + ", statusMessage=" + this.statusMessage
	            + ", hasEntity=" + this.hasEntity + ", error=" + this.error + ", hdrProperties="
	            + this.hdrProperties + ", mediaType=" + this.mediaType + ", contentLength="
	            + this.contentLength + "]";
    }
}
