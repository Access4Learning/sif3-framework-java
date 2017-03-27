package systemic.framework.utils.test;

import au.com.systemic.framework.utils.PropertyManager;


public class TestPropertyManager
{
  
  private PropertyManager getManager()
  {
    return PropertyManager.getInstance();
  }
  
  public static void main(String[] args)
  {

    TestPropertyManager tester = new TestPropertyManager();
    try
    {
      PropertyManager manager = tester.getManager();
      manager.loadPropertyFile("c3p0");
      manager.loadPropertyFile("env");
//      manager.loadPropertyFile("c3p0");
//      manager.loadPropertyFile("dummy");
      
      System.out.println("c3p0.autoCommitOnClose from c3p0: "+manager.getPropertyAsBool("c3p0", "c3p0.autoCommitOnClose", true));
      System.out.println("c3p0.autoCommitOnClose from env : "+manager.getPropertyAsBool("env", "c3p0.autoCommitOnClose", true));
      System.out.println("c3p0.maxIdleTime from c3p0: "+manager.getPropertyAsInt("c3p0", "c3p0.maxIdleTime"));
      System.out.println("eventID.dir from env : "+manager.getPropertyAsString("env", "eventID.dir", null));
      System.out.println("eventID.dir from env : "+manager.getPropertyAsInt("env", "eventID.dir", -5));
      System.out.println("env.fileList from env : "+manager.getFromCommaSeparated("env", "env.fileList"));
      
      
      
      System.out.println("PropertyManager: "+manager);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    System.out.println("End Testing PropertyManager.");
  }
}
