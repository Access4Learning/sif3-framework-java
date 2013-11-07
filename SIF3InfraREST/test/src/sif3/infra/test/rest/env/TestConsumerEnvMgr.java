package sif3.infra.test.rest.env;

import sif3.infra.common.env.EnvironmentStore;
import sif3.infra.rest.env.ConsumerEnvironmentManager;


public class TestConsumerEnvMgr
{
  private ConsumerEnvironmentManager envMgr = null; 
  
  public TestConsumerEnvMgr()
  {
	  EnvironmentStore store = EnvironmentStore.getInstance("StudentConsumer");
	  envMgr = ConsumerEnvironmentManager.getInstance(store);
  }
  
  private void testConnect(String envName)
  {
    System.out.println("Start Testing to connect to environment "+envName);
    try
    {
      boolean success = envMgr.connect(envName);
      if (success)
      {
        System.out.println("Successfully connected to environment "+envName);
        System.out.println("Environment Store: "+envMgr.getConnectedEnvironments());
      }
      else
      {
        System.out.println("Failed to connect to environment "+envName+". See error log for details.");

      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    System.out.println("End Testing to connect to environment "+envName);
  }
  
  
  private void testDisconnect(String envName)
  {
	    System.out.println("Start Testing to disconnect from environment "+envName);
	    try
	    {
	      boolean success = envMgr.disconnect(envName);
	      if (success)
	      {
	        System.out.println("Successfully disconnected from environment "+envName);
	      }
	      else
	      {
	        System.out.println("Failed to disconnect from environment "+envName+". See error log for details.");

	      }
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	    System.out.println("End Testing to disconnect from environment "+envName);
	  
  }
  
  private void testDisconnectAndRemove(String envName)
  {
	    System.out.println("Start Testing to disconnect and remove environment "+envName);
	    try
	    {
	      boolean success = envMgr.disconnectAndRemove(envName);
	      if (success)
	      {
	        System.out.println("Successfully disconnected and removed environment "+envName);
	      }
	      else
	      {
	        System.out.println("Failed to disconnect and remove environment "+envName+". See error log for details.");

	      }
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	    System.out.println("End Testing to disconnect and remove environment "+envName);
	  
  }
  
  public static void main(String[] args)
  {
	  String envName = "devLocal";
    TestConsumerEnvMgr tester = new TestConsumerEnvMgr();
    
    System.out.println("Start Testing ConsumerEnvironmentManager...");
    
    tester.testConnect(envName);
    //tester.testConnect(envName);
    //tester.testDisconnect(envName);
    //tester.testDisconnectAndRemove(envName);
    
    System.out.println("End Testing ConsumerEnvironmentManager.");
  }
}
