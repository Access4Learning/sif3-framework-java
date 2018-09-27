/*
 * BaseProvider.java
 * Created: 01/10/2013
 *
 * Copyright 2013 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package sif3.infra.rest.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.common.CommonConstants;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIFException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.interfaces.EventProvider;
import sif3.common.interfaces.Provider;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;

/**
 * This is the main class each specific provider of a given SIF Object type must extends to implement the CRUD operation as defined
 * by the SIF3 specification. It is a base implementation of the request connector. It implements some common functionality each provider
 * may require. It also ensures that all components of a provider service are wired up correctly within the framework. Please refer to 
 * the Developer's Guide for some more details about this class.
 *
 * @author Joerg Huber
 */
public abstract class BaseProvider extends CoreProvider implements Provider
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * Shuts down the sub-class provider. This should release all associated resources with that provider.
     */
    public abstract void shutdown();


    /**
     */
    public BaseProvider()
    {
	    super();
	    
		//Check a few things to ensure that all core methods are implemented.
		if (getMarshaller() == null)
		{
			logger.error("Provider "+getProviderName()+" has not implemented the getMarshaller() method properly. It returns null which is not valid.");
		}
		if (getUnmarshaller() == null)
		{
			logger.error("Provider "+getProviderName()+" has not implemented the getUnmarshaller() method properly. It returns null which is not valid.");
		}
		if (getSingleObjectClassInfo() == null)
		{
			logger.error("Provider "+getProviderName()+" has not implemented the getSingleObjectClassInfo() method properly. It returns null which is not valid.");
		}
		if (getMultiObjectClassInfo() == null)
		{
			logger.error("Provider "+getProviderName()+" has not implemented the getMultiObjectClassInfo() method properly. It returns null which is not valid.");
		}
    }
     
    public String getServiceName()
    {
    	return getMultiObjectClassInfo().getObjectName();
    }
    
    public ModelObjectInfo getCollectionObjectClassInfo()
    {
        return getMultiObjectClassInfo();
    }
    
    /* 0 = No events */
    public int getEventFrequency()
    {
        return getServiceProperties().getPropertyAsInt(CommonConstants.FREQ_PROPERTY, getProviderName(), CommonConstants.NO_EVENT);      
    }

    public int getEventDelay()
    {
        int delay = getServiceProperties().getPropertyAsInt(CommonConstants.EVENT_DELAY_PROPERTY, getProviderName(), CommonConstants.DEFAULT_EVENT_DELAY);
        return (delay < CommonConstants.DEFAULT_EVENT_DELAY) ? CommonConstants.DEFAULT_EVENT_DELAY : delay;
    }

    public EventProvider<?> getEventProvider()
    {
        return (BaseEventProvider<?>)BaseProvider.this;
    }

    /**
     * (non-Javadoc)
     * @see sif3.common.interfaces.Provider#finalise()
     */
    public void finalise()
    {
        // Shut down event timer & task - thread
        logger.debug("Finalise in BaseProvider for "+getClass().getSimpleName() +" called.");
    	// Call finalise on sub-class.
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.CoreProvider#finaliseSubClass()
     */
    @Override
    public void finaliseSubClass()
    {
        finalise();
        shutdown();
    }
    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#getServiceInfo(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     */
    @Override
    public HeaderProperties getServiceInfo(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException, SIFException
    {
        // For the time being we just call the abstract method getCustomServiceInfo().
        return getCustomServiceInfo(zone, context, pagingInfo, metadata);
    }

    /*---------------------*/
    /*-- Private methods --*/
    /*---------------------*/
}
