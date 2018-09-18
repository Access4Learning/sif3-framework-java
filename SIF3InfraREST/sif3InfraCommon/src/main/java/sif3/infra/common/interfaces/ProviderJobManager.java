/*
 * ProviderJobManager.java
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

package sif3.infra.common.interfaces;

import java.util.Date;

import sif3.common.CommonConstants.JobState;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.exception.PersistenceException;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.infra.common.env.types.ExtendedJobInfo;
import sif3.infra.common.model.JobCollectionType;

/**
 * This interface defines additional methods to the standard JobManager interface. These methods are applicable for the Provider only.
 * 
 * @author Joerg Huber
 *
 */
public interface ProviderJobManager extends JobManager
{
    /**
     * This method updates the overall Job state for the given job. It is intended to be used by the provider only. If events 
     * are turned on it will create the appropriate event.
     * 
     * @param jobID The ID of the Job to be updated. If Job does not exist then no action is taken.
     * @param newState The new state of the job. If null then no action is taken.
     * 
     * @throws PersistenceException Job can not be accessed or updated due to a DB access issue.
     */
    public void updateJobState(String jobID, JobState newState) throws PersistenceException;

    /**
     * This method updates the state for the given phase of the given job. If the job or phase doesn't exist then no action 
     * is taken. If events are turned on it will create the appropriate event.
     * 
     * @param jobID The ID of the Job to be updated. If Job does not exist then no action is taken.
     * @param phaseName The phase for which the state shall be updated. If phase doesn't exist then no action is taken.
     * @param newState The new state of the job. If null then no action is taken.
     * 
     * @throws PersistenceException Job can not be accessed or updated due to a DB access issue.
     */
    public void updatePhaseState(String jobID, String phaseName, PhaseState newState) throws PersistenceException;

    /**
     * This method updates the overall Job status as well as the status of the given phase. This is a convenience method, so that
     * a provider can update a phase state which in turn may also need an update to the overall job state. Using this method
     * is more efficient then using two separate methods to update job and a phase state separately. It also reduces the number
     * of events that will be created in case events are turned on. This method will only create on final event.
     * 
     * @param jobID The ID of the Job to be updated. If Job does not exist then no action is taken.
     * @param newJobState The new state of the job. If null then the state will not be updated.
     * @param phaseName The phase for which the state shall be updated. If phase doesn't exist then no action is taken.
     * @param newPhaseState The new state of the given phase. If null then the state will not be updated.
     * 
     * @throws PersistenceException Job can not be accessed or updated due to a DB access issue.
     */
    public void updateJobStateAndPhaseState(String jobID, JobState newJobState, String phaseName, PhaseState newPhaseState) throws PersistenceException;

    /**
     * This method persist the jobInfo.dbJob to the data store. If events are enabled it will also add an appropriate event to the event
     * table. Internally this method will marshal the latest jobInfo.xmlJob into the appropriate dbJob.jobXML property before it is
     * persisted. Other properties like lastUpdated will be set with an appropriate timestamp as well. The jobInfo parameter will hold
     * all the latest values after this call.
     * 
     * @param jobInfo The job data that needs to be persisted. If null then no action is taken.
     * @param consumerRequested Indicating if the update request is caused by consumer.
     * 
     * @throws PersistenceException Job can not be updated due to a DB access issue.
     */
    public void updateJob(ExtendedJobInfo jobInfo, boolean consumerRequested) throws PersistenceException;
    
    /**
     * This method returns all Jobs that have had any changes applied to them on or after the 'changesSince' date. The number 
     * of jobs returned in the collection is indicated by the 'pagingInfo' parameter. Note because this shall behave as the
     * "ChangesSince" as specified by the SIF 3.2.x specification jobs that have been removed are returned as and "empty" job
     * with just the 'id' property set.
     * 
     * @param changesSince Must not be null.
     * @param serviceName Can be null. It identifies if functional service name as defined by the service URL.
     * @param fingerprint Can be null. It identifies if jobs for a specific adapter shall be returned or any job.
     * @param zone The zone for which the jobs shall be returned.
     * @param context The context for which the jobs shall be returned.
     * @param pagingInfo Limiting the returned job list to the given 'window'.
     * 
     * @return A collection of job as described. Is never null but can be empty.
     * 
     * @throws IllegalArgumentException If the changesSince or adapterType is null.
     * @throws PersistenceException Could not access underlying data store.
     */
    public JobCollectionType retrieveJobChangesSince(Date changesSince, String serviceName, String fingerprint, SIFZone zone, SIFContext context, PagingInfo pagingInfo) throws PersistenceException;
    
}
