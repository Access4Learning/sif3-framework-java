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

import org.apache.log4j.Logger;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants.JobState;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.model.ServiceRights;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.persist.model.SIF3Job;
import sif3.common.persist.model.SIF3Phase;
import sif3.common.persist.model.SIF3PhaseState;
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
import sif3.infra.common.model.StateType;

/**
 * Utility methods for functional service related activities. 
 */
public class ServiceUtils
{
    protected final Logger       logger        = Logger.getLogger(getClass());
    private static ObjectFactory objectFactory = new ObjectFactory();

    /**
     * Get the named phase from the job instance.
     * 
     * @param job
     *            The job from which to retrieve the named phase
     * @param phaseName
     *            The name of the phase
     * @return The named phase from the job object
     * @throws IllegalArgumentException
     *             if the phase cannot be found
     */
    public static SIF3Phase getPhase(SIF3Job job, String phaseName)
    {
        if (job == null)
        {
            throw new IllegalArgumentException(
                    "Cannot get phase " + phaseName + " from a job that's null.");
        }

        if (StringUtils.isEmpty(phaseName))
        {
            throw new IllegalArgumentException(
                    "Cannot get empty phase from job (" + job.getId() + ").");
        }

        if (job.getPhases() == null)
        {
            job.setPhases(new ArrayList<SIF3Phase>());
        }
        for (SIF3Phase phase : job.getPhases())
        {
            if (phase.getName().equals(phaseName))
            {
                return phase;
            }
        }
        throw new IllegalArgumentException("Could not find phase " + phaseName + " in "
                + job.getName() + " job (" + job.getId() + ").");
    }

    /**
     * Gets the last state object from the named phase on the given job
     * 
     * @param job
     *            The job in which to look
     * @param phaseName
     *            The target phase name
     * @return The last state object for the named phase, or null if there isn't one.
     * @throws IllegalArgumentException
     *             if the phase cannot be found or other problem is encountered
     */
    public static SIF3PhaseState getLastPhaseState(SIF3Job job, String phaseName)
    {
        return getLastPhaseState(getPhase(job, phaseName));
    }

    /**
     * Gets the last state object from the named phase on the given job
     * 
     * @param phase
     *            The phase in which to look for the current state
     * @return The last state object for the given phase, or null if there isn't one.
     * @throws IllegalArgumentException
     *             If the phase is null
     */
    public static SIF3PhaseState getLastPhaseState(SIF3Phase phase)
    {
        if (phase == null)
        {
            throw new IllegalArgumentException("Cannot get the last state of a null phase.");
        }
        if (phase.getStates() == null)
        {
            return null;
        }
        List<SIF3PhaseState> states = phase.getStates();
        if (states == null || states.isEmpty())
        {
            return null;
        }
        return states.get(states.size() - 1);
    }

    /**
     * Adds a phase to a job instance
     * 
     * @param job
     *            The job to add the phase to
     * @param phaseName
     *            The name of the phase to add
     * @param required
     *            Whether the phase is required or not
     * @param rights
     *            The access rights for CRUD operations on the phase
     * @param statesRights
     *            The access rights for CRUD operations on the states of the phase
     * @return The phase instance that has been added to the job
     */
    public static SIF3Phase addPhase(SIF3Job job, String phaseName, boolean required,
            ServiceRights rights, ServiceRights statesRights)
    {
        return addPhase(job, phaseName, required, rights, statesRights, PhaseState.NOTSTARTED,
                null);
    }

    /**
     * Adds a phase to a job instance
     * 
     * @param job
     *            The job to add the phase to
     * @param phaseName
     *            The name of the phase to add
     * @param required
     *            Whether the phase is required or not
     * @param rights
     *            The access rights for CRUD operations on the phase
     * @param statesRights
     *            The access rights for CRUD operations on the states of the phase
     * @param state
     *            The initial state of the phase
     * @return The phase instance that has been added to the job
     */
    public static SIF3Phase addPhase(SIF3Job job, String phaseName, boolean required,
            ServiceRights rights, ServiceRights statesRights, PhaseState state)
    {
        return addPhase(job, phaseName, required, rights, statesRights, state, null);
    }

