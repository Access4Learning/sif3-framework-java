/*
 * BaseProvider.java
 * Created: 01/10/2013
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

package sif3.common.provider;

import au.com.systemic.framework.utils.AdvancedProperties;

/**
 * This class only exists to enforce the constructor so that the ProviderFactory will work as expected. All providers must extend this class
 * to ensure it is all wired up correctly and that the DataModel Generic Resource will function correctly
 *
 * @author Joerg Huber
 */
public abstract class BaseProvider implements Provider
{
	private String serviceID = null;
	private AdvancedProperties serviceProperties = null;
	
	/**
     * @param serviceID
     */
    public BaseProvider(String serviceID, AdvancedProperties serviceProperties)
    {
	    super();
    }
    
	/**
     * @param serviceID the serviceID to set
     */
    public void setServiceID(String serviceID)
    {
	    this.serviceID = serviceID;
    }

	/**
     * @return the serviceID
     */
    public String getServiceID()
    {
	    return serviceID;
    }

	/**
     * @param serviceProperties the serviceProperties to set
     */
    public void setServiceProperties(AdvancedProperties serviceProperties)
    {
	    this.serviceProperties = serviceProperties;
    }

	/**
     * @return the serviceProperties
     */
    public AdvancedProperties getServiceProperties()
    {
	    return serviceProperties;
    }
}
