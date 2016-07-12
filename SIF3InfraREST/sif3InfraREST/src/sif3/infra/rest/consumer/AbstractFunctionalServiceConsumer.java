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
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.NullArgumentException;
import org.apache.log4j.Logger;

import au.com.systemic.framework.utils.Timer;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.QueryIntention;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.interfaces.FunctionalServiceConsumer;
import sif3.common.model.CustomParameters;
import sif3.common.model.PagingInfo;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.model.URLQueryParameter;
import sif3.common.model.ZoneContextInfo;
import sif3.common.persist.model.SIF3Job;
import sif3.common.persist.model.SIF3PhaseState;
import sif3.common.utils.JAXBUtils;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.common.env.types.ConsumerEnvironment.ConnectorName;
import sif3.infra.common.utils.ServiceUtils;
import sif3.infra.rest.client.FunctionalServiceClient;

/**
 * The abstract functional service consumer. Developers wishing to build a consumer for their own
 * functional service should extend this class.
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public abstract class AbstractFunctionalServiceConsumer extends AbstractConsumer
        implements FunctionalServiceConsumer
{

    protected final Logger               logger       = Logger.getLogger(getClass());

    private static InfraUnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
    private static InfraMarshalFactory   marshaller   = new InfraMarshalFactory();

    /**
     * Constructor
     */
    public AbstractFunctionalServiceConsumer()
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
        ConnectorName connector = ConnectorName.servicesConnector;

        URI baseURI = envInfo.getConnectorBaseURI(connector);
        if (baseURI == null)
        {
            logger.error(connector.toString() + " not defined for environment "
                    + envInfo.getEnvironmentName());
            return null;
        }
        else
        {
            nullMethodCheck(getMarshaller(), "getMarshaller()");
            nullMethodCheck(getUnmarshaller(), "getUnmarshaller()");

            return new FunctionalServiceClient(ConsumerEnvironmentManager.getInstance(),
                    envInfo.getConnectorBaseURI(connector), getRequestMediaType(),
                    getResponseMediaType(), getMarshaller(), getUnmarshaller(),
                    envInfo.getSecureConnection(), getCompressionEnabled());
        }
    }

    @Override
    public List<Response> createSingle(Object data, java.util.List<ZoneContextInfo> zoneCtxList,
            CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        if (data == null)
        {
            throw new NullArgumentException("data");
        }
        if (!(data instanceof SIF3Job))
        {
            throw new IllegalArgumentException("Payload must be of type SIF3Job");
        }

        return ServiceUtils.unmarshal(super.createSingle(ServiceUtils.marshal((SIF3Job) data),
                zoneCtxList, customParameters));
    };

    @Override
    public List<BulkOperationResponse<CreateOperationStatus>> createMany(Object data,
            List<ZoneContextInfo> zoneCtxList, RequestType requestType,
            CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        if (data == null)
        {
            throw new NullArgumentException("data");
        }
        if (!(data instanceof Collection))
        {
            throw new IllegalArgumentException("Payload must be a collection");
        }
        return super.createMany(ServiceUtils.marshal((Collection<?>) data), zoneCtxList,
                requestType, customParameters);
    }

    @Override
    public List<Response> retrieveByPrimaryKey(String resourceID, List<ZoneContextInfo> zoneCtxList,
            CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        return ServiceUtils
                .unmarshal(super.retrieveByPrimaryKey(resourceID, zoneCtxList, customParameters));
    }

    @Override
    public List<Response> retrieveByQBE(Object exampleObject, PagingInfo pagingInfo,
            List<ZoneContextInfo> zoneCtxList, RequestType requestType,
            QueryIntention queryIntention, CustomParameters customParameters)
            throws PersistenceException, UnsupportedQueryException, ServiceInvokationException
    {
        if (exampleObject == null)
        {
            throw new NullArgumentException("data");
        }
        if (!(exampleObject instanceof SIF3Job))
        {
            throw new IllegalArgumentException("Example object must be of type SIF3Job");
        }
        return ServiceUtils
                .unmarshal(super.retrieveByQBE(ServiceUtils.marshal((SIF3Job) exampleObject),
                        pagingInfo, zoneCtxList, requestType, queryIntention, customParameters));
    }

    @Override
    public List<Response> retrieve(PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList,
            RequestType requestType, QueryIntention queryIntention,
            CustomParameters customParameters)
            throws PersistenceException, UnsupportedQueryException, ServiceInvokationException
    {
        return ServiceUtils.unmarshal(super.retrieve(pagingInfo, zoneCtxList, requestType,
                queryIntention, customParameters));
    }

    @Override
    public List<Response> updateSingle(Object data, String resourceID,
            List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        throw new UnsupportedOperationException("Operation not applicable to a functional service");
    }

    @Override
    public List<BulkOperationResponse<OperationStatus>> updateMany(Object data,
            List<ZoneContextInfo> zoneCtxList, RequestType requestType,
            CustomParameters customParameters)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        throw new UnsupportedOperationException("Operation not applicable to a functional service");
    }

    @Override
    public List<BulkOperationResponse<OperationStatus>> updateMany(Object data,
            List<ZoneContextInfo> zoneCtxList, RequestType requestType)
            throws IllegalArgumentException, PersistenceException, ServiceInvokationException
    {
        throw new UnsupportedOperationException("Operation not applicable to a functional service");
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.consumer.FunctionalServiceConsumer#createToPhase(java.lang.String,
     * java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType,
     * java.util.List, sif3.common.model.CustomParameters)
     */
    @Override
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

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.consumer.FunctionalServiceConsumer#retrieveToPhase(java.lang.String,
     * java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType,
     * java.util.List, sif3.common.model.CustomParameters)
     */
    @Override
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

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.consumer.FunctionalServiceConsumer#updateToPhase(java.lang.String,
     * java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType,
     * java.util.List, sif3.common.model.CustomParameters)
     */
    @Override
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

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.consumer.FunctionalServiceConsumer#deleteToPhase(java.lang.String,
     * java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, javax.ws.rs.core.MediaType,
     * java.util.List, sif3.common.model.CustomParameters)
     */
    @Override
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

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.common.interfaces.FunctionalServiceConsumer#createToState(java.lang.String,
     * java.lang.String, sif3.common.persist.model.SIF3PhaseState, java.util.List,
     * sif3.common.model.CustomParameters)
     */
    @Override
    public List<Response> createToState(String resourceID, String phaseName, SIF3PhaseState state,
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
        return ServiceUtils.unmarshal(responses);
    }
}
