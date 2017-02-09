/*
 * ProviderFactory.java
 * Created: 29/09/2013
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

package sif3.infra.rest.provider;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.StringUtils;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.interfaces.Provider;

/**
 * This is the provider factory. Each provider deals with a number of objects (i.e. StudentPersonal, SchoolInfo etc). 
 * The framework requires a separate class for each object type a provider deals with. This factory is wiring it all up in a manner
 * that it can easily be accessed with the internals of the framework. This factory requires the property file of the provider/adapter
 * for it to initialise and configure.
 * 
 * @author Joerg Huber
 */
public class ProviderFactory
{
	private static final int DEFAULT_DELAY = 10;
	private static final String DELAY_PROPERTY = "provider.startup.delay";

	private final static Logger logger = LoggerFactory.getLogger(ProviderFactory.class);

	private static ProviderFactory factory = null;
	
	// Active Providers for event publishing. These providers run in the background as an independent thread.
	private HashMap<ModelObjectInfo, BaseProvider> eventProviders = new HashMap<ModelObjectInfo, BaseProvider>();
	
	// Known providers that can be instantiated for standard request/response
	private HashMap<ModelObjectInfo, ProviderClassInfo> providerClasses = new HashMap<ModelObjectInfo, ProviderClassInfo>();
	
	private static Object locked = new Object();
  
	private ScheduledExecutorService providerService = null;

	/**
	 * This methods returns the ProviderFactory. Only used internally to initialise and access various components of the framework.
	 * 
	 * @param adapterProps The property file for this provider adapter.
	 * 
	 * @return See desc.
	 */
	public static synchronized ProviderFactory createFactory(AdvancedProperties adapterProps)
	{
        synchronized (locked)
        {
            logger.debug("Total Threads running before initialising provider Factory: " + Thread.activeCount() + " threads.");
            if (factory == null)
            {
                try
                {
                    factory = new ProviderFactory(adapterProps);
                }
                catch (Exception ex)
                {
                    logger.error("Failed to initialise provider factory. Provider won't run.", ex);
                    factory = null;
                }
            }
            logger.debug("Total Threads running after initialising Provider Factory: " + Thread.activeCount() + " threads.");
            return factory;
        }
	}
	
	/**
	 * This will shut down each provider class that make up this provider
	 */
	public static synchronized void shutdown()
	{
        synchronized (locked)
        {
            if (factory != null)
            {
                for (BaseProvider provider : factory.eventProviders.values())
                {
                    try
                    {
                        logger.debug("Finalise provider " + provider.getMultiObjectClassInfo().getObjectName() + "...");
                        provider.finalise();
                    }
                    catch (Exception ex)
                    {
                        // nothing we can really do. We are shutting down anyway...
                    }
                }

                // Shut down provider threads.
                logger.debug("Shut Down Provider Thread Pool...");
                if (factory.providerService != null)
                {
                    factory.providerService.shutdownNow();
                }
                logger.debug("Shut Down Provider Thread Pool: Done");
            }
            logger.info("All providers are shut down.");
        }
	}
	
	/**
	 * Returns an instance of this provider factory. If it has not been created before then this will return null;
	 * 
	 * @return See Desc.
	 */
	public static ProviderFactory getInstance()
	{
	    return factory;
	}
	
	/**
	 * This method is intended to be used to get a new instance of an object provider class to be used for request/response methods.
	 * If provider doesn't exist for the given objectInfo then null is returned.
	 * 
	 * @param objectInfo must have at least the ObjectName property set otherwise null is returned and an error is logged.
	 * 
	 * @return See desc.
	 */
	public Provider getProvider(ModelObjectInfo objectInfo)
	{
        if ((objectInfo != null) && (StringUtils.notEmpty(objectInfo.getObjectName())))
        {
            ProviderClassInfo providerClassInfo = providerClasses.get(objectInfo);
            if (providerClassInfo != null)
            {
                try
                {
                    return (BaseProvider) providerClassInfo.getClassInstance(null);
                }
                catch (Exception ex)
                {
                    logger.error("Failed to instantiate a provider for " + objectInfo.getObjectName() + ": " + ex.getMessage(), ex);
                    return null;
                }
            }
            else // no known provider for the given Object Type
            {
                return null;
            }
        }
        else
        {
            logger.error("The ModelObjectInfo parameter is either null or does not have the ObjectName property set. This is required! No Provider returned.");
            return null;
        }
	}
	
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private ProviderFactory(AdvancedProperties adapterProps) throws Exception
	{
		super();
		
		// Initialise all Providers listed in the Service Property File
		initialiseProviders(adapterProps);
		startProviders(adapterProps);
	}
	
	private void initialiseProviders(AdvancedProperties adapterProps)
	{
        List<String> classList = adapterProps.getFromCommaSeparated("provider.classes");
        String basePackageName = makePackageName(adapterProps.getPropertyAsString("provider.basePackageName", ""));
        for (String className : classList)
        {
            logger.debug("Provider class to initialse: " + className);
            try
            {
                Class<?> clazz = Class.forName(basePackageName + className);
                ProviderClassInfo providerClassInfo = new ProviderClassInfo(clazz, new Class[] {});

                Object classObj = providerClassInfo.getClassInstance(null);

                // Set properties and add it to correct structure
                if (classObj instanceof BaseProvider)
                {
                    BaseProvider provider = (BaseProvider) classObj;
                    ModelObjectInfo objectInfo = provider.getMultiObjectClassInfo();
                    if ((objectInfo != null) && (StringUtils.notEmpty(objectInfo.getObjectName())))
                    {
                        // First add it to the standard request/response hashmap
                        providerClasses.put(objectInfo, providerClassInfo);

                        // Add it to hasmap for background threads
                        eventProviders.put(objectInfo, provider);
                    }
                    else
                    {
                        logger.error("The ModelObjectInfo parameter is either null or does not have the ObjectName property set. This is required! Provider '" + provider.getClass().getSimpleName() + " not added to provider factory.");
                    }
                }
                else
                {
                    logger.error("Provider class " + className + " doesn't extend BaseProvider. Cannot initialse the Provider.");
                }
            }
            catch (Exception ex)
            {
                logger.error("Cannot create Provider Class " + basePackageName + className + ": " + ex.getMessage(), ex);
            }
        }
	}
	
    private String makePackageName(String packageName)
    {
    	return (StringUtils.isEmpty(packageName)) ? "" : packageName.trim() + ".";
    }

	private void startProviders(AdvancedProperties adapterProps) throws Exception
	{
		int delay = adapterProps.getPropertyAsInt(DELAY_PROPERTY, DEFAULT_DELAY);  //delay between threads in seconds
		logger.debug("Start up delay between providers is: "+delay+" seconds");

		int i = 0;
		for (BaseProvider provider : eventProviders.values())
		{	
			// Create thread in thread pool
			providerService = Executors.newSingleThreadScheduledExecutor();
			
			// Ensure there is 10 seconds between the start of each publisher so that they don't hammer
			// the system at the same time during startup. Startup thread on thread pool.
			providerService.schedule(provider, i*delay, TimeUnit.SECONDS);
			i++;
		}	
	}
}
