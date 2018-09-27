package sif3.infra.common.test.env;

import sif3.infra.common.env.mgr.DirectProviderEnvironmentManager;

public class TestProviderEnvMgr extends TestBase
{
	private DirectProviderEnvironmentManager envMgr = null;

	public TestProviderEnvMgr()
	{
	    super();
		DirectProviderEnvironmentManager.initialse("StudentProvider");
		envMgr = DirectProviderEnvironmentManager.getInstance();
	}

	private void printEnvManagerData()
	{
		System.out.println("Provider Environment Manager Data:\n" + envMgr);
	}

    private void printJobData(String jobURLName)
    {
        printInfraObjectAsXML(envMgr.getJobTemplate(jobURLName));
    }
    
    private void testJobManager()
    {
        System.out.println("Job Manager - Adapter Type: "+envMgr.getJobManager().getAdapterType());
        System.out.println("Job Manager - Job Template Info: "+envMgr.getJobManager().getJobTemplateInfo("RolloverStudents"));
    }

    public static void main(String[] args)
	{
		// String envName = "devLocal";
		TestProviderEnvMgr tester = new TestProviderEnvMgr();

		System.out.println("Start Testing DirectProviderEnvironmentManager...");

//		tester.printEnvManagerData();
//		tester.printJobData("RolloverStudents");
		tester.testJobManager();

		System.out.println("End Testing DirectProviderEnvironmentManager.");
		
		tester.shutdown();
	}
}
