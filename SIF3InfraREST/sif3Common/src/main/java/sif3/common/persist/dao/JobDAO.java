/*
 * JobDAO.java
 * Created: 6 Feb 2018
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

package sif3.common.persist.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.DateUtils;
import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.CommonConstants.JobState;
import sif3.common.exception.PersistenceException;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.model.PagingInfo;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.model.SIF3Job;
import sif3.common.persist.model.SIF3JobEvent;

/**
 * @author Joerg Huber
 *
 */
public class JobDAO extends BaseDAO
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    //-------------------------//
    //-- JOB related methods --//
    //-------------------------//
    public SIF3Job getJob(BasicTransaction tx, long internalID) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        
        try
        {
            return (SIF3Job)tx.getSession().get(SIF3Job.class, internalID);
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Job for internal ID = '"+ internalID + "'.", e);
        }   
    }

    public SIF3Job getJobByUUID(BasicTransaction tx, String uuid, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        
        if (StringUtils.isEmpty(uuid))
        {
          throw new IllegalArgumentException("uuid empty or null.");          
        }
        
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }

        try
        {
            Criteria criteria = tx.getSession().createCriteria(SIF3Job.class)
               .add(Restrictions.eq("jobID", uuid))
               .add(Restrictions.eq("adapterType", adapterType.name()));
            
            @SuppressWarnings("unchecked")
            List<SIF3Job> jobs = criteria.list();
            
            // There can only be a maximum of one
            if (jobs.isEmpty()) // no job for given uuid and adapter type
            {
                logger.debug("No Job for UUID = '"+ uuid + "' and adapterType = '" + adapterType + "' exists in SIF3_JOB table.");
                return null;
            }
            else // already exists
            {
                return jobs.get(0);
            }
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Job for uuid = '"+ uuid + " and adapterType = '" + adapterType + "'.", e);
        } 
    }
    
    /*
     * Ensure that not null is returned. Either throw exception or a non-null list must be returned.
     */
    public List<SIF3Job> retrieveJobs(BasicTransaction tx, String serviceName, String fingerprint, String zoneID, String contextID, PagingInfo pagingInfo, AdapterType adapterType) throws PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }

        try
        {
            Criteria criteria = tx.getSession().createCriteria(SIF3Job.class);
            
            if (StringUtils.notEmpty(serviceName))
            {
                criteria = criteria.add(Restrictions.eq("serviceName", serviceName));
            }
            if (StringUtils.notEmpty(fingerprint))
            {
                criteria = criteria.add(Restrictions.eq("fingerprint", fingerprint));
            }
            if (StringUtils.notEmpty(zoneID))
            {
                criteria = criteria.add(Restrictions.eq("zoneID", zoneID));
            }
            if (StringUtils.notEmpty(contextID))
            {
                criteria = criteria.add(Restrictions.eq("contextID", contextID));
            }
            criteria = criteria.add(Restrictions.eq("adapterType", adapterType.name()));
            
            // We need pageSize and page number to get a 'window' view.
            if (pagingInfo != null)
            {
                if (pagingInfo.getCurrentPageNo() < CommonConstants.FIRST_PAGE) // no really set, so we assume first page
                {
                    pagingInfo.setCurrentPageNo(CommonConstants.FIRST_PAGE);
                }
                
                if (pagingInfo.getPageSize() > 0)  // Ok we have a page size.
                {
                    criteria.setFirstResult((pagingInfo.getCurrentPageNo() - CommonConstants.FIRST_PAGE) * pagingInfo.getPageSize());
                    criteria.setMaxResults(pagingInfo.getPageSize());
                }
            }
            
            //Add orderBy to ensure consistent results
            criteria.addOrder(Order.asc("internalID"));
           
            @SuppressWarnings("unchecked")
            List<SIF3Job> jobs = criteria.list();
            
            // Just in case test for null....
            if (jobs == null)
            {
                jobs = new ArrayList<SIF3Job>();
            }
            
            return jobs;
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Job for fingerprint = '"+ fingerprint + ", zoneID = " + zoneID + ", contextID = "+ contextID + ", adapterType = '" + adapterType + "' and pagingInfo = "+pagingInfo+".", e);
        } 
    }

    
    /**
     * This method will add a Job to the SIF3_JOB table. The Job must have a minimum set of data, otherwise an IllegalArgumentExceptionis thrown. 
     * The data required is listed below:<br/><br/>
     * - jobID
     * - adapterType
     * - serviceName: This must match the name as in the Service URL Name.
     * - jobXML
     * - Ideally: fingerprint if it is intended to allow only the originator to see information about the job
     * - Ideally: currentState. If not set then it is defaulted to JobState.NOTSTARTED as defined by the SIF 3.x Spec.
     *                          Further this value must be a value of the CommonConstants.JobState enumeration.<br/><br/>
     * 
     * The following values will be ignored and defaulted. The returned object will have them populated as followed:<br/>
     * - internalID: Will automatically allocated.
     * - created: Will be set to current date.<br/>
     * - expireDatetime: Will be calculated based on the 'created' and the timeoutPeriod. If timeoutPeriod==null then the expireDatetime will 
     *                   not be set. Also if expireDatetime is set it will take precedence of the calculated value!
     *                   timeoutPeriod must be the string representation of a javax.xml.datatype.Duration type.
     * 
     * @param tx The current transaction. Cannot be null.
     * @param job The job to be added to the SIF3_JOB table.
     * 
     * @return The newly created job. This has now the internalID populated.
     * 
     * @throws IllegalArgumentException If the tx is null or any of the rules listed above are not met.
     * @throws PersistenceException Could not access underlying data store.
     */
    public SIF3Job createJob(BasicTransaction tx, SIF3Job job) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        if (job == null)
        {
            throw new IllegalArgumentException("Attempted to add a job with no data (i.e. job object is null)");
        }

        // There is a minimum set of data that is required for a job. If they are not provided an IllegalArgumentException is thrown.
        String errorTxt = "";
        if (StringUtils.isEmpty(job.getJobID()))
        {
            errorTxt = errorTxt + "jobID is null or empty.\n";
        }
        if (StringUtils.isEmpty(job.getServiceName()))
        {
            errorTxt = errorTxt + "job name is null or empty.\n";
        }
