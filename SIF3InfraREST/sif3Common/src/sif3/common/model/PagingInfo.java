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
import java.util.HashMap;
import java.util.Map;

import sif3.common.CommonConstants.PagingRequestProperty;
import sif3.common.CommonConstants.PagingResponseProperty;
import sif3.common.header.HeaderProperties;
import au.com.systemic.framework.utils.StringUtils;

/**
 * When retrieving large data sets from providers it is deemed unacceptable to return all data every time. This class is used to provide
 * 'paging' style information to providers.
 * 
 * @author Joerg Huber
 */
public class PagingInfo implements Serializable
{
    private static final long serialVersionUID = -7800932795045568406L;

    /* Some default values. */
    public static int DEFAULT_PAGE_SIZE = 10;
    public static int NOT_DEFINED = -1;

    private int pageSize = NOT_DEFINED; // The number of object per page: Response & Request
    private int totalObjects = NOT_DEFINED; // The total number of objects available for the 'query': count(*). This is only part of a response!
    private int currentPageNo = NOT_DEFINED; // The number of the current page. Starts with 0(!): Response & Request
    private String navigationId = null; // Used for paged requests where consumer wants re-use prvious query results: Response & Request
	
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
	
  /**
   * Constructor
   * 
   * @param pageSize The number of object per page
   * @param currentPageNo The number/index of the page to be returned. Starts with 0!
   * @param totalObjects The total number of objects available for the 'query': count(*)
   * @param navigationId The navogation ID as returned by a provider in the HTTP response. 
   */
  public PagingInfo(int pageSize, int currentPageNo, int totalObjects, String navigationId)
  {
    setCurrentPageNo(currentPageNo);
    setPageSize(pageSize);
    setTotalObjects(totalObjects);
    setNavigationId(navigationId);
  }
	
	/**
	 * This method specifically deals with the "dual" nature that some values can be set either through HTTP header fields (standard) or through
	 * URL query parameters (Simple SIF). It also ensures that the following rule is adhered to:<br/>
	 * - If the HTTP header is set it takes precedence over URL query parameter (i.e. URL query parameter is ignored if HTTP header is present).<br/>
	 * - If a specific value is not available through a HTTP header then the URL query parameter is used.<br/>
	 * - If neither HTTP Header nor URL query parameter is present then it is either set to null or a default value specified in the SIF Spec.<br/><br/>
	 * 
	 * For paging info, the values listed in the enumeration on top of this class can be used in the HTTP request header or the URL query parameters.
	 * These are also the values this constructur will attempt to extract from either parameter according to the rules listed above.   
	 *     
	 * @param requestHdrProps HTTP Header Values
	 * @param queryParams URL Query Parameters.
	 */
	public PagingInfo(HeaderProperties requestHdrProps, URLQueryParameter queryParams)
	{
	  // To ensure that HTTP Header parameters override URL Query Parameters we use URL parameters first and the override with HTTP header fields
	  if (queryParams != null)
	  {
  	  for (PagingRequestProperty property : PagingRequestProperty.values())
  	  {
  	    switch (property)
  	    {
  	      case navigationPageSize:
            String navigationPageSizeStr = queryParams.getQueryParam(PagingRequestProperty.navigationPageSize.name());
            if (StringUtils.notEmpty(navigationPageSizeStr)) // it is set
            {
              setPageSize(toInt(navigationPageSizeStr, DEFAULT_PAGE_SIZE));
            }
  	        break;
          case navigationPage:
            String navigationPageStr = queryParams.getQueryParam(PagingRequestProperty.navigationPage.name());
            if (StringUtils.notEmpty(navigationPageStr)) // it is set 
            {
              setCurrentPageNo(toInt(queryParams.getQueryParam(PagingRequestProperty.navigationPage.name()), 0));
            }
            break;
          case navigationId:
            setNavigationId(queryParams.getQueryParam(PagingRequestProperty.navigationId.name()));
            break;
  	    }
  	  }
	  }
	  
	  // Now check the HTTP header
	  if (requestHdrProps != null)
	  {
      for (PagingRequestProperty property : PagingRequestProperty.values())
      {
        switch (property)
        {
          case navigationPageSize:
            String navigationPageSizeStr = requestHdrProps.getHeaderProperty(PagingRequestProperty.navigationPageSize.name());
            if (StringUtils.notEmpty(navigationPageSizeStr)) // it is set => override
            {
              setPageSize(toInt(navigationPageSizeStr, DEFAULT_PAGE_SIZE));
            }
            break;
          case navigationPage:
            String navigationPageStr = requestHdrProps.getHeaderProperty(PagingRequestProperty.navigationPage.name());
            if (StringUtils.notEmpty(navigationPageStr)) // it is set => override
            {
              setCurrentPageNo(toInt(navigationPageStr, 0));
            }
            break;
          case navigationId:
            String navigationIdStr = requestHdrProps.getHeaderProperty(PagingRequestProperty.navigationId.name());
            if (StringUtils.notEmpty(navigationIdStr)) // it is set => override
            {
              setNavigationId(navigationIdStr);
            }
            break;
        }
      }	    
	  }
	  
	  // If page size is given but not which page then we assume page 0
	  if ((getPageSize() > 0) && (getCurrentPageNo() <= NOT_DEFINED))
	  {
	    setCurrentPageNo(0);
	  }
	}

