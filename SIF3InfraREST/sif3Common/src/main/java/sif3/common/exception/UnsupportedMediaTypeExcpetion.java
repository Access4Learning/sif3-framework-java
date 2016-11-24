/*
 * UnsupportedQueryException.java
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

public class UnsupportedMediaTypeExcpetion extends Exception
{
  private static final long serialVersionUID = 749070962345457L;

  public UnsupportedMediaTypeExcpetion()
  {
      super();
  }

  public UnsupportedMediaTypeExcpetion(String msg)
  {
      super(msg);
  }

  public UnsupportedMediaTypeExcpetion(String msg, Throwable ex)
  {
      super(msg, ex);
  }

  public UnsupportedMediaTypeExcpetion(Throwable ex)
  {
      super(ex);
  }
}
