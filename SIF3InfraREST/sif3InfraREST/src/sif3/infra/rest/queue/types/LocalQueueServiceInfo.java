/*
 * LocalQueueServiceInfo.java
 * Created: 10 Nov 2015
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

import sif3.common.model.ServiceInfo;
import sif3.infra.rest.queue.LocalConsumerQueue;

/**
 * This POJO links a service with its local push queue. It is intended to be used with events and delayed responses
 * where a remote queue needs to know which service links to what local consumer queue.
 * 
 * @author Joerg Huber
 *
 */
public class LocalQueueServiceInfo implements Serializable
{
    private static final long serialVersionUID = -8993986277755188610L;
    
    private ServiceInfo service = null;
    private LocalConsumerQueue localQueue = null;
    
    public LocalQueueServiceInfo(ServiceInfo service, LocalConsumerQueue localQueue)
    {
        super();
        setService(service);
        setLocalQueue(localQueue);
    }
    
    /**
     * @return the service
     */
    public ServiceInfo getService()
    {
        return service;
    }
    
    /**
     * @param service the service to set
     */
    private void setService(ServiceInfo service)
    {
        this.service = service;
    }
    
    /**
     * @return the localQueue
     */
    public LocalConsumerQueue getLocalQueue()
    {
        return localQueue;
    }
    /**
     * @param localQueue the localQueue to set
     */
    private void setLocalQueue(LocalConsumerQueue localQueue)
    {
        this.localQueue = localQueue;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "LocalQueueServiceInfo [service=" + service + ", localQueue=" + localQueue + "]";
    }    
}
