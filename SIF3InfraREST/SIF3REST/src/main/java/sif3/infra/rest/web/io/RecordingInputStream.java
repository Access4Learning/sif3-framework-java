/*
 * RecordingInputStream.java
 * Created: 04/03/2015
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
package sif3.infra.rest.web.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.ReadListener;

/**
 * 
 * @author Ben Carter
 *
 */
public class RecordingInputStream extends ServletInputStream
{

	protected InputStream         inputStream           = null;
	private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	public RecordingInputStream(InputStream inputStream)
	{
		this.inputStream = inputStream;
	}

	@Override
	public int available() throws IOException
	{
		return inputStream.available();
	}

	@Override
	public void close() throws IOException
	{
		inputStream.close();
	}

	@Override
	public synchronized void mark(int readlimit)
	{
		inputStream.mark(readlimit);
	}

	@Override
	public boolean markSupported()
	{
		return inputStream.markSupported();
	}

	@Override
	public int read() throws IOException
	{
		int result = inputStream.read();
		byteArrayOutputStream.write(result);
		return result;
	}

	@Override
	public synchronized void reset() throws IOException
	{
		inputStream.reset();
	}

	public String getContent()
	{
		return new String(byteArrayOutputStream.toByteArray());
	}
	
    // @Override
    public boolean isFinished()
    {
        return false;
    }

    // @Override
    public boolean isReady()
    {
        return false;
    }

    // @Override
    public void setReadListener(ReadListener arg0)
    {
        throw new IllegalStateException();
    }
}
