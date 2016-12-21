/*
 * RemoteQueueConfig.java
 * Created: 15 Oct 2015
 *
 * Copyright 2015 Systemic Pty Ltd
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

import java.io.Serializable;

import sif3.common.CommonConstants;
import sif3.common.CommonConstants.QueuePollingType;
import sif3.common.CommonConstants.QueueStrategy;

/**
 * This class is a simple POJO to hold consumer properties in relation to queues, subscriptions and alike configuration. The 
 * framework allows this type of properties to be set for event related configuration as well as delayed response configuration.
 * 
 * @author Joerg Huber
 *
 */
public class RemoteQueueConfig implements Serializable
{
    private static final long serialVersionUID = 3860193877063082333L;
    
    private QueueStrategy queueStrategy = QueueStrategy.ADAPTER_LEVEL;
    private QueuePollingType queueType = QueuePollingType.IMMEDIATE;
    private int pollFrequency = CommonConstants.DEFAULT_POLL_FREQ;
    private int longPollTimeOut = CommonConstants.DEFAULT_LONGPOLL_WAIT;
    private String queueName = null;
    private int numMsgQueueReaders = 1;
    private boolean removeSubscribersOnShutdown = false;
    
    
    /**
     * @return the queueStrategy
     */
    public QueueStrategy getQueueStrategy()
    {
        return queueStrategy;
    }
    
    /**
     * @param queueStrategy the queueStrategy to set
     */
    public void setQueueStrategy(QueueStrategy queueStrategy)
    {
        this.queueStrategy = queueStrategy;
    }
    
    /**
     * @return the queueType
     */
    public QueuePollingType getQueueType()
    {
        return queueType;
    }
    
    /**
     * @param queueType the queueType to set
     */
    public void setQueueType(QueuePollingType queueType)
    {
        this.queueType = queueType;
    }
    
    /**
     * @return the pollFrequency
     */
    public int getPollFrequency()
    {
        return pollFrequency;
    }
    
    /**
     * @param pollFrequency the pollFrequency to set
     */
    public void setPollFrequency(int pollFrequency)
    {
        this.pollFrequency = pollFrequency;
    }
    
    /**
     * @return the longPollTimeOut
     */
    public int getLongPollTimeOut()
    {
        return longPollTimeOut;
    }
    
    /**
     * @param longPollTimeOut the longPollTimeOut to set
     */
    public void setLongPollTimeOut(int longPollTimeOut)
    {
        this.longPollTimeOut = longPollTimeOut;
    }
    
    /**
     * @return the queueName
     */
    public String getQueueName()
    {
        return queueName;
    }
    
    /**
     * @param queueName the queueName to set
     */
    public void setQueueName(String queueName)
    {
        this.queueName = queueName;
    }
    
    /**
     * @return the numMsgQueueReaders
     */
    public int getNumMsgQueueReaders()
    {
        return numMsgQueueReaders;
    }
    
    /**
     * @param numMsgQueueReaders the numMsgQueueReaders to set
     */
    public void setNumMsgQueueReaders(int numMsgQueueReaders)
    {
        this.numMsgQueueReaders = numMsgQueueReaders;
    }
    
    /**
     * @return the removeSubscribersOnShutdown
     */
    public boolean getRemoveSubscribersOnShutdown()
    {
        return removeSubscribersOnShutdown;
    }
    
    /**
     * @param removeSubscribersOnShutdown the removeSubscribersOnShutdown to set
     */
    public void setRemoveSubscribersOnShutdown(boolean removeSubscribersOnShutdown)
    {
        this.removeSubscribersOnShutdown = removeSubscribersOnShutdown;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "RemoteQueueConfig [queueStrategy=" + queueStrategy + ", queueType=" + queueType
                + ", pollFrequency=" + pollFrequency + ", longPollTimeOut=" + longPollTimeOut
                + ", queueName=" + queueName + ", numMsgQueueReaders=" + numMsgQueueReaders
                + ", removeSubscribersOnShutdown=" + removeSubscribersOnShutdown + "]";
    }
}
