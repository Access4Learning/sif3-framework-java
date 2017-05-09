/*
 * ConsumerSubscriptionConnector.java
 * Created: 29/04/2014
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

package sif3.infra.rest.queue.connectors;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.model.ServiceInfo;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.model.SubscriptionKey;
import sif3.common.persist.model.SIF3Queue;
import sif3.common.persist.model.SIF3Session;
import sif3.common.persist.model.SIF3Subscription;
import sif3.common.persist.service.SIF3QueueService;
import sif3.common.persist.service.SIF3SubscriptionService;
import sif3.common.ws.Response;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.SubscriptionCollectionType;
import sif3.infra.common.model.SubscriptionType;
import sif3.infra.rest.client.SubscriptionClient;
import sif3.infra.rest.queue.types.QueueInfo;
import sif3.infra.rest.queue.types.QueueInfo.QueueType;

/**
 * This class manages subscriptions based on the Queue Strategy. It assumes that queues do already exist on the remote location
 * as well as the local DB. This means that the ConsumerQueueConnector has already been called and succeeded before this connector
 * is called. Then and only then it can be assumed that the queues are in the correct state and subscriptions can be sync'ed.
 * This class is responsible to sync the remote subscriptions on the broker with the local database.
 * 
 * @author Joerg Huber
 *
 */
