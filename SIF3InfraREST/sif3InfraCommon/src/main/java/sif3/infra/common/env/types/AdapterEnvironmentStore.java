/*
 * AdapterEnvironmentStore.java
 * Created: 11/02/2014
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

package sif3.infra.common.env.types;

import java.io.Serializable;
import java.net.URI;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.PropertyManager;
import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.CommonConstants.QueuePollingType;
import sif3.common.CommonConstants.QueueStrategy;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;
import sif3.common.utils.FileAndFolderUtils;
import sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType;

/**
 * This class abstract the reading and processing of the consumer and provider property file of this framework.
 * 
 * @author Joerg Huber
 *
 */
public class AdapterEnvironmentStore implements Serializable
{
	
    private static final long serialVersionUID = 34177453457564336L;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String TEMPLATE_DIR_NAME = "template";
	private static final String PROVIDER_DIR_NAME = "provider";
	private static final String CONSUMER_DIR_NAME = "consumer";
	private static final String DIRECT_DIR_NAME = "direct";
	private static final String BROKERED_DIR_NAME = "brokered";
	
	private static final String XML = "XML";
	private static final String JSON = "JSON";

	private EnvironmentInfo environment = null;
    private String environmentBaseDirName = null;
	private String adapterFileNameWithoutExt = null;
	private AdvancedProperties adapterProperties = null;
	
	public AdapterEnvironmentStore(String adapterFileNameWithoutExt)
	{
		super();
		readConfig(adapterFileNameWithoutExt);
	}
    
    public EnvironmentInfo getEnvironment()
    {
    	return this.environment;
    }

	public void setEnvironment(EnvironmentInfo environment)
    {
    	this.environment = environment;
    }

	public String getEnvironmentBaseDirName()
    {
    	return this.environmentBaseDirName;
    }

	public void setEnvironmentBaseDirName(String environmentBaseDirName)
    {
    	this.environmentBaseDirName = environmentBaseDirName;
    }

	public String getAdapterFileNameWithoutExt()
    {
    	return this.adapterFileNameWithoutExt;
    }

	public void setAdapterFileNameWithoutExt(String adapterFileNameWithoutExt)
    {
    	this.adapterFileNameWithoutExt = adapterFileNameWithoutExt;
    }

	/**
	 * Returns the property file content as used for this environment store.
	 * 
	 * @return See desc.
	 */
	public AdvancedProperties getAdapterProperties()
    {
    	return this.adapterProperties;
    }

	public String getFullTemplateDirName(boolean isConsumer, EnvironmentType envType)
    {
		if (isConsumer)
		{
			return getEnvDirName()+"/"+TEMPLATE_DIR_NAME;
		}
		else
		{
			return getEnvDirName()+"/"+TEMPLATE_DIR_NAME+"/"+((envType == EnvironmentType.DIRECT ? DIRECT_DIR_NAME : BROKERED_DIR_NAME));
		}
    }
	
	public String getEnvDirName()
	{
		return  getEnvironmentBaseDirName()+"/"+((getEnvironment().getAdapterType() == AdapterType.CONSUMER) ? CONSUMER_DIR_NAME : PROVIDER_DIR_NAME );
	}
	
	@Override
    public String toString()
    {
	    return "AdapterEnvironmentStore [environment=" + this.environment
	            + ", environmentBaseDirName=" + this.environmentBaseDirName
	            + ", adapterFileNameWithoutExt=" + this.adapterFileNameWithoutExt + "]";
    }

