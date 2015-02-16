package sif3.infra.test.common.env;

import sif3.infra.common.env.mgr.DirectProviderEnvironmentManager;

public class TestProviderEnvMgr
{
	private DirectProviderEnvironmentManager envMgr = null;

	public TestProviderEnvMgr()
	{
		DirectProviderEnvironmentManager.initialse("StudentProvider");
		envMgr = DirectProviderEnvironmentManager.getInstance();
	}

	private void printEnvManagerData()
	{
		System.out.println("Provider Environment Manager Data:\n" + envMgr);
	}

	public static void main(String[] args)
	{
		// String envName = "devLocal";
		TestProviderEnvMgr tester = new TestProviderEnvMgr();

		System.out.println("Start Testing DirectProviderEnvironmentManager...");

		tester.printEnvManagerData();

		System.out.println("End Testing DirectProviderEnvironmentManager.");
	}
}
