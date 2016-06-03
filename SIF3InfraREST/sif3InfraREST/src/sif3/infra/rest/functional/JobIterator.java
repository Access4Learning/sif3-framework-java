
package sif3.infra.rest.functional;

import java.util.ArrayList;
import java.util.List;

import au.com.systemic.framework.utils.AdvancedProperties;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.SIFEvent;
import sif3.infra.common.model.JobCollectionType;

public class JobIterator implements SIFEventIterator<JobCollectionType> {

	private int currentPos = 0;

	private List<SIFEvent<JobCollectionType>> sifevents = new ArrayList<SIFEvent<JobCollectionType>>();

	public JobIterator(String providerID, AdvancedProperties serviceProperties, List<SIFEvent<JobCollectionType>> sifevents) {
		currentPos = 0;
		if (sifevents != null) {
			this.sifevents = sifevents;
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
			event = sifevents.get(currentPos);
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
		return (currentPos < sifevents.size());
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.SIFEventIterator#releaseResources()
	 */
	@Override
	public void releaseResources() {
		sifevents.clear();
		sifevents = null;
	}
}
