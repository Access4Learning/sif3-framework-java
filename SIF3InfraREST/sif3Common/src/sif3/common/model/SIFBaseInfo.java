/*
 * SIFBaseInfo.java
 * Created: 29/09/2013
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

package sif3.common.model;

import java.io.Serializable;

/**
 * @author Joerg Huber
 *
 */
public class SIFBaseInfo implements Serializable
{
    private static final long serialVersionUID = -6522409095748704978L;
    
    private String id = null;
    private String uniqueName = null;
    private boolean isDefault = false;
    
	public SIFBaseInfo()
    {
      super();
    }
    
    public SIFBaseInfo(String id)
    {
      super();
      setId(id);
    }

    public SIFBaseInfo(String id, boolean isDefault)
    {
      super();
      setId(id);
      setDefault(isDefault);
    }

    public String getId()
    {
    	return this.id;
    }
	
	public void setId(String id)
    {
    	this.id = id;
    }
	
	public String getUniqueName()
    {
    	return this.uniqueName;
    }
	
	public void setUniqueName(String uniqueName)
    {
    	this.uniqueName = uniqueName;
    }

    public boolean getIsDefault()
    {
    	return this.isDefault;
    }

	public void setDefault(boolean isDefault)
    {
    	this.isDefault = isDefault;
    }

	@Override
    public String toString()
    {
	    return "SIFBaseInfo [id=" + this.id + ", uniqueName=" + this.uniqueName + ", isDefault=" + this.isDefault + "]";
    }
}
