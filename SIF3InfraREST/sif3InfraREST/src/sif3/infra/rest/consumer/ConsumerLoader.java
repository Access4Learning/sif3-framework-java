/*
 * ConsumerLoader.java Created: 07/05/2014
 * 
 * Copyright 2014 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package sif3.infra.rest.consumer;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import sif3.common.model.ServiceInfo;
import sif3.common.persist.common.HibernateUtil;
import sif3.common.persist.model.SIF3Session;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.interfaces.EnvironmentConnector;
import sif3.infra.rest.env.connectors.EnvironmentConnectorFactory;
import sif3.infra.rest.queue.QueueListenerConfigurator;
import sif3.infra.rest.queue.RemoteMessageQueueReader;
import sif3.infra.rest.queue.connectors.ConsumerQueueConnector;
import sif3.infra.rest.queue.connectors.ConsumerSubscriptionConnector;
import sif3.infra.rest.queue.types.QueueListenerInfo;
import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.StringUtils;

/**
 * This is the initialise/finalise class that must be called at startup or shutdown of any consumer adapter/service. The methods in here 
 * are only required to be called once. In fact the singleton nature of this class will ensure that methods can only be called once.
 * 
 * The purpose of this class is to initialise a consumer at startup. This includes but is not limited to:<br/>
 * - start DB connection Pools <br/>
 * - connect to environment provider and load/create a SIF Environment <br/>
 * - Initialise and configure queue, subscriptions and message processors where applicable <br/>
 * - etc<br/><br/>
 * 
 * Once consumer is shutdown the finalise method of this class should be called to free up all allocated resources and shut down related
 * components.
 * 
 * @author Joerg Huber
 */
public class ConsumerLoader
{
  protected static final Logger logger = Logger.getLogger(ConsumerLoader.class);

  private static ConsumerLoader instance = null;
  
  private EnvironmentConnector connector = null;
  private ConsumerQueueConnector queueConnector = null;
  private ConsumerSubscriptionConnector subscriptionConnector = null;
  private List<AbstractEventConsumer<?>> eventConsumers = new ArrayList<AbstractEventConsumer<?>>();
  private List<AbstractConsumer> crudConsumers = new ArrayList<AbstractConsumer>();
  private List<ExecutorService> msgReaderServices = new ArrayList<ExecutorService>();
  
  /**
   * Initialises the consumer based on the given property file. If anything fails to initialise then an error is logged and this method
   * returns false. The consumer should not really continue in such a case as its behaviour is not defined and most likely will throw
   * exceptions in many places.
   *  
   * @param consumerPropertyFileName
   */
  public static synchronized boolean initialise(String consumerPropertyFileName)
  {
    if (instance == null)
    {
      try
      {
        instance = new ConsumerLoader(consumerPropertyFileName);
      }
      catch (Exception ex) // error already logged.
      {
        instance = null;
      }
    }
    
    return isInitialised();
  }
  
  /**
   * There are some places in the code where we assume that the initialise of this method has been called. Where this is the case this
   * method can be called to ensure that indeed the initialise has been called. If the initialise isn't called then it is really a
   * programming error rather than a runtime error. In many cases the consumer should shut down if the initialise is not called successfully.
   * 
   * @return TRUE: Consumer is initialised successfully. FALSE otherwise.
   */
  public static synchronized boolean isInitialised()
  {
    return instance != null;
  }
  
