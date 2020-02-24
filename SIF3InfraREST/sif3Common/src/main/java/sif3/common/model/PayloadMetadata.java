/*
 * PayloadMetadata.java
 * Created: 11 Nov 2019
 *
 * Copyright 2019 Systemic Pty Ltd
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

import javax.ws.rs.core.MediaType;

/**
 * This class simply holds some information about a payload (request or response). The metadata consists of the mime type
 * as well as optional data model schema information as of SIF 3.3.
 * 
 * @author Joerg Huber
 */
public class PayloadMetadata implements Serializable
{
    private static final long serialVersionUID = 6371320607299424528L;
    
    private MediaType mimeType = null;
    private SchemaInfo schemaInfo = null;
    
    public PayloadMetadata()
    {
        this(null, null);
    }
    
    public PayloadMetadata(MediaType mimeType)
    {
        this(mimeType, null);
    }
    
    public PayloadMetadata(MediaType mimeType, SchemaInfo schemaInfo)
    {
        super();
        setMimeType(mimeType);
        setSchemaInfo(schemaInfo);
    }

    public MediaType getMimeType()
    {
        return mimeType;
    }
    
    public void setMimeType(MediaType mimeType)
    {
        this.mimeType = mimeType;
    }
    
    public SchemaInfo getSchemaInfo()
    {
        return schemaInfo;
    }
    
    public void setSchemaInfo(SchemaInfo schemaInfo)
    {
        this.schemaInfo = schemaInfo;
    }

    @Override
    public String toString()
    {
        return "PayloadMetadata [mimeType=" + mimeType + ", schemaInfo=" + schemaInfo + "]";
    }
}
