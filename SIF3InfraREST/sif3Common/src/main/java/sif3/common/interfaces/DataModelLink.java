/*
 * DataModelLink.java
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

package sif3.common.interfaces;

import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.conversion.UnmarshalFactory;

/**
 * This interface defines a few functions that are regularly used in all implementations where infrastructure is linked with a data model.
 * The functions in this interface are free of an actual data model implementation, rather use factories etc.
 * 
 * @author Joerg Huber
 *
 */
public interface DataModelLink
{
	/**
	 * Returns a marshaller applicable for the data model supported for this implementation.
	 * 
	 * @return See Desc.
	 */
	public MarshalFactory getMarshaller();

	/**
	 * Returns an unmarshaller applicable for the data model supported for this implementation.
	 * 
	 * @return See Desc.
	 */
	public UnmarshalFactory getUnmarshaller();

	/**
	 * Returns the information for the 'single object'. The returned object holds the name of a 'single object' (i.e StudentPersonal) 
	 * and the physical class this maps to for the data model supported for this implementation.
	 * 
	 * @return See Desc.
	 */
	public ModelObjectInfo getSingleObjectClassInfo();

	/**
	 * Returns the information for the 'collection-style object'. The returned object holds the name of a 'collection-style object' 
	 * (i.e StudentPersonals - note the added 's') and the physical class this maps to for the data model supported for this implementation.
	 * 
	 * @return See Desc.
	 */
	public ModelObjectInfo getMultiObjectClassInfo();
}
