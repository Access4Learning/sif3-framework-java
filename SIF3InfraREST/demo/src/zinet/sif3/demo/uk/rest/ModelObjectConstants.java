/*
 * Crown Copyright © Department for Education (UK) 2016
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

package zinet.sif3.demo.uk.rest;

import sif.dd.uk20.model.LearnerPersonalCollectionType;
import sif.dd.uk20.model.LearnerPersonalType;
import sif3.common.conversion.ModelObjectInfo;

/**
 * @author Dr Jon Nicholson (ZiNET Data Solutions Limited) on behalf of the Department for Education
 *         (UK)
 */
public class ModelObjectConstants
{

    public static final String          UTF_8             = "UTF-8";

    public static final ModelObjectInfo LEARNER_PERSONALS = new ModelObjectInfo("LearnerPersonals",
            LearnerPersonalCollectionType.class);
    public static final ModelObjectInfo LEARNER_PERSONAL  = new ModelObjectInfo("LearnerPersonal",
            LearnerPersonalType.class);
}
