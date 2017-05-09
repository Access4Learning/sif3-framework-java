/*
 * SIF3SessionService.java
 * Created: 04/02/2014
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

import java.util.Date;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.model.EnvironmentKey;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.dao.BaseDAO;
import sif3.common.persist.dao.SIF3SessionDAO;
import sif3.common.persist.model.SIF3Session;
import sif3.common.utils.UUIDGenerator;

/**
 * @author Joerg Huber
 *
 */
public class SIF3SessionService extends DBService
{
    private SIF3SessionDAO sif3SessionDAO = new SIF3SessionDAO();
    
    @Override
    public BaseDAO getDAO()
    {
	    return sif3SessionDAO;
    }

    public SIF3Session getSessionBySolutionAppkeyUserInst(EnvironmentKey environmentKey, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
    	SIF3Session row = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	row = sif3SessionDAO.getSessionBySolutionAppkeyUserInst(tx, environmentKey, adapterType);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve session for solutionID = '"+ environmentKey.getSolutionID() + "', applicationKey = '" + environmentKey.getApplicationKey() + "', adapterType = '" + adapterType + "', userToken = '" + environmentKey.getUserToken() + "' and instanceID = '"+environmentKey.getInstanceID()+"'.", true, false);
    	}
		return row;
    }

    public SIF3Session getSessionByEnvID(String environmentID, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
    	SIF3Session row = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	row = sif3SessionDAO.getSessionByEnvID(tx, environmentID, adapterType);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve session for environmentID = '"+ environmentID + "'.", true, false);
    	}
		return row;
    }

    public SIF3Session getSessionBySessionToken(String sessionToken, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
    	SIF3Session row = null;
    	BasicTransaction tx = null;

    	try
    	{
    		tx = startTransaction();
        	row = sif3SessionDAO.getSessionBySessionToken(tx, sessionToken, adapterType);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to retrieve session for sessionToken = '"+ sessionToken + "'.", true, false);
    	}
		return row;
    }

    public SIF3Session getSessionBySecurityToken(String securityToken, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        SIF3Session row = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            row = sif3SessionDAO.getSessionBySecurityToken(tx, securityToken, adapterType);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to retrieve session for securityToken = '" + securityToken + "'.", true, false);
        }
        return row;
    }

    public void save(SIF3Session sif3Session) throws IllegalArgumentException, PersistenceException
    {
    	BasicTransaction tx = null;	    	
    	try
    	{
    		tx = startTransaction();
    		sif3SessionDAO.save(tx, sif3Session);
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to save SIF3 Session:\n"+sif3Session, true, false);
    	}
    }
    
    /**
     * This will create a new session with the given values in the session store. No action is taken if the session for the
     * given values does already exist. In that case the existing session is returned. If the session does not exist it will
     * be created. In this case a sessionToken and an environmentID will be allocated as part of this method. If the session
     * cannot be created for whatever reason then the error is logged and null is returned.
     * 
     * @param sif3Session : solutionID: Mandatory, applicationKey Mandatory, userToken Optional, instanceID Optional, adapterType Mandatory
     * 
     * @return See desc
     * 
     * @throws IllegalArgumentException One of the mandatory parameters is null.
     * @throws PersistenceException Failed to access underlying store.
     */
    public SIF3Session createNewSession(SIF3Session sif3Session) throws IllegalArgumentException, PersistenceException
    {
    	SIF3Session session = null;
    	BasicTransaction tx = null;	    	
    	try
    	{
    		tx = startTransaction();
    		session = createNewSession(tx, sif3Session, AdapterType.valueOf(sif3Session.getAdapterType()));
    		
    		// store remaining fields
    		session.setAdapterName(sif3Session.getAdapterName());
    		session.setEnvironmentXML(sif3Session.getEnvironmentXML());
    		session.setPassword(sif3Session.getPassword());
    		sif3SessionDAO.save(tx, session);

    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to create SIF3 session for solutionID = '"+ sif3Session.getSolutionID() + "', applicationKey = '" + sif3Session.getApplicationKey() + "', adapterType = '" + sif3Session.getAdapterType() + "', userToken = '" + sif3Session.getUserToken() + "' and instanceID = '"+sif3Session.getInstanceID()+"'. See previous error log entry for details.", true, false);
    	}
    	
    	return session;
    }
    
    /**
     * This will create a new session with the given values in the session store. No action is taken if the session for the
     * given values does already exist. In that case the existing session is returned. If the session does not exist it will
     * be created. In this case a sessionToken and an environmentID will be allocated as part of this method. If the session
     * cannot be created for whatever reason then the error is logged and null is returned.
     * 
     * @param environmentKey : solutionID: Mandatory, applicationKey Mandatory, userToken Optional, instanceID Optional
     * @param adapterType Mandatory
     * 
     * @return See desc
     * 
     * @throws IllegalArgumentException One of the mandatory parameters is null.
     * @throws PersistenceException Failed to access underlying store.
     */
    public SIF3Session createNewSession(EnvironmentKey environmentKey, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
    	SIF3Session session = null;
    	BasicTransaction tx = null;	    	
    	try
    	{
    		tx = startTransaction();
    		session = createNewSession(tx, environmentKey, adapterType);	  		
    		tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to create SIF3 session for solutionID = '"+ environmentKey.getSolutionID() + "', applicationKey = '" + environmentKey.getApplicationKey() + "', adapterType = '" + adapterType + "', userToken = '" + environmentKey.getUserToken() + "' and instanceID = '"+environmentKey.getInstanceID()+"'. See previous error log entry for details.", true, false);
    	}
    	
    	return session;
    }

    /*
     * This will also remove associated queues and subscriptions to queues!
     */
    public void removeSessionBySessionToken(String sessionToken, AdapterType adapterType)  throws IllegalArgumentException, PersistenceException
    {
    	BasicTransaction tx = null;	    	
    	try
    	{
    		tx = startTransaction();
    		sif3SessionDAO.removeSessionBySessionToken(tx, sessionToken, adapterType);
        	tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to remove SIF3 session for sessionToken = '"+sessionToken + "'.", true, true);
    	}		    	
    }
    
    
    /*
     * This will also remove associated queues and subscriptions to queues!
     */
    public void removeSessionByEnvironmentID(String environmentID, AdapterType adapterType)  throws IllegalArgumentException, PersistenceException
    {
    	BasicTransaction tx = null;	    	
    	try
    	{
    		tx = startTransaction();
    		sif3SessionDAO.removeSessionByEnvironmentID(tx, environmentID, adapterType);
        	tx.commit();
    	}
    	catch (Exception ex)
    	{
    		rollback(tx);
    		exceptionMapper(ex, "Failed to remove SIF3 session for environmentID = '"+environmentID + "'.", true, true);
    	}		    	
   }
    
    /*---------------------*/
    /*-- Private methods --*/
    /*---------------------*/
    
    /*
     * Will allocate the sessionToken and environmentID if the session doesn't exist, yet. 
     */
    private SIF3Session createNewSession(BasicTransaction tx, EnvironmentKey environmentKey, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
    	SIF3Session session = new SIF3Session(environmentKey);
    	
		// Check if a session does already exist
		SIF3Session dbSession = getSessionBySolutionAppkeyUserInst(environmentKey, adapterType);
		if (dbSession == null) // all good. We can create a new one.
		{
			session.setAdapterType(adapterType.name());
			session.setCreated(new Date());
			session.setEnvironmentID(UUIDGenerator.getUUID());
			session.setSessionToken(UUIDGenerator.getUUID());
			session.setFingerprint(UUIDGenerator.getUUID());
			
			sif3SessionDAO.save(tx, session);
		}
		else
		{
			throw new PersistenceException("SIF3 Session for solutionID = '"+ environmentKey.getSolutionID() + "', applicationKey = '" + environmentKey.getApplicationKey() + "', adapterType = '" + adapterType + "', userToken = '" + environmentKey.getUserToken() + "' and instanceID = '"+environmentKey.getInstanceID()+"' does already exist.");
		}
    	
    	return session;
    }

}
