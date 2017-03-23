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

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.StringUtils;
import sif3.common.exception.PersistenceException;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.security.TokenInfo;
import sif3.common.persist.model.AppEnvironmentTemplate;
import sif3.common.persist.model.SIF3Session;
import sif3.infra.common.env.ops.DirectProviderEnvStoreOps;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.utils.SIFSessionUtils;

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
    protected final Logger logger = LoggerFactory.getLogger(getClass());

	/* The property file name of this consumer */
	private String adapterFileNameWithoutExt = null;
	
	/* The environment store operation class to be used with this manager. */
	private DirectProviderEnvStoreOps envOps = null;
	
	/* Key=SessionToken for environment & consumer, Data: SIF3Session. Used for BASIC and SIF_HMACSHA256 security */
	private HashMap<String, SIF3Session> sessions = new HashMap<String, SIF3Session>();

	/* Key=SecurityToken for environment & consumer, Data: SessionToken relating to securityToken. Used for Bearer security tokens*/
	private HashMap<String, String> secTokenSession = new HashMap<String, String>();

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
	 * 
	 * @see  sif3.infra.common.interfaces.EnvironmentManager#getSessionBySecurityToken(java.lang.String)
	 */
	@Override
	public SIF3Session getSessionBySecurityToken(String securityToken)
	{
		if (securityToken != null)
		{
			return getSessionBySessionToken(secTokenSession.get(securityToken));
		}
		return null; // no session or invalid securityToken
	}

	/* 
	 * (non-Javadoc)
	 * @see sif3.infra.common.interfaces.EnvironmentManager#updateSessionSecurityInfo(java.lang.String, java.lang.String, java.util.Date)
	 */
  	@Override
  	public boolean updateSessionSecurityInfo(String sessionToken, String securityToken, Date securityExpiryDate)
  	{
  		// First we must update the session store (DB)
  		if (envOps.updateSessionSecurityInfo(sessionToken, securityToken, securityExpiryDate))
  		{
  			//now we update the values in the session cache
  			SIF3Session sif3Session = getSessionBySessionToken(sessionToken);
  			if (sif3Session != null)
  			{
  				String oldSecurityToken = sif3Session.getSecurityToken();
  				sif3Session.setSecurityToken(securityToken);
  				sif3Session.setSecurityTokenExpiry(securityExpiryDate);
  				
  				// Refresh cache
  				sessions.put(sessionToken, sif3Session);
  				
  				// Potentially we must remove old security token - session token association
  				if (StringUtils.notEmpty(oldSecurityToken)) // should be in the security - session association cache
  				{
  					secTokenSession.remove(oldSecurityToken);
  				}

  				// Set new association
				if (StringUtils.notEmpty(securityToken))
  				{
  					secTokenSession.put(securityToken, sessionToken);
  				}

  				return true;
  			}
  			else // really odd!
  			{
  				logger.debug("No session found in session cache despite that there is one in the workstore.");
  				return false;
  			}
  		}
  		else
  		{
  			return false; // error already logged.
  		}
  		
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
	 * information according to the SIF3 spec is returned (i.e. Session Token, Environment Id etc). The associated
	 * sif3 session is stored in the internal session cache.
	 * 
	 * @param inputEnvironment The basic information required by the environment provider to create the environment for the consumer.
	 * @param tokenInfo Information related to the security token. Can be used to store expiry date and a security token related to the
	 *                  session to be created or updated.
	 * @param useSecured Indicate if the environment to load should use secured (TRUE) service URIs (https://...) or
	 *                   unsecured URIs (http://...)
	 * 
	 * @return See desc.
	 */
	public EnvironmentType createOrUpdateEnvironment(EnvironmentType inputEnvironment, TokenInfo tokenInfo, boolean useSecured)
	{
		SIF3Session sif3Session = envOps.createSession(inputEnvironment, tokenInfo, useSecured);
		
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
	
	/**
	 * Returns TRUE if the provider knows an environment for the given security token. If no  environment is known 
	 * for the given security token then FALSE is returned.
	 * 
	 * @param securityToken The token to check for.
	 * 
	 * @return See desc.
	 */
	public boolean existsEnvironmentForSecurityToken(String securityToken)
	{
		return (getSessionBySecurityToken(securityToken) != null);
	}

	public AppEnvironmentTemplate getTemplateInfo(EnvironmentKey envKey)
	{
		return envOps.getTemplateInfo(envKey);
	}

	/**
	 * This method loads the environment from the workstore. Before it is loaded it checks if it does already exist. If it 
	 * doesn't then null is returned, otherwise the environment is returned. Note if it doesn't exist it WON'T 
	 * create it. To create the environment from a template then the 'createAndStoreEnvIronment(...)' method in 
	 * this class must be called. Note this method will only get the environment from the work store but it won't
	 * add it to the internal session cache.
	 * 
	 * @param environmentKey solutionID Mandatory, applicationKey Mandatory, userToken Optional, instanceID Optional: 
	 * @param tokenInfo Information related to the security token. Can be used to store expiry date and a security token related to the
	 *                  session to be created or updated.
	 * @param useSecured Indicate if the environment to load should use secured (TRUE) service URIs
	 *                   (https://...) or unsecured URIs (http://...)
	 * 
	 * @return see Desc.
	 * 
	 * @throws IllegalArgumentException Any of the mandatory parameters is null or empty.
	 * @throws PersistenceException Could not access the underlying workstore.
	 */
	public EnvironmentType getEnvironmentFromWorkstore(EnvironmentKey environmentKey, TokenInfo tokenInfo, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		return envOps.loadEnvironmentFromWorkstore(environmentKey, tokenInfo, useSecured);
	}
	

	/**
	 * This method will load an environment for the given session token from the environment store. This is used to get an
	 * environment rather than creating one. If no environment for the given session token exists in the environment store then null 
	 * is returned.
	 * 
	 * @param sessionToken The session token based on which the environment shall be returned.
	 * @param tokenInfo Information related to the security token. Can be used to store expire date and a security token related to the
	 *                  session to be created or updated.
	 * @param useSecured Indicate if the environment to load should use secured (TRUE) service URIs
	 *                   (https://...) or unsecured URIs (http://...)
	 * 
	 * @return See desc.
	 * 
	 * @throws IllegalArgumentException If sessionToken is null.
	 * @throws PersistenceException Failed to access the underlying environment store.
	 */
	public EnvironmentType reloadEnvironmentBySessionToken(String sessionToken, TokenInfo tokenInfo, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		SIF3Session sif3Session = envOps.loadSessionFromWorkstore(sessionToken, tokenInfo, useSecured);

		return updateSessionAndExtractEnvironment(sif3Session);	
	}

	/**
	 * This method will load an environment for the given Token Info from the environment store. This is used to get an
	 * environment rather than creating one. If no environment for the given Token Info exists in the environment store 
	 * then null is returned.
	 * @param tokenInfo Information about the security token for which an environment shall be loaded from
	 *                  the environment store.
	 * @param useSecured Indicate if the environment to load should use secured (TRUE) service URIs
	 *                   (https://...) or unsecured URIs (http://...)
	 *                   
	 * @return See desc.
	 * 
	 * @throws IllegalArgumentException If tokenInfo is null or no security token is not set.
	 * @throws PersistenceException Failed to access the underlying environment store.
	 */
	public EnvironmentType reloadEnvironmentForSecurityToken(TokenInfo tokenInfo, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		SIF3Session sif3Session = envOps.loadSessionFromWorkstore(tokenInfo, useSecured);

		return updateSessionAndExtractEnvironment(sif3Session);	
	}

	/**
	 * This method will load an environment for the environmentKey from the environment store. This is used to get 
	 * an environment rather than creating one. If no environment for the given environmentKey exists then null is
	 * returned. If an environment does exist it is loaded and with its associated sif3 session and finally added 
	 * to the internal session cache.
	 * 
	 * @param environmentKey The environmentKey based on which the sif3 session shall be returned.
	 * @param tokenInfo Information related to the security token. Can be used to store expire date and a security
	 *                  token related to the session to be created or updated.
	 * @param useSecured Indicate if the environment to load should use secured (TRUE) service URIs
	 *                   (https://...) or unsecured URIs (http://...)
	 * 
	 * @return See desc.
	 * 
	 * @throws IllegalArgumentException If sessionToken is null.
	 * @throws PersistenceException Failed to access the underlying environment store.
	 */
	public EnvironmentType getEnvironmentByEnvKey(EnvironmentKey environmentKey, TokenInfo tokenInfo, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		SIF3Session sif3Session = envOps.loadAndUpdateSession(environmentKey, tokenInfo, useSecured);

		return updateSessionAndExtractEnvironment(sif3Session);	
	}

	/**
	 * This method will load an environment for the given environmentID from the environment store. This is used to 
	 * get an environment rather than creating one. If no environment for the given environmentID exists then null 
	 * is returned. If an environment does exist it is loaded and with its associated sif3 session and finally added 
	 * to the internal session cache.
	 * 
	 * @param environmentID The environmentID based on which the sif3 session shall be returned.
	 * @param tokenInfo Information related to the security token. Can be used to store expire date and a security
	 *                  token related to the session to be created or updated.
	 * @param useSecured Indicate if the environment to load should use secured (TRUE) service URIs
	 *                   (https://...) or unsecured URIs (http://...)
	 * 
	 * @return See desc.
	 * 
	 * @throws IllegalArgumentException If sessionToken is null.
	 * @throws PersistenceException Failed to access the underlying environment store.
	 */
	public EnvironmentType getEnvironmentByEnvironmentID(String environmentID, TokenInfo tokenInfo, boolean useSecured) throws IllegalArgumentException, PersistenceException
	{
		SIF3Session sif3Session = envOps.loadAndUpdateSession(environmentID, tokenInfo, useSecured);

		return updateSessionAndExtractEnvironment(sif3Session);	
	}

	/**
	 * This method will remove a sif3 session identified by the sessioToken from the session cache and
	 * optionally from the environment store.
	 * 
	 * @param sessionToken The session token for which the environment shall be removed.
	 * @param permanentRemoval TRUE: Remove environment from session cache AND session store.
	 *                         FALSE: Only remove session from the internal session cache but leave it
	 *                                in the session store (DB).
	 * 
	 * @return See desc.
	 */
	public boolean removeEnvironmentBySessionToken(String sessionToken, boolean permanentRemoval)
	{
		SIF3Session sif3Session = getSessionBySessionToken(sessionToken);
		if (sif3Session != null)
		{
			// Check if we need to remove session permanently
			if (permanentRemoval)
			{
				if (!envOps.removeEnvFromWorkstoreBySessionToken(sessionToken))
				{
					return false; // error already logged. Don't remove session from cache
				}
			}

			// At this point we only have to remove the session from the session cache.
		
			// Remove security token - session token link.
			if (StringUtils.notEmpty(sif3Session.getSecurityToken()))
			{
				secTokenSession.remove(sif3Session.getSecurityToken());
			}
		  
			// now remove it from the session store
			sessions.remove(sessionToken);
			return true;
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
			
			// Ok here we have to be careful. If external security services are used there is the possibility that new tokens are used for each
			// separate access. This means that we need to check first if there is a security token, although different, already linked to a session.
			SIF3Session currentSIF3Session = sessions.get(sif3Session.getSessionToken()); 
			
			//now we can add/replace the current session in session store
			sessions.put(sif3Session.getSessionToken(), sif3Session);

			// Check if we had a session and if it had a security token
			if ((currentSIF3Session != null) && (StringUtils.notEmpty(currentSIF3Session.getSecurityToken())))
			{
			    // remove this
			    secTokenSession.remove(currentSIF3Session.getSecurityToken());
			}
			
			// link session to security token if it is available
			if (StringUtils.notEmpty(sif3Session.getSecurityToken()))
			{
			  secTokenSession.put(sif3Session.getSecurityToken(), sif3Session.getSessionToken());
			}
			
			return newEnv;
		}
		return null;
	}
}
