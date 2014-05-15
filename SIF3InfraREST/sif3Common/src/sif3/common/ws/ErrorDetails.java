/*
 * ErrorDetails.java
 * Created: 09/09/2013
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

/**
 * This is a helper class to abstract the underlying infrastructure POJO representation of the ErrorType. This is done on purpose
 * to avoid exposing the JAXB POJO stuff to higher levels of the application. There are only very few objects that need to be used
 * in higher levels and these are abstracted. The error POJO is one of those.
 */
package sif3.common.ws;

import java.io.Serializable;

/**
 * This class is an abstract representation of an error and it is aligned with the properties of a SIF3 error message. This class
 * allows to fully abstract the low level infrastructure details for SIF3 and instead present an error in a nice POJO to the
 * higher levels of the framework.
 * 
 * @author Joerg Huber
 *
 */
public class ErrorDetails implements Serializable
{
  private static final long serialVersionUID = 9165700493001563052L;

  private int errorCode = -1;
  private String scope = null;
  private String message = null;
  private String description = null;
  
  public ErrorDetails() 
  {
	  this(-1, null, null, null);
  }
  
  /**
   * Constructor
   * 
   * @param errorCode Generally the HTTP error code.
   * @param message Generally the HTTP status message.
   */
  public ErrorDetails(int errorCode, String message)
  {
	  this(errorCode, message, null, null);
  }
  
  /**
   * Constructor
   * 
   * @param errorCode Generally the HTTP error code.
   * @param message Generally the HTTP status message.
   * @param description Additional description that puts the HTTP error code and message into a context of an operation.
   */
  public ErrorDetails(int errorCode, String message, String description)
  {
	  this(errorCode, message, description, null);
  }
  
  /**
  * Constructor
  * 
  * @param errorCode Generally the HTTP error code.
  * @param message Generally the HTTP status message.
  * @param description Additional description that puts the HTTP error code and message into a context of an operation.
  * @param scope Optional scope for the error and its context.
  */
  public ErrorDetails(int errorCode, String message, String description, String scope)
  {
	  this.errorCode = errorCode;
	  this.message = message;
	  this.description = description;
	  this.scope = scope;
  }
  
  public int getErrorCode()
  {
    return errorCode;
  }
  public void setErrorCode(int errorCode)
  {
    this.errorCode = errorCode;
  }
  public String getScope()
  {
    return scope;
  }
  public void setScope(String scope)
  {
    this.scope = scope;
  }
  public String getMessage()
  {
    return message;
  }
  public void setMessage(String message)
  {
    this.message = message;
  }
  public String getDescription()
  {
    return description;
  }
  public void setDescription(String description)
  {
    this.description = description;
  }
  @Override
  public String toString()
  {
    return "ErrorDetails [description=" + description + ", errorCode=" + errorCode + ", message=" + message + ", scope=" + scope + "]";
  }  
}
