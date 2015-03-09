/*
 * RequestData.java
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
public class RequestData implements Serializable
{
    private static final long serialVersionUID = 7592993075663080132L;

    private Date                timestamp;
	private String              clientIp;
	private String              url; // original invoked request URL
	private String              method; // HTTP Method such as GET, PUT, POST and DELETE
	private String              queryParameters; // URL query parameters
	private String              mediaType;
	private Map<String, Object> headers;
	private String              payload; // Payload of request

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

	public String getClientIp()
	{
		return clientIp;
	}

	public void setClientIp(String clientIp)
	{
		if ((this.clientIp == null || "".equals(this.clientIp)) && clientIp != null && !"".equals(clientIp))
		{
			this.clientIp = clientIp;
		}
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		if ((this.url == null || "".equals(this.url)) && url != null  && !"".equals(url))
		{
			this.url = url;
		}
	}

	public String getMethod()
	{
		return method;
	}

	public void setMethod(String method)
	{
		if ((this.method == null || "".equals(this.method)) && method != null && !"".equals(method))
		{
			this.method = method;
		}
	}

	public String getQueryParameters()
	{
		return queryParameters;
	}

	public void setQueryParameters(String queryParameters)
	{
		if ((this.queryParameters == null || "".equals(this.queryParameters)) && queryParameters != null && !"".equals(queryParameters))
		{
			this.queryParameters = queryParameters;
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

	@Override
    public String toString()
    {
	    return "RequestData [timestamp=" + this.timestamp + ", clientIp=" + this.clientIp
	            + ", url=" + this.url + ", method=" + this.method + ", queryParameters="
	            + this.queryParameters + ", mediaType=" + this.mediaType + ", headers="
	            + this.headers + ", payload=" + this.payload + "]";
    }
}
