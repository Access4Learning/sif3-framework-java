/*
 * UnsupportedQueryException.java Created: 23/09/2013
 *
 * Copyright 2013 Systemic Pty Ltd
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

import javax.ws.rs.core.Response.Status;

public class UnsupportedMediaTypeException extends SIF3Exception
{
    private static final long serialVersionUID = 749070962345457L;

    public UnsupportedMediaTypeException()
    {
        super();
    }

    public UnsupportedMediaTypeException(String message)
    {
        super(message);
    }

    public UnsupportedMediaTypeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public UnsupportedMediaTypeException(Throwable cause)
    {
        super(cause);
    }

    @Override
    public int getStatus()
    {
        return Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode();
    }
    
    @Override
    public String getHeadline()
    {
        return "Media type not supported";
    }
}
