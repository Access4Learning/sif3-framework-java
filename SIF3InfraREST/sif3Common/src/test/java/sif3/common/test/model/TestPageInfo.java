/*
 * TestPageInfo.java
 * Created: 03/10/2013
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

package sif3.common.test.model;

import sif3.common.CommonConstants;
import sif3.common.header.HeaderProperties;
import sif3.common.model.PagingInfo;
import sif3.common.model.URLQueryParameter;


/**
 * @author Joerg Huber
 *
 */
public class TestPageInfo
{
	public static void main(String[] args)
	{
		PagingInfo p = new PagingInfo();
		System.out.println("Total pages: "+p.getMaxPages());
		
		p.setPageSize(PagingInfo.DEFAULT_PAGE_SIZE);
		p.setTotalObjects(17);
		System.out.println("Total pages: "+p.getMaxPages());
		
		p.setTotalObjects(21);
		System.out.println("Total pages: "+p.getMaxPages());

		p.setTotalObjects(30);
		System.out.println("Total pages: "+p.getMaxPages());
		
		URLQueryParameter queryParams = new URLQueryParameter();
		//queryParams.setQueryParam(PagingInfo.PagingRequestProperty.navigationPageSize.name(), "20");
    queryParams.setQueryParam(CommonConstants.PagingRequestProperty.navigationPage.name(), "3");
		
    HeaderProperties reqHdrProps = new HeaderProperties();
    reqHdrProps.setHeaderProperty(CommonConstants.PagingRequestProperty.navigationPage.name(), "1");
    
    p = new PagingInfo(reqHdrProps, queryParams);
    p.setTotalObjects(102);
    System.out.println("Paging Info: "+p);
    
    System.out.println("Request Params: "+p.getRequestValues());
    System.out.println("Response Params: "+p.getResponseValues());
	}
}
