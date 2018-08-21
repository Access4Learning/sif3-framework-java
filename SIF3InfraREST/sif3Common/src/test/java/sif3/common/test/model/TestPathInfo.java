/*
 * TestPathInfo.java
 * Created: 1 Dec 2015
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

import sif3.common.model.URIPathInfo;

/**
 * @author Joerg Huber
 *
 */
public class TestPathInfo
{
    public static final String OBJ_SVC= "Object";
    public static final String SP_SVC= "SERVICEPath";
    public static final String FS_SVC= "FUNCTIONAL";
    
    public static void main(String[] argv)
    {
        // Object Services
        System.out.println(new URIPathInfo("/SchoolInfos.xml;zoneId=ABC;", OBJ_SVC));
        System.out.println(new URIPathInfo("/SchoolInfos/1234;zoneId=ABC;", OBJ_SVC));
        System.out.println(new URIPathInfo("/SchoolInfos;zoneId=ABC;test=2?myParam=aabss", OBJ_SVC));
        System.out.println(new URIPathInfo("/SchoolInfos;zoneId=ABC;test=2?myParam=aabss&param2=&param3=56", OBJ_SVC));
        
        //Service Paths
        System.out.println(new URIPathInfo("/SchoolInfos/1234-1245/StudentPersonal.xml;zoneId=ABC;contextId=DEFAULT", SP_SVC));
        System.out.println(new URIPathInfo("/SchoolInfos/1234-1245/StudentPersonal.xml;zoneId=ABC;contextId=DEFAULT?myParam=7", SP_SVC));
        System.out.println(new URIPathInfo("/SchoolInfos/1234-1245/StudentPersonals/9876-0998AF/StudentDailyAttendances;zoneId=ABC;contextId=DEFAULT?myParam=7", SP_SVC));

        // Job Services
        System.out.println(new URIPathInfo("/StudentRollover;contextId=DEFAULT?myParam=7", FS_SVC));
        System.out.println(new URIPathInfo("/StudentRollover/1234-7765-efee;contextId=DEFAULT", FS_SVC));
        System.out.println(new URIPathInfo("/StudentRollover/1234-7765-efee/Phase1?myParam=7", FS_SVC));

    }   
}
