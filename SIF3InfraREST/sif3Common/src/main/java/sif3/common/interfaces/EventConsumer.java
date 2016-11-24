/*
 * EventConsumer.java
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

package sif3.common.interfaces;

import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.model.EventMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;

/**
 * This class defines the events functionality for consumers. If your consumer must implement events then this interface 
 * must be implemented on top of the "Consumer" interface.<br/><br/>

 * @author Joerg Huber
 *
 */
public interface EventConsumer<L> extends DataModelLink
{
	/**
	 * This method is called every time an event is available for the given consumer.
	 * 
	 * @param sifEvent The event data that has been received and shall be processed by the consumer.
	 * @param zone The zone from which the event has been received.
	 * @param context The context for which the event is applicable for.
	 * @param metadata Metadata provided by the originator of the event.
	 * @param msgReadID The ID of the SIF queue reader. It is informative only and is only of use where there are multiple concurrent 
	 *                  subscribers on a message queue.
	 * @param consumerID The consumer ID that has been used to receive the event from the event queue. It is informative
	 *                   only and is only of use where there are multiple event subscribers enabled.
	 */
	public void onEvent(SIFEvent<L> sifEvent, SIFZone zone, SIFContext context, EventMetadata metadata, String msgReadID, String consumerID);
	
	/**
	 * This method creates an event object with the given sifObject data. This method is required by the event consumers
	 * so that events from a queue which are represented as a String can be converted to a full event object. The main 
	 * reason for this method is the fact that Java versions below 7 cannot instantiate generic objects. This method gets
	 * around that issue and ensures that this framework will also work with Java 5 & 6. If the sifObjectList is null then 
	 * a null object must be returned. If the sifObjectList is not of the type <L> then an error is logged and null is
	 * returned.
	 * 
	 * 
	 * @param sifObjectList A list style object that encapsulates the list of objects to pack into an event.
	 * @param eventAction The event action for this event. Ideally this is not null.
	 * @param updateType In case of an UPDATE this updateType indicates if it is a FULL or PARTIAL update.
	 * 
	 * @return A SIFEvent object that holds the sifObjectList as its data.
	 */
	public SIFEvent<L> createEventObject(Object sifObjectList, EventAction eventAction, UpdateType updateType);
}