    /**
     * Adds a phase to a job instance
     * 
     * @param job
     *            The job to add the phase to
     * @param phaseName
     *            The name of the phase to add
     * @param required
     *            Whether the phase is required or not
     * @param rights
     *            The access rights for CRUD operations on the phase
     * @param statesRights
     *            The access rights for CRUD operations on the states of the phase
     * @param state
     *            The initial state of the phase
     * @param stateDescription
     *            The description of the phase
     * @return The phase instance that has been added to the job
     */
    public static SIF3Phase addPhase(SIF3Job job, String phaseName, boolean required,
            ServiceRights rights, ServiceRights statesRights, PhaseState state,
            String stateDescription)
    {
        if (job.getPhases() == null)
        {
            job.setPhases(new ArrayList<SIF3Phase>());
        }

        SIF3Phase phase = new SIF3Phase();
        phase.setName(phaseName);
        phase.setRequired(required);
        phase.setStates(new ArrayList<SIF3PhaseState>());
        phase.setRights(rights);
        phase.setStatesRights(statesRights);

        List<SIF3Phase> phases = job.getPhases();
        phases.add(phase);
        job.setPhases(phases);

        changePhaseState(job, phase, state, stateDescription);

        return phase;
    }

    /**
     * Check that the given phase has sufficient rights for the given AccessRight and AccessType
     * instances.
     * 
     * @param phase
     *            The phase on which to look at the access control list
     * @param right
     *            The type of access right desired (CREATE, QUERY, DELETE, etc.)
     * @param type
     *            The expected value of the AccessRight (APPROOVED, REJECTED, etc.)
     * @return true if the phase supports the given access right, false otherwise.
     */
    public static boolean checkPhaseACL(SIF3Phase phase, AccessRight right, AccessType type)
    {
        return phase.getRights().hasRight(right, type);
    }

    /**
     * Change the state of a given job, updating the last modified value etc.
     * 
     * @param job
     *            The job to update the state of
     * @param state
     *            The new status of the job
     * @param description
     *            The description of the state change
     */
    public static void changeJobState(SIF3Job job, JobState state, String description)
    {
        job.setLastModified(now());
        job.setStateDescription(description);
        job.setState(JobState.valueOf(state.name()));
    }

    /**
     * Change the state of a phase on a given job
     * 
     * @param job
     *            The job the phase belongs to (so it can have its last modified updated)
     * @param phase
     *            The phase to update
     * @param state
     *            The new state type for the phase
     * @param description
     *            The description of the new state
     * @return The state that the phase has been updated with
     */
    public static SIF3PhaseState changePhaseState(SIF3Job job, SIF3Phase phase, PhaseState state,
            String description)
    {
        if (!job.getPhases().contains(phase))
        {
            String msg = "Phase with name " + phase.getName()
                    + " Does not belong to job with refid " + job.getId() + ".";

            if (job.getPhases().size() == 0)
            {
                throw new IllegalArgumentException(msg);
            }

            List<String> names = new ArrayList<String>();
            for (int i = 0; i < job.getPhases().size(); i++)
            {
                names.add(job.getPhases().get(i).getName());
            }
            throw new IllegalArgumentException(
                    msg + " Known phases are: " + StringUtils.join(names, ", "));
        }

        SIF3PhaseState current = null;
        try
        {
            current = phase.getStates().get(phase.getStates().size() - 1);
        }
        catch (Exception e)
        {
            // Do nothing
        }

        if (current == null || !current.getType().equals(state))
        {
            current = new SIF3PhaseState();
            current.setType(state);
            current.setCreated(now());
            List<SIF3PhaseState> states = phase.getStates();
            states.add(current);
            phase.setStates(states);
        }
        current.setDescription(description);

        touch(job, phase);

        return current;
    }

    /**
     * Convenience method to update the last modified of a job
     * 
     * @param job
     *            The job to touch
     */
    public static void touch(SIF3Job job)
    {
        job.setLastModified();
    }

    /**
     * Convenience method to update the last modified of a job and a phase
     * 
     * @param job
     *            The job to touch
     * @param phase
     *            The phase to touch
     */
    public static void touch(SIF3Job job, SIF3Phase phase)
    {
        Date time = now();
        getLastPhaseState(phase).setLastModified(time);
        job.setLastModified(time);
    }

