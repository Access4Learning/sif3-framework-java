/*
 * TestJobClient.java
 * Created: 22 Feb 2018
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

package sif3.infra.rest.test.client;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import au.com.systemic.framework.utils.FileReaderWriter;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.model.AttributeValue;
import sif3.common.model.PagingInfo;
import sif3.common.model.SIFZone;
import sif3.common.model.URLQueryParameter;
import sif3.common.model.job.JobCreateRequestParameter;
import sif3.common.model.job.PhaseInfo;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.BulkOperationResponse;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.OperationStatus;
import sif3.common.ws.Response;
import sif3.common.ws.job.PhaseDataRequest;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.rest.client.JobClient;
import sif3.infra.rest.consumer.ConsumerLoader;

/**
 * @author Joerg Huber
 *
 */
public class TestJobClient
{
    // Local
    private static final String CONSUMER_ID = "StudentConsumer";
    private static final String JOB_NAME_SINGULAR = "RolloverStudent";
    private static final String JOB_NAME_PLURAL = JOB_NAME_SINGULAR + "s";
    
    private static final String BASE_PATH = "/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST/TestData/xml/input";
    private static final String CREATE_PHASE_PAYLOAD = BASE_PATH+"/CreatePhasePayload.xml";
    private static final String UPDATE_PHASE_PAYLOAD = BASE_PATH+"/CreatePhasePayload.xml";
    private static final String DELETE_PHASE_PAYLOAD = BASE_PATH+"/DeletePhasePayload.xml";
    
    private static final String JOB_ID = "74f2ebfb-9cf8-4aa2-b757-12237d6c3c02";

    // Broker
  //private static final String CONSUMER_ID = "BrokeredAttTrackerConsumer";
    
    private JobClient client = null;
    
    private String getPhasePayload(String payloadFile)
    {
        return new String(FileReaderWriter.getFileBytes(payloadFile));
    }
    
    private void initClient()
    {
        client = new JobClient(ConsumerEnvironmentManager.getInstance(), JOB_NAME_PLURAL, JOB_NAME_SINGULAR);
    }
    
