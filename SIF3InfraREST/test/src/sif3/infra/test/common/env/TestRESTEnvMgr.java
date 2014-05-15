package sif3.infra.test.common.env;



public class TestRESTEnvMgr
{
//  private ConsumerEnvironmentManager envMgr = null; 
  
  public TestRESTEnvMgr()
  {
//	  AdapterEnvironmentStore store = AdapterEnvironmentStore.getInstance("StudentConsumer");
//	  envMgr = ConsumerEnvironmentManager.getInstance(store);
  }
  
//  private void testConnect()
//  {
//    System.out.println("Start Testing to connect to environment.");
//    try
//    {
//      boolean success = envMgr.connect();
//      if (success)
//      {
//        System.out.println("Successfully connected to environment "+envMgr.getEnvironment().getEnvironmentName());
//      }
//      else
//      {
//        System.out.println("Failed to connect to environment "+envMgr.getEnvironment().getEnvironmentName()+". See error log for details.");
//
//      }
//    }
//    catch (Exception ex)
//    {
//      ex.printStackTrace();
//    }
//    System.out.println("End Testing to connect to environment.");
//  }
//  
//  
//  private void testDisconnect()
//  {
//	    System.out.println("Start Testing to disconnect from environment.");
//	    try
//	    {
//	      boolean success = envMgr.disconnect();
//	      if (success)
//	      {
//	        System.out.println("Successfully disconnected from environment "+envMgr.getEnvironment().getEnvironmentName());
//	      }
//	      else
//	      {
//	        System.out.println("Failed to disconnect from environment "+envMgr.getEnvironment().getEnvironmentName()+". See error log for details.");
//
//	      }
//	    }
//	    catch (Exception ex)
//	    {
//	      ex.printStackTrace();
//	    }
//	    System.out.println("End Testing to disconnect from environment.");
//	  
//  }
//  
//  private void testDisconnectAndRemove()
//  {
//	    System.out.println("Start Testing to disconnect and remove environment.");
//	    try
//	    {
//	      boolean success = envMgr.disconnectAndRemove();
//	      if (success)
//	      {
//	        System.out.println("Successfully disconnected and removed environment "+envMgr.getEnvironment().getEnvironmentName());
//	      }
//	      else
//	      {
//	        System.out.println("Failed to disconnect and remove environment "+envMgr.getEnvironment().getEnvironmentName()+". See error log for details.");
//
//	      }
//	    }
//	    catch (Exception ex)
//	    {
//	      ex.printStackTrace();
//	    }
//	    System.out.println("End Testing to disconnect and remove environment.");
//	  
//  }
  
  public static void main(String[] args)
  {
//	String envName = "devLocal";
//	String envName = "systemicDemo";
//    TestRESTEnvMgr tester = new TestRESTEnvMgr();
    
    System.out.println("Start Testing ConsumerEnvironmentManager...");
    
//    tester.testConnect();
//    //tester.testConnect();
//    tester.testDisconnect();
//    //tester.testDisconnectAndRemove();
    
    System.out.println("End Testing ConsumerEnvironmentManager.");
  }
}
