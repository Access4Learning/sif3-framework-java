/*
 * Crown Copyright © Department for Education (UK) 2016
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

package sif3.infra.rest.provider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.BadRequestException;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.ForbiddenException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIF3Exception;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.utils.JAXBUtils;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.functional.JobEvent;
import sif3.infra.common.functional.JobEvents;
import sif3.infra.common.functional.JobIterator;
import sif3.infra.common.interfaces.FunctionalServiceProvider;
import sif3.infra.common.interfaces.PhaseActions;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.PhaseType;
import sif3.infra.common.model.StateType;
import sif3.infra.common.persist.service.SIF3JobService;
import sif3.infra.common.utils.ServiceUtils;

/**
 * Base implementation of A functional service. This should be extended to define what phases a job
 * has and other details about what the Job should know/be able to do/etc.
 */
public abstract class AbstractFunctionalServiceProvider extends BaseEventProvider<JobCollectionType> implements FunctionalServiceProvider
{

    protected final Logger                   logger          = Logger.getLogger(getClass());

    protected SIF3JobService                 sif3JobService  = new SIF3JobService();

    private static InfraUnmarshalFactory     unmarshaller    = new InfraUnmarshalFactory();
    private static InfraMarshalFactory       marshaller      = new InfraMarshalFactory();
    private static ObjectFactory             objectFactory   = new ObjectFactory();

    private String                           serviceName;
    protected HashMap<String, PhaseActions> phaseActions;

    private static JobEvents                 sifevents       = null;

    private Timer                            jobTimeoutTimer = null;
    private TimerTask                        jobTimeoutTask  = null;

    /**
     * Constructor
     * 
     * @param serviceName
     *            The (plural) name of this functional service.
     */
    public AbstractFunctionalServiceProvider(String serviceName)
    {
        super();

        this.serviceName = serviceName;
        phaseActions = new HashMap<String, PhaseActions>();

        JAXBUtils.initCtx(getMultiObjectClassInfo().getObjectType());
        JAXBUtils.initCtx(getSingleObjectClassInfo().getObjectType());

        if (sifevents == null)
        {
            logger.debug("Constructor for AbstractJobProvider called for the first time...");
            sifevents = new JobEvents();
        }
    }

    /**
     * Decorates (configures) a given Job instance with all tits phases and other information.
     * 
     * @param job
     *            The job to configure
     */
    protected abstract void configure(JobType job);

