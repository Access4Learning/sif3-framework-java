/*
 * ServiceRights.java
 * Created: 30/12/2013
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

package sif3.common.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Joerg Huber
 *
 */
public class ServiceRights implements Serializable
{
    private static final long serialVersionUID = 2972634204650446881L;

	public enum AccessType {APPROVED, SUPPORTED, REJECTED, REQUESTED, UNSUPPORTED };
	
	public enum AccessRight {QUERY, CREATE, UPDATE, DELETE, PROVIDE, SUBSCRIBE, ADMIN};
	
	private HashMap<AccessRight, AccessType> rights = new HashMap<AccessRight, AccessType>();

	public HashMap<AccessRight, AccessType> getRights()
    {
    	return this.rights;
    }

	public ServiceRights setRights(HashMap<AccessRight, AccessType> rights)
    {
    	this.rights = rights;
    	return this;
    }
	
	public ServiceRights addRight(AccessRight right, AccessType accessType)
	{
		rights.put(right, accessType);
		return this;
	}
	
	public boolean hasRight(AccessRight right, AccessType accessType)
	{
		AccessType accessExists = rights.get(right);
		if (accessExists != null)
		{
			return accessExists == accessType;
		}
		else
		{
			return false;
		}
	}
	
	public AccessType getAccessType(AccessRight right)
	{
		return rights.get(right);
	}
	
	/**
     * Clears the current set of rights
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights clear()
    {
        rights.clear();
        return this;
    }

    /**
     * Add ADMIN right as SUPPORTED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights admin()
    {
        this.admin(AccessType.APPROVED);
        return this;
    }

    /**
     * Add ADMIN right
     * 
     * @param type
     *            The type of right to add, e.g. APPROVED or SUPPORTED
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights admin(AccessType type)
    {
        rights.put(AccessRight.ADMIN, type);
        return this;
    }

    /**
     * Add CREATE right as APPROVED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights create()
    {
        this.create(AccessType.APPROVED);
        return this;
    }

    /**
     * Add CREATE right
     * 
     * @param type
     *            The type of right to add, e.g. APPROVED or SUPPORTED
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights create(AccessType type)
    {
        rights.put(AccessRight.CREATE, type);
        return this;
    }

    /**
     * Add DELETE right as APPROVED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights delete()
    {
        this.delete(AccessType.APPROVED);
        return this;
    }

    /**
     * Add DELETE right
     * 
     * @param type
     *            The type of right to add, e.g. APPROVED or SUPPORTED
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights delete(AccessType type)
    {
        rights.put(AccessRight.DELETE, type);
        return this;
    }

    /**
     * Add PROVIDE right as APPROVED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights provide()
    {
        this.provide(AccessType.APPROVED);
        return this;
    }

    /**
     * Add PROVIDE right
     * 
     * @param type
     *            The type of right to add, e.g. APPROVED or SUPPORTED
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights provide(AccessType type)
    {
        rights.put(AccessRight.PROVIDE, type);
        return this;
    }

    /**
     * Add QUERY right as APPROVED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights query()
    {
        this.query(AccessType.APPROVED);
        return this;
    }

    /**
     * Add QUERY right
     * 
     * @param type
     *            The type of right to add, e.g. APPROVED or SUPPORTED
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights query(AccessType type)
    {
        rights.put(AccessRight.QUERY, type);
        return this;
    }

    /**
     * Add SUBSCRIBE right as SUPPORTED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights subscribe()
    {
        this.subscribe(AccessType.SUPPORTED);
        return this;
    }

    /**
     * Add SUBSCRIBE right
     * 
     * @param type
     *            The type of right to add, e.g. APPROVED or SUPPORTED
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights subscribe(AccessType type)
    {
        rights.put(AccessRight.SUBSCRIBE, type);
        return this;
    }

    /**
     * Add UPDATE right as APPROVED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights update()
    {
        this.update(AccessType.APPROVED);
        return this;
    }

    /**
     * Add UPDATE right
     * 
     * @param type
     *            The type of right to add, e.g. APPROVED or SUPPORTED
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public ServiceRights update(AccessType type)
    {
        rights.put(AccessRight.UPDATE, type);
        return this;
    }
    
	@Override
    public String toString()
    {
	    return "ServiceRights [rights=" + this.rights + "]";
    }
	
	
}
