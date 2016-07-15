/*
 * ConsumerEnvironment.java Created: 15/09/2013
 * 
 * Copyright 2013 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package sif3.infra.common.env.types;

/**
 * This class is a sub-class of an environment. It adds properties typical in a consumer environment.
 * 
 * @author Joerg Huber
 * 
 */
public class ConsumerEnvironment extends EnvironmentInfo
{
	public static enum ConnectorName {environment, requestsConnector, servicesConnector, provisionRequests, eventsConnector, queues, subscriptions};
        	
	private static final long serialVersionUID = -2685814104916173550L;

	private boolean connected = false;
	private boolean useAdvisory = false;
	private String templateXMLFileName = null;
	
	/* Event, Queue and Subscription related properties */
	private boolean eventsEnabled = false;
    private RemoteQueueConfig eventConfig = new RemoteQueueConfig();
	
    /* Delayed Response, Queue and Subscription related properties */
    private boolean delayedEnabled = false;
    private RemoteQueueConfig delayedConfig = new RemoteQueueConfig();
			
	/**
	 * Constructor
	 * 
	 * @param serviceName The name of the service/adapter.
	 */
	public ConsumerEnvironment(String serviceName)
    {
	    super();
	    setAdapterName(serviceName);
    }

	/**
	 * TRUE if the service/adapter is connected to the given environment. This only happens after an attempt is made to the 
	 * environment URL to establish a new or existing environment. If the service/adapter is not connected or failed to connect then this
	 * value is set to false (default).
	 * 
	 * @return See desc.
	 */
	public boolean getIsConnected()
    {
    	return this.connected;
    }

	public void setConnected(boolean connected)
    {
    	this.connected = connected;
    }
	
	public boolean getUseAdvisory()
	{
		return useAdvisory;
	}

	public void setUseAdvisory(boolean useAdvisory)
	{
		this.useAdvisory = useAdvisory;
	}

	public String getTemplateXMLFileName()
    {
    	return this.templateXMLFileName;
    }

	public void setTemplateXMLFileName(String templateXMLFileName)
    {
    	this.templateXMLFileName = templateXMLFileName;
    }

	   /**
     * @return the eventsEnabled
     */
    public boolean getEventsEnabled()
    {
        return eventsEnabled;
    }

    /**
     * @param eventsEnabled the eventsEnabled to set
     */
    public void setEventsEnabled(boolean eventsEnabled)
    {
        this.eventsEnabled = eventsEnabled;
    }

    /**
     * @return the eventConfig
     */
    public RemoteQueueConfig getEventConfig()
    {
        return eventConfig;
    }

    /**
     * @param eventConfig the eventConfig to set
     */
    public void setEventConfig(RemoteQueueConfig eventConfig)
    {
        this.eventConfig = eventConfig;
    }

    /**
     * @return the delayedEnabled
     */
    public boolean getDelayedEnabled()
    {
        return delayedEnabled;
    }

    /**
     * @param delayedEnabled the delayedEnabled to set
     */
    public void setDelayedEnabled(boolean delayedEnabled)
    {
        this.delayedEnabled = delayedEnabled;
    }

    /**
     * @return the delayedConfig
     */
    public RemoteQueueConfig getDelayedConfig()
    {
        return delayedConfig;
    }

    /**
     * @param delayedConfig the delayedConfig to set
     */
    public void setDelayedConfig(RemoteQueueConfig delayedConfig)
    {
        this.delayedConfig = delayedConfig;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ConsumerEnvironment [connected=" + connected + ", useAdvisory=" + useAdvisory
                + ", templateXMLFileName=" + templateXMLFileName + ", eventsEnabled="
                + eventsEnabled + ", eventConfig=" + eventConfig + ", delayedEnabled="
                + delayedEnabled + ", delayedConfig=" + delayedConfig + ", toString()="
                + super.toString() + "]";
    }    
}
