/*
 * EnvironmentStore.java
 * Created: 27/08/2013
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

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import sif3.common.utils.FileAndFolderUtils;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.env.types.EnvironmentList;
import sif3.infra.common.env.types.ProviderEnvironment;
import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.PropertyManager;
import au.com.systemic.framework.utils.StringUtils;

/**
 * This class is a POJO that holds the infromation about environments as defined in the adpater's property file. It also maintains an
 * apprporiate directory structure to store and retrieve environment data (as XML files). It will ensure a strict directory structure
 * for consumer and provider environments. It assumes that the property file called 'environment.properties' exists and is on the classpath.
 * This file holds the root directory of the environmenty store (consumer and provider).<br/><br/>
 * 
 * The environment store is very static and file based. Therefore this class is implemented as a singleton because it is too expensive
 * to access files each time an environment is required. SIF3 requires each operation to be validated against and environment and 
 * therefore each operation will access the environment store. Another reason to hold it in memory is due to the static nature of the 
 * environment store and hence the use of a singleton.<br/><br/>
 * 
 * Operations within this class are NOT exposed to the higher levels of the framework and are fully abstracted form a developer of
 * a service (main intend of this framework). This class forms part of the Environment Porvider which each Provider Service in a DIRECT
 * environment must implement.
 * 
 * @author Joerg Huber
 *
 */
public class EnvironmentStore implements Serializable
{
    private static final long serialVersionUID = -4017716764457564336L;

	protected final Logger logger = Logger.getLogger(getClass());

	private static final String ENV_PROP_FILE_NAME = "environment";
	private static final String INPUT_DIR_NAME = "/input/";
	private static final String WORK_DIR_NAME = "/workstore/";
	private static final String TEMPLATE_DIR_NAME = "/template/";
	private static final String ANY_DIR_NAME = "/any/";
	
	private static final String XML = "XML";
	private static final String JSON = "JSON";

    /*-- Make this class a singleton --*/
    private static EnvironmentStore instance = null;
    
    private EnvironmentList<? extends EnvironmentInfo> environments = null;
    private String environmentBaseDirName = null;

	private String adapterFileNameWithoutExt = null;
 
	/**
	 * Constructor: Ensures the singleton behaviour of the environment store.
	 * 
	 * @param adapterFileNameWithoutExt The name of the adpater property file. This file must be on the classpath.
	 * 
	 * @return Instance of the Environment Store. Null if environment store cannot be created. See error log for details
	 */
	public static EnvironmentStore getInstance(String adapterFileNameWithoutExt)
    {
    	if (instance == null)
    	{
    		instance = new EnvironmentStore(adapterFileNameWithoutExt);
    		if (instance.getEnvironments() == null) // we had an error!
    		{
    			instance = null;
    		}
    	}
    	
    	return instance;	
    }

    public String getAdapterFileNameWithoutExt()
    {
    	return this.adapterFileNameWithoutExt;
    }

	public void setAdapterFileNameWithoutExt(String adapterFileNameWithoutExt)
    {
    	this.adapterFileNameWithoutExt = adapterFileNameWithoutExt;
    }

    public String getEnvironmentBaseDirName()
    {
    	return this.environmentBaseDirName;
    }

	public void setEnvironmentBaseDirName(String environmentBaseDirName)
    {
    	this.environmentBaseDirName = environmentBaseDirName;
    }

	/**
     * @return the environments
     */
    public EnvironmentList<? extends EnvironmentInfo> getEnvironments()
    {
	    return environments;
    }

	public String getFullInputDirName()
    {
		if (getEnvironments().getIsConsumer())
		{
			return getEnvDirName()+INPUT_DIR_NAME;
		}
		else
		{
			return null; // not applicable for providers
		}
    }

	public String getFullWorkDirName()
    {
    	return getEnvDirName()+WORK_DIR_NAME;
    }
	
	/**
	 * Full path where Environment Templates are stored.
	 * 
	 * @return See desc.
	 */
	public String getFullTemplateDirName()
    {
		if (getEnvironments().getIsConsumer())
		{
			return getEnvironmentBaseDirName()+"/consumers"+TEMPLATE_DIR_NAME;
		}
		else
		{
			return getEnvDirName()+TEMPLATE_DIR_NAME;
		}
    }

