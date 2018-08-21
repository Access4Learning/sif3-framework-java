/*
 * BaseEventProvider.java
 * Created: 19/03/2014
 *
 * Copyright 2014 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package sif3.infra.rest.provider;

import java.util.List;

import javax.ws.rs.core.MediaType;

import sif3.common.CommonConstants;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.interfaces.EventProvider;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.ACL.AccessRight;
import sif3.common.model.ACL.AccessType;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFEvent;
import sif3.common.model.SIFZone;
import sif3.common.model.ServiceInfo;
import sif3.common.model.ZoneContextInfo;
import sif3.common.persist.model.SIF3Session;
import sif3.common.ws.BaseResponse;
import sif3.infra.common.env.mgr.BrokeredProviderEnvironmentManager;
import sif3.infra.common.env.mgr.ProviderManagerFactory;
import sif3.infra.common.interfaces.EnvironmentManager;
import sif3.infra.rest.client.EventClient;


/**
 * This is the main class each specific provider of a given SIF Object type must extends if it wants to publish events. With this class it
 * enforces implementation of all CRUD methods of the BaseProvider and additionally the methods for publishing events. The BaseEventProvider
 * will take care of all housekeeping things relating to the actual event publishing. Please refer to the Developer's Guide for some more
 * details about this class.
 * 
 * @author Joerg Huber
 *
 */
public abstract class BaseEventProvider<L> extends BaseProvider implements EventProvider<L>
{	
	/**
	 */
    public BaseEventProvider()
    {
	    super();
    }

