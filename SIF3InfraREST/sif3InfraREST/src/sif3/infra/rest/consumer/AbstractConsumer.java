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

import org.apache.log4j.Logger;

import sif3.common.consumer.Consumer;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.TransportHeaderProperties;
import sif3.common.model.EnvironmentZoneContextInfo;
import sif3.common.model.PagingInfo;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.Response;
import sif3.infra.common.env.EnvironmentStore;
import sif3.infra.common.env.types.ConsumerEnvironment;
import sif3.infra.rest.client.ClientInterface;
import sif3.infra.rest.env.ConsumerEnvironmentManager;
import sif3.infra.rest.header.RESTHeaderProperties;
import sif3.infra.rest.header.RequestHeaderConstants;
import au.com.systemic.framework.utils.StringUtils;
import au.com.systemic.framework.utils.Timer;

/**
 * This is the core class that a developer will use to implement their consumers. Each consumer for each object type MUST extends this
 * class. It forms the link between the high level consumer implementation and the low level infrastructure functions which this class
 * abstracts.<br/>
 * It forms the link between the SIF3 functions/infrastructure and the data model that shall be transported over the SIF3 
 * Infrastructure. The marshal and unmarshal factories required by this class as well as the two core abstract methods
 * getSingleObjectClassInfo() and getMultiObjectClassInfo() are the key to link between data model and infrastructure.
 * 
 * @author Joerg Huber
 */
public abstract class AbstractConsumer implements Consumer
{
	protected final Logger logger = Logger.getLogger(getClass());

	private MarshalFactory marshaller = null;
	private UnmarshalFactory unmarshaller = null;
	private String serviceID = null;
	private ConsumerEnvironmentManager envMgr = null;
	
	/**
	 * Provides information about the Single Object Type to be dealt with in this consumer.
	 * 
	 * @return See desc.
	 */
	public abstract ModelObjectInfo getSingleObjectClassInfo();
	
	/**
	 * Provides information about the 'collection-style' Object Type to be dealt with in this consumer. It is mainly used
	 * in Bulk operations.
	 * 
	 * @return See desc.
	 */
	public abstract ModelObjectInfo getMultiObjectClassInfo();
	
	/**
	 * This method is called when a consumer service is shut down. It can be used to free up internally allocated resources
	 * as well as clean-up other things.
	 */
	public abstract void shutdown();
	
	/**
	 * Return TRUE if environments shall be removed/deleted. Return FALSE if environments shall be kept and only disconnect. Note if
	 * the environment is removed all data that might be queued with that environment will also be removed/lost. This method must be
	 * used with care.
	 * 
	 * @return see desc.
	 */
	public abstract boolean removeEnvironments();

	/**
	 * This method initialises a consumer. This includes but is not limited to reading the property file applicable for the consumer
	 * and connecting to all environments this consumer needs to interact with. It also makes the consumer aware of the data model
	 * it will deal with through the marshaller and unmarshaller provided to this constructor.
	 * 
	 * @param serviceID is the same for all consumers that belong to a service. This is the 'adapter id' and not the id belonging to 
	 *                  a specific consumer and object type.
	 * @param marshaller Marshaller applicable for the Data Model this consumer deals with.
	 * @param unmarshaller Unmarshaller applicable for the Data Model this consumer deals with.
	 */
	public AbstractConsumer(String serviceID, MarshalFactory marshaller, UnmarshalFactory unmarshaller)
	{
		super();
		setServiceID(serviceID);
		setMarshaller(marshaller);
		setUnmarshaller(unmarshaller);
		envMgr = ConsumerEnvironmentManager.getInstance(EnvironmentStore.getInstance(serviceID));
		
		// connect to known environments
		envMgr.connectAll();
	}
	
	public MarshalFactory getMarshaller()
    {
    	return this.marshaller;
    }

	public void setMarshaller(MarshalFactory marshaller)
    {
    	this.marshaller = marshaller;
    }

	public UnmarshalFactory getUnmarshaller()
    {
    	return this.unmarshaller;
    }

	public void setUnmarshaller(UnmarshalFactory unmarshaller)
    {
    	this.unmarshaller = unmarshaller;
    }

	public String getServcieID()
    {
    	return this.serviceID;
    }

	public void setServiceID(String serviceID)
    {
    	this.serviceID = serviceID;
    }

