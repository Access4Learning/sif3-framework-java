/*
 * JobEventWrapper.java
 * Created: 11 Sep 2018
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

package sif3.infra.rest.provider.functional;

import java.io.Serializable;
import java.util.ArrayList;

import sif3.common.header.HeaderValues.EventAction;
import sif3.common.model.ZoneContextInfo;

/**
 * This is a helper POJO to manage the publishing of Job Events.
 * 
 * @author Joerg Huber
 */
public class JobEventWrapper implements Serializable
{
    private static final long serialVersionUID = -5053804097493083125L;
    
    private EventAction eventAction;
    private String fingerprint;
    private ZoneContextInfo zoneContext;
    private ArrayList<JobEventData> events;

    public EventAction getEventAction()
    {
        return eventAction;
    }
    
    public void setEventAction(EventAction eventAction)
    {
        this.eventAction = eventAction;
    }
    
    public String getFingerprint()
    {
        return fingerprint;
    }
    
    public void setFingerprint(String fingerprint)
    {
        this.fingerprint = fingerprint;
    }
    
    public ZoneContextInfo getZoneContext()
    {
        return zoneContext;
    }
    
    public void setZoneContext(ZoneContextInfo zoneContext)
    {
        this.zoneContext = zoneContext;
    }
    
    public ArrayList<JobEventData> getEvents()
    {
        return events;
    }
    public void setEvents(ArrayList<JobEventData> events)
    {
        this.events = events;
    }

    @Override
    public String toString()
    {
        return "JobEventWrapper [eventAction=" + eventAction + ", fingerprint=" + fingerprint
                + ", zoneContext=" + zoneContext + ", events=" + events + "]";
    }
}
