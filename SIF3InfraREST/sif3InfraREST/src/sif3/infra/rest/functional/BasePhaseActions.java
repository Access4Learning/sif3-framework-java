/*
 * Crown Copyright © Department for Education (UK) 2016
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

package sif3.infra.rest.functional;

import javax.ws.rs.core.MediaType;

import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.PhaseType;
import sif3.infra.rest.provider.BaseFunctionalServiceProvider;

/**
 * A simple implementation of the {@link sif3.infra.rest.functional.IPhaseActions IPhaseActions}
 * interface that throws appropriate not implemented type errors for each method. Allows an
 * implementation to only implement the methods it needs.
 */
public class BasePhaseActions implements IPhaseActions
{
    protected ObjectFactory               objectFactory = new ObjectFactory();
    private BaseFunctionalServiceProvider provider;

    /**
     * Constructor
     * 
     * @param provider
     *            The provider through which events may be sent.
     */
    public BasePhaseActions(BaseFunctionalServiceProvider provider)
    {
        this.provider = provider;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.functional.IPhaseActions#create(sif3.infra.common.model.JobType,
     * sif3.infra.common.model.PhaseType, java.lang.String, javax.ws.rs.core.MediaType,
     * javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public String create(JobType job, PhaseType phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnmarshalException,
            UnsupportedMediaTypeException, UnsupportedQueryException
    {
        throw new UnsupportedQueryException("CREATE is not supported");
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.functional.IPhaseActions#retrieve(sif3.infra.common.model.JobType,
     * sif3.infra.common.model.PhaseType, java.lang.String, javax.ws.rs.core.MediaType,
     * javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public String retrieve(JobType job, PhaseType phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnsupportedMediaTypeException,
            UnsupportedQueryException
    {
        throw new UnsupportedQueryException("RETRIEVE is not supported");
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.functional.IPhaseActions#update(sif3.infra.common.model.JobType,
     * sif3.infra.common.model.PhaseType, java.lang.String, javax.ws.rs.core.MediaType,
     * javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public String update(JobType job, PhaseType phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnmarshalException,
            UnsupportedMediaTypeException, UnsupportedQueryException
    {
        throw new UnsupportedQueryException("UPDATE is not supported");
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.functional.IPhaseActions#delete(sif3.infra.common.model.JobType,
     * sif3.infra.common.model.PhaseType, java.lang.String, javax.ws.rs.core.MediaType,
     * javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public String delete(JobType job, PhaseType phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnmarshalException,
            UnsupportedMediaTypeException, UnsupportedQueryException
    {
        throw new UnsupportedQueryException("DELETE is not supported");
    }

    /**
     * Gets the provider this action is working for to, for example, be used to send events.
     * 
     * @return the Provider for this action.
     */
    public BaseFunctionalServiceProvider getProvider()
    {
        return provider;
    }
}
