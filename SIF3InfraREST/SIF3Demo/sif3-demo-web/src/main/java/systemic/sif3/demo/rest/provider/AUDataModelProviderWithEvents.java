/*
 * AUDataModelProviderWithEvents.java
 * Created: 19/03/2014
 *
 * Copyright 2014 Systemic Pty Ltd
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

package systemic.sif3.demo.rest.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif.dd.au30.conversion.DataModelMarshalFactory;
import sif.dd.au30.conversion.DataModelUnmarshalFactory;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.utils.JAXBUtils;
import sif3.infra.rest.provider.BaseEventProvider;

/**
 * @author Joerg Huber
 *
 */
public abstract class AUDataModelProviderWithEvents<L> extends BaseEventProvider<L>
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

	private static DataModelUnmarshalFactory unmarshaller = new DataModelUnmarshalFactory();
	private static DataModelMarshalFactory marshaller = new DataModelMarshalFactory();

	/**
     * @param serviceID
     */
    public AUDataModelProviderWithEvents()
    {
	    super();
	    
		//Initialise JAXB context for these classes. Make provider behave better against race conditions.
		JAXBUtils.initCtx(getMultiObjectClassInfo().getObjectType());
		JAXBUtils.initCtx(getSingleObjectClassInfo().getObjectType());
    }

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DataModelLink#getMarshaller()
	 */
    public MarshalFactory getMarshaller()
    {
	    return marshaller;
    }

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DataModelLink#getUnmarshaller()
	 */
    public UnmarshalFactory getUnmarshaller()
    {
	    return unmarshaller;
    }
	
	/*
	 * (non-Javadoc)
	 * @see sif3.infra.rest.provider.BaseProvider#shutdown()
	 */
    public void shutdown()
    {
    }
    
    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.BaseProvider#getCustomServiceInfo(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     */
    @Override
    public HeaderProperties getCustomServiceInfo(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
        return null;
    }
}