	/**
	 * Full path to the directory where consumer environments in the provider are stored that are not pre-defined.
	 * 
	 * @return See desc.
	 */
	public String getAnyDirName()
    {
		if (getEnvironments().getIsConsumer())
		{
			return null; // not applicable for consumers
		}
		else
		{
			return getEnvDirName()+ANY_DIR_NAME;
		}
    }

	public String getEnvDirName()
	{
		return  getEnvironmentBaseDirName()+"/"+((getEnvironments().getIsConsumer()) ? "consumers/" : "providers/" )+getEnvironments().getAdapterName();
	}

	@Override
    public String toString()
    {
	    return "EnvironmentStore [environments=" + this.environments + ", environmentBaseDirName="
	            + this.environmentBaseDirName + ", adapterFileNameWithoutExt="
	            + this.adapterFileNameWithoutExt + "]";
    }

	/*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    private EnvironmentStore(String adapterFileNameWithoutExt)
    {
    	super();
    	this.setAdapterFileNameWithoutExt(adapterFileNameWithoutExt);
    	
    	// First load the environments.properies file and store its information in this class
    	if (loadEnvironmentProperties())
    	{
    		//Load Service Property File
    		AdvancedProperties props = getProperties(adapterFileNameWithoutExt);
    		if (props != null)
    		{
	          boolean errors = false;
	          String serviceName = getServiceName(props);
	          Boolean isConsumer = getIsConsumer(props);
	          if ((serviceName != null) && (isConsumer != null)) // error already logged if one of these is null
	          {
	            environments = ((isConsumer) ? new EnvironmentList<ConsumerEnvironment>() : new EnvironmentList<ProviderEnvironment>()); 
	            environments.setAdapterName(serviceName);
	            environments.setIsConsumer(isConsumer);
	            
	            // Check if input and workstore folder exists. If not then create them.
	            if (!checkAndCreateDir(getFullInputDirName()))
	            {
	              errors = true;
	            }
	            if (!checkAndCreateDir(getFullWorkDirName()))
	            {
	              errors = true;
	            }
	  
	            if (!checkAndCreateDir(getFullTemplateDirName()))
	            {
	              errors = true;
	            }
	            if (!checkAndCreateDir(getAnyDirName()))
	            {
	              errors = true;
	            }
	  
	            if (!errors) // Read specific info
	            {
	              if (getEnvironments().getIsConsumer())
	              {
	                errors = loadConsumerInfo(props);
	              }
	              else
	              {
	                errors = loadProviderInfo(props);
	              }
	            }
	    			}
	          else // Error already logged.
	          {
	            errors = true;
	          }
	
	          // If we had any errors then the environment info cannot be relied upon. So we best null it to avoid confusion.
	          if (errors)
	          {
	            environments = null;
	          }
    		}
    	}
    }

    /* Null is returned if there is an error. Error is logged. */
    private String getServiceName(AdvancedProperties props)
    {
      String serviceName = props.getPropertyAsString("adapter.id", null);
      if (StringUtils.isEmpty(serviceName))
      {
        logger.error("Property 'adapter.id' is null or empty in "+getAdapterFileNameWithoutExt()+".properties. This property MUST be defined.");
        serviceName = null;                   
      }
      return serviceName;
    }
    
    
    /* Null is returned if there is an error. Error is logged. */
    private Boolean getIsConsumer(AdvancedProperties props)
    {
      String adapterType = props.getPropertyAsString("adapter.type", null);
      if (StringUtils.isEmpty(adapterType))
      {
        logger.error("Property 'adapter.type' is null or empty in "+getAdapterFileNameWithoutExt()+".properties. This property MUST be defined.");
        return null;
      }
      return adapterType.equalsIgnoreCase("consumer");
    }
    
    private boolean loadEnvironmentProperties()
    {
      AdvancedProperties props = getProperties(ENV_PROP_FILE_NAME);
      if (props == null)
      {
        logger.error("Properties file 'environment.properties' could not be read. Ensure it exists and is on the classpath.");
        return false;
      }
      setEnvironmentBaseDirName(props.getPropertyAsString("env.store.dir", null));
      if (StringUtils.isEmpty(getEnvironmentBaseDirName()))
      {
        logger.error("Property 'env.store.dir' is null or empty in environment.properties. It MUST be defined!");
        return false;
      }
      else
      {
        if (!FileAndFolderUtils.doesFolderExist(getEnvironmentBaseDirName(),
            true, true))
        {
          logger.error("Folder " + getEnvironmentBaseDirName() + " may not exist or it is not readable and writable. Please create it and/or make it read/write.");
          return false;
        }
      }
      return true;
    }
    
