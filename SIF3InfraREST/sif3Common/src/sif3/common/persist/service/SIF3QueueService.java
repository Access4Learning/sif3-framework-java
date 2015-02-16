/*
 * SIF3QueueService.java
 * Created: 21/04/2014
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

package sif3.common.persist.service;

import java.util.List;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.dao.BaseDAO;
import sif3.common.persist.dao.SIF3QueueDAO;
import sif3.common.persist.model.SIF3Queue;

/**
 * @author Joerg Huber
 *
 */
public class SIF3QueueService extends DBService
{
    private SIF3QueueDAO sif3QueueDAO = new SIF3QueueDAO();
    
    @Override
    public BaseDAO getDAO()
    {
	    return sif3QueueDAO;
    }

    public SIF3Queue getQueue(String queueID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
    	SIF3Queue row = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	row = sif3QueueDAO.getQueue(tx, queueID, adapterType);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve queue for queueID = '"+ queueID +"' and adapterType = '" + adapterType + "'.", true, false);
    	}
		return row;
    }

    public List<SIF3Queue> getQueuesForEnvironment(String environmentID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
    	List<SIF3Queue> rows = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	rows = sif3QueueDAO.getQueuesForEnvironment(tx, environmentID, adapterType);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve queues for environmentID = '"+ environmentID +"' and adapterType = '" + adapterType + "'.", true, false);
    	}
		return rows;
    }

    /*
     * For some queue strategies the query might look more complex to retrieve a particular queue. This method allows to retrieve
     * queue(s) based on a number of queue strategies. All parameters can be null except the environmentID and adapterType.
     * Note that a list of queues is returned. This may make sense if the criteria is at a higher level than the queue strategy would
     * expect. For example if the Queue Strategy is ADAPTER_LEVEL then only one queue would be expected for a particular environment. 
     * For any other queue strategy there might more than one queue for an environment. So if the environmentID is the only criteria
     * that is set for this call a list of queues might be returned.
     */
    public List<SIF3Queue> getQueuesByCriteria(String environmentID, 
    		                                   AdapterType adapterType,
    		                                   String zoneID,
    		                                   String contextID,
    		                                   String serviceName,
    		                                   String serviceType) throws IllegalArgumentException, PersistenceException
    {
    	List<SIF3Queue> rows = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	rows = sif3QueueDAO.getQueuesByCriteria(tx, environmentID, adapterType, zoneID, contextID, serviceName, serviceType);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve queues for environmentID = '"+ environmentID +"' and adapterType = '" + adapterType + "'.", true, false);
    	}
		return rows;
    	
    }

    /*
     * This will also remove existing subscriptions! A queue may have a number of subscriptions. Removing the
     * queue requires subscriptions to be removed as well.
     */
    public boolean removeQueue(String queueID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
	    BasicTransaction tx = null;	    	
		try
		{
			tx = startTransaction();
			
			// Now remove the queue. This will also remove associated subscriptions!
			if (sif3QueueDAO.removeQueue(tx, queueID, adapterType))
			{
				tx.commit();
				return true;
			}
			else
			{
	    		rollback(tx);
				return false;
			}
		}
		catch (Exception ex)
		{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to remove queue for queueID = '"+ queueID +"' and adapterType = '" + adapterType + "'.", true, false);
    		return false;
		}		    	
    }
    
    /*
     * This will also remove existing subscriptions! An environment has a number of queues that in return may have a 
     * number of subscriptions. Removing the queues also requires subscriptions to be removed.
     */
    public boolean removeAllQueueForEnvironment(String environmentID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
	    BasicTransaction tx = null;	    	
		try
		{
			tx = startTransaction();
			
			// Now remove the queue. This will also remove associated subscriptions!
			if (sif3QueueDAO.removeAllQueuesForEnvironment(tx, environmentID, adapterType))
			{
				tx.commit();
				return true;
			}
			else
			{
	    		rollback(tx);
				return false;
			}
		}
		catch (Exception ex)
		{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to remove all queues for environmentID = '"+ environmentID +"' and adapterType = '" + adapterType + "'.", true, false);
    		return false;
		}		    	
    }
    
    public SIF3Queue saveQueue(SIF3Queue queue) throws IllegalArgumentException, PersistenceException
    {
	    BasicTransaction tx = null;	    	
		try
		{
			tx = startTransaction();
			SIF3Queue returnQueue = sif3QueueDAO.saveQueue(tx, queue);
			if (returnQueue != null)
			{
				tx.commit();
			}
			else
			{
	    		rollback(tx);
			}
			
			return returnQueue;
		}
		catch (Exception ex)
		{
    		rollback(tx);
    		exceptionMapper(ex, "Unable to create or update Queue "+ queue, false, false); // error already logged.
    		return null;
		}		    	
   }
}
