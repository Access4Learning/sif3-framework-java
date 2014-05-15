/*
 * BaseProvider.java
 * Created: 01/10/2013
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

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import sif3.common.CommonConstants;
import sif3.common.interfaces.EventProvider;
import sif3.common.interfaces.Provider;
import sif3.infra.common.env.mgr.ProviderManagerFactory;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType;
import au.com.systemic.framework.utils.AdvancedProperties;

/**
 * This is the main class each specific provider of a given SIF Object type must extends to implement the CRUD operation as defined
 * by the SIF3 specification. It is a base implementation of the request connector. It implements some common functionality each provider
 * may require. It also ensures that all components of a provider service are wired up correctly within the framework. Please refer to 
 * the Developer's Guide for some more details about this class.
 *
 * @author Joerg Huber
 */
public abstract class BaseProvider implements Provider, Runnable
{
	protected final Logger logger = Logger.getLogger(getClass());

	private Timer eventTimer = null;
	private TimerTask eventTimerTask = null;
	
	/**
	 * Shuts down the sub-class provider. This should release all associated resources with that provider.
	 */
	public abstract void shutdown();
	
	  /**
     */
    public BaseProvider()
    {
	    super();
    }
    
    /**
     * Returns the Provider Configuration in a nice form. Should rarely be required by the concrete implementation of a provider class.
     * 
     * @return See desc.
     */
    public ProviderEnvironment getProviderEnvironment()
    {
      return (ProviderEnvironment)ProviderManagerFactory.getEnvironmentManager().getEnvironmentInfo();
    }
    
    /**
     * Utility method to easily retrieve the property file content for a provider.
     * 
     * @return See desc
     */
    public AdvancedProperties getServiceProperties()
    {
      return ProviderManagerFactory.getEnvironmentManager().getServiceProperties();
    }
    
    /**
     * Utility method. Mainly used for useful logging messages.
     * 
     * @return Returns the Service Id concatenated with the Provider Name.
     */
    public String getPrettyName()
    {
      return getProviderEnvironment().getAdapterName()+" - " + getProviderName();
    }

    /**
     * Utility method. Returns the class name of the provider.
     * 
     * @return Returns the Provider Class Name.
     */
    public String getProviderName()
    {
    	return getClass().getSimpleName();
    }
    
    /**
     * This method returns the actual service name (NOT serviceID). The service name is the name that is used in URL paths to identify the
     * SIF Object that we are dealing with. The service name is the plural form of the SIF Object name as specified in the SIF3 Spec. 
     * @return See Desc.
     */
    public String getServiceName()
    {
    	return getMultiObjectClassInfo().getObjectName();
    }
    
    /**
     * (non-Javadoc)
     * @see sif3.common.interfaces.Provider#finalise()
     */
    public void finalise()
    {
    	// Shut down event timer & task - thread
    	if (eventTimerTask != null)
    	{
    		logger.debug("Shut Down event task for: "+getProviderName());
    		eventTimerTask.cancel();
    		eventTimerTask = null;
    	}
    	if (eventTimer != null)
    	{
    		logger.debug("Shut Down event timer for: "+getProviderName());
    		eventTimer.cancel();
    		eventTimer.purge();
    		eventTimer = null;
    	}
    	
    	// Call finalise on sub-class.
    }

    /*----------------------------------------*/
    /* Implemented Method for Multi-threading */
    /*----------------------------------------*/

	/**
	 * This method is all that is needed to run the provider in its own thread. The thread is executed at
	 * given intervals driven by a property in the adapter's property file. The interval/frequency
	 * defined in there is used to determine how often this thread is run.
	 */
    @Override
    public final synchronized void run()
    {
    	String providerName = getProviderName();
    	boolean checkEnvType = getServiceProperties().getPropertyAsBool("provider.check.envType", true);
    	
		logger.debug("Start "+providerName+ " thread....");
    	
    	//Only if environment does support events we will start the event manager
    	if (getProviderEnvironment().getEventsSupported())
    	{	
        	// If this is a DIRECT environment then events are not supported, yet.
        	if ((getProviderEnvironment().getEnvironmentType() == EnvironmentType.DIRECT) && checkEnvType)
        	{
            	logger.info("The DIRECT Provider for this framework does NOT support events, yet.");
        	}
        	else
        	{
            	// Check if the provider implements the events interface. Only then events might be required.
    	    	if (EventProvider.class.isAssignableFrom(getClass()))
    	    	{
		        	int frequency = getEventFrequency(providerName);    	
		    		if (frequency != CommonConstants.NO_EVENT)
		    		{
			    		logger.debug("Events supported for this "+providerName+". Start up event thread.");
		    	    	startupEventManager(providerName, frequency);
		    		}
		    		else
		    		{
			    		logger.info("Events supported for  "+providerName+" but currently turned off (frequency=0)");
		    		}
    	    	}
    	    	else
    	    	{
    	        	logger.debug("Events NOT supported for "+providerName+". Provider does not implement EventProvider interface.");
    	    	}
        	}
    	}
    	else
    	{
        	logger.debug("Environment "+getProviderEnvironment().getEnvironmentName()+ " does NOT support events.");    		
    	}
		logger.debug(providerName+" started.");
    }

    
    /*---------------------*/
    /*-- Private methods --*/
    /*---------------------*/

	/**
	 * This method initialises and schedules the event producer task.
	 */
    //TODO: JH - Consider if I should use Executors.newSingleThreadScheduledExecutor style task/timers here.
	private void startupEventManager(String providerName, int frequencyInSec)
	{
		int period = frequencyInSec * CommonConstants.MILISEC;  // repeat every so often (multiply with milliseconds).
		int delayInSec = getEventDelay(providerName);
		
		logger.info(providerName+".startupEventManager: Event Frequency = " + frequencyInSec + " secs; Event Startup Delay = "+delayInSec+" secs.");
		if (eventTimerTask == null) // not created started
		{
			eventTimerTask = new TimerTask() 
			{
				public void run() 
				{
					logger.debug("Start Event Timer Task for "+getMultiObjectClassInfo().getObjectName()+".");
					((BaseEventProvider<?>)BaseProvider.this).broadcastEvents();
				}
			};
			
			// Now start scheduling events
			logger.debug("Start sending "+getMultiObjectClassInfo().getObjectName()+" events... (Total running threads = "+Thread.activeCount()+")");
			eventTimer = new Timer(true);
			eventTimer.scheduleAtFixedRate(eventTimerTask, delayInSec * CommonConstants.MILISEC, period);
		}
	}
	
	/* 0 = No events */
	private int getEventFrequency(String providerName)
	{
    	return getServiceProperties().getPropertyAsInt(CommonConstants.FREQ_PROPERTY, providerName, CommonConstants.NO_EVENT);    	
	}

	private int getEventDelay(String providerName)
	{
    	int delay = getServiceProperties().getPropertyAsInt(CommonConstants.EVENT_DELAY_PROPERTY, providerName, CommonConstants.DEFAULT_EVENT_DELAY);
    	return (delay < CommonConstants.DEFAULT_EVENT_DELAY) ? CommonConstants.DEFAULT_EVENT_DELAY : delay;
	}
}
