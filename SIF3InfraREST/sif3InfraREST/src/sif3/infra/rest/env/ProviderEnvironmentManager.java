/*
 * ProviderEnvironmentManager.java Created: 18/09/2013
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

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MediaType;

import sif3.common.utils.AuthenticationUtils;
import sif3.common.utils.FileAndFolderUtils;
import sif3.infra.common.env.EnvironmentStore;
import sif3.infra.common.env.ProviderEnvironmentStoreOperations;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.env.types.EnvironmentList;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.model.EnvironmentType;

/**
 * This class deals with the main functions relating to the environments that need to be managed on a provider. It is a cut down
 * version of an Environment Provider and is required for DIRECT environments on the provider side. It deals with the main
 * functions of a environment provider as defined in the SIF3 spec. Only lower level classes in this framework really require
 * this class, mainly the resources for objects as well as the actual environment resource.
 * 
 * @author Joerg Huber
 */
public class ProviderEnvironmentManager extends BaseEnvironmentManager
{
	private ProviderEnvironmentStoreOperations envOps = null;

	/* Key=AuthToken for environment & consumer, Data: ProviderEnvironment */
	private HashMap<String, ConsumerEnvironment> environmentsByAuthToken = new HashMap<String, ConsumerEnvironment>();

	private static ProviderEnvironmentManager instance = null;

	/**
	 * Returns an instance of the Provider Environment Manager.
	 * 
	 * @param environmentStore The environment store for this instance of the Provider Manager.
	 * 
	 * @return See Desc.
	 */
	public static ProviderEnvironmentManager getInstance(EnvironmentStore environmentStore)
	{
		if (instance == null)
		{
			instance = new ProviderEnvironmentManager(environmentStore);
		}

		return instance;
	}

	/**
	 * Returns TRUE if the provider knows an environment for the given Authentication Token. If no environment is known for the
	 * given authentication then FALSE is returned.
	 * 
	 * @param authToken The token to check for.
	 * 
	 * @return See desc.
	 */
	public boolean existsEnvironmentForAuthToken(String authToken)
	{
		return (getConsumerEnvironmentByAuthToken(authToken) != null);
	}

	/**
	 * Returns the environment information for a consumer as known to the environment provider.
	 * 
	 * @param envName The name of the environment for which the information shall be returned.
	 * @param consumerID The consumer for which the environment shall be returned.
	 * @param useSecured Indicate if the environment to get should use secured (TRUE) service URIs (https://...) or
	 *                   unsecured URIs (http://...)
	 * 
	 * @return The environment or null if the given consumer has no environment known to the environment provider.
	 */
	public EnvironmentType getEnvironmentForConsumer(String envName, String consumerID, boolean useSecured)
	{
		return envOps.loadEnvironmentForConsumer(envName, consumerID, useSecured);
	}

	/**
	 * Checks if a environment is supported for a given consumer.
	 * 
	 * @param envName The name of the environment for which the check shall be performed.
	 * @param consumerID The consumer for which the environment shall be checked.
	 * @param logError Indicates if an error shall be logged if the environment is not supported for the consumer. 
	 * 
	 * @return TRUE: Environments is supported for the consumer. FALSE otherwise.
	 */
	public boolean isEnvironmentSupportedForConsumer(String envName, String consumerID, boolean logError)
	{
		return envOps.isEnvironmentSupportedForConsumer(envName, consumerID, logError);
	}
	
