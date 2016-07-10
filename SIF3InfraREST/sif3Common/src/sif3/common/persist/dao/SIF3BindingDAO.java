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

package sif3.common.persist.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.model.SIF3ObjectBinding;

public class SIF3BindingDAO extends BaseDAO
{

    protected final Logger logger = Logger.getLogger(getClass());

    @SuppressWarnings("unchecked")
    public List<SIF3ObjectBinding> getBindings(BasicTransaction tx) throws IllegalArgumentException,
            PersistenceException, UnmarshalException, UnsupportedMediaTypeException
    {
        try
        {
            return tx.getSession().createCriteria(SIF3ObjectBinding.class).list();
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve SIF3 bindings.", e);
        }
    }

    @SuppressWarnings("unchecked")
    private SIF3ObjectBinding getObjectByRefId(BasicTransaction tx, String refId)
    {
        if (StringUtils.isEmpty(refId))
        {
            throw new IllegalArgumentException("ID is empty or null.");
        }

        Criteria criteria = tx.getSession().createCriteria(SIF3ObjectBinding.class)
                .add(Restrictions.eq("objectId", refId));
        List<SIF3ObjectBinding> bindings = criteria.list();

        // There can only be a maximum of one
        if (bindings.isEmpty())
        {
            // not in cache
            logger.debug("No binding found for object with id '" + refId + "'.");
            return null;
        }
        else
        {
            // exists
            return bindings.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    private List<SIF3ObjectBinding> getObjectBySessionToken(BasicTransaction tx,
            String sessionToken)
    {
        if (StringUtils.isEmpty(sessionToken))
        {
            throw new IllegalArgumentException("Session token is empty or null.");
        }

        Criteria criteria = tx.getSession().createCriteria(SIF3ObjectBinding.class)
                .add(Restrictions.eq("sessionToken", sessionToken));
        List<SIF3ObjectBinding> bindings = criteria.list();

        // There can only be a maximum of one
        if (bindings.isEmpty())
        {
            // not in cache
            logger.debug("No bindings found for session token '" + sessionToken + "'.");
        }
        return bindings;
    }

    public SIF3ObjectBinding getBindingByObjectId(BasicTransaction tx, String objectId)
            throws IllegalArgumentException, PersistenceException
    {
        try
        {
            return getObjectByRefId(tx, objectId);
        }
        catch (HibernateException e)
        {
            throw new PersistenceException(
                    "Unable to retrieve SIF3 binding for ID = '" + objectId + "'.", e);
        }
    }
    
    public List<SIF3ObjectBinding> getBindingsBySessionToken(BasicTransaction tx, String sessionToken)
            throws IllegalArgumentException, PersistenceException
    {
        try
        {
            return getObjectBySessionToken(tx, sessionToken);
        }
        catch (HibernateException e)
        {
            throw new PersistenceException(
                    "Unable to retrieve SIF3 bindings for session token '" + sessionToken + "'.", e);
        }
    }

    public String save(BasicTransaction tx, SIF3ObjectBinding binding)
            throws IllegalArgumentException, PersistenceException
    {
        if (binding == null)
        {
            throw new IllegalArgumentException("Binding is null.");
        }

        try
        {
            if (StringUtils.isEmpty(binding.getObjectId()))
            {
                throw new IllegalArgumentException("The objectId of the binding is empty or null.");
            }

            logger.info("Attempting to save binding for " + binding.getObjectId() + " in "
                    + binding.getSessionToken());
            tx.getSession().saveOrUpdate(binding.getObjectId(), binding);
            return binding.getObjectId();
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to save binding for object "
                    + binding.getObjectId() + " in " + binding.getSessionToken(), e);
        }
    }

    public void removeByObjectId(BasicTransaction tx, String id) throws PersistenceException
    {
        try
        {
            SIF3ObjectBinding binding = getObjectByRefId(tx, id);
            if (binding != null)
            {
                tx.getSession().delete(id, binding);
            }
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Failed to delete binding with ID='" + id + "'.", e);
        }
    }

    public void removeBySessionToken(BasicTransaction tx, String sessionToken)
            throws PersistenceException
    {
        try
        {
            List<SIF3ObjectBinding> bindings = getObjectBySessionToken(tx, sessionToken);
            if (bindings != null)
            {
                for(SIF3ObjectBinding binding : bindings) {
                    tx.getSession().delete(sessionToken, binding);
                }
            }
        }
        catch (HibernateException e)
        {
            throw new PersistenceException(
                    "Failed to delete binding with ID='" + sessionToken + "'.", e);
        }
    }
}
