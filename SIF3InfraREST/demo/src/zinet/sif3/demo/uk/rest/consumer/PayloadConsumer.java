
package zinet.sif3.demo.uk.rest.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.core.MediaType;

import au.com.systemic.framework.utils.GUIDGenerator;
import sif.dd.conversion.DataModelMarshalFactory;
import sif.dd.uk20.model.LearnerPersonalType;
import sif.dd.uk20.model.NameType;
import sif.dd.uk20.model.PersonalInformationType;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.model.EventMetadata;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.model.delayed.DelayedResponseReceipt;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.common.ws.model.MultiOperationStatusList;
import sif3.infra.common.ServiceStatus.PhaseState;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.PhaseStateType;
import sif3.infra.common.model.StateType;
import sif3.infra.common.utils.ServiceUtils;
import sif3.infra.rest.consumer.AbstractJobConsumer;
import zinet.sif3.demo.uk.rest.PayloadConstants;

public class PayloadConsumer extends AbstractJobConsumer {

	public PayloadConsumer() {
		super();
		
		// Emulate an external actor issuing consumer actions
		new Timer().schedule(new TimerTask() {

			public void run() {
				ObjectFactory factory = new ObjectFactory();
				JobType job = null;
				StateType state = null;
				List<Response> responses;
				Response r;

				try {
					// Create a job
					logTestHeading("Creating Payload job");
					job = factory.createJobType();
					job.setName("Payload");
					job.setDescription("testing");
					responses = createSingle(job, null);
					r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when creating a job");
					}
					// logger.debug(r.toString());
					job = (JobType) r.getDataObject();
					logResult("Job created and given ID " + job.getId());

					
					// Get a job
					logTestHeading("Fetching job " + job.getId());
					responses = retrieveByPrimaryKey(job.getId(), null);
					r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when retrieving a job");
					}
					job = (JobType) r.getDataObject();
					logResult("Fetched " + job.getName() + " (" + job.getId() + ")");

					
					// Create message to phase default
					logTestHeading("Create message to phase 'default'.");
					responses = createToPhase(job.getId(), "default", "Sending CREATE", MediaType.TEXT_PLAIN_TYPE, MediaType.TEXT_PLAIN_TYPE, null, null);
					r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when calling create on a phase");
					}
					logResult("Phase responded with data with mime type: " + r.getMediaType() + " and data:\n" + r.getDataObject());

					
					// Query phase "default", expecting INPROGRESS
					logTestHeading("Check state of phase 'default', expecting INPROGRESS.");
					responses = retrieveByPrimaryKey(job.getId(), null);
					r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when retrieving a job");
					}
					job = (JobType) r.getDataObject();
					state = ServiceUtils.getLastPhaseState(job, "default");
					if(state == null) {
						logResult("Got UNEXPECTED result, no state object!");
					} else {
	          if (state.getType().equals(PhaseState.INPROGRESS.name()))
	          {
	          	logResult("Got EXPECTED result, last modified at " + state.getLastModified().getTime());
	          }
	          else
	          {
	          	logResult("Got UNEXPECTED result " + state.getType() + ", last modified at " + state.getLastModified().getTime());
	          }
					}
					
					
          // Execute UPDATE to phase "default".
          logTestHeading("Executing UPDATE to phase 'default'.");
          responses = updateToPhase(job.getId(), "default", "Sending UPDATE", MediaType.TEXT_PLAIN_TYPE, MediaType.TEXT_PLAIN_TYPE, null, null);
					r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when calling update on a phase");
					}
					logResult("Phase responded with data with mime type: " + r.getMediaType() + " and data:\n" + r.getDataObject());
          

