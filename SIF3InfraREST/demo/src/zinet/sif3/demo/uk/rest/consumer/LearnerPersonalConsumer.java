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

package zinet.sif3.demo.uk.rest.consumer;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import sif.dd.uk20.model.LearnerPersonalCollectionType;
import sif.dd.uk20.model.LearnerPersonalType;
import sif.dd.uk20.model.NameType;
import sif.dd.uk20.model.ObjectFactory;
import sif.dd.uk20.model.PersonalInformationType;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.UpdateType;
import sif3.common.model.SIFEvent;
import sif3.common.ws.Response;
import zinet.sif3.demo.uk.rest.ModelObjectConstants;

public class LearnerPersonalConsumer extends UKDataModelEventConsumer<LearnerPersonalCollectionType>
{
    public LearnerPersonalConsumer()
    {
        super();

        // Emulate an external actor issuing consumer actions
        new Timer().schedule(new TimerTask()
        {

            public void run()
            {
                List<Response> responses;
                Response r;
                LearnerPersonalType learner = null;
                String refid = "53E6F4C0109FDE4DC7E11DB9C0100163";

                try
                {
                    // Get a learner
                    logger.info("...");
                    logger.info("...");
                    logger.info("Fetching learner " + refid);
                    responses = retrieveByPrimaryKey(refid, null);
                    r = responses.isEmpty() ? null : responses.get(0);
                    if (r == null)
                    {
                        logger.error("Didn't get a response when retrieving a learner");
                    }
                    learner = (LearnerPersonalType) r.getDataObject();
                    NameType name = learner.getPersonalInformation().getName();
                    logger.info("Fetched " + name.getGivenName().getValue() + " "
                            + name.getFamilyName().getValue());

                    // Create a learner
                    logger.info("...");
                    logger.info("...");
                    logger.info("Creating learner Bruce Wayne");

                    ObjectFactory factory = new ObjectFactory();
                    LearnerPersonalType bruce = factory.createLearnerPersonalType();
                    bruce.setLocalId(factory.createLocalId("555"));

                    PersonalInformationType pi = factory.createPersonalInformationType();
                    NameType bname = factory.createNameType();
                    bname.setType("C");
                    bname.setFamilyName(factory.createNameTypeFamilyName("Wayne"));
                    bname.setGivenName(factory.createNameTypeGivenName("Bruce"));
                    pi.setName(bname);
                    bruce.setPersonalInformation(pi);

                    responses = createSingle(bruce, null);
                    r = responses.isEmpty() ? null : responses.get(0);
                    if (r == null)
                    {
                        logger.error("Didn't get a response when creating a learner");
                    }
                    learner = (LearnerPersonalType) r.getDataObject();
                    refid = learner.getRefId();
                    logger.info("Bruce was given RefID " + refid);

                    // Delete a learner
                    logger.info("...");
                    logger.info("...");
                    logger.info("Deleting learner Bruce Wayne");
                    if (refid == null || refid.isEmpty())
                    {
                        logger.error("No refid given, cannot delete without a refid");
                        return;
                    }
                    responses = deleteSingle(refid, null);
                    r = responses.isEmpty() ? null : responses.get(0);
                    if (r == null)
                    {
                        logger.error("Didn't get a response when deleting a learner");
                    }
                }
                catch (Exception e)
                {
                    logger.error(e.getMessage(), e);
                }
            }
        }, 3000);

    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.EventConsumer#createEventObject(java.lang.Object,
     * sif3.common.header.HeaderValues.EventAction, sif3.common.header.HeaderValues.UpdateType)
     */
    @Override
    public SIFEvent<LearnerPersonalCollectionType> createEventObject(Object sifObjectList,
            EventAction eventAction, UpdateType updateType)
    {
        if (sifObjectList != null)
        {
            if (sifObjectList instanceof LearnerPersonalCollectionType)
            {
                int size = ((LearnerPersonalCollectionType) sifObjectList).getLearnerPersonal()
                        .size();
                return new SIFEvent<LearnerPersonalCollectionType>(
                        (LearnerPersonalCollectionType) sifObjectList, eventAction, updateType,
                        size);
            }
            else
            {
                logger.error(
                        "The given event data is not of type LearnerCollectionType as expected. Cannot create event object. Return null");
            }
        }
        else
        {
            logger.error("The given event data is null. Cannot create event object. Return null");
        }
        return null; // if something is wrong then we get here.
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getSingleObjectClassInfo()
    {
        return ModelObjectConstants.LEARNER_PERSONAL;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
        return ModelObjectConstants.LEARNER_PERSONALS;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.infra.rest.consumer.AbstractConsumer#shutdown()
     */
    public void shutdown()
    {
        // Nothing to do at this stage
    }
}
