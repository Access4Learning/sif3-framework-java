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

import java.util.Date;
import java.util.List;

import sif3.common.CommonConstants;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;
import sif3.common.model.security.TokenInfo;
import sif3.common.persist.model.AppEnvironmentTemplate;
import sif3.common.persist.model.SIF3Session;
import sif3.common.persist.service.AppEnvironmentService;
import sif3.common.persist.service.SIF3SessionService;
import sif3.common.utils.UUIDGenerator;
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
	private AppEnvironmentService appEnvService = new AppEnvironmentService();

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
     * This method will attempt to load the environment template record from the underlying template info store. Note that 
     * this method won't load the actual template rather it returns some meta information around the environment template.
     * This functionality is only applicable for the DIRECT environment provider of this framework. If there is no entry in
     * the environment template store for the given environment key or an error occurs accessing the store then null is 
     * returned.
     *  
     * @param envKey The environment key for which the environment template information shall be retrieved.
     * 
     * @return See desc.
     */
	public AppEnvironmentTemplate getTemplateInfo(EnvironmentKey envKey)
	{
		try
        {
	        return appEnvService.getEnvironmentTemplate(envKey);
        }
        catch (Exception ex) // error is already logged.
        {
	        return null;
        }
	}

	/**
	 * This method loads the environment from the workstore. Before it is loaded it checks if it does already exist. If it 
	 * doesn't then null is returned, otherwise the environment is returned. Note if it doesn't exist it WON'T create it. 
	 * To create the environment from a template then the 'createEnvironmentAndSession(...)' method in this class must be called.
	 * 
	 * @param environmentKey solutionID Mandatory, applicationKey Mandatory, userToken Optional, instanceID Optional 
	 * @param tokenInfo Information related to the security token. Can be used to store expire date and a security token related to the
	 *                  session to be created or updated.
	 * @param useSecured TRUE => Indicates that HTTPS end-points shall be returned. FALSE => Return HTTP end-points if available
	 * 
	 * @return see Desc.
	 * 
	 * @throws IllegalArgumentException Any of the mandatory parameters is null or empty.
	 * @throws PersistenceException Could not access the underlying workstore.
	 */
    public EnvironmentType loadEnvironmentFromWorkstore(EnvironmentKey environmentKey, TokenInfo tokenInfo, boolean useSecured) throws IllegalArgumentException, PersistenceException
    {
		SIF3Session session = loadAndUpdateSession(environmentKey, tokenInfo, useSecured);
		if (session != null)
		{
			return loadEnvironmentFromString(session.getEnvironmentXML());
		}
		return null;
    }
    
	/**
	 * This method will attempt to load an existing session from the workstore and then update the XML with the 
	 * latest template values such as connector URLSs, ACLs etc that may have changed since the last time the 
	 * session was loaded. The changes are stored back to the session store and the updated session is returned. 
	 * If no environment exits for the given environmentKey then null is returned.
	 * 
	 * @param environmentKey The environment information for which the session shall be loaded.
	 * @param tokenInfo Information related to the security token. Can be used to store expire date and a security 
	 *                  token related to the session to be created or updated.
	 * @param useSecured TRUE => Indicates that HTTPS end-points shall be returned. FALSE => Return HTTP end-points if available
	 * 
	 * @return See desc.
	 * 
	 * @throws IllegalArgumentException environmentKey is null or environmentKey.applicationKey is null or empty.
	 * @throws PersistenceException Could not access the underlying workstore.
	 */
	public SIF3Session loadAndUpdateSession(EnvironmentKey environmentKey, TokenInfo tokenInfo, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		ProviderEnvironment envInfo = (ProviderEnvironment)getEnvironmentStore().getEnvironment();
		SIF3Session sif3Session = service.getSessionBySolutionAppkeyUserInst(environmentKey, CommonConstants.AdapterType.ENVIRONMENT_PROVIDER);
		if (sif3Session != null) // Session exists. May need to update some values
		{
			updateSessionInfo(sif3Session, envInfo, tokenInfo, useSecured);
			return sif3Session;
		}
		return null;
	}

	/**
	 * This method will attempt to load an existing session from the workstore and then update the XML with the 
	 * latest template values such as connector URLSs, ACLs etc that may have changed since the last time the 
	 * session was loaded. The changes are stored back to the session store and the updated session is returned. 
	 * If no environment exits for the given environmentKey then null is returned.
	 * 
	 * @param environmentID The environment ID for which the session shall be loaded.
	 * @param tokenInfo Information related to the security token. Can be used to store expire date and a security 
	 *                  token related to the session to be created or updated.
	 * @param useSecured TRUE => Indicates that HTTPS end-points shall be returned. FALSE => Return HTTP end-points if available
	 * 
	 * @return See desc.
	 * 
	 * @throws IllegalArgumentException environmentID is null or empty.
	 * @throws PersistenceException Could not access the underlying workstore.
	 */
	public SIF3Session loadAndUpdateSession(String environmentID, TokenInfo tokenInfo, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		ProviderEnvironment envInfo = (ProviderEnvironment)getEnvironmentStore().getEnvironment();
		SIF3Session sif3Session = service.getSessionByEnvID(environmentID, CommonConstants.AdapterType.ENVIRONMENT_PROVIDER);
		if (sif3Session != null) // Session exists. May need to update some values
		{
			updateSessionInfo(sif3Session, envInfo, tokenInfo, useSecured);
			return sif3Session;
		}
		return null;
	}
	
	/**
	 * This method will load a SIF3 Session from the workstore and update it with the latest values form the template if needed. The updated
	 * values are then stored back to the work store and the final session is returned. If there is no known session in the workstore for the
	 * for the given session token then null is returned.
	 * 
	 * @param sessionToken The sessionToken for which the SIF3 session shall be loaded, updated and returned. 
	 * @param tokenInfo Information related to the security token. Can be used to store expire date and a security token related to the
	 *                  session to be created or updated.
	 * @param useSecured TRUE => Indicates that HTTPS end-points shall be returned. FALSE => Return HTTP end-points if available
	 * 
	 * @return See Desc.
	 * 
	 * @throws IllegalArgumentException sessionToken is null or empty.
	 * @throws PersistenceException Could not access the underlying workstore.
	 */
	public SIF3Session loadSessionFromWorkstore(String sessionToken, TokenInfo tokenInfo, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		if (StringUtils.isEmpty(sessionToken))
		{
			throw new IllegalArgumentException("sessionToken is null or empty. Cannot retrive environment/session for this session token.");
		}

		SIF3Session sif3Session = getSIF3SessionBySessionToken(sessionToken,AdapterType.ENVIRONMENT_PROVIDER, service);
		if (sif3Session != null)
		{
			ProviderEnvironment envInfo = (ProviderEnvironment) getEnvironmentStore().getEnvironment();
			updateSessionInfo(sif3Session, envInfo, tokenInfo, useSecured);
		}
		return sif3Session;
	}

	/**
	 * This method will load a SIF3 Session from the workstore based on the security token (tokenInfo) and 
	 * update it with the latest values form the template if needed. The updated values are then stored back 
	 * to the work store and the final session is returned. If there is no known session in the workstore for 
	 * the for the given security token (tokenInfo) then null is returned.
	 * 
	 * @param tokenInfo The token info for which the SIF3 session shall be loaded, updated and returned. This 
	 *                  must hold the security token in the tokenInfo.token property.
	 * @param useSecured TRUE => Indicates that HTTPS end-points shall be returned. FALSE => Return HTTP end-points if available
	 * 
	 * @return See Desc.
	 * 
	 * @throws IllegalArgumentException tokenInfo and/or tokenInfo.token is null or empty.
	 * @throws PersistenceException Could not access the underlying workstore.
	 */
	public SIF3Session loadSessionFromWorkstore(TokenInfo tokenInfo, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		if (tokenInfo == null)
		{
			throw new IllegalArgumentException("tokenInfo is null. Cannot retrive environment/session for this security token.");			
		}
		if (StringUtils.isEmpty(tokenInfo.getToken()))
		{
			throw new IllegalArgumentException("Token is not given in tokenInfo. Cannot retrive environment/session for this security token.");			
		}
		
		SIF3Session sif3Session = getSIF3SessionBySecurityToken(tokenInfo.getToken(), AdapterType.ENVIRONMENT_PROVIDER, service);
		if (sif3Session != null)
		{
			ProviderEnvironment envInfo = (ProviderEnvironment) getEnvironmentStore().getEnvironment();
			updateSessionInfo(sif3Session, envInfo, tokenInfo, useSecured);
		}
		return sif3Session;
	}

	/**
	 * This method takes the inputEnv and uses its data to create a new full environment and associated session. The reminder of the 
	 * environment data is read from the template directory (environment services and permissions). A SessionToken and Environment ID 
	 * is also created before the final environment is stored. The input environment is modified before the final 
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
	 * @param tokenInfo Information related to the security token. Can be used to store expiry date and a security token related to the
	 *                  session to be created or updated.
	 * @param useSecured TRUE => Indicates that HTTPS end-points shall be returned. FALSE => Return HTTP end-points if available
	 *                 
	 * @return The new Environment that has been created based on the inputEnv parameter and the context.
	 */
    public EnvironmentType createEnvironmentAndSession(EnvironmentType inputEnv, TokenInfo tokenInfo, boolean useSecured)
    {
		SIF3Session sif3Session = createSession(inputEnv, tokenInfo, useSecured);
		if (sif3Session != null) //we are all good
		{
			return loadEnvironmentFromString(sif3Session.getEnvironmentXML());
		}
		else
		{
			return null; // error already logged
		}
	}

	/*
	 * 
	 */
	public boolean updateSessionSecurityInfo(String sessionToken, String securityToken, Date securityExpiryDate)
	{
		return updateSessionSecurityInfo(sessionToken, securityToken, securityExpiryDate, AdapterType.ENVIRONMENT_PROVIDER, service);
	}

	/**
	 * The same behaviour as the createEnvironmentAndSession() method except that the returned value is a SIF3 Session 
	 * where the environment is returned as an XML string value in the SIF3Session.environmentXML property.
	 * 
	 * @param inputEnv The environment as provided by a environment provider (brokered) or the consumer (direct - in this case
	 *                 is a cut-down version with minimal data as specified  in the SIF3 spec).
	 * @param tokenInfo Information related to the security token. Can be used to store expire date and a security token related to the
	 *                  session to be created or updated.
	 * @param useSecured TRUE => Indicates that HTTPS end-points shall be returned. FALSE => Return HTTP end-points if available
	 *                 
	 * @return A SIF3 Session object with all values populated that are relevant to the newly created session.
	 */
    public SIF3Session createSession(EnvironmentType inputEnv, TokenInfo tokenInfo, boolean useSecured)
    {
		if ((inputEnv == null) || (inputEnv.getApplicationInfo() == null))
		{
			logger.error("The consumer input environment is null or does not have the Application Info set. Environment cannot be created.");
			return null;
		}
//	    if (StringUtils.isEmpty(inputEnv.getSolutionId()) || StringUtils.isEmpty(inputEnv.getApplicationInfo().getApplicationKey()))
	    if (StringUtils.isEmpty(inputEnv.getApplicationInfo().getApplicationKey()))
	    {
//	      	logger.error("The application key and/or the solution id in the consumer input environment is null or empty. Environment cannot be created.");
	    	logger.error("The application key in the consumer input environment is null or empty. Environment cannot be created.");
	    	return null;
	    }
		ProviderEnvironment envInfo = (ProviderEnvironment)getEnvironmentStore().getEnvironment();
	    
		try
		{
			EnvironmentKey envKey = new EnvironmentKey(inputEnv.getSolutionId(), inputEnv.getApplicationInfo().getApplicationKey(), inputEnv.getUserToken(), inputEnv.getInstanceId());
			SIF3Session sif3Session = loadAndUpdateSession(envKey, tokenInfo, useSecured);
			if (sif3Session != null) // Session exists. All done => return it.
			{
				logger.info("SIF3 Session for "+envInfo.getEnvironmentName()+" exists already. Simply updated connector URLs, ACLs etc if needed and return it and do not create it again.");
				return sif3Session;
			}
			
			// If we get here then we don't have an environment, yet => create and store it
			// Get the template name to use
			AppEnvironmentTemplate appEnvTemplate = getTemplateInfo(envKey);
			if (appEnvTemplate == null) // error already logged.
			{
				return null;
			}
			
			EnvironmentType environment = loadEnvironmentFromTemplate(appEnvTemplate.getEnvironmentTemplate().getTemplateFileName());
			
			if (environment == null) // does not exist in template directory. we cannot create it.
			{
				return null; // error already logged.
			}

			// Create a session the environment in the store.
			sif3Session = service.createNewSession(envKey, CommonConstants.AdapterType.ENVIRONMENT_PROVIDER);
			
			if (sif3Session != null) // all good
			{
				// Now we have a session token and a environmentID. Store them in the environment.
				environment.setSessionToken(sif3Session.getSessionToken());
				environment.setId(sif3Session.getEnvironmentID());
				environment.setFingerprint(sif3Session.getFingerprint());
				
				// Other important values can be taken from the input environment or if not provided (payload free creation)
				// some of the values should remain as in the template others can be taken from the template lookup tabble

				// Check what we have from Application Info: Note AppKey and Transport are always there
			    if (environment.getApplicationInfo() == null) // nothing in template, so use what is in inputEnv.
			    {
			    	environment.setApplicationInfo(inputEnv.getApplicationInfo());
			    }
			    else //environment Template has some info. use it and update with input environment
			    {
  					environment.getApplicationInfo().setApplicationKey(inputEnv.getApplicationInfo().getApplicationKey());
  					environment.getApplicationInfo().setTransport(inputEnv.getApplicationInfo().getTransport());
  					if (StringUtils.notEmpty(inputEnv.getApplicationInfo().getDataModelNamespace()))
  					{
  						environment.getApplicationInfo().setDataModelNamespace(inputEnv.getApplicationInfo().getDataModelNamespace());
  					}
  					if (StringUtils.notEmpty(inputEnv.getApplicationInfo().getSupportedInfrastructureVersion()))
  					{
  						environment.getApplicationInfo().setSupportedInfrastructureVersion(inputEnv.getApplicationInfo().getSupportedInfrastructureVersion());
  					}
  					if (inputEnv.getApplicationInfo().getApplicationProduct() != null)
  					{
  						environment.getApplicationInfo().setApplicationProduct(inputEnv.getApplicationInfo().getApplicationProduct());
  					}
  					if (inputEnv.getApplicationInfo().getAdapterProduct() != null)
  					{
  						environment.getApplicationInfo().setAdapterProduct(inputEnv.getApplicationInfo().getAdapterProduct());
  					}
			    }
				
			    // If the input has a consumer name, use this otherwise leave what is in the template xml.
				if (StringUtils.notEmpty(inputEnv.getConsumerName()))
				{
					environment.setConsumerName(inputEnv.getConsumerName());
				}
				
				// Solution ID must be taken firstly from template lookup and secondly from environment template xml.
				if (StringUtils.notEmpty(appEnvTemplate.getSolutionID())) // there is a SolutionID. Take it, otherwise leave as in template xml.
				{
				  environment.setSolutionId(appEnvTemplate.getSolutionID());
				}

		        // Maybe the authentication method has changed, too.
		        if (StringUtils.notEmpty(appEnvTemplate.getAuthMethod()))
		        {
		          environment.setAuthenticationMethod(appEnvTemplate.getAuthMethod());
		        }
		        else if (StringUtils.isEmpty(environment.getAuthenticationMethod())) // if empty in env.xml set to Basic
		        {
		          environment.setAuthenticationMethod(AuthenticationMethod.Basic.name());
		        }
		        
		        // Set Authentication Method is sif3 Session.
		        sif3Session.setAuthenticationMethod(environment.getAuthenticationMethod());

				environment.setType(EnvironmentTypeType.DIRECT); // It is a direct environment, so set it accordingly	
				environment.setUserToken(inputEnv.getUserToken());
				environment.setInstanceId(inputEnv.getInstanceId());
				updateConnectorURLs(environment, envInfo, useSecured); //update URLs for infrastructure services.
				
				// All is set now. We can update the session in the session store with the final values.
				sif3Session.setPassword(appEnvTemplate.getPassword());
				sif3Session.setAdapterName(environment.getConsumerName());
				sif3Session.setSolutionID(environment.getSolutionId());
				
		        // There might be a security token attached to the session
		        if (tokenInfo != null)
		        {
		          sif3Session.setSecurityToken(tokenInfo.getToken());
		          sif3Session.setSecurityTokenExpiry(tokenInfo.getTokenExpiryDate());
		        }
				
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
	private void updateSessionInfo(SIF3Session sif3Session, ProviderEnvironment envInfo, TokenInfo tokenInfo, boolean useSecured)
	{
		if (sif3Session != null) // Session exists. May need to update some values
		{
			EnvironmentType environment = loadEnvironmentFromString(sif3Session.getEnvironmentXML());
			if (environment != null) // all fine
			{
				// We may need to replace infrastructure URLs depending on how the environment is used (http, https).
				// Lookup the template this provider supports and replace things as required.
				// Get name of the template
				AppEnvironmentTemplate appEnvTemplate = getTemplateInfo(sif3Session);
				if (appEnvTemplate != null)
				{
					EnvironmentType templateEnv = loadEnvironmentFromTemplate(appEnvTemplate.getEnvironmentTemplate().getTemplateFileName());
					if (templateEnv != null)
					{
						// Ensure that all ACLs are updated.
						reloadServiceInfo(environment, templateEnv);
						
						// Update infrastructure service URIs in case they have changed.
						environment.setInfrastructureServices(templateEnv.getInfrastructureServices());
						updateConnectorURLs(environment, envInfo, useSecured);
						
						// Maybe the authentication method has changed, too.
						if (StringUtils.notEmpty(appEnvTemplate.getAuthMethod()))
						{
							environment.setAuthenticationMethod(appEnvTemplate.getAuthMethod());							
						}
						else // assume Basic
						{
							environment.setAuthenticationMethod(AuthenticationMethod.Basic.name());
						}
						
                        // Set authentication method it in the session.
                        sif3Session.setAuthenticationMethod(environment.getAuthenticationMethod());

						// Maybe the password has changed, too.
						if (StringUtils.notEmpty(appEnvTemplate.getPassword()))
						{
							sif3Session.setPassword(appEnvTemplate.getPassword());
						}
						
						// The SolutionID might not have been set by the input environment but is given by the environment template lookup or
						// the environment template.
						if (StringUtils.notEmpty(appEnvTemplate.getSolutionID())) // use env template lookup as first priority
						{
						  sif3Session.setSolutionID(appEnvTemplate.getSolutionID()); 
						}
						else // use what is in the template xml.
						{
						  sif3Session.setSolutionID(templateEnv.getSolutionId()); // either given in template xml or not used.
						}
						
						// There might be a security token attached to the session
						if (tokenInfo != null)
						{
						  sif3Session.setSecurityToken(tokenInfo.getToken());
						  if (tokenInfo.getTokenExpiryDate() != null)
						  {
							  sif3Session.setSecurityTokenExpiry(tokenInfo.getTokenExpiryDate());
						  }
						}

						// ensure that other values that are of importance match the values in the template, especially since most value of the environment
						// creation are optional and might be set in the template instead.
						if (StringUtils.isEmpty(sif3Session.getAdapterName())) // only override consumer if it doesn't exist
						{
						  sif3Session.setAdapterName(templateEnv.getConsumerName());
						}
						
						// Since SIF 3.2.1 we need a fingerprint. This bit is providing a automatic upgrade to this SIF version.
						if (StringUtils.isEmpty(environment.getFingerprint()))
						{
						    sif3Session.setFingerprint(UUIDGenerator.getUUID());
                            environment.setFingerprint(sif3Session.getFingerprint());
						}
						
						//Note some values may have been changed in the applciationInfo node but we don't really know how to deal with them
						//right now so we ignore them as they are of no importance for operational purposes.
						
						// Store the updated values. Note even if this fails it is no drama as it will be recreated the next
						// time a consumer connects.
						storeEnvDataToWorkstore(sif3Session, environment, service);
					}
					else
					{
						logger.error("No environment template found for "+appEnvTemplate.getEnvironmentTemplate().getTemplateFileName()+". Cannot update connector URLs.");
					}
				}
				// else: No template found in DB. Error already logged.
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
	
	private void reloadServiceInfo(EnvironmentType environment, EnvironmentType templEnv)
	{
		environment.setProvisionedZones(templEnv.getProvisionedZones());
	}
}
