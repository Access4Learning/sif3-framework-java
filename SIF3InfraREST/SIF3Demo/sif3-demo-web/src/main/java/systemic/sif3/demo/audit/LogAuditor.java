/*
 * LogAuditor.java
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
package systemic.sif3.demo.audit;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.common.interfaces.Auditor;
import sif3.common.model.audit.AuditRecord;

/**
 * This is a demo implementation of an Audit Class. This particular implementation simply logs all the audit entries in a particular audit
 * file through the log4j configuration.
 * 
 * @author Ben Carter & Joerg Huber
 *
 */
public class LogAuditor implements Auditor
{
	private static final Logger logger = LoggerFactory.getLogger(LogAuditor.class);

	@Override
	public void audit(AuditRecord auditRecord)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
		StringWriter logEntry = new StringWriter();
		PrintWriter writer = new PrintWriter(logEntry);
		writer.println();
		writer.println("----------");
		writer.print("Request Date: ");
		writer.println(sdf.format(auditRecord.getRequestData().getTimestamp()));
		writer.print("Requested Operation: ");
		writer.println(auditRecord.getRequestData().getMethod());
		writer.print("Solution ID: ");
		writer.println(auditRecord.getSifData().getSolutionId());
		writer.print("App Key: ");
		writer.println(auditRecord.getSifData().getAppKey());
		writer.print("User Token: ");
		writer.println(auditRecord.getSifData().getUserToken());
		writer.print("Context: ");
		writer.println(auditRecord.getSifData().getContext());
		writer.print("Zone: ");
		writer.println(auditRecord.getSifData().getZone());
		writer.print("Environment ID: ");
		writer.println(auditRecord.getSifData().getEnvironmentId());
		writer.print("Session Token: ");
		writer.println(auditRecord.getSifData().getSessionToken());
		writer.print("URL: ");
		writer.println(auditRecord.getRequestData().getUrl());
        writer.print("Mime Type: ");
        writer.println(auditRecord.getRequestData().getMediaType());
        writer.print("Headers: ");
        writer.println(auditRecord.getRequestData().getHeaders());
		writer.println("Body: ");
		writer.println("--");
		writer.println(auditRecord.getRequestData().getPayload());
		writer.println("--");
		writer.print("HTTP Response Status:");
		writer.println(auditRecord.getResponseData().getHttpStatus());
        writer.print("Response Mime Type:");
        writer.println(auditRecord.getResponseData().getMediaType());
        writer.print("Response Headers:");
        writer.println(auditRecord.getResponseData().getHeaders());
		writer.println("Response:");
		writer.println("--");
		writer.println(auditRecord.getResponseData().getPayload());
		writer.println("--");
		writer.print("Response Date: ");
		writer.println(sdf.format(auditRecord.getResponseData().getTimestamp()));
		writer.println("----------");
		logger.info(logEntry.toString());
	}

}
