/*
 * StudentPersonalProvider.java
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

package systemic.sif3.demo.rest.provider;

import java.util.List;

import javax.ws.rs.core.MediaType;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.OperationStatus;
import sif3.infra.rest.provider.BaseEventProvider;
import systemic.sif3.demo.rest.conversion.CSVMarshaller;
import systemic.sif3.demo.rest.conversion.CSVUnmarshaller;
import systemic.sif3.demo.rest.provider.iterators.CSVStudentIterator;

/**
 * @author Joerg Huber
 *
 */
public class CSVStudentProvider extends  BaseEventProvider<String>
{
	public CSVStudentProvider()
	{
		super();
		logger.debug("Constructor for CSVStudentProvider has been called.");
    }

    /*--------------------------------*/
    /*-- Provider Interface Methods --*/
    /*--------------------------------*/

    /* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#retrievByPrimaryKey(java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object retrievByPrimaryKey(String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	logger.debug("Retrieve By Primary Key Resoucre ID = "+resourceID+", "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    	throw new IllegalArgumentException("Not implemented for CSV Provider.");
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#createSingle(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object createSingle(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	logger.debug("Create Single for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    	logger.debug("Payload to Process:\n"+data.toString());

    	return null;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#updateSingle(java.lang.Object, java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public boolean updateSingle(Object data, String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
		logger.debug("Update single with Resoucre ID = "+resourceID+", "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    	throw new IllegalArgumentException("Not implemented for CSV Provider.");
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#deleteSingle(java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public boolean deleteSingle(String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	logger.debug("Remove Single with Resoucre ID = "+resourceID+", "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    	throw new IllegalArgumentException("Not implemented for CSV Provider.");
    }

    
	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#retrive(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo)
     */
    @Override
    public Object retrieve(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
    	logger.debug("Retrieve All for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    	throw new IllegalArgumentException("Not implemented for CSV Provider.");
    }
    
	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#createMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<CreateOperationStatus> createMany(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
		logger.debug("Create Many (Bulk Operation) for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    	throw new IllegalArgumentException("Not implemented for CSV Provider.");
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#updateMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> updateMany(Object data, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
		logger.debug("Update Many (Bulk Operation) for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    	throw new IllegalArgumentException("Not implemented for CSV Provider.");
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#deleteMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> deleteMany(List<String> resourceIDs, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	logger.debug("Delete Many (Bulk Operation) for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    	throw new IllegalArgumentException("Not implemented for CSV Provider.");
    }

    /*--------------------------------------*/
    /*-- Event required Interface Methods --*/
    /*--------------------------------------*/

    /* (non-Javadoc)
     * @see sif3.common.interfaces.EventProvider#getSIFEvents()
     */
    @Override
    public SIFEventIterator<String> getSIFEvents()
    {
	    return new CSVStudentIterator(getServiceProperties());
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.EventProvider#modifyBeforePublishing(sif3.common.model.SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public SIFEvent<String> modifyBeforePublishing(SIFEvent<String> sifEvent, SIFZone zone, SIFContext context, HeaderProperties customHTTPHeaders)
    {
    	// At this point we don't need to modify anything. Just send as is...
	    return sifEvent;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.EventProvider#onEventError(sif3.common.model.SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public void onEventError(SIFEvent<String> sifEvent, SIFZone zone, SIFContext context)
    {
	    //We need to deal with the error. At this point in time we just log it.
    	if ((sifEvent != null) && (sifEvent.getSIFObjectList() != null))
    	{
//    		try
//    		{
//    			String eventXML = getMarshaller().marshalToXML(sifEvent.getSIFObjectList());
        		logger.error("Failed to sent "+getServiceName()+" Objects as and Event to Zone ("+zone+") and Context ("+context+").");
//    		}
//    		catch (Exception ex)
//    		{
//    			logger.error("Failed to marshall events.", ex);
//    		}
    	}
    	else
    	{
    		logger.error("sifEvent Object is null, or there are no events on sifEvent.sifObjectList");
    	}
    }

    /*------------------------------------*/
    /*-- Media Type Override for Events --*/
    /*------------------------------------*/
	@Override
    public MediaType getRequestMediaType()
	{
		return getMarshaller().getDefault();
	}
	
	@Override
	public MediaType getResponseMediaType()
	{
		return getUnmarshaller().getDefault();
	}    
    
    /*--------------------------------------*/
    /*-- Other required Interface Methods --*/
    /*--------------------------------------*/
	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getSingleObjectClassInfo()
    {
	    return new ModelObjectInfo("CSVStudent", String.class);
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
	    return new ModelObjectInfo("CSVStudents", String.class);
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMarshaller()
     */
    @Override
    public MarshalFactory getMarshaller()
    {
	    return new CSVMarshaller();
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getUnmarshaller()
     */
    @Override
    public UnmarshalFactory getUnmarshaller()
    {
	    return new CSVUnmarshaller();
    }

    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
	private String getZoneAndContext(SIFZone zone, SIFContext context)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Zone = ").append((zone==null) ? "null" : zone.getId()+(zone.getIsDefault()?" (dafault)":"")).append(" ");
		buffer.append("- Context = ").append((context == null) ? "null" : context.getId());
		
		return buffer.toString();
	}

	/* (non-Javadoc)
     * @see sif3.infra.rest.provider.BaseProvider#shutdown()
     */
    @Override
    public void shutdown()
    {}
    
    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.BaseProvider#getCustomServiceInfo(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     */
    @Override
    public HeaderProperties getCustomServiceInfo(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
        return null;
    }
}
