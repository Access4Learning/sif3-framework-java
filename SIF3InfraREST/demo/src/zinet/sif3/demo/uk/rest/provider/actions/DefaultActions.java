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

package zinet.sif3.demo.uk.rest.provider.actions;

import javax.ws.rs.core.MediaType;

import sif3.common.CommonConstants;
import sif3.common.CommonConstants.JobState;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.infra.common.interfaces.FunctionalServiceProvider;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.PhaseType;
import sif3.infra.common.utils.ServiceUtils;
import sif3.infra.rest.functional.BasePhaseActions;

public class DefaultActions extends BasePhaseActions
{
    public DefaultActions(FunctionalServiceProvider provider)
    {
        super(provider);
    }

    @Override
    public String create(JobType job, PhaseType phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnmarshalException,
            UnsupportedMediaTypeException, UnsupportedQueryException
    {
        ServiceUtils.changeJobState(job, CommonConstants.JobState.INPROGRESS, "CREATE to " + phase.getName());
        ServiceUtils.changePhaseState(job, phase, CommonConstants.PhaseState.INPROGRESS, "CREATE");

        getProvider().sendJobEvent(job, phase.getName(), EventAction.UPDATE, zone, context);

        return "Got CREATE message for " + phase.getName() + "@" + job.getId()
                + " with content type " + requestMediaType.toString() + " and accept "
                + responseMediaType.toString() + ".\nBODY START\n" + payload + ".\nBODY END.";
    }

    @Override
    public String retrieve(JobType job, PhaseType phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
    {
        ServiceUtils.changeJobState(job, CommonConstants.JobState.INPROGRESS, "RETRIEVE to " + phase.getName());
        ServiceUtils.changePhaseState(job, phase, CommonConstants.PhaseState.INPROGRESS, "RETRIEVE");

        getProvider().sendJobEvent(job, phase.getName(), EventAction.UPDATE, zone, context);

        return "Got RETRIEVE message for " + phase.getName() + "@" + job.getId() + " with accept "
                + responseMediaType.toString() + ".";
    }

    @Override
    public String update(JobType job, PhaseType phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
    {
        ServiceUtils.changeJobState(job, CommonConstants.JobState.INPROGRESS, "UPDATE to " + phase.getName());
        ServiceUtils.changePhaseState(job, phase, CommonConstants.PhaseState.COMPLETED, "UPDATE");

        getProvider().sendJobEvent(job, phase.getName(), EventAction.UPDATE, zone, context);

        return "Got UPDATE message for " + phase.getName() + "@" + job.getId()
                + " with content type " + requestMediaType.toString() + " and accept "
                + responseMediaType.toString() + ".\nBODY START\n" + payload + ".\nBODY END.";
    }

    @Override
    public String delete(JobType job, PhaseType phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
    {
        ServiceUtils.changeJobState(job, CommonConstants.JobState.INPROGRESS, "DELETE to " + phase.getName());
        ServiceUtils.changePhaseState(job, phase, CommonConstants.PhaseState.COMPLETED, "DELETE");

        getProvider().sendJobEvent(job, phase.getName(), EventAction.UPDATE, zone, context);

        return "Got DELETE message for " + phase.getName() + "@" + job.getId()
                + " with content type " + requestMediaType.toString() + " and accept "
                + responseMediaType.toString() + ".\nBODY START\n" + payload + ".\nBODY END.";
    }
}
