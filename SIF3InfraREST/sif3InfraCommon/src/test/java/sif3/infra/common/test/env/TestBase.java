/*
 * TestBase.java
 * Created: 9 Jan 2018
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

package sif3.infra.common.test.env;

import sif3.common.exception.MarshalException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.persist.common.HibernateUtil;
import sif3.infra.common.conversion.InfraMarshalFactory;

/**
 * @author Joerg Huber
 *
 */
public class TestBase
{
    public TestBase()
    {
        HibernateUtil.initialise(null);
    }
    
    public void shutdown()
    {
        HibernateUtil.shutdown();
    }
    
    protected void printInfraObjectAsXML(Object infraObj)
    {
        if (infraObj == null)
        {
            System.out.println("Null object provided!");
        }
        else
        {
            try
            {
                InfraMarshalFactory marshaller = new InfraMarshalFactory();
                System.out.println(infraObj.getClass().getSimpleName()+" as XML:\n" + marshaller.marshalToXML(infraObj));
            }
            catch (MarshalException ex)
            {
                ex.printStackTrace();
            }
            catch (UnsupportedMediaTypeExcpetion ex)
            {
                ex.printStackTrace();
            }
        }
    }

}
