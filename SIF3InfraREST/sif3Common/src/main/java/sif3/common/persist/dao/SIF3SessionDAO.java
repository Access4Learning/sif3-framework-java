/*
 * SIF3SessionDAO.java
 * Created: 30/01/2014
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
import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.model.EnvironmentKey;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.model.SIF3Session;

/**
 * This class provides some low level DB access to SIF3 session information.
 *  
 * @author Joerg Huber
 *
 */
public class SIF3SessionDAO extends BaseDAO
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
	
    @SuppressWarnings("unchecked")
    public SIF3Session getSessionBySolutionAppkeyUserInst(BasicTransaction tx, EnvironmentKey environmentKey, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        if (environmentKey == null)
        {
          throw new IllegalArgumentException("environmentKey and/or null.");          
        }
        
        if (StringUtils.isEmpty(environmentKey.getApplicationKey()) || (adapterType == null))
        {
            throw new IllegalArgumentException("solutionID, applicationKey and/or adapterType is empty or null.");
        }

        try
        {
            Criteria criteria = tx.getSession().createCriteria(SIF3Session.class)
               .add(Restrictions.eq("applicationKey", environmentKey.getApplicationKey()))
               .add(Restrictions.eq("adapterType", adapterType.name()));
            
            // SolutionID is optional. Don't set it to null as a criteria as it might be retrieved.
            if (StringUtils.notEmpty(environmentKey.getSolutionID()))
            {
              criteria = criteria.add(Restrictions.eq("solutionID", environmentKey.getSolutionID()));
            }

            if (StringUtils.notEmpty(environmentKey.getUserToken()))
            {
            	criteria = criteria.add(Restrictions.eq("userToken", environmentKey.getUserToken()));
            }
            else
            {
            	criteria = criteria.add(Restrictions.isNull("userToken"));
            }
            
            if (StringUtils.notEmpty(environmentKey.getInstanceID()))
            {
            	criteria = criteria.add(Restrictions.eq("instanceID", environmentKey.getInstanceID()));
            }
            else
            {
            	criteria = criteria.add(Restrictions.isNull("instanceID"));
            }

            List<SIF3Session> sessions = criteria.list();
            
            // There can only be a maximum of one
            if (sessions.isEmpty()) // not in cache, yet
            {
            	logger.debug("No Session for solutionID = '"+ environmentKey.getSolutionID() + "', applicationKey = '" + environmentKey.getApplicationKey() + "', adapterType = '" + adapterType + "', userToken = '" + environmentKey.getUserToken() + "' and instanceID = '"+environmentKey.getInstanceID()+"' exists.");
                return null;
            }
            else // already exists
            {
            	SIF3Session session = sessions.get(0);
            	
                return session;
            }
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve SIF3 session for solutionID = '"+ environmentKey.getSolutionID() + "', applicationKey = '" + environmentKey.getApplicationKey() + "', adapterType = '" + adapterType + "', userToken = '" + environmentKey.getUserToken() + "' and instanceID = '"+environmentKey.getInstanceID()+"'.", e);
        }
    }

    public SIF3Session getSessionByEnvID(BasicTransaction tx, String environmentID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
    	return getSession(tx, "environmentID", environmentID, adapterType);
    }

    public SIF3Session getSessionBySessionToken(BasicTransaction tx, String sessionToken, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
    	return getSession(tx, "sessionToken", sessionToken, adapterType);
    }

    public SIF3Session getSessionBySecurityToken(BasicTransaction tx, String securityToken, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
      return getSession(tx, "securityToken", securityToken, adapterType);
    }

    public void save(BasicTransaction tx, SIF3Session sif3Session) throws IllegalArgumentException, PersistenceException
    {
        if (sif3Session == null)
        {
            throw new IllegalArgumentException("sif3Session is null.");
        }

        // applicationKey & envrionmentType are mandatory. Check if the are available.
        if (StringUtils.isEmpty(sif3Session.getApplicationKey()) || (sif3Session.getAdapterType() == null))
        {
            throw new IllegalArgumentException("applicationKey and/or adapterType is empty or null.");
        }
        
        try
        {
        	if (sif3Session.getSessionID()<=0) // new session
        	{
        		sif3Session.setCreated(new Date());
        	}
    		sif3Session.setLastAccessed(new Date());
        	tx.getSession().saveOrUpdate(sif3Session);
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to save sif3Session object " + sif3Session + ".", e);
        }    	
    }

    /*
     * This will also remove associated queues and subscriptions to queues!
     */
    public void removeSessionBySessionToken(BasicTransaction tx, String sessionToken, AdapterType adapterType)  throws IllegalArgumentException, PersistenceException
    {
    	try
    	{
    		SIF3QueueDAO queueDAO = new SIF3QueueDAO();
    		SIF3Session sif3Session = getSessionBySessionToken(tx, sessionToken, adapterType);
    		if (sif3Session != null)
    		{
    			// First we delete queues & subscriptions associated with this session
    			queueDAO.removeAllQueuesForEnvironment(tx, sif3Session.getEnvironmentID(), adapterType);
    			
    			tx.getSession().delete(sif3Session);
    		}
    	}
        catch (HibernateException e)
        {
            throw new PersistenceException("Failed to delete sif3Session for sessionToken " + sessionToken + ".", e);
        }    	
    }
     
    /*
     * This will also remove associated queues and subscriptions to queues!
     */
    public void removeSessionByEnvironmentID(BasicTransaction tx, String environmentID, AdapterType adapterType)  throws IllegalArgumentException, PersistenceException
    {
    	try
    	{
    		SIF3QueueDAO queueDAO = new SIF3QueueDAO();
    		SIF3Session sif3Session = getSessionByEnvID(tx, environmentID, adapterType);
    		if (sif3Session != null)
    		{
    			// First we delete queues & subscriptions associated with this session
    			queueDAO.removeAllQueuesForEnvironment(tx, sif3Session.getEnvironmentID(), adapterType);
    			
    			tx.getSession().delete(sif3Session);
    		}
    	}
        catch (HibernateException e)
        {
            throw new PersistenceException("Failed to delete sif3Session for environmentID " + environmentID + ".", e);
        }    	
    }
    
    /*---------------------*/
    /*-- Private methods --*/
    /*---------------------*/
    @SuppressWarnings("unchecked")
    private SIF3Session getSession(BasicTransaction tx, String criteriaName, String criteriaValue, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        if (StringUtils.isEmpty(criteriaValue) )
        {
            throw new IllegalArgumentException(criteriaName+" is empty or null.");
        }

        try
        {
            Criteria criteria = tx.getSession().createCriteria(SIF3Session.class)
            			.add(Restrictions.eq(criteriaName, criteriaValue))
            			.add(Restrictions.eq("adapterType", adapterType.name()));
            List<SIF3Session> sessions = criteria.list();
            
            // There can only be a maximum of one
            if (sessions.isEmpty()) // not in cache, yet
            {
            	logger.debug("No Session for "+criteriaName+" = '"+ criteriaValue + "' and Adpater Type = "+adapterType.name()+" exists.");
                return null;
            }
            else // already exists
            {
            	SIF3Session session = sessions.get(0);
            	
                return session;
            }
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve SIF3 session for "+criteriaName+" = '"+ criteriaValue + "'.", e);
        }
    }
}
