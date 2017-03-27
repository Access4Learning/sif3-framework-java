/*
 * SIF3Session.java
 * Created: 30/01/2014
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sif3.common.CommonConstants;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import au.com.systemic.framework.utils.StringUtils;

/**
 * POJO to encapsulate SIF3 Session Information and configuration.
 * 
 * @author Joerg Huber
 *
 */
public class SIF3Session extends EnvironmentKey implements Serializable
{
	private static final long serialVersionUID = 4255199616263324174L;
    
	private long sessionID;
	private String adapterName;
	private String password;
	private String sessionToken;
	private String environmentID;
	private String fingerprint;
    private String adapterType; //CONSUMER, PROVIDER, ENVIRONMENT_PROVIDER
	private String securityToken;
	private Date securityTokenExpiry;
	private String environmentXML;
	private String queueStrategy = CommonConstants.QueueStrategy.ADAPTER_LEVEL.name();
	private Date created;
	private Date lastAccessed;
	
	// The properties below are runtime properties. They are not read or maintained in the DB!
	private transient SIFZone defaultZone             = null;
	private transient AuthenticationMethod authenticationMethod = AuthenticationMethod.Basic;
	
	// List of all services according to the ACL of this session.
    private transient ArrayList<ServiceInfo> services = new ArrayList<ServiceInfo>();

	public SIF3Session() {}
	
	public SIF3Session(EnvironmentKey environmenKey)
	{
		super(environmenKey);
	}
	
	public long getSessionID()
    {
    	return this.sessionID;
    }
	
	public void setSessionID(long sessionID)
    {
    	this.sessionID = sessionID;
    }
	
	public String getAdapterName()
    {
    	return this.adapterName;
    }
	
	public void setAdapterName(String adapterName)
	{
		this.adapterName = StringUtils.isEmpty(adapterName) ? null : adapterName;
	}
		
	public String getPassword()
    {
    	return this.password;
    }

	public void setPassword(String password)
    {
    	this.password = password;
    }

	public String getSessionToken()
    {
    	return this.sessionToken;
    }
	
	public void setSessionToken(String sessionToken)
    {
    	this.sessionToken = sessionToken;
    }
	
	public String getEnvironmentID()
    {
    	return this.environmentID;
    }
	
	public void setEnvironmentID(String environmentID)
    {
    	this.environmentID = environmentID;
    }
	
    public String getFingerprint()
    {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint)
    {
        this.fingerprint = fingerprint;
    }

	public String getSecurityToken()
	{
		return securityToken;
	}

	public void setSecurityToken(String securityToken)
	{
		this.securityToken = securityToken;
	}

	public Date getSecurityTokenExpiry()
	{
		return securityTokenExpiry;
	}

	public void setSecurityTokenExpiry(Date securityTokenExpiry)
	{
		this.securityTokenExpiry = securityTokenExpiry;
	}

	public String getAdapterType()
    {
    	return this.adapterType;
    }
	
	public void setAdapterType(String adapterType)
    {
    	this.adapterType = adapterType;
    }
	
	public String getEnvironmentXML()
    {
    	return this.environmentXML;
    }
	
	public void setEnvironmentXML(String environmentXML)
    {
    	this.environmentXML = environmentXML;
    }
	
	public String getQueueStrategy()
    {
    	return this.queueStrategy;
    }

