/*
 * ExternalSecurityServiceDAO.java
 * Created: 31/03/2017
 *
 * Copyright 2017 Systemic Pty Ltd
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
package sif3.common.persist.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.common.exception.PersistenceException;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.model.ExternalSecurityService;

/**
 * Implements some low level DB operations relating to the external security service configuration.
 * 
 * @author Joerg Huber
 *
 */
public class ExternalSecurityServiceDAO extends BaseDAO
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * This method attempts to retrieve all defined security services as listed in the SIF3_EXT_ECURITY_SERVICE table. 
     * If no services are defined then an empty list is returned. With each returned service all the related parameters 
     * from the SIF3_SEC_SERVICE_PARAM that relate to a specific service are also returned (i.e. lazy loading is disabled).
     * 
     * @param tx The current transaction. Cannot be null.
     * 
     * @return See desc.
     * 
     * @throws IllegalArgumentException tx is null.
     * @throws PersistenceException  Could not access underlying data store.
     */
    @SuppressWarnings("unchecked")
    public List<ExternalSecurityService> getSecurityServices(BasicTransaction tx) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");

        }

        try
        {
            Criteria criteria = tx.getSession().createCriteria(ExternalSecurityService.class);
            
            return criteria.list();  
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve External Security Service configuration from underlaying data store.", e);
        }   
    }

}
