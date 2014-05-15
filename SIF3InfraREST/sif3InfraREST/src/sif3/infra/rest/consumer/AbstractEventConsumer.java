/*
 * AbstractEventConsumer.java
 * Created: 08/05/2014
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

package sif3.infra.rest.consumer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.interfaces.EventConsumer;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.model.ServiceRights;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.persist.model.SIF3Session;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.rest.queue.LocalConsumerQueue;
import sif3.infra.rest.queue.LocalMessageConsumer;

/**
 * This is the core class that a developer will use to implement for a consumer that shall subscribe to events. Each consumer for each object 
 * type that is interested in receiving events MUST extend this class. It forms the link between the high level consumer implementation and 
 * the low level infrastructure functions which this class abstracts.<br/>
 * It extends the AbstractConsumer class to give access to all CRUD operations required by a SIF Object Service consumer. Additionally this
 * class implement the EventConsumer<L> interface to link the consumer with the event processor.
 * 
 * @author Joerg Huber
 *
 */
public abstract class AbstractEventConsumer<L> extends AbstractConsumer implements EventConsumer<L>, Runnable
{
  private LocalConsumerQueue localConsumerQueue = null;
  private ExecutorService service = null;
  
  /**
   * This method is called when a consumer service has received an event. This class does implement the actual onEvent( method
   * from the event interface. It may do some additional work for house keeping purpose so the original onEvent() id processed
   * as part of this class but then this method is called so that the actual consumer can do its work as required.
   * 
   * @param sifEvent The event data that has been received and shall be processed by the consumer.
   * @param zone The zone from which the event has been received.
   * @param context The context for which the event is applicable for.
   * @param msgReadID The ID of the SIF queue reader. It is informative only and is only of use where there are multiple concurrent 
   *                  subscribers on a message queue.
   * @param consumerID The consumer ID that has been used to receive the event from the event queue. It is informative
   *                   only and is only of use where there are multiple event subscribers enabled.
   */
  public abstract void processEvent(SIFEvent<L> sifEvent, SIFZone zone, SIFContext context, String msgReadID, String consumerID);
  
	public AbstractEventConsumer()
	{
		super();
		// Only create local queue if event processing is enabled.
	    if (getConsumerEnvironment().getEventsEnabled() && getConsumerEnvironment().getEventsSupported())
	    {
	    	createLocalConsumerQueue();
	    }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sif3.common.interfaces.EventConsumer#onEvent(sif3.common.model.SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext, java.lang.String)
	 */
	@Override
	public void onEvent(SIFEvent<L> sifEvent, SIFZone zone, SIFContext context, String msgReadID, String consumerID)
	{
		// Right now all that is required is to call the abstract processEvent() method.
		processEvent(sifEvent, zone, context, msgReadID, consumerID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sif3.infra.rest.consumer.AbstractConsumer#finalise()
	 */
	@Override
	public void finalise()
	{
		logger.debug("Shut down Event Consumer Thread Pool for "+getConsumerName());
		if (service != null)
		{
			service.shutdown();
		}
		
		// Call user defined finalise of the subscriber.
		super.finalise();
	}
       
    /*----------------------------------------*/
    /* Implemented Method for Multi-threading */
    /*----------------------------------------*/

    /**
     * This method is all that is needed to run the subscriber in its own thread.
     */
    @Override
    public final void run()
    {
//      logger.debug("============================\n Start "+getConsumerName()+" worker thread.\n======================================");
    }
    
    /*------------------------------*/
    /* Some Getter & Setter methods */
    /*------------------------------*/
    
    public final LocalConsumerQueue getLocalConsumerQueue()
    {
      return localConsumerQueue;
    }

    public final void setLocalConsumerQueue(LocalConsumerQueue localConsumerQueue)
    {
      this.localConsumerQueue = localConsumerQueue;
    }
    
    /*----------------------------*/
    /*-- Other required methods --*/
    /*----------------------------*/
	public final int getNumOfConsumerThreads()
	{
		return getServiceProperties().getPropertyAsInt("consumer.local.workerThread", getClass().getSimpleName(), 1);
	}

	public List<ServiceInfo> getEventServices()
	{
		SIF3Session sif3Session = ConsumerEnvironmentManager.getInstance().getSIF3Session();
		return sif3Session.getServiceInfoForService(getMultiObjectClassInfo().getObjectName(), ServiceType.OBJECT, AccessRight.SUBSCRIBE, ServiceRights.AccessType.APPROVED);
	}

	/**
	 * Only creates the local consumer queue if it doesn't already exist. The queue (new or existing) is then returned.
	 * 
	 * @return See desc.
	 */
	public final LocalConsumerQueue createLocalConsumerQueue()
	{
		if (getLocalConsumerQueue() == null)
		{
			// Create local queue with the capacity indicated with the consumer config
//			logger.debug("Create Local Queue for "+getConsumerName()+" with capacity = "+getNumOfConsumerThreads());
			logger.debug("Create Local Queue for "+getConsumerName());
//			setLocalConsumerQueue(new LocalConsumerQueue(getNumOfConsumerThreads(), getClass().getSimpleName() + "LocalQueue", getClass().getSimpleName()));

			// Use the local queue as a trigger of threads rather than actual queueing of messages. Use 1 as the minimum
			setLocalConsumerQueue(new LocalConsumerQueue(1, getClass().getSimpleName() + "LocalQueue", getClass().getSimpleName()));
			startListenerThreads();
		}
		return getLocalConsumerQueue();
	}
	
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	
	/*
	 * Will initialise the threads and add them to the local consumer queue.
	 */
	private void startListenerThreads()
	{
		// Start up all consumers for this subscriber.
		int numThreads = getNumOfConsumerThreads();
		logger.debug("Start "+numThreads+" "+getConsumerName()+" threads.");
		logger.debug("Total number of threads before starting Local Queue for "+getConsumerName()+" "+Thread.activeCount());
		service = Executors.newFixedThreadPool(numThreads);
		for (int i = 0; i < numThreads; i++)
		{
			String consumerID = getConsumerName()+" "+(i+1);
			logger.debug("Start Consumer "+consumerID);
			LocalMessageConsumer consumer = new LocalMessageConsumer(getLocalConsumerQueue(), consumerID, this);
			service.execute(consumer);
		}
		logger.debug(numThreads+" "+getConsumerName()+" initilaised and started.");
		logger.debug("Total number of threads after starting Local Queue for "+getConsumerName()+" "+Thread.activeCount());
	}
}
