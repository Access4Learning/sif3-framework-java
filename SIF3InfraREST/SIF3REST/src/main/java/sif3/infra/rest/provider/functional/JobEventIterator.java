/*
 * JobEventIterator.java
 * Created: 4 Sep 2018
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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.model.ZoneContextInfo;
import sif3.common.persist.model.SIF3JobEvent;
import sif3.common.persist.service.JobService;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;

/**
 * This method is the implementation for the event iterator for functional service jobs. Because the framework
 * maintains events internally and the event object is known for functional services this can fully be implemented
 * by this framework and doesn't have to be done by individual provider classes as with object services.
 * 
 * @author Joerg Huber
 */
public class JobEventIterator implements SIFEventIterator<JobCollectionType>
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private InfraUnmarshalFactory infraUnmarshaller =  new InfraUnmarshalFactory();

    private int currentPos = 0;
    private List<SIF3JobEvent> jobEvents =  new ArrayList<SIF3JobEvent>();

    public JobEventIterator(String serviceName, boolean includeConsumeRequested, AdapterType adapterType, AdvancedProperties serviceProperties)
    {
        currentPos = 0;
        try
        {
            JobService service = new JobService();
        
            jobEvents = service.retrieveJobEvents(serviceName, adapterType, includeConsumeRequested);
        }
        catch (Exception ex)
        {
            logger.error("Failed to retrieve Job Events for Functional Service "+serviceName+": "+ex.getMessage(), ex);
            jobEvents =  new ArrayList<SIF3JobEvent>();
        }
    }

    /* 
     * We do not implement this method! For Jobs we need to do some more sophistication and since the publishing
     * of Job Events is under full control of the BaseEventProvider we can implement another method that is
     * specifically geared towards the way Job Event publishing may work. The core issue is that Job events are
     * published to the 'fingerprint' consumer only but also to some generic auditZones. Later the published jobs
     * need to be marked as published. So a job event may need to be published to a few zones and a different set of
     * events may need to be published to each zone. The 'fingerprint' belongs to a specific zone and indicates
     * the owner of the job. Most likely the consumerCreated events do not need to be published to that zone
     * where as provider created events do. In Audit Zones all events need to be published. To avoid too many
     * events to be published to the 'fingerprint' zone we need a little bit more info in the getNextEvent()
     * method than just a list of SIF Job Objects. 
     *  
     * (non-Javadoc)
     * @see sif3.common.interfaces.SIFEventIterator#getNextEvents(int)
     */
    @Override
    public SIFEvent<JobCollectionType> getNextEvents(int maxListSize)
    {
        return null;
    }
    
    /**
     * This method returns the next set of events. The max size of the list is given by the maxListSize parameter.
     * Note the returned list holds events of the same event type and the same zone, context and fingerprint.
     * The BaseFunctionalServiceProvider must use this method to get the next set of events rather than the
     * getNextEvents(int maxListSize) method above. If null is returned by this method then it must be assumed
     * that there are no more events available.
     * 
     * @param maxListSize
     * 
     * @return See desc.
     */
    public JobEventWrapper getEvents(int maxListSize)
    {
        JobEventWrapper events = null;
        if (hasNext())
        {
            events = new JobEventWrapper();
            events.setEvents(new ArrayList<JobEventData>());
            while ((events.getEvents().size() < maxListSize) && hasNext())
            {
                SIF3JobEvent jobEvent = jobEvents.get(currentPos);
                currentPos++;
                
                try
                {
                    if (events.getEvents().size() == 0) // first record => initialise wrapper
                    {
                        events.setEventAction(mapEventType(jobEvent.getEventType()));
                        events.setFingerprint(jobEvent.getFingerprint());
                        events.setZoneContext(new ZoneContextInfo(new SIFZone(jobEvent.getZoneID()), new SIFContext(jobEvent.getContextID())));
                        
                        addEventToWrapper(events, jobEvent);
                    }
                    else
                    {
                        EventAction eventAction = mapEventType(jobEvent.getEventType());
                        if ((events.getEventAction() == eventAction) &&
                             events.getFingerprint().equals(jobEvent.getFingerprint()) &&
                             events.getZoneContext().getZone().getId().equals(jobEvent.getZoneID()) &&
                             events.getZoneContext().getContext().getId().equals(jobEvent.getContextID()))
                        {
                            addEventToWrapper(events, jobEvent);
                        }
                        else // We are done for this set. Roll back counter as this event isn't processed yet
                        {
                            currentPos--;
                            break; // and stop....
                        }
                    }
                }
                catch (Exception ex) // most likely failed to unmarshal event. Should not really happen! 
                {
                    logger.error("Failed to extract Jobe Event Data for Job Event ID = "+jobEvent.getInternalID()+": "+ex.getMessage()+". Ignore this event.", ex);
                }
            }
        }

        return events;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.SIFEventIterator#hasNext()
     */
    @Override
    public boolean hasNext()
    {
        return (currentPos < jobEvents.size());
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.SIFEventIterator#releaseResources()
     */
    @Override
    public void releaseResources()
    {
        jobEvents = new ArrayList<SIF3JobEvent>();
        currentPos = 0;
    }
    
    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    private EventAction mapEventType(String jobDBEventType)
    {
        if ("U".equalsIgnoreCase(jobDBEventType))
        {
            return EventAction.UPDATE;
        }
        if ("C".equalsIgnoreCase(jobDBEventType))
        {
            return EventAction.CREATE;
        }
        if ("D".equalsIgnoreCase(jobDBEventType))
        {
            return EventAction.DELETE;
        }
        
        return null;
    }
    
    private void addEventToWrapper(JobEventWrapper events, SIF3JobEvent jobEvent) throws Exception
    {
        JobType job = null;
        JobEventData event = new JobEventData();
        event.setConsumerEvent(jobEvent.isConsumerRequested());
        event.setInternalJobID(jobEvent.getInternalID());

        // If it is a delete event then we simply create a empty JobType with only the refID populated.
        if (events.getEventAction() == EventAction.DELETE)
        {
            job = new JobType();
            job.setId(jobEvent.getJobID());
        }
        else
        {
            job = (JobType)infraUnmarshaller.unmarshalFromXML(jobEvent.getJobXML(), JobType.class);
        }
        event.setJobData(job);
        
        events.getEvents().add(event);
    }
}
