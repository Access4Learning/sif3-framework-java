/*
 * PropertyManager.java
 *
 * Copyright 2003-2014 Systemic Pty Ltd
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
package au.com.systemic.framework.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class allows to load any number of property files and keep their content in a singleton to avoid poroperty files to be read over
 * and over again. Most of the time developers keep them in a singleton anyway but have to implement this themselves. This class provides
 * a simple utility to access porperty files as singleton. When property files are loaded through this manager each property file must have
 * a unique ID, through wich it will be accessed. See load() methods for details.
 * 
 * @author Joerg Huber
 */
public class PropertyManager
{
  protected Logger logger = LoggerFactory.getLogger(getClass());

  private HashMap<String, AdvancedProperties> properties = new HashMap<String, AdvancedProperties>();
  
  private static PropertyManager instance = null;

  public static PropertyManager getInstance()
  {
    if (instance == null){
      instance = new PropertyManager();
    }
    
    return instance;
  }
  
  /**
   * Will load the given porperty file and assign it the ID of the propertyID. After a successful loading the properties can be accessed
   * using this ID. If there is already a property file loaded with that ID then no action is taken (i.e. It won't be loaded again) if
   * forceReload=FALSE. If you wish to reload the properies again (i.e remove existing and relaod) then forceReload must be set to TRUE. 
   * 
   * @param fileNameWithoutExt
   * @param propertyID
   * @param forceReload
   */
  public void loadPropertyFile(String fileNameWithoutExt, String propertyID, boolean forceReload)
  {
    String id = getPropertID(fileNameWithoutExt, propertyID);
    AdvancedProperties props = properties.get(id);
    if (props == null) // new, so load it
    {
      loadProperties(fileNameWithoutExt, id);
    }
    else if (forceReload) // force a reload again
    {
      properties.remove(id);
      loadProperties(fileNameWithoutExt, id);
    }
  }

  /**
   * Convenience method. It will call the loadPropertyFile(fileNameWithoutExt, null, false) which means the property ID will be the file name
   * and if the properties for that file are already loaded it WON'T relaod it again.
   * 
   * @param fileNameWithoutExt
   */
  public void loadPropertyFile(String fileNameWithoutExt)
  {
    loadPropertyFile(fileNameWithoutExt, null, false);
  }
  
  /*--------------------------------------------------------------------------------------------------------------------------------*/
  /*-- This section needs to replicate the methods from the AdvancedProperies Class put add the parameter of the proprrty file ID --*/
  /*--------------------------------------------------------------------------------------------------------------------------------*/
  /**
   * Can return null if no properties file with the given ID is loaded otherwise the properties are returned in form of an
   * AddvancedProperties class.
   */
  public AdvancedProperties getProperties(String propFileID)
  {
    return properties.get(propFileID);
  }
  
  public boolean isLoaded(String propFileID)
  {
    return (properties.get(propFileID) != null) && (properties.get(propFileID).isLoaded());
  }

  /**
   * Returns the given property as a boolean object. If it doesn't exist or is not a boolean then null is
   * returned.
   */
  public Boolean getPropertyAsBool(String propFileID, String propertyName)
  {
    AdvancedProperties props = getProperties(propFileID);
    if (props != null)
    {
      return props.getPropertyAsBool(propertyName);
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns the given property as a boolean. If it doesn't exist or is not a boolean then the default 
   * value is returned.
   */
  public boolean getPropertyAsBool(String propFileID, String propertyName, boolean defaultValue)
  {
    AdvancedProperties props = getProperties(propFileID);
    if (props != null)
    {
      return props.getPropertyAsBool(propertyName, defaultValue);
    }
    else
    {
      return defaultValue;
    }
  }
  
  /**
   * Returns the given property as a Integer object. If it doesn't exist or is not an Integer then null is
   * returned.
   */
  public Integer getPropertyAsInt(String propFileID, String propertyName)
  {
    AdvancedProperties props = getProperties(propFileID);
    if (props != null)
    {
      return props.getPropertyAsInt(propertyName);
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns the given property as a int. If it doesn't exist or is not an Integer then the default value
   * is returned.
   */
  public int getPropertyAsInt(String propFileID, String propertyName, int defaultValue)
  {
    AdvancedProperties props = getProperties(propFileID);
    if (props != null)
    {
      return props.getPropertyAsInt(propertyName, defaultValue);
    }
    else
    {
      return defaultValue;
    }
  }

  /**
   * Returns the given property as a String. If it doesn't exist or is empty then the default value is returned.
   */
  public String getPropertyAsString(String propFileID, String propertyName, String defaultValue)
  {
    AdvancedProperties props = getProperties(propFileID);
    if (props != null)
    {
      return props.getPropertyAsString(propertyName, defaultValue);
    }
    else
    {
      return defaultValue;
    }
  }
  
  /**
   * This method reads the given property and then attempts to split the value at the commas. This allows to read a comma separated
   * property value. The result is returned as a List of Strings. If the property doesn't exist or there is no value defined then
   * an empty list is returned.
   * 
   * @param propertyName
   * 
   * @return See desc.
   */
  public List<String> getFromCommaSeparated(String propFileID, String propertyName)
  {
    AdvancedProperties props = getProperties(propFileID);
    List<String> list = new ArrayList<String>();
    if (props != null)
    {
      list = props.getFromCommaSeparated(propertyName);
    }    
    return list;
  }
  
  
  
  @Override
  public String toString()
  {
    return "PropertyManager [properties=" + properties + "]";
  }

  /*---------------------*/
  /*-- Private Methods --*/
  /*---------------------*/
  private PropertyManager()
  {
    super();
  }
    
  private String getPropertID(String fileNameWithoutExt, String propertyID)
  {
    return ((StringUtils.isEmpty(propertyID)) ? fileNameWithoutExt : propertyID);
  }
  
  private void loadProperties(String fileNameWithoutExt, String id)
  {
    AdvancedProperties props = new AdvancedProperties(fileNameWithoutExt);
    if (props.isLoaded())
    {
      properties.put(id, props);
    }
    else
    {
      logger.error("Failed to load property file with the name "+props.getPropFileNameFull()+" Ensure it exists and is on the classpath.");
    }
  }

}
