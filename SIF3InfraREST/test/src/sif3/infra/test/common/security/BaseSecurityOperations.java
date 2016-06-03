/*
 * BaseSecurityOperations.java
 * Created: 14 Jul 2015
 *
 * Copyright 2015 Systemic Pty Ltd
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

import java.nio.charset.Charset;
import java.text.ParseException;

import org.apache.commons.codec.binary.Base64;

import au.com.systemic.framework.utils.DateUtils;
import sif3.common.model.EnvironmentKey;
import sif3.common.model.security.TokenCoreInfo;
import sif3.common.model.security.TokenInfo;

/**
 * This is just a dummy implementation on how a token could be generated. It uses the applicationKey and a timestamp.
 * 
 * @author Joerg Huber
 *
 */
public class BaseSecurityOperations
{
    private static final int MINUTES = 60;

    @SuppressWarnings("unused")
    public static TokenInfo createToken(TokenCoreInfo coreInfo, String password)
    {
        String iso8601Str = "2016-05-17T14:01:00Z";
        String token = coreInfo.getAppUserInfo().getApplicationKey()+":"+iso8601Str;
        token = new String(Base64.encodeBase64(token.getBytes()), Charset.forName("ASCII"));
        TokenInfo newToken = null;
        
        try
        {
            newToken = new TokenInfo(token, DateUtils.stringToDate(iso8601Str, DateUtils.ISO_8601));
        }
        catch (Exception ex)
        {
            System.out.println("Failed to create token");
            ex.printStackTrace();
            return null;            
        }
/*        
        TokenInfo newToken = new TokenInfo(coreInfo);
        Date expiryDate = DateUtils.getDateWithAddedMinutes(new Date(), MINUTES);
        String token = newToken.getAppUserInfo().getApplicationKey()+":"+DateUtils.getISO8601(expiryDate);
        newToken.setTokenExpiryDate(expiryDate);
        newToken.setToken(new String(Base64.encodeBase64(token.getBytes()), Charset.forName("ASCII")));
*/        
        return newToken;
    }
    
    public static TokenInfo getTokenInfo(String token)
    {
        TokenInfo newToken = new TokenInfo(token);
        String decodedToken = new String(Base64.decodeBase64(token), Charset.forName("ASCII"));
        System.out.println("Decoded Token: "+decodedToken);
        int pos = decodedToken.indexOf(":");
        String appKey = decodedToken.substring(0, pos);
        String expDate = decodedToken.substring(pos+1);
//        newToken.setAppUserInfo(new EnvironmentKey(null, appKey));
        newToken.setAppUserInfo(new EnvironmentKey("test", appKey));
        try
        {
            newToken.setTokenExpiryDate(DateUtils.stringToDate(expDate, DateUtils.ISO_8601));
        }
        catch (ParseException e)
        {
            System.out.println("Failed to convert date string to date. Expected date format is "+DateUtils.ISO_8601.toString()+". Date String is "+expDate);
            return null;
        }
        
        return newToken;
    }
    
    public static void main(String[] args)
    {
        TokenCoreInfo coreInfo = new TokenCoreInfo();
        coreInfo.setAppUserInfo(new EnvironmentKey(null, "BearerTest"));
        TokenInfo tokenInfo = createToken(coreInfo, "test");
        System.out.println("Created Token: "+tokenInfo);
        
        System.out.println("Extracted token info: "+getTokenInfo(tokenInfo.getToken()));
    }
}
