/*
 * StudentPersonalIterator.java Created: 19/03/2014 Copyright 2014 Systemic Pty
 * Ltd Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a
 * copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless
 * required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package zinet.sif3.demo.uk.rest.provider.iterators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import au.com.systemic.framework.utils.AdvancedProperties;
import sif.dd.uk20.model.LearnerPersonalCollectionType;
import sif.dd.uk20.model.LearnerPersonalType;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.SIFEvent;

public class LearnerPersonalIterator implements SIFEventIterator<LearnerPersonalCollectionType> {

	private int currentPos = 0;
	private int currentCycle = 1;
	private int numRecycle = 1;

	private List<LearnerPersonalType> learnerEvents = new ArrayList<LearnerPersonalType>();

	public LearnerPersonalIterator(String providerID, AdvancedProperties serviceProperties, HashMap<String, LearnerPersonalType> students) {
		currentPos = 0;
		if (students != null) {
			learnerEvents = new ArrayList<LearnerPersonalType>(students.values());
		}
		numRecycle = serviceProperties.getPropertyAsInt("provider.events.recycle", 1);

	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.SIFEventIterator#getNextEvents(int)
	 */
	@Override
	public SIFEvent<LearnerPersonalCollectionType> getNextEvents(int maxListSize) {
		SIFEvent<LearnerPersonalCollectionType> events = null;
		if (hasNext()) {
			events = new SIFEvent<LearnerPersonalCollectionType>(new LearnerPersonalCollectionType(), EventAction.UPDATE, UpdateType.FULL, 0);
			while ((events.getListSize() < maxListSize) && hasNext()) {
				events.getSIFObjectList().getLearnerPersonal().add(learnerEvents.get(currentPos));
				events.setListSize(events.getListSize() + 1);
				currentPos++;
				if (currentPos == learnerEvents.size()) {
					if (currentCycle < numRecycle) {
						currentPos = 0;
						currentCycle++;
					}
				}
			}
		}

		return events;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.SIFEventIterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return (currentPos < learnerEvents.size());
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.SIFEventIterator#releaseResources()
	 */
	@Override
	public void releaseResources() {
	}
}
