/*
 * DataModelObjectEnumUS.java
 * Created: 11/10/2013
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

package sif.dd.us30.conversion;

import org.apache.log4j.Logger;


/**
 * @author Joerg Huber
 *
 */
public class DataModelObjectEnumUS
{
	  protected static final Logger logger = Logger.getLogger(DataModelObjectEnumUS.class);

	  public enum DataModel
	  {
	    Students,
	    Student,
	  };
	  
	  public static DataModel getDataModelEnum(Object obj)
	  {
	    if (obj != null)
	    {
	      return getDataModelEnum(obj.getClass().getSimpleName());
	    }
	    return null;
	  }
	  
	  public static DataModel getDataModelEnum(String dataModelStr)
	  {
	    try
	    {
	      return DataModel.valueOf(dataModelStr);
	    }
	    catch (Exception ex)
	    {
	      logger.error("Try to convert '"+dataModelStr+"' into a DataModelObjectEnum.DataModel enumeration failed: "+ex.getMessage());
	      return null;
	    }
	  }

}
