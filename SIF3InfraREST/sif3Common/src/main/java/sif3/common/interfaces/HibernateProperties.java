/*
 * HibernateProperties.java 
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
package sif3.common.interfaces;

import java.util.Properties;

/**
 * This interface defines some basic operations to setup properties for the hibernate configuration.
 * There are situations where the DB configuration, mainly username and password shall not be visible in
 * any configuration file but shall be retrieved programmatically from some other sources. If this is the
 * case an implementation of this interface can be provided to the SIF3 Framework that sets some of the
 * hibernate properties that are commonly found in either the hibernate.properties or hibernate.cfg.xml
 * file through this class. The property names are the same as in the hibernate.cfg.xml file. If the SIF3
 * framework shall use an implementation of this interface then the fully qualified class name of the 
 * sub-class must be listed in the consumer's or provider's property file in the "adapter.hbr.propertyClass"
 * property.<br/><br/>
 * 
 * Note:<br/>
 * Properties that are set in the hibernate.cfg.xml file will always take precedence over properties in the
 * implementation of this class.
 * 
 * @author Joerg Huber & Dan Whitehouse
 *
 */
public interface HibernateProperties
{
    /**
     * Returns all programmatically set properties that shall be used in the hibernate configuration.
     * 
     * @return Null can be returned. The returned property class can be empty.
     */
    public Properties getProperties();
    
    /**
     * This method will replace the currently set properties with the given properties. This is not a
     * merge!. Existing properties will be wiped out.
     * 
     * @param properties
     */
    public void setProperties(Properties properties);

    /**
     * This method will set/replace the property given by its key with the given value.
     * 
     * @param key The name of the property to set/replace.
     * @param value The value to set/replace the property with.
     */
    public void addProperty(String key, String value);
}
