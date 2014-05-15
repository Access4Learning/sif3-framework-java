/*
 * ConsumerEnvironmentStoreOperations.java
 * Created: 29/08/2013
 *
 * Copyright 2013 Systemic Pty Ltd
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

package sif3.infra.common.env.ops;

import sif3.common.CommonConstants;
import sif3.common.model.EnvironmentKey;
import sif3.common.persist.model.SIF3Session;
import sif3.common.persist.service.SIF3SessionService;
import sif3.infra.common.interfaces.ClientEnvStoreOperations;
import sif3.infra.common.model.EnvironmentType;

/**
 * Utility class to specifically deal with operations on consumer environments and its store. All of the functions are used on the 
 * lower levels of this framework and are not expected to be exposed to higher levels where a developer will operate on.
 * 
 * @author Joerg Huber
 *
 */
public class ConsumerEnvironmentStoreOperations extends AdapterBaseEnvStoreOperations implements ClientEnvStoreOperations
{
	private static final CommonConstants.AdapterType ADAPTER_TYPE = CommonConstants.AdapterType.CONSUMER;

	private SIF3SessionService service = new SIF3SessionService();

	/**
	 * Constructor
	 * 
	 * @param adapterFileNameWithoutExt The property file name for which this Store Operations shall be instantiated.
	 */
	public ConsumerEnvironmentStoreOperations(String adapterFileNameWithoutExt)
	{
		super(adapterFileNameWithoutExt);
	}
		
	/*
	 * (non-Javadoc)
	 * @see sif3.infra.common.env.ClientEnvStoreOperations#loadSession(sif3.common.model.EnvironmentKey)
	 */
	public SIF3Session loadSession(EnvironmentKey environmentKey)
	{
		return loadSession(environmentKey, ADAPTER_TYPE, service);
	}
		
	/*
	 * (non-Javadoc)
	 * 
	 * @see sif3.infra.common.env.ClientEnvStoreOperations#createOrUpdateSession(sif3.infra.common.model.EnvironmentType)
	 */
	public SIF3Session createOrUpdateSession(EnvironmentType inputEnv)
	{
		return createOrUpdateSession(inputEnv, ADAPTER_TYPE, service);
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.infra.common.env.ClientEnvStoreOperations#removeEnvFromWorkstoreBySessionToken(java.lang.String)
	 */
	public boolean removeEnvFromWorkstoreBySessionToken(String sessionToken)
	{
		return removeEnvFromWorkstoreBySessionToken(sessionToken, ADAPTER_TYPE, service);
	}
}
