/*
 * NamedQueryConsumer.java
 * Created: 11 Dec 2018
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

/**
 * This interface defines the various methods that form part of SIF 3.x for a named query consumer. Note that the named
 * query consumer really only has the "retrieve" method available but to fit this interface with the core functionality
 * of the SIF3 framework it must extend the MinimalConsumer. The MinimalConsumer enforces the "retrievByPrimaryKey" and
 * the "getServiceInfo" method. Both of these are NOT applicable for named query services and can be nulled out by the
 * implementation of this interface.
 * 
 * @author Joerg Huber
 */
public interface NamedQueryConsumer extends MinimalConsumer
{

}
