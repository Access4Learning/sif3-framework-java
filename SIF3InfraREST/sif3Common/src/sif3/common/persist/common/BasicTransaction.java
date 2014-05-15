/*
 * BasicTransaction.java
 * Created: 24/04/2012
 *
 * Copyright 2011 Systemic Pty Ltd
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

package sif3.common.persist.common;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Joerg Huber
 *
 */
public class BasicTransaction
{
	private Session session = null;
	private Transaction tx = null;
	
	public BasicTransaction() {}

	/**
	 * This method will create the session and start the transaction scope.
	 */
	public void startTransaction()
	{
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}
	
	/**
	 * Returns the session. It is assumed that startTransaction() method is called first.
	 * 
	 * @return A active session or null if startTransaction() has not been called successfully.
	 */
	public Session getSession()
	{
		return session;
	}
	
	/**
	 * Returns the active transaction. It is assumed that startTransaction() method is called first.
	 * 
	 * @return A active transaction or null if startTransaction() has not been called successfully.
	 */
	public Transaction getTransaction()
	{
		return tx;
	}
	
	/** 
	 * Commits changes and finalises transaction and the session. 
	 */
	public void commit()
	{
		if ((tx != null) && tx.isActive())
		{
			tx.commit();
			tx = null;
		}
		if (session != null)
		{
			session.close();
			session = null;
		}
	}
	
	/** 
	 * Rollback changes and finalises transaction and session
	 */
	public void rollback()
	{
		if ((tx != null) && tx.isActive())
		{
			tx.rollback();
			tx = null;
		}
		if (session != null)
		{
			session.close();
			session = null;
		}
	}
}
