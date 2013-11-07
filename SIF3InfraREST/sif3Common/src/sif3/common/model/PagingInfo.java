/*
 * PagingInfo.java
 * Created: 29/09/2013
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

package sif3.common.model;

import java.io.Serializable;

/**
 * When retrieving large data sets from providers it is deemed unacceptable to return all data every time. This class is used to provide
 * 'paging' style information to providers.
 * 
 * @author Joerg Huber
 */
public class PagingInfo implements Serializable
{
    private static final long serialVersionUID = -7800932795045568406L;

    private int pageSize = 10; // The number of object per page
	private int totalObjects = 0; // The total number of objects available for the 'query': count(*)
	private int currentPageNo = 0; // The number of the current page. Starts with 0!
	
	public PagingInfo()
	{}
	
	/**
	 * Constructor
	 * 
	 * @param pageSize The number of object per page
	 * @param currentPageNo The number/index of the page to be returned. Starts with 0!
	 */
	public PagingInfo(int pageSize, int currentPageNo)
	{
		setCurrentPageNo(currentPageNo);
		setPageSize(pageSize);
	}

  /**
   * Constructor
   * 
   * @param pageSize The number of object per page
   * @param currentPageNo The number/index of the page to be returned. Starts with 0!
   * @param totalObjects The total number of objects available for the 'query': count(*)
   */
	public PagingInfo(int pageSize, int currentPageNo, int totalObjects)
	{
		setCurrentPageNo(currentPageNo);
		setPageSize(pageSize);
		setTotalObjects(totalObjects);
	}

	public PagingInfo clone()
	{
		return new PagingInfo(getPageSize(), getCurrentPageNo(), getTotalObjects());
	}
	
	public int getPageSize()
    {
    	return this.pageSize;
    }
	
	/**
	 * Sets the number of object that shall be returned in each page. Default = 10.
	 * 
	 * @param pageSize See desc.
	 */
	public void setPageSize(int pageSize)
    {
    	this.pageSize = pageSize;
    }
	
	public int getTotalObjects()
    {
    	return this.totalObjects;
    }
	
	public void setTotalObjects(int totalObjects)
    {
    	this.totalObjects = totalObjects;
    }
	
	public int getCurrentPageNo()
    {
    	return this.currentPageNo;
    }
	
	/**
	 * Set the page to be returned. Starts with 0, so to return the first page set this value to 0.
	 * 
	 * @param currentPageNo See desc. Default = 0.
	 */
	public void setCurrentPageNo(int currentPageNo)
    {
    	this.currentPageNo = currentPageNo;
    }
	
	public int getMaxPages()
	{
		if (getPageSize() <= 0)
		{
			return 0;
		}
		boolean hasReminder = (getTotalObjects() % getPageSize()) != 0;
		
		return getTotalObjects()/getPageSize() + (hasReminder ? 1 : 0);
	}
	
	@Override
    public String toString()
    {
	    return "PagingInfo [pageSize=" + this.pageSize + ", totalObjects=" + this.totalObjects
	            + ", currentPageNo=" + this.currentPageNo + "]";
    }
}
