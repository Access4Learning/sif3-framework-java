/*
 * SIF3JobEvent.java
 * Created: 6 Feb 2018
 *
 * Copyright 2018 Systemic Pty Ltd
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

package sif3.common.persist.model;

import java.io.Serializable;
import java.util.Date;

import sif3.common.header.HeaderValues.EventAction;

/**
 * @author Joerg Huber
 *
 */
public class SIF3JobEvent extends JobBase implements Serializable
{
    private static final long serialVersionUID = -7652413318428804910L;
    
    public static enum JobEventType {U, C, D};
    
    private Date eventDate = null; // date and time when inserted into table
    private String eventType = null; // U=Update, C=Create, D=Delete
    private boolean fullUpdate = Boolean.TRUE; // TRUE = Update event is full update; FALSE = Update is partial
    private boolean toFingerPrintOnly = Boolean.TRUE; // TRUE = Include fingerprint in event; FALSE = May not include fingerprint
    private boolean consumerRequested = Boolean.TRUE; // TRUE = consumer requested an operation on the job
    private boolean published = Boolean.FALSE; // TRUE = Event is already published; FALSE = Event isn't published, yet.
    private Date publishedDate = null; // Date when event has been sent/published to event end-point.
    
    public SIF3JobEvent()
    {
        super();
    }
    
    /**
     * Creates a Job Event object for the given event type. It will set the fingerprint only and consumerRequested
     * flags as given.
     * 
     * @param job Create an event with the data of the job.
     * @param eventType The event type for this job event.
     * @param fingerprintOnly TRUE then job event will be sent to fingerprint only (not yet used)
     * @param consumerRequested Event was due to a consumer requested action on the job object.
     */
    public SIF3JobEvent(SIF3Job job, EventAction eventType, boolean fingerprintOnly, boolean consumerRequested)
    {
        if (job != null)
        {
            setJobID(job.getJobID());
            setServiceName(job.getServiceName());
            setAdapterType(job.getAdapterType());
            setZoneID(job.getZoneID());
            setContextID(job.getContextID());
            setEnvironmentID(job.getEnvironmentID());
            setJobXML(job.getJobXML());
            setFingerprint(job.getFingerprint());
            setToFingerPrintOnly(fingerprintOnly);
            setConsumerRequested(consumerRequested);
            setEventDate(new Date());
            setEventType(eventType.name().substring(0, 1));
            setFullUpdate(true);
            setPublished(false);
        }
    }
    
    public SIF3JobEvent(long internalID)
    {
        super(internalID);
    }

    public Date getEventDate()
    {
        return eventDate;
    }
    
    public void setEventDate(Date eventDate)
    {
        this.eventDate = eventDate;
    }
    
    public String getEventType()
    {
        return eventType;
    }
    
    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }
    
    public boolean isFullUpdate()
    {
        return fullUpdate;
    }
    
    public void setFullUpdate(boolean fullUpdate)
    {
        this.fullUpdate = fullUpdate;
    }
    
    public boolean isToFingerPrintOnly()
    {
        return toFingerPrintOnly;
    }
    
    public void setToFingerPrintOnly(boolean toFingerPrintOnly)
    {
        this.toFingerPrintOnly = toFingerPrintOnly;
    }
    
    public boolean isConsumerRequested()
    {
        return consumerRequested;
    }

    public void setConsumerRequested(boolean consumerRequested)
    {
        this.consumerRequested = consumerRequested;
    }

    public boolean isPublished()
    {
        return published;
    }
    
    public void setPublished(boolean published)
    {
        this.published = published;
    }
    
    public Date getPublishedDate()
    {
        return publishedDate;
    }
    
    public void setPublishedDate(Date publishedDate)
    {
        this.publishedDate = publishedDate;
    }
    
    @Override
    public String toString()
    {
        return "SIF3JobEvent [eventDate=" + eventDate + ", eventType=" + eventType + ", fullUpdate="
                + fullUpdate + ", toFingerPrintOnly=" + toFingerPrintOnly + ", consumerRequested="
                + consumerRequested + ", published=" + published + ", publishedDate="
                + publishedDate + ", toString()=" + super.toString() + "]";
    }
}
