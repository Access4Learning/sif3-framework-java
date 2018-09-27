/*
 * ProviderEnvironment.java
 * Created: 15/09/2013
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

package sif3.infra.common.env.types;

import java.net.URI;
import java.util.HashSet;

import sif3.common.CommonConstants.AuthenticationType;
import sif3.common.CommonConstants.JobState;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.UpdateType;

/**
 * This class represents a provider environment. This is a more complex class than a consumer environment as it has to validate a
 * number of values when a consumer attempts to connect.
 * 
 * @author Joerg Huber
 *
 */
public class ProviderEnvironment extends EnvironmentInfo
{
    private static final long serialVersionUID = 6469968451023182574L;
    public static final String JOB_HOUSEKEEPING_CRON = "0 0 2 * * ?"; // once a day at 2am
    
	private boolean              connected              = false;
	private String               templateXMLFileName    = null;
	private URI                  secureConnectorBaseURI = null; // Secure URI to connect to Provider 
	private URI                  connectorBaseURI       = null; // URI to connect to Provider
	private boolean 			 eventsSupported 		= false;
	private UpdateType           defaultUpdateType      = UpdateType.FULL; // Default value for update events.
	private boolean              autoCreateEnvironment  = false;
	private String accessTokenAuthMethod = AuthenticationType.Basic.name(); // Default Auth Method if accessToken is used.
	private boolean              allowAuthOnURL         = false;
	private HeaderProperties     customResponseHeaders  = new HeaderProperties(); 
	
	// Functional Service Properties
	private boolean              jobEnabled             = false; // default 
    private HashSet<JobState>    jobEndStates           = new HashSet<JobState>();
	private int                  jobEventFrequency      = 900; // default is 15 min = 900 seconds
	private int                  jobEventKeepDays       = 30; // default 30 days
    private int                  jobKeepDays            = 30; // default 30 days
	private String               jobHousekeepingCron    = JOB_HOUSEKEEPING_CRON;
	
    /**
	 * Constructor
	 * 
	 * @param serviceName The name of the service/adapter.
	 */
	public ProviderEnvironment(String serviceName)
	{
		super();
		setAdapterName(serviceName);
	}
 
	public boolean getIsConnected()
    {
    	return this.connected;
    }

	public void setConnected(boolean connected)
    {
    	this.connected = connected;
    }

	public String getTemplateXMLFileName()
    {
    	return this.templateXMLFileName;
    }

	public void setTemplateXMLFileName(String templateXMLFileName)
    {
    	this.templateXMLFileName = templateXMLFileName;
    }

	public URI getSecureConnectorBaseURI()
    {
    	return this.secureConnectorBaseURI;
    }

	public void setSecureConnectorBaseURI(URI secureConnectorBaseURI)
    {
    	this.secureConnectorBaseURI = secureConnectorBaseURI;
    }

	public void setSecureConnectorBaseURI(String secureConnectorBaseURI)
	{
		try
		{
			this.secureConnectorBaseURI = new URI(secureConnectorBaseURI);
		}
		catch (Exception ex)
		{
			this.secureConnectorBaseURI = null;
		}
	}

	public URI getConnectorBaseURI()
    {
    	return this.connectorBaseURI;
    }

	public void setConnectorBaseURI(URI connectorBaseURI)
    {
    	this.connectorBaseURI = connectorBaseURI;
    }

	public boolean getEventsSupported()
	{
		return this.eventsSupported;
	}

	public void setEventsSupported(boolean eventsSupported)
	{
		this.eventsSupported = eventsSupported;
	}

	public void setConnectorBaseURI(String connectorBaseURI)
	{
		try
		{
			this.connectorBaseURI = new URI(connectorBaseURI);
		}
		catch (Exception ex)
		{
			this.connectorBaseURI = null;
		}
	}
  
	public UpdateType getDefaultUpdateType()
    {
    	return this.defaultUpdateType;
    }

