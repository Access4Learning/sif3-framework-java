/*
 * SIF3Subscription.java
 * Created: 18/04/2014
 *
 * Copyright 2014 Systemic Pty Ltd
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

package sif3.common.persist.model;

import java.io.Serializable;
import java.util.Date;

/**
 * POJO to encapsulate SIF3 Subscription Information and configuration.
 * 
 * @author Joerg Huber
 *
 */
public class SIF3Subscription implements Serializable
{
	private static final long serialVersionUID = 8765421301776L;

	private String subscriptionID;
	private String queueID;
	private String adapterType; //CONSUMER, PROVIDER, ENVIRONMENT_PROVIDER
	private String zoneID;
	private String contextID;
	private String serviceName;
	private String serviceType; // HedaerValues.ServiceType
	private Date created;
	private Date lastAccessed;
	
	public String getSubscriptionID()
    {
    	return this.subscriptionID;
    }
	
	public void setSubscriptionID(String subscriptionID)
    {
    	this.subscriptionID = subscriptionID;
    }
	
	public String getQueueID()
    {
    	return this.queueID;
    }
	
	public void setQueueID(String queueID)
    {
    	this.queueID = queueID;
    }
	
	public String getAdapterType()
    {
    	return this.adapterType;
    }
	
	public void setAdapterType(String adapterType)
    {
    	this.adapterType = adapterType;
    }
	
	public String getZoneID()
    {
    	return this.zoneID;
    }
	
	public void setZoneID(String zoneID)
    {
    	this.zoneID = zoneID;
    }
	
	public String getContextID()
    {
    	return this.contextID;
    }
	
	public void setContextID(String contextID)
    {
    	this.contextID = contextID;
    }
	
	public String getServiceName()
    {
    	return this.serviceName;
    }
	
	public void setServiceName(String serviceName)
    {
    	this.serviceName = serviceName;
    }
	
	public String getServiceType()
    {
    	return this.serviceType;
    }
	
	public void setServiceType(String serviceType)
    {
    	this.serviceType = serviceType;
    }
	
	public Date getCreated()
    {
    	return this.created;
    }
	
	public void setCreated(Date created)
    {
    	this.created = created;
    }
	
	public Date getLastAccessed()
    {
    	return this.lastAccessed;
    }
	public void setLastAccessed(Date lastAccessed)
    {
    	this.lastAccessed = lastAccessed;
    }
	
	@Override
    public String toString()
    {
	    return "SIF3Subscription [subscriptionID=" + this.subscriptionID + ", queueID="
	            + this.queueID + ", adapterType=" + this.adapterType + ", zoneID=" + this.zoneID
	            + ", contextID=" + this.contextID + ", serviceName=" + this.serviceName
	            + ", serviceType=" + this.serviceType + ", created=" + this.created
	            + ", lastAccessed=" + this.lastAccessed + "]";
    }
}
