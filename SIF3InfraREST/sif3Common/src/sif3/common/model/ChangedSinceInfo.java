/*
 * ChangedSinceInfo.java
 * Created: 8 Sep 2015
 *
 * Copyright 2015 Systemic Pty Ltd
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

package sif3.common.model;

import java.io.Serializable;
import java.util.Date;

import au.com.systemic.framework.utils.DateUtils;
import au.com.systemic.framework.utils.StringUtils;

/**
 * This class is a simple POJO for holding data related to a "Changed Since" date retrieval.
 * 
 * @author Joerg Huber
 */
public class ChangedSinceInfo implements Serializable
{
    public enum ChangeType {CREATED, UPDATED, DELETED, NOT_STATED};
    
    private static final long serialVersionUID = 8037005349278666410L;
    
    private Date changedSinceDate = new Date();
    private ChangeType changeType = ChangeType.NOT_STATED;
    
    /**
     * Default Constructor: Defaults changedSinceDate=now & changeType=NOT_STATED.
     */
    public ChangedSinceInfo()
    {
        setChangedSinceDate(new Date());
        setChangeType(ChangeType.NOT_STATED);
    }
    
    /**
     * Constructor: Defaults changeType=NOT_STATED and changedSinceDate=now if null! 
     */
    public ChangedSinceInfo(Date changedSinceDate)
    {
        setChangedSinceDate((changedSinceDate == null) ? new Date() : changedSinceDate);
        setChangeType(ChangeType.NOT_STATED);
    }
    
    /**
     * Constructor: Defaults changeType=NOT_STATED if null and changedSinceDate=now if null! 
     */
    public ChangedSinceInfo(Date changedSinceDate, ChangeType changeType)
    {
        setChangedSinceDate((changedSinceDate == null) ? new Date() : changedSinceDate);
        setChangeType((changeType == null) ? ChangeType.NOT_STATED : changeType);
    }

    /**
     * @return the changedSinceDate
     */
    public Date getChangedSinceDate()
    {
        return changedSinceDate;
    }
    
    /**
     * @param includeMilliseconds TRUE: Include milliseconds in the returned string.
     *                            FALSE: Milliseconds are ignored and not provided as part of the returned string.
     * 
     * @return the changedSinceDate as String in the ISO 8601 format. Milliseconds are included if the  without milliseconds. Null is returned if the date is null.
     */
    public String getChangedSinceDateAsISO8601(boolean includeMilliseconds)
    {
        if (changedSinceDate != null)
        {
            return (includeMilliseconds) ? DateUtils.getISO8601withSecFraction(changedSinceDate) : DateUtils.getISO8601(changedSinceDate);
        }
        else
        {
            return null;
        }
    }

    /**
     * @param changedSinceDate the changedSinceDate to set
     */
    public void setChangedSinceDate(Date changedSinceDate)
    {
        this.changedSinceDate = changedSinceDate;
    }
    
    /**
     * @param changedSinceDateAsISO8601 A ISO 8601 formatted date string with or without milliseconds. If null or empty then the date 
     *                                  will be set to null. If the date is not a valid ISO 8601 date then it will be set to null.
     */
    public void setISO8601ChangedSinceDate(String changedSinceDateAsISO8601)
    {
        if (StringUtils.notEmpty(changedSinceDateAsISO8601))
        {
            try
            {
                setChangedSinceDate(DateUtils.stringToDate(changedSinceDateAsISO8601, DateUtils.ISO_8601));
            }
            catch (Exception ex)
            {
                // Maybe there are milliseconds provided!
                try
                {
                    setChangedSinceDate(DateUtils.stringToDate(changedSinceDateAsISO8601, DateUtils.ISO_8601_SECFRACT));
                } 
                catch (Exception innerEx)
                {
                    // Still not good. Give up....
                    changedSinceDate = null;
                }
                    
            }
        }
        else
        {
            this.changedSinceDate = null;
        }
    }    

    /**
     * @return the changeType
     */
    public ChangeType getChangeType()
    {
        return changeType;
    }

    /**
     * @param changeType the changeType to set
     */
    public void setChangeType(ChangeType changeType)
    {
        this.changeType = changeType;
    }
    
    /**
     * @param changeType String format of the changed type. Case insensitive value. If an invalid value, null or empty string is 
     *                   given it will be defaulted to NOT_STATED.
     */
    public void setChangeType(String changeType)
    {
        if (StringUtils.notEmpty(changeType))
        {
            try
            {
                setChangeType(ChangeType.valueOf(changeType.trim().toUpperCase()));
            }
            catch (Exception ex)
            {
                setChangeType(ChangeType.NOT_STATED);
            }
        }
        else
        {
            setChangeType(ChangeType.NOT_STATED);
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ChangedSinceInfo [changedSinceDate=" + changedSinceDate + ", changeType=" + changeType + "]";
    }
}
