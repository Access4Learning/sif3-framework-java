/*
 * DelayedRequestReceipt.java
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

package sif3.common.model.delayed;

import java.io.Serializable;
import java.util.Date;

/**
 * This class is a POJO that holds some useful information about the response to a DELAYED request. It is up to the consumer to
 * decide what it wants to do with that receipt information. Ignore it, persist it or any other actions are all valid options.
 * Note that this POJO is not a stand-alone class but will form part of a response object that is returned to a consumer and
 * therefore there is no need to hold information that is originally stored in the response such as HTTP status, error info etc.
 * Please refer to @see sif3.common.ws.BaseResponse for more information.
 * 
 * @author Joerg Huber
 *
 */
public class DelayedRequestReceipt extends DelayedBaseReceipt implements Serializable
{
    private static final long serialVersionUID = -1965220525993041749L;

    // Date when request was issued.
    private Date requestDate = new Date();
    
    // Full URI of the request
    private String requestURI = null;
    
    /**
     * @return the requestDate
     */
    public Date getRequestDate()
    {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(Date requestDate)
    {
        this.requestDate = requestDate;
    }

    /**
     * @return the requestURI
     */
    public String getRequestURI()
    {
        return requestURI;
    }

    /**
     * @param requestURI the requestURI to set
     */
    public void setRequestURI(String requestURI)
    {
        this.requestURI = requestURI;
    }

	@Override
    public String toString()
    {
	    return "DelayedRequestReceipt [requestDate=" + requestDate
	            + ", requestURI=" + requestURI + ", toString()="
	            + super.toString() + "]";
    }
}
