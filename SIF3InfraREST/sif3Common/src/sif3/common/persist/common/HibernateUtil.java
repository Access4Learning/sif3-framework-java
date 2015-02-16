package sif3.common.persist.common;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
	protected static final Logger logger = Logger.getLogger(HibernateUtil.class);
	
	private static SessionFactory sessionFactory = null;

	static
	{
		Session session = null;
		Transaction tx = null;
		try
		{
		    sessionFactory = new  Configuration().configure("sif3infra.hibernate.cfg.xml").buildSessionFactory();
		    
		    //even if the above fails, i.e. cannot allocate a connection then no error is reported and the 
		    //sessionFactory is created. The only way to find out if it succeeded is by trying to get a connection.
		    //So let's try this here and if no connection can be allocated then report the error that way.
		    session = getSessionFactory().openSession();
		    
		    // Getting a transaction will throw an exception if hibernate had an issue allocating a connection pool.
		    tx = session.beginTransaction();		    
		}
		catch (Exception e)
		{
			logger.error("Failed to initialise hibernate and connection pool: "+e.getMessage(), e);
			sessionFactory = null;
		}
		finally 
		{
			// Close test session and connection.
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

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public static void loadSubObject(Object proxy)
	{
		Hibernate.initialize(proxy);
	}

	public static void shutdown()
	{
		try
		{
			if (getSessionFactory() != null)
			{
				getSessionFactory().close();
			}
		}
		catch (Exception ex) {} // nothing we can really do!
	}
}
