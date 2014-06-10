/*
 * ClientEnvStoreOperations.java
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

import sif3.common.model.EnvironmentKey;
import sif3.common.persist.model.SIF3Session;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.model.EnvironmentType;
import au.com.systemic.framework.utils.AdvancedProperties;


/**
 * Defines the interface for a Client Environment OPeration. A client environment operations are applicable for a consumer of a provider 
 * in a brokered environment.
 * 
 * @author Joerg Huber
 *
 */
public interface ClientEnvStoreOperations
{
	/**
	 * Load a session/environment from environment store for the given environment key. This will only attempt to load the environment
	 * from the environment store but won't create it if it doesn't exist. If it doesn't exist then null is returned. Null is also
	 * returned if there is an issue accessing the underlying environment store. In that case an error shall also be logged.
	 * 
	 * @param environmentKey solutionID: Mandatory, applicationKey: Mandatory, userToken: Optional, instanceID: Optional 
	 * 
	 * @return See desc.
	 */
	public SIF3Session loadSession(EnvironmentKey environmentKey);
	
	/**
	 * This method will create or update an environment in the consumer environment store with data from the given inputEnv. 
	 * If there is already an environment in the environment store for the given inputEnvironment then the environment store is 
	 * updated, otherwise the environment will be created in the environment store. If this operation fails an error is logged 
	 * and null is returned. This method expects that the inputEnvironment is a full environment as returned from the environment 
	 * provider including sessionToken, environmentID etc. The Session Information is then returned to the caller, and the 
	 * environment is stored as an XML in the environmentXML property.
	 * 
	 * @param inputEnv full environment as returned from the environment provider including sessionToken, environmentID
	 * 
	 * @return See desc.
	 */
	public SIF3Session createOrUpdateSession(EnvironmentType inputEnv);

	/**
	 * This removes the environment data stored in the environment store for the given sessionToken. This operation should be used 
	 * with care! A deletion of an environment means that it is no longer recoverable and a consumer/provider can no longer connect 
	 * to the environment because the information relating to that environment are lost for good! A loss of an environment also means
	 * a loss of all data that relate to an environment such as SIF events, responses etc. All things that are potentially 
	 * stored in queues.
	 * 
	 * @param sessionToken Uniquely identifies the environment to be removed.
	 * 
	 * @return TRUE: Operation successful. FALSE: Error occurred. See error log for details.
	 */
	public boolean removeEnvFromWorkstoreBySessionToken(String sessionToken);

	/**
	 * Returns the environment information (configuration) that goes with this environment setup. The environment information is just the 
	 * underlying information that relates to environment properties file that relates to this class.
	 * 
	 * @return See desc.
	 */
	public EnvironmentInfo getEnvironmentInfo();
	
	/**
	 * Return the properties associated with this environment. This are the properties of the consumer or provider property 
	 * files with which the system is initialised.
	 * 
	 * @return See desc.
	 */
	public AdvancedProperties getServiceProperties();

}
