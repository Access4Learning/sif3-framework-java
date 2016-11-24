/*
 * SIFEventIterator.java
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

import sif3.common.model.SIFEvent;


/**
 * @author Joerg Huber
 *
 */
public interface SIFEventIterator<L>
{
	/**
	 * This method returns the next available SIF Event. The SIFEvent returned can only be of a particular event
	 * type and the maximum size of the object list that forms part of the returned SIFEvent POJO is given by
	 * the maxListSize parameter. Note the returned object list can be less than the maxListSize but it should
	 * not be larger. If there are no events available then this method must return null.
	 * 
	 * @param maxListSize The max size of the next object list to be returned as part of this method.
     *                   
     * @return SIFEvent The next available SIFEvent of a particular event type (DELETE, CHANGE, CREATE). The maximum
     *                  size of the objectList in the returned object is given by the maxListSize parameter.
     */
	public SIFEvent<L> getNextEvents(int maxListSize);
	
	/**
	 * Returns TRUE if there are more SIF Events available. In this case the getNextEvents() method should
	 * return a SIF Event that is not null. FALSE is returned if there are no more SIF Events available. In
	 * this case the getNextEvents() method should return null.
	 * 
	 * @return See description.
	 */
	public boolean hasNext();
	
	/**
	 * To be able to retrieve SIF Events in a "batched" manner there might be the need to allocate some resources in the
	 * class that implements this interface. These resources might be allocated until the last SIF Event is returned to the publisher. 
	 * This method is called by the BasePublisher class once all events are  retrieved. It allows the implementor of this interface to 
	 * release the allocated resources. Typical examples of allocated resources are DB Connections, SQL Result Sets etc.
	 */
	public void releaseResources();
}
