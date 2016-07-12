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

/**
 * Exception class for when data could not be found.
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public class NotFoundException extends SIF3Exception
{
    private static final long serialVersionUID = 1409571837341328926L;

    public NotFoundException()
    {
        super();
    }

    public NotFoundException(String message)
    {
        super(message);
    }

    public NotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NotFoundException(Throwable cause)
    {
        super(cause);
    }

    @Override
    public int getStatus()
    {
        return Status.NOT_FOUND.getStatusCode();
    }
    
    @Override
    public String getHeadline()
    {
        return "Could not find the requested resource.";
    }
}