    private void testGetJob()
    {
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
//          hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, AUTH_TOKEN);
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            
            Response response = client.getJob("82feecf1-2f01-40f8-8ece-b4a1d030a6ef", hdrProps, urlQueryParams, null, null);
            
            if (response.hasError())
            {
                System.out.println("Error Occured retrieving Job.");
            }
            else
            {
                System.out.println("Retrieving Job succeeded.");                
            }
            System.out.println(response);
            if (!response.hasError())
            {
                InfraMarshalFactory marshaller = new InfraMarshalFactory(); 
                System.out.println(marshaller.marshalToXML(response.getDataObject()));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    private void testGetJobs()
    {
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
//          hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, AUTH_TOKEN);
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            
            Response response = client.getJobs(new PagingInfo(5, 2), hdrProps, urlQueryParams, null, null, RequestType.IMMEDIATE);
//            Response response = client.getJobs(new PagingInfo(5, 1), hdrProps, urlQueryParams, new SIFZone("TestZone"), null, RequestType.IMMEDIATE);

            if (response.hasError())
            {
                System.out.println("Error Occured retrieving Jobx.");
            }
            else
            {
                System.out.println("Retrieving Jobs succeeded.");                
            }
            System.out.println(response);
            if (!response.hasError())
            {
                InfraMarshalFactory marshaller = new InfraMarshalFactory(); 
                System.out.println(marshaller.marshalToXML(response.getDataObject()));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    private void testGetJobPhaseStates(String phaseName)
    {
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            
            Response response = client.getPhaseStates(new PhaseInfo(JOB_ID, phaseName), hdrProps, urlQueryParams, null, null);
            
            if (response.hasError())
            {
                System.out.println("Error Occured Retrieving Job Phase States.");
            }
            else
            {
                System.out.println("Job Phase State retrieved succeessfully.");                
            }
            System.out.println(response);
            if (!response.hasError())
            {
                InfraMarshalFactory marshaller = new InfraMarshalFactory(); 
                System.out.println(marshaller.marshalToXML(response.getDataObject()));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    private void testCreateJob()
    {
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
//          hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, AUTH_TOKEN);
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            
//            ArrayList<AttributeValue> attrValue = new ArrayList<AttributeValue>();
//            attrValue.add(new AttributeValue("old-year", "2017"));
//            attrValue.add(new AttributeValue("new-year", "2018"));
            JobCreateRequestParameter jobCreateInfo = new JobCreateRequestParameter("oldYearEnrolment", null);
            Response response = client.createJob(jobCreateInfo, hdrProps, urlQueryParams, null, null);
            
            System.out.println("Job ID assigned = "+jobCreateInfo.getJobID());
            
            if (response.hasError())
            {
                System.out.println("Error Occured Job Create Request.");
            }
            else
            {
                System.out.println("Create Job succeeded.");                
            }
            System.out.println(response);
            if (!response.hasError())
            {
                InfraMarshalFactory marshaller = new InfraMarshalFactory(); 
                System.out.println(marshaller.marshalToXML(response.getDataObject()));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    private void testUpdateJobPhaseState(String phaseName, PhaseState newState)
    {
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            
            Response response = client.updatePhaseState(new PhaseInfo(JOB_ID, phaseName), newState, hdrProps, urlQueryParams, null, null);
            
            if (response.hasError())
            {
                System.out.println("Error Occured Updateing Job Phase State Request.");
            }
            else
            {
                System.out.println("Job Phase State Updated succeeded.");                
            }
            System.out.println(response);
            if (!response.hasError())
            {
                InfraMarshalFactory marshaller = new InfraMarshalFactory(); 
                System.out.println(marshaller.marshalToXML(response.getDataObject()));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    private void testCreateJobs()
    {
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
//          hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTH_TOKEN, AUTH_TOKEN);
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            
            ArrayList<JobCreateRequestParameter> jobInits = new ArrayList<JobCreateRequestParameter>();
            jobInits.add(new JobCreateRequestParameter("oldYearEnrolment", null));
            jobInits.add(new JobCreateRequestParameter("newYearEnrolment", null));
            jobInits.add(new JobCreateRequestParameter(null, null));
            jobInits.add(null);
            
            BulkOperationResponse<CreateOperationStatus> response = client.createJobs(jobInits, hdrProps, urlQueryParams, null, null, RequestType.IMMEDIATE);
            
            if (response.hasError())
            {
                System.out.println("Error Occured Job Create Request.");
            }
            else
            {
                System.out.println("Create Job succeeded.");                
            }
            System.out.println(response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    private void testRemoveJob()
    {
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            
            Response response = client.removeJob("a1b314d1-7cbe-4bce-a5d8-cb115b4f03cb", hdrProps, urlQueryParams, null, null);
            
            if (response.hasError())
            {
                System.out.println("Error Occured Job Delete Request.");
            }
            else
            {
                System.out.println("Delete Job succeeded.");                
            }
            System.out.println(response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    private void testRemoveJobs()
    {
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            ArrayList<String> jobIDs = new ArrayList<String>();
            jobIDs.add("80c6b1ca-735a-40f5-8ef0-99908add342a");
            jobIDs.add("3ddbf879-f43f-4438-843f-b224fb5ef172");
            jobIDs.add("9c26cc3a-bd99-471f-88b5-898b0cd2e4d6");
            jobIDs.add("82feecf1-2f01-40f8-8ece-b4a1d030a6ef");

            BulkOperationResponse<OperationStatus> response = client.removeJobs(jobIDs, hdrProps, urlQueryParams, null, null, RequestType.IMMEDIATE);
            
            if (response.hasError())
            {
                System.out.println("Error Occured Jobs Delete Request.");
            }
            else
            {
                System.out.println("Delete Job succeeded.");                
            }
            System.out.println(response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void testGetServiceInfo()
    {
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            
            Response response = client.getServiceInfo(null, hdrProps, urlQueryParams, null, null);
            
            if (response.hasError())
            {
                System.out.println("Error Occured retrieving Job.");
            }
            else
            {
                System.out.println("Retrieving Service Info succeeded.");                
            }
            System.out.println(response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    private void testRetriveFromPhase()
    {
        try
        {
            HeaderProperties hdrProps = new HeaderProperties();
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            
            Response response = client.retrieveDataFromPhase(new PhaseInfo(JOB_ID, "oldYearEnrolment"), MediaType.APPLICATION_XML_TYPE, new PagingInfo(5,  1), hdrProps, urlQueryParams, null, null, RequestType.IMMEDIATE);
            
            if (response.hasError())
            {
                System.out.println("Error Occured Retrieving Job Phase Data.");
            }
            else
            {
                System.out.println("Job Phase Data retrieved succeessfully.");                
            }
            System.out.println(response);
            if (!response.hasError())
            {
                System.out.println("Data Type of Response: "+response.getDataObjectType());
                System.out.println("Data Retrieved: "+response.getDataObject());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        } 
    }

    private void testCreateDataInPhase()
    {
        try
        {
            String payload = getPhasePayload(CREATE_PHASE_PAYLOAD);
            HeaderProperties hdrProps = new HeaderProperties();
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            
            Response response = client.createDataInPhase(new PhaseInfo(JOB_ID, "oldYearEnrolment"), new PhaseDataRequest(payload, MediaType.APPLICATION_XML_TYPE), MediaType.APPLICATION_XML_TYPE, true, hdrProps, urlQueryParams, null, null, RequestType.IMMEDIATE);
            
            if (response.hasError())
            {
                System.out.println("Error Occured Creating Job Phase Data.");
            }
            else
            {
                System.out.println("Job Phase Data created succeessfully.");                
            }
            System.out.println(response);
            if (!response.hasError())
            {
                System.out.println("Data Type of Response: "+response.getDataObjectType());
                System.out.println("Data Retrieved: "+response.getDataObject());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }        
    }

    private void testUpdateDataInPhase()
    {
        try
        {
            String payload = getPhasePayload(UPDATE_PHASE_PAYLOAD);
            HeaderProperties hdrProps = new HeaderProperties();
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            
            Response response = client.updateDataInPhase(new PhaseInfo(JOB_ID, "oldYearEnrolment"), new PhaseDataRequest(payload, MediaType.APPLICATION_XML_TYPE), MediaType.APPLICATION_XML_TYPE, hdrProps, urlQueryParams, null, null, RequestType.IMMEDIATE);
            
            if (response.hasError())
            {
                System.out.println("Error Occured Updating Job Phase Data.");
            }
            else
            {
                System.out.println("Job Phase Data update succeessfully.");                
            }
            System.out.println(response);
            if (!response.hasError())
            {
                System.out.println("Data Type of Response: "+response.getDataObjectType());
                System.out.println("Data Retrieved: "+response.getDataObject());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }                
    }

    private void testDeleteDataInPhase()
    {
        try
        {
            String payload = getPhasePayload(DELETE_PHASE_PAYLOAD);
            HeaderProperties hdrProps = new HeaderProperties();
            URLQueryParameter urlQueryParams = new URLQueryParameter();
            
            Response response = client.deleteDataInPhase(new PhaseInfo(JOB_ID, "oldYearEnrolment"), new PhaseDataRequest(payload, MediaType.APPLICATION_XML_TYPE), MediaType.APPLICATION_XML_TYPE, hdrProps, urlQueryParams, null, null, RequestType.IMMEDIATE);
            
            if (response.hasError())
            {
                System.out.println("Error Occured Deleting Job Phase Data.");
            }
            else
            {
                System.out.println("Job Phase Data delete succeessfully.");                
            }
            System.out.println(response);
            if (!response.hasError())
            {
                System.out.println("Data Type of Response: "+response.getDataObjectType());
                System.out.println("Data Retrieved: "+response.getDataObject());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }        
        
    }

    public static void main(String[] args)
    {
        TestJobClient tester = new TestJobClient();
        
        if (ConsumerLoader.initialise(CONSUMER_ID))
        {
            System.out.println("Start Testing JobClient...");
            tester.initClient();
        
            //
            // Job Operations
            //
//            tester.testGetJob();
//            tester.testGetJobs();
            
//            tester.testCreateJob();
//            tester.testCreateJobs();
            
            tester.testRemoveJob();
//            tester.testRemoveJobs();
//            tester.testGetServiceInfo();
            
            //
            // Phase State Operations
            //
//            tester.testUpdateJobPhaseState("newYearEnrolment", PhaseState.FAILED);
//            tester.testGetJobPhaseStates("newYearEnrolment");
            
            //
            // Phase Operations
            //
//            tester.testRetriveFromPhase();
//            tester.testCreateDataInPhase();
//            tester.testUpdateDataInPhase();
//            tester.testDeleteDataInPhase();

            System.out.println("End Testing JobClient.");
            
            ConsumerLoader.shutdown();
        }
        else
        {
            System.out.println("Failed to initialse tester!");
        }
    }
}
