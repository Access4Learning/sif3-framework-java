package zinet.sif3.demo.uk.rest.provider.actions;

import javax.ws.rs.core.MediaType;

import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.infra.common.ServiceStatus.JobState;
import sif3.infra.common.ServiceStatus.PhaseState;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.PhaseType;
import sif3.infra.common.utils.ServiceUtils;
import sif3.infra.rest.functional.BasePhaseActions;
import sif3.infra.rest.provider.BaseFunctionalServiceProvider;

public class DefaultActions extends BasePhaseActions {
	public DefaultActions(BaseFunctionalServiceProvider provider) {
		super(provider);
	}
	
	@Override
	public String create(JobType job, PhaseType phase, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException, UnmarshalException, UnsupportedMediaTypeException, UnsupportedQueryException {
		ServiceUtils.changeJobState(job, JobState.INPROGRESS, "CREATE to " + phase.getName());
		ServiceUtils.changePhaseState(job, phase, PhaseState.INPROGRESS, "CREATE");
		
		getProvider().sendJobEvent(job, phase.getName(), EventAction.UPDATE, zone, context);
		
		return "Got CREATE message for " + phase.getName() + "@" + job.getId() + " with content type " + requestMediaType.toString() + " and accept " + responseMediaType.toString() + ".\nBODY START\n" + payload + ".\nBODY END.";
	}

	@Override
	public String retrieve(JobType job, PhaseType phase, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context) {
		ServiceUtils.changeJobState(job, JobState.INPROGRESS, "RETRIEVE to " + phase.getName());
		ServiceUtils.changePhaseState(job, phase, PhaseState.INPROGRESS, "RETRIEVE");
		
		getProvider().sendJobEvent(job, phase.getName(), EventAction.UPDATE, zone, context);
		
		return "Got RETRIEVE message for " + phase.getName() + "@" + job.getId() + " with accept " + responseMediaType.toString() + ".";
	}

	@Override
	public String update(JobType job, PhaseType phase, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context) {
		ServiceUtils.changeJobState(job, JobState.INPROGRESS, "UPDATE to " + phase.getName());
		ServiceUtils.changePhaseState(job, phase, PhaseState.COMPLETED, "UPDATE");
		
		getProvider().sendJobEvent(job, phase.getName(), EventAction.UPDATE, zone, context);
		
		return "Got UPDATE message for " + phase.getName() + "@" + job.getId() + " with content type " + requestMediaType.toString() + " and accept " + responseMediaType.toString() + ".\nBODY START\n" + payload + ".\nBODY END.";
	}

	@Override
	public String delete(JobType job, PhaseType phase, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context) {
		ServiceUtils.changeJobState(job, JobState.INPROGRESS, "DELETE to " + phase.getName());
		ServiceUtils.changePhaseState(job, phase, PhaseState.COMPLETED, "DELETE");
		
		getProvider().sendJobEvent(job, phase.getName(), EventAction.UPDATE, zone, context);
		
		return "Got DELETE message for " + phase.getName() + "@" + job.getId() + " with content type " + requestMediaType.toString() + " and accept " + responseMediaType.toString() + ".\nBODY START\n" + payload + ".\nBODY END.";
	}
}
