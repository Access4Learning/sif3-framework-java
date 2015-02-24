/*
 * CSVStudentConsumer.java
 * Created: 27/01/2015
 *
 * Copyright 2015 Systemic Pty Ltd
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

import java.util.Date;

import javax.ws.rs.core.MediaType;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.model.EventMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.infra.rest.consumer.AbstractEventConsumer;
import systemic.sif3.demo.rest.conversion.CSVMarshaller;
import systemic.sif3.demo.rest.conversion.CSVUnmarshaller;
import au.com.systemic.framework.utils.DateUtils;

/**
 * @author Joerg Huber
 *
 */
public class CSVStudentConsumer extends AbstractEventConsumer<String>
{

	private final static String RECORD_MARKER = "\n================================================================================================\n";

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMarshaller()
     */
    @Override
    public MarshalFactory getMarshaller()
    {
	    return new CSVMarshaller();
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getUnmarshaller()
     */
    @Override
    public UnmarshalFactory getUnmarshaller()
    {
    	return new CSVUnmarshaller();
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getSingleObjectClassInfo()
    {
	    return new ModelObjectInfo("CSVStudent", String.class);
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
	    return new ModelObjectInfo("CSVStudents", String.class);
    }

	/* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractConsumer#shutdown()
     */
    @Override
    public void shutdown()
    {}
    
    //---------------------------//
    //-- Override Media Types! --//
    //---------------------------//
    @Override
    public MediaType getRequestMediaType()
	{
		return getMarshaller().getDefault(); // Marshaling when sending -> request
//    	return MediaType.APPLICATION_JSON_TYPE;
	}
	
    @Override
	public MediaType getResponseMediaType()
	{
		return getUnmarshaller().getDefault(); // Unmarshaling when receiving -> response
//    	return MediaType.APPLICATION_JSON_TYPE;
	}

	/* (non-Javadoc)
     * @see sif3.common.interfaces.EventConsumer#createEventObject(java.lang.Object, sif3.common.header.HeaderValues.EventAction, sif3.common.header.HeaderValues.UpdateType)
     */
    @Override
    public SIFEvent<String> createEventObject(Object sifObjectList, EventAction eventAction, UpdateType updateType)
    {
    	if (sifObjectList != null)
    	{
	    	if (sifObjectList instanceof String)
	    	{
	    		//int size = ((StudentCollectionType)sifObjectList).getStudentPersonal().size();
	    		return new SIFEvent<String>((String)sifObjectList, eventAction, updateType, 0);
	    	}
	    	else
	    	{
	    		logger.error("The given event data is not of type String as expected. Cannot create event object. Return null");
	    	}
    	}
    	else
    	{
    		logger.error("The given event data is null. Cannot create event object. Return null");    		
    	}
	    return null; // if something is wrong then we get here.
    }

	/* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractEventConsumer#processEvent(sif3.common.model.SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.EventMetadata, java.lang.String, java.lang.String)
     */
    @Override
    public void processEvent(SIFEvent<String> sifEvent, SIFZone zone, SIFContext context, EventMetadata metadata, String msgReadID, String consumerID)
    {
    	// We know from the framework that zone and context is never null. For the time being we just log the event.
        String timestamp = DateUtils.getISO8601withSecFraction(new Date());
        logger.debug(RECORD_MARKER+"Record processed by Thread ID = "+Thread.currentThread().getId()+"\n"+sifEvent.getEventAction().name()+" Events from Queue Reader "+ msgReadID+"\nReceived at "+timestamp+" from Zone = "+zone.getId()+" and Context = "+context.getId()+"\nData:\n"+sifEvent.getSIFObjectList()+RECORD_MARKER);    	
    }
}
