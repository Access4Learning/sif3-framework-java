/*
 * SchoolInfoConsumer.java
 * Created: 08/05/2014
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

package systemic.sif3.demo.rest.consumer;

import sif3.common.conversion.ModelObjectInfo;
import systemic.sif3.demo.rest.ModelObjectConstants;

/**
 * @author Joerg Huber
 *
 */
public class SchoolInfoConsumer extends AUDataModelConsumer
{	
	/**
     * @param marshaller
     * @param unmarshaller
     */
    public SchoolInfoConsumer()
    {
	    super();
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getSingleObjectClassInfo()
    {
	    return ModelObjectConstants.SCHOOL_INFO;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
	    return ModelObjectConstants.SCHOOL_INFOS;
    }

	/* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractConsumer#shutdown()
     */
    @Override
    public void shutdown()
    {
    	//Nothing to do at this stage
    }
}
