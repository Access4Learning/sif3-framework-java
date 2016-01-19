/*
 * ChangesSinceProvider.java
 * Created: 06/01/2016
 *
 * Copyright 2016 Systemic Pty Ltd
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
package sif3.common.interfaces;

import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.ChangedSinceInfo;
import sif3.common.model.PagingInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;

/**
 * This class defines the Provider Interface for "Changes Since" functionality. This functionality is intended to be used by DIRECT environments where
 * SIF Events might not be available. Overall "Changes Since" allows a consumer to request changes for a given SIF Object (i.e. StudentPersonal) based
 * on an opaque marker. The Opaque Marker is provided by the provider and the consumer uses it to tell the provider to return all changes from that
 * opaque marker onwards. The SIF 3.2 Infrastructure Specification contains the detailed mechanism for "Changes Since". 
 * 
 * @author Joerg Huber
 */
public interface ChangesSinceProvider
{
    /**
     * Indicates if a particular object provider supports the "Changes Since" functionality. If this method returns true then it is expected that all
     * other methods in this interface are implemented according to the SIF 3.2+ Infrastructure Specification. Methods of this interface are only invoked
     * by this framework, specifically the DataModelResource class, if this method returns true. If this method returns false the framework will assume
     * that the specific Object Service doesn't support "Changes Since" functionality and appropriate error messages will be returned to the consumers.
     * 
     * @return See description.
     */
    public boolean changesSinceSupported();
    
    /**
     * As stated by the SIF Infrastructure Specification 3.2+ the consumer will require the "opaque marker" that indicates where the latest change 
     * on the provider is. This can be a sequence number in a change log, a datetime stamp, a version number etc. It is the responsibility of the 
     * provider to know how to interpret this opaque marker once it is being received as a URL Query Parameter from a consumer.<br/>
     * This method shall return the latest value of that opaque marker in the provider for the given SIF Object Service. This value shall not be null or 
     * empty if the changesSinceSupported() method returns true, indication that changes since is supported. If the changesSinceSupported() returns false 
     * then this method can return null as it will never be called by the SIF3 Framework. 
     * 
     * @return See description.
     */
    public String getLatestOpaqueMarker();
    
    /**
     * This method is used to retrieve a list of changed SIF Objects. The returned object must be a "Collection" style SIF Object (i.e. 
     * StudentPersonalCollectionType) even if there is only one change. The changes returned must be all objects of this OBJECT provider
     * that have undergone any changes since the given "changedSinceInfo.changesSinceMarker". Paging might be used for this method indicating
     * that the consumer will call this method multiple times to get all the changes since the "changedSinceInfo.changesSinceMarker". Paging
     * starts with page number 0 (pagingInfo.currentPageNumber). If there are no more changes based on the changedSinceInfo.changesSinceMarker
     * then this method must return null. If the data set to be returned is considered too large by the provider (implementation dependent) 
     * then a DataTooLargeException must be raised. This exception is then translated into an appropriate HTTP Status within the framework to 
     * meet the SIF Specification.
     * 
     * @param zone The Zone from which the request is being issued. Can be Null (default Zone)
     * @param context The Context for which the objects shall be returned. Can be Null (default Context)
     * @param pagingInfo Page information to determine which results to return. Null = Return all (Might return a large data set. Use with care).
     * @param changedSinceInfo Data required to determine the changes to be returned. This must hold the opaque changes since value that was
     *                         given to the consumer at some stage via the getLatestOpaqueMarker() method of this class. 
     * @param metadata Metadata relating to the request. Note that most of the properties might be null.
     * 
     * @throws UnsupportedQueryException The query provided with this request is not supported. This would be returned if this method is called
     *                                   without a value (null or empty) for the changedSinceInfo.changesSinceMarker.
     * @throws PersistenceException Persistence Store could not be accessed successfully. An error log entry is performed and the 
     *                              message of the exceptions holds some info.
     * @throws DataTooLargeException If the data that shall be returned is too large due to the values in the paging info.
     */
    public Object getChangesSince(SIFZone zone, SIFContext context, PagingInfo pagingInfo, ChangedSinceInfo changedSinceInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException, DataTooLargeException;
 
}
