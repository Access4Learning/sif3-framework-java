/*
 * DailyAttendanceProvider.java
 * Created: 27/05/2014
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

import java.util.List;

import sif.dd.au30.model.StudentDailyAttendanceCollectionType;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.OperationStatus;
import systemic.sif3.demo.rest.ModelObjectConstants;
import systemic.sif3.demo.rest.provider.iterators.DailyAttendanceEventIterator;

/**
 * @author Joerg Huber
 *
 */
public class DailyAttendanceProvider extends AUDataModelProviderWithEvents<StudentDailyAttendanceCollectionType>
{
    public DailyAttendanceProvider()
    {
	    super();
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
	    return ModelObjectConstants.STUDENT_DAILY_ATTENDANCE ;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
	    return ModelObjectConstants.STUDENT_DAILY_ATTENDANCES;
    }

    /*-------------------------------------*/
    /*-- EventProvider Interface Methods --*/
    /*-------------------------------------*/
    /*
     * (non-Javadoc)
     * @see sif3.common.interfaces.EventProvider#getSIFEvents()
     */
    @Override
    public SIFEventIterator<StudentDailyAttendanceCollectionType> getSIFEvents()
    {
    	int numSchools = getServiceProperties().getPropertyAsInt("custom.events.dailyAtt.numSchools", 0);
    	int numStudents = getServiceProperties().getPropertyAsInt("custom.events.dailyAtt.numStudents", 0);
 	    return new DailyAttendanceEventIterator(getServiceName(), getServiceProperties(), numSchools, numStudents);
    }
    
    /*
     * (non-Javadoc)
     * @see sif3.common.interfaces.EventProvider#onEventError(sif3.common.model.SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public void onEventError(SIFEvent<StudentDailyAttendanceCollectionType> sifEvent, SIFZone zone,SIFContext context)
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

    /*----------------------------*/
    /*-- CRUD Interface Methods --*/
    /*----------------------------*/
    
    /*
     * This section is dummied out. This provider is only used as a Event Generator.
     */

    /* (non-Javadoc)
     * @see sif3.common.interfaces.EventProvider#modifyBeforeSent(sif3.common.model.SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public SIFEvent<StudentDailyAttendanceCollectionType> modifyBeforePublishing(SIFEvent<StudentDailyAttendanceCollectionType> sifEvent, SIFZone zone, SIFContext context)
    {
    	// At this point we don't need to modify anything. Just send as is...
	    return sifEvent;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#retrievByPrimaryKey(java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object retrievByPrimaryKey(String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
	    return null;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#createSingle(java.lang.Object, boolean, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object createSingle(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
	    return null;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#updateSingle(java.lang.Object, java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public boolean updateSingle(Object data, String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
	    return false;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#deleteSingle(java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public boolean deleteSingle(String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
	    return false;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#retrieve(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo)
     */
    @Override
    public Object retrieve(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException
    {
	    return null;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#createMany(java.lang.Object, boolean, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<CreateOperationStatus> createMany(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
	    return null;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#updateMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> updateMany(Object data, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
	    return null;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#deleteMany(java.util.List, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> deleteMany(List<String> resourceIDs, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
	    return null;
    }
}
