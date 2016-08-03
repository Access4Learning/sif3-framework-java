package sif3.infra.test.rest.env;

import sif3.common.persist.common.HibernateUtil;
import sif3.infra.common.env.mgr.ConsumerEnvironmentManager;
import sif3.infra.rest.env.connectors.ConsumerEnvironmentConnector;

public class TestConsumerConnector
{
    private static final String          CONSUMER_PROP_FILE_NAME = "StudentConsumer";
    // private static final String CONSUMER_PROP_FILE_NAME = "BrokeredAttTrackerConsumer";
    // private static final String CONSUMER_PROP_FILE_NAME = "QueueTestConsumer";
    // private static final String CONSUMER_PROP_FILE_NAME = "SecureStudentConsumer";

    private ConsumerEnvironmentConnector connector               = null;
    private ConsumerEnvironmentManager   envMgr                  = null;

    public TestConsumerConnector()
    {
        HibernateUtil.initialise(null);
        envMgr = ConsumerEnvironmentManager.initialse(CONSUMER_PROP_FILE_NAME);
        connector = new ConsumerEnvironmentConnector(envMgr);
    }

    private void testConnect()
    {
        System.out.println("Start Testing to connect to environment.");
        try
        {
            boolean success = connector.connect();
            if (success)
            {
                System.out.println("Successfully connected to environment " + envMgr.getEnvironmentInfo().getEnvironmentName());
            }
            else
            {
                System.out.println("Failed to connect to environment " + envMgr.getEnvironmentInfo().getEnvironmentName() + ". See error log for details.");

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("End Testing to connect to environment.");
    }

    private void testDisconnect()
    {
        System.out.println("Start Testing to disconnect from environment.");
        try
        {
            boolean success = connector.disconnect(envMgr.getEnvironmentInfo().getRemoveEnvOnShutdown());
            if (success)
            {
                System.out.println("Successfully disconnected from environment " + envMgr.getEnvironmentInfo().getEnvironmentName());
            }
            else
            {
                System.out.println("Failed to disconnect from environment " + envMgr.getEnvironmentInfo().getEnvironmentName() + ". See error log for details.");

            }
            HibernateUtil.shutdown();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("End Testing to disconnect from environment.");

    }

    public static void main(String[] args)
    {
        TestConsumerConnector tester = new TestConsumerConnector();

        System.out.println("Start Testing TestConsumerConnector...");

        tester.testConnect();
        tester.testDisconnect();

        System.out.println("End Testing TestConsumerConnector.");
    }
}
