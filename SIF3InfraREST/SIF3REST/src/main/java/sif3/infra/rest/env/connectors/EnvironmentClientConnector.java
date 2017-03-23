/*
 * EnvironmentClientConnector.java
 * Created: 14/03/2014
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
package sif3.infra.rest.env.connectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;
import sif3.common.model.security.TokenCoreInfo;
import sif3.common.model.security.TokenInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.common.security.AbstractSecurityService;
import sif3.common.security.BearerSecurityFactory;
import sif3.common.ws.Response;
import sif3.infra.common.env.types.ConsumerEnvironment.ConnectorName;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.interfaces.ClientEnvironmentManager;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.rest.client.EnvironmentClient;

/**
 * This class provides a set of methods that are required by a "client connector" style service. This includes a consumer but also a
 * provider if it acts in a brokered environment. It kind of implements the EnvironmentConnector interface without actually implementing it.
 * This class is intended to be used by actual and concrete client connector implementations that implement that interface.
 * 
 * @author Joerg Huber
 *
 */
public class EnvironmentClientConnector
{
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  private ClientEnvironmentManager envMgr = null;
  
  public EnvironmentClientConnector(ClientEnvironmentManager envMgr)
  {
    this.envMgr = envMgr;
  }

  protected ClientEnvironmentManager getEnvironmentManager()
  {
    return envMgr;
  }
  
