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
 * Exception class for generic bad requests
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public class BadRequestException extends SIF3Exception
{
    private static final long serialVersionUID = -1486796509065132216L;

    public BadRequestException()
    {
        super();
    }

    public BadRequestException(String msg)
    {
        super(msg);
    }

    public BadRequestException(String msg, Throwable ex)
    {
        super(msg, ex);
    }

    public BadRequestException(Throwable ex)
    {
        super(ex);
    }

    @Override
    public int getStatus()
    {
        return Status.BAD_REQUEST.getStatusCode();
    }
}
