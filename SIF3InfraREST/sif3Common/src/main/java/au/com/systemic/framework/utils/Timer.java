/*
 * Timer.java
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

import java.util.Date;

/**
 * @author Joerg Huber
 * 
 */

public class Timer
{
	private long startTime = -1;
	private long endTime = -1;

	/**
	 * Starts the timer
	 */
	public void start()
	{
		Date startDate = new Date();
		startTime = startDate.getTime();
	}

	/**
	 * Stops the timer
	 */
	public void finish()
	{
		Date endDate = new Date();
		endTime = endDate.getTime();
	}

	/**
	 * Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT when start() was called last.
	 * 
	 * @return see description
	 */
	public long getStartTime()
	{
		return startTime;
	}

	/**
	 * Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT when finish() was called last.
	 * 
	 * @return see description
	 */
	public long getFinishTime()
	{
		return endTime;
	}

	/**
	 * Returns the number of milliseconds passed between the last call of start() and finish().
	 * 
	 * @return see desc.
	 */
	public long timeTaken()
	{
		return endTime - startTime;
	}

}
