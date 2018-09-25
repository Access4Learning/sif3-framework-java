/*
 * BaseProviderJobManager.java
 * Created: 11 Jun 2018
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

package sif3.infra.common.env.mgr;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants.JobState;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.exception.PersistenceException;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.persist.model.SIF3JobEvent;
import sif3.common.persist.service.JobService;
import sif3.common.utils.UUIDGenerator;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.env.types.ExtendedJobInfo;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.interfaces.ProviderJobManager;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobStateType;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.PhaseStateType;
import sif3.infra.common.model.PhaseType;
import sif3.infra.common.model.StateCollectionType;
import sif3.infra.common.model.StateType;

/**
 * @author Joerg Huber
 *
 */
public abstract class BaseProviderJobManager extends BaseJobManager implements ProviderJobManager
{
    protected BaseProviderJobManager(EnvironmentInfo environmentInfo)
    {
        super(environmentInfo);
    }

    /*-----------------------*/
    /*-- Interface Methods --*/
    /*-----------------------*/
   
    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.ProviderJobManager#updateJobState(java.lang.String, sif3.common.CommonConstants.JobState)
     */
    @Override
    public void updateJobState(String jobID, JobState newState) throws PersistenceException
    {
        if (newState != null)
        {
            ExtendedJobInfo jobInfo = getJobInfo(jobID);
            if ((jobInfo != null) && (jobInfo.isXMLValid())) // we are all good to go
            {
                jobInfo.getDBJob().setCurrentState(newState.name());
                jobInfo.getXMLJob().setState(JobStateType.valueOf(newState.name()));
                updateJob(jobInfo, false);
            }
            else if (jobInfo.isXMLValid()) // we don't have a valid XML. Cannot update state
            {
                throw new PersistenceException("Failed to retrieve Job "+jobID+" from underlying Data Store. The Job XML is invalid.");
            }
        }
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.ProviderJobManager#updatePhaseState(java.lang.String, java.lang.String, sif3.common.CommonConstants.PhaseState)
     */
    @Override
    public void updatePhaseState(String jobID, String phaseName, PhaseState newState) throws PersistenceException
    {
        if ((phaseName != null) && (newState != null))
        {
            StateType newPhaseState = new StateType();
            newPhaseState.setType(PhaseStateType.valueOf(newState.name()));
            
            ExtendedJobInfo jobInfo = getJobInfo(jobID);
            if ((jobInfo != null) && (jobInfo.isXMLValid())) // we are all good to go
            {
                PhaseType phase = getPhase(jobInfo.getXMLJob(), phaseName);
                if (phase != null) // add the state
                {
                    addStateToPhase(phase, newPhaseState);
                    updateJob(jobInfo, false);
                }
            }
            else if (jobInfo.isXMLValid()) // we don't have a valid XML. Cannot update state
            {
                throw new PersistenceException("Failed to retrieve Job "+jobID+" from underlying Data Store. The Job XML is invalid.");
            }
        }
    }

    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.ProviderJobManager#updateJobStateAndPhaseState(java.lang.String, sif3.common.CommonConstants.JobState, java.lang.String, sif3.common.CommonConstants.PhaseState)
     */
    @Override
    public void updateJobStateAndPhaseState(String jobID, JobState newJobState, String phaseName, PhaseState newPhaseState) throws PersistenceException
    {
        ExtendedJobInfo jobInfo = getJobInfo(jobID);
        if ((jobInfo != null) && (jobInfo.isXMLValid())) // we are all good to go
        {
            boolean valuesUpdated = false;
            if (newJobState != null)
            {
                jobInfo.getDBJob().setCurrentState(newJobState.name());
                jobInfo.getXMLJob().setState(JobStateType.valueOf(newJobState.name()));
                valuesUpdated = true;
            }
            if ((phaseName != null) && (newPhaseState != null))
            {
                StateType phaseState = new StateType();
                phaseState.setType(PhaseStateType.valueOf(newPhaseState.name()));
                PhaseType phase = getPhase(jobInfo.getXMLJob(), phaseName);
                if (phase != null) // add the state
                {
                    addStateToPhase(phase, phaseState);
                    valuesUpdated = true;
                }
            }
            
            if (valuesUpdated)
            {
                updateJob(jobInfo, false);
            }
        }
        else if (jobInfo.isXMLValid()) // we don't have a valid XML. Cannot update state
        {
            throw new PersistenceException("Failed to retrieve Job "+jobID+" from underlying Data Store. The Job XML is invalid.");
        }
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.ProviderJobManager#updateJob(sif3.infra.common.env.types.ExtendedJobInfo)
     */
    @Override
    public void updateJob(ExtendedJobInfo jobInfo, boolean consumerRequested) throws PersistenceException
    {
        JobService service = getService();
        if (jobInfo != null)
        {
            jobInfo.getXMLJob().setLastModified(Calendar.getInstance());
            if (!jobInfo.marshalXMLintoDBJob())
            {
                throw new PersistenceException("Failed to marshal Job into XML after an update.");
            }
            
            service.updateJob(jobInfo.getDBJob());
            
            if (getJobEnabled()) // Create UPDATE event.
            {   
                service.createJobEvent(jobInfo.getDBJob(), EventAction.UPDATE, true, consumerRequested);
            }
        }
     }
    
    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.ProviderJobManager#retrieveJobChangesSince(java.util.Date, java.lang.String, java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo)
     */
    @Override
    public JobCollectionType retrieveJobChangesSince(Date changesSince, 
                                                     String serviceName,
                                                     String fingerprint, 
                                                     SIFZone zone, 
                                                     SIFContext context, 
                                                     PagingInfo pagingInfo)
            throws PersistenceException
    {
        JobService service = getService();
        List<SIF3JobEvent> sifJobEvents = service.retrieveJobEventsSince(changesSince, serviceName, fingerprint, zone.getId(), context.getId(), pagingInfo, getAdapterType());
        
        JobCollectionType jobList = new JobCollectionType();
        for (SIF3JobEvent jobEvent : sifJobEvents)
        {
            JobType job = new JobType();
            if (jobEvent.getEventType().equals("D")) // Delete Event => Only set RefID
            {
                job.setId(jobEvent.getJobID());
            }
            else // We need to unmarshal the XML into an object.
            {
                job = unmarschalJobXML(jobEvent.getJobXML());
            }
            
            if (job != null)
            {
                jobList.getJob().add(job);
            }
        }
        
        return jobList;
    }


    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    private PhaseType getPhase(JobType job, String phaseName)
    {
        // Start iterating through the phases until it matches.
        if (job.getPhases() == null) // no phases known
        {
            return null;
        }
        
        for (PhaseType phase : job.getPhases().getPhase())
        {
            if (phase.getName().equals(phaseName)) // found the applicable phase
            {
                return phase;
            }
        }
        
        // if we get here then we haven't found the correct phase
        return null;
    }
    
    private StateType addStateToPhase(PhaseType phase, StateType newState)
    {
        if (phase != null) // add the state
        {
            StateCollectionType states = phase.getStates();
            if (states == null)
            {
                states = new StateCollectionType();
                phase.setStates(states);
            }
            
            StateType state = new StateType();
            state.setCreated(Calendar.getInstance());
            state.setLastModified(Calendar.getInstance());
            state.setId(StringUtils.isEmpty(newState.getId()) ? UUIDGenerator.getUUID() : newState.getId());
            state.setType(newState.getType());
            states.getState().add(state);
            
            return state;
        }
        
        return null;
    }

    // Only do events/changes since entries if jobs are enabled.
    private boolean getJobEnabled()
    {
        return ((ProviderEnvironment)getEnvironmentInfo()).isJobEnabled();
    }
    
}