	/**
	 * This method will create an environment for a consumer. The environment will be created based on the given input environment
	 * information. If this operation fails an error is logged and null is returned. The environment will be created and all the
	 * information according to the SIF3 spec is returned (i.e. Session Token, Environment Id etc).
	 * 
	 * @param inputEnvironment The basic information required by the environment provider to create the environment for the consumer.
	 * @param userName Initial user name for environment creation.
	 * @param password Initial password  for environment creation. Is used to create the new AuthenticationToken.
	 * @param mediaType Indicates if the consumer is using XML or JSON.
	 * @param useSecured Indicate if the environment to load should use secured (TRUE) service URIs (https://...) or
	 *                   unsecured URIs (http://...)
	 * 
	 * @return See desc.
	 */
	public EnvironmentType createEnvironment(EnvironmentType inputEnvironment, String userName, String password, MediaType mediaType, boolean useSecured)
	{
		EnvironmentType newEnv = envOps.createAndStoreEnvForConsumer(inputEnvironment, useSecured);
		if (newEnv != null)
		{
			ConsumerEnvironment consumerEnv = new ConsumerEnvironment(newEnv.getSolutionId(), newEnv.getConsumerName());
			consumerEnv.setEnvGUID(newEnv.getId());
			consumerEnv.setMediaType(mediaType);
			consumerEnv.setAdapterName(newEnv.getConsumerName());
			consumerEnv.setSessionToken(newEnv.getSessionToken());
			consumerEnv.setUserName(userName);
			consumerEnv.setPassword(password);
			consumerEnv.setAuthenticationToken(AuthenticationUtils.getBasicAuthToken(newEnv.getSessionToken(), password));

			loadServiceInfoInConsumer(consumerEnv, newEnv);

			// add to authtoken Store
			environmentsByAuthToken.put(consumerEnv.getAuthenticationToken(), consumerEnv);
		}
		return newEnv;
	}

	/**
	 * This method will load an environment from the environment store. This is used to get an
	 * environment rather than creating one. If no environment for the given authentication token
	 * exists in the environment store then null is returned.
	 * 
	 * @param authToken The authorisation token based on which the environment shall be returned.
	 * @param useSecured Indicate if the environment to load should use secured (TRUE) service URIs
	 *                   (https://...) or unsecured URIs (http://...)
	 * 
	 * @return See desc.
	 */
	public EnvironmentType loadEnvironmentByAuthToken(String authToken, boolean useSecured)
	{
		ConsumerEnvironment consumerEnvironment = getConsumerEnvironmentByAuthToken(authToken);
		if (consumerEnvironment != null)
		{
			return envOps.loadEnvironmentForConsumer(consumerEnvironment.getEnvironmentName(), consumerEnvironment.getAdapterName(), useSecured);
		}
		else
		{
			logger.error("Cannot load environment for given Authorisation Token.");
			return null;
		}
	}
	
	/**
	 * This method will remove an environment identified by the authentication token permanently from the environment store and 
	 * provider.
	 * 
	 * @param authToken The authentication token for which the environment shall be removed.
	 * 
	 * @return See desc.
	 */
	public boolean removeEnvironmentByAuthToken(String authToken)
	{
		ConsumerEnvironment consumerEnvironment = getConsumerEnvironmentByAuthToken(authToken);
		if (consumerEnvironment != null)
		{
			// First we remove it from the Environment store
			if (envOps.deleteEnvironmentForConsumer(consumerEnvironment.getEnvironmentName(), consumerEnvironment.getAdapterName()))
			{
				environmentsByAuthToken.remove(authToken);
				return true;
			}
			else
			{
				return false; // error already logged.
			}
		}
		else
		{
			logger.warn("There is no environment for given Authorisation Token. Cannot delete. No action taken");
			return true;
		}
	}
	
	/**
	 * Returns the environment for the given Authentication Token. If no environment is known for the given authentication then 
	 * null is returned.
	 * 
	 * @param authToken The token to return the environment for.
	 * 
	 * @return See desc.
	 */
	public ConsumerEnvironment getConsumerEnvironmentByAuthToken(String authToken)
	{
		if (authToken != null)
		{
			return environmentsByAuthToken.get(authToken);
		}
		return null;
	}

	@Override
	public String toString()
	{
		return "ProviderEnvironmentManager [environmentsByAuthToken=" + environmentsByAuthToken + "]";
	}

	/*---------------------*/
	/*-- Private methods --*/
	/*---------------------*/
	private ProviderEnvironmentManager(EnvironmentStore environmentStore)
	{
		super(environmentStore);
		this.envOps = new ProviderEnvironmentStoreOperations(environmentStore);
		
		// At this point in time it doesn't matter if the URIs will be secure or unsecured.
		loadKnownEnvironments();
	}

