/*
 * DummySecurityService.java
 * Created: 15/12/2014
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
package sif3.common.test.security;

import sif3.common.model.RequestMetadata;
import sif3.common.model.security.TokenCoreInfo;
import sif3.common.model.security.TokenInfo;
import sif3.common.security.AbstractSecurityService;
import au.com.systemic.framework.utils.AdvancedProperties;

public class DummySecurityService extends AbstractSecurityService
{
//	private static final long MINUTE = 1000*60;

	public DummySecurityService(AdvancedProperties properties)
	{
		super(properties);
		System.out.println("DummySecurityService Constructor called with property: " + getServiceProperties());
	}

	@Override
	public TokenInfo getTokenInfo(String securityToken, RequestMetadata requestMetadata)
	{
		System.out.println("DummySecurityService.getTokenInfo() called for securityToken = "+securityToken+" and request metadata = "+requestMetadata);
		TokenInfo tokenInfo = BaseSecurityOperations.getTokenInfo(securityToken);
		
//		long expireTime = MINUTE*5; // expire every x minute.
//		TokenInfo tokenInfo = new TokenInfo(securityToken);
//		tokenInfo.setTokenExpiryDate(new Date(((new Date()).getTime()) + expireTime));
//		tokenInfo.setSessionToken("80404112-96f7-4b2e-a118-3db458608877");
//		tokenInfo.setAppUserInfo(new EnvironmentKey(null, "BearerTest"));
//		tokenInfo.setAppUserInfo(new EnvironmentKey(null, "BearerTest1"));
		return tokenInfo;
	}

	@Override
	public boolean validateToken(String securityToken, RequestMetadata requestMetadata)
	{
		System.out.println("DummySecurityService.validateToken() called for securityToken = "+securityToken+" and request metadata = "+requestMetadata);
		return true;
	}

    @Override
    public TokenInfo generateToken(TokenCoreInfo coreInfo, String password)
    {
        System.out.println("TokenCoreInfo: "+coreInfo);
        System.out.println("Value for prop2: "+coreInfo.getOtherInfo().get("prop2"));
        return BaseSecurityOperations.createToken(coreInfo, getServiceProperties().getPropertyAsString("env.pwd", null));
    }
}
