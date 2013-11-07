/*
 * ProviderEnvironmentStoreOperations.java
 * Created: 17/09/2013
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

package sif3.infra.common.env;

import java.util.List;

import au.com.systemic.framework.utils.StringUtils;

import sif3.common.utils.FileAndFolderUtils;
import sif3.common.utils.UUIDGenerator;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.InfrastructureServicesType;
import sif3.infra.common.model.PropertyType;

/**
 * Utility class to specifically deal with operations on provider environments and its store. All of the functions are used on the 
 * lower levels of this framework and are not expected to be exposed to higher levels where a developer will operate on.
 * 
 * @author Joerg Huber
 *
 */
public class ProviderEnvironmentStoreOperations extends BaseEnvironmentStoreOperations
{
	public ProviderEnvironmentStoreOperations(EnvironmentStore environmentStore)
	{
		super(environmentStore);
	}
	
	/**
	 * Checks if the environment exists in the template directory for the provider.
	 * 
	 * @param envName Name of environment to check for.
	 * 
	 * @return TRUE if XML for the envName exists in the template directory. FALSE otherwise 
	 */
	public boolean existEnvironmentTemplate(String envName)
	{
		return FileAndFolderUtils.doesFileExist(getFullFileName(getEnvironmentStore().getFullTemplateDirName(), envName));
	}

	/**
	 * Check of an environment does already exist in the environment store for the given consumer. TRUE: it exists, FALSE it doesn't.
	 * 
	 * @param envName The name of the environment to check for.
	 * @param consumerID The ID of the consumer for which the check shall be performed.
	 * 
	 * @return TRUE: Environment already exists. FALSE: The environment for the given consumer doen't exists.
	 */
	public boolean existEnvironmentForConsumer(String envName, String consumerID)
	{
		return FileAndFolderUtils.doesFileExist(getFullFileName(getEnvironmentStore().getFullWorkDirName()+consumerID+"/", envName));
	}
	
	/**
	 * If 'Any' is allowed in the environment then the consumer may have an environment in the 'any' environment store location. This
	 * method checks if this is the case.
	 * 
   * @param envName The name of the environment to check for.
   * @param consumerID The ID of the consumer for which the check shall be performed.
   * 
   * @return TRUE: Environment already exists. FALSE: The environment for the given consumer doen't exists.
	 */
	public boolean existEnvironmentForAnyConsumer(String envName, String consumerID)
	{
		return FileAndFolderUtils.doesFileExist(getFullFileName(getEnvironmentStore().getAnyDirName()+consumerID+"/", envName));
	}

	/**
	 * Before a new environment can be created it must be read from a template. This method loads the environment from the template 
	 * directory. If no such environment exists then null is returned.
	 * 
	 * @param envName The name of the environement (file name) to be located and loaded form the template directory.
	 * 
	 * @return See desc.
	 */
	public EnvironmentType loadEnvironmentFromTemplate(String envName)
	{
		return loadEnvironmentData(getFullFileName(getEnvironmentStore().getFullTemplateDirName(), envName));
	}
	
	/**
	 * This method loads the environment for a given consumer. Before it is loaded it checks if it does already exist. If it doesn't then
	 * null is returned, otherwise the environment is returned. Note if it doesn't exist it WON'T create it. To create the environment
	 * from a temaplate then the 'createAndStoreEnvForConsumer(...)' method in this class must be called.
	 * 
   * @param envName The name of the environment to load.
   * @param consumerID The ID of the consumer for which the environment shall be loaded.
	 * 
	 * @return See desc.
	 */
	public EnvironmentType loadEnvironmentForConsumer(String envName, String consumerID)
	{
		if (environmentKnown(envName, true) && consumerKnown(envName, consumerID, true))
		{
			return loadEnvForConsumerNoChecks(envName, consumerID);
		}
		return null;
	}

