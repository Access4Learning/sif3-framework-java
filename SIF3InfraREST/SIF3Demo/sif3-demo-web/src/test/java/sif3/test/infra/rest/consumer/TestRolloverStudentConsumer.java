/*
 * TestRolloverStudentConsumer.java
 * Created: 26 Jul 2018
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

package sif3.test.infra.rest.consumer;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.DOMOutputter;

import au.com.systemic.framework.utils.FileReaderWriter;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.model.AttributeValue;
import sif3.common.model.CustomParameters;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.URLQueryParameter;
import sif3.common.model.ZoneContextInfo;
import sif3.common.model.job.JobCreateRequestParameter;
import sif3.common.model.job.PhaseInfo;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.common.ws.job.PhaseDataRequest;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.StateCollectionType;
import sif3.infra.common.model.StateType;
import sif3.infra.rest.consumer.ConsumerLoader;
import systemic.sif3.demo.rest.consumer.functional.RolloverStudentConsumer;

/**
 * @author Joerg Huber
 *
 */
public class TestRolloverStudentConsumer
{
    private final static String PATH = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST";
  
    private static final String BASE_PATH = "/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST/TestData/xml/input";
    private static final String CREATE_PHASE_PAYLOAD = BASE_PATH+"/CreatePhasePayload.xml";
    private static final String UPDATE_PHASE_PAYLOAD = BASE_PATH+"/CreatePhasePayload.xml";
    private static final String DELETE_PHASE_PAYLOAD = BASE_PATH+"/DeletePhasePayload.xml";
    
    private static final String JOB_ID = "9fce9406-27d5-467b-92cc-949fff41d4e4";
   private static final String CONSUMER_ID = "StudentConsumer";
//    private static final String CONSUMER_ID = "HITSStudentConsumer";
//    private static final String CONSUMER_ID = "BrokeredAttTrackerConsumer";
//  private static final String CONSUMER_ID = "QueueTestConsumer";

    
    private static final RequestType REQUEST_TYPE = RequestType.DELAYED;

    private RolloverStudentConsumer getConsumer()
    {
        return new RolloverStudentConsumer();
    }

    private String getPhasePayload(String payloadFile)
    {
        return new String(FileReaderWriter.getFileBytes(payloadFile));
    }

    private void printResponses(List<Response> responses)
    {
        if (responses != null)
        {
            for (Response response : responses)
            {
                printResponse(response);
            }
        }
        else
        {
            System.out.println("Responses from attempt to execute a Functional Service: null");               
        }       
    }
    
