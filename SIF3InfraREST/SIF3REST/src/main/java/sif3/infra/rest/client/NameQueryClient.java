/*
 * NameQueryClient.java
 * Created: 22 Nov 2018
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

package sif3.infra.rest.client;

import java.util.HashMap;

import javax.ws.rs.core.Response.Status;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.exception.ServiceInvokationException;
import sif3.common.header.HeaderProperties;
import sif3.common.header.HeaderValues.RequestType;
import sif3.common.header.HeaderValues.ServiceType;
import sif3.common.header.RequestHeaderConstants;
import sif3.common.model.PagingInfo;
import sif3.common.model.PayloadMetadata;
import sif3.common.model.QueryTemplateInfo;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.URLQueryParameter;
import sif3.common.ws.Response;
import sif3.infra.common.env.types.ConsumerEnvironment.ConnectorName;
import sif3.infra.common.interfaces.ClientEnvironmentManager;

/**
 * @author Joerg Huber
 *
 */
public class NameQueryClient extends BaseClient
{
    
//    private String queryName = null;
    
    /**
     * Constructor
     * 
     * @param clientEnvMgr Session manager to access the clients session information.
     */
    public NameQueryClient(ClientEnvironmentManager clientEnvMgr)
    {
        super(clientEnvMgr, 
              clientEnvMgr.getEnvironmentInfo().getConnectorBaseURI(ConnectorName.requestsConnector), 
              null,
              null,
              null,
              null, 
              clientEnvMgr.getEnvironmentInfo().getSecureConnection(), 
              clientEnvMgr.getEnvironmentInfo().getCompressionEnabled());
    }

//    public String getQueryName()
//    {
//        return queryName;
//    }
//
//    public void setQueryName(String queryName)
//    {
//        this.queryName = queryName;
//    }

    /**
     * This method is used to retrieve data from a named query service. This can be any data. It is up to the implementation of the
     * named query service to know what that data is for a given query. This framework is agnostic to that data. The returned
     * value is a String that must represent the "marshalled" version of the data in the format indicated by the "returnMimeType".
     * Because the data that can be returned as part of a service might be a collection, the paging parameter can be provided. If the 
     * data to be returned is considered too large by the provider (implementation dependent) then an appropriate error is 
     * returned (HTTP Status 413 - Response too large).
     * 
     * @param queryTemplateInfo Hold the query name and query parameters of the named query service from where the data shall be 
     *                          retrieved. The parameter and the queryTemplateInfo.queryName property must not be null/empty.
     * @param returnPayloadMetadata The mime type and optional schema info the response data is expected to be returned as. 
     *                              It is expected that the consumer provides that and the provider should attempt to marshal the data
     *                              to the given mime type, potentially using the given schema info, and return the resulting string
     *                              in the requested format. If the provider cannot marshal the data to the requested mime type then an
     *                              appropriate error is returned to this consumer (HTTP Status 400 - Bad Request).
     * @param pagingInfo Page information to determine which results to return. Null = Return all (NOT RECOMMENDED! Might be rejected
     *                   by provider.).
     * @param hdrProperties Header Properties to be added to the header of the request. Can be null.
     * @param urlQueryParams Additional URL query parameters to be added to the request. It is assumed that these are custom
     *                       URL query parameters that may not form part of the actual named query parameters. They are conveyed 
     *                       to the provider unchanged. URL query parameter names are case sensitive. This parameter can be null.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the the request is being issued. Can be Null (default Zone)
     * @param requestType Indicating if IMMEDIATE or DELAYED request is desired.
     *                       
     * @return Response Object holding appropriate values and results of the call. Because the framework is agnostic to the
     *         data that is returned for a phase the Response.dataObjectType will be set to "String" and the Response.dataObject
     *         will hold the string representation of the returned payload. It is up to the caller of this method to potentially
     *         marshal that payload into an appropriate object.
     *         
     * @throws ServiceInvokationException Any underlying errors occurred such as failure to invoke actual web-service etc. 
     */
    public Response retrieveDataFromNamedQuery(QueryTemplateInfo queryTemplateInfo, 
                                               PayloadMetadata returnPayloadMetadata, 
                                               PagingInfo pagingInfo, 
                                               HeaderProperties hdrProperties, 
                                               URLQueryParameter urlQueryParams, 
                                               SIFZone zone, 
                                               SIFContext context, 
                                               RequestType requestType)
            throws ServiceInvokationException
    {
        if ((queryTemplateInfo == null) || StringUtils.isEmpty(queryTemplateInfo.getQueryName())) 
        {
            String errorMsg = "queryTemplateInfo parameter or queryTemplateInfo.queryName is null or empty. These values must be provided.";
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg);
        }
        WebResource service = getService();
        try
        {
            //Add Named Query Parameters to URL Query Parameters
            if ((queryTemplateInfo.getQueryParameters() != null) && !queryTemplateInfo.getQueryParameters().isEmpty())
            {
                if (urlQueryParams == null)
                {
                    urlQueryParams = new URLQueryParameter();
                    urlQueryParams.setQueryParams(new HashMap<String, String>());
                }
                
                urlQueryParams.getQueryParams().putAll(queryTemplateInfo.getQueryParameters());
            }
            
            service = buildURI(service, zone, context, urlQueryParams, queryTemplateInfo.getQueryName());
            hdrProperties = addAuthenticationHdrProps(hdrProperties);
            hdrProperties = addSchemaHdrProps(hdrProperties, false, null, true, returnPayloadMetadata.getSchemaInfo());
            hdrProperties.setHeaderProperty(RequestHeaderConstants.HDR_SERVICE_TYPE, ServiceType.XQUERYTEMPLATE.name());
            addPagingInfoToHeaders(pagingInfo, hdrProperties);
            addDelayedInfo(hdrProperties, zone, context, queryTemplateInfo.getQueryName(), ServiceType.XQUERYTEMPLATE, requestType);
            
            ClientResponse response = setRequestHeaderAndMediaTypes(service, returnPayloadMetadata.getMimeType(), returnPayloadMetadata.getMimeType(), hdrProperties, requestType, true, true, false).get(ClientResponse.class);

            return setResponse(service, response, String.class, hdrProperties, zone, context, requestType, true, Status.OK, Status.NOT_MODIFIED, Status.NO_CONTENT, Status.ACCEPTED);
        }
        catch (Exception ex)
        {
            String errorMsg = "Failed to invoke 'retrieveDataFromNamedQuery' service (REST GET) on URI " + service.getURI() + ": " + ex.getMessage();
            logger.error(errorMsg);
            throw new ServiceInvokationException(errorMsg, ex);
        }
    }
}