	@SuppressWarnings("unchecked")
	private boolean loadKnownEnvironments()
	{
		boolean errorFound = false;
		logger.debug("Load all environments for Provider: " + envOps.getEnvironmentStore().getAdapterFileNameWithoutExt());
		EnvironmentList<ProviderEnvironment> providerEnvs = (EnvironmentList<ProviderEnvironment>) envOps.getEnvironmentStore().getEnvironments();
		for (ProviderEnvironment environment : providerEnvs.getEnvironments().values())
		{
			logger.debug("Load consumer environments for " + environment.getEnvironmentName());
			// Get list of valid specific consumers for the given environment and attempt to load their existing environment data
			for (String consumerID : environment.getConsumers().keySet())
			{
				logger.debug("Attempt to load " + environment.getEnvironmentName() + " for specific consumer " + consumerID);
				if (envOps.existEnvironmentForConsumer(environment.getEnvironmentName(), consumerID))
				{
					// At this point of time it doesn't matter if the URIs are unsecured or secured. Call method with 'false'
					EnvironmentType consumerEnvironment = envOps.loadEnvironmentForConsumer(environment.getEnvironmentName(), consumerID, false);
					if (consumerEnvironment != null)
					{
						logger.debug("Loaded " + environment.getEnvironmentName() + " for specific consumer " + consumerID);
						ConsumerEnvironment envInfo = environment.getConsumer(consumerID);

						// update consumer env with the actual environment information and put it into the authToken Map.
						populateBasicEnvInfoFromEnvironment(envInfo, consumerEnvironment);
						
						loadServiceInfoInConsumer(envInfo, consumerEnvironment);

						environmentsByAuthToken.put(envInfo.getAuthenticationToken(), envInfo);
					}
					else
					{
						logger.error("Unable to load  Environment " + environment.getEnvironmentName() + " for consumer " + consumerID);
						errorFound = true;
					}
				}
				else
				{
					logger.debug(environment.getEnvironmentName() + " for specific consumer " + consumerID + " does not exist.");
				}
			}

			// Get all 'any' consumers for the given environment and attempt to load their existing environment data
			if (environment.getAllowAny())
			{
				// Figure out all the names of the sub-directories of 'any'. These directories are the names of the consumers. Now check if the
				// consumers have the given environment and load it.
				List<String> subdirs = FileAndFolderUtils.getSubDirectoriesOf(envOps.getEnvironmentStore().getAnyDirName());
				if (logger.isDebugEnabled())
				{
					logger.debug("Subdirs (=ConsumerIDs) of " + envOps.getEnvironmentStore().getAnyDirName() + ": " + subdirs);
				}
				if (subdirs != null)
				{
					// only load valid environments. There might be old invalid env laying around. Ignore those.
					for (String consumerID : subdirs)
					{
						if (envOps.existEnvironmentForAnyConsumer(environment.getEnvironmentName(), consumerID))
						{
							logger.debug("Attempt to load " + environment.getEnvironmentName() + " for 'any' consumer " + consumerID);

							// At this point of time it doesn't matter if the URIs are unsecured or secured. Call method with 'false'.
							EnvironmentType consumerEnvironment = envOps.loadEnvironmentForConsumer(environment.getEnvironmentName(), consumerID, false);
							if (consumerEnvironment != null)
							{
								logger.debug("Loaded " + environment.getEnvironmentName() + " for 'any' consumer " + consumerID);
								ConsumerEnvironment envInfo = new ConsumerEnvironment(environment.getEnvironmentName(), consumerID);
								envInfo.setMediaType(environment.getMediaType());
								envInfo.setPassword(environment.getAllowAnyPassword());
								envInfo.setUserName(environment.getAllowAnyUserName());

								// update consumer env with the actual environment information and put it into the authToken Map.
								populateBasicEnvInfoFromEnvironment(envInfo, consumerEnvironment);
								
								loadServiceInfoInConsumer(envInfo, consumerEnvironment);

								environmentsByAuthToken.put(envInfo.getAuthenticationToken(),envInfo);
							}
							else
							{
								logger.error("Unable to load  Environment " + environment.getEnvironmentName() + " for consumer " + consumerID + " under the 'any' directory.");
								errorFound = true;
							}
						}
						else
						{
							logger.debug(environment.getEnvironmentName() + " for 'any' consumer " + consumerID + " does not exist.");
						}
					}
				}
			}
		}

		if (errorFound)
		{
			logger.error("Not all environments for all consumers have been loaded successfully. See previous error log entries for details.");
		}
		return !errorFound;
	}
	

}
