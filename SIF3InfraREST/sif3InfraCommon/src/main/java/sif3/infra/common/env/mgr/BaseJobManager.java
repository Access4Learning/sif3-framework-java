/*
 * BaseJobManager.java
 * Created: 13 Feb 2018
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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.MarshalException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.persist.model.JobTemplate;
import sif3.common.persist.model.SIF3Job;
import sif3.common.persist.service.JobService;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType;
import sif3.infra.common.env.types.ExtendedJobInfo;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.interfaces.JobManager;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;

/**
 * This method has some implementations of the JobManager interface that is common for all concrete implementations. This is an abstract
 * class that allows some interface methods to be implemented here but others in the concrete implementation. This class further has some
 * utility methods beyond the interface method implementations.
 * 
 * @author Joerg Huber
 *
 */
public abstract class BaseJobManager implements JobManager
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private JobService service = new JobService();
    private EnvironmentInfo environmentInfo = null;
    
    protected BaseJobManager(EnvironmentInfo environmentInfo)
    {
        super();
        this.environmentInfo = environmentInfo;
    }
    
    protected JobService getService()
    {
        return service;
    }

    /*-----------------------*/
    /*-- Interface Methods --*/
    /*-----------------------*/
    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.JobManager#getEnvironmentInfo()
     */
    @Override
    public EnvironmentInfo getEnvironmentInfo()
    {
        return environmentInfo;
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.JobManager#getEnvironmentType()
     */
    @Override
    public EnvironmentType getEnvironmentType()
    {
        return getEnvironmentInfo().getEnvironmentType();
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.JobManager#getJobTemplateInfo(java.lang.String)
     */
    @Override
    public JobTemplate getJobTemplateInfo(String urlName)
    {
        try
        {
            return service.getJobTemplateForAdapter(urlName, getAdapterType());
        }
        catch (Exception ex) // error is already logged.
        {
            return null;
        }    
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.JobManager#getJob(java.lang.String)
     */
    @Override
    public SIF3Job getJob(String jobID) throws PersistenceException
    {
        if(StringUtils.isEmpty(jobID))
        {
            return null;
        }
        
        JobService service = getService();
        return service.getJobByUUID(jobID, getAdapterType());
    }


    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.JobManager#saveNewJob(sif3.infra.common.model.JobType)
     */
    @Override
    public void saveNewJob(JobType newJob, String serviceName, SIFZone zone, SIFContext context, String environmentID, String fingerprint) throws PersistenceException
    {
        String jobXML = null;
        try
        {
            jobXML = createJobXML(newJob);
        }
        catch (MarshalException ex)
        {
            throw new PersistenceException(ex);
        }
 
        JobService service = getService();
        SIF3Job sif3Job = new SIF3Job();
        sif3Job.setAdapterType(getAdapterType().name());
        sif3Job.setContextID(context.getId());
        sif3Job.setZoneID(zone.getId());
        sif3Job.setEnvironmentID(environmentID);
        sif3Job.setFingerprint(fingerprint);
        sif3Job.setJobID(newJob.getId());
        sif3Job.setCreated(newJob.getCreated().getTime());
        sif3Job.setServiceName(serviceName);
        
        if (newJob.getTimeout() != null)
        {
            sif3Job.setTimeoutPeriod(newJob.getTimeout().toString());
        }
        if (newJob.getState() != null)
        {
            sif3Job.setCurrentState(newJob.getState().name());
        }
        
        sif3Job.setJobXML(jobXML);
        
        service.createJob(sif3Job);
        
        if (addToChangeLog())
        {
            service.createJobEvent(sif3Job, EventAction.CREATE, true);
//            SIF3JobEvent event = new SIF3JobEvent();
//            event.setAdapterType(getAdapterType().name());
//            event.setContextID(context.getId());
//            event.setZoneID(zone.getId());
//            event.setEnvironmentID(environmentID);
//            event.setFingerprint(fingerprint);
//            event.setJobID(newJob.getId());
//            event.setServiceName(serviceName);
//            event.setEventType(JobEventType.C.name());
//            event.setToFingerPrintOnly(true);
//            event.setJobXML(jobXML);
//            
//            service.createJobEvent(event);
        }
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.JobManager#removeJob(java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext, java.lang.String, java.lang.String)
     */
    @Override
    public boolean removeJob(String jobID) throws PersistenceException
    {
        JobService service = getService();
        SIF3Job sif3Job = service.getJobByUUID(jobID, getAdapterType());
        
        if (sif3Job == null) // does not exist => We are done. 
        {
            return false;
        }
        
        // Remove job
        service.removeJob(sif3Job.getInternalID());
        
        if (addToChangeLog())
        {
            service.createJobEvent(sif3Job, EventAction.DELETE, true);
//            
//            SIF3JobEvent event = new SIF3JobEvent();
//            event.setAdapterType(getAdapterType().name());
//            event.setContextID(sif3Job.getContextID());
//            event.setZoneID(sif3Job.getZoneID());
//            event.setEnvironmentID(sif3Job.getEnvironmentID());
//            event.setFingerprint(sif3Job.getFingerprint());
//            event.setJobID(sif3Job.getJobID());
//            event.setServiceName(sif3Job.getServiceName());
//            event.setEventType(JobEventType.D.name());
//            event.setToFingerPrintOnly(true);
//            event.setJobXML(sif3Job.getJobXML());
//            
//            service.createJobEvent(event);
        }
        
        return true;
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.JobManager#retrieveJobs(java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo)
     */
    @Override
    public JobCollectionType retrieveJobs(String serviceName, String fingerprint, SIFZone zone, SIFContext context, PagingInfo pagingInfo) throws PersistenceException
    {
        JobService service = getService();
        List<SIF3Job> sif3Jobs = service.retrieveJobs(serviceName, fingerprint, zone.getId(), context.getId(), pagingInfo, getAdapterType());
        
        JobCollectionType jobCollection = new JobCollectionType();
        for (SIF3Job job : sif3Jobs)
        {
            JobType jobType = unmarschalJobXML(job.getJobXML());
            if (jobType == null) // should not really be. All we can do is create an empty jobType with the refId
            {
                jobType = new JobType();
                jobType.setId(job.getJobID());
            }
            jobCollection.getJob().add(jobType);
        }

        return jobCollection;
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.JobManager#retrieveJob(java.lang.String)
     */
    @Override
    public JobType retrieveJob(String jobID) throws PersistenceException
    {
        SIF3Job sif3Job = getJob(jobID);
        if (sif3Job == null) // does not exist => We are done. 
        {
            return null;
        }
        else
        {
            return unmarschalJobXML(sif3Job.getJobXML());
        }
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.common.interfaces.JobManager#getJobInfo(java.lang.String)
     */
    @Override
    public ExtendedJobInfo getJobInfo(String jobID) throws PersistenceException
    {
        SIF3Job sif3Job = getJob(jobID);
        if (sif3Job != null)
        {
            return new ExtendedJobInfo(sif3Job);
        }
        return null;
    }

    /*-------------------------------*/
    /*-- Protected Utility Methods --*/
    /*-------------------------------*/
   
    /**
     * Marshal a Job Object to XML.
     * 
     * @param job The Job Object to marshal to XML.
     * 
     * @return The final XML string.
     * 
     * @throws MarshalException Invalid data. Could not marshal. Error logged already. 
     */
    protected String createJobXML(JobType job) throws MarshalException
    {
        InfraMarshalFactory marshaller = new InfraMarshalFactory();
        try
        {
            return marshaller.marshalToXML(job);
        }
        catch (UnsupportedMediaTypeExcpetion ex) // cannot happen here
        {
            return null;
        }
    }

    /**
     * Unmarshal the Job XML to a proper Job.
     * 
     * @param jobXML The JobXML to unmarshal to Job Object.
     * 
     * @return The Job Object. Can be null if XML is null or empty or if the XML is not a JobType. In both cases an error is logged.
     * 
     * @throws MarshalException Invalid data. Could not marshal. Error logged already. 
     */
    protected JobType unmarschalJobXML(String jobXML)
    {
        if (jobXML == null)
        {
            logger.error("Job XML is null. Cannot unmrshal => Return null.");
            return null;
        }
        InfraUnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
        try
        {
            return (JobType)unmarshaller.unmarshalFromXML(jobXML, JobType.class);
        }
        catch (UnmarshalException ex) // cannot happen here
        {
            logger.error("Invalid Job XML. Cannot unmrshal => Return null.\nOffending XML:\n"+jobXML);
            return null;
        }
        catch (UnsupportedMediaTypeExcpetion e) // should not happen
        {
            return null;
        }
    }
    
    private boolean addToChangeLog()
    {
        // If we are a provider and jobs are enabled then we add the job to the change table.
        return ((getEnvironmentInfo().getAdapterType() != AdapterType.CONSUMER) && (((ProviderEnvironment)getEnvironmentInfo()).isJobEnabled()));
    }
}
