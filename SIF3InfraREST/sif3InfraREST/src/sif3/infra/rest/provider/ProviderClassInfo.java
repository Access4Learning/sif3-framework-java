/*
 * ProviderClassInfo.java
 * Created: 01/10/2014
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
package sif3.infra.rest.provider;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * This is a small utility class to be used by the ProviderFactory. It is intended to be used as a container to maintain all
 * valid provider constructors, so that an efficient lookup of appropriate provider classes and their instantiation can be performed
 * by the ProviderFactory.<br/><br/>
 * 
 * Note: At this point in time this is a very simple helper class which, in future, might be extended. But as of now (Oct. 2014) it is 
 * specifically geared to be used by the ProviderFactory.
 * 
 * @author Joerg Huber
 *
 */
public class ProviderClassInfo implements Serializable
{
	private static final long serialVersionUID = 4829550138088401431L;

	private Constructor<?> constructor = null;

	/**
	 * This will create the information for the constructor of the given 'clazz' and parameter
	 * types.
	 * 
	 * @param clazz  Fully qualified path of the class for which constructor information shall be
	 *               maintained.
	 * @param paramTypes A list of parameter types as specified in the java reflection API. This 
	 *                   parameter can be null.
	 * 
	 * @throws SecurityException  See java reflection for details.
	 * @throws NoSuchMethodException If the given 'clazz' has no constructor for the given parameter type list.
	 */
	@SuppressWarnings("rawtypes")
	public ProviderClassInfo(Class<?> clazz, Class[] paramTypes) throws SecurityException, NoSuchMethodException
	{
		setConstructor(clazz.getConstructor(paramTypes));
	}

	/**
	 * Returns an instance of the class as given by the constructor of this ProviderClassInfo.
	 * 
	 * @param params
	 *            Actual values of parameters to be used for the instantiation of the class. This
	 *            can be null. If the array is not null then the type of each element must match the
	 *            type listed in the 'paramTypes' parameter of the constructor of this class. For
	 *            details refer to the java reflection API.
	 * 
	 * @return See desc.
	 * 
	 * @throws IllegalArgumentException Invalid type of one or more elements in the 'params' array.
	 * @throws InstantiationException Failed to instantiate the object.
	 * @throws IllegalAccessException See java reflection for details.
	 * @throws InvocationTargetException See java reflection for details.
	 */
	public Object getClassInstance(Object[] params) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		return getConstructor().newInstance(params);
	}

	public Constructor<?> getConstructor()
	{
		return constructor;
	}

	public void setConstructor(Constructor<?> constructor)
	{
		this.constructor = constructor;
	}
}
