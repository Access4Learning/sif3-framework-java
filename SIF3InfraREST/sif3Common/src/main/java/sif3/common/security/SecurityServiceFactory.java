/*
 * SecurityServiceFactory.java
 * Created: 11 Apr 2017
 *
 * Copyright 2017 Systemic Pty Ltd
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

package sif3.common.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.model.security.InternalSecurityServiceConstants;
import sif3.common.model.security.SecurityServiceInfo;
import sif3.common.model.security.SecurityServiceKey;
import sif3.common.persist.model.ExternalSecurityService;

/**
 * This class is the factory for the security service. The SIF3 Framework supports Basic and SIF_HMACSHA256 as security and authentication
 * mechanism. These are defined as the known security concepts for SIF 3. If any other more generic security shall be used, such
 * as OAuth, Active Directory etc instead of the SIF 3 specified options then this framework allows this by means of implementing a
 * custom security interface. This factory class gives access to this custom security interface as well as the default BASIC and 
 * SIF_HMACSHA256.<br/><br/> 
 * 
 * To enable any security service including the default SIF3 ones a implementation of the AbstractSecurity Service for a given
 * authentication method will be required. Access to security implementations within the SIF3 Framework will use this SecurityServiceFactory.
 * If a SIF3 Adapter using this framework must set the 'env.authentication.method' which will be used in this factory to return the appropriate
 * implementation. The tables SIF3_EXT_SECURITY_SERVICE must have an entry for that adapter property to ensure that the correct configuration
 * can be loaded and returned by this factory.<br/><br/>
 * 
 * @author Joerg Huber
 *
 */
public class SecurityServiceFactory implements Serializable
{
   private static final long serialVersionUID = -4266212183461454328L;
   private static final Logger logger = LoggerFactory.getLogger(SecurityServiceFactory.class);

   private static SecurityServiceFactory factory = null;
   
   private HashMap<SecurityServiceKey, ExternalSecurityService> securityServices = new HashMap<SecurityServiceKey, ExternalSecurityService>();
   
   /**
    * This method returns a ExternalSecurityService POJO that holds information about a Security Service. The POJO returned depends
    * on the two input parameters. SIF3 Security Service Information is also returned. These are for 'Basic' and 'SIF_HMACSHA256'. If
    * any other authentication method is used it must be configured in the SIF3_EXT_SECURITY_SERVICE table. The 'authenticationMethod'
    * parameter must match a value in the AUTH_METHOD column of that table (case insensitive!). If no such service can be found then null
    * is returned. Please refer to the developer's guide section TBD for more details on how to configure external security services.
    * 
    * @param authenticationMethod The name of the authentication method for which a security service POJO shall be returned.
    *                             There are two internal ones available called 'Basic' and 'SIF_HMACSHA256'. To get an other security
    *                             service it is expected that the caller to this method passes the 'name' of the authentication method
    *                             to this method. This would generally be the <b>first</b> token of the "Authorization" HTTP header.
    *                             For example if OAuth is used the Authorization HTTP Header would be of the form 'Bearer dfuy64kjadfuijh'.
    *                             The first token in this case would be 'Bearer'.
    * @param adapterType The adapter type as defined by the enumeration. This enables that a consumer and a provider can have different 
    *                    implementation classes.
    *                    
    * @return See description.
    */
   public static ExternalSecurityService getSecurityService(String authenticationMethod, AdapterType adapterType)
   {
       try
        {
            if (factory == null) // not yet initialised. First time access.
            {
                factory = new SecurityServiceFactory();
            }

            // If we get here then we do have a factory initialised and we should be able to instantiate the security service.
            // Check if there is a implementation for the given method and adapter type.
            ExternalSecurityService service = factory.getSecurityServices().get(new SecurityServiceKey(authenticationMethod, adapterType.name()));
            if (service != null)
            {
                return service;
            }
            else
            {
                logger.error("No implementation can be found for a security service with the Authentication Method = "+authenticationMethod+" and Adapeter Type = "+adapterType+" in the table SIF3_EXT_SECURITY_SERVICE.");
                return null;
            }
        }
        catch (Exception ex)
        {
            logger.error("Failed to initialise Security Service factory: " + ex.getMessage(), ex);
            factory = null;

            // Indicate to the caller that call failed.
            return null;
        }
    }
   
   /*---------------------*/
   /*-- Private methods --*/
   /*---------------------*/
   /*
    * MUST throw an exception if anything fails!
    */
    private SecurityServiceFactory() throws Exception
    {
        try
        {
            sif3.common.persist.service.ExternalSecurityService service = new sif3.common.persist.service.ExternalSecurityService();
            List<ExternalSecurityService> dbSecurityServices = service.getSecurityServices();
            if ((dbSecurityServices != null) && (dbSecurityServices.size() > 0))
            {
                for (ExternalSecurityService extService : dbSecurityServices)
                {
                    // Only put services into the cache that have an implementation class defined.
                    if (StringUtils.notEmpty(extService.getImplementationClass()))
                    {
                        getSecurityServices().put(new SecurityServiceKey(extService.getAuthenticationMethod(), extService.getAdapterType()), extService);
                    }
                }
            }
            
            // Now we put the SIF3 Internal Security configuration into the factory. These will be special cases!!
            for (SecurityServiceInfo internalService : InternalSecurityServiceConstants.ALL_INTERNAL_SERVICES)
            {
                getSecurityServices().put(new SecurityServiceKey(internalService.getAuthenticationMethod(), internalService.getAdapterType()),
                                          new ExternalSecurityService(internalService));
            }
            
//            for (AdapterType adapterType : AdapterType.values())
//            {
//                addInternalService(AuthenticationType.Basic, adapterType);
//                addInternalService(AuthenticationType.SIF_HMACSHA256, adapterType);               
//            }
            logger.debug("List of Security Services:\n"+getSecurityServices());
        }
        catch (Exception ex)
        {
            logger.error("Failed to load external service configuration form the data store (SIF3_EXT_SECURITY_SERVICE): " + ex.getMessage(), ex);
            throw new Exception(ex);
        }
    }

    private HashMap<SecurityServiceKey, ExternalSecurityService> getSecurityServices()
    {
        return securityServices;
    }

//    private void setSecurityServices(HashMap<SecurityServiceKey, ExternalSecurityService> securityServices)
//    {
//        this.securityServices = securityServices;
//    }
    
//    private void addInternalService(AuthenticationType authenticationType, AdapterType adapterType)
//    {
//        getSecurityServices().put(new SecurityServiceKey(authenticationType.name(), adapterType.name()),
//                                  new ExternalSecurityService(authenticationType.name(), adapterType.name(), authenticationType.name(), authenticationType.name(), Boolean.TRUE, authenticationType));
//    }
}
