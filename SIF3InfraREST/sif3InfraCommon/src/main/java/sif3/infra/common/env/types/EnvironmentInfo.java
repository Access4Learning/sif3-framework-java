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
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.CommonConstants.AuthenticationType;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.PayloadMetadata;
import sif3.infra.common.env.types.ConsumerEnvironment.ConnectorName;

/**
 * This class is a super-class for environment management. It can be used for Consumer and Provider environment management but it is 
 * unlikely to be used on its own. It holds base information that is required in either environment and must be maintained in either
 * environment (Consumer, Provider).
 * 
 * @author Joerg Huber
 *
 */
public class EnvironmentInfo implements Serializable
{
	private static final long serialVersionUID = 8203669966934384325L;
    
	/**
	 * Valid Environment Types.
	 */
	public enum EnvironmentType {DIRECT, BROKERED};

    private URI       baseURI               = null; // URI to broker
    private boolean   secureConnection      = false;
    
    PayloadMetadata dmPayloadMetadata       = new PayloadMetadata(MediaType.APPLICATION_XML_TYPE);
    PayloadMetadata infraPayloadMetadata    = new PayloadMetadata(MediaType.APPLICATION_XML_TYPE);

    private AdapterType adapterType         = null;
    private boolean checkACL                = true;
    private EnvironmentType environmentType = null;
    private String authMethod               = AuthenticationType.Basic.name();
    private boolean removeEnvOnShutdown     = false;
    private String adapterName              = null; ; // consumerName 
    private EnvironmentKey environmentKey   = new EnvironmentKey();
    private String    password              = null;
    private String generatorID              = null; // Value to be used for the generatorId HTTP Header field. 
    private boolean compressionEnabled      = false;
    private boolean noCertificateCheck      = false; // by default we want certificates checked.
    
    // Properties for using an existing environment.
    private boolean useExistingEnv          = false;
	private String existingSessionToken     = null;
    private URI existingEnvURI              = null;
    
    private boolean isSchemaNegotiationEnabled = false;
    
    // Other framework specific properties
    private boolean envCreateConflictIsError = true; // Shall the HTTP Status 409 for environment creation be treated as error (true)? 
        
	/* 
     * This is a runtime value only. But it is stored here rather than in the session because it is valid for ALL sessions of that
     * environment.
    */
	private HashMap<ConnectorName, URI> connectorBaseURIs = new HashMap<ConnectorName, URI>();
    
    public String getAdapterName()
    {
      return adapterName;
    }

    public void setAdapterName(String adapterName)
    {
      this.adapterName = adapterName;
    }

    public EnvironmentKey getEnvironmentKey()
    {
      return environmentKey;
    }

    public void setEnvironmentKey(EnvironmentKey environmentKey)
    {
      this.environmentKey = environmentKey;
    }

    public String getPassword()
    {
      return password;
    }

    public void setPassword(String password)
    {
      this.password = password;
    }

    public EnvironmentType getEnvironmentType()
    {
    	return this.environmentType;
    }

	public void setEnvironmentType(EnvironmentType environmentType)
    {
    	this.environmentType = environmentType;
    }

	public AdapterType getAdapterType()
    {
    	return this.adapterType;
    }

	public void setAdapterType(AdapterType adapterType)
    {
    	this.adapterType = adapterType;
    }

	public boolean getSecureConnection()
    {
    	return this.secureConnection;
    }

	public void setSecureConnection(boolean secureConnection)
    {
    	this.secureConnection = secureConnection;
    }
    public String getGeneratorID()
    {
    	return this.generatorID;
    }

	public void setGeneratorID(String generatorID)
    {
    	this.generatorID = generatorID;
    }
	
	public boolean getCompressionEnabled()
    {
    	return this.compressionEnabled;
    }

	public void setCompressionEnabled(boolean compressionEnabled)
    {
    	this.compressionEnabled = compressionEnabled;
    }
	
    public boolean getNoCertificateCheck()
    {
        return noCertificateCheck;
    }

