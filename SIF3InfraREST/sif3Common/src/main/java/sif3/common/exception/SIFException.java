/*
 * SIFException.java
 * Created: 11 Jan 2018
 *
 * Copyright 2018 Systemic Pty Ltd
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

import sif3.common.ws.ErrorDetails;

/**
 * This exception is a generic SIF Exception that can be used to raise within the Framework. It is intended to be use by
 * adapters where an exception needs to be translated in an SIF Error Message (XML or JSON). It encapsulates the information
 * that can be used in a SIF Error Message. As HTTP Status is always required when throwing this exception to ensure that
 * a proper SIF Error Message can be produced.
 * 
 * NOT YET USED IN FRAMEWORK. FUTURE EXTENTION.
 * 
 * @author Joerg Huber
 *
 */
public class SIFException extends Exception
{
    private static final long serialVersionUID = -9199894564131208357L;
    
    private ErrorDetails error = null;
    
    /*
     * The equivalent SIF Error will have the code & message set. Message is limited to 1024 Characters
     */
    public SIFException(int httpErrorStatus, String message)
    {
        this(httpErrorStatus, message, null, null, null);
    }
    
    /*
     * The equivalent SIF Error will have the code & message set. Message is limited to 1024 Characters
     */
    public SIFException(int httpErrorStatus, String message, Throwable cause)
    {
        this(httpErrorStatus, message, null, null, cause);
    }

    
    /*
     * The equivalent SIF Error will have the code, message & description set. Message is limited to 1024 Characters
     */
    public SIFException(int httpErrorStatus, String message, String description)
    {
        this(httpErrorStatus, message, description, null, null);
    }
    
    /*
     * The equivalent SIF Error will have the code, message & description set. Message is limited to 1024 Characters
     */
    public SIFException(int httpErrorStatus, String message, String description, Throwable cause)
    {
        this(httpErrorStatus, message, description, null, cause);
    }

    /*
     * The equivalent SIF Error will have the code, message, description & scope set. Message is limited to 1024 Characters
     */
    public SIFException(int httpErrorStatus, String message, String description, String scope)
    {
        this (httpErrorStatus, message, description, scope, null);
    }
    
    /*
     * The equivalent SIF Error will have the code, message, description & scope set. Message is limited to 1024 Characters
     */
    public SIFException(int httpErrorStatus, String message, String description, String scope, Throwable cause)
    {
        this (new ErrorDetails(httpErrorStatus, message, description, scope), cause);
    }

    /*
     * The equivalent SIF Error will have the code, message, description & scope set. Message is limited to 1024 Characters. The
     * values will be extracted form the error parameter. If any value is set to null then it is not passed to the SIF Error
     * message. The error.code should be set otherwise it assumes HTTP Status 400.
     */
    public SIFException(ErrorDetails error)
    {
        this(error, null);
    }
    
    public SIFException(ErrorDetails error, Throwable cause)
    {
        super(error.getMessage(), cause);
        if (error.getErrorCode() < 100)
        {
            error.setErrorCode(400);
        }
        
        this.error = error;
    }
    
    public ErrorDetails getErrorDetails()
    {
        return error;
    }
    
    @Override
    public String getMessage()
    {
        return getErrorDetails().getMessage();
    }
}
