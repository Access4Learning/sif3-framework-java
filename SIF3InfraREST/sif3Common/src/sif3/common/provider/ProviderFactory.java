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

package sif3.common.provider;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import sif3.common.conversion.ModelObjectInfo;
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
	private final Logger logger = Logger.getLogger(getClass());

	private static ProviderFactory factory = null;
	private HashMap<ModelObjectInfo, Provider> providers = new HashMap<ModelObjectInfo, Provider>();
	
	/**
	 * This methods returns the ProviderFactory. Only used internally to initialse and access various components of the framework.
	 * 
	 * @param adapterProps The property file for this provider adapter.
	 * 
	 * @return See desc.
	 */
	public static ProviderFactory getFactory(AdvancedProperties adapterProps)
	{
	    if (factory == null)
	    {
	      factory = new ProviderFactory(adapterProps);
	    }
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
	private ProviderFactory(AdvancedProperties adapterProps)
	{
		super();
		
		// Initialise all Providers listed in the Service Property File
		initialiseProviders(adapterProps);
	}
	
	private void initialiseProviders(AdvancedProperties adapterProps)
	{
	    List<String> classList = adapterProps.getFromCommaSeparated("provider.classes");
	    String basePackageName = makePackageName(adapterProps.getPropertyAsString("provider.basePackageName", ""));
	    String serviceID = adapterProps.getPropertyAsString("adapter.id", null);
	    
	    for (String className : classList)
	    {
	        logger.debug("Provider class to initialse: " + className);
	        try
	        {
	            Class<?> clazz = Class.forName(basePackageName + className);
	            
	            // Set info for Constructor parameters
	            Class<?> paramTypes[] = new Class[2];
	            paramTypes[0] = String.class;
	            paramTypes[1] = AdvancedProperties.class;
	            Object paramList[] = new Object[2];
	            paramList[0] = serviceID;
	            paramList[1] = adapterProps;
	
	            // Get constructor and instantiate it.
	            Constructor<?> ct = clazz.getConstructor(paramTypes);
	            Object classObj = ct.newInstance(paramList);
	            
	            // Set properties and add it to correct structure
	            if (classObj instanceof BaseProvider)
	            {
	            	BaseProvider provider = (BaseProvider)classObj;
	            	joinFactory(provider.getMultiObjectClassInfo(), provider);
	            }
	            else
	            {
	                logger.error("Subscriber class " + className + " doesn't extend RESTBaseSubscriber. Cannot initialse the Subscriber.");
	            }
	        }
	        catch (Exception ex)
	        {
	            logger.error("Cannot create Subscriber Object " + basePackageName + className + ": " + ex.getMessage(), ex);
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
	private synchronized boolean joinFactory(ModelObjectInfo objectInfo, Provider provider)
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

}
