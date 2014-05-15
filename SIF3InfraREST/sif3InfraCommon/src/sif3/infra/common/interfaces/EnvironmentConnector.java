/*
 * EnvironmentConnector.java
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

package sif3.infra.common.interfaces;

/**
 * This interface defines the methods that are required for a service to establish or terminate a connection with an environment provider 
 * (DIRECT or BROKERED). 
 * 
 * @author Joerg Huber
 */
public interface EnvironmentConnector
{
  /**
   * This method establishes a connection with the environment provider. If the connection succeeds then true must be returned. If it fails
   * false must be returned. Ideally the implementation if this method will also log the errors if the connection failed. Once a connection
   * is successfully established the service initiating the connection should be able to start interacting with the environment provider.
   * 
   * NOTE: The implementation might be quite different for consumers, providers and if they connect to a BROKER or a DIRECT environment. 
   * 
   * @return See Desc
   */
  public boolean connect();
  
  /**
   * This method will terminate a connection with an environment provider. If no connection was established through the connect()
   * method previously then this method should not take any actions. After this operation no further call can be made to a environment
   * provider.<br/><br/> 
   * 
   * If the parameter remomeEnvironment is set to TRUE then this method will only disconnect but the environment will still exist in the 
   * Environment Store and the environment provider. After a call to this method a connect() is required first to resume operations. 
   * The connect() will connect to the existing environment and won't create a new one. This is the expected standard behaviour for any 
   * service.<br/><br/>
   * 
   * If the parameter remomeEnvironment is set to FALSE then this method disconnect BUT on top of this it will also remove the environment 
   * from the Environment Store and the environment provider. If there are queues, events etc still available on the environment they will 
   * be lost for ever! remomeEnvironment=TRUE must be used with care and really should only be used that way in a 'minimal/direct/immediate'
   * environment where no queues and events or any delayed responses with callbacks are used. After a call to this method the connect() 
   * method will create a new environment again.
   * 
   * @param remomeEnvironment TRUE: Disconnect and remove/delete environment in local environment store and on environment provider.<br/><br/> 
   *                          FALSE: Only disconnect but leave environment in local environment store and on environment provider.
   * 
   * @return TRUE: Disconnect was successful (or service was not connected at all). FALSE: Disconnect failed. Error is logged.
   */
  public boolean disconnect(boolean remomeEnvironment);

}
