/*
 * StudentsInYear.java
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

package systemic.sif3.demo.rest.provider.namedquery;

import javax.ws.rs.core.MediaType;

import sif.dd.au30.conversion.DataModelMarshalFactory;
import sif.dd.au30.conversion.DataModelUnmarshalFactory;
import sif3.common.conversion.MarshalFactory;
import sif3.common.conversion.UnmarshalFactory;
import sif3.common.exception.DataTooLargeException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.SIFException;
import sif3.common.exception.UnsupportedMediaTypeExcpetion;
import sif3.common.model.PagingInfo;
import sif3.common.model.PayloadMetadata;
import sif3.common.model.QueryTemplateInfo;
import sif3.common.model.RequestMetadata;
import sif3.common.model.ResponseParameters;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.common.model.StringPayload;
import sif3.infra.rest.provider.namedquery.BaseNamedQueryProvider;

/**
 * @author Joerg Huber
 *
 */
public class StudentsInYearProvider extends BaseNamedQueryProvider
{

    /* 
     * @see sif3.common.interfaces.NamedQueryProvider#retrieveData(sif3.common.model.QueryTemplateInfo, sif3.common.model.SIFZone, sif3.common.model.SIFContext, sif3.common.model.PagingInfo, sif3.common.model.RequestMetadata, sif3.common.model.ResponseParameters, javax.ws.rs.core.MediaType)
     */
    @Override
    public StringPayload retrieveData(QueryTemplateInfo queryTemplateInfo,
                                      SIFZone zone, 
                                      SIFContext context, 
                                      PagingInfo pagingInfo, 
                                      RequestMetadata metadata,
                                      ResponseParameters customResponseParams, 
                                      PayloadMetadata returnPayloadMetadata)
            throws PersistenceException, UnsupportedMediaTypeExcpetion, DataTooLargeException, SIFException
    {
        logger.debug("StudentsInYearProvider.retrieveData request received with QueryTemplateInfo:\n"+queryTemplateInfo);
        logger.debug("StudentsInYearProvider.retrieveData request received with requested response payload mime type and schema:\n"+returnPayloadMetadata);
        if(returnPayloadMetadata.getMimeType().isCompatible(MediaType.APPLICATION_JSON_TYPE))
        {
            throw new UnsupportedMediaTypeExcpetion("JSON not supported for "+getServiceName()+" named query.");
        }
        StringPayload response = new StringPayload();
        
        // Need to set the payload metadata. This also includes the schema info if enabled. At this point we simply assume it is all
        //correct as set in the returnPayloadMetadata. However a proper and full implementation with schema negotiation would
        // also test if the schema requested matched what is returned.
        response.setPayloadMetadata(returnPayloadMetadata);
        
        response.setData( 
        "<Students xmlns=\"http://www.au/StudentInYear/3.4.2\">\n"+
        "    <school>4001</school>\n"+
        "  <Student>\n"+
        "    <LogonId>dan.petersen</LogonId>\n"+
        "  </Student>\n"+
        "  <Student>\n"+
        "    <LogonId>leila.stone</LogonId>\n"+
        "  </Student>\n"+
        "</Students>"); 

        return response;
    }

    /* Name of the service as it appears in the environment or the end-point.
     * 
     * (non-Javadoc)
     * @see sif3.infra.rest.provider.CoreProvider#getServiceName()
     */
    @Override
    public String getServiceName()
    {
        return "StudentsInYear";
    }

    /* (non-Javadoc)
     * @see sif3.infra.rest.provider.CoreProvider#shutdown()
     */
    @Override
    public void shutdown()
    {
        logger.debug("Shutdown Named Query Service for " + getPrettyName()); 
    }
    
    /*-------------------------------------------------------------------------------------------*/
    /*-- Here we can override the marshaller and unmarshaller methods of the CoreProvider.     --*/
    /*-- This would ensure that the return payload is correctly marshalled and that mime types --*/
    /*-- managed appropriately.                                                                --*/ 
    /*-- For the purpose of this demo we just use the DM marshaller but it could be any other. --*/
    /*-------------------------------------------------------------------------------------------*/
    private static DataModelUnmarshalFactory unmarshaller = new DataModelUnmarshalFactory();
    private static DataModelMarshalFactory marshaller = new DataModelMarshalFactory();

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getMarshaller()
     */
    @Override
    public MarshalFactory getMarshaller()
    {
        return marshaller;
    }

    /* (non-Javadoc)
     * @see sif3.common.interfaces.DataModelLink#getUnmarshaller()
     */
    @Override
    public UnmarshalFactory getUnmarshaller()
    {
        return unmarshaller;
    }

    
}
