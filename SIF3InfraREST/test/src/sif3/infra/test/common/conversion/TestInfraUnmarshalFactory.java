package sif3.infra.test.common.conversion;

import javax.ws.rs.core.MediaType;

import sif3.common.conversion.UnmarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.model.EnvironmentType;
import au.com.systemic.framework.utils.FileReaderWriter;

public class TestInfraUnmarshalFactory
{
  //private final static String INPUT_ENV_FILE_NAME = "C:/DEV/eclipseWorkspace/SIF3InfraREST/TestData/xml/input/environment_large.xml";
  private final static String INPUT_ENV_FILE_NAME = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraREST/TestData/xml/input/environment_large.xml";

  private void printEnvironment(EnvironmentType env)
  {
    if (env == null)
    {
      System.out.println("Environment Object is null");
    }
    else
    {
      System.out.println("Environment Object unmarshalled:");
      System.out.println("   ID           : " + env.getId());
      System.out.println("   Type         : " + env.getType());
      System.out.println("   Session Token: " + env.getSessionToken());
      System.out.println("   SolutionID   : " + env.getSolutionId());
      System.out.println("   Default Zone : " + env.getDefaultZone());
      System.out.println("   Auth Method  : " + env.getAuthenticationMethod());
      System.out.println("   Consumer Name: " + env.getConsumerName());
      System.out.println("   AppInfo: ");
      System.out.println("      App Key      : " + env.getApplicationInfo().getApplicationKey());
      System.out.println("      Infra Version: " + env.getApplicationInfo().getSupportedInfrastructureVersion());
      System.out.println("      Data Model   : " + env.getApplicationInfo().getDataModelNamespace());
      System.out.println("      Transport    : " + env.getApplicationInfo().getTransport());
      if (env.getApplicationInfo().getApplicationProduct() != null)
      {
        System.out.println("      App Product: ");      
        System.out.println("         Vendor Name    : " + env.getApplicationInfo().getApplicationProduct().getVendorName());      
        System.out.println("         Product Name   : " + env.getApplicationInfo().getApplicationProduct().getProductName());      
        System.out.println("         Product Version: " + env.getApplicationInfo().getApplicationProduct().getProductVersion());      
      }
    }
  }
  
  private void testFromXML()
  {
    try
    {
      UnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
      String inputEnvXML = FileReaderWriter.getFileContent(INPUT_ENV_FILE_NAME);
      System.out.println("File content:\n"+inputEnvXML);
           
      EnvironmentType env = (EnvironmentType) unmarshaller.unmarschal(inputEnvXML, EnvironmentType.class, MediaType.APPLICATION_XML_TYPE);
      printEnvironment(env);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
  
  public static void main(String[] args)
  {
    TestInfraUnmarshalFactory tester = new TestInfraUnmarshalFactory();
    
    System.out.println("Start Testing InfraUnmarshalFactory...");
    
    tester.testFromXML();
    
    System.out.println("End Testing InfraUnmarshalFactory.");
  }
}
