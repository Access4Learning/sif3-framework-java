/*
 * RolloverStudentsProvider.java
 * Created: 7 Jun 2018
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

package systemic.sif3.demo.rest.provider.functional;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import sif3.common.CommonConstants.JobState;
import sif3.common.CommonConstants.PhaseState;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIFException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.model.PagingInfo;
import sif3.common.model.PayloadMetadata;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.job.PhaseInfo;
import sif3.common.ws.job.PhaseDataRequest;
import sif3.common.ws.job.PhaseDataResponse;
import sif3.infra.common.model.JobType;
import sif3.infra.rest.provider.BaseFunctionalServiceProvider;

/**
 * @author Joerg Huber
 *
 */
public class RolloverStudentsProvider extends BaseFunctionalServiceProvider
{
    private static int numOperations = 0;

    /*--------------------*/
    /*-- Job Operations --*/
    /*--------------------*/
    
    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.BaseFunctionalServiceProvider#createNewJob(sif3.infra.common.model.JobType, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public JobType createNewJob(JobType jobData, 
                                SIFZone zone, 
                                SIFContext context,
                                RequestMetadata metadata, 
                                ResponseParameters customResponseParams)
            throws IllegalArgumentException, PersistenceException, SIFException
    {
        logger.debug("Create Job for Functional Service "+getMultiObjectClassInfo().getObjectName()+"...");
      
        // At the moment we do nothing... A real implementation may need to do some stuff here.
        if (jobData.getInitialization() != null)
        {
            logger.debug("Initialisation values:\nInitialisation Phase: "+jobData.getInitialization().getPhaseName()+"\nInitialisation Parameters: "+jobData.getInitialization().getPayload());
        }
        else
        {
            logger.debug("No initialisation provided for Job.");
        }
      
        // return the final job object. Generally that is the same as the one given to this method.
        return jobData;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#deleteJob(java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public boolean deleteJob(String jobID, 
                             SIFZone zone, 
                             SIFContext context, 
                             RequestMetadata metadata, 
                             ResponseParameters customResponseParams) 
                   throws IllegalArgumentException, PersistenceException, SIFException
    {
        logger.debug("Delete Job for Functional Service "+getMultiObjectClassInfo().getObjectName()+" and jobID = '"+jobID+"'");

        // Generally the implementation would check here what needs to be done to remove a job. If the implementation no longer 
        // knows the job (i.e. has been removed in the past) then it must return false. If the job is still known it must be removed and true
        // must be returned.
        
        // We pretend to fail every 3rd delete just for the sake of testing real world scenarios where failure occur.
        return ((numOperations++ % 3) != 0);
    }

    /*----------------------*/
    /*-- Phase Operations --*/
    /*----------------------*/
    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#retrieveDataFromPhase(java.lang.String, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters, javax.ws.rs.core.MediaType)
     */
    @Override
    public PhaseDataResponse retrieveDataFromPhase(PhaseInfo phaseInfo, 
                                                   SIFZone zone, 
                                                   SIFContext context,  
                                                   PagingInfo pagingInfo, 
                                                   RequestMetadata metadata, 
                                                   ResponseParameters customResponseParams, 
                                                   PayloadMetadata returnPayloadMetadata)
            throws PersistenceException, UnsupportedMediaTypeExcpetion, DataTooLargeException, SIFException
    {
        logger.debug("retrieveDataFromPhase for Functional Service "+getMultiObjectClassInfo().getObjectName()+" and jobID = "+phaseInfo.getJobID()+" and phase "+phaseInfo.getPhaseName()+" called.");
        logger.debug("Requested Return Mime Type & Schema: "+returnPayloadMetadata+"\nPaging Info: "+pagingInfo);
        
        // Generally the implementation would check here what needs to be returned for the given phase.
        // General flow would be:
        // 1) Check the phase for the given job and based on this...
        // 2) Query the provider's data store for data.
        // 3) Marshal the result into String with the format indicated by the 'returnMimeType'.
        //    Suggestion: Use the MarshallFactory class of this framework.
        // 4) If all is ok then return the resulting String. If anything fails raise appropriate exception.
        //    Note: Null can be returned which indicates no or no further data available for this phase. This will be 
        //          translated into an appropriate value being returned to the consumer (SIF Specification states that 
        //          this will return a HTTP Status 204 (NO_CONTENT).)
        //
        // As part of this call the provider may also update the job and/or phase state using appropriate updateXYZ() methods
        // of the BaseFunctionalServiceProvider class.
        
        // For testing an illustration purpose we create the odd error...
        if ((pagingInfo == null) || (pagingInfo.getPageSize() > 100))
        {
            throw new DataTooLargeException("Pagin Info invalid.","Paging info not provided or page size is larger than 100.", "Provider ("+getProviderName()+")");
        }
        
        if (phaseInfo.getPhaseName().equalsIgnoreCase("oldYearEnrolment"))
        {
            if (!returnPayloadMetadata.getMimeType().isCompatible(MediaType.APPLICATION_XML_TYPE))
            {
                throw new UnsupportedMediaTypeExcpetion(returnPayloadMetadata.getMimeType().toString()+" is not accepted. Valid mime type is "+ MediaType.APPLICATION_XML_TYPE.toString());
            }
            
            //make up some dummy data..
            PhaseDataResponse response = new PhaseDataResponse();
            response.setPayloadMetadata(returnPayloadMetadata);
            response.setData( 
            "<EnrolmentData xmlns=\"http://www.au/enrolmentUseCase/3.4.2\">\n"+
            "  <schoolYear>\n"+
            "    <year>2017</year>\n"+
            "    <school>4001</school>\n"+
            "  </schoolYear>\n"+
            "  <schoolYear>\n"+
            "    <year>2018</year>\n"+
            "    <school>4002</school>\n"+
            "  </schoolYear>\n"+
            "</EnrolmentData>");
            
            return response;
        }
        
        // For all other phase we return nothing....
        return null;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#createDataInPhase(java.lang.String, java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, boolean, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public PhaseDataResponse createDataInPhase(PhaseInfo phaseInfo, 
                                               PhaseDataRequest phaseData, 
                                               boolean useAdvisory, 
                                               SIFZone zone, 
                                               SIFContext context, 
                                               RequestMetadata metadata, 
                                               ResponseParameters customResponseParams, 
                                               PayloadMetadata returnPayloadMetadata)
            throws PersistenceException, UnsupportedMediaTypeExcpetion, SIFException
    {
        logger.debug("createDataInPhase for Functional Service "+getMultiObjectClassInfo().getObjectName()+" and jobID = "+phaseInfo.getJobID()+" and phase "+phaseInfo.getPhaseName()+" called.");
        logger.debug("\nData:\n"+phaseData.getData()+"\nUse Advisory: "+useAdvisory+"\nPayload Metadata: "+phaseData.getPayloadMetadata());
        logger.debug("Requested Return Mime Type & Schema: "+returnPayloadMetadata);

        // Generally the implementation would check here what needs to be done to create data for the given phase.
        // General flow would be:
        // 1) Check the phase for the given job and based on this...
        // 2) Unmarshal the 'phaseData.data'. The 'phaseData.mimeType' indicates the mime type of the 'data'.
        //    Suggestion: Use the UnmarshallFactory class of this framework. Note it is entirely valid that there is
        //                no data given. It is up to the binding documentation and the agreed contract between consumer
        //                and provider if data is passed to this method.
        // 3) If all is ok then process the data as requested. If anything fails raise appropriate exception.
        // 4) If data shall be returned then create appropriate PhaseDataResponse with the data marshalled to a String using
        //    the returnMimeType.
        //
        // It is suggested that the implementation of this method may use asynchronous processing, meaning that
        // The request might be put on an internal queue or stored in DB for later processing. This is important
        // as it is expected that this method finishes execution in a timely manner so that a connection to the
        // caller doesn't remain open for a long time.
        //
        // As part of this call the provider may also update the job and/or phase state using appropriate updateXYZ() methods
        // of the BaseFunctionalServiceProvider class.
        
        // For testing purposes we fail every 5th operation...
        if ((numOperations++ % 5) == 2)
        {
            throw new SIFException(Status.BAD_REQUEST.getStatusCode(), "Failed to create data.", "Data could not be created in phase "+phaseInfo.getPhaseName()+" for job with ID = "+phaseInfo.getJobID(), getMultiObjectClassInfo().getObjectName()+" Provider.");
        }
        
        if (phaseInfo.getPhaseName().equalsIgnoreCase("oldYearEnrolment"))
        {
            if (!returnPayloadMetadata.getMimeType().isCompatible(MediaType.APPLICATION_XML_TYPE))
            {
                throw new UnsupportedMediaTypeExcpetion(returnPayloadMetadata.getMimeType().toString()+" is not accepted. Valid mime type is "+ MediaType.APPLICATION_XML_TYPE.toString());
            }
            
            //make up some dummy data..
            PhaseDataResponse response = new PhaseDataResponse();
            response.setPayloadMetadata(returnPayloadMetadata);
            response.setStatus(Status.OK); // This is an override of the default of 201!
            response.setData( 
            "<EnrolmentData xmlns=\"http://www.au/enrolmentUseCase/3.4.2\">\n"+
            "  <schoolYear>\n"+
            "    <year>2017</year>\n"+
            "    <school>4001</school>\n"+
            "    <status>201</status>\n"+
            "  </schoolYear>\n"+
            "  <schoolYear>\n"+
            "    <year>2018</year>\n"+
            "    <school>4002</school>\n"+
            "    <status>406</status>\n"+
            "    <error>Invalid data for this year and school.</error>\n"+
            "  </schoolYear>\n"+
            "</EnrolmentData>");
            
            return response;
        }
        
        // For all other phases we return null. This should default the response status to 201.
        return null;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#updateDataInPhase(java.lang.String, java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public PhaseDataResponse updateDataInPhase(PhaseInfo phaseInfo, 
                                               PhaseDataRequest phaseData, 
                                               SIFZone zone, 
                                               SIFContext context, 
                                               RequestMetadata metadata, 
                                               ResponseParameters customResponseParams, 
                                               PayloadMetadata returnPayloadMetadata)
            throws PersistenceException, UnsupportedMediaTypeExcpetion, SIFException
    {
        logger.debug("updateDataInPhase for Functional Service "+getMultiObjectClassInfo().getObjectName()+" and jobID = "+phaseInfo.getJobID()+" and phase "+phaseInfo.getPhaseName()+" called.");
        logger.debug("\nData:\n"+phaseData.getData()+"\nPayload Metadata: "+phaseData.getPayloadMetadata());
        logger.debug("Requested Return Mime Type & Schema: "+returnPayloadMetadata);

        // Generally the implementation would check here what needs to be done to update data for the given phase.
        // General flow would be:
        // 1) Check the phase for the given job and based on this...
        // 2) Unmarshal the 'phaseData.data'. The 'phaseData.mimeType' indicates the mime type of the 'data'.
        //    Suggestion: Use the UnmarshallFactory class of this framework. Note it is entirely valid that there is
        //                no data given. It is up to the binding documentation and the agreed contract between consumer
        //                and provider if data is passed to this method.
        // 3) If all is ok then process the data as requested. If anything fails raise appropriate exception.
        // 4) If data shall be returned then create appropriate PhaseDataResponse with the data marshalled to a String using
        //    the returnMimeType.
        //
        // It is suggested that the implementation of this method may use asynchronous processing, meaning that
        // The request might be put on an internal queue or stored in DB for later processing. This is important
        // as it is expected that this method finishes execution in a timely manner so that a connection to the
        // caller doesn't remain open for a long time.
        //
        // As part of this call the provider may also update the job and/or phase state using appropriate updateXYZ() methods
        // of the BaseFunctionalServiceProvider class.

        // For testing purposes we fail every 4th operation...
        if ((numOperations++ % 4) == 0)
        {
            throw new SIFException(Status.BAD_REQUEST.getStatusCode(), "Failed to update data.", "Data could not be updated in phase "+phaseInfo.getPhaseName()+" for job with ID = "+phaseInfo.getJobID(), getMultiObjectClassInfo().getObjectName()+" Provider.");
        }
        
        // For the time being we return null. This should default the response status to 200.
        return null;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#deleteDataInPhase(java.lang.String, java.lang.String, java.lang.String, javax.ws.rs.core.MediaType, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public PhaseDataResponse deleteDataInPhase(PhaseInfo phaseInfo, 
                                               PhaseDataRequest phaseData, 
                                               SIFZone zone, 
                                               SIFContext context, 
                                               RequestMetadata metadata, 
                                               ResponseParameters customResponseParams, 
                                               PayloadMetadata returnPayloadMetadata)
            throws PersistenceException, UnsupportedMediaTypeExcpetion, SIFException
    {
        logger.debug("deleteDataInPhase for Functional Service "+getMultiObjectClassInfo().getObjectName()+" and jobID = "+phaseInfo.getJobID()+" and phase "+phaseInfo.getPhaseName()+" called.");
        logger.debug("\nData:\n"+phaseData.getData()+"\nPayload Metadata: "+phaseData.getPayloadMetadata());
        logger.debug("Requested Return Mime Type & Schema: "+returnPayloadMetadata);

        // Generally the implementation would check here what needs to be done to delete data for the given phase.
        // General flow would be:
        // 1) Check the phase for the given job and based on this...
        // 2) Unmarshal the 'phaseData.data'. The 'phaseData.mimeType' indicates the mime type of the 'data'.
        //    Suggestion: Use the UnmarshallFactory class of this framework. Note it is entirely valid that there is
        //                no data given. It is up to the binding documentation and the agreed contract between consumer
        //                and provider if data is passed to this method.
        // 3) If all is ok then process the data as requested. If anything fails raise appropriate exception.
        // 4) If data shall be returned then create appropriate PhaseDataResponse with the data marshalled to a String using
        //    the returnMimeType.
        //
        // It is suggested that the implementation of this method may use asynchronous processing, meaning that
        // The request might be put on an internal queue or stored in DB for later processing. This is important
        // as it is expected that this method finishes execution in a timely manner so that a connection to the
        // caller doesn't remain open for a long time.
        //
        // As part of this call the provider may also update the job and/or phase state using appropriate updateXYZ() methods
        // of the BaseFunctionalServiceProvider class.

        if ((numOperations++ % 4) == 0)
        {
            throw new SIFException(Status.BAD_REQUEST.getStatusCode(), "Failed to delete data.", "Data could not be deleted in phase "+phaseInfo.getPhaseName()+" for job with ID = "+phaseInfo.getJobID(), getMultiObjectClassInfo().getObjectName()+" Provider.");
        }
        
        // For the time being we return null. This should default the response status to 204.
        return null;
    }

    /*----------------------------*/
    /*-- Phase State Operations --*/
    /*----------------------------*/
    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.FunctionalServiceProvider#phaseStateUpdatedByConsumer(java.lang.String, java.lang.String, sif3.common.CommonConstants.PhaseState, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters)
     */
    @Override
    public void phaseStateUpdatedByConsumer(PhaseInfo phaseInfo, 
                                            PhaseState newState, 
                                            SIFZone zone, 
                                            SIFContext context, 
                                            RequestMetadata metadata, 
                                            ResponseParameters customResponseParams) 
            throws PersistenceException, SIFException
    {
        logger.debug("Phase State Updated called by Consumer Job for Functional Service "+getMultiObjectClassInfo().getObjectName()+" and jobID = '"+phaseInfo.getJobID()+"'. Phase to update is "+phaseInfo.getPhaseName()+". New State is "+newState.name());

        // Generally the implementation would check here what needs to be done after such an update.
        // This could cause many actions to be triggered. Some include the various updateXYZ() methods that are available as part
        // of this provider class.
        
        // Below is just a potential example.
        if (phaseInfo.getPhaseName().equalsIgnoreCase("oldYearEnrolment"))
        {
            if (newState == PhaseState.PENDING)
            {
                updateJobStateAndPhaseState(phaseInfo.getJobID(), JobState.INPROGRESS, "oldYearEnrolment", PhaseState.INPROGRESS);
            }

            if (newState == PhaseState.COMPLETED)
            {
                // First phase is done. Mark the next phase as in progress.
                updatePhaseState(phaseInfo.getJobID(), "newYearEnrolment", PhaseState.INPROGRESS);
            }
        }
        else if ((phaseInfo.getPhaseName().equalsIgnoreCase("newYearEnrolment")))
        {
            if (newState == PhaseState.COMPLETED)
            {
                // Mark the job as complete
                updateJobState(phaseInfo.getJobID(), JobState.COMPLETED);
            }
            if (newState == PhaseState.FAILED)
            {
                // Mark the job as complete
                updateJobState(phaseInfo.getJobID(), JobState.FAILED);
            }
        }
    }
    
    /*--------------------------------------------*/
    /*-- Other required housekeeping Operations --*/
    /*--------------------------------------------*/
    
    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
     */
    @Override
    public ModelObjectInfo getMultiObjectClassInfo()
    {
        //This must be the same name as used in the URL or as listed in the SIF3_JOB_TEMPLATE.JOB_URL_NAME column!
        return new ModelObjectInfo("RolloverStudents", null);
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.CoreProvider#shutdown()
     */
    @Override
    public void shutdown()
    {
        logger.debug("Shutdown Functional Service for " + getPrettyName()); 
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.CoreProvider#getCustomServiceInfo(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     */
    @Override
    public HeaderProperties getCustomServiceInfo(SIFZone zone, 
                                                 SIFContext context, 
                                                 PagingInfo pagingInfo, 
                                                 RequestMetadata metadata) 
            throws PersistenceException, UnsupportedQueryException, DataTooLargeException
    {
        //We can return some specific headers. Most of the time that is really nothing but there might be times where some are required.
        //For pure illustration purpose we return some values.
        
        HeaderProperties someHeaders = new HeaderProperties();
        someHeaders.setHeaderProperty("DemoJobProvider", getMultiObjectClassInfo().getObjectName());
        return someHeaders;
    }

    /* 
     * Example how a specific provider can overwrite the valid job end states for a functional service.
     */
    @Override
    public boolean isJobEndState(JobState state)
    {
        // In this case we only consider the "COMPLETED" as an end state. By default COMPLETED and FAILED are end states or
        // values in the provider properties called job.endStates are end states.
        return state == JobState.COMPLETED;
    }
}
