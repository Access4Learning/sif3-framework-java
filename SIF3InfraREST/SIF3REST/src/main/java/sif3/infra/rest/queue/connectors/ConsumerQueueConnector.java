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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.CommonConstants.QueuePollingType;
import sif3.common.CommonConstants.QueueStrategy;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.model.RemoteQueueInfo;
import sif3.common.persist.model.SIF3Queue;
import sif3.common.persist.model.SIF3Session;
import sif3.common.persist.service.SIF3QueueService;
import sif3.common.ws.Response;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.env.types.RemoteQueueConfig;
import sif3.infra.common.model.QueueCollectionType;
import sif3.infra.common.model.QueueType;
import sif3.infra.rest.client.QueueClient;
import sif3.infra.rest.queue.types.LocalQueueServiceInfo;
import sif3.infra.rest.queue.types.QueueInfo;

/**
 * This class is responsible to sync the remote queues on the broker with the local database based on the chosen queue strategy.
 * 
 * @author Joerg Huber
 *
 */
public class ConsumerQueueConnector
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    private static final AdapterType CONSUMER = AdapterType.CONSUMER;

    private SIF3QueueService queueService = new SIF3QueueService();
    private QueueClient queueClient = null;
    private ConsumerEnvironmentManager consumerEvnMgr = null;
    private SIF3Session sif3Session;
    
    //key=QueueID, value=QueueInfo
    private HashMap<String, QueueInfo> queues = new HashMap<String, QueueInfo>();
    
    /**
     * Constructor
     */
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
     * This framework will maintain a separate queues for events and delayed responses. It will sync both set of queues.
     * 
     * The result of this method might be a hash map of queues based on the queue strategy. This returned hash map is ought
     * to be used in the subscription connector and the startup of remote queue connectors. If anything fails during this
     * sync process then an appropriate exception is raised. It must be noted that if neither events nor delayed responses
     * are enabled then the returned has map of this method might be empty. IF an exception is thrown then there is a problem 
     * in configuring queues and subscriptions and therefore events or delayed responses cannot be retrieved. An error is 
     * logged with more information.
     * 
     * Note: This class only syncs queues with the remote and local environment. To sync subscriptions the 
     * ConsumerSubcriptionConnector must be called after a successful execution of this method.
     * 
     * @return A hash map where the key=QueueID, value=QueueInfo. This hash map can be empty
     * 
     * @throws ServiceInvokationException Failed to retrieve SIF3 Queue Information from Environment Provider and sync 
     *                                    with local setup. 
     */
    public HashMap<String, QueueInfo> syncQueuesAtStartup(List<LocalQueueServiceInfo> localQueueEventServices, List<LocalQueueServiceInfo> localQueueCRUDServices) throws ServiceInvokationException
    {
        // First we do the check if we need events queue and if so we sync them
        if (getConsumerEnvInfo().getEventsEnabled())
        {
        	List<QueueInfo> queueInfos = syncQueuesAtStartup(QueueInfo.QueueType.EVENT_QUEUE, localQueueEventServices);
        	for (QueueInfo queueInfo : queueInfos)
        	{
        		queues.put(queueInfo.getQueue().getQueueID(), queueInfo);
        	}
        }
        
        //Now we check if delayed responses are enabled and if so we sync them
        if (getConsumerEnvInfo().getDelayedEnabled() )
        {
        	List<QueueInfo> queueInfos = syncQueuesAtStartup(QueueInfo.QueueType.DELAYED_QUEUE, localQueueCRUDServices);
        	for (QueueInfo queueInfo : queueInfos)
        	{
        		queues.put(queueInfo.getQueue().getQueueID(), queueInfo);
        	}
        }

        return queues;
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

    private RemoteQueueConfig getQueueConfig(boolean isEventQueue)
    {
        return isEventQueue ? getConsumerEnvInfo().getEventConfig() : getConsumerEnvInfo().getDelayedConfig();
    }

    /*
     * NOTE:
     * This method is currently 'hard-wired" to ADAPTER for the Queue Strategy. 
     * 
     * It returns a list of queues and the assigned subscriptions for each according to the queue strategy.
     */
    private List<QueueInfo> syncQueuesAtStartup(QueueInfo.QueueType queueType,  List<LocalQueueServiceInfo> localQueueServices) throws ServiceInvokationException
    {
    	RemoteQueueConfig queueConfig = getQueueConfig(queueType == QueueInfo.QueueType.EVENT_QUEUE);
        checkQueueStrategy(queueConfig); 
        
        //TODO: JH - Need changing once other queue strategies are supported.
        try
        {
            // First check what we have a queue in DB
            SIF3Queue dbQueue = getDBQueue(sif3Session, queueConfig);
            
            // Check if we have the same queue on EnvProvider
            QueueType remoteQueue = getRemoteQueue(sif3Session, queueConfig);
            
            // to be in sync both queues are either null or not null
            if ((dbQueue == null) && (remoteQueue == null))
            {
                logger.debug("No queue exists in DB or remote location. Create them...");
                remoteQueue = createRemoteQueue(queueConfig);
                dbQueue = new SIF3Queue();
                copy(remoteQueue, dbQueue);
                dbQueue = queueService.saveQueue(dbQueue);
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
                dbQueue = queueService.saveQueue(dbQueue);
                logger.debug("DB Queue is sync'ed with remote location.");
            }
            else if ((dbQueue == null) && (remoteQueue != null))
            {
                // There is a queue on the environment provider but not in the DB. Create it in the DB.
                logger.debug("Queue exists on remote location but not in DB. Sync DB with remote location.");
                dbQueue = new SIF3Queue();
                copy(remoteQueue, dbQueue);
                dbQueue = queueService.saveQueue(dbQueue);
                logger.debug("DB Queue is sync'ed with remote location.");
            }
            else //((dbQueue != null) && (remoteQueue == null))
            {
                // There is a queue in the DB but not on the remote location. => Remove from DB and re-create
                // with remote location.
                logger.debug("Queue exists in DB but not on remote location! Remove in DB and create them again in DB and remote location.");
                queueService.removeQueue(dbQueue.getQueueID(), CONSUMER);
                remoteQueue = createRemoteQueue(queueConfig);
                dbQueue = new SIF3Queue();
                copy(remoteQueue, dbQueue);
                dbQueue = queueService.saveQueue(dbQueue);
                logger.debug("Queue has been created in DB and on remote location.");
            }
            
            ArrayList<QueueInfo> queueInfos = new ArrayList<QueueInfo>();
            QueueInfo queueInfo = new QueueInfo(dbQueue, queueType, queueConfig);
            
            // Set the localQueue and the remote queueID for each service
            for (LocalQueueServiceInfo localQueueService : localQueueServices)
            {
            	// Set remote Queue ID for delayed responses
            	if (queueInfo.getQueueType() == QueueInfo.QueueType.DELAYED_QUEUE)
            	{
            		localQueueService.getService().setRemoteQueueInfo(new RemoteQueueInfo(queueInfo.getQueue().getQueueID(), queueInfo.getQueue().getName()));
            	}
            	queueInfo.addListener(localQueueService);
            }
            
            queueInfos.add(queueInfo);
            
            // At this point the DB and remote provider have the same queue info. Now we must check if we need to update the 
            // remote location with some changes from the config file.
            return queueInfos;
        }
        catch (Exception ex)
        {
            logger.error("Failed to sync queue on remote location with local DB: "+ex.getLocalizedMessage(), ex);
            throw new ServiceInvokationException("Failed to sync queue on remote location with local DB: "+ex.getLocalizedMessage(), ex);
        }
    }
    
    private void checkQueueStrategy(RemoteQueueConfig queueConfig)
    {
        // Currently only ADAPTER_LEVEL queue strategy is supported. This means only one queue is available.
        if (!queueConfig.getQueueStrategy().name().equals(getSif3Session().getQueueStrategy()))
        {
            logger.error("Cannot change queue strategy from "+getSif3Session().getQueueStrategy()+" to "+queueConfig.getQueueStrategy()+". This is not yet supported. Only supported queue strategy is "+QueueStrategy.ADAPTER_LEVEL+".");
            queueConfig.setQueueStrategy(QueueStrategy.ADAPTER_LEVEL);
        } 
        else if (queueConfig.getQueueStrategy() != QueueStrategy.ADAPTER_LEVEL)
        {
            logger.error("Queue strategy "+queueConfig.getQueueStrategy()+"not yet supported. Dafault to "+QueueStrategy.ADAPTER_LEVEL+".");
            queueConfig.setQueueStrategy(QueueStrategy.ADAPTER_LEVEL);            
        }
    }
    
    /*
     * Since only ADAPTER_LEVEL is supported there should only be one queue for events and one queue for delayed responses.
     * The queue name is the differenciator of there is a queue for each message pattern.
     */
    private SIF3Queue getDBQueue(SIF3Session sif3Session, RemoteQueueConfig queueConfig) throws PersistenceException
    {
        List<SIF3Queue> queues = queueService.getQueuesForEnvironment(sif3Session.getEnvironmentID(), CONSUMER);
        
        // Since we only support only ADAPTER_LEVEL we can only have a max of 2 queues (1 Event, 1 Delayed Response)
        if ((queues != null) && ((queues.size() > 0) && (queues.size() <= 2)))
        {
            if (queues.get(0).getName().equals(queueConfig.getQueueName()))
            {
                return queues.get(0);
            }
            else if (queues.size() == 2)
            {
                // Is there a second queue and if so does name match?
                if (queues.get(1).getName().equals(queueConfig.getQueueName())) 
                {
                    return queues.get(1);
                }
                else //If the queue size is 2 and none matched then three is an issue
                {
                    logger.error("Found queues for environment but their names don't match the consumer properties name. Environment with inconsistency "+sif3Session.getEnvironmentID()+". ADAPTER_LEVEL queue strategy only supports one queue per message type. There is a data inconsistency. See your system admin for help.");
                    throw new PersistenceException("Invalid queues found for environment "+sif3Session.getEnvironmentID());
                }
            }
            else // Number of Queue is 1 and we have not found the queue, so it doesn't exist yet. This is ok.
            {
                return null;
            }
        }
        else if ((queues != null) && (queues.size() > 2)) // something is wrong!
        {
            logger.error("Found more than two queue for environment "+sif3Session.getEnvironmentID()+". ADAPTER_LEVEL queue strategy only supports one queue per message type. There is a data inconsistency. See your system admin for help.");
            throw new PersistenceException("Too many queues found for environment "+sif3Session.getEnvironmentID());
        }
        else // no queues found
        {
            return null;
        }               
    }
    
    private QueueType createRemoteQueue(RemoteQueueConfig queueConfig) throws ServiceInvokationException
    {        
        queueClient = new QueueClient(getConsumerEvnMgr());
        Response response = null;
        if (queueConfig.getQueueType() == QueuePollingType.LONG)
        {
            response = queueClient.createLongPollingQueue(queueConfig.getQueueName(), queueConfig.getNumMsgQueueReaders(), queueConfig.getLongPollTimeOut());
        }
        else
        {
            response = queueClient.createImmediateQueue(queueConfig.getQueueName(), queueConfig.getNumMsgQueueReaders());
        }
        if (response.hasError())
        {
          logger.error("An error has been createing the queue for "+queueConfig.getQueueName()+" form location: " + response.getResourceURI());
          logger.error("Error Returned:\n" + response.getError());
          throw new ServiceInvokationException(response.getError().toString());
        }
        
        return (QueueType)response.getDataObject();
    }

    /*
     * Since only ADAPTER_LEVEL is supported there should only be one or two queues!
     */
    private QueueType getRemoteQueue(SIF3Session sif3Session, RemoteQueueConfig queueConfig) throws ServiceInvokationException
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
            if ((remoteQueues != null) && (remoteQueues.getQueue() != null)) //queues found. Should only be one or two
            {
                if (remoteQueues.getQueue().size() == 0) // no queues returned
                {
                    return null;
                }
                
                // There are more than 0 queues. Check that it is between 1 and 2 otherwise we have too many queues.
                if ((remoteQueues.getQueue().size() >= 1) && (remoteQueues.getQueue().size() <=2)) 
                {
                    if (remoteQueues.getQueue().get(0).getName().equals(queueConfig.getQueueName()))
                    {
                        return remoteQueues.getQueue().get(0);
                    }
                    else if (remoteQueues.getQueue().size() == 2)
                    {
                        if (remoteQueues.getQueue().get(1).getName().equals(queueConfig.getQueueName()))
                        {
                            return remoteQueues.getQueue().get(1);
                        }
                        else // we have two queues but none matches the name => Not good!
                        {
                            logger.error("Found queues on broker for environment but their names don't match the consumer properties name. Environment with inconsistency "+sif3Session.getEnvironmentID()+". ADAPTER_LEVEL queue strategy only supports one queue per message type. There is a data inconsistency. See your system admin for help.");
                            throw new ServiceInvokationException("Invalid queues on broker found for environment "+sif3Session.getEnvironmentID());
                        }
                    }
                    else // No matching queues found.
                    {
                        return null;
                    }
                }
                else // we have more than 2 queues.
                {
                    logger.error("More than two queue on broker for environment "+sif3Session.getEnvironmentID()+" returned by environment provider. ADAPTER_LEVEL queue strategy only supports one queue per message type (event, delayed responses). There is a data inconsistency. See your system admin for help.");
                    throw new ServiceInvokationException("Too many queues on broker returned for environment "+sif3Session.getEnvironmentID());
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
