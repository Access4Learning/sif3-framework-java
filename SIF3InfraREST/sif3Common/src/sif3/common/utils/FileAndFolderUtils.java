/*
 * FileAndFolderUtils.java
 * Created: 27/08/2013
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

package sif3.common.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

/**
 * @author Joerg Huber
 *
 */
public class FileAndFolderUtils
{
	public static boolean doesFolderExist(String folderPath)
	{
		File f = new File(folderPath);
		return f.exists() && f.isDirectory();
	}
	
	/**
	 * 
	 * @param folderPath Folder to check for existence.
	 * @param checkReadable If folder exists check if it can be read (checkReadable=true).
	 * @param checkWritable If folder exists check if it can be written to (checkWritable=true).
	 * 
	 * @return TRUE: Folder exists and has appropriate permissions. FALSE: Folder does not exist or doesn't have 
	 *         requested permissions.
	 */
	public static boolean doesFolderExist(String folderPath, boolean checkReadable, boolean checkWritable)
	{
		File f = new File(folderPath);
		if (f.exists() && f.isDirectory())
		{
			if (checkReadable)
			{
				if (!f.canRead())
				{
					return false;
				}
			}
			if (checkWritable)
			{
				if (!f.canWrite())
				{
					return false;
				}
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean doesFileExist(String fullPathAndFileName)
	{
		File f = new File(fullPathAndFileName);
		return f.exists() && f.isFile();
	}
	
	public static boolean createFolder(String folderPath, boolean alsoCreateParentDir) throws Exception
	{
		File f = new File(folderPath);
		if (!f.exists())
		{
			if (alsoCreateParentDir)
			{
				return f.mkdirs();
			}
			else
			{
				return f.mkdir(); 
			}
		}
		
		return true;
	}
	
	public static boolean removeFile(String fullPathAndFileName)
	{
		File f = new File(fullPathAndFileName);
		return f.delete();
	}
	
	public static List<String> getSubDirectoriesOf(String dirName)
	{
	  File file = new File(dirName);
	  String[] directories = file.list(new FilenameFilter() 
	  {
	    @Override
	    public boolean accept(File current, String name) 
	    {
	      return new File(current, name).isDirectory();
	    }
	  });
	  return Arrays.asList(directories);  
	}
		
}
