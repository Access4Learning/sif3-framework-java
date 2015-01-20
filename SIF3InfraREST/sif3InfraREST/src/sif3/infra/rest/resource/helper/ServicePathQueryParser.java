/*
 * ServicePathQueryParser.java
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
package sif3.infra.rest.resource.helper;

import java.util.List;

import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

import sif3.common.model.QueryCriteria;
import sif3.common.model.QueryJoinType;
import sif3.common.model.QueryOperator;
import sif3.common.model.QueryPredicate;

public class ServicePathQueryParser
{
	private QueryCriteria queryCriteria = null;
	private String servicePath = null;
	private String objectNamePlural = null;

	public ServicePathQueryParser(UriInfo uriInfo)
	{
		List<PathSegment> pathSegments = uriInfo.getPathSegments();
		// We must have more than 3 segments. shortest valid service path query : /requests/parent/key/target
		// We must have an even number of segments :  /requests{/parent/key}/target
		if (pathSegments.size() > 3 && pathSegments.size() % 2 == 0)
		{
			String prefix = "";
			this.servicePath = "";
			this.queryCriteria = new QueryCriteria(QueryJoinType.AND);
			for (int i = 1; i < pathSegments.size() - 1; i += 2)
			{
				String name = pathSegments.get(i).getPath();
				String value = pathSegments.get(i + 1).getPath();
				this.queryCriteria.addPredicate(new QueryPredicate(name, QueryOperator.EQUAL, value));
				servicePath += prefix + name + "/{}";
				prefix = "/";
			}
			
			// The last segment is the actual service to invoke! It may contain the mime type extension.
			String dmPluralAndMimeType = pathSegments.get(pathSegments.size()-1).getPath();
			int pos = dmPluralAndMimeType.lastIndexOf(".");
			this.objectNamePlural = (pos > -1) ? dmPluralAndMimeType.substring(0, pos): dmPluralAndMimeType;
			servicePath += prefix + this.objectNamePlural;
		}
	}

	public QueryCriteria getQueryCriteria()
	{
		return queryCriteria;
	}

	public String getServicePath()
	{
		return servicePath;
	}

	public String getObjectNamePlural()
	{
		return objectNamePlural;
	}

	public boolean isServicePath()
	{
		return servicePath != null && queryCriteria != null && queryCriteria.size() > 0;
	}

	@Override
    public String toString()
    {
	    return "ServicePathQueryParser [queryCriteria=" + this.queryCriteria + ", servicePath="
	            + this.servicePath + ", objectNamePlural=" + this.objectNamePlural + "]";
    }
}
