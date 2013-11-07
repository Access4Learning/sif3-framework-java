/*
 * ConsumerEnvironment.java Created: 15/09/2013
 * 
 * Copyright 2013 Systemic Pty Ltd
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

import java.net.URI;
import java.util.HashMap;

/**
 * This class is a sub-class of an environment. It adds properties typical in a consumer environment.
 * 
 * @author Joerg Huber
 * 
 */
public class ConsumerEnvironment extends EnvironmentInfo
{
	public static enum ConnectorName {environment, requestsConnector, provisionRequests, queues, subscriptions};
        	
	private static final long serialVersionUID = -2685814104916173550L;

	private boolean connected = false;
	
	private HashMap<ConnectorName, URI> connectorBaseURIs = new HashMap<ConnectorName, URI>();
		
	/**
	 * Constructor
	 * 
	 * @param environmentName The environment name
	 * @param serviceName The name of the service/adapter.
	 */
	public ConsumerEnvironment(String environmentName, String serviceName)
    {
	    super(environmentName, serviceName);
    }


	/**
	 * TRUE if the service/adapter is connected to the given environment. This only happens after an attempt is made to the 
	 * environment URL to establish a new or existing environment. If the service/adapter is not connected or failed to connect then this
	 * value is set to false (default).
	 * 
	 * @return See desc.
	 */
	public boolean isConnected()
    {
    	return this.connected;
    }

	public void setConnected(boolean connected)
    {
    	this.connected = connected;
    }
	
	public void addConnectorBaseURI(ConnectorName connectorName, URI connectorURI)
	{
		connectorBaseURIs.put(connectorName, connectorURI);
	}
	
	public URI getConnectorBaseURI(ConnectorName conectorName)
	{
		return connectorBaseURIs.get(conectorName);
	}

	@Override
    public String toString()
    {
	    return "ConsumerEnvironment [connected=" + this.connected + ", connectorBaseURIs=" + this.connectorBaseURIs + ", toString()=" + super.toString() + "]";
    }
}
