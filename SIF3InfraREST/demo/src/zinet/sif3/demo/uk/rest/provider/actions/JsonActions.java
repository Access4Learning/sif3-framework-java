/*
 * Crown Copyright © Department for Education (UK) 2016
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package zinet.sif3.demo.uk.rest.provider.actions;

import javax.ws.rs.core.MediaType;

import sif.dd.conversion.DataModelUnmarshalFactory;
import sif.dd.uk20.model.LearnerPersonalType;
import sif.dd.uk20.model.NameType;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.infra.common.ServiceStatus.JobState;
import sif3.infra.common.ServiceStatus.PhaseState;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.PhaseType;
import sif3.infra.common.utils.ServiceUtils;
import sif3.infra.rest.functional.BasePhaseActions;
import sif3.infra.rest.provider.BaseFunctionalServiceProvider;

public class JsonActions extends BasePhaseActions {
	public JsonActions(BaseFunctionalServiceProvider provider) {
		super(provider);
	}
	
	@Override
	public String update(JobType job, PhaseType phase, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException, UnmarshalException, UnsupportedMediaTypeException, UnsupportedQueryException {
		ServiceUtils.changeJobState(job, JobState.INPROGRESS, "UPDATE to " + phase.getName());
		
    String response;
    if (!requestMediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE))
    {
        response = "Invalid Content-Type, expecting " + MediaType.APPLICATION_JSON_TYPE.toString();
        ServiceUtils.changePhaseState(job, phase, PhaseState.FAILED, response);
        throw new IllegalArgumentException(response);
    }

    LearnerPersonalType learner = (LearnerPersonalType)new DataModelUnmarshalFactory().unmarshalFromJSON(payload, LearnerPersonalType.class);

    NameType name = learner.getPersonalInformation().getName();
    ServiceUtils.changePhaseState(job, phase, PhaseState.COMPLETED, "UPDATE");
    
    getProvider().sendJobEvent(job, phase.getName(), EventAction.UPDATE, zone, context);
    
    return "Got UPDATE message for " + phase.getName() + "@" + job.getId() + " with content type " + requestMediaType.toString() + " and accept " + responseMediaType.toString() + ".\nGot record for learner:" + name.getGivenName().getValue() + " " + name.getFamilyName().getValue();
	}
}
