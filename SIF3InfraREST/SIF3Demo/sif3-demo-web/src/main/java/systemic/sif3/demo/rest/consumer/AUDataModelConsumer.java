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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif.dd.au30.conversion.DataModelMarshalFactory;
import sif.dd.au30.conversion.DataModelUnmarshalFactory;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.delayed.DelayedResponseReceipt;
import sif3.common.utils.JAXBUtils;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.model.MultiOperationStatusList;
import sif3.infra.rest.consumer.AbstractConsumer;

/**
 * @author Joerg Huber
 *
 */
public abstract class AUDataModelConsumer extends AbstractConsumer
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

	private static DataModelUnmarshalFactory unmarshaller = new DataModelUnmarshalFactory();
	private static DataModelMarshalFactory marshaller = new DataModelMarshalFactory();

	public AUDataModelConsumer()
	{
		super();
		
		//Initialise JAXB context for these classes. Make data processor behave better against race conditions.
		JAXBUtils.initCtx(getMultiObjectClassInfo().getObjectType());
		JAXBUtils.initCtx(getSingleObjectClassInfo().getObjectType());
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


	@Override
    public void shutdown()
    {
	    // nothing to do at the moment
    }


//	@Override
//    public ModelObjectInfo getSingleObjectClassInfo()
//    {
//	    return null;
//    }
//
//	@Override
//    public ModelObjectInfo getMultiObjectClassInfo()
//    {
//	    return null;
//    }

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
}
