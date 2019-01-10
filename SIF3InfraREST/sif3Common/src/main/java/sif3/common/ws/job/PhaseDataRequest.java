/*
 * PhaseDataRequest.java
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

import javax.ws.rs.core.MediaType;

import sif3.common.model.StringPayload;

/**
 * @author Joerg Huber
 *
 */
public class PhaseDataRequest extends StringPayload
{
    private static final long serialVersionUID = -1760425027860602060L;
    
    public PhaseDataRequest()
    {
        this(null, null);
    }
    
    public PhaseDataRequest(String data, MediaType mimeType)
    {
        super(data, mimeType);
    }

    @Override
    public String toString()
    {
        return "PhaseDataRequest [toString()=" + super.toString() + "]";
    }
}
