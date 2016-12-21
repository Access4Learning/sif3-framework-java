/*
 * ServicePathPredicate.java
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

/**
 * This class represents a special version of a Query Predicate for Service Path Predicates.
 * The operator is always EQUAL
 *
 * @author Ben Carter
 */
public class ServicePathPredicate extends QueryPredicate 
{
	private static final long serialVersionUID = -5647502092983681930L;

	/**
	 * Constructor represents the following service path subject/value
	 * 
	 * @param subject the left hand side of a service path couple
	 * @param value the right hand side of a service path couple
	 */
	public ServicePathPredicate(String subject, String value)
	{
		super(subject, QueryOperator.EQUAL, value);
	}
}
