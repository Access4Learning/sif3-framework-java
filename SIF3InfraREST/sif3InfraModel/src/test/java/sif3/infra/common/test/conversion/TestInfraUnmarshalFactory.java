package sif3.infra.common.test.conversion;

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
           
      EnvironmentType env = (EnvironmentType) unmarshaller.unmarshal(inputEnvXML, EnvironmentType.class, MediaType.APPLICATION_XML_TYPE);
      printEnvironment(env);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
  
  public void testSupportMethods()
  {
	  UnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
	  System.out.println("Get Default MediaType: "+unmarshaller.getDefault());
	  System.out.println("Supported Media Types: "+unmarshaller.getSupportedMediaTypes());
	  System.out.println("Test "+MediaType.WILDCARD_TYPE+": "+unmarshaller.isSupported(MediaType.WILDCARD_TYPE));
	  System.out.println("Test "+MediaType.APPLICATION_FORM_URLENCODED_TYPE+": "+unmarshaller.isSupported(MediaType.APPLICATION_FORM_URLENCODED_TYPE));
	  System.out.println("Test "+MediaType.APPLICATION_JSON_TYPE+": "+unmarshaller.isSupported(MediaType.APPLICATION_JSON_TYPE));
  }
  
  public static void main(String[] args)
  {
    TestInfraUnmarshalFactory tester = new TestInfraUnmarshalFactory();
    
    System.out.println("Start Testing InfraUnmarshalFactory...");
    
//    tester.testFromXML();
    tester.testSupportMethods();
    
    System.out.println("End Testing InfraUnmarshalFactory.");
  }
}
