/*
 * BaseNamedQueryProvider.java
 * Created: 4 Dec 2018
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

package sif3.infra.rest.provider.namedquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIFException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.interfaces.EventProvider;
import sif3.common.interfaces.NamedQueryProvider;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.infra.rest.provider.CoreProvider;

/**
 * @author Joerg Huber
 *
 */
public abstract class BaseNamedQueryProvider extends CoreProvider implements NamedQueryProvider
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * Shuts down the sub-class provider. This should release all associated resources with that provider.
     */
    public abstract void shutdown();
    
    /**
     */
    public BaseNamedQueryProvider()
    {
        super();
    }

    /*----------------------------------------------------------------------*/
    /*-- Methods to be implemented for CoreProvider and other interfaces. --*/
    /*----------------------------------------------------------------------*/

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMarshaller()
     */
    @Override
    public MarshalFactory getMarshaller()
    {
        return null;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getUnmarshaller()
     */
    @Override
    public UnmarshalFactory getUnmarshaller()
    {
        return null;
    }

    /*
     * This is not required for named services as it is a known mode.
     * 
     * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getSingleObjectClassInfo()
    {
        return new ModelObjectInfo(null, null);
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
        //This must be the same name as used in the URL or as listed in the Environment XML/JSON.
        return new ModelObjectInfo(getServiceName(), null);
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.NamedQueryProvider#finalise()
     */
    @Override
    public void finalise()
    {
        // Shut down task - thread
        logger.debug("Finalise in BaseNamedQueryProvider for "+getClass().getSimpleName() +" called.");
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
     * @see sif3.infra.rest.provider.CoreProvider#getCollectionObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getCollectionObjectClassInfo()
    {
        return getMultiObjectClassInfo();
    }

    /*-------------------------------------------------------------*/
    /*-- Methods not required/supported for Named Query Services --*/
    /*-------------------------------------------------------------*/

    /* HTTP HEAD is not defined/supported for Named Query.
     * 
     * @see sif3.infra.rest.provider.CoreProvider#getCustomServiceInfo(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     */
    @Override
    public HeaderProperties getCustomServiceInfo(SIFZone zone, 
                                                 SIFContext context,
                                                 PagingInfo pagingInfo, 
                                                 RequestMetadata metadata) 
        throws PersistenceException, UnsupportedQueryException, DataTooLargeException, SIFException
    {
        return null;
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.CoreProvider#getEventFrequency()
     */
    @Override
    public int getEventFrequency()
    {
        return 0;
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.CoreProvider#getEventDelay()
     */
    @Override
    public int getEventDelay()
    {
        return 0;
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.CoreProvider#getEventProvider()
     */
    @Override
    public EventProvider<?> getEventProvider()
    {
        return null;
    }
}
