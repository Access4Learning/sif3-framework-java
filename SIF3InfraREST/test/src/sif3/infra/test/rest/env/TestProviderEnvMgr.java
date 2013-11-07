package sif3.infra.test.rest.env;

import sif3.infra.common.env.EnvironmentStore;
import sif3.infra.rest.env.ProviderEnvironmentManager;

public class TestProviderEnvMgr
{
  private ProviderEnvironmentManager envMgr = null; 
  
  public TestProviderEnvMgr()
  {
    EnvironmentStore store = EnvironmentStore.getInstance("StudentProvider");
    envMgr = ProviderEnvironmentManager.getInstance(store);
  }

  private void printEnvManagerData()
  {
    System.out.println("Provider Environment Manager Data:\n"+envMgr);
  }
  
  public static void main(String[] args)
  {
    //String envName = "devLocal";
    TestProviderEnvMgr tester = new TestProviderEnvMgr();
    
    System.out.println("Start Testing ProviderEnvironmentManager...");
    
    tester.printEnvManagerData();
    
    System.out.println("End Testing ProviderEnvironmentManager.");
  }
}
