/*
 * SIF3Job.java
 * Created: 6 Feb 2018
 *
 * Copyright 2018 Systemic Pty Ltd
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
import java.util.Date;

/**
 * @author Joerg Huber
 *
 */
public class SIF3Job extends JobBase implements Serializable
{
    private static final long serialVersionUID = 6667790445851364424L;

    private String currentState = null;
    private Date created = null;
    private String timeoutPeriod = null;
    private Date lastModified = null;
    private Date expireDatetime = null;

    public SIF3Job()
    {
        super();
    }
    
    public SIF3Job(long internalID)
    {
        super(internalID);
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
    public String getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(String currentState)
    {
        this.currentState = currentState;
    }

    public String getTimeoutPeriod()
    {
        return timeoutPeriod;
    }

    public void setTimeoutPeriod(String timeoutPeriod)
    {
        this.timeoutPeriod = timeoutPeriod;
    }

    public Date getExpireDatetime()
    {
        return expireDatetime;
    }

    public void setExpireDatetime(Date expireDatetime)
    {
        this.expireDatetime = expireDatetime;
    }

    @Override
    public String toString()
    {
        return "SIF3Job [currentState=" + currentState + ", created=" + created + ", timeoutPeriod="
                + timeoutPeriod + ", lastModified=" + lastModified + ", expireDatetime="
                + expireDatetime + ", toString()=" + super.toString() + "]";
    }
}
