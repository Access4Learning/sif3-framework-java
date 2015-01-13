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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import sif.dd.au30.model.ObjectFactory;
import sif.dd.au30.model.StudentCollectionType;
import sif.dd.au30.model.StudentPersonalType;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.interfaces.QueryProvider;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import systemic.sif3.demo.rest.ModelObjectConstants;
import systemic.sif3.demo.rest.provider.iterators.StudentPersonalIterator;
import au.com.systemic.framework.utils.FileReaderWriter;
import au.com.systemic.framework.utils.StringUtils;

/**
 * @author Joerg Huber
 *
 */
public class StudentPersonalProvider extends AUDataModelProviderWithEvents<StudentCollectionType> implements QueryProvider
{
	private static int numDeletes = 0;
	private static HashMap<String, StudentPersonalType> students = null; //new HashMap<String, StudentPersonalType>();
	private ObjectFactory dmObjectFactory = new ObjectFactory();
	
	public StudentPersonalProvider()
	{
		super();

		logger.debug("Constructor for StudentPersonalProvider has been called.");
		if (students == null)
		{
			logger.debug("Constructor for StudentPersonalProvider called for the first time. Try to load students from XML file...");

			// Load all students so that we can do some real stuff here.
			String studentFile = getServiceProperties().getPropertyAsString("provider.student.file.location", null);
			if (studentFile != null)
			{
				String inputXML = FileReaderWriter.getFileContent(studentFile);
				try
				{
					StudentCollectionType studentList = (StudentCollectionType) getUnmarshaller().unmarshalFromXML(inputXML, getMultiObjectClassInfo().getObjectType());
					if ((studentList != null) && (studentList.getStudentPersonal() != null))
					{
						students = new HashMap<String, StudentPersonalType>();
						for (StudentPersonalType studentPersonal : studentList.getStudentPersonal())
						{
							students.put(studentPersonal.getRefId(), studentPersonal);
						}
						logger.debug("Loaded " + students.size() + " s into memory.");
					}
				}
				catch (UnmarshalException ex)
				{
					ex.printStackTrace();
				}
				catch (UnsupportedMediaTypeExcpetion ex)
				{
					ex.printStackTrace();
				}
			}
		}

		// If students are still null then something must have failed and would have been logged.
		// For the purpose of making things work ok we initialise the students hashmap now. It will avoid null pointer errors.
		if (students == null)
		{
			students = new HashMap<String, StudentPersonalType>();
		}
    }

    /*-------------------------------------*/
    /*-- EventProvider Interface Methods --*/
    /*-------------------------------------*/
    /*
     * (non-Javadoc)
     * @see sif3.common.interfaces.EventProvider#getSIFEvents()
     */
    @Override
    public SIFEventIterator<StudentCollectionType> getSIFEvents()
    {
 	    return new StudentPersonalIterator(getServiceName(), getServiceProperties(), students);
    }
    
