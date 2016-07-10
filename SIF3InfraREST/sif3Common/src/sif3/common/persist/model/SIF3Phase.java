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

/**
 * The following Phase elements/properties are mandatory according to the SIF specification:
 * /phase/name /phase/state /phase/required /phase/rights
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
}
