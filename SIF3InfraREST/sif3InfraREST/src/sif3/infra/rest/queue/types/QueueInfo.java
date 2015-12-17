/*
 * QueueInfo.java
 * Created: 09/11/2015
 *
 * Copyright 2015 Systemic Pty Ltd
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

import sif3.common.model.SubscriptionKey;
import sif3.common.persist.model.SIF3Queue;
import sif3.infra.common.env.types.RemoteQueueConfig;
import sif3.infra.rest.queue.LocalConsumerQueue;

/**
 * This class is links the SIF3 Queue with the information relating to Queue Strategy and its local subscriptions to the local Blocking-Push queue.
 * 
 * @author Joerg Huber
 *
 */
public class QueueInfo implements Serializable
{
    private static final long serialVersionUID = -4395199431194735390L;

	public enum QueueType {EVENT_QUEUE, DELAYED_QUEUE};
	
	// The info about the SIF3 Queue
	private SIF3Queue queue;
	
	// What the queue is used for.
	private QueueType queueType = QueueType.EVENT_QUEUE;
	
	// The setup of the queue strategy and concurrent reader.
	private RemoteQueueConfig remoteQueueConfig = null;
	
	// Set of subscription keys applicable for this queue and the local push queue they relate to.
	private HashMap<SubscriptionKey, LocalConsumerQueue> listeners = new HashMap<SubscriptionKey, LocalConsumerQueue>();

	public QueueInfo(SIF3Queue queue, QueueType queueType, RemoteQueueConfig remoteQueueConfig)
	{
		super();
		setQueue(queue);
		setQueueType(queueType);
		setRemoteQueueConfig(remoteQueueConfig);
	}
	
	public SIF3Queue getQueue()
	{
		return queue;
	}

	public void setQueue(SIF3Queue queue)
	{
		this.queue = queue;
	}

	public QueueType getQueueType()
	{
		return queueType;
	}

	public void setQueueType(QueueType queueType)
	{
		this.queueType = queueType;
	}

	public RemoteQueueConfig getRemoteQueueConfig()
	{
		return remoteQueueConfig;
	}

	public void setRemoteQueueConfig(RemoteQueueConfig remoteQueueConfig)
	{
		this.remoteQueueConfig = remoteQueueConfig;
	}

    /**
     * @return the listeners
     */
    public HashMap<SubscriptionKey, LocalConsumerQueue> getListeners()
    {
        return listeners;
    }

    /**
     * @param listeners the listeners to set
     */
    public void setListeners(HashMap<SubscriptionKey, LocalConsumerQueue> listeners)
    {
        this.listeners = listeners;
    }
    
    public void addListener(LocalQueueServiceInfo localQueueInfo)
    {
    	if (localQueueInfo != null)
    	{
    		getListeners().put(localQueueInfo.getService().getSubscriptionKey(), localQueueInfo.getLocalQueue());
    	}
    }

	public LocalConsumerQueue getLocalConsumerQueue(SubscriptionKey key)
	{
		return getListeners().get(key);
	}
	
	public LocalConsumerQueue getLocalConsumerQueue(String zoneID, String contextID, String serviceName, String serviceType)
	{
		return getListeners().get(new SubscriptionKey(zoneID, contextID, serviceName, serviceType));
	}


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "QueueInfo [queue=" + queue + ", queueType=" + queueType + ", remoteQueueConfig="
                + remoteQueueConfig + ", listeners=" + listeners + "]";
    }
}
