/*
 * QueueReaderInfo.java
 * Created: 28 Aug 2018
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

package sif3.infra.rest.queue;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * This class is a simple POJO that holds the link between an ExecutorService and the list of "threads" that are managed.
 * The "thread" list is a list of objects (runnable) that are managed with the Executor Service. The intend of this linkage
 * is to allow to call specific methods of the "runnable" class at certain times, most commonly at shutdown to ensure
 * threads are properly recycled and/or shut down. Within this framework there are a couple of places where this type of
 * service is required, most notably for the Remote and Local Message Queue Readers. They need to be shutdown properly with
 * a specific shutdown() method to ensure 'blocking waits' are resolved.
 *  
 * @author Joerg Huber
 */
public class QueueReaderInfo<T> implements Serializable
{
    private static final long serialVersionUID = 4075125778176009331L;
    
    private ExecutorService service = null;
    private List<T> linkedClasses = null;
    
    public QueueReaderInfo()
    {
        this(null, null);
    }

    public QueueReaderInfo(ExecutorService service)
    {
        this(service, null);
    }
    
    public QueueReaderInfo(ExecutorService service, List<T> linkedClasses)
    {
        super();
        setService(service);
        setLinkedClasses(linkedClasses);
    }
    
    public ExecutorService getService()
    {
        return service;
    }
    
    public void setService(ExecutorService service)
    {
        this.service = service;
    }
    
    public List<T> getLinkedClasses()
    {
        return linkedClasses;
    }

    public void setLinkedClasses(List<T> linkedClasses)
    {
        this.linkedClasses = linkedClasses;
    }
}