    @SuppressWarnings("unchecked")
    private boolean loadConsumerInfo(AdvancedProperties props)
    {
  		boolean errorsFound = false;
  		
  		// Process through Environment List
  		List<String> envNames = props.getFromCommaSeparated("env.list");
  		if (envNames.isEmpty())
  		{
  			logger.error("Property 'env.list' is null or empty in "+getAdapterFileNameWithoutExt()+".properties. One environment name MUST be defined!");
  			errorsFound = true;    			
  		}
  		else
  		{
  			for (String envName : envNames)
  			{
  				envName = envName.trim();
  				if (StringUtils.notEmpty(envName))
  				{
      				ConsumerEnvironment envInfo = new ConsumerEnvironment(envName, environments.getAdapterName());
      				envInfo.setMediaType(convertMediaType(props.getPropertyAsString("env.mediaType."+envName, null)));
      				envInfo.setBaseURI(props.getPropertyAsString("env.baseURI."+envName, null));
      				envInfo.setUserName(props.getPropertyAsString("env.user."+envName, null));
      				envInfo.setPassword(props.getPropertyAsString("env.pwd."+envName, null));
      				if ((envInfo.getBaseURI() == null) || (StringUtils.isEmpty(envInfo.getUserName())) || (StringUtils.isEmpty(envInfo.getPassword())))
      				{
      	    			logger.error("Ensure that all of the properties for environment '"+envName+"' are set and not empty in "+getAdapterFileNameWithoutExt()+".properties.");
      	    			logger.error("Required Properties are: env.baseURI."+envName+", env.user."+envName+", env.pwd."+envName);
      	    			errorsFound = true;    			    					
      				}
      				else
      				{
      				  ((EnvironmentList<ConsumerEnvironment>)environments).addEnvironment(envInfo);
      				}
  				}
  			}
  		}
  		
  		if (errorsFound)
  		{
  			logger.error("Errors found in reading environment information from "+getAdapterFileNameWithoutExt()+".properties. See previous log entries for details and please correct them.");
  		}
  		return errorsFound;
   }
    
