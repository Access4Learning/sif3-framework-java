/*
 * ErrorInfo.java 
 * Created: 02/12/2015
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
package sif3.infra.rest.queue.types;

import java.io.Serializable;

/**
 * This class is a utility type to allow a consumer to package the actual ERROR data and related
 * information in one structure. This type uses the actual ERROR payload data as a String as it has been
 * received from the remote message queue. This is so that the payload is still raw without the need
 * of knowing what to unmarshal it into until it is actually send to the final consumer. This
 * way an ERROR can easily be passed between classes and processed without having the need of many
 * parameters or actual knowledge of the RESPONSE object class<br/>
 * <br/>
 * 
 * @author Joerg Huber
 *
 */
public class ErrorInfo extends DelayedBaseInfo implements Serializable
{
    private static final long serialVersionUID = 626191573052681937L;
}
