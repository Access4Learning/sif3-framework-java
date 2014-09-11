/*
 * SchoolInfoProvider.java
 * Created: 18/03/2014
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import sif.dd.au30.model.ObjectFactory;
import sif.dd.au30.model.SchoolCollectionType;
import sif.dd.au30.model.SchoolInfoType;
import sif.dd.au30.model.StudentCollectionType;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import systemic.sif3.demo.rest.ModelObjectConstants;
import au.com.systemic.framework.utils.FileReaderWriter;
import au.com.systemic.framework.utils.StringUtils;

/**
 * @author Joerg Huber
 *
 */
public class SchoolInfoProvider extends AUDataModelProvider
{
	private static int numDeletes = 0;
	private HashMap<String, SchoolInfoType> schools = new HashMap<String, SchoolInfoType>();
	private ObjectFactory dmObjectFactory = new ObjectFactory();
	
	/**
     */
    public SchoolInfoProvider()
    {
	    super();
	    
	    // Load all students so that we can do some real stuff here.
	    String schoolFile = getServiceProperties().getPropertyAsString("provider.school.file.location", null);
	    if (schoolFile != null)
	    {
			try
			{
				String inputXML = FileReaderWriter.getFileContent(schoolFile);
				SchoolCollectionType schoolList = (SchoolCollectionType)getUnmarshaller().unmarshalFromXML(inputXML, getMultiObjectClassInfo().getObjectType());
				if ((schoolList != null) && (schoolList.getSchoolInfo() != null))
				{
					for (SchoolInfoType schoolInfo : schoolList.getSchoolInfo())
					{
						schools.put(schoolInfo.getRefId(), schoolInfo);
					}
					logger.debug("Loaded "+schools.size()+" schools into memory.");
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				logger.debug("Loaded "+schools.size()+" schools into memory.");
			}
	    }
    }

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
    	
    	logger.debug("Retrieve student with Resoucre ID = "+resourceID);
    	
    	return schools.get(resourceID);
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#createSingle(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object createSingle(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	// Must be of type StudentPersonalType
    	if (data instanceof SchoolInfoType)
    	{
    		SchoolInfoType school = (SchoolInfoType)data;
    		if (StringUtils.isEmpty(school.getRefId()))
    		{
    			//In future this should be a UUID instead of a GUID
    		  if (!useAdvisory)
    		  {
    		    // Create new UUID because the advisory shall not be used.
    		    school.setRefId(UUIDGenerator.getSIF2GUIDUpperCase());
    		  }
    		  // else leave student UUID untouched.
    		}
    		
    		//In the real implementation we would call a BL method here to create the Student.
    		return school;
    	}
    	else
    	{
    		throw new IllegalArgumentException("Expected Object Type  = SchoolInfoType. Received Object Type = "+data.getClass().getSimpleName());
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
    	if (data instanceof SchoolInfoType)
    	{
    		logger.debug("Update school with Resoucre ID = "+resourceID);

    		//In the real implementation we would call a BL method here to modify the Student.
    		return true;
    	}
    	else
    	{
    		throw new IllegalArgumentException("Expected Object Type  = SchoolInfoType. Received Object Type = "+data.getClass().getSimpleName());
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
    	
    	logger.debug("Remove SchoolInfo with Resoucre ID = "+resourceID);

    	//In the real implementation we would call a BL method here to remove the Student.
    	return ((numDeletes++ % 3) != 0);  // every third tome of the call I return false.
    }

    
	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#retrive(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo)
     */
    @Override
    public Object retrieve(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException
    {
    	ArrayList<SchoolInfoType> schoolList = new ArrayList<SchoolInfoType>();
    	if (pagingInfo == null) //return all
    	{
    		schoolList.addAll(schools.values());
    	}
    	else
    	{
    		pagingInfo.setTotalObjects(schools.size());
    		if ((pagingInfo.getPageSize() * (pagingInfo.getCurrentPageNo()+1)) > schools.size())
    		{
    			return null; // Requested page outside of limits.
    		}
    		
    		// retrieve applicable students
    		Collection<SchoolInfoType> allSchools = schools.values();
    		int i = 0;
    		int startPos = pagingInfo.getPageSize() * pagingInfo.getCurrentPageNo();
    		int endPos = startPos + pagingInfo.getPageSize();
    		for (Iterator<SchoolInfoType> iter = allSchools.iterator(); iter.hasNext();)
    		{
    			SchoolInfoType school = iter.next();
    			if ((i>=startPos) && (i<endPos))
    			{
    				schoolList.add(school);
    			}
    			i++;
    		}
    	}
    	
    	SchoolCollectionType schoolCollection = dmObjectFactory.createSchoolCollectionType();
    	schoolCollection.getSchoolInfo().addAll(schoolList);
	    return schoolCollection;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#createMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<CreateOperationStatus> createMany(Object data, boolean useAdvisory, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
    	// Must be of type StudentPersonalType
    	if (data instanceof SchoolCollectionType)
    	{
    		logger.debug("Create schools (Bulk Operation)");
    		SchoolCollectionType schools = (SchoolCollectionType)data;
        ArrayList<CreateOperationStatus> opStatus = new ArrayList<CreateOperationStatus>();
        int i=0;
        for (SchoolInfoType school : schools.getSchoolInfo())
        {
          if ((i % 3) == 0)
          {
				// Set advisoryID the same as resourceID.
            opStatus.add(new CreateOperationStatus(school.getRefId(), school.getRefId(), 404, new ErrorDetails(400, "Data not good.")));
          }
          else
          {
            if (useAdvisory)
            {
				// Advisory refId was used. Set resourceId and advisoryId to the same
              opStatus.add(new CreateOperationStatus(school.getRefId(), school.getRefId(), 201));
            }
            else
            {
				// Create a new refId (resourceID) but we must also report back the original RefId as the advisory if it was available.
              opStatus.add(new CreateOperationStatus(UUIDGenerator.getSIF2GUIDUpperCase(), school.getRefId(), 201));
            }
          }
          i++;
        }

    		return opStatus;
    	}
    	else
    	{
    		throw new IllegalArgumentException("Expected Object Type  = SchoolCollectionType. Received Object Type = "+data.getClass().getSimpleName());
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
    		logger.debug("Update schools (Bulk Operation)");
    		SchoolCollectionType schools = (SchoolCollectionType)data;
        ArrayList<OperationStatus> opStatus = new ArrayList<OperationStatus>();
    		int i=0;
    		for (SchoolInfoType school : schools.getSchoolInfo())
    		{
          if ((i % 3) == 0)
          {
            opStatus.add(new OperationStatus(school.getRefId(), 404, new ErrorDetails(404, "School with GUID = "+school.getRefId()+" does not exist.")));
          }
          else
          {
            opStatus.add(new OperationStatus(school.getRefId(), 200));
          }
          i++;
    		}
    		
    		return opStatus;
    	}
    	else
    	{
    		throw new IllegalArgumentException("Expected Object Type  = SchoolCollectionType. Received Object Type = "+data.getClass().getSimpleName());
    	}
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.Provider#deleteMany(java.lang.Object, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> deleteMany(List<String> resourceIDs, SIFZone zone, SIFContext context, RequestMetadata metadata) throws IllegalArgumentException, PersistenceException
    {
      //In the real implementation we would call a BL method here to modify the Student.
      ArrayList<OperationStatus> opStatus = new ArrayList<OperationStatus>();
      int i=0;
      for (String resourceID : resourceIDs)
      {
        if ((i % 3) == 0)
        {
          opStatus.add(new OperationStatus(resourceID, 404, new ErrorDetails(404, "School with GUID = "+resourceID+" does not exist.")));
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
	    return ModelObjectConstants.SCHOOL_INFO;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
	    return ModelObjectConstants.SCHOOL_INFOS;
    }
}
