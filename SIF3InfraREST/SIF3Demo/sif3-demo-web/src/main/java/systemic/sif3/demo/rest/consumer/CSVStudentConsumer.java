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

import au.com.systemic.framework.utils.DateUtils;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.model.EventMetadata;
import sif3.common.model.PagingInfo;
import sif3.common.model.PayloadMetadata;
import sif3.common.model.QueryCriteria;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.model.delayed.DelayedResponseReceipt;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.model.MultiOperationStatusList;
import sif3.infra.rest.consumer.AbstractEventConsumer;
import systemic.sif3.demo.rest.conversion.CSVMarshaller;
import systemic.sif3.demo.rest.conversion.CSVUnmarshaller;

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
    
	/*----------------------------------------------------------------------------*/
	/*-- Abstract Consumer Methods: Dummy Implementation - Just log the values. --*/
	/*----------------------------------------------------------------------------*/

	@Override
    public void processDelayedCreateMany(MultiOperationStatusList<CreateOperationStatus> statusList, DelayedResponseReceipt receipt)
    {
		logger.debug("Received DELAYED CREATE Response:\n"+statusList+"\nDelayed Receipt Details:\n"+receipt);
    }

	@Override
    public void processDelayedUpdateMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt)
    {
		logger.debug("Received DELAYED UPDATE Response:\n"+statusList+"\nDelayed Receipt Details:\n"+receipt);
    }

	@Override
    public void processDelayedDeleteMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt)
    {
		logger.debug("Received DELAYED DELETE Response:\n"+statusList+"\nDelayed Receipt Details:\n"+receipt);
    }

	@Override
    public void processDelayedQuery(Object dataObject, PagingInfo pagingInfo, DelayedResponseReceipt receipt)
    {
		logger.debug("Received DELAYED QUERY Response:\n"+dataObject+"\nPagingInfo:\n"+pagingInfo+"\nDelayed Receipt Details:\n"+receipt);
    }

	@Override
    public void processDelayedServicePath(Object dataObject, QueryCriteria queryCriteria, PagingInfo pagingInfo, DelayedResponseReceipt receipt)
    {
		logger.debug("Received DELAYED SERVICEPATH Response:\n"+dataObject+"\nQuery Criteria:\n"+queryCriteria+"\nPagingInfo:\n"+pagingInfo+"\nDelayed Receipt Details:\n"+receipt);
    }

	@Override
    public void processDelayedError(ErrorDetails error, DelayedResponseReceipt receipt)
    {
		logger.debug("Received DELAYED ERROR Response:\n"+error+"\nDelayed Receipt Details:\n"+receipt);
    }
    
    //---------------------------//
    //-- Override Media Types! --//
    //---------------------------//
	@Override
    public PayloadMetadata getDataModelRequestPayloadMetadata()
    {
	    // Override the Framework default mime type with the marshaller's mime type which should indicate CSV type (text/plain).
	    // The implementation below assumes no schema negotiation is turned on. If it is turned on then appropriate values
	    // need to be set as well.
	    return new PayloadMetadata(getMarshaller().getDefault(), "UTF-8");
	    
	    // Example if schema negotiation is turned on.
//	    return new PayloadMetadata(getMarshaller().getDefault(), "UTF-8", new SchemaInfo("data", "us", "1.0"));
	    
    }
    
    @Override
    public PayloadMetadata getDataModelResponsePayloadMetadata()
    {
        //Override the Framework default mime type with the unmarshaller's mime type which should indicate CSV type (text/plain) 
        // The implementation below assumes no schema negotiation is turned on. If it is turned on then appropriate values
        // need to be set as well.
        return new PayloadMetadata(getUnmarshaller().getDefault(), "UTF-8");

        // Example if schema negotiation is turned on.
//      return new PayloadMetadata(getUnmarshaller().getDefault(), "UTF-8", new SchemaInfo("data", "us", "1.0"));
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
//  public void processEvent(SIFEvent<L> sifEvent, SIFZone zone, SIFContext context, EventMetadata metadata, String msgReadID, String consumerID)
    public void processEvent(SIFEvent<String> sifEvent, EventMetadata metadata, String msgReadID, String consumerID)
    {
    	// We know from the framework that zone and context is never null. For the time being we just log the event.
        String timestamp = DateUtils.getISO8601withSecFraction(new Date());
        SIFZone zone = sifEvent.getLimitToZoneCtxList().get(0).getZone();
        SIFContext context = sifEvent.getLimitToZoneCtxList().get(0).getContext();
        
        logger.debug(RECORD_MARKER+"Record processed by Thread ID = "+Thread.currentThread().getId()+"\n"+sifEvent.getEventAction().name()+" Events from Queue Reader "+ msgReadID+"\nReceived at "+timestamp+" from Zone = "+zone.getId()+" and Context = "+context.getId()+"\nData:\n"+sifEvent.getSIFObjectList()+RECORD_MARKER);    	
    }

}
