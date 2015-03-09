/*
 * AuditableResource.java
 * Created: 04/03/2015
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
package sif3.infra.rest.resource.audit;

import sif3.infra.rest.resource.BaseResource;

/**
 * Holds the instance of the current resource being audited and a flag indicating if auditing is enabled. This is not the ideal solution but needs
 * to be implemented this way so that auditing is compatible with all web containers and implementations.
 * 
 * @author Ben Carter
 */
public class AuditableResource
{

	// static only.
	private AuditableResource()
	{}

	private static final ThreadLocal<BaseResource> resource = new ThreadLocal<BaseResource>();
	private static final ThreadLocal<Boolean>      auditing = new ThreadLocal<Boolean>();

	public static void setResource(BaseResource baseResource)
	{
		resource.set(baseResource);
	}

	public static BaseResource getResource()
	{
		return resource.get();
	}

	public static boolean isAuditing()
	{
		return Boolean.TRUE.equals(auditing.get());
	}

	public static void setAuditing()
	{
		auditing.set(Boolean.TRUE);
	}

	public static void remove()
	{
		auditing.remove();
		resource.remove();
	}
}
