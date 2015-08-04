/*
 * ConsumerQueueConnector.java
 * Created: 22/04/2014
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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.CommonConstants.QueuePollingType;
import sif3.common.CommonConstants.QueueStrategy;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.persist.model.SIF3Queue;
import sif3.common.persist.model.SIF3Session;
import sif3.common.persist.service.SIF3QueueService;
import sif3.common.ws.Response;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.model.QueueCollectionType;
import sif3.infra.common.model.QueueType;
import sif3.infra.rest.client.QueueClient;

/**
 * This class is responsible to sync the remote queues on the broker with the local database based on the chosen queue strategy.
 * 
 * @author Joerg Huber
 *
 */
public class ConsumerQueueConnector
{
	protected final Logger logger = Logger.getLogger(getClass());
	
	private static final AdapterType CONSUMER = AdapterType.CONSUMER;

	private SIF3QueueService queueService = new SIF3QueueService();
	private QueueClient queueClient = null;
    private ConsumerEnvironmentManager consumerEvnMgr = null;
	private SIF3Session sif3Session;
	
	public ConsumerQueueConnector()
	{
		super();
        consumerEvnMgr = ConsumerEnvironmentManager.getInstance();
        sif3Session = consumerEvnMgr.getSIF3Session();
	}
	
	/**
	 * This method will configure queues based on the queue strategy. It is intended to be used when a consumer is started.
	 * During standard runtime queues are fixed until the time the service shuts down. At next start up things may have 
	 * changed and a new Sync with the environment provider is required.
	 * 
	 * If true is returned then all is fine and subscriptions can be sync'ed or be maintained and events can be received once the
	 * subscriptions are fine. If false is returned then there is a problem in configuring queues and subscriptions and therefore 
	 * events cannot be retrieved.
	 * 
	 * Note: This class only syncs queues with the remote and local environment. To sync subscriptions the 
	 * ConsumerSubcriptionConnector must be called after a successful execution of this method.
	 */
	public boolean syncQueuesAtStartup()
	{
		checkQueueStrategy();
		try
		{
			// First check what we have a queue in DB
			SIF3Queue dbQueue = getDBQueue(sif3Session);
			
			// Check if we have the same queue on EnvProvider
			QueueType remoteQueue = getRemoteQueue(sif3Session);
			
			// to be in sync both queues are either null or not null
			if ((dbQueue == null) && (remoteQueue == null))
			{
				logger.debug("No queue exists in DB or remote location. Create them...");
				remoteQueue = createRemoteQueue();
				dbQueue = new SIF3Queue();
				copy(remoteQueue, dbQueue);
				queueService.saveQueue(dbQueue);
				logger.debug("Queue has been created in DB and on remote location.");
			} 
			else if  ((dbQueue != null) && (remoteQueue != null))
			{
				logger.debug("Queue exists in DB and on remote location. Sync DB with remote location.");
				// update queue in DB store if they both have the same QueueID
				if (dbQueue.getQueueID().equals(remoteQueue.getClass()))
				{
					copy(remoteQueue, dbQueue);
				}
				else // oops. Not the same queue! We must delete the queue in the DB and re-create it in the DB.
				{
					queueService.removeQueue(dbQueue.getQueueID(), CONSUMER);
					dbQueue = new SIF3Queue();
					copy(remoteQueue, dbQueue);
				}
				queueService.saveQueue(dbQueue);
				logger.debug("DB Queue is sync'ed with remote location.");
			}
			else if ((dbQueue == null) && (remoteQueue != null))
			{
				// There is a queue on the environment provider but not in the DB. Create it in the DB.
				logger.debug("Queue exists on remote location but not in DB. Sync DB with remote location.");
				dbQueue = new SIF3Queue();
				copy(remoteQueue, dbQueue);
				queueService.saveQueue(dbQueue);
				logger.debug("DB Queue is sync'ed with remote location.");
			}
			else //((dbQueue != null) && (remoteQueue == null))
			{
				// There is a queue in the DB but not on the remote location. => Remove from DB and re-create
				// with remote location.
				logger.debug("Queue exists in DB but not on remote location! Remove in DB and create them again in DB and remote location.");
				queueService.removeQueue(dbQueue.getQueueID(), CONSUMER);
				remoteQueue = createRemoteQueue();
				dbQueue = new SIF3Queue();
				copy(remoteQueue, dbQueue);
				queueService.saveQueue(dbQueue);
				logger.debug("Queue has been created in DB and on remote location.");
			}
			
			// At this point the DB and remote provider have the same queue info. Now we must check if we need to update the 
			// remote location with some changes from the config file.
			// TODO: JH - Update remote queue info with config data.
			return true;
		}
		catch (Exception ex)
		{
			logger.error("Failed to sync queue on remote location with local DB: "+ex.getLocalizedMessage(), ex);
			return false;
		}
	}
	
