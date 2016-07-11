/*
 * Crown Copyright © Department for Education (UK) 2016
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

package sif3.common.persist.service;

import java.util.List;

import sif3.common.exception.PersistenceException;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.dao.BaseDAO;
import sif3.common.persist.dao.SIF3BindingDAO;
import sif3.common.persist.model.SIF3ObjectBinding;

public class SIF3BindingService extends DBService
{

    private SIF3BindingDAO sif3BindingDAO = new SIF3BindingDAO();

    @Override
    public BaseDAO getDAO()
    {
        return sif3BindingDAO;
    }

    public List<SIF3ObjectBinding> getBindings()
            throws IllegalArgumentException, PersistenceException
    {
        List<SIF3ObjectBinding> bindings = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            bindings = sif3BindingDAO.getBindings(tx);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to retrieve bindings.", true, false);
        }
        return bindings;
    }

    public SIF3ObjectBinding getBindingByObjectId(String objectId)
            throws IllegalArgumentException, PersistenceException
    {
        SIF3ObjectBinding binding = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            binding = sif3BindingDAO.getBindingByObjectId(tx, objectId);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to retrieve binding for object with ID '" + objectId + "'.",
                    true, false);
        }
        return binding;
    }

    public SIF3ObjectBinding save(SIF3ObjectBinding binding)
            throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            String objectId = sif3BindingDAO.save(tx, binding);
            tx.commit();
            return getBindingByObjectId(objectId);
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex,
                    "Failed to save binding for object '" + binding.getObjectId()
                            + "' against ownerId '" + binding.getOwnerId() + "'",
                    true, false);
            return null;
        }
    }

    public void removeBindingByObjectId(String objectId)
            throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            sif3BindingDAO.removeByObjectId(tx, objectId);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to remove binding for object ID '" + objectId + "'.", true,
                    true);
        }
    }

    public void removeBindingsByOwnerId(String ownerId)
            throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            sif3BindingDAO.removeByOwnerId(tx, ownerId);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex,
                    "Failed to remove bindings for ownerId '" + ownerId + "'.", true,
                    true);
        }
    }

    public SIF3ObjectBinding bind(String objectId, String ownerId)
            throws IllegalArgumentException, PersistenceException
    {
        SIF3ObjectBinding binding = new SIF3ObjectBinding();
        binding.setObjectId(objectId);
        binding.setOwnerId(ownerId);
        return save(binding);
    }

    public void unbind(String objectId) throws IllegalArgumentException, PersistenceException
    {
        removeBindingByObjectId(objectId);
    }
    
    public boolean isBound(String objectId) throws IllegalArgumentException, PersistenceException
    {
        return getBindingByObjectId(objectId) != null;
    }
    
    public boolean isBound(String objectId, String sessionToken) throws IllegalArgumentException, PersistenceException
    {
        SIF3ObjectBinding binding = getBindingByObjectId(objectId);
        if(binding == null) {
            return false;
        }
        return  binding.getOwnerId().equals(sessionToken);
    }
}
