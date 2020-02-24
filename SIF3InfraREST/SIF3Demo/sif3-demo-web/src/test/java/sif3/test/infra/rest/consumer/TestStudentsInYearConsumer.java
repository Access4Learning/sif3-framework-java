package sif3.test.infra.rest.consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MediaType;

import sif3.common.header.HeaderValues.QueryIntention;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.model.PagingInfo;
import sif3.common.model.PayloadMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.SchemaInfo;
import sif3.common.model.ZoneContextInfo;
import sif3.common.ws.Response;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.rest.consumer.ConsumerLoader;
import systemic.sif3.demo.rest.consumer.namedquery.StudentsInYearConsumer;

public class TestStudentsInYearConsumer
{
    private static final String CONSUMER_ID = "StudentConsumer";
//    private static final String CONSUMER_ID = "BrokeredAttTrackerConsumer";

    private static final RequestType REQUEST_TYPE = RequestType.IMMEDIATE;
//    private static final RequestType REQUEST_TYPE = RequestType.DELAYED;

    private StudentsInYearConsumer getConsumer()
    {
        return new StudentsInYearConsumer();
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
            System.out.println("Responses from attempt to execute a Named Query Service: null");               
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
                        // Check if response type is String
                        if (response.getDataObjectType() != String.class) // This is for phase response
                        {
                            System.out.println("Unexpectded Data Type retrieved in Response ("+response.getDataObjectType()+")! Cannot unmarshal");
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
                System.out.println("Responses from attempt to execute a Named Query Service: null");               
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    private void getStudentsInYear(StudentsInYearConsumer consumer)
    {
        System.out.println("Start 'Get Students In Year' ...");
        try
        {
            List<ZoneContextInfo> zoneCtxList = new ArrayList<ZoneContextInfo>();
            zoneCtxList.add(new ZoneContextInfo((SIFZone)null, (SIFContext)null)); // Default zone and context
//            zoneCtxList.add(new ZoneContextInfo(new SIFZone("auRolloverTestingZone"), null));
            
            HashMap<String, String> namedQueryParameters = new HashMap<String, String>();
            namedQueryParameters.put("Year", "Yr7");
            namedQueryParameters.put("SchoolCode", "4001");

            // Example on how a specific non XML/JSON schema info can be used.
//            PayloadMetadata payloadMetadata = new PayloadMetadata(MediaType.TEXT_PLAIN_TYPE, new SchemaInfo(SchemaInfo.MODEL_TYPE_DM, "au", "3.4.5"));
//            payloadMetadata.getSchemaInfo().setSchemaType("csv");
            PayloadMetadata payloadMetadata = new PayloadMetadata(MediaType.APPLICATION_JSON_TYPE, new SchemaInfo(SchemaInfo.MODEL_TYPE_DM, "au", "3.4.5"));
            payloadMetadata.getSchemaInfo().setSchemaType("goessner");
//            PayloadMetadata payloadMetadata = new PayloadMetadata(MediaType.APPLICATION_XML_TYPE, new SchemaInfo(SchemaInfo.MODEL_TYPE_DM, "au", "3.4.5"));
//            PayloadMetadata payloadMetadata = new PayloadMetadata(MediaType.APPLICATION_XML_TYPE, null);
            List<Response> responses = consumer.retrieveDataFromNamedQuery(namedQueryParameters, payloadMetadata, new PagingInfo(5, 1), zoneCtxList, REQUEST_TYPE, QueryIntention.ALL, null);

            // Example how a standard XML schema can be used. Since no schema info is set the framework will use the one specified in the
            // adpater's property file.
//            List<Response> responses = consumer.retrieveDataFromNamedQuery(namedQueryParameters, new PayloadMetadata(MediaType.APPLICATION_XML_TYPE), new PagingInfo(5, 1), zoneCtxList, REQUEST_TYPE, QueryIntention.ALL, null);

            System.out.println("Responses from attempt to Get Students in Year:");
            printResponses(responses);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Finished 'Get Students in Year'.");
    }
    
    
    public static void main(String[] args)
    {
        TestStudentsInYearConsumer tester = new TestStudentsInYearConsumer();
        System.out.println("Start Testing StudentsInYearConsumer...");

        if (ConsumerLoader.initialise(CONSUMER_ID))
        {
            System.out.println("Consumer loaded successfully. Environment Data:\n"+ConsumerEnvironmentManager.getInstance().getEnvironmentInfo());
        
            StudentsInYearConsumer consumer = tester.getConsumer();
//            StudentsInYearConsumer consumer2 = tester.getConsumer();

            //
            // Get Students
            //
            tester.getStudentsInYear(consumer);
//            tester.getStudentsInYear(consumer2);

            // Put this agent to a blocking wait.....
            if (true)
            {
                try
                {
                    Object semaphore = new Object();
                    synchronized (semaphore) // this will block until CTRL-C is pressed.
                    {
                        System.out.println("==================================================\nTestStudentsInYearConsumer is running (Press Ctrl-C to stop)\n==================================================");
                        semaphore.wait();
                    }
                }
                catch (Exception ex)
                {
                    System.out.println("Blocking wait in TestStudentsInYearConsumer interrupted: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

            System.out.println("Finalise Consumer (i.e. disconnect and remove environment).");
            consumer.finalise();
        }
        
        ConsumerLoader.shutdown();
        System.out.println("End Testing StudentsInYearConsumer.");
    }
}
