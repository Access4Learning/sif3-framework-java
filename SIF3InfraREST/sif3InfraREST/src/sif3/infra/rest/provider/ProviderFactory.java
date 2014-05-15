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

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import sif3.common.conversion.ModelObjectInfo;
import sif3.common.interfaces.Provider;
import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.StringUtils;

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

	private static final Logger logger = Logger.getLogger(ProviderFactory.class);

	private static ProviderFactory factory = null;
	private HashMap<ModelObjectInfo, BaseProvider> providers = new HashMap<ModelObjectInfo, BaseProvider>();

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
        logger.debug("Total Threads running before initialising provider Factory: " +Thread.activeCount()+" threads.");
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
        logger.debug("Total Threads running after initialising Provider Factory: " +Thread.activeCount()+" threads.");
	    return factory;
	}
	
	/**
	 * This will shut down each provider class that make up this provider
	 */
	public static synchronized void shutdown()
	{
	    if (factory != null)
	    {
    		for (BaseProvider provider : factory.providers.values())
    		{
    	    	try
    	    	{
    				logger.debug("Finalise provider "+provider.getMultiObjectClassInfo().getObjectName()+"...");
    	    		provider.finalise();
    	    	}
    	    	catch (Exception ex)
    	    	{
    	    		// nothing we can really do. We are shutting down anyway...
    	    	}
    		}
    		
    		// Shut down provider threads.
    		logger.debug("Shut Down Provider Thread Pool...");
    		factory.providerService.shutdownNow();
    		logger.debug("Shut Down Provider Thread Pool: Done");
	    }
        logger.info("All providers are shut down.");
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
			return providers.get(objectInfo);
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
	            
	            // Set info for Constructor parameters
              Constructor<?> ct = clazz.getConstructor(new Class[] {});
	            
	            // Instantiate class.
              Object classObj = ct.newInstance();
	            
	            // Set properties and add it to correct structure
	            if (classObj instanceof BaseProvider)
	            {
	            	BaseProvider provider = (BaseProvider)classObj;
	            	joinFactory(provider.getMultiObjectClassInfo(), provider);
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

	/*
	 * Add a provider to the provider Factory. TRUE: Provider successfully added. FALSE: Provider not added. See error log for details.
	 * 
	 * @param objectInfo This information is used within the factory to know what the provider deals with. The ObjectName property must be set
	 *                   otherwise the provider won't be added to the factory and an error woll be logged.
	 * @param provider
	 * @return
	 */
	private synchronized boolean joinFactory(ModelObjectInfo objectInfo, BaseProvider provider)
	{
		if ((objectInfo != null) && (StringUtils.notEmpty(objectInfo.getObjectName())))
		{
			providers.put(objectInfo, provider);
			return true;
		}
		else
		{
			logger.error("The ModelObjectInfo parameter is either null or does not have the ObjectName property set. This is required! Provider '"+provider.getClass().getSimpleName()+" not added to provider factory.");
			return false;
		}
	}

	
	private void startProviders(AdvancedProperties adapterProps) throws Exception
	{
		int delay = adapterProps.getPropertyAsInt(DELAY_PROPERTY, DEFAULT_DELAY);  //delay between threads in seconds
		logger.debug("Start up delay between providers is: "+delay+" seconds");

		int i = 0;
		for (BaseProvider provider : providers.values())
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
