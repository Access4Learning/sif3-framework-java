/*
 * RolloverStudentConsumer.java
 * Created: 26 Jul 2018
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

package systemic.sif3.demo.rest.consumer.functional;

/**
 * @author Joerg Huber
 *
 */
public class RolloverStudentConsumer extends BaseFunctionalConsumer
{

    public RolloverStudentConsumer()
    {
        super();
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractFunctionalServiceConsumer#getServiceURLNamePlural()
     */
    @Override
    public String getServiceURLNamePlural()
    {
        return "RolloverStudents";
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.consumer.AbstractFunctionalServiceConsumer#getServiceURLNameSingular()
     */
    @Override
    public String getServiceURLNameSingular()
    {
        return "RolloverStudent";
    }


    /* (non-Javadoc)
     * @see sif3.infra.rest.consumer.BaseConsumer#shutdown()
     */
    @Override
    public void shutdown()
    {
        logger.info("Shutting down Functional Service Consumer '"+getServiceURLNamePlural()+"'");
    }
}
