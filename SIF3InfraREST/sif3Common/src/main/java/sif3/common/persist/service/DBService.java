/*
 * DBService.java
 * Created: 04/02/2014
 *
 * Copyright 2014 Systemic Pty Ltd
 * 
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.common.exception.PersistenceException;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.common.HibernateUtil;
import sif3.common.persist.dao.BaseDAO;


/**
 * @author Joerg Huber
 *
 */
public abstract class DBService
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

	public abstract BaseDAO getDAO();
	
	public DBService()
	{
	}
	
	public BasicTransaction startTransaction()
	{
		BasicTransaction tx = new BasicTransaction(); 
		tx.startTransaction();
		return tx;
	}
	
	/* Convenience method to 'hide' hibernate as the underlying Data Access Engine */
	public void loadSubObject(Object proxy)
	{
		HibernateUtil.loadSubObject(proxy);
	}
	
	/*
	 * This method takes the given exception and simply re-throws it if it is a IllegalArgumentException, PersistenceException.
	 * Any other exception is mapped to a persistence exception since this service mainly deals with DB operations.
	 * The given errorMsg is added to the IllegalArgumentException or PersistenceException if addErrorMsgToStandardEx is
	 * true. If the exception is any other type then the error message is added regardless addErrorMsgToStandardEx parameter.
	 * The errorMsg is logged if logError is set to true.
	 */
	protected void exceptionMapper(Exception ex, String errorMsg, boolean logError, boolean addErrorMsgToStandardEx) throws IllegalArgumentException, PersistenceException
	{
		if (logError)
		{
			logger.error(errorMsg, ex);
		}
		if (ex instanceof IllegalArgumentException)
		{
			if (addErrorMsgToStandardEx)
			{
				throw new IllegalArgumentException(errorMsg, ex);
			}
			throw (IllegalArgumentException)ex;				
		}
		if 	(ex instanceof PersistenceException)
		{
			if (addErrorMsgToStandardEx)
			{
				throw new PersistenceException(errorMsg, ex);
			}
			throw (PersistenceException)ex;			
		}
		
		// If we get here the ex is of any other type
		throw new PersistenceException(errorMsg, ex);
	}
	
	protected void rollback(BasicTransaction tx)
	{
		if (tx != null)
		{
			tx.rollback();
		}
	}
}