  public static synchronized void shutdown()
  {
    if (isInitialised())
    {
      instance.shutdownConsumer();
      instance = null;
      logger.info("Consumer shutdown complete.");
    }
    else
    {
      logger.info("Consumer was not started successfully. Nothing to shutdown.");
    }      
  }
  
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	/* Throws an exception if any part of the initialise fails. An error will be logged. */
	private ConsumerLoader(String consumerPropertyFileName) throws Exception
	{
		if (StringUtils.isEmpty(consumerPropertyFileName))
		{
			logAndThrowException("Consumer Property File name is null or empty. Cannot initialse consumer.");
		}

		logger.debug("Initialise DB Connection Pool....");
		if (HibernateUtil.getSessionFactory() == null)
		{
			logAndThrowException("Failed to initialise DB connection pool.");
		}

		// Check if we need to initialise the consumer properties. This should only happens once.
		logger.debug("Initialise Consumer Environment Manager and Session Store with property file " + consumerPropertyFileName + ".properties");
		ConsumerEnvironmentManager envMgr = ConsumerEnvironmentManager.initialse(consumerPropertyFileName);

		logger.debug("Connect to environment provider...");
		if (!getConsumerEnvironment().getIsConnected()) // connect to known environments if we are not yet connected
		{
			connector = EnvironmentConnectorFactory.getEnvironmentConnector(envMgr);
			if (connector != null)
			{
				if (connector.connect()) // If this fails then error is already logged
				{
					logger.debug("Connection to environment provider succeeded.");
				}
				else
				{
					shutdown();
					logAndThrowException("Failed to connect consumer to environment provider. See previous error log entries.");
				}
			}
			else
			{
				shutdown();
				logAndThrowException("Failed to retrieve a Environment Connector. See previoue error log entries for details.");
			}
		}
    
		logger.debug("Initialise consumers......");
		initialiseConsumers(ConsumerEnvironmentManager.getInstance().getServiceProperties());

		if (getConsumerEnvironment().getEventsEnabled() && getConsumerEnvironment().getEventsSupported())
		{
			logger.info("Events are enabled and supported. Event Processing will be started.");
			if (!initEventProcessor())
			{
				shutdown();
				logAndThrowException("Failed to initialise event processing. See previoue error log entries for details.");
			}
		}
		else
		{
			logger.info("Events are not enabled and supported. No event processing has been started.");
		}
		logger.info("Initialse Consumer sucessful.");
	}
  
	private void shutdownConsumer()
	{
		logger.debug("Shutdown process for consumer started...");
		ConsumerEnvironmentManager envMgr = ConsumerEnvironmentManager.getInstance();

		logger.debug("Shut down all remote message queue reader threads...");
		for (ExecutorService service : msgReaderServices)
		{
			service.shutdown();
		}
		
		logger.debug("Shut down each consumer ...");
		for (AbstractConsumer consumer : crudConsumers)
		{
			logger.debug("Shutdown " + consumer.getClass().getSimpleName());
			consumer.finalise();
		}

		for (AbstractEventConsumer<?> consumer : eventConsumers)
		{
			logger.debug("Shutdown " + consumer.getClass().getSimpleName());
			consumer.finalise();
		}

		logger.debug("Shutdown Subscription Connector...");
		if (subscriptionConnector != null)
		{
			subscriptionConnector.syncSubscriptionsAtShutDown();
		}

		logger.debug("Shutdown Queue Connector...");
		if (queueConnector != null)
		{
			queueConnector.syncQueuesAtShutDown();
		}

		logger.debug("Disconnect consumer from Environment Provider...");
		if ((connector != null) && (envMgr != null))
		{
			logger.debug("Remove Environment in Full = " + envMgr.getEnvironmentInfo().getRemoveEnvOnShutdown());
			connector.disconnect(envMgr.getEnvironmentInfo().getRemoveEnvOnShutdown());
			logger.debug("Consumer disconnected from Environment provider successfully");
		}

		logger.debug("Release DB Connections....");
		HibernateUtil.shutdown();
	}
  
	private ConsumerEnvironment getConsumerEnvironment()
	{
		return (ConsumerEnvironment) ConsumerEnvironmentManager.getInstance().getEnvironmentInfo();
	}

	private SIF3Session getSIF3Session()
	{
		return ConsumerEnvironmentManager.getInstance().getSIF3Session();
	}

	private void logAndThrowException(String errorText) throws Exception
	{
		logger.error(errorText);
		throw new Exception(errorText);
	}

	private boolean initEventProcessor()
	{
		// Get Event Services for all consumer
		List<ServiceInfo> allServices = new ArrayList<ServiceInfo>();
		for (AbstractEventConsumer<?> consumer : eventConsumers)
		{
			allServices.addAll(consumer.getEventServices());
		}

		// Sync up Queues and Subscriptions
		logger.debug("Start Queue Connector...");
		queueConnector = new ConsumerQueueConnector();
		if (!queueConnector.syncQueuesAtStartup())
		{
			return false;
		}
		
		logger.debug("Start Subscription Connector...");
		subscriptionConnector = new ConsumerSubscriptionConnector();
		if (!subscriptionConnector.syncSubscriptionsAtStartup(allServices))
		{
			return false;
		}
		
		// Initialise Queue Configuration
		HashMap<String, QueueListenerInfo> queueConfiguration = null;
		try
		{
			QueueListenerConfigurator queueConfigurator = new QueueListenerConfigurator(getSIF3Session().getEnvironmentID());
			
			// Join each event consumer to configurator
			for (AbstractEventConsumer<?> consumer : eventConsumers)
			{
				logger.debug("Set Local Queue Configuration for "+consumer.getClass().getSimpleName());
				queueConfigurator.joinListener(consumer.getLocalConsumerQueue(), consumer.getEventServices());
			}
			queueConfiguration = queueConfigurator.finalise();
		}
		catch (Exception ex)
		{
			return false; // error already logged
		}
		
		// Start up threads for event processing
		if ((queueConfiguration != null) && (queueConfiguration.keySet().size() > 0))
		{
			logger.debug("Event Processing required. Queues and Configuration available...");
			
			if (!startReadingRemoteQueues(queueConfiguration))
			{
				return false;
			}
		}
		else
		{
			logger.debug("Event Processing enabled but no event subscriptions available. Don't start reading from message queues.");
		}	
		return true;
	}
  
