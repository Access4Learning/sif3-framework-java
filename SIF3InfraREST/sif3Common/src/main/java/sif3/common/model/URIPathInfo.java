/*
 * URIPathInfo.java Created: 1 Dec 2015
 * 
 * Copyright 2015-2018 Systemic Pty Ltd
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

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants;
import sif3.common.header.HeaderValues.ServiceType;

/**
 * This class is not only a POJO but also a small helper class to transform a relativeURLPath into its components.
 * The components typically are the service name (object or servicePath), the zone and context (from matrix parameters)
 * and some metadata about the url. There is no real simple url processor class in java, so this helper class was 
 * developed to take care of some SIF specifics in processing a relative url. This class assumes that the relative URL
 * does not contain the baseURL of a service nor the protocol. A couple typical URLs are:<br/><br/>
 * 
 * Object Service: /SchoolInfos/1234;zoneId=ABC<br/>
 * Service Path: /SchoolInfos/1234-1245/StudentPersonal.xml;zoneId=ABC;contextId=DEFAULT<br/><br/>
 * Job Object: /StudentRollover;zoneId=ABC;contextId=DEFUALT<br/><br/>
 * Job Phase: /StudentRollover/<jobId>/<phaseName>;zoneId=ABC;contextId=DEFUALT<br/><br/>
 * 
 * @author Joerg Huber
 *
 */
public class URIPathInfo implements Serializable
{
    private static final long serialVersionUID  = -4769833196778943230L;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private String originalURLString = null;
    private String serviceName       = null;  // Service Path => school/{}/students; Job & Object service it will be first segment
    private String phaseName         = null;  // Only applicable for Functional Services.
    private ServiceType serviceType  = null;
    private String urlService        = null;  // Service Path => school/1224/students
    private String resourceID        = null;  // refId of object or job
    private SIFZone zone             = null;
    private SIFContext context       = CommonConstants.DEFAULT_CONTEXT;
    private URLQueryParameter queryParams = new URLQueryParameter();


	/*
     * Typical example can be:
     * Servicepath: /SchoolInfos/1234-1245/StudentPersonal.xml;zoneId=ABC;contextId=DEFUALT
     * Job Object: /StudentRollover;zoneId=ABC;contextId=DEFUALT
     * Job Phase: /StudentRollover/<jobId>/<phaseName>;zoneId=ABC;contextId=DEFUALT
     * 
     * Because ServicePath and JobPhase paths look structurally the same the "serviceType" parameter is required to decide where
     * values of the segment should be stored. If serviceType is empty or null "OBJECT" is assumed as per SIF Specification.
     */
    public URIPathInfo(String relURLNoProtocol, String serviceTypeStr)
    {
        originalURLString = new String(relURLNoProtocol);
        if (StringUtils.notEmpty(serviceTypeStr))
        {
            try
            {
                this.serviceType = ServiceType.valueOf(serviceTypeStr.toUpperCase());
            }
            catch (Exception ex)
            {
                logger.error("Found invalid service type in request header. Assume OBJECT.");
                this.serviceType = ServiceType.OBJECT;
            }
        }
        else
        {
            this.serviceType = ServiceType.OBJECT;
        }

        // Split on '?' to extract the URL Query parameters
        String[] components = relURLNoProtocol.split("\\?");
        
        if (components.length == 2) // we have query parameters.
        {
        	setQueryParameters(components[1]);
        }

        // Now we split on ";" to get service name separated from zone and context ID.
        components = components[0].split(";");

        // This should have at least 1 element, the service. We must remove any ".xml" or ".json" etc.
        urlService = (components[0].split("\\."))[0];

        // It may hold a "/" at the beginning. We must remove this.
        if (urlService.startsWith("/"))
        {
            urlService = urlService.substring(1);
        }

        processServiceName(urlService);

        // Check if we have zone and/or context IDs
        if (components.length > 1)
        {
            setZoneOrContext(components[1]);
        }
        if (components.length > 2)
        {
            setZoneOrContext(components[2]);
        }
    }

    public String getOriginalURLString()
    {
        return originalURLString;
    }

    public String getResourceID()
    {
        return resourceID;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public String getPhaseName()
    {
        return phaseName;
    }

    public ServiceType getServiceType()
    {
        return serviceType;
    }

    public String getUrlService()
    {
        return urlService;
    }

    public SIFZone getZone()
    {
        return zone;
    }

    public SIFContext getContext()
    {
        return context;
    }

    public URLQueryParameter getQueryParams()
	{
		return queryParams;
	}

    @Override
    public String toString()
    {
        return "URIPathInfo [originalURLString=" + originalURLString + ", serviceName="
                + serviceName + ", phaseName=" + phaseName + ", serviceType=" + serviceType
                + ", urlService=" + urlService + ", resourceID=" + resourceID + ", zone=" + zone
                + ", context=" + context + ", queryParams=" + queryParams + "]";
    }

    private void setZoneOrContext(String zoneOrContextID)
    {
        String[] parts = zoneOrContextID.split("=");
        if ("zoneId".equals(parts[0]))
        {
            zone = new SIFZone(parts[1]);
        }
        if ("contextId".equals(parts[0]))
        {
            context = new SIFContext(parts[1]);
        }
    }

    private void processServiceName(String segmentStr)
    {
        // Check if we have more than 3+ segments => ServicePath (3, 5, 7 etc segments) or Job Phase (exactly 3 segments)!
        String[] segments = segmentStr.split("/");
        if (segments.length > 2) // not an object service
        {
            if (getServiceType() == ServiceType.SERVICEPATH)
            {
                serviceName = "";
                for (int i = 0; i < segments.length - 2; i += 2)
                {
                    serviceName = serviceName + segments[i] + "/{}/";
    
                }
                serviceName = serviceName + segments[segments.length - 1];
//              serviceType = ServiceType.SERVICEPATH;
            }
            else // Job Phase
            {
                serviceName = segments[0];
                resourceID = segments[1];
                phaseName = segments[2];
            }
        }
        else // one or two segments is Object or Job (not job phase though!)
        {
            serviceName = segments[0];
            if (segments.length == 2)
            {
                resourceID = segments[1];
            }
//            serviceType = ServiceType.OBJECT;
        }
    }
    
    private void setQueryParameters(String urlQueryParamStr)
    {
    	if (StringUtils.notEmpty(urlQueryParamStr))
    	{
    		// Split on '&' as each indicates a new URL Query Parameter
    		String[] urlQueryParams = urlQueryParamStr.split("&");
            for (int i = 0; i < urlQueryParams.length; i++)
            {
            	// split on '=' to get name and value
            	String[] param = urlQueryParams[i].split("=");
            	if (param.length == 2) // only then it is a valid URL Query parameter
            	{
            		queryParams.setQueryParam(param[0], param[1]); 
            	}
            }
    	}
    }
}
