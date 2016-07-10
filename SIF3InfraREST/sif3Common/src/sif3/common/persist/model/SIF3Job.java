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

import org.apache.log4j.Logger;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants.JobState;
import sif3.common.CommonConstants.PhaseState;

/**
 * The following Job elements/properties are mandatory according to the SIF specification: /job[@id]
 * /job/name
 */
public class SIF3Job implements Serializable
{
    protected final Logger         logger           = Logger.getLogger(getClass());
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
            this.phases.put(phase.getName(), phase);
        }
    }
}