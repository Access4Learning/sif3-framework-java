/*
 * BaseEnvironmentManager.java Created: 23/09/2013
 * 
 * Copyright 2013 Systemic Pty Ltd
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
package sif3.infra.rest.env;

import org.apache.log4j.Logger;

import au.com.systemic.framework.utils.StringUtils;

import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.utils.AuthenticationUtils;
import sif3.infra.common.env.EnvironmentStore;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.env.types.ServiceInfo;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.ProvisionedZoneType;

/**
 * The provider and the consumer must do some environment management. This class holds the common functionality for each of those
 * environment managers. It also serves as a collection of utility methods for the two style of environment managers.
 * 
 * @author Joerg Huber
 *
 */
public class BaseEnvironmentManager
{
	protected final Logger logger = Logger.getLogger(getClass());
	
	/* Fixed name for Default Zone and Context */
	protected static final String DEFAULT = "DEFAULT";

	private EnvironmentStore environmentStore = null;

	public BaseEnvironmentManager(EnvironmentStore environmentStore)
	{
		super();
		this.environmentStore = environmentStore;
	}

	/**
	 * Returns the Environment Store associated with this environment manager.
	 * 
	 * @return See desc.
	 */
	public EnvironmentStore getEnvironmentStore()
	{
		return this.environmentStore;
	}

	protected boolean populateBasicEnvInfoFromEnvironment(ConsumerEnvironment envInfo, EnvironmentType env)
	{
		envInfo.setEnvGUID(env.getId());
		envInfo.setSessionToken(env.getSessionToken());
		if (AuthenticationUtils.AUTHENTICATION_METHOD.Basic.toString().equals(env.getAuthenticationMethod()))
		{
			envInfo.setAuthenticationToken(AuthenticationUtils.getBasicAuthToken(envInfo.getSessionToken(), envInfo.getPassword()));
		}
		else
		{
			logger.error("Only Basic authentication is currently supported. Failed to connect to environment " + envInfo.getEnvironmentName() + " because Authentication Mehod is set to " + env.getAuthenticationMethod());
			return false;
		}
		return true;
	}
	
	protected void loadServiceInfoInConsumer(ConsumerEnvironment envInfo, EnvironmentType env)
	{
		// Get Default Zone
		if ((env.getDefaultZone() != null) &&  StringUtils.notEmpty(env.getDefaultZone().getId()))
		{
			envInfo.setDefaultZone(new SIFZone(env.getDefaultZone().getId().trim(), true));
		}
		
		// Load services
		if ((env.getProvisionedZones() != null) && (env.getProvisionedZones().getProvisionedZone() != null))
		{
			for (ProvisionedZoneType zoneType : env.getProvisionedZones().getProvisionedZone())
			{
				SIFZone zone = new SIFZone(zoneType.getId().trim());
				if (envInfo.getDefaultZone() != null)
				{
					zone.setDefault(zone.getId().equals(envInfo.getDefaultZone().getId())); // mark as default zone if ID = ID of Default Zone
				}
				
				if ((zoneType.getServices() != null) && (zoneType.getServices().getService() != null))
				{
					for (sif3.infra.common.model.ServiceType service : zoneType.getServices().getService())
					{
						ServiceInfo.ServiceType serviceType = ServiceInfo.ServiceType.OBJECT;
						boolean isDefaultCtx = false;
						
						if (StringUtils.notEmpty(service.getType()))
						{
							try
							{
								serviceType = ServiceInfo.ServiceType.valueOf(service.getType().trim().toUpperCase());
							}
							catch (Exception ex) // log error and assume it is OBJECT
							{
								logger.warn("The service '"+service.getName()+"' for environment '"+envInfo.getEnvironmentName()+"' has an invalid 'type' of '"+service.getType().trim()+"' set. Valid values are: OBJECT, UTILITY and FUNCTION.");
								serviceType = ServiceInfo.ServiceType.OBJECT;
							}
						}
						if (StringUtils.isEmpty(service.getContextId())) // no context provided then this service is valid for default context
						{
							isDefaultCtx = true;
						}
						else
						{
							isDefaultCtx = DEFAULT.equalsIgnoreCase(service.getContextId().trim());
						}
						
						ServiceInfo serviceInfo = new ServiceInfo(service.getName().trim(), serviceType);
						serviceInfo.setContext(new SIFContext((isDefaultCtx) ? DEFAULT : service.getContextId().trim(), isDefaultCtx));
						serviceInfo.setZone(zone);
						serviceInfo.setEnvironmentName(envInfo.getEnvironmentName());
						envInfo.getServices().add(serviceInfo);
					}
				}
			}
		}
	}

}
