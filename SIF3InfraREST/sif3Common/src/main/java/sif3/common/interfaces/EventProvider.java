/*
 * EventProvider.java
 * Created: 17/03/2014
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

import sif3.common.header.HeaderProperties;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;



/**
 * This class defines the events functionality for providers. If your provider must implement events then this interface must be implemented on
 * top of the "Provider" interface.<br/><br/>
 * 
 * @author Joerg Huber
 */
public interface EventProvider<L> extends DataModelLink
{
    /**
     * This method returns a SIFEventIterator. This needs to be implemented (i.e. not returning null) if the 
     * provider should publish events. It is up to the provider that implements this interface to decide the following:<br />
     * a) Do events need to be published (ie. if not then this method can be  implemented as 'return null')<br />
     * b) The frequency/method (i.e. event driven or polling) how events are published.<p>
     * 
     * @return SIFEventIterator An iterator for retrieving events so that they can be published.
     */
    public SIFEventIterator<L> getSIFEvents();
    
	/**
	 * This method is called just before the event is sent. This allows the provider to modify the actual object data before it 
	 * is sent. The final object to be sent must then be returned. Additional custom HTTP header fields can be provided that 
	 * are sent with the event. Note if custom HTTP header fields are provided they might be overridden if they have the same
	 * name as a SIF3 specified HTTP header.<br/>
	 * The intend of this method is that the provider can change the event based on the zone and context this is sent to. A SIF event for
	 * a particular SIF Object might be sent to more than one zone and context and for each of them the data might need to be altered before
	 * it is sent.<br/><br/>
	 * 
	 * Note: If the object is altered it should be recreated from scratch and not the given object should be changed. Since the object might
	 * need to be sent to more than one zone and alteration of the passed in object would then be visible to all the other zones.
	 * 
	 * @param sifEvent The event object for which the events will be sent.
	 * @param zone The zone the events will be sent to. This parameter value can be null which indicated the default zone.
	 * @param context The context for which the events apply to. This parameter value can be null which indicated the default context.
	 * @param customHTTPHeaders HTTP header values to be added to the publishing of an event.
	 * 
	 * @return See desc.
	 */
	public SIFEvent<L> modifyBeforePublishing(SIFEvent<L> sifEvent, SIFZone zone, SIFContext context, HeaderProperties customHTTPHeaders);

    
    /**
	 * This method is called if the sending of an event message fails. It is up to the implementor of this method to decide what to
	 * to do in this case.
	 * 
	 * @param sifEvent The event that could not be sent.
	 * @param zone The zone the events was for. This parameter value can be null which indicated the default zone.
	 * @param context The context for which the events applied to. This parameter value can be null which indicated the default context.
	 */
	public void onEventError(SIFEvent<L> sifEvent, SIFZone zone, SIFContext context);
	
	public void broadcastEvents();

}
