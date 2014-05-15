/*
 * StudentDailyAttendanceConsumer.java
 * Created: 08/05/2014
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

package systemic.sif3.demo.rest.consumer;

import sif.dd.au30.model.StudentDailyAttendanceCollectionType;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.model.SIFEvent;
import systemic.sif3.demo.rest.ModelObjectConstants;

/**
 * @author Joerg Huber
 *
 */
public class StudentDailyAttendanceConsumer extends AUDataModelEventConsumer<StudentDailyAttendanceCollectionType>
{	
    public StudentDailyAttendanceConsumer()
    {
	    super();
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.EventConsumer#createEventObject(java.lang.Object, sif3.common.header.HeaderValues.EventAction, sif3.common.header.HeaderValues.UpdateType)
     */
    @Override
    public SIFEvent<StudentDailyAttendanceCollectionType> createEventObject(Object sifObjectList, EventAction eventAction, UpdateType updateType)
    {
    	if (sifObjectList != null)
    	{
	    	if (sifObjectList instanceof StudentDailyAttendanceCollectionType)
	    	{
	    		int size = ((StudentDailyAttendanceCollectionType)sifObjectList).getStudentDailyAttendance().size();
	    		return new SIFEvent<StudentDailyAttendanceCollectionType>((StudentDailyAttendanceCollectionType)sifObjectList, eventAction, updateType, size);
	    	}
	    	else
	    	{
	    		logger.error("The given event data is not of type StudentCollectionType as expected. Cannot create event object. Return null");
	    	}
    	}
    	else
    	{
    		logger.error("The given event data is null. Cannot create event object. Return null");    		
    	}
	    return null; // if something is wrong then we get here.
    }
    
	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
	 */
    public ModelObjectInfo getSingleObjectClassInfo()
    {
	    return ModelObjectConstants.STUDENT_DAILY_ATTENDANCE;
    }

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
	 */
    public ModelObjectInfo getMultiObjectClassInfo()
    {
	    return ModelObjectConstants.STUDENT_DAILY_ATTENDANCES;
    }
	
	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.consumer.AbstractConsumer#shutdown()
	 */
    public void shutdown()
    {
		//Nothing to do at this stage
    }
}
