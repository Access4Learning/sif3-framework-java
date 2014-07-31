/*
 * EnvironmentTemplate.java
 * Created: 30/06/2014
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
package sif3.common.persist.model;

import java.io.Serializable;

/**
 * POJO to encapsulate Environment Template Names.
 * 
 * @author Joerg Huber
 *
 */
public class EnvironmentTemplate implements Serializable
{
  private static final long serialVersionUID = -1291855911906629440L;
  
  private String templateID = null;
  private String templateFileName = null; // Must be full name including file extension.
  
  public String getTemplateID()
  {
    return templateID;
  }
  
  public void setTemplateID(String templateID)
  {
    this.templateID = templateID;
  }
  
  public String getTemplateFileName()
  {
    return templateFileName;
  }
  
  public void setTemplateFileName(String templateFileName)
  {
    this.templateFileName = templateFileName;
  }
  
  @Override
  public String toString()
  {
    return "EnvironmentTemplate [templateFileName=" + templateFileName + ", templateID=" + templateID + "]";
  }
}
