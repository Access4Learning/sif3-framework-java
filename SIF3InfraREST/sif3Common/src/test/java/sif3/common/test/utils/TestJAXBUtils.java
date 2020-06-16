/*
 * TestJAXBUtils.java
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

package sif3.common.test.utils;

import javax.xml.bind.JAXBElement;

import au.com.systemic.framework.utils.FileReaderWriter;
import sif.dd.au30.conversion.DataModelMarshalFactory;
import sif.dd.au30.model.ObjectFactory;
import sif.dd.au30.model.StaffAssignmentType;
import sif.dd.au30.model.StudentPersonalType;
import sif3.common.CommonConstants.SchemaType;
import sif3.common.conversion.MarshalFactory;
import sif3.common.exception.MarshalException;
import sif3.common.utils.JAXBUtils;

/**
 * @author Joerg Huber
 *
 */
public class TestJAXBUtils
{
    private final static String XML_BASE_DIR = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST/TestData/xml/input/";

    // AU Data Model sample files
    private final static String INPUT_STUDENT_FILE_NAME_XML         = XML_BASE_DIR + "StudentPersonal.xml";
    private final static String INPUT_STUDENT_FILE_NAME_PESC        = XML_BASE_DIR + "StudentPersonal-PESC.json";
    private final static String INPUT_STUDENT_FILE_NAME_GOESSNER    = XML_BASE_DIR + "StudentPersonal.json";

    private final static String INPUT_STAFF_ASSIGN_FILE_NAME_XML    = XML_BASE_DIR + "StaffAssignment.xml";
    private final static String INPUT_STAFF_ASSIGN_FILE_NAME_PESC   = XML_BASE_DIR + "StaffAssignment-PESC.json";

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
        MarshalFactory marshaller = new DataModelMarshalFactory();
        
        String xmlStr = marshaller.marshalToXML(jaxbObject);
        System.out.println(jaxbObject.getClass().getSimpleName()+" String:\n"+xmlStr);
    }   

    /*---------------------------------------*/
    /*-- Configure section for Marshalling --*/
    /*---------------------------------------*/
    private void toJSON(JAXBElement<?> jaxbObject, SchemaType schema) throws MarshalException
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
    private void testXMLToJSONStudentPersonal(String fileName, Class<?> clazz, SchemaType schema) throws Exception
    {
        Object object = loadFileContentFromXML(fileName, clazz);
        
        if (object != null)
        {
            // package the read object into a JAXBElement as required - Assume StudentPersonal
            ObjectFactory of = new ObjectFactory();
            JAXBElement<StudentPersonalType> student = of.createStudentPersonal((StudentPersonalType)object);
            
            int maxIter = 1;
            for (int i=0; i<maxIter; i++)
            {
                toJSON(student, schema);
            }
        }
    }
    
    /*
     * Read an XML file into a SIF Object and then produce JSON according to PESC.
     */
    private void testXMLToJSONStaffAssignment(String fileName, Class<?> clazz, SchemaType schema) throws Exception
    {
        Object object = loadFileContentFromXML(fileName, clazz);
        
        if (object != null)
        {
            // package the read object into a JAXBElement as required - Assume StudentPersonal
            ObjectFactory of = new ObjectFactory();
            JAXBElement<StaffAssignmentType> staff = of.createStaffAssignment((StaffAssignmentType)object);
            
            int maxIter = 1;
            for (int i=0; i<maxIter; i++)
            {
                toJSON(staff, schema);
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
        TestJAXBUtils tester = new TestJAXBUtils();
        System.out.println("Start Testing JAXBUtils...");
        try
        {
            /*--
             * AU Data Model Tests
             */
            // Test XML to PESC-JSON
//            tester.testXMLToJSONStudentPersonal(INPUT_STUDENT_FILE_NAME_XML, StudentPersonalType.class, SchemaType.pesc);
            tester.testXMLToJSONStaffAssignment(INPUT_STAFF_ASSIGN_FILE_NAME_XML, StaffAssignmentType.class, SchemaType.pesc);

            
            // Test XML to Goessner-JSON
//            tester.testXMLToJSONStudentPersonal(INPUT_STUDENT_FILE_NAME_XML, StudentPersonalType.class, SchemaType.goessner);

            // Test PESC-JSON to XML 
//            tester.testJSONToXML(INPUT_STUDENT_FILE_NAME_PESC, StudentPersonalType.class, SchemaType.pesc); 
            tester.testJSONToXML(INPUT_STAFF_ASSIGN_FILE_NAME_PESC, StaffAssignmentType.class, SchemaType.pesc); 
            
            // Test Goessne-JSON to XML 
//            tester.testJSONToXML(INPUT_STUDENT_FILE_NAME_GOESSNER, StudentPersonalType.class, SchemaType.goessner); 

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("End Testing JAXBUtils.");
    }
}
