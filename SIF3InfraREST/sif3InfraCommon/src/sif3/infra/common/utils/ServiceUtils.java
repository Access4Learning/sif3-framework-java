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

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.infra.common.ServiceStatus.JobState;
import sif3.infra.common.ServiceStatus.PhaseState;
import sif3.infra.common.model.JobStateType;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.PhaseStateType;
import sif3.infra.common.model.PhaseType;
import sif3.infra.common.model.RightType;
import sif3.infra.common.model.StateType;

/**
 * Utility methods for functional service related activities.
 */
public class ServiceUtils
{
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
    public static PhaseType getPhase(JobType job, String phaseName)
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
            job.setPhases(objectFactory.createPhaseCollectionType());
        }
        for (PhaseType phase : job.getPhases().getPhase())
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
    public static StateType getLastPhaseState(JobType job, String phaseName)
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
    public static StateType getLastPhaseState(PhaseType phase)
    {
        if (phase == null)
        {
            throw new IllegalArgumentException("Cannot get the last state of a null phase.");
        }
        if (phase.getStates() == null)
        {
            return null;
        }
        List<StateType> states = phase.getStates().getState();
        if (states.isEmpty())
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
    public static PhaseType addPhase(JobType job, String phaseName, boolean required,
            List<RightType> rights, List<RightType> statesRights)
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
    public static PhaseType addPhase(JobType job, String phaseName, boolean required,
            List<RightType> rights, List<RightType> statesRights, PhaseState state)
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
    public static PhaseType addPhase(JobType job, String phaseName, boolean required,
            List<RightType> rights, List<RightType> statesRights, PhaseState state,
            String stateDescription)
    {
        if (job.getPhases() == null)
        {
            job.setPhases(objectFactory.createPhaseCollectionType());
        }
        PhaseType phase = objectFactory.createPhaseType();
        phase.setName(phaseName);
        phase.setRequired(required);
        phase.setStates(objectFactory.createStateCollectionType());

        phase.setRights(objectFactory.createRightsType());
        phase.getRights().getRight().addAll(rights);

        phase.setStatesRights(objectFactory.createRightsType());
        phase.getStatesRights().getRight().addAll(statesRights);

        job.getPhases().getPhase().add(phase);

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
    public static boolean checkPhaseACL(PhaseType phase, AccessRight right, AccessType type)
    {
        SIFRights rights = new SIFRights().setRights(phase.getRights());
        return rights.hasRight(right, type);
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
    public static void changeJobState(JobType job, JobState state, String description)
    {
        job.setLastModified(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
        job.setStateDescription(description);
        job.setState(JobStateType.valueOf(state.name()));
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
    public static StateType changePhaseState(JobType job, PhaseType phase, PhaseState state,
            String description)
    {
        if (!job.getPhases().getPhase().contains(phase))
        {
            throw new IllegalArgumentException("Phase with name " + phase.getName()
                    + " Does not belong to job with refid " + job.getId());
        }

        StateType current = null;
        try
        {
            current = phase.getStates().getState().get(phase.getStates().getState().size() - 1);
        }
        catch (Exception e)
        {
            // Do nothing
        }

        if (current == null || !current.getType().equals(state))
        {
            current = objectFactory.createStateType();
            current.setType(PhaseStateType.valueOf(state.name()));
            current.setCreated(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
            phase.getStates().getState().add(current);
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
    public static void touch(JobType job)
    {
        job.setLastModified(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
    }

    /**
     * Convenience method to update the last modified of a job and a phase
     * 
     * @param job
     *            The job to touch
     * @param phase
     *            The phase to touch
     */
    public static void touch(JobType job, PhaseType phase)
    {
        Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        getLastPhaseState(phase).setLastModified(time);
        job.setLastModified(time);
    }
}
