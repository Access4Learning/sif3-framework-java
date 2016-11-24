/*
 * HttpHeaders.java
 * Created: 04/03/2015
 *
 * Copyright 2015 Systemic Pty Ltd
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
package sif3.infra.rest.web.audit;

import java.util.Collection;

/**
 * Utility Interface used for the 'Audit Wrapper' classes. It is not expected that these interfaces are
 * implemented by any other classes than the internal audit wrappers.
 * 
 * @author Ben Carter
 *
 */
public interface HttpHeaders {

  public Collection<String> getHeaderNamesCollection();
  public Collection<String> getHeadersCollection(String name);
  
}
