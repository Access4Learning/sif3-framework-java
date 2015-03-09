/*
 * Auditor.java
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
package sif3.common.interfaces;

import sif3.common.model.audit.AuditRecord;

/**
 * This interface defines the methods that are required by the audit functionality for the SIF3 Framework. A specific implementation must implement this
 * interface to meet the project's audit needs.
 * 
 * @author Joerg Huber & Ben Carter
 *
 */
public interface Auditor
{
	/**
	 * This method shall log the auditRecord. What 'logging the record' refers to is up to the specific implementation of this interface. It could include
	 * logging it to a database, log file, console (not recommended) etc.
	 *   
	 * @param auditRecord The record to be 'logged'.
	 */
	public void audit(AuditRecord auditRecord);
}
