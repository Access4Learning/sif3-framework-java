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

import au.com.systemic.framework.utils.StringUtils;

import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.model.EnvironmentZoneContextInfo;
import sif3.infra.common.env.types.ServiceRights.AccessRight;
import sif3.infra.common.env.types.ServiceRights.AccessType;

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
    private static final long serialVersionUID = -948637161157336728L;
    
	private String serviceName = null;
    private ServiceType serviceType = ServiceType.OBJECT;
    private ServiceRights rights = new ServiceRights();
    
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

    public ServiceRights getRights()
    {
    	return this.rights;
    }

	public void setRights(ServiceRights rights)
    {
    	this.rights = rights;
    }
	
	/* Convenience method */
	public void setRight(AccessRight right, AccessType accessType)
	{
		getRights().addRight(right, accessType);
	}

	/* Convenience method: True: Success, False: Failed (i.e. invalid right or access type */
	public boolean setRight(String right, String accessType)
	{
		if (StringUtils.notEmpty(right) && StringUtils.notEmpty(accessType))
		{
			try
			{
				setRight(AccessRight.valueOf(right.toUpperCase()), AccessType.valueOf(accessType.toUpperCase()));
				return true;
			}
			catch (Exception ex)
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	@Override
    public String toString()
    {
	    return "ServiceInfo [serviceName=" + this.serviceName + ", serviceType=" + this.serviceType
	            + ", rights=" + this.rights + ", toString()=" + super.toString() + "]";
    }
}
