/*
 * JobTemplateDAO.java
 * Created: 21/12/2017
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
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.exception.PersistenceException;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.model.JobTemplate;

/**
 * Implements some low level DB operations relating to the job templates.
 * 
 * @author Joerg Huber
 *
 */
public class JobTemplateDAO extends BaseDAO
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * This method attempts to retrieve the job template by the primary key. If there is no row in the SIF3_JOB_TEMPLATE for the
     * given templateID then null is returned.
     * 
     * @param tx The current transaction. Cannot be null.
     * @param jobTemplateID template ID for which the job template information from the SIF3_JOB_TEMPLATE table shall be returned.
     * 
     * @return See desc.
     * 
     * @throws IllegalArgumentException tx is null.
     * @throws PersistenceException Could not access underlying data store.
     */
    public JobTemplate getJobTemplate(BasicTransaction tx, long jobTemplateID) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        
        try
        {
            return (JobTemplate)tx.getSession().get(JobTemplate.class, jobTemplateID);
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Job Template for jobTemplateID = '"+ jobTemplateID + "'.", e);
        }   
    }
    
    /**
     * This method attempts to retrieve the job template by its URL Name and adapter type. If there is no row in the SIF3_JOB_TEMPLATE for the
     * given parameters then null is returned.
     * 
     * @param tx The current transaction. Cannot be null.
     * @param jobURLName URL Name for which the job template information from the SIF3_JOB_TEMPLATE table shall be returned.
     * @param adapterType Adapter Type for which the job template information from the SIF3_JOB_TEMPLATE table shall be returned.
     * 
     * @return See desc.
     * 
     * @throws IllegalArgumentException tx is null and/or jobURLName is null or empty and/or adapterType is null
     * @throws PersistenceException Could not access underlying data store.
     */
    public JobTemplate getJobTemplateForAdapter(BasicTransaction tx, String jobURLName, AdapterType adapterType) throws IllegalArgumentException, PersistenceException
    {
        if (tx == null)
        {
            throw new IllegalArgumentException("Current transaction is null.");
        }
        
        if (StringUtils.isEmpty(jobURLName))
        {
          throw new IllegalArgumentException("jobURLName empty or null.");          
        }
        
        if (adapterType == null)
        {
          throw new IllegalArgumentException("adapterType is null.");          
        }

        try
        {
            Criteria criteria = tx.getSession().createCriteria(JobTemplate.class)
               .add(Restrictions.eq("urlName", jobURLName))
               .add(Restrictions.eq("adapterType", adapterType.name()));
            
            @SuppressWarnings("unchecked")
            List<JobTemplate> jobTemplates = criteria.list();
            
            // There can only be a maximum of one
            if (jobTemplates.isEmpty()) // no job templates for given name and adapter type
            {
                logger.debug("No Job Template for urlName = '"+ jobURLName + "' and adapterType = '" + adapterType + "' exists.");
                return null;
            }
            else // already exists
            {
                return jobTemplates.get(0);
            }
        }
        catch (HibernateException e)
        {
            throw new PersistenceException("Unable to retrieve Job Template for urlName = '"+ jobURLName + " and adapterType = '" + adapterType + "'.", e);
        }   
    }
}
