/*
 * EnvironmentManager.java
 * Created: 15/03/2014
 *
 * Copyright 2014-2018 Systemic Pty Ltd
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

package sif3.infra.common.interfaces;

import java.util.Date;

import au.com.systemic.framework.utils.AdvancedProperties;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.persist.model.SIF3Session;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType;
import sif3.infra.common.model.JobType;

/**
 * This interface defines all methods required by this framework in relation to any environment managers (consumer, provider, direct,
 * brokered etc). Thise are the minimum functions. Some more specific interfaces for some environment managers may extend this
 * interface to define some more detailed functionality.
 * 
 * @author Joerg Huber
 *
 */
public interface EnvironmentManager
{	
	/**
	 * Returns the environment information (configuration) that goes with this environment manager. Note this is not the session store.
	 * The session store forms part of the environment manager. The environment information is just the underlying information
	 * that relates to environment this manager deals with. If there is no initialised environment configuration for this environment 
	 * manager then null is returned.
	 * 
	 * @return See desc.
	 */
	public EnvironmentInfo getEnvironmentInfo();
	
	/**
	 * Return the properties associated with this environment manager. This are the properties of the consumer or provider property 
	 * files with which the system is initialised.
	 * 
	 * @return See desc.
	 */
	public AdvancedProperties getServiceProperties();
	
	/**
	 * returns the adapter type for this environment manager.
	 * 
	 * @return See Desc.
	 */
	public AdapterType getAdapterType();
	
	/**
	 * This method should return an active, existing and already loaded, session from the session store for the given sessionToken.
	 * If there is no session in the session store for the given sessionToken then null must be returned. This method MUST NOT attempt
	 * to load the session from the environment store, just look it up in the already loaded sessions in the session store. This method
	 * shall be used if Basic or SIF_HMACSHA256 security is used.
	 * 
	 * @param sessionToken The sessionToken for which an already, active session shall be returned.
	 * 
	 * @return See desc.
	 */
	public SIF3Session getSessionBySessionToken(String sessionToken);
	
	/**
	 * This method should return an active, existing and already loaded, session from the session store for 
	 * the given securityToken. If there is no session in the session store for the given securityToken then
	 * null must be returned. This method MUST NOT attempt to load the session from the environment store,
	 * just look it up in the already loaded sessions in the session store. This method shall be used if 
	 * Bearer, custom security service, is used.
	 * 
	 * @param securityToken The securityToken for which an already, active session shall be returned.
	 * 
	 * @return See desc.
	 */
	public SIF3Session getSessionBySecurityToken(String securityToken);
	
	/**
	 * This method will set the security token and expire date for a given session in the session cache and
	 * session store. If the sessionToken doesn't exist then no action is taken and false is returned. If
	 * the underlying session store cannot be updated due to internal errors then false is returned and
	 * an error shall be logged.
	 * 
	 * @param sessionToken The sessionToken for which the security information shall be updated.
	 * @param securityToken The security token with witch the sessionToken shall be associated.
	 * @param securityExpiryDate The expire date to be set for the security token. Can be null.
	 * 
	 * @return TRUE if session is successfully be updated. FALSE otherwise and an error shall be logged.
	 */
	public boolean updateSessionSecurityInfo(String sessionToken, String securityToken, Date securityExpiryDate);

    /**
     * This method will attempt to load the job template from the underlying job template store.
     * If there is no job template for the given urlName or an error occurs accessing  the store then null is returned.
     *  
     * @param urlName The unique name of the job template as given in the job URL for which the template shall be
     *                retrieved. A job URL is generally of the form .../job/<uniqueJobName>/... The uniqueJobName is what the
     *                URL name refers to and is the same value as in the SIF3_JOB_TEMPLATE table in the column JOB_URL_NAME.
     * 
     * @return See desc.
     */
    public JobType getJobTemplate(String urlName);
    
    /**
     * Returns the Job Manager for this environment manager. The Job Manager abstracts a number of useful functions relating to
     * functional services and how they are managed in the data stores and event management.
     * 
     * @return See desc.
     */
    public JobManager getJobManager();
	
	/**
	 * This method must return the environment type of the given environment manager. Note for providers this must always be set. For
	 * consumer this is mostly irrelevant as they behave the same independent if it is a brokered or direct environment. For consumers
	 * this method my return null.
	 * 
	 * @return See desc.
	 */
	public EnvironmentType getEnvironmentType();
	
}
