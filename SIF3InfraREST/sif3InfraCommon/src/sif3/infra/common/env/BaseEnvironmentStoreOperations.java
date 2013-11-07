/*
 * BaseEnvironmentStoreOperations.java
 * Created: 17/09/2013
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

import org.apache.log4j.Logger;

import sif3.common.exception.UnmarshalException;
import sif3.common.utils.FileAndFolderUtils;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;
import sif3.infra.common.model.EnvironmentType;
import au.com.systemic.framework.utils.FileReaderWriter;

/**
 * Helper class for some operations required by the Environment Store (Consumer or Provider). Only used internal to the framework.
 * Since the current version (October 2013) of the environment store is file based most of the operations in this class are file
 * operations (store, delete, read etc). Also the environment information is stored as XML.
 * 
 * @author Joerg Huber
 *
 */
public class BaseEnvironmentStoreOperations
{
	protected final Logger logger = Logger.getLogger(getClass());
	
	private EnvironmentStore environmentStore = null;

	/**
	 * Constructor
	 * 
	 * @param environmentStore The environment store for which the provided operations are being performed.
	 */
	public BaseEnvironmentStoreOperations(EnvironmentStore environmentStore)
	{
		this.environmentStore = environmentStore;
	}
	
	public EnvironmentStore getEnvironmentStore()
    {
    	return this.environmentStore;
    }
	
	protected String getXMLFromFile(String fileName)
	{
		return FileReaderWriter.getFileContent(fileName);
	}

	/**
	 * @param fullName Full name of file (path, name and extension. It is expected this file to have XML content.
	 * 
	 * @return Null => Failed to load data due to some error. See error log for details.
	 */
	protected EnvironmentType loadEnvironmentData(String fullName)
	{
		if (FileAndFolderUtils.doesFileExist(fullName))
		{
			String xmlStr = getXMLFromFile(fullName);
			if (xmlStr != null)
			{
				InfraUnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
				try
				{
					return (EnvironmentType) unmarshaller.unmarshalFromXML(xmlStr, EnvironmentType.class);
				}
				catch (UnmarshalException ex)
				{
					logger.error("Failed to successfully parse the XML content from "+fullName);
					return null;
				}
			}
			else
			{
				logger.error("The XML file "+fullName+" cannot be read. Check permissions and content.");				
				return null;
			}
		}
		else
		{
			logger.error("There is no XML file: "+fullName);
			return null;
		}
	}
	
	protected boolean storeEnvironmentData(String dirName, String envName, EnvironmentType environment)
	{
		if (!FileAndFolderUtils.doesFolderExist(dirName,true, true))
		{
			try
			{
				if (!FileAndFolderUtils.createFolder(dirName, true))
				{
					logger.error("Failed to create directory "+dirName+". No further info available. Could not store Environment "+envName);
					return false;				
				}
			}
			catch (Exception ex)
			{
				logger.error("Failed to create directory "+dirName+". Could not store Environment "+envName+": "+ex.getMessage());
				return false;
			}
		}

		String fileName = getFullFileName(dirName, envName);
		try
		{
			InfraMarshalFactory marshaller = new InfraMarshalFactory();
			String envStr = marshaller.marshalToXML(environment);

			FileReaderWriter.createFile(fileName, envStr.getBytes());
			return true;
		}
		catch (Exception ex)
		{
			logger.error("Environment "+envName+" could not be stored to location "+fileName+": "+ex.getMessage());
			return false;
		}
	}

	protected boolean removeEnvironment(String dirName, String envName)
	{
		String fullFileName = getFullFileName(dirName, envName);
		if (FileAndFolderUtils.doesFileExist(fullFileName))
		{
			if (!FileAndFolderUtils.removeFile(fullFileName))
			{
				logger.error("Failed to remove environment file: "+fullFileName);
				return false;
			}
			else
			{
				logger.info("Environment "+envName+" removed. File "+fullFileName+" deleted successfully.");
			}
		}
		else
		{
			logger.debug("File "+fullFileName+" does not exist. No action taken.");
		}
		return true;
	}
	
	
	protected String getFullFileName(String path, String envName)
	{
		return path+envName+".xml";
	}	
}
