/*
 * QueueMessage.java
 * Created: 3 Nov 2015
 *
 * Copyright 2015 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License. 
 */

package sif3.infra.rest.queue.types;

import java.io.Serializable;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import sif3.common.header.HeaderValues.MessageType;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;

/**
 * This class is a POJO that holds a "message" information retrieved from a queue from the Broker's Queue Manager. It holds
 * all required data, including the payload, headers etc for the massage in a "neutral" form so that it can be passed 
 * between classes and processed without having the need of many parameters or actual knowledge of the message content. The
 * payload is a String and not yet unmarshalled into an object.
 *  
 * @author Joerg Huber
 *
 */
public class QueueMessage implements Serializable
{
    private static final long serialVersionUID = -5579818384830135914L;
    
    private MessageType messageType = null;
    private ServiceType serviceType = null;
    private String payload;
    private HashMap<String, String> metadata = new HashMap<String, String>(); // metadata managed by developer.
    
    // Data about message and its origin
    private MediaType mediaType; // indicates what format the payload string is in.
    private SIFZone zone;
    private SIFContext context;
    private String fingerprint = null;
    private String messageQueueReaderID;

    public QueueMessage()
    {
      this(null, null, null, null, null, null, null);
    }
    
    public QueueMessage(String payload, MediaType mediaType)
    {
      this(payload, mediaType, null, null, null, null, null);
    }

    public QueueMessage(String payload, MediaType mediaType, ServiceType serviceType, MessageType messageType)
    {
      this(payload, mediaType, serviceType, messageType, null, null, null);
    }

    public QueueMessage(String payload, MediaType mediaType, ServiceType serviceType, MessageType messageType, SIFZone zone, SIFContext context)
    {
        this(payload, mediaType, serviceType, messageType, zone, context, null);     
    }
    
    public QueueMessage(String payload, MediaType mediaType, ServiceType serviceType, MessageType messageType, SIFZone zone, SIFContext context, String messageQueueReaderID)
    {
      super();
      setPayload(payload);
      setMediaType(mediaType);
      setServiceType(serviceType);
      setMessageType(messageType);
      setZone(zone);
      setContext(context);
      setMessageQueueReaderID(messageQueueReaderID);
    }

    /**
     * @return the messageType
     */
    public MessageType getMessageType()
    {
        return messageType;
    }
    
    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(MessageType messageType)
    {
        this.messageType = messageType;
    }
    
    /**
     * @return the serviceType
     */
    public ServiceType getServiceType()
    {
        return serviceType;
    }
    
    /**
     * @param serviceType the serviceType to set
     */
    public void setServiceType(ServiceType serviceType)
    {
        this.serviceType = serviceType;
    }
    
    /**
     * @return the payload
     */
    public String getPayload()
    {
        return payload;
    }
    
    /**
     * @param payload the payload to set
     */
    public void setPayload(String payload)
    {
        this.payload = payload;
    }
    
    /**
     * @return the metadata
     */
    public HashMap<String, String> getMetadata()
    {
        return metadata;
    }
    
    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(HashMap<String, String> metadata)
    {
        this.metadata = metadata;
    }
    
    /**
     * @return the mediaType
     */
    public MediaType getMediaType()
    {
        return mediaType;
    }
    
    /**
     * @param mediaType the mediaType to set
     */
    public void setMediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
    }
    
    /**
     * @return the zone
     */
    public SIFZone getZone()
    {
        return zone;
    }
    
    /**
     * @param zone the zone to set
     */
    public void setZone(SIFZone zone)
    {
        this.zone = zone;
    }
    
    /**
     * @return the context
     */
    public SIFContext getContext()
    {
        return context;
    }
    
    /**
     * @param context the context to set
     */
    public void setContext(SIFContext context)
    {
        this.context = context;
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
     * @return the messageQueueReaderID
     */
    public String getMessageQueueReaderID()
    {
        return messageQueueReaderID;
    }
    
    /**
     * @param messageQueueReaderID the messageQueueReaderID to set
     */
    public void setMessageQueueReaderID(String messageQueueReaderID)
    {
        this.messageQueueReaderID = messageQueueReaderID;
    }
    
    @Override
    public String toString()
    {
        return "QueueMessage [messageType=" + messageType + ", serviceType=" + serviceType
                + ", payload=" + payload + ", metadata=" + metadata + ", mediaType=" + mediaType
                + ", zone=" + zone + ", context=" + context + ", fingerprint=" + fingerprint
                + ", messageQueueReaderID=" + messageQueueReaderID + "]";
    }

}
