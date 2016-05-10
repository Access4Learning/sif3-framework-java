
package sif3.infra.rest.functional;

import sif3.common.header.HeaderValues.EventAction;
import sif3.infra.common.model.JobType;

public class JobEvent {

	// SIFZone zone;
	// SIFContext context;
	JobType job;
	EventAction action;

	// public JobEvent(JobType job, EventAction action, SIFZone zone, SIFContext
	// context) {
	public JobEvent(JobType job, EventAction action) {
		this.job = job;
		this.action = action;
		// this.zone = zone;
		// this.context = context;
	}

	public JobType getJob() {
		return job;
	}

	public EventAction getEventAction() {
		return action;
	}

	// public SIFZone getZone() {
	// return zone;
	// }

	// public SIFContext getContext() {
	// return context;
	// }
}
