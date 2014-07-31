/*
 * DirectProviderEnvironmentManager.java Created: 18/09/2013
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

package sif3.infra.common.env.mgr;

import java.util.HashMap;

import org.apache.log4j.Logger;

import sif3.common.exception.PersistenceException;
import sif3.common.model.EnvironmentKey;
import sif3.common.persist.model.AppEnvironmentTemplate;
import sif3.common.persist.model.SIF3Session;
import sif3.infra.common.env.ops.DirectProviderEnvStoreOps;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.utils.SIFSessionUtils;
import au.com.systemic.framework.utils.AdvancedProperties;

/**
 * This class deals with the main functions relating to the environments that need to be managed by the environment provider. 
 * It is a cut down version of an Environment Provider and is required for DIRECT environments on the provider side. It deals with 
 * the main functions of a environment provider as defined in the SIF3 spec. Only lower level classes in this framework really 
 * require this class, mainly the resources for objects as well as the actual environment resource.
 * 
 * @author Joerg Huber
 */
public class DirectProviderEnvironmentManager implements EnvironmentManager
{
	protected final Logger logger = Logger.getLogger(getClass());

	/* The property file name of this consumer */
	private String adapterFileNameWithoutExt = null;
	
	/* The environment store operation class to be used with this manager. */
	private DirectProviderEnvStoreOps envOps = null;
	
	/* Key=SessionToken for environment & consumer, Data: SIF3Session */
  private HashMap<String, SIF3Session> sessions = new HashMap<String, SIF3Session>();

  private static DirectProviderEnvironmentManager instance = null;

	/**
	 * Initialises an instance of the Provider Environment Manager.
	 * 
	 * @param adapterFileNameWithoutExt  The name of the property file to be used for this environment manager.
	 */
	public static DirectProviderEnvironmentManager initialse(String adapterFileNameWithoutExt)
	{
		if (instance == null)
		{
			instance = new DirectProviderEnvironmentManager(adapterFileNameWithoutExt);
		}
		
		return instance;
	}

  /**
	 * Returns an instance of the Provider Environment Manager.
	 * 
	 * @return See Desc.
	 */
	public static DirectProviderEnvironmentManager getInstance()
	{
		return instance;
	}

	/**
     * @return the adapterFileNameWithoutExt
     */
    public String getAdapterFileNameWithoutExt()
    {
	    return adapterFileNameWithoutExt;
    }

    /*-----------------------*/
	/*-- Interface Methods --*/
	/*-----------------------*/
	
	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.env.ClientEnvironmentManager#getEnvironmentInfo
	 */
    @Override
	public EnvironmentInfo getEnvironmentInfo()
	{
		return envOps.getEnvironmentInfo();
	}

    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.EnvironmentManager#getServiceProperties()
     */
    @Override
    public AdvancedProperties getServiceProperties()
    {
	    return envOps.getServiceProperties();
    }
    
    /*
     * (non-Javadoc)
     * @see sif3.infra.common.interfaces.EnvironmentManager#getSessionBySessionToken(java.lang.String)
     */
	public SIF3Session getSessionBySessionToken(String sessionToken)
	{
		if (sessionToken != null)
		{
			return sessions.get(sessionToken);
		}
		return null;
	}

    /*
     * (non-Javadoc)
     * @see sif3.infra.common.interfaces.EnvironmentManager#getEnvironmentType()
     */
    @Override
    public sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType getEnvironmentType()
    {
	    return getEnvironmentInfo().getEnvironmentType();
    }

    /*--------------------------*/
	/*-- Other Public Methods --*/
	/*--------------------------*/

	/**
	 * This method will create an environment on the provider. The environment will be created based on the given input environment
	 * information. If this operation fails an error is logged and null is returned. The environment will be created and all the
	 * information according to the SIF3 spec is returned (i.e. Session Token, Environment Id etc).
	 * 
	 * @param inputEnvironment The basic information required by the environment provider to create the environment for the consumer.
	 * @param useSecured Indicate if the environment to load should use secured (TRUE) service URIs (https://...) or
	 *                   unsecured URIs (http://...)
	 * 
	 * @return See desc.
	 */
	public EnvironmentType createOrUpdateEnvironment(EnvironmentType inputEnvironment, boolean useSecured)
	{
		SIF3Session sif3Session = envOps.createSession(inputEnvironment, useSecured);
		
		return updateSessionAndExtractEnvironment(sif3Session);
	}

