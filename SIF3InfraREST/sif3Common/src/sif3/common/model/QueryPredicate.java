/*
 * QueryPredicate.java
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

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a query predicate which is used when evaluating predicates.
 * 
 * @author Ben Carter
 */
public class QueryPredicate implements Serializable
{
	private static final long    serialVersionUID = 5515817219198796201L;

	private static final Pattern predicateParser  = Pattern.compile("^([^\\s]*)\\s([^\\s]*)\\s(.*)$");

	private String               subject;
	private QueryOperator        operator;
	private String               value;

	/**
	 * Constructor
	 * 
	 * @param subject the left hand side of a query predicate
	 * @param operator the type of comparison to make when evaluating this predicate
	 * @param value the right hand side of a query predicate
	 */
	public QueryPredicate(String subject, QueryOperator operator, String value)
	{
		this.subject = subject;
		this.operator = operator;
		this.value = value;
	}

	/**
	 * 
	 * @return the left hand side of a query predicate
	 */
	public String getSubject()
	{
		return subject;
	}

	/**
	 * 
	 * @param subject the left hand side of a query predicate
	 */
	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	/**
	 * 
	 * @return the type of comparison to make when evaluating this predicate
	 */
	public QueryOperator getOperator()
	{
		return operator;
	}

	/**
	 * 
	 * @param operator the type of comparison to make when evaluating this predicate
	 */
	public void setOperator(QueryOperator operator)
	{
		this.operator = operator;
	}

	/**
	 * 
	 * @return the right hand side of a query predicate
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * 
	 * @param value the right hand side of a query predicate
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * A convenience method to parse a string into a query predicate. value must be of the format "subject operator value" 
	 * eg. name = john
	 * 
	 * @param value the string to parse.
	 */
	public static QueryPredicate parse(String value)
	{
		Matcher matcher = predicateParser.matcher(value);
		if (matcher.matches())
		{
			return new QueryPredicate(matcher.group(1), QueryOperator.fromSign(matcher.group(2)), matcher.group(3));
		}
		else
		{
			return null;
		}
	}

	public String toString()
	{
		String operator = this.operator == null ? "null" : this.operator.getSign();
		return subject + " " + operator + " " + value;
	}
}
