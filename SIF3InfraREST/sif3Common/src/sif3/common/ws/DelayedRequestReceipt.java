/*
 * DelayedRequestReceipt.java
 * Created: 8 Sep 2015
 *
 * Copyright 2015 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License. 
 */

package sif3.common.ws;

import java.io.Serializable;
import java.util.Date;

import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;

/**
 * This class is a POJO that holds some useful information about the response to a DELAYED request. It is up to the consumer to
 * decide what it wants to do with that receipt information. Ignore it, persist it or any other actions are all valid options.
 * Note that this POJO is not a stand-alone class but will form part of a response object that is returned to a consumer and
 * therefore there is no need to hold information that is originally stored in the response such as HTTP status, error info etc.
 * Please refer to @see sif3.common.ws.BaseResponse for more information.
 * 
 * @author Joerg Huber
 *
 */
public class DelayedRequestReceipt implements Serializable
{
    private static final long serialVersionUID = -1965220525993041749L;

    /**
     *  The GUID of the original request. This is the core info to should be used to relate future delayed responses back to the
     *  original request.
     */
    private String requestGUID = null;
    
    // Date when request was issued.
    private Date requestDate = new Date();
    
    // Full URI of the request
    private String requestURI = null;
    
    // The zone the request was sent to.
    private SIFZone zone = null;
    
    // The context the request was sent to.
    private SIFContext context = null;
    
    // The Service Name of the request
    private String serviceName = null;
    
    // The serviceType (OBJECT, SERVICEPATH etc).
    private ServiceType serviceType = ServiceType.OBJECT;
    
    // Holds the type originally requested operation (i.e. CREATE, QUERY ...)
    private ResponseAction requestedAction = null;

	/**
     * @return the requestGUID
     */
    public String getRequestGUID()
    {
        return requestGUID;
    }

    /**
     * @param requestGUID the requestGUID to set
     */
    public void setRequestGUID(String requestGUID)
    {
        this.requestGUID = requestGUID;
    }

    /**
     * @return the requestDate
     */
    public Date getRequestDate()
    {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(Date requestDate)
    {
        this.requestDate = requestDate;
    }

    /**
     * @return the relativeRequestURI
     */
    public String getRequestURI()
    {
        return requestURI;
    }

    /**
     * @param relativeRequestURI the requestURI to set
     */
    public void setRequestURI(String requestURI)
    {
        this.requestURI = requestURI;
    }

    /**
     * @return the zone
     */
    public SIFZone getZone()
    {
        return zone;
    }

    /**
     * @param zone the zone to set
     */
    public void setZone(SIFZone zone)
    {
        this.zone = zone;
    }

    /**
     * @return the context
     */
    public SIFContext getContext()
    {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(SIFContext context)
    {
        this.context = context;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName()
    {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    /**
     * @return the serviceType
     */
    public ServiceType getServiceType()
    {
        return serviceType;
    }

    /**
     * @param serviceType the serviceType to set
     */
    public void setServiceType(ServiceType serviceType)
    {
        this.serviceType = serviceType;
    }

    public ResponseAction getRequestedAction()
	{
		return requestedAction;
	}

	public void setRequestedAction(ResponseAction requestedAction)
	{
		this.requestedAction = requestedAction;
	}

	@Override
    public String toString()
    {
	    return "DelayedRequestReceipt [requestGUID=" + requestGUID
	            + ", requestDate=" + requestDate + ", requestURI=" + requestURI
	            + ", zone=" + zone + ", context=" + context + ", serviceName="
	            + serviceName + ", serviceType=" + serviceType
	            + ", requestedAction=" + requestedAction + "]";
    }
}
