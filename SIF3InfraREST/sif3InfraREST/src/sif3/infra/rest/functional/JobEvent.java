
package sif3.infra.rest.functional;

import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.infra.common.model.JobCollectionType;

public class JobEvent {
	private SIFEvent<JobCollectionType> event;
	private SIFZone zone;
	private SIFContext context;

	public JobEvent() {
		super();
	}
	
	public JobEvent(SIFEvent<JobCollectionType> event) {
		this();
		this.event = event;
	}
	
	public JobEvent(SIFEvent<JobCollectionType> event, SIFZone zone, SIFContext context) {
		this(event);
		this.zone = zone;
		this.context = context;
	}
	
	/**
	 * @return the event
	 */
	public SIFEvent<JobCollectionType> getEvent() {
		return event;
	}

	
	/**
	 * @param event the event to set
	 */
	public void setEvent(SIFEvent<JobCollectionType> event) {
		this.event = event;
	}

	/**
	 * @return the zone
	 */
	public SIFZone getZone() {
		return zone;
	}

	/**
	 * @param zone
	 *          the zone to set
	 */
	public void setZone(SIFZone zone) {
		this.zone = zone;
	}

	/**
	 * @return the context
	 */
	public SIFContext getContext() {
		return context;
	}

	/**
	 * @param context
	 *          the context to set
	 */
	public void setContext(SIFContext context) {
		this.context = context;
	}
}
