/*
 * ExtendedJobInfo.java
 * Created: 12 Jun 2018
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

package sif3.infra.common.env.types;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.common.exception.MarshalException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.persist.model.SIF3Job;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.model.JobType;

/**
 * This is a utility class that holds extended job information. In the functional service resource the job and related
 * housekeeping data needs to be moved around. For efficiency reason, avoiding unnecessary DB accesses, this object can be used.
 * 
 * @author Joerg Huber
 */
public class ExtendedJobInfo implements Serializable
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    private static final long serialVersionUID = 2474434076004433430L;
    
    private SIF3Job dbJob = null;
    private JobType xmlJob = null;
    private boolean isXMLValid = true;
    
    public ExtendedJobInfo()
    {
        super();
    }
    
    /**
     * This will set the dbJob property of this class but it will also unmarshal the dbJob.jobXML property into the 
     * xmlJob property of this class. If this succeeds then the property "xmlValid" is set to true.
     * 
     * @param dbJob The job to be set.
     */
    public ExtendedJobInfo(SIF3Job dbJob)
    {
        super();
        setDBJob(dbJob);
    }
    
    public SIF3Job getDBJob()
    {
        return dbJob;
    }
    
    /**
     * This will set the dbJob property of this class but it will also unmarshal the dbJob.jobXML property into the 
     * xmlJob property of this class. If this succeeds then the property "xmlValid" is set to true.
     * 
     * @param dbJob The job to be set.
     */
    public void setDBJob(SIF3Job dbJob)
    {
        this.dbJob = dbJob;
        setXMLJob(unmarschalJobXML(dbJob.getJobXML()));
    }
    
    public JobType getXMLJob()
    {
        return xmlJob;
    }
    
    public void setXMLJob(JobType xmlJob)
    {
        this.xmlJob = xmlJob;
    }
    
    public boolean isXMLValid()
    {
        return isXMLValid;
    }
    
    /**
     * This method attempts to marshal the given JobXML into a String and then assign it to the dbJob.jobXML property.
     * If it succeeds then true is returned. Any failure will return false and an error is logged.
     * 
     * @return See Desc.
     */
    public boolean marshalXMLintoDBJob()
    {
        if (isXMLValid())
        {
            try
            {
                getDBJob().setJobXML(createJobXML(getXMLJob()));
                return true;
            }
            catch (MarshalException ex)
            {
                logger.error(("Failed to marshal the Job Data into XML: "+ex.getMessage()));
            }
        }
        else
        {
            logger.error("There is no valid Job Data available to be marshaled to a XML.");
        }
        
        return false;
    }

    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    
    /*
     * Unmarshal the Job XML to a proper Job.
     * 
     * @param jobXML The JobXML to unmarshal to Job Object.
     * 
     * @return The Job Object. Can be null if XML is null or empty or if the XML is not a JobType. In both cases an error is logged.
     * 
     * @throws MarshalException Invalid data. Could not marshal. Error logged already. 
     */
    private JobType unmarschalJobXML(String jobXML)
    {
        if (jobXML == null)
        {
            logger.error("Job XML is null. Cannot unmrshal => Return null.");
            isXMLValid = false;
            return null;
        }
        InfraUnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
        try
        {
            return (JobType)unmarshaller.unmarshalFromXML(jobXML, JobType.class);
        }
        catch (UnmarshalException ex) // cannot happen here
        {
            logger.error("Invalid Job XML. Cannot unmrshal => Return null.\nOffending XML:\n"+jobXML);
            isXMLValid = false;
            return null;
        }
        catch (UnsupportedMediaTypeExcpetion e) // should not happen
        {
            isXMLValid = false;
            return null;
        }
    }
    
    /**
     * Marshal a Job Object to XML.
     * 
     * @param job The Job Object to marshal to XML.
     * 
     * @return The final XML string.
     * 
     * @throws MarshalException Invalid data. Could not marshal. Error logged already. 
     */
    private String createJobXML(JobType job) throws MarshalException
    {
        InfraMarshalFactory marshaller = new InfraMarshalFactory();
        try
        {
            return marshaller.marshalToXML(job);
        }
        catch (UnsupportedMediaTypeExcpetion ex) // cannot happen here
        {
            return null;
        }
    }

    
}
