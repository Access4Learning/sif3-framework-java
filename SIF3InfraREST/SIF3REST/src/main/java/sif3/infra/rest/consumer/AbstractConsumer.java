/*
 * AbstractConsumer.java Created: 23/09/2013
 * 
 * Copyright 2013 Systemic Pty Ltd
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.Timer;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.QueryIntention;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.interfaces.Consumer;
import sif3.common.interfaces.DelayedConsumer;
import sif3.common.interfaces.QueryConsumer;
import sif3.common.model.ACL.AccessRight;
import sif3.common.model.ACL.AccessType;
import sif3.common.model.CustomParameters;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.QueryPredicate;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.model.URLQueryParameter;
import sif3.common.model.ZoneContextInfo;
import sif3.common.model.delayed.DelayedResponseReceipt;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.common.ws.model.MultiOperationStatusList;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.rest.client.ObjectServiceClient;

/**
 * This is the core class that a developer will use to implement their object consumers. Each consumer for each object type MUST extend 
 * this class. It forms the link between the high level consumer implementation and the low level infrastructure functions which this 
 * class abstracts.<br/>
 * It forms the link between the SIF3 functions/infrastructure and the data model that shall be transported over the SIF3 
 * Infrastructure. The marshal and unmarshal factories required by this class as well as the two core abstract methods
 * getSingleObjectClassInfo() and getMultiObjectClassInfo() are the key to link between data model and infrastructure.<br/>
 * It is assumed that the ConsumerLoader.initialise() method has been called before any methods of this class are called.If not then 
 * the behaviour of this class is not defined. In fact each call to any method of this class will first test if initialisation has 
 * succeeded and no action in any of the top level methods will be executed if the ConsumerLoader.initialise() hasn't been called before.
 * 
 * @author Joerg Huber
 */
