/*
 * ConsumerEnvironmentManager.java
 * Created: 03/09/2013
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

package sif3.infra.common.env.mgr;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.StringUtils;
import sif3.common.exception.PersistenceException;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.security.TokenInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.infra.common.env.ops.ConsumerEnvironmentStoreOperations;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.interfaces.ClientEnvironmentManager;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.InfrastructureServiceType;
import sif3.infra.common.model.InfrastructureServicesType;
import sif3.infra.common.utils.SIFSessionUtils;

/**
 * This class deals with the main functions relating to the environments that need to be managed on a consumer. A consumer connects, 
 * creates or removes environments. This functionality must be maintained in a consumer but also communicated to the environment
 * provider. This class ensures that these two components are kept in sync and communicate appropriate actions with each other. It is
 * not expected that high level classes of this framework would interact with the environment manager directly.
 * 
 * @author Joerg Huber
 */
public class ConsumerEnvironmentManager implements ClientEnvironmentManager
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

	/* The property file name of this consumer */
	private String adapterFileNameWithoutExt = null;
	
	/* The environment store operation class to be used with this manager. */
	private ConsumerEnvironmentStoreOperations envOps = null;

	/* Active SIF3 Session of this consumer. There is only one. */
	private SIF3Session sif3Session = null;

	private static ConsumerEnvironmentManager instance = null;

	/**
	 * Initialises an instance of the Consumer Environment Manager.
	 * 
	 * @param adapterFileNameWithoutExt  The name of the property file to be used for this environment manager.
	 */
	public static ConsumerEnvironmentManager initialse(String adapterFileNameWithoutExt)
	{
		if (instance == null)
		{
			instance = new ConsumerEnvironmentManager(adapterFileNameWithoutExt);
		}
		
		return instance;
	}

	/**
	 * Returns an instance of the Provider Environment Manager.
	 * 
	 * @return See Desc.
	 */
	public static ConsumerEnvironmentManager getInstance()
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

	/* (non-Javadoc)
     * @see sif3.infra.common.interfaces.EnvironmentManager#getSessionBySessionToken(java.lang.String)
     */
    @Override
    public SIF3Session getSessionBySessionToken(String sessionToken)
    {
	    if ((getSIF3Session() != null) && (StringUtils.notEmpty(sessionToken)))
	    {
	    	if (sessionToken.equals(getSIF3Session().getSessionToken()))
	    	{
	    		return getSIF3Session();
	    	}
	    	else
	    	{
	    		logger.error("The sessionToken for the session in this Consumer Environment Manager ("+getSIF3Session().getSessionToken()+") is different to the sessionToken from the request (" + sessionToken +"). User is not allowed to access this session.");
	    		return null;
	    	}
	    }
	    return null; // no session or invalid sessionToken
    }

    /*
     * (non-Javadoc)
     * @see sif3.infra.common.interfaces.EnvironmentManager#getSessionBySecurityToken(java.lang.String)
     */
    @Override
    public SIF3Session getSessionBySecurityToken(String securityToken)
    {
      if ((getSIF3Session() != null) && (StringUtils.notEmpty(securityToken)))
      {
        if (securityToken.equals(getSIF3Session().getSecurityToken()))
        {
          return getSIF3Session();
        }
        else
        {
          logger.error("The securityToken for the session in this Consumer Environment Manager ("+getSIF3Session().getSecurityToken()+") is different to the securityToken from the request (" + securityToken +"). User is not allowed to access this session.");
          return null;
        }
      }
      return null; // no session or invalid sessionToken
    }
    
	/* (non-Javadoc)
     * @see sif3.infra.common.interfaces.EnvironmentManager#updateSessionSecurityInfo(java.lang.String, java.lang.String, java.util.Date)
     */
    @Override
    public boolean updateSessionSecurityInfo(String sessionToken, String securityToken, Date securityExpiryDate)
    {
	    return envOps.updateSessionSecurityInfo(sessionToken, securityToken, securityExpiryDate);
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

    /*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.env.ClientEnvironmentManager#getSIF3Session()
	 */
	public SIF3Session getSIF3Session()
	{
		return sif3Session;
	}
	  
	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.env.ClientEnvironmentManager#loadTemplateEnvironmentData()
	 */
	public EnvironmentType loadTemplateEnvironmentData()
	{
		ConsumerEnvironment envInfo = getConsumerEnvrionment();
		return envOps.loadTemplateEnvironmentData(envInfo.getTemplateXMLFileName(), envInfo.getAdapterType(), envInfo.getEnvironmentType());
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.env.ClientEnvironmentManager#reloadSessionFromFromWorkstore(sif3.common.model.EnvironmentKey)
	 */
	public EnvironmentType reloadSessionFromFromWorkstore(EnvironmentKey environmentKey) throws IllegalArgumentException, PersistenceException
	{
		setSIF3Session(envOps.loadSession(environmentKey));
		if (getSIF3Session() != null) // session exists and was loaded successfully
		{
			// Extract the service info and store it in an easy accessible way in the session.
			EnvironmentType environment = envOps.loadEnvironmentFromString(getSIF3Session().getEnvironmentXML());
			SIFSessionUtils.loadServiceInfoForSession(getSIF3Session(), environment);
			populateInfrastructureURLs(environment);

			// XML no longer needed.
			getSIF3Session().setEnvironmentXML(null);

			return environment;
		}
		else
		{
			return null; // error already logged.
		}
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.env.ClientEnvironmentManager#createOrUpdateEnvironment(sif3.infra.common.model.EnvironmentType)
	 */
	public EnvironmentType createOrUpdateEnvironment(EnvironmentType inputEnvironment, TokenInfo tokenInfo)
	{
		setSIF3Session(envOps.createOrUpdateSession(inputEnvironment, tokenInfo));
		if (getSIF3Session() != null) // creation was successful
		{
			// Extract the service info and store it in an easy accessible way in the session.
			SIFSessionUtils.loadServiceInfoForSession(getSIF3Session(), inputEnvironment);
			populateInfrastructureURLs(inputEnvironment);

			// XML no longer needed.
			getSIF3Session().setEnvironmentXML(null);

			return inputEnvironment;
		}
		else
		{
			return null; // error already logged.
		}
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.env.ClientEnvironmentManager#disconnect()
	 */
	public void disconnect()
	{   
		ConsumerEnvironment envInfo = getConsumerEnvrionment();
		if (!envInfo.getIsConnected())
		{
			logger.debug("The consumer "+envInfo.getEnvironmentKey().getEnvironmentName()+" is not connected. No action taken.");
		}
		envInfo.setConnected(false);
		setSIF3Session(null);
		envInfo.clearConnectorBaseURIs();
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.env.ClientEnvironmentManager#removeEnvironment(sif3.common.model.EnvironmentKey)
	 */
	public boolean removeEnvironment(EnvironmentKey environmentKey)
	{
		if (existsSIF3SessionInSessionStore())
		{
			// check if the session is for the given environment key.
			if ((environmentKey == null) || (!environmentKey.equals(sif3Session))) // They are NOT the same. That is odd! should not happen
			{
				logger.error("Attempted to remove a environment for an active session with the environment key: " + getSIF3Session().getEnvironmentName() + ". Deletion request is for environment key: " + environmentKey.getEnvironmentName() + ". Inconsistent data! No action taken.");
				return false;
			}
		}
		else
		{
			// Session is not in session store. => Attempt to load it and if that is successful we remove it.
			setSIF3Session(envOps.loadSession(environmentKey));
		}

		// If we get here we either have a valid session in the store for which the environment must removed or there is no 
		// environment in the workstore for the given environmentKey.
		if (existsSIF3SessionInSessionStore())
		{
			if (envOps.removeEnvFromWorkstoreBySessionToken(getSIF3Session().getSessionToken()))
			{
				// remove session from session store as well.
				disconnect();
				return true;

			}
			else // that is odd! Could not remove session in session store.
			{
				return false;
			}
		}
		else
		{
			return true; // no such session/environment => no action taken.
		}
	}

	@Override
    public String toString()
    {
      return "ConsumerEnvironmentManager [sif3Session=" + sif3Session + "]";
    }

	/*---------------------*/
	/*-- Private methods --*/
	/*---------------------*/
	/*
	 * Constructor: Create an EvironmentStore manager for the given properties file.
	 */
	private ConsumerEnvironmentManager(String adapterFileNameWithoutExt)
	{
		super();
		this.adapterFileNameWithoutExt = adapterFileNameWithoutExt;
		this.envOps = new ConsumerEnvironmentStoreOperations(adapterFileNameWithoutExt);
	}
	
	private boolean existsSIF3SessionInSessionStore()
	{
		return sif3Session != null;
	}

	private void setSIF3Session(SIF3Session sif3Session)
	{
		this.sif3Session = sif3Session;
	}


	private ConsumerEnvironment getConsumerEnvrionment()
	{
		return (ConsumerEnvironment)getEnvironmentInfo();
	}

	private boolean populateInfrastructureURLs(EnvironmentType environment)
	{
		boolean success = true;
		ConsumerEnvironment envInfo = getConsumerEnvrionment();
		envInfo.clearConnectorBaseURIs();
		InfrastructureServicesType infraServices = environment.getInfrastructureServices();
		if (infraServices != null)
		{
			List<InfrastructureServiceType> services = infraServices.getInfrastructureService();
			if (services.size() == 0) // that must be a problem
			{
				success = false;
			}
			else
			{
				for (InfrastructureServiceType service : services)
				{
					try
					{
						envInfo.addConnectorBaseURI(ConsumerEnvironment.ConnectorName.valueOf(service.getName().value()), new URI(service.getValue()));
					}
					catch (Exception ex)
					{
						String connectorName = (service.getName() != null) ? service.getName().value() : "Connector Name MISSING";
						logger.error("Could not add URI = " + service.getValue() + " for connector = " + connectorName + " to Consumer Environment " + envInfo.getEnvironmentName());
						success = false;
					}
				}
			}
		}
		else
		{
			logger.error("Environment data on provider incomplete. Infrastructure Service URIs are missing.");
			success = false;
		}
		return success;
	}
}
