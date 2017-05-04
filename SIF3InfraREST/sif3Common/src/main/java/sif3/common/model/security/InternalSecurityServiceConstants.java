/*
 * InternalSecurityServiceConstants.java
 * Created: 13 Apr 2017
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

package sif3.common.model.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sif3.common.CommonConstants.AdapterType;
import sif3.common.CommonConstants.AuthenticationType;

/**
 * @author Joerg Huber
 *
 */
public class InternalSecurityServiceConstants implements Serializable
{
    private static final long serialVersionUID = -4316613712710000226L;

    //
    // Basic
    //
    public static final SecurityServiceInfo BASIC_CONSUMER_SECURITY = 
            new SecurityServiceInfo(AuthenticationType.Basic.name(), AdapterType.CONSUMER.name(), AuthenticationType.Basic.name(), AuthenticationType.Basic.name(), Boolean.TRUE, AuthenticationType.Basic);

    public static final SecurityServiceInfo BASIC_PROVIDER_SECURITY = 
            new SecurityServiceInfo(AuthenticationType.Basic.name(), AdapterType.PROVIDER.name(), AuthenticationType.Basic.name(), AuthenticationType.Basic.name(), Boolean.TRUE, AuthenticationType.Basic);

    public static final SecurityServiceInfo BASIC_ENV_PROVIDER_SECURITY = 
            new SecurityServiceInfo(AuthenticationType.Basic.name(), AdapterType.ENVIRONMENT_PROVIDER.name(), AuthenticationType.Basic.name(), AuthenticationType.Basic.name(), Boolean.TRUE, AuthenticationType.Basic);

    // SIF_HMACSHA256
    public static final SecurityServiceInfo SIF_HMACSHA256_CONSUMER_SECURITY = 
            new SecurityServiceInfo(AuthenticationType.SIF_HMACSHA256.name(), AdapterType.CONSUMER.name(), AuthenticationType.SIF_HMACSHA256.name(), AuthenticationType.SIF_HMACSHA256.name(), Boolean.TRUE, AuthenticationType.SIF_HMACSHA256);

    public static final SecurityServiceInfo SIF_HMACSHA256_PROVIDER_SECURITY = 
            new SecurityServiceInfo(AuthenticationType.SIF_HMACSHA256.name(), AdapterType.PROVIDER.name(), AuthenticationType.SIF_HMACSHA256.name(), AuthenticationType.SIF_HMACSHA256.name(), Boolean.TRUE, AuthenticationType.SIF_HMACSHA256);

    public static final SecurityServiceInfo SIF_HMACSHA256_ENV_PROVIDER_SECURITY = 
            new SecurityServiceInfo(AuthenticationType.SIF_HMACSHA256.name(), AdapterType.ENVIRONMENT_PROVIDER.name(), AuthenticationType.SIF_HMACSHA256.name(), AuthenticationType.SIF_HMACSHA256.name(), Boolean.TRUE, AuthenticationType.SIF_HMACSHA256);


    public static final List<SecurityServiceInfo> ALL_INTERNAL_SERVICES = 
            new ArrayList<SecurityServiceInfo>(Arrays.asList(BASIC_CONSUMER_SECURITY, 
                                                             BASIC_PROVIDER_SECURITY, 
                                                             BASIC_ENV_PROVIDER_SECURITY, 
                                                             SIF_HMACSHA256_CONSUMER_SECURITY, 
                                                             SIF_HMACSHA256_PROVIDER_SECURITY, 
                                                             SIF_HMACSHA256_ENV_PROVIDER_SECURITY));
    
    // Generic Values not tied to a adapter type.
    public static final SecurityServiceInfo BASIC_GENERIC_SECURITY = 
            new SecurityServiceInfo(AuthenticationType.Basic.name(), null, AuthenticationType.Basic.name(), AuthenticationType.Basic.name(), Boolean.TRUE, AuthenticationType.Basic);
    public static final SecurityServiceInfo SIF_HMACSHA256_GENERIC_SECURITY = 
            new SecurityServiceInfo(AuthenticationType.SIF_HMACSHA256.name(), AdapterType.CONSUMER.name(), AuthenticationType.SIF_HMACSHA256.name(), AuthenticationType.SIF_HMACSHA256.name(), Boolean.TRUE, AuthenticationType.SIF_HMACSHA256);
}