	public PagingInfo clone()
	{
		return new PagingInfo(getPageSize(), getCurrentPageNo(), getTotalObjects(), getNavigationId());
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
	
  public String getNavigationId()
  {
    return navigationId;
  }

  public void setNavigationId(String navigationId)
  {
    this.navigationId = navigationId;
  }

  /**
   * This method returns the total number of pages based on the page size and the total numer objects. If either if these two values is negative
   * then it indicates that they are not defined and NOT_DEFINED (-1) is returned. If both of these values are given then the appropriate max
   * number of pages value is returned.
   * 
   * @return
   */
	public int getMaxPages()
	{
		if (getPageSize() <= NOT_DEFINED)
		{
			return NOT_DEFINED;
		}
		
		if (getTotalObjects() <= NOT_DEFINED)
		{
		  return NOT_DEFINED;
		}
		
		boolean hasReminder = (getTotalObjects() % getPageSize()) != 0;
		
		return getTotalObjects()/getPageSize() + (hasReminder ? 1 : 0);
	}
	
	public Map<String, String> getRequestValues()
	{
    Map<String, String> params = new HashMap<String, String>();
    for (PagingRequestProperty property : PagingRequestProperty.values())
    {
      switch (property)
      {
        case navigationPageSize:
          if (getPageSize() > NOT_DEFINED)
          {
            params.put(PagingRequestProperty.navigationPageSize.name(), String.valueOf(getPageSize()));
          }
          break;
        case navigationPage:
          if (getCurrentPageNo() > NOT_DEFINED)
          {
            params.put(PagingRequestProperty.navigationPage.name(), String.valueOf(getCurrentPageNo()));
          }
          break;
        case navigationId:
          if (StringUtils.notEmpty(getNavigationId()))
          {
            params.put(PagingRequestProperty.navigationId.name(), getNavigationId());
          }
          break;
      }
    }
    return params;
	}
	
  public Map<String, String> getResponseValues()
  {
    Map<String, String> params = new HashMap<String, String>();
    for (PagingResponseProperty property : PagingResponseProperty.values())
    {
      switch (property)
      {
        case navigationPageSize:
          if (getPageSize() > NOT_DEFINED)
          {
            params.put(PagingResponseProperty.navigationPageSize.name(), String.valueOf(getPageSize()));
          }
          break;
        case navigationPage:
          if (getCurrentPageNo() > NOT_DEFINED)
          {
            params.put(PagingResponseProperty.navigationPage.name(), String.valueOf(getCurrentPageNo()));
          }
          break;
        case navigationId:
          if (StringUtils.notEmpty(getNavigationId()))
          {
            params.put(PagingResponseProperty.navigationId.name(), getNavigationId());
          }
          break;
        case navigationCount:
          if (getTotalObjects() > NOT_DEFINED)
          {
            params.put(PagingResponseProperty.navigationCount.name(), String.valueOf(getTotalObjects()));
          }
          break;
        case navigationLastPage:
          if (getMaxPages() > NOT_DEFINED)
          {
            params.put(PagingResponseProperty.navigationLastPage.name(), String.valueOf(getMaxPages()));
          }
          break;
      }
    }
    return params;
  }
  	
	
	@Override
  public String toString()
  {
    return "PagingInfo [currentPageNo=" + currentPageNo + ", navigationId="
        + navigationId + ", pageSize=" + pageSize + ", totalObjects="
        + totalObjects + ", getMaxPages()=" + getMaxPages() + "]";
  }
	
	 /*---------------------*/
  /*-- Private methods --*/
  /*---------------------*/
  private Integer toInteger(String value)
  {
    try
    {
      return Integer.valueOf(value);
    }
    catch (Exception ex)
    {
      return null;
    }
  }

  private int toInt(String value, int defaultValue)
  {
    Integer intValue = toInteger(value);
    return (intValue == null) ? defaultValue : intValue;
  }
}
