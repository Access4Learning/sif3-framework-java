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

package sif3.infra.test.common.model;

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
        System.out.println("ISO 8601 Date: "+changeSince.getChangedSinceDateAsISO8601(false));
        System.out.println("ISO 8601 Date: "+changeSince.getChangedSinceDateAsISO8601(true));
        
        changeSince.setISO8601ChangedSinceDate("2015-09-08T11:24:36.123Z");
//        changeSince.setISO8601ChangedSinceDate("2015-09-08T11:24:36Z");
        System.out.println("ISO 8601 Date after setting it: "+changeSince.getChangedSinceDateAsISO8601(true));
        
        changeSince.setChangeType("updated ");
        System.out.println("After setting changeType: "+changeSince.getChangeType().name());
    }
}
