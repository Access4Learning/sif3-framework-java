/*
 * PersistenceException.java
 * Created: 23/09/2013
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

package sif3.common.exception;

public class PersistenceException extends Exception
{
  private static final long serialVersionUID = 1294192906545296557L;

  public PersistenceException()
  {
      super();
  }

  public PersistenceException(String msg)
  {
      super(msg);
  }

  public PersistenceException(String msg, Throwable ex)
  {
      super(msg, ex);
  }

  public PersistenceException(Throwable ex)
  {
      super(ex);
  }

}
