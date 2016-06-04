
package sif3.infra.rest.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.PhaseType;
import sif3.infra.common.persist.service.SIF3JobService;
import sif3.infra.common.utils.ServiceUtils;
import sif3.infra.rest.functional.IPhaseActions;
import sif3.infra.rest.functional.JobEvent;
import sif3.infra.rest.functional.JobEvents;
import sif3.infra.rest.functional.JobIterator;

// TODO Check all methods to ensure they do what is expected rather than
// returning fake data
public abstract class BaseJobProvider extends BaseEventProvider<JobCollectionType> {

	protected final Logger logger = Logger.getLogger(getClass());

	protected SIF3JobService sif3JobService = new SIF3JobService();

	private static InfraUnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
	private static InfraMarshalFactory marshaller = new InfraMarshalFactory();
	private static ObjectFactory objectFactory = new ObjectFactory();

	private String serviceName;
	protected HashMap<String, IPhaseActions> phaseActions;

	private static JobEvents sifevents = null;

	public BaseJobProvider(String serviceName) {
		super();

		this.serviceName = serviceName;
		phaseActions = new HashMap<String, IPhaseActions>();

		JAXBUtils.initCtx(getMultiObjectClassInfo().getObjectType());
		JAXBUtils.initCtx(getSingleObjectClassInfo().getObjectType());

		if (sifevents == null) {
			logger.debug("Constructor for AbstractJobProvider called for the first time...");
			sifevents = new JobEvents();
		}
	}

	protected abstract void addPhases(JobType job);

	protected abstract void shutdownJob(JobType job);

	public MarshalFactory getMarshaller() {
		return marshaller;
	}

	public UnmarshalFactory getUnmarshaller() {
		return unmarshaller;
	}

	@override
	public String getServiceName() {
		return serviceName;
	}
	
	@Override
	public ServiceType getServiceType() {
		return ServiceType.FUNCTIONAL;
	}

	/*-------------------------------------*/
	/*-- EventProvider Interface Methods --*/
	/*-------------------------------------*/
	/*
	 * (non-Javadoc) @see sif3.common.interfaces.EventProvider#getSIFEvents()
	 */
	@Override
	public SIFEventIterator<JobCollectionType> getSIFEvents() {
		if(sifevents.isEmpty()) {
			return null;
		}
		JobIterator itr = new JobIterator(getServiceName(), getServiceProperties(), sifevents.getAllEvents());
		return itr;
	}

	/*
	 * (non-Javadoc) @see
	 * sif3.common.interfaces.EventProvider#onEventError(sif3.common.model.
	 * SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
	 */
	@Override
	public void onEventError(SIFEvent<JobCollectionType> sifEvent, SIFZone zone, SIFContext context) {
		// We need to deal with the error. At this point in time we just log it.
		if ((sifEvent != null) && (sifEvent.getSIFObjectList() != null)) {
			try {
				String eventXML = getMarshaller().marshalToXML(sifEvent.getSIFObjectList());
				logger.error("Failed to sent the following Objects as and Event to Zone (" + zone + ") and Context (" + context + "):\n" + eventXML);
			} catch (Exception ex) {
				logger.error("Failed to marshall events.", ex);
			}
		} else {
			logger.error("sifEvent Object is null, or there are no events on sifEvent.sifObjectList");
		}

	}

