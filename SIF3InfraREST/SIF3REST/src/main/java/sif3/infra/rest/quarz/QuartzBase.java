/*
 * QuartzBase.java
 * Created: 26 Jun 2018
 *
 * Copyright 2018 Systemic Pty Ltd
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

package sif3.infra.rest.quarz;

import java.io.Serializable;

/**
 * Base class with useful methods and constants to be used in this package for scheduling tasks using Quartz.
 * @author Joerg Huber
 *
 */
public class QuartzBase implements Serializable
{
    private static final long serialVersionUID = -4629650291775704231L;
    
    /* Group to be used with Provider Quartz Jobs */
    public static final String PROVIDER_GROUP = "ProviderQuartzGroup";
    
    /* Group to be used with Consumer Quartz Jobs */
    public static final String CONSUMER_GROUP = "ConsumerQuartzGroup";
}
