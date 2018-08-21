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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.common.model.PagingInfo;
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
}
