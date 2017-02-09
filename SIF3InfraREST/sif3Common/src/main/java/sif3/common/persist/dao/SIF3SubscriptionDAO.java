/*
 * SIF3SubscriptionDAO.java
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

package sif3.common.persist.dao;

import java.util.ArrayList;
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
import sif3.common.persist.model.SIF3Subscription;

/**
 * This class provides a low level DB interface to SIF3 subscription information. This information is required locally for retrieveing events on
 * a consumer.
 * 
 * @author Joerg Huber
 *
 */
public class SIF3SubscriptionDAO extends BaseDAO
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
    public SIF3Subscription getSubscription(BasicTransaction tx, String subscriptionID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
	{
        if (StringUtils.isEmpty(subscriptionID))
        {
          throw new IllegalArgumentException("subscriptionID empty or null.");          
        }
        
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }

        try
        {
            Criteria criteria = tx.getSession().createCriteria(SIF3Subscription.class)
               .add(Restrictions.eq("subscriptionID", subscriptionID))
               .add(Restrictions.eq("adapterType", adapterType.name()));
            
            List<SIF3Subscription> subscriptions = criteria.list();
            
            // There can only be a maximum of one
            if (subscriptions.isEmpty()) // no queue for given id
            {
            	logger.debug("No Subscription for subscriptionID = '"+ subscriptionID + " and adapterType = '" + adapterType + "' exists.");
                return null;
            }
            else // already exists
            {
            	return subscriptions.get(0);
            }
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Subscription for subscriptionID = '"+ subscriptionID + " and adapterType = '" + adapterType + "'.", e);
        }	
	}
	
    @SuppressWarnings("unchecked")
    public List<SIF3Subscription> getSubscriptionsForQueue(BasicTransaction tx, String queueID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
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
            Criteria criteria = tx.getSession().createCriteria(SIF3Subscription.class)
               .add(Restrictions.eq("queueID", queueID))
               .add(Restrictions.eq("adapterType", adapterType.name()));
            
            return criteria.list();  
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Subscriptions for queueID = '"+ queueID + " and adapterType = '" + adapterType + "'.", e);
        }	
	}

    /*
     * This gets all subscriptions for the given environment. This means getting all queues for an environment and 
     * then all subscriptions for these queues.
     */
    public List<SIF3Subscription> getSubscriptionsForEnvironment(BasicTransaction tx, String environmentID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
	{
        if (StringUtils.isEmpty(environmentID))
        {
          throw new IllegalArgumentException("environmentID empty or null.");          
        }
        
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }
        
        SIF3QueueDAO queueDAO = new SIF3QueueDAO();
        try
        {
           	List<SIF3Subscription> subscriptions = new ArrayList<SIF3Subscription>();
            List<SIF3Queue> queues = queueDAO.getQueuesForEnvironment(tx, environmentID, adapterType);
            if (queues != null)
            {
            	for (SIF3Queue queue : queues)
            	{
            		subscriptions.addAll(getSubscriptionsForQueue(tx, queue.getQueueID(), adapterType));
            	}
            }

            return subscriptions;  
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Susbcriptions for environmentID = '"+ environmentID + " and adapterType = '" + adapterType + "'.", e);
        }	
	}
    
    public boolean removeSubscription(BasicTransaction tx, String subscriptionID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        if (StringUtils.isEmpty(subscriptionID))
        {
          throw new IllegalArgumentException("subscriptionID empty or null.");          
        }
        
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }
    	try
    	{
    		SIF3Subscription subscription = getSubscription(tx, subscriptionID, adapterType);
    		if (subscription != null)
    		{
    			tx.getSession().delete(subscription);
    			return true;
    		}
    		else // does not exist. No action required
    		{
    			return true;
    		}
    	}
        catch (HibernateException e)
        {
            throw new PersistenceException("Failed to delete Subscription for subscriptionID = '"+ subscriptionID + " and adapterType = '" + adapterType + "'.", e);
        }
    }
    
    /*
     * The current implementation is not the most efficient. Since we only will have very few subscriptions for a 
     * queue or we really never should delete subscriptions this operations will be used rarely and will not have 
     * any impact on performance. If it is used it will be called at startup and/or shut down but not during standard
     * operation of an adapter.
     */
    public boolean removeAllSubscriptionsForQueue(BasicTransaction tx, String queueID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
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
        	List<SIF3Subscription> subscriptions = getSubscriptionsForQueue(tx, queueID, adapterType);
        	for (SIF3Subscription subscription : subscriptions)
        	{
        		tx.getSession().delete(subscription);
        	}
        	return true;
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to remove Subscriptions for queueID = '"+ queueID + " and adapterType = '" + adapterType + "'.", e);
        }	
	}
    
    /*
     * The current implementation is not the most efficient. Since we only will have very few subscriptions for a 
     * queue or we really never should delete subscriptions this operations will be used rarely and will not have 
     * any impact on performance. If it is used it will be called at startup and/or shut down but not during standard
     * operation of an adapter.
     */
    public boolean removeAllSubscriptionsForEnvironment(BasicTransaction tx, String environmentID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
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
        	List<SIF3Subscription> subscriptions = getSubscriptionsForEnvironment(tx, environmentID, adapterType);
        	for (SIF3Subscription subscription : subscriptions)
        	{
        		tx.getSession().delete(subscription);
        	}
        	return true;
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to remove Subscriptions for environmentID = '"+ environmentID + " and adapterType = '" + adapterType + "'.", e);
        }	
	}
    
    /*
     * This will create a subscription with the given values if it doesn't exist, yet. 
     * 
     * NOTE: Subscriptions cannot be updated! If an attempt is made then only the last accessed date is updated
     *       but no other fields.
     */
    public SIF3Subscription saveSubscription(BasicTransaction tx, SIF3Subscription subscription) throws IllegalArgumentException, PersistenceException
    {
    	if (subscription == null)
    	{
            throw new IllegalArgumentException("subscription parameter is null.");          
    	}
    	if (StringUtils.isEmpty(subscription.getSubscriptionID()))
    	{
            throw new IllegalArgumentException("subscription.subscriptionID property is null or empty. It must be set.");          
    	}
    	if (StringUtils.isEmpty(subscription.getAdapterType()))
    	{
            throw new IllegalArgumentException("subscription.adapterType property is null or empty. It must be set.");          
    	}
    	if (StringUtils.isEmpty(subscription.getZoneID()))
    	{
            throw new IllegalArgumentException("subscription.zoneID property is null or empty. It must be set.");          
    	}    	
    	if (StringUtils.isEmpty(subscription.getQueueID()))
    	{
            throw new IllegalArgumentException("subscription.queueID property is null or empty. It must be set.");          
    	}
    	if (StringUtils.isEmpty(subscription.getServiceName()))
    	{
            throw new IllegalArgumentException("subscription.serviceName property is null or empty. It must be set.");          
    	}
    	if (StringUtils.isEmpty(subscription.getServiceType()))
    	{
            throw new IllegalArgumentException("subscription.serviceType property is null or empty. It must be set to OBJECT, UTILITY or FUNCTION.");          
    	}
    	
    	// If context is not given we set it to default context
    	if (StringUtils.isEmpty(subscription.getContextID()))
    	{
            subscription.setContextID(CommonConstants.DEFAULT_CONTEXT_NAME);         
    	}
    	
    	try
    	{
	    	// Check if queue already exist.
    		SIF3Subscription dbSubscription = getSubscription(tx, subscription.getSubscriptionID(), CommonConstants.AdapterType.valueOf(subscription.getAdapterType()));
    		if (dbSubscription == null) // doesn't exist
	    	{
	    		subscription.setCreated(new Date());
	    		dbSubscription = subscription;
	    		
	    		// We can only create subscriptions for existing queues. Check if queue is valid.
	            SIF3QueueDAO queueDAO = new SIF3QueueDAO();
	            SIF3Queue queue = queueDAO.getQueue(tx, subscription.getQueueID(), CommonConstants.AdapterType.valueOf(subscription.getAdapterType()));
	    		if (queue == null) // there is no queue for the requested subscription
	    		{
	    			throw new IllegalArgumentException("There is no known queue with the ID = '"+subscription.getQueueID()+"'. Cannot create subscription.");
	    		}
	    	}
	    	else
	    	{
	    		dbSubscription.setLastAccessed(new Date());
	    		logger.debug("Update subscription with ID = "+subscription.getSubscriptionID()+" is requested. Only Last Accessed Date is updated as subscriptions ar immutable!");
	    	}
	    	tx.getSession().saveOrUpdate(dbSubscription);
	    	return subscription;
    	}
        catch (Exception e)
        {
            throw new PersistenceException("Unable to create or update Subscription "+ subscription +": "+e.getMessage(), e);
        }	
    }
    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
}
