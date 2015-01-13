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

import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.interfaces.Consumer;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.QueryPredicate;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.common.model.ZoneContextInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.common.ws.BaseResponse;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.rest.client.ClientInterface;
import sif3.infra.rest.client.ClientUtils;
import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.StringUtils;
import au.com.systemic.framework.utils.Timer;

/**
 * This is the core class that a developer will use to implement their consumers. Each consumer for each object type MUST extend this
 * class. It forms the link between the high level consumer implementation and the low level infrastructure functions which this class
 * abstracts.<br/>
 * It forms the link between the SIF3 functions/infrastructure and the data model that shall be transported over the SIF3 
 * Infrastructure. The marshal and unmarshal factories required by this class as well as the two core abstract methods
 * getSingleObjectClassInfo() and getMultiObjectClassInfo() are the key to link between data model and infrastructure.<br/>
 * It is assumed that the ConsumerLoader.initialise() method has been called before any methods of this class are called.If not then 
 * the behaviour of this class is not defined. In fact each call to any method of this class will first test if initialisation has 
 * succeeded and no action in any of the top level methods will be executed if the ConsumerLoader.initialise() hasn't been called before.
 * 
 * @author Joerg Huber
 */
public abstract class AbstractConsumer implements Consumer
{
	protected final Logger logger = Logger.getLogger(getClass());

	private boolean checkACL = true;
	private boolean initOK = true;
	
	/**
	 * This method is called when a consumer service is shut down. It can be used to free up internally allocated resources
	 * as well as clean-up other things.
	 */
	public abstract void shutdown();

	/**
	 * Constructor.
	 */
	public AbstractConsumer()
	{
		super();
		
		// Set some properties at this stage for simplicity reasons.
		checkACL = getConsumerEnvironment().getCheckACL();
		
		//Check a few things to ensure that all core methods are implemented.
		if (getMarshaller() == null)
		{
			logger.error("Consumer "+getConsumerName()+" has not implemented the getMarshaller() method properly. It returns null which is not valid.");
			initOK = false;
		}
		if (getUnmarshaller() == null)
		{
			logger.error("Consumer "+getConsumerName()+" has not implemented the getUnmarshaller() method properly. It returns null which is not valid.");
			initOK = false;
		}
		if (getSingleObjectClassInfo() == null)
		{
			logger.error("Consumer "+getConsumerName()+" has not implemented the getSingleObjectClassInfo() method properly. It returns null which is not valid.");
			initOK = false;			
		}
		if (getMultiObjectClassInfo() == null)
		{
			logger.error("Consumer "+getConsumerName()+" has not implemented the getMultiObjectClassInfo() method properly. It returns null which is not valid.");
			initOK = false;			
		}
	}

	/**
	 * Utility method to easily retrieve the consumer environment configuration.
	 * 
	 * @return See desc
	 */
	public ConsumerEnvironment getConsumerEnvironment()
	{
	  return (ConsumerEnvironment)ConsumerEnvironmentManager.getInstance().getEnvironmentInfo();
	}
	
  /**
   * Utility method to easily retrieve the property file content for a consumer.
   * 
   * @return See desc
   */
	public AdvancedProperties getServiceProperties()
	{
	  return ConsumerEnvironmentManager.getInstance().getServiceProperties();
	}
	
	/**
	 * This method returns the value of the adapter.generator.id property from the consumer's property file. If that
	 * needs to be overridden by a specific implementation then the specific sub-class should override this method.
	 * 
	 * @return The adapter.generator.id property from the consumer's property file
	 */
	public String getGeneratorID()
	{
		return getConsumerEnvironment().getGeneratorID();
	}

	/**
	 * @return Returns the actual Class Name of this consumer
	 */
	public String getConsumerName()
	{
		return getClass().getSimpleName();
	}
	
	/**
	 * @return Returns the Service Name.
	 */
	public String getServiceName()
	{
		return getMultiObjectClassInfo().getObjectName();
	}
	
    /**
     * Utility method. Mainly used for useful logging messages.
     * 
     * @return Returns the Adapter Name as defined in the adapter.id property of the consumer property file concatenated with the 
     *         Consumer Name (class name)
     */
    public String getPrettyName()
    {
    	return getConsumerEnvironment().getAdapterName()+" - " + getConsumerName();
    }

