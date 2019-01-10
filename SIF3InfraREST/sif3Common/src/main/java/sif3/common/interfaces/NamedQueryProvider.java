/*
 * NamedQueryProvider.java
 * Created: 4 Dec 2018
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

package sif3.common.interfaces;

import javax.ws.rs.core.MediaType;

import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIFException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryTemplateInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.StringPayload;

/**
 * This class defines the methods a named query service provider (service type = XQUERYTEMPLATE) must implement to fit 
 * with this framework. It defines the core function SIF3 specifies for a name query service provider.<br/>
 * Note that each method has a number of parameters but they all have the following three given parameters. The zone, 
 * the context and metadata. The first two relate to the standard SIF concept of zone and context. The metadata parameter
 * however is additional info that may be provided by a consumer with each request. This can be typical HTTP header 
 * fields such as generatorId, queryIntention etc. Please refer to the SIF 3 Infrastructure Service documentation what 
 * these fields mean as well as where they might be used. It is important to note that most of the properties in the 
 * metadata could be null and therefore implementations must take care how they are used.<br/>
 * The named query service is a "query" only service. It doesn't support create, update or delete operations and no
 * event functionality is availble either.<br/><br/>
 * 
 * Note:<br/>
 * Because this framework used to run under Java 6 some of the types in various methods use "Object" instead of the template 
 * notation. This is because Java 6 doesn't allow a 'new T()' and therefore the interface avoids the template notation to not break 
 * the implementation where a constructor for an Object might be required. This may change in future versions of the framework.
 * Further it requires some objects form the Infrastructure data model but the sif3-infra-model module is not available in this 
 * common module the type 'Object' is used in a couple of places. Please refer to the documentation of the appropriate method
 * for more details on what exact type shall be expected in these methods.
 * 
 * @author Joerg Huber
 */
public interface NamedQueryProvider extends DataModelLink
{
    /**
     * This method is used to retrieve data for a named query service. This can be any data. It is up to the implementation of 
     * the named query service to know what data to be returned. This framework is agnostic to that data. The returned
     * value is a String that must represent the "marshalled" version of the data in the format indicated by the "returnMimeType".
     * Because the data that can be returned as part of this method might be a collection, the paging parameter should be 
     * provided. If the data to be returned is considered too large by the provider (implementation dependent) then a 
     * DataTooLargeException must be raised. This exception is then translated into an appropriate HTTP Status within the 
     * framework to meet the SIF Specification.
     * 
     * @param queryTemplateInfo Hold the queryTemplateInfo.queryName holds the name of the "named query" to be executed. The
     *                          queryTemplateInfo.queryParameters holds ALL URL query parameter the consumer has provided.
     *                          It is up to the implementation class to inspect and use them accordingly. Since it holds ALL
     *                          URL query parameter this list may also hold URL query parameters not related to the actual 
     *                          query parameters.
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the data shall be returned. Can be Null (default Zone)
     * @param pagingInfo Page information to determine which results to return. Null = Return all (NOT RECOMMENDED!).
     * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * @param customResponseParams Values to be returned as part of the response. These are generally just HTTP Header fields. If a developer
     *                             sets the HTTP Header of a well defined SIF3 HTTP Header (i.e. providerId, timestamp) then the framework
     *                             may override these with its own value to ensure the correct use and workings of the framework. It is the
     *                             developer who will populate the object. When it is passed to this method it not null but empty.
     * @param returnMimeType The mime type the response data is in. It is expected that the consumer provides that and the provider
     *                       should attempt to marshal the data to the given mime type and return the resulting string as
     *                       part of this call. If the provider cannot marshal the data to the requested mime type then an
     *                       UnsupportedMediaTypeExcpetion must be raised.
     *                       
     * @return The response data (result set) with its mime type. It can be null indicating no or no further data available. 
     *         The returned string should be the marshalled value of the result set and it should be in the mime type as 
     *         indicated in the returnMimeType parameter. The status of the return value will be set by the framework and will be
     *         either HTTP 200 (OK) when data is returned or HTTP 204 (No Content) if there is no further data available.
     *                       
     * @throws UnsupportedMediaTypeExcpetion The provider cannot support the requested return media type.
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
     * @throws DataTooLargeException If the data that shall be returned is too large due to the values in the paging info.
     * @throws SIFException Any other exception the implementation of that class wants to throw. This will be translated into proper
     *                      SIF Error message that will be returned to the consumer.
     */
    public StringPayload retrieveData(QueryTemplateInfo queryTemplateInfo,
                                      SIFZone zone, 
                                      SIFContext context, 
                                      PagingInfo pagingInfo, 
                                      RequestMetadata metadata, 
                                      ResponseParameters customResponseParams, 
                                      MediaType returnMimeType)
            throws PersistenceException, UnsupportedMediaTypeExcpetion, DataTooLargeException, SIFException;

    /*-------------------------------*/
    /*-- Other required Operations --*/
    /*-------------------------------*/ 
    
    /**
     * This method is called when a provider shuts down. Can be used to clean-up internally held resources etc. 
     */
    public void finalise();

}
