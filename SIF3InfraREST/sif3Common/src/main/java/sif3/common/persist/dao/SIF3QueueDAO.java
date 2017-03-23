/*
 * SIF3QueueDAO.java
 * Created: 18/04/2014
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

package sif3.common.persist.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.model.SIF3Queue;

/**
 * Implements some low level DB operations relating to the SIF Queue Information that must be stored locally.
 * 
 * @author Joerg Huber
 *
 */
public class SIF3QueueDAO extends BaseDAO
{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
    public SIF3Queue getQueue(BasicTransaction tx, String queueID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
	{
        if (StringUtils.isEmpty(queueID))
        {
          throw new IllegalArgumentException("queueID empty or null.");          
        }
        
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }

        try
        {
            Criteria criteria = tx.getSession().createCriteria(SIF3Queue.class)
               .add(Restrictions.eq("queueID", queueID))
               .add(Restrictions.eq("adapterType", adapterType.name()));
            
            List<SIF3Queue> queues = criteria.list();
            
            // There can only be a maximum of one
            if (queues.isEmpty()) // no queue for given id
            {
            	logger.debug("No Queue for queueID = '"+ queueID + " and adapterType = '" + adapterType + "' exists.");
                return null;
            }
            else // already exists
            {
            	return queues.get(0);
            }
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Queue for queueID = '"+ queueID + " and adapterType = '" + adapterType + "'.", e);
        }	
	}
	
    @SuppressWarnings("unchecked")
    public List<SIF3Queue> getQueuesForEnvironment(BasicTransaction tx, String environmentID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
	{
        if (StringUtils.isEmpty(environmentID))
        {
          throw new IllegalArgumentException("environmentID empty or null.");          
        }
        
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }

        try
        {
            Criteria criteria = tx.getSession().createCriteria(SIF3Queue.class)
               .add(Restrictions.eq("environmentID", environmentID))
               .add(Restrictions.eq("adapterType", adapterType.name()));
            
            return criteria.list();  
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Queues for environmentID = '"+ environmentID + " and adapterType = '" + adapterType + "'.", e);
        }	
	}
    

    /*
     * For some queue strategies the query might look more complex to retrieve a particular queue. This method allows to retrieve
     * queue(s) based on a number of queue strategies. All parameters can be null except the environmentID and adapterType.
     * Note that a list of queues is returned. This may make sense if the criteria is at a higher level than the queue strategy would
     * expect. For example if the Queue Strategy is ADAPTER_LEVEL then only one queue would be expected for a particular environment. 
     * For any other queue strategy there might more than one queue for an environment. So if the environmentID is the only criteria
     * that is set for this call a list of queues might be returned.
     */
    @SuppressWarnings("unchecked")
    public List<SIF3Queue> getQueuesByCriteria(BasicTransaction tx, 
    		                                  String environmentID, 
    		                                  AdapterType adapterType,
    		                                  String zoneID,
    		                                  String contextID,
    		                                  String serviceName,
    		                                  String serviceType) throws IllegalArgumentException, PersistenceException
	{
        if (StringUtils.isEmpty(environmentID))
        {
          throw new IllegalArgumentException("environmentID empty or null.");          
        }
        
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }

        try
        {
            Criteria criteria = tx.getSession().createCriteria(SIF3Queue.class)
               .add(Restrictions.eq("environmentID", environmentID))
               .add(Restrictions.eq("adapterType", adapterType.name()));
            
            if (StringUtils.notEmpty(zoneID))
            {
            	criteria = criteria.add(Restrictions.eq("zoneID", zoneID));
            }
            if (StringUtils.notEmpty(contextID))
            {
            	criteria = criteria.add(Restrictions.eq("contextID", contextID));
            }
            if (StringUtils.notEmpty(serviceName))
            {
            	criteria = criteria.add(Restrictions.eq("serviceName", serviceName));
            }
            if (StringUtils.notEmpty(serviceType))
            {
            	criteria = criteria.add(Restrictions.eq("serviceType", serviceType));
            }
            
            return criteria.list();  
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Queues for environmentID = '"+ environmentID + " and adapterType = '" + adapterType + "'.", e);
        }	
	}
    
    /*
     * This will also remove existing subscriptions! A queue may have a number of subscriptions. Removing the
     * queue requires subscriptions to be removed as well.
     */
    public boolean removeQueue(BasicTransaction tx, String queueID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        if (StringUtils.isEmpty(queueID))
        {
          throw new IllegalArgumentException("queueID empty or null.");          
        }
        
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }
    	try
    	{
    		SIF3Queue queue = getQueue(tx, queueID, adapterType);
    		if (queue != null)
    		{
    			// First we must remove existing subscriptions!
    			SIF3SubscriptionDAO subscriptionDAO = new SIF3SubscriptionDAO();
    			subscriptionDAO.removeAllSubscriptionsForQueue(tx, queueID, adapterType);
    			
    			// Now remove actual queue
    			tx.getSession().delete(queue);
    			return true;
    		}
    		else // does not exist. No action required
    		{
    			return true;
    		}
    	}
        catch (HibernateException e)
        {
            throw new PersistenceException("Failed to delete Queue for queueID = '"+ queueID + " and adapterType = '" + adapterType + "'.", e);
        }
    }
    
    /*
     * The current implementation is not the most efficient. Since we only will have very few queues in an environment
     * or we really never should delete queues this operations will be used rarely and will not have any impact on
     * performance.
     */
    public boolean removeAllQueuesForEnvironment(BasicTransaction tx, String environmentID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
	{
        if (StringUtils.isEmpty(environmentID))
        {
          throw new IllegalArgumentException("environmentID empty or null.");          
        }
        
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }

        try
        {
			SIF3SubscriptionDAO subscriptionDAO = new SIF3SubscriptionDAO();
        	List<SIF3Queue> queues = getQueuesForEnvironment(tx, environmentID, adapterType);
        	for (SIF3Queue queue : queues)
        	{
    			// First we must remove existing subscriptions!
    			subscriptionDAO.removeAllSubscriptionsForQueue(tx, queue.getQueueID(), adapterType);

    			// Now remove actual queue
    			tx.getSession().delete(queue);
        	}
        	return true;
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to remove Queues for environmentID = '"+ environmentID + " and adapterType = '" + adapterType + "'.", e);
        }	
	}
    
    /*
     * This will create a queue with the given values if it doesn't exist, yet. If it already exists it will update
     * the queue with the given values. The updated/created queue is then returned. If null is returned then 
     * the operation failed and an error is logged.
     */
    public SIF3Queue saveQueue(BasicTransaction tx, SIF3Queue queue) throws IllegalArgumentException, PersistenceException
    {
    	if (queue == null)
    	{
            throw new IllegalArgumentException("queue parameter is null.");          
    	}
    	if (StringUtils.isEmpty(queue.getName()))
    	{
            throw new IllegalArgumentException("queue.name property is null or empty. It must be set.");          
    	}
    	if (StringUtils.isEmpty(queue.getQueueID()))
    	{
            throw new IllegalArgumentException("queue.queueID property is null or empty. It must be set.");          
    	}
    	if (StringUtils.isEmpty(queue.getAdapterType()))
    	{
            throw new IllegalArgumentException("queue.adapterType property is null or empty. It must be set.");          
    	}
    	if (StringUtils.isEmpty(queue.getEnvironmentID()))
    	{
            throw new IllegalArgumentException("queue.environmentID property is null or empty. It must be set.");          
    	}
    	if (StringUtils.isEmpty(queue.getQueueType()))
    	{
            throw new IllegalArgumentException("queue.queueType property is null or empty. It must be set to IMMEDIATE or LONG.");          
    	}
    	if (StringUtils.isEmpty(queue.getMessageURI()))
    	{
            throw new IllegalArgumentException("queue.messageURI property is null or empty. It must be set.");          
    	}

    	try
    	{
	    	// Check if queue already exist.
	    	SIF3Queue dbQueue = getQueue(tx, queue.getQueueID(), CommonConstants.AdapterType.valueOf(queue.getAdapterType()));
	    	if (dbQueue == null) // doesn't exist
	    	{
	    		queue.setCreated(new Date());
	    		dbQueue = queue;
	    	}
	    	else
	    	{
	    		copy(queue, dbQueue);
	    	}
    		tx.getSession().saveOrUpdate(dbQueue);	    	
	    	return queue;
    	}
        catch (Exception e)
        {
            throw new PersistenceException("Unable to create or update Queue "+ queue +": "+e.getMessage(), e);
        }	
    }
    
    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    /*
     * Only copy some properties that make sense in this context
     */
    private void copy(SIF3Queue source, SIF3Queue target)
    {
    	target.setLastAccessed(new Date());
    	target.setLastModified(source.getLastModified());
    	target.setQueueType(source.getQueueType()); // maybe we want to change from IMMEDIATE to LONG_POLLING!
    	target.setLongPollingTimeout(source.getLongPollingTimeout()); // see above
    	target.setMaxConsumers(source.getMaxConsumers()); // This might be increased
    	target.setMessageURI(source.getMessageURI()); // Message URI could have moved since last time
    	target.setName(source.getName());
    	target.setNumMessages(source.getNumMessages());
    	target.setWaitTime(source.getWaitTime());
    	target.setWakeupURI(source.getWakeupURI()); // In case we also support WakeUp queues at some stage.
    }
    
}