	/*-------------------------------------------------*/
	/*-- Reflection to Create instances of consumers --*/
	/*-------------------------------------------------*/
	private boolean startReadingRemoteQueues(HashMap<String, QueueListenerInfo> queueConfiguration)
	{
		int numThreads = getNumMsgReaderThreads();
		for (QueueListenerInfo queueListenerInfo : queueConfiguration.values())
		{
			msgReaderServices.add(startRemoteMessageReaderThreads(queueListenerInfo, numThreads));
		}
		return true;
	}

	/*
	 * Will initialise the threads and add them to the local consumer queue.
	 */
	private ExecutorService startRemoteMessageReaderThreads(QueueListenerInfo queueListenerInfo, int numThreads)
	{
		String remoteQueueName = getRemoteQueueName(queueListenerInfo);
		logger.debug("Start "+numThreads+" message readers for "+remoteQueueName);
		logger.debug("Total number of threads before starting message readers for "+remoteQueueName+" "+Thread.activeCount());
		ExecutorService service = Executors.newFixedThreadPool(numThreads);
		for (int i = 0; i < numThreads; i++)
		{
			String readerID = remoteQueueName+" - Reader "+(i+1);
			try
			{
				RemoteMessageQueueReader remoteReader = new RemoteMessageQueueReader(queueListenerInfo, readerID);
				logger.debug("Start Remote Reader "+readerID);
				service.execute(remoteReader);
			}
			catch (Exception ex)
			{
				logger.error("Failed to start message reader thread for : "+readerID);
			}
		}
		logger.debug(numThreads+" "+remoteQueueName+" message readers initilaised and started.");
		logger.debug("Total number of threads after starting message readers for "+remoteQueueName+" "+Thread.activeCount());
		return service;
	}
	
	private void initialiseConsumers(AdvancedProperties adapterProps)
	{
		List<String> classList = adapterProps.getFromCommaSeparated("consumer.classes");
		String basePackageName = makePackageName(adapterProps.getPropertyAsString("consumer.basePackageName", ""));
		for (String className : classList)
		{
			logger.debug("Consumer class to initialse: " + className);
			try
			{
				Class<?> clazz = Class.forName(basePackageName + className);

				// Set info for Constructor parameters - No parameters
				Constructor<?> ct = clazz.getConstructor(new Class[] {});

				// Instantiate class.
				Object classObj = ct.newInstance();

				// Set properties and add it to correct structure
				if (classObj instanceof AbstractEventConsumer)
				{
					logger.debug("Added " + classObj.getClass().getSimpleName() + " to eventConsumer list");
					eventConsumers.add((AbstractEventConsumer<?>) classObj);
				}
				else if (classObj instanceof AbstractConsumer)
				{
					logger.debug("Added " + classObj.getClass().getSimpleName() + " to crudConsumer list");
					crudConsumers.add((AbstractConsumer) classObj);
				}
				else
				{
					logger.error("Consumer class " + className + " doesn't extend AbstractConsumer or AbstractEventConsumer. Cannot initialse the Consumer.");
				}
			}
			catch (Exception ex)
			{
				logger.error("Cannot create Consumer Class " + basePackageName + className + ": "+ ex.getMessage(), ex);
			}
		}
	}

	private String makePackageName(String packageName)
	{
		return (StringUtils.isEmpty(packageName)) ? "" : packageName.trim() + ".";
	}
	
	private int getNumMsgReaderThreads()
	{
		return getConsumerEnvironment().getNumMsgQueueReaders();
	}

	private String getRemoteQueueName(QueueListenerInfo queueListenerInfo)
	{
		return queueListenerInfo.getQueue().getName()+" ("+queueListenerInfo.getQueue().getQueueID()+")";
	}
}
