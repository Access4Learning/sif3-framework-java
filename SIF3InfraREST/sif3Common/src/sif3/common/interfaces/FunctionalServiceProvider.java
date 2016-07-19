package sif3.common.interfaces;

import javax.ws.rs.core.MediaType;

import sif3.common.exception.SIF3Exception;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.persist.model.SIF3Job;

public interface FunctionalServiceProvider extends Provider
{

    /**
     * Determines if this provider should accept operations on the given job instance
     * 
     * @param job
     *            The job to check.
     * @return True if the job is one this provider supports, false otherwise.
     */
    boolean acceptJob(SIF3Job job);

    /**
     * Determines if this provider should accept operations on the given job name.
     * 
     * @param jobName
     *            The job name to check.
     * @return True if the job name is one this provider supports, false otherwise.
     */
    boolean acceptJob(String jobName);

    /*----------------------*/
    /*-- Phase Operations --*/
    /*----------------------*/
    /**
     * Responds to create operations on the named phase on the given job.
     * 
     * @param resourceID
     *            The refid of the job (functional service instance) to perform the operation on.
     * @param phaseName
     *            The name of the phase being targeted.
     * @param payload
     *            A payload, possibly an object marshaled to its string representation.
     * @param requestMediaType
     *            The media type of the payload.
     * @param responseMediaType
     *            The media type the consumer expects data back in.
     * @param zone
     *            The zone for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT zone.
     * @param context
     *            The context for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT context.
     * @param metadata
     *            Metadata of the request
     * @param customResponseParams
     *            Any custom header properties set by the consumer.
     * 
     * @return a string to be returned to the consumer.
     */
    String createToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception;

    /**
     * Responds to retrieve operations on the named phase on the given job.
     * 
     * @param resourceID
     *            The refid of the job (functional service instance) to perform the operation on.
     * @param phaseName
     *            The name of the phase being targeted.
     * @param payload
     *            A payload, possibly an object marshaled to its string representation.
     * @param requestMediaType
     *            The media type of the payload.
     * @param responseMediaType
     *            The media type the consumer expects data back in.
     * @param zone
     *            The zone for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT zone.
     * @param context
     *            The context for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT context.
     * @param metadata
     *            Metadata of the request
     * @param customResponseParams
     *            Any custom header properties set by the consumer.
     * 
     * @return a string to be returned to the consumer.
     */
    String retrieveToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception;

    /**
     * Responds to update operations on the named phase on the given job.
     * 
     * @param resourceID
     *            The refid of the job (functional service instance) to perform the operation on.
     * @param phaseName
     *            The name of the phase being targeted.
     * @param payload
     *            A payload, possibly an object marshaled to its string representation.
     * @param requestMediaType
     *            The media type of the payload.
     * @param responseMediaType
     *            The media type the consumer expects data back in.
     * @param zone
     *            The zone for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT zone.
     * @param context
     *            The context for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT context.
     * @param metadata
     *            Metadata of the request
     * @param customResponseParams
     *            Any custom header properties set by the consumer.
     * 
     * @return a string to be returned to the consumer.
     */
    String updateToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception;

    /**
     * Responds to delete operations on the named phase on the given job.
     * 
     * @param resourceID
     *            The refid of the job (functional service instance) to perform the operation on.
     * @param phaseName
     *            The name of the phase being targeted.
     * @param payload
     *            A payload, possibly an object marshaled to its string representation.
     * @param requestMediaType
     *            The media type of the payload.
     * @param responseMediaType
     *            The media type the consumer expects data back in.
     * @param zone
     *            The zone for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT zone.
     * @param context
     *            The context for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT context.
     * @param metadata
     *            Metadata of the request
     * @param customResponseParams
     *            Any custom header properties set by the consumer.
     * 
     * @return a string to be returned to the consumer.
     */
    String deleteToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception;

    /*----------------------*/
    /*-- State Operations --*/
    /*----------------------*/
    /**
     * Responds to create operations on the state of the named phase on the given job.
     * 
     * @param resourceID
     *            The refid of the job (functional service instance) to perform the operation on.
     * @param phaseName
     *            The name of the phase being targeted.
     * @param date
     *            The StateType instance to create.
     * @param zone
     *            The zone for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT zone.
     * @param context
     *            The context for which this operation shall be invoked. Can be null which indicates
     *            the DEFAULT context.
     * @param metadata
     *            Metadata of the request
     * @param customResponseParams
     *            Any custom header properties set by the consumer.
     * 
     * @return The current phase state to be returned to the consumer.
     */
    Object createToState(String resourceID, String phaseName, Object date, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws SIF3Exception;
}