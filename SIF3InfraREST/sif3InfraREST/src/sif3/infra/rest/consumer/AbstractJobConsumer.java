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

package sif3.infra.rest.consumer;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import au.com.systemic.framework.utils.Timer;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.model.CustomParameters;
import sif3.common.model.SIFEvent;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.model.URLQueryParameter;
import sif3.common.model.ZoneContextInfo;
import sif3.common.utils.JAXBUtils;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.StateType;
import sif3.infra.rest.client.FunctionalServiceClient;

public abstract class AbstractJobConsumer extends AbstractEventConsumer<JobCollectionType>
{

    protected final Logger               logger       = Logger.getLogger(getClass());

    private static InfraUnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
    private static InfraMarshalFactory   marshaller   = new InfraMarshalFactory();

    /**
     * Constructor
     */
    public AbstractJobConsumer()
    {
        super();

        JAXBUtils.initCtx(getMultiObjectClassInfo().getObjectType());
        JAXBUtils.initCtx(getSingleObjectClassInfo().getObjectType());
    }

    @Override
    protected HeaderValues.ServiceType getServiceType()
    {
        return HeaderValues.ServiceType.FUNCTIONAL;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.DataModelLink#getMarshaller()
     */
    @Override
    public MarshalFactory getMarshaller()
    {
        return marshaller;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.DataModelLink#getUnmarshaller()
     */
    @Override
    public UnmarshalFactory getUnmarshaller()
    {
        return unmarshaller;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.EventConsumer#createEventObject(java.lang.Object,
     * sif3.common.header.HeaderValues.EventAction, sif3.common.header.HeaderValues.UpdateType)
     */
    @Override
    public SIFEvent<JobCollectionType> createEventObject(Object sifObjectList,
            EventAction eventAction, UpdateType updateType)
    {
        if (sifObjectList != null)
        {
            if (sifObjectList instanceof JobCollectionType)
            {
                int size = ((JobCollectionType) sifObjectList).getJob().size();
                return new SIFEvent<JobCollectionType>((JobCollectionType) sifObjectList,
                        eventAction, updateType, size);
            }
            else
            {
                logger.error(
                        "The given event data is not of type JobCollectionType as expected. Cannot create event object. Return null");
            }
        }
        else
        {
            logger.error("The given event data is null. Cannot create event object. Return null");
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.consumer.AbstractConsumer#shutdown()
     */
    public void shutdown()
    {
        // Nothing to do at this stage
    }

    @Override
    protected FunctionalServiceClient getClient(ConsumerEnvironment envInfo)
            throws IllegalArgumentException
    {
        URI baseURI = envInfo
                .getConnectorBaseURI(ConsumerEnvironment.ConnectorName.servicesConnector);
        if (baseURI == null)
        {
            logger.error(ConsumerEnvironment.ConnectorName.servicesConnector.toString()
                    + " not defined for environment " + envInfo.getEnvironmentName());
            return null;
        }
        else
        {
            nullMethodCheck(getMarshaller(), "getMarshaller()");
            nullMethodCheck(getUnmarshaller(), "getUnmarshaller()");

            return new FunctionalServiceClient(ConsumerEnvironmentManager.getInstance(),
                    envInfo.getConnectorBaseURI(
                            ConsumerEnvironment.ConnectorName.servicesConnector),
                    getRequestMediaType(), getResponseMediaType(), getMarshaller(),
                    getUnmarshaller(), envInfo.getSecureConnection(), getCompressionEnabled());
        }
    }

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
    public List<Response> createToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType,
            List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");
        nullMethodCheck(getSingleObjectClassInfo(), "getSingleObjectClassInfo()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null
                ? customParameters.getQueryParams() : null;
        List<Response> responses = new ArrayList<Response>();

        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error(
                    "No connected environment for " + getConsumerEnvironment().getEnvironmentName()
                            + ". See previous erro log entries.");
            return responses;
        }

        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList,
                getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(AccessRight.UPDATE, AccessType.APPROVED,
                    zoneCtx.getZone(), zoneCtx.getContext(), null);
            if (error == null)
            {
                // all good
                FunctionalServiceClient client = getClient(getConsumerEnvironment());
                client.setRequestMediaType(requestMediaType, getMarshaller());
                client.setResponseMediaType(responseMediaType, getUnmarshaller());
                responses.add(client.createToPhase(getMultiObjectClassInfo().getObjectName(),
                        resourceID, phaseName, payload, getHeaderProperties(true, customParameters),
                        urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext()));
            }
            else
            {
                // pretend to have received a 'fake' error Response
                responses.add(createErrorResponse(error));
            }
        }

        timer.finish();
        logger.debug("Time taken to call and process 'createToPhase' for "
                + getSingleObjectClassInfo().getObjectName() + "/" + resourceID + "/phases/"
                + phaseName + ": " + timer.timeTaken() + "ms");
        return responses;
    }

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
    public List<Response> retrieveToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType,
            List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");
        nullMethodCheck(getSingleObjectClassInfo(), "getSingleObjectClassInfo()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null
                ? customParameters.getQueryParams() : null;
        List<Response> responses = new ArrayList<Response>();

        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error(
                    "No connected environment for " + getConsumerEnvironment().getEnvironmentName()
                            + ". See previous erro log entries.");
            return responses;
        }

        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList,
                getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(AccessRight.QUERY, AccessType.APPROVED,
                    zoneCtx.getZone(), zoneCtx.getContext(), null);
            if (error == null)
            {
                // all good
                FunctionalServiceClient client = getClient(getConsumerEnvironment());
                client.setRequestMediaType(requestMediaType, getMarshaller());
                client.setResponseMediaType(responseMediaType, getUnmarshaller());
                responses.add(client.retrieveToPhase(getMultiObjectClassInfo().getObjectName(),
                        resourceID, phaseName, payload,
                        getHeaderProperties(false, customParameters), urlQueryParameter,
                        zoneCtx.getZone(), zoneCtx.getContext()));
            }
            else
            {
                // pretend to have received a 'fake' error Response
                responses.add(createErrorResponse(error));
            }
        }

        timer.finish();
        logger.debug("Time taken to call and process 'retrieveToPhase' for "
                + getSingleObjectClassInfo().getObjectName() + "/" + resourceID + "/phases/"
                + phaseName + ": " + timer.timeTaken() + "ms");
        return responses;
    }

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
    public List<Response> updateToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType,
            List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");
        nullMethodCheck(getSingleObjectClassInfo(), "getSingleObjectClassInfo()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null
                ? customParameters.getQueryParams() : null;
        List<Response> responses = new ArrayList<Response>();

        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error(
                    "No connected environment for " + getConsumerEnvironment().getEnvironmentName()
                            + ". See previous erro log entries.");
            return responses;
        }

        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList,
                getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(AccessRight.UPDATE, AccessType.APPROVED,
                    zoneCtx.getZone(), zoneCtx.getContext(), null);
            if (error == null)
            {
                // all good
                FunctionalServiceClient client = getClient(getConsumerEnvironment());
                client.setRequestMediaType(requestMediaType, getMarshaller());
                client.setResponseMediaType(responseMediaType, getUnmarshaller());
                responses.add(
                        client.updateToPhase(getMultiObjectClassInfo().getObjectName(), resourceID,
                                phaseName, payload, getHeaderProperties(false, customParameters),
                                urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext()));
            }
            else
            {
                // pretend to have received a 'fake' error Response
                responses.add(createErrorResponse(error));
            }
        }

        timer.finish();
        logger.debug("Time taken to call and process 'updateToPhase' for "
                + getMultiObjectClassInfo().getObjectName() + "/" + resourceID + "/phases/"
                + phaseName + ": " + timer.timeTaken() + "ms");
        return responses;
    }

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
    public List<Response> deleteToPhase(String resourceID, String phaseName, String payload,
            MediaType requestMediaType, MediaType responseMediaType,
            List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");
        nullMethodCheck(getSingleObjectClassInfo(), "getSingleObjectClassInfo()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null
                ? customParameters.getQueryParams() : null;
        List<Response> responses = new ArrayList<Response>();

        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error(
                    "No connected environment for " + getConsumerEnvironment().getEnvironmentName()
                            + ". See previous erro log entries.");
            return responses;
        }

        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList,
                getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(AccessRight.UPDATE, AccessType.APPROVED,
                    zoneCtx.getZone(), zoneCtx.getContext(), null);
            if (error == null)
            {
                // all good
                FunctionalServiceClient client = getClient(getConsumerEnvironment());
                client.setRequestMediaType(requestMediaType, getMarshaller());
                client.setResponseMediaType(responseMediaType, getUnmarshaller());
                responses.add(
                        client.deleteToPhase(getMultiObjectClassInfo().getObjectName(), resourceID,
                                phaseName, payload, getHeaderProperties(false, customParameters),
                                urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext()));
            }
            else
            {
                // pretend to have received a 'fake' error Response
                responses.add(createErrorResponse(error));
            }
        }

        timer.finish();
        logger.debug("Time taken to call and process 'deleteToPhase' for "
                + getMultiObjectClassInfo().getObjectName() + "/" + resourceID + "/phases/"
                + phaseName + ": " + timer.timeTaken() + "ms");
        return responses;
    }

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
    public List<Response> createToState(String resourceID, String phaseName, StateType state,
            List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");
        nullMethodCheck(getSingleObjectClassInfo(), "getSingleObjectClassInfo()");

        Timer timer = new Timer();
        timer.start();
        URLQueryParameter urlQueryParameter = customParameters != null
                ? customParameters.getQueryParams() : null;
        List<Response> responses = new ArrayList<Response>();

        if (!getConsumerEnvironment().getIsConnected())
        {
            logger.error(
                    "No connected environment for " + getConsumerEnvironment().getEnvironmentName()
                            + ". See previous erro log entries.");
            return responses;
        }

        List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList,
                getSIF3Session());

        // Request operation in all zone/contexts as listed.
        for (ZoneContextInfo zoneCtx : finalZoneContextList)
        {
            ErrorDetails error = allClientChecks(AccessRight.UPDATE, AccessType.APPROVED,
                    zoneCtx.getZone(), zoneCtx.getContext(), null);
            if (error == null)
            {
                // all good
                FunctionalServiceClient client = getClient(getConsumerEnvironment());
                responses.add(client.createToState(getMultiObjectClassInfo().getObjectName(),
                        resourceID, phaseName, state, getHeaderProperties(true, customParameters),
                        urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext()));
            }
            else
            {
                // pretend to have received a 'fake' error Response
                responses.add(createErrorResponse(error));
            }
        }

        timer.finish();
        logger.debug("Time taken to call and process 'createToPhase' for "
                + getSingleObjectClassInfo().getObjectName() + "/" + resourceID + "/phases/"
                + phaseName + ": " + timer.timeTaken() + "ms");
        return responses;
    }
}
