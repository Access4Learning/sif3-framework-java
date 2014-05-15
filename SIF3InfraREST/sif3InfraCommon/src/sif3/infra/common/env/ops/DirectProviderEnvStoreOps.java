/*
 * DirectProviderEnvStoreOps.java
 * Created: 20/02/2014
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

package sif3.infra.common.env.ops;

import java.util.List;

import sif3.common.CommonConstants;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.model.EnvironmentKey;
import sif3.common.persist.model.SIF3Session;
import sif3.common.persist.service.SIF3SessionService;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.EnvironmentTypeType;
import sif3.infra.common.model.InfrastructureServiceType;
import sif3.infra.common.model.InfrastructureServicesType;
import au.com.systemic.framework.utils.StringUtils;

/**
 * This class implements operations required by a direct environment provider. They are quite distinct and therefore warrant having its own
 * implementation.
 * 
 * @author Joerg Huber
 *
 */
public class DirectProviderEnvStoreOps extends AdapterBaseEnvStoreOperations
{
	private SIF3SessionService service = new SIF3SessionService();

	/**
     * @param adapterFileNameWithoutExt The property file name for Store Operation class.
     */
    public DirectProviderEnvStoreOps(String adapterFileNameWithoutExt)
    {
	    super(adapterFileNameWithoutExt);
    }

	/**
	 * Check of an environment template for the given filename exists. TRUE: it exists, FALSE it doesn't.
	 * 
	 * @param templateFileName The name of the environment template file to check for in template directory. This name must 
	 *                         include the extension ".xml".
	 * 
	 * @return TRUE: Environment Template exists. FALSE: The environment template for the given file name doen't exists.
	 */
    public boolean existEnvironmentTemplate(String templateFileName)
    {
	    return existEnvironmentTemplate(templateFileName, AdapterType.ENVIRONMENT_PROVIDER, sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType.DIRECT);
    }

	/**
	 * This method loads the given environment from the template store and returns it. If no such environment exists then null 
	 * is returned.
	 * 
	 * @param envFileName The name of the environment file to load from template directory. This name must include the extension ".xml".
	 * 
	 * @return Null => Failed to load data due to some error. See error log for details.
	 */
    public EnvironmentType loadEnvironmentFromTemplate(String envFileName)
    {
	    return loadTemplateEnvironmentData(envFileName, AdapterType.ENVIRONMENT_PROVIDER, sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType.DIRECT);
    }

	/**
	 * Checks if an environment does already exists in the workstore for the given environmentKey.
	 * 
	 * @param environmentKey solutionID Mandatory, applicationKey Mandatory, userToken Optional, instanceID Optional: 
	 * 
	 * @return Returns true if an environment does already exist, which means an existing session is available.
	 * 
	 * @throws IllegalArgumentException Any of the mandatory parameters is null or empty.
	 * @throws PersistenceException Could not access the underlying workstore.
	 */
    public boolean existEnvInWorkstore(EnvironmentKey environmentKey) throws IllegalArgumentException, PersistenceException
    {
		return service.getSessionBySolutionAppkeyUserInst(environmentKey, CommonConstants.AdapterType.ENVIRONMENT_PROVIDER) != null;
    }

	/**
	 * This method loads the environment for a the workstore. Before it is loaded it checks if it does already exist. If it 
	 * doesn't then null is returned, otherwise the environment is returned. Note if it doesn't exist it WON'T create it. 
	 * To create the environment from a template then the 'createAndStoreEnvIronment(...)' method in this class must be called.
	 * 
	 * @param environmentKey solutionID Mandatory, applicationKey Mandatory, userToken Optional, instanceID Optional 
	 * @param useSecured TRUE => Indicates that HTTPS end-points shall be returned. FALSE => Return HTTP end-points if available
	 * 
	 * @return see Desc.
	 * 
	 * @throws IllegalArgumentException Any of the mandatory parameters is null or empty.
	 * @throws PersistenceException Could not access the underlying workstore.
	 */
    public EnvironmentType loadEnvironmentFromWorkstore(EnvironmentKey environmentKey, boolean useSecured) throws IllegalArgumentException, PersistenceException
    {
		SIF3Session session = loadAndUpdateSession(environmentKey, useSecured);
		if (session != null)
		{
			return loadEnvironmentFromString(session.getEnvironmentXML());
		}
		return null;
    }	
	
