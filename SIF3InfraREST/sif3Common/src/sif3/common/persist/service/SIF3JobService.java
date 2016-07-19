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
import sif3.common.persist.dao.SIF3JobDAO;
import sif3.common.persist.model.SIF3Job;
import sif3.common.persist.service.DBService;

/**
 * Service through which SIF3 job objects can be interrogated.
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public class SIF3JobService extends DBService
{

    private SIF3JobDAO sif3JobDAO = new SIF3JobDAO();

    @Override
    public BaseDAO getDAO()
    {
        return sif3JobDAO;
    }

    public List<SIF3Job> getJobs() throws IllegalArgumentException, PersistenceException
    {
        List<SIF3Job> jobs = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            jobs = sif3JobDAO.getJobs(tx);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to retrieve jobs.", true, false);
        }
        return jobs;
    }

    public SIF3Job getJobById(String id) throws IllegalArgumentException, PersistenceException
    {
        SIF3Job job = null;
        BasicTransaction tx = null;

        try
        {
            tx = startTransaction();
            job = sif3JobDAO.getJobByRefId(tx, id);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to retrieve job for ID = '" + id + "'.", true, false);
        }
        return job;
    }

    public SIF3Job save(SIF3Job job) throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            String id = sif3JobDAO.save(tx, job);
            tx.commit();
            return getJobById(id);
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to save Job with ID='" + job.getId() + "'.", true, false);
            return null;
        }
    }

    public void removeJobById(String id) throws IllegalArgumentException, PersistenceException
    {
        BasicTransaction tx = null;
        try
        {
            tx = startTransaction();
            sif3JobDAO.removeById(tx, id);
            tx.commit();
        }
        catch (Exception ex)
        {
            rollback(tx);
            exceptionMapper(ex, "Failed to remove Job for ID = '" + id + "'.", true, true);
        }
    }
}
