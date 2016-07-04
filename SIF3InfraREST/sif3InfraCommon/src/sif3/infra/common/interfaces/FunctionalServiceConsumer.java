package sif3.infra.common.interfaces;

import java.util.List;

import javax.ws.rs.core.MediaType;

import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.interfaces.Consumer;
import sif3.common.interfaces.EventConsumer;
import sif3.common.model.CustomParameters;
import sif3.common.model.ZoneContextInfo;
import sif3.common.ws.Response;
import sif3.infra.common.model.StateType;

public interface FunctionalServiceConsumer<L> extends Consumer, EventConsumer<L>
{
    /**
     * Will invoke the REST POST method. This expects the payload to have already been marshaled to
     * a string before this is called, and appropriate format (mediaType) information added to
     * 'hdrProperties'. If there are any errors then the ServiceInvokationException is raised and
     * the error is logged.
     * 
     * @param resourceID
     *            The refid of the job (functional service instance) to send the request to.
     * @param phaseName
     *            The name of the phase to send the request to.
     * @param payload
     *            The payload in its marshaled string representation.
     * @param requestMediaType
     *            The media type that the payload has been marshaled to.
     * @param responseMediaType
     *            The media type that the consumer expects the response payload to be in.
     * @param zoneCtxList
     *            A collection of zones and contexts to issue the request to.
     * @param customParameters
     *            Custom header properties to be added to the header of the request.
     * 
     * @return A list of responses (one per zone/context pair) holding appropriate values and
     *         results of the call.
     * 
     * @throws ServiceInvokationException
     *             Any underlying errors occurred such failure to invoke actual web-service etc.
     */
    List<Response> createToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType,
            List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException;

    /**
     * Will invoke the REST GET method (a POST with the header changed to GET). This expects the
     * payload to have already been marshaled to a string before this is called, and appropriate
     * format (mediaType) information added to 'hdrProperties'. If there are any errors then the
     * ServiceInvokationException is raised and the error is logged.
     * 
     * @param resourceID
     *            The refid of the job (functional service instance) to send the request to.
     * @param phaseName
     *            The name of the phase to send the request to.
     * @param payload
     *            The payload in its marshaled string representation.
     * @param requestMediaType
     *            The media type that the payload has been marshaled to.
     * @param responseMediaType
     *            The media type that the consumer expects the response payload to be in.
     * @param zoneCtxList
     *            A collection of zones and contexts to issue the request to.
     * @param customParameters
     *            Custom header properties to be added to the header of the request.
     * 
     * @return A list of responses (one per zone/context pair) holding appropriate values and
     *         results of the call.
     * 
     * @throws ServiceInvokationException
     *             Any underlying errors occurred such failure to invoke actual web-service etc.
     */
    List<Response> retrieveToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType,
            List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException;

    /**
     * Will invoke the REST PUT method. This expects the payload to have already been marshaled to a
     * string before this is called, and appropriate format (mediaType) information added to
     * 'hdrProperties'. If there are any errors then the ServiceInvokationException is raised and
     * the error is logged.
     * 
     * @param resourceID
     *            The refid of the job (functional service instance) to send the request to.
     * @param phaseName
     *            The name of the phase to send the request to.
     * @param payload
     *            The payload in its marshaled string representation.
     * @param requestMediaType
     *            The media type that the payload has been marshaled to.
     * @param responseMediaType
     *            The media type that the consumer expects the response payload to be in.
     * @param zoneCtxList
     *            A collection of zones and contexts to issue the request to.
     * @param customParameters
     *            Custom header properties to be added to the header of the request.
     * 
     * @return A list of responses (one per zone/context pair) holding appropriate values and
     *         results of the call.
     * 
     * @throws ServiceInvokationException
     *             Any underlying errors occurred such failure to invoke actual web-service etc.
     */
    List<Response> updateToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType,
            List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException;

    /**
     * Will invoke the REST DELETE method (a PUT with the header changed to DELETE). This expects
     * the payload to have already been marshaled to a string before this is called, and appropriate
     * format (mediaType) information added to 'hdrProperties'. If there are any errors then the
     * ServiceInvokationException is raised and the error is logged.
     * 
     * @param resourceID
     *            The refid of the job (functional service instance) to send the request to.
     * @param phaseName
     *            The name of the phase to send the request to.
     * @param payload
     *            The payload in its marshaled string representation.
     * @param requestMediaType
     *            The media type that the payload has been marshaled to.
     * @param responseMediaType
     *            The media type that the consumer expects the response payload to be in.
     * @param zoneCtxList
     *            A collection of zones and contexts to issue the request to.
     * @param customParameters
     *            Custom header properties to be added to the header of the request.
     * 
     * @return A list of responses (one per zone/context pair) holding appropriate values and
     *         results of the call.
     * 
     * @throws ServiceInvokationException
     *             Any underlying errors occurred such failure to invoke actual web-service etc.
     */
    List<Response> deleteToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType,
            List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException;

    /**
     * Will invoke the REST POST method. The state will be sent to the phase of the functional
     * service to, for example, indicate that the consumer has finished sending data to the
     * provider. If there are any errors then the ServiceInvokationException is raised and the error
     * is logged.
     * 
     * @param resourceID
     *            The refid of the job (functional service instance) to send the request to.
     * @param phaseName
     *            The name of the phase to send the request to.
     * @param state
     *            The state to send to the functional service provider.
     * @param zoneCtxList
     *            A collection of zones and contexts to issue the request to.
     * @param customParameters
     *            Custom header properties to be added to the header of the request.
     * 
     * @return A list of responses (one per zone/context pair) holding appropriate values and
     *         results of the call.
     * 
     * @throws ServiceInvokationException
     *             Any underlying errors occurred such failure to invoke actual web-service etc.
     */
    List<Response> createToState(String resourceID, String phaseName, StateType state,
            List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException;

}