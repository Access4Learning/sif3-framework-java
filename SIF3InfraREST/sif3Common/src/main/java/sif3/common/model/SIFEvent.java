/*
 * SIFEvent.java
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

package sif3.common.model;

import java.io.Serializable;
import java.util.HashMap;

import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.UpdateType;

/**
 * This is a simple POJO to encapsulate SIF Objects and its associated SIF Event Action. In SIF 3.x events are ALWAYS a list of SIF objects.
 * It is expected that the object type this event class deals with is a list of objects a defined in the applicable data model specification.
 * To keep this class data model independent it is not possible to generically enforce how a "list of objects" is represented in the POJOs
 * of the data model and therefore it is necessary to pass on the size of the object list the event deals with as a parameter/property to 
 * this class.
 * 
 * @author Joerg Huber
 */
public class SIFEvent<L> implements Serializable
{
	private static final long serialVersionUID = 7122348903009L;

	private L sifObjectList;
	private EventAction eventAction;
	private int listSize;
	private UpdateType updateType;
	private String fingerprint;
    private HashMap<String, String> metadata = new HashMap<String, String>();

	public SIFEvent()
	{
		this(null, null, null, 0);
	}

	/**
	 * Constructor 
	 * @param sifObjectList A list style object that encapsulates the list of objects to pack into an event.
	 * @param eventAction The event action for this event. Note that all object in the sifObjectList will be published with this event action.
	 * @param updateType If the event action is UPDATE then this update type is required to indicate if the data in the sifObjectList are 
	 *                   partial updates (only updated fields are provided in the event) or are full updates (all known fields to that provider
	 *                   for the objects are provided).
	 * @param listSize Since this class is independent from the data model it cannot automatically determine how many objects are in the
	 *                 sifObjectList. The sifObjectList could be any data structure. To indicate the number of object in the sifObjectList
	 *                 this field must be set.
	 */
	public SIFEvent(L sifObjectList, EventAction eventAction, UpdateType updateType, int listSize)
	{
		super();
		setSIFObjectList(sifObjectList);
		setEventAction(eventAction);
		setListSize(listSize);
		setUpdateType(updateType);
	}

	public L getSIFObjectList()
    {
    	return this.sifObjectList;
    }

	public void setSIFObjectList(L sifObjectList)
    {
    	this.sifObjectList = sifObjectList;
    }

	public int getListSize()
    {
    	return this.listSize;
    }

	/**
	 * @param listSize Since this class is independent from the data model it cannot automatically determine how many objects are in the
	 *                 sifObjectList. The sifObjectList could be any data structure. To indicate the number of object in the sifObjectList
	 *                 this field must be set.
	 */
	public void setListSize(int listSize)
    {
    	this.listSize = listSize;
    }

	public EventAction getEventAction()
    {
    	return this.eventAction;
    }

	public void setEventAction(EventAction eventAction)
    {
    	this.eventAction = eventAction;
    }

	public UpdateType getUpdateType()
    {
    	return this.updateType;
    }

	/**
	 * @param updateType updateType If the event action is UPDATE then this update type is required to indicate if the data in the sifObjectList are 
	 *                   partial updates (only updated fields are provided in the event) or are full updates (all known fields to that provider
	 *                   for the objects are provided).

	 */
	public void setUpdateType(UpdateType updateType)
    {
    	this.updateType = updateType;
    }

	public HashMap<String, String> getMetadata()
    {
    	return this.metadata;
    }

    public String getFingerprint()
    {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint)
    {
        this.fingerprint = fingerprint;
    }

	/**
	 * This method stores a simple name/value hashmap with this event. Note that this data is not forming part of the actual event that
	 * is being sent in a provider. It is also not received by a consumer from the event queue. It is the developer that can add additional
	 * information about the event in this structure for internal house keeping or the like.
	 * 
	 * @param metadata See desc.
	 */
	public void setMetadata(HashMap<String, String> metadata)
    {
    	this.metadata = metadata;
    }
	
	/**
	 * Adds a single name/value pair to the metadata map.
	 * 
	 * @param name The name of the pair
	 * @param value The actual value of the pair.
	 */
	public void addMetadataValue(String name, String value)
	{
		getMetadata().put(name, value);
	}

	@Override
    public String toString()
    {
        return "SIFEvent [sifObjectList=" + sifObjectList + ", eventAction=" + eventAction
                + ", listSize=" + listSize + ", updateType=" + updateType + ", fingerprint="
                + fingerprint + ", metadata=" + metadata + "]";
    }


}
