/*
 * ServiceInfo.java
 * Created: 22/10/2013
 *
 * Copyright 2013 Systemic Pty Ltd
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

package sif3.infra.common.env.types;

import java.io.Serializable;

import sif3.common.model.EnvironmentZoneContextInfo;

/**
 * This class is a POJO for service information as provided by the Environment. Each environment has a list of services and each service 
 * has a number of properties that define a service such as Zone, Context, Object Type (i.e. StudentPersonals) etc. These properties
 * can be stored in this class and utilised in higher level classes of this framework.
 * 
 * @author Joerg Huber
 *
 */
public class ServiceInfo extends EnvironmentZoneContextInfo implements Serializable
{
	public static enum ServiceType {OBJECT, UTILITY, FUNCTION};
	
    private static final long serialVersionUID = -948637161157336728L;
    
	private String serviceName = null;
    private ServiceType serviceType = ServiceType.OBJECT;
    
    /**
     * Constructor
     * 
     * @param serviceName The name of a service such as 'StudentPersonals', 'alerts', 'zones' etc. This is NOT the name of the adapter!
     */
    public ServiceInfo(String serviceName)
    {
    	super(null);
    	setServiceName(serviceName);
    }
    
    /**
     * Constructor
     * 
     * @param serviceName The name of a service such as 'StudentPersonals', 'alerts', 'zones' etc. This is NOT the name of the adapter!
     * @param serviceType Indicates if the service is an OBJECT, UTILITY or FUNCTIONal service as defined by the SIF3 spec.
     */
    public ServiceInfo(String serviceName, ServiceType serviceType)
    {
    	super(null);
    	setServiceName(serviceName);
    	setServiceType(serviceType);
    }

    public String getServiceName()
    {
    	return this.serviceName;
    }

	public void setServiceName(String serviceName)
    {
    	this.serviceName = serviceName;
    }

	public ServiceType getServiceType()
    {
    	return this.serviceType;
    }

	public void setServiceType(ServiceType serviceType)
    {
    	this.serviceType = serviceType;
    }

	@Override
    public String toString()
    {
	    return "ServiceInfo [serviceName=" + this.serviceName + ", serviceType=" + this.serviceType + ", toString()=" + super.toString() + "]";
    }
}
