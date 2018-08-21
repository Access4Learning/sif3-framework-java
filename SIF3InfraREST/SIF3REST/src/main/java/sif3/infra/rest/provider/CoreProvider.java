/*
 * CoreProvider.java
 * Created: 7 Jun 2018
 *
 * Copyright 2018 Systemic Pty Ltd
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

package sif3.infra.rest.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIFException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.infra.common.env.mgr.ProviderManagerFactory;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.interfaces.ProviderJobManager;

/**
 * This abstract class forms the basis of all provider style classes. All provider classes must extend this class and then add
 * appropriate interfaces or abstract methods to is as required.
 * 
 * @author Joerg Huber
 *
 */
public abstract class CoreProvider
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Shuts down the sub-class provider. This should release all associated resources with that provider.
     */
    public abstract void shutdown();
    
    /**
     * This method behave like the getServiceInfo() method of the Provider Interface. Please refer to description in that class:<br/><br/>
     * 
     * @see sif3.common.interfaces.Provider#getServiceInfo(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     * 
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the objects shall be returned. Can be Null (default Zone)
     * @param pagingInfo Page information to determine which results to return. Null = Return all (NOT RECOMMENDED!).
     * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * 
     * @return Header Properties that shall be returned as HTTP Headers to the caller. Note this can be null or any number of
     *         custom HTTP headers. Generally one would expect that to be null.
     * 
     * @throws UnsupportedQueryException The query provided with this request is not supported (NOT YET IMPLEMENTED FUNCTIONALITY)
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
     * @throws DataTooLargeException If the data that shall be returned is too large due to the values in the paging info.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
     */
    public abstract HeaderProperties getCustomServiceInfo(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException, SIFException;
    
    /**
     * This method returns the actual service name (NOT serviceID). The service name is the name that is used in URL paths to identify the
     * SIF Object that we are dealing with. The service name is the plural form of the SIF Object name as specified in the SIF3 Spec.
     *  
     * @return See Desc.
     */
    public abstract String getServiceName();
    
    /**
     * Returns the information for the 'collection-style object'. The returned object holds the name of a 'collection-style object' 
     * (i.e StudentPersonals) and the physical class this maps to for the data model supported for this implementation.
     * 
     * @return See Desc.
     */
    public abstract ModelObjectInfo getCollectionObjectClassInfo();

    /**
     */
    public CoreProvider()
    {
        super();
    }
    
    /**
     * Returns the Provider Configuration in a nice form. Should rarely be required by the concrete implementation of a provider class.
     * 
     * @return See desc.
     */
    public ProviderEnvironment getProviderEnvironment()
    {
      return (ProviderEnvironment)ProviderManagerFactory.getEnvironmentManager().getEnvironmentInfo();
    }
    
    /**
     * Returns the Job Provider Manger for this provider.
     * 
     * @return See desc.
     */
    public ProviderJobManager getJobManager()
    {
        return (ProviderJobManager)ProviderManagerFactory.getEnvironmentManager().getJobManager();
    }

    /**
     * Utility method to easily retrieve the property file content for a provider.
     * 
     * @return See desc
     */
    public AdvancedProperties getServiceProperties()
    {
      return ProviderManagerFactory.getEnvironmentManager().getServiceProperties();
    }
    
    /**
     * Utility method. Mainly used for useful logging messages.
     * 
     * @return Returns the Service Id concatenated with the Provider Name.
     */
    public String getPrettyName()
    {
      return getProviderEnvironment().getAdapterName()+" - " + getProviderName();
    }

    /**
     * Utility method. Returns the class name of the provider.
     * 
     * @return Returns the Provider Class Name.
     */
    public String getProviderName()
    {
        return getClass().getSimpleName();
    }
}
