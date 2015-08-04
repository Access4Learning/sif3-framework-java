/*
 * TestSecurityServiceFactory.java Created: 15/12/2014
 * 
 * Copyright 2014 Systemic Pty Ltd
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
package sif3.infra.test.common.security;

import sif3.common.model.EnvironmentKey;
import sif3.common.model.security.TokenCoreInfo;
import sif3.common.model.security.TokenInfo;
import sif3.common.security.AbstractSecurityService;
import sif3.common.security.BearerSecurityFactory;
import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.PropertyManager;

public class TestSecurityServiceFactory
{
    private static final boolean IS_PROVDER          = false;

    private static final String  PROV_PROP_FILE_NAME = "StudentProvider";
    private static final String  CONS_PROP_FILE_NAME = "StudentConsumer";

    private AdvancedProperties   properties          = null;

    public TestSecurityServiceFactory()
    {
        PropertyManager propMgr = PropertyManager.getInstance();
        String propFileName = IS_PROVDER ? PROV_PROP_FILE_NAME : CONS_PROP_FILE_NAME;
        propMgr.loadPropertyFile(propFileName);

        properties = propMgr.getProperties(propFileName);
    }

    public void testGetInstance()
    {
    	AbstractSecurityService service = BearerSecurityFactory.getSecurityService(properties);
        if (service != null)
        {
            if (IS_PROVDER)
            {
                TokenInfo tokenInfo = service.getInfo("VGVzdFNJUzoyMDE1LTA3LTE0VDE1OjE5OjAxWg==", null);
                System.out.println(tokenInfo);
            }
            else
            {
                TokenCoreInfo coreInfo = new TokenCoreInfo();
                coreInfo.setAppUserInfo(new EnvironmentKey(null, properties.getPropertyAsString("env.application.key", null)));
//                TokenInfo tokenInfo = service.generateToken(coreInfo, properties.getPropertyAsString("env.pwd", null));
                TokenInfo tokenInfo = service.createToken(coreInfo, properties.getPropertyAsString("env.pwd", null));
                System.out.println(tokenInfo);
            }
        }
        else
        {
            System.out.println("Returned Security Service is null.");
        }
    }

    public static void main(String[] args)
    {
        TestSecurityServiceFactory tester = new TestSecurityServiceFactory();

        System.out.println("Start Testing TestSecurityServiceFactory...");

        tester.testGetInstance();

        System.out.println("End Testing TestSecurityServiceFactory.");
    }
}
