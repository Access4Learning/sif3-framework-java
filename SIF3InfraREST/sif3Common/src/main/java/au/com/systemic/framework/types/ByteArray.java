/*
 * ByteArray.java
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
package au.com.systemic.framework.types;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * This class encapsulates the byte[] type. It provides a few handy methods relating to a byte array type.
 * 
 * @author Joerg Huber
 *
 */
public class ByteArray
{
	private byte[] bytes = null;
	
	public ByteArray(){}
	
	/**
	 * Initialises the byte array based on the given string.
	 * 
	 * @param value String to convert to byte array.
	 */
	public ByteArray(String value)
	{
		getfromString(value);
	}
	
	public ByteArray(byte[] bytes)
	{
		this.bytes = bytes;
	}

	
	/**
	 * Initialises the byte array based on the given input stream.
	 * 
	 * @param bais The byte array stream to be used.
	 * 
	 * @throws IOException If there are any issues with the ByteArrayInputStream
	 */
	public ByteArray(ByteArrayInputStream bais) throws IOException
	{
		getFromInputStream(bais);
	}
	
	public byte[] getBytes()
    {
    	return bytes;
    }

	public void setBytes(byte[] bytes)
    {
    	this.bytes = bytes;
    }

	public boolean isNull()
	{
		return bytes == null;
	}
	
	public int length()
	{
		return isNull() ? 0 : bytes.length;
	}
	
	/**
	 * Should only be used if one is sure that the byte array represents a string otherwise the behaviour is unknown.
	 */
	public String toString()
	{
		return isNull() ? null : new String(bytes);
	}
	
	/**
	 * Assigns the given string to the byte array. If the string is null then null is assigned to the byte array.
	 * 
	 * @param value String to assign to the byte array.
	 */
	public void getfromString(String value)
	{
		bytes = (value == null) ? null : value.getBytes();
	}
	
	/**
	 * Return the byte array as an output stream. If the byte array is null then the returned output stream will hold
	 * no bytes
	 * 
	 * @return See desc.
	 * 
	 * @throws IOException If there are any issues with the ByteArrayOutputStream
	 */
	public ByteArrayOutputStream getOutputStream() throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (!isNull())
		{
			baos.write(bytes);
		}
		return baos;
	}
	
	/**
	 * Reads the bytes form the given input stream into the byte array.
	 * 
	 * @param bais
	 * 
	 * @throws IOException If there are any issues with the ByteArrayInputStream
	 */
	public void getFromInputStream(ByteArrayInputStream bais) throws IOException
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[512];
		int len;
		while ((len = bais.read(buf)) > 0)
		{
			bos.write(buf, 0, len);
		}
		bytes = bos.toByteArray();
		bos.close();
	}
	
}
