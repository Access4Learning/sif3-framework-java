/*
 * BrokeredProviderEnvironmentConnector.java
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
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.interfaces.EnvironmentConnector;

/**
 * This class is a concrete implementation of the EnvironmentConnector interface. It is being used by a provider that is deployed in a
 * brokered environment. In this case its low level behaviour is almost identical to a consumer.
 * 
 * @author Joerg Huber
 *
 */
public class BrokeredProviderEnvironmentConnector extends EnvironmentClientConnector implements EnvironmentConnector
{
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public BrokeredProviderEnvironmentConnector(BrokeredProviderEnvironmentManager environmentManager)
  {
    super(environmentManager);
  }

  /*
   * This method connects this provider to a given environment. The environment is looked up from the Environment Store for the connection 
   * details. It also checks if the environment exists meaning there is an session in the workstore. If the environment exists in the 
   * workstore then an attempt to connect is made. If that succeeds then TRUE is returned. If no session exists in the workstore 
   * then it is assumed that such an environment does not exists on the server and an attempt to create and connect to it is made. 
   * The details for the creation of the environment are taken from the XML files in the consumer's template directory 
   * environment/consumer/template.
   * 
   * @return TRUE: Successfully connected. FALSE: Could not connect. Error is logged.
   * 
   * (non-Javadoc)
   * @see sif3.infra.common.env.EnvironmentConnector#connect()
   */
  @Override
  public boolean connect()
  {
    ProviderEnvironment envInfo = getEnvironment();
    envInfo.setConnected(connect(envInfo));
    return envInfo.getIsConnected();
  }

  /*
   * (non-Javadoc)
   * 
   * @see sif3.infra.common.env.EnvironmentConnector#disconnect(boolean)
   */
  @Override
  public boolean disconnect(boolean removeEnvironment)
  {
    return disconnect(removeEnvironment, getEnvironment());
  }

  /*---------------------*/
  /*-- Private methods --*/
  /*---------------------*/
  /**
   * Returns the consumer environment info for this consumer.
   */
  private ProviderEnvironment getEnvironment()
  {
    return (ProviderEnvironment)getEnvironmentManager().getEnvironmentInfo();
  }
}