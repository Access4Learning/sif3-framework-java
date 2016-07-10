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
package sif3.common.persist.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import sif3.common.CommonConstants.PhaseState;

public class SIF3PhaseState implements Serializable
{
    private static final long serialVersionUID = -1570304821696181148L;
    private String            id;
    private PhaseState        type;
    private Date              created;
    private Date              lastModified;
    private String            description;

    /**
     * Basic constructor that sets logical defaults of this phase state
     */
    public SIF3PhaseState()
    {
    }

    /**
     * Constructor that takes a phase type and optional description arguments
     * 
     * @param type
     * @param description
     */
    public SIF3PhaseState(PhaseState type, String description)
    {
        this();
        setType(type);
        setDescription(description);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public PhaseState getType()
    {
        return type;
    }

    public void setType(PhaseState type)
    {
        this.type = type;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
    }

    public void setLastModified()
    {
        this.lastModified = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