					// Query phase "default", expecting COMPLETED
					logTestHeading("Check state of phase 'default', expecting COMPLETED.");
					responses = retrieveByPrimaryKey(job.getId(), null);
					r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when retrieving a job");
					}
					job = (JobType) r.getDataObject();
					state = ServiceUtils.getLastPhaseState(job, "default");
					if(state == null) {
						logResult("Got UNEXPECTED result, no state object!");
					} else {
	          if (state.getType().equals(PhaseState.COMPLETED.name()))
	          {
	          	logResult("Got EXPECTED result, last modified at " + state.getLastModified().getTime());
	          }
	          else
	          {
	          	logResult("Got UNEXPECTED result " + state.getType() + ", last modified at " + state.getLastModified().getTime());
	          }
					}

          // Execute DELETE to phase "default".
          logTestHeading("Executing DELETE to phase 'default'.");
          responses = deleteToPhase(job.getId(), "default", "Sending DELETE", MediaType.TEXT_PLAIN_TYPE, MediaType.TEXT_PLAIN_TYPE, null, null);
					r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when calling delete on a phase");
					}
					if(r.hasError()) {
						logResult("EXPECTED error obtained (HTTP:" + r.getStatus() + "), provider responded with:\nCode: "+r.getError().getErrorCode()+"\nMessage: "+r.getError().getMessage()+"\nDescription: "+r.getError().getDescription());
					} else {
						logResult("UNEXPECTED - no error obtained!");
					}

					
          // Execute UPDATE to phase "xml".
          logTestHeading("Executing UPDATE to phase 'xml'.");
          String xml = new DataModelMarshalFactory(sif.dd.uk20.model.ObjectFactory.class).marshalToXML(getBruceWayne());
          responses = updateToPhase(job.getId(), "xml", xml, MediaType.APPLICATION_XML_TYPE, MediaType.TEXT_PLAIN_TYPE, null, null);
					r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when calling create on a phase");
					}
					logResult("Phase responded with data with mime type: " + r.getMediaType() + " and data:\n" + r.getDataObject());
          

          // Execute UPDATE to phase "json".
          logTestHeading("Executing UPDATE to phase 'json'.");
          String json = new DataModelMarshalFactory(sif.dd.uk20.model.ObjectFactory.class).marshalToJSON(getBruceWayne());
          responses = updateToPhase(job.getId(), "json", json, MediaType.APPLICATION_JSON_TYPE, MediaType.TEXT_PLAIN_TYPE, null, null);
					r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when calling create on a phase");
					}
					logResult("Phase responded with data with mime type: " + r.getMediaType() + " and data:\n" + r.getDataObject());

					// Change state of phase "json".
					logTestHeading("Executing CREATE to the state of phase 'json'.");
					StateType failed = new StateType();
					failed.setType(PhaseStateType.FAILED);
					failed.setDescription("Because I want it to");
          responses = createToState(job.getId(), "json", failed, null, null);  
          r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when calling create to state on a phase");
					}
					state = (StateType) r.getDataObject();
					if(state == null) {
						logResult("Got UNEXPECTED result, no state object!");
					} else {
	          if (state.getType().equals(PhaseState.FAILED.name()))
	          {
	          	logResult("Got EXPECTED result, last modified at " + state.getLastModified().getTime());
	          }
	          else
	          {
	          	logResult("Got UNEXPECTED result " + state.getType() + ", last modified at " + state.getLastModified().getTime());
	          }
					}
					
				// Query phase "json", expecting FAILED
					logTestHeading("Check state of phase 'json', expecting FAILED.");
					responses = retrieveByPrimaryKey(job.getId(), null);
					r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when retrieving a job");
					}
					job = (JobType) r.getDataObject();
					state = ServiceUtils.getLastPhaseState(job, "json");
					if(state == null) {
						logResult("Got UNEXPECTED result, no state object!");
					} else {
	          if (state.getType().equals(PhaseState.FAILED.name()))
	          {
	          	logResult("Got EXPECTED result, last modified at " + state.getLastModified().getTime());
	          }
	          else
	          {
	          	logResult("Got UNEXPECTED result " + state.getType() + ", last modified at " + state.getLastModified().getTime());
	          }
					}
					
					// Delete a job
          logTestHeading("Deleting created job with ID " + job.getId());
					responses = deleteSingle(job.getId(), null);
					r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when deleting a job");
					}
					responses = retrieveByPrimaryKey(job.getId(), null);
					r = responses.isEmpty() ? null : responses.get(0);
					if (r == null) {
						logger.error("Didn't get a response when retrieving a job");
					}
					job = (JobType)r.getDataObject();
					if(job == null) {
						logResult("Job deleted successfully");
					} else {
						logResult("FAILED TO DELETE JOB");
					}
					
					// Create many jobs
          logTestHeading("Creating many jobs");
          JobCollectionType collection = new JobCollectionType();
          for(int i = 0; i < 5; i++) {
          	JobType j = new JobType();
          	j.setName("Payload");
          	collection.getJob().add(j);
          }
          
          List<BulkOperationResponse<CreateOperationStatus>> creates = createMany(collection, null, null);

          logger.info("Processing multiple job creation:");
          List<String> ids = new ArrayList<String>();
          for(BulkOperationResponse<CreateOperationStatus> op : creates) {
          	if(op.hasError()) {
          		logger.error("Error creating jobs: " + op.getError().getMessage() + "(" + op.getError().getErrorCode() + ")");
          	} else {
          		for(CreateOperationStatus status : op.getOperationStatuses()) {
              	if(status.getStatus() >= 200 && status.getStatus() < 300) {
              		logger.info("++ " + status.getResourceID() + " created (" + status.getStatus() + ")");
              		ids.add(status.getResourceID());
              	} else {
              		ErrorDetails e = status.getError();
              		logger.error("-- " + e.getMessage() + "(" + e.getErrorCode() + ")");
              	}
              }
          	}
          }

					// Delete many jobs
          logTestHeading("Deleting many jobs");
          // Change a random job's id, which will result in:
          // 1) The missing job will be removed by the timeout
          // 2) We expect a single job to have an error when being removed
          ids.set(new Random().nextInt(ids.size()), GUIDGenerator.getRandomGUID());

          logger.info("Processing multiple job deletion:");
          List<BulkOperationResponse<OperationStatus>> deletes = deleteMany(ids, null, null);
          for(BulkOperationResponse<OperationStatus> op : deletes) {
          	if(op.hasError()) {
          		logger.error("Error deleting jobs: " + op.getError().getMessage() + "(" + op.getError().getErrorCode() + ")");
          	} else {
          		for(OperationStatus status : op.getOperationStatuses()) {
          			if(status.getStatus() >= 200 && status.getStatus() < 300) {
              		logger.info("++ " + status.getResourceID() + " deleted (" + status.getStatus() + ")");
              		ids.add(status.getResourceID());
              	} else {
              		ErrorDetails e = status.getError();
              		logger.error("-- " + e.getMessage() + "(" + e.getErrorCode() + ")");
              	}
              }
          	}
          }
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}, 5000);
	}
	
	private void logTestHeading(String message) {
		logger.info("\n\n*** " + message + "\n");
	}
	
	private void logResult(String message) {
		logger.info("\n\n+++ " + message + "\n");
	}
	
	private LearnerPersonalType getBruceWayne() {
		sif.dd.uk20.model.ObjectFactory factory = new sif.dd.uk20.model.ObjectFactory();
		LearnerPersonalType bruce = factory.createLearnerPersonalType();
		bruce.setLocalId(factory.createLocalId("555"));

		PersonalInformationType pi = factory.createPersonalInformationType();
		NameType bname = factory.createNameType();
		bname.setType("C");
		bname.setFamilyName(factory.createNameTypeFamilyName("Wayne"));
		bname.setGivenName(factory.createNameTypeGivenName("Bruce"));
		pi.setName(bname);
		bruce.setPersonalInformation(pi);
		return bruce;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
	 */
	@Override
	public ModelObjectInfo getSingleObjectClassInfo() {
		return PayloadConstants.PAYLOAD;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
	 */
	@Override
	public ModelObjectInfo getMultiObjectClassInfo() {
		return PayloadConstants.PAYLOADS;
	}

	/*----------------------------------------------------------------------------*/
	/*-- Abstract Consumer Methods: Dummy Implementation - Just log the values. --*/
	/*----------------------------------------------------------------------------*/

	/*
	 * (non-Javadoc)
	 * @see
	 * sif3.common.interfaces.EventConsumer#onEvent(sif3.common.model.SIFEvent,
	 * sif3.common.model.SIFZone, sif3.common.model.SIFContext, java.lang.String)
	 */
	@Override
	public void processEvent(SIFEvent<JobCollectionType> sifEvent, SIFZone zone, SIFContext context, EventMetadata metadata, String msgReadID, String consumerID) {
		String consumerName = getPrettyName() + "(QueueID:" + msgReadID + "; ConsumerID: " + consumerID + ")";
		logResult(consumerName + " received an event from Zone = " + zone.getId() + ", Context = " + context.getId() + " and Event Metadata = " + metadata);
	}
	
	@Override
	public void processDelayedCreateMany(MultiOperationStatusList<CreateOperationStatus> statusList, DelayedResponseReceipt receipt) {
		logger.debug("Received DELAYED CREATE Response:\n" + statusList + "\nDelayed Receipt Details:\n" + receipt);
	}

	@Override
	public void processDelayedUpdateMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt) {
		logger.debug("Received DELAYED UPDATE Response:\n" + statusList + "\nDelayed Receipt Details:\n" + receipt);
	}

	@Override
	public void processDelayedDeleteMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt) {
		logger.debug("Received DELAYED DELETE Response:\n" + statusList + "\nDelayed Receipt Details:\n" + receipt);
	}

	@Override
	public void processDelayedQuery(Object dataObject, PagingInfo pagingInfo, DelayedResponseReceipt receipt) {
		logger.debug("Received DELAYED QUERY Response:\n" + dataObject + "\nPagingInfo:\n" + pagingInfo + "\nDelayed Receipt Details:\n" + receipt);
	}

	@Override
	public void processDelayedServicePath(Object dataObject, QueryCriteria queryCriteria, PagingInfo pagingInfo, DelayedResponseReceipt receipt) {
		logger.debug("Received DELAYED SERVICEPATH Response:\n" + dataObject + "\nQuery Criteria:\n" + queryCriteria + "\nPagingInfo:\n" + pagingInfo + "\nDelayed Receipt Details:\n" + receipt);
	}

	@Override
	public void processDelayedError(ErrorDetails error, DelayedResponseReceipt receipt) {
		logger.debug("Received DELAYED ERROR Response:\n" + error + "\nDelayed Receipt Details:\n" + receipt);
	}
}
