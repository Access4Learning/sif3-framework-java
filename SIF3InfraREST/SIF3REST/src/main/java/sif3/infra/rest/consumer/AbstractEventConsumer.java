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

import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.interfaces.EventConsumer;
import sif3.common.model.ACL.AccessRight;
import sif3.common.model.EventMetadata;
import sif3.common.model.SIFEvent;
import sif3.common.model.ServiceInfo;

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
  	/**
  	 * This method is called when a consumer service has received an event. This class does implement the actual onEvent( method
  	 * from the event interface. It may do some additional work for house keeping purpose so the original onEvent() id processed
  	 * as part of this class but then this method is called so that the actual consumer can do its work as required.
  	 * 
  	 * @param sifEvent The event data that has been received and shall be processed by the consumer. This parameter also holds
  	 *                 the zone and context in the limitToZoneCtxList property. It will always only hold one entry in that
  	 *                 list. So the zone can be retrieved with the following call: sifEvent.getLimitToZoneCtxList().get(0).getZone().
  	 * @param metadata Additional metadata that is known for the event. Typical values include custom HTTP headers, sourceName etc.
  	 * @param msgReadID The ID of the SIF queue reader. It is informative only and is only of use where there are multiple concurrent 
  	 *                  subscribers on a message queue.
  	 * @param consumerID The consumer ID that has been used to receive the event from the event queue. It is informative
  	 *                   only and is only of use where there are multiple event subscribers enabled.
  	 */
    public abstract void processEvent(SIFEvent<L> sifEvent, EventMetadata metadata, String msgReadID, String consumerID);
  
	public AbstractEventConsumer()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sif3.common.interfaces.EventConsumer#onEvent(sif3.common.model.SIFEvent, sif3.common.model.EventMetadata, java.lang.String, java.lang.String)
	 */
	@Override
//	public void onEvent(SIFEvent<L> sifEvent, SIFZone zone, SIFContext context, EventMetadata metadata, String msgReadID, String consumerID)
    public void onEvent(SIFEvent<L> sifEvent, EventMetadata metadata, String msgReadID, String consumerID)
	{
		// Right now all that is required is to call the abstract processEvent() method.
//		processEvent(sifEvent, zone, context, metadata, msgReadID, consumerID);
        processEvent(sifEvent, metadata, msgReadID, consumerID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sif3.infra.rest.consumer.AbstractConsumer#finalise()
	 */
	@Override
	public void finalise()
	{
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
    
    /*----------------------------*/
    /*-- Other required methods --*/
    /*----------------------------*/
    /**
     * This method is is called when the event processor is initialised. It passes all subscription services for the
     * given OBJECT service across all zones for the connected environment to this method. It allows the specific OBJECT
     * event consumer implementation to remove some of the subscription services it is not interested in. Basically it allows
     * the implementor to filter out un-needed subscriptions before the event processor subscribes to the queues. Only
     * events for the returned list of service info will then be received by the particular OBJECT service. Most standard
     * implementations would not require any overriding of this method. If a specific implementation wishes to filter out
     * some of the environment provided subscriptions then the sub-class of this class should override this method.
     * 
     * @param envEventServices A list of services for this OBJECT that is allowed to subscribe to events in across the 
     *                         environment of this consumer.
     * 
     * @return The final list of event services for which this consumer class shall subscribe to.
     */
    public List<ServiceInfo> filterEventServices(List<ServiceInfo> envEventServices)
    {
    	return envEventServices;
    }
    
    /*
     * Return all OBJECT services that have the right of SUBSCRIBE is set to APPROVED in the ACL.
     */
	protected final List<ServiceInfo> getEventServices()
	{
		return filterEventServices(getAllApprovedServicesForRights(getMultiObjectClassInfo().getObjectName(), ServiceType.OBJECT, AccessRight.SUBSCRIBE));
	}

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
}
