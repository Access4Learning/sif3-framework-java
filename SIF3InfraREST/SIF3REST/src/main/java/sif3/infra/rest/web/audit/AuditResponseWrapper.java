/*
 * AuditResponseWrapper.java
 * Created: 04/03/2015
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
package sif3.infra.rest.web.audit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import sif3.infra.rest.web.io.TeeOutputStream;

/**
 * This class is used by the audit filter to wrap and 'intercept' the data from a HTTP Response. It would allow
 * to potentially extract or change values in the actual HTTP Response.
 * 
 * @author Ben Carter
 *
 */
public class AuditResponseWrapper extends HttpServletResponseWrapper implements HttpHeaders
{

	private ByteArrayOutputStream outputStream    = null;
	private TeeOutputStream       teeOutputStream = null;

	public AuditResponseWrapper(HttpServletResponse response)
	{
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException
	{
		this.outputStream = new ByteArrayOutputStream();
		this.teeOutputStream = new TeeOutputStream(super.getOutputStream(), this.outputStream);
		
		return this.teeOutputStream;
	}

	public String getContent()
	{
		String result = null;
		if (outputStream != null)
		{
			result = new String(outputStream.toByteArray());
		}
		return result;
	}

	/*-----------------------*/
	/*-- Interface Methods --*/
	/*-----------------------*/

	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.web.util.HttpHeaders#getHeaderNamesCollection()
	 */
	@Override
	public Collection<String> getHeaderNamesCollection()
	{
		return super.getHeaderNames();
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.web.util.HttpHeaders#getHeadersCollection(java.lang.String)
	 */
	@Override
	public Collection<String> getHeadersCollection(String name)
	{
		return super.getHeaders(name);
	}
}
