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

    public SIF3Exception(String msg)
    {
        super(msg);
    }

    public SIF3Exception(String msg, Throwable ex)
    {
        super(msg, ex);
    }

    public SIF3Exception(Throwable ex)
    {
        super(ex);
    }

    /**
     * Returns a Status object that should be used when reporting this exception. Should be
     * overridden.
     * 
     * @return The Status object appropriate for this exception.
     */
    public int getStatus()
    {
        return Status.INTERNAL_SERVER_ERROR.getStatusCode();
    }
}
