/*
 * TestChangeSinceInfo.java
 * Created: 8 Sep 2015
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

package sif3.common.test.model;

import au.com.systemic.framework.utils.DateUtils;
import sif3.common.model.ChangedSinceInfo;

/**
 * @author Joerg Huber
 *
 */
public class TestChangeSinceInfo
{
    public static void main(String[] args)
    {
        ChangedSinceInfo changeSince = new ChangedSinceInfo();
        System.out.println("Dafault Constructor: "+changeSince);

        String opaqueMarker = DateUtils.nowAsISO8601();
        changeSince.setChangesSinceMarker(opaqueMarker);
        
        System.out.println("After Setting Opaque Marker: "+changeSince);
    }
}
