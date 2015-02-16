/*
 * SIFZone.java
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

/**
 * @author Joerg Huber
 *
 */
public class SIFZone extends SIFBaseInfo
{
    private static final long serialVersionUID = -98756246563709787L;

    public SIFZone()
    {
      this(null);
    }
    
    public SIFZone(String zoneID)
    {
      super(zoneID);
    }
    
    public SIFZone(String zoneID, boolean isDefault)
    {
      super(zoneID, isDefault);
    }

    @Override
    public String toString()
    {
	    return "SIFZone [toString()=" + super.toString() + "]";
    }
}
