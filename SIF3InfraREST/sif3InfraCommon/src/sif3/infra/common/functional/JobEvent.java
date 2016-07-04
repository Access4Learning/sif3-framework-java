/*
 * Crown Copyright © Department for Education (UK) 2016
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

package sif3.infra.common.functional;

import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.infra.common.model.JobCollectionType;

/**
 * An object that represents an event for a particular zone and context.
 */
public class JobEvent
{
    private SIFEvent<JobCollectionType> event;
    private SIFZone                     zone;
    private SIFContext                  context;

    /**
     * Empty constructor
     */
    public JobEvent()
    {
        super();
    }

    /**
     * Constructor with a SIFEvent.
     * 
     * @param event
     *            A SIF event for a collection of jobs.
     */
    public JobEvent(SIFEvent<JobCollectionType> event)
    {
        this();
        this.event = event;
    }

    /**
     * Constructor with a SIFEvent, zone and context.
     * 
     * @param event
     *            A SIF event for a collection of jobs.
     * @param zone
     *            The zone the event occurred in.
     * @param context
     *            The context the event occurred in.
     */
    public JobEvent(SIFEvent<JobCollectionType> event, SIFZone zone, SIFContext context)
    {
        this(event);
        this.zone = zone;
        this.context = context;
    }

    /**
     * Gets the event instance
     * 
     * @return The SIFEvent instance
     */
    public SIFEvent<JobCollectionType> getEvent()
    {
        return event;
    }

    /**
     * Sets the SIFEvent instance
     * 
     * @param event
     *            the event to set
     */
    public void setEvent(SIFEvent<JobCollectionType> event)
    {
        this.event = event;
    }

    /**
     * Gets the zone
     * 
     * @return the zone
     */
    public SIFZone getZone()
    {
        return zone;
    }

    /**
     * Sets the zone
     * 
     * @param zone
     *            the zone to set
     */
    public void setZone(SIFZone zone)
    {
        this.zone = zone;
    }

    /**
     * Checks if the given zone is specified in this event
     * 
     * @param zone
     *            The zone to check
     * @return True if the zone in this JobEvent and arguments are equal or both null.
     */
    public boolean hasZone(SIFZone zone)
    {
        if (getZone() == null)
        {
            return zone == null;
        }
        else
        {
            return getZone().equals(zone);
        }
    }

    /**
     * Gets the context
     * 
     * @return the context
     */
    public SIFContext getContext()
    {
        return context;
    }

    /**
     * Sets the context
     * 
     * @param context
     *            the context to set
     */
    public void setContext(SIFContext context)
    {
        this.context = context;
    }

    /**
     * Checks if the given context is specified in this event
     * 
     * @param context
     *            The context to check
     * @return True if the context in this JobEvent and arguments are equal or both null.
     */
    public boolean hasContext(SIFContext context)
    {
        if (getContext() == null)
        {
            return context == null;
        }
        else
        {
            // TODO Investigate why the contexts that end up here have the right IDs but the
            // isDefault values differ. Since equality of SIFContext objects depends on their
            // toString() this gives an unexpected result. Instead we will rely on just the ID
            // instead.
            return context == null ? false : getContext().getId().equals(context.getId());
        }
    }
}
