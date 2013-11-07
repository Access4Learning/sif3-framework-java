/*
 * ModelObjectConstants.java
 * Created: 01/10/2013
 *
 * Copyright 2013 Systemic Pty Ltd
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

package systemic.sif3.demo.rest;

import sif.dd.us30.model.Student;
import sif.dd.us30.model.Students;
import sif3.common.conversion.ModelObjectInfo;

/**
 * @author Joerg Huber
 *
 */
public class ModelObjectConstantsUS
{
	public static final ModelObjectInfo STUDENT = new ModelObjectInfo("student", Student.class);
	public static final ModelObjectInfo STUDENTS = new ModelObjectInfo("students", Students.class);

}
