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

public class QueryCriteria implements Serializable
{

	private static final long serialVersionUID = 1803812315289373807L;

	private QueryJoinType joinType;
	private List<QueryPredicate> predicates = new ArrayList<QueryPredicate>();

	public QueryCriteria()
	{
		joinType = QueryJoinType.AND;
	}

	public QueryCriteria(QueryJoinType joinType)
	{
		this.joinType = joinType;
	}

	public QueryJoinType getJoinType()
	{
		return joinType;
	}

	public void setJoinType(QueryJoinType joinType)
	{
		this.joinType = joinType;
	}

	public List<QueryPredicate> getPredicates()
	{
		return predicates;
	}

	public void setPredicates(List<QueryPredicate> predicates)
	{
		this.predicates = predicates;
	}

	public void addPredicate(QueryPredicate predicate)
	{
		this.predicates.add(predicate);
	}

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