	/**
	 * Returns TRUE if the provider knows an environment for the given session token. If no environment is known for 
	 * the given session token then FALSE is returned.
	 * 
	 * @param sessionToken The token to check for.
	 * 
	 * @return See desc.
	 */
	public boolean existsEnvironmentForSessionToken(String sessionToken)
	{
		return (getSessionBySessionToken(sessionToken) != null);
	}
	
	public AppEnvironmentTemplate getTemplateInfo(EnvironmentKey envKey)
	{
		return envOps.getTemplateInfo(envKey);
	}

	/**
	 * This method loads the environment from the workstore. Before it is loaded it checks if it does already exist. If it 
	 * doesn't then null is returned, otherwise the environment is returned. Note if it doesn't exist it WON'T create it. 
	 * To create the environment from a template then the 'createAndStoreEnvIronment(...)' method in this class must be called.
	 * 
	 * @param environmentKey solutionID Mandatory, applicationKey Mandatory, userToken Optional, instanceID Optional: 
	 * @param useSecured
	 * 
	 * @return see Desc.
	 * 
	 * @throws IllegalArgumentException Any of the mandatory parameters is null or empty.
	 * @throws PersistenceException Could not access the underlying workstore.
	 */
	public EnvironmentType getEnvironmentFromWorkstore(EnvironmentKey environmentKey, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		return envOps.loadEnvironmentFromWorkstore(environmentKey, useSecured);
	}
	

	/**
	 * This method will load an environment for the given session token from the environment store . This is used to get an
	 * environment rather than creating one. If no environment for the given session token exists in the environment store then null 
	 * is returned.
	 * 
	 * @param sessionToken The session token based on which the environment shall be returned.
	 * @param useSecured Indicate if the environment to load should use secured (TRUE) service URIs
	 *                   (https://...) or unsecured URIs (http://...)
	 * 
	 * @return See desc.
	 */
	public EnvironmentType reloadEnvironmentBySessionToken(String sessionToken, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		SIF3Session sif3Session = envOps.loadSessionFromWorkstore(sessionToken, useSecured);

		return updateSessionAndExtractEnvironment(sif3Session);
		
	}
			
	/**
	 * This method will remove an environment identified by the sessioToken permanently from the environment store and 
	 * provider.
	 * 
	 * @param sessionToken The session token for which the environment shall be removed.
	 * 
	 * @return See desc.
	 */
	public boolean removeEnvironmentBySessionToken(String sessionToken)
	{
		SIF3Session sif3Session = getSessionBySessionToken(sessionToken);
		if (sif3Session != null)
		{
			// First we remove it from the Environment store
			if (envOps.removeEnvFromWorkstoreBySessionToken(sessionToken))
			{
				// now remove it from the session store
				sessions.remove(sessionToken);
				return true;
			}
			else
			{
				return false; // error already logged.
			}
		}
		else
		{
			logger.warn("There is no environment for given session token. Cannot delete. No action taken");
			return true;
		}
	}
	
	@Override
    public String toString()
    {
	    return "DirectProviderEnvironmentManager [sessions=" + this.sessions + "]";
    }

	/*---------------------*/
	/*-- Private methods --*/
	/*---------------------*/
	private DirectProviderEnvironmentManager(String adapterFileNameWithoutExt)
	{
		super();
		this.adapterFileNameWithoutExt = adapterFileNameWithoutExt;
		this.envOps = new DirectProviderEnvStoreOps(adapterFileNameWithoutExt);
	}
	
	/*
	 * This method expects that the environmentXML is set in the given session. It will extract it, use its data to update the
	 * sif3session's services and store the updated the sif3session in the session store. The extracted environment is then 
	 * returned.
	 */
	private EnvironmentType updateSessionAndExtractEnvironment(SIF3Session sif3Session)
	{
		if (sif3Session != null) // we are good and have a session.
		{
			EnvironmentType newEnv = envOps.loadEnvironmentFromString(sif3Session.getEnvironmentXML());
			
			// Extract the service info and store it in an easy accessible way in the session.
			SIFSessionUtils.loadServiceInfoForSession(sif3Session, newEnv);

			// XML no longer needed.
			sif3Session.setEnvironmentXML(null);

			// add to session Store
			sessions.put(sif3Session.getSessionToken(), sif3Session);
			
			return newEnv;
		}
		return null;
	}
}