	/**
	 * This method takes the inputEnv and uses its data to create a new full environment. The reminder of the environment data is read from
	 * the template directory (environment services and permissions). A SessionToken and Environment ID is also created before the final 
	 * environment is stored. The final environment data is then returned.
	 * If the environment cannot be created in the environment store then the error is logged and null is returned.<br/><br/>
	 * 
	 * NOTE:<br/>
	 * If an environment does already exist for the given consumer then that environment is returned and no new one is created.
	 * 
	 * @param inputEnv The environment as provided by a consumer. This is a cut-down version with minimal data as specified in the SIF3 spec.
	 */
	public EnvironmentType createAndStoreEnvForConsumer(EnvironmentType inputEnv)
	{
		if ((inputEnv == null) || (inputEnv.getApplicationInfo() == null))
		{
			logger.error("The consumer input environment is null or does not have the Application Info set. Environment cannot be created.");
			return null;
		}
    if (StringUtils.isEmpty(inputEnv.getSolutionId()) || StringUtils.isEmpty(inputEnv.getConsumerName()))
    {
      logger.error("The consumer name and/or the solution id in the input environment is null or empty. Environment cannot be created.");
      return null;
    }
    String envName = inputEnv.getSolutionId().trim();
    String consumerID = inputEnv.getConsumerName().trim();
    
		if (environmentKnown(envName, true) && consumerKnown(envName, consumerID, true))
		{
			EnvironmentType environment = loadEnvForConsumerNoChecks(envName, consumerID);
			if (environment != null) // environment for this consumer does already exist => return it as is.
			{
				logger.info("Environment "+getFullFileName(getOutputDirectoryForConsumer(envName, consumerID), envName)+" exists already. Simply return it and do not create it again.");
				return environment;
			}
			
			// If we get to this point then an environment does not yet exist. Read it from the template directory.
			environment = loadEnvironmentFromTemplate(envName);
			if (environment == null) // does not exist in template directory. we cannot create it.
			{
				return null; // error already logged.
			}
			
			// Ok we have loaded the environment from the template directory. Add the inputEnv stuff to it
			environment.setApplicationInfo(inputEnv.getApplicationInfo());
			environment.setConsumerName(inputEnv.getConsumerName());
			environment.setType(inputEnv.getType());
			
			// Create environmentID and sessionToken and environment Service
			environment.setId(UUIDGenerator.getUUID());
			environment.setSessionToken(UUIDGenerator.getUUIDWithoutDashes());
			if (!updateEnvironmentURL(environment))
			{
				return null; // error already logged
			}
			
			// Store the environment in the store.
			if (!storeEnvironmentData(getOutputDirectoryForConsumer(envName, consumerID), envName, environment))
			{
				environment = null; // error already logged
			}
			
			// finally return the environment
			return environment;
		}
		else
		{
			return null; //error already logged.
		}
	}
	
	/**
	 * Remove and environment for a consumer. It is checked if the environment exists and only then it is removed (physically deleted from
	 * the environment store!).
	 * 
   * @param envName The name of the environment to remove.
   * @param consumerID The ID of the consumer for which the environment shall be removed.
   * 
	 * @return TRUE: It existed and has been removed. FALSE: Didn't exist or could not be removed (see error.log for details).
	 */
	public boolean deleteEnvironmentForConsumer(String envName, String consumerID)
	{
		if (environmentKnown(envName, true) && consumerKnown(envName, consumerID, true))
		{
			return removeEnvironment(getOutputDirectoryForConsumer(envName, consumerID), envName);
		}
		else
		{
			return false; // error logged.
		}
	}
	
	/**
	 * This method checks if a consumer is allowed to access an environment of a given name.
	 * 
   * @param envName The name of the environment to check for.
   * @param consumerID The ID of the consumer for which the environment shall be checked.
	 * @param logError Indicate if the consumer has no access to an environment if that fact shall be logged.
	 * 
	 * @return TRUE: Consumer has access to environment. FALSE: Consumer has no access.
	 */
	public boolean isEnvironmentSupportedForConsumer(String envName, String consumerID, boolean logError)
	{
	  if (environmentKnown(envName, logError))
	  {
	    return consumerKnown(envName,consumerID, logError);
	  }
	  else
	  {
	    return false;
	  }
	}
	
