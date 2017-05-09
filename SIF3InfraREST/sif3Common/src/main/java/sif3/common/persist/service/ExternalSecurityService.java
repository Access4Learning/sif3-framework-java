/*
 * ExternalSecurityService.java
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
package sif3.common.persist.service;

import java.util.List;

import sif3.common.exception.PersistenceException;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.dao.BaseDAO;
import sif3.common.persist.dao.ExternalSecurityServiceDAO;

/**
 * @author Joerg Huber
 *
 */
public class ExternalSecurityService extends DBService
{
    private ExternalSecurityServiceDAO externalSecurityServiceDAO = new ExternalSecurityServiceDAO();

    @Override
    public BaseDAO getDAO()
    {
        return externalSecurityServiceDAO;
    }

    /**
     * This method attempts to retrieve all defined security services as listed in the SIF3_EXT_ECURITY_SERVICE table. If no 
     * services are defined then an empty list is returned. With each returned service all the related parameters from the
     *  SIF3_SEC_SERVICE_PARAM that relate to a specific service are also returned (i.e. lazy loading is disabled).
     * 
     * @return See desc.
     * 
     * @throws PersistenceException Could not access underlying data store.
     */
    public List<sif3.common.persist.model.ExternalSecurityService> getSecurityServices() throws IllegalArgumentException, PersistenceException
    {
        List<sif3.common.persist.model.ExternalSecurityService> rows = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            rows = externalSecurityServiceDAO.getSecurityServices(tx);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to retrieve list of external security service configurations.", true, false);
        }
        return rows;
    }
}
