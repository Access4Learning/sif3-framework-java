/*
 * SIFData.java Created: 09/03/2015
 * 
 * Copyright 2015 Systemic Pty Ltd
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

package sif3.common.model.audit;

import java.io.Serializable;

/**
 * @author Joerg Huber
 * 
 */
public class SIFData implements Serializable
{
	private static final long serialVersionUID = 2919380179644458823L;

	private String solutionId;
	private String appKey;
	private String userToken;
	private String instanceId;
	private String context; // SIF Context
	private String zone; // SIF Zone
	private String environmentId; // EnvironmentId
	private String sessionToken;

	public String getSolutionId()
	{
		return solutionId;
	}

	public void setSolutionId(String solutionId)
	{
		if ((this.solutionId == null || "".equals(this.solutionId)) && solutionId != null && !"".equals(solutionId))
		{
			this.solutionId = solutionId;
		}
	}

	public String getAppKey()
	{
		return appKey;
	}

	public void setAppKey(String appKey)
	{
		if ((this.appKey == null || "".equals(this.appKey)) && appKey != null && !"".equals(appKey))
		{
			this.appKey = appKey;
		}
	}

	public String getUserToken()
	{
		return userToken;
	}

	public void setUserToken(String userToken)
	{
		if ((this.userToken == null || "".equals(this.userToken)) && userToken != null && !"".equals(userToken))
		{
			this.userToken = userToken;
		}
	}

	public String getInstanceId()
	{
		return instanceId;
	}

	public void setInstanceId(String instanceId)
	{
		if ((this.instanceId == null || "".equals(this.instanceId)) && instanceId != null && !"".equals(instanceId))
		{
			this.instanceId = instanceId;
		}
	}

	public String getContext()
	{
		return context;
	}

	public void setContext(String context)
	{
		if ((this.context == null || "".equals(this.context)) && context != null && !"".equals(context))
		{
			this.context = context;
		}
	}

	public String getZone()
	{
		return zone;
	}

	public void setZone(String zone)
	{
		if ((this.zone == null || "".equals(this.zone)) && zone != null && !"".equals(zone))
		{
			this.zone = zone;
		}
	}

	public String getEnvironmentId()
	{
		return environmentId;
	}

	public void setEnvironmentId(String environmentId)
	{
		if ((this.environmentId == null || "".equals(this.environmentId)) && (environmentId != null) && !"".equals(environmentId))
		{
			this.environmentId = environmentId;
		}
	}

	public String getSessionToken()
	{
		return sessionToken;
	}

	public void setSessionToken(String sessionToken)
	{
		if ((this.sessionToken == null || "".equals(this.sessionToken)) && sessionToken != null && !"".equals(sessionToken))
		{
			this.sessionToken = sessionToken;
		}
	}

	@Override
    public String toString()
    {
	    return "SIFData [solutionId=" + this.solutionId + ", appKey=" + this.appKey
	            + ", userToken=" + this.userToken + ", instanceId=" + this.instanceId
	            + ", context=" + this.context + ", zone=" + this.zone + ", environmentId="
	            + this.environmentId + ", sessionToken=" + this.sessionToken + "]";
    }
}
