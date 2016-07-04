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

package sif3.infra.common.functional;

import java.util.ArrayList;
import java.util.List;

import au.com.systemic.framework.utils.AdvancedProperties;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.SIFEvent;
import sif3.infra.common.model.JobCollectionType;

/**
 * An iterator for JobCollectionType objects in a SIFEvent
 */
public class JobIterator implements SIFEventIterator<JobCollectionType>
{

    private int                               currentPos = 0;

    private List<SIFEvent<JobCollectionType>> sifevents  = new ArrayList<SIFEvent<JobCollectionType>>();

    public JobIterator(String providerID, AdvancedProperties serviceProperties,
            List<SIFEvent<JobCollectionType>> sifevents)
    {
        currentPos = 0;
        if (sifevents != null)
        {
            this.sifevents = sifevents;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.SIFEventIterator#getNextEvents(int)
     */
    @Override
    public SIFEvent<JobCollectionType> getNextEvents(int maxListSize)
    {
        SIFEvent<JobCollectionType> event = null;
        if (hasNext())
        {
            event = sifevents.get(currentPos);
            currentPos++;
        }
        return event;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.SIFEventIterator#hasNext()
     */
    @Override
    public boolean hasNext()
    {
        return (currentPos < sifevents.size());
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.SIFEventIterator#releaseResources()
     */
    @Override
    public void releaseResources()
    {
        sifevents.clear();
        sifevents = null;
    }
}
