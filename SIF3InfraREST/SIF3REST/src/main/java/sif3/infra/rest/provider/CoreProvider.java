/*
 * CoreProvider.java
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

package sif3.infra.rest.provider;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.AdvancedProperties;
import sif3.common.CommonConstants;
import sif3.common.CommonConstants.AdapterType;
import sif3.common.conversion.ModelObjectInfo;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIFException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.interfaces.EventProvider;
import sif3.common.model.ACL.AccessRight;
import sif3.common.model.ACL.AccessType;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.RequestParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.common.ws.BaseResponse;
import sif3.infra.common.env.mgr.BrokeredProviderEnvironmentManager;
import sif3.infra.common.env.mgr.ProviderManagerFactory;
import sif3.infra.common.env.types.EnvironmentInfo.EnvironmentType;
import sif3.infra.common.env.types.ProviderEnvironment;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.common.interfaces.ProviderJobManager;
import sif3.infra.rest.client.EventClient;

/**
 * This abstract class forms the basis of all provider style classes. All provider classes must extend this class and then add
 * appropriate interfaces or abstract methods to is as required.
 * 
 * @author Joerg Huber
 *
 */
public abstract class CoreProvider implements Runnable
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /* properties used for event threads */
    private Timer eventTimer = null;
    private TimerTask eventTimerTask = null;

    /**
     * Shuts down the sub-class provider. This should release all associated resources with that provider.
     */
    public abstract void finaliseSubClass();
    
    /**
     * This method behave like the getServiceInfo() method of the Provider Interface. Please refer to description in that class:<br/><br/>
     * 
     * @see sif3.common.interfaces.Provider#getServiceInfo(sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata)
     * 
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the objects shall be returned. Can be Null (default Zone)
     * @param pagingInfo Page information to determine which results to return. Null = Return all (NOT RECOMMENDED!).
     * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * 
     * @return Header Properties that shall be returned as HTTP Headers to the caller. Note this can be null or any number of
     *         custom HTTP headers. Generally one would expect that to be null.
     * 
     * @throws UnsupportedQueryException The query provided with this request is not supported (NOT YET IMPLEMENTED FUNCTIONALITY)
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
     * @throws DataTooLargeException If the data that shall be returned is too large due to the values in the paging info.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
     */
    public abstract HeaderProperties getCustomServiceInfo(SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException, SIFException;
    
    /**
     * This method returns the actual service name (NOT serviceID). The service name is the name that is used in URL paths to identify the
     * SIF Object that we are dealing with. The service name is the plural form of the SIF Object name as specified in the SIF3 Spec.
     *  
     * @return See Desc.
     */
    public abstract String getServiceName();
    
    /**
     * Returns the information for the 'collection-style object'. The returned object holds the name of a 'collection-style object' 
     * (i.e StudentPersonals) and the physical class this maps to for the data model supported for this implementation.
     * 
     * @return See Desc.
     */
    public abstract ModelObjectInfo getCollectionObjectClassInfo();
    
    /**
     * Returns the frequency in seconds at which events should be published.
     * 
     * @return See desc.
     */
    public abstract int getEventFrequency();

    /**
     * Returns the delay in seconds at which various event threads shall be started.
     * 
     * @return See desc.
     */
    public abstract int getEventDelay();
    
    /**
     * Returns the particular event provider class.
     * 
     * @return See desc.
     */
    public abstract EventProvider<?> getEventProvider();


    /**
     */
    public CoreProvider()
    {
        super();
    }
    
    /**
     * Returns the Provider Configuration in a nice form. Should rarely be required by the concrete implementation of a provider class.
     * 
     * @return See desc.
     */
    public ProviderEnvironment getProviderEnvironment()
    {
      return (ProviderEnvironment)ProviderManagerFactory.getEnvironmentManager().getEnvironmentInfo();
    }
    
    /**
     * Returns the Job Provider Manger for this provider.
     * 
     * @return See desc.
     */
    public ProviderJobManager getJobManager()
    {
        return (ProviderJobManager)ProviderManagerFactory.getEnvironmentManager().getJobManager();
    }

    /**
     * Utility method to easily retrieve the property file content for a provider.
     * 
     * @return See desc
     */
    public AdvancedProperties getServiceProperties()
    {
      return ProviderManagerFactory.getEnvironmentManager().getServiceProperties();
    }
    
    /**
     * Utility method to easily retrieve the property file content for a provider.
     * 
     * @return See desc
     */
    public AdapterType getAdapterType()
    {
      return ProviderManagerFactory.getEnvironmentManager().getAdapterType();
    }

    /**
     * Utility method. Mainly used for useful logging messages.
     * 
     * @return Returns the Service Id concatenated with the Provider Name.
     */
    public String getPrettyName()
    {
      return getProviderEnvironment().getAdapterName()+" - " + getProviderName();
    }

    /**
     * Utility method. Returns the class name of the provider.
     * 
     * @return Returns the Provider Class Name.
     */
    public String getProviderName()
    {
        return getClass().getSimpleName();
    }
    
    /**
     * This method will retrieve the value of the "where" URL Query Parameter. This parameters is a special parameter supported
     * by the SIF 3.x specification. It allows consumers to provide a "where" clause when retrieving data from a SIF end-point.
     * If the "where" parameter is not provided by the consumer then null is returned. It is important to not that the
     * "where" parameter will use xPath notation and can be quite complex. It is up to the provider implementation to process that
     * xPath with its own toolset. This method simply returns the value of the URL query parameter as a string. Further the value of
     * the parameter might be urlEndcoded. The urlDecode parameter indicates if the value shall be returned in its raw form or if it
     * shall be URL decoded before it is returned. If the value fails to URL decode due to an invalid format then the raw value
     * is returned and an error will be logged.
     * 
     * @param requestParams The framework will have this value as part of each request. Generally this is part of the 
     *                       RequestMetadata parameter of each provider method.
     *                       
     * @param urlDecode TRUE: The value of the "where" URL query parameter will be decoded before returned. FALSE: value will remain
     *                  unaltered (e.g. will be returned in its raw format as received).                     
     * 
     * @return See desc.
     */
    public String getWhereClause(RequestParameters requestParams, boolean urlDecode)
    {
        return getURLQueryParam(requestParams, CommonConstants.WHERE_CALUSE, urlDecode);
    }
    
    /**
     * This method will retrieve the value of the "order" URL Query Parameter. This parameters is a special parameter supported
     * by the SIF 3.x specification. It allows consumers to provide a "order" clause when retrieving data from a SIF end-point.
     * If the "order" parameter is not provided by the consumer then null is returned. It is important to not that the
     * "order" parameter will use xPath notation and can be quite complex. It is up to the provider implementation to process that
     * xPath with its own toolset. This method simply returns the value of the URL query parameter as a string. Further the value of
     * the parameter might be urlEndcoded. The urlDecode parameter indicates if the value shall be returned in its raw form or if it
     * shall be URL decoded before it is returned. If the value fails to URL decode due to an invalid format then the raw value
     * is returned and an error will be logged.
     * 
     * @param requestParams The framework will have this value as part of each request. Generally this is part of the 
     *                       RequestMetadata parameter of each provider method.
     *                       
     * @param urlDecode TRUE: The value of the "order" URL query parameter will be decoded before returned. FALSE: value will remain
     *                  unaltered (e.g. will be returned in its raw format as received).                     
     * 
     * @return See desc.
     */
    public String getOrderClause(RequestParameters requestParams, boolean urlDecode)
    {
        return getURLQueryParam(requestParams, CommonConstants.ORDER_CALUSE, urlDecode);        
    }
    
    /*------------------------------------------------------------------------------------------------------------------------
     * Start of 'Dynamic' HTTP Header Field override section for Events
     * 
     * The following set of methods are used for a more configurable way how some HTTP header parameters are set.
     * By default the following HTTP Header fields are retrieved from the provider's property file and put in corresponding
     * HTTP Header Fields of each event:
     * 
     * Property                      HTTP Header
     * ----------------------------------------------------------------
     * adapter.generator.id          generatorId
     * env.application.key           applicationKey
     * env.userToken                 authenticatedUser
     * env.mediaType                 Content-Type, Accept
     * adapter.compression.enabled   Content-Encoding, Accept-Encoding
     * 
     * Only properties that are not null or empty string will be set in the corresponding HTTP Header.
     *
     * There are situations where and application may need a more 'dynamic' behaviour where the above values are determined
     * at runtime, based on other circumstances and therefore these properties must be retrieved from an other source than the
     * providers's property file. In such a case the methods below can be overwritten to make them dynamic and controlled by
     * the implementation rather than driven by the provider's property file. If any of the methods below is overwritten then
     * the value of the over riding method is set in the corresponding HTTP Header field if the return value of the method 
     * is not null or an empty string.
     *------------------------------------------------------------------------------------------------------------------------*/
    
    /**
     * This method returns the value of the adapter.generator.id property from the provider's property file. If that
     * needs to be overridden by a specific implementation then the specific sub-class should override this method.
     * 
     * @return The adapter.generator.id property from the provider's property file
     */
    public String getGeneratorID()
    {
        return getProviderEnvironment().getGeneratorID();
    }
    
    /**
     * This method returns the value of the env.application.key property from the provider's property file. If that
     * needs to be overridden by a specific implementation then the specific sub-class should override this method.
     * 
     * @return The env.application.key property from the provider's property file
     */
    public String getApplicationKey()
    {
        return getProviderEnvironment().getEnvironmentKey().getApplicationKey();
    }

    /**
     * This method returns the value of the env.userToken property from the provider's property file. If that
     * needs to be overridden by a specific implementation then the specific sub-class should override this method.
     * 
     * @return The env.userToken property from the provider's property file
     */
    public String getAuthentictedUser()
    {
        return getProviderEnvironment().getEnvironmentKey().getUserToken();
    }
    
    /**
     * This method returns the value of the env.mediaType property from the provider's property file. If that
     * needs to be overridden by a specific implementation then the specific sub-class should override this method.
     * 
     * @return The env.mediaType property from the provider's property file
     */
    public MediaType getRequestMediaType()
    {
        return getProviderEnvironment().getMediaType();
    }
    
    /**
     * This method returns the value of the env.mediaType property from the provider's property file. If that
     * needs to be overridden by a specific implementation then the specific sub-class should override this method.
     * 
     * @return The env.mediaType property from the provider's property file
     */
    public MediaType getResponseMediaType()
    {
        return getProviderEnvironment().getMediaType();
    }
    
    /**
     * This method returns the value of the adapter.compression.enabled property from the provider's property file. If 
     * that needs to be overridden by a specific implementation then the specific sub-class should override this method.
     * 
     * @return The adapter.compression.enabled property from the provider's property file
     */
    public boolean getCompressionEnabled()
    {
        return getProviderEnvironment().getCompressionEnabled();
    }

    /*
     * The following properties will be added to the hdrProps. It will use the override methods above. Note that
     * the properties related to the media type are set differently and not through the mechanism of this method.
     */
    protected void addSIF3OverrideHeaderProperties(HeaderProperties hdrProps)
    {
        hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_GENERATOR_ID, getGeneratorID());
        hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_APPLICATION_KEY, getApplicationKey());
        hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTHENTICATED_USER, getAuthentictedUser());
    }
    /*------------------------------------------------------------------------------------------------------------------------
     * End of 'Dynamic' HTTP Header Field override section
     *-----------------------------------------------------------------------------------------------------------------------*/ 
    
    public void finaliseCoreProvider()
    {
        logger.debug("Finalise in CoreProvider for "+getClass().getSimpleName() +" called.");
        finaliseEventThreads();
        finaliseSubClass();
    }
    
    /**
     * This method is all that is needed to run the provider in its own thread. The thread is executed at
     * given intervals driven by a property in the adapter's property file. The interval/frequency
     * defined in there is used to determine how often this thread is run.
     */
    @Override
    public final synchronized void run()
    {
        String providerName = getProviderName();
        boolean checkEnvType = getServiceProperties().getPropertyAsBool("provider.check.envType", true);
        
        logger.debug("Start "+providerName+ " thread....");
        
        //Only if environment does support events we will start the event manager
        if (getProviderEnvironment().getEventsSupported())
        {   
            // If this is a DIRECT environment then events are not supported, yet.
            if ((getProviderEnvironment().getEnvironmentType() == EnvironmentType.DIRECT) && checkEnvType)
            {
                logger.info("The DIRECT Provider for this framework does NOT support events, yet.");
            }
            else
            {
                // Check if the provider implements the events interface. Only then events might be required.
                if (EventProvider.class.isAssignableFrom(getClass()))
                {
                    int frequency = getEventFrequency();        
                    if (frequency != CommonConstants.NO_EVENT)
                    {
                        logger.debug("Events supported for this "+providerName+". Start up event thread.");
                        startupEventManager(providerName, frequency, getEventDelay(), getEventProvider());
                    }
                    else
                    {
                        logger.info("Events supported for  "+providerName+" but currently turned off (frequency=0)");
                    }
                }
                else
                {
                    logger.debug("Events NOT supported for "+providerName+". Provider does not implement EventProvider interface.");
                }
            }
        }
        else
        {
            logger.debug("Environment "+getProviderEnvironment().getEnvironmentName()+ " does NOT support events.");            
        }
        logger.debug(providerName+" started.");
    }
    
    /*------------------------------------------------------------------------------*/
    /*-- Some utility methods to be used by this class hierarchy only (protected) --*/
    /*------------------------------------------------------------------------------*/ 
    protected List<ServiceInfo> getServicesForProvider(SIF3Session sif3Session, ServiceType serviceType)
    {
        if (sif3Session != null)
        {
            return sif3Session.getServiceInfoForService(getServiceName(), serviceType);
        }
        
        return null;
    }

    protected BrokeredProviderEnvironmentManager getBrokeredEnvironmentManager()
    {
        EnvironmentManager envMgr = ProviderManagerFactory.getEnvironmentManager();
        if (envMgr != null) // we have a proper setup and are initialised.
        {
            // Note: Currently we only support events for Brokered Environments, so the EnvironmentManager should be of type 
            //       BrokeredProviderEnvironmentManager
            if (envMgr instanceof BrokeredProviderEnvironmentManager)
            {
                return (BrokeredProviderEnvironmentManager)envMgr;
            }
            else
            {
                logger.error("Events are only supported for BROKERED environments. This provider is a DIRECT Environment.");
            }
        }
        else
        {
            logger.error("Environment Manager not initialised. Not connected to an environment. No Environment Manger available.");
        }
        
        // If we get here then we don't have a valid environment manager.
        return null;
    }

    protected boolean hasAccess(ServiceInfo service, AccessRight accessRight, AccessType accessType)
    {
        return service.getRights().hasRight(accessRight, accessType);
    }
    
    /**
     * If one doesn't want certain events to be published to a given zone then this method needs to be 
     * overridden. It allows to test for the event and zone and make the appropriate decision if the event
     * shall be sent.
     * 
     * @param event The event to be published to the zone.
     * @param zone The zone to which the event is published to.
     * @param customHTTPHeaders Custom HTTP Headers to be added to the event. 
     * 
     * @return TRUE: Event sent successfully. FALSE: Failed to send event. Error must be logged.
     */
    protected boolean sendEvents(EventClient evtClient, SIFEvent<?> sifEvents, SIFZone zone, SIFContext context, ServiceType serviceType, HeaderProperties customHTTPHeaders)
    {
        logger.debug(getPrettyName()+" sending a "+getServiceName()+" event with "+sifEvents.getListSize()+" sif objects.");
        try
        {
            if (logger.isDebugEnabled())
            {
                logger.debug("Custom HTTP Headers set by modifyBeforePublishing() method: "+customHTTPHeaders);
            }
            if (customHTTPHeaders == null)
            {
                customHTTPHeaders = new HeaderProperties();
            }
            
            // Add all other HTTP headers to this customHTTPHeader. This ensures that SIF managed HTTP headers will
            // override custom HTTP Headers.
            addSIF3OverrideHeaderProperties(customHTTPHeaders);
            
            BaseResponse response = evtClient.sendEvents(sifEvents, zone, context, serviceType, customHTTPHeaders);
            if (response.hasError())
            {
                logger.error("Failed to send event: "+response.getError());
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (Exception ex)
        {
            logger.error("An error occured sending an event message. See previous error log entries.", ex);
            return false;
        }
    }
    
    protected void logNoAccessRight(SIFZone zone, SIFContext context)
    {
        String zoneID = (zone == null) ? "Default" : zone.getId();
        String contextID = (context == null) ? CommonConstants.DEFAULT_CONTEXT_NAME : context.getId();
        
        logger.debug("The "+getProviderName()+" does not have the ACL entry PROVIDE = APPROVED for service = " + getServiceName() + " for the Zone = "+ zoneID + " and the Context = " + contextID + ". Events are NOT sent to this zone and context.");
    }
    
    protected String getURLQueryParam(RequestParameters requestParams, String paramName, boolean urlDecode)
    {
        String value = null;
        
        if (requestParams != null)
        {
            value = requestParams.getURLQueryParameter(paramName, urlDecode);
        }
        
        return value;
    }

    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/ 
    
    /**
     * This method initialises and schedules the event producer task.
     */
    // TODO: JH - Consider if I should use Executors.newSingleThreadScheduledExecutor style or even Quartz Job
    // task/timers here.
    private void startupEventManager(String providerName, int frequencyInSec, int delayInSec, final EventProvider<?> eventProvider)
    {
        int period = frequencyInSec * CommonConstants.MILISEC; // repeat every so often (multiply with milliseconds).

        logger.info(providerName + ".startupEventManager: Event Frequency = " + frequencyInSec  + " secs; Event Startup Delay = " + delayInSec + " secs.");
        if (eventTimerTask == null) // not created started
        {
            eventTimerTask = new TimerTask()
            {
                public void run()
                {
                    logger.debug("Start Event Timer Task for " + getServiceName() + ".");
                    eventProvider.broadcastEvents();
                }
            };

            // Now start scheduling events
            logger.debug("Start sending " + getServiceName() + " events... (Total running threads = " + Thread.activeCount() + ")");
            eventTimer = new Timer(true);
            eventTimer.scheduleAtFixedRate(eventTimerTask, delayInSec * CommonConstants.MILISEC, period);
        }
    }
    
    private void finaliseEventThreads()
    {
        // Shut down event timer & task - thread
        if (eventTimerTask != null)
        {
            logger.debug("Shut Down event task for: "+getProviderName());
            eventTimerTask.cancel();
            eventTimerTask = null;
        }
        if (eventTimer != null)
        {
            logger.debug("Shut Down event timer for: "+getProviderName());
            eventTimer.cancel();
            eventTimer.purge();
            eventTimer = null;
        }
    }
}
