/*
 * BaseEnvironmentStoreOperations.java
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

package sif3.infra.common.env.ops;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.FileReaderWriter;
import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.security.TokenInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.common.persist.service.SIF3SessionService;
import sif3.common.utils.FileAndFolderUtils;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.types.AdapterEnvironmentStore;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.model.EnvironmentType;

/**
 * Helper class for some operations required by the Environment Store (Consumer or Provider). Only used internal to the framework.
 * Since the current version (May 2014) of the environment store is file based for environment templates and DB based for the actual
 * storage of a runtime environment/session data, most of the operations are a mixture of these operations. With this class that complexity
 * is abstracted to the higher level of the framework.
 * 
 * @author Joerg Huber
 *
 */
public class AdapterBaseEnvStoreOperations
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private AdapterEnvironmentStore environmentStore = null;

	/**
	 * Constructor
	 * 
	 * @param adapterFileNameWithoutExt The property file name for which the Store Operations shall be instantiated.
	 */
	public AdapterBaseEnvStoreOperations(String adapterFileNameWithoutExt)
	{
		this.environmentStore = new AdapterEnvironmentStore(adapterFileNameWithoutExt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.infra.common.interfaces.ClientEnvStoreOperations#getEnvironmentInfo()
	 */
    public EnvironmentInfo getEnvironmentInfo()
    {
	    return getEnvironmentStore().getEnvironment();
    }

	/*
	 * (non-Javadoc)
	 * @see sif3.infra.common.interfaces.ClientEnvStoreOperations#getServiceProperties()
	 */
    public AdvancedProperties getServiceProperties()
    {
	    return getEnvironmentStore().getAdapterProperties();
    }

	protected AdapterEnvironmentStore getEnvironmentStore()
    {
    	return this.environmentStore;
    }
	
	public boolean existEnvironmentTemplate(String templateFileName, AdapterType adapterType, sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType envType)
	{
		return FileAndFolderUtils.doesFileExist(getFullTemplateFileName(templateFileName, adapterType, envType));
	}

	/**
	 * 
	 * @param envFileName The name of the environment file to load from template directory.
	 * 
	 * @return Null => Failed to load data due to some error. See error log for details.
	 */
	public EnvironmentType loadTemplateEnvironmentData(String envFileName, AdapterType adapterType, sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType envType)
	{
		return loadEnvironmentDataFromFile(getFullTemplateFileName(envFileName, adapterType, envType));
	}

	/**
	 * Returns a SIF3Session for the given session token. If there is no such session then null is returned.
	 * 
	 * @param sessionToken The sessionToken for which the SIF3 session shall be loaded and returned. 
	 * 
	 * @return See Desc.
	 * 
	 * @throws IllegalArgumentException sessionToken is null or empty.
	 * @throws PersistenceException Could not access the underlying workstore.
	 */
	public SIF3Session getSIF3SessionBySessionToken(String sessionToken, AdapterType adapterType, SIF3SessionService service) throws IllegalArgumentException, PersistenceException
	{
		return service.getSessionBySessionToken(sessionToken, adapterType);
	}

	/**
	 * Returns a SIF3Session for the given security token. If there is no such session then null is returned.
	 * 
	 * @param securityToken The securityToken for which the SIF3 session shall be loaded, updated and returned. 
	 * 
	 * @return See Desc.
	 * 
	 * @throws IllegalArgumentException sessionToken is null or empty.
	 * @throws PersistenceException Could not access the underlying workstore.
	 */
	public SIF3Session getSIF3SessionBySecurityToken(String securityToken, AdapterType adapterType, SIF3SessionService service) throws IllegalArgumentException, PersistenceException
	{
		return service.getSessionBySecurityToken(securityToken, adapterType);
	}

	/**
	 * Load a session/environment from environment store for the given environment key and adapter type. This will only attempt to 
	 * load the environment from the environment store but won't create it if it doesn't exist. If it doesn't exist then null is 
	 * returned. Null is also returned if there is an issue accessing the underlying environment store. In that case an error shall 
	 * also be logged.
	 * 
	 * @param environmentKey solutionID: Mandatory, applicationKey: Mandatory, userToken: Optional, instanceID: Optional 
	 * @param adapterType Adapter Type for which the environment key is for.
	 * @param service The service to load the actual session from the workstore.
	 * 
	 * @return See desc.
	 */
	public SIF3Session loadSession(EnvironmentKey environmentKey, AdapterType adapterType, SIF3SessionService service)
	{
		try
        {
	        SIF3Session session = service.getSessionBySolutionAppkeyUserInst(environmentKey, adapterType);
	        if (session == null)// Session does not exist
	        {
	        	logger.error("No SIF3 Session exists in workstore for Adapter Type="+adapterType.name()+" and "+environmentKey.getEnvironmentName()+"'.");
	        	return null;
	        }
	        return session;
        }
        catch (Exception ex)
        {
        	// Error already logged. Just return null;
        	return null;
        }
	}

	/**
	 * This method will create or update an environment in the consumer/provider environment store with data from the given inputEnv. 
	 * If there is already an environment in the environment store for the given inputEnvironment then the environment store is 
	 * updated, otherwise the environment will be created in the environment store. If this operation fails an error is logged 
	 * and null is returned. This method expects that the inputEnvironment is a full environment as returned from the environment 
	 * provider including sessionToken, environmentID etc. The Session Information is then returned to the caller, and the 
	 * environment is stored as an XML in the environmentXML property.<br/><br/>
	 * 
	 * NOTE: This method is only intended to be used by a consumer or a provider in a brokered environment. A provider in a DIRECT
	 *       environment should not really use that method. 
	 * 
	 * @param inputEnv full environment as returned from the environment provider including sessionToken, environmentID
	 * @param tokenInfo Information related to the security token. Can be used to store expire date and a security token related 
	 *                  to the session to be created or updated. Since this is not part of the environment XML/POJO it must be given to 
	 *                  this method as a separate parameter.
	 * @param adapterType Adapter Type for which the environment is for.
	 * @param service The service to load the actual session from the workstore.
	 * 
	 * @return See desc.
	 */
	public SIF3Session createOrUpdateSession(EnvironmentType inputEnv, TokenInfo tokenInfo, AdapterType adapterType, SIF3SessionService service)
	{
		//getEnvironmentStore().getEnvironment();
		if ((inputEnv == null) || (inputEnv.getApplicationInfo() == null))
		{
			logger.error("The environment provider environment is null or does not have the Application Info set. Environment/SIF3 Session cannot be created.");
			return null;
		}
//		if (StringUtils.isEmpty(inputEnv.getSolutionId()) || StringUtils.isEmpty(inputEnv.getApplicationInfo().getApplicationKey()))
		if (StringUtils.isEmpty(inputEnv.getApplicationInfo().getApplicationKey()))
		{
//			logger.error("The application key and/or the solution id in the environment provider environment is null or empty. Environment/SIF3 Session cannot be created.");
			logger.error("The application key  in the environment provider environment is null or empty. Environment/SIF3 Session cannot be created.");
			return null;
		}

		try
		{
			// First we check if a session for this environment does already exist
			SIF3Session sif3Session = service.getSessionByEnvID(inputEnv.getId(), adapterType);
			if (sif3Session != null) // we already have such a session => Just update it with the latest input environment
			{
				// There is the possibility that the password has been reset since the last access. => Update it just in case
				sif3Session.setPassword(getEnvironmentStore().getEnvironment().getPassword());
				
				// Also add the security token info if it is available
				if (tokenInfo != null)
				{
					sif3Session.setSecurityToken(tokenInfo.getToken());
					sif3Session.setSecurityTokenExpiry(tokenInfo.getTokenExpiryDate());
				}
				
				// Fingerprint is now available or may have changed. Need to update
				sif3Session.setFingerprint(inputEnv.getFingerprint());
			}
			else
			{
				// If we get here then we don't have an existing session. => Create one from scratch using the input environment information.
				sif3Session = new SIF3Session(new EnvironmentKey(inputEnv.getSolutionId(), inputEnv.getApplicationInfo().getApplicationKey(), inputEnv.getUserToken(), inputEnv.getInstanceId()));
				sif3Session.setPassword(getEnvironmentStore().getEnvironment().getPassword());
				sif3Session.setAdapterName(inputEnv.getConsumerName());
				sif3Session.setAdapterType(adapterType.name());
				sif3Session.setSessionToken(inputEnv.getSessionToken());
				sif3Session.setEnvironmentID(inputEnv.getId());
				sif3Session.setFingerprint(inputEnv.getFingerprint());

				// Also add the security token info if it is available
				if (tokenInfo != null)
				{
					sif3Session.setSecurityToken(tokenInfo.getToken());
					sif3Session.setSecurityTokenExpiry(tokenInfo.getTokenExpiryDate());
				}

				if (adapterType == AdapterType.CONSUMER) // set queue strategy
				{
				    //TODO: JH - In future we might allow different queue strategies for events and delayed responses. In this
				    //           case we need to stor this in the DB separately. For now we only allow ADAPTER so one column
				    //           is fine.
				    // Use Event Queue Strategy.
					sif3Session.setQueueStrategy(((ConsumerEnvironment)getEnvironmentStore().getEnvironment()).getEventConfig().getQueueStrategy().name());
				}
			}

			// All is done we can save the session info and environment data to the workstore and proceed.
			if (storeEnvDataToWorkstore(sif3Session, inputEnv, service))
			{
				// Store the latest session with this environment info object and finally return the session.
				return sif3Session;
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
	 * This method will set the security token and expire date for a given session in the session store. If 
	 * the sessionToken doesn't exist then no action is taken and false is returned. If the underlying session
	 * store cannot be updated due to internal errors then false is returned and an error is logged.
	 * 
	 * @param sessionToken The sessionToken for which the security information shall be updated.
	 * @param securityToken The security token with witch the sessionToken shall be associated.
	 * @param securityExpiryDate The expire date to be set for the security token. Can be null.
	 * @param adapterType Adapter Type for which the environment is for.
	 * @param service The service to load the actual session from the workstore.
	 * 
	 * @return See desc.
	 */
	public boolean updateSessionSecurityInfo(String sessionToken, String securityToken, Date securityExpiryDate, AdapterType adapterType, SIF3SessionService service)
	{
		if (StringUtils.notEmpty(sessionToken))
		{
			try
			{
				SIF3Session sif3Session = getSIF3SessionBySessionToken(sessionToken, adapterType, service);
				if (sif3Session != null)
				{
					sif3Session.setSecurityToken(securityToken);
					sif3Session.setSecurityTokenExpiry(securityExpiryDate);
					service.save(sif3Session);
					return true;
				}
				else
				{
					logger.debug("No session exists for sessionToken = "+sessionToken+". Security Information not updated. No action taken.");
					return false;
				}
			}
			catch (PersistenceException ex)
			{
				logger.error(ex.getMessage(), ex);
				return false;
			}
		}
		else
		{
			logger.error("SessionToken is null or empty. Cannot update security info.");
			return false;
		}
	}

	/**
	 * This method takes the given XML environment string and converts it into a Infrastructure Environment Type. If the string is null or
	 * invalid then an error is logged and null is returned. 
	 * 
	 * @param envrionmentData XML formatted environment data as of the SIF3 spec.
	 * 
	 * @return See desc.
	 */
	public EnvironmentType loadEnvironmentFromString(String envrionmentData)
	{
		if (envrionmentData != null)
		{
			InfraUnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
			try
			{
				return (EnvironmentType) unmarshaller.unmarshalFromXML(envrionmentData, EnvironmentType.class);
			}
			catch (Exception ex)
			{
				logger.error("Failed to successfully parse the XML content:\n"+envrionmentData);
				return null;
			}
		}
		else
		{
			logger.error("The provided environment data to parse is null.");
			return null;
		}
	}

	/**
	 * Store the environment data (inclusive session info) to workstore for the given SIF3 session. All property of the session
	 * parameter remain untouched by this method with the exception of the EnvironmentXML that is replaced with the XML version
	 * of the environment parameter
	 * 
	 * @param session All the data associated with this session, including session token, environmentID, environmentXML etc.
	 * @param environment The environment that is marshalled to XML and the stored with the session.
	 * @param service The service to store the actual session to the workstore.
	 * 
	 * @return TRUE: stored successfully. FALSE: not stored. See error log for details.
	 */
	protected boolean storeEnvDataToWorkstore(SIF3Session session, EnvironmentType environment, SIF3SessionService service)
	{
		try
		{
			InfraMarshalFactory marshaller = new InfraMarshalFactory();
			session.setEnvironmentXML(marshaller.marshalToXML(environment));
			service.save(session);
			return true;
		}
		catch (Exception ex) // error already logged
		{
			return false;
		}
	}

	/**
	 * This removes the environment data stored in the environment store. This operation should be used with care! A deletion 
	 * of an environment means that it is no longer recoverable and a consumer/provider can no longer connect to the 
	 * environment because the information relating to that environment are lost for good! A loss of an environment also means
	 * a loss of all data that relate to an environment such as SIF events, responses etc. All things that are potentially 
	 * stored in queues.
	 * 
	 * @param sessionToken Uniquely identifies the environment to be removed.
	 * @param service The service to remove the actual session from the workstore.
	 * 
	 * @return TRUE: Operation successful. FALSE: Error occurred. See error log for details.
	 * 
	 */
	protected boolean removeEnvFromWorkstoreBySessionToken(String sessionToken, AdapterType adapterType, SIF3SessionService service)
	{
		try
		{
			service.removeSessionBySessionToken(sessionToken, adapterType);
			return true;
		}
		catch (Exception ex) // error already logged.
		{
			return false;
		}
	}

	/**
	 * As above but deletion occurs based on environment ID.
	 * 
	 * @param environmentID  Uniquely identifies the environment to be removed.
	 * @param service The service to remove the actual session from the workstore.
	 * 
	 * @return TRUE: Operation successful. FALSE: Error occurred. See error log for details.
	 */
	protected boolean removeEnvFromWorkstoreByEnvID(String environmentID, AdapterType adapterType, SIF3SessionService service)
	{
		try
		{
			service.removeSessionByEnvironmentID(environmentID, adapterType);
			return true;
		}
		catch (Exception ex) // error already logged.
		{
			return false;
		}
	}
	
	protected String getXMLFromFile(String fileName)
	{
		return FileReaderWriter.getFileContent(fileName);
	}

	/**
	 * @param fullName Full name of file (path, name and extension. It is expected this file to have XML content.
	 * 
	 * @return Null => Failed to load data due to some error. See error log for details.
	 */
	protected EnvironmentType loadEnvironmentDataFromFile(String fullFileName)
	{
		if (FileAndFolderUtils.doesFileExist(fullFileName))
		{
			String xmlStr = getXMLFromFile(fullFileName);
			if (xmlStr != null)
			{
				return loadEnvironmentFromString(xmlStr);
			}
			else
			{
				logger.error("The XML file "+fullFileName+" cannot be read. Check permissions and content.");				
				return null;
			}
		}
		else
		{
			logger.error("There is no XML file: "+fullFileName);
			return null;
		}
	}
		
	protected String getFullTemplateFileName(String envFileName, AdapterType adapterType, sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType envType)
	{
		return getFullFileName(getEnvironmentStore().getFullTemplateDirName(adapterType==AdapterType.CONSUMER, envType), envFileName);
	}

	protected String getFullFileName(String path, String envFileName)
	{
		return  path+"/"+envFileName;
	}
	
	/*
	 * This method must only be called for Brokered Providers and standard Consumers to update the SIF3Session object with some additional, 
	 * transient data from the properties file or other sources.
	 */
	protected void updateSIF3SessionForClients(SIF3Session sif3Session)
	{
	    if (sif3Session != null) // only the case for create environment!
	    {
	        sif3Session.setAuthenticationMethod(getEnvironmentInfo().getAuthMethod());
	    }
	}
}
