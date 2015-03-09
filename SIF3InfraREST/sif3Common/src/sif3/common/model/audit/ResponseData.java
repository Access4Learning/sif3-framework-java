/*
 * ResponseData.java
 * Created: 09/03/2015
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

package sif3.common.model.audit;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.ws.rs.core.MediaType;

/**
 * @author Joerg Huber
 *
 */
public class ResponseData implements Serializable
{
    private static final long serialVersionUID = 9151171684509161145L;

    private Date                timestamp;
	private Integer             httpStatus; // The response HTTP Status
	private String              mediaType;
	private Map<String, Object> headers;
	private String              payload; // Payload of response

	public Date getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Date timestamp)
	{
		if ((this.timestamp == null || "".equals(this.timestamp)) && timestamp != null && !"".equals(timestamp))
		{
			this.timestamp = timestamp;
		}
	}

	public Map<String, Object> getHeaders()
	{
		return headers;
	}

	public void setHeaders(Map<String, Object> headers)
	{
		if ((this.headers == null || "".equals(this.headers))  && headers != null && !"".equals(headers))
		{
			this.headers = headers;
		}
	}

	public String getPayload()
	{
		return payload;
	}

	public void setPayload(String payload)
	{
		if ((this.payload == null || "".equals(this.payload)) && payload != null && !"".equals(payload))
		{
			this.payload = payload;
		}
	}

	public String getMediaType()
	{
		return mediaType;
	}

	public void setMediaType(MediaType mediaType)
	{
		if (mediaType != null)
		{
			this.mediaType = mediaType.toString();
		}
	}	

	public Integer getHttpStatus()
	{
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus)
	{
		if (this.httpStatus == null && httpStatus != null)
		{
			this.httpStatus = httpStatus;
		}
	}

	@Override
    public String toString()
    {
	    return "ResponseData [timestamp=" + this.timestamp + ", httpStatus=" + this.httpStatus
	            + ", mediaType=" + this.mediaType + ", headers=" + this.headers + ", payload="
	            + this.payload + "]";
    }
	
}
