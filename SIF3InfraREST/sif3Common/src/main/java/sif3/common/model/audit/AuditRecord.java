/*
 * AuditRecord.java
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
package sif3.common.model.audit;

import java.io.Serializable;

/**
 * This is a basic POJO of all the supported properties that can be audited through the SIF3 Framework.
 * 
 * @author Joerg Huber & Ben Carter
 *
 */
public class AuditRecord implements Serializable
{
	private static final long   serialVersionUID = -2942306308081090696L;

	private RequestData requestData;
	private ResponseData responseData;
	private SIFData sifData;
	
	public AuditRecord()
	{
		this(new RequestData(), new ResponseData(), new SIFData());
	}
	
	public AuditRecord(RequestData requestData, ResponseData responseData, SIFData sifData)
	{
		super();
		setRequestData(requestData);
		setResponseData(responseData);
		setSifData(sifData);
	}

	public RequestData getRequestData()
    {
    	return this.requestData;
    }
	
	public void setRequestData(RequestData requestData)
    {
    	this.requestData = requestData;
    }
	
	public ResponseData getResponseData()
    {
    	return this.responseData;
    }
	
	public void setResponseData(ResponseData responseData)
    {
    	this.responseData = responseData;
    }
	
	public SIFData getSifData()
    {
    	return this.sifData;
    }
	
	public void setSifData(SIFData sifData)
    {
    	this.sifData = sifData;
    }

	@Override
    public String toString()
    {
	    return "AuditRecord [requestData=" + this.requestData + ", responseData="
	            + this.responseData + ", sifData=" + this.sifData + "]";
    }
	
}
