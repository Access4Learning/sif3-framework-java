/*
 * PhaseInfo.java
 * Created: 11/01/2018
 *
 * Copyright 2018 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package sif3.common.model;

import java.io.Serializable;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.model.ACL.AccessRight;
import sif3.common.model.ACL.AccessType;

/**
 * This class is a POJO for phase information of a functional service as provided by the Job. Each Job has a list of phases and each phase 
 * has a number of properties that define a phase such as name, ACLS etc. These properties can be stored in this class and utilised in 
 * higher level classes of this framework.
 * 
 * @author Joerg Huber
 *
 */
public class PhaseACL implements Serializable
{
    private static final long serialVersionUID = 8044591386912190668L;
    
    private String phaseName = null;
    private PhaseRights phaseRights = new PhaseRights();
    private StateRights stateRights = new StateRights();
    
    /**
     * Constructor
     * 
     * @param phaseName The name of a phase within a functional service.
     */
    public PhaseACL(String phaseName)
    {
    	super();
    	setPhaseName(phaseName);
    }

    public String getPhaseName()
    {
    	return this.phaseName;
    }

	public void setPhaseName(String phaseName)
    {
    	this.phaseName = phaseName;
    }

    public PhaseRights getPhaseRights()
    {
        return phaseRights;
    }

    public void setPhaseRights(PhaseRights phaseRights)
    {
        this.phaseRights = phaseRights;
    }

    public StateRights getStateRights()
    {
        return stateRights;
    }

    public void setStateRights(StateRights stateRights)
    {
        this.stateRights = stateRights;
    }
	
	/* Convenience method */
	public void setPhaseRight(AccessRight right, AccessType accessType)
	{
		getPhaseRights().addRight(right, accessType);
	}

	/* Convenience method */
    public void setStateRight(AccessRight right, AccessType accessType)
    {
        getStateRights().addRight(right, accessType);
    }

	/* Convenience method: True: Success, False: Failed (i.e. invalid right or access type */
	public boolean setStateRight(String right, String accessType)
	{
	    return setRight(right, accessType, Boolean.TRUE);
	}

    /* Convenience method: True: Success, False: Failed (i.e. invalid right or access type */
    public boolean setPhaseRight(String right, String accessType)
    {
        return setRight(right, accessType, Boolean.FALSE);
    }
    
    @Override
    public String toString()
    {
        return "PhaseInfo [phaseName=" + phaseName + ", phaseRights=" + phaseRights
                + ", stateRights=" + stateRights + "]";
    }
	
	/*--------------------*/
	/*-- Private method --*/
    /*--------------------*/
	private boolean setRight(String right, String accessType, boolean isStateRight)
    {
        if (StringUtils.notEmpty(right) && StringUtils.notEmpty(accessType))
        {
            try
            {
                if (isStateRight)
                {
                    setStateRight(AccessRight.valueOf(right.toUpperCase()), AccessType.valueOf(accessType.toUpperCase()));
                }
                else
                {
                    setPhaseRight(AccessRight.valueOf(right.toUpperCase()), AccessType.valueOf(accessType.toUpperCase()));
                }
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}