	/*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
	private void readConfig(String adapterFileNameWithoutExt)
	{
		this.setAdapterFileNameWithoutExt(adapterFileNameWithoutExt);

		// First load the environments.properies file and store its information in this class
		if (loadEnvironmentProperties())
		{
			// Load Service Property File
			adapterProperties = getProperties(adapterFileNameWithoutExt);
			if (adapterProperties != null)
			{
				boolean errors = false;
				String serviceName = getServiceName(adapterProperties);
				Boolean isConsumer = getIsConsumer(adapterProperties);
				if ((serviceName != null) && (isConsumer != null)) // error already logged if one of these is null
				{
					environment = (isConsumer) ? new ConsumerEnvironment(serviceName) : new ProviderEnvironment(serviceName);
					environment.setAdapterType(isConsumer ? AdapterType.CONSUMER : AdapterType.PROVIDER);
					environment.setCheckACL(adapterProperties.getPropertyAsBool("adapter.checkACL", true));
//					environment.setEventsSupported(adapterProperties.getPropertyAsBool("env.events.supported", false));
					environment.setRemoveEnvOnShutdown(adapterProperties.getPropertyAsBool("adapter.deleteEnvironment.onShutdown", false));
					environment.setGeneratorID(adapterProperties.getPropertyAsString("adapter.generator.id", null));
					environment.setEnvCreateConflictIsError(adapterProperties.getPropertyAsBool("env.create.conflictIsError", true));
					environment.setCompressionEnabled(adapterProperties.getPropertyAsBool("adapter.compression.enabled", false));
                    environment.setNoCertificateCheck(adapterProperties.getPropertyAsBool("adapter.noCertificateCheck", false));
					
					//This is also set for providers in brokered environment
					environment.setSecureConnection(getSecureConnectionInfo(adapterProperties));
					
					environment.setEnvironmentType(getEnvironmentType(adapterProperties, isConsumer));
					errors = ((!isConsumer) && (environment.getEnvironmentType() == null));

					if (!errors)
					{
						// Check if required directories exist. Create them if they don't exist.
						if (!checkAndCreateDir(getFullTemplateDirName(isConsumer, environment.getEnvironmentType())))
						{
							errors = true;
						}
					}
					
			  		// Media Type
			  		environment.setMediaType(convertMediaType(adapterProperties.getPropertyAsString("env.mediaType", null)));
			  		
			  		// Charset Encoding to be used with media type
                    environment.setCharsetEncoding(adapterProperties.getPropertyAsString("env.mediaType.charset", null));

			  		if (!loadExistingEnvInfo(adapterProperties))
			  		{
			  			errors = true;
			  		}
			  		
			  		if (!errors) // Read specific info
					{
						if (getEnvironment().getAdapterType() == AdapterType.CONSUMER)
						{
							errors = loadConsumerInfo(adapterProperties, (ConsumerEnvironment)environment);
						}
						else
						{
							errors = loadProviderInfo(adapterProperties, (ProviderEnvironment)environment);
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
					environment = null;
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
			logger.error("Property 'adapter.id' is null or empty in " + getAdapterFileNameWithoutExt() + ".properties. This property MUST be defined.");
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
			logger.error("Property 'adapter.type' is null or empty in " + getAdapterFileNameWithoutExt() + ".properties. This property MUST be defined.");
			return null;
		}
		return adapterType.equalsIgnoreCase("consumer");
	}


	/* If not provided in property file then FULL is default value to be returned */
	private UpdateType getUpdateType(AdvancedProperties props)
	{
		String updateType = props.getPropertyAsString("event.default.updateType", UpdateType.FULL.name());
		return UpdateType.valueOf(updateType.toUpperCase());
	}

	/* Null is returned if there is an error. Error is logged. */
	private EnvironmentType getEnvironmentType(AdvancedProperties props, boolean isConsumer)
	{
		String envType = props.getPropertyAsString("env.type", null);
		if (StringUtils.isEmpty(envType))
		{
			// for Providers we must have this value
			if (!isConsumer)
			{
				logger.error("Property 'env.type' is null or empty in " + getAdapterFileNameWithoutExt() + ".properties. This property MUST be defined.");
			}
			return null;			
		}
		else
		{
			try
			{
				return EnvironmentType.valueOf(envType.toUpperCase());
			}
			catch (Exception ex)
			{
				logger.error("Invalid property 'env.type' in " + getAdapterFileNameWithoutExt() + ".properties. Valid values are 'DIRECT' & 'BROKERED'.");
				return null;
			}
		}
	}
	
