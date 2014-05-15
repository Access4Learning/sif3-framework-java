/*
 * EnvironmentManager.java
 * Created: 15/03/2014
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

package sif3.infra.common.interfaces;

import sif3.common.persist.model.SIF3Session;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType;
import au.com.systemic.framework.utils.AdvancedProperties;

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
	 * This method should return an active, existing and already loaded, session from the session store for the given sessionToken.
	 * If there is no session in the session store for the given sessionTToken then null must be returned. This method MUST NOT attempt
	 * to load the session from the environment store, just look it up in the already loaded sessions in the session store.
	 * 
	 * @param sessionToken The sessionToken for which an already, active session shall be returned.
	 * 
	 * @return See desc.
	 */
	public SIF3Session getSessionBySessionToken(String sessionToken);
	
	/**
	 * This method must return the environment type of the given environment manager. Note for providers this must always be set. For
	 * consumer this is mostly irrelevant as they behave the same independent if it is a brokered or direct environment. For consumers
	 * this method my return null.
	 * 
	 * @return See desc.
	 */
	public EnvironmentType getEnvironmentType();
	
}
