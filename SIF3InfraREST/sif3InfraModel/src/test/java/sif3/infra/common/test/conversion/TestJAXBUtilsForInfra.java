/*
 * TestJAXBUtilsForInfra.java
 * Created: 10 Jun 2020
 *
 * Copyright 2020 Systemic Pty Ltd
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

package sif3.infra.common.test.conversion;

import javax.xml.bind.JAXBElement;

import au.com.systemic.framework.utils.FileReaderWriter;
import sif3.common.CommonConstants.SchemaType;
import sif3.common.conversion.MarshalFactory;
import sif3.common.exception.MarshalException;
import sif3.common.utils.JAXBUtils;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.model.EnvironmentType;
import sif3.infra.common.model.InfrastructureServiceNamesType;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.PhaseStateType;

/**
 * @author Joerg Huber
 *
 */
public class TestJAXBUtilsForInfra
{
    private final static String XML_BASE_DIR = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST/TestData/xml/input/";

    // Infrastructure Data Model sample files
    private final static String INPUT_JOB_FILE_NAME_XML         = XML_BASE_DIR + "rolloverStudentJob.xml";
    private final static String INPUT_JOB_FILE_NAME_PESC        = XML_BASE_DIR + "rolloverStudentJob-PESC.json";
    private final static String INPUT_JOB_FILE_NAME_GOESSNER    = XML_BASE_DIR + "rolloverStudentJob-GOESSNER.json";

    // Infrastructure Data Model sample files
    private final static String INPUT_ENV_FILE_NAME_XML         = XML_BASE_DIR + "environment.xml";
    private final static String INPUT_ENV_FILE_NAME_PESC        = XML_BASE_DIR + "environment-PESC.json";
    private final static String INPUT_ENV_FILE_NAME_GOESSNER    = XML_BASE_DIR + "renvironment-GOESSNER.json";
    
    /*---------------------*/
    /*-- Utility methods --*/
    /*---------------------*/
    private String loadFileContent(String fileName)
    {
        String content = FileReaderWriter.getFileContent(fileName);
        System.out.println("File content:\n" + content);
        
        return content;
    }

    private Object loadFileContentFromXML(String fileName, Class<?> clazz) throws Exception
    {
        String content = loadFileContent(fileName);
        
        return JAXBUtils.unmarshalFromXMLIntoObject(content, clazz);
    }
    
    private void toXML(Object jaxbObject) throws Exception
    {
        MarshalFactory marshaller = new InfraMarshalFactory();
        
        String xmlStr = marshaller.marshalToXML(jaxbObject);
        System.out.println(jaxbObject.getClass().getSimpleName()+" String:\n"+xmlStr);
    }   
    
    private void testListEnumRepresentation()
    {
        System.out.println("PhaseStateType.COMPLETED.name(): "+ PhaseStateType.COMPLETED.name());
        System.out.println("PhaseStateType.COMPLETED.value(): "+ PhaseStateType.COMPLETED.value());
        System.out.println("PhaseStateType.COMPLETED.toString(): "+ PhaseStateType.COMPLETED.toString());

        System.out.println("InfrastructureServiceNamesType.PROVISION_REQUESTS.name(): "+ InfrastructureServiceNamesType.PROVISION_REQUESTS.name());
        System.out.println("InfrastructureServiceNamesType.PROVISION_REQUESTS.value(): "+ InfrastructureServiceNamesType.PROVISION_REQUESTS.value());
        System.out.println("InfrastructureServiceNamesType.PROVISION_REQUESTS.toString(): "+ InfrastructureServiceNamesType.PROVISION_REQUESTS.toString());
    }

    /*---------------------------------------*/
    /*-- Configure section for Marshalling --*/
    /*---------------------------------------*/
    private void toJSON(JAXBElement<?> jaxbObject, SchemaType schema) throws MarshalException
    {
        int maxIter = 1;
        for (int i = 0; i < maxIter; i++)
        {
            if ((schema == SchemaType.pesc) || (schema == SchemaType.goessner))
            {
                String jsonStr = JAXBUtils.marshalToJSON(jaxbObject, schema);
                System.out.println(jaxbObject.getClass().getSimpleName()+" String:\n"+jsonStr.toString());
            }
            else
            {
                System.out.println("SchemaType must be pesc or goessner. No action taken.");
            }
        }
    }
    
