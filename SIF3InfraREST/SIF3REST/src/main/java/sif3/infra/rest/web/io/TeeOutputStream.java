/*
 * TeeOutputStream.java
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

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

/**
 * 
 * @author Ben Carter
 *
 */
public class TeeOutputStream extends ServletOutputStream
{

	protected OutputStream branch;
	protected OutputStream out;

	public TeeOutputStream(OutputStream out, OutputStream branch)
	{
		this.out = out;
		this.branch = branch;
	}

	@Override
	public synchronized void write(byte[] b) throws IOException
	{
		this.out.write(b);
		this.branch.write(b);
	}

	@Override
	public synchronized void write(byte[] b, int off, int len) throws IOException
	{
		this.out.write(b, off, len);
		this.branch.write(b, off, len);
	}

	@Override
	public synchronized void write(int b) throws IOException
	{
		this.out.write(b);
		this.branch.write(b);
	}

	@Override
	public void flush() throws IOException
	{
		this.out.flush();
		this.branch.flush();
	}

	@Override
	public void close() throws IOException
	{
		try
		{
			this.out.close();
		}
		finally
		{
			this.branch.close();
		}
	}
}
