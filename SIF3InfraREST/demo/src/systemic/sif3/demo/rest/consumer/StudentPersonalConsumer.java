/*
 * StudentPersonalConsumer.java
 * Created: 03/09/2013
 *
 * Copyright 2013 Systemic Pty Ltd
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

import sif.dd.au30.model.StudentCollectionType;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.model.SIFEvent;
import systemic.sif3.demo.rest.ModelObjectConstants;

/**
 * @author Joerg Huber
 *
 */
public class StudentPersonalConsumer extends AUDataModelEventConsumer<StudentCollectionType> {	
    public StudentPersonalConsumer()
    {
	    super();
    }

    /* 
     * If the generator.id property from the consumer property file shall be overridden or set specifically for a 
     * consumer then the method below can be un-commented and the generator ID can be assigned. This value, if not 
     * empty, will go into the HTTP Header of each request to the object provider.
     */
//    @Override
//    public String getGeneratorID()
//    {
//		return "Overridden Generator ID";
//    }
    
	/* (non-Javadoc)
     * @see sif3.common.interfaces.EventConsumer#createEventObject(java.lang.Object, sif3.common.header.HeaderValues.EventAction, sif3.common.header.HeaderValues.UpdateType)
     */
    @Override
    public SIFEvent<StudentCollectionType> createEventObject(Object sifObjectList, EventAction eventAction, UpdateType updateType)
    {
    	if (sifObjectList != null)
    	{
	    	if (sifObjectList instanceof StudentCollectionType)
	    	{
	    		int size = ((StudentCollectionType)sifObjectList).getStudentPersonal().size();
	    		return new SIFEvent<StudentCollectionType>((StudentCollectionType)sifObjectList, eventAction, updateType, size);
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
    
	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getSingleObjectClassInfo()
    {
	    return ModelObjectConstants.STUDENT_PERSONAL;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
	    return ModelObjectConstants.STUDENT_PERSONALS;
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
