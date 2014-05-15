/*
 * ProviderServletContext.java
 * Created: 09/03/2014
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

package sif3.infra.rest.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import sif3.common.persist.common.HibernateUtil;
import sif3.infra.common.env.mgr.ProviderManagerFactory;
import sif3.infra.common.interfaces.EnvironmentConnector;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.rest.env.connectors.EnvironmentConnectorFactory;
import sif3.infra.rest.provider.ProviderFactory;
import au.com.systemic.framework.utils.StringUtils;

/**
 * This class is to initialise the provider at startup and clean up resources at shutdown.
 * 
 * @author Joerg Huber
 *
 */
public class ProviderServletContext implements ServletContextListener
{
	protected final Logger logger = Logger.getLogger(getClass());
	
	/* Context Property name: <context-param>...<param-name> Node in web.xml */ 
	private static final String SERVICE_PROPERTY_FILE_TAG = "SERVICE_PROPERTY_FILE";

   	private EnvironmentConnector connector = null;
	
   	/*
   	 * (non-Javadoc)
   	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
   	 */
    public void contextInitialized(ServletContextEvent servletCtxEvent) 
    {
    	boolean allOK = true;
		logger.info("Initialise Provider: start");
		logger.debug("Initialise DB Connection Pool....");
		if (HibernateUtil.getSessionFactory() == null)
		{
			logger.error("Failed to initialise DB connection pool.");
			allOK = false;
		}
		else
		{
			String propertyFileName = servletCtxEvent.getServletContext().getInitParameter(SERVICE_PROPERTY_FILE_TAG);
			logger.info("Provider property file to use: " + propertyFileName + ".properties");
	
			// Check if we need to initialise the service properties. This should only happens once.
			if (StringUtils.notEmpty(propertyFileName))
			{				
				logger.debug("Initialise Provider Environment Manager and Session Store...");
				EnvironmentManager envMgr = ProviderManagerFactory.initialse(propertyFileName);
				
				logger.debug("Attempt to connect to Environment Provider...");
				connector = EnvironmentConnectorFactory.getEnvironmentConnector(envMgr);
				if (connector != null)
				{
					if (connector.connect())
					{
						logger.debug("Initialise Provider Factory...");
						if (ProviderFactory.createFactory(envMgr.getServiceProperties()) == null)
					    {
							logger.error("Failed to initialise Provider Factory. See prveious error logs for details.");
							allOK = false;
					    }					
					}
					else
					{
						allOK = false; // error already logged.
					}
				}
				else
				{
					logger.error("Failed to establish connection with Environment Provider. See prveious error logs for details.");
					allOK = false;
				}
			}
			else
			{
				logger.error("Can not retrieve the Service Property File name from web.xml");
				allOK = false;
			}
		}
		logger.info("Initialise Provider sucessful: "+allOK);
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletCtxEvent) 
    {
        logger.info("Shutdown Provider...");
        EnvironmentManager envMgr = ProviderManagerFactory.getEnvironmentManager();

		if ((connector != null) && (envMgr != null))
		{
	        logger.debug("Disconnect provider from Environment Provider: Remove Environment in Full = "+envMgr.getEnvironmentInfo().getRemoveEnvOnShutdown());
			connector.disconnect(envMgr.getEnvironmentInfo().getRemoveEnvOnShutdown());
		}

        logger.debug("Shutdown Provider Factory...");
        ProviderFactory.shutdown();
		
        logger.debug("Release DB Connections....");
        HibernateUtil.shutdown();
        logger.info("Shutdown Provider: done.");
    }
}
