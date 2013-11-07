/*
 * ProviderEnvironment.java
 * Created: 15/09/2013
 *
 * Copyright 2013 Systemic Pty Ltd
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

package sif3.infra.common.env.types;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class represents a provider environment. This is a more complex class than a consumer environment as it has to validate a
 * number of values when a consumer attempts to connect.
 * 
 * @author Joerg Huber
 *
 */
public class ProviderEnvironment extends EnvironmentInfo
{
    private static final long serialVersionUID = 6469968451023182574L;
    
    private boolean allowAny = false;
    private String allowAnyUserName = null;
    private String allowAnyPassword = null;
    
    /* key: <dmName>|<dmVersion>: Example: SIF-AU|1.3 */
    private HashSet<String> supportedDM = new HashSet<String>();
    
    /* List of known comsumers: Key=ConsumerID, Data=ConsumerEnvironment */
    private HashMap<String, ConsumerEnvironment> consumers = new HashMap<String, ConsumerEnvironment>();


    /**
     * Constructor
     * 
     * @param environmentName The environment name
     * @param serviceName The name of the service/adapter.
     */
  public ProviderEnvironment(String environmentName, String serviceName)
  {
    super(environmentName, serviceName);
  }
  
  /**
   * TRUE: Anyone with the correct 'Any'-username and 'Any'-password can connect to this environment. FALSE: onlu consumers with a given
   * name, user and password can connect to this environment. See developer's guide for more details.
   * 
   * @return See desc.
   */
  public boolean getAllowAny()
  {
    return allowAny;
  }

  public void setAllowAny(boolean allowAny)
  {
    this.allowAny = allowAny;
  }

  /**
   * The username if 'Any' is allowed for this environment.
   * 
   * @return See desc.
   */
  public String getAllowAnyUserName()
  {
    return allowAnyUserName;
  }

  public void setAllowAnyUserName(String allowAnyUserName)
  {
    this.allowAnyUserName = allowAnyUserName;
  }

  /**
   * The password if 'Any' is allowed for this environment.
   * 
   * @return See desc.
   */
  public String getAllowAnyPassword()
  {
    return allowAnyPassword;
  }

  public void setAllowAnyPassword(String allowAnyPassword)
  {
    this.allowAnyPassword = allowAnyPassword;
  }

  public HashSet<String> getSupportedDM()
  {
    return supportedDM;
  }

  public void setSupportedDM(HashSet<String> supportedDM)
  {
    this.supportedDM = supportedDM;
  }

  public void addConsumer(String consumerID, ConsumerEnvironment consumer)
  {
    consumers.put(consumerID, consumer);
  }
  
  public ConsumerEnvironment getConsumer(String consumerName)
  {
    return consumers.get(consumerName);
  }
  
  
  public HashMap<String, ConsumerEnvironment> getConsumers()
  {
    return consumers;
  }

  public void setConsumers(HashMap<String, ConsumerEnvironment> consumers)
  {
    this.consumers = consumers;
  }

  @Override
  public String toString()
  {
    return "ProviderEnvironment [allowAny=" + allowAny + ", allowAnyPassword="
        + allowAnyPassword + ", allowAnyUserName=" + allowAnyUserName
        + ", consumers=" + consumers + ", supportedDM=" + supportedDM
        + ", toString()=" + super.toString() + "]";
  }  

  
}
