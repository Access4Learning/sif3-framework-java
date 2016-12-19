/*
 * HibernateHelper.java 
 * Created: 30/04/2016
 * 
 * Copyright 2016 Systemic Pty Ltd
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
package sif3.common.persist.common;

import sif3.common.exception.PersistenceException;
import sif3.common.interfaces.HibernateProperties;
import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.PropertyManager;
import au.com.systemic.framework.utils.StringUtils;

/**
 * Small helper class for some hibernate functionality specific to the SIF3 Framework.
 * 
 * @author Joerg Huber
 *
 */
public class HibernateHelper
{
    
    public HibernateHelper()
    {
        super();
    }
    
    /**
     * This method will return an implementation of the HibernateProperties interface. The implementation to be returned is held
     * in the properties file given by its name. In context of the SIF3 Framework that will either be the consumer's or provider's
     * property file. The property name used to find the implementation class is "adapter.hbr.propertyClass". If this property
     * is not set or not present then null will be returned. This is a valid scenario and the caller of this method must assume
     * that there is no implementation of the HibernateProperties interface present.
     * 
     * @param propertyFileName Property File Name without the extension of ".properties".
     * 
     * @return Null if no implementation of the HibernateProperties interface is set in the "adapter.hbr.propertyClass" property
     *         of the propertyFileName. If a valid implementation exists then the class is returned.
     * 
     * @throws PersistenceException If and invalid class is listed in the "adapter.hbr.propertyClass" property.
     */
    public HibernateProperties getHiberbnateProperties(String propertyFileName) throws PersistenceException
    {
        String hbrProprClassName = null;
        try
        {
            HibernateProperties hbrProps = null;
            PropertyManager propMgr = PropertyManager.getInstance();
            propMgr.loadPropertyFile(propertyFileName);
            AdvancedProperties advProps = propMgr.getProperties(propertyFileName);
            hbrProprClassName = advProps.getPropertyAsString("adapter.hbr.propertyClass", null);
            if (StringUtils.notEmpty(hbrProprClassName))
            {
                Class<?> clazz = Class.forName(hbrProprClassName);
                Object objClass = clazz.getConstructor().newInstance();
                if (objClass instanceof HibernateProperties)
                {
                    hbrProps = ((HibernateProperties) objClass);
                }
                else
                {
                    throw new PersistenceException(hbrProprClassName+" does not implement the HibernateProperties interface.");                    
                }
            }
            
            return hbrProps;
        }
        catch (Exception ex)
        {
            throw new PersistenceException("Failed to use "+hbrProprClassName+" properly. Class may not be on classpath or might not implement the HibernateProperties interface: "+ex.getMessage());
        }
    }
}
