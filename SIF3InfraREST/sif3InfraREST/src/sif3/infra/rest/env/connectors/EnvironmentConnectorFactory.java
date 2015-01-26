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

import org.apache.log4j.Logger;

import sif3.infra.common.env.mgr.BrokeredProviderEnvironmentManager;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.mgr.HITSDirectProviderEnvironmentManager;
import sif3.infra.common.interfaces.EnvironmentConnector;
import sif3.infra.common.interfaces.EnvironmentManager;

/**
 * @author Joerg Huber
 *
 */
public class EnvironmentConnectorFactory
{
	protected static final Logger logger = Logger.getLogger(EnvironmentConnectorFactory.class);

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
						// HITS Customisation
						logger.debug("HITS Direct Provider Environment Connector will be used.");
						return new DirectProviderEnvironmentConnector((HITSDirectProviderEnvironmentManager) envMgr);
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