public abstract class AbstractConsumer extends BaseConsumer implements Consumer, DelayedConsumer, QueryConsumer
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /*---------------------------------------------------------------------*/
    /* Abstract method relating to DELAYED request response functionality. */
    /*---------------------------------------------------------------------*/
	
	/**
	 * This method is called when a delayed create response is retrieved from the consumer's queue.<br/><br/>
	 * 
	 * @see sif3.common.interfaces.DelayedConsumer#onCreateMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
	 * 
	 * @param statusList See onCreateMany() method in DelayedConsumer class.
	 * @param receipt See onCreateMany() method in DelayedConsumer class.
	 */
	public abstract void processDelayedCreateMany(MultiOperationStatusList<CreateOperationStatus> statusList, DelayedResponseReceipt receipt);
	
	/**
	 * This method is called when a delayed update response is retrieved from the consumer's queue.<br/><br/>
	 * 
	 * @see sif3.common.interfaces.DelayedConsumer#onUpdateMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
	 * 
	 * @param statusList See onUpdateMany() method in DelayedConsumer class.
	 * @param receipt See onUpdateMany() method in DelayedConsumer class.
	 */
	public abstract void processDelayedUpdateMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt);
	
	/**
	 * This method is called when a delayed delete response is retrieved from the consumer's queue.<br/><br/>
	 * 
	 * @see sif3.common.interfaces.DelayedConsumer#onDeleteMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
	 * 
	 * @param statusList See onDeleteMany() method in DelayedConsumer class.
	 * @param receipt See onDeleteMany() method in DelayedConsumer class.
	 */
	public abstract void processDelayedDeleteMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt);
	
	/**
	 * This method is called when a delayed query response is retrieved from the consumer's queue.<br/><br/>
	 * 
	 * @see sif3.common.interfaces.DelayedConsumer#onQuery(java.lang.Object, sif3.common.model.PagingInfo, sif3.common.model.delayed.DelayedResponseReceipt)
	 * 
	 * @param dataObject See onQuery() method in DelayedConsumer class.
	 * @param pagingInfo See onQuery() method in DelayedConsumer class.
	 * @param receipt See onQuery() method in DelayedConsumer class.
	 */
	public abstract void processDelayedQuery(Object dataObject, PagingInfo pagingInfo, DelayedResponseReceipt receipt);
	
	/**
	 * This method is called when a delayed service path response is retrieved from the consumer's queue.<br/><br/>
	 * 
	 * @see sif3.common.interfaces.DelayedConsumer#onServicePath(java.lang.Object, sif3.common.model.QueryCriteria, sif3.common.model.PagingInfo, sif3.common.model.delayed.DelayedResponseReceipt)
	 * 
	 * @param dataObject See onServicePath() method in DelayedConsumer class.
	 * @param queryCriteria See onServicePath() method in DelayedConsumer class.
	 * @param pagingInfo See onServicePath() method in DelayedConsumer class.
	 * @param receipt See onServicePath() method in DelayedConsumer class.
	 */
	public abstract void processDelayedServicePath(Object dataObject, QueryCriteria queryCriteria, PagingInfo pagingInfo, DelayedResponseReceipt receipt);
	
    /*----------------------------------*/
    /* Consumer Implementation Methods. */
    /*----------------------------------*/

	/**
	 * Constructor.
	 */
	public AbstractConsumer()
	{
		super();
	}

    /*-----------------------*/
	/*-- Create Operations --*/
	/*-----------------------*/
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#createMany(java.lang.Object, java.util.List)
	 */
	@Override
	public List<BulkOperationResponse<CreateOperationStatus>> createMany(Object data, List<ZoneContextInfo> zoneCtxList, RequestType requestType, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
	    nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");

		Timer timer = new Timer();
		timer.start();
		URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
		List<BulkOperationResponse<CreateOperationStatus>> responses = new ArrayList<BulkOperationResponse<CreateOperationStatus>>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}

		List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

		// Request operation in all zone/contexts as listed.
		for (ZoneContextInfo zoneCtx : finalZoneContextList)
		{
			ErrorDetails error = allClientChecks(AccessRight.CREATE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
			if (error == null) //all good => Send request.
			{
				BulkOperationResponse<CreateOperationStatus> response = getClient(getConsumerEnvironment()).createMany(getMultiObjectClassInfo().getObjectName(), getMultiObjectClassInfo().getObjectName(), data, getHeaderProperties(true, customParameters), urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext(), requestType);

				// Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
				finaliseDelayedReceipt(response.getDelayedReceipt(), getMultiObjectClassInfo().getObjectName(), ServiceType.OBJECT, ResponseAction.CREATE);
				responses.add(response);
			}
			else //pretend to have received a 'fake' error Response
			{
				responses.add(makeBulkErrorResponseForCreates(error));
			}
		}					

		timer.finish();
		logger.debug("Time taken to call and process 'createMany' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*
	 * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
	 */
	public List<BulkOperationResponse<CreateOperationStatus>> createMany(Object data, List<ZoneContextInfo> zoneCtxList, RequestType requestType) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		return createMany(data, zoneCtxList, requestType, null);
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#createSingle(java.lang.Object, java.util.List)
	 */
	@Override
	public List<Response> createSingle(Object data, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");
        nullMethodCheck(getSingleObjectClassInfo(), "getSingleObjectClassInfo()");

	    Timer timer = new Timer();
		timer.start();
		URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
		List<Response> responses = new ArrayList<Response>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}

		List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

		// Request operation in all zone/contexts as listed.
		for (ZoneContextInfo zoneCtx : finalZoneContextList)
		{
			ErrorDetails error = allClientChecks(AccessRight.CREATE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), null);
			if (error == null) //all good
			{
				responses.add(getClient(getConsumerEnvironment()).createSingle(getMultiObjectClassInfo().getObjectName()+"/"+getSingleObjectClassInfo().getObjectName(), data, getHeaderProperties(true, customParameters), urlQueryParameter, getSingleObjectClassInfo().getObjectType(), zoneCtx.getZone(), zoneCtx.getContext()));
			}
			else //pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}					

		timer.finish();
		logger.debug("Time taken to call and process 'createSingle' for "+getSingleObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*
	 * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
	 */
	public List<Response> createSingle(Object data, List<ZoneContextInfo> zoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		return createSingle(data, zoneCtxList, null);
	}
	
	/*-----------------------*/
	/*-- Delete Operations --*/
	/*-----------------------*/

	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#deleteMany(java.lang.Object, java.util.List)
	 */
	@Override
	public List<BulkOperationResponse<OperationStatus>> deleteMany(List<String> resourceIDs, List<ZoneContextInfo> zoneCtxList, RequestType requestType, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");

	    Timer timer = new Timer();
		timer.start();
		URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
		List<BulkOperationResponse<OperationStatus>> responses = new ArrayList<BulkOperationResponse<OperationStatus>>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}

		List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

		// Request operation in all zone/contexts as listed.
		for (ZoneContextInfo zoneCtx : finalZoneContextList)
		{
			ErrorDetails error = allClientChecks(AccessRight.DELETE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
			if (error == null) //all good => Send request
			{
				BulkOperationResponse<OperationStatus> response = getClient(getConsumerEnvironment()).removeMany(getMultiObjectClassInfo().getObjectName(), getMultiObjectClassInfo().getObjectName(), resourceIDs, getHeaderProperties(false, customParameters), urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext(), requestType);

				// Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
				finaliseDelayedReceipt(response.getDelayedReceipt(), getMultiObjectClassInfo().getObjectName(), ServiceType.OBJECT, ResponseAction.DELETE);
				responses.add(response);
			}
			else //pretend to have received a 'fake' error Response
			{
				responses.add(makeBulkErrorResponse(error));
			}
		}

		timer.finish();
		logger.debug("Time taken to call and process 'removeMany' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*
	 * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
	 */
	public List<BulkOperationResponse<OperationStatus>> deleteMany(List<String> resourceIDs, List<ZoneContextInfo> zoneCtxList, RequestType requestType) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		return deleteMany(resourceIDs, zoneCtxList, requestType, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#deleteSingle(java.lang.String, java.util.List)
	 */
	@Override
	public List<Response> deleteSingle(String resourceID, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");

	    Timer timer = new Timer();
		timer.start();
		URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
		List<Response> responses = new ArrayList<Response>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}

		List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

		// Request operation in all zone/contexts as listed.
		for (ZoneContextInfo zoneCtx : finalZoneContextList)
		{
			ErrorDetails error = allClientChecks(AccessRight.DELETE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), null);
			if (error == null) //all good
			{
				responses.add(getClient(getConsumerEnvironment()).removeSingle(getMultiObjectClassInfo().getObjectName(), resourceID, getHeaderProperties(false, customParameters), urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext()));
			}
			else //pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}					

		timer.finish();
		logger.debug("Time taken to call and process 'delete by primary key' for "+getMultiObjectClassInfo().getObjectName()+"/"+resourceID+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*
	 * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
	 */
	public List<Response> deleteSingle(String resourceID, List<ZoneContextInfo> zoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		return deleteSingle(resourceID, zoneCtxList, null);
	}
	
	/*-------------------------*/
	/*-- Retrieve Operations --*/
	/*-------------------------*/
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#retrievByPrimaryKey(java.lang.String, java.util.List)
	 */
	@Override
	public List<Response> retrievByPrimaryKey(String resourceID, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");
        nullMethodCheck(getSingleObjectClassInfo(), "getSingleObjectClassInfo()");

		Timer timer = new Timer();
		timer.start();
		URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
		List<Response> responses = new ArrayList<Response>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}
		
		List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

		// Request operation in all zone/contexts as listed.
		for (ZoneContextInfo zoneCtx : finalZoneContextList)
		{
			ErrorDetails error = allClientChecks(AccessRight.QUERY, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), null);
			if (error == null) //all good
			{
				responses.add(getClient(getConsumerEnvironment()).getSingle(getMultiObjectClassInfo().getObjectName(), resourceID, getHeaderProperties(false, customParameters), urlQueryParameter, getSingleObjectClassInfo().getObjectType(), zoneCtx.getZone(), zoneCtx.getContext()));
			}
			else //pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}					

		timer.finish();
		logger.debug("Time taken to call and process 'retrieve by primary key' for "+getSingleObjectClassInfo().getObjectName()+"/"+resourceID+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*
	 * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
	 */
	public List<Response> retrievByPrimaryKey(String resourceID, List<ZoneContextInfo> zoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		return retrievByPrimaryKey(resourceID, zoneCtxList, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#retrieve(sif3.common.model.PagingInfo, java.util.List)
	 */
	@Override
	public List<Response> retrieve(PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, RequestType requestType, QueryIntention queryIntention, CustomParameters customParameters) throws PersistenceException, ServiceInvokationException
	{
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");

		Timer timer = new Timer();
		timer.start();
		URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
		List<Response> responses = new ArrayList<Response>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}
		
		// Set default set of HTTP Header fields
		HeaderProperties hdrProps = getHeaderProperties(false, customParameters);
		
        // Add query intention to headers.
        addQueryIntentionToHeaders(hdrProps, queryIntention);
		
		List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

		// Request operation in all zone/contexts as listed.
		for (ZoneContextInfo zoneCtx : finalZoneContextList)
		{
			ErrorDetails error = allClientChecks(AccessRight.QUERY, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
			if (error == null) //all good => Send request
			{
				Response response = getClient(getConsumerEnvironment()).getMany(getMultiObjectClassInfo().getObjectName(), getMultiObjectClassInfo().getObjectName(), ServiceType.OBJECT, pagingInfo, hdrProps, urlQueryParameter, getMultiObjectClassInfo().getObjectType(), zoneCtx.getZone(), zoneCtx.getContext(), requestType);

				// Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
				finaliseDelayedReceipt(response.getDelayedReceipt(), getMultiObjectClassInfo().getObjectName(), ServiceType.OBJECT, ResponseAction.QUERY);
				responses.add(response);
			}
			else //pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}					

		timer.finish();
		logger.debug("Time taken to call and process 'retrieve all' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*
	 * See description of retrieve() but without the queryIntention parameter. Since this parameter is not required
	 * by this method it will be assumed null, which in turn will assume ONE-OFF as per interface definition. Further
	 * the customParameters will be set to null.
	 */
	public List<Response> retrieve(PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, RequestType requestType) throws PersistenceException, UnsupportedQueryException, ServiceInvokationException
	{
		return retrieve(pagingInfo, zoneCtxList, requestType, QueryIntention.ONE_OFF, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.QueryConsumer#retrieveByServicePath(sif3.common.model.QueryCriteria, sif3.common.model.PagingInfo, java.util.List, sif3.common.header.HeaderValues.RequestType, sif3.common.header.HeaderValues.QueryIntention, sif3.common.model.CustomParameters)
	 */
	public List<Response> retrieveByServicePath(QueryCriteria queryCriteria, PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, RequestType requestType, QueryIntention queryIntention, CustomParameters customParameters) throws PersistenceException, ServiceInvokationException
	{
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");

		Timer timer = new Timer();
		timer.start();
		URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
		List<Response> responses = new ArrayList<Response>();

		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for " + getConsumerEnvironment().getEnvironmentName() + ". See previous erro log entries.");
			return responses;
		}
		
		// Ensure query Intention is not null. if so default to ONE-OFF as per SIF 3.x spec.
		queryIntention = (queryIntention == null) ? QueryIntention.ONE_OFF : queryIntention;
		
		// Set default set of HTTP Header fields
		HeaderProperties hdrProps = getHeaderProperties(false, HeaderValues.ServiceType.SERVICEPATH, customParameters);
		
		// Add query intention to headers.
		hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_QUERY_INTENTION, queryIntention.getHTTPHeaderValue());

		List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

		String serviceName = getServiceName(queryCriteria);
		String servicePath = getServicePath(queryCriteria);
		
		// Request operation in all zone/contexts as listed.
		for (ZoneContextInfo zoneCtx : finalZoneContextList)
		{
			ErrorDetails error = allClientChecks(serviceName, null, AccessRight.QUERY, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
			if (error == null) //all good => Send request
			{
				
				Response response = getClient(getConsumerEnvironment()).getMany(servicePath, serviceName, ServiceType.SERVICEPATH, pagingInfo, hdrProps, urlQueryParameter, getMultiObjectClassInfo().getObjectType(), zoneCtx.getZone(), zoneCtx.getContext(), requestType);

				// Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
				finaliseDelayedReceipt(response.getDelayedReceipt(), getMultiObjectClassInfo().getObjectName(), ServiceType.SERVICEPATH, ResponseAction.QUERY);
				responses.add(response);
			}
			else // pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}

	    timer.finish();
	    logger.debug("Time taken to call and process 'retrieve all' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
	    return responses;
	}
	 
	/*
	 * See description of retrieveByServicePath() but without the queryIntention & customParameters parameters. Since this parameter 
	 * is not required by this method it will be assumed null, which in turn will assume ONE-OFF as per interface definition. Further
	 * the customParameters will be set to null.
	 */
	public List<Response> retrieveByServicePath(QueryCriteria queryCriteria, PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, RequestType requestType) throws PersistenceException, UnsupportedQueryException, ServiceInvokationException
	{
		return retrieveByServicePath(queryCriteria, pagingInfo, zoneCtxList, requestType, QueryIntention.ONE_OFF, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.QueryConsumer#retrieveByQBE(java.lang.Object, sif3.common.model.PagingInfo, java.util.List, sif3.common.header.HeaderValues.RequestType, sif3.common.header.HeaderValues.QueryIntention, sif3.common.model.CustomParameters)
	 */
	public List<Response> retrieveByQBE(Object exampleObject, 
								        PagingInfo pagingInfo, 
								        List<ZoneContextInfo> zoneCtxList, 
								        RequestType requestType, 
								        QueryIntention queryIntention, 
								        CustomParameters customParameters) throws PersistenceException, ServiceInvokationException
	{
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");

        Timer timer = new Timer();
		timer.start();
		URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
		List<Response> responses = new ArrayList<Response>();

		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for " + getConsumerEnvironment().getEnvironmentName() + ". See previous erro log entries.");
			return responses;
		}

		// Ensure query Intention is not null. if so default to ONE-OFF as per SIF 3.x spec.
		queryIntention = (queryIntention == null) ? QueryIntention.ONE_OFF : queryIntention;
		
		// Set default set of HTTP Header fields
		HeaderProperties hdrProps = getHeaderProperties(false, HeaderValues.ServiceType.OBJECT, customParameters);
		
		// Add query intention to headers.
		hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_QUERY_INTENTION, queryIntention.getHTTPHeaderValue());

		List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

		// Request operation in all zone/contexts as listed.
		for (ZoneContextInfo zoneCtx : finalZoneContextList)
		{
			ErrorDetails error = allClientChecks(AccessRight.QUERY, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
			if (error == null) //all good => Send request
			{
				Response response = getClient(getConsumerEnvironment()).getByQBE(getMultiObjectClassInfo().getObjectName(), getMultiObjectClassInfo().getObjectName(), exampleObject, pagingInfo, hdrProps, urlQueryParameter, getMultiObjectClassInfo().getObjectType(), zoneCtx.getZone(), zoneCtx.getContext(), requestType);

				// Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
				finaliseDelayedReceipt(response.getDelayedReceipt(), getMultiObjectClassInfo().getObjectName(), ServiceType.OBJECT, ResponseAction.QUERY);
				responses.add(response);
			}
			else // pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}

	    timer.finish();
	    logger.debug("Time taken to call and process 'retrieve all' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
	    return responses;
	}
	
	/*
	 * See description of retrieveByServicePath() but without the queryIntention & customParameters parameters. Since this parameter 
	 * is not required by this method it will be assumed null, which in turn will assume ONE-OFF as per interface definition. Further
	 * the customParameters will be set to null.
	 */
	public List<Response> retrieveByQBE(Object exampleObject, 
								        PagingInfo pagingInfo, 
								        List<ZoneContextInfo> zoneCtxList, 
								        RequestType requestType) throws PersistenceException, UnsupportedQueryException, ServiceInvokationException
	{
		return retrieveByQBE(exampleObject, pagingInfo, zoneCtxList, requestType, QueryIntention.ONE_OFF, null);
	}
	
	/*-----------------------*/
	/*-- Update Operations --*/
	/*-----------------------*/
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#updateMany(java.lang.Object, java.util.List)
	 */
	@Override
	public List<BulkOperationResponse<OperationStatus>> updateMany(Object data, List<ZoneContextInfo> zoneCtxList, RequestType requestType, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");

    	Timer timer = new Timer();
		timer.start();
		URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
		List<BulkOperationResponse<OperationStatus>> responses = new ArrayList<BulkOperationResponse<OperationStatus>>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}

		List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

		// Request operation in all zone/contexts as listed.
		for (ZoneContextInfo zoneCtx : finalZoneContextList)
		{
			ErrorDetails error = allClientChecks(AccessRight.UPDATE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
			if (error == null) //all good => Send request
			{
				BulkOperationResponse<OperationStatus> response = getClient(getConsumerEnvironment()).updateMany(getMultiObjectClassInfo().getObjectName(), getMultiObjectClassInfo().getObjectName(), data, getHeaderProperties(false, customParameters), urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext(), requestType);

				// Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
				finaliseDelayedReceipt(response.getDelayedReceipt(), getMultiObjectClassInfo().getObjectName(), ServiceType.OBJECT, ResponseAction.UPDATE);
				responses.add(response);
			}
			else //pretend to have received a 'fake' error Response
			{
				responses.add(makeBulkErrorResponse(error));
			}
		}					

		timer.finish();
		logger.debug("Time taken to call and process 'updateMany' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*
	 * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
	 */
	public List<BulkOperationResponse<OperationStatus>> updateMany(Object data, List<ZoneContextInfo> zoneCtxList, RequestType requestType) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		return updateMany(data, zoneCtxList, requestType, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#updateSingle(java.lang.Object, java.lang.String, java.util.List)
	 */
	@Override
	public List<Response> updateSingle(Object data, String resourceID, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");

    	Timer timer = new Timer();
		timer.start();
		URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
		List<Response> responses = new ArrayList<Response>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}

		List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

		// Request operation in all zone/contexts as listed.
		for (ZoneContextInfo zoneCtx : finalZoneContextList)
		{
			ErrorDetails error = allClientChecks(AccessRight.UPDATE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), null);
			if (error == null) //all good
			{
				responses.add(getClient(getConsumerEnvironment()).updateSingle(getMultiObjectClassInfo().getObjectName(), resourceID, data, getHeaderProperties(false, customParameters), urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext()));
			}
			else //pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}					

		timer.finish();
		logger.debug("Time taken to call and process 'update by primary key' for "+getMultiObjectClassInfo().getObjectName()+"/"+resourceID+": "+timer.timeTaken()+"ms");
		return responses;
	}
	
	/*
	 * Convenience method. The same as above but without the parameter 'customParameters' which is defaulted to null.
	 */
	public List<Response> updateSingle(Object data, String resourceID, List<ZoneContextInfo> zoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		return updateSingle(data, resourceID, zoneCtxList, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.MinimalConsumer#finalise()
	 */
	@Override
    public void finalise()
    {
	    // Clean up stuff for this consumer
	    
	    // Clean up the BaseConsumer
	    super.finalise();

	    // Clean up the specific consumer implementation
	    shutdown();
    }
	
	/*---------------------------------------------------*/
    /*-- HEAD Method Consumer Interface implementation --*/
    /*---------------------------------------------------*/
	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.Consumer#getServiceInfo(sif3.common.model.PagingInfo, java.util.List, sif3.common.model.CustomParameters)
	 */
	@Override
    public List<Response> getServiceInfo(PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, CustomParameters customParameters) throws PersistenceException, ServiceInvokationException
    {
        nullMethodCheck(getMultiObjectClassInfo(), "getMultiObjectClassInfo()");

		Timer timer = new Timer();
		timer.start();
		URLQueryParameter urlQueryParameter = customParameters != null ? customParameters.getQueryParams() : null;
		List<Response> responses = new ArrayList<Response>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}
		
		// Set default set of HTTP Header fields
		HeaderProperties hdrProps = getHeaderProperties(false, customParameters);
		List<ZoneContextInfo> finalZoneContextList = getFinalZoneCtxList(zoneCtxList, getSIF3Session());

		// Request operation in all zone/contexts as listed.
		for (ZoneContextInfo zoneCtx : finalZoneContextList)
		{
			ErrorDetails error = allClientChecks(AccessRight.QUERY, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), RequestType.IMMEDIATE);
			if (error == null) //all good => Send request
			{
				Response response = getClient(getConsumerEnvironment()).getServiceInfo(getMultiObjectClassInfo().getObjectName(), getMultiObjectClassInfo().getObjectName(), pagingInfo, hdrProps, urlQueryParameter, zoneCtx.getZone(), zoneCtx.getContext());

				// Set the missing delayed response properties. No need to check if it was delayed request as it is checked in the finaliseDelayedReceipt method.
				finaliseDelayedReceipt(response.getDelayedReceipt(), getMultiObjectClassInfo().getObjectName(), ServiceType.OBJECT, ResponseAction.HEAD);
				responses.add(response);
			}
			else //pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}					

		timer.finish();
		logger.debug("Time taken to call and process 'retrieve all' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
		return responses;
    }


	/*----------------------------------------------------------*/
    /*-- Methods for DelayedConsumer Interface implementation --*/
    /*----------------------------------------------------------*/
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DelayedConsumer#onCreateMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
	 */
	public void onCreateMany(MultiOperationStatusList<CreateOperationStatus> statusList, DelayedResponseReceipt receipt)
	{
		processDelayedCreateMany(statusList, receipt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DelayedConsumer#onUpdateMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
	 */
	public void onUpdateMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt)
	{
		processDelayedUpdateMany(statusList, receipt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DelayedConsumer#onDeleteMany(sif3.common.ws.model.MultiOperationStatusList, sif3.common.model.delayed.DelayedResponseReceipt)
	 */
	public void onDeleteMany(MultiOperationStatusList<OperationStatus> statusList, DelayedResponseReceipt receipt)
	{
		processDelayedDeleteMany(statusList, receipt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DelayedConsumer#onQuery(java.lang.Object, sif3.common.model.PagingInfo, sif3.common.model.delayed.DelayedResponseReceipt)
	 */
	public void onQuery(Object dataObject, PagingInfo pagingInfo, DelayedResponseReceipt receipt)
	{
		processDelayedQuery(dataObject, pagingInfo, receipt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DelayedConsumer#onServicePath(java.lang.Object, sif3.common.model.QueryCriteria, sif3.common.model.PagingInfo, sif3.common.model.delayed.DelayedResponseReceipt)
	 */
	public void onServicePath(Object dataObject, QueryCriteria queryCriteria, PagingInfo pagingInfo, DelayedResponseReceipt receipt)
	{
		processDelayedServicePath(dataObject, queryCriteria, pagingInfo, receipt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DelayedConsumer#onError(sif3.common.ws.ErrorDetails, sif3.common.model.delayed.DelayedResponseReceipt)
	 */
	public void onError(ErrorDetails error, DelayedResponseReceipt receipt)
	{
		processDelayedError(error, receipt);
	}
	
    /*----------------------------*/
    /*-- Other required methods --*/
    /*----------------------------*/
    /**
     * This method is is called when the async processor is initialised. It passes all subscription services for the
     * given OBJECT & SERVICEPATH service across all zones for the connected environment to this method. It allows the specific
     * async consumer implementation to remove some of the subscription services it is not interested in. Basically it allows
     * the implementor to filter out un-needed services before the this consumer subscribes to the local queues. Only
     * delayed responses for the returned list of service info will then be received by the particular service. Most standard
     * implementations would not require any overriding of this method. If a specific implementation wishes to filter out
     * some of the environment provided subscriptions then the sub-class of this class should override this method.
     * 
     * @param allServices A list of services for this OBJECT/SERVICEPATH that is allowed to subscribe to events and delayed responses 
     *                    across the environment of this consumer.
     * 
     * @return The final list of services for which this consumer class shall subscribe to.
     */
    public List<ServiceInfo> filterApprovedCRUDServices(List<ServiceInfo> allServices)
    {
    	return allServices;
    }

	/*
	 * Returns all CRUD services for which this consumer has access to. This should be a list of different zones, contexts and service types.
	 * It returns all OBJECT and SERVICEPATH services where the consumer has CREATE, UPDATE, DELETE or QUERY as approved in the ACL.
	 */
	protected final List<ServiceInfo> getAllApprovedCRUDServices()
	{
	    List<ServiceInfo> allServices = new ArrayList<ServiceInfo>();
	    
	    // Get all Object Services
	    allServices.addAll(getAllApprovedServicesForRights(getMultiObjectClassInfo().getObjectName(), ServiceType.OBJECT, AccessRight.CREATE, AccessRight.UPDATE, AccessRight.DELETE, AccessRight.QUERY));

	    // Get all ServicePath Services
        allServices.addAll(getAllApprovedServicesForRights(getMultiObjectClassInfo().getObjectName(), ServiceType.SERVICEPATH, AccessRight.QUERY));

		return filterApprovedCRUDServices(allServices);
	}	
	
	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private ObjectServiceClient getClient(ConsumerEnvironment envInfo) throws IllegalArgumentException
	{
		URI baseURI = envInfo.getConnectorBaseURI(ConsumerEnvironment.ConnectorName.requestsConnector);
		if (baseURI == null)
		{
			logger.error(ConsumerEnvironment.ConnectorName.requestsConnector.toString()+" not defined for environment "+envInfo.getEnvironmentName());
			return null;
		}
		else
		{
		    nullMethodCheck(getMarshaller(), "getMarshaller()");
            nullMethodCheck(getUnmarshaller(), "getUnmarshaller()");
		    
			return new ObjectServiceClient(ConsumerEnvironmentManager.getInstance(),
			                               envInfo.getConnectorBaseURI(ConsumerEnvironment.ConnectorName.requestsConnector), 
			                               getDataModelRequestPayloadMetadata(),
			                               getDataModelResponsePayloadMetadata(),
			                               getMarshaller(), 
			                               getUnmarshaller(),
			                               envInfo.getSecureConnection(),
			                               getCompressionEnabled());
		}
	}
		
	private HeaderProperties getHeaderProperties(boolean isCreateOperation, CustomParameters customParameters)
	{
	  return getHeaderProperties(isCreateOperation, ServiceType.OBJECT, customParameters);
	}
	
	/*
	 * Will perform hasAccess() and requestTypeSupported() checks. This is a convenience method, so that not each operation has to
	 * call the two methods sequentially and manage all the flow.
	 */
	private ErrorDetails allClientChecks(AccessRight right, AccessType accessType, SIFZone zone, SIFContext context, RequestType requestType) 
	{
	  return allClientChecks(getMultiObjectClassInfo().getObjectName(), null, right, accessType, zone, context, requestType);
	}
	
	private String getServiceName(QueryCriteria queryCriteria)
	{
		String result = null;
		if (queryCriteria != null && queryCriteria.getPredicates() != null)
		{
			result = "";
			for (QueryPredicate predicate : queryCriteria.getPredicates())
			{
				result += predicate.getSubject() + "/{}/";
			}
			result += getMultiObjectClassInfo().getObjectName();
		}
		return result;
	}

	private String getServicePath(QueryCriteria queryCriteria)
	{
		String result = null;
		if (queryCriteria != null && queryCriteria.getPredicates() != null)
		{
			result = "";
			for (QueryPredicate predicate : queryCriteria.getPredicates())
			{
				result += predicate.getSubject() + "/" + predicate.getValue() + "/";
			}
			result += getMultiObjectClassInfo().getObjectName();
		}
		return result;
	}
}