    /**
     * Attempts to shutdown the given job. Should throw an exception if the job cannot be shutdown
     * immediately. This will result in the comsumer's delete request failing and reporting the
     * issue. It is up to the implementation to decide if the delete request will be honoured in the
     * future (when possible) or if it should be ignored until the consumer tries again at another
     * time.
     * 
     * @param job
     *            The job to shut down.
     */
    protected abstract void shutdownJob(JobType job);

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.DataModelLink#getMarshaller()
     */
    public MarshalFactory getMarshaller()
    {
        return marshaller;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.DataModelLink#getUnmarshaller()
     */
    public UnmarshalFactory getUnmarshaller()
    {
        return unmarshaller;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.provider.BaseProvider#getServiceName()
     */
    @Override
    public String getServiceName()
    {
        return serviceName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.provider.BaseProvider#getServiceType()
     */
    @Override
    public ServiceType getServiceType()
    {
        return ServiceType.FUNCTIONAL;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.provider.BaseProvider#finalise()
     */
    @Override
    public void finalise()
    {
        super.finalise();
        // Shut down event timer & task - thread
        if (jobTimeoutTask != null)
        {
            logger.debug("Shut Down event task for: " + getProviderName());
            jobTimeoutTask.cancel();
            jobTimeoutTask = null;
        }
        if (jobTimeoutTimer != null)
        {
            logger.debug("Shut Down event timer for: " + getProviderName());
            jobTimeoutTimer.cancel();
            jobTimeoutTimer.purge();
            jobTimeoutTimer = null;
        }

        // Call finalise on sub-class.
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.provider.BaseProvider#run()
     */
    @Override
    public void run()
    {
        super.run();
        int frequencyInSec = 20;// getEventFrequency(providerName);
        int period = frequencyInSec * CommonConstants.MILISEC;

        logger.info("Job timout frequency " + frequencyInSec + " secs (" + period + ").");
        if (jobTimeoutTask == null)
        {
            jobTimeoutTask = new TimerTask()
            {

                public void run()
                {
                    logger.debug("++++++++++++++++++++++++++++++ Starting job timout task for "
                            + getProviderName() + ".");

                    try
                    {
                        sif3JobService.getJobs().stream().filter(new Predicate<JobType>()
                        {
                            @Override
                            public boolean test(JobType job)
                            {
                                Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                                Calendar then = (Calendar) job.getCreated().clone();

                                job.getTimeout().addTo(then);

                                return acceptJob(job)
                                        && job.getTimeout()
                                                .getTimeInMillis(Calendar.getInstance()) > 0
                                        && now.after(then);
                            }
                        }).forEach(new Consumer<JobType>()
                        {
                            @Override
                            public void accept(JobType job)
                            {
                                logger.debug("Job " + job.getId()
                                        + " has timed out, requesting its deletion.");
                                try
                                {
                                    deleteSingle(job.getId(), null, null, null, null);
                                }
                                catch (Exception e)
                                {
                                    logger.error("Failed to delete timed out job with id "
                                            + job.getId() + " due to error: " + e.getMessage(), e);
                                }
                            }
                        });
                    }
                    catch (Exception e)
                    {
                        logger.error("Failed to retrieve jobs to process timeout due to error: "
                                + e.getMessage(), e);
                    }
                    logger.debug("++++++++++++++++++++++++++++++ Finished job timout task for "
                            + getProviderName() + ".");
                }
            };

            jobTimeoutTimer = new Timer(true);
            jobTimeoutTimer.scheduleAtFixedRate(jobTimeoutTask, 0, period);
        }
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#acceptJob(sif3.infra.common.model.JobType)
     */
    @Override
    public boolean acceptJob(JobType job)
    {
        return acceptJob(job.getName());
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#acceptJob(java.lang.String)
     */
    @Override
    public boolean acceptJob(String jobName)
    {
        return acceptJob(getServiceName(), jobName);
    }

    protected boolean acceptJob(String serviceName, String jobName)
    {
        if (StringUtils.isEmpty(getServiceName()) || StringUtils.isEmpty(serviceName)
                || StringUtils.isEmpty(jobName))
        {
            return false;
        }
        return getServiceName().equals(serviceName) && getServiceName().equals(jobName + "s");
    }

    /*-------------------------------------*/
    /*-- EventProvider Interface Methods --*/
    /*-------------------------------------*/
    /*
     * (non-Javadoc) @see sif3.common.interfaces.EventProvider#getSIFEvents()
     */
    @Override
    public SIFEventIterator<JobCollectionType> getSIFEvents()
    {
        if (sifevents.isEmpty())
        {
            return null;
        }
        JobIterator itr = new JobIterator(getServiceName(), getServiceProperties(),
                sifevents.getAllEvents());
        return itr;
    }

    /*
     * (non-Javadoc) @see sif3.common.interfaces.EventProvider#onEventError(sif3.common.model.
     * SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public void onEventError(SIFEvent<JobCollectionType> sifEvent, SIFZone zone, SIFContext context)
    {
        // We need to deal with the error. At this point in time we just log it.
        if ((sifEvent != null) && (sifEvent.getSIFObjectList() != null))
        {
            try
            {
                String eventXML = getMarshaller().marshalToXML(sifEvent.getSIFObjectList());
                logger.error("Failed to sent the following Objects as and Event to Zone (" + zone
                        + ") and Context (" + context + "):\n" + eventXML);
            }
            catch (Exception ex)
            {
                logger.error("Failed to marshall events.", ex);
            }
        }
        else
        {
            logger.error(
                    "sifEvent Object is null, or there are no events on sifEvent.sifObjectList");
        }

    }

    /*
     * (non-Javadoc) @see sif3.common.interfaces.EventProvider#modifyBeforeSent(sif3.common.model.
     * SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public SIFEvent<JobCollectionType> modifyBeforePublishing(SIFEvent<JobCollectionType> sifEvent,
            SIFZone zone, SIFContext context, HeaderProperties customHTTPHeaders)
    {
        JobEvent jobEvent = sifevents.get(sifEvent);
        if (jobEvent == null)
        {
            // Don't know anything about this event
            return null;
        }

        if (!jobEvent.getZone().equals(zone))
        {
            // Not the right zone to send this to
            return null;
        }

        if (!jobEvent.getContext().equals(context))
        {
            // Not the right context to send this to
            return null;
        }

        // Looks ok
        return sifEvent;
    }

    /*--------------------------------*/
    /*-- Provider Interface Methods --*/
    /*--------------------------------*/

    /*
     * (non-Javadoc) @see sif3.common.interfaces.Provider#retrieveByPrimaryKey(java.lang.String,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object retrieveByPrimaryKey(String resourceID, SIFZone zone, SIFContext context,
            RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        if (StringUtils.isEmpty(resourceID))
        {
            throw new IllegalArgumentException(
                    "Resource ID is null or empty. It must be provided to retrieve an entity.");
        }

        logger.debug("Retrieve job with Resource ID = " + resourceID + ", "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        return sif3JobService.getJobById(resourceID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#createSingle(java.lang.Object, boolean,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata,
     * sif3.common.model.ResponseParameters)
     */
    @Override
    public Object createSingle(Object data, boolean useAdvisory, SIFZone zone, SIFContext context,
            RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        logger.debug("Create single job for " + getZoneAndContext(zone, context)
                + " and RequestMetadata = " + metadata);

        if (data instanceof JobType)
        {
            JobType job = (JobType) data;

            configure(job);

            if (!useAdvisory)
            {
                job.setId(null);
            }

            if (useAdvisory && StringUtils.isEmpty(job.getId()))
            {
                logger.debug("UseAdvisory is true, but no advisory refid provided.");
            }

            job = sif3JobService.save(job);

            customResponseParams.addHTTPHeaderParameter("jobId", job.getId());

            sendJobEvent(job, EventAction.CREATE, zone, context);

            return job;
        }
        else
        {
            throw new IllegalArgumentException(
                    "Expected Object Type = JobType. Received Object Type = "
                            + data.getClass().getSimpleName());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#retrieve(sif3.common.model.SIFZone,
     * sif3.common.model.SIFContext, sif3.common.model.PagingInfo,
     * sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public Object retrieve(SIFZone zone, SIFContext context, PagingInfo pagingInfo,
            RequestMetadata metadata, ResponseParameters customResponseParams)
            throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
        logger.debug("Retrieve jobs for " + getZoneAndContext(zone, context)
                + " and RequestMetadata = " + metadata);
        logger.debug("ChangedSince Date: " + metadata.getRequestParameter("ChangedSince"));
        logger.debug("Custom HTTP Header (customHdr): " + metadata.getHTTPParameter("customHdr"));

        if (pagingInfo == null)
        {
            throw new DataTooLargeException(
                    "No paging info is provided. Please provide navigationPage and navigationPageSize.");
        }
        else
        {
            if (pagingInfo.getCurrentPageNo() == CommonConstants.FIRST_PAGE)
            {
                pagingInfo.setNavigationId(UUIDGenerator.getUUID());
            }
        }

        JobCollectionType jobCollection = objectFactory.createJobCollectionType();

        jobCollection.getJob().addAll(fetchJobs(sif3JobService.getJobs(), pagingInfo));

        return jobCollection;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#updateSingle(java.lang.Object, java.lang.String,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata,
     * sif3.common.model.ResponseParameters)
     */
    @Override
    public boolean updateSingle(Object data, String resourceID, SIFZone zone, SIFContext context,
            RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#deleteSingle(java.lang.String,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata,
     * sif3.common.model.ResponseParameters)
     */
    @Override
    public boolean deleteSingle(String resourceID, SIFZone zone, SIFContext context,
            RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        if (StringUtils.isEmpty(resourceID))
        {
            throw new IllegalArgumentException(
                    "Resource ID is null or empty. It must be provided to delete an entity.");
        }

        logger.debug("Remove job with Resource ID = " + resourceID + ", "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        JobType job = (JobType) sif3JobService.getJobById(resourceID);

        if (job == null)
        {
            logger.error(
                    "Attempted to delete job with id " + resourceID + ", but no such job exists");
            return false;
        }

        try
        {
            shutdownJob(job);
        }
        catch (Exception e)
        {
            logger.error("Unable to shutdown job with ID " + job.getId(), e);
            return false;
        }

        sif3JobService.removeJobById(resourceID);

        sendJobEvent(job, EventAction.DELETE, zone, context);

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#createMany(java.lang.Object, boolean,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata,
     * sif3.common.model.ResponseParameters)
     */
    @Override
    public List<CreateOperationStatus> createMany(Object data, boolean useAdvisory, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        if (data instanceof JobCollectionType)
        {
            logger.debug("Create jobs (Bulk Operation) for " + getZoneAndContext(zone, context)
                    + " and RequestMetadata = " + metadata);
            JobCollectionType jobCollection = (JobCollectionType) data;
            ArrayList<CreateOperationStatus> opStatus = new ArrayList<CreateOperationStatus>();
            Object created = null;
            String advisoryId = null;
            for (JobType job : jobCollection.getJob())
            {
                advisoryId = job.getId();
                created = createSingle(job, useAdvisory, zone, context, metadata,
                        customResponseParams);
                if (created == null)
                {
                    opStatus.add(new CreateOperationStatus(advisoryId, advisoryId, 404,
                            new ErrorDetails(400, "Data not good.")));
                }
                else
                {
                    opStatus.add(new CreateOperationStatus(job.getId(), advisoryId, 201));
                }
            }
            return opStatus;
        }
        else
        {
            throw new IllegalArgumentException(
                    "Expected Object of type JobCollectionType. Received Object Type = "
                            + data.getClass().getSimpleName());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#updateMany(java.lang.Object, sif3.common.model.SIFZone,
     * sif3.common.model.SIFContext, sif3.common.model.RequestMetadata,
     * sif3.common.model.ResponseParameters)
     */
    @Override
    public List<OperationStatus> updateMany(Object data, SIFZone zone, SIFContext context,
            RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        return new ArrayList<OperationStatus>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#deleteMany(java.lang.Object, sif3.common.model.SIFZone,
     * sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> deleteMany(List<String> resourceIDs, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        logger.debug("Delete Jobs (Bulk Operation) for " + getZoneAndContext(zone, context)
                + " and RequestMetadata = " + metadata);

        ArrayList<OperationStatus> opStatus = new ArrayList<OperationStatus>();
        for (String resourceID : resourceIDs)
        {
            if (deleteSingle(resourceID, zone, context, metadata, customResponseParams))
            {
                opStatus.add(new OperationStatus(resourceID, 200));
            }
            else
            {
                opStatus.add(new OperationStatus(resourceID, 404, new ErrorDetails(404,
                        "Job with GUID = " + resourceID + " does not exist.")));
            }
        }
        return opStatus;
    }

    /*----------------------*/
    /*-- Phase Operations --*/
    /*----------------------*/
    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#createToPhase(java.lang.String, java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public String createToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception
    {
        logger.debug("Create to phase " + resourceID + "/" + phaseName + " in "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        JobType job = (JobType) retrieveByPrimaryKey(resourceID, zone, context, metadata,
                customResponseParams);
        if (job == null)
        {
            throw new BadRequestException("No job found with refid " + resourceID);
        }

        PhaseType phase = ServiceUtils.getPhase(job, phaseName);
        if (phase == null)
        {
            throw new BadRequestException(
                    "No phase with name " + phaseName + " found for job with refid " + resourceID);
        }

        if (!ServiceUtils.checkPhaseACL(phase, AccessRight.CREATE, AccessType.APPROVED))
        {
            throw new ForbiddenException();
        }

        PhaseActions actions = phaseActions.get(phase.getName());
        if (actions == null)
        {
            throw new IllegalArgumentException("Unexpected error, phase is known (" + job.getId()
                    + "/" + phase.getName() + "), but has no actions can be found");
        }

        String result = actions.create(job, phase, payload, requestMediaType, responseMediaType,
                zone, context);

        sif3JobService.save(job);

        return result;
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#retrieveToPhase(java.lang.String, java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public String retrieveToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception
    {
        logger.debug("Retrieve to phase " + resourceID + "/" + phaseName + " in "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        JobType job = (JobType) retrieveByPrimaryKey(resourceID, zone, context, metadata,
                customResponseParams);
        if (job == null)
        {
            throw new BadRequestException("No job found with refid " + resourceID);
        }

        PhaseType phase = ServiceUtils.getPhase(job, phaseName);
        if (phase == null)
        {
            throw new BadRequestException(
                    "No phase with name " + phaseName + " found for job with refid " + resourceID);
        }

        if (!ServiceUtils.checkPhaseACL(phase, AccessRight.QUERY, AccessType.APPROVED))
        {
            throw new ForbiddenException();
        }

        PhaseActions actions = phaseActions.get(phase.getName());
        if (actions == null)
        {
            throw new IllegalArgumentException("Unexpected error, phase is known (" + job.getId()
                    + "/" + phase.getName() + "), but has no actions can be found");
        }

        String result = actions.retrieve(job, phase, payload, requestMediaType, responseMediaType,
                zone, context);

        // A get to a phase may have potentially modified the job state
        sif3JobService.save(job);

        return result;
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#updateToPhase(java.lang.String, java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public String updateToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception
    {
        logger.debug("Update to phase " + resourceID + "/" + phaseName + " in "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        JobType job = (JobType) retrieveByPrimaryKey(resourceID, zone, context, metadata,
                customResponseParams);
        if (job == null)
        {
            throw new BadRequestException("No job found with refid " + resourceID);
        }

        PhaseType phase = ServiceUtils.getPhase(job, phaseName);
        if (phase == null)
        {
            throw new BadRequestException(
                    "No phase with name " + phaseName + " found for job with refid " + resourceID);
        }

        if (!ServiceUtils.checkPhaseACL(phase, AccessRight.UPDATE, AccessType.APPROVED))
        {
            throw new ForbiddenException();
        }

        PhaseActions actions = phaseActions.get(phase.getName());
        if (actions == null)
        {
            throw new IllegalArgumentException("Unexpected error, phase is known (" + job.getId()
                    + "/" + phase.getName() + "), but has no actions can be found");
        }

        String result = actions.update(job, phase, payload, requestMediaType, responseMediaType,
                zone, context);

        sif3JobService.save(job);

        return result;
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#deleteToPhase(java.lang.String, java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public String deleteToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception
    {
        logger.debug("Delete to phase " + resourceID + "/" + phaseName + " in "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        JobType job = (JobType) retrieveByPrimaryKey(resourceID, zone, context, metadata,
                customResponseParams);
        if (job == null)
        {
            throw new BadRequestException("No job found with refid " + resourceID);
        }

        PhaseType phase = ServiceUtils.getPhase(job, phaseName);
        if (phase == null)
        {
            throw new BadRequestException(
                    "No phase with name " + phaseName + " found for job with refid " + resourceID);
        }

        if (!ServiceUtils.checkPhaseACL(phase, AccessRight.DELETE, AccessType.APPROVED))
        {
            throw new ForbiddenException();
        }

        PhaseActions actions = phaseActions.get(phase.getName());
        if (actions == null)
        {
            throw new IllegalArgumentException("Unexpected error, phase is known (" + job.getId()
                    + "/" + phase.getName() + "), but has no actions can be found");
        }

        String result = actions.delete(job, phase, payload, requestMediaType, responseMediaType,
                zone, context);

        sif3JobService.save(job);

        return result;
    }

    /*----------------------*/
    /*-- State Operations --*/
    /*----------------------*/
    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#createToState(java.lang.String, java.lang.String, java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public StateType createToState(String resourceID, String phaseName, Object data, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception
    {
        logger.debug("Create to state for phase " + resourceID + "/" + phaseName + " in "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        if (!(data instanceof StateType))
        {
            throw new IllegalArgumentException(
                    "Expected Object Type = StateType. Received Object Type = "
                            + data.getClass().getSimpleName());
        }
        StateType state = (StateType) data;

        JobType job = sif3JobService.getJobById(resourceID);

        if (job == null)
        {
            throw new IllegalArgumentException("No job found with id " + resourceID + ".");
        }

        PhaseType phase = ServiceUtils.getPhase(job, phaseName);

        if (phase == null)
        {
            throw new IllegalArgumentException(
                    "No phase found called " + phaseName + " on job with id " + resourceID + ".");
        }

        ServiceUtils.changePhaseState(job, phase, CommonConstants.PhaseState.valueOf(state.getType().name()),
                state.getDescription());

        job = sif3JobService.save(job);

        if (job == null)
        {
            throw new IllegalArgumentException("Failed to save job with id " + resourceID + ".");
        }

        sendJobEvent(job, EventAction.UPDATE, zone, context);

        state = ServiceUtils.getLastPhaseState(job, phaseName);

        if (state == null)
        {
            throw new IllegalArgumentException("Could not find the most recent state in phase "
                    + phaseName + " on job with id " + resourceID + ".");
        }

        return state;
    }

    /*----------------------*/
    /*-- Head Operations --*/
    /*----------------------*/
    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.provider.BaseProvider#getCustomServiceInfo(sif3.common.model.SIFZone,
     * sif3.common.model.SIFContext, sif3.common.model.PagingInfo,
     * sif3.common.model.RequestMetadata)
     */
    @Override
    public HeaderProperties getCustomServiceInfo(SIFZone zone, SIFContext context,
            PagingInfo pagingInfo, RequestMetadata metadata)
            throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
        return metadata.getHTTPParameters();
    }

    /*----------------------*/
    /*--  Phase Eventing  --*/
    /*----------------------*/
    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#sendJobEvent(sif3.infra.common.model.JobType, sif3.common.header.HeaderValues.EventAction, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public void sendJobEvent(JobType job, EventAction action, SIFZone zone, SIFContext context)
    {
        SIFEvent<JobCollectionType> event = new SIFEvent<JobCollectionType>(new JobCollectionType(),
                action, UpdateType.FULL, 1);
        event.getSIFObjectList().getJob().add(job);
        sifevents.add(new JobEvent(event, zone, context));
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#sendJobEvent(sif3.infra.common.model.JobType, java.lang.String, sif3.common.header.HeaderValues.EventAction, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public void sendJobEvent(JobType job, String phaseName, EventAction action, SIFZone zone,
            SIFContext context)
    {
        SIFEvent<JobCollectionType> event = new SIFEvent<JobCollectionType>(new JobCollectionType(),
                action, UpdateType.PARTIAL, 1);
        event.getSIFObjectList().getJob().add(filter(job, phaseName));
        sifevents.add(new JobEvent(event, zone, context));
    }

    /*--------------------------------------*/
    /*-- Other required Interface Methods --*/
    /*--------------------------------------*/

    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    private String getZoneAndContext(SIFZone zone, SIFContext context)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Zone = ").append(
                (zone == null) ? "null" : zone.getId() + (zone.getIsDefault() ? " (dafault)" : ""))
                .append(" ");
        buffer.append("- Context = ").append((context == null) ? "null" : context.getId());

        return buffer.toString();
    }

    private List<JobType> fetchJobs(List<JobType> jobs, PagingInfo pagingInfo)
    {
        ArrayList<JobType> output = new ArrayList<JobType>();
        if (pagingInfo == null)
        {
            output.addAll(jobs);
        }
        else
        {
            pagingInfo.setTotalObjects(jobs.size());
            if ((pagingInfo.getPageSize() * (pagingInfo.getCurrentPageNo())) > jobs.size())
            {
                // Requested page outside of limits.
                return null;
            }

            // retrieve applicable jobs
            int i = 0;
            int startPos = pagingInfo.getPageSize() * pagingInfo.getCurrentPageNo();
            int endPos = startPos + pagingInfo.getPageSize();
            for (Iterator<JobType> iter = jobs.iterator(); iter.hasNext();)
            {
                JobType job = iter.next();
                if ((i >= startPos) && (i < endPos))
                {
                    output.add(job);
                }
                i++;
            }
        }

        pagingInfo.setPageSize(output.size());

        return output;
    }

    private JobType filter(JobType job, String phaseName)
    {
        JobType newJob = objectFactory.createJobType();
        newJob.setId(job.getId());
        newJob.setName(job.getName());
        /*
         * newJob.setDescription(job.getDescription()); newJob.setCreated(job.getCreated());
         * newJob.setLastModified(job.getLastModified()); newJob.setState(job.getState());
         */
        newJob.setPhases(objectFactory.createPhaseCollectionType());
        newJob.getPhases().getPhase().add(ServiceUtils.getPhase(job, phaseName));
        return newJob;
    }
}
