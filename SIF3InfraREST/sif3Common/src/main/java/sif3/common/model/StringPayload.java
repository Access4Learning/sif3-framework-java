/*
 * StringPayload.java
 * Created: 11 Dec 2018
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

package sif3.common.model;

import java.io.Serializable;

/**
 * This class encapsulates typical data that is passed from/to an end-point where little is known about the actual object. 
 * There are many places in the SIF Specification where any data can be passed around not just data defined in the SIF
 * Data Model. Such data can be passed in a payload for a request or a response. For these services (eg. phases of a functional
 * service or responses to a named queries) the framework must support as data model agnostic representation of a payload. 
 * This class enables data to be passed around in its raw String representation with the applicable mime type and optional
 * schema information, so that the recipient of the data knows how what it deals with. It is the responsibility of the 
 * recipient of such a "string" payload to marshal it into a known data structure using the given mime type.
 * 
 * @author Joerg Huber
 */
public class StringPayload implements Serializable
{
    private static final long serialVersionUID = 7279610736312134859L;

    private String data = null;
//    private MediaType mimeType = null;
    private PayloadMetadata payloadMetadata = null;
    
    public StringPayload()
    {
        this(null, null);
    }
    
    public StringPayload(String data, PayloadMetadata payloadMetadata)
    {
        super();
        this.data = data;
        this.payloadMetadata = payloadMetadata;
    }

    public String getData()
    {
        return data;
    }
    
    public void setData(String data)
    {
        this.data = data;
    }
    
    public PayloadMetadata getPayloadMetadata()
    {
        return payloadMetadata;
    }

    public void setPayloadMetadata(PayloadMetadata payloadMetadata)
    {
        this.payloadMetadata = payloadMetadata;
    }

//    public MediaType getMimeType()
//    {
//        return mimeType;
//    }
//    
//    public void setMimeType(MediaType mimeType)
//    {
//        this.mimeType = mimeType;
//    }
    
    @Override
    public String toString()
    {
        return "StringPayload [data=" + data + ", payloadMetadata=" + payloadMetadata + "]";
    }
}
