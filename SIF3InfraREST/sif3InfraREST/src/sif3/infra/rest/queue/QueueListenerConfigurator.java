/*
 * QueueListenerConfigurator.java
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

package sif3.infra.rest.queue;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.model.ServiceInfo;
import sif3.common.persist.model.SIF3Queue;
import sif3.common.persist.model.SIF3Subscription;
import sif3.common.persist.service.SIF3QueueService;
import sif3.common.persist.service.SIF3SubscriptionService;
import sif3.infra.rest.queue.types.QueueListenerInfo;
import sif3.infra.rest.queue.types.SubscriptionKey;
import au.com.systemic.framework.utils.StringUtils;

/**
 * This method is an utility class to abstract the quite complex mechanism of building the local consumer queue configuration with the remote
 * queue configuration. Besides, this method will also configure the applicable local consumer instances for each queue. This will allow the
 * multi-threaded consumption of messages in the consumers. The intent of this class is that the methods in here allow to build up the
 * queue configuration and consumer threads as event consumers join the assembly. Once all consumers have joined the final configuration
 * can be retrieved and then passed along to a higher level class that will kick off the message reading and processing in a controlled manner.
 * 
 * @author Joerg Huber
 *
 */
public class QueueListenerConfigurator
{
  protected final Logger logger = Logger.getLogger(getClass());
	private static final AdapterType CONSUMER = AdapterType.CONSUMER;
	
  private String environmentID;
  private List<SIF3Queue> dbQueues = null;
	private List<SIF3Subscription> dbSubscriptions = null;
	
	// Make this varaible static, so that it is stick within a deployment
	private static boolean isFinalised = false;
	
	/* This is the list that holds all active queues and their consumers */
	private HashMap<String, QueueListenerInfo> listeners = new HashMap<String, QueueListenerInfo>();
	

	/**
	 * This method will instantiate the queue listener configuration for the given environment and environment configuration. After
	 * this call consumers can register with the configurator.<br/><br/>
	 * 
	 * Assumption:<br/>
	 * It is assumed that the ConsumerQueueConnector & ConsumerSubscriptionConnector have been called before a call to this
	 * method is done. This is to ensure that queues and subscriptions are all known on remote location as well as properly
	 * sync'ed with the local DB.
	 * 
	 * @param environmentID The environment ID this consumer deals with.
	 * 
	 * @throws IllegalArgumentException The consumerEnvInfo or environmentID is null or empty.
	 * @throws PersistenceException Underlying DB cannot be accessed to load queue and subscription configuration.
	 */	
  public QueueListenerConfigurator(String environmentID) throws IllegalArgumentException, PersistenceException  
  {
    super();
    
    if (!isFinalised)
    {
      if (StringUtils.isEmpty(environmentID))
      {
        throw new IllegalArgumentException("environmentID parameter is null or empty.");
      }

      setEnvironmentID(environmentID);
      
      // Lets get a list of valid queues and subscriptions for this consumer. This will be needed at the time when a
      // consumer wants to join the queue lister.   
      SIF3SubscriptionService subscriptionService = new SIF3SubscriptionService();
      SIF3QueueService queueService = new SIF3QueueService();
      
      setDbQueues(queueService.getQueuesForEnvironment(getEnvironmentID(), CONSUMER));
      setDbSubscriptions(subscriptionService.getSubscriptionsForEnvironment(getEnvironmentID(), CONSUMER));
    }
    else
    {
      logger.error("Already finalised. Cannot take any actions any longer. All operations to this class are ignored.");
    }
  }
	
