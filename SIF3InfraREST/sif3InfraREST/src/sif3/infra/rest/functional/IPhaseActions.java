/*
 * Crown Copyright © Department for Education (UK) 2016 Licensed under the
 * Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package sif3.infra.rest.functional;

import javax.ws.rs.core.MediaType;

import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.PhaseType;

public interface IPhaseActions {

	String create(JobType job, PhaseType phase, String data, MediaType requestMediaType, MediaType responseMediaType) throws IllegalArgumentException, PersistenceException, UnmarshalException, UnsupportedMediaTypeException, UnsupportedQueryException;

	String retrieve(JobType job, PhaseType phase, MediaType responseMediaType) throws IllegalArgumentException, PersistenceException, UnsupportedMediaTypeException, UnsupportedQueryException;

	String update(JobType job, PhaseType phase, String data, MediaType requestMediaType, MediaType responseMediaType) throws IllegalArgumentException, PersistenceException, UnmarshalException, UnsupportedMediaTypeException, UnsupportedQueryException;

	String delete(JobType job, PhaseType phase, String data, MediaType requestMediaType, MediaType responseMediaType) throws IllegalArgumentException, PersistenceException, UnmarshalException, UnsupportedMediaTypeException, UnsupportedQueryException;
}
