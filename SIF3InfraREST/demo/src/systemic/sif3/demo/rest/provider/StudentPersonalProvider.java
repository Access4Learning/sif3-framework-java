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

import javax.xml.bind.JAXBElement;

import au.com.systemic.framework.utils.DateUtils;
import au.com.systemic.framework.utils.FileReaderWriter;
import au.com.systemic.framework.utils.StringUtils;
import sif.dd.au34.model.ObjectFactory;
import sif.dd.au34.model.StudentPersonalCollectionType;
import sif.dd.au34.model.StudentPersonalType;
import sif.dd.au34.model.TeachingGroupCollectionType;
import sif.dd.au34.model.TeachingGroupType.StudentList;
import sif.dd.au34.model.TeachingGroupType.StudentList.TeachingGroupStudent;
import sif3.common.CommonConstants;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.ResponseHeaderConstants;
import sif3.common.interfaces.ChangesSinceProvider;
import sif3.common.interfaces.QueryProvider;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.ChangedSinceInfo;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.QueryPredicate;
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

/**
 * @author Joerg Huber
 *
 */
public class StudentPersonalProvider extends AUDataModelProviderWithEvents<StudentPersonalCollectionType> implements QueryProvider, ChangesSinceProvider
{
	private static int numDeletes = 0;
	private static HashMap<String, StudentPersonalType> students = null; //new HashMap<String, StudentPersonalType>();
	private static HashMap<String, StudentPersonalType> teachingGroupStudents = null; //new HashMap<String, StudentPersonalType>();
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
//				String inputXML = FileReaderWriter.getFileContent(studentFile, getProviderEnvironment().getCharsetEncoding());
                String inputXML = FileReaderWriter.getFileContent(studentFile, ModelObjectConstants.UTF_8);
				try
				{
					StudentPersonalCollectionType studentList = (StudentPersonalCollectionType) getUnmarshaller().unmarshalFromXML(inputXML, getMultiObjectClassInfo().getObjectType());
					if ((studentList != null) && (studentList.getStudentPersonal() != null))
					{
						students = new HashMap<String, StudentPersonalType>();
						for (StudentPersonalType studentPersonal : studentList.getStudentPersonal())
						{
//							studentPersonal.setRefId(UUIDGenerator.getUUID());
							students.put(studentPersonal.getRefId(), studentPersonal);
						}
						logger.debug("Loaded " + students.size() + " Students into memory.");
					}
				}
				catch (UnmarshalException ex)
				{
					ex.printStackTrace();
				}
				catch (UnsupportedMediaTypeException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		
		if (teachingGroupStudents == null)
		{
			logger.debug("Try to load students for teaching group from XML file...");

			// Load all students so that we can do some real stuff here.
			String fileName = getServiceProperties().getPropertyAsString("provider.teachinggroup.file.location", null);
			if (fileName != null)
			{
				String inputXML = FileReaderWriter.getFileContent(fileName, ModelObjectConstants.UTF_8);
				try
				{
					TeachingGroupCollectionType classes = (TeachingGroupCollectionType) getUnmarshaller().unmarshalFromXML(inputXML, ModelObjectConstants.TEACHING_GROUPS.getObjectType());
					if ((classes != null) && (classes.getTeachingGroup() != null))
					{	
						// Get student list of first teaching group
						JAXBElement<StudentList> jaxbClassStudents = classes.getTeachingGroup().get(0).getStudentList();
						if (jaxbClassStudents != null)
						{
							teachingGroupStudents = new HashMap<String, StudentPersonalType>();
							StudentList classStudents = jaxbClassStudents.getValue();
							for (TeachingGroupStudent student : classStudents.getTeachingGroupStudent())
							{
								teachingGroupStudents.put(student.getStudentPersonalRefId().getValue(), students.get(student.getStudentPersonalRefId().getValue()));
							}
						}
						logger.debug("Loaded " + teachingGroupStudents.size() + " teaching group students for a class into memory.");
					}
				}
				catch (UnmarshalException ex)
				{
					ex.printStackTrace();
				}
				catch (UnsupportedMediaTypeException ex)
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
		if (teachingGroupStudents == null)
		{
			teachingGroupStudents = new HashMap<String, StudentPersonalType>();
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
    public SIFEventIterator<StudentPersonalCollectionType> getSIFEvents()
    {
 	    return new StudentPersonalIterator(getServiceName(), getServiceProperties(), students);
    }
    
    /*
     * (non-Javadoc)
     * @see sif3.common.interfaces.EventProvider#onEventError(sif3.common.model.SIFEvent, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public void onEventError(SIFEvent<StudentPersonalCollectionType> sifEvent, SIFZone zone,SIFContext context)
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
    public SIFEvent<StudentPersonalCollectionType> modifyBeforePublishing(SIFEvent<StudentPersonalCollectionType> sifEvent, SIFZone zone, SIFContext context, HeaderProperties customHTTPHeaders)
    {
        // Here we could add some custom HTTP header values but at this time we have no interest to do so.
    	
    	// At this point we don't need to modify anything. Just send as is...
	    return sifEvent;
    }


    /*--------------------------------*/
    /*-- Provider Interface Methods --*/
    /*--------------------------------*/

    /* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#retrieveByPrimaryKey(java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object retrieveByPrimaryKey(String resourceID, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	if (StringUtils.isEmpty(resourceID))
    	{
    		throw new IllegalArgumentException("Resource ID is null or empty. It must be provided to retrieve an entity.");
    	}
    	
    	logger.debug("Retrieve student with Resoucre ID = "+resourceID+", "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    	
    	StudentPersonalType student = students.get(resourceID);
//    	logger.debug("Student to return: "+((student != null) ? student.getPersonInfo().getName().getGivenName().getValue() : student));
    	
    	return student;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#createSingle(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object createSingle(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	logger.debug("Create Single Student for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);

//    	return null; // test return null value
    	
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
    public Object retrieve(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
    	logger.debug("Retrieve Students for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    	logger.debug("ChangedSince Date: "+metadata.getRequestParameter("ChangedSince"));
    	logger.debug("Custom HTTP Header (customHdr): "+metadata.getHTTPParameter("customHdr"));
    	
    	if (pagingInfo == null)
    	{
    		throw new DataTooLargeException("No paging info is provided. Please provide navigationPage and navigationPageSize.");
    	}
    	else
    	{
    	    // We may want to set the navigationID! Here we set it as a GUID but only if we are on the first page because it is expected that in subsequent
    	    // paged calls that the consumer would provide that id.
    	    if (pagingInfo.getCurrentPageNo() == CommonConstants.FIRST_PAGE)
    	    {
//    	        pagingInfo.setNavigationId(UUIDGenerator.getUUID());
    	    }
    	}
    	
    	ArrayList<StudentPersonalType> studentList = fetchStudents(students, pagingInfo);
    	
    	StudentPersonalCollectionType studentCollection = dmObjectFactory.createStudentPersonalCollectionType();
    	
    	if (studentList != null)
    	{
    		studentCollection.getStudentPersonal().addAll(studentList);
    		return studentCollection;
    	}
    	else
    	{
    		return null;
    	}
    }
    
    /*
     * (non-Javadoc)
     * @see sif3.common.interfaces.QueryProvider#retrieveByServicePath(sif3.common.model.QueryCriteria, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     */
    @Override
	public Object retrieveByServicePath(QueryCriteria queryCriteria, SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) 
    	throws PersistenceException, UnsupportedQueryException, DataTooLargeException
	{
		logger.debug("Performing query by service path.");
		if (logger.isDebugEnabled())
		{
			logger.debug("Query Condition (given by service path): " + queryCriteria);
		}
		
		// Check if the query is SchoolInfo or TeachingGroup
		List<QueryPredicate> predicates = queryCriteria.getPredicates();
		if ((predicates != null) && (predicates.size() == 1)) // ensure it is a valid condition
		{
			if ("SchoolInfos".equals(predicates.get(0).getSubject()))
			{
				// Assume all students known from the file are at the same school.
				return retrieve(zone, context, pagingInfo, metadata);
			}
			else if ("TeachingGroups".equals(predicates.get(0).getSubject()))
			{
		    	logger.debug("Retrieve Students for Teaching Group (class) "+predicates.get(0).getValue()+" for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);

		    	ArrayList<StudentPersonalType> studentList = fetchStudents(teachingGroupStudents, pagingInfo);
		    	StudentPersonalCollectionType studentCollection = dmObjectFactory.createStudentPersonalCollectionType();
		    	if (studentList != null)
		    	{
		    		studentCollection.getStudentPersonal().addAll(studentList);
		    		return studentCollection;
		    	}
		    	else
		    	{
		    		return null;
		    	}
		    }
			else
			{
				throw new UnsupportedQueryException("The query condition (driven by the service path) "+queryCriteria+" is not supported by the provider.");
			}
		}
		else // not supported query (only single level service path query supported by this provider)
		{
			throw new UnsupportedQueryException("The query condition (driven by the service path) "+queryCriteria+" is not supported by the provider.");
		}
	}
    
    /*
     * (non-Javadoc)
     * @see sif3.common.interfaces.QueryProvider#retrieveByQBE(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     */
    public Object retrieveByQBE(Object exampleObject, SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) 
    	throws PersistenceException, UnsupportedQueryException, DataTooLargeException
	{
		logger.debug("Performing QBE query for: "+exampleObject);
    	if (exampleObject instanceof StudentPersonalType)
    	{
	    	ArrayList<StudentPersonalType> studentList = fetchStudents(students, pagingInfo);
	    	StudentPersonalCollectionType studentCollection = dmObjectFactory.createStudentPersonalCollectionType();
	    	if (studentList != null)
	    	{
	    		studentCollection.getStudentPersonal().addAll(studentList);
	    		return studentCollection;
	    	}
	    	else
	    	{
	    		return null;
	    	}
    	}
    	else
    	{
    		throw new IllegalArgumentException("Expected Object Type  = StudentPersonalType. Received Object Type = "+exampleObject.getClass().getSimpleName());
    	}
    	//throw new UnsupportedQueryException("QBE not supported for StudentPersonals");
	}

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#createMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<CreateOperationStatus> createMany(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	// Must be of type StudentPersonalType
    	if (data instanceof StudentPersonalCollectionType)
    	{
			logger.debug("Create students (Bulk Operation) for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
			StudentPersonalCollectionType students = (StudentPersonalCollectionType) data;
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
    	if (data instanceof StudentPersonalCollectionType)
    	{
    		logger.debug("Update students (Bulk Operation) for "+getZoneAndContext(zone, context)+" and RequestMetadata = "+metadata);
    		StudentPersonalCollectionType students = (StudentPersonalCollectionType)data;
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

    /*
     * Override the method from the AUDataModelProviderWithEvents class and set a few custom headers.
     * 
     * (non-Javadoc)
     * @see systemic.sif3.demo.rest.provider.AUDataModelProviderWithEvents#getCustomServiceInfo(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     */
    @Override
    public HeaderProperties getCustomServiceInfo(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
        HeaderProperties headers = new HeaderProperties();
        headers.setHeaderProperty("TestHeaderProperty1", "value1");
        headers.setHeaderProperty(ResponseHeaderConstants.HDR_ENVIRONMENT_URI, "/should/be/overridden");
        headers.setHeaderProperty(ResponseHeaderConstants.HDR_REQUEST_ID, "123"); // should not be accepted.
        return headers;
    }
    
    /*--------------------------------------------*/
    /*-- ChangesSinceProvider Interface Methods --*/
    /*--------------------------------------------*/

    /*
     * (non-Javadoc)
     * @see sif3.common.interfaces.ChangesSinceProvider#changesSinceSupported()
     */
    @Override
    public boolean changesSinceSupported()
    {
        return true;
    }

    /*
     * For the purpose of illustrating the use of the changes since functionality we assume that changes since are provided by this Object provider
     * based on date time stamps. Real implementations may look up a table to get the latest value of the opaque marker.
     * 
     * (non-Javadoc)
     * @see sif3.common.interfaces.ChangesSinceProvider#getLatestOpaqueMarker()
     */
    @Override
    public String getLatestOpaqueMarker()
    {
        return DateUtils.nowAsISO8601withSecFraction();
    }

    /*
     * (non-Javadoc)
     * @see sif3.common.interfaces.ChangesSinceProvider#getChangesSince(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.ChangedSinceInfo, sif3.common.model.RequestMetadata)
     */
    @Override
    public Object getChangesSince(SIFZone zone, SIFContext context, PagingInfo pagingInfo, ChangedSinceInfo changedSinceInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
        // This is not a real implementation. We just fake things here. In a real implementation one would go to a change log to retrieve
        // Students for the given changedSinceInfo criteria.
        logger.info("getChangesSince for "+getProviderName()+" called with changes since info: "+changedSinceInfo);
        return retrieve(zone, context, pagingInfo, metadata);
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
	
	private ArrayList<StudentPersonalType> fetchStudents(HashMap<String, StudentPersonalType> studentMap, PagingInfo pagingInfo)
	{
    	ArrayList<StudentPersonalType> studentList = new ArrayList<StudentPersonalType>();
    	if (pagingInfo == null) //return all
    	{
    		studentList.addAll(studentMap.values());
    	}
    	else
    	{
    		pagingInfo.setTotalObjects(studentMap.size());
    		if ((pagingInfo.getPageSize() * (pagingInfo.getCurrentPageNo())) > studentMap.size())
    		{
    			return null; // Requested page outside of limits.
    		}
    		
    		// retrieve applicable students
    		Collection<StudentPersonalType> allStudent = studentMap.values();
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
    		// Set the number of object that are returned in the paging info. Will ensure HTTP headers are set correctly.
    		pagingInfo.setPageSize(studentList.size());
    	}
    	
	    return studentList;
	}
}
