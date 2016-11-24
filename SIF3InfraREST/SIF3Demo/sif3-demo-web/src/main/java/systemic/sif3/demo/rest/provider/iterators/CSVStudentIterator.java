/*
 * StudentPersonalIterator.java
 * Created: 19/03/2014
 *
 * Copyright 2014 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package systemic.sif3.demo.rest.provider.iterators;

import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.SIFEvent;
import au.com.systemic.framework.utils.AdvancedProperties;

/**
 * @author Joerg Huber
 *
 */
public class CSVStudentIterator implements SIFEventIterator<String>
{	
	private int numRecycles = 1;
	private int currentCycle = 0;
	
	public CSVStudentIterator(AdvancedProperties serviceProperties)
	{
		this.numRecycles = serviceProperties.getPropertyAsInt("provider.events.recycle", 1);
	}
	
	/* (non-Javadoc)
     * @see sif3.common.interfaces.SIFEventIterator#getNextEvents(int)
     */
    @Override
    public SIFEvent<String> getNextEvents(int maxListSize)
    {
    	currentCycle++;
	    return new SIFEvent<String>(new String("123,Peter,Hydon,1\n456,Dan,Carter,1\n890,Monica,Saladin,2"), EventAction.UPDATE, UpdateType.FULL, 3);
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.SIFEventIterator#hasNext()
     */
    @Override
    public boolean hasNext()
    {
		return currentCycle < numRecycles;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.SIFEventIterator#releaseResources()
     */
    @Override
    public void releaseResources()
    {
    }
}
