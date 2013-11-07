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
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import systemic.sif3.demo.rest.ModelObjectConstants;
import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.FileReaderWriter;
import au.com.systemic.framework.utils.StringUtils;

/**
 * @author Joerg Huber
 *
 */
public class StudentPersonalProvider extends AUDataModelProvider
{
	private static int numDeletes = 0;
	private HashMap<String, StudentPersonalType> students = new HashMap<String, StudentPersonalType>();
	private ObjectFactory dmObjectFactory = new ObjectFactory();
	
	/**
     * @param providerID The ID of the provider.
     * @param serviceProperties values of provider property file.
     */
    public StudentPersonalProvider(String providerID, AdvancedProperties serviceProperties)
    {
	    super(providerID, serviceProperties);
	    
	    // Load all students so that we can do some real stuff here.
	    String studentFile = serviceProperties.getPropertyAsString("provider.student.file.location", null);
	    if (studentFile != null)
	    {
			String inputXML = FileReaderWriter.getFileContent(studentFile);
			try
			{
				StudentCollectionType studentList = (StudentCollectionType)getUnmarshaller().unmarshalFromXML(inputXML, getMultiObjectClassInfo().getObjectType());
				if ((studentList != null) && (studentList.getStudentPersonal() != null))
				{
					for (StudentPersonalType studentPersonal : studentList.getStudentPersonal())
					{
						students.put(studentPersonal.getRefId(), studentPersonal);
					}
					logger.debug("Loaded "+students.size()+" students into memory.");
				}
			}
			catch (UnmarshalException ex)
			{
				ex.printStackTrace();
			}
	    }
    }

	/* (non-Javadoc)
     * @see sif3.common.provider.Provider#retrievByPrimaryKey(java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object retrievByPrimaryKey(String resourceID, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
    	if (StringUtils.isEmpty(resourceID))
    	{
    		throw new IllegalArgumentException("Resource ID is null or empty. It must be provided to retrieve an entity.");
    	}
    	
    	logger.debug("Retrieve student with Resoucre ID = "+resourceID);
    	
    	return students.get(resourceID);
    }

	/* (non-Javadoc)
     * @see sif3.common.provider.Provider#createSingle(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object createSingle(Object data, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
    	// Must be of type StudentPersonalType
    	if (data instanceof StudentPersonalType)
    	{
    		StudentPersonalType student = (StudentPersonalType)data;
    		if (StringUtils.isEmpty(student.getRefId()))
    		{
    			//In future this should be a UUID instead of a GUID
    			student.setRefId(UUIDGenerator.getSIF2GUIDUpperCase());
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
     * @see sif3.common.provider.Provider#updateSingle(java.lang.Object, java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public boolean updateSingle(Object data, String resourceID, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
    	if (StringUtils.isEmpty(resourceID))
    	{
    		throw new IllegalArgumentException("Resource ID is null or empty. It must be provided to update an entity.");
    	}
    	
    	// Must be of type StudentPersonalType
    	if (data instanceof StudentPersonalType)
    	{
    		logger.debug("Update student with Resoucre ID = "+resourceID);

    		//In the real implementation we would call a BL method here to modify the Student.
    		return true;
    	}
    	else
    	{
    		throw new IllegalArgumentException("Expected Object Type  = StudentPersonalType. Received Object Type = "+data.getClass().getSimpleName());
    	}
    }

	/* (non-Javadoc)
     * @see sif3.common.provider.Provider#deleteSingle(java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public boolean deleteSingle(String resourceID, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
    	if (StringUtils.isEmpty(resourceID))
    	{
    		throw new IllegalArgumentException("Resource ID is null or empty. It must be provided to delete an entity.");
    	}
    	
    	logger.debug("Remove student with Resoucre ID = "+resourceID);

    	//In the real implementation we would call a BL method here to remove the Student.
    	return ((numDeletes++ % 3) != 0);  // every third tome of the call I return false.
    }

    
	/* (non-Javadoc)
     * @see sif3.common.provider.Provider#retrive(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo)
     */
    @Override
    public Object retrieve(SIFZone zone, SIFContext context, PagingInfo pagingInfo) throws PersistenceException, UnsupportedQueryException
    {
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
     * @see sif3.common.provider.Provider#createMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> createMany(Object data, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
    	// Must be of type StudentPersonalType
    	if (data instanceof StudentCollectionType)
    	{
    		logger.debug("Create students (Bulk Operation)");

    		//In the real implementation we would call a BL method here to modify the Student.
    		ArrayList<OperationStatus> opStatus = new ArrayList<OperationStatus>();
    		opStatus.add(new OperationStatus(UUIDGenerator.getSIF2GUIDUpperCase(), 201));
    		opStatus.add(new OperationStatus(UUIDGenerator.getSIF2GUIDUpperCase(), 400, new ErrorDetails(400, "Data not good.")));
    		opStatus.add(new OperationStatus(UUIDGenerator.getSIF2GUIDUpperCase(), 201));
    		opStatus.add(new OperationStatus(UUIDGenerator.getSIF2GUIDUpperCase(), 201));
    		
    		return opStatus;
    	}
    	else
    	{
    		throw new IllegalArgumentException("Expected Object Type  = StudentCollectionType. Received Object Type = "+data.getClass().getSimpleName());
    	}
    }

	/* (non-Javadoc)
     * @see sif3.common.provider.Provider#updateMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> updateMany(Object data, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
    	// Must be of type StudentPersonalType
    	if (data instanceof StudentCollectionType)
    	{
    		logger.debug("Update students (Bulk Operation)");

    		//In the real implementation we would call a BL method here to modify the Student.
    		ArrayList<OperationStatus> opStatus = new ArrayList<OperationStatus>();
    		opStatus.add(new OperationStatus(UUIDGenerator.getSIF2GUIDUpperCase(), 200));
    		opStatus.add(new OperationStatus(UUIDGenerator.getSIF2GUIDUpperCase(), 200));
    		opStatus.add(new OperationStatus(UUIDGenerator.getSIF2GUIDUpperCase(), 400, new ErrorDetails(400, "Data not good for entry 3.")));
    		opStatus.add(new OperationStatus(UUIDGenerator.getSIF2GUIDUpperCase(), 400, new ErrorDetails(400, "Data not good for entry 4.")));
    		opStatus.add(new OperationStatus(UUIDGenerator.getSIF2GUIDUpperCase(), 200));
    		
    		return opStatus;
    	}
    	else
    	{
    		throw new IllegalArgumentException("Expected Object Type  = StudentCollectionType. Received Object Type = "+data.getClass().getSimpleName());
    	}
    }

	/* (non-Javadoc)
     * @see sif3.common.provider.Provider#deleteMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> deleteMany(List<String> resourceIDs, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException
    {
      //In the real implementation we would call a BL method here to modify the Student.
      ArrayList<OperationStatus> opStatus = new ArrayList<OperationStatus>();
      int i=0;
      for (String resourceID : resourceIDs)
      {
        if ((i % 3) == 0)
        {
          opStatus.add(new OperationStatus(resourceID, 404, new ErrorDetails(404, "Student with GUID = "+resourceID+" does not exist.")));
        }
        else
        {
          opStatus.add(new OperationStatus(resourceID, 200));
        }
        i++;
      }
	    return null;
    }

    /*--------------------------------------*/
    /*-- Other required Interface Methods --*/
    /*--------------------------------------*/
    @Override
    public ModelObjectInfo getSingleObjectClassInfo()
    {
	    return ModelObjectConstants.STUDENT_PERSONAL ;
    }

	@Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
	    return ModelObjectConstants.STUDENT_PERSONALS;
    }
}
