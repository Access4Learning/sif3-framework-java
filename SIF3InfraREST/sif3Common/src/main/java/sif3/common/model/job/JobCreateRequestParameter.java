/*
 * JobRequestParameter.java
 * Created: 22 Feb 2018
 *
 * Copyright 2018 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License. 
 */

package sif3.common.model.job;

import java.io.Serializable;

import org.w3c.dom.Element;

/**
 * As part of creating a job by a consumer the consumer can pass some "initialising" values to the job. The initial values 
 * relate to a particular phase and can hold any data structure. The "any data structure" is indicated by an xs:anyType in
 * the infrastructure data model for a job.
 * Because this framework uses JAXB as its underlying serialiser for the infrastructure data model the xs:anyType is encapsulated
 * into a org.w3c.dom.Element. This class abstracts the initialisation part of the Job object accordingly.
 * 
 * The "unfortunate" side effect of having an xs:anyType in the initialisation section of a Job object is that consumers and
 * providers have to programmatically inspect the Element to extract actual parameter data. The framework cannot further 
 * support that as an xs:anyType can be of any structure and depends entirely on the implementation of the functional service
 * what that structure is. The framework is agnostic to this initialisation structure.
 * 
 * @author Joerg Huber
 */
public class JobCreateRequestParameter implements Serializable
{
    private static final long serialVersionUID = -2397727515320241805L;
    
    private String jobID = null;
    private String initPhaseName = null;
//    private List<AttributeValue> initParams = null;
    private Element initParams = null;
    
    public JobCreateRequestParameter()
    {
        this(null, null);
    }

    public JobCreateRequestParameter(String initPhaseName, Element initParams)
    {
        super();
        setInitParams(initParams);
        setInitPhaseName(initPhaseName);
    }
    
    public String getJobID()
    {
        return jobID;
    }

    public void setJobID(String jobID)
    {
        this.jobID = jobID;
    }

     public String getInitPhaseName()
    {
        return initPhaseName;
    }
    
    public void setInitPhaseName(String initPhaseName)
    {
        this.initPhaseName = initPhaseName;
    }
    
    public Element getInitParams()
    {
        return initParams;
    }
    
    public void setInitParams(Element initParams)
    {
        this.initParams = initParams;
    }
    
    @Override
    public String toString()
    {
        return "JobCreateRequestParameter [jobID=" + jobID + ", initPhaseName=" + initPhaseName
                + ", initParams=" + initParams + "]";
    }
}
