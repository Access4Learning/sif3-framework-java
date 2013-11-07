/*
 * StudentsConsumer.java
 * Created: 11/10/2013
 *
 * Copyright 2013 Systemic Pty Ltd
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

package systemic.sif3.demo.rest.consumer;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.infra.rest.consumer.AbstractConsumer;
import systemic.sif3.demo.rest.ModelObjectConstantsUS;

/**
 * @author Joerg Huber
 *
 */
public class StudentsConsumer extends AbstractConsumer
{

	/**
     * @param serviceID
     * @param marshaller
     * @param unmarshaller
     */
    public StudentsConsumer(String serviceID, MarshalFactory marshaller, UnmarshalFactory unmarshaller)
    {
	    super(serviceID, marshaller, unmarshaller);
    }

	/* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractConsumer#getSingleObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getSingleObjectClassInfo()
    {
	    return ModelObjectConstantsUS.STUDENTS;
    }

	/* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractConsumer#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
	    return ModelObjectConstantsUS.STUDENTS;
    }

	/* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractConsumer#shutdown()
     */
    @Override
    public void shutdown()
    {
    }

	/* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractConsumer#removeEnvironments()
     */
    @Override
    public boolean removeEnvironments()
    {
	    return false;
    }

}
