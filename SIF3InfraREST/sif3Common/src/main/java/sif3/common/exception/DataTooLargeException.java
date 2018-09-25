/*
 * DataTooLargeException.java Created: 23/04/2015
 * 
 * Copyright 2015-2018 Systemic Pty Ltd
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
import sif3.common.ws.ErrorDetails;

/**
 * Exception that can be used by providers if a request is received that would result into response payload that is too large.
 * Generally this is the case for unbound queries or where the 'page size' of a query is too large. It defaults the HTTP Status to 413
 * (Response too large) which indicates that the request cannot be served.
 * 
 * @author Joerg Huber
 *
 */
public class DataTooLargeException extends SIFException
{
	private static final long serialVersionUID = 8201196298932433571L;

    /*
     * The equivalent SIF Error will have the code & message set. Message is limited to 1024 Characters
     */
    public DataTooLargeException(String message)
    {
        this(message, null, null, null);
    }
    
    /*
     * The equivalent SIF Error will have the code & message set. Message is limited to 1024 Characters
     */
    public DataTooLargeException(String message, Throwable cause)
    {
        this(message, null, null, cause);
    }

    /*
     * The equivalent SIF Error will have the code, message & description set. Message is limited to 1024 Characters
     */
    public DataTooLargeException(String message, String description)
    {
        this(message, description, null, null);
    }
    
    /*
     * The equivalent SIF Error will have the code, message & description set. Message is limited to 1024 Characters
     */
    public DataTooLargeException(String message, String description, Throwable cause)
    {
        this(message, description, null, cause);
    }

    /*
     * The equivalent SIF Error will have the code, message, description & scope set. Message is limited to 1024 Characters
     */
    public DataTooLargeException(String message, String description, String scope)
    {
        this (message, description, scope, null);
    }
    
    /*
     * The equivalent SIF Error will have the code, message, description & scope set. Message is limited to 1024 Characters
     */
    public DataTooLargeException(String message, String description, String scope, Throwable cause)
    {
        super(new ErrorDetails(CommonConstants.RESPONSE_TOO_LARGE, message, description, scope), cause);
    }

//	public DataTooLargeException()
//	{
//		super();
//	}
//
//	public DataTooLargeException(String msg)
//	{
//		super(msg);
//	}
//
//	public DataTooLargeException(String msg, Throwable ex)
//	{
//		super(msg, ex);
//	}
//
//	public DataTooLargeException(Throwable ex)
//	{
//		super(ex);
//	}
}
