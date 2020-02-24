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
import sif3.common.model.PayloadMetadata;
import sif3.common.model.QueryCriteria;
import sif3.common.model.QueryPredicate;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.SchemaInfo;
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

	/*------------------------------------------------------------------------------------------------------------------------
	 * Start of 'Dynamic' HTTP Header Field override section
	 * 
	 * The following set of methods are used for a more configurable way how some HTTP header in relation to data model
	 * are set. The BaseConsumer class caters for some more generic HTTP header overrides. The ones listed here are
	 * are specific for Object Services only. They do not need to be available for Functional or any other service types.
	 * By default the following HTTP Header fields are retrieved from the consumer's property file and put in corresponding
	 * HTTP Header Fields:
	 * 
	 * env.schema.dm.domain,env.schema.dm.version,env.schema.dm.json.type ==> HTTP Header: content-profile, accept-profile
	 * 
	 * There are situations where and application may need a more 'dynamic' behaviour where the above values are determined
	 * at runtime, based on other circumstances and therefore these properties must be retrieved from an other source than the
	 * consumer's property file. In such a case the methods below can be overwritten to make them dynamic and controlled by
	 * the implementation rather than driven by the consumer's property file. If any of the methods below is overwritten then
	 * the value of the over riding method is set in the corresponding HTTP Header field if the return value of the method 
	 * is not null or an empty string.
	 *------------------------------------------------------------------------------------------------------------------------*/

	/**
	 * If the property env.schema.enabled in the consumer's property file is set to true then this method returns the SchemaInfo 
	 * made up of the following properties in the consumer's property file:<br/>
	 * - env.schema.dm.domain <br/>
	 * - env.schema.dm.version <br/>
	 * - env.schema.dm.json.type <br/><br/>
	 *  
	 * If the env.schema.enabled in the consumer's property file is set to false then null is returned as it is assumed that schema
	 * negotiation is not enabled for the given consumer.<br/><br/>
	 * 
	 * The value returned, if any, is assumed to be the schema negotiation value to be set in the content-profile HTTP Header of
	 * requests that have a payload as part of the request.
	 * 
	 * @return See desc.
	 */
	public SchemaInfo getRequestDMSchemaInfo()
	{
	    if (getConsumerEnvironment().isSchemaNegotiationEnabled())
	    {
	        return getConsumerEnvironment().getDataModelSchemaInfo();
	    }
	    else
	    {
	        return null;
	    }
	}
	
    /**
     * If the property env.schema.enabled in the consumer's property file is set to true then this method returns the SchemaInfo 
     * made up of the following properties in the consumer's property file:<br/>
     * - env.schema.dm.domain <br/>
     * - env.schema.dm.version <br/>
     * - env.schema.dm.json.type <br/><br/>
     *  
     * If the env.schema.enabled in the consumer's property file is set to false then null is returned as it is assumed that schema
     * negotiation is not enabled for the given consumer.<br/><br/>
     * 
     * The value returned, if any, is assumed to be the schema negotiation value to be set in the accept-profile HTTP Header of
     * requests that have a payload in the response.
     * 
     * @return See desc.
     */
    public SchemaInfo getResponseDMSchemaInfo()
    {
        return getRequestDMSchemaInfo();
    }

	/*------------------------------------------------------------------------------------------------------------------------
	 * End of 'Dynamic' HTTP Header Field override section
	 *-----------------------------------------------------------------------------------------------------------------------*/ 

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
/*
        if (!initOK)
	    {
	      logger.error("Consumer not initialised properly. See previous error log entries.");
	      return null;
	    }
*/
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
/*
	    if (!initOK)
	    {
	      logger.error("Consumer not initialised properly. See previous error log entries.");
	      return null;
	    }
*/
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
/*        
	    if (!initOK)
	    {
	      logger.error("Consumer not initialised properly. See previous error log entries.");
	      return null;
	    }
*/
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
/*        
		if (!initOK)
		{
			logger.error("Consumer not initialised properly. See previous error log entries.");
			return null;
		}
*/
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
/*        
		if (!initOK)
		{
			logger.error("Consumer not initialised properly. See previous error log entries.");
			return null;
		}
*/
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
/*        
		if (!initOK)
		{
			logger.error("Consumer not initialised properly. See previous error log entries.");
			return null;
		}
*/
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
/*        
		if (!initOK)
		{
			logger.error("Consumer not initialised properly. See previous error log entries.");
			return null;
		}
*/
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
/*
        if (!initOK)
        {
          logger.error("Consumer not initialised properly. See previous error log entries.");
          return null;
        }
*/
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
/*        
		if (!initOK)
		{
			logger.error("Consumer not initialised properly. See previous error log entries.");
			return null;
		}
*/
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
/*
        if (!initOK)
		{
			logger.error("Consumer not initialised properly. See previous error log entries.");
			return null;
		}
*/
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
	
	/*------------------------------------------------------------------------------------*/
    /*-- Methods for Local Queues and Threads for Delayed Response and Event management --*/
    /*------------------------------------------------------------------------------------*/

	/**
     * Only creates the local consumer queue if it doesn't already exist. The queue (new or existing) is then returned.
     * 
     * @return See desc.
     */
