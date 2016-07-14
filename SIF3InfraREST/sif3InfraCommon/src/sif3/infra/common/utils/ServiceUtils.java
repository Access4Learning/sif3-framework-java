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

package sif3.infra.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import org.apache.log4j.Logger;

import sif3.common.CommonConstants.JobState;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.model.ServiceRights;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.persist.model.SIF3Job;
import sif3.common.persist.model.SIF3Phase;
import sif3.common.persist.model.SIF3PhaseState;
import sif3.common.utils.DateUtils;
import sif3.common.ws.Response;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobStateType;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.PhaseCollectionType;
import sif3.infra.common.model.PhaseStateType;
import sif3.infra.common.model.PhaseType;
import sif3.infra.common.model.RightType;
import sif3.infra.common.model.RightValueType;
import sif3.infra.common.model.RightsType;
import sif3.infra.common.model.StateCollectionType;
import sif3.infra.common.model.StateType;

/**
 * Utility methods for functional service related activities.
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public class ServiceUtils
{
    protected final Logger       logger        = Logger.getLogger(getClass());
    private static ObjectFactory objectFactory = new ObjectFactory();

    public static JobCollectionType marshal(Collection<?> jobs)
    {
        JobCollectionType items = objectFactory.createJobCollectionType();
        if (jobs != null && !jobs.isEmpty())
        {
            for (Object job : jobs)
            {
                if (!(job instanceof SIF3Job))
                {
                    throw new IllegalArgumentException(
                            "The collection must contain elements of type SIF3Job");
                }
                items.getJob().add(marshal((SIF3Job) job));
            }
        }
        return items;
    }

    public static JobType marshal(SIF3Job job)
    {
        if (job == null)
        {
            return null;
        }
        JobType item = objectFactory.createJobType();
        item.setId(job.getId());
        item.setName(job.getName());
        item.setDescription(job.getDescription());
        item.setState(job.getState() == null ? null : JobStateType.valueOf(job.getState().name()));
        item.setStateDescription(job.getStateDescription());
        item.setCreated(convert(job.getCreated()));
        item.setLastModified(convert(job.getLastModified()));
        item.setTimeout(convert(job.getTimeout()));
        PhaseCollectionType phases = objectFactory.createPhaseCollectionType();
        for (SIF3Phase p : job.getPhases())
        {
            phases.getPhase().add(marshal(p));
        }
        item.setPhases(phases);
        return item;
    }

    public static PhaseType marshal(SIF3Phase phase)
    {
        if (phase == null)
        {
            return null;
        }
        PhaseType item = objectFactory.createPhaseType();
        item.setName(phase.getName());
        item.setRequired(phase.isRequired());
        item.setRights(marshal(phase.getRights()));
        item.setStatesRights(marshal(phase.getStatesRights()));
        StateCollectionType states = objectFactory.createStateCollectionType();
        for (SIF3PhaseState s : phase.getStates())
        {
            states.getState().add(marshal(s));
        }
        item.setStates(states);
        return item;
    }

    public static StateType marshal(SIF3PhaseState state)
    {
        if (state == null)
        {
            return null;
        }
        StateType item = objectFactory.createStateType();
        item.setId(state.getId());
        item.setDescription(state.getDescription());
        item.setCreated(convert(state.getCreated()));
        item.setLastModified(convert(state.getLastModified()));
        item.setType(
                state.getType() == null ? null : PhaseStateType.valueOf(state.getType().name()));
        return item;
    }

    public static RightsType marshal(ServiceRights rights)
    {
        if (rights == null)
        {
            return null;
        }
        RightsType items = objectFactory.createRightsType();
        for (AccessRight accessRight : rights.getRights().keySet())
        {
            RightType right = objectFactory.createRightType();
            right.setType(accessRight.name());
            right.setValue(RightValueType.valueOf(rights.getRights().get(accessRight).name()));
            items.getRight().add(right);
        }
        return items;
    }

    public static List<SIF3Job> unmarshal(JobCollectionType items)
    {
        ArrayList<SIF3Job> jobs = new ArrayList<SIF3Job>();
        if (items == null)
        {
            return jobs;
        }

        for (JobType item : items.getJob())
        {
            jobs.add(unmarshal(item));
        }
        return jobs;
    }

    public static SIF3Job unmarshal(JobType item)
    {
        if (item == null)
        {
            return null;
        }
        SIF3Job job = new SIF3Job();
        job.setId(item.getId());
        job.setName(item.getName());
        job.setDescription(item.getDescription());
        job.setState(item.getState() == null ? null : JobState.valueOf(item.getState().name()));
        job.setStateDescription(item.getStateDescription());
        job.setCreated(convert(item.getCreated()));
        job.setLastModified(convert(item.getLastModified()));
        long timeout = convert(item.getTimeout());
        if (timeout > 0)
        {
            job.setTimeout(timeout);
        }
        job.setPhases(unmarshal(item.getPhases()));
        return job;
    }

    public static List<SIF3Phase> unmarshal(PhaseCollectionType items)
    {
        if (items == null)
        {
            return null;
        }

        List<SIF3Phase> phases = new ArrayList<SIF3Phase>();
        for (PhaseType phase : items.getPhase())
        {
            phases.add(unmarshal(phase));
        }
        return phases;
    }

    public static SIF3Phase unmarshal(PhaseType item)
    {
        if (item == null)
        {
            return null;
        }
        SIF3Phase phase = new SIF3Phase();
        phase.setName(item.getName());
        phase.setRequired(item.isRequired());
        phase.setRights(unmarshal(item.getRights()));
        phase.setStatesRights(unmarshal(item.getStatesRights()));
        phase.setStates(unmarshal(item.getStates()));
        return phase;
    }

    public static List<SIF3PhaseState> unmarshal(StateCollectionType items)
    {
        if (items == null)
        {
            return null;
        }

        List<SIF3PhaseState> states = new ArrayList<SIF3PhaseState>();
        for (StateType state : items.getState())
        {
            states.add(unmarshal(state));
        }
        return states;
    }

    public static SIF3PhaseState unmarshal(StateType item)
    {
        if (item == null)
        {
            return null;
        }
        SIF3PhaseState state = new SIF3PhaseState();
        state.setId(item.getId());
        state.setDescription(item.getDescription());
        state.setCreated(convert(item.getCreated()));
        state.setLastModified(convert(item.getLastModified()));
        state.setType(item.getType() == null ? null : PhaseState.valueOf(item.getType().name()));
        return state;
    }

    public static ServiceRights unmarshal(RightsType items)
    {
        if (items == null)
        {
            return null;
        }
        ServiceRights rights = new ServiceRights();
        for (RightType rightType : items.getRight())
        {
            rights.addRight(AccessRight.valueOf(rightType.getType()),
                    AccessType.valueOf(rightType.getValue().name()));
        }
        return rights;
    }

    public static List<Response> unmarshal(List<Response> responses)
    {
        for (Response r : responses)
        {
            if (!r.getHasEntity())
            {
                continue;
            }
            if (JobType.class.isAssignableFrom(r.getDataObjectType()))
            {
                r.setDataObjectType(SIF3Job.class);
                r.setDataObject(unmarshal((JobType) r.getDataObject()));
            }
            if (StateType.class.isAssignableFrom(r.getDataObjectType()))
            {
                r.setDataObjectType(SIF3PhaseState.class);
                r.setDataObject(unmarshal((StateType) r.getDataObject()));
            }
        }
        return responses;
    }

    public static SIF3Job filter(SIF3Job job, String phaseName)
    {
        SIF3Job newJob = new SIF3Job();
        newJob.setId(job.getId());
        newJob.setName(job.getName());
        /*
         * newJob.setDescription(job.getDescription()); newJob.setCreated(job.getCreated());
         * newJob.setLastModified(job.getLastModified()); newJob.setState(job.getState());
         */
        newJob.getPhases().add(job.getPhase(phaseName));
        return newJob;
    }

    private static Date convert(Calendar calendar)
    {
        if (calendar == null)
        {
            return null;
        }
        return calendar.getTime();
    }

    private static Calendar convert(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        time.setTime(date);
        return time;
    }

    private static long convert(Duration duration)
    {
        return duration == null ? 0 : duration.getTimeInMillis(DateUtils.now());
    }

    private static Duration convert(long duration)
    {
        if (duration != 0)
        {
            return null;
        }

        try
        {
            return DatatypeFactory.newInstance().newDuration(duration);
        }
        catch (DatatypeConfigurationException e)
        {
            throw new IllegalArgumentException(
                    "Could not convert the timeout to an XML Duration object.", e);
        }
    }
}