public class ConsumerSubscriptionConnector
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final AdapterType CONSUMER = AdapterType.CONSUMER;

	private SIF3SubscriptionService subscriptionService = new SIF3SubscriptionService();
	private ConsumerEnvironmentManager consumerEvnMgr = null;
    private SIF3QueueService queueService = new SIF3QueueService();
	private SIF3Session sif3Session;
	
	public ConsumerSubscriptionConnector()
	{
		super();
		consumerEvnMgr = ConsumerEnvironmentManager.getInstance();
		sif3Session = consumerEvnMgr.getSIF3Session();
	}
	
	/**
	 * This method will configure subscriptions according to the queue strategy. It is intended to be used when a consumer is started.
	 * During standard runtime subscriptions are fixed until the time the service shuts down. At next start up things may have changed
	 * and a new Sync with the environment provider is required.
	 * 
	 * Since it is assumed that the queues are already sync'ed correctly it can also be assumed that the queue strategy is already
	 * in place and existing subscriptions match that strategy. If the queue strategy has changed, invalid subscriptions would have
	 * been removed.
	 * 
	 * If true is returned then all is fine and events can be received. If false is returned then there is a
	 * problem in configuring queues and subscriptions and therefore events cannot be retrieved.
	 * 
	 * @param queues A list of queues with their subscriptions according to the ACL.
	 */
	public boolean syncSubscriptionsAtStartup(HashMap<String, QueueInfo> queues)
	{
		try
		{
			// Subscriptions cannot be updated. This means we simply get all subscriptions from the remote location and apply them
			// to the local DB. Before we just delete DB subscriptions.
			logger.debug("Remove current subscriptions from local DB...");
			subscriptionService.removeAllSubscriptionsForEnvironment(getSif3Session().getEnvironmentID(), CONSUMER);

			logger.debug("Get subscriptions from remote location and apply them to the DB.");
			SubscriptionCollectionType remoteSubscriptions = getRemoteSubscriptions();
			if (remoteSubscriptions != null)
			{
				for (SubscriptionType subscription : remoteSubscriptions.getSubscription())
				{
					subscriptionService.saveSubscription(getSubscription(subscription));
				}
			}
			else
			{
				logger.debug("No subscriptions exist on remote location.");
			}
			
			// At this stage the remote and local (DB) subscription information are in sync. Now we need to ensure that they are updated on the
			// remote and local (DB) configuration according to the ACLs.
						
	        // Configure Subscriptions for Events
			if (getConsumerEnvInfo().getEventsEnabled())
			{
    	        if (!syncSubscriptions(queues))
    	        {
    	        	return false;
    	        }
			}
	        
	        // Delayed Responses do not work on subscriptions so there is nothing to do here for Delayed responses..
	       
			return true;
		}
		catch (Exception ex)
		{
			logger.error("Failed to sync subscriptions on remote location with local DB: "+ex.getLocalizedMessage(), ex);
			return false;
		}
	}	
		
	/**
	 * This methods will configure the subscriptions for a shut down of the service/consumer. At this point
	 * in time this means "do nothing". That behaviour might change in the future as more functionality to queues and
	 * subscriptions is added.
	 */
	public void syncSubscriptionsAtShutDown()
	{
		logger.debug("Sync & Shutdown Remote and Local SIF Subscriptions.");
		
		try
		{	
			// Check if Event subscriptions need to be removed.
			if (getConsumerEnvInfo().getEventsEnabled()) // only then we may have subscriptions
			{
				removeSubscriptions();
			}        
		}
		catch (Exception ex)
		{
			logger.error("Subscriptions for environment "+ getSif3Session().getEnvironmentID()+" may not fully be removed. Will be re-sync'ed at next startup.");
		}
	}
		
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
    private ConsumerEnvironmentManager getConsumerEvnMgr()
    {
        return consumerEvnMgr;
    }

    private SIF3Session getSif3Session()
    {
        return sif3Session;
    }
    
    private ConsumerEnvironment getConsumerEnvInfo()
    {
        return (ConsumerEnvironment)getConsumerEvnMgr().getEnvironmentInfo();
    }


    private void removeSubscriptions() throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
		logger.debug("Remove Subscriptions: "+getConsumerEnvInfo().getEventConfig().getRemoveSubscribersOnShutdown());
		if (getConsumerEnvInfo().getEventConfig().getRemoveSubscribersOnShutdown())
		{		
			// Let's get all queues. We need to remove subscriptions to a set of queues. Only EVENT type queues do have subscriptions
			List<SIF3Queue> dbQueues = queueService.getQueuesForEnvironment(getSif3Session().getEnvironmentID(), CONSUMER);
			for (SIF3Queue dbQueue : dbQueues)
			{
				logger.debug("Remove subscriptions in DB for Queue: "+dbQueue.getName());
				subscriptionService.removeAllSubscriptionsForQueue(dbQueue.getQueueID(), CONSUMER);
			
				logger.debug("Remove subscriptions for queue "+dbQueue.getName()+"("+dbQueue.getQueueID()+") on remote location...");
				removeRemoteSubscriptions(dbQueue);
			
				logger.debug("All subscriptions removed for queue"+dbQueue.getName()+".");
			}
		}
    }
    
    private boolean syncSubscriptions(HashMap<String, QueueInfo> queues) throws PersistenceException, ServiceInvokationException
    {
    	for (QueueInfo queueInfo : queues.values())
    	{
    		// Subscriptions are only required for EvVent queues
    		if (queueInfo.getQueueType() == QueueInfo.QueueType.EVENT_QUEUE)
    		{    		
        		// Check if the current consumer has subscriptions active that are no longer allowed according to the ACLs!
    			checkSubscriptionsWithACL();
    			
    			// Create required subscriptions for those that are missing.
    			createMissingSubscriptions(queues);
    		}
    	}
    	
    	return true;
    }  

    private SubscriptionCollectionType getRemoteSubscriptions() throws ServiceInvokationException
	{
		SubscriptionClient subscriptionClient = new SubscriptionClient(getConsumerEvnMgr());
	    Response response = subscriptionClient.getSubscriptions();
	
	    if (response.hasError())
	    {
	      logger.error("An error has been returned in retrieving the subscriptions from location: " + response.getResourceURI());
	      logger.error("Error Returned:\n" + response.getError());
	      throw new ServiceInvokationException(response.getError().toString());
	    }
	    else
	    {
	    	SubscriptionCollectionType remoteSubscriptions = (SubscriptionCollectionType)response.getDataObject();
	    	if ((remoteSubscriptions != null) && (remoteSubscriptions.getSubscription() != null) && remoteSubscriptions.getSubscription().size()>0) //subscriptions found. Should only be one
	    	{
	    		return remoteSubscriptions;
	    	}	    		
	    	else // no subscriptions returned
	    	{
	    		return null;
	    	}
		}
	}
	
	private void removeRemoteSubscriptions(SIF3Queue dbQueue) throws ServiceInvokationException
	{
		SubscriptionCollectionType remoteSubscriptions = getRemoteSubscriptions();
		if (remoteSubscriptions != null)
		{
			SubscriptionClient subscriptionClient = new SubscriptionClient(getConsumerEvnMgr());
			for (SubscriptionType subscription : remoteSubscriptions.getSubscription())
			{
				if (dbQueue.getQueueID().equals(subscription.getQueueId()))
				{
					subscriptionClient.unsubscribe(subscription.getId());
				}
			}
		}
	}
	
	private SubscriptionType createRemoteSubscription(SubscriptionKey subscriptionKey, QueueInfo queueInfo) throws ServiceInvokationException
	{
		SubscriptionClient subscriptionClient = new SubscriptionClient(getConsumerEvnMgr());
		SubscriptionType subscriptionInfo = new ObjectFactory().createSubscriptionType();
		subscriptionInfo.setContextId(subscriptionKey.getContextID());
		subscriptionInfo.setZoneId(subscriptionKey.getZoneID());
		subscriptionInfo.setServiceName(subscriptionKey.getServiceName());
		subscriptionInfo.setServiceType(subscriptionKey.getServiceType());
		subscriptionInfo.setQueueId(queueInfo.getQueue().getQueueID());		
		Response response = subscriptionClient.subscribe(subscriptionInfo);
		
	    if (response.hasError())
	    {
	      logger.error("An error has been returned in creatingsubscriptions from location: " + response.getResourceURI());
	      logger.error("Error Returned:\n" + response.getError());
	      throw new ServiceInvokationException(response.getError().toString());
	    }
	    else
	    {
	    	return (SubscriptionType)response.getDataObject();
		}
	}

	private SIF3Subscription getSubscription(SubscriptionType subscription)
	{
		if (subscription != null)
		{
			SIF3Subscription dbSubscription = new SIF3Subscription();
			dbSubscription.setAdapterType(CONSUMER.name());
			dbSubscription.setContextID(subscription.getContextId());
			dbSubscription.setLastAccessed(new Date());
			dbSubscription.setQueueID(subscription.getQueueId());
			dbSubscription.setServiceName(subscription.getServiceName());
			dbSubscription.setServiceType(subscription.getServiceType());
			dbSubscription.setSubscriptionID(subscription.getId());
			dbSubscription.setZoneID(subscription.getZoneId());
			
			return dbSubscription;
		}
		else
		{
			return null;
		}
	}
	
	/*
	 * This method will check if the current subscriptions are still allowed according to the consumers ACL. Note this check is
	 * only performed if the Check ACL flag is turned on. At this point it is assumed that the DB is fully synced with the
	 * remote provider.
	 */
    private void checkSubscriptionsWithACL()
	{
		try
		{
			if (getConsumerEnvInfo().getCheckACL())
			{
				// We need to get all subscriptions for this environment and compare it against all services for this session.
				List<SIF3Subscription> dbSubscriptions = subscriptionService.getSubscriptionsForEnvironment(getSif3Session().getEnvironmentID(), CONSUMER);
				if ((dbSubscriptions != null) && (dbSubscriptions.size() > 0))
				{
					SubscriptionClient subscriptionClient = new SubscriptionClient(getConsumerEvnMgr());
					
					List<ServiceInfo> services = getSif3Session().getServices();
					
					// We need to check all subscriptions we are holding in the DB and Remote queues if they are still valid
					// According to the ACL. If they are not we need to remove them.
					for (SIF3Subscription subscription : dbSubscriptions)
					{
					    if (!hasAccess(subscription, services))
					    {
                            logger.debug("The following active subscription has no longer SUBSCRIBE=APPROVED rights according to the ACL. It will be removed:\n"+subscription);
                            subscriptionClient.unsubscribe(subscription.getSubscriptionID());
                            subscriptionService.removeSubscription(subscription.getSubscriptionID(), CONSUMER);					        
					    }
					}					
				}
			}
		}
		catch (Exception ex)
		{
			// There is not much we can do. Just ignore it for the time being but don't stop the client from working
			logger.error("Could not update subscriptions according to environments ACL. Consumer will still continue but issue should be investigated further: "+ex.getLocalizedMessage(), ex);
		}
	}

    private boolean hasAccess(SIF3Subscription subscription, List<ServiceInfo> services)
    {
        for (ServiceInfo service : services)
        {
            if (subscription.getZoneID().equals(service.getZone().getId()) &&
                subscription.getContextID().equals(service.getContext().getId()) &&
                subscription.getServiceName().equals(service.getServiceName()) &&
                subscription.getServiceType().equals(service.getServiceType().name()))
            {
                return service.getRights().hasRight(AccessRight.SUBSCRIBE, AccessType.APPROVED);
            }
        }
        
        // There is no such service => No right to subscribe
        return false;
    }
    
	private SIF3Subscription getSubscriptionFromList(SubscriptionKey subscriptionKey, List<SIF3Subscription> dbSubscriptions)
	{
		for (SIF3Subscription subscription : dbSubscriptions)
		{
			if (subscription.getZoneID().equals(subscriptionKey.getZoneID()) &&
                subscription.getContextID().equals(subscriptionKey.getContextID()) &&
                subscription.getServiceName().equals(subscriptionKey.getServiceName()) &&
                subscription.getServiceType().equals(subscriptionKey.getServiceType()))
			{
				return subscription;
			}
		}
		return null; // Indicates not found
	}
	
	/*
	 * Create required subscriptions for those that are missing. Only create subscriptions for objects the consumer is
	 * interested in. If there are other subscriptions still in place then they must remain since the consumer could have
	 * been started temporarily with a different assembly. Also check rights if subscription is allowed!
	 */
	private void createMissingSubscriptions(HashMap<String, QueueInfo> queues) throws PersistenceException, ServiceInvokationException
	{
		if (queues != null)
		{
			for (QueueInfo queueInfo : queues.values())
			{
				if (queueInfo.getQueueType() == QueueType.EVENT_QUEUE)
				{
					if (queueInfo.getListeners().keySet() != null)
					{
            			List<SIF3Subscription> dbSubscriptions = subscriptionService.getSubscriptionsForQueue(queueInfo.getQueue().getQueueID(), CONSUMER);
            			for (SubscriptionKey subcriptionKey : queueInfo.getListeners().keySet())
            			{
            				// Because the subscription key map is based on the ACL it also means that the subscription must be allowed!
        					SIF3Subscription dbSubscription = getSubscriptionFromList(subcriptionKey, dbSubscriptions);
        					if (dbSubscription == null) // does not exist => create subscription
        					{
        						logger.debug("The subscription with the following details doesn't exist and will be created:\n"+subcriptionKey);
        						createSubscription(subcriptionKey, queueInfo);
        					}
            			}
					}
				}
			}

		}
	}
	
	private void createSubscription(SubscriptionKey subscriptionKey, QueueInfo queueInfo) throws ServiceInvokationException, PersistenceException
	{
		SubscriptionType remoteSubscription = createRemoteSubscription(subscriptionKey, queueInfo);
		if (remoteSubscription != null)
		{
			subscriptionService.saveSubscription(getSubscription(remoteSubscription));
		}
		else
		{
			logger.error("Could not create subscription on remote location. See previous error log entries.");
		}
	}
}
