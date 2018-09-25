/*
 * PhaseData.java
 * Created: 19 Jun 2018
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

package sif3.common.ws.job;

import java.io.Serializable;

import javax.ws.rs.core.MediaType;

/**
 * This class encapsulates typical data that is passed from/to a phase of a functional service. Almost all phase operations deal
 * with some data, either in the request, the response or both. The framework is data model agnostic and therefore doesn't
 * know what payloads are passed to or from a phase. The data is passed around in its raw String representation with the applicable
 * mime type, so that the recipient of the data knows how what it deals with.
 * 
 * @author Joerg Huber
 *
 */
public class PhaseData implements Serializable
{
    private static final long serialVersionUID = -4749828404357889220L;

    private String data = null;
    private MediaType mimeType = null;
    
    public PhaseData()
    {
        this(null, null);
    }
    
    public PhaseData(String data, MediaType mimeType)
    {
        super();
        this.data = data;
        this.mimeType = mimeType;
    }

    public String getData()
    {
        return data;
    }
    
    public void setData(String data)
    {
        this.data = data;
    }
    
    public MediaType getMimeType()
    {
        return mimeType;
    }
    
    public void setMimeType(MediaType mimeType)
    {
        this.mimeType = mimeType;
    }
    
    @Override
    public String toString()
    {
        return "PhaseData [data=" + data + ", mimeType=" + mimeType + "]";
    }
}
