/*
 * SIFSessionUtils.java
 * Created: 11/03/2014
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

package sif3.infra.common.utils;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.ProvisionedZoneType;
import sif3.infra.common.model.RightType;

/**
 * This is a simple utility class to read and process the Service Information from an environment XML and store it in an easy accessible
 * structure in the SIF3 Session object. It is not expected that this class is used at higher levels of this framework.
 * 
 * @author Joerg Huber
 *
 */
public class SIFSessionUtils
{
    protected static final Logger logger = LoggerFactory.getLogger(SIFSessionUtils.class);

	/**
	 * This method populates the SIF3Session.services property with the values of the given environment. If there are no services
	 * defined in the environment or the environment is null then the "services" property in the sif3session remains untouched.
	 * 
	 * @param sif3Session The session o be updated with the services from the environment.
	 * @param environment The environment from where to extract the service information.
	 */
	public static synchronized void loadServiceInfoForSession(SIF3Session sif3Session, EnvironmentType environment)
	{
		if (environment != null)
		{
			// Get Default Zone
			if ((environment.getDefaultZone() != null) &&  StringUtils.notEmpty(environment.getDefaultZone().getId()))
			{
				sif3Session.setDefaultZone(new SIFZone(environment.getDefaultZone().getId().trim(), true));
			}
			
			// Load services & service rights
			if ((environment.getProvisionedZones() != null) && (environment.getProvisionedZones().getProvisionedZone() != null))
			{
				// Remove existing services and reload them.
				sif3Session.setServices(new ArrayList<ServiceInfo>());
				
				// Iterate through all services and add them to the session.
				for (ProvisionedZoneType zoneType : environment.getProvisionedZones().getProvisionedZone())
				{
					SIFZone zone = new SIFZone(zoneType.getId().trim());
					if (sif3Session.getDefaultZone() != null)
					{
						zone.setDefault(zone.getId().equals(sif3Session.getDefaultZone().getId())); // mark as default zone if ID = ID of Default Zone
					}
					
					if ((zoneType.getServices() != null) && (zoneType.getServices().getService() != null))
					{
						for (sif3.infra.common.model.ServiceType service : zoneType.getServices().getService())
						{
							ServiceType serviceType = ServiceType.OBJECT;
							boolean isDefaultCtx = false;
							
							if (StringUtils.notEmpty(service.getType()))
							{
								try
								{
									serviceType = ServiceType.valueOf(service.getType().trim().toUpperCase());
								}
								catch (Exception ex) // log error and assume it is OBJECT
								{
									logger.warn("The service '"+service.getName()+"' for environment '"+sif3Session.getEnvironmentName()+"' has an invalid 'type' of '"+service.getType().trim()+"' set. Valid values are: OBJECT, FUNCTION, UTILITY, SERVICEPATH and XQUERYTEMPLATE.");
									serviceType = ServiceType.OBJECT;
								}
							}
							if (StringUtils.isEmpty(service.getContextId())) // no context provided then this service is valid for default context
							{
								isDefaultCtx = true;
							}
							else
							{
								isDefaultCtx = CommonConstants.DEFAULT_CONTEXT_NAME.equalsIgnoreCase(service.getContextId().trim());
							}
							
							ServiceInfo serviceInfo = new ServiceInfo(service.getName().trim(), serviceType);
							serviceInfo.setContext(new SIFContext((isDefaultCtx) ? CommonConstants.DEFAULT_CONTEXT_NAME : service.getContextId().trim(), isDefaultCtx));
							serviceInfo.setZone(zone);
							
							// Read all the access rights
							if ((service.getRights() != null) && (service.getRights().getRight() != null))
							{
								for (RightType right : service.getRights().getRight())
								{
									if (!serviceInfo.setRight(right.getType(), right.getValue()))
									{
										logger.error("The service '"+service.getName()+"' for environment '"+sif3Session.getEnvironmentName()+"' has an invalid Acces Right type or value: Type = '"+ right.getType() +"' Value = '"+right.getValue()+"'. Contact your environment provider for details.");	
									}
								}
							}
							sif3Session.getServices().add(serviceInfo);
						}
					}
				}
			}
		}
		else
		{
			logger.warn("The environment for session '"+sif3Session.getEnvironmentName()+" is null. Cannot populate services for this session.");			
		}
	}

}
