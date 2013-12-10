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

package sif3.infra.rest.env;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.TransportHeaderProperties;
import sif3.common.utils.AuthenticationUtils;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.ConsumerEnvironmentStoreOperations;
import sif3.infra.common.env.EnvironmentStore;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.env.types.EnvironmentList;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.InfrastructureServicesType;
import sif3.infra.common.model.PropertyType;
import sif3.infra.rest.client.ClientInterface;
import sif3.infra.rest.header.RESTHeaderProperties;
import sif3.infra.rest.header.RequestHeaderConstants;

/**
 * This class deals with the main functions relating to the environments that need to be managed on a consumer. A consumer connects, 
 * creates or removes environments. This functionality must be maintained in a consumer but also communicated to the environment
 * provider. This class ensures that these two components are kept in sync and communicate appropriate actions with each other. It is
 * not expected that high level classes of this framework would interact with the environment manager directly.
 * 
 * @author Joerg Huber
 */
public class ConsumerEnvironmentManager extends BaseEnvironmentManager
{
	//protected final Logger logger = Logger.getLogger(getClass());
	
	private static final String PLURAL_ENV_URI = "environments";
	private static final String SINGLE_ENV_URI = "environment";
	
	private ConsumerEnvironmentStoreOperations envOps = null;
	private MarshalFactory marshaller = new InfraMarshalFactory();
	private UnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
	
	private static ConsumerEnvironmentManager instance = null;

	/**
	 * Returns an instance of the Consumer Environment Manager.
	 * 
	 * @param environmentStore The environment store for this instance of the Consumer Manager.
	 * 
	 * @return See Desc.
	 */
	public static ConsumerEnvironmentManager getInstance(EnvironmentStore environmentStore)
	{
		if (instance == null)
		{
			instance = new ConsumerEnvironmentManager(environmentStore);
		}

		return instance;
	}

	/**
	 * Returns a Set of known environments for the consumer. This is based on the property file for a given consumer.
	 *  
	 * @return See desc.
	 */
	public Set<String> getKnownEnvironmentNames()
	{
		return envOps.getEnvironmentStore().getEnvironments().getEnvironments().keySet();
	}
	
	/**
	 * Returns a List of environments a consumer has successfully connected to.
	 * 
	 * @return See desc.
	 */
	@SuppressWarnings("unchecked")
    public List<ConsumerEnvironment> getConnectedEnvironments()
	{
		List<ConsumerEnvironment> connectedEnvs = new ArrayList<ConsumerEnvironment>();
		for (ConsumerEnvironment env : (Collection<ConsumerEnvironment>)envOps.getEnvironmentStore().getEnvironments().getEnvironments().values())
		{
			if (env.isConnected())
			{
				connectedEnvs.add(env);
			}
		}
		return connectedEnvs;
	}
	
	/**
	 * Attempt to connects all known environments of the given environment store. The environments it will connect to is based on the
	 * consumers property file. TRUE is returned if the consumer successfully connected to ALL environments. There might still be partial
	 * success where the consumer may have connected to some but not all environments. To figure out which environments a consumer is
	 * connected to refer to the methods getConnectedEnvironments() in this class.<br/><br/>
	 * 
	 * @see sif3.infra.rest.env.ConsumerEnvironmentManager#connect(java.lang.String)
	 * 
	 * @return TRUE: Succeeded to connect to all environments. FALSE: Failed to connect to all environments.
	 */
	public boolean connectAll()
	{
		boolean allConnected = true;
		for (String envName : getKnownEnvironmentNames())
		{
			if (!connect(envName))
			{
				allConnected = false;
			}
		}
		
		if (!allConnected)
		{
			logger.error("Consumer '"+envOps.getEnvironmentStore().getAdapterFileNameWithoutExt()+"' could not connect to all environments. See previous error log entries for details.");
		}
		return allConnected;
	}
	
	/**
	 * Will disconnect the consumer from all environments. The consumer is only disconnected but the environment will still exist 
	 * for the consumer. The consumer can later re-connect to the environments without the need of creating them first.
	 * 
	 * @return Always TRUE.
	 */
	public boolean disconnectAll()
	{
		for (String envName : getKnownEnvironmentNames())
		{
			disconnect(envName);
		}
		return true;
	}
	
