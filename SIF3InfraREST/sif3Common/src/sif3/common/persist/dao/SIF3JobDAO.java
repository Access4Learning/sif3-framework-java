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
import sif3.common.persist.model.SIF3Job;

/**
 * DAO class for SIF Jobs, the object that represents the state of a functional service.
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public class SIF3JobDAO extends BaseDAO
{

    protected final Logger logger = Logger.getLogger(getClass());

    @SuppressWarnings("unchecked")
    public List<SIF3Job> getJobs(BasicTransaction tx) throws IllegalArgumentException,
            PersistenceException, UnmarshalException, UnsupportedMediaTypeException
    {
        try
        {
            return tx.getSession().createCriteria(SIF3Job.class).list();
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve SIF3 jobs.", e);
        }
    }

    @SuppressWarnings("unchecked")
    private SIF3Job getObjectByRefId(BasicTransaction tx, String refId)
    {
        if (StringUtils.isEmpty(refId))
        {
            throw new IllegalArgumentException("ID is empty or null.");
        }

        Criteria criteria = tx.getSession().createCriteria(SIF3Job.class)
                .add(Restrictions.eq("id", refId));
        List<SIF3Job> jobs = criteria.list();

        // There can only be a maximum of one
        if (jobs.isEmpty())
        {
            // not in cache
            logger.debug("No job for id = '" + refId + "' exists.");
            return null;
        }
        else
        {
            // exists
            return jobs.get(0);
        }
    }

    public SIF3Job getJobByRefId(BasicTransaction tx, String refId)
            throws IllegalArgumentException, PersistenceException
    {
        try
        {
            return getObjectByRefId(tx, refId);
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve SIF3 job for ID = '" + refId + "'.",
                    e);
        }
    }

    public String save(BasicTransaction tx, SIF3Job job)
            throws IllegalArgumentException, PersistenceException
    {
        if (job == null)
        {
            throw new IllegalArgumentException("Job is null.");
        }

        try
        {
            if (StringUtils.isEmpty(job.getId()))
            {
                logger.info("Attempting to save Job without an advisory ID");
                return (String) tx.getSession().save(job);
            }
            else
            {
                logger.info("Attempting to save Job using the advisory ID " + job.getId());
                tx.getSession().saveOrUpdate(job.getId(), job);
                return job.getId();
            }
        }
        catch (HibernateException e)
        {
            throw new PersistenceException(
                    "Unable to save job object with ID='" + job.getId() + "'.", e);
        }
    }

    public void removeById(BasicTransaction tx, String id) throws PersistenceException
    {
        try
        {
            SIF3Job job = getObjectByRefId(tx, id);
            if (job != null)
            {
                tx.getSession().delete(id, job);
            }
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Failed to delete Job with ID='" + id + "'.", e);
        }
    }
}
