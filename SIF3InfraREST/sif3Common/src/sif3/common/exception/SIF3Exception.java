/*
 * Crown Copyright © Department for Education (UK) 2016
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
 * The root exception for all SIF3 exceptions.
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public class SIF3Exception extends Exception
{

    private static final long serialVersionUID = 924365325457L;

    public SIF3Exception()
    {
        super();
    }

    public SIF3Exception(String message)
    {
        super(message);
    }

    public SIF3Exception(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SIF3Exception(Throwable cause)
    {
        super(cause);
    }

    /**
     * Returns the HTTP status code that should be used when reporting this exception. Should be
     * overridden.
     * 
     * @return The HTTP status code (int) appropriate for this exception.
     */
    public int getStatus()
    {
        return Status.INTERNAL_SERVER_ERROR.getStatusCode();
    }

    /**
     * Returns the headline of this exception.
     * 
     * @return The Status object appropriate for this exception.
     */
    public String getHeadline()
    {
        return "Error performing action.";
    }

    /**
     * Converts this exception into an ErrorDetails instance ready to be used in a REST Response.
     * The ErrorDetails object's "message" property will be populated with the string returned by
     * getHeadline(), whereas the "description" property will be populated by the exceptions message
     * (getMessage()). The scope is left undefined.
     * 
     * @return ErrorDetails object with default details.
     */
    public ErrorDetails getErrorDetails()
    {
        return new ErrorDetails(getStatus(), getHeadline(), getMessage());
    }

    /**
     * Converts this exception into an ErrorDetails instance ready to be used in a REST Response.
     * The ErrorDetails object's "message" property will be populated with the string returned by
     * getHeadline(), whereas the "description" property will be populated by the exceptions message
     * (getMessage()). The scope, which should indicate the attempted operation, is defined by the
     * single argument.
     * 
     * @param scope
     *            The scope to put into the ErrorDetails object.
     * @return ErrorDetails object with default details.
     */
    public ErrorDetails getErrorDetails(String scope)
    {
        return new ErrorDetails(getStatus(), getHeadline(), getMessage(), scope);
    }
}