/*
 * EnvironmentKey.java
 * Created: 12/03/2014
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
package sif3.common.model;

import java.io.Serializable;

import au.com.systemic.framework.utils.StringUtils;

/**
 * This class is a basic POJO to encapsulate the components of an environment key. In SIF3 an environment is uniquely identified by a maximum 
 * of 4 components. Not all 4 components are required, though. SolutionID and applicationKey are the mandatory elements of an environment key as 
 * per SIF3 specification, but the userToken and instanceId can be provided to further identify an environment for certain cases. Please refer 
 * to the SIF3 Specification on details about environments. 
 * 
 * @author Joerg Huber
 *
 */
public class EnvironmentKey implements Serializable
{
  private static final long serialVersionUID = 2103107572019986234L;
  
  private String solutionID;
  private String applicationKey;
  private String userToken;
  private String instanceID;

  public EnvironmentKey()
  {
    this(null, null, null, null);
  }
  
  public EnvironmentKey(EnvironmentKey environmentKey)
  {
	  super();
	  if (environmentKey != null)
	  {
		  setSolutionID(environmentKey.getSolutionID());
		  setApplicationKey(environmentKey.getApplicationKey());
		  setUserToken(environmentKey.getUserToken());
		  setInstanceID(environmentKey.getInstanceID());
	  }
  }
  
  public EnvironmentKey(String solutionID, String applicationKey)
  {
    this(solutionID, applicationKey, null, null);
  }
  
  public EnvironmentKey(String solutionID, String applicationKey, String userToken)
  {
    this(solutionID, applicationKey, userToken, null);
  }

  public EnvironmentKey(String solutionID, String applicationKey, String userToken, String instanceID)
  {
    super();
    setSolutionID(solutionID);
    setApplicationKey(applicationKey);
    setUserToken(userToken);
    setInstanceID(instanceID);  
  }

  public void setSolutionID(String solutionID)
  {
    this.solutionID = solutionID;
  }

  public void setApplicationKey(String applicationKey)
  {
    this.applicationKey = applicationKey;
  }

  public void setUserToken(String userToken)
  {
    this.userToken = (StringUtils.isEmpty(userToken)) ? null : userToken;
  }

  public void setInstanceID(String instanceID)
  {
    this.instanceID = (StringUtils.isEmpty(instanceID)) ? null : instanceID;
  }
  
  public String getSolutionID()
  {
    return solutionID;
  }
  
  public String getApplicationKey()
  {
    return applicationKey;
  }
  
  public String getUserToken()
  {
    return userToken;
  }
  
  public String getInstanceID()
  {
    return instanceID;
  }
  
  public boolean equals(EnvironmentKey envKey)
  {
    if (envKey == null)
    {
      return false;
    }
    if (!equals(getSolutionID(), envKey.getSolutionID()))
    {
      return false;
    }
    if (!equals(getApplicationKey(), envKey.getApplicationKey()))
    {
      return false;
    }
    if (!equals(getUserToken(), envKey.getUserToken()))
    {
      return false;
    }
    if (!equals(getInstanceID(), envKey.getInstanceID()))
    {
      return false;
    }
    
    // All tests passed = > return true.
    return true;
  }
  
  /**
   * Environments to not really have a name but somehow we could create one based on the environment information. This
   * should mainly be used for display, debug etc purposes but nothing else.
   * 
   * @return A nice name for the environment.
   */
  public String getEnvironmentName()
  {
    return this.toString();
  }
  
  @Override
  public String toString()
  {
    return "EnvironmentKey [applicationKey=" + applicationKey
        + ", instanceID=" + instanceID
        + ", solutionID=" + solutionID + ", userToken=" + userToken + "]";
  }

  /*---------------------*/
  /*-- Private methods --*/
  /*---------------------*/
//  private void setEnvironmentName()
//  {
//    StringBuffer envName = new StringBuffer();
//    boolean empty = true;
//    if (getSolutionID() != null)
//    {
//      envName.append((empty) ? getSolutionID() : "/" + getSolutionID());
//      empty = false;
//    }
//    if (getApplicationKey() != null)
//    {
//      envName.append((empty) ? getApplicationKey() : "/" + getApplicationKey());
//      empty = false;
//    }
//    if (getUserToken() != null)
//    {
//      envName.append((empty) ? getUserToken() : "/" + getUserToken());
//      empty = false;
//    }
//    if (getInstanceID() != null)
//    {
//      envName.append((empty) ? getInstanceID() : "/" + getInstanceID());
//      empty = false;
//    }
//    environmentName = envName.toString();
//  }
  
  private boolean equals(String left, String right)
  {
    if ((left==null) && (right==null))
    {
      return true;
    }
    if ((left!=null) && (right==null))
    {
      return false;
    }
    if ((left==null) && (right!=null))
    {
      return false;
    }
    return left.equals(right);
  }
}