	/**
	 * This method will load a SIF3 Session from the workstore and update it with the latest values form the template if needed. The updated
	 * values are then stored back to the work store and the final session is returned. If there is no known session in the workstore for the
	 * for the given session token then null is returned.
	 * 
	 * @param sessionToken The sessionToken for which the SIF3 session shall be loaded, updated and returned. 
	 * @param useSecured TRUE => Indicates that HTTPS end-points shall be returned. FALSE => Return HTTP end-points if available
	 * 
	 * @return See Desc.
	 * 
	 * @throws IllegalArgumentException sessionToken is null or empty.
	 * @throws PersistenceException Could not access the underlying workstore.
	 */
	public SIF3Session loadSessionFromWorkstore(String sessionToken, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		if (StringUtils.isEmpty(sessionToken))
		{
			throw new IllegalArgumentException("sessionToken is null or empty. Cannot retrive environment/session for this session token.");
		}

		SIF3Session sif3Session = getSIF3SessionBySessionToken(sessionToken,AdapterType.ENVIRONMENT_PROVIDER, service);
		if (sif3Session != null)
		{
			ProviderEnvironment envInfo = (ProviderEnvironment) getEnvironmentStore().getEnvironment();
			updateSessionInfo(sif3Session, envInfo, useSecured);
		}
		return sif3Session;
	}

	/**
	 * This method takes the inputEnv and uses its data to create a new full environment and associated session. The reminder of the 
	 * environment data is read from the template directory (environment services and permissions). A SessionToken and Environment ID 
	 * is also  created before the final environment created and stored. The input environment is modified before the final 
	 * environment is returned. It is expected that the final returned environment is a full environment with all applicable 
	 * infrastructure end-points, ACLs, environmentID, sessionToken etc that is applicable for the context (brokered vs direct).
	 * If the environment cannot be created in the environment store then the error is logged and null is returned.<br/><br/>
	 * 
	 * NOTE:<br/>
	 * If an environment does already exist for the given input environment then that environment is returned and no new one 
	 * is created.
	 * 
	 * @param inputEnv The environment as provided by a environment provider (brokered) or the consumer (direct - in this case
	 *                 is a cut-down version with minimal data as specified  in the SIF3 spec).
	 * @param useSecured TRUE => Indicates that HTTPS end-points shall be returned. FALSE => Return HTTP end-points if available
	 *                 
	 * @return The new Environment that has been created based on the inputEnv parameter and the context.
	 */
    public EnvironmentType createEnvironmentAndSession(EnvironmentType inputEnv, boolean useSecured)
    {
		SIF3Session sif3Session = createSession(inputEnv, useSecured);
		if (sif3Session != null) //we are all good
		{
			return loadEnvironmentFromString(sif3Session.getEnvironmentXML());
		}
		else
		{
			return null; // error already logged
		}
	}