	private QueueStrategy getQueueStrategy(AdvancedProperties props, String configPrefix)
	{
	    String strategyStr = props.getPropertyAsString(configPrefix+"queue.strategy", QueueStrategy.ADAPTER_LEVEL.name());
		try
		{
			QueueStrategy strategy =  QueueStrategy.valueOf(strategyStr);

			//TODO: JH - Change once other queue strategies are supported.
			if (strategy != QueueStrategy.ADAPTER_LEVEL)
			{
				logger.info("Queue Strategy is set to "+strategy+". This is not yet supported. ADAPTER_LEVEL is used as default.");
				strategy = QueueStrategy.ADAPTER_LEVEL; 
			}
			return strategy;
		}
		catch (Exception ex) // Invalid value has been provided.
		{
			logger.error("Invalid Queue Strategy provided in property events.queue.strategy in "+props.getPropFileNameFull()+". ADAPTER_LEVEL is used as default.");
			return QueueStrategy.ADAPTER_LEVEL;
		}	
	}
	
	private QueuePollingType getQueueType(AdvancedProperties props, String configPrefix)
	{
		String value = props.getPropertyAsString(configPrefix+"queue.type", QueuePollingType.IMMEDIATE.name());
		try
		{
			return  QueuePollingType.valueOf(value);
		}
		catch (Exception ex) // Invalid value has been provided.
		{
			logger.error("Invalid Queue Polling Type provided in property events.queue.type in "+props.getPropFileNameFull()+". IMMEDIATE is used as default.");
			return QueuePollingType.IMMEDIATE;
		}	
	}

	private boolean getSecureConnectionInfo(AdvancedProperties props)
    {
    	return props.getPropertyAsBool("adapter.use.https", false);
    }

