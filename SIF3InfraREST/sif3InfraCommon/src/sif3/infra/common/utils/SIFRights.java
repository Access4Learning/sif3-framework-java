/*
 * Crown Copyright © Department for Education (UK) 2016
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package sif3.infra.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.RightType;
import sif3.infra.common.model.RightValueType;
import sif3.infra.common.model.RightsType;

/**
 * A class to manage creating a collection of SIFRights
 */
public class SIFRights
{
    private Map<AccessRight, AccessType> rights        = new HashMap<AccessRight, AccessType>();
    private ObjectFactory                objectFactory = new ObjectFactory();

    /**
     * Constructor
     */
    public SIFRights()
    {
        for (AccessRight right : AccessRight.values())
        {
            rights.put(right, AccessType.REJECTED);
        }
    }

    /**
     * Get the rights contained in this object as a list
     * 
     * @return A list of SIF rights.
     */
    public List<RightType> getRights()
    {
        List<RightType> list = new ArrayList<RightType>();
        for (AccessRight right : rights.keySet())
        {
            RightType r = objectFactory.createRightType();
            r.setType(right.name());
            r.setValue(RightValueType.valueOf(rights.get(right).name()));
            list.add(r);
        }
        return list;
    }

    /**
     * Set the rights in this object from a RightsType POJO instance
     * 
     * @param rights
     *            A RightsType instance - a POJO encapsulating a collection of rights
     * @return The instance this method executed on, allowing for a chained programming style.
     * @see SIFRights#setRights(List)
     */
    public SIFRights setRights(RightsType rights)
    {
        if (rights == null)
        {
            return this;
        }
        return setRights(rights.getRight());
    }

    /**
     * Set the rights in this object from a list of RightType instances
     * 
     * @param rightsList
     *            The list of rights to build this object's rights from
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public SIFRights setRights(List<RightType> rightsList)
    {
        if (rightsList != null)
        {
            for (RightType r : rightsList)
            {
                rights.put(AccessRight.valueOf(r.getType()),
                        AccessType.valueOf(r.getValue().name()));
            }
        }
        return this;
    }

    /**
     * Clears the current set of rights
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public SIFRights clear()
    {
        rights.clear();
        return this;
    }

    /**
     * Gets the {@link AccessType} for the given {@link AccessRight}
     * 
     * @param right
     *            The access right to look for
     * @return The type of access permitted, or null if it is not defined
     */
    public AccessType getRight(AccessRight right)
    {
        return rights.get(right);
    }

    /**
     * Check if the given right type and value are found in this {@link SIFRights} object, useful
     * for checking ACLs for example.
     * 
     * @param right
     *            The type of right to look for (e.g. CREATE)
     * @param type
     *            The type access granted (e.g. REJECTED)
     * @return true if the right is supported (found), false otherwise.
     */
    public boolean hasRight(AccessRight right, AccessType type)
    {
        return getRight(right).equals(type);
    }

    /**
     * Add ADMIN right as SUPPORTED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public SIFRights admin()
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
    public SIFRights admin(AccessType type)
    {
        rights.put(AccessRight.ADMIN, type);
        return this;
    }

    /**
     * Add CREATE right as APPROVED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public SIFRights create()
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
    public SIFRights create(AccessType type)
    {
        rights.put(AccessRight.CREATE, type);
        return this;
    }

    /**
     * Add DELETE right as APPROVED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public SIFRights delete()
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
    public SIFRights delete(AccessType type)
    {
        rights.put(AccessRight.DELETE, type);
        return this;
    }

    /**
     * Add PROVIDE right as APPROVED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public SIFRights provide()
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
    public SIFRights provide(AccessType type)
    {
        rights.put(AccessRight.PROVIDE, type);
        return this;
    }

    /**
     * Add QUERY right as APPROVED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public SIFRights query()
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
    public SIFRights query(AccessType type)
    {
        rights.put(AccessRight.QUERY, type);
        return this;
    }

    /**
     * Add SUBSCRIBE right as SUPPORTED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public SIFRights subscribe()
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
    public SIFRights subscribe(AccessType type)
    {
        rights.put(AccessRight.SUBSCRIBE, type);
        return this;
    }

    /**
     * Add UPDATE right as APPROVED
     * 
     * @return The instance this method executed on, allowing for a chained programming style.
     */
    public SIFRights update()
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
    public SIFRights update(AccessType type)
    {
        rights.put(AccessRight.UPDATE, type);
        return this;
    }
}
