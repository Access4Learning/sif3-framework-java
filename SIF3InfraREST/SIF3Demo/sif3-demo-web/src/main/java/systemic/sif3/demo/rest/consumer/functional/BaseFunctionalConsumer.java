/*
 * BaseFunctionalConsumer.java
 * Created: 26 Jul 2018
 *
 * Copyright 2018 Systemic Pty Ltd
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

package systemic.sif3.demo.rest.consumer.functional;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.DateUtils;
import sif3.common.model.EventMetadata;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.model.delayed.DelayedResponseReceipt;
import sif3.common.model.job.PhaseInfo;
import sif3.common.utils.JAXBUtils;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.job.PhaseDataResponse;
import sif3.common.ws.model.MultiOperationStatusList;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.rest.consumer.AbstractFunctionalServiceConsumer;

/**
 * Note: All the processABC() methods implemented in this class are for demo purpose only. It simply logs the returned response to any
 * delayed requests. In actual real world implementations these methods need to be implemented properly, meaning some action is performed
 * on the data store that relates to the received response.
 * 
 * @author Joerg Huber
 */
public abstract class BaseFunctionalConsumer extends AbstractFunctionalServiceConsumer
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    private final static String RECORD_MARKER = "\n================================================================================================\n";
    private HashMap<String, Integer> recordCounters = new HashMap<String, Integer>();
    private static Boolean writePayload = null;
    
    public BaseFunctionalConsumer()
    {
        super();
        
        //Initialise JAXB context for Job classes. Make consumer behave better against race conditions.
        JAXBUtils.initCtx(getMultiObjectClassInfo().getObjectType());
        JAXBUtils.initCtx(getSingleObjectClassInfo().getObjectType());
    }


    /* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractFunctionalServiceConsumer#processDelayedJobsCreate(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void processDelayedJobsCreate(MultiOperationStatusList<CreateOperationStatus> statusList, DelayedResponseReceipt receipt)
    {
        logger.debug("Received DELAYED JOB CREATE Response for "+getServiceURLNamePlural()+":\n"+statusList+"\nDelayed Receipt Details:\n"+receipt);
       
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractFunctionalServiceConsumer#processDelayedJobsDelete(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void processDelayedJobsDelete(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt)
    {
        logger.debug("Received DELAYED JOB DELETE Response for "+getServiceURLNamePlural()+":\n"+statusList+"\nDelayed Receipt Details:\n"+receipt);
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractFunctionalServiceConsumer#processDelayedJobsQuery(sif3.infra.common.model.JobCollectionType, sif3.common.model.PagingInfo, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void processDelayedJobsQuery(JobCollectionType jobs, PagingInfo pagingInfo, DelayedResponseReceipt receipt)
    {
        logger.debug("Received DELAYED JOB QUERY Response for "+getServiceURLNamePlural()+":\n"+jobs+"\nPagingInfo:\n"+pagingInfo+"\nDelayed Receipt Details:\n"+receipt);
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractFunctionalServiceConsumer#processDelayedPhaseQuery(sif3.common.model.job.PhaseInfo, sif3.common.ws.job.PhaseDataResponse, sif3.common.model.PagingInfo, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void processDelayedPhaseQuery(PhaseInfo phaseInfo, PhaseDataResponse phaseDataResponse, PagingInfo pagingInfo, DelayedResponseReceipt receipt)
    {
        logger.debug("Received DELAYED PHASE QUERY Response:\nPhase Info: "+phaseInfo+"\nPagingInfo:\n"+pagingInfo+"\nDelayed Receipt Details:\n"+receipt+"\nPhase Response Data:\n"+phaseDataResponse);
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractFunctionalServiceConsumer#processDelayedPhaseCreate(sif3.common.model.job.PhaseInfo, sif3.common.ws.job.PhaseDataResponse, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void processDelayedPhaseCreate(PhaseInfo phaseInfo, PhaseDataResponse phaseDataResponse, DelayedResponseReceipt receipt)
    {
        logger.debug("Received DELAYED PHASE CREATE Response:\nPhase Info: "+phaseInfo+"\nDelayed Receipt Details:\n"+receipt+"\nPhase Response Data:\n"+phaseDataResponse);
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractFunctionalServiceConsumer#processDelayedPhaseUpdate(sif3.common.model.job.PhaseInfo, sif3.common.ws.job.PhaseDataResponse, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void processDelayedPhaseUpdate(PhaseInfo phaseInfo, PhaseDataResponse phaseDataResponse, DelayedResponseReceipt receipt)
    {
        logger.debug("Received DELAYED PHASE UPDATE Response:\nPhase Info: "+phaseInfo+"\nDelayed Receipt Details:\n"+receipt+"\nPhase Response Data:\n"+phaseDataResponse);
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractFunctionalServiceConsumer#processDelayedPhaseDelete(sif3.common.model.job.PhaseInfo, sif3.common.ws.job.PhaseDataResponse, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void processDelayedPhaseDelete(PhaseInfo phaseInfo, PhaseDataResponse phaseDataResponse, DelayedResponseReceipt receipt)
    {
        logger.debug("Received DELAYED PHASE DELETE Response:\nPhase Info: "+phaseInfo+"\nDelayed Receipt Details:\n"+receipt+"\nPhase Response Data:\n"+phaseDataResponse);
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.consumer.BaseConsumer#processDelayedError(sif3.common.ws.ErrorDetails, sif3.common.model.delayed.DelayedResponseReceipt)
     */
    @Override
    public void processDelayedError(ErrorDetails error, DelayedResponseReceipt receipt)
    {
        logger.debug("Received DELAYED ERROR Response:\n"+error+"\nDelayed Receipt Details:\n"+receipt);
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractFunctionalServiceConsumer#processJobEvent(sif3.common.model.SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.EventMetadata, java.lang.String, java.lang.String)
     */
    @Override
    public void processJobEvent(SIFEvent<JobCollectionType> sifEvent, SIFZone zone, SIFContext context, EventMetadata metadata, String msgReadID, String consumerID)
    {
        String consumerName = getPrettyName()+"(QueueID:"+msgReadID+"; ConsumerID: "+consumerID+")";
        logger.debug(consumerName +" received an event from Zone = "+zone+", Context = "+context+" and Event Metadata = "+metadata);
        dumpJobEvent(sifEvent, zone, context, msgReadID, consumerID);
    }
    
    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    
    private void dumpJobEvent(SIFEvent<JobCollectionType> sifEvent, SIFZone zone, SIFContext context, String msgReadID, String consumerID)
    {
//      logger.debug(consumerID +" write data to file...");
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
            
            if (writePayload == null)
            {
                writePayload = getServiceProperties().getPropertyAsBool("test.consumer.write.payload", true);
            }
            
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