  protected boolean connect(EnvironmentInfo envInfo)
  {
    if (envInfo == null)
    {
      return false; // error already logged
    }

    // Do we already have a connection/session
    SIF3Session sif3Session = getEnvironmentManager().getSIF3Session();
    if (sif3Session != null)// already connected. No further action required.
    {
      return true;
    }

    // Not yet connected => start connection process. First check if it exists in workstore
    // Read template to get all info for this environment, such as solutionID, userToken, instanceID
    EnvironmentType template = getEnvironmentManager().loadTemplateEnvironmentData();

    // Grab the solution ID and set it in the envInfo.environmentKey
    envInfo.getEnvironmentKey().setSolutionID(template.getSolutionId());

    // If userToken and instanceID are not set in the envInfo then we have to use the one from the template.
    if (StringUtils.isEmpty(envInfo.getEnvironmentKey().getUserToken()))
    {
      envInfo.getEnvironmentKey().setUserToken(template.getUserToken());
    }
    if (StringUtils.isEmpty(envInfo.getEnvironmentKey().getInstanceID()))
    {
      envInfo.getEnvironmentKey().setInstanceID(template.getInstanceId());
    }

    // Now we can check if there is an active session for that environment in the environment store. 
    // This will load the session as well.
    EnvironmentType localEnvironment = null;
    try
    {
      localEnvironment = getEnvironmentManager().reloadSessionFromFromWorkstore(envInfo.getEnvironmentKey());
    }
    catch (Exception ex)
    {
      disconnect();
      return false; // error is already logged
    }
    
    sif3Session = getEnvironmentManager().getSIF3Session();
    if (sif3Session != null) // we had an environment
    {
      if (localEnvironment == null) // It exists but could not be loaded. This is a problem.
      {
        disconnect();
        return false; // error is already logged
      }

      logger.debug("Environment for " + sif3Session.getEnvironmentName() + " exists in workstore. Check if it is still valid by connecting to it.");

      // If we get here then environment is loaded from store. Attempt to connect => Get Environment from remote location.
      // This is needed because some things may have changed (i.e end-points) since the last time we were connected.
      EnvironmentType remoteEnv = retrieveRemoteEnvironment(envInfo, sif3Session, false);

      if (remoteEnv != null) // All good. Store the latest version in the Environment Store.
      {
    	  // The security token might be available. Get it as it is needed in the update of the createOrUpdateEnvironment() method
    	  TokenInfo tokenInfo = null;
    	  if (StringUtils.notEmpty(sif3Session.getSecurityToken()))
    	  {
    		  tokenInfo = new TokenInfo(sif3Session.getSecurityToken());
    		  tokenInfo.setTokenExpiryDate(sif3Session.getSecurityTokenExpiry());
    	  }
    	  
    	  localEnvironment = getEnvironmentManager().createOrUpdateEnvironment(remoteEnv, tokenInfo);
    	  if (localEnvironment == null)
    	  {
    		  // could not store the latest environment in the Environment Store. This might not be a problem so right now we 
    		  // only flag the issue in the log but otherwise we assume all is fine.
    		  logger.warn("Environment could be retrieved from remote location but not updated in Environment Store. This could pose a problem.");
    	  }

    	  logger.debug("Successfully connected to environment: " + envInfo);
    	  return true;
      }
      else
      {
        logger.error("Could not verify environment '" + envInfo.getEnvironmentName() + "' with remote location. See previous errors for details.");
        disconnect();
        return false;
      }
    }
    else // We have no session in workstore => create new environment with environment provider
    {
    	// Check if we shall use pre-existing environment or create a new one from scratch.
        // Also since we connect to exiting or may need to create a new environment we may need to use an
        // external security service. There is no existing session at the moment so we cannot retrieve the info from there.
        TokenInfo tokenInfo = null;
        if (envInfo.getAuthMethod() == AuthenticationMethod.Bearer)
        {
            AbstractSecurityService securityService = BearerSecurityFactory.getSecurityService(getEnvironmentManager().getServiceProperties());
            if (securityService != null)
            {
                TokenCoreInfo coreInfo = new TokenCoreInfo();
                coreInfo.setAppUserInfo(envInfo.getEnvironmentKey());
                coreInfo.setConsumerName(envInfo.getAdapterName());
                coreInfo.setSessionToken(envInfo.getUseExistingEnv() ? envInfo.getExistingSessionToken() : null);

                // Now call the security service
                tokenInfo = securityService.createToken(coreInfo, envInfo.getPassword());
                if ((tokenInfo == null) || StringUtils.isEmpty(tokenInfo.getToken())) // we failed to get a token!
                {
                   logger.error("Failed to retrieve a security token. See previous error log entries.");
                   return false;
                }                       
            }            
        }
            
    	if (envInfo.getUseExistingEnv())
    	{
    		logger.debug("No Environment for " + envInfo.getEnvironmentName() + " exists. Attempt to connect to existing environment without creating it (use pre-existing) ...");

    		// Create a temporary pseudo SIF3 Session
    		sif3Session = new SIF3Session();
    		sif3Session.setSessionToken(envInfo.getExistingSessionToken());
    		sif3Session.setPassword(envInfo.getPassword());
    		if (tokenInfo != null)
    		{
    		    sif3Session.setSecurityToken(tokenInfo.getToken());
    		    sif3Session.setSecurityTokenExpiry(tokenInfo.getTokenExpiryDate());
    		}
    		
    		// And try to retrieve environment from remote location.
    		EnvironmentType remoteEnv = retrieveRemoteEnvironment(envInfo, sif3Session, true);
			if (remoteEnv != null) // successfully retrieved
			{
				localEnvironment = getEnvironmentManager().createOrUpdateEnvironment(remoteEnv, tokenInfo);

				if (localEnvironment == null) // oops something is not good. => disconnect
				{
					logger.error("Environment has been retrieved from remote location but not updated in Environment Store.");
					disconnect();
					return false;
				}

				logger.debug("Successfully connected to environment: " + envInfo);
				return true;
			}
			else // not retrieved. Error already logged.
			{
				disconnect();
				return false;
			}	
    	}
    	else // create one from scratch. This is standard behaviour.
    	{
	      logger.debug("No Environment for " + envInfo.getEnvironmentName() + " exists. Attempt to create and connect ...");
	      
	      EnvironmentType remoteEnv = createRemoteEnvironment(envInfo, template, tokenInfo);
	      if (remoteEnv != null) // successfully created
	      {
	        localEnvironment = getEnvironmentManager().createOrUpdateEnvironment(remoteEnv, tokenInfo);
	
	        if (localEnvironment == null) // oops something is not good. => remove it again from remote location
	        {
	          logger.error("Environment has been created on remote location but not updated in Environment Store. Environment is deleted on remote location again");
	          removeRemoteEnvironment(envInfo); // error or info already logged
	          disconnect();
	          return false;       
	        }
	
	        logger.debug("Successfully connected to environment: " + envInfo);
	        return true;
	      }
	      else // not created. Error already logged.
	      {
	        disconnect();
	        return false;
	      }
    	}
    }
  }
  
  protected boolean disconnect(boolean removeEnvironment, EnvironmentInfo envInfo)
  {
    if (removeEnvironment)
    {
      return disconnectAndRemove(envInfo, envMgr.getSIF3Session());
    }
    else
    {
      disconnect();
      return true;
    }
  }

  /*---------------------*/
  /*-- Private methods --*/
  /*---------------------*/