//    public final LocalConsumerQueue createLocalConsumerQueue()
//    {
//        if (getLocalConsumerQueue() == null)
//        {
//            // Create local queue with the capacity indicated with the consumer config
//            logger.debug("Create Local Queue for "+getConsumerName());
//
//            // Use the local queue as a trigger of threads rather than actual queueing of messages. Use 1 as the minimum
//            setLocalConsumerQueue(new LocalConsumerQueue(1, getClass().getSimpleName() + "LocalQueue", getClass().getSimpleName()));
//            startListenerThreads();
//        }
//        return getLocalConsumerQueue();
//    }

    /*
     * Will initialise the threads and add them to the local consumer queue.
     */
//    private void startListenerThreads()
//    {
//        // Start up all consumers for this subscriber.
//        int numThreads = getNumOfConsumerThreads();
//        logger.debug("Start "+numThreads+" "+getConsumerName()+" threads.");
//        logger.debug("Total number of threads before starting Local Queue for "+getConsumerName()+" "+Thread.activeCount());
//        service = Executors.newFixedThreadPool(numThreads);
//        for (int i = 0; i < numThreads; i++)
//        {
//            String consumerID = getConsumerName()+" "+(i+1);
//            logger.debug("Start Consumer "+consumerID);
//            LocalMessageConsumer consumer = new LocalMessageConsumer(getLocalConsumerQueue(), consumerID, this);
//            service.execute(consumer);
//        }
//        logger.debug(numThreads+" "+getConsumerName()+" initilaised and started.");
//        logger.debug("Total number of threads after starting Local Queue for "+getConsumerName()+" "+Thread.activeCount());
//    }

//    private final int getNumOfConsumerThreads()
//    {
//        return getServiceProperties().getPropertyAsInt("consumer.local.workerThread", getClass().getSimpleName(), 1);
//    }

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

//		SIF3Session sif3Session = ConsumerEnvironmentManager.getInstance().getSIF3Session();
//		List<ServiceInfo> allServices = new ArrayList<ServiceInfo>(); 
//		
//		// Get OBJECT Services
//		List<ServiceInfo> services = sif3Session.getServiceInfoForService(getMultiObjectClassInfo().getObjectName(), ServiceType.OBJECT);
//		for (ServiceInfo serviceInfo : services)
//		{
//			if (serviceInfo.getRights().hasRight(AccessRight.CREATE, AccessType.APPROVED) ||
//			    serviceInfo.getRights().hasRight(AccessRight.UPDATE, AccessType.APPROVED) ||
//			    serviceInfo.getRights().hasRight(AccessRight.DELETE, AccessType.APPROVED) ||
//			    serviceInfo.getRights().hasRight(AccessRight.QUERY, AccessType.APPROVED) )
//			{
//				allServices.add(serviceInfo);
//			}
//		}
//		
//		// Now get SERVICEPATHs. They are only valid for QUERY permissions. No events or other types.
//		allServices.addAll(sif3Session.getServiceInfoForService(getMultiObjectClassInfo().getObjectName(), ServiceType.SERVICEPATH, AccessRight.QUERY, AccessType.APPROVED));
		
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
	                   				   new PayloadMetadata(getRequestMediaType(), getRequestDMSchemaInfo()),
	                   				   new PayloadMetadata(getResponseMediaType(), getResponseDMSchemaInfo()),
	                   				   getMarshaller(), 
	                   				   getUnmarshaller(),
	                   				   envInfo.getSecureConnection(),
	                   				   getCompressionEnabled());
		}
	}
		