    /*
     * (non-Javadoc)
     * @see sif3.common.interfaces.EventProvider#onEventError(sif3.common.model.SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public void onEventError(SIFEvent<StudentCollectionType> sifEvent, SIFZone zone,SIFContext context)
    {
	    //We need to deal with the error. At this point in time we just log it.
    	if ((sifEvent != null) && (sifEvent.getSIFObjectList() != null))
    	{
    		try
    		{
    			String eventXML = getMarshaller().marshalToXML(sifEvent.getSIFObjectList());
        		logger.error("Failed to sent the following Objects as and Event to Zone ("+zone+") and Context ("+context+"):\n"+eventXML);
    		}
    		catch (Exception ex)
    		{
    			logger.error("Failed to marshall events.", ex);
    		}
    	}
    	else
    	{
    		logger.error("sifEvent Object is null, or there are no events on sifEvent.sifObjectList");
    	}
	    
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.EventProvider#modifyBeforeSent(sif3.common.model.SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public SIFEvent<StudentCollectionType> modifyBeforePublishing(SIFEvent<StudentCollectionType> sifEvent, SIFZone zone, SIFContext context)
    {
    	// At this point we don't need to modify anything. Just send as is...
	    return sifEvent;
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
    	if (StringUtils.isEmpty(resourceID))
    	{
    		throw new IllegalArgumentException("Resource ID is null or empty. It must be provided to retrieve an entity.");
    	}
    	
    	logger.debug("Retrieve student with Resoucre ID = "+resourceID+", "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    	
    	return students.get(resourceID);
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#createSingle(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object createSingle(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	logger.debug("Create Single Student for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);

    	// Must be of type StudentPersonalType
    	if (data instanceof StudentPersonalType)
    	{
    		StudentPersonalType student = (StudentPersonalType)data;
    		if (StringUtils.isEmpty(student.getRefId()))
    		{
    			//In future this should be a UUID instead of a GUID
    		  if (!useAdvisory)
    		  {
    		    // Create new UUID because the advisory shall not be used.
    		    student.setRefId(UUIDGenerator.getSIF2GUIDUpperCase());
    		  }
    		  // else leave student UUID untouched.
    		}
    		
    		//In the real implementation we would call a BL method here to create the Student.
    		return student;
    	}
    	else
    	{
    		throw new IllegalArgumentException("Expected Object Type  = StudentPersonalType. Received Object Type = "+data.getClass().getSimpleName());
    	}
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#updateSingle(java.lang.Object, java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public boolean updateSingle(Object data, String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	if (StringUtils.isEmpty(resourceID))
    	{
    		throw new IllegalArgumentException("Resource ID is null or empty. It must be provided to update an entity.");
    	}
    	
    	// Must be of type StudentPersonalType
    	if (data instanceof StudentPersonalType)
    	{
    		logger.debug("Update student with Resoucre ID = "+resourceID+", "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);

    		//In the real implementation we would call a BL method here to modify the Student.
    		return true;
    	}
    	else
    	{
    		throw new IllegalArgumentException("Expected Object Type  = StudentPersonalType. Received Object Type = "+data.getClass().getSimpleName());
    	}
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#deleteSingle(java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public boolean deleteSingle(String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	if (StringUtils.isEmpty(resourceID))
    	{
    		throw new IllegalArgumentException("Resource ID is null or empty. It must be provided to delete an entity.");
    	}
    	
    	logger.debug("Remove student with Resoucre ID = "+resourceID+", "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);

    	//In the real implementation we would call a BL method here to remove the Student.
    	return ((numDeletes++ % 3) != 0);  // every third time of the call I return false.
    }

    
	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#retrive(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo)
     */
    @Override
    public Object retrieve(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException
    {
    	logger.debug("Retrieve Students for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);

    	ArrayList<StudentPersonalType> studentList = new ArrayList<StudentPersonalType>();
    	if (pagingInfo == null) //return all
    	{
    		studentList.addAll(students.values());
    	}
    	else
    	{
    		pagingInfo.setTotalObjects(students.size());
    		if ((pagingInfo.getPageSize() * (pagingInfo.getCurrentPageNo()+1)) > students.size())
    		{
    			return null; // Requested page outside of limits.
    		}
    		
    		// retrieve applicable students
    		Collection<StudentPersonalType> allStudent = students.values();
    		int i = 0;
    		int startPos = pagingInfo.getPageSize() * pagingInfo.getCurrentPageNo();
    		int endPos = startPos + pagingInfo.getPageSize();
    		for (Iterator<StudentPersonalType> iter = allStudent.iterator(); iter.hasNext();)
    		{
    			StudentPersonalType student = iter.next();
    			if ((i>=startPos) && (i<endPos))
    			{
    				studentList.add(student);
    			}
    			i++;
    		}
    	}
    	
    	StudentCollectionType studentCollection = dmObjectFactory.createStudentCollectionType();
    	studentCollection.getStudentPersonal().addAll(studentList);
	    return studentCollection;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#createMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<CreateOperationStatus> createMany(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	// Must be of type StudentPersonalType
    	if (data instanceof StudentCollectionType)
    	{
			logger.debug("Create students (Bulk Operation) for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
			StudentCollectionType students = (StudentCollectionType) data;
			ArrayList<CreateOperationStatus> opStatus = new ArrayList<CreateOperationStatus>();
			int i = 0;
			for (StudentPersonalType student : students.getStudentPersonal())
			{
				if ((i % 3) == 0)
				{
					// Set advisoryID the same as resourceID.
					opStatus.add(new CreateOperationStatus(student.getRefId(), student.getRefId(), 404, new ErrorDetails(400, "Data not good.")));
				}
				else
				{
					if (useAdvisory)
					{
						// Advisory refId was used. Set resourceId and advisoryId to the same
						opStatus.add(new CreateOperationStatus(student.getRefId(), student.getRefId(), 201));
					}
					else
					{
						// Create a new refId (resourceID) but we must also report back the original RefId as the advisory if it was available.
						opStatus.add(new CreateOperationStatus(UUIDGenerator.getSIF2GUIDUpperCase(), student.getRefId(), 201));
					}
				}
				i++;
			}

    		return opStatus;
    	}
    	else
    	{
    		throw new IllegalArgumentException("Expected Object Type  = StudentCollectionType. Received Object Type = "+data.getClass().getSimpleName());
    	}
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#updateMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> updateMany(Object data, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	// Must be of type StudentPersonalType
    	if (data instanceof StudentCollectionType)
    	{
    		logger.debug("Update students (Bulk Operation) for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    		StudentCollectionType students = (StudentCollectionType)data;
    		ArrayList<OperationStatus> opStatus = new ArrayList<OperationStatus>();
    		int i=0;
    		for (StudentPersonalType student : students.getStudentPersonal())
    		{
          if ((i % 3) == 0)
          {
            opStatus.add(new OperationStatus(student.getRefId(), 404, new ErrorDetails(404, "Student with GUID = "+student.getRefId()+" does not exist.")));
          }
          else
          {
            opStatus.add(new OperationStatus(student.getRefId(), 200));
          }
          i++;
    		}
    		
    		return opStatus;
    	}
    	else
    	{
    		throw new IllegalArgumentException("Expected Object Type  = StudentCollectionType. Received Object Type = "+data.getClass().getSimpleName());
    	}
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#deleteMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> deleteMany(List<String> resourceIDs, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	logger.debug("Delete Students (Bulk Operation) for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);

    	//In the real implementation we would call a BL method here to modify the Student.
		ArrayList<OperationStatus> opStatus = new ArrayList<OperationStatus>();
		int i = 0;
		for (String resourceID : resourceIDs)
		{
			if ((i % 3) == 0)
			{
				opStatus.add(new OperationStatus(resourceID, 404, new ErrorDetails(404, "Student with GUID = " + resourceID + " does not exist.")));
			}
			else
			{
				opStatus.add(new OperationStatus(resourceID, 200));
			}
			i++;
		}
	    return opStatus;
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
	    return ModelObjectConstants.STUDENT_PERSONAL ;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
	    return ModelObjectConstants.STUDENT_PERSONALS;
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

  @Override
  public Object retrieveByServicePath(QueryCriteria queryCriteria, SIFZone zone, SIFContext context,
      PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException {
    logger.debug("Performing query by service path.");
    if (logger.isDebugEnabled()) {
      String queryString = queryCriteria == null ? "null" : queryCriteria.toString();
      logger.debug("query: " + queryString);
    }
    // in a real implementation you would parse the query criteria and return filtered results.
    // just perform a standard retrieve in this environment.
    return retrieve(zone, context, pagingInfo, metadata);
  }
}
