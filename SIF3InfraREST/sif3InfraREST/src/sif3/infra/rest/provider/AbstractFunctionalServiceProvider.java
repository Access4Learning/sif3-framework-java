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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.BadRequestException;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.ForbiddenException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIF3Exception;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.interfaces.FunctionalServiceProvider;
import sif3.common.interfaces.PhaseActions;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.persist.model.SIF3Job;
import sif3.common.persist.model.SIF3Phase;
import sif3.common.persist.model.SIF3PhaseState;
import sif3.common.persist.service.SIF3JobService;
import sif3.common.utils.JAXBUtils;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.StateType;
import sif3.infra.common.utils.ServiceUtils;

/**
 * Base implementation of A functional service. This should be extended to define what phases a job
 * has and other details about what the Job should know/be able to do/etc.
 */
public abstract class AbstractFunctionalServiceProvider extends BaseProvider
        implements FunctionalServiceProvider
{

    protected final Logger                  logger          = Logger.getLogger(getClass());

    protected SIF3JobService                sif3JobService  = new SIF3JobService();

    private static InfraUnmarshalFactory    unmarshaller    = new InfraUnmarshalFactory();
    private static InfraMarshalFactory      marshaller      = new InfraMarshalFactory();
    private static ObjectFactory            objectFactory   = new ObjectFactory();

    private String                          serviceName;
    protected HashMap<String, PhaseActions> phaseActions;

    private Timer                           jobTimeoutTimer = null;
    private TimerTask                       jobTimeoutTask  = null;

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
    }

    /**
     * Decorates (configures) a given Job instance with all tits phases and other information.
     * 
     * @param job
     *            The job to configure
     */
    protected abstract void configure(SIF3Job job);

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
    protected abstract void shutdownJob(SIF3Job job);

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
        if (jobTimeoutTask != null)
        {
            logger.debug("Shut Down timout task for: " + getProviderName());
            jobTimeoutTask.cancel();
            jobTimeoutTask = null;
        }
        if (jobTimeoutTimer != null)
        {
            logger.debug("Shut Down timeout timer for: " + getProviderName());
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
        int frequencyInSec = getTimeoutFrequency(getServiceName());
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
                        List<SIF3Job> jobs = sif3JobService.getJobs();

                        for (SIF3Job job : jobs)
                        {
                            if (hasTimedout(job))
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
                        }
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * sif3.infra.common.interfaces.FunctionalServiceProvider#acceptJob(sif3.common.persist.model.
     * SIF3Job)
     */
    @Override
    public boolean acceptJob(SIF3Job job)
    {
        return acceptJob(job.getName());
    }

    /*
     * (non-Javadoc)
     * 
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

        return ServiceUtils.marshal(sif3JobService.getJobById(resourceID));
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

        if (!(data instanceof JobType))
        {
            throw new IllegalArgumentException(
                    "Expected Object Type = JobType. Received Object Type = "
                            + data.getClass().getSimpleName());
        }
        SIF3Job job = ServiceUtils.unmarshal((JobType) data);

        initialise(job);
        configure(job);

        if (!useAdvisory)
        {
            job.setId(null);
        }

        if (useAdvisory && StringUtils.isEmpty(job.getId()))
        {
            logger.debug("UseAdvisory is true, but no advisory refid provided.");
        }

        return ServiceUtils.marshal(sif3JobService.save(job));
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

        SIF3Job job = sif3JobService.getJobById(resourceID);

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
        if (!(data instanceof JobCollectionType))
        {
            throw new IllegalArgumentException(
                    "Expected Object of type JobCollectionType. Received Object Type = "
                            + data.getClass().getSimpleName());
        }

        logger.debug("Create jobs (Bulk Operation) for " + getZoneAndContext(zone, context)
                + " and RequestMetadata = " + metadata);
        List<JobType> jobs = ((JobCollectionType) data).getJob();
        ArrayList<CreateOperationStatus> opStatus = new ArrayList<CreateOperationStatus>();
        JobType created = null;
        String advisoryId = null;
        for (JobType job : jobs)
        {
            advisoryId = job.getId();
            created = (JobType) createSingle(job, useAdvisory, zone, context, metadata,
                    customResponseParams);
            if (created == null)
            {
                opStatus.add(new CreateOperationStatus(advisoryId, advisoryId, 404,
                        new ErrorDetails(400, "Data not good.")));
            }
            else
            {
                opStatus.add(new CreateOperationStatus(created.getId(), advisoryId, 201));
            }
        }
        return opStatus;
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
        if (!(data instanceof JobCollectionType))
        {
            throw new IllegalArgumentException(
                    "Expected Object of type JobCollectionType. Received Object Type = "
                            + data.getClass().getSimpleName());
        }

        List<SIF3Job> jobs = ServiceUtils.unmarshal((JobCollectionType) data);
        ArrayList<OperationStatus> opStatus = new ArrayList<OperationStatus>();
        for (SIF3Job job : jobs)
        {
            opStatus.add(new OperationStatus(job.getId(), 400, new ErrorDetails(400,
                    "A direct update not supported on functional services, use phase or state interface for as appropriate.")));
        }
        return opStatus;
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
    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#createToPhase(java.lang.String,
     * java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata,
     * sif3.common.model.ResponseParameters)
     */
    @Override
    public String createToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception
    {
        logger.debug("Create to phase " + resourceID + "/" + phaseName + " in "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        SIF3Job job = ServiceUtils.unmarshal((JobType) retrieveByPrimaryKey(resourceID, zone,
                context, metadata, customResponseParams));

        if (job == null)
        {
            throw new BadRequestException("No job found with refid " + resourceID);
        }

        SIF3Phase phase = ServiceUtils.getPhase(job, phaseName);
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

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#retrieveToPhase(java.lang.String,
     * java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata,
     * sif3.common.model.ResponseParameters)
     */
    @Override
    public String retrieveToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception
    {
        logger.debug("Retrieve to phase " + resourceID + "/" + phaseName + " in "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        SIF3Job job = ServiceUtils.unmarshal((JobType) retrieveByPrimaryKey(resourceID, zone,
                context, metadata, customResponseParams));

        if (job == null)
        {
            throw new BadRequestException("No job found with refid " + resourceID);
        }

        SIF3Phase phase = ServiceUtils.getPhase(job, phaseName);

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

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#updateToPhase(java.lang.String,
     * java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata,
     * sif3.common.model.ResponseParameters)
     */
    @Override
    public String updateToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception
    {
        logger.debug("Update to phase " + resourceID + "/" + phaseName + " in "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        SIF3Job job = ServiceUtils.unmarshal((JobType) retrieveByPrimaryKey(resourceID, zone,
                context, metadata, customResponseParams));

        if (job == null)
        {
            throw new BadRequestException("No job found with refid " + resourceID);
        }

        SIF3Phase phase = ServiceUtils.getPhase(job, phaseName);

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

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.provider.FunctionalServiceProvider#deleteToPhase(java.lang.String,
     * java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata,
     * sif3.common.model.ResponseParameters)
     */
    @Override
    public String deleteToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception
    {
        logger.debug("Delete to phase " + resourceID + "/" + phaseName + " in "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        SIF3Job job = ServiceUtils.unmarshal((JobType) retrieveByPrimaryKey(resourceID, zone,
                context, metadata, customResponseParams));

        if (job == null)
        {
            throw new BadRequestException("No job found with refid " + resourceID);
        }

        SIF3Phase phase = ServiceUtils.getPhase(job, phaseName);

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
    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.common.interfaces.FunctionalServiceProvider#createToState(java.lang.String,
     * java.lang.String, java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext,
     * sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
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

        SIF3PhaseState state = ServiceUtils.unmarshal((StateType) data);

        SIF3Job job = sif3JobService.getJobById(resourceID);

        if (job == null)
        {
            throw new IllegalArgumentException("No job found with id " + resourceID + ".");
        }

        SIF3Phase phase = ServiceUtils.getPhase(job, phaseName);

        if (phase == null)
        {
            throw new IllegalArgumentException(
                    "No phase found called " + phaseName + " on job with id " + resourceID + ".");
        }

        ServiceUtils.changePhaseState(job, phase,
                CommonConstants.PhaseState.valueOf(state.getType().name()), state.getDescription());

        job = sif3JobService.save(job);

        if (job == null)
        {
            throw new IllegalArgumentException("Failed to save job with id " + resourceID + ".");
        }

        state = ServiceUtils.getLastPhaseState(job, phaseName);

        if (state == null)
        {
            throw new IllegalArgumentException("Could not find the most recent state in phase "
                    + phaseName + " on job with id " + resourceID + ".");
        }

        return ServiceUtils.marshal(state);
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

    /*--------------------------------------*/
    /*-- Other required Interface Methods --*/
    /*--------------------------------------*/

    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    /* 0 = No timeout */
    private int getTimeoutFrequency(String providerName)
    {
        return getServiceProperties().getPropertyAsInt(CommonConstants.TIMEOUT_FREQ_PROPERTY,
                providerName, CommonConstants.DEFAULT_TIMEOUT_FREQ);
    }

    private boolean hasTimedout(SIF3Job job)
    {
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar then = (Calendar) now.clone();

        then.add(Calendar.SECOND, (int) job.getTimeout());

        return acceptJob(job) && job.getTimeout() > 0 && now.after(then);
    }

    private String getZoneAndContext(SIFZone zone, SIFContext context)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Zone = ").append(
                (zone == null) ? "null" : zone.getId() + (zone.getIsDefault() ? " (dafault)" : ""))
                .append(" ");
        buffer.append("- Context = ").append((context == null) ? "null" : context.getId());

        return buffer.toString();
    }

    private List<JobType> fetchJobs(List<SIF3Job> jobs, PagingInfo pagingInfo)
    {
        ArrayList<JobType> output = new ArrayList<JobType>();
        if (pagingInfo == null)
        {
            for (SIF3Job job : jobs)
            {
                output.add(ServiceUtils.marshal(job));
            }
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
            for (Iterator<SIF3Job> iter = jobs.iterator(); iter.hasNext();)
            {
                SIF3Job job = iter.next();
                if ((i >= startPos) && (i < endPos))
                {
                    output.add(ServiceUtils.marshal(job));
                }
                i++;
            }
        }

        pagingInfo.setPageSize(output.size());

        return output;
    }

    private void initialise(SIF3Job job)
    {
        Date time = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();

        if (job.getCreated() == null)
        {
            job.setCreated(time);
            job.setLastModified(time);
        }

        if (job.getLastModified() == null)
        {
            job.setLastModified(time);
        }

        if (job.getPhases() == null)
        {
            job.setPhases(new ArrayList<SIF3Phase>());
            return;
        }

        for (SIF3Phase phase : job.getPhases())
        {
            if (phase.getStates() == null)
            {
                phase.setStates(new ArrayList<SIF3PhaseState>());
                continue;
            }

            for (SIF3PhaseState state : phase.getStates())
            {
                if (state.getType() == null)
                {
                    state.setType(PhaseState.NOTSTARTED);
                }
                if (state.getCreated() == null)
                {
                    state.setCreated(time);
                    state.setLastModified(time);
                }
            }
        }
    }
}
