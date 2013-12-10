/*
 * EnvironmentInfo.java
 * Created: 27/08/2013
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

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import sif3.common.model.SIFZone;

/**
 * This class is a super-class for environment management. It can be used for Consumer and Provider environment managemnt but it is 
 * unlikely to be used on its own. It holds base infromation that is required in either environment and must be maintained in either
 * environment (Consumer, Provider).
 * 
 * @author Joerg Huber
 *
 */
public class EnvironmentInfo implements Serializable
{
    private static final long serialVersionUID = 8203669966934384325L;
    
    private String    adapterName           = null;
	private String    environmentName       = null;
    private String    userName              = null;
    private String    password              = null;
    private URI       baseURI               = null;
    private URI       secureBaseURI         = null;
    private String    sessionToken          = null;
    private String    envGUID               = null;
    private String    authenticationToken   = null;
    private boolean   secureConnection      = false;
   	private MediaType mediaType             = MediaType.APPLICATION_XML_TYPE;
	private SIFZone   defaultZone           = null;
	private ArrayList<ServiceInfo> services = new ArrayList<ServiceInfo>();
    
	/**
     * @param environmentName
     */
    public EnvironmentInfo(String environmentName, String adapterName)
    {
	    super();
	    this.environmentName = environmentName;
	    this.adapterName = adapterName;
    }

    /**
     * Get th adapter name. This is the name of the full service. On the provider side it is something like the name of the web-app where
     * as on the consumer side it might be the name of the execuatble.
     * 
     * @return See desc.
     */
    public String getAdapterName()
    {
    	return this.adapterName;
    }

	public void setAdapterName(String adapterName)
    {
    	this.adapterName = adapterName;
    }

	public String getEnvironmentName()
    {
    	return this.environmentName;
    }

	public void setEnvironmentName(String environmentName)
    {
    	this.environmentName = environmentName;
    }

	public String getUserName()
    {
    	return this.userName;
    }
	
	public void setUserName(String userName)
    {
    	this.userName = userName;
    }
	
	public String getPassword()
    {
    	return this.password;
    }
	
	public void setPassword(String password)
    {
    	this.password = password;
    }
	
    public boolean getIsSecureConnection()
    {
    	return this.secureConnection;
    }

	public void setSecureConnection(boolean secureConnection)
    {
    	this.secureConnection = secureConnection;
    }

	public URI getBaseURI()
    {
    	return this.baseURI;
    }
	
	public void setBaseURI(URI baseURI)
    {
    	this.baseURI = baseURI;
    }

  public void setBaseURI(String baseURI)
  {
    try
    {
      this.baseURI = new URI(baseURI);
    }
    catch (Exception ex)
    {
      this.baseURI = null;
    }
  }

  public URI getSecureBaseURI()
  {
    return secureBaseURI;
  }

  public void setSecureBaseURI(URI secureBaseURI)
  {
    this.secureBaseURI = secureBaseURI;
  }

  public void setSecureBaseURI(String secureBaseURI)
  {
    try
    {
      this.secureBaseURI = new URI(secureBaseURI);
    }
    catch (Exception ex)
    {
      this.secureBaseURI = null;
    }
  }

  public String getSessionToken()
    {
    	return this.sessionToken;
    }

	public void setSessionToken(String sessionToken)
    {
    	this.sessionToken = sessionToken;
    }

	public String getEnvGUID()
    {
    	return this.envGUID;
    }

	public void setEnvGUID(String envGUID)
    {
    	this.envGUID = envGUID;
    }

	public String getAuthenticationToken()
    {
    	return this.authenticationToken;
    }

	public void setAuthenticationToken(String authenticationToken)
    {
    	this.authenticationToken = authenticationToken;
    }

	public MediaType getMediaType()
	{
		return mediaType;
	}

	public void setMediaType(MediaType mediaType)
	{
		this.mediaType = mediaType;
	}

	public SIFZone getDefaultZone()
    {
    	return this.defaultZone;
    }

	public void setDefaultZone(SIFZone defaultZone)
    {
    	this.defaultZone = defaultZone;
    }

	/**
	 * List of services available for this environment. This are OBJECT, UTILITY and FUNCTIONal services. Each service also has an
	 * assigned zone and context with it, both of which can be ommited. If they are ommited then the default zone and context are assumed
	 * as per SIF3 specification.
	 * 
	 * @return See desc.
	 */
    public ArrayList<ServiceInfo> getServices()
    {
    	return this.services;
    }

	public void setServices(ArrayList<ServiceInfo> services)
    {
    	this.services = services;
    }

	@Override
  public String toString()
  {
    return "EnvironmentInfo [adapterName=" + adapterName
        + ", authenticationToken=" + authenticationToken + ", baseURI="
        + baseURI + ", defaultZone=" + defaultZone + ", envGUID=" + envGUID
        + ", environmentName=" + environmentName + ", mediaType=" + mediaType
        + ", password=" + password + ", secureBaseURI=" + secureBaseURI
        + ", secureConnection=" + secureConnection + ", services=" + services
        + ", sessionToken=" + sessionToken + ", userName=" + userName + "]";
  }
}
