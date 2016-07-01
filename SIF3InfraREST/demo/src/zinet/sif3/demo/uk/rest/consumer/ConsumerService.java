/*
 * Crown Copyright © Department for Education (UK) 2016
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

package zinet.sif3.demo.uk.rest.consumer;

import sif3.infra.rest.consumer.ConsumerLoader;

/**
 * This is a basic Consumer Service that just starts up and if events are enabled will start
 * processing events. It must be run from the command line and will block until CTRL-C is pressed.
 * In that case it will shut down and quit.
 * 
 * The intend of this class is to illustrate how a stand-alone consumer service could be implemented
 * and started.
 */
public class ConsumerService
{

    private static void usage(String[] args)
    {
        System.out.println(
                "Usage <JAVA_HOME>/bin/java <JVM_SETTINGS> -cp <classpath> systemic.sif3.demo.uk.rest.consumer.StudentConsumerService <serviceID> <consumer.properties>");
        System.out
                .println("   <serviceID>       : Required. Must be an unique ID of this service.");
        System.out.println(
                "   <agent.properties>: Required. The name of the consumer properties file without the extension '.properties'. The directory where this file resides must be on the classpath.");

        System.out.println("\nProvided Values:");
        for (int i = 0; i < args.length; i++)
        {
            System.out.println("Argument[" + i + "]: " + args[i]);
        }
    }

    private static String getServiceIDParam(String[] args)
    {
        return args[0].trim();
    }

    private static String getPropertyFileNameParam(String[] args)
    {
        return args[1].trim();
    }

    public void stopService(String serviceID)
    {
        System.out.println("Shutting down " + serviceID);
        ConsumerLoader.shutdown();
    }

    public void startService(final String serviceID, String consumerPropFileName) throws Exception
    {
        System.out.println(serviceID + " startService() called...");
        System.out.println(serviceID + ": Installing shutdown hook");
        final ConsumerService tmpSvc = this;
        Runtime.getRuntime().addShutdownHook(new Thread()
        {

            public void run()
            {
                tmpSvc.stopService(serviceID);
            }
        });

        if (ConsumerLoader.initialise(consumerPropFileName))
        {
            System.out.println(serviceID + "initialised successfully.");
        }

        System.out.println(serviceID + " is running (Press Ctrl-C to stop)");
    }

    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            System.out.println("ERROR Starting Service.");
            usage(args);

            System.exit(0);
        }
        else
        {
            ConsumerService svc = null;
            String serviceID = getServiceIDParam(args);
            try
            {
                svc = new ConsumerService();
                svc.startService(serviceID, getPropertyFileNameParam(args));

                // Put this agent to a blocking wait.....
                try
                {
                    Object semaphore = new Object();
                    synchronized (semaphore) // this will block until CTRL-C is
                    // pressed.
                    {
                        semaphore.wait();
                    }
                }
                catch (Exception ex)
                {
                    System.out.println(
                            "Blocking wait in " + serviceID + " interrupted: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
            catch (Exception ex)
            {
                System.out.println(
                        serviceID + " could not be started. See previous log entries for details.");
                ex.printStackTrace();
            }
            finally // If startup is successful then this will never be
            // reached.
            {
                System.out.println("Exit " + serviceID + "...");
                if (svc != null)
                {
                    svc.stopService(serviceID);
                }
            }
        }
    }

}
