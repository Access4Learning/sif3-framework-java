/*
 * BaseFunctionalServiceProvider.java
 * Created: 07/06/2018
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

package sif3.infra.rest.provider;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.DateUtils;
import sif3.common.CommonConstants;
import sif3.common.CommonConstants.JobState;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.ExpiredException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIFException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.interfaces.ChangesSinceProvider;
import sif3.common.interfaces.FunctionalServiceProvider;
import sif3.common.model.ChangedSinceInfo;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.model.JobType;

/**
 * This is the main class each specific provider of a given SIF Object type must extends to implement the CRUD operation as defined
 * by the SIF3 specification. It is a base implementation of the request connector. It implements some common functionality each provider
 * may require. It also ensures that all components of a provider service are wired up correctly within the framework. Please refer to 
 * the Developer's Guide for some more details about this class.
 *
 * @author Joerg Huber
 */
public abstract class BaseFunctionalServiceProvider extends CoreProvider implements FunctionalServiceProvider, ChangesSinceProvider, Runnable
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    private InfraMarshalFactory infraMarshaller =  new InfraMarshalFactory();
    private InfraUnmarshalFactory infraUnmarshaller =  new InfraUnmarshalFactory();

    /*
        The FunctionalServiceProvider interface defines a couple of method with "Object" as the parameter or return type. This
        was done that way, so that the interface doesn't have a dependency on the infrastructure data model. Because the Job is
        a infrastructure model we know some more details about it and can narrow down the parameters of these interface methods
        to the specific type here. Therefore this class simply re-defines the few FunctionalServiceProvider interface methods
        with the applicable infrastructure data model type, so that the implementation of a specific functional service doesn't
        need to do type casts or guess work.
    */

    /**
     * This method re-maps the FunctionalServiceProvider's method createJob and uses the JobType rather than the Object type as 
     * parameters. All parameters an behaviour of this method is identical to @see sif3.common.interfaces.FunctionalServiceProvider#createJob(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     *
     * @param jobData The data of the actual job to be created. It may or may not hold the jobID and the provider may or may not accept it.
     *             It is up to the implementation to make that decision. The final jobID is returned as part of the returned job.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the job shall be created. Can be Null (default Zone)
     * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * @param customResponseParams Values to be returned as part of the response. These are generally just HTTP Header fields. If a developer
     *                             sets the HTTP Header of a well defined SIF3 HTTP Header (i.e. providerId, timestamp) then the framework
     *                             may override these with its own value to ensure the correct use and workings of the framework. It is the
     *                             developer who will populate the object. When it is passed to this method it not null but empty.
     * 
     * @return The job that is created. It may hold additional data than the one provided. This will be of type JobType as defined
     *         infrastructure data model.
     * 
     * @throws IllegalArgumentException One of the parameters is invalid.
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
     */
    public abstract JobType createNewJob(JobType jobData, SIFZone zone, SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams) 
            throws IllegalArgumentException, PersistenceException, SIFException;

    /**
     */
    public BaseFunctionalServiceProvider()
    {
	    super();
    }
     
    /**
     * Returns a marshaller applicable for the data model supported for this implementation.
     * 
     * @return See Desc.
     */
    public MarshalFactory getMarshaller()
    {
        return infraMarshaller;
    }

    /**
     * Returns an unmarshaller applicable for the data model supported for this implementation.
     * 
     * @return See Desc.
     */
    public UnmarshalFactory getUnmarshaller()
    {
        return infraUnmarshaller;
    }

    /**
     * This is not required for the Job as it is a known model.
     * 
     * @return See Desc.
     */
    public ModelObjectInfo getSingleObjectClassInfo()
    {
        return null;
    }

    public String getServiceName()
    {
    	return getMultiObjectClassInfo().getObjectName();
    }
    
    public ModelObjectInfo getCollectionObjectClassInfo()
    {
        return getMultiObjectClassInfo();
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#createJob(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public Object createJob(Object jobData, 
                            SIFZone zone, 
                            SIFContext context, 
                            RequestMetadata metadata, 
                            ResponseParameters customResponseParams) 
            throws IllegalArgumentException, PersistenceException, SIFException
    {
        return createNewJob((JobType)jobData, zone, context, metadata, customResponseParams);
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#updateJobStatus(java.lang.String, sif3.common.CommonConstants.JobState)
     */
    @Override
    public void updateJobState(String jobID, JobState newState) throws PersistenceException
    {
        getJobManager().updateJobState(jobID, newState);
    }
    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#updatePhaseState(java.lang.String, java.lang.String, sif3.common.CommonConstants.PhaseState)
     */
    @Override
    public void updatePhaseState(String jobID, String phaseName, PhaseState newState) throws PersistenceException
    {
        getJobManager().updatePhaseState(jobID, phaseName, newState);
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#updateJobStateAndPhaseState(java.lang.String, sif3.common.CommonConstants.JobState, java.lang.String, sif3.common.CommonConstants.PhaseState)
     */
    @Override
    public void updateJobStateAndPhaseState(String jobID, JobState newJobState, String phaseName, PhaseState newPhaseState) throws PersistenceException
    {
        getJobManager().updateJobStateAndPhaseState(jobID, newJobState, phaseName, newPhaseState);
    }

    /**
     * (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#finalise()
     */
    public void finalise()
    {
        // Call finalise on sub-class.
    }
    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#getServiceInfo(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     */
    @Override
    public HeaderProperties getServiceInfo(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException, SIFException
    {
        // For the time being we just call the abstract method getCustomServiceInfo().
        return getCustomServiceInfo(zone, context, pagingInfo, metadata);
    }
 
    /*---------------------------*/
    /*-- Changes Since Methods --*/
    /*---------------------------*/
    /* (non-Javadoc)
     * @see sif3.common.interfaces.ChangesSinceProvider#changesSinceSupported()
     */
    @Override
    public boolean changesSinceSupported()
    {
        // If job is enabled then changes since is enabled.
        return getProviderEnvironment().isJobEnabled();
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.ChangesSinceProvider#getLatestOpaqueMarker(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     */
    @Override
    public String getLatestOpaqueMarker(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata)
    {
        // We use the SIF3_JOB_EVENT table for the implementation. This is based around timestamps.
        return DateUtils.getISO8601withSecFraction(new Date());
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.ChangesSinceProvider#getChangesSince(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.ChangedSinceInfo, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public Object getChangesSince(SIFZone zone, SIFContext context, PagingInfo pagingInfo, ChangedSinceInfo changedSinceInfo, RequestMetadata metadata, ResponseParameters customResponseParams) 
            throws PersistenceException, UnsupportedQueryException, DataTooLargeException, ExpiredException, SIFException
    {
        // If it is not supported then we return UnsupportedQueryException
        if (changesSinceSupported())
        {
            Date changesSince = getChangesSince(changedSinceInfo);
            if (changesSince != null)
            {
                // We only keep changes in the event table for a certain time. The number of days we keep the data is in the
                // ProviderEnvironment. If the date of the changes since marker is older than this then we should return ExpiredException.
                if (isChangeSinceMarkerExpired(changesSince))
                {
                    throw new ExpiredException("changesSinceMarker has expired.", "The given changesSinceMarker has expired. Get a new one by calling the appropriate HTTP HEAD method.", "Provider ("+getProviderName()+")");
                }
                
                return getJobManager().retrieveJobChangesSince(changesSince, getServiceName(), metadata.getFingerprint(), zone, context, pagingInfo);
            }
            else
            {
                throw new UnsupportedQueryException("Retrieved invalid Changes Since Marker", "The changes since marker is null.", "Provider ("+getProviderName()+")");
            }
        }
        else
        {
            throw new UnsupportedQueryException("Changes Since is not suported","The Functional Services "+getProviderName() + " does not support changes since.", "Provider ("+getProviderName()+")");
        }
    }


    /*----------------------------------------*/
    /* Implemented Method for Multi-threading */
    /*----------------------------------------*/

	/**
	 * This method is all that is needed to run the provider in its own thread. The thread is executed at
	 * given intervals driven by a property in the adapter's property file. The interval/frequency
	 * defined in there is used to determine how often this thread is run.
	 */
    @Override
    public final synchronized void run()
    {
    	String providerName = getProviderName();
    	
		logger.debug(providerName+" started.");
    }

    /*---------------------------------------------------------------------------------------------------------------
     * Section with default behaviour. Some of these methods are based on the provider property file. Some of these 
     * methods might be overriden by the actual functional service provider class to do some specific tasks that
     * are applicable for that class only. 
     *-------------------------------------------------------------------------------------------------------------*/

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#isJobEndState(sif3.common.CommonConstants.JobState)
     */
    @Override
    public boolean isJobEndState(JobState state)
    {
        return getProviderEnvironment().getJobEndStates().contains(state);
    }
    
    /*---------------------*/
    /*-- Private methods --*/
    /*---------------------*/
    
	/* 0 = No events */
	@SuppressWarnings("unused")
    private int getEventFrequency(String providerName)
	{
    	return getServiceProperties().getPropertyAsInt(CommonConstants.FREQ_PROPERTY, providerName, CommonConstants.NO_EVENT);    	
	}

	@SuppressWarnings("unused")
    private int getEventDelay(String providerName)
	{
    	int delay = getServiceProperties().getPropertyAsInt(CommonConstants.EVENT_DELAY_PROPERTY, providerName, CommonConstants.DEFAULT_EVENT_DELAY);
    	return (delay < CommonConstants.DEFAULT_EVENT_DELAY) ? CommonConstants.DEFAULT_EVENT_DELAY : delay;
	}
	
	 private Date getChangesSince(ChangedSinceInfo changedSinceInfo)
	 {
	     try
	     {
	         return DateUtils.stringToDate(changedSinceInfo.getChangesSinceMarker(), DateUtils.ISO_8601_SECFRACT);
	     }
	     catch (Exception ex)
	     {
	         logger.error("Failed to convert changes since marker "+changedSinceInfo.getChangesSinceMarker()+" to date.");
	         return null;
	     }
	 }
	 
	 private boolean isChangeSinceMarkerExpired(Date changesSince)
	 {
	     Date now = new Date();
	     Date oldestValidDate = DateUtils.getDateWithAddedDays(now, -getProviderEnvironment().getJobEventKeepDays());
	     
	     return changesSince.getTime() < oldestValidDate.getTime();
	 }
}
