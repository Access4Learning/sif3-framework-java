
package sif3.infra.rest.provider;

import java.util.ArrayList;
import java.util.List;

import au.com.systemic.framework.utils.AdvancedProperties;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.SIFEvent;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.rest.functional.JobEvent;

public class JobIterator implements SIFEventIterator<JobCollectionType> {

	private int currentPos = 0;

	private List<JobEvent> jobEvents = new ArrayList<JobEvent>();

	public JobIterator(String providerID, AdvancedProperties serviceProperties, List<JobEvent> jobEvents) {
		currentPos = 0;
		if (jobEvents != null) {
			this.jobEvents = jobEvents;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.SIFEventIterator#getNextEvents(int)
	 */
	@Override
	public SIFEvent<JobCollectionType> getNextEvents(int maxListSize) {
		SIFEvent<JobCollectionType> event = null;
		if (hasNext()) {
			event = new SIFEvent<JobCollectionType>(new JobCollectionType(), jobEvents.get(currentPos).getEventAction(), UpdateType.FULL, 0);
			event.getSIFObjectList().getJob().add(jobEvents.get(currentPos).getJob());
			event.setListSize(event.getListSize() + 1);
			currentPos++;
		}
		return event;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.SIFEventIterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return (currentPos < jobEvents.size());
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.SIFEventIterator#releaseResources()
	 */
	@Override
	public void releaseResources() {
	}
}