  // sif3Session cannot be null! Check before call. 
  private EnvironmentType retrieveRemoteEnvironment(EnvironmentInfo envInfo, SIF3Session pseudoSIF3Session, boolean useExistingEnvURI)
  {
    Response response = null;
    try
    {
    	EnvironmentClient client = null;
    	if (useExistingEnvURI)
    	{
    	    // We do not really have a valid SIF3 Session yet. We must first get an already existing environment.
    		client = new EnvironmentClient(getEnvironmentManager(), envInfo.getExistingEnvURI(), envInfo);
    		
    		// Use the method that is intended for getting an existing environment that is not yet known to the consumer.
    		response = client.getEnvironment(pseudoSIF3Session);
    	}
    	else
    	{
    		client = new EnvironmentClient(getEnvironmentManager(), envInfo.getConnectorBaseURI(ConnectorName.environment), envInfo);

            // We have an existing environment. We only want to sync-up with remote location to get latest changes.
            // Don't use the pseudo session as the getEnvironment will use the internal proper session of none is given.
    		response = client.getEnvironment(null);
    	}
    }
    catch (ServiceInvokationException ex)
    {
      return null; // error already logged.
    }

    if (response.hasError())
    {
      logger.error("An error has been returned in retrieving the environment form location: " + response.getResourceURI());
      logger.error("Returned Response Data:\n" + response);
      return null;
    }
    else
    {
      return (EnvironmentType) response.getDataObject();
    }
  }
  
  private EnvironmentType createRemoteEnvironment(EnvironmentInfo envInfo, EnvironmentType template, TokenInfo tokenInfo)
  {
    if (template != null)
    {
        // Set a few fields in the template before it is sent to environment provider
        template.setConsumerName(envInfo.getAdapterName());
        template.setAuthenticationMethod(envInfo.getAuthMethod().name());
        template.setSolutionId(envInfo.getEnvironmentKey().getSolutionID());
        template.getApplicationInfo().setApplicationKey(envInfo.getEnvironmentKey().getApplicationKey());
        template.setUserToken(envInfo.getEnvironmentKey().getUserToken());
        template.setInstanceId(envInfo.getEnvironmentKey().getInstanceID());
        
        Response response = null;
        try
        {
        	EnvironmentClient client = new EnvironmentClient(getEnvironmentManager(), envInfo.getBaseURI(), envInfo);
        	response = client.createEnvironment(template, tokenInfo);
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
        else //all might be good! It could still have a 409 (Conflict, i.e. already exist) and payload might be empty!
        {
          return (EnvironmentType)response.getDataObject();
        }
    }
    else
    {
      logger.error("Failed to create environment for "+envInfo.getEnvironmentName()+". See previous log entries for details.");
    }   
    return null;
  }
  
  /*
   * This will disconnect the consumer from the given environment. After this operation no further call can be made to any providers in the
   * given environment. Note it will only disconnect but the environment will still exist in the Environment Store and the remote location.
   * After a call to this method a connect() is required first to resume operations. The connect() will connect to the existing environment
   * and won't create a new one. This is the expected standard behaviour for any adapter.
   * 
   * @return TRUE: Operation succeeded. Environment is disconnected. FALSE: Something failed. See error log for details.
   */
  private void disconnect()
  {
    getEnvironmentManager().disconnect();
  }
  
  /*
   * Disconnect from the environment as of previous call but on top of this it will also remove the environment from the Environment Store and
   * the remote location. If there are queues, events etc still available on the environment they will be lost for ever! This method must be
   * used with care and really only makes sense in a 'minimal/direct/immediate' environment where no queues and events or any delayed responses
   * with callbacks are used. After a call to this method the connect() method will create a new environment again.
   * 
   * @return TRUE: Operation succeeded. Environment is disconnected and for ever removed. FALSE: Something failed. See error log for details.
   */
  private boolean disconnectAndRemove(EnvironmentInfo envInfo, SIF3Session sif3Session)
  {   
    if (sif3Session != null)
    {
      // First we attempt to remove the environment on the environment provider
      if (removeRemoteEnvironment(envInfo))
      {
        // that worked. Now we need to remove it in the environment and session store.
        return getEnvironmentManager().removeEnvironment(sif3Session);
      }
      else
      {
        // Failed to remove remote environment. Error is logged an the best we can do is just to disconnect
        disconnect();
        return false; // error already logged.
      }
    }
    else
    {
      logger.debug("There is no active session for the consumer: "+envInfo.getEnvironmentName()+". No action taken.");
      disconnect();
      return true;
    }
  }
  
  // environment cannot be null! Check before call. It must also have a sessionToken and environmentID. 
  private boolean removeRemoteEnvironment(EnvironmentInfo envInfo)
  {
    EnvironmentClient client = new EnvironmentClient(getEnvironmentManager(), envInfo.getConnectorBaseURI(ConnectorName.environment), envInfo);
    return client.removeEnvironment();
  }
}