    @SuppressWarnings("unchecked")
    private boolean loadProviderInfo(AdvancedProperties props)
    {
      boolean errorsFound = false;
      
      // Process through Environment List
      List<String> envNames = props.getFromCommaSeparated("env.list");
      if (envNames.isEmpty())
      {
        logger.error("Property 'env.list' is null or empty in "+getAdapterFileNameWithoutExt()+".properties. One environment name MUST be defined!");
        errorsFound = true;         
      }
      else
      {
        for (String envName : envNames)
        {
          envName = envName.trim();
          if (StringUtils.notEmpty(envName))
          {
              ProviderEnvironment envInfo = new ProviderEnvironment(envName, environments.getAdapterName());
              
              //Check for the 'any' properies. If they aren't defined assume 'any' is not allowed!
              envInfo.setAllowAny(props.getPropertyAsBool("env.allowAny."+envName, false));
              if (envInfo.getAllowAny()) // 'any' is allowed. Read username and password
              {
                envInfo.setAllowAnyUserName(props.getPropertyAsString("env.allowAny.user."+envName, null));
                envInfo.setAllowAnyPassword(props.getPropertyAsString("env.allowAny.pwd."+envName, null));
                if ((StringUtils.isEmpty(envInfo.getAllowAnyUserName())) || (StringUtils.isEmpty(envInfo.getAllowAnyPassword())))
                {
                    logger.error("env.allowAny."+envName+" is set to true. You must provide a username and password for this environment.");
                    logger.error("Required Properties are: env.allowAny.user."+envName+", env.allowAny.pwd."+envName);
                    errorsFound = true;                       
                }
              }
              
              // Media Type
              envInfo.setMediaType(convertMediaType(props.getPropertyAsString("env.mediaType."+envName, null)));
              
              //Base URL for connectors
              String baseURLStr = props.getPropertyAsString("env.connector.url."+envName, null);
              if (StringUtils.isEmpty(baseURLStr))
              {
                  logger.error("env.connector.url."+envName+" is missing. You must provide this URL for this environment.");
                  errorsFound = true;                                   	  
              }
              else
              {
                  // Remove trailing '/' if it is there
            	  baseURLStr = baseURLStr.trim();
            	  if (baseURLStr.endsWith("/"))
            	  {
            		  baseURLStr = baseURLStr.substring(0, baseURLStr.length()-1);
            	  }
            	  try
            	  {
            		  envInfo.setBaseURI(new URI(baseURLStr));
            	  }
            	  catch (Exception ex)
            	  {
                      logger.error("env.connector.url."+envName+" is not a valid URL. Please correct the URL for this environment.");
                      errorsFound = true;                                   	              		  
            	  }
              }
              
              // Data Models for this environment
              List<String> dmList = props.getFromCommaSeparated("env.dmInfo."+envName);
              if (dmList.isEmpty())
              {
                logger.error("Ensure that the porperty env.dmInfo."+envName +" is set in "+getAdapterFileNameWithoutExt()+".properties.");
                errorsFound = true;
              }
              else
              {
                for (String dm : dmList)
                {
                  envInfo.getSupportedDM().add(dm.trim());
                }
              }
              
              // Get Consumer information for each environments. Note this section is optional for each environment that has 'allowAny'
              // enabled.
              List<String> consumerIDs = props.getFromCommaSeparated("env.consumerID.list."+envName);
              if (consumerIDs.isEmpty() && !envInfo.getAllowAny()) // Consumers must be identified otherwise no one can connect
              {
                logger.warn("No consumers defined for environment "+envName+". Either set env.allowAny."+envName+" to true or define consumers in the env.consumerID.list."+envName+" property.");
                //errorsFound = true;                
              }
              else
              {
                for (String consumerID : consumerIDs)
                {
                  ConsumerEnvironment consumer = new ConsumerEnvironment(envName, consumerID);
                  consumer.setUserName(getComplexProperty(props, "consumer.env.user."+consumerID, envName));
                  consumer.setPassword(getComplexProperty(props, "consumer.env.pwd."+consumerID, envName));

                  if ((StringUtils.isEmpty(consumer.getUserName())) || (StringUtils.isEmpty(consumer.getPassword())))
                  {
                      logger.error("Ensure that all of the properties for consumer "+ consumerID +" for environment '"+envName+"' are set and not empty in "+getAdapterFileNameWithoutExt()+".properties.");
                      logger.error("Required Properties are: consumer.env.user."+consumerID+"[."+envName+"], consumer.env.pwd."+consumerID+"[."+envName+"]");
                      errorsFound = true;                       
                  }
                  else
                  {
                    envInfo.addConsumer(consumerID, consumer);
                  }
                }
              }
              
              if (!errorsFound)
              {
                ((EnvironmentList<ProviderEnvironment>)environments).addEnvironment(envInfo);
              }
          }
        }
      }
      
      if (errorsFound)
      {
        logger.error("Errors found in reading environment information from "+getAdapterFileNameWithoutExt()+".properties. See previous log entries for details and please correct them.");
      }
      return errorsFound;
    } 
    	
    private boolean checkAndCreateDir(String fullDirName)
    {
    	if (StringUtils.isEmpty(fullDirName)) // Directory not required
    	{
    		return true;
    	}
    	if (!FileAndFolderUtils.doesFolderExist(fullDirName))
    	{
    		logger.info("Folder "+fullDirName+" does not exist. Creating it now.");   			
    		try
    		{
    			if (!FileAndFolderUtils.createFolder(fullDirName, true))
    			{
    				logger.error("Failed to create folder: "+fullDirName);
    				return false;
    			}
    		} 
    		catch (Exception ex)
    		{
    			logger.error("Failed to create folder: "+fullDirName);    				
    			return false;
    		}
    	}
    	return true;
    }
    
    private String getComplexProperty(AdvancedProperties props, String basePropName, String postfixPropName)
    {
      String value = props.getPropertyAsString(basePropName, null);
      return (value == null) ? props.getPropertyAsString(basePropName+"."+postfixPropName, null) : value;
    }
    
    private MediaType convertMediaType(String mediaType)
    {
  		mediaType = (StringUtils.isEmpty(mediaType) ? XML : mediaType.toUpperCase().trim());
  		return (mediaType.equals(JSON) ? MediaType.APPLICATION_JSON_TYPE : MediaType.APPLICATION_XML_TYPE);
    }

    private AdvancedProperties getProperties(String fileNameWithoutExt)
    {
      PropertyManager propMgr = PropertyManager.getInstance();
      propMgr.loadPropertyFile(fileNameWithoutExt);
      if (propMgr.isLoaded(fileNameWithoutExt))
      {
        return propMgr.getProperties(fileNameWithoutExt);
      }
      return null;
    }

}
