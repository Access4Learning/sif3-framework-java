/*
 * EnvironmentList.java
 * Created: 27/08/2013
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

package sif3.infra.common.env.types;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A service/adapter may deal with multiple environments, especially on the provider side. This class simply represents a list of
 * environments. This class is not a singleton, rather a POJO for environment lists. In an actual implementation it is suggested
 * to provide this list as a singleton as it is not a 'dynamic' structure, rather fairly static.
 * 
 * @author Joerg Huber
 *
 */
public class EnvironmentList<T extends EnvironmentInfo> implements Serializable
{
    private static final long serialVersionUID = -6777249825355460810L;
    
    private String adapterName = null;
    private boolean isConsumer = true;
    
    /* Hashmap: Key = environment Name, Data = ConsumerEnvironment or ProviderEnvironment*/
    private HashMap<String, T> environments = new HashMap<String, T>();
    
    public EnvironmentList() 
    {
    	this(null, true);
    }
	
    public EnvironmentList(String adapterName, boolean isConsumer) 
    {
    	super();
    	this.adapterName = adapterName;
    	this.isConsumer = isConsumer;
    }
    
	public String getAdapterName()
    {
    	return this.adapterName;
    }

	public void setAdapterName(String adapterName)
    {
    	this.adapterName = adapterName;
    }

	public boolean getIsConsumer()
    {
    	return this.isConsumer;
    }

	public void setIsConsumer(boolean isConsumer)
    {
    	this.isConsumer = isConsumer;
    }
	
  public void addEnvironment(T environment)
  {
    environments.put(environment.getEnvironmentName(), environment);
  }
  
  public T getEnvironment(String environmentName)
  {
    return environments.get(environmentName);
  }

	public HashMap<String, T> getEnvironments()
  {
    return environments;
  }

  public void setEnvironments(HashMap<String, T> environments)
  {
    this.environments = environments;
  }

  @Override
    public String toString()
    {
	    return "EnvironmentList [adapterName=" + this.adapterName + ", isConsumer="
	            + this.isConsumer + ", environments=" + this.environments + "]";
    }

}
