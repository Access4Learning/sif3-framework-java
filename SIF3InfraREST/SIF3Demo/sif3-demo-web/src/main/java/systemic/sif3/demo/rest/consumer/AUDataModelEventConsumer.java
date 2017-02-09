/*
 * AUDataModelConsumer.java
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.DateUtils;
import sif.dd.au30.conversion.DataModelMarshalFactory;
import sif.dd.au30.conversion.DataModelUnmarshalFactory;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.model.EventMetadata;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.model.delayed.DelayedResponseReceipt;
import sif3.common.utils.JAXBUtils;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.model.MultiOperationStatusList;
import sif3.infra.rest.consumer.AbstractEventConsumer;

/**
 * @author Joerg Huber
 *
 */
public abstract class AUDataModelEventConsumer<L> extends AbstractEventConsumer<L>
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static Integer busyInSec = null;
	private static Boolean writePayload = null;
	
    private final static String RECORD_MARKER = "\n================================================================================================\n";
    private HashMap<String, Integer> recordCounters = new HashMap<String, Integer>();

	private static DataModelUnmarshalFactory unmarshaller = new DataModelUnmarshalFactory();
	private static DataModelMarshalFactory marshaller = new DataModelMarshalFactory();
	
	public AUDataModelEventConsumer()
	{
		super();
		if (busyInSec == null)
		{
			busyInSec = getServiceProperties().getPropertyAsInt("test.consumer.busy.delay", 0);
		}
		if (writePayload == null)
		{
			writePayload = getServiceProperties().getPropertyAsBool("test.consumer.write.payload", true);
		}
		
		//Initialise JAXB context for these classes. Make data processor behave better against race conditions.
		JAXBUtils.initCtx(getMultiObjectClassInfo().getObjectType());
		JAXBUtils.initCtx(getSingleObjectClassInfo().getObjectType());
	}

	/* (non-Javadoc)
     * @see sif3.common.interfaces.EventConsumer#onEvent(sif3.common.model.SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext, java.lang.String)
     */
    @Override
    public void processEvent(SIFEvent<L> sifEvent, SIFZone zone, SIFContext context, EventMetadata metadata, String msgReadID, String consumerID)
    {
    	String consumerName = getPrettyName()+"(QueueID:"+msgReadID+"; ConsumerID: "+consumerID+")";
    	
    	// We know from the framework that zone and context is never null. For the time being we just log the event.
    	logger.debug(consumerName +" received an event from Zone = "+zone.getId()+", Context = "+context.getId()+" and Event Metadata = "+metadata);
    	dumpObject(sifEvent, zone, context, msgReadID, consumerID);
    	
    	// Pretend to be busy for a long time to test threading
    	try
    	{
    		if (busyInSec > 0)
    		{
	    		System.out.println(consumerID+" busy for the next "+busyInSec+" seconds...");
	    		Thread.sleep(busyInSec*1000);
	    		System.out.println(consumerID+" ready to process next message.");
    		}
    	}
    	catch (Exception ex) {}
    	//logger.debug(consumerName +" process event:\n"+sifEvent);
    }
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DataModelLink#getMarshaller()
	 */
	@Override
    public MarshalFactory getMarshaller()
    {
	    return marshaller;
    }

	@Override
    public UnmarshalFactory getUnmarshaller()
    {
	    return unmarshaller;
    }
	
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
	
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	
    private void dumpObject(SIFEvent<L> sifEvent, SIFZone zone, SIFContext context, String msgReadID, String consumerID)
    {
//    	logger.debug(consumerID +" write data to file...");
        String filename = getFileNameOnly(consumerID);
    	String fullFileName= getFullFileName(consumerID);
        Integer recordNum = recordCounters.get(filename);
        if (recordNum == null)
        {
        	recordNum = 1;
        	recordCounters.put(filename, recordNum);
        }
        String timestamp = DateUtils.getISO8601withSecFraction(new Date());
        
        BufferedWriter out = null;
        try
        {
            FileWriter fstream = new FileWriter(fullFileName, true);
            out = new BufferedWriter(fstream);
            out.write(RECORD_MARKER);
            out.write("Record " + recordNum + " - processed by Thread ID = "+Thread.currentThread().getId()+"\n"+sifEvent.getListSize()+" "+sifEvent.getEventAction().name()+" Events from Queue Reader "+ msgReadID+"\nReceived at "+timestamp+" from Zone = "+zone.getId()+" and Context = "+context.getId());
            out.write(RECORD_MARKER);
            if (writePayload)
            {
            	out.write(getMarshaller().marshal(sifEvent.getSIFObjectList(), MediaType.APPLICATION_XML_TYPE));
            }
            recordNum++;
            recordCounters.put(filename, recordNum);
        }
        catch (Exception ex)
        {
            logger.error("Failed to write data to dump file with name:" + fullFileName, ex);
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (Exception ex) {} // nothing we can do
            }
//        	logger.debug(consumerID +" data written.");
        }
    }
    
    private String getFullFileName(String consumerID)
    {
      return getServiceProperties().getPropertyAsString("test.tempDir.output","") + "/" + getFileNameOnly(consumerID);
      
    }
    
    private String getFileNameOnly(String consumerID)
    {
      return consumerID+".log";
    }
}
