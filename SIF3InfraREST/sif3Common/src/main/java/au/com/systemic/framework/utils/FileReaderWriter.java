/*
 * FileReaderWriter.java
 *
 * Copyright 2003-2014 Systemic Pty Ltd
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
package au.com.systemic.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author Joerg Huber
 */
public class FileReaderWriter
{
  /**
   * This method opens the file given by its name and full path (ie: C:/temp/testFile.xml), reads its
   * content, closes the file again and returns the content as String. It is therefore assumed that the
   * file content is ASCII text otherwise the behaviour of this method is not defined. If the file 
   * doesn't exist (ie. file name wrong or path not there) then null is returned.
   * 
   * @param fileNameInclFullPath File name with fully qualified path.
   * 
   * @return See description.
   */
  public static String getFileContent(String fileNameInclFullPath)
  {
    try
    {
      return new String(getFileBytes(new File(fileNameInclFullPath)));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return null;
    }
  }
  
  /**
   * This method opens the file given by its name and full path (ie: C:/temp/testFile.xml), reads its
   * content using the encoding given by the charsetName (i.e. UTF-8), closes the file again and returns 
   * the content as String. It is therefore assumed that the file content is ASCII text otherwise the behaviour 
   * of this method is not defined. If the file doesn't exist (ie. file name wrong or path not there) then null 
   * is returned. 
   * 
   * @param fileNameInclFullPath File name with fully qualified path.
   * @param charsetName The name of a supported charset. The behaviour of this method is unspecified when the given
   *                    file contains bytes that are not valid in the given charset. If this parameter is null then no
   *                    encoding is applied.
   *                    
   * @return See description.
   */
  public static String getFileContent(String fileNameInclFullPath, String charsetName)
  {
    try
    {
        if (StringUtils.isEmpty(charsetName))
        {
            return new String(getFileBytes(new File(fileNameInclFullPath)));
        }
        else
        {
            return new String(getFileBytes(new File(fileNameInclFullPath)), charsetName);
        }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return null;
    }
  }
  
  /**
   * This method writes the content to the file given by its name and fully qualified path 
   * (ie. C:/temp/testFile.xml). It is assumed that the path exists and that the file if it exists
   *  is closed otherwise false is returned. If the file doesn't exist then it will be created otherwise
   *  it will be overwritten (ie. not appended). If the file content is null then no action is taken.
   *  
   * @param content Content to write into the file.
   * @param fileNameInclFullPath Fully qualified path of the file to write to. If file doesn't exist it
   *                             is created, otherwise it is overwritten.
   *                             
   * @return TRUE : All succeeded.
   *         FALSE: Something failed.
   */
  public static boolean writeContentToFile(String content, String fileNameInclFullPath)
  {
    try
    {
      if (content != null)
      {
        File file = createFile(fileNameInclFullPath, content.getBytes());
        return file != null;
      }
      return true;
    }
    catch (Exception ex)
    {
      return false;
    }
  }
  
  /**
   * Returns a byte[]read from the given filename
   * @param fileNameInclFullPath  full path to file to read
   * @return  byte array containing file bytes. If there are any errors during processing then null is
   *          returned.
   */
  public static byte[] getFileBytes(String fileNameInclFullPath)
  {
    try
    {
      return getFileBytes(new File(fileNameInclFullPath));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return null;
    }    
  }
  
  /**
   * Returns a byte[] read from the given file
   * @param file  file to read
   * @return byte array containing file bytes. If there are any errors during processing then an
   *         exception is thrown.
   */
  public static byte[] getFileBytes(File file) throws Exception
  {
     byte[] buffer = null;
     FileInputStream fis = null;
     try
     {
        fis = new FileInputStream(file);
        buffer = new byte[fis.available()];

        fis.read(buffer);
        fis.close();
     }
     catch (Exception ex)
     {
        try
        {
           fis.close();
        }
        catch (Exception innerex) {}

        throw ex;
     }
     return buffer;
  }
  
  /**
   * Creates a new file instance with the given path and filename and saves the given content to the
   * file.
   * 
   * @param fileNameInclFullPath Name of file to create. Can include path.
   * @param content   byte array containing the content to save in the file
   * 
   * @return  new file instance if successfully created and the given content was saved.
   */
  public static File createFile(String fileNameInclFullPath, byte[] content) throws Exception
  {
    if (null == fileNameInclFullPath || fileNameInclFullPath.equals(""))
    {
      throw new NullPointerException("Cannot create new file object. Filename is null!");
    }
    if (content == null)
    {
      throw new NullPointerException("File content is null!!");
    }

    FileOutputStream fos = null;
    File newFile = null;
    try
    {
      newFile = new File(fileNameInclFullPath);
      fos = new FileOutputStream(newFile);
      fos.write(content);
      fos.flush();
      fos.close();
    }
    finally
    {
      if (fos != null)
      {
        fos.close();
      }
    }

    return newFile;
  }
}
