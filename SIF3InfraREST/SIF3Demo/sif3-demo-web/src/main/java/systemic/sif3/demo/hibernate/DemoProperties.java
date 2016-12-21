/*
 * DemoProperties.java 
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
package systemic.sif3.demo.hibernate;

import java.util.Properties;

import sif3.common.interfaces.HibernateProperties;

/**
 * This class demonstrates how hibernate properties can be set programmatically and injected to the SIF3 Framework.
 * 
 * @author Joerg Huber
 */
public class DemoProperties implements HibernateProperties
{
    private static Properties props = null;

    public DemoProperties()
    {
        if (props == null)
        {
            props = new Properties();
            props.setProperty("hibernate.dialect", "sif3.common.persist.common.SQLiteDialect");
            props.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
            props.setProperty("hibernate.connection.url", "jdbc:sqlite:/DEV/lunaWorkspace/SIF3InfraREST/DB/Data/SIF3Infra.sqliteDB");
            props.setProperty("hibernate.connection.username", "");
            props.setProperty("hibernate.connection.password", "");
        }
    }
    
    @Override
    public Properties getProperties()
    {
        return props;
    }

    @Override
    public void setProperties(Properties properties)
    {
        props = properties;
    }

    @Override
    public void addProperty(String key, String value)
    {
        if (props != null)
        {
            props.setProperty(key, value);
        }
    }
}
