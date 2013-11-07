/*
 * ModelObjectInfo.java
 * Created: 24/09/2013
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

package sif3.common.conversion;

import java.io.Serializable;

/**
 * This class is used to link up the Object Names in the data model with the class it is represented by within the framework.
 *  
 * @author Joerg Huber
 */
public class ModelObjectInfo implements Serializable
{
	private static final long serialVersionUID = 1319372824155892439L;
    
	private String objectName = null;
	private Class<?> objectType = null;
	
	public ModelObjectInfo(String objectName, Class<?> objectType)
	{
		super();
		setObjectName(objectName);
		setObjectType(objectType);
	}
	
	public String getObjectName()
    {
    	return this.objectName;
    }

	public void setObjectName(String objectName)
    {
    	this.objectName = objectName;
    }

	public Class<?> getObjectType()
    {
    	return this.objectType;
    }

	public void setObjectType(Class<?> objectType)
    {
    	this.objectType = objectType;
    }

	@Override
    public String toString()
    {
	    return "ModelObjectInfo [objectName=" + this.objectName + ", objectType=" + this.objectType.getSimpleName() + "]";
    }
	
    @Override
    public int hashCode()
    {
	    return 31 + ((this.objectName == null) ? 0 : this.objectName.hashCode());
    }

	@Override
    public boolean equals(Object obj)
    {
	    if (this == obj)
	    {
		    return true;
	    }
	    if (obj == null)
	    {
		    return false;
	    }
	    if (getClass() != obj.getClass())
	    {
		    return false;
	    }
	    ModelObjectInfo other = (ModelObjectInfo) obj;
	    if (this.objectName == null)
	    {
		    if (other.objectName != null)
		    {
			    return false;
		    }
	    }
	    else if (!this.objectName.equals(other.objectName))
	    {
		    return false;
	    }
	    return true;
    }
	
}
