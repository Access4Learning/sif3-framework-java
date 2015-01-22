/*
 * BaseMetadata.java
 * Created: 09/09/2014
 *
 * Copyright 2014 Systemic Pty Ltd
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

package sif3.common.model;

import java.io.Serializable;

/**
 * @author Joerg Huber
 *
 */
public class BaseMetadata implements Serializable
{
    private static final long serialVersionUID = 4812586181544050954L;
   
    private String generatorID = null;  // This is an optional field and might be provided by the consumer.
    
    // The following three fields are early adopters for proposed SIF 3.1 inclusions. Should only be used once officially
    // released. They may or may not be set. Until the official release they may not be set at all and if they are they
    // may only available in a DIRECT environment but NOT through a BROKERED environment.
    private String applicationKey = null; // The applicationKey from the HTTP header field 'applicationKey' set by the consumer. 
 	private String userToken = null; // The user Token from the HTTP header field 'userToken' set by the consumer. 
    private String instanceID = null; // The instanceId from the HTTP header field 'instanceId' set by the consumer. 

	public String getGeneratorID()
    {
    	return this.generatorID;
    }

	public void setGeneratorID(String generatorID)
    {
    	this.generatorID = generatorID;
    }

	public String getApplicationKey()
	{
		return applicationKey;
	}

	public void setApplicationKey(String applicationKey)
	{
		this.applicationKey = applicationKey;
	}

	public String getUserToken()
	{
		return userToken;
	}

	public void setUserToken(String userToken)
	{
		this.userToken = userToken;
	}

	public String getInstanceID()
	{
		return instanceID;
	}

	public void setInstanceID(String instanceID)
	{
		this.instanceID = instanceID;
	}

	@Override
    public String toString()
    {
	    return "BaseMetadata [applicationKey=" + applicationKey
	            + ", generatorID=" + generatorID + ", instanceID=" + instanceID
	            + ", userToken=" + userToken + "]";
    }
}
