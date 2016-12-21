/*
 * AuditRequestWrapper.java
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import sif3.common.CommonConstants;
import sif3.common.model.audit.AuditRecord;
import sif3.common.persist.model.SIF3Session;
import sif3.infra.rest.resource.BaseResource;
import sif3.infra.rest.resource.audit.AuditableResource;
import sif3.infra.rest.web.io.RecordingInputStream;

/**
 * This class is used by the audit filter to wrap and 'intercept' the data from a HTTP Request and the
 * related HTTP Response. Further the Audit Filter will pass the original REST resource to this wrapper
 * allowing it to extract information held by that resource. For this audit filter this is mainly date
 * related to the client's SIF session, zone and context. This is mainly useful for DIRECT environments
 * but can also be used in BROKERED environments. 
 * 
 * @author Ben Carter
 *
 */
public class AuditRequestWrapper extends HttpServletRequestWrapper implements HttpHeaders
{

	private AuditRecord          auditRecord = null;
	private RecordingInputStream inputStream = null;
	private BaseResource         resource    = null;

	public AuditRequestWrapper(HttpServletRequest request)
	{
		super(request);
		this.auditRecord = new AuditRecord();
		this.auditRecord.getRequestData().setTimestamp(new Date());
		this.auditRecord.getRequestData().setUrl(request.getRequestURL().toString());
	}

	@Override
	public ServletInputStream getInputStream() throws IOException
	{
		if (inputStream == null)
		{
			inputStream = new RecordingInputStream(super.getInputStream());
		}
		return inputStream;
	}

	public void setResource(BaseResource resource)
	{
		this.resource = resource;
	}

	public String getContent()
	{
		String result = null;
		if (inputStream != null)
		{
			result = inputStream.getContent();
		}
		return result;
	}

	public AuditRecord getAuditRecord(AuditResponseWrapper httpResponse)
	{
		AuditRecord result = this.auditRecord;
		if (result != null)
		{
			this.resource = AuditableResource.getResource();
			result.getRequestData().setPayload(this.getContent());
			result.getRequestData().setMethod(this.getMethod());
			result.getRequestData().setClientIp(this.getRemoteAddr());
			result.getResponseData().setTimestamp(new Date());
			result.getResponseData().setPayload(httpResponse.getContent());
			result.getResponseData().setHttpStatus(httpResponse.getStatus());
			result.getRequestData().setQueryParameters(this.getQueryString());
			result.getRequestData().setHeaders(getHeaders(this));
			result.getResponseData().setHeaders(getHeaders(httpResponse));
			auditResource(resource);
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
		Collection<String> result = null;
		Enumeration<String> headerNames = getHeaderNames();
		if (headerNames != null)
		{
			result = Collections.list(headerNames);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.web.util.HttpHeaders#getHeadersCollection(java.lang.String)
	 */
	@Override
	public Collection<String> getHeadersCollection(String name)
	{
		Collection<String> result = null;
		Enumeration<String> headers = getHeaders(name);
		if (headers != null)
		{
			result = Collections.list(headers);
		}
		return result;
	}

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/

	private void auditResource(BaseResource resource)
	{
		if (resource != null)
		{
			if (resource.getSifZone() != null)
			{
				auditRecord.getSifData().setZone(resource.getSifZone().getId());
			}
			if (resource.getSifContext() != null)
			{
				auditRecord.getSifData().setContext(resource.getSifContext().getId());
			}
			if (resource.getRequestMediaType() != null)
			{
				auditRecord.getRequestData().setMediaType(resource.getRequestMediaType());
			}
			if (resource.getResponseMediaType() != null)
			{
				auditRecord.getResponseData().setMediaType(resource.getResponseMediaType());
			}

			if (resource.getEnvironmentManager() != null)
			{
// Don't use values from Environment Manager config because it is not the "consumer's" data, rather the data of the provider in a brokered environment!				
//				EnvironmentManager environmentManager = resource.getEnvironmentManager();
//				if (environmentManager.getEnvironmentInfo() != null && environmentManager.getEnvironmentInfo().getEnvironmentKey() != null)
//				{
//					EnvironmentKey environmentKey = resource.getEnvironmentManager().getEnvironmentInfo().getEnvironmentKey();
//					auditRecord.setSolutionId(environmentKey.getSolutionID());
//					auditRecord.setAppKey(environmentKey.getApplicationKey());
//					auditRecord.setInstanceId(environmentKey.getInstanceID());
//					auditRecord.setUserToken(environmentKey.getUserToken());
//				}
				
				// At this point the validateSession() should already have been called within the normal path of execution in the original resource.
				// It could potentially be an expensive call and should not be done again in here.
				//resource.validateSession();
				SIF3Session session = resource.getSIF3SessionForRequest();
				if (session != null)
				{
					auditRecord.getSifData().setSolutionId(session.getSolutionID());
					auditRecord.getSifData().setAppKey(session.getApplicationKey());
					auditRecord.getSifData().setInstanceId(session.getInstanceID());
					auditRecord.getSifData().setUserToken(session.getUserToken());
					auditRecord.getSifData().setEnvironmentId(session.getEnvironmentID());
					auditRecord.getSifData().setSessionToken(session.getSessionToken());

					if (auditRecord.getSifData().getZone() == null)
					{
						auditRecord.getSifData().setZone(session.getDefaultZone().getId());
					}
					if (auditRecord.getSifData().getContext() == null)
					{
						auditRecord.getSifData().setContext(CommonConstants.DEFAULT_CONTEXT.getId());
					}
				}
			}
		}
	}

	private Map<String, Object> getHeaders(HttpHeaders httpRequest)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		if (httpRequest != null)
		{
			Collection<String> headerNames = httpRequest.getHeaderNamesCollection();
			if (headerNames != null)
			{
				for (String header : headerNames)
				{
					result.put(header, getHeaderValue(httpRequest.getHeadersCollection(header)));
				}
			}
		}
		return result;
	}

	private Object getHeaderValue(Collection<String> headerValues)
	{
		List<String> result = new ArrayList<String>();
		if (headerValues != null)
		{
			for (String value : headerValues)
			{
				result.add(value);
			}
		}
		if (result.size() == 1)
		{
			return result.get(0);
		}
		return result;
	}
}
