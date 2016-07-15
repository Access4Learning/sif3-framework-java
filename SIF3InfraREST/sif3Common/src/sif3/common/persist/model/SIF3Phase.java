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
import java.util.List;

import javax.persistence.Transient;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.model.ServiceRights;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.utils.DateUtils;

/**
 * A SIF phase object, representing the current state of a phase in a functional service.
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public class SIF3Phase implements Serializable
{
    private static final long    serialVersionUID = 5707867542752374831L;
    private long                 id;
    private String               name;
    private List<SIF3PhaseState> states;
    private boolean              required;
    private ServiceRights        rights;
    private ServiceRights        statesRights;

    /**
     * Basic constructor that sets logical default values
     */
    public SIF3Phase()
    {
        setName("undefined");
        setStates(new ArrayList<SIF3PhaseState>());
        setRights(new ServiceRights());
        setStatesRights(new ServiceRights());
    }

    /**
     * Constructor that takes a phase name.
     * 
     * @param name
     *            Name of the Phase.
     */
    public SIF3Phase(String name)
    {
        this();
        if (StringUtils.isEmpty(name) || name.contains(" "))
        {
            throw new IllegalArgumentException(
                    "Phases must have a name, which cannot contain any spaces");
        }
        setName(name);
    }

    /**
     * Constructor that takes a phase name and if the phase is required or not.
     * 
     * @param name
     *            Name of the Phase.
     * @param required
     *            If the Phase is required or not. Default is false.
     */
    public SIF3Phase(String name, boolean required)
    {
        this(name);
        setRequired(required);
    }

    /**
     * Constructor that takes a phase name, if it's required, a set of rights information, and
     * initial state information.
     * 
     * @param phaseName
     *            The name of the phase
     * @param required
     *            If this phase is required to complete for the job to complete
     * @param rights
     *            Access rights for the phase
     * @param stateRights
     *            Access rights for the states on this phase
     * @param phaseState
     *            The initial state of the phase
     * @param stateDescription
     *            The initial state description for the phase
     */
    public SIF3Phase(String phaseName, boolean required, ServiceRights rights,
            ServiceRights stateRights, PhaseState phaseState, String stateDescription)
    {
        this(phaseName, required);
        if (rights != null)
        {
            setRights(rights);
        }
        if (stateRights != null)
        {
            setStatesRights(stateRights);
        }
        updateState(phaseState, stateDescription);
    }

    /**
     * Changes the state of the phase
     * 
     * @param type
     *            The state to set
     * @param description
     *            The optional description to set
     * @return The current state of the phase
     */
    public SIF3PhaseState updateState(PhaseState type, String description)
    {
        SIF3PhaseState current = getCurrentState();
        if (current != null && current.getType() == type)
        {
            current.setLastModified();
            current.setDescription(description);
            return current;
        }

        SIF3PhaseState newState = new SIF3PhaseState(type, description);
        getStates().add(newState);
        return newState;
    }

    /**
     * Get the most recent/current/last state of this phase.
     * 
     * @return The last state in the list of states for this phase.
     */
    @Transient
    public SIF3PhaseState getCurrentState()
    {
        return getStates() == null ? null : getStates().get(getStates().size() - 1);
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
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

    public List<SIF3PhaseState> getStates()
    {
        return states;
    }

    public void setStates(List<SIF3PhaseState> states)
    {
        this.states = states;
    }

    public boolean addState(SIF3PhaseState state)
    {
        return states.add(state);
    }

    public boolean isRequired()
    {
        return required;
    }

    public void setRequired(boolean required)
    {
        this.required = required;
    }

    public ServiceRights getRights()
    {
        return rights;
    }

    public void setRights(ServiceRights rights)
    {
        this.rights = rights;
    }

    public ServiceRights getStatesRights()
    {
        return statesRights;
    }

    public void setStatesRights(ServiceRights statesRights)
    {
        this.statesRights = statesRights;
    }

    /**
     * Gets the last state object from the named phase on the given job
     * 
     * @return The last state object for the given phase, or null if there isn't one.
     * @throws IllegalArgumentException
     *             If the phase is null
     */
    public SIF3PhaseState getLastPhaseState()
    {
        if (getStates() == null)
        {
            return null;
        }

        if (getStates() == null || getStates().isEmpty())
        {
            return null;
        }
        return getStates().get(getStates().size() - 1);
    }

    /**
     * Change the state of a phase on a given job
     * 
     * @param state
     *            The new state type for the phase
     * @param description
     *            The description of the new state
     * @return The state that the phase has been updated with
     */
    public SIF3PhaseState updatePhaseState(PhaseState state, String description)
    {
        if (getStates() == null)
        {
            setStates(new ArrayList<SIF3PhaseState>());
        }

        SIF3PhaseState current = null;
        try
        {
            current = getStates().get(getStates().size() - 1);
        }
        catch (Exception e)
        {
            // Do nothing
        }

        if (current == null || !current.getType().equals(state))
        {
            current = new SIF3PhaseState();
            current.setType(state);
            current.setCreated(DateUtils.now());
            addState(current);
        }
        current.setDescription(description);
        current.setLastModified();

        return current;
    }

    /**
     * Check that the given phase has sufficient rights for the given AccessRight and AccessType
     * instances.
     * 
     * @param right
     *            The type of access right desired (CREATE, QUERY, DELETE, etc.)
     * @param type
     *            The expected value of the AccessRight (APPROOVED, REJECTED, etc.)
     * @return true if the phase supports the given access right, false otherwise.
     */
    public boolean checkPhaseACL(AccessRight right, AccessType type)
    {
        return getRights().hasRight(right, type);
    }
}
