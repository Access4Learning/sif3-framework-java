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

import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
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
     * @throws IllegalArgumentException
     *             Implementations should throw this exception if there is a problem with the
     *             payload beyond an unexpected media type issue.
     * @throws PersistenceException
     *             Implementations should throw this exception if there is a problem with any
     *             persistence operation that may be performed to the payload.
     * @throws UnsupportedMediaTypeException
     *             Implementations should throw this exception if there either the request or
     *             response media type are unsupported.
     * @throws UnsupportedQueryException
     *             Implementations should throw this exception if this operation is not supported on
     *             this phase.
     */
    String create(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnmarshalException,
            UnsupportedMediaTypeException, UnsupportedQueryException;

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
     * @throws IllegalArgumentException
     *             Implementations should throw this exception if there is a problem with the
     *             payload beyond an unexpected media type issue.
     * @throws PersistenceException
     *             Implementations should throw this exception if there is a problem with any
     *             persistence operation that may be performed to the payload.
     * @throws UnsupportedMediaTypeException
     *             Implementations should throw this exception if there either the request or
     *             response media type are unsupported.
     * @throws UnsupportedQueryException
     *             Implementations should throw this exception if this operation is not supported on
     *             this phase.
     */
    String retrieve(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnsupportedMediaTypeException,
            UnsupportedQueryException;

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
     * @throws IllegalArgumentException
     *             Implementations should throw this exception if there is a problem with the
     *             payload beyond an unexpected media type issue.
     * @throws PersistenceException
     *             Implementations should throw this exception if there is a problem with any
     *             persistence operation that may be performed to the payload.
     * @throws UnsupportedMediaTypeException
     *             Implementations should throw this exception if there either the request or
     *             response media type are unsupported.
     * @throws UnsupportedQueryException
     *             Implementations should throw this exception if this operation is not supported on
     *             this phase.
     */
    String update(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnmarshalException,
            UnsupportedMediaTypeException, UnsupportedQueryException;

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
     * @throws IllegalArgumentException
     *             Implementations should throw this exception if there is a problem with the
     *             payload beyond an unexpected media type issue.
     * @throws PersistenceException
     *             Implementations should throw this exception if there is a problem with any
     *             persistence operation that may be performed to the payload.
     * @throws UnsupportedMediaTypeException
     *             Implementations should throw this exception if there either the request or
     *             response media type are unsupported.
     * @throws UnsupportedQueryException
     *             Implementations should throw this exception if this operation is not supported on
     *             this phase.
     */
    String delete(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnmarshalException,
            UnsupportedMediaTypeException, UnsupportedQueryException;
}
