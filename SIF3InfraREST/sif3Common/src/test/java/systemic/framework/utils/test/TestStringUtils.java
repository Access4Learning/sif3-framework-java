package systemic.framework.utils.test;

import au.com.systemic.framework.utils.FileReaderWriter;
import au.com.systemic.framework.utils.StringUtils;

public class TestStringUtils
{
  private static final String FULL_FILE_NAME = "C:/Development/GitHubRepositories/SIF3InfraRest/SIF3InfraRest/TestData/inputData/events.xml";
  
  
  private static String loadFileContent()
  {
    return FileReaderWriter.getFileContent(FULL_FILE_NAME);
  }
  
  private static String[] splitEvents()
  {
    String fileContent = loadFileContent();
    return StringUtils.splitter(fileContent, "###", false);
  }
  
  
  public static void main(String[] args)
  {
    System.out.println("Test StringUtils.splitter:");
    String[] events = splitEvents();
    
    if (events == null)
    {
      System.out.println("No Data: events == null");
    }
    else
    {
      for (int i=0; i<events.length; i++)
      {
        System.out.println("Event Data "+(i+1)+":\n"+events[i].trim());
      }
    }
  }
}
