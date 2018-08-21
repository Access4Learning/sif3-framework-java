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

//    /* (non-Javadoc)
//     * @see sif3.infra.common.interfaces.ProviderJobManager#hasConsumerPhaseStatusRightForJob(java.lang.String, java.lang.String, java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext, java.lang.String, sif3.common.model.ACL.AccessRight, sif3.common.model.ACL.AccessType)
//     */
//    @Override
//    public boolean hasConsumerPhaseStatusRightForJob(String jobID, String serviceName, String phaseName, SIFZone zone, SIFContext context, String fingerprint, AccessRight right, AccessType accessType) throws ResourceNotFoundException
//    {
//        try
//        {
//            SIF3Job sif3Job = getJobAndCheckConsumer(jobID, serviceName, zone, context, fingerprint);
//           
//            // We are all good up to here. Let's get the actual Job object and check for phases and rights.
//            JobType job = unmarschalJobXML(sif3Job.getJobXML());
//            if (job == null) // could not unmarshal. We do not know anything about the job. Return false! Error should be logged
//            {
//                return false;
//            }
//            
//            PhaseType phase = getPhase(job, phaseName);
//            if (phase == null) // no phase found => return false
//            {
//                return false;
//            }
//            
//            return hasRight( phase.getStatesRights(), right, accessType);
//        }
//        catch (PersistenceException | IllegalArgumentException ex)
//        {
//            logger.error("Failed to determine Phase Status Right for Service = '"+serviceName+"' and JobID = '"+jobID+"': "+ex.getMessage(), ex);
//            return false;
//        }
//    }

//    /* (non-Javadoc)
//     * @see sif3.infra.common.interfaces.ProviderJobManager#hasConsumerPhaseRightForJob(java.lang.String, java.lang.String, java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext, java.lang.String, sif3.common.model.ACL.AccessRight, sif3.common.model.ACL.AccessType)
//     */
//    @Override
//    public boolean hasConsumerPhaseRightForJob(String jobID, String serviceName, String phaseName, SIFZone zone, SIFContext context, String fingerprint, AccessRight right, AccessType accessType) throws ResourceNotFoundException
//    {
//        try
//        {
//            SIF3Job sif3Job = getJobAndCheckConsumer(jobID, serviceName, zone, context, fingerprint);
//           
//            // We are all good up to here. Let's get the actual Job object and check for phases and rights.
//            JobType job = unmarschalJobXML(sif3Job.getJobXML());
//            if (job == null) // could not unmarshal. We do not know anything about the job. Return false! Error should be logged
//            {
//                return false;
//            }
//            
//            PhaseType phase = getPhase(job, phaseName);
//            if (phase == null) // no phase found => return false
//            {
//                return false;
//            }
//            
//            return hasRight(phase.getRights(), right, accessType);
//        }
//        catch (PersistenceException | IllegalArgumentException ex)
//        {
//            logger.error("Failed to determine Phase Right for Service = '"+serviceName+"' and JobID = '"+jobID+"': "+ex.getMessage(), ex);
//            return false;
//        }
//    }
    
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
                updateJob(jobInfo);
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
                    updateJob(jobInfo);
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
                updateJob(jobInfo);
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
    public void updateJob(ExtendedJobInfo jobInfo) throws PersistenceException
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
                service.createJobEvent(jobInfo.getDBJob(), EventAction.UPDATE, true);
            }
        }
     }


