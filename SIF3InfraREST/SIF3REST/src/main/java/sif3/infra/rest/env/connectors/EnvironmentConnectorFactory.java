/*
 * EnvironmentConnectorFactory.java
 * Created: 10/03/2014
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

package sif3.infra.rest.env.connectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.infra.common.env.mgr.BrokeredProviderEnvironmentManager;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.mgr.DirectProviderEnvironmentManager;
import sif3.infra.common.interfaces.EnvironmentConnector;
import sif3.infra.common.interfaces.EnvironmentManager;

/**
 * Many classes within the framework use generic code and therefore use interfaces as their implementation. At runtime concrete implementation
 * are required based on the service type (consumer, provider) and context (DIRECT, BROKRED) they operate in. To get access to concrete runtime
 * implementations these classes use this factory to get the appropriate environment connector implementation.
 * 
 * @author Joerg Huber
 *
 */
public class EnvironmentConnectorFactory
{
	protected final static Logger logger = LoggerFactory.getLogger(EnvironmentConnectorFactory.class);

	public static EnvironmentConnector getEnvironmentConnector(EnvironmentManager envMgr)
	{
		switch (envMgr.getEnvironmentInfo().getAdapterType())
		{
			case CONSUMER:
				logger.debug("Consumer Environment Connector will be used.");
				return new ConsumerEnvironmentConnector((ConsumerEnvironmentManager) envMgr);
			case PROVIDER:
				switch (envMgr.getEnvironmentInfo().getEnvironmentType())
				{
					case DIRECT:
						logger.debug("Direct Provider Environment Connector will be used.");
						return new DirectProviderEnvironmentConnector((DirectProviderEnvironmentManager) envMgr);
					case BROKERED:
						logger.debug("Brokered Provider Environment Connector will be used.");
						return new BrokeredProviderEnvironmentConnector((BrokeredProviderEnvironmentManager) envMgr);
					default: // cannot really happen
						return null;
				}
			default:
				logger.debug("Invalid service/addapter type! No Environment connnector will be returnd.");
				return null;
		}
	}
}
