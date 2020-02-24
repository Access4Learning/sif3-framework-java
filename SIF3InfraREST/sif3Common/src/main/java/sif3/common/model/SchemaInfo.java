/*
 * SchamaInfo.java
 * Created: 28 Oct 2019
 *
 * Copyright 2019 Systemic Pty Ltd
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

package sif3.common.model;

import java.io.Serializable;

import au.com.systemic.framework.utils.StringUtils;

/**
 * This POJO simply holds information about the payload schema as defined in the SIF Infra 3.3 - Versions.pdf. It provides
 * a few utility methods to converts the values into the correct string notation and vice versa.
 * 
 * @author Joerg Huber
 */
public class SchemaInfo implements Serializable
{
    private static final long serialVersionUID = -3444453777155358130L;
    private static final String URN = "urn";
    
    // Constants that are used in SIF for data model version negotiation.
    public static final String SIF_NAMESPACE_ID = "sif";
    public static final String MODEL_TYPE_DM = "data";
    public static final String MODEL_TYPE_INFRA = "inf";
    public static final String MODEL_DOMAIN_INFRA = "global";
    
    // All the properties below are 'String'. This is done intentionally. Event SIF specifies values for some of them
    // future expansions may need more flexibilities. Further where payloads are not JSON or XML, or where a non-SIF
    // data model is used these properties could be of any values. This is a reason why less enumerations are used in here
    // and the more generic 'String' type is in place.
    private String namespaceID = SIF_NAMESPACE_ID; // default
    private String modelType = null; // valid values: data, inf
    private String modelDomain = null; // examples: global, au, nz, uk, us
    private String modelVersion = null;
    private String schemaType = null; // examples: xml, goessner, pesc (the last two are JSON types)
    private Double qualityValue = null;
    
    /**
     * Default constructor. Will set/default namespaceID to "sif".
     */
    public SchemaInfo()
    {
        this(null, null, null);
    }
    
    /**
     * Will populate properties of this object with values of the given parameter string. See also populate() method in this class.
     *  
     * @param fullSchemaValue Full value of a schema string as is is typically extracted from appropriate HTTP Headers. The prefix "urn:"
     *                        is optional. Every other component is mandatory and if not provided the behaviour of this method is not 
     *                        defined and cannot be relied upon.
     */
    public SchemaInfo(String fullSchemaValue)
    {
        super();
        populate(fullSchemaValue);
    } 
    
    /**
     * Will default the namespaceID to "sif". All other values are taken as given by the various parameters.
     * 
     * @param modelType Typical values are: data or inf
     * @param modelDomain Typical values are: global, au, nz, uk, us
     * @param modelVersion Typical values are,  depending on locale: 3.2.1, 3.3, 3.4.5, 4.0
     */
    public SchemaInfo(String modelType, String modelDomain, String modelVersion)
    {
        super();
        setNamespaceID(SIF_NAMESPACE_ID);
        setModelType(modelType);
        setModelDomain(modelDomain);
        setModelVersion(modelVersion);
    }
    
    public String getNamespaceID()
    {
        return namespaceID;
    }

    public void setNamespaceID(String namespaceID)
    {
        this.namespaceID = namespaceID;
    }

    public String getModelType()
    {
        return modelType;
    }

    public void setModelType(String modelType)
    {
        this.modelType = modelType;
    }

    public String getModelDomain()
    {
        return modelDomain;
    }

    public void setModelDomain(String modelDomain)
    {
        this.modelDomain = modelDomain;
    }

    public String getModelVersion()
    {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion)
    {
        this.modelVersion = modelVersion;
    }

    public String getSchemaType()
    {
        return schemaType;
    }

    public void setSchemaType(String schemaType)
    {
        this.schemaType = schemaType;
    }

    public Double getQualityValue()
    {
        return qualityValue;
    }

    public void setQualityValue(Double qualityValue)
    {
        this.qualityValue = qualityValue;
    }

    /**
     * This method creates the schema value that can be used to populate appropriate HTTP Headers as specified in the 
     * SIF Infra 3.3 - Versions.pdf. Basically it is based on all the values given in this POJO. The final string 
     * will be:<br/><br/>
     * 
     * urn:{namespaceID}:{modelType}/{modelDomain}/{modelVersion}+{schemaType};q={qualityValue}
     * <br/><br/>
     * 
     * Note: {schemaType} and {qualityValye} are optional.
     * 
     * @return See Desc.
     */
    public String getFullSchemaValue()
    {
        return URN+":"+
               getNamespaceID()+":"+
               getModelType()+"/"+
               getModelDomain()+"/"+
               getModelVersion()+
               ((StringUtils.isEmpty(getSchemaType())) ? "" : ("+"+getSchemaType())) +
               ((getQualityValue() != null) ? (";q="+getQualityValue()) : "");
    }
    
    /**
     * This method is the inverse of the getFullSchemaValue() method. It disassembles the full schema value string into its components.
     * 
     * @param fullSchemaValue Full value of a schema string as is is typically extracted from appropriate HTTP Headers. The prefix "urn:"
     *                        is optional. Every other component is mandatory and if not provided the behaviour of this method is not 
     *                        defined and cannot be relied upon.
     */
    public void populate(String fullSchemaValue)
    {
        if (StringUtils.notEmpty(fullSchemaValue))
        {
            if (fullSchemaValue.startsWith(URN+":"))
            {
                fullSchemaValue = fullSchemaValue.substring(URN.length()+1);
            }
            
            // Here we have removed the "urn:". Let's split on the next ":" to get the namespaceID and the model info
            String[] components = fullSchemaValue.split(":");
            if (components.length == 1) // we probably have a missing namespaceID, so we default to "sif"
            {
                setNamespaceID(SIF_NAMESPACE_ID);
            }
            else if (components.length == 2) // this is the expected result
            {
                setNamespaceID(components[0]);
                
                // Start processing the model values. We can split on "/".
                String[] modelComponents = components[1].split("/");
                if (modelComponents.length == 3) // we need exactly this to be valid
                {
                    setModelType(modelComponents[0]);
                    setModelDomain(modelComponents[1]);
                    
                    // The version may also have a schema type and a quantifier. So first we need to split on quantifier...
                    String[] quantifierComponents = modelComponents[2].split(";");
                    
                    //first is version and schema type
                    String[] modelVersionComponents = quantifierComponents[0].split("\\+");
                    setModelVersion(modelVersionComponents[0]);
                    if(modelVersionComponents.length == 2) // we also have a schema type
                    {
                        setSchemaType(modelVersionComponents[1]);
                    }
                    
                    // if we have a second component then it is the qualifier
                    if (quantifierComponents.length == 2) // process qualifier
                    {
                        String[] quantifierParts = quantifierComponents[1].split("=");
                        if (quantifierParts.length == 2) // all good
                        {
                            setQualityValue(Double.valueOf(quantifierParts[1]));
                        }
                    }
                }
                else
                {
                    // this is invalid. Nothing we can do ...
                }
            }
            else // not good! There are more than the allowed maximum ":" in the string.
            {
                setNamespaceID(SIF_NAMESPACE_ID); // default. But this is all we can do!
            }
        }
    }
    
    @Override
    public String toString()
    {
        return "SchemaInfo [namespaceID=" + namespaceID + ", modelType=" + modelType
                + ", modelDomain=" + modelDomain + ", modelVersion=" + modelVersion
                + ", schemaType=" + schemaType + ", qualityValue=" + qualityValue + "]";
    }
}
