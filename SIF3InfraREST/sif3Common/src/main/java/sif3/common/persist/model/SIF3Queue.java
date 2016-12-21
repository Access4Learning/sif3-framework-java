/*
 * SIF3Queue.java
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
 * POJO to encapsulate SIF3 Queue Information and configuration.
 * 
 * @author Joerg Huber
 *
 */
public class SIF3Queue implements Serializable
{
	private static final long serialVersionUID = 76126564120010134L;

	private String queueID;
	private String name;
	private String queueType;  // CommonConstants.QueuePollingType
	private String environmentID;
	private String adapterType; //CONSUMER, PROVIDER, ENVIRONMENT_PROVIDER
	private String zoneID;
	private String contextID;
	private String serviceName;
	private String serviceType; // HedaerValues.ServiceType
	private String messageURI;
	private String wakeupURI;
	private Long longPollingTimeout;
	private Long waitTime;
	private int maxConsumers;
	private Long numMessages;
	private Date created;
	private Date lastAccessed;
	private Date lastModified;
	
	public String getQueueID()
    {
    	return this.queueID;
    }
	
	public void setQueueID(String queueID)
    {
    	this.queueID = queueID;
    }
	
	public String getName()
    {
    	return this.name;
    }
	
	public void setName(String name)
    {
    	this.name = name;
    }
	
	public String getQueueType()
    {
    	return this.queueType;
    }
	
	public void setQueueType(String queueType)
    {
    	this.queueType = queueType;
    }
	
	public String getEnvironmentID()
    {
    	return this.environmentID;
    }
	
	public void setEnvironmentID(String environmentID)
    {
    	this.environmentID = environmentID;
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

	public String getMessageURI()
    {
    	return this.messageURI;
    }
	
	public void setMessageURI(String messageURI)
    {
    	this.messageURI = messageURI;
    }
	
	public String getWakeupURI()
    {
    	return this.wakeupURI;
    }
	
	public void setWakeupURI(String wakeupURI)
    {
    	this.wakeupURI = wakeupURI;
    }
	
	public Long getLongPollingTimeout()
    {
    	return this.longPollingTimeout;
    }
	
	public void setLongPollingTimeout(Long longPollingTimeout)
    {
    	this.longPollingTimeout = longPollingTimeout;
    }
	
	public Long getWaitTime()
    {
    	return this.waitTime;
    }
	
	public void setWaitTime(Long waitTime)
    {
    	this.waitTime = waitTime;
    }
	
	public int getMaxConsumers()
    {
    	return this.maxConsumers;
    }
	
	public void setMaxConsumers(int maxConsumers)
    {
    	this.maxConsumers = maxConsumers;
    }
	
	public Long getNumMessages()
    {
    	return this.numMessages;
    }
	
	public void setNumMessages(Long numMessages)
    {
    	this.numMessages = numMessages;
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
	
	public Date getLastModified()
    {
    	return this.lastModified;
    }
	
	public void setLastModified(Date lastModified)
    {
    	this.lastModified = lastModified;
    }
	
	@Override
    public String toString()
    {
	    return "SIF3Queue [queueID=" + this.queueID + ", name=" + this.name + ", queueType="
	            + this.queueType + ", environmentID=" + this.environmentID + ", adapterType="
	            + this.adapterType + ", zoneID=" + this.zoneID + ", contextID=" + this.contextID
	            + ", serviceName=" + this.serviceName + ", serviceType=" + this.serviceType
	            + ", messageURI=" + this.messageURI + ", wakeupURI=" + this.wakeupURI
	            + ", longPollingTimeout=" + this.longPollingTimeout + ", waitTime=" + this.waitTime
	            + ", maxConsumers=" + this.maxConsumers + ", numMessages=" + this.numMessages
	            + ", created=" + this.created + ", lastAccessed=" + this.lastAccessed
	            + ", lastModified=" + this.lastModified + "]";
    }	
}
