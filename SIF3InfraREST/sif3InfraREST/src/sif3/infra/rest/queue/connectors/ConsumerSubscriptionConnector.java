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
import java.util.List;

import org.apache.log4j.Logger;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.CommonConstants.QueueStrategy;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.persist.model.SIF3Queue;
import sif3.common.persist.model.SIF3Session;
import sif3.common.persist.model.SIF3Subscription;
import sif3.common.persist.service.SIF3QueueService;
import sif3.common.persist.service.SIF3SubscriptionService;
import sif3.common.ws.Response;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.SubscriptionCollectionType;
import sif3.infra.common.model.SubscriptionType;
import sif3.infra.rest.client.SubscriptionClient;

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
	protected final Logger logger = Logger.getLogger(getClass());
	
	private static final AdapterType CONSUMER = AdapterType.CONSUMER;

	private SIF3SubscriptionService subscriptionService = new SIF3SubscriptionService();
	private SIF3QueueService queueService = new SIF3QueueService();
	private ConsumerEnvironment consumerEnvInfo;
	private SIF3Session sif3Session;
	
	public ConsumerSubscriptionConnector(ConsumerEnvironment consumerEnvInfo, SIF3Session sif3Session)
	{
		super();
		setConsumerEnvInfo(consumerEnvInfo);
		setSif3Session(sif3Session);
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
	 * @param consumerSubscriptions A list of subscriptions the given consumer is interested in.
	 */
	public boolean syncSubscriptionsAtStartup(List<ServiceInfo> consumerSubscriptions)
	{
		try
		{
			// Subscriptions cannot be updated. This means we simply get all subscriptions from the remote location and apply them
			// to the local DB. Before we just delete DB subscriptions.
			logger.debug("Remove current subscriptions from local DB...");
			subscriptionService.removeAllSubscriptionsForEnvironment(getSif3Session().getEnvironmentID(), CONSUMER);

			logger.debug("Get subscriptions from remote location and apply them to the DB.");
			SubscriptionCollectionType remoteSubscriptions = getRemoteSubscriptions(getSif3Session());
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
			
			// Check if the current consumer has subscriptions active that are no longer allowed according to the ACLs!
			checkSubscriptionsWithACL();
			
			// Create required subscriptions for those that are missing.
			createMissingSubscriptions(consumerSubscriptions);
			
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
		logger.debug("Remove Subscriptions: "+getConsumerEnvInfo().getRemoveSubscribersOnShutdown());
		if (getConsumerEnvInfo().getRemoveSubscribersOnShutdown())
		{
			try
			{
				logger.debug("Remove subscriptions in DB...");
				subscriptionService.removeAllSubscriptionsForEnvironment(getSif3Session().getEnvironmentID(), CONSUMER);
				
				logger.debug("Remove subscriptions on remote location...");
				removeRemoteSubscriptions(getSif3Session());
				
				logger.debug("All subscriptions removed.");
			}
			catch (Exception ex)
			{
				logger.error("Subscriptions for environment "+ getSif3Session().getEnvironmentID()+" may not fully be removed. Will be re-sync'ed at next startup.");
			}
		}
	}
	
	public ConsumerEnvironment getConsumerEnvInfo()
    {
    	return this.consumerEnvInfo;
    }

	public void setConsumerEnvInfo(ConsumerEnvironment consumerEnvInfo)
    {
    	this.consumerEnvInfo = consumerEnvInfo;
    }

	public SIF3Session getSif3Session()
    {
    	return this.sif3Session;
    }

	public void setSif3Session(SIF3Session sif3Session)
    {
    	this.sif3Session = sif3Session;
    }
	
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private SubscriptionCollectionType getRemoteSubscriptions(SIF3Session sif3Session) throws ServiceInvokationException
	{
		SubscriptionClient subscriptionClient = new SubscriptionClient(getConsumerEnvInfo(), sif3Session);
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
	
	private void removeRemoteSubscriptions(SIF3Session sif3Session) throws ServiceInvokationException
	{
		SubscriptionCollectionType remoteSubscriptions = getRemoteSubscriptions(sif3Session);
		if (remoteSubscriptions != null)
		{
			SubscriptionClient subscriptionClient = new SubscriptionClient(getConsumerEnvInfo(), getSif3Session());
			for (SubscriptionType subscription : remoteSubscriptions.getSubscription())
			{
				subscriptionClient.unsubscribe(subscription.getId());
			}
		}
	}
	
	private SubscriptionType createRemoteSubscription(SIF3Session sif3Session, ServiceInfo service, List<SIF3Queue> dbQueues) throws ServiceInvokationException
	{
		SubscriptionClient subscriptionClient = new SubscriptionClient(getConsumerEnvInfo(), sif3Session);
		SubscriptionType subscriptionInfo = new ObjectFactory().createSubscriptionType();
		subscriptionInfo.setContextId(service.getContext().getId());
		subscriptionInfo.setZoneId(service.getZone().getId());
		subscriptionInfo.setServiceName(service.getServiceName());
		subscriptionInfo.setServiceType(service.getServiceType().name());
		subscriptionInfo.setQueueId(getQueueID(service, dbQueues));		
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
	public void checkSubscriptionsWithACL()
	{
		try
		{
			if (getConsumerEnvInfo().getCheckACL())
			{
				List<SIF3Subscription> dbSubscriptions = subscriptionService.getSubscriptionsForEnvironment(getSif3Session().getEnvironmentID(), CONSUMER);
				if ((dbSubscriptions != null) && (dbSubscriptions.size() > 0))
				{
					SubscriptionClient subscriptionClient = new SubscriptionClient(getConsumerEnvInfo(), getSif3Session());
					for (ServiceInfo service : getSif3Session().getServices())
					{
						if (!service.getRights().hasRight(AccessRight.SUBSCRIBE, AccessType.APPROVED))
						{
							SIF3Subscription subscription = getSubscriptionFromList(service.getZone(), service.getContext(), service.getServiceName(), service.getServiceType(), dbSubscriptions);
							if (subscription != null) // we have a subscription for a service where we have no access
							{
								logger.debug("The following active subscription has no longer SUBSCRIBE=APPROVED rights according to the ACL. It will be removed:\n"+subscription);
								subscriptionClient.unsubscribe(subscription.getSubscriptionID());
								subscriptionService.removeSubscription(subscription.getSubscriptionID(), CONSUMER);
							}
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

	private SIF3Subscription getSubscriptionFromList(SIFZone zone, SIFContext context, String serviceName, ServiceType serviceType, List<SIF3Subscription> dbSubscriptions)
	{
		for (SIF3Subscription subscription : dbSubscriptions)
		{
			if (subscription.getZoneID().equals(zone.getId()) &&
                subscription.getContextID().equals(context.getId()) &&
                subscription.getServiceName().equals(serviceName) &&
                subscription.getServiceType().equals(serviceType.name()))
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
	private void createMissingSubscriptions(List<ServiceInfo> consumerSubscriptions) throws PersistenceException, ServiceInvokationException
	{
		if ((consumerSubscriptions != null) && (consumerSubscriptions.size() > 0))
		{
			List<SIF3Subscription> dbSubscriptions = subscriptionService.getSubscriptionsForEnvironment(getSif3Session().getEnvironmentID(), CONSUMER);
			List<SIF3Queue> dbQueues = queueService.getQueuesForEnvironment(getSif3Session().getEnvironmentID(), CONSUMER);
			for (ServiceInfo service : consumerSubscriptions)
			{
				// First check if we have SUBSCRIBE permission
				boolean subscribeAllowed = true;
				if (getConsumerEnvInfo().getCheckACL())
				{
					ServiceInfo envService = getSif3Session().getServiceInfoForService(service.getZone(), service.getContext(), service.getServiceName(), service.getServiceType());
					if (envService != null)
					{
						subscribeAllowed = envService.getRights().hasRight(AccessRight.SUBSCRIBE, AccessType.APPROVED);
					}
					else // no such service => No access
					{
						subscribeAllowed = false;
					}
				}
				if (subscribeAllowed)	
				{
					SIF3Subscription dbSubscription = getSubscriptionFromList(service.getZone(), service.getContext(), service.getServiceName(), service.getServiceType(), dbSubscriptions);
					if (dbSubscription == null) // does not exist => create subscription
					{
						logger.debug("The subscription with the following details doesn't exist and will be created:\n"+service);
						createSubscription(service, dbQueues);
					}
				}
				else
				{
					logger.debug("The subscription with the following details has no SUBSCRIBE=APPROVED rights according to the ACL. It will not be created:\n"+service);
				}
			}
		}
	}
	
	private void createSubscription(ServiceInfo service, List<SIF3Queue> dbQueues) throws ServiceInvokationException, PersistenceException
	{
		SubscriptionType remoteSubscription = createRemoteSubscription(getSif3Session(), service, dbQueues);
		if (remoteSubscription != null)
		{
			subscriptionService.saveSubscription(getSubscription(remoteSubscription));
		}
		else
		{
			logger.error("Could not create subscription on remote location. See previous error log entries.");
		}
	}
	
	private String getQueueID(ServiceInfo service, List<SIF3Queue> dbQueues)
	{
		switch (QueueStrategy.valueOf(getSif3Session().getQueueStrategy()))
		{
			case ADAPTER_LEVEL: // there is only one queue
				return dbQueues.get(0).getQueueID();
			case OBJECT_LEVEL: // all criteria must match
				for (SIF3Queue queue : dbQueues)
				{
					if (service.getZone().getId().equals(queue.getZoneID()) &&
					    service.getContext().getId().equals(queue.getContextID()) &&
					    service.getServiceName().equals(queue.getServiceName()) &&
					    service.getServiceType().name().equals(queue.getServiceType()))
					{
						return queue.getQueueID();
					}
				}
				break;
			case ZONE_LEVEL: // only one queue per zone
				for (SIF3Queue queue : dbQueues)
				{
					if (service.getZone().getId().equals(queue.getZoneID()))
					{
						return queue.getQueueID();
					}
				}
				break;
		}
		return null;
	}
}
