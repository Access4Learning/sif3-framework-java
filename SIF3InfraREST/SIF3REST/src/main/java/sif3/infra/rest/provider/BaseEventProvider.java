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

import sif3.common.CommonConstants;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.header.HeaderValues.ServiceType;
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
import sif3.infra.common.env.mgr.BrokeredProviderEnvironmentManager;
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
		
		BrokeredProviderEnvironmentManager envMgr = getBrokeredEnvironmentManager();
		if (envMgr == null) // not a brokered environment. Cannot sent events!
		{
		    return; // error already logged.
		}
		
		SIF3Session sif3Session = envMgr.getSIF3Session();
		List<ServiceInfo> servicesForProvider = getServicesForProvider(sif3Session, ServiceType.OBJECT);
		
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
			EventClient evtClient = new EventClient(envMgr, getRequestMediaType(), getResponseMediaType(), serviceName, getMarshaller(), getCompressionEnabled());
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
	                                if (hasAccess(service, AccessRight.PROVIDE, AccessType.APPROVED))
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
            
            if (!sendEvents(evtClient, modifiedEvents, zone, context, ServiceType.OBJECT, customHTTPHeaders))
            {
                //Report back to the caller. This should also give the event back to the caller.
                onEventError(modifiedEvents, zone, context);
                failedRecords = ((modifiedEvents != null) ? modifiedEvents.getListSize() : 0);
            }
        }
        
        return failedRecords;
    }
}
