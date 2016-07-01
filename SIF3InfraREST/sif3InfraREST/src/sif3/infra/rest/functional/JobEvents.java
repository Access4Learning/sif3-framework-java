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

package sif3.infra.rest.functional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sif3.common.model.SIFEvent;
import sif3.infra.common.model.JobCollectionType;

/**
 * A class that aggregates JobEvent instances
 */
public class JobEvents
{
    private List<JobEvent> sifevents = Collections.synchronizedList(new ArrayList<JobEvent>());

    /**
     * Get the JobEvent that contains the given SIFEvent
     * 
     * @param sifevent
     *            The SIFEvent to look for
     * @return The JobEvent (includes zone and context information) found, null otherwise.
     */
    public JobEvent get(SIFEvent<JobCollectionType> sifevent)
    {
        for (JobEvent event : sifevents)
        {
            if (event.getEvent().equals(sifevent))
            {
                return event;
            }
        }
        return null;
    }

    /**
     * Get all the SIFEvents from the internal collection of JobEvents
     * 
     * @return A list of SIFEvents
     */
    public List<SIFEvent<JobCollectionType>> getAllEvents()
    {
        List<SIFEvent<JobCollectionType>> events = new ArrayList<SIFEvent<JobCollectionType>>();
        for (JobEvent event : sifevents)
        {
            events.add(event.getEvent());
        }
        return events;
    }

    /**
     * Add a JobEvent to this collection
     * 
     * @param event
     *            The JobEvent to add
     * @return true if added, false otherwise.
     */
    public boolean add(JobEvent event)
    {
        return sifevents.add(event);
    }

    /**
     * Removes a JobEvent from this collection
     * 
     * @param event
     *            The JobEvent to remove
     * @return true if removed, false otherwise.
     */
    public boolean remove(JobEvent event)
    {
        return sifevents.remove(event);
    }

    /**
     * A method to determine if a JobEvents instance contains any JobEvents.
     * 
     * @return True if there are no JobEvents, false otherwise.
     */
    public boolean isEmpty()
    {
        return sifevents.isEmpty();
    }
}