//	private SIF3Session getSIF3Session()
//	{
//	  return ConsumerEnvironmentManager.getInstance().getSIF3Session();
//	}

//	private HeaderProperties getHeaderProperties(boolean isCreateOperation, HeaderValues.ServiceType serviceType, CustomParameters customParameters) 
//	{
//	   HeaderProperties hdrProps = new HeaderProperties();
//	   
//	   // First we add all Custom HTTP Headers. We add SIF defined HTTP header later. This will also ensure that we
//	   // will override custom properties with SIF defined properties.
//	   if ((customParameters != null) && (customParameters.getHttpHeaderParams() != null))
//	   {
//		   hdrProps = customParameters.getHttpHeaderParams();
//	   }
//	    
//	   // Now we set SIF defined HTTP headers...
//	   	    
//	   // Set the remaining header fields for this type of request
//	   if (isCreateOperation)
//	   {
//	      hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_ADVISORY, (getMustUseAdvisory() ? "true" : "false"));
//	   }
//	   hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, serviceType.name());
//	   
//	   // Set values of consumer property file or their overridden value. Note thsetHeaderProperty() method will do the check
//	   // for null, so no need to do this here.
//	   hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_APPLICATION_KEY, getApplicationKey());
//	   hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTHENTICATED_USER, getAuthentictedUser());
//	   hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_GENERATOR_ID, getGeneratorID());
//	   	    
//	   return hdrProps;
//	}
	
	private HeaderProperties getHeaderProperties(boolean isCreateOperation, CustomParameters customParameters)
	{
	  return getHeaderProperties(isCreateOperation, ServiceType.OBJECT, customParameters);
	}
	
//	private void setErrorDetails(BaseResponse response, ErrorDetails errorDetails)
//	{
//		response.setStatus(errorDetails.getErrorCode());
//		response.setStatusMessage(errorDetails.getMessage());
//		response.setError(errorDetails);
//		response.setContentLength(0);
//		response.setHasEntity(false);
//	}

	/*
	 * Will perform hasAccess() and requestTypeSupported() checks. This is a convenience method, so that not each operation has to
	 * call the two methods sequentially and manage all the flow.
	 */
	private ErrorDetails allClientChecks(AccessRight right, AccessType accessType, SIFZone zone, SIFContext context, RequestType requestType) 
	{
	  return allClientChecks(getMultiObjectClassInfo().getObjectName(), null, right, accessType, zone, context, requestType);
	}
	
//	private ErrorDetails allClientChecks(String serviceName, AccessRight right, AccessType accessType, SIFZone zone, SIFContext context, RequestType requestType)
//	{
//		ErrorDetails error = hasAccess(serviceName, right, accessType, zone, context);
//		if ((error == null) && (requestType != null))
//		{
//			error = requestTypeEnabled(requestType);
//		}	
//		return error;
//	}
		
//	private ErrorDetails hasAccess(String serviceName, AccessRight right, AccessType accessType, SIFZone zone, SIFContext context)
//	{
//		ErrorDetails error = null;
//		if (checkACL)
//		{
//			if (!getSIF3Session().hasAccess(right, accessType, serviceName, null, zone, context))
//			{
//				String zoneID = (zone == null) ? "Default" : zone.getId();
//				String contextID = (context == null) ? "Default" : context.getId();
//				error = new ErrorDetails(Status.FORBIDDEN.getStatusCode(), "Consumer is not authorized to issue the requested operation.", right.name()+ " access is not set to "+accessType.name()+" for the service " + serviceName +" and the given zone ("+zoneID+") and context ("+contextID+") in the environment "+getSIF3Session().getEnvironmentName(), "Client side check.");			
//			}
//		}
//		return error;
//	}
	
