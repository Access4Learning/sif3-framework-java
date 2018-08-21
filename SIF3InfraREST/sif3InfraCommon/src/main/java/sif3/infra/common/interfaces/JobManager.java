/*
 * JobManager.java
 * Created: 13/02/2018
 *
 * Copyright 2014-2018 Systemic Pty Ltd
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

package sif3.infra.common.interfaces;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.persist.model.JobTemplate;
import sif3.common.persist.model.SIF3Job;
import sif3.infra.common.env.types.EnvironmentInfo;
import sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType;
import sif3.infra.common.env.types.ExtendedJobInfo;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;

/**
 * This interface defines all methods required by this framework in relation to any jobs (functional services) for consumer, provider, direct,
 * brokered etc. These are the minimum functions. Some more specific interfaces for some job managers may extend this interface to define some 
 * more detailed functionality.
 * 
 * @author Joerg Huber
 *
 */
public interface JobManager
{	
	/**
	 * Returns the environment information (configuration) that goes with this job manager. The environment information is just the
	 * underlying information that relates to environment configuration file related to the specific adapter type. If there is no 
	 * initialised environment configuration for this environment then null shall be returned.
	 * 
	 * @return See desc.
	 */
	public EnvironmentInfo getEnvironmentInfo();
	
	/**
	 * returns the adapter type for this job manager.
	 * 
	 * @return See Desc.
	 */
	public AdapterType getAdapterType();
	
	/**
	 * This method must return the environment type of the given job manager. Note for providers this must always be set. For
	 * consumer this is mostly irrelevant as they behave the same independent if it is a brokered or direct environment. For consumers
	 * this method my return null. Possible values are DIRECT or BROKERED.
	 * 
	 * @return See desc.
	 */
	public EnvironmentType getEnvironmentType();
	
    /**
     * This method will attempt to load the job template info from the underlying job template info store. Note that 
     * this method won't load the actual template (XML) rather it returns some meta information around the Job template.
     * If there is no entry in job template store store for the given urlName. If no such template info exists or an error 
     * occurs accessing  the store then null is returned.
     *  
     * @param urlName The unique name of the job template as given in the job URL for which the template meta data shall be
     *                retrieved. A job URL is generally of the form .../job/<uniqueJobName>/... The uniqueJobName is what the
     *                URL name refers to and is the same value as in the SIF3_JOB_TEMPLATE table in the column JOB_URL_NAME.
     * 
     * @return See desc.
     */
    public JobTemplate getJobTemplateInfo(String urlName);
    
    /**
     * This method shall return the job given by its ID (refID). If no such job exists then null is returned.
     * 
     * @param jobID The ID of the job to be returned. If null or empty then null is returned.
     * 
     * @return See desc.
     * 
     * @throws PersistenceException If the underlying data store cannot be accessed.
     */
    public SIF3Job getJob(String jobID) throws PersistenceException;
    
    /**
     * This method will persist the job to the appropriate tables. For a provider it may also store an entry in the job event table
     * if the provider is a brokered provider and has event support.
     * 
     * @param newJob The job to be persisted.
     * @param serviceName Cannot be null. It identifies if functional service name as defined by the service URL.
     * @param zone The zone for which the job is applicable.
     * @param context The context for which the job is applicable.
     * @param environmentID The environment for which the job is applicable.
     * @param fingerprint The fingerprint of the Adapter that requested the job creation.
     * 
     * @throws PersistenceException Some data could not be persisted. An error log entry is performed and the message of the exceptions 
     *                              holds some info.
     */
    public void saveNewJob(JobType newJob, String serviceName, SIFZone zone, SIFContext context, String environmentID, String fingerprint) throws PersistenceException;

    /**
     * This method will attempt to remove the job for the given jobID (job RefID) from the SIF3_JOB table. If it exists it will also make a 
     * "DELETE" entry in the SIF3_JOB_EVENT table if events are supported. If event support is not enabled then no entry will be performed 
     * to the SIF3_JOB_EVENT table.
     * 
     * @param jobID The refID of the job to be removed.
     * 
     * @return TRUE: Job is removed and optional an entry is made in the event table. FALSE: Job did not exist. No changes are performed.
     * 
     * @throws PersistenceException Some data could not be persisted/removed. An error log entry is performed and the message of the exceptions 
     *                              holds some info.
     */
    public boolean removeJob(String jobID) throws PersistenceException;

    /**
     * This method will attempt to get the job for the given jobID (job RefID) from the SIF3_JOB table. If it exists it will return the job 
     * object. If it doesn't exist then null is returned.
    * 
     * @param jobID The refID of the job to be retrieved.
     * 
     * @return Job if it exists otherwise null is returned.
     * 
     * @throws PersistenceException Some data could not be accessed. An error log entry is performed and the message of the exceptions 
     *                              holds some info.
     */
    public JobType retrieveJob(String jobID) throws PersistenceException;

    /**
     * This method retrieves all jobs for the given adapter identified by the fingerprint, zone & context. If no fingerprint is given 
     * it gets all jobs for the given zone & context. The jobs list returned is also limited to the values given in the pagingInfo parameter.
     * 
     * @param serviceName Can be null. It identifies if functional service name as defined by the service URL.
     * @param fingerprint Can be null. It identifies if jobs for a specific adapter shall be returned or any job.
     * @param zone The zone for which the jobs shall be returned.
     * @param context The context for which the jobs shall be returned.
     * @param pagingInfo Limiting the returned job list to the given 'window'.
     * 
     * @return A list of jobs that match the criteria given by the input parameters. It can be an empty list if no jobs match the
     *         criteria. Should never be null.
     * 
     * @throws PersistenceException Some data could not be accessed. An error log entry is performed and the message of the exceptions 
     *                              holds some info.
     */
    public JobCollectionType retrieveJobs(String serviceName, String fingerprint, SIFZone zone, SIFContext context, PagingInfo pagingInfo) throws PersistenceException;


    /**
     * This method attempts to get the ExtendedJobInfo for the given Job. There are many places in the Functional Service
     * implementation where not only the DB representation but also the XML representation of the job is required. This method allows
     * to have one object that hold bot representations. It may also help avoiding too many DB accesses and conversions between
     * String and JAXB data.
     *  
     * @param jobID The ID of the Job for which the extended object shall be retrieved. If job does not exist then null is returned.
     * 
     * @return See Desc.
     * 
     * @throws PersistenceException Some data could not be accessed. An error log entry is performed and the message of the exceptions 
     *                              holds some info.
     */
    public ExtendedJobInfo getJobInfo(String jobID) throws PersistenceException;
    
    
}
