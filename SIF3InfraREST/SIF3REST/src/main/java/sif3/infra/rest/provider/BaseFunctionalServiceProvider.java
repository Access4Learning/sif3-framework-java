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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.interfaces.ChangesSinceProvider;
import sif3.common.interfaces.EventProvider;
import sif3.common.interfaces.FunctionalServiceProvider;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.ACL.AccessRight;
import sif3.common.model.ACL.AccessType;
import sif3.common.model.ChangedSinceInfo;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.model.ZoneContextInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.common.persist.service.JobService;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.mgr.BrokeredProviderEnvironmentManager;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;
import sif3.infra.rest.client.EventClient;
import sif3.infra.rest.provider.functional.JobEventData;
import sif3.infra.rest.provider.functional.JobEventIterator;
import sif3.infra.rest.provider.functional.JobEventWrapper;

/**
 * This is the main class each specific provider of a given SIF Object type must extends to implement the CRUD operation as defined
 * by the SIF3 specification. It is a base implementation of the request connector. It implements some common functionality each provider
 * may require. It also ensures that all components of a provider service are wired up correctly within the framework. Please refer to 
 * the Developer's Guide for some more details about this class.
 *
 * @author Joerg Huber
 */
public abstract class BaseFunctionalServiceProvider extends CoreProvider implements FunctionalServiceProvider, ChangesSinceProvider, EventProvider<JobCollectionType>
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final static int MAX_EVENTS = 10;
    
    private InfraMarshalFactory infraMarshaller =  new InfraMarshalFactory();
    private InfraUnmarshalFactory infraUnmarshaller =  new InfraUnmarshalFactory();
    
    private int maxObjectsInEvent = MAX_EVENTS;
    private ArrayList<ZoneContextInfo> auditZones = null;

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
     * Shuts down the sub-class provider. This should release all associated resources with that provider.
     */
    public abstract void shutdown();
 
    /**
     */
    public BaseFunctionalServiceProvider()
    {
	    super();
	    setMaxObjectsInEvent(getServiceProperties().getPropertyAsInt("job.event.maxObjects", getServiceName(), MAX_EVENTS));
	    auditZones = getAuditZones();
    }
    
    /* 0 = No events */
    public int getEventFrequency()
    {
        return getServiceProperties().getPropertyAsInt("job.event.frequency", getServiceName(), CommonConstants.NO_EVENT);      
    }

    public int getEventDelay()
    {
        int delay = getServiceProperties().getPropertyAsInt("job.event.startup.delay", getServiceName(), CommonConstants.DEFAULT_EVENT_DELAY);
        return (delay < CommonConstants.DEFAULT_EVENT_DELAY) ? CommonConstants.DEFAULT_EVENT_DELAY : delay;
    }

    public EventProvider<?> getEventProvider()
    {
        return BaseFunctionalServiceProvider.this;
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
     * This is not required for the Job as it is a known model. Simply return an empty object.
     * 
     * @return See Desc.
     */
    public ModelObjectInfo getSingleObjectClassInfo()
    {
        return new ModelObjectInfo(null, null);
    }

    public String getServiceName()
    {
    	return getMultiObjectClassInfo().getObjectName();
    }
    
    public ModelObjectInfo getCollectionObjectClassInfo()
    {
        return getMultiObjectClassInfo();
    }
    
    public int getMaxObjectsInEvent()
    {
        return maxObjectsInEvent;
    }

    public void setMaxObjectsInEvent(int maxObjectsInEvent)
    {
        this.maxObjectsInEvent = maxObjectsInEvent;
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
        // Shut down event timer & task - thread
        logger.debug("Finalise in BaseFunctionalProvider for "+getClass().getSimpleName() +" called.");

        // Call finalise on sub-class.
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.CoreProvider#finaliseSubClass()
     */
    @Override
    public void finaliseSubClass()
    {
        finalise();
        shutdown();
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

    /*-------------------------------------*/
    /* Implemented Event Interface Methods */
    /*-------------------------------------*/

    @Override
    public SIFEventIterator<JobCollectionType> getSIFEvents()
    {
        return new JobEventIterator(getServiceName(), includeConsumerEvents(), getAdapterType(), getServiceProperties());
    }

    @Override
    public SIFEvent<JobCollectionType> modifyBeforePublishing(SIFEvent<JobCollectionType> sifEvent, SIFZone zone, SIFContext context, HeaderProperties customHTTPHeaders)
    {
        return sifEvent;
    }

    @Override
    public void onEventError(SIFEvent<JobCollectionType> sifEvent, SIFZone zone, SIFContext context)
    {
        // Events are not published. Report to error log but don't mark them as 'published'.
        ArrayList<String> ids = new ArrayList<String>();
        if ((sifEvent != null) && (sifEvent.getSIFObjectList() != null))
        {
            for (JobType job : sifEvent.getSIFObjectList().getJob())
            {
                ids.add(job.getId());
            }
        }
        logger.error("Failed to events to zone = "+zone.getUniqueName() + " and Context = " + context.getUniqueName()+ "\n Internal Job Id that failed:\n"+ids);
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
    
    /*---------------------------------------------------------------------------------------------------------------
     * Methods specific for this class
     *-------------------------------------------------------------------------------------------------------------*/
    public void broadcastEvents()
    {
        int totalRecordsForFingerprintZones = 0;
        int failedRecordsForFingerprintZones = 0;
        int totalRecordsForAuditZones = 0;
        int failedRecordsForAuditZones = 0;
        boolean consumerRequestedProperty = getConsumerRequestetProperty();
        boolean includeConsumeRequested = includeConsumerEvents();
        
        logger.debug("================================ broadcastEvents() called for provider "+getPrettyName());
        logger.info("Audit Zone List: "+auditZones);
        logger.debug("Include Consumer Events: "+includeConsumeRequested);
        logger.info("Max objects per SIF Event: "+getMaxObjectsInEvent());
        
        BrokeredProviderEnvironmentManager envMgr = getBrokeredEnvironmentManager();
        if (envMgr == null) // not a brokered environment. Cannot sent events!
        {
            return; // error already logged.
        }
        
        SIF3Session sif3Session = envMgr.getSIF3Session();
        List<ServiceInfo> servicesForProvider = getServicesForProvider(sif3Session, ServiceType.FUNCTIONAL);
        
        // If there are no services for this provider defined then we don't need to get any events at all.
        if ((servicesForProvider == null) || (servicesForProvider.size() == 0))
        {
            logger.info("This emvironment does not have any zones and contexts defined for the "+getServiceName() + " service. No events can be sent.");
            return;
        }       
 
        // lets start sending events....
        try
        {
            // Let's get the Event Client
            String serviceName = getServiceName();
            EventClient evtClient = new EventClient(envMgr, getRequestMediaType(), getResponseMediaType(), serviceName, getMarshaller(), getCompressionEnabled());
            JobEventIterator iterator = (JobEventIterator)getSIFEvents();
            if (iterator != null)
            {
                while (iterator.hasNext())
                {
                    // We know it is a "custom" Job Event Iterator. Call specific method and then create necessary
                    // SIFEvent<JobCollectionType>
                    JobEventWrapper jobEventWrapper = iterator.getEvents(getMaxObjectsInEvent());
                    
                    // This should not return null since the hasNext() returned true, but just in case we check and exit the loop if it should return null. 
                    // In this case we assume that there is no more data. We also log an error to make the coder aware of the issue.
                    if (jobEventWrapper != null)
                    {
                        logger.debug("Number of "+serviceName+" Job Event Objects: " + jobEventWrapper.getEvents().size());
                        
                        // Do we need to send events to audit zones
                        if ((auditZones != null) && (auditZones.size() > 0))
                        {
                            // first create SIFEvent<JobCollectionType> which is what we send
                            SIFEvent<JobCollectionType> events = getEventsFromWrapper(jobEventWrapper, false, false);
                            for (ZoneContextInfo zoneCtxInfo : auditZones)
                            {
                                // Check if provider has rights to publish to given zone and context
                                if (sif3Session.hasAccess(AccessRight.PROVIDE, AccessType.APPROVED, serviceName, null, zoneCtxInfo.getZone(), zoneCtxInfo.getContext()))
                                {
                                    failedRecordsForAuditZones = failedRecordsForAuditZones + prepareEventAndSend(evtClient, events, zoneCtxInfo.getZone(), zoneCtxInfo.getContext());                                        
                                    totalRecordsForAuditZones = totalRecordsForAuditZones + events.getListSize();
                                }
                                else
                                {
                                    logNoAccessRight(zoneCtxInfo.getZone(), zoneCtxInfo.getContext());
                                }
                            }
                        }
                        
                        // Send to fingerprint:  We may need to filter consumerEvents now...
                        SIFEvent<JobCollectionType> events = getEventsFromWrapper(jobEventWrapper, !consumerRequestedProperty, true);
                        
                        // Check if there are actually any events to be sent. Could be none if events are
                        // consumerRequested only.
                        if (events.getListSize() > 0)
                        {
                            logger.debug("Job Event holds "+events.getListSize()+" Objects for Consumer with Fingerprint = "+events.getFingerprint());
                            // Do we have access rights for the requested zone/context (we should but just in case...)
                            // Check if provider has rights to publish to given zone and context
                            if (sif3Session.hasAccess(AccessRight.PROVIDE, AccessType.APPROVED, serviceName, null, jobEventWrapper.getZoneContext().getZone(), jobEventWrapper.getZoneContext().getContext()))
                            {
                                failedRecordsForFingerprintZones = failedRecordsForFingerprintZones + prepareEventAndSend(evtClient, events, jobEventWrapper.getZoneContext().getZone(), jobEventWrapper.getZoneContext().getContext());                                        
                                totalRecordsForFingerprintZones = totalRecordsForFingerprintZones + events.getListSize();
                            }
                            else
                            {
                                logNoAccessRight(jobEventWrapper.getZoneContext().getZone(), jobEventWrapper.getZoneContext().getContext());
                            }
                        }
                        else
                        {
                            logger.debug("There are currently no events required to be sent to the consumer with the fingerprint = "+events.getFingerprint()+".");
                        }
                        
                        // all is sent. We need to mark the events as published.
                        markEventsAsPublished(jobEventWrapper);                            
                    }
                    else
                    {
                        logger.error("iterator.hasNext() has returned true but iterator.getEvent() has retrurned null => no further events are broadcasted.");
                        break;
                    }
                }
                iterator.releaseResources();
            }
            else
            {
                logger.info("getSIFEvents() for provider "+getPrettyName()+" returned null. Currently no events to be sent.");
            }
        }
        catch (Exception ex)
        {
            logger.error("Failed to retrieve events for provider "+getPrettyName()+": "+ex.getMessage(), ex);                               
        }
        
        logger.info("Total SIF Event Objects broadcasted to Fingerprint Zones: "+totalRecordsForFingerprintZones);
        logger.info("Total SIF Event Objects failed for Fingerprint Zones    : "+failedRecordsForFingerprintZones);
        if ((auditZones != null) && (auditZones.size() > 0))
        {
            logger.info("Total SIF Event Objects broadcasted to Audit Zone(s)    : "+totalRecordsForAuditZones);
            logger.info("Total SIF Event Objects failed for Audit Zone(s)        : "+failedRecordsForAuditZones);
        }
        logger.debug("================================ Finished broadcastEvents() for provider "+getPrettyName());
    }
    
    /*---------------------*/
    /*-- Private methods --*/
    /*---------------------*/   
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
	 
	 private SIFEvent<JobCollectionType> getEventsFromWrapper(JobEventWrapper jobEventWrapper, boolean providerEventsOnly, boolean includeFingerprint)
	 {
	     SIFEvent<JobCollectionType> events = new SIFEvent<JobCollectionType>();
	     events.setSIFObjectList(new JobCollectionType());
	     events.setEventAction(jobEventWrapper.getEventAction());
	     events.setUpdateType(UpdateType.FULL);
	     if (includeFingerprint)
	     {
	         events.setFingerprint(jobEventWrapper.getFingerprint());
	     }
	     for (JobEventData jobEventData : jobEventWrapper.getEvents())
	     {
	         if (!providerEventsOnly || !jobEventData.isConsumerEvent())
	         {
	             events.getSIFObjectList().getJob().add(jobEventData.getJobData());
	         }
	     }
	     events.setListSize(events.getSIFObjectList().getJob().size());
	     
	     return events;
	 }
	 
    /*
     * Return the number of failed records. If 0 is return all then the event with its content is sent successfully.
     */
    private int prepareEventAndSend(EventClient evtClient, SIFEvent<JobCollectionType> sifEvents, SIFZone zone, SIFContext context)
    {
        int failedRecords = 0;
        HeaderProperties customHTTPHeaders = new HeaderProperties();
        if (!sendEvents(evtClient, sifEvents, zone, context, ServiceType.FUNCTIONAL, customHTTPHeaders))
        {
            //Report back to the caller. This should also give the event back to the caller.
            onEventError(sifEvents, zone, context);
            failedRecords = ((sifEvents != null) ? sifEvents.getListSize() : 0);
        }
        
        return failedRecords;
    }

    private void markEventsAsPublished(JobEventWrapper jobEventWrapper)
    {
        JobService service = new JobService();
        for (JobEventData jobEventData : jobEventWrapper.getEvents())
        {
            try
            {
                service.markJobEventAsPublished(jobEventData.getInternalJobID());
            }
            catch (Exception ex)
            {
                logger.debug("Failed to mark job event with internal ID = " + jobEventData.getInternalJobID() +" as published: "+ex.getMessage(), ex);
            }
        }
    }

    private ArrayList<ZoneContextInfo> getAuditZones()
    {
        ArrayList<ZoneContextInfo> auditZones = null;
        String auditZoneStr = getServiceProperties().getPropertyAsString("job.event.auditZones", getServiceName(), null);      
        if (auditZoneStr != null)
        {
            auditZones = new ArrayList<ZoneContextInfo>();
            String[] auditZoneArr = auditZoneStr.split(",");
            for (String zoneCtxStr : auditZoneArr)
            {
                ZoneContextInfo zoneContext = new ZoneContextInfo();
                String[] zoneCtxInfo = zoneCtxStr.split("\\|");
                zoneContext.setZone(new SIFZone(zoneCtxInfo[0].trim()));
                if (zoneCtxInfo.length > 1) // we have a context
                {
                    zoneContext.setContext(new SIFContext(zoneCtxInfo[1].trim()));
                }
                else // default context
                {
                    zoneContext.setContext(new SIFContext(CommonConstants.DEFAULT_CONTEXT_NAME));
                }
                auditZones.add(zoneContext);
            }
        }
        return auditZones;
    }

    private boolean includeConsumerEvents()
    {
        boolean includeConsumeRequested = getConsumerRequestetProperty();

        // If we have auditZone we do need all events for these regardless of the property job.event.includeConsumerRequested
        return (auditZones != null) || includeConsumeRequested;
    }
     
    private boolean getConsumerRequestetProperty()
    {
        return getServiceProperties().getPropertyAsBool("job.event.includeConsumerRequested", getServiceName(), false);
    }
}