	/**
	 * The same behaviour as the createEnvironmentAndSession() method except that the returned value is a SIF3 Session 
	 * where the environment is returned as an XML string value in the SIF3Session.environmentXML property.
	 * 
	 * @param inputEnv The environment as provided by a environment provider (brokered) or the consumer (direct - in this case
	 *                 is a cut-down version with minimal data as specified  in the SIF3 spec).
	 * @param useSecured TRUE => Indicates that HTTPS end-points shall be returned. FALSE => Return HTTP end-points if available
	 *                 
	 * @return A SIF3 Session object with all values populated that are relevant to the newly created session.
	 */
    public SIF3Session createSession(EnvironmentType inputEnv, boolean useSecured)
    {
		if ((inputEnv == null) || (inputEnv.getApplicationInfo() == null))
		{
			logger.error("The consumer input environment is null or does not have the Application Info set. Environment cannot be created.");
			return null;
		}
	    if (StringUtils.isEmpty(inputEnv.getSolutionId()) || StringUtils.isEmpty(inputEnv.getApplicationInfo().getApplicationKey()))
	    {
	      logger.error("The application key and/or the solution id in the consumer input environment is null or empty. Environment cannot be created.");
	      return null;
	    }
		ProviderEnvironment envInfo = (ProviderEnvironment)getEnvironmentStore().getEnvironment();
	    
		try
		{
			EnvironmentKey envKey = new EnvironmentKey(inputEnv.getSolutionId(), inputEnv.getApplicationInfo().getApplicationKey(), inputEnv.getUserToken(), inputEnv.getInstanceId());
			SIF3Session sif3Session = loadAndUpdateSession(envKey, useSecured);
			if (sif3Session != null) // Session exists. All done => return it.
			{
				logger.info("SIF3 Session for "+envInfo.getEnvironmentName()+" exists already. Simply updated connector URLs, ACLs etc if needed and return it and do not create it again.");
				return sif3Session;
			}
			
			// If we get here then we don't have an environment, yet => create and store it
			EnvironmentType environment = loadEnvironmentFromTemplate(envInfo.getTemplateXMLFileName());
			
			if (environment == null) // does not exist in template directory. we cannot create it.
			{
				return null; // error already logged.
			}

			//TODO: JH - Check if inputEnv parameters match the environment and environment Template parameter! 
			// SolutionID from Template must match inputEnv.SolutionId; env.ApplicationKey must match inputEnv.ApplKey etc
			
			// Create a session the environment in the store.
			sif3Session = service.createNewSession(envKey, CommonConstants.AdapterType.ENVIRONMENT_PROVIDER);
			
			if (sif3Session != null) // all good
			{
				// Now we have a session token and a environmentID. Store them in the environment.
				environment.setSessionToken(sif3Session.getSessionToken());
				environment.setId(sif3Session.getEnvironmentID());
				
				// Other important values can be taken from the input environment. SolutionId should be in the template, so no need to 
				// use it from the inputEnv. But the following values need to be used	
				environment.setApplicationInfo(inputEnv.getApplicationInfo());  // this has the application key
				environment.setConsumerName(inputEnv.getConsumerName());
				environment.setType(EnvironmentTypeType.DIRECT); // IT is a direct environment, so set it accordingly	
				environment.setUserToken(inputEnv.getUserToken());
				environment.setInstanceId(inputEnv.getInstanceId());
				updateConnectorURLs(environment, envInfo, useSecured); //update URLs for infrastructure services.
				
				// All is set now. We can update the session in the session store with the final values.
				sif3Session.setPassword(envInfo.getPassword());
				sif3Session.setAdapterName(inputEnv.getConsumerName());
				
				if (storeEnvDataToWorkstore(sif3Session, environment, service))
				{
					return sif3Session;
				}
				else
				{
					return null; // error already logged					
				}
			}
			else
			{
				return null; // error already logged	
			}
		}
        catch (Exception ex) // really should only be PersistenceException or IllegarArgumentException
        {
	        // errors should already be logged. Just return null as environment 
        	return null;
        }
    }
	
	/**
	 * This removes the environment data stored in the environment store. This operation should be used with care! A deletion 
	 * of an environment means that it is no longer recoverable and a consumer/provider can no longer connect to the 
	 * environment because the information relating to that environment are lost for good! A loss of an environment also means
	 * a loss of all data that relate to an environment such as SIF events, responses etc. All things that are potentially 
	 * stored in queues.
	 * 
	 * @param environmentID Uniquely identifies the environment to be removed.
	 * 
	 * @return TRUE: Operation successful. FALSE: Error occurred. Session might not be removed! See error log for details.
	 */
    public boolean removeEnvFromWorkstoreByEnvID(String environmentID)
    {
		return removeEnvFromWorkstoreByEnvID(environmentID, AdapterType.ENVIRONMENT_PROVIDER, service);
    }

