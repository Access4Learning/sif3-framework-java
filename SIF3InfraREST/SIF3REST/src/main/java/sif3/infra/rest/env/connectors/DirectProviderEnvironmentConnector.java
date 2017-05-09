/*
 * Direct
 * ProviderEnvironmentConnector.java
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

import sif3.infra.common.env.mgr.DirectProviderEnvironmentManager;
import sif3.infra.common.interfaces.EnvironmentConnector;

/**
 * @author Joerg Huber
 *
 */
public class DirectProviderEnvironmentConnector implements EnvironmentConnector
{
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public DirectProviderEnvironmentConnector(DirectProviderEnvironmentManager environmentManager)
	{
		super();
 	}

	/*
	 * In a direct environment the provider is the environment provider. No action required.
	 * (non-Javadoc)
	 * 
	 * @see sif3.infra.common.env.EnvironmentConnector#connect()
	 */
	@Override
	public boolean connect()
	{
		logger.info("DirectProviderEnvironmentConnector.connect: no action required.");
		return true;
	}

	/*
	 * In a direct environment the provider is the environment provider. No action required.
	 * (non-Javadoc)
	 * 
	 * @see sif3.infra.common.env.EnvironmentConnector#disconnect(boolean)
	 */
	@Override
	public boolean disconnect(boolean remomeEnvironment)
	{
		logger.info("DirectProviderEnvironmentConnector.disconnect: no action required.");
		return true;
	}
}