	public void setDefaultUpdateType(UpdateType defaultUpdateType)
    {
		if (defaultUpdateType == null)
		{
			this.defaultUpdateType = UpdateType.FULL;
		}
		else
		{
			this.defaultUpdateType = defaultUpdateType;
		}
    }

	public boolean getAutoCreateEnvironment()
    {
    	return this.autoCreateEnvironment;
    }

	public void setAutoCreateEnvironment(boolean autoCreateEnvironment)
    {
    	this.autoCreateEnvironment = autoCreateEnvironment;
    }

	public String getAccessTokenAuthMethod()
    {
    	return accessTokenAuthMethod;
    }

	public void setAccessTokenAuthMethod(String accessTokenAuthMethod)
    {
    	this.accessTokenAuthMethod = accessTokenAuthMethod;
    }

    public boolean getAllowAuthOnURL()
    {
        return allowAuthOnURL;
    }

    public void setAllowAuthOnURL(boolean allowAuthOnURL)
    {
        this.allowAuthOnURL = allowAuthOnURL;
    }

    /**
     * To ensure no accidental override of these default values when adding stuff to potentially copies of this property we
     * create a true copy of the HeaderProperties rather than a shallow copy as the default getter would be.
     *  
     * @return TRUE copy of Custom Response Header properties from the provider propert file. 
     */
    public HeaderProperties getCustomResponseHeaders()
    {
        return (customResponseHeaders == null) ? new HeaderProperties() : new HeaderProperties(customResponseHeaders.getHeaderProperties());
    }

    public void setCustomResponseHeaders(HeaderProperties customResponseHeaders)
    {
        if (customResponseHeaders == null)
        {
            this.customResponseHeaders = new HeaderProperties();
        }
        else
        {
            this.customResponseHeaders = customResponseHeaders;
        }
    }
    
    public HashSet<JobState> getJobEndStates()
    {
        return jobEndStates;
    }

    public void setJobEndStates(HashSet<JobState> jobEndStates)
    {
        this.jobEndStates = jobEndStates;
    }


    public boolean isJobEnabled()
    {
        return jobEnabled;
    }

    public void setJobEnabled(boolean jobEnabled)
    {
        this.jobEnabled = jobEnabled;
    }

    public int getJobEventFrequency()
    {
        return jobEventFrequency;
    }

    public void setJobEventFrequency(int jobEventFrequency)
    {
        this.jobEventFrequency = jobEventFrequency;
    }

    public int getJobKeepDays()
    {
        return jobKeepDays;
    }

    public void setJobKeepDays(int jobKeepDays)
    {
        this.jobKeepDays = jobKeepDays;
    }

    public int getJobEventKeepDays()
    {
        return jobEventKeepDays;
    }

    public void setJobEventKeepDays(int jobEventKeepDays)
    {
        this.jobEventKeepDays = jobEventKeepDays;
    }

    public String getJobHousekeepingCron()
    {
        return jobHousekeepingCron;
    }

    public void setJobHousekeepingCron(String jobHousekeepingCron)
    {
        this.jobHousekeepingCron = jobHousekeepingCron;
    }

    @Override
    public String toString()
    {
        return "ProviderEnvironment [connected=" + connected + ", templateXMLFileName="
                + templateXMLFileName + ", secureConnectorBaseURI=" + secureConnectorBaseURI
                + ", connectorBaseURI=" + connectorBaseURI + ", eventsSupported=" + eventsSupported
                + ", defaultUpdateType=" + defaultUpdateType + ", autoCreateEnvironment="
                + autoCreateEnvironment + ", accessTokenAuthMethod=" + accessTokenAuthMethod
                + ", allowAuthOnURL=" + allowAuthOnURL + ", customResponseHeaders="
                + customResponseHeaders + ", jobEnabled=" + jobEnabled + ", jobEndStates="
                + jobEndStates + ", jobEventFrequency=" + jobEventFrequency + ", jobEventKeepDays="
                + jobEventKeepDays + ", jobKeepDays=" + jobKeepDays + ", jobHousekeepingCron="
                + jobHousekeepingCron + ", toString()=" + super.toString() + "]";
    }
}