//	private ErrorDetails requestTypeEnabled(RequestType requestType)
//	{
//		ErrorDetails error = null;
//		
//		if ((requestType == RequestType.DELAYED) && (!getConsumerEnvironment().getDelayedEnabled()))
//		{
//			error = new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Client side Check: DELAYED requests are not enabled.");
//		}
//		return error;
//	}

//	private Response createErrorResponse(ErrorDetails error)
//	{
//		Response response = new Response();
//		setErrorDetails(response, error);
//		return response;	
//	}
	
//	private BulkOperationResponse<CreateOperationStatus> makeBulkErrorResponseForCreates(ErrorDetails error)
//	{
//		BulkOperationResponse<CreateOperationStatus> response = new BulkOperationResponse<CreateOperationStatus>();
//		setErrorDetails(response, error);
//		return response;	
//	}
//	
//	private BulkOperationResponse<OperationStatus> makeBulkErrorResponse(ErrorDetails error)
//	{
//		BulkOperationResponse<OperationStatus> response = new BulkOperationResponse<OperationStatus>();
//		setErrorDetails(response, error);
//		return response;	
//	}
	
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
	
//    private List<ZoneContextInfo> getFinalZoneCtxList( List<ZoneContextInfo> zoneCtxList, SIF3Session sif3Session)
//	{
//		List<ZoneContextInfo> finalZoneContextList = null;
//		
//		if (zoneCtxList == null)
//		{
//			finalZoneContextList = new ArrayList<ZoneContextInfo>();
//		}
//		else
//		{
//			finalZoneContextList = zoneCtxList;
//		}
//		
//		if (finalZoneContextList.size() == 0) //add default context and zone
//		{
//		    // Set zone and context to null which will ensure that the matrix params won't be set and therefore the provider will assume default context & zone
//            finalZoneContextList.add(new ZoneContextInfo((SIFZone)null, (SIFContext)null));
//		}
//		
//		// Check all entries and if 'null' is used as zone or context then we assign the default.
//		for (ZoneContextInfo zoneCtxInfo : finalZoneContextList)
//		{
//			// If zone or zone ID is null then we set the default zone.
//			if ((zoneCtxInfo.getZone() == null) || StringUtils.isEmpty(zoneCtxInfo.getZone().getId()))
//			{
//                zoneCtxInfo.setZone(null); // won't set matrix parameter which means default zone
//			}
//			// If context or context ID is null then we set the default zone.
//			if ((zoneCtxInfo.getContext() == null) || StringUtils.isEmpty(zoneCtxInfo.getContext().getId()))
//			{
//				zoneCtxInfo.setContext(null); // won't set matrix parameter which means default context
//			}
//		}
//		
//		return finalZoneContextList;
//	}
	
	/*
	 * This method sets the remaining properties in the receipt for delayed responses. There are a few fields that cannot be set at the ObjectServiceClient as
	 * they are not known or cannot be determined in there but are well known in the abstract consumer.
	 */
//	private void finaliseDelayedReceipt(DelayedRequestReceipt delayedReceipt, String serviceName, ServiceType serviceType, ResponseAction requestedAction)
//	{
//		if (delayedReceipt != null)
//		{
//            //delayedReceipt.setRequestDate(requestDate);
//            delayedReceipt.setServiceName(serviceName);
//            delayedReceipt.setServiceType(serviceType);
//            delayedReceipt.setRequestedAction(requestedAction);
//		}
//	}
	

//	private void nullMethodCheck(Object objectToCheck, String methodName) throws IllegalArgumentException
//	{
//    	if (objectToCheck == null)
//        {
//            throw new IllegalArgumentException(methodName+" method not implemented correctly. Returns null which is invalid.");
//        }
//	}

	
    /*
     * This method checks if the test.testmode in the consumer's property file is set to TRUE.
     */
//    @SuppressWarnings("unused")
//    private boolean isTestMode()
//    {
//        if (testMode == null)
//        {
//            AdvancedProperties props = getServiceProperties();
//            testMode = props.getPropertyAsBool("test.testmode", false);
//        }
//        return testMode;
//    }

}