    private void printResponse(Response response)
    {
        try
        {
            if (response != null)
            {
                System.out.println("Response:\n"+response);
                if (response.hasError())
                {
                    System.out.println("Error for Response: "+response.getError());
                }
                else // We may have data
                {
                    if (response.getHasEntity())
                    {
                        // Check if Job or Phase response
                        if (response.getDataObjectType() == String.class) // This is for phase response
                        {
                            System.out.println("Response is a Phase Operation Response: "+response.getDataObject());
                        }
                        else if ((response.getDataObjectType() == JobType.class) || (response.getDataObjectType() == JobCollectionType.class)) // Job Response
                        {
                            InfraMarshalFactory marshaller = new InfraMarshalFactory(); 
                            System.out.println("Job Response Object Data:\n"+marshaller.marshalToXML(response.getDataObject()));
                        }
                        else if ((response.getDataObjectType() == StateType.class) || (response.getDataObjectType() == StateCollectionType.class)) // Phase State Response
                        {
                            InfraMarshalFactory marshaller = new InfraMarshalFactory(); 
                            System.out.println("Job Phase State Response Object Data:\n"+marshaller.marshalToXML(response.getDataObject()));
                        }
                        else
                        {
                            System.out.println("Data Type retrieved in Response is not expected! Cannot unmarshal");
                        }
                    }
                    else // in delayed we may have delayed receipt
                    {
                        System.out.println("No Data Returned. Response Status = "+response.getStatus()+" ("+response.getStatusMessage()+")");
                        System.out.println("Delayed Receipt: "+response.getDelayedReceipt());
                    }
                }
            }
            else
            {
                System.out.println("Responses from attempt to execute a Functional Service: null");               
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    private  org.w3c.dom.Element createInitPayload()
    {     
        // w3c usage
        DOMOutputter domOutputter = new DOMOutputter();
        try
        {
            Document document = new Document();
            document.setRootElement(new Element("payload"));
            org.w3c.dom.Document domDocument = domOutputter.output(document);
            org.w3c.dom.Element rootElement = domDocument.getDocumentElement();
            rootElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "type", "propertiesType");
            
            org.w3c.dom.Element child = domDocument.createElement("property");
            child.setAttribute("name", "contextId");
            child.setTextContent("future");
            rootElement.appendChild(child);
            
            child = domDocument.createElement("property");
            child.setAttribute("name", "initiator");
            child.setTextContent("user1");
            rootElement.appendChild(child);
                
            return rootElement;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    private void createJobs(RolloverStudentConsumer consumer)
    {
        System.out.println("Start 'Create Jobs' ...");
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
//          hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, AUTH_TOKEN);
            URLQueryParameter urlQueryParams = new URLQueryParameter();

            List<ZoneContextInfo> zoneCtxList = new ArrayList<ZoneContextInfo>();
//          zoneCtxList.add(new ZoneContextInfo(new SIFZone("auRolloverTestingZone"), null));

            ArrayList<JobCreateRequestParameter> jobInits = new ArrayList<JobCreateRequestParameter>();
            jobInits.add(new JobCreateRequestParameter("oldYearEnrolment", null));
            jobInits.add(new JobCreateRequestParameter("newYearEnrolment", null));
            jobInits.add(new JobCreateRequestParameter(null, null));
            jobInits.add(null);
            
            CustomParameters customParameters = new CustomParameters();
            customParameters.setHttpHeaderParams(hdrProps);
            customParameters.setQueryParams(urlQueryParams);
            
            List<BulkOperationResponse<CreateOperationStatus>> responses = consumer.createJobs(jobInits, zoneCtxList, REQUEST_TYPE, customParameters);

            System.out.println("Responses from attempt to Create Jobs:");
            if (responses != null)
            {
                int i = 1;
                for (BulkOperationResponse<CreateOperationStatus> response : responses)
                {
                    System.out.println("Response "+i+":\n"+response);
                    if (response.hasError())
                    {
                        System.out.println("Error for Response "+i+": "+response.getError());
                    }
                    else // We should have a status list object or a delayed response
                    {
                        System.out.println("Job Response "+i+": "+response.getOperationStatuses());
                    }
                    i++;
                }
            }
            else
            {
                System.out.println("Responses from attempt to create Jobs: null");              
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        System.out.println("Finished 'Create Jobs'.");
    }
    
    private void createJob(RolloverStudentConsumer consumer)
    {
        System.out.println("Start 'Create Job' ...");
        try
        {
            List<ZoneContextInfo> zoneCtxList = new ArrayList<ZoneContextInfo>();
//          zoneCtxList.add(new ZoneContextInfo(new SIFZone("auRolloverTestingZone"), null));
            
//            ArrayList<AttributeValue> attrValue = new ArrayList<AttributeValue>();
//            attrValue.add(new AttributeValue("old-year", "2017"));
//            attrValue.add(new AttributeValue("new-year", "2018"));
            JobCreateRequestParameter jobCreateInfo = new JobCreateRequestParameter("oldYearEnrolment", createInitPayload());
            
            List<Response> responses = consumer.createJob(jobCreateInfo, zoneCtxList);

            System.out.println("Responses from attempt to Create Job:");
            printResponses(responses);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Create Job'.");
    }

    private void getJob(RolloverStudentConsumer consumer)
    {
        System.out.println("Start 'Get Job' ...");
        try
        {
            List<ZoneContextInfo> zoneCtxList = new ArrayList<ZoneContextInfo>();
            zoneCtxList.add(new ZoneContextInfo((SIFZone)null, (SIFContext)null)); // Default zone and context
            zoneCtxList.add(new ZoneContextInfo(new SIFZone("auRolloverTestingZone"), null));
            
            List<Response> responses = consumer.retrievByJobId(JOB_ID, zoneCtxList);

            System.out.println("Responses from attempt to Get Job:");
            printResponses(responses);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Get Job'.");
    }
    
    private void getJobs(RolloverStudentConsumer consumer)
    {
        System.out.println("Start 'Get Jobs' ...");
        try
        {
            List<ZoneContextInfo> zoneCtxList = new ArrayList<ZoneContextInfo>();
//            zoneCtxList.add(new ZoneContextInfo((SIFZone)null, (SIFContext)null)); // Default zone and context
//            zoneCtxList.add(new ZoneContextInfo(new SIFZone("auRolloverTestingZone"), null));
            
            List<Response> responses = consumer.retrieveJobs(new PagingInfo(5, 1), zoneCtxList, REQUEST_TYPE, null);

            System.out.println("Responses from attempt to Get Jobs:");
            printResponses(responses);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Get Jobs'.");
    }
    
    private void removeJob(RolloverStudentConsumer consumer)
    {
        System.out.println("Start 'Remove Job' ...");
        try
        {
            List<ZoneContextInfo> zoneCtxList = new ArrayList<ZoneContextInfo>();
            List<Response> responses = consumer.deleteJob(JOB_ID, zoneCtxList);
            printResponses(responses);
       }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Remove Job'.");
    }
    
    private void removeJobs(RolloverStudentConsumer consumer)
    {
        System.out.println("Start 'Remove Jobs' ...");
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
            hdrProps.setHeaderProperty("FS_TEST", "Test Header Value");
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            urlQueryParams.setQueryParam("FSQeryParam1", "fsValue1");
            
            CustomParameters customParameters = new CustomParameters();
            customParameters.setHttpHeaderParams(hdrProps);
            customParameters.setQueryParams(urlQueryParams);
            
            List<ZoneContextInfo> zoneCtxList = new ArrayList<ZoneContextInfo>();
            
            ArrayList<String> jobIDs = new ArrayList<String>();
            jobIDs.add("98eae51c-fdec-412c-b6e9-9750223301ea");
            jobIDs.add("eeeae51c-fdec-412c-b6e9-9750223301ea");
            jobIDs.add("40dcaafb-69d4-490d-9093-e679ecbead96");
            
            List<BulkOperationResponse<OperationStatus>>  responses = consumer.deleteJobs(jobIDs, zoneCtxList, REQUEST_TYPE, customParameters);
            if (responses != null)
            {
                int i = 1;
                for (BulkOperationResponse<OperationStatus> response : responses)
                {
                    System.out.println("Response " + i + ":\n" + response);
                    i++;
                }
            }
            else
            {
                System.out.println("Responses from attempt to delete jobs: null");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Remove Jobs'.");
    }

    private void getServiceInfo(RolloverStudentConsumer consumer)
    {
        System.out.println("Start 'Get Service Info' ...");
        try
        {
            List<Response> responses = consumer.getServiceInfo(null, null);
            printResponses(responses);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Get Service Info'.");
    }
    
    private void updateJobPhaseState(RolloverStudentConsumer consumer, String phaseName, PhaseState state)
    {
        System.out.println("Start 'Update Phase State' ...");
        try
        {
            Response response = consumer.updatePhaseState(new PhaseInfo(JOB_ID, phaseName), state, null, null, null);
            printResponse(response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Update Phase State'.");        
    }
    
    private void getJobPhaseStates(RolloverStudentConsumer consumer, String phaseName)
    {
        System.out.println("Start 'Get Phase States' ...");
        try
        {
            Response response = consumer.getPhaseStates(new PhaseInfo(JOB_ID, phaseName), null, null, null);
            printResponse(response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Get Phase States'.");        
    }
    
    private void retriveFromPhase(RolloverStudentConsumer consumer, String phaseName)
    {
        System.out.println("Start 'Retrieve Data from Phase' ...");
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
            hdrProps.setHeaderProperty("FS_TEST", "Test Header Value");
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            urlQueryParams.setQueryParam("FSQeryParam1", "fsValue1");
            
            CustomParameters customParameters = new CustomParameters();
            customParameters.setHttpHeaderParams(hdrProps);
            customParameters.setQueryParams(urlQueryParams);

            Response response = consumer.retrieveDataFromPhase(new PhaseInfo(JOB_ID, phaseName), MediaType.APPLICATION_XML_TYPE, new PagingInfo(5, 2), null, null, null, REQUEST_TYPE, customParameters);
            printResponse(response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Retrieve Data from Phase'.");              
    }
    
    private void createDataInPhase(RolloverStudentConsumer consumer, String phaseName)
    {
        System.out.println("Start 'Create Data in Phase' ...");
        try
        {
            String payload = getPhasePayload(CREATE_PHASE_PAYLOAD);
            Response response = consumer.createDataInPhase(new PhaseInfo(JOB_ID, phaseName), new PhaseDataRequest(payload, MediaType.APPLICATION_XML_TYPE), MediaType.APPLICATION_XML_TYPE, false, null, null, REQUEST_TYPE, null);
            
            printResponse(response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Create Data in Phase'.");        
    }

    private void updateDataInPhase(RolloverStudentConsumer consumer, String phaseName)
    {
        System.out.println("Start 'Update Data in Phase' ...");
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
            hdrProps.setHeaderProperty("FS_TEST_UPDATE", "Test Header Value fur UPDATE PAHSE");
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            urlQueryParams.setQueryParam("FSQeryParam1", "updatePhase");
            
            CustomParameters customParameters = new CustomParameters();
            customParameters.setHttpHeaderParams(hdrProps);
            customParameters.setQueryParams(urlQueryParams);

            String payload = getPhasePayload(UPDATE_PHASE_PAYLOAD);
            Response response = consumer.updateDataInPhase(new PhaseInfo(JOB_ID, phaseName), new PhaseDataRequest(payload, MediaType.APPLICATION_XML_TYPE), MediaType.APPLICATION_XML_TYPE, null, null, REQUEST_TYPE, customParameters);
            
            printResponse(response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Update Data in Phase'.");        
    }

    private void deleteDataInPhase(RolloverStudentConsumer consumer, String phaseName)
    {
        System.out.println("Start 'Delete Data in Phase' ...");
        try
        {
            String payload = getPhasePayload(DELETE_PHASE_PAYLOAD);
            Response response = consumer.deleteDataInPhase(new PhaseInfo(JOB_ID, phaseName), new PhaseDataRequest(payload, MediaType.APPLICATION_XML_TYPE), MediaType.APPLICATION_XML_TYPE, null, null, REQUEST_TYPE, null);
            printResponse(response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Delete Data in Phase'.");        
    }
    
    public static void main(String[] args)
    {
        TestRolloverStudentConsumer tester = new TestRolloverStudentConsumer();
        System.out.println("Start Testing RolloverStudentConsumer...");

        if (ConsumerLoader.initialise(CONSUMER_ID))
        {
            System.out.println("Consumer loaded successfully. Environment Data:\n"+ConsumerEnvironmentManager.getInstance().getEnvironmentInfo());
        
            RolloverStudentConsumer consumer = tester.getConsumer();

            //
            // Job Operations
            //
            tester.createJob(consumer);
//            tester.createJobs(consumer);

            
//            tester.getJob(consumer);
//            tester.getJobs(consumer);
                
//            tester.removeJob(consumer);
//            tester.removeJobs(consumer);
//            tester.getServiceInfo(consumer);
        
            //
            // Phase State Operations
            //
//            tester.updateJobPhaseState(consumer, "newYearEnrolment", PhaseState.FAILED);
//            tester.getJobPhaseStates(consumer, "newYearEnrolment");
        
            //
            // Phase Operations
            //
//            tester.retriveFromPhase(consumer, "oldYearEnrolment");
//            tester.createDataInPhase(consumer, "oldYearEnrolment");
//            tester.updateDataInPhase(consumer, "oldYearEnrolment");
//            tester.deleteDataInPhase(consumer, "oldYearEnrolment");

            // Put this agent to a blocking wait.....
            if (true)
            {
                try
                {
                    Object semaphore = new Object();
                    synchronized (semaphore) // this will block until CTRL-C is pressed.
                    {
                        System.out.println("==================================================\nTestRolloverStudentConsumer is running (Press Ctrl-C to stop)\n==================================================");
                        semaphore.wait();
                    }
                }
                catch (Exception ex)
                {
                    System.out.println("Blocking wait in TestRolloverStudentConsumer interrupted: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

            System.out.println("Finalise Consumer (i.e. disconnect and remove environment).");
            consumer.finalise();
        }
        
        ConsumerLoader.shutdown();
        System.out.println("End Testing RolloverStudentConsumer.");
    }
}
