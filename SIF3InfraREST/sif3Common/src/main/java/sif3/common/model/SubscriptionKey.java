/*
 * SubscriptionKey.java
 * Created: 06/05/2014
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

package sif3.common.model;

import java.io.Serializable;

/**
 * POJO to abstract the elements that make up a queue subscription key.
 * 
 * @author Joerg Huber
 *
 */
public class SubscriptionKey implements Serializable
{
    private static final long serialVersionUID = 2122328483663348452L;
    
	private String zoneID;
	private String contextID;
	private String serviceName;
	private String serviceType;
	
	public SubscriptionKey()
	{
		this(null, null, null, null);
	}
	
	public SubscriptionKey(String zoneID, String contextID, String serviceName, String serviceType)
	{
		super();
		setZoneID(zoneID);
		setContextID(contextID);
		setServiceName(serviceName);
		setServiceType(serviceType);
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
	
    @Override
    public boolean equals(Object obj) 
    {
        if ((obj != null) && (obj instanceof SubscriptionKey))
        {
        	return this.hashCode() == ((SubscriptionKey)obj).hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() 
    {
    	int result = 31 + ((getZoneID() != null) ? getZoneID().hashCode() : 0);
    	result = result * 31 + ((getContextID() != null) ? getContextID().hashCode() : 0);
    	result = result * 31 + ((getServiceName() != null) ? getServiceName().hashCode() : 0);
    	result = result * 31 + ((getServiceType() != null) ? getServiceType().hashCode() : 0);
    	
    	return result;
    }
	
	@Override
    public String toString()
    {
	    return "SubscriptionKey [zoneID=" + this.zoneID + ", contextID=" + this.contextID
	            + ", serviceName=" + this.serviceName + ", serviceType=" + this.serviceType + "]";
    }
}