	/*-----------------------*/
	/*-- Create Operations --*/
	/*-----------------------*/
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#createMany(java.lang.Object, java.util.List)
	 */
	@Override
	public List<BulkOperationResponse> createMany(Object data, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		Timer timer = new Timer();
		timer.start();
		List<BulkOperationResponse> responses = new ArrayList<BulkOperationResponse>();
		
		// We must call the create for each environment we are connected to and that supports this object type
		for (ConsumerEnvironment env : envMgr.getConnectedEnvironments())
		{
			// List is null or empty which means we perform action in default Zone/Context
			if ((envZoneCtxList == null) || (envZoneCtxList.size() == 0)) 
			{
				responses.add(getClient(env).createMany(getMultiObjectClassInfo().getObjectName(), data, getHeaderProperties(env), null, null));
			}
			else // Only perform action where environment matches current environment
			{
				for (EnvironmentZoneContextInfo envZoneCtx : envZoneCtxList)
				{
					// The environment name must be set. Constructor enforces it.
					if (validZoneCtxForEnv(env, envZoneCtx))
					{
						responses.add(getClient(env).createMany(getMultiObjectClassInfo().getObjectName(), data, getHeaderProperties(env), envZoneCtx.getZone(), envZoneCtx.getContext()));
					}
				}					
			}
		}
		timer.finish();
		logger.debug("Time taken to call and process 'createMany' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken());
		return responses;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#createSingle(java.lang.Object, java.util.List)
	 */
	@Override
	public List<Response> createSingle(Object data, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		Timer timer = new Timer();
		timer.start();
		List<Response> responses = new ArrayList<Response>();
		
		// We must call the create for each environment we are connected to and that supports this object type
		for (ConsumerEnvironment env : envMgr.getConnectedEnvironments())
		{
			// List is null or empty which means we perform action in default Zone/Context
			if ((envZoneCtxList == null) || (envZoneCtxList.size() == 0)) 
			{
				responses.add(getClient(env).createSingle(getMultiObjectClassInfo().getObjectName()+"/"+getSingleObjectClassInfo().getObjectName(), data, getHeaderProperties(env), getSingleObjectClassInfo().getObjectType(), null, null));
			}
			else // Only perform action where environment matches current environment
			{
				for (EnvironmentZoneContextInfo envZoneCtx : envZoneCtxList)
				{
					// The environment name must be set. Constructor enforces it.
					if (validZoneCtxForEnv(env, envZoneCtx))
					{
						responses.add(getClient(env).createSingle(getMultiObjectClassInfo().getObjectName()+"/"+getSingleObjectClassInfo().getObjectName(), data, getHeaderProperties(env), getSingleObjectClassInfo().getObjectType(), envZoneCtx.getZone(), envZoneCtx.getContext()));
					}
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
	public List<BulkOperationResponse> deleteMany(List<String> resourceIDs, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		Timer timer = new Timer();
		timer.start();
		List<BulkOperationResponse> responses = new ArrayList<BulkOperationResponse>();
		
		// We must call the create for each environment we are connected to and that supports this object type
		for (ConsumerEnvironment env : envMgr.getConnectedEnvironments())
		{
			// List is null or empty which means we perform action in default Zone/Context
			if ((envZoneCtxList == null) || (envZoneCtxList.size() == 0)) 
			{
				responses.add(getClient(env).removeMany(getMultiObjectClassInfo().getObjectName(), resourceIDs, getHeaderProperties(env), null, null));
			}
			else // Only perform action where environment matches current environment
			{
				for (EnvironmentZoneContextInfo envZoneCtx : envZoneCtxList)
				{
					// The environment name must be set. Constructor enforces it.
					if (validZoneCtxForEnv(env, envZoneCtx))
					{
						responses.add(getClient(env).removeMany(getMultiObjectClassInfo().getObjectName(), resourceIDs, getHeaderProperties(env), envZoneCtx.getZone(), envZoneCtx.getContext()));
					}
				}					
			}
		}
		timer.finish();
		logger.debug("Time taken to call and process 'removeMany' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken());
		return responses;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#deleteSingle(java.lang.String, java.util.List)
	 */
	@Override
	public List<Response> deleteSingle(String resourceID, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		Timer timer = new Timer();
		timer.start();
		List<Response> responses = new ArrayList<Response>();
		
		// We must call the remove for each environment we are connected to and that supports this object type
		for (ConsumerEnvironment env : envMgr.getConnectedEnvironments())
		{
			// List is null or empty which means we perform action in default Zone/Context
			if ((envZoneCtxList == null) || (envZoneCtxList.size() == 0)) 
			{
				responses.add(getClient(env).removeSingle(getMultiObjectClassInfo().getObjectName(), resourceID, getHeaderProperties(env), null, null));
			}
			else // Only perform action where environment matches current environment
			{
				for (EnvironmentZoneContextInfo envZoneCtx : envZoneCtxList)
				{
					// The environment name must be set. Constructor enforces it.
					if (validZoneCtxForEnv(env, envZoneCtx))
					{
						responses.add(getClient(env).removeSingle(getMultiObjectClassInfo().getObjectName(), resourceID, getHeaderProperties(env), envZoneCtx.getZone(), envZoneCtx.getContext()));
					}
				}					
			}
		}
		timer.finish();
		logger.debug("Time taken to call and process 'delete by primary key' for "+getMultiObjectClassInfo().getObjectName()+"/"+resourceID+": "+timer.timeTaken());
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
	public List<Response> retrievByPrimaryKey(String resourceID, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		Timer timer = new Timer();
		timer.start();
		List<Response> responses = new ArrayList<Response>();
		
		// We must call the get for each environment we are connected to and that supports this object type
		for (ConsumerEnvironment env : envMgr.getConnectedEnvironments())
		{
			// List is null or empty which means we perform action in default Zone/Context
			if ((envZoneCtxList == null) || (envZoneCtxList.size() == 0)) 
			{
				responses.add(getClient(env).getSingle(getMultiObjectClassInfo().getObjectName(), resourceID, getHeaderProperties(env), getSingleObjectClassInfo().getObjectType(), null, null));
			}
			else // Only perform action where environment matches current environment
			{
				for (EnvironmentZoneContextInfo envZoneCtx : envZoneCtxList)
				{
					// The environment name must be set. Constructor enforces it.
					if (validZoneCtxForEnv(env, envZoneCtx))
					{
						responses.add(getClient(env).getSingle(getMultiObjectClassInfo().getObjectName(), resourceID, getHeaderProperties(env), getSingleObjectClassInfo().getObjectType(), envZoneCtx.getZone(), envZoneCtx.getContext()));
					}
				}					
			}
		}
		timer.finish();
		logger.debug("Time taken to call and process 'retrieve by primary key' for "+getSingleObjectClassInfo().getObjectName()+"/"+resourceID+": "+timer.timeTaken());
		return responses;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#retrieve(sif3.common.model.PagingInfo, java.util.List)
	 */
	@Override
	public List<Response> retrieve(PagingInfo pagingInfo, List<EnvironmentZoneContextInfo> envZoneCtxList) throws PersistenceException, UnsupportedQueryException, ServiceInvokationException
	{
		Timer timer = new Timer();
		timer.start();
		List<Response> responses = new ArrayList<Response>();
		
		// We must call the create for each environment we are connected to and that supports this object type
		for (ConsumerEnvironment env : envMgr.getConnectedEnvironments())
		{
			// List is null or empty which means we perform action in default Zone/Context
			if ((envZoneCtxList == null) || (envZoneCtxList.size() == 0)) 
			{
				responses.add(getClient(env).getMany(getMultiObjectClassInfo().getObjectName(), pagingInfo, getHeaderProperties(env), getMultiObjectClassInfo().getObjectType(), null, null));
			}
			else // Only perform action where environment matches current environment
			{
				for (EnvironmentZoneContextInfo envZoneCtx : envZoneCtxList)
				{
					// The environment name must be set. Constructor enforces it.
					if (validZoneCtxForEnv(env, envZoneCtx))
					{
						responses.add(getClient(env).getMany(getMultiObjectClassInfo().getObjectName(), pagingInfo, getHeaderProperties(env), getMultiObjectClassInfo().getObjectType(), envZoneCtx.getZone(), envZoneCtx.getContext()));
					}
				}					
			}
		}
		timer.finish();
		logger.debug("Time taken to call and process 'retrieve all' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken());
		return responses;
	}

	/*-----------------------*/
	/*-- Update Operations --*/
	/*-----------------------*/
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#updateMany(java.lang.Object, java.util.List)
	 */
	@Override
	public List<BulkOperationResponse> updateMany(Object data, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		Timer timer = new Timer();
		timer.start();
		List<BulkOperationResponse> responses = new ArrayList<BulkOperationResponse>();
		
		// We must call the create for each environment we are connected to and that supports this object type
		for (ConsumerEnvironment env : envMgr.getConnectedEnvironments())
		{
			// List is null or empty which means we perform action in default Zone/Context
			if ((envZoneCtxList == null) || (envZoneCtxList.size() == 0)) 
			{
				responses.add(getClient(env).updateMany(getMultiObjectClassInfo().getObjectName(), data, getHeaderProperties(env), null, null));
			}
			else // Only perform action where environment matches current environment
			{
				for (EnvironmentZoneContextInfo envZoneCtx : envZoneCtxList)
				{
					// The environment name must be set. Constructor enforces it.
					if (validZoneCtxForEnv(env, envZoneCtx))
					{
						responses.add(getClient(env).updateMany(getMultiObjectClassInfo().getObjectName(), data, getHeaderProperties(env), envZoneCtx.getZone(), envZoneCtx.getContext()));
					}
				}					
			}
		}
		timer.finish();
		logger.debug("Time taken to call and process 'updateMany' for "+getMultiObjectClassInfo().getObjectName()+": "+timer.timeTaken());
		return responses;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.consumer.Consumer#updateSingle(java.lang.Object, java.lang.String, java.util.List)
	 */
	@Override
	public List<Response> updateSingle(Object data, String resourceID, List<EnvironmentZoneContextInfo> envZoneCtxList) throws IllegalArgumentException, PersistenceException, ServiceInvokationException
	{
		Timer timer = new Timer();
		timer.start();
		List<Response> responses = new ArrayList<Response>();
		
		// We must call the update for each environment we are connected to and that supports this object type
		for (ConsumerEnvironment env : envMgr.getConnectedEnvironments())
		{
			// List is null or empty which means we perform action in default Zone/Context
			if ((envZoneCtxList == null) || (envZoneCtxList.size() == 0)) 
			{
				responses.add(getClient(env).updateSingle(getMultiObjectClassInfo().getObjectName(), resourceID, data, getHeaderProperties(env), null, null));
			}
			else // Only perform action where environment matches current environment
			{
				for (EnvironmentZoneContextInfo envZoneCtx : envZoneCtxList)
				{
					// The environment name must be set. Constructor enforces it.
					if (validZoneCtxForEnv(env, envZoneCtx))
					{
						responses.add(getClient(env).updateSingle(getMultiObjectClassInfo().getObjectName(), resourceID, data, getHeaderProperties(env), envZoneCtx.getZone(), envZoneCtx.getContext()));
					}
				}					
			}
		}
		timer.finish();
		logger.debug("Time taken to call and process 'update by primary key' for "+getMultiObjectClassInfo().getObjectName()+"/"+resourceID+": "+timer.timeTaken());
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
	    if (removeEnvironments())
	    {
	    	envMgr.disconnectAndRemoveAll();
	    }
	    else
	    {
	    	envMgr.disconnectAll();
	    }
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
	                   				   getUnmarshaller());
		}
	}
	
	private TransportHeaderProperties getHeaderProperties(ConsumerEnvironment envInfo)
	{
		TransportHeaderProperties hdrProps = new RESTHeaderProperties();
		hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, envInfo.getAuthenticationToken());
		hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_REQUEST_ID, UUIDGenerator.getUUID());
		
		return hdrProps;
	}
	
	private boolean validZoneCtxForEnv(ConsumerEnvironment env, EnvironmentZoneContextInfo envZoneCtx)
	{
		if (env.getEnvironmentName().equals(envZoneCtx.getEnvironmentName()))
		{
			if (((envZoneCtx.getZone() == null) || StringUtils.isEmpty(envZoneCtx.getZone().getId())) &&
				((envZoneCtx.getContext() == null) || StringUtils.isEmpty(envZoneCtx.getContext().getId())))
			{
				return true; // Default Zone and Context
			}
			else
			{
				//TODO: JH - Check if zone and context are listed in the given environment
				return true;
			}
		}
		return false;
	}

}