    /**
     * Attempts to read the max objects per event value from the adapter property file. If no value is found it will use a default of 10.
     * If that behaviour needs to be changed for a particular provider then this method should be overridden by the particular provider
     * implementation.
     *  
     * @return See desc.
     */
    public int getMaxObjectsInEvent()
    {
    	return getServiceProperties().getPropertyAsInt(CommonConstants.EVENT_MAX_OBJ, getProviderName(), 10);
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


	/*------------------------------------------------------------------------------------------------------------------------
	 * End of 'Dynamic' HTTP Header Field override section
	 *-----------------------------------------------------------------------------------------------------------------------*/ 

    
    /**
     * This method retrieves all events to be published by calling the abstract method getSIFEvents(). The returned list
     * is then broadcasted to all zones known to the implementing agent.
     * 
     * @see #getSIFEvents
     */
    public void broadcastEvents()
    {
    	logger.debug("================================ broadcastEvents() called for provider "+getPrettyName());
		int totalRecords = 0;
		int failedRecords = 0;
		int maxNumObjPerEvent = getMaxObjectsInEvent();
		
		SIF3Session sif3Session = getActiveSession();
		if (sif3Session == null)
		{
			return; //cannot send events. Error already logged.
		}
		
		List<ServiceInfo> servicesForProvider = getServicesForProvider(sif3Session);
		
		// If there are no services for this provider defined then we don't need to get any events at all.
		if ((servicesForProvider == null) || (servicesForProvider.size() == 0))
		{
			logger.info("This emvironment does not have any zones and contexts defined for the "+getMultiObjectClassInfo().getObjectName() + " service. No events can be sent.");
			return;
		}		
		try
		{
			// Let's get the Event Client
		    String serviceName = getServiceName();
			EventClient evtClient = new EventClient(getEnvironmentManager(), getRequestMediaType(), getResponseMediaType(), serviceName, getMarshaller(), getCompressionEnabled());
			SIFEventIterator<L> iterator = getSIFEvents();
			if (iterator != null)
			{
				while (iterator.hasNext())
				{
					SIFEvent<L> sifEvents = null;
					try
					{
						sifEvents = iterator.getNextEvents(maxNumObjPerEvent);
						// This should not return null since the hasNext() returned true, but just in case we check and exit the loop if it should return null. 
						// In this case we assume that there is no more data. We also log an error to make the coder aware of the issue.
						if (sifEvents != null)
						{
							logger.debug("Number of "+serviceName+" Objects in this Event: " + sifEvents.getListSize());
							
                            // keep event action. Just in case the developer changes it in modifyBeforePublishing() which would confuse everything.
                            EventAction eventAction = sifEvents.getEventAction();
                            
                            // Do we need to sent to all services or to a limited list of zone & context?
							if ((sifEvents.getLimitToZoneCtxList() == null) || (sifEvents.getLimitToZoneCtxList().size() == 0))
							{
	                            for (ServiceInfo service : servicesForProvider)
	                            {
	                                // Check if provider has rights to publish to given zone and context
	                                if (hasAccess(service))
	                                {
	                                    failedRecords = failedRecords + prepareEventAndSend(evtClient, sifEvents, service.getZone(), service.getContext(), eventAction);
	                                }
	                                else
	                                {
                                        logNoAccessRight(service.getZone(), service.getContext());
	                                }
	                            }							    
							}
							else // only sent to limited list
							{
							    for (ZoneContextInfo zoneCtxInfo : sifEvents.getLimitToZoneCtxList())
							    {
							        // Check if provider has rights to publish to given zone and context
							        if (sif3Session.hasAccess(AccessRight.PROVIDE, AccessType.APPROVED, serviceName, null, zoneCtxInfo.getZone(), zoneCtxInfo.getContext()))
						            {
                                        failedRecords = failedRecords + prepareEventAndSend(evtClient, sifEvents, zoneCtxInfo.getZone(), zoneCtxInfo.getContext(), eventAction);							            
						            }
							        else
                                    {
                                        logNoAccessRight(zoneCtxInfo.getZone(), zoneCtxInfo.getContext());
                                    }
							    }
							}
							
				            totalRecords = totalRecords + sifEvents.getListSize();
						}
						else
						{
							logger.error("iterator.hasNext() has returned true but iterator.getNextEvent() has retrurned null => no further events are broadcasted.");
							break;
						}
					}
					catch (Exception ex)
					{
						logger.error("Failed to retrieve next event for provider "+getPrettyName()+": "+ex.getMessage(), ex);					
						failedRecords = failedRecords + ((sifEvents != null) ? sifEvents.getListSize() : 0);
					}
				}
				iterator.releaseResources();
			}
			else
			{
				logger.info("getSIFEvents() for provider "+getPrettyName()+" returned null. Currently no events to be sent.");
			}
		}
		catch (Exception ex)
		{
			logger.error("Failed to retrieve events for provider "+getPrettyName()+": "+ex.getMessage(), ex);								
		}
		logger.info("Total SIF Event Objects broadcasted: "+totalRecords);
		logger.info("Total SIF Event Objects failed     : "+failedRecords);
    	logger.debug("================================ Finished broadcastEvents() for provider "+getPrettyName());
    }

    /*
     * Return the number of failed records. If 0 is return all then the event with its content is sent successfully.
     */
    protected int prepareEventAndSend(EventClient evtClient, SIFEvent<L> sifEvents, SIFZone zone, SIFContext context, EventAction eventAction)
    {
        int failedRecords = 0;
        
        HeaderProperties customHTTPHeaders = new HeaderProperties();
        SIFEvent<L> modifiedEvents = modifyBeforePublishing(sifEvents, zone, context, customHTTPHeaders);
        if (modifiedEvents != null)
        {
            //Just in case the developer has changed it. Should not be allowed :-)
            modifiedEvents.setEventAction(eventAction);
            
            if (!sendEvents(evtClient, modifiedEvents, zone, context, customHTTPHeaders))
            {
                //Report back to the caller. This should also give the event back to the caller.
                onEventError(modifiedEvents, zone, context);
                failedRecords = ((modifiedEvents != null) ? modifiedEvents.getListSize() : 0);
            }
        }
        
        return failedRecords;
    }
    
    private void logNoAccessRight(SIFZone zone, SIFContext context)
    {
        String zoneID = (zone == null) ? "Default" : zone.getId();
        String contextID = (context == null) ? CommonConstants.DEFAULT_CONTEXT_NAME : context.getId();
        
        logger.debug("The "+getProviderName()+" does not have the ACL entry PROVIDE = APPROVED for service = " + getServiceName() + " for the Zone = "+ zoneID + " and the Context = " + contextID + ". Events are NOT sent to this zone and context.");
//        return ((sifEvents != null) ? sifEvents.getListSize() : 0);
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
    protected boolean sendEvents(EventClient evtClient, SIFEvent<?> sifEvents, SIFZone zone, SIFContext context, HeaderProperties customHTTPHeaders)
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
    		
    		BaseResponse response = evtClient.sendEvents(sifEvents, zone, context, customHTTPHeaders);
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
    
    
    protected void addSIF3OverrideHeaderProperties(HeaderProperties hdrProps)
    {
    	//HeaderProperties hdrProps = new HeaderProperties();
    	hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_GENERATOR_ID, getGeneratorID());
    	hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_APPLICATION_KEY, getApplicationKey());
    	hdrProps.setHeaderProperty(RequestHeaderConstants.HDR_AUTHENTICATED_USER, getAuthentictedUser());

    	//return hdrProps;
    }
    
    
    private BrokeredProviderEnvironmentManager getEnvironmentManager()
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
    
    
    private List<ServiceInfo> getServicesForProvider(SIF3Session sif3Session)
    {
    	if (sif3Session != null)
    	{
    		return sif3Session.getServiceInfoForService(getServiceName(), ServiceType.OBJECT);
    	}
    	
    	return null;
    }
    
    private SIF3Session getActiveSession()
    {
        BrokeredProviderEnvironmentManager envMgr = getEnvironmentManager();
        if (envMgr != null)
        {
            return envMgr.getSIF3Session();
        }

        return null; // Error already logged.
    }
    
    private boolean hasAccess(ServiceInfo service)
    {
    	// All that is required is PROVIDE right to be set to APPROVED.
    	return service.getRights().hasRight(AccessRight.PROVIDE, AccessType.APPROVED);
    }
}