    /*----------------------------------------*/
    /*-- Configure section for umarshalling --*/
    /*----------------------------------------*/
    private Object fromJSON(String fileName, Class<?> clazz, SchemaType schema) throws Exception
    {
        String content = loadFileContent(fileName);

        return JAXBUtils.unmarshalFromJSONIntoObject(content, clazz, schema);
    }
    
    /*------------------*/
    /*-- Test methods --*/
    /*------------------*/

    /*
     * Read an XML file into a SIF Object and then produce JSON according to PESC.
     */
    private void testXMLToJSONJob(String fileName, Class<?> clazz, SchemaType schema) throws Exception
    {
        Object object = loadFileContentFromXML(fileName, clazz);
        
        if (object != null)
        {
            // package the read object into a JAXBElement as required - Assume StudentPersonal
            ObjectFactory of = new ObjectFactory();
            JAXBElement<JobType> job = of.createJob((JobType)object);

            int maxIter = 1;
            for (int i=0; i<maxIter; i++)
            {
                toJSON(job, schema);
            }
        }
    }

    /*
     * Read an XML file into a SIF Object and then produce JSON according to PESC.
     */
    private void testXMLToJSONEnv(String fileName, Class<?> clazz, SchemaType schema) throws Exception
    {
        Object object = loadFileContentFromXML(fileName, clazz);
        
        if (object != null)
        {
            // package the read object into a JAXBElement as required - Assume StudentPersonal
            ObjectFactory of = new ObjectFactory();
            JAXBElement<EnvironmentType> job = of.createEnvironment((EnvironmentType)object);

            int maxIter = 1;
            for (int i=0; i<maxIter; i++)
            {
                toJSON(job, schema);
            }
        }
    }

    /*
     * Read a JSON file into a SIF Object and then produce XML.
     */
    private void testJSONToXML(String fileName, Class<?> clazz, SchemaType schema) throws Exception
    {
        Object jaxbObject = fromJSON(fileName, clazz, schema);
        if (jaxbObject != null)
        {
            toXML(jaxbObject); //revert to check if the XML looks correct
        }        
    }

    /*-----------------*/
    /*-- Main Method --*/
    /*-----------------*/
    public static void main(String[] args)
    {
        TestJAXBUtilsForInfra tester = new TestJAXBUtilsForInfra();
        System.out.println("Start Testing TestJAXBUtilsForInfra...");
        try
        {
            tester.testListEnumRepresentation();
            
            /*--
             * Infra Data Model Tests - JOB
             */
            // Test XML to PESC-JSON
//            tester.testXMLToJSONJob(INPUT_JOB_FILE_NAME_XML, JobType.class, SchemaType.pesc);

            // Test XML to Goessner-JSON
//            tester.testXMLToJSONJob(INPUT_JOB_FILE_NAME_XML, JobType.class, SchemaType.goessner);

            // Test PESC-JSON to XML
//             tester.testJSONToXML(INPUT_JOB_FILE_NAME_PESC, JobType.class, SchemaType.pesc);

            // Test Goessne-JSON to XML
//             tester.testJSONToXML(INPUT_JOB_FILE_NAME_GOESSNER, JobType.class, SchemaType.goessner);

           /*--
            * Infra Data Model Tests - Environment
            */
            // Test XML to PESC-JSON
            tester.testXMLToJSONEnv(INPUT_ENV_FILE_NAME_XML, EnvironmentType.class, SchemaType.pesc);

            // Test XML to Goessner-JSON
//            tester.testXMLToJSONEnv(INPUT_ENV_FILE_NAME_XML, EnvironmentType.class, SchemaType.goessner);

            // Test PESC-JSON to XML
//            tester.testJSONToXML(INPUT_ENV_FILE_NAME_PESC, EnvironmentType.class, SchemaType.pesc);

            // Test Goessne-JSON to XML
//            tester.testJSONToXML(INPUT_ENV_FILE_NAME_GOESSNER, EnvironmentType.class, SchemaType.goessner);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("End Testing TestJAXBUtilsForInfra.");
    }
}
