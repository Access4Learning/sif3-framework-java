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

package sif3.infra.test.common.model;

import sif3.common.model.PagingInfo;


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
		
		p.setTotalObjects(17);
		System.out.println("Total pages: "+p.getMaxPages());
		
		p.setTotalObjects(21);
		System.out.println("Total pages: "+p.getMaxPages());

		p.setTotalObjects(30);
		System.out.println("Total pages: "+p.getMaxPages());
	}
}
