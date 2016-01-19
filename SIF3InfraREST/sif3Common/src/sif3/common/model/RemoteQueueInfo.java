/*
 * RemoteQueueInfo.java
 * Created: 11/11/2015
 *
 * Copyright 2015 Systemic Pty Ltd
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

public class RemoteQueueInfo implements Serializable
{
    private static final long serialVersionUID = -7499360431946561217L;
    
	private String queueID = null;
	private String queueName = null;
	
	public RemoteQueueInfo(String queueID, String queueName)
	{
		super();
		setQueueID(queueID);
		setQueueName((queueName == null) ? queueID : queueName);
	}
	
	public String getQueueID()
	{
		return queueID;
	}
		
	public String getQueueName()
	{
		return queueName;
	}
	
	private void setQueueName(String queueName)
	{
		this.queueName = queueName;
	}
	
	private void setQueueID(String queueID)
	{
		this.queueID = queueID;
	}

	@Override
    public String toString()
    {
	    return "RemoteQueueInfo [queueID=" + queueID + ", queueName="
	            + queueName + "]";
    }
}
