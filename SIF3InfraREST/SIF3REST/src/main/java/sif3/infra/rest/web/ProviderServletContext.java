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

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.StringUtils;
import sif3.common.exception.PersistenceException;
import sif3.common.interfaces.HibernateProperties;
import sif3.common.persist.common.HibernateHelper;
import sif3.common.persist.common.HibernateUtil;
import sif3.infra.common.env.mgr.ProviderManagerFactory;
import sif3.infra.common.interfaces.EnvironmentConnector;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.rest.env.connectors.EnvironmentConnectorFactory;
import sif3.infra.rest.provider.ProviderFactory;

/**
 * This class is to initialise the provider at startup and clean up resources at shutdown.
 * 
 * @author Joerg Huber
 *
 */
public class ProviderServletContext implements ServletContextListener
{
   protected final Logger logger = LoggerFactory.getLogger(getClass());
	
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
		
        String propertyFileName = servletCtxEvent.getServletContext().getInitParameter(SERVICE_PROPERTY_FILE_TAG);
        logger.info("Provider property file to use: " + propertyFileName + ".properties");
        
        // Check if we have a property file. If not then we should stop....
        if (StringUtils.notEmpty(propertyFileName))
        {
            // Initialise Hibernate...
            HibernateHelper hbrHelper = new HibernateHelper();
            HibernateProperties hbrProps = null;
            try
            {
                hbrProps = hbrHelper.getHiberbnateProperties(propertyFileName);
                logger.debug("Initialise DB Connection Pool....");
                if (!HibernateUtil.initialise((hbrProps != null) ? hbrProps.getProperties() : null))
                {
                    logger.error("Failed to initialise DB connection pool.");
                    allOK = false;
                }
            }
            catch (PersistenceException ex)
            {
                logger.error("Failed to retrieve hibernate properties: "+ex.getMessage());
                allOK = false;
            }
        }
        else
        {
            logger.error("Can not retrieve the Service Property File name from web.xml");
            allOK = false;
        }

        // Only continue if all is fine up to here.
        if (allOK)
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
			
			// If all is good till now we try to install the Auditor Filter
			installAuditorFilter(servletCtxEvent, envMgr.getServiceProperties());
		}
		logger.info("Initialise Provider sucessful: "+allOK);
		
		// Force a RuntimeExcpetion. According to some sites this is the only way that the web-app is undeployed if
		// The servlet context listener reports an error. It further depends on the web/app server is such an
		// exception is honoured and indeed will undeploy the webapp. But this behaviour is outside the control
		// of this class.
		if (!allOK)
		{
			throw new RuntimeException("Failed to initialse ProviderServletContext. See previous error log entries for details");
		}
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
    
    /*---------------------*/
    /*-- Private methods --*/
    /*---------------------*/
    private void installAuditorFilter(ServletContextEvent servletCtxEvent, AdvancedProperties props)
    {
    	String auditClass = props.getPropertyAsString("adapter.audit.service", null);
    	if (StringUtils.isEmpty(auditClass)) // none defined. No need to install audit filter
    	{
    		logger.debug("Property adapter.audit.service is not set in provider property file. No audit filter will be installed.");
    		//return true;
    	}
    	else
    	{
    		logger.debug("Audit Class to use: "+auditClass+". Install Audit Service...");
    		try
    		{
        		Dynamic registration = servletCtxEvent.getServletContext().addFilter("auditFilter", new AuditFilter());
        		registration.setInitParameter(AuditFilter.AUDIT_SERVICE_CLASS, auditClass);
        		registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        		
        		logger.debug("Audit Service Installed successfully.");
        		//return true;
    		}
    		catch (Exception ex)
    		{
    			logger.error("Failed to install Audit Service: "+ex.getMessage(), ex);
    			//return false;
    		}
    	}
    }
    
}
