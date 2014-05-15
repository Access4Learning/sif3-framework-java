/*
 * EventInfo.java
 * Created: 09/05/2014
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
package sif3.infra.rest.queue.types;

import java.io.Serializable;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;

/**
 * This class is a utility type to allow event consumer package the actual event data and related information in one structure. This type
 * uses the actual event data as a String as it has been received from the remote message queue. This is so that the event is still raw
 * without the need of knowing what to unmarshal it into until it is actually send to the final event consumer. This way an event can
 * easily be passed between classes and processed without having the need of many parameters or actual knowledge of the event object 
 * class<br/><br/>

 * @author Joerg Huber
 *
 */
public class EventInfo implements Serializable
{
  private static final long serialVersionUID = -920081412771061609L;

  // Actual Event data
  private String eventPayload;
  private EventAction eventAction;
  private UpdateType updateType;
  private HashMap<String, String> metadata = new HashMap<String, String>();
  
  // Data about event
  private MediaType mediaType; // indicates what format the payload string is in.
  private SIFZone zone;
  private SIFContext context;
  private String messageQueueReaderID;

  public EventInfo()
  {
    this(null, null, null, null, null, null, null);
  }
  
  public EventInfo(String eventPayload, MediaType mediaType)
  {
    this(eventPayload, mediaType, null, null, null, null, null);
  }

  public EventInfo(String eventPayload, MediaType mediaType, EventAction eventAction)
  {
    this(eventPayload, mediaType, eventAction, null, null, null, null);
  }

  public EventInfo(String eventPayload, MediaType mediaType, EventAction eventAction, UpdateType updateType)
  {
    this(eventPayload, mediaType, eventAction, updateType, null, null, null);
  }

  public EventInfo(String eventPayload, MediaType mediaType, EventAction eventAction, UpdateType updateType, SIFZone zone, SIFContext context)
  {
    this(eventPayload, mediaType, eventAction, updateType, zone, context, null);
  }

  public EventInfo(String eventPayload, MediaType mediaType, EventAction eventAction, UpdateType updateType, SIFZone zone, SIFContext context, String messageQueueReaderID)
  {
    super();
    setEventPayload(eventPayload);
    setMediaType(mediaType);
    setEventAction(eventAction);
    setUpdateType(updateType);
    setZone(zone);
    setContext(context);
    setMessageQueueReaderID(messageQueueReaderID);
  }

  public String getEventPayload()
  {
    return eventPayload;
  }

  public void setEventPayload(String eventPayload)
  {
    this.eventPayload = eventPayload;
  }

  public MediaType getMediaType()
  {
    return mediaType;
  }

  public void setMediaType(MediaType mediaType)
  {
    this.mediaType = mediaType;
  }

  public EventAction getEventAction()
  {
    return eventAction;
  }

  public void setEventAction(EventAction eventAction)
  {
    this.eventAction = eventAction;
  }

  public UpdateType getUpdateType()
  {
    return updateType;
  }

  public void setUpdateType(UpdateType updateType)
  {
    this.updateType = updateType;
  }

  public HashMap<String, String> getMetadata()
  {
    return metadata;
  }

  public void setMetadata(HashMap<String, String> metadata)
  {
    this.metadata = metadata;
  }

  public SIFZone getZone()
  {
    return zone;
  }

  public void setZone(SIFZone zone)
  {
    this.zone = zone;
  }

  public SIFContext getContext()
  {
    return context;
  }

  public void setContext(SIFContext context)
  {
    this.context = context;
  }

  public String getMessageQueueReaderID()
  {
    return messageQueueReaderID;
  }

  public void setMessageQueueReaderID(String messageQueueReaderID)
  {
    this.messageQueueReaderID = messageQueueReaderID;
  }

  @Override
  public String toString()
  {
    return "EventInfo [context=" + context + ", eventAction=" + eventAction
        + ", eventPayload=" + eventPayload + ", mediaType=" + mediaType
        + ", messageQueueReaderID=" + messageQueueReaderID + ", metadata="
        + metadata + ", updateType=" + updateType + ", zone=" + zone + "]";
  } 
}