	/**
	 * Will disconnect the consumer from all environments and will also REMOVE all environments on the consumer as well as on the
	 * environment provider. After this call the consumer can no longer re-connect to any environments without creating them first.
	 * This method must be used with care because once an environment is removed all associated data with that environment will be
	 * removed/lost as well.
	 * 
	 * @return TRUE: If consumer has successfully disconnected and removed all environments. FALSE otherwise.
	 */
	public boolean disconnectAndRemoveAll()
	{
		boolean allDisconnected = true;
		for (String envName : getKnownEnvironmentNames())
		{
			if (!disconnectAndRemove(envName))
			{
				allDisconnected = false;
			}
		}
		
		if (!allDisconnected)
		{
			logger.error("Consumer '"+envOps.getEnvironmentStore().getAdapterFileNameWithoutExt()+"' could not disconnect and remove to all environments. See previous error log entries for details.");
		}
		return allDisconnected;
	}
	
	/**
	 * This method connects a consumer to a given environment. The environment is looked up from the Environment Store for the connection 
	 * details. The 'envName' parameter is the logic name as it is known in the environment store. This is the name of XML file in the 
	 * environment/consumers/<consumerID>/input directory. It also checks if the environment exists meaning there is an XML file of the 
	 * same name in the environment/consumers/<consumerID>/workstore directory. If the environment exists in the 
	 * environment/consumers/<consumerID>/workstore then an attempt to connect is made. If that succeeds then TRUE is returned. If no XML 
	 * file exists in the environment/consumers/<consumerID>/workstore directory then it is assumed that such an environment does not
	 * exists on the server and attempt to create and connect to it is made. The details for the creation of the environment are taken 
	 * from the XML file in the environment/consumers/<consumerID>/input directory.
	 * 
	 * @param envName Name of the environment to connect to.
	 */
	public boolean connect(String envName)
	{
		EnvironmentType env = null;
		ConsumerEnvironment envInfo = (ConsumerEnvironment)getEnvInfo(envName);
		if (envInfo == null)
		{
			return false; // error already logged
		}
		
		if (envInfo.isConnected())// already connected. No further action required.
		{
			return true;
		}
		
		// Not yet connected => start connection process.
		if (envOps.existOutputEnvironment(envName))
		{
			logger.debug("Environment for "+envName+" exists in "+envOps.getEnvironmentStore().getFullWorkDirName()+". Check if it is still valid by connecting to it.");
			env = envOps.loadOutputEnvironmentData(envName);
			if (env == null) // It exists but could not be loaded. This is a problem
			{
				envInfo.setConnected(false);
				return false; //error is already logged
			}
			
			// if we get here then environment is loaded from store. Attempt to connect.
			if (!populateBasicEnvInfoFromEnvironment(envInfo, env))
			{
				envInfo.setConnected(false);
				return false;
			}
			
			// If we get here then environment is loaded from store. Attempt to connect => Get Environment from remote location.
			// This is needed because some things may have changed (i.e endpoints) since the last time we were connected.
			EnvironmentType remoteEnv = retrieveEnvironment(envInfo);
			
			if (remoteEnv != null)  //All good. Store the latest version in the Environment Store.
			{
				if (!envOps.storeOutputEnvironmentData(envName, remoteEnv))
				{
					//could not store the latest environment in the Environment Store. This might not be a problem so right now we only flag
					//the issue in the log but otherwise we assume all is fine.
					logger.warn("Environment could be retrieved from remote location but not updated in Environment Store. This could pose a problem.");
				}
				populateFullEnvInfoFromEnvironment(envInfo, remoteEnv);
				envInfo.setConnected(true);
				logger.debug(envInfo);
				return true;
			}
			else
			{
				logger.error("Could not verify environment '"+envName+"' with remote location. See previous errors for details.");
				envInfo.setConnected(false);
				return false;
			}
		}
		else
		{
			logger.debug("No Environment for "+envName+" exists. Attempt to create and connect ...");
			EnvironmentType remoteEnv = createEnvironment(envInfo); 
			if (remoteEnv != null) //successfully created 
			{
			  //System.out.println("We have a new environment...");
				populateFullEnvInfoFromEnvironment(envInfo, remoteEnv);
				//System.out.println("envInfo Populated: "+envInfo);
				
				// Store the new environment in the environment store
				if (!envOps.storeOutputEnvironmentData(envName, remoteEnv))
				{
					logger.error("Environment has been created on remote location but not updated in Environment Store. Environment is deleted on remote location again");
					removeEnvironment(envInfo);  //error or info already logged
					envInfo.setConnected(false);
					return false;			  					
				}
				envInfo.setConnected(true);
				
				logger.debug("Successfully create new Environment: "+envInfo);
				return true;			  
			}
			else //not created. Error already logged.
			{
				envInfo.setConnected(false);
				return false;			  
			}
		}
	}
	