	/**
	 * As above but deletion occurs based on environment ID.
	 * 
	 * @param sessionToken  Uniquely identifies the environment to be removed.
	 * 
	 * @return TRUE: Operation successful. FALSE: Error occurred. Session might not be removed! See error log for details.
	 */
    public boolean removeEnvFromWorkstoreBySessionToken(String sessionToken)
    {
	    return removeEnvFromWorkstoreBySessionToken(sessionToken, AdapterType.ENVIRONMENT_PROVIDER, service);
    }
	
	/*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
	/*
	 * This method will attempt to load an existing session from the workstore and then update the XML with the latest template values
	 * such as connector URLSs, ACLs etc that may have changed since the last time the session was loaded. The changes are stored back to
	 * the session store and the updated session is returned.
	 * If no session exists to start of with then null is returned.
	 */
	private SIF3Session loadAndUpdateSession(EnvironmentKey environmentKey, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		ProviderEnvironment envInfo = (ProviderEnvironment)getEnvironmentStore().getEnvironment();
		SIF3Session sif3Session = service.getSessionBySolutionAppkeyUserInst(environmentKey, CommonConstants.AdapterType.ENVIRONMENT_PROVIDER);
		if (sif3Session != null) // Session exists. May need to update some values
		{
			updateSessionInfo(sif3Session, envInfo, useSecured);
			return sif3Session;
		}
		return null;
	}
	
	private void updateSessionInfo(SIF3Session sif3Session, ProviderEnvironment envInfo, boolean useSecured)
	{
		if (sif3Session != null) // Session exists. May need to update some values
		{
			EnvironmentType environment = loadEnvironmentFromString(sif3Session.getEnvironmentXML());
			if (environment != null) // all fine
			{
				// We may need to replace infrastructure URLs depending on how the environment is used (http, https).
				// Lookup the template this provider supports and replace things as required.
				EnvironmentType templateEnv = loadEnvironmentFromTemplate(envInfo.getTemplateXMLFileName());
				if (templateEnv != null)
				{
					environment.setInfrastructureServices(templateEnv.getInfrastructureServices());
					updateConnectorURLs(environment, envInfo, useSecured);
					
					// Store the updated values. Note even if this fails it is no drama as it will be recreated the next
					// time a consumer connects.
					storeEnvDataToWorkstore(sif3Session, environment, service);
				}
				else
				{
					logger.error("No environment template found for "+envInfo.getTemplateXMLFileName()+". Cannot update connector URLs.");
				}	
			}
			// else (no XML with session) error already logged. This would be a strange setup. Should not really happen!
		}
	}
	
	private void updateConnectorURLs(EnvironmentType environment, ProviderEnvironment envInfo, boolean useSecured)
	{
		String baseURIStr = useSecured ? envInfo.getSecureConnectorBaseURI().toString() : envInfo.getConnectorBaseURI().toString();
		InfrastructureServicesType infraServices = environment.getInfrastructureServices();
		
		if (infraServices != null)
		{
			List<InfrastructureServiceType> services = infraServices.getInfrastructureService();
			for (InfrastructureServiceType infraService : services)
			{
				String connectorURL = infraService.getValue();
				
		        // Remove trailing '/' if it is there
		  	  	if (connectorURL.endsWith("/"))
		  	  	{
		  	  		connectorURL = connectorURL.substring(0, connectorURL.length()-1);
		  	  	}

				//check if it has a leading '/'
				boolean hasSlash = connectorURL.startsWith("/");
				
				// Search for service called "environment" and also add the environment ID
				if (infraService.getName().value().equals(ConsumerEnvironment.ConnectorName.environment.toString()))
				{
					infraService.setValue(baseURIStr+(hasSlash?"":"/")+connectorURL+"/"+environment.getId());
				}
				else
				{
					infraService.setValue(baseURIStr+(hasSlash?"":"/")+connectorURL);
				}
			}
		}
		else
		{
			logger.error("Infrastructure Services not defined in environment template "+envInfo.getTemplateXMLFileName()+". This must be set.");
		}
	}
}
