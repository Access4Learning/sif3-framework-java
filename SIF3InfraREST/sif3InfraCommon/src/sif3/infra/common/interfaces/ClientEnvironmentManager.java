/*
 * ClientEnvironmentManager.java 
 * Created: 14/03/2014
 * 
 * Copyright 2014 Systemic Pty Ltd
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
package sif3.infra.common.interfaces;

import sif3.common.exception.PersistenceException;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.security.TokenInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.infra.common.model.EnvironmentType;

/**
 * Defines the interface for a Client Environment Manager. A client environment Manager can be for a consumer of a provider in a
 * brokered environment.
 * 
 * @author Joerg Huber
 *
 */

public interface ClientEnvironmentManager extends EnvironmentManager
{
	/**
	 * Returns the currently active session if there is one. If there is no session then null is returned. This method should not
	 * attempt to load a session from the environment store. It shall simply lookup if the client has an already active session in
	 * the session store.
	 * 
	 * @return See Desc.
	 */
	public SIF3Session getSIF3Session();
    
  /**
   * Load the template for this environment and return it. If there is no template for this environment then null is returned.
   * 
   * @return See desc.
   */
  public EnvironmentType loadTemplateEnvironmentData();
  

  /**
   * This method loads the environment from the workstore into the session store. If it exists then the environment is returned. 
   * If it doesn't exist then null is returned. Note if it doesn't exist it WON'T create it. To create the environment the 
   * 'createEnvironment(...)'  method in this class must be called.
   * 
   * @param environmentKey solutionID: Mandatory, applicationKey: Mandatory, userToken: Optional, instanceID: Optional
   * 
   * @return see Desc.
   * 
   * @throws IllegalArgumentException Any of the mandatory parameters is null or empty.
   * @throws PersistenceException Could not access the underlying workstore.
   */
  public EnvironmentType reloadSessionFromFromWorkstore(EnvironmentKey environmentKey) throws IllegalArgumentException, PersistenceException;
  
  /**
  * This method will create or update an environment for the this client in the workstore and the session store. The environment
  * will be created/updated based on the given input environment information. If this operation fails an error is logged and 
  * null is returned. This method expects that the inputEnvironment is a full environment as returned from the environment 
  * provider including sessionToken, environmentID etc. The stored environment is then returned.
  * 
  * @param inputEnvironment full environment as returned from the environment provider including sessionToken, environmentID
  * @param tokenInfo Information related to the security token. Can be used to store expire date and a security token related to the
  *                  session to be created or updated.
  * 
  * @return See desc.
  */
  public EnvironmentType createOrUpdateEnvironment(EnvironmentType inputEnvironment, TokenInfo tokenInfo);
  
  /**
   * This method will only disconnect the current session but it won't remove the environment in the environment store. 
   * The consumer can reconnect to the same environment again with a call to reloadSessionFromFromWorkstore() method and 
   * resume where it left of.
   */
  public void disconnect();
  
  /**
   * This method will remove an environment identified by the environmentKey from the environment store. Note this must be 
   * done with care. Once an environment is permanently removed a consumer can no longer reconnect to this environment. It is
   * permanently lost and a new environment must be created first before operations can continue. With a removed environment
   * all associated queues and outstanding events and delayed response will be lost or are no longer recoverable.
   * 
   * @param environmentKey solutionID Mandatory, applicationKey Mandatory, userToken Optional, instanceID Optional:
   * 
   * @return TRUE: Environment is removed from the works store and the session store. FALSE: Failed to remove environment
   */
  public boolean removeEnvironment(EnvironmentKey environmentKey);
}
