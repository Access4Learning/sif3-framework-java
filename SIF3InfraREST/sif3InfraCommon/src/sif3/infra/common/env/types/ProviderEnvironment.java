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

import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.model.AuthenticationInfo.AuthenticationMethod;

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
    
	private boolean              connected              = false;
	private String               templateXMLFileName    = null;
	private URI                  secureConnectorBaseURI = null; // Secure URI to connect to Provider 
	private URI                  connectorBaseURI       = null; // URI to connect to Provider
	private UpdateType           defaultUpdateType      = UpdateType.FULL; // Default value for update events.
	private boolean              autoCreateEnvironment  = false;
	private AuthenticationMethod accessTokenAuthMethod  = AuthenticationMethod.Bearer; // Default Auth Method if accessToke is used.
	
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

	public AuthenticationMethod getAccessTokenAuthMethod()
    {
    	return accessTokenAuthMethod;
    }

	public void setAccessTokenAuthMethod(AuthenticationMethod accessTokenAuthMethod)
    {
    	this.accessTokenAuthMethod = accessTokenAuthMethod;
    }

	// authMethod for accessToken: Valid values are what is listed in AuthenticationUtils.AuthenticationMethod (case sensitive!!!)
	public void setAccessTokenAuthMethod(String accessTokenAuthMethod)
    {
		try
		{
			this.accessTokenAuthMethod = AuthenticationMethod.valueOf(accessTokenAuthMethod);
		}
		catch (Exception ex)
		{
			this.accessTokenAuthMethod = AuthenticationMethod.Bearer;
		}
    }
	
	@Override
    public String toString()
    {
	    return "ProviderEnvironment [connected=" + this.connected + ", templateXMLFileName="
	            + this.templateXMLFileName + ", secureConnectorBaseURI="
	            + this.secureConnectorBaseURI + ", connectorBaseURI=" + this.connectorBaseURI
	            + ", defaultUpdateType=" + this.defaultUpdateType + ", autoCreateEnvironment="
	            + this.autoCreateEnvironment + ", accessTokenAuthMethod="
	            + this.accessTokenAuthMethod + ", toString()=" + super.toString() + "]";
    }
}
