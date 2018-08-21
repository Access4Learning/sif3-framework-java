/*
 * StateRights.java
 * Created: 11/01/2013
 *
 * Copyright 2018 Systemic Pty Ltd
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

package sif3.common.model;

import sif3.common.CommonConstants.RightType;

/**
 * @author Joerg Huber
 *
 */
public class StateRights extends ACL
{
    private static final long serialVersionUID = 107494441323318L;

    public StateRights()
    {
        super();
    }
    
    /* (non-Javadoc)
     * @see sif3.common.model.ACL#getRightType()
     */
    @Override
    public RightType getRightType()
    {
        return RightType.STATE;
    }
}
