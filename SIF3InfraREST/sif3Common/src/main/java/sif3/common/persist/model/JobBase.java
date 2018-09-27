/*
 * JobBase.java
 * Created: 6 Feb 2018
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

package sif3.common.persist.model;

import java.io.Serializable;

/**
 * @author Joerg Huber
 *
 */
public class JobBase implements Serializable
{
    private static final long serialVersionUID = 8949924746543581132L;

    private long internalID;  // sequence: auto generated
    private String jobID = null; // Job UUID or RefID
    private String serviceName = null;
    private String environmentID = null; // Environment UUID or RefID
    private String adapterType = null; //CONSUMER, PROVIDER, ENVIRONMENT_PROVIDER
    private String fingerprint = null;
    private String zoneID = null;
    private String contextID = null;
    private String jobXML = null;
    
    public JobBase()
    {
        super();
    }

    public JobBase(long internalID)
    {
        super();
        setInternalID(internalID);
    }

    public long getInternalID()
    {
        return internalID;
    }

    public void setInternalID(long internalID)
    {
        this.internalID = internalID;
    }

    public String getJobID()
    {
        return jobID;
    }
    
    public void setJobID(String jobID)
    {
        this.jobID = jobID;
    }
    
    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getEnvironmentID()
    {
        return environmentID;
    }
    
    public void setEnvironmentID(String environmentID)
    {
        this.environmentID = environmentID;
    }
    
    public String getAdapterType()
    {
        return adapterType;
    }
    
    public void setAdapterType(String adapterType)
    {
        this.adapterType = adapterType;
    }
    
    public String getFingerprint()
    {
        return fingerprint;
    }
    
    public void setFingerprint(String fingerprint)
    {
        this.fingerprint = fingerprint;
    }
    
    public String getZoneID()
    {
        return zoneID;
    }
    
    public void setZoneID(String zoneID)
    {
        this.zoneID = zoneID;
    }
    
    public String getContextID()
    {
        return contextID;
    }
    
    public void setContextID(String contextID)
    {
        this.contextID = contextID;
    }
    
    public String getJobXML()
    {
        return jobXML;
    }

    public void setJobXML(String jobXML)
    {
        this.jobXML = jobXML;
    }

    @Override
    public String toString()
    {
        return "JobBase [internalID=" + internalID + ", jobID=" + jobID + ", serviceName="
                + serviceName + ", environmentID=" + environmentID + ", adapterType=" + adapterType
                + ", fingerprint=" + fingerprint + ", zoneID=" + zoneID + ", contextID=" + contextID
                + ", jobXML=" + jobXML + "]";
    }    
}