    private boolean loadEnvironmentProperties()
	{
		AdvancedProperties props = getProperties(CommonConstants.ENV_PROP_FILE_NAME);
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
			if (!FileAndFolderUtils.doesFolderExist(getEnvironmentBaseDirName(), true, true))
			{
				logger.error("Folder " + getEnvironmentBaseDirName() + " may not exist or it is not readable and writable. Please create it and/or make it read/write.");
				return false;
			}
		}
		return true;
	}
    
    /*
     * This method load information in relation to existing environments. If bits are missing or don't make sense then
     * an error is logged and false is returned.
     */
	private boolean loadExistingEnvInfo(AdvancedProperties adapterProperties)
  	{
		environment.setUseExistingEnv(adapterProperties.getPropertyAsBool("env.use.existing", false));
		environment.setExistingSessionToken(adapterProperties.getPropertyAsString("env.existing.sessionToken", null));
		String envURI = adapterProperties.getPropertyAsString("env.existing.environmentURI", null);
		
		boolean allOK = true;
		if (environment.getUseExistingEnv())
		{
			if (StringUtils.isEmpty(environment.getExistingSessionToken()))
			{
				logger.error("env.use.existing is set to true but no sessionToken has been provided. This is an invalid configuration in "+adapterProperties.getPropFileNameFull());
				allOK = false;
			}
			if (StringUtils.isEmpty(envURI))
			{
				logger.error("env.use.existing is set to true but no existing environment URI has been provided. This is an invalid configuration in "+adapterProperties.getPropFileNameFull());
				allOK = false;
			}
			if (allOK)
			{
				environment.setExistingEnvURI(envURI);
				if (environment.getExistingEnvURI() == null)
				{
					logger.error("The URI given in the property env.existing.environmentURI appears to be invalid in "+adapterProperties.getPropFileNameFull());
					allOK = false;
				}
			}
		}
		else
		{
			if (StringUtils.notEmpty(envURI))
			{
				environment.setExistingEnvURI(envURI);
			}
		}
		return allOK;
  	}

		
    /*
     * This method read the following Consumer Adapter specific properties:
     * adapter.mustUseAdvisoryIDs=true
     * env.xml.file.name=devLocal.xml
  	 * env.userToken=
  	 * env.instanceID=
  	 * env.baseURI=http://localhost:9080/SIF3InfraREST/sif3
  	 * events.enabled=true
	 * events.queue.strategy=ADAPTER_LEVEL
	 * events.queue.name=StudentConsumer
	 * events.queue.subscribers=3
	 * events.queue.type=IMMEDIATE
	 * events.polling.frequency=30
	 * events.longPolling.timeout=120
     */
    private boolean loadConsumerInfo(AdvancedProperties props, ConsumerEnvironment envInfo)
    {
  		boolean errorsFound = false;
  		
  		// Application Key
  		envInfo.getEnvironmentKey().setApplicationKey(adapterProperties.getPropertyAsString("env.application.key", null));
  		if (StringUtils.isEmpty(envInfo.getEnvironmentKey().getApplicationKey()))
  		{
  			logger.error("Property 'env.application.key' is null or empty in "+getAdapterFileNameWithoutExt()+".properties. Application Key must be set!");
  			errorsFound = true;    			
  		}
  		
  		// Password
  		envInfo.setPassword(adapterProperties.getPropertyAsString("env.pwd", null));
  		if (StringUtils.isEmpty(envInfo.getPassword()))
  		{
  			logger.error("Property 'env.pwd' is null or empty in "+getAdapterFileNameWithoutExt()+".properties. Password for this application must be set!");
  			errorsFound = true;    			
  		}
          
  		// Authentication Method
  		envInfo.setAuthMethod(adapterProperties.getPropertyAsString("env.authentication.method", AuthenticationMethod.Basic.name()));
 		
		envInfo.getEnvironmentKey().setUserToken(props.getPropertyAsString("env.userToken", null));
		envInfo.getEnvironmentKey().setInstanceID(props.getPropertyAsString("env.instanceID", null));

		envInfo.setUseAdvisory(props.getPropertyAsBool("adapter.mustUseAdvisoryIDs", false));
  		//System.out.println("Used Advisory: "+envInfo.getUseAdvisory());

		envInfo.setBaseURI(cleanURI(props.getPropertyAsString("env.baseURI", null), "env.baseURI"));
		if (envInfo.getBaseURI() == null)
		{
			logger.error("Property 'env.baseURI' is null, empty or invalid in "+getAdapterFileNameWithoutExt()+".properties. Base URI must be set!");
			errorsFound = true;    			
		}

  		envInfo.setTemplateXMLFileName(props.getPropertyAsString("env.xml.file.name", null));
  		if (StringUtils.isEmpty(envInfo.getTemplateXMLFileName()))
  		{
  			logger.error("Property 'env.xml.file.name' is null or empty in "+getAdapterFileNameWithoutExt()+".properties. File name for environment XML template must be set!");
  			errorsFound = true;    			
  		}
  		  		
		envInfo.setEventsEnabled(props.getPropertyAsBool("events.enabled", false));
		loadRemoteQueueConfigData(envInfo.getEventConfig(), props, "events", getDefaultQueueName(envInfo.getAdapterName(), true));
		
        envInfo.setDelayedEnabled(props.getPropertyAsBool("delayed.enabled", false));
        loadRemoteQueueConfigData(envInfo.getDelayedConfig(), props, "delayed", getDefaultQueueName(envInfo.getAdapterName(), false));

		if (errorsFound)
  		{
  			logger.error("Errors found in reading environment information from "+getAdapterFileNameWithoutExt()+".properties. See previous log entries for details and please correct them.");
  		}
  		  		
  		return errorsFound;
   }
    
    private boolean loadProviderInfo(AdvancedProperties props, ProviderEnvironment envInfo)
    {
    	boolean errorsFound = false;

		envInfo.setEventsSupported(props.getPropertyAsBool("env.events.supported", false));

    	// The following properties are required if it is a BROKERED environment. For DIRECT environments these values
    	// are read from the SIF_APP_TEMPLATE table!
		if (envInfo.getEnvironmentType() == EnvironmentType.BROKERED)
		{
	  		// Application Key
	  		envInfo.getEnvironmentKey().setApplicationKey(adapterProperties.getPropertyAsString("env.application.key", null));
	  		if (StringUtils.isEmpty(envInfo.getEnvironmentKey().getApplicationKey()))
	  		{
	  			logger.error("Property 'env.application.key' is null or empty in "+getAdapterFileNameWithoutExt()+".properties. Application Key must be set!");
	  			errorsFound = true;    			
	  		}
	  		
	  		// Password
	  		envInfo.setPassword(adapterProperties.getPropertyAsString("env.pwd", null));
	  		if (StringUtils.isEmpty(envInfo.getPassword()))
	  		{
	  			logger.error("Property 'env.pwd' is null or empty in "+getAdapterFileNameWithoutExt()+".properties. Password for this application must be set!");
	  			errorsFound = true;    			
	  		}
	          
	  		// Authentication Method
	  		envInfo.setAuthMethod(adapterProperties.getPropertyAsString("env.authentication.method", AuthenticationMethod.Basic.name()));
	  			  		
	  		envInfo.setTemplateXMLFileName(props.getPropertyAsString("env.xml.file.name", null));
	  		if (StringUtils.isEmpty(envInfo.getTemplateXMLFileName()))
	  		{
	  			logger.error("Property 'env.xml.file.name' is null or empty in "+getAdapterFileNameWithoutExt()+".properties. File name for environment XML template must be set!");
	  			errorsFound = true;    			
	  		}
		}

		// Base URL for connectors
		String connectorBaseURLStr = props.getPropertyAsString("env.connector.url", null);

		// Secure URL Base URL for connectors
		String secureConnectorBaseURLStr = props.getPropertyAsString("env.connector.url.secure", null);

		if (StringUtils.isEmpty(connectorBaseURLStr) && StringUtils.isEmpty(secureConnectorBaseURLStr))
		{
			logger.error("env.connector.url AND env.connector.url.secure for "+getAdapterFileNameWithoutExt()+".properties is missing. You must provide at least one of these URLs for them.");
			errorsFound = true;
		}
		else
		{
			if (StringUtils.notEmpty(connectorBaseURLStr))
			{
				envInfo.setConnectorBaseURI(cleanURI(connectorBaseURLStr, "env.connector.url"));
				if (envInfo.getConnectorBaseURI() == null)
				{
					errorsFound = true;
				}
			}
			if (StringUtils.notEmpty(secureConnectorBaseURLStr))
			{
				envInfo.setSecureConnectorBaseURI(cleanURI(secureConnectorBaseURLStr, "env.connector.url.secure"));
				if (envInfo.getSecureConnectorBaseURI() == null)
				{
					errorsFound = true;
				}
			}
		}  		
  		
		envInfo.setBaseURI(cleanURI(props.getPropertyAsString("env.baseURI", null), "env.baseURI"));
		if (envInfo.getEnvironmentType() == EnvironmentType.BROKERED) // The baseURI is required 
		{
			if (envInfo.getBaseURI() == null)
			{
				logger.error("Property 'env.baseURI' is null, empty or invalid in "+getAdapterFileNameWithoutExt()+".properties. Base URI must be set for brokered environments!");
				errorsFound = true;    			
			}
		}
            
		envInfo.setDefaultUpdateType(getUpdateType(props));
		
		// The properties below might only be applicable for DIRECT environments but this remains to be seen. Since they all have a default
		// value it should not matter if they are set or not and what environment we are in.
		envInfo.setAutoCreateEnvironment(props.getPropertyAsBool("env.allow.autoCreate", false));
		
  		// Authentication Method
  		envInfo.setAccessTokenAuthMethod(adapterProperties.getPropertyAsString("adapter.default.accessToken.authentication.method", AuthenticationMethod.Bearer.name()));

        // Allow access_token or URL
        envInfo.setAllowAuthOnURL(adapterProperties.getPropertyAsBool("adapter.authTokenOnURL.allowed", false));
        
        // Load custom response headers.
        errorsFound = errorsFound || loadCustomResponseHeaders(props, envInfo);

		if (errorsFound)
		{
			logger.error("Errors found in reading environment information from " + getAdapterFileNameWithoutExt() + ".properties. See previous log entries for details and please correct them.");
		}
		return errorsFound;
    }
    
    
    /*
     * Returns TRUE if there was an error loading custom http headers. Detailed error will be logged.
     */
    private boolean loadCustomResponseHeaders(AdvancedProperties props, ProviderEnvironment envInfo)
    {
        boolean errorsFound = false;
        HeaderProperties customHeaders = new HeaderProperties();
        envInfo.setCustomResponseHeaders(customHeaders);
        
        String headerList = props.getPropertyAsString("adapter.custom.response.headers", null);
        if (StringUtils.notEmpty(headerList))
        {
            // First we split on '|'
            String[] headerArray = headerList.trim().split("\\|");
            for (int i=0; i<headerArray.length; i++)
            {
                String headerStr = headerArray[i];
                
                // Next we split on the ":" to get header name and header value
                String[] nameValue = headerStr.split(":");
                
                // We must have 2 values in the array otherwise the header notation is incorrect.
                if (nameValue.length == 2)
                {
                    customHeaders.setHeaderProperty(nameValue[0].trim(), nameValue[1].trim());
                }
                else
                {
                    errorsFound = true;
                    logger.error("The value '"+headerStr+"' does not follow the notation for the adapter.custom.response.headers properties. Header Name and Header Value must be separated by a ':'. Different headers must be separated by a '|'.");
                }
            }
        }
        
        return errorsFound;
    }
    
    
    /*
     * This method read the following Consumer Adapter specific properties:
     * abc.queue.strategy=ADAPTER_LEVEL
     * abc.queue.name=StudentConsumer
     * abc.queue.subscribers=3
     * abc.queue.type=IMMEDIATE
     * abc.polling.frequency=30
     * abc.longPolling.timeout=120
     */
   private void loadRemoteQueueConfigData(RemoteQueueConfig remoteQueueConfig, AdvancedProperties props, String configPrefix, String defaultQueueName)
    {
        configPrefix = configPrefix+".";
        
        // Get queue name. If not provided use defaultQueueName.
        remoteQueueConfig.setQueueName(props.getPropertyAsString(configPrefix+"queue.name", defaultQueueName));
        remoteQueueConfig.setNumMsgQueueReaders(props.getPropertyAsInt(configPrefix+"queue.subscribers", 1));
        remoteQueueConfig.setQueueStrategy(getQueueStrategy(props, configPrefix));
        remoteQueueConfig.setQueueType(getQueueType(props, configPrefix));
        remoteQueueConfig.setPollFrequency(props.getPropertyAsInt(configPrefix+"polling.frequency", CommonConstants.DEFAULT_POLL_FREQ));
        remoteQueueConfig.setLongPollTimeOut(props.getPropertyAsInt(configPrefix+"longPolling.timeout", CommonConstants.DEFAULT_LONGPOLL_WAIT));
        remoteQueueConfig.setRemoveSubscribersOnShutdown(props.getPropertyAsBool(configPrefix+"subscriptions.removeOnShutdown", false));
    }
    
    /*
     * This uses the adapter name, removes all white spaces and adds a prefix of "EVENT_" (isEventConfig=TRUE) or 
     * "DELAYED_" (isEventConfig=FLASE) to make it a unique name.
     */
    private String getDefaultQueueName(String adapterName, boolean isEventConfig)
    {
        return (isEventConfig ? "EVENT_" : "DELAYED_") + adapterName.replaceAll("\\s+","");
    }
    	
	private URI cleanURI(String rawURI, String uriName)
	{
		if (rawURI != null)
		{
			// Remove trailing '/' if it is there
			rawURI = rawURI.trim();
			if (rawURI.endsWith("/"))
			{
				rawURI = rawURI.substring(0, rawURI.length() - 1);
			}
			try
			{
				return new URI(rawURI);
			}
			catch (Exception ex)
			{
				logger.error("The URI " + rawURI + " for " + uriName + " is an invalid URI. Please correct the URI");
				return null;
			}
		}
		else
		{
			return null;
		}
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
        
    private MediaType convertMediaType(String mediaType)
    {
  		mediaType = (StringUtils.isEmpty(mediaType) ? XML : mediaType.toUpperCase().trim());
  		return (mediaType.equals(JSON) ? MediaType.APPLICATION_JSON_TYPE : MediaType.APPLICATION_XML_TYPE);
    }

	private AdvancedProperties getProperties(String fileNameWithoutExt)
	{
		PropertyManager propMgr = PropertyManager.getInstance();
		
		// Check if it is already loaded.
		if (!propMgr.isLoaded(fileNameWithoutExt)) //NO: Load it this time
		{
			propMgr.loadPropertyFile(fileNameWithoutExt);
			
			// Is it loaded now. If so return it
			return propMgr.isLoaded(fileNameWithoutExt) ? propMgr.getProperties(fileNameWithoutExt) : null;
		}
		else
		{
			return propMgr.getProperties(fileNameWithoutExt);
		}
	}
}
