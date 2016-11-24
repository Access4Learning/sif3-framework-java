/*
 * EventMetadata.java
 * Created: 09/09/2014
 *
 * Copyright 2014 Systemic Pty Ltd
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

import sif3.common.header.HeaderProperties;

/**
 * @author Joerg Huber
 *
 */
public class EventMetadata extends BaseMetadata
{
    private static final long serialVersionUID = 7520826455394603343L;

    public EventMetadata(HeaderProperties httpHeaderParams)
    {
    	super(null, httpHeaderParams);
    }

	@Override
    public String toString()
    {
	    return "EventMetadata [toString()=" + super.toString() + "]";
    }
}
