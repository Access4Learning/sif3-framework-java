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

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.OperationStatus;
import sif3.infra.rest.provider.BaseProvider;
import systemic.sif3.demo.rest.conversion.CSVMarshaller;
import systemic.sif3.demo.rest.conversion.CSVUnmarshaller;

/**
 * @author Joerg Huber
 *
 */
public class CSVStudentProvider extends BaseProvider
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
    public Object retrieve(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException
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
}