//        if (StringUtils.isEmpty(job.getEnvironmentID()))
//        {
//            errorTxt = errorTxt + "environmentID is null or empty.\n";
//        }
        if (job.getAdapterType() == null)
        {
            errorTxt = errorTxt + "adapterType is null.\n";
        }
        if (job.getJobXML() == null)
        {
            errorTxt = errorTxt + "jobXML is required.\n";
        }                
        
        if (errorTxt.length() > 0)
        {
            throw new IllegalArgumentException(errorTxt);
        }
        
        // Default some values:
        job.setCreated(new Date());
        
        if (StringUtils.isEmpty(job.getCurrentState()))
        {
            job.setCurrentState(JobState.NOTSTARTED.name());
        }
        
        //Check if we need to calculate expire date
        if (job.getExpireDatetime() == null) // if set it takes precedence and we don't calculate it.
        {
            //Check if Timeout Period is set. 
            if (job.getTimeoutPeriod() != null)
            {
                try
                {
                    job.setExpireDatetime(DateUtils.addDuration(job.getTimeoutPeriod(), null));
                }
                catch (ParseException ex)
                {
                    // This is not good there is nothing we can do. Report error and throw a persistence exception
                    throw new PersistenceException(ex);
                }
            }
        }
        
        // now we are ready to save it to the DB.
        try
        {
            tx.getSession().save(job);
            return job;
        }
        catch (Exception e)
        {
            throw new PersistenceException("Unable to create Job in SIF3_JOB table for "+ job +": "+e.getMessage(), e);
        }   
    }

    /**
     * Updates a job with the given values. Note that the internalID must be set and have the value of an existing object. The job object is
     * updated with the exact values given. Some values are mandatory for it to be a valid object. They are listed below:<br/><br/>
     * - internalID: must be a value of an existing Job in the SIF3_JOB table.
     * - jobID:
     * - adapterType
     * - seriveName: This must match the name as in the Service URL Name.
     * - jobXML
     * - Ideally: fingerprint if it is intended to allow only the originator to see information about the job<br/><br/>
     * - Ideally: currentState shall not be null. If it is it will be defaulted to JobState.NOTSTARTED<br/><br/>
     * 
     * The following values will be ignored and defaulted. The returned object will have them populated as followed:<br/>
     *  - lastModified: Will be set to current date.<br/>
     * 
     * @param tx The current transaction. Cannot be null.
     * @param job The job to be updated to the SIF3_JOB table.
     * 
     * @return The updated job. This has a new lastModified date which is now the current date.
     * 
     * @throws IllegalArgumentException If the tx is null or any of the rules listed above are not met.
     * @throws PersistenceException Could not access underlying data store.
     */
    public SIF3Job updateJob(BasicTransaction tx, SIF3Job job) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        if (job == null)
        {
            logger.info("Attempted to updat a job with no data (i.e. job object is null). Ignore operation.");
        }

        // There is a minimum set of data that is required for a job. If they are not provided an IllegalArgumentException is thrown.
        String errorTxt = "";
        if (job.getInternalID() <= 0) //invalid!
        {
            errorTxt = errorTxt + "internalID must be a valid id > 0.\n";
        }
        if (StringUtils.isEmpty(job.getJobID()))
        {
            errorTxt = errorTxt + "jobID is null or empty.\n";
        }
        if (StringUtils.isEmpty(job.getServiceName()))
        {
            errorTxt = errorTxt + "job name is null or empty.\n";
        }
