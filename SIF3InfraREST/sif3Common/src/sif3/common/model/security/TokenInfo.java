/*
 * TokenInfo.java Created: 12/12/2014
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
package sif3.common.model.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import au.com.systemic.framework.utils.StringUtils;

import sif3.common.model.EnvironmentKey;

public class TokenInfo implements Serializable
{
	private static final long serialVersionUID = 1429507572774333454L;

	// Date when token expires. Null = no expire date
	private Date tokenExpiryDate = null;
	private String token = null;

	// Data known about application and/or user.
	private EnvironmentKey appUserInfo = null;
	private String consumerName = null;
	private String dataModelNamespace = null;

	// Data known about existing SIF Environment and SIF Session Token
	private String environmentID = null;
	private String sessionToken = null;

	// Attribute value list with other information about security token.
	private Map<String, String> otherInfo = new HashMap<String, String>();

	public TokenInfo(String token) throws IllegalArgumentException
	{
	  super();
	  if (StringUtils.isEmpty(token))
	  {
	    throw new IllegalArgumentException("Token must not be empty or null.");
	  }
	  setToken(token);
	}
	
  public String getToken()
  {
    return token;
  }

  public void setToken(String token)
  {
    this.token = token;
  }

	public Date getTokenExpiryDate()
	{
		return tokenExpiryDate;
	}

	public void setTokenExpiryDate(Date tokenExpiryDate)
	{
		this.tokenExpiryDate = tokenExpiryDate;
	}

	public EnvironmentKey getAppUserInfo()
	{
		return appUserInfo;
	}

	public void setAppUserInfo(EnvironmentKey appUserInfo)
	{
		this.appUserInfo = appUserInfo;
	}

	public String getEnvironmentID()
	{
		return environmentID;
	}

	public void setEnvironmentID(String environmentID)
	{
		this.environmentID = environmentID;
	}

	public String getSessionToken()
	{
		return sessionToken;
	}

	public void setSessionToken(String sessionToken)
	{
		this.sessionToken = sessionToken;
	}

	public Map<String, String> getOtherInfo()
	{
		return otherInfo;
	}

	public void setOtherInfo(Map<String, String> otherInfo)
	{
		this.otherInfo = otherInfo;
	}

	public String getConsumerName()
	{
		return this.consumerName;
	}

	public void setConsumerName(String consumerName)
	{
		this.consumerName = consumerName;
	}

	public String getDataModelNamespace()
	{
		return this.dataModelNamespace;
	}

	public void setDataModelNamespace(String dataModelNamespace)
	{
		this.dataModelNamespace = dataModelNamespace;
	}

	@Override
    public String toString()
    {
	    return "TokenInfo [tokenExpiryDate=" + this.tokenExpiryDate + ", token=" + this.token
	            + ", appUserInfo=" + this.appUserInfo + ", consumerName=" + this.consumerName
	            + ", dataModelNamespace=" + this.dataModelNamespace + ", environmentID="
	            + this.environmentID + ", sessionToken=" + this.sessionToken + ", otherInfo="
	            + this.otherInfo + "]";
    }
}
