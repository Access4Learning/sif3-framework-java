/*
 * DelayedBaseInfo.java 
 * Created: 02/12/2015
 * 
 * Copyright 2015 Systemic Pty Ltd
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
package sif3.infra.rest.queue.types;

import java.io.Serializable;

import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.ResponseAction;
import sif3.common.model.URLQueryParameter;

/**
 * This class is a utility type to allow a consumer to package the actual DELAYED response/error data and related
 * information in one structure. This type uses the actual response payload data as a String as it has been
 * received from the remote message queue. This is so that the payload is still raw without the need
 * of knowing what to unmarshal it into until it is actually send to the final consumer. This
 * way a DELAYED response/error can easily be passed between classes and processed without having the need of many
 * parameters or actual knowledge of the DELAYED response/error object class<br/>
 * <br/>
 * 
 * @author Joerg Huber
 *
 */
public class DelayedBaseInfo extends QueueMessage implements Serializable
{
    private static final long serialVersionUID = 485952611520785166L;
    
    private String requestGUID = null;
    
    // Full URL as set in the 'relservicepath' HTTP header
    private String fullRelativeURL = null;
    
	// The Service Name of the request (StudentPersonals, SchoolInfos/{}/StudentPersonals etc are valid names)
    private String serviceName = null;

    // Job Phases or potentially future other service types may have a resourceID for which the delayed response is for.
    private String resourceID = null;

    // Functional Services can have delayed request/response for phases.
    private String phaseName = null;

    // The Service Name with real URL (StudentPersonals, SchoolInfos/1234-8826ABCD-1234/StudentPersonals etc are valid values)
    private String urlService = null;
    
    // Holds the type originally requested operation (i.e. CREATE, QUERY ...)
    private ResponseAction responseAction = null;
    
    private HeaderProperties httpHeaders = new HeaderProperties();
    private URLQueryParameter queryParameters = new URLQueryParameter(); 

	public String getRequestGUID()
	{
		return requestGUID;
	}

	public void setRequestGUID(String requestGUID)
	{
		this.requestGUID = requestGUID;
	}

    /**
     * The Service Name of the request as known in the Environments ACL. Typical examples are:<br/><br/>
     * 
     * Object Service: StudentPersonals<br/>
     * Service Path: SchoolInfos/{}/StudentPersonals<br/><br/>
     * 
     * @return See above
     */
	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

    public String getResourceID()
    {
        return resourceID;
    }

    public void setResourceID(String resourceID)
    {
        this.resourceID = resourceID;
    }

    public String getPhaseName()
    {
        return phaseName;
    }

    public void setPhaseName(String phaseName)
    {
        this.phaseName = phaseName;
    }

	public HeaderProperties getHttpHeaders()
	{
		return httpHeaders;
	}

	public void setHttpHeaders(HeaderProperties httpHeaders)
	{
		if (httpHeaders != null)
		{
			this.httpHeaders = httpHeaders;
		}
	}

	public ResponseAction getResponseAction()
    {
	    return responseAction;
    }

	public void setResponseAction(ResponseAction responseAction)
    {
	    this.responseAction = responseAction;
    }

	/**
	 * The Service Name with real URL of the request. Typical examples are:<br/><br/>
	 * 
	 * Object Service: StudentPersonals<br/>
	 * Service Path: SchoolInfos/24ed508e-1ed0-4bba-8219-8233efa55859/StudentPersonals<br/><br/>
	 * 
	 * @return See above
	 */
	public String getUrlService()
    {
	    return urlService;
    }

	public void setUrlService(String urlService)
    {
	    this.urlService = urlService;
    }

    public String getFullRelativeURL()
	{
		return fullRelativeURL;
	}

	public void setFullRelativeURL(String fullRelativeURL)
	{
		this.fullRelativeURL = fullRelativeURL;
	}

	public URLQueryParameter getQueryParameters()
	{
		return queryParameters;
	}

	public void setQueryParameters(URLQueryParameter queryParameters)
	{
		if (queryParameters != null)
		{
			this.queryParameters = queryParameters;
		}
	}

	@Override
    public String toString()
    {
        return "DelayedBaseInfo [requestGUID=" + requestGUID + ", fullRelativeURL="
                + fullRelativeURL + ", serviceName=" + serviceName + ", resourceID=" + resourceID
                + ", phaseName=" + phaseName + ", urlService=" + urlService + ", responseAction="
                + responseAction + ", httpHeaders=" + httpHeaders + ", queryParameters="
                + queryParameters + ", toString()=" + super.toString() + "]";
    }
}
