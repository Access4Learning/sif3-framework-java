/*
 * TokenInfo.java Created: 12/12/2014
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
package sif3.common.model.security;

import java.io.Serializable;
import java.util.Date;

import au.com.systemic.framework.utils.StringUtils;

public class TokenInfo extends TokenCoreInfo implements Serializable
{
	private static final long serialVersionUID = 1429507572774333454L;

	// Date when token expires. Null = no expire date
	private Date tokenExpiryDate = null;
	private String token = null;

    public TokenInfo(String token) throws IllegalArgumentException
    {
        this(token, null);
    }

    public TokenInfo(String token, Date expireDate) throws IllegalArgumentException
    {
        super();
        if (StringUtils.isEmpty(token))
        {
            throw new IllegalArgumentException("Token must not be empty or null.");
        }
        setToken(token);
        setTokenExpiryDate(expireDate);
    }

    public TokenInfo(TokenCoreInfo coreInfo) throws IllegalArgumentException
    {
        super(coreInfo);
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public Date getTokenExpiryDate()
    {
        return tokenExpiryDate;
    }

    public void setTokenExpiryDate(Date tokenExpiryDate)
    {
        this.tokenExpiryDate = tokenExpiryDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "TokenInfo [tokenExpiryDate=" + tokenExpiryDate + ", token=" + token
                + ", toString()=" + super.toString() + "]";
    }
}
