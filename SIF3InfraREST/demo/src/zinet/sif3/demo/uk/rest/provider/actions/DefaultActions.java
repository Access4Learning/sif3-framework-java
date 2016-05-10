package zinet.sif3.demo.uk.rest.provider.actions;

import javax.ws.rs.core.MediaType;

import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.infra.common.ServiceStatus.JobState;
import sif3.infra.common.ServiceStatus.PhaseState;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.PhaseType;
import sif3.infra.common.utils.ServiceUtils;
import sif3.infra.rest.functional.BasePhaseActions;

public class DefaultActions extends BasePhaseActions {

	@Override
	public String create(JobType job, PhaseType phase, String data, MediaType requestMediaType, MediaType responseMediaType) throws IllegalArgumentException, PersistenceException, UnmarshalException, UnsupportedMediaTypeException, UnsupportedQueryException {
		ServiceUtils.changeJobState(job, JobState.INPROGRESS, "CREATE to " + phase.getName());
		ServiceUtils.changePhaseState(job, phase, PhaseState.INPROGRESS, "CREATE");
		return "Got CREATE message for " + phase.getName() + "@" + job.getId() + " with content type " + requestMediaType.toString() + " and accept " + responseMediaType.toString() + ".\nBODY START\n" + data + ".\nBODY END.";
	}

	@Override
	public String retrieve(JobType job, PhaseType phase, MediaType responseMediaType) {
		ServiceUtils.changeJobState(job, JobState.INPROGRESS, "RETRIEVE to " + phase.getName());
		ServiceUtils.changePhaseState(job, phase, PhaseState.INPROGRESS, "RETRIEVE");
		return "Got RETRIEVE message for " + phase.getName() + "@" + job.getId() + " with accept " + responseMediaType.toString() + ".";
	}

	@Override
	public String update(JobType job, PhaseType phase, String data, MediaType requestMediaType, MediaType responseMediaType) {
		ServiceUtils.changeJobState(job, JobState.INPROGRESS, "UPDATE to " + phase.getName());
		ServiceUtils.changePhaseState(job, phase, PhaseState.COMPLETED, "UPDATE");
		return "Got UPDATE message for " + phase.getName() + "@" + job.getId() + " with content type " + requestMediaType.toString() + " and accept " + responseMediaType.toString() + ".\nBODY START\n" + data + ".\nBODY END.";
	}

	@Override
	public String delete(JobType job, PhaseType phase, String data, MediaType requestMediaType, MediaType responseMediaType) {
		ServiceUtils.changeJobState(job, JobState.INPROGRESS, "DELETE to " + phase.getName());
		ServiceUtils.changePhaseState(job, phase, PhaseState.COMPLETED, "DELETE");
		return "Got DELETE message for " + phase.getName() + "@" + job.getId() + " with content type " + requestMediaType.toString() + " and accept " + responseMediaType.toString() + ".\nBODY START\n" + data + ".\nBODY END.";
	}
}
