/*
 * SIF3SubscriptionService.java
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
import sif3.common.persist.dao.SIF3SubscriptionDAO;
import sif3.common.persist.model.SIF3Subscription;

/**
 * @author Joerg Huber
 *
 */
public class SIF3SubscriptionService extends DBService
{
    private SIF3SubscriptionDAO sif3SubscriptionDAO = new SIF3SubscriptionDAO();
    
    @Override
    public BaseDAO getDAO()
    {
	    return sif3SubscriptionDAO;
    }

    public SIF3Subscription getSubscription(String subscriptionID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
	{
    	SIF3Subscription row = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	row = sif3SubscriptionDAO.getSubscription(tx, subscriptionID, adapterType);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve Subcription for subscriptionID = '"+ subscriptionID +"' and adapterType = '" + adapterType + "'.", true, false);
    	}
		return row;
	}
	
    public List<SIF3Subscription> getSubscriptionsForQueue(String queueID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
	{
    	List<SIF3Subscription> rows = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	rows = sif3SubscriptionDAO.getSubscriptionsForQueue(tx, queueID, adapterType);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve subscriptions for queueID = '"+ queueID +"' and adapterType = '" + adapterType + "'.", true, false);
    	}
		return rows;
	}

    public List<SIF3Subscription> getSubscriptionsForEnvironment(String environmentID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
	{
    	List<SIF3Subscription> rows = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	rows = sif3SubscriptionDAO.getSubscriptionsForEnvironment(tx, environmentID, adapterType);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve subscriptions for environmentID = '"+ environmentID +"' and adapterType = '" + adapterType + "'.", true, false);
    	}
		return rows;
	}
    
    public boolean removeSubscription(String subscriptionID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
	    BasicTransaction tx = null;	    	
		try
		{
			tx = startTransaction();
			if (sif3SubscriptionDAO.removeSubscription(tx, subscriptionID, adapterType))
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
    		exceptionMapper(ex, "Failed to remove subscription for subscriptionID = '"+ subscriptionID +"' and adapterType = '" + adapterType + "'.", true, false);
    		return false;
		}		    	
    }
    
    public boolean removeAllSubscriptionsForQueue(String queueID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
	{
	    BasicTransaction tx = null;	    	
		try
		{
			tx = startTransaction();
			if (sif3SubscriptionDAO.removeAllSubscriptionsForQueue(tx, queueID, adapterType))
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
    		exceptionMapper(ex, "Failed to remove subscriptions for queueID = '"+ queueID +"' and adapterType = '" + adapterType + "'.", true, false);
    		return false;
		}		    	
	}
    
    public boolean removeAllSubscriptionsForEnvironment(String environmentID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
	{
	    BasicTransaction tx = null;	    	
		try
		{
			tx = startTransaction();
			if (sif3SubscriptionDAO.removeAllSubscriptionsForEnvironment(tx, environmentID, adapterType))
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
    		exceptionMapper(ex, "Failed to remove subscriptions for environmentID = '"+ environmentID +"' and adapterType = '" + adapterType + "'.", true, false);
    		return false;
		}		    	
	}
    
    public SIF3Subscription saveSubscription(SIF3Subscription subscription) throws IllegalArgumentException, PersistenceException
    {
	    BasicTransaction tx = null;	    	
		try
		{
			tx = startTransaction();
			SIF3Subscription returnSubscription = sif3SubscriptionDAO.saveSubscription(tx, subscription);
			if (returnSubscription != null)
			{
				tx.commit();
			}
			else
			{
	    		rollback(tx);
			}
			
			return returnSubscription;
		}
		catch (Exception ex)
		{
    		rollback(tx);
    		exceptionMapper(ex, "Unable to create or update Subscription "+ subscription, false, false); // error already logged.
    		return null;
		}		    	
    }
}
