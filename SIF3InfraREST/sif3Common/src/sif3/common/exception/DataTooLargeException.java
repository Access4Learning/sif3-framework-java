/*
 * DataTooLargeException.java Created: 23/04/2015
 * 
 * Copyright 2015 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package sif3.common.exception;

import sif3.common.CommonConstants;

public class DataTooLargeException extends SIF3Exception
{
    private static final long serialVersionUID = 8201196298932433571L;

    public DataTooLargeException()
    {
        super();
    }

    public DataTooLargeException(String message)
    {
        super(message);
    }

    public DataTooLargeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DataTooLargeException(Throwable cause)
    {
        super(cause);
    }

    @Override
    public int getStatus()
    {
        return CommonConstants.RESPONSE_TOO_LARGE;
    }
    
    @Override
    public String getHeadline()
    {
        return "Data too large.";
    }
}