	/**
	 * This will disconnect the consumer from the given environment. After this operation no further call can be made to any providers in the
	 * given environment. Note it will only disconnect but the environment will still exist in the Environment Store and the remote location.
	 * After a call to this method a connect() is required first to resume operations. The connect() will connect to the existing environment
	 * and won't create a new one. This is the expected standard behaviour for any adapter.
	 * 
	 * @param envName The environment to disconnect from.
	 * 
	 * @return TRUE: Operation succeeded. Environment is disconnected. FALSE: Something failed. See error log for details.
	 */
	public boolean disconnect(String envName)
	{
		ConsumerEnvironment envInfo = (ConsumerEnvironment)getEnvInfo(envName);
		if (envInfo == null)
		{
			return false; // error already logged
		}

		if (envInfo.isConnected())
		{
			setAsDisconnect(envInfo);
		}
		else
		{
			logger.info("Attemped to disconnect from "+envName+" but it was not connected at all. No action taken.");
		}
		return true;
	}
	
	/**
	 * Disconnect from the environment as of previous call but on top of this it will also remove the environment from the Environment Store and
	 * the remote location. If there are queues, events etc still available on the environment they will be lost for ever! This method must be
	 * used with care and really only makes sense in a 'minimal/direct/immediate' environment where no queues and events or any delayed responses
	 * with callbacks are used. After a call to this method the connect() method will create a new environment again.
	 * 
	 * @param envName The environment to disconnect from and to be removed.
	 * 
	 * @return TRUE: Operation succeeded. Environment is disconnected and for ever removed. FALSE: Something failed. See error log for details.
	 */
	public boolean disconnectAndRemove(String envName)
	{
		ConsumerEnvironment envInfo = (ConsumerEnvironment)getEnvInfo(envName);
		if (envInfo == null)
		{
			return false; // error already logged
		}
		if (envInfo.isConnected())
		{		
			if (removeEnvironment(envInfo))
			{
				if (!envOps.removeOutputEnvironmentData(envName))
				{
					logger.error("Environment"+envName+" could not be reomved from environment store. Environment was removed from remote location though.");
					return false;			  					
				}
			
				setAsDisconnect(envInfo);
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			logger.info("Attemped to disconnect from "+envName+" but it was not connected at all. No action taken.");
			return true;
		}
	}
	
	/*---------------------*/
	/*-- Private methods --*/
	/*---------------------*/
	/*
	 * Constructor: Create an EvironmentStore manager for the given Environment Store.
	 */
	private ConsumerEnvironmentManager(EnvironmentStore environmentStore)
	{
		super(environmentStore);
		this.envOps = new ConsumerEnvironmentStoreOperations(environmentStore);
	}
	
	private EnvironmentType retrieveEnvironment(ConsumerEnvironment envInfo)
	{
		Response response = null;
		try
		{
		  response = getClient(envInfo).getSingle(PLURAL_ENV_URI, envInfo.getEnvGUID(), getHeaderProperties(envInfo.getAuthenticationToken()), EnvironmentType.class, null, null);
		}
		catch (ServiceInvokationException ex)
		{
		  return null; // error is already logged.
		}
		
		if (response.hasError())
		{
			logger.error("An error has been returned in retrieving the environment form location: "+response.getResourceURI());
			logger.error("Returned Response Data:\n"+response);
			return null;
		}
		else
		{
			return (EnvironmentType) response.getDataObject();
		}
	}
	
	private EnvironmentType createEnvironment(ConsumerEnvironment envInfo)
	{
		EnvironmentType inputEnv = envOps.loadInputEnvironmentData(envInfo.getEnvironmentName());
		if (inputEnv != null)
		{
			if (AuthenticationUtils.AUTHENTICATION_METHOD.Basic.toString().equals(inputEnv.getAuthenticationMethod()))
			{
				String authToken = AuthenticationUtils.getBasicAuthToken(envInfo.getUserName(), envInfo.getPassword());
				logger.debug("Initial Authentication Token for creating Environment "+envInfo.getEnvironmentName()+" is '"+authToken+"'");
												
				// Create Environments
				Response response = null;
				try
				{
				  response = getClient(envInfo).createSingle(PLURAL_ENV_URI+"/"+SINGLE_ENV_URI, inputEnv, getHeaderProperties(authToken), EnvironmentType.class, null, null);
				}
				catch (ServiceInvokationException ex)
				{
				  return null; // error already logged.
				}
				
				if (response.hasError())
				{
					logger.error("An error has been returned in creating the environment '"+envInfo.getEnvironmentName()+"' form location: "+response.getResourceURI());
					logger.error("Returned Response Data:\n"+response);
					return null;
				}
				else //all good!
				{
					return (EnvironmentType)response.getDataObject();
				}
			}
			else
			{
				logger.error("Only Basic authentication is currently supported. Failed to create environment " + envInfo.getEnvironmentName() + " because Authentication Mehod is set to " + inputEnv.getAuthenticationMethod());
			}			
		}
		else
		{
			logger.error("Failed to create environment for "+envInfo.getEnvironmentName()+". See previous log entries for details.");
		}		
		return null;
	}
	
	private boolean removeEnvironment(ConsumerEnvironment envInfo)
	{
		Response response = null;
	    try
	    {
	      response = getClient(envInfo).removeSingle(PLURAL_ENV_URI, envInfo.getEnvGUID(), getHeaderProperties(envInfo.getAuthenticationToken()), null, null);
	    }
	    catch (ServiceInvokationException ex)
	    {
	      return false; // error already logged.
	    }
		
	    if (response.hasError())
	    {
			logger.error("An error has been returned in removing the environment with ID = "+envInfo.getEnvGUID() + " form location: "+response.getResourceURI());
			logger.error("Returned Response Data:\n"+response);
			return false;
	    }
	    else
	    {
			logger.info("Environment with ID = "+envInfo.getEnvGUID()+" has been removed from remote location.");
			return true;
	    }
	}	
	
	private ClientInterface getClient(ConsumerEnvironment envInfo)
	{
		return new ClientInterface(envInfo.getBaseURI(), envInfo.getMediaType(), marshaller, unmarshaller, envInfo.getIsSecureConnection());
	}

	private TransportHeaderProperties getHeaderProperties(String authToken)
	{
		TransportHeaderProperties hdrProps = new RESTHeaderProperties();
		hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, authToken);
		hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_REQUEST_ID, UUIDGenerator.getUUID());
		return hdrProps;
	}

	private boolean populateFullEnvInfoFromEnvironment(ConsumerEnvironment envInfo, EnvironmentType env)
	{
		envInfo.setEnvGUID(env.getId());
		envInfo.setSessionToken(env.getSessionToken());
		if (AuthenticationUtils.AUTHENTICATION_METHOD.Basic.toString().equals(env.getAuthenticationMethod()))
		{
			envInfo.setAuthenticationToken(AuthenticationUtils.getBasicAuthToken(envInfo.getSessionToken(), envInfo.getPassword()));
		}
		else
		{
			logger.error("Only Basic authentication is currently supported. Failed to connect to environment "+envInfo.getEnvironmentName()+" because Authentication Mehod is set to "+env.getAuthenticationMethod());
			return false;
		}
		
		InfrastructureServicesType infraServices = env.getInfrastructureServices();
		if (infraServices != null)
		{
			List<PropertyType> services = infraServices.getInfrastructureService();
			for (PropertyType service : services)
			{
				try
				{
					envInfo.addConnectorBaseURI(ConsumerEnvironment.ConnectorName.valueOf(service.getName()), new URI(service.getValue()));
				}
				catch (Exception ex)
				{
					logger.error("Could not add URI = "+service.getValue()+" for connector = "+service.getName()+" to Consumer Environment "+envInfo.getEnvironmentName());
				}
			}
		}
		else
		{
			logger.error("Environment data on provider incomplete. Infrastructure Service URIs are missing.");
		}
		
		loadServiceInfoInConsumer(envInfo, env);
		
		return true;
	}
	
	/* Depending if we are a consumer or provider the EnvironmentInfo is of type ConsumerEnvironment or ProviderEnvironment */ 
	private EnvironmentInfo getEnvInfo(String envName)
	{
		EnvironmentList<? extends EnvironmentInfo> environments = envOps.getEnvironmentStore().getEnvironments();
		
		if (environments == null)
		{
			return null; //error is already logged
		}
		
		EnvironmentInfo envInfo = environments.getEnvironment(envName);	
		if (envInfo == null)
		{
			logger.error("Environment for "+envName+" is not known. Check environment properties file if it is defined.");
		}
		return envInfo;		
	}

	private void setAsDisconnect(ConsumerEnvironment envInfo)
	{
		envInfo.setAuthenticationToken(null);
		envInfo.setEnvGUID(null);
		envInfo.setSessionToken(null);
		envInfo.setConnected(false);
	}

}
