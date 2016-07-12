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

package sif3.common.interfaces;

import javax.ws.rs.core.MediaType;

import sif3.common.exception.SIF3Exception;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.persist.model.SIF3Job;
import sif3.common.persist.model.SIF3Phase;

/**
 * Interface for all actions that a phase may be allowed to execute.
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public interface PhaseActions
{
    /**
     * Create operation on this phase.
     * 
     * @param job
     *            The job on which this operation is occurring.
     * @param phase
     *            The phase on which this operation is occurring.
     * @param payload
     *            The payload to operate on.
     * @param requestMediaType
     *            The media type the consumer said the payload should be understood as.
     * @param responseMediaType
     *            The media type the consumer expects data in.
     * @param zone
     *            The zone the operation has happened on.
     * @param context
     *            The context the operation has happened in.
     * @return The result of the operation, marshaled to a string as necessary.
     * @throws SIF3Exception
     *             Indicates that an error has occurred in this phase. The calling provider will
     *             convey an appropriate message back to the consumer using an error code
     *             appropriate for the exception thrown.
     * @throws RuntimeException
     *             Runtime exceptions (e.g. IllegalArgumentException) can also be used to report
     *             errors, but are conveyed to the consumer in a generic Internal Server Error (500)
     *             response.
     */
    String create(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context) throws SIF3Exception;

    /**
     * Retrieve operation on this phase.
     * 
     * @param job
     *            The job on which this operation is occurring.
     * @param phase
     *            The phase on which this operation is occurring.
     * @param payload
     *            The payload to operate on.
     * @param requestMediaType
     *            The media type the consumer said the payload should be understood as.
     * @param responseMediaType
     *            The media type the consumer expects data in.
     * @param zone
     *            The zone the operation has happened on.
     * @param context
     *            The context the operation has happened in.
     * @return The result of the operation, marshaled to a string as necessary.
     * @throws SIF3Exception
     *             Indicates that an error has occurred in this phase. The calling provider will
     *             convey an appropriate message back to the consumer using an error code
     *             appropriate for the exception thrown.
     * @throws RuntimeException
     *             Runtime exceptions (e.g. IllegalArgumentException) can also be used to report
     *             errors, but are conveyed to the consumer in a generic Internal Server Error (500)
     *             response.
     */
    String retrieve(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context) throws SIF3Exception;

    /**
     * Update operation on this phase.
     * 
     * @param job
     *            The job on which this operation is occurring.
     * @param phase
     *            The phase on which this operation is occurring.
     * @param payload
     *            The payload to operate on.
     * @param requestMediaType
     *            The media type the consumer said the payload should be understood as.
     * @param responseMediaType
     *            The media type the consumer expects data in.
     * @param zone
     *            The zone the operation has happened on.
     * @param context
     *            The context the operation has happened in.
     * @return The result of the operation, marshaled to a string as necessary.
     * @throws SIF3Exception
     *             Indicates that an error has occurred in this phase. The calling provider will
     *             convey an appropriate message back to the consumer using an error code
     *             appropriate for the exception thrown.
     * @throws RuntimeException
     *             Runtime exceptions (e.g. IllegalArgumentException) can also be used to report
     *             errors, but are conveyed to the consumer in a generic Internal Server Error (500)
     *             response.
     */
    String update(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws SIF3Exception;

    /**
     * Delete operation on this phase.
     * 
     * @param job
     *            The job on which this operation is occurring.
     * @param phase
     *            The phase on which this operation is occurring.
     * @param payload
     *            The payload to operate on.
     * @param requestMediaType
     *            The media type the consumer said the payload should be understood as.
     * @param responseMediaType
     *            The media type the consumer expects data in.
     * @param zone
     *            The zone the operation has happened on.
     * @param context
     *            The context the operation has happened in.
     * @return The result of the operation, marshaled to a string as necessary.
     * @throws SIF3Exception
     *             Indicates that an error has occurred in this phase. The calling provider will
     *             convey an appropriate message back to the consumer using an error code
     *             appropriate for the exception thrown.
     * @throws RuntimeException
     *             Runtime exceptions (e.g. IllegalArgumentException) can also be used to report
     *             errors, but are conveyed to the consumer in a generic Internal Server Error (500)
     *             response.
     */
    String delete(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws SIF3Exception;
}
