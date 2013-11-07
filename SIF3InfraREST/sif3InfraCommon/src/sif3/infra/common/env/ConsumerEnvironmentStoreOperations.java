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

package sif3.infra.common.env;

import sif3.common.utils.FileAndFolderUtils;
import sif3.infra.common.model.EnvironmentType;

/**
 * Utility class to specifically deal with operations on consumer environments and its store. All of the functions are used on the 
 * lower levels of this framework and are not expected to be exposed to higher levels where a developer will operate on.
 * 
 * @author Joerg Huber
 *
 */
public class ConsumerEnvironmentStoreOperations extends BaseEnvironmentStoreOperations
{
  /**
   * Constructor
   * 
   * @param environmentStore The environment store for which the  operations of this class will be applied.
   */
	public ConsumerEnvironmentStoreOperations(EnvironmentStore environmentStore)
	{
		super(environmentStore);
	}
	
	public boolean existInputEnvironment(String envName)
	{
		return FileAndFolderUtils.doesFileExist(getFullFileName(envName, true));
	}
	
	public boolean existOutputEnvironment(String envName)
	{
		return FileAndFolderUtils.doesFileExist(getFullFileName(envName, false));
	}

	/**
	 * 
	 * @param envName The name of the environment to load.
	 * 
	 * @return Null => Failed to load data due to some error. See error log for details.
	 */
	public EnvironmentType loadInputEnvironmentData(String envName)
	{
		return loadEnvironmentData(envName, true);
	}
	
	/**
	 * 
	 * @param envName The name of the environment to load.
	 * 
	 * @return Null => Failed to load data due to some error. See error log for details.
	 */
	public EnvironmentType loadOutputEnvironmentData(String envName)
	{
		return loadEnvironmentData(envName, false);
	}
	
	public boolean storeOutputEnvironmentData(String envName, EnvironmentType environment)
	{
		return storeEnvironmentData(getEnvironmentDirectory(envName, false), envName, environment);
	}
	
	/**
	 * This removes the environment data stored in the XML for the environment store. This operation should be used with care! A deletion of an
	 * environment means that it is no longer recoverable and a consumer/provider can no longer connect to the environment because the information
	 * relating to that environment are lost for good! A loss of an environment also means a loss of all data that relate to an environment such
	 * as SIF events, responses etc. All things that are potentially stored in queues.
	 * 
	 * @param envName
	 * 
	 * @return See Desc.
	 */
	public boolean removeOutputEnvironmentData(String envName)
	{
		return removeEnvironment(getEnvironmentDirectory(envName, false), envName);
	}
	
	/*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
	private String getFullFileName(String envName, boolean inputData)
	{
		return getFullFileName(getEnvironmentDirectory(envName, inputData), envName);
	}
	
	private String getEnvironmentDirectory(String envName, boolean inputData)
	{
		return ((inputData) ? getEnvironmentStore().getFullInputDirName() : getEnvironmentStore().getFullWorkDirName());
	}
	
	/*
	 * 
	 * @param envName The environment name for which the data shall be loaded.
	 * @param inputData TRUE=Load input XML. Used for creating an environment. 
	 *                  FALSE=load Environment XML stored in workstore area. 
	 *                        This is what the create or get environment methods will return.
	 * @return Null => Failed to load data due to some error. See error log for details.
	 */
	private EnvironmentType loadEnvironmentData(String envName, boolean inputData)
	{  
		return loadEnvironmentData(getFullFileName(envName, inputData));
	}

}
