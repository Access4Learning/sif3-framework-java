/*
 * JobTemplate.java
 * Created: 21/12/2017
 *
 * Copyright 2017 Systemic Pty Ltd
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
 * POJO to encapsulate Job Template Names.
 * 
 * @author Joerg Huber
 *
 */
public class JobTemplate implements Serializable
{
  private static final long serialVersionUID = 159185591190684440L;
  
    private long     templateID;
    private String   urlName          = null;
    private String   adapterType;  //CONSUMER, PROVIDER, ENVIRONMENT_PROVIDER
    private String   templateFileName = null;  // Must be full name including file extension.

    public long getTemplateID()
    {
        return templateID;
    }

    public void setTemplateID(long templateID)
    {
        this.templateID = templateID;
    }

    public String getUrlName()
    {
        return urlName;
    }

    public void setUrlName(String urlName)
    {
        this.urlName = urlName;
    }

    public String getAdapterType()
    {
        return adapterType;
    }

    public void setAdapterType(String adapterType)
    {
        this.adapterType = adapterType;
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
        return "JobTemplate [templateID=" + templateID + ", urlName=" + urlName + ", adapterType="
                + adapterType + ", templateFileName=" + templateFileName + "]";
    }
}
