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

package sif3.common.persist.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants.JobState;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.model.ServiceRights;
import sif3.common.utils.DateUtils;

/**
 * A SIF3 job object representing the state of a functional service.
 * 
 * Must at least have a name defined.
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public class SIF3Job implements Serializable
{
    private static final long      serialVersionUID = 6414285847037508670L;
    private long                   jobID;
    private String                 id;
    private String                 name;
    private String                 description;
    private JobState               state;
    private String                 stateDescription;
    private Date                   created;
    private Date                   lastModified;
    private long                   timeout;
    private Map<String, SIF3Phase> phases;

    public SIF3Job()
    {
        setName("undefined");
        setPhases(new ArrayList<SIF3Phase>());
    }

    public SIF3Job(String name, String description)
    {
        this();

        if (StringUtils.isEmpty(name))
        {
            throw new IllegalArgumentException("Job should be created with a name");
        }
        setName(name);

        if (!StringUtils.isEmpty(description))
        {
            setDescription(description);
        }
    }

    /**
     * Changes the state of the job
     * 
     * @param type
     *            The type to change the current status to
     * @param description
     *            An optional description of the change
     */
    public void updateState(JobState type, String description)
    {
        setLastModified();
        setStateDescription(description);
        setState(type);
    }

    /**
     * Adds a phase to the collection of phases
     * 
     * @param phase
     *            Phase to add
     */
    public void addPhase(SIF3Phase phase)
    {
        phases.put(phase.getName(), phase);
    }

    /**
     * Sets the identified phase's state with optional description.
     * 
     * @param phaseName
     *            Name of phase to update
     * @param state
     *            The state to set
     * @param stateDescription
     *            Optional description
     */
    public void updatePhaseState(String phaseName, PhaseState state, String stateDescription)
    {
        SIF3PhaseState s = phases.get(phaseName).updateState(state, stateDescription);
        setLastModified(s.getLastModified());
    }

    public long getJobID()
    {
        return jobID;
    }

    public void setJobID(long jobID)
    {
        this.jobID = jobID;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public JobState getState()
    {
        return state;
    }

    public void setState(JobState state)
    {
        this.state = state;
    }

    public String getStateDescription()
    {
        return stateDescription;
    }

    public void setStateDescription(String stateDescription)
    {
        this.stateDescription = stateDescription;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
    }

    public void setLastModified()
    {
        this.lastModified = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
    }

    public long getTimeout()
    {
        return timeout;
    }

    /**
     * 
     * @param timeout
     *            in milliseconds
     */
    public void setTimeout(long timeout)
    {
        this.timeout = timeout;
    }

    public List<SIF3Phase> getPhases()
    {
        return new ArrayList<SIF3Phase>(phases.values());
    }

    public void setPhases(List<SIF3Phase> phases)
    {
        this.phases = new HashMap<String, SIF3Phase>();
        for (SIF3Phase phase : phases)
        {
            addPhase(phase);
        }
    }

    /**
     * Adds a phase to a job instance
     * 
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
    public SIF3Phase addPhase(String phaseName, boolean required, ServiceRights rights,
            ServiceRights statesRights)
    {
        return addPhase(phaseName, required, rights, statesRights, PhaseState.NOTSTARTED, null);
    }

    /**
     * Adds a phase to a job instance
     * 
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
    public SIF3Phase addPhase(String phaseName, boolean required, ServiceRights rights,
            ServiceRights statesRights, PhaseState state)
    {
        return addPhase(phaseName, required, rights, statesRights, state, null);
    }

    /**
     * Adds a phase to a job instance
     * 
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
    public SIF3Phase addPhase(String phaseName, boolean required, ServiceRights rights,
            ServiceRights statesRights, PhaseState state, String stateDescription)
    {
        if (getPhases() == null)
        {
            setPhases(new ArrayList<SIF3Phase>());
        }

        SIF3Phase phase = new SIF3Phase();
        phase.setName(phaseName);
        phase.setRequired(required);
        phase.setStates(new ArrayList<SIF3PhaseState>());
        phase.setRights(rights);
        phase.setStatesRights(statesRights);
        phase.updatePhaseState(state, stateDescription);

        addPhase(phase);

        return phase;
    }

    /**
     * Get the named phase from the job instance.
     * 
     * @param phaseName
     *            The name of the phase
     * @return The named phase from the job object
     * @throws IllegalArgumentException
     *             if the phase cannot be found
     */
    public SIF3Phase getPhase(String phaseName)
    {
        if (StringUtils.isEmpty(phaseName))
        {
            throw new IllegalArgumentException(
                    "Cannot get empty phase from job (" + getId() + ").");
        }

        if (getPhases() == null)
        {
            setPhases(new ArrayList<SIF3Phase>());
        }

        if (phases.containsKey(phaseName))
        {
            return phases.get(phaseName);
        }

        List<String> names = new ArrayList<String>(phases.keySet());
        throw new IllegalArgumentException("Could not find phase " + phaseName + " in " + getName()
                + " job (" + getId() + "). Known phases are: " + StringUtils.join(names, ", "));
    }

    /**
     * Gets the last state object from the named phase on the given job
     * 
     * @param phaseName
     *            The target phase name
     * @return The last state object for the named phase, or null if there isn't one.
     * @throws IllegalArgumentException
     *             if the phase cannot be found or other problem is encountered
     */
    public SIF3PhaseState getLastPhaseState(String phaseName)
    {
        return this.getPhase(phaseName).getLastPhaseState();
    }

    /**
     * Change the state of a given job, updating the last modified value etc.
     * 
     * @param state
     *            The new status of the job
     * @param description
     *            The description of the state change
     */
    public void updateJobState(JobState state, String description)
    {
        setLastModified(DateUtils.now());
        setStateDescription(description);
        setState(JobState.valueOf(state.name()));
    }
}