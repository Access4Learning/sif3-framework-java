/*
 * AppEnvironmentTemplate.java 
 * Created: 30/06/2014
 * 
 * Copyright 2014 Systemic Pty Ltd
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

import sif3.common.model.EnvironmentKey;

/**
 * POJO to encapsulate Application to Environment Template relationship.
 * 
 * @author Joerg Huber
 * 
 */
public class AppEnvironmentTemplate extends EnvironmentKey implements Serializable
{
	private static final long serialVersionUID = -4947072879957192434L;

	private long appTemplateID;
	private String password = null;
	private String authMethod = null;
	private String infraVersion = null;
    private EnvironmentTemplate environmentTemplate;

	public AppEnvironmentTemplate()
	{
	}

	public AppEnvironmentTemplate(EnvironmentKey environmenKey)
	{
		super(environmenKey);
	}

	public long getAppTemplateID()
	{
		return appTemplateID;
	}

	public void setAppTemplateID(long appTemplateID)
	{
		this.appTemplateID = appTemplateID;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getAuthMethod()
	{
		return this.authMethod;
	}

	public void setAuthMethod(String authMethod)
	{
		this.authMethod = authMethod;
	}

    public String getInfraVersion()
    {
        return infraVersion;
    }

    public void setInfraVersion(String infraVersion)
    {
        this.infraVersion = infraVersion;
    }

	public EnvironmentTemplate getEnvironmentTemplate()
	{
		return environmentTemplate;
	}

	public void setEnvironmentTemplate(EnvironmentTemplate environmentTemplate)
	{
		this.environmentTemplate = environmentTemplate;
	}

	@Override
    public String toString()
    {
        return "AppEnvironmentTemplate [appTemplateID=" + appTemplateID + ", password=" + password
                + ", authMethod=" + authMethod + ", infraVersion=" + infraVersion
                + ", environmentTemplate=" + environmentTemplate + ", toString()="
                + super.toString() + "]";
    }
}