//        if (StringUtils.isEmpty(job.getEnvironmentID()))
//        {
//            errorTxt = errorTxt + "environmentID is null or empty.\n";
//        }
        if (job.getAdapterType() == null)
        {
            errorTxt = errorTxt + "adapterType is null.\n";
        }
        if (job.getJobXML() == null)
        {
            errorTxt = errorTxt + "jobXML is required.\n";
        }                
        
        if (errorTxt.length() > 0)
        {
            throw new IllegalArgumentException(errorTxt);
        }
        
        // Default some values:
        job.setLastModified(new Date());
        
        // now we are ready to save it to the DB.
        try
        {
            tx.getSession().update(job);
            return job;
        }
        catch (Exception e)
        {
            throw new PersistenceException("Unable to update Job in SIF3_JOB table for "+ job +": "+e.getMessage(), e);
        }   
    }
    
    public void removeJob(BasicTransaction tx, long internalID) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        if (internalID <= 0)
        {
            logger.info("InternalID <= 0 for job. Ignore operation");
        }
        
        // now we are ready to save it to the DB.
        try
        {
            SIF3Job job = new SIF3Job(internalID);
            tx.getSession().delete(job);
        }
        catch (Exception e)
        {
            throw new PersistenceException("Unable to remove Job in SIF3_JOB table for intenalID = "+ internalID +": "+e.getMessage(), e);
        }   
    }    

    /**
     * This method removes all rows in the SIF3_JOB table where the expire date is older than the current date. if no expire date is
     * set then these jobs won't be removed.
     *   
     * @param tx The current transaction. Cannot be null.
     * @param adapterType The adapter type for which the events shall be removed.
     * 
     * @throws IllegalArgumentException If the tx or adapterType is null.
     * @throws PersistenceException Could not access underlying data store.
     */
    public void removeExpiredJobs(BasicTransaction tx, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        if (adapterType == null)
        {
            throw new IllegalArgumentException("adapterType is null.");
        }

        Date now = new Date();
        logger.debug("Remove Jobs that expired before or on "+now);
        Query deleteStatement = tx.getSession().createQuery("delete from SIF3Job where expireDatetime is not null and expireDatetime <= :now and adapterType = :adapterType");
        deleteStatement = deleteStatement.setTimestamp("now", now).setString("adapterType", adapterType.name());
        
        try
        {
            int numRowsDeleted = deleteStatement.executeUpdate();
            logger.debug(numRowsDeleted+" row(s) deleted from SIF3_JOB table.");
        }
        catch (HibernateException ex)
        {
            throw new PersistenceException(ex);
        }
    }

    /**
     * This method removes all rows in the SIF3_JOB table where the job has not been updated since a given date. In other words the 
     * last update date & time is before the noUpdateSince date. This can be considered a stale job. They will be removed only if
     * there is no expire date & time. If an expire date & time is available then the job won't be removed, regardless if it hasn't
     * been updated since the given noUpdateSince. To remove expired jobs, use the removeExpiredJobs() method.
     *   
     * @param tx The current transaction. Cannot be null.
     * @param noUpdateSince The date for jobs to be removed that have not been updated since that date and do not have an expire date.
     * @param adapterType The adapter type for which the events shall be removed.
     * 
     * @throws IllegalArgumentException If the tx or noUpdateSince or adapterType is null.
     * @throws PersistenceException Could not access underlying data store.
     */
    public void removeJobsWithoutChangeSince(BasicTransaction tx, Date noUpdateSince, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        if (adapterType == null)
        {
            throw new IllegalArgumentException("adapterType is null.");
        }
        if (noUpdateSince == null)
        {
            throw new IllegalArgumentException("noUpdateSince is null.");
        }
        
        logger.debug("Remove Jobs that have not changed since "+noUpdateSince);        
        Query deleteStatement = tx.getSession().createQuery("delete from SIF3Job where expireDatetime is null and lastModified is not null and lastModified <= :noUpdateSince and adapterType = :adapterType");
        deleteStatement = deleteStatement.setTimestamp("noUpdateSince", noUpdateSince).setString("adapterType", adapterType.name());
        
        try
        {
            int numRowsDeleted = deleteStatement.executeUpdate();
            logger.debug(numRowsDeleted+" row(s) deleted from SIF3_JOB table.");
        }
        catch (HibernateException ex)
        {
            throw new PersistenceException(ex);
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
     * @param tx The current transaction. Cannot be null.
     * @param noUpdateSince The date for jobs to be removed that have not been created before that date and do not have an expire date
     *                      or last update date.
     * @param adapterType The adapter type for which the events shall be removed.
     * 
     * @throws IllegalArgumentException If the tx or noUpdateSince or adapterType is null.
     * @throws PersistenceException Could not access underlying data store.
     */
    public void removeCreatedJobsWithoutChangeSince(BasicTransaction tx, Date noUpdateSince, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        if (adapterType == null)
        {
            throw new IllegalArgumentException("adapterType is null.");
        }
        
        if (noUpdateSince == null)
        {
            throw new IllegalArgumentException("noUpdateSince is null.");
        }
        
        logger.debug("Remove Jobs that have been created before or on "+noUpdateSince+" but have never been updated.");        
        Query deleteStatement = tx.getSession().createQuery("delete from SIF3Job where expireDatetime is null and lastModified is null and created is not null and created <= :noUpdateSince and adapterType = :adapterType");
        deleteStatement = deleteStatement.setTimestamp("noUpdateSince", noUpdateSince).setString("adapterType", adapterType.name());
        
        try
        {
            int numRowsDeleted = deleteStatement.executeUpdate();
            logger.debug(numRowsDeleted+" row(s) deleted from SIF3_JOB table.");
        }
        catch (HibernateException ex)
        {
            throw new PersistenceException(ex);
        }
    }
    
    //-------------------------------//
    //-- JOB Event related methods --//
    //------------------------------//
    public SIF3JobEvent getJobEvent(BasicTransaction tx, long internalID) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        
        try
        {
            return (SIF3JobEvent)tx.getSession().get(SIF3JobEvent.class, internalID);
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Job Event for internal ID = '"+ internalID + "'.", e);
        }   
    }

    /*
     * If any of the following parameters is null then it will be ignored as a "query parameter":
     * - eventType
     * - notYetPublished
     * 
     * All other parameters are required.
     * 
     * The list returned are events applicable for the given Job UUID and Adapter Type.
     */
    @SuppressWarnings("unchecked")
    public List<SIF3JobEvent> getJobEventsByUUID(BasicTransaction tx, String uuid, AdapterType adapterType, EventAction eventType, Boolean notYetPublished) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        
        if (StringUtils.isEmpty(uuid))
        {
          throw new IllegalArgumentException("uuid empty or null.");          
        }
        
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }

        try
        {
            Criteria criteria = tx.getSession().createCriteria(SIF3JobEvent.class)
               .add(Restrictions.eq("jobID", uuid))
               .add(Restrictions.eq("adapterType", adapterType.name()));
            
            if (eventType != null)
            {
                criteria.add(Restrictions.eq("eventType", eventType.name().substring(0, 1)));
            }
            
            if (notYetPublished != null)
            {
                criteria.add(Restrictions.eq("published", notYetPublished));
            }
            
            criteria.addOrder(Order.asc("eventDate"));
            return criteria.list();
            
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Job Events for uuid = '"+ uuid + ", adapterType = '" + adapterType + "', Event Type = "+eventType + " and Not Yet Published = " + notYetPublished, e);
        }
    }
    
    /**
     * This method will add a Job Event to the SIF3_JOB_EVENT table. The event is not sent as part of this event! <br/>
     * The event must have a minimum set of data, otherwise an IllegalArgumentExceptionis thrown. The data required may depend on the 
     * event type as well. The minimum rules are listed below:<br/><br/>
     * - jobID
     * - seriveName: This must match the name as in the Service URL Name.
     * - adapterType
     * - jobXML if event type is Update or Create. It can be null if the event type is Delete.
     * - eventType
     * - fullUpdate if event type is Update.
     * - fingerprint if toFingerPrintOnly = true<br/><br/>
     * 
     * A few values will be defaulted if they are not set.<br/>
     * - toFingerPrintOnly defaults to TRUE: Only the consumer with that fingerprint will receive the event.
     * - fullUpdate defaults to TRUE: it is assumed that the entire Job Object is provided in case of an update event.<br/><br/>
     * 
     * The following values will be ignored and defaulted. The returned object will have them populated as followed:<br/>
     * - internalID: Will automatically allocated.
     * - eventDate: Current date.
     * - published: FALSE
     * - publishedDate: null.<br/>
     * 
     * @param tx The current transaction. Cannot be null.
     * @param jobEvent The event to be added to the SIF3_JOB_EVENT table.
     * 
     * @return The newly created job event. This has now the internalID and the event date and published populated. The published will be set to FALSE
     *         and the event date will be set to current datetime.
     * 
     * @throws IllegalArgumentException If the tx is null or any of the rules listed above are not met.
     * @throws PersistenceException Could not access underlying data store.
     */
    public SIF3JobEvent createJobEvent(BasicTransaction tx, SIF3JobEvent jobEvent) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        if (jobEvent == null)
        {
            logger.info("Attempted to add a job event with no data (i.e. jobEvent object is null");
        }

        // There is a minimum set of data that is required for a job event. If they are not provided an IllegalArgumentException
        // is thrown.
        String errorTxt = "";
        if (StringUtils.isEmpty(jobEvent.getJobID()))
        {
            errorTxt = errorTxt + "jobID is null or empty.\n";
        }
        if (StringUtils.isEmpty(jobEvent.getServiceName()))
        {
            errorTxt = errorTxt + "Service Name is null or empty.\n";
        }