	/**
	 * This methods will configure the queues for a shut down of the service/consumer. At this point in time this 
	 * means "do nothing". That behaviour might change in the future as more functionality to queues and subscriptions is added.
	 */
	public void syncQueuesAtShutDown()
	{
		logger.debug("Sync & Shutdown Remote and Local SIF Queues.");
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

    private void checkQueueStrategy()
	{
		// Currently only ADAPTER_LEVEL queue strategy is supported. This means only one queue is available.
		if (!getConsumerEnvInfo().getQueueStrategy().name().equals(getSif3Session().getQueueStrategy()))
		{
			logger.error("Cannot change queue strategy from "+getSif3Session().getQueueStrategy()+" to "+getConsumerEnvInfo().getQueueStrategy()+". This is not yet supported. Only supported queue strategy is "+QueueStrategy.ADAPTER_LEVEL+".");
			getConsumerEnvInfo().setQueueStrategy(QueueStrategy.ADAPTER_LEVEL);
		} 
		else if (getConsumerEnvInfo().getQueueStrategy() != QueueStrategy.ADAPTER_LEVEL)
		{
			logger.error("Queue strategy "+getConsumerEnvInfo().getQueueStrategy()+"not yet supported. Dafault to "+QueueStrategy.ADAPTER_LEVEL+".");
			getConsumerEnvInfo().setQueueStrategy(QueueStrategy.ADAPTER_LEVEL);			
		}
	}
	
	/*
	 * Since only ADAPTER_LEVEL is supported there should only be one queue!
	 */
	private SIF3Queue getDBQueue(SIF3Session sif3Session) throws PersistenceException
	{
		List<SIF3Queue> queues = queueService.getQueuesForEnvironment(sif3Session.getEnvironmentID(), CONSUMER);
		if ((queues != null) && (queues.size() == 1))
		{
			return queues.get(0);
		}
		else if ((queues != null) && (queues.size() > 1)) // something is wrong!
		{
			logger.error("Found more than one queue for environment "+sif3Session.getEnvironmentID()+". ADAPTER_LEVEL queue strategy only supports one queue. There is a data inconsistency. See your system admin for help.");
			throw new PersistenceException("Multiple queues found for environment "+sif3Session.getEnvironmentID());
		}
		else // no queues found
		{
			return null;
		}
	}
	
	private QueueType createRemoteQueue() throws ServiceInvokationException
	{		
		queueClient = new QueueClient(getConsumerEvnMgr());
		Response response = null;
		if (getConsumerEnvInfo().getQueueType() == QueuePollingType.LONG)
		{
			response = queueClient.createLongPollingQueue(getConsumerEnvInfo().getQueueName(), getConsumerEnvInfo().getNumMsgQueueReaders(), getConsumerEnvInfo().getLongPollTimeOut());
		}
		else
		{
			response = queueClient.createImmediateQueue(getConsumerEnvInfo().getQueueName(), getConsumerEnvInfo().getNumMsgQueueReaders());
		}
	    if (response.hasError())
	    {
	      logger.error("An error has been createing the queue for "+getConsumerEnvInfo().getQueueName()+" form location: " + response.getResourceURI());
	      logger.error("Error Returned:\n" + response.getError());
	      throw new ServiceInvokationException(response.getError().toString());
	    }
	    
	    return (QueueType)response.getDataObject();
	}

	/*
	 * Since only ADAPTER_LEVEL is supported there should only be one queue!
	 */
	private QueueType getRemoteQueue(SIF3Session sif3Session) throws ServiceInvokationException
	{
		queueClient = new QueueClient(getConsumerEvnMgr());
	    Response response = queueClient.getQueues();

	    if (response.hasError())
	    {
	      logger.error("An error has been returned in retrieving the queues from location: " + response.getResourceURI());
	      logger.error("Error Returned:\n" + response.getError());
	      throw new ServiceInvokationException(response.getError().toString());
	    }
	    else
	    {
	    	QueueCollectionType remoteQueues = (QueueCollectionType)response.getDataObject();
	    	if ((remoteQueues != null) && (remoteQueues.getQueue() != null)) //queues found. Should only be one
	    	{
	    		if (remoteQueues.getQueue().size() == 1)
	    		{
	    			return remoteQueues.getQueue().get(0);
	    		} 
	    		else if (remoteQueues.getQueue().size() > 1)
	    		{
	    			logger.error("More than one queue for environment "+sif3Session.getEnvironmentID()+" returned by environment provider. ADAPTER_LEVEL queue strategy only supports one queue. There is a data inconsistency. See your system admin for help.");
	    			throw new ServiceInvokationException("Multiple queues returned by environment provider for environment "+sif3Session.getEnvironmentID());	    			
	    		}
	    		else // no queues returned
	    		{
	    			return null;
	    		}		
	    	}
	    	else // no queues returned
	    	{
	    		return null;
	    	}
	    }
	}
	
	private void copy(QueueType source, SIF3Queue target)
	{
		target.setAdapterType(CONSUMER.name());
		target.setCreated(calendarToDate(source.getCreated()));
		target.setEnvironmentID(source.getOwnerId());
		target.setLastAccessed(calendarToDate(source.getLastAccessed()));
		target.setLastModified(calendarToDate(source.getLastModified()));
		target.setLongPollingTimeout(source.getIdleTimeout());
		target.setMaxConsumers((source.getMaxConcurrentConnections() != null) ? source.getMaxConcurrentConnections().intValue() : 1);
		target.setMessageURI(source.getQueueUri());
		target.setName(source.getName());
		target.setNumMessages(source.getMessageCount());
		target.setQueueID(source.getId());
		target.setQueueType(source.getPolling());
		target.setWaitTime(source.getMinWaitTime());
		target.setWakeupURI(source.getOwnerUri());
	}
	
	private Date calendarToDate(Calendar date)
	{
		if (date == null)
		{
			return null;
		}
		else
		{
			return date.getTime();
		}
	}
}