    public static void touchAll(SIF3Job job)
    {
        Date time = now();
        for (SIF3Phase phase : job.getPhases())
        {
            getLastPhaseState(phase).setLastModified(time);
        }
        job.setLastModified(time);
    }

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
        item.setCreated(convertDate(job.getCreated()));
        item.setLastModified(convertDate(job.getLastModified()));
        try
        {
            if (job.getTimeout() != 0)
            {
                item.setTimeout(DatatypeFactory.newInstance().newDuration(job.getTimeout()));
            }
        }
        catch (DatatypeConfigurationException e)
        {
            throw new IllegalArgumentException(
                    "Could not convert the timeout to an XML Duration object.", e);
        }
        PhaseCollectionType phases = objectFactory.createPhaseCollectionType();
        for (SIF3Phase p : job.getPhases())
        {
            phases.getPhase().add(marshal(p));
        }
        item.setPhases(phases);
        return item;
    }

    public static PhaseType marshal(SIF3Phase p)
    {
        if (p == null)
        {
            return null;
        }
        PhaseType phase = objectFactory.createPhaseType();
        phase.setName(p.getName());
        phase.setRequired(p.isRequired());
        phase.setRights(marshal(p.getRights()));
        phase.setStatesRights(marshal(p.getStatesRights()));
        return phase;
    }

    public static StateType marshal(SIF3PhaseState s)
    {
        if (s == null)
        {
            return null;
        }
        StateType state = objectFactory.createStateType();
        state.setId(s.getId());
        state.setDescription(s.getDescription());
        state.setCreated(convertDate(s.getCreated()));
        state.setLastModified(convertDate(s.getLastModified()));
        state.setType(s.getType() == null ? null : PhaseStateType.valueOf(s.getType().name()));
        return state;
    }

    public static RightsType marshal(ServiceRights r)
    {
        if (r == null)
        {
            return null;
        }
        RightsType rights = objectFactory.createRightsType();
        for (AccessRight accessRight : r.getRights().keySet())
        {
            RightType right = objectFactory.createRightType();
            right.setType(accessRight.name());
            right.setValue(RightValueType.valueOf(r.getRights().get(accessRight).name()));
            rights.getRight().add(right);
        }
        return rights;
    }

    public static List<SIF3Job> unmarshal(JobCollectionType items)
    {
        ArrayList<SIF3Job> jobs = new ArrayList<SIF3Job>();
        if (items != null)
        {
            for (JobType item : items.getJob())
            {
                jobs.add(unmarshal(item));
            }
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
        job.setCreated(convertDate(item.getCreated()));
        job.setLastModified(convertDate(item.getLastModified()));
        job.setTimeout(item.getTimeout() == null ? 0 : item.getTimeout().getTimeInMillis(now()));
        ArrayList<SIF3Phase> phases = new ArrayList<SIF3Phase>();
        for (PhaseType p : item.getPhases().getPhase())
        {
            phases.add(unmarshal(p));
        }
        job.setPhases(phases);
        return job;
    }

    public static SIF3Phase unmarshal(PhaseType p)
    {
        if (p == null)
        {
            return null;
        }
        SIF3Phase phase = new SIF3Phase();
        phase.setName(p.getName());
        phase.setRequired(p.isRequired());
        phase.setRights(unmarshal(p.getRights()));
        phase.setStatesRights(unmarshal(p.getStatesRights()));
        return phase;
    }

    public static SIF3PhaseState unmarshal(StateType s)
    {
        if (s == null)
        {
            return null;
        }
        SIF3PhaseState state = new SIF3PhaseState();
        state.setId(s.getId());
        state.setDescription(s.getDescription());
        state.setCreated(convertDate(s.getCreated()));
        state.setLastModified(convertDate(s.getLastModified()));
        state.setType(s.getType() == null ? null : PhaseState.valueOf(s.getType().name()));
        return state;
    }

    public static ServiceRights unmarshal(RightsType r)
    {
        if (r == null)
        {
            return null;
        }
        ServiceRights rights = new ServiceRights();
        for (RightType rightType : r.getRight())
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
        newJob.getPhases().add(ServiceUtils.getPhase(job, phaseName));
        return newJob;
    }

    private static Calendar convertDate(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        time.setTime(date);
        return time;
    }

    private static Date convertDate(Calendar calendar)
    {
        if (calendar == null)
        {
            return null;
        }
        return calendar.getTime();
    }

    private static Date now()
    {
        return Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
    }
}
