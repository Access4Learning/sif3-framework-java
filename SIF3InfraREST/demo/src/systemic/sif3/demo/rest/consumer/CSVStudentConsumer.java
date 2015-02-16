/*
 * CSVStudentConsumer.java
 * Created: 27/01/2015
 *
 * Copyright 2015 Systemic Pty Ltd
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

import javax.ws.rs.core.MediaType;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;
import sif3.infra.rest.consumer.AbstractConsumer;
import systemic.sif3.demo.rest.conversion.CSVMarshaller;
import systemic.sif3.demo.rest.conversion.CSVUnmarshaller;

/**
 * @author Joerg Huber
 *
 */
public class CSVStudentConsumer extends AbstractConsumer
{

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMarshaller()
     */
    @Override
    public MarshalFactory getMarshaller()
    {
	    return new CSVMarshaller();
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getUnmarshaller()
     */
    @Override
    public UnmarshalFactory getUnmarshaller()
    {
    	return new CSVUnmarshaller();
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getSingleObjectClassInfo()
    {
	    return new ModelObjectInfo("CSVStudent", String.class);
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
	    return new ModelObjectInfo("CSVStudents", String.class);
    }

	/* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractConsumer#shutdown()
     */
    @Override
    public void shutdown()
    {}
    
    //---------------------------//
    //-- Override Media Types! --//
    //---------------------------//
    @Override
    public MediaType getRequestMediaType()
	{
		return getMarshaller().getDefault(); // Marshaling when sending -> request
//    	return MediaType.APPLICATION_JSON_TYPE;
	}
	
    @Override
	public MediaType getResponseMediaType()
	{
		return getUnmarshaller().getDefault(); // Unmarshaling when receiving -> response
//    	return MediaType.APPLICATION_JSON_TYPE;
	}
}
