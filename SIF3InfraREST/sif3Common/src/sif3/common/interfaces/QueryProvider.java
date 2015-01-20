/*
 * QueryProvider.java
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
package sif3.common.interfaces;

import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;

/**
 * This interface is an extension to the Provider interface to also allow object queries.
 */
public interface QueryProvider extends Provider
{

	/**
	 * This method is used to retrieve any number of objects. Filtered by the service path. Each
	 * predicate within the criteria holds a parent object and it's key.
	 * 
	 * @param queryCriteria
	 * @param zone
	 * @param context
	 * @param pagingInfo
	 * @param metadata
	 * @return
	 * @throws PersistenceException
	 * @throws UnsupportedQueryException
	 */
	public Object retrieveByServicePath(QueryCriteria queryCriteria, 
			                            SIFZone zone, 
			                            SIFContext context, 
			                            PagingInfo pagingInfo, 
			                            RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException;

}
