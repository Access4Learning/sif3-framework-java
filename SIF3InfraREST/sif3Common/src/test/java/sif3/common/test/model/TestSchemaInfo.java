/*
 * TestSchemaInfo.java
 * Created: 28 Oct 2019
 *
 * Copyright 2019 Systemic Pty Ltd
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

import sif3.common.CommonConstants.SchemaType;
import sif3.common.model.SchemaInfo;

/**
 * @author Joerg Huber
 *
 */
public class TestSchemaInfo
{
    public static void main(String[] argv)
    {
        SchemaInfo schemaInfo = new SchemaInfo();
        schemaInfo.setModelType(SchemaInfo.MODEL_TYPE_DM);
        schemaInfo.setModelDomain("nz");
        schemaInfo.setModelVersion("1.0");
        System.out.println(schemaInfo.getFullSchemaValue());
        
        schemaInfo = new SchemaInfo();
        schemaInfo.setModelType(SchemaInfo.MODEL_TYPE_INFRA);
        schemaInfo.setModelDomain(SchemaInfo.MODEL_DOMAIN_INFRA);
        schemaInfo.setModelVersion("3.2.1");
        System.out.println(schemaInfo.getFullSchemaValue());
        
        // and reverse
        SchemaInfo schemaInfoReverse = new SchemaInfo();
        schemaInfoReverse.populate(schemaInfo.getFullSchemaValue());
        System.out.println(schemaInfoReverse);
        System.out.println(schemaInfoReverse.getFullSchemaValue());

        schemaInfo = new SchemaInfo();
        schemaInfo.setModelType(SchemaInfo.MODEL_TYPE_DM);
        schemaInfo.setModelDomain("au");
        schemaInfo.setModelVersion("3.4.5");
        schemaInfo.setSchemaType(SchemaType.goessner.name());
        System.out.println(schemaInfo.getFullSchemaValue());
        
        schemaInfoReverse = new SchemaInfo();
        schemaInfoReverse.populate(schemaInfo.getFullSchemaValue());
        System.out.println(schemaInfoReverse);
        System.out.println(schemaInfoReverse.getFullSchemaValue());
        
        schemaInfo = new SchemaInfo();
        schemaInfo.setModelType(SchemaInfo.MODEL_TYPE_DM);
        schemaInfo.setModelDomain("au");
        schemaInfo.setModelVersion("3.4.5");
        schemaInfo.setQualityValue(0.8);
        System.out.println(schemaInfo.getFullSchemaValue());
        
        schemaInfoReverse = new SchemaInfo();
        schemaInfoReverse.populate(schemaInfo.getFullSchemaValue());
        System.out.println(schemaInfoReverse);
        System.out.println(schemaInfoReverse.getFullSchemaValue());

        schemaInfo = new SchemaInfo(SchemaInfo.MODEL_TYPE_INFRA, SchemaInfo.MODEL_DOMAIN_INFRA, "3.3");
        System.out.println(schemaInfo.getFullSchemaValue());
        
        schemaInfo = new SchemaInfo();
        schemaInfo.setModelType(SchemaInfo.MODEL_TYPE_DM);
        schemaInfo.setModelDomain("us");
        schemaInfo.setModelVersion("3.3");
        schemaInfo.setSchemaType(SchemaType.xml.name());
        schemaInfo.setQualityValue(1.0);
        System.out.println(schemaInfo.getFullSchemaValue());
        
        schemaInfoReverse = new SchemaInfo();
        schemaInfoReverse.populate(schemaInfo.getFullSchemaValue());
        System.out.println(schemaInfoReverse);
        System.out.println(schemaInfoReverse.getFullSchemaValue());

    }   
}
