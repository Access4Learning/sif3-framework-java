/*
 * AppEnvTemplateDAO.java
 * Created: 30/06/2014
 *
 * Copyright 2014 Systemic Pty Ltd
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
import sif3.common.exception.PersistenceException;
import sif3.common.model.EnvironmentKey;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.model.AppEnvironmentTemplate;

/**
 * Implements some low level DB operations relating to the environment templates and their application relationship.
 * 
 * @author Joerg Huber
 *
 */
public class AppEnvTemplateDAO extends BaseDAO
{
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * This method attempts to retrive the application environment template. This works in a hierarchical manner. The lowest level of the hierarchy
   * is when the following values of the environmentKey are set: solutionID, applicationKey, userToken, instanceID.<br/>
   * In this case the instanceID is ignored in determining the actual row because according to SIF3 the instanceID is only used for cases where
   * two different devices access the same environment. The environment composition for both instances is assumed to be the same and therefore
   * the only fields to be used for the determination of the actual environment are solutionID, applicationKey, userToken.<br/> 
   * If there is no template listed for this combination then the next level is attempted: solutionID, applicationKey.<br/>
   * It must be noted that the 'solutionID' is optional. It is possible that the applicationKey is the only value provided and that there is a
   * template for that. In the DB it could have a solutionID set but the consumer doesn't provide it and only provides the applicationKey. This
   * method will also work for that case. The returned row will may or may not have the solutionID set though. It is important that in such a 
   * case it is not possible to have two solutions with the same applicationKey in the SIF3_APP_TEMPLATE table.
   * 
   * @param tx The current transaction. Cannot be null.
   * @param environmentKey The environment key data for which the application environment template shall be returned.
   * 
   * @return See desc.
   * 
   * @throws IllegalArgumentException tx is null, environmentKey is null or all properties of environmentKey are null or empty.
   * @throws PersistenceException Could not access underlying data store.
   */
  public AppEnvironmentTemplate getEnvironmentTemplate(BasicTransaction tx, EnvironmentKey environmentKey) throws IllegalArgumentException, PersistenceException
  {
    if (tx == null)
    {
      throw new IllegalArgumentException("Current transaction is null.");          

    }
    if (environmentKey == null)
    {
      throw new IllegalArgumentException("environmentKey is null.");          
    }
    if (StringUtils.isEmpty(environmentKey.getSolutionID()) && StringUtils.isEmpty(environmentKey.getApplicationKey()) && StringUtils.isEmpty(environmentKey.getUserToken()))
    {
        throw new IllegalArgumentException("solutionID, applicationKey and userToken is empty or null.");
    }
    
    AppEnvironmentTemplate appEnvTpl = null;
    
    // Let's try the lowest level first. Remember solutionID is optional
    if (StringUtils.notEmpty(environmentKey.getApplicationKey()) && StringUtils.notEmpty(environmentKey.getUserToken()) && StringUtils.notEmpty(environmentKey.getInstanceID()))
    {
      appEnvTpl = getEnvironmentTemplate(tx, environmentKey.getSolutionID(), environmentKey.getApplicationKey(), environmentKey.getUserToken(), environmentKey.getInstanceID());
      if (appEnvTpl != null) // all good. We found one. We are done.
      {
        return appEnvTpl;
      }
    }

    // Try the next level up. We have no result, yet.
    if (StringUtils.notEmpty(environmentKey.getApplicationKey()) && StringUtils.notEmpty(environmentKey.getUserToken()))
    {
      appEnvTpl = getEnvironmentTemplate(tx, environmentKey.getSolutionID(), environmentKey.getApplicationKey(), environmentKey.getUserToken(), null);
      if (appEnvTpl != null) // all good. We found one. We are done.
      {
        return appEnvTpl;
      }
    }
    
    // And finally try the top level. We still have no result.
    if (StringUtils.notEmpty(environmentKey.getApplicationKey()))
    {
      appEnvTpl = getEnvironmentTemplate(tx, environmentKey.getSolutionID(), environmentKey.getApplicationKey(), null, null);
      if (appEnvTpl != null) // all good. We found one. We are done.
      {
        return appEnvTpl;
      }
    }

    // If we get here then there is no template listed for the given environmentKey.
    return null;
  }
  
  /*---------------------*/
  /*-- Private methods --*/
  /*---------------------*/
  @SuppressWarnings("unchecked")
  private AppEnvironmentTemplate getEnvironmentTemplate(BasicTransaction tx, String solutionID, String appKey, String userToken, String instanceID) throws PersistenceException
  {
    try
    {
        Criteria criteria = tx.getSession().createCriteria(AppEnvironmentTemplate.class);
        
        // SolutionID is optional. Don't set it to null as a criteria as it might be retrieved.
        if (StringUtils.notEmpty(solutionID))
        {
          criteria = criteria.add(Restrictions.eq("solutionID", solutionID));
        }
        
        if (StringUtils.notEmpty(appKey))
        {
          criteria = criteria.add(Restrictions.eq("applicationKey", appKey));
        }
        else
        {
          criteria = criteria.add(Restrictions.isNull("applicationKey"));
        }

        if (StringUtils.notEmpty(userToken))
        {
          criteria = criteria.add(Restrictions.eq("userToken", userToken));
        }
        else
        {
          criteria = criteria.add(Restrictions.isNull("userToken"));
        }

        if (StringUtils.notEmpty(instanceID))
        {
          criteria = criteria.add(Restrictions.eq("instanceID", instanceID));
        }
        else
        {
          criteria = criteria.add(Restrictions.isNull("instanceID"));
        }

        List<AppEnvironmentTemplate> appTemplates = criteria.list();
        
        // There can only be a maximum of one
        if (appTemplates.isEmpty()) // not in cache, yet
        {
          logger.debug("No application environment template exists for solutionID = '"+ solutionID + "', applicationKey = '" + appKey + "', userToken = '" + userToken + "' and instanceID = '"+instanceID+"' exists.");
          return null;
        }
        else if (appTemplates.size() == 1) // only 1 exists. That is the correct behaviour => Return it
        {
          return appTemplates.get(0);
        }
        else // we have more than one entry for the given criteria. This is an invalid configuration. Report it and throw an exception.
        {
          String errorMsg = "More than one application environment template relationship exists in the SIF3_APP_TEMPLATE table for solutionID = '"+ solutionID + "', applicationKey = '" + appKey + "', userToken = '" + userToken + "' and instanceID = '"+instanceID+"'. Please modify SIF3_APP_TEMPLATE accordingly.";
          logger.error(errorMsg);
          throw new PersistenceException(errorMsg);
        }
    }
    catch (HibernateException e)
    {
        throw new PersistenceException("Unable to retrieve/access SIF3_APP_TEMPLATE for solutionID = '"+ solutionID + "', applicationKey = '" + appKey + "', userToken = '" + userToken + "' and instanceID = '"+instanceID+"'.", e);
    }
  }
}