	/**
	 * Consumers can call this method to joint the queue listener. Once a consumer has joined and the given subscriptions
	 * are valid then messages can be received from the queue listener and will be distributed to the appropriate consumer.
	 * 
	 * @param localConsumerQueue The queue where the given services shall be directed to.
	 * @param consumerServices A list of services applicable for this consumer. Events from this services will be sent to the given
	 *                         event consumer. 
	 * 
	 * @return TRUE: If the services are successfully joined the queue listener.
	 */
  	public boolean joinListener(LocalConsumerQueue localConsumerQueue, List<ServiceInfo> consumerServices)
	{
		if (!isFinalised)
		{
			logger.debug("Add services to LocalConsumer Queue " + localConsumerQueue.getLocalQueueID() + "...");
			if ((consumerServices != null) && (consumerServices.size() > 0))
			{
				// Assign the local queue to each service
				for (ServiceInfo serviceInfo : consumerServices)
				{
					SubscriptionKey key = new SubscriptionKey(serviceInfo.getZone().getId(), serviceInfo.getContext().getId(), serviceInfo.getServiceName(), serviceInfo.getServiceType().name());
					logger.debug("Add service with Service Key: " + key);

					// First check if there is already a listener on the appropriate queue for this service's subscription
					SIF3Subscription subscription = getSubscriptionFromList(key, getDbSubscriptions());
					if (subscription != null) // this is a valid subscription
					{
						logger.debug("Dealing with valid subscription (ACL approved and exists on Env-Provider and Local DB)");
						// Check if a queue listener is already in place
						QueueListenerInfo queueInfo = listeners.get(subscription.getQueueID());
						if (queueInfo == null) // we have none. Just create a new entry in the listener map
						{
							logger.debug("Add QueueListener configurationn to map of listererns.");
							queueInfo = new QueueListenerInfo(getQueue(subscription.getQueueID()));
							listeners.put(subscription.getQueueID(), queueInfo);
						}

						// Register service with this queue
						queueInfo.addLocalConsumerQueue(key, localConsumerQueue);
					}
				}
			}
			return true;
		}
		else
		{
			logger.error("Already finalised. Cannot take any actions any longer. All operations to this class are ignored.");
			return false;
		}
	}
	
	/** 
	 * This method must be called once all event consumers have joint the queue listener configuration. It will do what is required to finalise
	 * the configuration. The final configuration is then returned as a hashmap where the key=queueID (remote message queue ID) and value=listener
	 * configuration for that queue. After a call to this method the joinListener() can no longer be called (i.e. will return false).
	 *  
	 * @return See desc.
	 */
	public HashMap<String, QueueListenerInfo> finalise()
	{
		if (!isFinalised)
		{
			return listeners;
		}
		else
		{
			logger.error("Already finalised. Cannot take any actions any longer. All operations to this class are ignored.");
			return null;
		}
	}

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	
	private SIF3Subscription getSubscriptionFromList(SubscriptionKey key, List<SIF3Subscription> dbSubscriptions)
	{
		if (dbSubscriptions != null)
		{
			for (SIF3Subscription subscription : dbSubscriptions)
			{
				if (subscription.getZoneID().equals(key.getZoneID()) &&
	                subscription.getContextID().equals(key.getContextID()) &&
	                subscription.getServiceName().equals(key.getServiceName()) &&
	                subscription.getServiceType().equals(key.getServiceType()))
				{
					return subscription;
				}
			}
		}
		return null; // Indicates not found
	}

	private SIF3Queue getQueue(String queueID)
	{
		if (getDbQueues() != null)
		{
			for (SIF3Queue queue : getDbQueues())
			{
				if (queue.getQueueID().equals(queueID))
				{
					return queue;
				}
			}
		}
		return null;
	}
	
//	private AdvancedProperties getConsumerProperties()
//  {
//    return consumerProperties;
//  }
//
//	private void setConsumerProperties(AdvancedProperties consumerProperties)
//  {
//    this.consumerProperties = consumerProperties;
//  }

	private List<SIF3Queue> getDbQueues()
    {
    	return this.dbQueues;
    }

	private void setDbQueues(List<SIF3Queue> dbQueues)
    {
    	this.dbQueues = dbQueues;
    }

	private List<SIF3Subscription> getDbSubscriptions()
    {
    	return this.dbSubscriptions;
    }

	private void setDbSubscriptions(List<SIF3Subscription> dbSubscriptions)
    {
    	this.dbSubscriptions = dbSubscriptions;
    }
	
	 private String getEnvironmentID()
	  {
	    return environmentID;
	  }

	  private void setEnvironmentID(String environmentID)
	  {
	    this.environmentID = environmentID;
	  }

}
