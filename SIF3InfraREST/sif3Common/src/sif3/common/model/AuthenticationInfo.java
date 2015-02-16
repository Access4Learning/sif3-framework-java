/*
 * AuthenticationInfo.java
 * Created: 07/03/2014
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

/**
 * This is a simple utility class to deal with components that make up the authentication token in SIF3. If the authentication method
 * is 'Basic' or 'SIF_HMACSHA256' then the authentication token has the structure as defined in the SIF specification, meaning it is bas64
 * encoded and can be split on the ':' that separates the applicationKey/sessionToken and the password/HMAC Hash. If the authentication token
 * is 'Bearer' then it is assumed that the token is managed by a security mechanism outside of the SIF Specification and therefore no
 * assumptions about the structure of the token can be made. In this case the 'password' component of this class should never be populated
 * and the 'userToken' component should always hold the full token given by the 'Bearer'.
 * 
 * @author Joerg Huber
 *
 */
public class AuthenticationInfo implements Serializable
{
  private static final long serialVersionUID = -8736784141972585133L;

  /* Defines valid authentication methods for SIF 3.0 */
  public enum AuthenticationMethod {Basic, SIF_HMACSHA256, Bearer};

  private AuthenticationMethod authMethod = AuthenticationMethod.Basic;
  private String userToken = null;
  private String password = null;
  
  public AuthenticationInfo()
  {
    this(AuthenticationMethod.Basic, null, null);
  }
  
  public AuthenticationInfo(AuthenticationMethod authMethod, String userToken, String password)
  {
    super();
    setAuthMethod(authMethod);
    setUserToken(userToken);
    setPassword(password);
  }
  
  public AuthenticationInfo(String authMethod, String userToken, String password)
  {
    super();
    setAuthMethod(authMethod);
    setUserToken(userToken);
    setPassword(password);
  }

  public AuthenticationMethod getAuthMethod()
  {
    return authMethod;
  }
  
  public void setAuthMethod(AuthenticationMethod authMethod)
  {
    this.authMethod = authMethod;
  }
  
  public void setAuthMethod(String authMethod)
  {
    try
    {
      this.authMethod = AuthenticationMethod.valueOf(authMethod);
    }
    catch (Exception ex)
    {
      this.authMethod = AuthenticationMethod.Basic;
    }
  }

  public String getUserToken()
  {
    return userToken;
  }
  
  public void setUserToken(String userToken)
  {
    this.userToken = userToken;
  }
  
  public String getPassword()
  {
    return password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  @Override
  public String toString()
  {
    return "AuthenticationInfo [authMethod=" + authMethod + ", password=" + password + ", userToken=" + userToken + "]";
  }
  
  
}
