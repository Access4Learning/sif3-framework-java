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

package zinet.sif3.demo.uk.rest.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import au.com.systemic.framework.utils.DateUtils;
import au.com.systemic.framework.utils.FileReaderWriter;
import au.com.systemic.framework.utils.StringUtils;
import sif.dd.uk20.model.LearnerPersonalCollectionType;
import sif.dd.uk20.model.LearnerPersonalType;
import sif.dd.uk20.model.ObjectFactory;
import sif3.common.CommonConstants;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.ResponseHeaderConstants;
import sif3.common.interfaces.ChangesSinceProvider;
import sif3.common.interfaces.QueryProvider;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.ChangedSinceInfo;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.utils.UUIDGenerator;
import sif3.common.ws.CreateOperationStatus;
import sif3.common.ws.ErrorDetails;
import sif3.common.ws.OperationStatus;
import zinet.sif3.demo.uk.rest.ModelObjectConstants;
import zinet.sif3.demo.uk.rest.provider.iterators.LearnerPersonalIterator;

public class LearnerPersonalProvider
        extends UKDataModelProviderWithEvents<LearnerPersonalCollectionType>
        implements QueryProvider, ChangesSinceProvider
{

    private static int                                  numDeletes      = 0;
    private static HashMap<String, LearnerPersonalType> learners        = null;
    private ObjectFactory                               dmObjectFactory = new ObjectFactory();

    public LearnerPersonalProvider()
    {
        super();

        logger.debug("Constructor for LearnerPersonalProvider has been called.");
        if (learners == null)
        {
            logger.debug(
                    "Constructor for LearnerPersonalProvider called for the first time. Try to load Learners from XML file...");

            learners = new HashMap<String, LearnerPersonalType>();

            // Load all Learners so that we can do some real stuff here.
            String learnerFile = getServiceProperties()
                    .getPropertyAsString("provider.learner.file.location", null);
            if (learnerFile != null)
            {
                String inputXML = FileReaderWriter.getFileContent(learnerFile,
                        ModelObjectConstants.UTF_8);
                try
                {
                    LearnerPersonalCollectionType learnerList = (LearnerPersonalCollectionType) getUnmarshaller()
                            .unmarshalFromXML(inputXML, getMultiObjectClassInfo().getObjectType());
                    if ((learnerList != null) && (learnerList.getLearnerPersonal() != null))
                    {
                        for (LearnerPersonalType learnerPersonal : learnerList.getLearnerPersonal())
                        {
                            learners.put(learnerPersonal.getRefId(), learnerPersonal);
                        }
                        logger.debug("Loaded " + learners.size() + " Learners into memory.");
                    }
                }
                catch (UnmarshalException ex)
                {
                    logger.error(ex.getMessage(), ex);
                }
                catch (UnsupportedMediaTypeException ex)
                {
                    logger.error(ex.getMessage(), ex);
                }
            }
        }
    }

    /*-------------------------------------*/
    /*-- EventProvider Interface Methods --*/
    /*-------------------------------------*/
    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.EventProvider#getSIFEvents()
     */
    @Override
    public SIFEventIterator<LearnerPersonalCollectionType> getSIFEvents()
    {
        return new LearnerPersonalIterator(getServiceName(), getServiceProperties(), learners);
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.EventProvider#onEventError(sif3.common.model. SIFEvent,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public void onEventError(SIFEvent<LearnerPersonalCollectionType> sifEvent, SIFZone zone,
            SIFContext context)
    {
        // We need to deal with the error. At this point in time we just log it.
        if ((sifEvent != null) && (sifEvent.getSIFObjectList() != null))
        {
            try
            {
                String eventXML = getMarshaller().marshalToXML(sifEvent.getSIFObjectList());
                logger.error("Failed to sent the following Objects as and Event to Zone (" + zone
                        + ") and Context (" + context + "):\n" + eventXML);
            }
            catch (Exception ex)
            {
                logger.error("Failed to marshall events.", ex);
            }
        }
        else
        {
            logger.error(
                    "sifEvent Object is null, or there are no events on sifEvent.sifObjectList");
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.EventProvider#modifyBeforeSent(sif3.common.model. SIFEvent,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public SIFEvent<LearnerPersonalCollectionType> modifyBeforePublishing(
            SIFEvent<LearnerPersonalCollectionType> sifEvent, SIFZone zone, SIFContext context,
            HeaderProperties customHTTPHeaders)
    {
        // Here we could add some custom HTTP header values but at this time we
        // have no interest to do so.

        // At this point we don't need to modify anything. Just send as is...
        return sifEvent;
    }

    /*--------------------------------*/
    /*-- Provider Interface Methods --*/
    /*--------------------------------*/

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#retrieveByPrimaryKey(java.lang.String,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object retrieveByPrimaryKey(String resourceID, SIFZone zone, SIFContext context,
            RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        if (StringUtils.isEmpty(resourceID))
        {
            throw new IllegalArgumentException(
                    "Resource ID is null or empty. It must be provided to retrieve an entity.");
        }

        logger.debug("Retrieve learner with Resource ID = " + resourceID + ", "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        LearnerPersonalType learner = learners.get(resourceID);
        // logger.debug("Learner to return: "+((learner != null) ?
        // learner.getPersonInfo().getName().getGivenName().getValue() :
        // learner));

        return learner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#createSingle(java.lang.Object,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public Object createSingle(Object data, boolean useAdvisory, SIFZone zone, SIFContext context,
            RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        logger.debug("Create Single learner for " + getZoneAndContext(zone, context)
                + " and RequestMetadata = " + metadata);

        // Must be of type LearnerPersonalType
        if (data instanceof LearnerPersonalType)
        {
            LearnerPersonalType learner = (LearnerPersonalType) data;
            if (StringUtils.isEmpty(learner.getRefId()))
            {
                // In future this should be a UUID instead of a GUID
                if (!useAdvisory)
                {
                    // Create new UUID because the advisory shall not be used.
                    learner.setRefId(UUIDGenerator.getSIF2GUIDUpperCase());
                    logger.debug("Learner now has refid " + learner.getRefId());
                }
                // else leave learner UUID untouched.
            }

            // In the real implementation we would call a BL method here to
            // create the learner.

            learners.put(learner.getRefId(), learner);

            return learner;
        }
        else
        {
            throw new IllegalArgumentException(
                    "Expected Object Type  = LearnerPersonalType. Received Object Type = "
                            + data.getClass().getSimpleName());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#updateSingle(java.lang.Object, java.lang.String,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public boolean updateSingle(Object data, String resourceID, SIFZone zone, SIFContext context,
            RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        if (StringUtils.isEmpty(resourceID))
        {
            throw new IllegalArgumentException(
                    "Resource ID is null or empty. It must be provided to update an entity.");
        }

        // Must be of type LearnerPersonalType
        if (data instanceof LearnerPersonalType)
        {
            logger.debug("Update learner with Resoucre ID = " + resourceID + ", "
                    + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

            // In the real implementation we would call a BL method here to
            // modify the learner.
            return true;
        }
        else
        {
            throw new IllegalArgumentException(
                    "Expected Object Type  = LearnerPersonalType. Received Object Type = "
                            + data.getClass().getSimpleName());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#deleteSingle(java.lang.String,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext)
     */
    @Override
    public boolean deleteSingle(String resourceID, SIFZone zone, SIFContext context,
            RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        if (StringUtils.isEmpty(resourceID))
        {
            throw new IllegalArgumentException(
                    "Resource ID is null or empty. It must be provided to delete an entity.");
        }

        logger.debug("Remove learner with Resoucre ID = " + resourceID + ", "
                + getZoneAndContext(zone, context) + " and RequestMetadata = " + metadata);

        // Remove the learner if it exists
        if (learners.containsKey(resourceID))
        {
            learners.remove(resourceID);
            return true;
        }
        // Fake it so that the operation sometimes succeeds and fails
        return ((numDeletes++ % 3) != 0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#retrive(sif3.common.model.SIFZone,
     * sif3.common.model.SIFContext, sif3.common.model.PagingInfo)
     */
    @Override
    public Object retrieve(SIFZone zone, SIFContext context, PagingInfo pagingInfo,
            RequestMetadata metadata, ResponseParameters customResponseParams)
            throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
        logger.debug("Retrieve learners for " + getZoneAndContext(zone, context)
                + " and RequestMetadata = " + metadata);
        logger.debug("ChangedSince Date: " + metadata.getRequestParameter("ChangedSince"));
        logger.debug("Custom HTTP Header (customHdr): " + metadata.getHTTPParameter("customHdr"));

        if (pagingInfo == null)
        {
            throw new DataTooLargeException(
                    "No paging info is provided. Please provide navigationPage and navigationPageSize.");
        }
        else
        {
            // We may want to set the navigationID! Here we set it as a GUID but
            // only if we are on the first page because it is expected that in
            // subsequent
            // paged calls that the consumer would provide that id.
            if (pagingInfo.getCurrentPageNo() == CommonConstants.FIRST_PAGE)
            {
                // pagingInfo.setNavigationId(UUIDGenerator.getUUID());
            }
        }

        ArrayList<LearnerPersonalType> learnerList = fetchLearners(learners, pagingInfo);

        LearnerPersonalCollectionType LearnerCollection = dmObjectFactory
                .createLearnerPersonalCollectionType();

        if (learnerList != null)
        {
            LearnerCollection.getLearnerPersonal().addAll(learnerList);
            return LearnerCollection;
        }
        else
        {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.QueryProvider#retrieveByServicePath(sif3.common.
     * model.QueryCriteria, sif3.common.model.SIFZone, sif3.common.model.SIFContext,
     * sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     */
    @Override
    public Object retrieveByServicePath(QueryCriteria queryCriteria, SIFZone zone,
            SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata,
            ResponseParameters customResponseParams)
            throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
        logger.debug("Performing query by service path.");
        if (logger.isDebugEnabled())
        {
            logger.debug("Query Condition (given by service path): " + queryCriteria);
        }

        // Not implemented in this demo
        throw new UnsupportedQueryException("The query condition (driven by the service path) "
                + queryCriteria + " is not supported by the provider.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.QueryProvider#retrieveByQBE(java.lang.Object,
     * sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo,
     * sif3.common.model.RequestMetadata)
     */
    public Object retrieveByQBE(Object exampleObject, SIFZone zone, SIFContext context,
            PagingInfo pagingInfo, RequestMetadata metadata,
            ResponseParameters customResponseParams)
            throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
        logger.debug("Performing QBE query for: " + exampleObject);
        if (exampleObject instanceof LearnerPersonalType)
        {
            ArrayList<LearnerPersonalType> learnerList = fetchLearners(learners, pagingInfo);
            LearnerPersonalCollectionType learnerCollection = dmObjectFactory
                    .createLearnerPersonalCollectionType();
            if (learnerList != null)
            {
                learnerCollection.getLearnerPersonal().addAll(learnerList);
                return learnerCollection;
            }
            else
            {
                return null;
            }
        }
        else
        {
            throw new IllegalArgumentException(
                    "Expected Object Type  = LearnerPersonalType. Received Object Type = "
                            + exampleObject.getClass().getSimpleName());
        }
        // throw new UnsupportedQueryException("QBE not supported for
        // LearnerPersonals");
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#createMany(java.lang.Object, sif3.common.model.SIFZone,
     * sif3.common.model.SIFContext)
     */
    @Override
    public List<CreateOperationStatus> createMany(Object data, boolean useAdvisory, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        // Must be of type LearnerPersonalType
        if (data instanceof LearnerPersonalCollectionType)
        {
            logger.debug("Create learners (Bulk Operation) for " + getZoneAndContext(zone, context)
                    + " and RequestMetadata = " + metadata);
            LearnerPersonalCollectionType learners = (LearnerPersonalCollectionType) data;
            ArrayList<CreateOperationStatus> opStatus = new ArrayList<CreateOperationStatus>();
            int i = 0;
            for (LearnerPersonalType learner : learners.getLearnerPersonal())
            {
                if ((i % 3) == 0)
                {
                    // Set advisoryID the same as resourceID.
                    opStatus.add(new CreateOperationStatus(learner.getRefId(), learner.getRefId(),
                            404, new ErrorDetails(400, "Data not good.")));
                }
                else
                {
                    if (useAdvisory)
                    {
                        // Advisory refId was used. Set resourceId and
                        // advisoryId to the same
                        opStatus.add(new CreateOperationStatus(learner.getRefId(),
                                learner.getRefId(), 201));
                    }
                    else
                    {
                        // Create a new refId (resourceID) but we must also
                        // report back the original RefId as the advisory if it
                        // was available.
                        opStatus.add(new CreateOperationStatus(UUIDGenerator.getSIF2GUIDUpperCase(),
                                learner.getRefId(), 201));
                    }
                }
                i++;
            }

            return opStatus;
        }
        else
        {
            throw new IllegalArgumentException(
                    "Expected Object Type  = LearnerCollectionType. Received Object Type = "
                            + data.getClass().getSimpleName());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#updateMany(java.lang.Object, sif3.common.model.SIFZone,
     * sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> updateMany(Object data, SIFZone zone, SIFContext context,
            RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        // Must be of type LearnerPersonalType
        if (data instanceof LearnerPersonalCollectionType)
        {
            logger.debug("Update Learners (Bulk Operation) for " + getZoneAndContext(zone, context)
                    + " and RequestMetadata = " + metadata);
            LearnerPersonalCollectionType learners = (LearnerPersonalCollectionType) data;
            ArrayList<OperationStatus> opStatus = new ArrayList<OperationStatus>();
            int i = 0;
            for (LearnerPersonalType learner : learners.getLearnerPersonal())
            {
                if ((i % 3) == 0)
                {
                    opStatus.add(new OperationStatus(learner.getRefId(), 404, new ErrorDetails(404,
                            "Learner with GUID = " + learner.getRefId() + " does not exist.")));
                }
                else
                {
                    opStatus.add(new OperationStatus(learner.getRefId(), 200));
                }
                i++;
            }

            return opStatus;
        }
        else
        {
            throw new IllegalArgumentException(
                    "Expected Object Type  = LearnerCollectionType. Received Object Type = "
                            + data.getClass().getSimpleName());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.Provider#deleteMany(java.lang.Object, sif3.common.model.SIFZone,
     * sif3.common.model.SIFContext)
     */
    @Override
    public List<OperationStatus> deleteMany(List<String> resourceIDs, SIFZone zone,
            SIFContext context, RequestMetadata metadata, ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException
    {
        logger.debug("Delete Learners (Bulk Operation) for " + getZoneAndContext(zone, context)
                + " and RequestMetadata = " + metadata);

        // In the real implementation we would call a BL method here to modify
        // the Learner.
        ArrayList<OperationStatus> opStatus = new ArrayList<OperationStatus>();
        int i = 0;
        for (String resourceID : resourceIDs)
        {
            if ((i % 3) == 0)
            {
                opStatus.add(new OperationStatus(resourceID, 404, new ErrorDetails(404,
                        "Learner with GUID = " + resourceID + " does not exist.")));
            }
            else
            {
                opStatus.add(new OperationStatus(resourceID, 200));
            }
            i++;
        }
        return opStatus;
    }

    /*
     * Override the method from the AUDataModelProviderWithEvents class and set a few custom
     * headers. (non-Javadoc)
     * 
     * @see systemic.sif3.demo.rest.provider.AUDataModelProviderWithEvents#
     * getCustomServiceInfo(sif3.common.model.SIFZone, sif3.common.model.SIFContext,
     * sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     */
    @Override
    public HeaderProperties getCustomServiceInfo(SIFZone zone, SIFContext context,
            PagingInfo pagingInfo, RequestMetadata metadata)
            throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
        HeaderProperties headers = new HeaderProperties();
        headers.setHeaderProperty("TestHeaderProperty1", "value1");
        headers.setHeaderProperty(ResponseHeaderConstants.HDR_ENVIRONMENT_URI,
                "/should/be/overridden");
        headers.setHeaderProperty(ResponseHeaderConstants.HDR_REQUEST_ID, "123"); // should
        // not
        // be
        // accepted.
        return headers;
    }

    /*--------------------------------------------*/
    /*-- ChangesSinceProvider Interface Methods --*/
    /*--------------------------------------------*/

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.ChangesSinceProvider#changesSinceSupported()
     */
    @Override
    public boolean changesSinceSupported()
    {
        return true;
    }

    /*
     * For the purpose of illustrating the use of the changes since functionality we assume that
     * changes since are provided by this Object provider based on date time stamps. Real
     * implementations may look up a table to get the latest value of the opaque marker.
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.ChangesSinceProvider#getLatestOpaqueMarker()
     */
    @Override
    public String getLatestOpaqueMarker(SIFZone zone, SIFContext context, PagingInfo pagingInfo,
            RequestMetadata metadata)
    {
        return DateUtils.nowAsISO8601withSecFraction();
    }

    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.ChangesSinceProvider#getChangesSince(sif3.common. model.SIFZone,
     * sif3.common.model.SIFContext, sif3.common.model.PagingInfo,
     * sif3.common.model.ChangedSinceInfo, sif3.common.model.RequestMetadata)
     */
    @Override
    public Object getChangesSince(SIFZone zone, SIFContext context, PagingInfo pagingInfo,
            ChangedSinceInfo changedSinceInfo, RequestMetadata metadata,
            ResponseParameters customResponseParams)
            throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
        // This is not a real implementation. We just fake things here. In a
        // real implementation one would go to a change log to retrieve
        // Learners for the given changedSinceInfo criteria.
        logger.info("getChangesSince for " + getProviderName() + " called with changes since info: "
                + changedSinceInfo);
        return retrieve(zone, context, pagingInfo, metadata, customResponseParams);
    }

    /*--------------------------------------*/
    /*-- Other required Interface Methods --*/
    /*--------------------------------------*/
    /*
     * (non-Javadoc)
     * 
     * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getSingleObjectClassInfo()
    {
        return zinet.sif3.demo.uk.rest.ModelObjectConstants.LEARNER_PERSONAL;
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

    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    private String getZoneAndContext(SIFZone zone, SIFContext context)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Zone = ").append(
                (zone == null) ? "null" : zone.getId() + (zone.getIsDefault() ? " (dafault)" : ""))
                .append(" ");
        buffer.append("- Context = ").append((context == null) ? "null" : context.getId());

        return buffer.toString();
    }

    private ArrayList<LearnerPersonalType> fetchLearners(
            HashMap<String, LearnerPersonalType> LearnerMap, PagingInfo pagingInfo)
    {
        ArrayList<LearnerPersonalType> learnerList = new ArrayList<LearnerPersonalType>();
        if (pagingInfo == null) // return all
        {
            learnerList.addAll(LearnerMap.values());
        }
        else
        {
            pagingInfo.setTotalObjects(LearnerMap.size());
            if ((pagingInfo.getPageSize() * (pagingInfo.getCurrentPageNo())) > LearnerMap.size())
            {
                return null; // Requested
                             // page
                             // outside
                             // of
                             // limits.
            }

            // retrieve applicable Learners
            Collection<LearnerPersonalType> allLearner = LearnerMap.values();
            int i = 0;
            int startPos = pagingInfo.getPageSize() * pagingInfo.getCurrentPageNo();
            int endPos = startPos + pagingInfo.getPageSize();
            for (Iterator<LearnerPersonalType> iter = allLearner.iterator(); iter.hasNext();)
            {
                LearnerPersonalType Learner = iter.next();
                if ((i >= startPos) && (i < endPos))
                {
                    learnerList.add(Learner);
                }
                i++;
            }
            // Set the number of object that are returned in the paging info.
            // Will ensure HTTP headers are set correctly.
            pagingInfo.setPageSize(learnerList.size());
        }

        return learnerList;
    }
}
