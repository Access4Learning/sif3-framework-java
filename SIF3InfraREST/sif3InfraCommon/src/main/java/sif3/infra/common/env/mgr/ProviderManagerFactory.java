/*
 * ProviderManagerFactory.java
 * Created: 16/03/2014
 *
 * Copyright 2014 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package sif3.infra.common.env.mgr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.infra.common.env.types.AdapterEnvironmentStore;
import sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType;
import sif3.infra.common.interfaces.EnvironmentManager;

/**
 * Within this framework there are many places where an environment manager is required. Because many of these implementations are
 * generic for consumers and providers it is essential that code of these implementations deal with interfaces rather than with
 * hard-wired concrete classes. At runtime though a concrete implemementation is required and for that this factory shall be used.
 * 
 * @author Joerg Huber
 *
 */
public class ProviderManagerFactory
{
    protected static final Logger logger = LoggerFactory.getLogger(ProviderManagerFactory.class);

	private static EnvironmentManager envMgr = null;
	
	public static synchronized EnvironmentManager initialse(String adapterFileNameWithoutExt)
	{
		if (envMgr == null)
		{
			switch (getEnvironmentType(adapterFileNameWithoutExt))
	        {
		        case DIRECT:
		        	logger.debug("Environment Provider Manager (DIRECT Environment) will be used.");
			        envMgr = DirectProviderEnvironmentManager.initialse(adapterFileNameWithoutExt);
			        break;
		        case BROKERED:
		        	logger.debug("Brokered Provider Manager (BROKERED Environment) will be used.");
			        envMgr = BrokeredProviderEnvironmentManager.initialse(adapterFileNameWithoutExt);
			        break;
			    default: //cannot really happen
			    	return null;
	        }
		}
		return envMgr;
	}
	
	public static EnvironmentManager getEnvironmentManager()
	{
		return envMgr;
	}
	
	/*---------------------*/
	/*-- Private methods --*/
	/*---------------------*/
	// I know this is not an ideal implementation but since this is only called once at startup we should only have minimal overhead with
	// that.
	private static synchronized EnvironmentType getEnvironmentType(String adapterFileNameWithoutExt)
	{
		AdapterEnvironmentStore adapterConfig = new AdapterEnvironmentStore(adapterFileNameWithoutExt);
		return adapterConfig.getEnvironment().getEnvironmentType();
	}
}
