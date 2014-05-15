/*
 * LocalConsumerQueue.java
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

import org.apache.log4j.Logger;

import sif3.common.interfaces.EventConsumer;
import sif3.common.model.SIFEvent;
import sif3.infra.rest.queue.types.EventInfo;


/**
 * This class allows the subscriber to consume messages in a multi-threaded manner according to the
 * producer-consumer design pattern.<p>
 * 
 * @author Joerg Huber
 *
 */
public class LocalMessageConsumer implements Runnable
{
	protected final Logger logger = Logger.getLogger(getClass());

	private LocalConsumerQueue localQueue;
	private String consumerID;
	private EventConsumer<?> eventConsumer;
	
	/**
	 * This method initialises a Consumer to be able to receive and process events from the local event queue. The 'eventConsumer' parameter is 
	 * required by the local consumer as it invokes the event consumer's onEvent method for each event receive on the local event queue.
	 * 
	 * @param localQueue The local queue on which this consumer will be listening on.
	 * @param consumerID A name of the consumer. Mainly needed for nice debug and error reporting.
	 * @param eventConsumer An instance of consumer that will process the event.
	 */
	public LocalMessageConsumer(LocalConsumerQueue localQueue, String consumerID, EventConsumer<?> eventConsumer)
	{
		this.localQueue = localQueue;
		this.consumerID = consumerID;
		this.eventConsumer = eventConsumer;
	}
	
	/**
	 * Required for this class to run in its own thread.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	//@Override
    public void run()
    {
      consume();
    }
	
	/*-----------------*/
	/* Private methods */
	/*-----------------*/
	/*
	 * This method will run in an infinite loop and try to retrieve messages from the local queue. Once
	 * a message is retrieved it will determine if it will be sent to appropriate consumer thread for it to be processed.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void consume()
	{
		while (true)
		{
			EventInfo eventInfo = localQueue.blockingPull();
			if (eventInfo != null)
			{
				logger.debug(consumerID + " has receive an event from its local consumer queue ID: "  + eventInfo.getMessageQueueReaderID());
				try
				{
					Object eventPayload = eventConsumer.getUnmarshaller().unmarschal(eventInfo.getEventPayload(), eventConsumer.getMultiObjectClassInfo().getObjectType(), eventInfo.getMediaType());

					// Create actual event Object
					SIFEvent event = eventConsumer.createEventObject(eventPayload, eventInfo.getEventAction(), eventInfo.getUpdateType());
					event.setMetadata(eventInfo.getMetadata());

					// Send event to actual event consumer.
					eventConsumer.onEvent(event, eventInfo.getZone(), eventInfo.getContext(), eventInfo.getMessageQueueReaderID(), consumerID);
				}
				catch (Exception ex)
				{
					logger.error("Failed to create and send actual event to event consumer (" + eventConsumer.getClass().getSimpleName() + "): " + ex.getMessage());
					logger.error("Event Data Info for failed event:\n" + eventInfo);
				}
			}
			else
			{
				logger.error(consumerID + " has encountered a problem receiving an event from its local consumer queue.");
			}
		}
	}
	
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
}
