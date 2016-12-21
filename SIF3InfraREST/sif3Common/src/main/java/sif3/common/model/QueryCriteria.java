/*
 * QueryCriteria.java
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
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a query where all predicates can only be joined by one
 * join method. It is intended to be used for both service path queries and URL
 * parameter queries.
 * 
 * @author Ben Carter
 */
public class QueryCriteria implements Serializable
{

	private static final long    serialVersionUID = 1803812315289373807L;

	private QueryJoinType        joinType;
	private List<QueryPredicate> predicates;

	/**
	 * Default Constructor
	 * 
	 * joinType defaults to AND predicates defaults to an Empty List.
	 */
	public QueryCriteria()
	{
	    setJoinType(QueryJoinType.AND);
		setPredicates(new ArrayList<QueryPredicate>());
	}
	
	/**
	 * This constructor takes a String that represents a service path and converts it into a query criteria. The service path string
	 * must have the following syntax (EBNF):<br/><br/>
	 * 
	 * ServicePath := ObjectName/ObjectID{/ObjectName/ObjectUUID}/ReturnObjectName<br/>
	 * ObjectName := SIF Object Name (Plural Form)<br/>
	 * ObjectID := UUID or RefID<br/>
	 * ReturnObjectName := SIF Object Name (Plural Form)<br/><br/>
	 * 
	 * Examples:<br/>
	 * StudentPersonals/24ed508e-1ed0-4bba-8219-8233efa55859/SchoolInfos/1a47dae9-579b-4aa5-8048-608b06c611cb/DailyAttendances<br/>
	 * SchoolInfos/1a47dae9-579b-4aa5-8048-608b06c611cb/StudentPersonals<br/>
	 * 
	 * @param servicePath The service path to be converted into a query condition.
	 */
	public QueryCriteria(String servicePath)
	{
	    if (servicePath != null)
	    {
	        // Just in case the given servicePath starts with a "/". Remove it
	        if (servicePath.startsWith("/"))
	        {
	            servicePath = servicePath.substring(1);
	        }

	        String[] segments = servicePath.split("/");
	        if (segments.length > 2)
	        {
	            // That looks like a proper Service Path....
	            setJoinType(QueryJoinType.AND);
	            setPredicates(new ArrayList<QueryPredicate>());
	            for (int i = 0; i < segments.length - 2; i += 2)
	            {
	                addPredicate(new QueryPredicate(segments[i], QueryOperator.EQUAL, segments[i+1]));
	            }
	        }
	    }
	}

	/**
	 * Constructor
	 * 
	 * @param joinType the type of join to be applied to all predicates
	 */
	public QueryCriteria(QueryJoinType joinType)
	{
		this.joinType = joinType;
		this.predicates = new ArrayList<QueryPredicate>();
	}

	/**
	 * 
	 * @return the joinType which should be applied to all predicates
	 */
	public QueryJoinType getJoinType()
	{
		return joinType;
	}

	/**
	 * 
	 * @param joinType the type of join to be applied to all predicates
	 */
	public void setJoinType(QueryJoinType joinType)
	{
		this.joinType = joinType;
	}

	/**
	 * 
	 * @return the list of predicates to be used to perform the query
	 */
	public List<QueryPredicate> getPredicates()
	{
		return predicates;
	}

	/**
	 * 
	 * @param predicates the list of predicates to be used to perform the query
	 */
	public void setPredicates(List<QueryPredicate> predicates)
	{
		this.predicates = predicates;
	}

	/**
	 * Convenience method to add a predicate to the list of predicates
	 * 
	 * @param predicate a predicate to be used to perform the query
	 */
	public void addPredicate(QueryPredicate predicate)
	{
		if (this.predicates == null)
		{
			this.predicates = new ArrayList<QueryPredicate>();
		}
		this.predicates.add(predicate);
	}

	/**
	 * 
	 * @return how many predicates are in this query.
	 */
	public int size()
	{
		return predicates == null ? 0 : predicates.size();
	}

	public String toString()
	{
		String result = "";
		String prefix = "";
		for (int i = 0; predicates != null && i < predicates.size(); i++)
		{
			QueryPredicate predicate = predicates.get(i);
			result += prefix + predicate.toString();
			prefix = " " + joinType.getName() + " ";
		}
		return result;
	}
}
