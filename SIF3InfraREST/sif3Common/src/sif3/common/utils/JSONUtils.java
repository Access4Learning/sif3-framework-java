/*
 * JSONUtils.java
 * Created: 23/06/2014
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
package sif3.common.utils;

import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.apache.log4j.Logger;

import sif3.common.exception.MarshalException;
import sif3.common.exception.UnmarshalException;
import au.com.systemic.framework.utils.StringUtils;
import au.com.systemic.framework.utils.Timer;

public class JSONUtils
{
  protected static final Logger logger = Logger.getLogger(JSONUtils.class);

  /**
   * This method converts a JSON string to a XML String as defined in http://www.xml.com/pub/a/2006/05/31/converting-between-xml-and-json.html. 
   * If this operation fails then an exception is thrown and the error is logged. If the input string is empty or null then null is returned.
   * 
   * @param jsonStr The JSON String
   * 
   * @return An XML String converted from the JSON string according to the rules in http://www.xml.com/pub/a/2006/05/31/converting-between-xml-and-json.html
   * 
   * @throws Exception A exception is thrown if the conversion fails which is typically the case if the JSON String is invalid.
   */
  public static String fromJSON(String jsonStr) throws UnmarshalException
  {
    if (StringUtils.isEmpty(jsonStr))
    {
      return null;
    }
    try
    {
      return unmarshalFromJSON((JSONObject) JSONSerializer.toJSON(jsonStr));
    }
    catch (Exception ex)
    {
      logger.error("The following JSON string may not be a valid JSON:\n" + jsonStr);
      throw new UnmarshalException("The following JSON string may not be a valid JSON:\n" + jsonStr, ex);
    }
  }

  /**
   * This method marshals a given XML stringt to a JSON string according to the rules in 
   * http://www.xml.com/pub/a/2006/05/31/converting-between-xml-and-json.html. If this operation fails then an exception is thrown and the error 
   * is logged. If the input string is empty or null then null is returned.
   * 
   * @param xmlStr The XML string to be converted to JSON.
   * 
   * @return JSON string of the given XML string according to the rules in in http://www.xml.com/pub/a/2006/05/31/converting-between-xml-and-json.html.
   */
  public static String toJSON(String xmlStr) throws MarshalException
  {
    if (StringUtils.isEmpty(xmlStr))
    {
      return null;
    }
    try
    {
      return marshalToJSON(xmlStr).toString();
    }
    catch (Exception ex)
    {
      logger.error("The following XML string could not be marshalled to JSON:\n" + xmlStr);
      throw new MarshalException("The following XML string could not be marshalled to JSON:\n" + xmlStr, ex);
    }
  }

  /*---------------------*/
  /*-- Private Methods --*/
  /*---------------------*/
  /*
   * The expected JSON String must have the format of: {"<SIF_Object_Name>": {...}}
   * 
   * Example:
   * {"StudentPersonal":{"@RefId":"ADE41209E3A412ECBADE41209E3A412E","MedicalAlertMessages":{...}}}
   * 
   * This method assumes the above structure otherwise the unmarshal won't work!
   */
  private static String unmarshalFromJSON(JSONObject jsonObject) throws Exception
  {
      Timer timer = null; 
      if (logger.isDebugEnabled())
      {
        timer = new Timer();
        timer.start();
      }
      XMLSerializer xmlSerialiser = new XMLSerializer();
      
      // Avoids the 'type' attribute to be added to each XML element
      xmlSerialiser.setTypeHintsEnabled(false);
      
      // we must get the Root element name (i.e StudentPersonal) out of the json string. It also means that the current
      // JSON object can only have one sub-object that being the StudentPersonal for example. If it has more than one
      // Sub-Object then the structure is wrong according to the SIF REST Direct guidelines.
      logger.debug("Number of Sub-Objects (should only be 1!): "+jsonObject.size());
      if (jsonObject.size() > 1)
      {
        throw new IllegalArgumentException("The given JSON string is invalid. The first key/value pair must be the SIF Object Name (i.e. {\"StudentPersonal\":{...}");
      }
      
      // If we get here then there is only one Sub-Object. Grab the name of it...
      String sifObjName = (String)jsonObject.names().get(0);
      
      // Go down one level (i.e. pretend to remove the 'root' node such as StudentPersonal). This must be done otherwise
      // the resulting SIFObject will have the root node twice.
      jsonObject = (JSONObject)jsonObject.get(sifObjName);

      processArrayTags(jsonObject);
        
      // Ensure that the root element of the object is not <o>...</o> rather the name of the SIF Object
      xmlSerialiser.setRootName(sifObjName);
      
      try
      {
        /*-----------------------------------------------------------------------------------------------------------------------------------
         * Right now it appears that all opening tags have a leading space before the '>'. This could be a problem but must be investigated.
         * This is, I believe a 'bug' in the underlying json-lib library. Maybe we need to fix this in there. If we cannot and it proves
         * to be a problem then the code below is a fix. Not the nicest way but it will work. For right now it is not used and commented out
         * but we may need to change this later.
         *----------------------------------------------------------------------------------------------------------------------------------*/
    	//return xmlSerialiser.write(jsonObject).replace(" >", ">");
        /*-----------------------------------------------------------------------------------------------------------------------------------
         * End potential fix above.
         *----------------------------------------------------------------------------------------------------------------------------------*/

        return xmlSerialiser.write(jsonObject);
      }
      finally
      {
        if (logger.isDebugEnabled())
        {
          timer.finish();
          logger.debug("Time taken to unmarshal "+sifObjName+" JSON->XML: "+timer.timeTaken()+"ms");
        }
      }
  }

  private static JSONObject marshalToJSON(String xmlStr) throws Exception
  {
      Timer timer = null; 
      if (logger.isDebugEnabled())
      {
        timer = new Timer();
        timer.start();
      }
      XMLSerializer xmlSerialiser = new XMLSerializer();
      
      // The next statement ensures that there are no naming conflicts if the XML has an attribute with the name 'type'.
      xmlSerialiser.setTypeHintsCompatibility(false);

      //The next line will add the root element name (i.e StudentPersonal) to the JSON Object 
      xmlSerialiser.setForceTopLevelObject(true);
      
      try
      {
        return (JSONObject)xmlSerialiser.read(xmlStr);  
      }
      finally
      {
        if (logger.isDebugEnabled())
        {
          timer.finish();
          logger.debug("Time taken to marshal XML->JSON: "+timer.timeTaken()+"ms");
        }
      }
  }

  @SuppressWarnings({ "unchecked" })
  private static void processArrayTags(JSONObject json)
  {
    for (String key : (Set<String>)json.keySet())
    {
      Object obj = json.get(key);
      if (obj instanceof JSONObject)
      {
        processArrayTags((JSONObject)obj);
      }
      else if (obj instanceof JSONArray)
      {
        processArrayTags((JSONArray)obj);
      }
    }
  }

  private static void processArrayTags(JSONArray jsonArray)
  {
    jsonArray.setExpandElements(true);
    for (int i=0; i<jsonArray.size(); i++)
    {
      if (jsonArray.get(i) instanceof JSONObject)
      {
        processArrayTags((JSONObject)jsonArray.get(i));
      }
      else if (jsonArray.get(i) instanceof JSONArray)
      {
        processArrayTags((JSONArray)jsonArray.get(i));        
      }
    }  
  }
}
