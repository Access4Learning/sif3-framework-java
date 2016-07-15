/*
 * AuthenticationInfo.java Created: 07/03/2014
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

package sif3.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * This is a simple utility class to deal with components that make up the authentication token in
 * SIF3. If the authentication method is 'Basic' or 'SIF_HMACSHA256' then the authentication token
 * has the structure as defined in the SIF specification, meaning it is base64 encoded and can be
 * split on the ':' that separates the applicationKey/sessionToken and the password/HMAC Hash. If
 * the authentication token is 'Bearer' then it is assumed that the token is managed by a security
 * mechanism outside of the SIF Specification and therefore no assumptions about the structure of
 * the token can be made. In this case the 'password' component of this class should never be
 * populated and the 'userToken' component should always hold the full token given by the 'Bearer'.
 * 
 * @author Joerg Huber
 *
 */
public class AuthenticationInfo implements Serializable
{
    private static final long serialVersionUID = -8736784141972585133L;

    /* Defines valid authentication methods for SIF 3.0 as in the Environment XML */
    public enum AuthenticationMethod
    {
        BASIC, SIF_HMACSHA256, Bearer;

        /**
         * Gets the text that appears the authorization header property. Usually this is the same as
         * the method name, but can differ, for example the method "BASIC" should appear in the
         * authorization header as "Basic".
         * 
         * @return See description.
         */
        public String getAuthTokenString()
        {
            if (this.equals(BASIC))
            {
                return "Basic";
            }
            return this.name();
        }

        /**
         * A case insensitive check if the method provided as a string matches this authentication
         * method.
         * 
         * @param method
         *            The string to check. Will be trimmed before compared.
         * @return True if the argument is the same as the method name, ignoring case. False
         *         otherwise.
         */
        public boolean isFor(String method)
        {
            return this.name().equalsIgnoreCase(method.trim());
        }

        /**
         * Finds an authentication method in the same way as valueOf, but not case sensitive.
         * 
         * @param method
         *            The method to look for as a string. Will be trimmed before compared.
         * @return The AuthenticationMethod that matched the name (case insensitive).
         * @throws IllegalArgumentException
         *             When the method string does not identify any supported AuthenticaionMethod.
         */
        public static AuthenticationMethod lookup(String method)
        {
            for (AuthenticationMethod m : AuthenticationMethod.values())
            {
                if (m.name().equalsIgnoreCase(method))
                {
                    return m;
                }
            }
            throw new IllegalArgumentException(
                    "Could not find the requested authentication method " + method);
        }
    };

    private AuthenticationMethod authMethod = AuthenticationMethod.BASIC;
    private String               userToken  = null;
    private String               password   = null;
    private Date                 expireDate = null;                      // date when token expires.

    public AuthenticationInfo()
    {
        this(AuthenticationMethod.BASIC, null, null, null);
    }

    public AuthenticationInfo(AuthenticationMethod authMethod, String userToken, String password,
            Date expireDate)
    {
        super();
        setAuthMethod(authMethod);
        setUserToken(userToken);
        setPassword(password);
        setExpireDate(expireDate);
    }

    public AuthenticationInfo(AuthenticationMethod authMethod, String userToken, String password)
    {
        this(authMethod, userToken, password, null);
    }

    public AuthenticationInfo(String authMethod, String userToken, String password)
    {
        this(null, userToken, password, null);
        setAuthMethod(authMethod);
    }

    public AuthenticationMethod getAuthMethod()
    {
        return authMethod;
    }

    public void setAuthMethod(AuthenticationMethod authMethod)
    {
        this.authMethod = authMethod;
    }

    public void setAuthMethod(String authMethod)
    {
        try
        {
            this.authMethod = AuthenticationMethod.lookup(authMethod);
        }
        catch (Exception ex)
        {
            this.authMethod = AuthenticationMethod.BASIC;
        }
    }

    public String getUserToken()
    {
        return userToken;
    }

    public void setUserToken(String userToken)
    {
        this.userToken = userToken;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }

    @Override
    public String toString()
    {
        return "AuthenticationInfo [authMethod=" + authMethod + ", userToken=" + userToken
                + ", password=" + password + ", expireDate=" + expireDate + "]";
    }

}