	/**
	 * Will default to ADAPTER_LEVEL if set to null or empty.
	 * @param queueStrategy
	 */
	public void setQueueStrategy(String queueStrategy)
    {
		this.queueStrategy = StringUtils.isEmpty(queueStrategy) ? CommonConstants.QueueStrategy.ADAPTER_LEVEL.name() : queueStrategy;
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
	
    public AuthenticationMethod getAuthenticationMethod()
    {
        return authenticationMethod;
    }

    /**
     * Will default to BASIC if set to null.
     * 
     * @param authenticationMethod the authenticationMethod to set.
     */
    public void setAuthenticationMethod(AuthenticationMethod authenticationMethod)
    {
        this.authenticationMethod = (authenticationMethod == null) ? AuthenticationMethod.Basic : authenticationMethod;
    }
    
    public void setAuthenticationMethod(String authenticationMethod)
    {
        this.authenticationMethod = StringUtils.isEmpty(authenticationMethod) ? AuthenticationMethod.Basic : AuthenticationMethod.valueOf(authenticationMethod);
    }
	
	/*---------------------------------------------------------------------------------------------------------------------------------------*/
	/*-- This section has utility methods that are only available at runtime and when a SIF3 Session is loaded into memory and linked with --*/
	/*-- an environment given by the environment provider.                                                                                 --*/
    /*---------------------------------------------------------------------------------------------------------------------------------------*/
	
	/**
	 * List of services available for this environment. This are OBJECT, SERVICEPATH, UTILITY and FUNCTIONal services. Each service also has an
	 * assigned zone and context with it, both of which can be omitted. If they are omitted then the default zone and context are assumed
	 * as per SIF3 specification.
	 * 
	 * @return See desc.
	 */
    public ArrayList<ServiceInfo> getServices()
    {
    	return this.services;
    }

	public void setServices(ArrayList<ServiceInfo> services)
    {
    	this.services = services;
    }

	public SIFZone getDefaultZone()
	{
		return this.defaultZone;
	}

	public void setDefaultZone(SIFZone defaultZone)
	{
		this.defaultZone = defaultZone;
	}

	/**
	 * Environments to not really have a name but somehow we could create one based on the environment information. This
	 * should mainly be used for display, debug etc purposes but nothing else.
	 * 
	 * @return A nice name for the environment.
	 */
	public String getEnvironmentName()
	{
		return getAdapterName();
	}
  
	/**
	 * This method checks if this environment has a service with the given name for which the given zone and context 
	 * is defined and has the given access right and access type. If all these criteria are met then true is returned 
	 * otherwise false is returned.
	 * 
	 * @param right The access right (QUERY, UPDATE etc) that shall be checked for.
	 * @param accessType The access level (SUPPORTED, APPROVED, etc) that must be met for the given service and right.
	 * @param serviceName Service for which the access rights shall be checked.
	 * @param zone The Zone for which the service is valid and for which the access rights shall be checked. This can be 
	 *             null and would indicate the default zone.
	 * @param context The context for which the service is valid and for which the access rights shall be checked. This
	 *                can be null and would indicate the default context.
	 * 
	 * @return See desc
	 */
	public boolean hasAccess(AccessRight right, AccessType accessType, String serviceName, SIFZone zone, SIFContext context)
	{
		boolean accessApproved = false;
		for (ServiceInfo serviceInfo : getServices())
		{
			if (serviceInfo.getServiceName().equals(serviceName)) //service name matches
			{
				//Check if Zone matches
				boolean zoneMatches = (zone == null) ? serviceInfo.getZone().getIsDefault() : zone.getId().equals(serviceInfo.getZone().getId());
				
				//check if context matches as well
				boolean contextMatches = (context == null) ? serviceInfo.getContext().getIsDefault() : context.getId().equals(serviceInfo.getContext().getId());

				// Check if access right is the correct level
				if (zoneMatches && contextMatches)
				{
					accessApproved = serviceInfo.getRights().hasRight(right, accessType);
				}
			}
		}
		return accessApproved;
	}

	/**
	 * This method returns all services (zone, context, access rights etc) that are known for the given serviceName and type. A specific 
	 * service might be available in more than one zone and/or contexts. If the serviceName and type have no known entry in all existing
	 * services then an empty list is returned. The serviceName can include service paths that end in that name.
	 * 
	 * @param serviceName The serviceName to check for. MUST NOT BE NULL! This is the actual service name meaning for service paths it
	 *                    must be the last segment of the URL (i.e. schools/{}/students => Service Name = 'students'!)
	 * @param serviceType The serviceType to check for. MUST NOT BE NULL!
	 * 
	 * @return See desc.
	 */
	public List<ServiceInfo> getServiceInfoForService(String serviceName, ServiceType serviceType)
	{
		ArrayList<ServiceInfo> validServices = new ArrayList<ServiceInfo>();
		if (getServices() != null)
		{
			for (ServiceInfo serviceInfo : getServices())
			{
//				if (serviceInfo.getServiceName().equals(serviceName) && (serviceInfo.getServiceType() == serviceType))
				if (checkServiceNameAndType(serviceInfo, serviceName, serviceType))
				{
					validServices.add(serviceInfo);
				}
			}
		}
		return validServices;
	}
	
  /**
   * This method returns all services (zone, context, access rights etc) that are known for the given serviceName and type for the specified
   * access right and access type. A specific service might be available in more than one zone and/or contexts. If the serviceName and type 
   * have no known entry in all existing services then an empty list is returned. The serviceName can include service paths that end in that name.
   * 
   * @param serviceName The serviceName to check for. MUST NOT BE NULL! This is the actual service name meaning for service paths it
   *                    must be the last segment of the URL (i.e. schools/{}/students => Service Name = 'students'!)
   * @param serviceType The serviceType to check for. MUST NOT BE NULL!
   * @param right The The access right requires (i.e. CREATE, SUBSCRIPE etc). MUST NOT BE NULL!
   * @param accessType The access type required (i.e. APPROVED, REJECTED etc). MUST NOT BE NULL!
   * 
   * @return See desc.
   */
	public List<ServiceInfo> getServiceInfoForService(String serviceName, ServiceType serviceType, AccessRight right, AccessType accessType)
	{
		ArrayList<ServiceInfo> validServices = new ArrayList<ServiceInfo>();
		if (getServices() != null)
		{
			for (ServiceInfo serviceInfo : getServices())
			{
//				if (serviceInfo.getServiceName().equals(serviceName) && (serviceInfo.getServiceType() == serviceType))
				if (checkServiceNameAndType(serviceInfo, serviceName, serviceType))
				{
					if (serviceInfo.getRights().hasRight(right, accessType))
					{
						validServices.add(serviceInfo);
					}
				}
			}
		}
		return validServices;
	}
	
	/**
	 * This method returns the service info (zone, context, access rights etc) that matches the given criteria. There should only be one in each 
	 * environment. If no such service exists then null is returned.
	 * 
	 * @param zone  The zone to check for. If it is null it will be set to the DEFAULT zone/
	 * @param context  The context to check for. If it is null it will be set to the DEFAULT Context.
	 * @param serviceName The serviceName to check for. MUST NOT BE NULL! This is the RAW service name as listed in the environment ACL. In case of a
	 *                    service path that must be the full service path template (i.e. schools/{}/students). 
	 * @param serviceType The serviceType to check for. MUST NOT BE NULL!
	 * 
	 * @return See desc.
	 */
	public ServiceInfo getServiceInfoForService(SIFZone zone, SIFContext context, String serviceName, ServiceType serviceType)
	{
		if (getServices() != null)
		{
			for (ServiceInfo serviceInfo : getServices())
			{
				if ((serviceInfo.getServiceName().equals(serviceName) && 
				    (serviceInfo.getServiceType() == serviceType)) && 
				    (serviceInfo.getZone().getId().equals(getZone(zone).getId())) && 
				    (serviceInfo.getContext().getId().equals(getContext(context).getId())))
				{
						return serviceInfo;
				}
			}
		}
		return null; // not found
	}
	
    /*
     * This method checks if the given zone or zone id null and if so will return the default zone otherwise the passed in zone will be returned.
     */
    public SIFZone getZone(SIFZone zone)
    {
        return ((zone == null) || (zone.getId() == null)) ? getDefaultZone() : zone;
    }
    
    /*
     * This method checks if the given context or context id null and if so will return the default context otherwise the passed in context will be returned.
     */
    public SIFContext getContext(SIFContext context)
    {
        return ((context == null) || (context.getId() == null)) ? CommonConstants.DEFAULT_CONTEXT : context;
    }

	
	@Override
    public String toString()
    {
        return "SIF3Session [sessionID=" + sessionID + ", adapterName=" + adapterName
                + ", password=" + password + ", sessionToken=" + sessionToken + ", environmentID="
                + environmentID + ", fingerprint=" + fingerprint + ", adapterType=" + adapterType
                + ", securityToken=" + securityToken + ", securityTokenExpiry="
                + securityTokenExpiry + ", environmentXML=" + environmentXML + ", queueStrategy="
                + queueStrategy + ", created=" + created + ", lastAccessed=" + lastAccessed
                + ", defaultZone=" + defaultZone + ", authenticationMethod=" + authenticationMethod
                + ", services=" + services + ", toString()=" + super.toString() + "]";
    }	
    
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/

    /*
	 * This method checks if the given serviceInfo matches the serviceName and serviceType. Special care must be taken for servicePaths
	 * where the service name is only the 'ending' of the actual serviceName. Service Name of the serviceINfo would be SchoolInfo/{}/StudentPersonals 
	 * where the serviceName is StudentPersonals.
	 */
	private boolean checkServiceNameAndType(ServiceInfo serviceInfo, String serviceName, ServiceType serviceType)
	{
		if (serviceType == ServiceType.SERVICEPATH)
		{
			return (serviceInfo.getServiceName().endsWith("{}/"+serviceName) && (serviceInfo.getServiceType() == serviceType));
		}
		else // assume to only check for service name as is
		{
			return (serviceInfo.getServiceName().equals(serviceName) && (serviceInfo.getServiceType() == serviceType));
		}

	}
}
