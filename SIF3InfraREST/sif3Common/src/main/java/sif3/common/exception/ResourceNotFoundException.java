/*
 * ResourceNotFoundException.java
 * Created: 11 Jun 2018
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

import javax.ws.rs.core.Response.Status;

import sif3.common.ws.ErrorDetails;

/**
 * Exception that can be used by providers if a resource is not found. It defaults the HTTP Status to 404 which indicates
 * that the resource/service is not found.
 * 
 * @author Joerg Huber
 *
 */
public class ResourceNotFoundException extends SIFException
{
    private static final long serialVersionUID = 2642998385326590026L;

    /*
     * The equivalent SIF Error will have the code & message set. Message is limited to 1024 Characters
     */
    public ResourceNotFoundException(String message)
    {
        this(message, null, null, null);
    }
    
    /*
     * The equivalent SIF Error will have the code & message set. Message is limited to 1024 Characters
     */
    public ResourceNotFoundException(String message, Throwable cause)
    {
        this(message, null, null, cause);
    }

    
    /*
     * The equivalent SIF Error will have the code, message & description set. Message is limited to 1024 Characters
     */
    public ResourceNotFoundException(String message, String description)
    {
        this(message, description, null, null);
    }
    
    /*
     * The equivalent SIF Error will have the code, message & description set. Message is limited to 1024 Characters
     */
    public ResourceNotFoundException(String message, String description, Throwable cause)
    {
        this(message, description, null, cause);
    }

    /*
     * The equivalent SIF Error will have the code, message, description & scope set. Message is limited to 1024 Characters
     */
    public ResourceNotFoundException(String message, String description, String scope)
    {
        this (message, description, scope, null);
    }
    
    /*
     * The equivalent SIF Error will have the code, message, description & scope set. Message is limited to 1024 Characters
     */
    public ResourceNotFoundException(String message, String description, String scope, Throwable cause)
    {
        super(new ErrorDetails(Status.NOT_FOUND.getStatusCode(), message, description, scope), cause);
    }
}