    public void setNoCertificateCheck(boolean noCertificateCheck)
    {
        this.noCertificateCheck = noCertificateCheck;
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

	public boolean getCheckACL()
	{
		return this.checkACL;
	}

	public void setCheckACL(boolean checkACL)
	{
		this.checkACL = checkACL;
	}

    public String getAuthMethod()
    {
    	return this.authMethod;
    }

	public void setAuthMethod(String authMethod)
    {
    	this.authMethod = authMethod;
    }

	public boolean getRemoveEnvOnShutdown()
	{
		return removeEnvOnShutdown;
	}

	public void setRemoveEnvOnShutdown(boolean removeEnvOnShutdown)
	{
		this.removeEnvOnShutdown = removeEnvOnShutdown;
	}

	public void addConnectorBaseURI(ConnectorName connectorName, URI connectorURI)
	{
		connectorBaseURIs.put(connectorName, connectorURI);
	}
	
	public URI getConnectorBaseURI(ConnectorName conectorName)
	{
		return connectorBaseURIs.get(conectorName);
	}
	
	public void clearConnectorBaseURIs()
	{
		connectorBaseURIs = new HashMap<ConnectorName, URI>();
	}

	/**
	 * Convenience Method. It simply calls the getEnvironmentName form the SIF3Session property.
	 * 
	 * @return A nice to read name for this environment.
	 */
	public String getEnvironmentName()
	{
	  if (getEnvironmentKey() != null)
	  {
	    return getAdapterName()+"/"+getEnvironmentKey().getEnvironmentName();
	  }
	  else
	  {
	    return getAdapterName();
	  }
	}

    public boolean getUseExistingEnv()
    {
    	return this.useExistingEnv;
    }

	public void setUseExistingEnv(boolean useExistingEnv)
    {
    	this.useExistingEnv = useExistingEnv;
    }

	public String getExistingSessionToken()
    {
    	return this.existingSessionToken;
    }

	public void setExistingSessionToken(String existingSessionToken)
    {
    	this.existingSessionToken = existingSessionToken;
    }

	public URI getExistingEnvURI()
    {
    	return this.existingEnvURI;
    }

	public void setExistingEnvURI(URI existingEnvURI)
    {
    	this.existingEnvURI = existingEnvURI;
    }

	public void setExistingEnvURI(String existingEnvURI)
    {
		try
		{
			this.existingEnvURI = new URI(existingEnvURI);
		}
		catch (Exception ex)
		{
			this.existingEnvURI = null;
		}
    }

	public boolean getEnvCreateConflictIsError()
    {
    	return envCreateConflictIsError;
    }

	public void setEnvCreateConflictIsError(boolean envCreateConflictIsError)
    {
    	this.envCreateConflictIsError = envCreateConflictIsError;
    }

    public boolean isSchemaNegotiationEnabled()
    {
        return isSchemaNegotiationEnabled;
    }

    public void setSchemaNegotiationEnabled(boolean isSchemaNegotiationEnabled)
    {
        this.isSchemaNegotiationEnabled = isSchemaNegotiationEnabled;
    }
    
    public PayloadMetadata getDataModelPayloadMetadata()
    {
        return dmPayloadMetadata;
    }

    public void setDataModelPayloadMetadata(PayloadMetadata dmPayloadMetadata)
    {
        this.dmPayloadMetadata = dmPayloadMetadata;
    }

    public PayloadMetadata getInfraPayloadMetadata()
    {
        return infraPayloadMetadata;
    }

    public void setInfraPayloadMetadata(PayloadMetadata infraPayloadMetadata)
    {
        this.infraPayloadMetadata = infraPayloadMetadata;
    }
    
    @Override
    public String toString()
    {
        return "EnvironmentInfo [baseURI=" + baseURI + ", secureConnection=" + secureConnection
                + ", dmPayloadMetadata=" + dmPayloadMetadata + ", infraPayloadMetadata="
                + infraPayloadMetadata + ", adapterType=" + adapterType + ", checkACL=" + checkACL
                + ", environmentType=" + environmentType + ", authMethod=" + authMethod
                + ", removeEnvOnShutdown=" + removeEnvOnShutdown + ", adapterName=" + adapterName
                + ", environmentKey=" + environmentKey + ", password=" + password + ", generatorID="
                + generatorID + ", compressionEnabled=" + compressionEnabled
                + ", noCertificateCheck=" + noCertificateCheck + ", useExistingEnv="
                + useExistingEnv + ", existingSessionToken=" + existingSessionToken
                + ", existingEnvURI=" + existingEnvURI + ", isSchemaNegotiationEnabled="
                + isSchemaNegotiationEnabled + ", envCreateConflictIsError="
                + envCreateConflictIsError + ", connectorBaseURIs=" + connectorBaseURIs + "]";
    }

}
