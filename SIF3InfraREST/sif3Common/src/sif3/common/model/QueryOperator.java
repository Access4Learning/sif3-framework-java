/*
 * QueryOperator.java
 * Created: 05/01/2015
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
package sif3.common.model;

import java.util.HashMap;

/**
 * This class represents an operator to be used when building a query predicate
 *
 * @author Ben Carter
 */
public enum QueryOperator
{
	EQUAL("=");

	private static final HashMap<String, QueryOperator> SIGN_MAP = new HashMap<String, QueryOperator>();

	private String sign = null;

	static
	{
		for (QueryOperator qo : QueryOperator.values())
		{
			SIGN_MAP.put(qo.getSign(), qo);
		}
	}


	public String getSign()
	{
		return sign;
	}

	/**
	 * Lookup method to return the operator from the sign used to represent it.
	 */
	public static QueryOperator fromSign(String sign) throws IllegalArgumentException
	{
		QueryOperator result = SIGN_MAP.get(sign);
		if (result == null)
		{
			throw new IllegalArgumentException("No QueryOperator with sign : " + sign);
		}
		return result;
	}

	/*---------------------*/
	/*-- Private Methods --*/
	/*---------------------*/
	private QueryOperator(String sign)
	{
		this.sign = sign;
	}


}