	/*-----------------------*/
	/*-- Create Operations --*/
	/*-----------------------*/
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#createMany(java.lang.Object, java.util.List)
	 */
	@Override
	public List<BulkOperationResponse<CreateOperationStatus>> createMany(Object data, List<ZoneContextInfo> zoneCtxList, RequestType requestType) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		if (!initOK)
	  	{
			logger.error("Consumer not initialsied properly. See previous error log entries.");
			return null;
	  	}

		Timer timer = new Timer();
		timer.start();
		List<BulkOperationResponse<CreateOperationStatus>> responses = new ArrayList<BulkOperationResponse<CreateOperationStatus>>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}

		// List is null or empty which means we attempt to perform action in default Zone/Context of each environment
		if ((zoneCtxList == null) || (zoneCtxList.size() == 0)) 
		{
			ErrorDetails error = allClientChecks(AccessRight.CREATE, AccessType.APPROVED, null, null, requestType);
			if (error == null) //all good
			{
				responses.add(getClient(getConsumerEnvironment()).createMany(getMultiObjectClassInfo().getObjectName(), data, getHeaderProperties(getConsumerEnvironment(), true, requestType), null, null));
			}
			else  //pretend to have received a 'fake' error Response
			{
				responses.add(makeBulkErrorResponseForCreates(error));
			}
		}
		else // Only perform action where environment matches current environment
		{
			for (ZoneContextInfo zoneCtx : zoneCtxList)
			{
				ErrorDetails error = allClientChecks(AccessRight.CREATE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
				if (error == null) //all good
				{
					responses.add(getClient(getConsumerEnvironment()).createMany(getMultiObjectClassInfo().getObjectName(), data, getHeaderProperties(getConsumerEnvironment(), true, requestType), zoneCtx.getZone(), zoneCtx.getContext()));
				}
				else //pretend to have received a 'fake' error Response
				{
					responses.add(makeBulkErrorResponseForCreates(error));
				}
			}					
		}
		timer.finish();
		logger.debug("Time taken to call and process 'createMany' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#createSingle(java.lang.Object, java.util.List)
	 */
	@Override
	public List<Response> createSingle(Object data, List<ZoneContextInfo> zoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
    if (!initOK)
    {
      logger.error("Consumer not initialsied properly. See previous error log entries.");
      return null;
    }

    Timer timer = new Timer();
		timer.start();
		List<Response> responses = new ArrayList<Response>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}
		// List is null or empty which means we perform action in default Zone/Context
		if ((zoneCtxList == null) || (zoneCtxList.size() == 0)) 
		{
			ErrorDetails error = hasAccess(AccessRight.CREATE, AccessType.APPROVED, null, null);
			if (error == null) //all good
			{
				responses.add(getClient(getConsumerEnvironment()).createSingle(getMultiObjectClassInfo().getObjectName()+"/"+getSingleObjectClassInfo().getObjectName(), data, getHeaderProperties(getConsumerEnvironment(), true, RequestType.IMMEDIATE), getSingleObjectClassInfo().getObjectType(), null, null));
			}
			else  //pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}
		else // Only perform action where environment matches current environment
		{
			for (ZoneContextInfo zoneCtx : zoneCtxList)
			{
				ErrorDetails error = hasAccess(AccessRight.CREATE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext());
				if (error == null) //all good
				{
					responses.add(getClient(getConsumerEnvironment()).createSingle(getMultiObjectClassInfo().getObjectName()+"/"+getSingleObjectClassInfo().getObjectName(), data, getHeaderProperties(getConsumerEnvironment(), true, RequestType.IMMEDIATE), getSingleObjectClassInfo().getObjectType(), zoneCtx.getZone(), zoneCtx.getContext()));
				}
				else //pretend to have received a 'fake' error Response
				{
					responses.add(createErrorResponse(error));
				}
			}					
		}
		timer.finish();
		logger.debug("Time taken to call and process 'createSingle' for "+getSingleObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*-----------------------*/
	/*-- Delete Operations --*/
	/*-----------------------*/

	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#deleteMany(java.lang.Object, java.util.List)
	 */
	@Override
	public List<BulkOperationResponse<OperationStatus>> deleteMany(List<String> resourceIDs, List<ZoneContextInfo> zoneCtxList, RequestType requestType) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
    if (!initOK)
    {
      logger.error("Consumer not initialsied properly. See previous error log entries.");
      return null;
    }

    Timer timer = new Timer();
		timer.start();
		List<BulkOperationResponse<OperationStatus>> responses = new ArrayList<BulkOperationResponse<OperationStatus>>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}

		// List is null or empty which means we perform action in default Zone/Context
		if ((zoneCtxList == null) || (zoneCtxList.size() == 0)) 
		{
			ErrorDetails error = allClientChecks(AccessRight.DELETE, AccessType.APPROVED, null, null, requestType);
			if (error == null) //all good
			{
				responses.add(getClient(getConsumerEnvironment()).removeMany(getMultiObjectClassInfo().getObjectName(), resourceIDs, getHeaderProperties(getConsumerEnvironment(), false, requestType), null, null));
			}
			else  //pretend to have received a 'fake' error Response
			{
				responses.add(makeBulkErrorResponse(error));
			}
		}
		else // Only perform action where environment matches current environment
		{
			for (ZoneContextInfo zoneCtx : zoneCtxList)
			{
				ErrorDetails error = allClientChecks(AccessRight.DELETE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
				if (error == null) //all good
				{
					responses.add(getClient(getConsumerEnvironment()).removeMany(getMultiObjectClassInfo().getObjectName(), resourceIDs, getHeaderProperties(getConsumerEnvironment(), false, requestType), zoneCtx.getZone(), zoneCtx.getContext()));
				}
				else //pretend to have received a 'fake' error Response
				{
					responses.add(makeBulkErrorResponse(error));
				}
			}					
		}
		timer.finish();
		logger.debug("Time taken to call and process 'removeMany' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#deleteSingle(java.lang.String, java.util.List)
	 */
	@Override
	public List<Response> deleteSingle(String resourceID, List<ZoneContextInfo> zoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
    if (!initOK)
    {
      logger.error("Consumer not initialsied properly. See previous error log entries.");
      return null;
    }

    Timer timer = new Timer();
		timer.start();
		List<Response> responses = new ArrayList<Response>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}
		// List is null or empty which means we perform action in default Zone/Context
		if ((zoneCtxList == null) || (zoneCtxList.size() == 0)) 
		{
			ErrorDetails error = hasAccess(AccessRight.DELETE, AccessType.APPROVED, null, null);
			if (error == null) //all good
			{
				responses.add(getClient(getConsumerEnvironment()).removeSingle(getMultiObjectClassInfo().getObjectName(), resourceID, getHeaderProperties(getConsumerEnvironment(), false, RequestType.IMMEDIATE), null, null));
			}
			else  //pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}
		else // Only perform action where environment matches current environment
		{
			for (ZoneContextInfo zoneCtx : zoneCtxList)
			{
				ErrorDetails error = hasAccess(AccessRight.DELETE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext());
				if (error == null) //all good
				{
					responses.add(getClient(getConsumerEnvironment()).removeSingle(getMultiObjectClassInfo().getObjectName(), resourceID, getHeaderProperties(getConsumerEnvironment(), false, RequestType.IMMEDIATE), zoneCtx.getZone(), zoneCtx.getContext()));
				}
				else //pretend to have received a 'fake' error Response
				{
					responses.add(createErrorResponse(error));
				}
			}					
		}
		timer.finish();
		logger.debug("Time taken to call and process 'delete by primary key' for "+getMultiObjectClassInfo().getObjectName()+"/"+resourceID+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*-------------------------*/
	/*-- Retrieve Operations --*/
	/*-------------------------*/
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#retrievByPrimaryKey(java.lang.String, java.util.List)
	 */
	@Override
	public List<Response> retrievByPrimaryKey(String resourceID, List<ZoneContextInfo> zoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
    if (!initOK)
    {
      logger.error("Consumer not initialsied properly. See previous error log entries.");
      return null;
    }

    Timer timer = new Timer();
		timer.start();
		List<Response> responses = new ArrayList<Response>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}
		// List is null or empty which means we perform action in default Zone/Context
		if ((zoneCtxList == null) || (zoneCtxList.size() == 0)) 
		{
			ErrorDetails error = hasAccess(AccessRight.QUERY, AccessType.APPROVED, null, null);
			if (error == null) //all good
			{
				responses.add(getClient(getConsumerEnvironment()).getSingle(getMultiObjectClassInfo().getObjectName(), resourceID, getHeaderProperties(getConsumerEnvironment(), false, RequestType.IMMEDIATE), getSingleObjectClassInfo().getObjectType(), null, null));
			}
			else  //pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}
		else // Only perform action where environment matches current environment
		{
			for (ZoneContextInfo zoneCtx : zoneCtxList)
			{
				ErrorDetails error = hasAccess(AccessRight.QUERY, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext());
				if (error == null) //all good
				{
					responses.add(getClient(getConsumerEnvironment()).getSingle(getMultiObjectClassInfo().getObjectName(), resourceID, getHeaderProperties(getConsumerEnvironment(), false, RequestType.IMMEDIATE), getSingleObjectClassInfo().getObjectType(), zoneCtx.getZone(), zoneCtx.getContext()));
				}
				else //pretend to have received a 'fake' error Response
				{
					responses.add(createErrorResponse(error));
				}
			}					
		}
		timer.finish();
		logger.debug("Time taken to call and process 'retrieve by primary key' for "+getSingleObjectClassInfo().getObjectName()+"/"+resourceID+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#retrieve(sif3.common.model.PagingInfo, java.util.List)
	 */
	@Override
	public List<Response> retrieve(PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, RequestType requestType) throws PersistenceException, UnsupportedQueryException, ServiceInvokationException
	{
    if (!initOK)
    {
      logger.error("Consumer not initialsied properly. See previous error log entries.");
      return null;
    }

    Timer timer = new Timer();
		timer.start();
		List<Response> responses = new ArrayList<Response>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}
		// List is null or empty which means we perform action in default Zone/Context
		if ((zoneCtxList == null) || (zoneCtxList.size() == 0)) 
		{
			ErrorDetails error = allClientChecks(AccessRight.QUERY, AccessType.APPROVED, null, null, requestType);
			if (error == null)
			{
				error = requestTypeSupported(requestType);
			}
			if (error == null) //all good
			{
				responses.add(getClient(getConsumerEnvironment()).getMany(getMultiObjectClassInfo().getObjectName(), pagingInfo, getHeaderProperties(getConsumerEnvironment(), false, requestType), getMultiObjectClassInfo().getObjectType(), null, null));
			}
			else  //pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}
		else // Only perform action where environment matches current environment
		{
			for (ZoneContextInfo zoneCtx : zoneCtxList)
			{
				ErrorDetails error = allClientChecks(AccessRight.QUERY, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
				if (error == null) //all good
				{
					responses.add(getClient(getConsumerEnvironment()).getMany(getMultiObjectClassInfo().getObjectName(), pagingInfo, getHeaderProperties(getConsumerEnvironment(), false, requestType), getMultiObjectClassInfo().getObjectType(), zoneCtx.getZone(), zoneCtx.getContext()));
				}
				else //pretend to have received a 'fake' error Response
				{
					responses.add(createErrorResponse(error));
				}
			}					
		}
		timer.finish();
		logger.debug("Time taken to call and process 'retrieve all' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
		return responses;
	}
	
	 public List<Response> retrieveByServicePath(QueryCriteria queryCriteria, PagingInfo pagingInfo, List<ZoneContextInfo> zoneCtxList, RequestType requestType) throws PersistenceException, UnsupportedQueryException, ServiceInvokationException
	  {
	    if (!initOK)
	    {
	      logger.error("Consumer not initialsied properly. See previous error log entries.");
	      return null;
	    }

	    Timer timer = new Timer();
	    timer.start();
	    List<Response> responses = new ArrayList<Response>();
	    
	    if (!getConsumerEnvironment().getIsConnected())
	    {
	      logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
	      return responses;
	    }
	    // List is null or empty which means we perform action in default Zone/Context
	    if ((zoneCtxList == null) || (zoneCtxList.size() == 0)) 
	    {
	      ErrorDetails error = allClientChecks(getServiceName(queryCriteria), AccessRight.QUERY, AccessType.APPROVED, null, null, requestType);
	      if (error == null)
	      {
	        error = requestTypeSupported(requestType);
	      }
	      if (error == null) //all good
	      {
	        responses.add(getClient(getConsumerEnvironment()).getMany(getServicePath(queryCriteria), pagingInfo, getHeaderProperties(getConsumerEnvironment(), false, requestType, HeaderValues.ServiceType.SERVICEPATH), getMultiObjectClassInfo().getObjectType(), null, null));
	      }
	      else  //pretend to have received a 'fake' error Response
	      {
	        responses.add(createErrorResponse(error));
	      }
	    }
	    else // Only perform action where environment matches current environment
	    {
	      for (ZoneContextInfo zoneCtx : zoneCtxList)
	      {
	        ErrorDetails error = allClientChecks(getServiceName(queryCriteria), AccessRight.QUERY, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
	        if (error == null) //all good
	        {
	          responses.add(getClient(getConsumerEnvironment()).getMany(getServicePath(queryCriteria), pagingInfo, getHeaderProperties(getConsumerEnvironment(), false, requestType, HeaderValues.ServiceType.SERVICEPATH), getMultiObjectClassInfo().getObjectType(), zoneCtx.getZone(), zoneCtx.getContext()));
	        }
	        else //pretend to have received a 'fake' error Response
	        {
	          responses.add(createErrorResponse(error));
	        }
	      }         
	    }
	    timer.finish();
	    logger.debug("Time taken to call and process 'retrieve all' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
	    return responses;
	  }
	 
	 private String getServiceName(QueryCriteria queryCriteria) {
	   String result = null;
	   if (queryCriteria != null && queryCriteria.getPredicates() != null) {
	     result = "";
	     for (QueryPredicate predicate : queryCriteria.getPredicates()) {
	       result += predicate.getSubject() + "/{}/";
	     }
	     result += getMultiObjectClassInfo().getObjectName();
	   }
	   return result;
	 }
	 
   private String getServicePath(QueryCriteria queryCriteria) {
     String result = null;
     if (queryCriteria != null && queryCriteria.getPredicates() != null) {
       result = "";
       for (QueryPredicate predicate : queryCriteria.getPredicates()) {
         result += predicate.getSubject() + "/" + predicate.getValue() + "/";
       }
       result += getMultiObjectClassInfo().getObjectName();
     }
     return result;
   }

	/*-----------------------*/
	/*-- Update Operations --*/
	/*-----------------------*/
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#updateMany(java.lang.Object, java.util.List)
	 */
	@Override
	public List<BulkOperationResponse<OperationStatus>> updateMany(Object data, List<ZoneContextInfo> zoneCtxList, RequestType requestType) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
    if (!initOK)
    {
      logger.error("Consumer not initialsied properly. See previous error log entries.");
      return null;
    }

    Timer timer = new Timer();
		timer.start();
		List<BulkOperationResponse<OperationStatus>> responses = new ArrayList<BulkOperationResponse<OperationStatus>>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}
		// List is null or empty which means we perform action in default Zone/Context
		if ((zoneCtxList == null) || (zoneCtxList.size() == 0)) 
		{
			ErrorDetails error = allClientChecks(AccessRight.UPDATE, AccessType.APPROVED, null, null, requestType);
			if (error == null) //all good
			{
				responses.add(getClient(getConsumerEnvironment()).updateMany(getMultiObjectClassInfo().getObjectName(), data, getHeaderProperties(getConsumerEnvironment(), false, requestType), null, null));
			}
			else  //pretend to have received a 'fake' error Response
			{
				responses.add(makeBulkErrorResponse(error));
			}
		}
		else // Only perform action where environment matches current environment
		{
			for (ZoneContextInfo zoneCtx : zoneCtxList)
			{
				ErrorDetails error = allClientChecks(AccessRight.UPDATE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext(), requestType);
				if (error == null) //all good
				{
					responses.add(getClient(getConsumerEnvironment()).updateMany(getMultiObjectClassInfo().getObjectName(), data, getHeaderProperties(getConsumerEnvironment(), false, requestType), zoneCtx.getZone(), zoneCtx.getContext()));
				}
				else //pretend to have received a 'fake' error Response
				{
					responses.add(makeBulkErrorResponse(error));
				}
			}					
		}
		timer.finish();
		logger.debug("Time taken to call and process 'updateMany' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken()+"ms");
		return responses;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#updateSingle(java.lang.Object, java.lang.String, java.util.List)
	 */
	@Override
	public List<Response> updateSingle(Object data, String resourceID, List<ZoneContextInfo> zoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
    if (!initOK)
    {
      logger.error("Consumer not initialsied properly. See previous error log entries.");
      return null;
    }

    Timer timer = new Timer();
		timer.start();
		List<Response> responses = new ArrayList<Response>();
		
		if (!getConsumerEnvironment().getIsConnected())
		{
			logger.error("No connected environment for "+getConsumerEnvironment().getEnvironmentName()+". See previous erro log entries.");
			return responses;
		}
		// List is null or empty which means we perform action in default Zone/Context
		if ((zoneCtxList == null) || (zoneCtxList.size() == 0)) 
		{
			ErrorDetails error = hasAccess(AccessRight.UPDATE, AccessType.APPROVED, null, null);
			if (error == null) //all good
			{
				responses.add(getClient(getConsumerEnvironment()).updateSingle(getMultiObjectClassInfo().getObjectName(), resourceID, data, getHeaderProperties(getConsumerEnvironment(), false, RequestType.IMMEDIATE), null, null));
			}
			else  //pretend to have received a 'fake' error Response
			{
				responses.add(createErrorResponse(error));
			}
		}
		else // Only perform action where environment matches current environment
		{
			for (ZoneContextInfo zoneCtx : zoneCtxList)
			{
				ErrorDetails error = hasAccess(AccessRight.UPDATE, AccessType.APPROVED, zoneCtx.getZone(), zoneCtx.getContext());
				if (error == null) //all good
				{
					responses.add(getClient(getConsumerEnvironment()).updateSingle(getMultiObjectClassInfo().getObjectName(), resourceID, data, getHeaderProperties(getConsumerEnvironment(), false, RequestType.IMMEDIATE), zoneCtx.getZone(), zoneCtx.getContext()));
				}
				else //pretend to have received a 'fake' error Response
				{
					responses.add(createErrorResponse(error));
				}
			}					
		}
		timer.finish();
		logger.debug("Time taken to call and process 'update by primary key' for "+getMultiObjectClassInfo().getObjectName()+"/"+resourceID+": "+timer.timeTaken()+"ms");
		return responses;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#finalise()
	 */
	@Override
    public void finalise()
    {
	    shutdown();
    }

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private ClientInterface getClient(ConsumerEnvironment envInfo)
	{
		URI baseURI = envInfo.getConnectorBaseURI(ConsumerEnvironment.ConnectorName.requestsConnector);
		if (baseURI == null)
		{
			logger.error(ConsumerEnvironment.ConnectorName.requestsConnector.toString()+" not defined for environment "+envInfo.getEnvironmentName());
			return null;
		}
		else
		{
			return new ClientInterface(envInfo.getConnectorBaseURI(ConsumerEnvironment.ConnectorName.requestsConnector), 
	                   				   envInfo.getMediaType(),
	                   				   getMarshaller(), 
	                   				   getUnmarshaller(),
	                   				   envInfo.getSecureConnection());
		}
	}
		
	private SIF3Session getSIF3Session()
	{
	  return ConsumerEnvironmentManager.getInstance().getSIF3Session();
	}

	private HeaderProperties getHeaderProperties(ConsumerEnvironment envInfo, boolean isCreateOperation, RequestType requestType, HeaderValues.ServiceType serviceType) 
	{
	   HeaderProperties hdrProps = new HeaderProperties();
	    
	   // First create the properties for the authentication header.
	   ClientUtils.setAuthenticationHeader(hdrProps, envInfo.getAuthMethod(), getSIF3Session().getSessionToken(), getSIF3Session().getPassword());
	    
	   // Set the remaining header fields for this type of request
	   if (isCreateOperation)
	   {
	      hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_ADVISORY, (envInfo.getUseAdvisory() ? "true" : "false"));
	   }
	   hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, serviceType.name());
	   hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_REQUEST_TYPE, requestType.name());
	    
	   String generatorID = getGeneratorID();
	   if (StringUtils.notEmpty(generatorID))
	   {
	      hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_GENERATOR_ID, generatorID);
	   }
	    
	   return hdrProps;
	}
	
	private HeaderProperties getHeaderProperties(ConsumerEnvironment envInfo, boolean isCreateOperation, RequestType requestType)
	{
	  return getHeaderProperties(envInfo, isCreateOperation, requestType, HeaderValues.ServiceType.OBJECT);
	}
	
	private void setErrorDetails(BaseResponse response, ErrorDetails errorDetails)
	{
		response.setStatus(errorDetails.getErrorCode());
		response.setStatusMessage(errorDetails.getMessage());
		response.setError(errorDetails);
		response.setContentLength(0);
		response.setHasEntity(false);
	}

	/*
	 * Will perform hasAccess() and requestTypeSupported() checks. This is a convenience method, so that not each operation has to
	 * call the two methods sequentially and manage all the flow.
	 */
	private ErrorDetails allClientChecks(AccessRight right, AccessType accessType, SIFZone zone, SIFContext context, RequestType requestType) {
	  return allClientChecks(getMultiObjectClassInfo().getObjectName(), right, accessType, zone, context, requestType);
	}
	
	private ErrorDetails allClientChecks(String serviceName, AccessRight right, AccessType accessType, SIFZone zone, SIFContext context, RequestType requestType)
	{
		ErrorDetails error = hasAccess(serviceName, right, accessType, zone, context);
		if (error == null)
		{
			error = requestTypeSupported(requestType);
		}	
		return error;
	}
	
	private ErrorDetails hasAccess(AccessRight right, AccessType accessType, SIFZone zone, SIFContext context) {
	  return hasAccess(getMultiObjectClassInfo().getObjectName(), right, accessType, zone, context);
	}
	
	private ErrorDetails hasAccess(String serviceName, AccessRight right, AccessType accessType, SIFZone zone, SIFContext context)
	{
		ErrorDetails error = null;
		if (checkACL)
		{
			if (!getSIF3Session().hasAccess(right, accessType, serviceName, zone, context))
			{
				String zoneID = (zone == null) ? "Default" : zone.getId();
				String contextID = (context == null) ? "Default" : context.getId();
				error = new ErrorDetails(Status.UNAUTHORIZED.getStatusCode(), "Not authorized.", right.name()+ " access is not set to "+accessType.name()+" for the service " + serviceName +" and the given zone ("+zoneID+") and context ("+contextID+") in the environment "+getSIF3Session().getEnvironmentName(), "Client side check.");			
			}
		}
		return error;
	}
	
	private ErrorDetails requestTypeSupported(RequestType requestType)
	{
		ErrorDetails error = null;
		if (requestType == RequestType.DELAYED)
		{
			error = new ErrorDetails(Status.BAD_REQUEST.getStatusCode(), "Client side Check: DELAYED requests are not supported, yet.");
		}
		return error;
	}

	private Response createErrorResponse(ErrorDetails error)
	{
		Response response = new Response();
		setErrorDetails(response, error);
		return response;	
	}
	
	private BulkOperationResponse<CreateOperationStatus> makeBulkErrorResponseForCreates(ErrorDetails error)
	{
		BulkOperationResponse<CreateOperationStatus> response = new BulkOperationResponse<CreateOperationStatus>();
		setErrorDetails(response, error);
		return response;	
	}
	
	private BulkOperationResponse<OperationStatus> makeBulkErrorResponse(ErrorDetails error)
	{
		BulkOperationResponse<OperationStatus> response = new BulkOperationResponse<OperationStatus>();
		setErrorDetails(response, error);
		return response;	
	}
}