//    /* (non-Javadoc)
//     * @see sif3.infra.common.interfaces.ProviderJobManager#updatePhaseStateByConsumer(java.lang.String, java.lang.String, sif3.common.CommonConstants.PhaseState)
//     */
//    @Override
//    public StateType addPhaseStateByConsumer(String jobID, String phaseName, StateType newState) throws PersistenceException
//    {
//        JobService service = getService();
//        SIF3Job sif3Job = service.getJobByUUID(jobID, getAdapterType());
//        if (sif3Job != null)
//        {
//            // Get the actual Job Object from XML and search for appropriate phase
//            JobType job = unmarschalJobXML(sif3Job.getJobXML());
//            if (job != null) // all good so we can update.
//            {
//                PhaseType phase = getPhase(job, phaseName);
//                if (phase != null) // add the state
//                {
//                    StateType state = addStateToPhase(phase, newState);
//                    job.setLastModified(Calendar.getInstance());
//                    try
//                    {
//                        sif3Job.setJobXML(createJobXML(job));
//                    }
//                    catch (MarshalException ex) // not good. We must rollback. This is done by simply throw PersistenceException.
//                    {
//                        throw new PersistenceException("Failed to marshal Job into XML after phase state update. Attempted to update state of phase "+phaseName+" to is "+newState+".", ex);
//                    }
//                    
//                    service.updateJob(sif3Job);
//                    
//                    if (getEventSuported()) // Create UPDATE event.
//                    {   
//                        service.createJobEvent(sif3Job, EventAction.UPDATE, true);
//                    }
//
//                    return state;
//                }
//            }
//        }
//        
//        return null;
//    }
    
//    /* (non-Javadoc)
//     * @see sif3.infra.common.interfaces.ProviderJobManager#getStatesOfPhase(java.lang.String, java.lang.String)
//     */
//    @Override
//    public StateCollectionType getStatesOfPhase(String jobID, String phaseName) throws PersistenceException
//    {
//        JobService service = getService();
//        SIF3Job sif3Job = service.getJobByUUID(jobID, getAdapterType());
//        if (sif3Job != null)
//        {
//            // Get the actual Job Object from XML and serch for appropriate phase
//            JobType job = unmarschalJobXML(sif3Job.getJobXML());
//            if (job != null) // all good so we can update.
//            {
//                PhaseType phase = getPhase(job, phaseName);
//                if (phase != null) // add the state
//                {
//                    StateCollectionType states = phase.getStates();
//                    if (states == null)
//                    {
//                        states = new StateCollectionType();
//                    }
//                    
//                    return states;
//                }
//            }
//        }
//        
//        // If we get here then we haven't found an appropiate job of phase.
//        return new StateCollectionType();
//    }
    
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
//    private SIF3Job getJobAndCheckConsumer(String jobID, String serviceName, SIFZone zone, SIFContext context, String fingerprint) throws ResourceNotFoundException, PersistenceException, IllegalArgumentException
//    {
//        SIF3Job sif3Job = getJob(jobID);
//        if (sif3Job == null) // not found
//        {
//            throw new ResourceNotFoundException("Job does not exist.", "No Job with ID = "+jobID+" found.", "Provider");
//        }
//        
//        // Let'scheck if the fingerprint is given and if so it must match
//        boolean allGood = true;
//        if (StringUtils.notEmpty(fingerprint))
//        {
//            allGood = allGood && fingerprint.equals(sif3Job.getFingerprint());
//        }
//        
//        if (allGood) // lets check the rest: Is it the correct consumer in the right zone & context?
//        {
//            allGood = allGood && (serviceName.equals(sif3Job.getServiceName()) && zone.getId().equals(sif3Job.getZoneID()) && context.getId().equals(sif3Job.getContextID()));
//        }
//        
//        if (!allGood) // Job not found for the given parameters
//        {
//            throw new ResourceNotFoundException("Job does not exist.", "No Job with ID = "+jobID+" found for service = "+serviceName+" in zone = "+zone.getId()+" and context = "+context.getId()+". Fingerprint is "+fingerprint+".", "Provider");                
//        }
//        
//        return sif3Job;
//    }
    
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

//    private boolean hasRight(RightsType rights, AccessRight right, AccessType accessType)
//    {
//        if (rights == null)
//        {
//            return false;
//        }
//        
//        for (RightType rightType : rights.getRight())
//        {
//            if (rightType.getType().equals(right.name()) && rightType.getValue().equals(accessType.name())) // Found match!
//            {
//                return true;
//            }
//        }
//        
//        // if we get here then we haven't found the correct right
//        return false;
//    }
    
    // Only do events/changes since entries if jobs are enabled.
    private boolean getJobEnabled()
    {
        return ((ProviderEnvironment)getEnvironmentInfo()).isJobEnabled();
    }
    
}
