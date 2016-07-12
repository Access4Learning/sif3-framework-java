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

package sif3.common.model;

import javax.ws.rs.core.MediaType;

import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.interfaces.FunctionalServiceProvider;
import sif3.common.interfaces.PhaseActions;
import sif3.common.persist.model.SIF3Job;
import sif3.common.persist.model.SIF3Phase;

/**
 * A simple implementation of the {@link sif3.common.interfaces.PhaseActions IPhaseActions}
 * interface that throws appropriate not implemented type errors for each method. Allows an
 * implementation to only implement the methods it needs.
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public class BasePhaseActions implements PhaseActions
{
    private FunctionalServiceProvider provider;

    /**
     * Constructor
     * 
     * @param provider
     *            The provider through which events may be sent.
     */
    public BasePhaseActions(FunctionalServiceProvider provider)
    {
        this.provider = provider;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.common.interfaces.PhaseActions#create(sif3.common.persist.model.SIF3Job,
     * sif3.common.persist.model.SIF3Phase, java.lang.String, javax.ws.rs.core.MediaType,
     * javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public String create(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnmarshalException,
            UnsupportedMediaTypeException, UnsupportedQueryException
    {
        throw new UnsupportedQueryException("CREATE is not supported");
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.common.interfaces.PhaseActions#retrieve(sif3.common.persist.model.SIF3Job,
     * sif3.common.persist.model.SIF3Phase, java.lang.String, javax.ws.rs.core.MediaType,
     * javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public String retrieve(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnsupportedMediaTypeException,
            UnsupportedQueryException
    {
        throw new UnsupportedQueryException("RETRIEVE is not supported");
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.common.interfaces.PhaseActions#update(sif3.common.persist.model.SIF3Job,
     * sif3.common.persist.model.SIF3Phase, java.lang.String, javax.ws.rs.core.MediaType,
     * javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public String update(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnmarshalException,
            UnsupportedMediaTypeException, UnsupportedQueryException
    {
        throw new UnsupportedQueryException("UPDATE is not supported");
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.common.interfaces.PhaseActions#delete(sif3.common.persist.model.SIF3Job,
     * sif3.common.persist.model.SIF3Phase, java.lang.String, javax.ws.rs.core.MediaType,
     * javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public String delete(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnmarshalException,
            UnsupportedMediaTypeException, UnsupportedQueryException
    {
        throw new UnsupportedQueryException("DELETE is not supported");
    }

    /**
     * Gets the provider this action is working for so that, for example, it can be used to send
     * events.
     * 
     * @return the Provider for this action.
     */
    public FunctionalServiceProvider getProvider()
    {
        return provider;
    }
}