	/*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
	private boolean allowAny(String envName)
	{
		return ((ProviderEnvironment)getEnvironmentStore().getEnvironments().getEnvironment(envName)).getAllowAny();
	}
	
	private boolean environmentKnown(String envName, boolean logError)
	{
		ProviderEnvironment env = (ProviderEnvironment)getEnvironmentStore().getEnvironments().getEnvironment(envName);
		if ((env == null) && (logError))
		{
			logger.error("Environment with name "+envName+" not listed in "+getEnvironmentStore().getAdapterFileNameWithoutExt()+".properties.");			
		}
		return env != null;
	}
	
	/* Consumer is known if allowAny=true or if it is listed as a consumer of the given environment */
	private boolean consumerKnown(String envName, String consumerID, boolean logError)
	{
		boolean allowed = allowAny(envName);
		if (!allowed) // check if it is specifically listed in the consumer list of the environment
		{
			return isSpecificConsumer(envName, consumerID, true);
		}
		return true;
	}
	
	private boolean isSpecificConsumer(String envName, String consumerID, boolean logError)
	{
		ProviderEnvironment env = (ProviderEnvironment)getEnvironmentStore().getEnvironments().getEnvironment(envName);
		if (env.getConsumer(consumerID) == null)
		{
			if (logError)
			{
				logger.error("The consumer with name "+consumerID+" is not allowed to access the environment "+envName+". If you want to enable it then modify the "+getEnvironmentStore().getAdapterFileNameWithoutExt()+".properties accordingly.");				
			}
			return false;
		}
		return true;
	}
	
	/*
	 *  Attempts to load the environment data for a given consumer. No checks are performed. It is assumed checks of environment and consumerID
	 * existence is already done.
	 */
	private EnvironmentType loadEnvForConsumerNoChecks(String envName, String consumerID)
	{
		EnvironmentType environment = null;
		
		// Check if file for specific consumer exist
		if (existEnvironmentForConsumer(envName, consumerID))
		{
			environment = loadEnvironmentData(getFullFileName(getEnvironmentStore().getFullWorkDirName()+consumerID+"/", envName));
		}
		else if (allowAny(envName))
		{
			if (existEnvironmentForAnyConsumer(envName, consumerID))
			{
				environment = loadEnvironmentData(getFullFileName(getEnvironmentStore().getAnyDirName()+consumerID+"/", envName));
			}
		}
		
		return environment;
	}
	
	private String getOutputDirectoryForConsumer(String envName, String consumerID)
	{
		if (isSpecificConsumer(envName, consumerID, false))
		{
			return getEnvironmentStore().getFullWorkDirName()+consumerID+"/";
		}
		else // must be 'allowAny'
		{
			return getEnvironmentStore().getAnyDirName()+consumerID+"/";
		}
	}
	
	private boolean updateEnvironmentURL(EnvironmentType environment)
	{
		InfrastructureServicesType infraServices = environment.getInfrastructureServices();
		if (infraServices != null)
		{
			// Search for service called "environment"
			List<PropertyType> services = infraServices.getInfrastructureService();
			for (PropertyType service : services)
			{
				if (service.getName().equals(ConsumerEnvironment.ConnectorName.environment.toString()))
				{
					service.setValue(service.getValue()+environment.getId());
					return true;
				}
			}
			// if I get here then no service with name environment was found
			logger.error("No Infrastructure service with the name environment found in environment "+environment.getSolutionId()+". This must be set.");
			return false;
		}
		else
		{
			logger.error("Infrastructure Services not defined in environment template for environment "+environment.getSolutionId()+". This must be set.");
			return false;
		}
	}
}