	/*
	 * (non-Javadoc) @see
	 * sif3.common.interfaces.EventProvider#modifyBeforeSent(sif3.common.model.
	 * SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
	 */
	@Override
	public SIFEvent<JobCollectionType> modifyBeforePublishing(SIFEvent<JobCollectionType> sifEvent, SIFZone zone, SIFContext context, HeaderProperties customHTTPHeaders) {
		JobEvent jobEvent = sifevents.get(sifEvent);
		if(jobEvent == null) {
			// Don't know anything about this event
			return null;
		}
		
		if(!jobEvent.getZone().equals(zone)) {
			// Not the right zone to send this to
			return null;
		}
		
		if(!jobEvent.getContext().equals(context)) {
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
	 * (non-Javadoc) @see
	 * sif3.common.interfaces.Provider#retrieveByPrimaryKey(java.lang.String,
	 * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
	 */
	@Override
	public Object retrieveByPrimaryKey(String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException {
		if (StringUtils.isEmpty(resourceID)) {
			throw new IllegalArgumentException("Resource ID is null or empty. It must be provided to retrieve an entity.");
		}

		logger.debug("Retrieve job with Resource ID = " + resourceID + ", " + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

		return sif3JobService.getJobById(resourceID);
	}

	/*
	 * (non-Javadoc) @see
	 * sif3.common.interfaces.Provider#createSingle(java.lang.Object,
	 * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
	 */
	@Override
	public Object createSingle(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException {
		logger.debug("Create single job for " + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

		if (data instanceof JobType) {
			JobType job = (JobType) data;

			addPhases(job);

			if (!useAdvisory) {
				job.setId(null);
			} else {
				if (StringUtils.isEmpty(job.getId())) {
					logger.debug("UseAdvisory is true, but no advisory refid provided.");
				}
			}

			sif3JobService.save(job);

			sendJobEvent(job, EventAction.CREATE, zone, context);

			return job;
		} else {
			throw new IllegalArgumentException("Expected Object Type = JobType. Received Object Type = " + data.getClass().getSimpleName());
		}
	}

	/*
	 * (non-Javadoc) @see
	 * sif3.common.interfaces.Provider#retrive(sif3.common.model.SIFZone,
	 * sif3.common.model.SIFContext, sif3.common.model.PagingInfo)
	 */
	@Override
	public Object retrieve(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException {
		logger.debug("Retrieve jobs for " + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);
		logger.debug("ChangedSince Date: " + metadata.getRequestParameter("ChangedSince"));
		logger.debug("Custom HTTP Header (customHdr): " + metadata.getHTTPParameter("customHdr"));

		if (pagingInfo == null) {
			throw new DataTooLargeException("No paging info is provided. Please provide navigationPage and navigationPageSize.");
		} else {
			if (pagingInfo.getCurrentPageNo() == CommonConstants.FIRST_PAGE) {
				pagingInfo.setNavigationId(UUIDGenerator.getUUID());
			}
		}

		JobCollectionType jobCollection = objectFactory.createJobCollectionType();

		jobCollection.getJob().addAll(fetchJobs(sif3JobService.getJobs(), pagingInfo));

		return jobCollection;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.QueryProvider#retrieveByQBE(java.lang.Object,
	 * sif3.common.model.SIFZone, sif3.common.model.SIFContext,
	 * sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
	 */
	public Object retrieveByQBE(Object exampleObject, SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException {
		throw new UnsupportedQueryException("QBE not supported for Jobs");
	}

	/*
	 * (non-Javadoc) @see
	 * sif3.common.interfaces.Provider#updateSingle(java.lang.Object,
	 * java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
	 */
	@Override
	public boolean updateSingle(Object data, String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException {
		return false;
	}

	/*
	 * (non-Javadoc) @see
	 * sif3.common.interfaces.Provider#deleteSingle(java.lang.String,
	 * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
	 */
	@Override
	public boolean deleteSingle(String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException {
		if (StringUtils.isEmpty(resourceID)) {
			throw new IllegalArgumentException("Resource ID is null or empty. It must be provided to delete an entity.");
		}

		logger.debug("Remove job with Resource ID = " + resourceID + ", " + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

		JobType job = (JobType) retrieveByPrimaryKey(resourceID, zone, context, metadata);
		try {
			shutdownJob(job);
		} catch (Exception e) {
			logger.error("Unable to shutdown job with ID " + job.getId(), e);
			return false;
		}

		sif3JobService.removeJobById(resourceID);
		
		sendJobEvent(job, EventAction.DELETE, zone, context);
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.Provider#createMany(java.lang.Object,
	 * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
	 */
	@Override
	public List<CreateOperationStatus> createMany(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException {
		if (data instanceof JobCollectionType) {
			logger.debug("Create jobs (Bulk Operation) for " + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);
			JobCollectionType jobCollection = (JobCollectionType) data;
			ArrayList<CreateOperationStatus> opStatus = new ArrayList<CreateOperationStatus>();
			Object created = null;
			String advisoryId = null;
			for (JobType job : jobCollection.getJob()) {
				advisoryId = job.getId();
				created = createSingle(job, useAdvisory, zone, context, metadata);
				if (created == null) {
					opStatus.add(new CreateOperationStatus(advisoryId, advisoryId, 404, new ErrorDetails(400, "Data not good.")));
				} else {
					opStatus.add(new CreateOperationStatus(job.getId(), advisoryId, 201));
				}
			}
			return opStatus;
		} else {
			throw new IllegalArgumentException("Expected Object of type JobCollectionType. Received Object Type = " + data.getClass().getSimpleName());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.Provider#updateMany(java.lang.Object,
	 * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
	 */
	@Override
	public List<OperationStatus> updateMany(Object data, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException {
		return new ArrayList<OperationStatus>();
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.Provider#deleteMany(java.lang.Object,
	 * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
	 */
	@Override
	public List<OperationStatus> deleteMany(List<String> resourceIDs, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException {
		logger.debug("Delete Jobs (Bulk Operation) for " + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

		ArrayList<OperationStatus> opStatus = new ArrayList<OperationStatus>();
		for (String resourceID : resourceIDs) {
			if (deleteSingle(resourceID, zone, context, metadata)) {
				opStatus.add(new OperationStatus(resourceID, 200));
			} else {
				opStatus.add(new OperationStatus(resourceID, 404, new ErrorDetails(404, "Job with GUID = " + resourceID + " does not exist.")));
			}
		}
		return opStatus;
	}

	/*----------------------*/
	/*-- Phase Operations --*/
	/*----------------------*/

	public String createToPhase(String resourceID, String phaseName, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context, RequestMetadata metadata) throws SIF3Exception {
		logger.debug("Create to phase " + resourceID + "/" + phaseName + " in " + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

		JobType job = (JobType) retrieveByPrimaryKey(resourceID, zone, context, metadata);
		if (job == null) {
			throw new BadRequestException("No job found with refid " + resourceID);
		}

		PhaseType phase = ServiceUtils.getPhase(job, phaseName);
		if (phase == null) {
			throw new BadRequestException("No phase with name " + phaseName + " found for job with refid " + resourceID);
		}

		if (!ServiceUtils.checkPhaseACL(phase, AccessRight.CREATE, AccessType.APPROVED)) {
			throw new ForbiddenException();
		}

		IPhaseActions actions = phaseActions.get(phase.getName());
		if (actions == null) {
			throw new IllegalArgumentException("Unexpected error, phase is known (" + job.getId() + "/" + phase.getName() + "), but has no actions can be found");
		}

		String result = actions.create(job, phase, payload, requestMediaType, responseMediaType, zone, context);
		
		sif3JobService.save(job);
		
		return result;
	}

	public String retrieveToPhase(String resourceID, String phaseName, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context, RequestMetadata metadata) throws SIF3Exception {
		logger.debug("Retrieve to phase " + resourceID + "/" + phaseName + " in " + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

		JobType job = (JobType) retrieveByPrimaryKey(resourceID, zone, context, metadata);
		if (job == null) {
			throw new BadRequestException("No job found with refid " + resourceID);
		}

		PhaseType phase = ServiceUtils.getPhase(job, phaseName);
		if (phase == null) {
			throw new BadRequestException("No phase with name " + phaseName + " found for job with refid " + resourceID);
		}

		if (!ServiceUtils.checkPhaseACL(phase, AccessRight.QUERY, AccessType.APPROVED)) {
			throw new ForbiddenException();
		}

		IPhaseActions actions = phaseActions.get(phase.getName());
		if (actions == null) {
			throw new IllegalArgumentException("Unexpected error, phase is known (" + job.getId() + "/" + phase.getName() + "), but has no actions can be found");
		}

		String result = actions.retrieve(job, phase, payload, requestMediaType, responseMediaType, zone, context);

		// A get to a phase may have potentially modified the job state
		sif3JobService.save(job);
		
		return result;
	}

	public String updateToPhase(String resourceID, String phaseName, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context, RequestMetadata metadata) throws SIF3Exception {
		logger.debug("Update to phase " + resourceID + "/" + phaseName + " in " + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

		JobType job = (JobType) retrieveByPrimaryKey(resourceID, zone, context, metadata);
		if (job == null) {
			throw new BadRequestException("No job found with refid " + resourceID);
		}

		PhaseType phase = ServiceUtils.getPhase(job, phaseName);
		if (phase == null) {
			throw new BadRequestException("No phase with name " + phaseName + " found for job with refid " + resourceID);
		}

		if (!ServiceUtils.checkPhaseACL(phase, AccessRight.UPDATE, AccessType.APPROVED)) {
			throw new ForbiddenException();
		}

		IPhaseActions actions = phaseActions.get(phase.getName());
		if (actions == null) {
			throw new IllegalArgumentException("Unexpected error, phase is known (" + job.getId() + "/" + phase.getName() + "), but has no actions can be found");
		}

		String result = actions.update(job, phase, payload, requestMediaType, responseMediaType, zone, context);
		
		sif3JobService.save(job);
		
		return result;
	}

	public String deleteToPhase(String resourceID, String phaseName, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context, RequestMetadata metadata) throws SIF3Exception {
		logger.debug("Delete to phase " + resourceID + "/" + phaseName + " in " + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

		JobType job = (JobType) retrieveByPrimaryKey(resourceID, zone, context, metadata);
		if (job == null) {
			throw new BadRequestException("No job found with refid " + resourceID);
		}

		PhaseType phase = ServiceUtils.getPhase(job, phaseName);
		if (phase == null) {
			throw new BadRequestException("No phase with name " + phaseName + " found for job with refid " + resourceID);
		}

		if (!ServiceUtils.checkPhaseACL(phase, AccessRight.DELETE, AccessType.APPROVED)) {
			throw new ForbiddenException();
		}

		IPhaseActions actions = phaseActions.get(phase.getName());
		if (actions == null) {
			throw new IllegalArgumentException("Unexpected error, phase is known (" + job.getId() + "/" + phase.getName() + "), but has no actions can be found");
		}

		String result = actions.delete(job, phase, payload, requestMediaType, responseMediaType, zone, context);
		
		sif3JobService.save(job);
		
		return result;
	}

	/*----------------------*/
	/*-- Head Operations --*/
	/*----------------------*/

	@Override
	public HeaderProperties getCustomServiceInfo(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException {
		return metadata.getHTTPParameters();
	}
	
	/*----------------------*/
	/*--  Phase Eventing  --*/
	/*----------------------*/
	
	public void sendJobEvent(JobType job, EventAction action, SIFZone zone, SIFContext context) {
		SIFEvent<JobCollectionType> event = new SIFEvent<JobCollectionType>(new JobCollectionType(), action, UpdateType.FULL, 1);
		event.getSIFObjectList().getJob().add(job);
		sifevents.add(new JobEvent(event, zone, context));
	}
	
	public void sendJobEvent(JobType job, String phaseName, EventAction action, SIFZone zone, SIFContext context) {
		SIFEvent<JobCollectionType> event = new SIFEvent<JobCollectionType>(new JobCollectionType(), action, UpdateType.PARTIAL, 1);
		event.getSIFObjectList().getJob().add(filter(job, phaseName));
		sifevents.add(new JobEvent(event, zone, context));
	}

	/*--------------------------------------*/
	/*-- Other required Interface Methods --*/
	/*--------------------------------------*/

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private String getZoneAndContext(SIFZone zone, SIFContext context) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Zone = ").append((zone == null) ? "null" : zone.getId() + (zone.getIsDefault() ? " (dafault)" : "")).append(" ");
		buffer.append("- Context = ").append((context == null) ? "null" : context.getId());

		return buffer.toString();
	}

	private List<JobType> fetchJobs(List<JobType> jobs, PagingInfo pagingInfo) {
		ArrayList<JobType> output = new ArrayList<JobType>();
		if (pagingInfo == null) {
			output.addAll(jobs);
		} else {
			pagingInfo.setTotalObjects(jobs.size());
			if ((pagingInfo.getPageSize() * (pagingInfo.getCurrentPageNo())) > jobs.size()) {
				// Requested page outside of limits.
				return null;
			}

			// retrieve applicable jobs
			int i = 0;
			int startPos = pagingInfo.getPageSize() * pagingInfo.getCurrentPageNo();
			int endPos = startPos + pagingInfo.getPageSize();
			for (Iterator<JobType> iter = jobs.iterator(); iter.hasNext();) {
				JobType job = iter.next();
				if ((i >= startPos) && (i < endPos)) {
					output.add(job);
				}
				i++;
			}
		}

		pagingInfo.setPageSize(output.size());

		return output;
	}
	
	private JobType filter(JobType job, String phaseName) {
		JobType newJob = objectFactory.createJobType();
		newJob.setId(job.getId());
		newJob.setName(job.getName());
		/*
		newJob.setDescription(job.getDescription());
		newJob.setCreated(job.getCreated());
		newJob.setLastModified(job.getLastModified());
		newJob.setState(job.getState());
		*/
		newJob.setPhases(objectFactory.createPhaseCollectionType());
		newJob.getPhases().getPhase().add(ServiceUtils.getPhase(job, phaseName));
		return newJob;
	}
}
