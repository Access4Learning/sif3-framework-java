/*
 * GZIPUtil.java
 *
 * Copyright 2015 Systemic Pty Ltd
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.types.ByteArray;

/**
 * This class contains a few utility methods to converts a String into a GZIP byte array and vice-versa.
 * 
 * @author Joerg Huber
 *
 */
public class GZIPUtil
{
	protected static final Logger logger = LoggerFactory.getLogger(GZIPUtil.class);

	/**
	 * This method gzip the given string and returns the gzip as a byte array. If anything fails during the process then
	 * an IOException is thrown.
	 * 
	 * @param input The string to gzip and convert to a byte array.
	 * 
	 * @return The byte array of a the gzipped input string.
	 * 
	 * @throws IOException Failure to gzip the given string.
	 */
	public static byte[] gzipStringToBytes(String input) throws IOException
	{
		return gzipBytes(input.getBytes());
	}
	
	/**
	 * As above but the returned value is a ByteArray rather than byte[]. This might be a much more resource efficient 
	 * way to pass around the ByteArray as a parameter to other methods than a native byte[]. 
	 * 
	 * @param input The string to gzip and convert to a ByteArray.
	 * 
	 * @return The ByteArray of a the gzipped input string.
	 * 
	 * @throws IOException Failure to gzip the given string.
	 */
	public static ByteArray gzipStringToByteArray(String input) throws IOException
	{
		return gzipByteArray(new ByteArray(input));
	}


	/**
	 * This method gzip the given byte[] and returns the gzip as a byte array. If anything fails during the process then
	 * an IOException is thrown.
	 * 
	 * @param input The byte[] to gzip.
	 * 
	 * @return The byte array of a the gzipped input byte[].
	 * 
	 * @throws IOException Failure to gzip the given input.
	 */
	public static byte[] gzipBytes(byte[] input) throws IOException
	{
		Timer timer = null;	
		try
		{
			if (logger.isDebugEnabled())
			{
				timer = new Timer();
				timer.start();
			}
	
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			BufferedOutputStream bufos = new BufferedOutputStream(new GZIPOutputStream(bos));
			bufos.write(input);
			bufos.close();
			byte[] retval = bos.toByteArray();
			bos.close();
			return retval;
		}
		finally
		{
			if (logger.isDebugEnabled())
			{
				timer.finish();
				logger.debug("Time taken GZIP Content: "+timer.timeTaken()+"ms");
			}
		}
	}

	/**
	 * As above but instead of dealing with byte[] the potential more resource friendly ByteArray type is used. 
	 * If anything fails during the process then an IOException is thrown.
	 * 
	 * @param input The ByteArray to gzip.
	 * 
	 * @return The ByteArray of a the gzipped input ByteArray.
	 * 
	 * @throws IOException Failure to gzip the given input.
	 */
	public static ByteArray gzipByteArray(ByteArray input) throws IOException
	{
		Timer timer = null;	
		try
		{
			if (logger.isDebugEnabled())
			{
				timer = new Timer();
				timer.start();
			}
	
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			BufferedOutputStream bufos = new BufferedOutputStream(new GZIPOutputStream(bos));
			bufos.write(input.getBytes());
			bufos.close();
			ByteArray retval = new ByteArray(bos.toByteArray());
			bos.close();
			return retval;
		}
		finally
		{
			if (logger.isDebugEnabled())
			{
				timer.finish();
				logger.debug("Time taken GZIP Content: "+timer.timeTaken()+"ms");
			}
		}		
	}

	/**
	 * This method takes a gzip byte array, unzips it then and converts it to a String. If anything fails during the
	 * process then an IOException is thrown.
	 * 
	 * @param content The byte array that represents a gzipped string.
	 * 
	 * @return The String derived from unzipping the given byte array.
	 * 
	 * @throws IOException Failure to unzip the given byte array into a String.
	 */
	public static String ungzipStringFromBytes(byte[] content) throws IOException
	{
		byte[] unzippedContent = ungzipFromBytes(content);
		return (unzippedContent == null) ? null : new String(unzippedContent);
	}
	
	public static String ungzipStringFromByteArray(ByteArray content) throws IOException
	{
		ByteArray unzippedContent = ungzipFromByteArray(content);
		return (unzippedContent == null) ? null : unzippedContent.toString();
		
	}

	/**
	 * This method takes a gzip byte array and unzips it. The unzipped content is then returned as a byte[]. 
	 * If anything fails during the process then an IOException is thrown.
	 * 
	 * @param content The byte array that represents a gzipped content.
	 * 
	 * @return The byte[] derived from unzipping the given byte array.
	 * 
	 * @throws IOException Failure to unzip the given byte array.
	 */
	public static byte[] ungzipFromBytes(byte[] content) throws IOException
	{
		Timer timer = null;	
		try
		{
			if (logger.isDebugEnabled())
			{
				timer = new Timer();
				timer.start();
			}
			ByteArrayInputStream bis = new ByteArrayInputStream(content);
			BufferedInputStream bufis = new BufferedInputStream(new GZIPInputStream(bis));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int len;
			while ((len = bufis.read(buf)) > 0)
			{
				bos.write(buf, 0, len);
			}
			bis.close();
			bufis.close();
			bos.close();
			return bos.toByteArray();
		}
		finally
		{
			if (logger.isDebugEnabled())
			{
				timer.finish();
				logger.debug("Time taken Un-GZIP Content: "+timer.timeTaken()+"ms");
			}
		}
	}
	
	/**
	 * As above but instead of dealing with byte[] the potential more resource friendly ByteArray type is used. 
	 * If anything fails during the process then an IOException is thrown.
	 * 
	 * @param content The ByteArray that represents a gzipped content.
	 * 
	 * @return The ByteArray derived from unzipping the given ByteArray.
	 * 
	 * @throws IOException Failure to unzip the given byte array.
	 */
	public static ByteArray ungzipFromByteArray(ByteArray content) throws IOException
	{
		Timer timer = null;	
		try
		{
			if (logger.isDebugEnabled())
			{
				timer = new Timer();
				timer.start();
			}
			ByteArrayInputStream bis = new ByteArrayInputStream(content.getBytes());
			BufferedInputStream bufis = new BufferedInputStream(new GZIPInputStream(bis));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int len;
			while ((len = bufis.read(buf)) > 0)
			{
				bos.write(buf, 0, len);
			}
			bis.close();
			bufis.close();
			bos.close();
			return new ByteArray(bos.toByteArray());
		}
		finally
		{
			if (logger.isDebugEnabled())
			{
				timer.finish();
				logger.debug("Time taken Un-GZIP Content: "+timer.timeTaken()+"ms");
			}
		}		
	}

}