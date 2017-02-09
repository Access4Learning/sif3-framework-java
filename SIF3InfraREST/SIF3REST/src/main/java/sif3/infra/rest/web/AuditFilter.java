/*
 * AuditFilter.java
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
package sif3.infra.rest.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.common.interfaces.Auditor;
import sif3.common.model.audit.AuditRecord;
import sif3.infra.rest.resource.audit.AuditableResource;
import sif3.infra.rest.web.audit.AuditRequestWrapper;
import sif3.infra.rest.web.audit.AuditResponseWrapper;

/**
 * This class is the Servlet Filter implementation to enable auditing of all HTTP Requests and HTTP Responses.
 * 
 * @author Ben Carter & Joerg Huber
 *
 */
public class AuditFilter implements Filter
{
	public static final String AUDIT_SERVICE_CLASS = "AUDIT_SERVICE_CLASS";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Auditor        auditor = null;

	@Override
	public void destroy()
	{
		// nothing to do here.
	}

	@Override
	public void init(FilterConfig config) throws ServletException
	{
		logger.debug("Initialse AuditFilter.");		
		String className = config.getInitParameter(AUDIT_SERVICE_CLASS);
		if (className != null)
		{
			try
			{
				Class<?> auditorClass = Class.forName(className);
				Object instance = null;
				try
				{
					instance = auditorClass.newInstance();
				}
				catch (Exception ignore)
				{}
				
				// Ensure that the audit class implementation implements the correct interface.
				if ((instance != null) && Auditor.class.isAssignableFrom(instance.getClass()))
				{
					auditor = Auditor.class.cast(instance);
				}
				
				if (auditor != null)
				{
					logger.info("Auditing enabled with: " + className);
				}
				else
				{
					logger.error("Auditing disabled with :" + className + ". Ensure the class '"+className+"' implements the "+Auditor.class.getName()+" interface.");
				}
			}
			catch (ClassNotFoundException e)
			{
				logger.error("Auditing disabled with: " + className + " - ClassNotFound", e);
			}
		}
		else
		{
			logger.info("No auditing service defined. Auditing disabled");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		if (auditor != null && HttpServletRequest.class.isAssignableFrom(request.getClass()) && HttpServletResponse.class.isAssignableFrom(response.getClass()))
		{
			AuditRequestWrapper httpRequest = new AuditRequestWrapper(HttpServletRequest.class.cast(request));
			AuditResponseWrapper httpResponse = new AuditResponseWrapper(HttpServletResponse.class.cast(response));
			AuditableResource.setAuditing();
			try
			{
				chain.doFilter(httpRequest, httpResponse);
			}
			catch (Exception ex)
			{
				logger.error("Unhandled exception being thrown :", ex);
			}
			AuditRecord auditRecord = httpRequest.getAuditRecord(httpResponse);
			auditor.audit(auditRecord);
			AuditableResource.remove();
		}
		else
		{
			chain.doFilter(request, response);
		}
	}
}
