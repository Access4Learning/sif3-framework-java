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

import sif3.common.CommonConstants;
import sif3.common.CommonConstants.QueuePollingType;
import sif3.common.CommonConstants.QueueStrategy;


/**
 * This class is a sub-class of an environment. It adds properties typical in a consumer environment.
 * 
 * @author Joerg Huber
 * 
 */
public class ConsumerEnvironment extends EnvironmentInfo
{
	public static enum ConnectorName {environment, requestsConnector, provisionRequests, eventsConnector, queues, subscriptions};
        	
	private static final long serialVersionUID = -2685814104916173550L;

	private boolean connected = false;
	private boolean useAdvisory = false;
	private String templateXMLFileName = null;
	
	/* Event, Queue and Subscription related properties */
	private boolean eventsEnabled = false;
	private QueueStrategy queueStrategy = QueueStrategy.ADAPTER_LEVEL;
	private QueuePollingType queueType = QueuePollingType.IMMEDIATE;
	private int pollFrequency = CommonConstants.DEFAULT_POLL_FREQ;
	private int longPollTimeOut = CommonConstants.DEFAULT_LONGPOLL_WAIT;
	private String queueName = null;
	private int numMsgQueueReaders = 1;
	private boolean removeSubscribersOnShutdown = false;
		
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

	public boolean getEventsEnabled()
    {
    	return this.eventsEnabled;
    }

	public void setEventsEnabled(boolean eventsEnabled)
    {
    	this.eventsEnabled = eventsEnabled;
    }

	public QueueStrategy getQueueStrategy()
    {
    	return this.queueStrategy;
    }

	public void setQueueStrategy(QueueStrategy queueStrategy)
    {
    	this.queueStrategy = queueStrategy;
    }

	public String getQueueName()
    {
    	return this.queueName;
    }

	public void setQueueName(String queueName)
    {
    	this.queueName = queueName;
    }

	public int getNumMsgQueueReaders()
    {
    	return this.numMsgQueueReaders;
    }

	public void setNumMsgQueueReaders(int numMsgQueueReaders)
    {
    	this.numMsgQueueReaders = numMsgQueueReaders;
    }

	public QueuePollingType getQueueType()
    {
    	return this.queueType;
    }

	public void setQueueType(QueuePollingType queueType)
    {
    	this.queueType = queueType;
    }

	public int getPollFrequency()
    {
    	return this.pollFrequency;
    }

	public void setPollFrequency(int pollFrequency)
    {
    	this.pollFrequency = pollFrequency;
    }

	public int getLongPollTimeOut()
    {
    	return this.longPollTimeOut;
    }

	public void setLongPollTimeOut(int longPollTimeOut)
    {
    	this.longPollTimeOut = longPollTimeOut;
    }

	public boolean getRemoveSubscribersOnShutdown()
    {
    	return this.removeSubscribersOnShutdown;
    }

	public void setRemoveSubscribersOnShutdown(boolean removeSubscribersOnShutdown)
    {
    	this.removeSubscribersOnShutdown = removeSubscribersOnShutdown;
    }

	@Override
    public String toString()
    {
	    return "ConsumerEnvironment [connected=" + this.connected + ", useAdvisory="
	            + this.useAdvisory + ", templateXMLFileName=" + this.templateXMLFileName
	            + ", eventsEnabled=" + this.eventsEnabled + ", queueStrategy=" + this.queueStrategy
	            + ", queueType=" + this.queueType + ", pollFrequency=" + this.pollFrequency
	            + ", longPollTimeOut=" + this.longPollTimeOut + ", queueName=" + this.queueName
	            + ", numMsgQueueReaders=" + this.numMsgQueueReaders + ", removeSubscribersOnShutdown="
	            + this.removeSubscribersOnShutdown + ", toString()=" + super.toString() + "]";
    }
}
