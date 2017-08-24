package sif3.common.persist.common;

import java.util.Properties;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.PropertyFileReader;

public class HibernateUtil
{
    public static final String HIBERNATE_PROP_NAME = "hibernate";
    public static final String SIF3INFRA_HIBERNATE_PROP_NAME = "sif3infra."+HIBERNATE_PROP_NAME;
    
	protected final static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
	
	private static SessionFactory sessionFactory = null;

	/**
	 * This method initialises this Hibernate. It will setup some static elements within this class. Ideally this method
	 * is called at the beginning of any SIF3 Adapter. Only if this method returns true it can be assumed that hibernate is
	 * initialise correctly and any other method within this class can successfully be used. If the properties parameter is not
	 * null then the initialising of the hibernate will utilise these properties as described in the hibernate developer's
	 * guide. Properties such as username, password, DB connection URL etc. can be set in the properties parameter and they
	 * will be applied accordingly. If a given property is set in the hibernate.cfg.xml file and as a property in the 
	 * "properties" parameter then the value in the hibernate.cfg.xml file will take precedence (i.e. value in "properties"
	 * will be ignored).
	 * 
	 * @param properties Can be null. Otherwise see description for details.
	 * 
	 * @return True if initialise method completed successfully, false otherwise.
	 */
	public static boolean initialise(Properties properties)
	{
        Session session = null;
        Transaction tx = null;
        try
        {
            // This will also load the hibernate.properties and make them available with the getProperties() method.
            Configuration configuration = new Configuration(); //.configure("sif3infra.hibernate.cfg.xml");
                        
            // Get properties from hibernate.properties if available
            Properties hibernateProps = getHibernateProperties(HIBERNATE_PROP_NAME);

            // Merge the two sets. sif3Infra.hibernate should overwrite hibernate to allow "inheritance"
            logger.debug("Merge sif3infra.hibernate.properties with hibernate.properties. sif3infra.hibernate.properties will take precedence ...");
            hibernateProps.putAll(getHibernateProperties(SIF3INFRA_HIBERNATE_PROP_NAME));
            
            if ((properties != null) && (!properties.isEmpty()))
            {
                logger.debug("Merge hibernate properties with injected Properties. Injected Properties will take precedence ...");
                hibernateProps.putAll(properties);
            }
            logger.debug("Set all Hibernate Properties and merge them with sif3infra.hibernate.cfg.xml.");
            configuration.setProperties(hibernateProps);
            configuration.configure("sif3/common/persist/model/sif3infra.hibernate.cfg.xml");
            
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()). build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
            //even if the above fails, i.e. cannot allocate a connection then no error is reported and the 
            //sessionFactory is created. The only way to find out if it succeeded is by trying to get a connection.
            //So let's try this here and if no connection can be allocated then report the error that way.
            session = getSessionFactory().openSession();
            
            // Getting a transaction will throw an exception if hibernate had an issue allocating a connection pool.
            tx = session.beginTransaction();            
        }
        catch (Exception ex)
        {
            logger.error("Failed to initialise hibernate and connection pool: "+ex.getMessage(), ex);
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
        
        // If we have a sessionFactory then we are all good and things appear to have initialised properly.
        return sessionFactory != null;
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
	
	/*---------------------*/
	/*-- Private Methods --*/
    /*---------------------*/
	private static Properties getHibernateProperties(String fileNameWithoutExt)
	{
	    PropertyFileReader reader = new PropertyFileReader(fileNameWithoutExt);
	    Properties props = reader.getProperties();
	    
	    // properties are null if file does not exist or there is a problem with accessing it. This is already
	    // logged. For the purpose of this class that might be fine, so we return empty properties.
	    return props == null ? new Properties() : props;
	}
}
