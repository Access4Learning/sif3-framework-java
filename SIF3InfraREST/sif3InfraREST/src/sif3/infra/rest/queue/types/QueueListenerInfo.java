/*
 * QueueListenerInfo.java
 * Created: 06/05/2014
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

package sif3.infra.rest.queue.types;

import java.io.Serializable;
import java.util.HashMap;

import sif3.common.persist.model.SIF3Queue;
import sif3.infra.rest.queue.LocalConsumerQueue;

/**
 * This is a utility class only used at the low level within the framework. It is not intended to be used at any higher levels.
 * It mainly is a POJO to hold information about a local queue and subscriptions relating to that queue.
 *  
 * @author Joerg Huber
 *
 */
public class QueueListenerInfo implements Serializable
{
    private static final long serialVersionUID = -668070892217027127L;
    
	private SIF3Queue queue;
	//private HashMap<SubscriptionKey, EventConsumer<?>> consumers = new HashMap<SubscriptionKey, EventConsumer<?>>();

	private HashMap<SubscriptionKey, LocalConsumerQueue> consumerQueues = new HashMap<SubscriptionKey, LocalConsumerQueue>();

	
	public QueueListenerInfo(SIF3Queue queue)
	{
		super();
		setQueue(queue);
	}
	
	public QueueListenerInfo(SIF3Queue queue, SubscriptionKey key, LocalConsumerQueue consumerQueue)
	{
		super();
		setQueue(queue);
		addLocalConsumerQueue(key, consumerQueue);
	}

	public SIF3Queue getQueue()
    {
    	return this.queue;
    }
	
	public void setQueue(SIF3Queue queue)
    {
    	this.queue = queue;
    }
	
	public void addLocalConsumerQueue(SubscriptionKey key, LocalConsumerQueue consumerQueue)
	{
		getConsumerQueues().put(key, consumerQueue);
	}
	
	public void addLocalConsumerQueue(String zoneID, String contextID, String serviceName, String serviceType, LocalConsumerQueue consumerQueue)
	{
	  getConsumerQueues().put(new SubscriptionKey(zoneID, contextID, serviceName, serviceType) , consumerQueue);
	}

	public LocalConsumerQueue getLocalConsumerQueue(SubscriptionKey key)
	{
		return getConsumerQueues().get(key);
	}
	
	public LocalConsumerQueue getLocalConsumerQueue(String zoneID, String contextID, String serviceName, String serviceType)
	{
		return getConsumerQueues().get(new SubscriptionKey(zoneID, contextID, serviceName, serviceType));
	}
	
	public HashMap<SubscriptionKey, LocalConsumerQueue> getConsumerQueues()
    {
    	return this.consumerQueues;
    }
	
	public void setConsumerQueues(HashMap<SubscriptionKey, LocalConsumerQueue> consumerQueues)
    {
    	this.consumerQueues = consumerQueues;
    }
}
