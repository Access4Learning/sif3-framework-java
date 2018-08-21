/*
 * JobService.java
 * Created: 21/12/2017
 *
 * Copyright 2017 Systemic Pty Ltd
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
package sif3.common.persist.service;

import java.util.Date;
import java.util.List;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.model.PagingInfo;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.dao.BaseDAO;
import sif3.common.persist.dao.JobDAO;
import sif3.common.persist.dao.JobTemplateDAO;
import sif3.common.persist.model.JobTemplate;
import sif3.common.persist.model.SIF3Job;
import sif3.common.persist.model.SIF3JobEvent;

/**
 * @author Joerg Huber
 *
 */
public class JobService extends DBService
{
    private JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
    private JobDAO jobDAO = new JobDAO();

    @Override
    public BaseDAO getDAO()
    {
        return jobTemplateDAO;
    }

    /**
     * This method attempts to retrieve the job template by the primary key. If there is no row in the SIF3_JOB_TEMPLATE for the
     * given templateID then null is returned.
     * 
     * @param jobTemplateID template ID for which the job template information from the SIF3_JOB_TEMPLATE table shall be returned.
     * 
     * @return See desc.
     * 
     * @throws PersistenceException Could not access underlying data store.
     */
    public JobTemplate getJobTemplate(long jobTemplateID) throws IllegalArgumentException, PersistenceException
    {
        JobTemplate row = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            row = jobTemplateDAO.getJobTemplate(tx, jobTemplateID);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, ex.getMessage(), false, false);
        }
        return row;
    }
    
    /**
     * This method attempts to retrieve the job template by its URL Name and adapter type. If there is no row in the SIF3_JOB_TEMPLATE for the
     * given parameters then null is returned.
     * 
     * @param jobURLName URL Name for which the job template information from the SIF3_JOB_TEMPLATE table shall be returned.
     * @param adapterType Adapter Type for which the job template information from the SIF3_JOB_TEMPLATE table shall be returned.
     * 
     * @return See desc.
     * 
     * @throws IllegalArgumentException jobURLName is null or empty and/or adapterType is null
     * @throws PersistenceException Could not access underlying data store.
     */
    public JobTemplate getJobTemplateForAdapter(String jobURLName, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        JobTemplate row = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            row = jobTemplateDAO.getJobTemplateForAdapter(tx, jobURLName, adapterType);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, ex.getMessage(), false, false);
        }
        return row;
    }
    
    public SIF3Job getJob(long internalID) throws IllegalArgumentException, PersistenceException
    {
        SIF3Job row = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            row = jobDAO.getJob(tx, internalID);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, ex.getMessage(), false, false);
        }
        return row;
    }
    
    public SIF3Job getJobByUUID(String uuid, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        SIF3Job row = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            row = jobDAO.getJobByUUID(tx, uuid, adapterType);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, ex.getMessage(), false, false);
        }
        return row;
    }
    
    public List<SIF3Job> retrieveJobs(String serviceName, String fingerprint, String zoneID, String contextID, PagingInfo pagingInfo, AdapterType adapterType) throws PersistenceException
    {
        List<SIF3Job> jobs = null;
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            jobs = jobDAO.retrieveJobs(tx, serviceName, fingerprint, zoneID, contextID, pagingInfo, adapterType);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, ex.getMessage(), false, false);
        }
        
        return jobs;
    }

    /**
     * This method will add a Job to the SIF3_JOB table. Please refer to the appropriate method in the JobDAO for details on 
     * pre- and post-conditions.
     * 
     * @param job The job to be added to the SIF3_JOB table.
     * 
     * @return The newly created job. This has now the internalID populated.
     * 
     * @throws IllegalArgumentException If the tx is null or any of the rules listed above are not met.
     * @throws PersistenceException Could not access underlying data store.
     */
    public SIF3Job createJob(SIF3Job job) throws IllegalArgumentException, PersistenceException
    {
        SIF3Job row = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            row = jobDAO.createJob(tx, job);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, ex.getMessage(), false, false);
        }
        return row;
    }

    /**
     * Updates a job with the given values. Note that the internalID must be set and have the value of an existing object. The job object is
     * updated with the exact values given. Please refer to the appropriate method in the JobDAO for details on pre- and post-conditions.<br/>
     * 
     * @param tx The current transaction. Cannot be null.
     * @param job The job to be updated to the SIF3_JOB table.
     * 
     * @return The updated job. This has a new lastModified date which is now the current date.
     * 
     * @throws IllegalArgumentException If the tx is null or any of the rules listed above are not met.
     * @throws PersistenceException Could not access underlying data store.
     */
    public SIF3Job updateJob(SIF3Job job) throws IllegalArgumentException, PersistenceException
    {
        SIF3Job row = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            row = jobDAO.updateJob(tx, job);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to update Job\n" +job+ "\nReported Error: "+ex.getMessage(), false, false);
        }
        return row;
    }
    
    public void removeJob(long internalID) throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            jobDAO.removeJob(tx, internalID);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to delete Job with intenalID = "+internalID+". Job may not exists. Reported Error: "+ex.getMessage(), false, false);
        }
    }
    
    /**
     * This method removes all rows in the SIF3_JOB table where the expire date is older than the current date. if no expire date is
     * set then these jobs won't be removed.
     *   
     * @param adapterType The adapter type for which the events shall be removed.
     * 
     * @throws IllegalArgumentException If the adapterType is null.
     * @throws PersistenceException Could not access underlying data store.
     */
    public void removeExpiredJobs(AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            jobDAO.removeExpiredJobs(tx, adapterType);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to delete JOBs for entries. Reported Error: "+ex.getMessage(), false, false);
        }
    }

    /**
     * This method removes all rows in the SIF3_JOB table where the job has not been updated since a given date. In other words the 
     * last update date & time is before the noUpdateSince date. This can be considered a stale job. They will be removed only if
     * there is no expire date & time. If an expire date & time is available then the job won't be removed, regardless if it hasn't
     * been updated since the given noUpdateSince. To remove expired jobs, use the removeExpiredJobs() method.
     *   
     * @param noUpdateSince The date for jobs to be removed that have not been updated since that date and do not have an expire date.
     * @param adapterType The adapter type for which the events shall be removed.
     * 
     * @throws IllegalArgumentException If the noUpdateSince or adapterType is null.
     * @throws PersistenceException Could not access underlying data store.
     */
    public void removeJobsWithoutChangeSince(Date noUpdateSince, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            jobDAO.removeJobsWithoutChangeSince(tx, noUpdateSince, adapterType);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to delete JOBs for entries. Reported Error: "+ex.getMessage(), false, false);
        }
    }

    /**
     * This method removes all rows in the SIF3_JOB table where the job has been created before the noUpdateSince date but has 
     * no last update date and no expire date. In other words the creation date & time is before the noUpdateSince date. 
     * This can be considered a stale job. They will be removed only if there is no expire and no last update date & time. 
     * If an expire or last update date & time is available then the job won't be removed, regardless if it has been created before
     * the noUpdateSince. To remove expired jobs, use the removeExpiredJobs() method. To remove jobs that have last been updated\
     * before a given date use the removeJobsWithoutChangeSince() method.
     *   
     * @param noUpdateSince The date for jobs to be removed that have not been created before that date and do not have an expire date
     *                      or last update date.
     * @param adapterType The adapter type for which the events shall be removed.
     * 
     * @throws IllegalArgumentException If the noUpdateSince or adapterType is null.
     * @throws PersistenceException Could not access underlying data store.
     */
    public void removeCreatedJobsWithoutChangeSince(Date noUpdateSince, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            jobDAO.removeCreatedJobsWithoutChangeSince(tx, noUpdateSince, adapterType);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to delete JOBs for entries. Reported Error: "+ex.getMessage(), false, false);
        }
    }

    /**
     * This method removes all rows in the SIF3_JOB table where the job has:<br/>
     * - Expired<br/>
     * - Has a last updated date older than noUpdateSince<br/>
     * - Has been created but not been update since noUpdateSince<br/><br/>
     * 
     * It is a convenience method that combines the removeExpiredJobs(), removeJobsWithoutChangeSince() and 
     * removeCreatedJobsWithoutChangeSince() method into one call. See details for all the mentioned methods.
     *   
     * @param noUpdateSince The date for jobs to be removed that have not been created before that date and do not have an expire date
     *                      or last update date.
     * @param adapterType The adapter type for which the events shall be removed.
     * 
     * @throws IllegalArgumentException If the noUpdateSince or adapterType is null.
     * @throws PersistenceException Could not access underlying data store.
     */
    public void removeExpiredAndStaleJobs(Date noUpdateSince, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            jobDAO.removeExpiredJobs(tx, adapterType);
            jobDAO.removeJobsWithoutChangeSince(tx, noUpdateSince, adapterType);
            jobDAO.removeCreatedJobsWithoutChangeSince(tx, noUpdateSince, adapterType);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to delete JOBs for entries. Reported Error: "+ex.getMessage(), false, false);
        }
        
    }
    
    //-------------------------------//
    //-- JOB Event related methods --//
    //------------------------------//

    public SIF3JobEvent getJobEvent(long internalID) throws IllegalArgumentException, PersistenceException
    {
        SIF3JobEvent row = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            row = jobDAO.getJobEvent(tx, internalID);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, ex.getMessage(), false, false);
        }
        return row;
    }
    
    public List<SIF3JobEvent> getJobEventsByUUID(String uuid, AdapterType adapterType, EventAction eventType, Boolean notYetPublished) throws IllegalArgumentException, PersistenceException
    {
        List<SIF3JobEvent> rows = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            rows = jobDAO.getJobEventsByUUID(tx, uuid, adapterType, eventType, notYetPublished);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, ex.getMessage(), false, false);
        }
        return rows;
    }
    
    /**
     * This method will add a Job Event to the SIF3_JOB_EVENT table. The event is not sent as part of this event! <br/>
     * The event must have a minimum set of data, otherwise an IllegalArgumentExceptionis thrown. Please refer to the
     * appropriate method in the JobDAO for details on these pre- and post-conditions.
     * 
     * @param jobEvent The event to be added to the SIF3_JOB_EVENT table.
     * 
     * @return The newly created job event. This has now the internalID and the event date and published populated. The published will be set to FALSE
     *         and the event date will be set to current datetime.
     * 
     * @throws IllegalArgumentException If the tx is null or any of the rules listed above are not met.
     * @throws PersistenceException Could not access underlying data store.
     */
    public SIF3JobEvent createJobEvent(SIF3JobEvent jobEvent) throws IllegalArgumentException, PersistenceException
    {
        SIF3JobEvent row = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            row = jobDAO.createJobEvent(tx, jobEvent);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, ex.getMessage(), false, false);
        }
        return row;
    }
    
    /**
     * This method creates an event based on the given Job and teh other parameters. If the job.xml is empty then an event cannot really
     * be created as crucial data is missing. The only exception is the delete event which only requires the job.id. There are a number of
     * other pre-conditions to be met. They are listed in the createJobEvent(BasicTransaction, SIF3JobEvent) method.
     *
     * @param job The job based on which an event shall be created. If null then no event is created and null is returned.
     * @param eventType The event type (CREATE, UPDATE, DELETE).
     * @param fingerprintOnly Indicating if the event is for the 'fingerprint' consumer only.
     * 
     * @return The newly created job event. This has now the internalID and the event date and published populated. 
     *         The published will be set to FALSE and the event date will be set to current datetime.
     * 
     * @throws IllegalArgumentException If the tx is null or any of the rules listed above are not met.
     * @throws PersistenceException Could not access underlying data store.
     */
    public SIF3JobEvent createJobEvent(SIF3Job job, EventAction eventType, boolean fingerprintOnly) throws IllegalArgumentException, PersistenceException
    {
        SIF3JobEvent row = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            row = jobDAO.createJobEvent(tx, job, eventType, fingerprintOnly);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, ex.getMessage(), false, false);
        }
        return row;
    }

    
    public void markJobEventAsPublished(long internalID) throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            jobDAO.markJobEventAsPublished(tx, internalID);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, ex.getMessage(), false, false);
        }
     }

    public void removeJobEvent(long internalID) throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            jobDAO.removeJobEvent(tx, internalID);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to delete Job Event with intenalID = "+internalID+". Job Event may not exists. Reported Error: "+ex.getMessage(), false, false);
        }
    }
 
    /**
     * This method removes all rows in the SIF3_JOB_EVENT table that are older than the given date (inclusive).
     * 
     * @param olderThanDate The date to use. All entries older than this date will removed.
     * @param adapterType The adapter type for which the events shall be removed.
     * 
     * @throws IllegalArgumentException If the olderThanDate or adapterType is null.
     * @throws PersistenceException Could not access underlying data store.
     */
    public void removeJobEvents(Date olderThanDate, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            jobDAO.removeJobEvents(tx, olderThanDate, adapterType);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to delete JOB Event log for entries older than "+olderThanDate.toString()+". Reported Error: "+ex.getMessage(), false, false);
        }
     }

    /**
     * This method returns all Job Events that have been created on or after the 'changesSince' date. The number of rows to return
     * is indicated by the 'pagingInfo' parameter. 
     * 
     * @param changesSince Must not be null.
     * @param serviceName The service name for which the events shall be returned. Can be null.
     * @param fingerprint The fingerprint for which the events shall be returned. Can be null.
     * @param zoneID The zone for which the events shall be returned. Can be null.
     * @param contextID The context for which the events shall be returned. Can be null.
     * @param pagingInfo Paging Info. Indicating which page and how many rows to return. Can be null => return all. First page = 1.
     * @param adapterType Must not be null.
     * 
     * @return A list of job events as described. Is never null.
     * 
     * @throws IllegalArgumentException If the changesSince or adapterType is null.
     * @throws PersistenceException Could not access underlying data store.
     */
    public List<SIF3JobEvent> retrieveJobEventsSince(Date changesSince, String serviceName, String fingerprint, String zoneID, String contextID, PagingInfo pagingInfo, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        List<SIF3JobEvent> jobEvents = null;
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            jobEvents = jobDAO.retrieveJobEventsSince(tx, changesSince, serviceName, fingerprint, zoneID, contextID, pagingInfo, adapterType);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, ex.getMessage(), false, false);
        }
        
        return jobEvents;
    }
}
