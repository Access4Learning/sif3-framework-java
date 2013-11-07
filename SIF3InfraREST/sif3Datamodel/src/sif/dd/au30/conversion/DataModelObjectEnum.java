package sif.dd.au30.conversion;

import org.apache.log4j.Logger;

public class DataModelObjectEnum
{
  protected static final Logger logger = Logger.getLogger(DataModelObjectEnum.class);

  public enum DataModel
  {
    StudentCollectionType,
    StudentPersonalType,
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
