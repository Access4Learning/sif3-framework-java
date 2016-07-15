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

import sif.dd.conversion.DataModelUnmarshalFactory;
import sif.dd.uk20.model.LearnerPersonalType;
import sif.dd.uk20.model.NameType;
import sif3.common.CommonConstants;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.interfaces.FunctionalServiceProvider;
import sif3.common.model.BasePhaseActions;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.persist.model.SIF3Job;
import sif3.common.persist.model.SIF3Phase;

/**
 * The actions for the phase "xml"
 * 
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public class XmlActions extends BasePhaseActions
{
    public XmlActions(FunctionalServiceProvider provider)
    {
        super(provider);
    }

    @Override
    public String update(SIF3Job job, SIF3Phase phase, String payload, MediaType requestMediaType,
            MediaType responseMediaType, SIFZone zone, SIFContext context)
            throws IllegalArgumentException, PersistenceException, UnmarshalException,
            UnsupportedMediaTypeException, UnsupportedQueryException
    {
        job.updateJobState(CommonConstants.JobState.INPROGRESS, "UPDATE to " + phase.getName());

        String response;
        if (!requestMediaType.isCompatible(MediaType.APPLICATION_XML_TYPE))
        {
            response = "Invalid Content-Type, expecting "
                    + MediaType.APPLICATION_XML_TYPE.toString();
            phase.updatePhaseState(CommonConstants.PhaseState.FAILED, response);
            throw new IllegalArgumentException(response);
        }

        LearnerPersonalType learner = (LearnerPersonalType) new DataModelUnmarshalFactory()
                .unmarshalFromXML(payload, LearnerPersonalType.class);

        NameType name = learner.getPersonalInformation().getName();
        phase.updatePhaseState(CommonConstants.PhaseState.COMPLETED, "UPDATE");

        return "Got UPDATE message for " + phase.getName() + "@" + job.getId()
                + " with content type " + requestMediaType.toString() + " and accept "
                + responseMediaType.toString() + ".\nGot record for learner:"
                + name.getGivenName().getValue() + " " + name.getFamilyName().getValue();
    }
}