//        if (StringUtils.isEmpty(jobEvent.getEnvironmentID()))
//        {
//            errorTxt = errorTxt + "environmentID is null or empty.\n";
//        }
        if (jobEvent.getAdapterType() == null)
        {
            errorTxt = errorTxt + "adapterType is null.\n";
        }
        if (jobEvent.isToFingerPrintOnly())
        {
            if (StringUtils.isEmpty(jobEvent.getFingerprint()))
            {
                errorTxt = errorTxt + "fingerprint is null or empty. Must be set because toFingerPrintOnly is set to TRUE.\n";
            }
        }
        if (StringUtils.isEmpty(jobEvent.getEventType()))
        {
            errorTxt = errorTxt + "eventType is null or empty.\n";
        }
        else
        {
            if ("C".equalsIgnoreCase(jobEvent.getEventType()))
            {
                if (jobEvent.getJobXML() == null)
                {
                    errorTxt = errorTxt + "jobXML is required for eventType = Create.\n";
                }
            }
            else if ("U".equalsIgnoreCase(jobEvent.getEventType()))
            {
                if (jobEvent.getJobXML() == null)
                {
                    errorTxt = errorTxt + "jobXML is required for eventType = Update.\n";
                }                
            }
        }
        
        if (errorTxt.length() > 0)
        {
            throw new IllegalArgumentException(errorTxt);
        }
        
        // If we get here we have all minimum data. Default some of the values
        jobEvent.setEventDate(new Date());
        jobEvent.setPublished(Boolean.FALSE);
        jobEvent.setPublishedDate(null);
        
        // now we are ready to save it to the DB.
        try
        {
            tx.getSession().save(jobEvent);
            return jobEvent;
        }
        catch (Exception e)
        {
            throw new PersistenceException("Unable to create Job Event in SIF3_JOB_EVENT table for "+ jobEvent +": "+e.getMessage(), e);
        }   
    }
    
    /**
     * This method creates an event based on the given Job and teh other parameters. If the job.xml is empty then an event cannot really
     * be created as crucial data is missing. The only exception is the delete event which only requires the job.id. There are a number of
     * other pre-conditions to be met. They are listed in the createJobEvent(BasicTransaction, SIF3JobEvent) method.
     *
     * @param tx The current transaction. Cannot be null.
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
    public SIF3JobEvent createJobEvent(BasicTransaction tx, SIF3Job job, EventAction eventType, boolean fingerprintOnly) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        
        if (job != null)
        {
             return createJobEvent(tx, new SIF3JobEvent(job, eventType, fingerprintOnly));           
        }
        else
        {
            return null;
        }
    }
    
    /**
     * This method removes all rows in the SIF3_JOB_EVENT table that are older than the given date (inclusive).
     * 
     * @param tx The current transaction. Cannot be null.
     * @param olderThanDate The date to use. All entries older than this date will removed.
     * @param adapterType The adapter type for which the events shall be removed.
     * 
     * @throws IllegalArgumentException If the tx, olderThanDate or adapterType is null.
     * @throws PersistenceException Could not access underlying data store.
     */
    public void removeJobEvents(BasicTransaction tx, Date olderThanDate, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        if (olderThanDate == null)
        {
            throw new IllegalArgumentException("olderThanDate is null.");
        }
        if (adapterType == null)
        {
            throw new IllegalArgumentException("adapterType is null.");
        }
        
        logger.debug("Remove Job Events created before or on "+olderThanDate+".");        
        String hql = "delete from SIF3JobEvent where eventDate <= :olderThan and adapterType = :adapterType";
        try
        {
            int numRowsDeleted = tx.getSession().createQuery(hql).setTimestamp("olderThan", olderThanDate).setString("adapterType", adapterType.name()).executeUpdate();
            logger.debug(numRowsDeleted+" row(s) deleted from SIF3_JOB_EVENT table.");
        }
        catch (HibernateException ex)
        {
            throw new PersistenceException(ex);
        }
    }
    
    public void markJobEventAsPublished(BasicTransaction tx, long internalID) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }

        SIF3JobEvent jobEvent = getJobEvent(tx, internalID);
        if (jobEvent != null)
        {
            jobEvent.setPublished(Boolean.TRUE);
            jobEvent.setPublishedDate(new Date());;
            tx.getSession().update(jobEvent);
        }        
    }

    public void removeJobEvent(BasicTransaction tx, long internalID) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        if (internalID <= 0)
        {
            logger.info("InternalID <= 0 for jobEvent. Ignore operation");
        }

        try
        {
            SIF3JobEvent jobEvent = new SIF3JobEvent(internalID);
            tx.getSession().delete(jobEvent);
        }
        catch (Exception e)
        {
            throw new PersistenceException("Unable to remove Job Event in SIF3_JOB_EVENT table for intenalID = "+ internalID +": "+e.getMessage(), e);
        }   
    }

    /*
     * Ensure that not null is returned. Either throw exception or a non-null list must be returned.
     * Returns all Job Events since the given date. Only a "window" of rows are returned indicated with the pagingInfo
     * parameter.
     */
    public List<SIF3JobEvent> retrieveJobEventsSince(BasicTransaction tx, Date changesSince, String serviceName, String fingerprint, String zoneID, String contextID, PagingInfo pagingInfo, AdapterType adapterType) throws PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }
        if (changesSince == null)
        {
          throw new IllegalArgumentException("changesSince is null.");          
        }

        try
        {
            Criteria criteria = tx.getSession().createCriteria(SIF3JobEvent.class);
            
            criteria = criteria.add(Restrictions.ge("eventDate", changesSince));
            
            if (StringUtils.notEmpty(serviceName))
            {
                criteria = criteria.add(Restrictions.eq("serviceName", serviceName));
            }
            if (StringUtils.notEmpty(fingerprint))
            {
                criteria = criteria.add(Restrictions.eq("fingerprint", fingerprint));
            }
            if (StringUtils.notEmpty(zoneID))
            {
                criteria = criteria.add(Restrictions.eq("zoneID", zoneID));
            }
            if (StringUtils.notEmpty(contextID))
            {
                criteria = criteria.add(Restrictions.eq("contextID", contextID));
            }
            criteria = criteria.add(Restrictions.eq("adapterType", adapterType.name()));
            
            // We need pageSize and page number to get a 'window' view.
            if (pagingInfo != null)
            {
                if (pagingInfo.getCurrentPageNo() < CommonConstants.FIRST_PAGE) // no really set, so we assume first page
                {
                    pagingInfo.setCurrentPageNo(CommonConstants.FIRST_PAGE);
                }
                
                if (pagingInfo.getPageSize() > 0)  // Ok we have a page size.
                {
                    criteria.setFirstResult((pagingInfo.getCurrentPageNo() - CommonConstants.FIRST_PAGE) * pagingInfo.getPageSize());
                    criteria.setMaxResults(pagingInfo.getPageSize());
                }
            }
            
            //Add orderBy to ensure consistent results
            criteria.addOrder(Order.asc("eventDate"));
           
            @SuppressWarnings("unchecked")
            List<SIF3JobEvent> jobEventss = criteria.list();
            
            // Just in case test for null....
            if (jobEventss == null)
            {
                jobEventss = new ArrayList<SIF3JobEvent>();
            }
            
            return jobEventss;
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Job Events since "+DateUtils.dateToString(changesSince, DateUtils.DISP_DATE_TIME_SEC)+" for fingerprint = '"+ fingerprint + ", zoneID = " + zoneID + ", contextID = "+ contextID + ", adapterType = '" + adapterType + "' and pagingInfo = "+pagingInfo+".", e);
        } 
        
    }

    
}
