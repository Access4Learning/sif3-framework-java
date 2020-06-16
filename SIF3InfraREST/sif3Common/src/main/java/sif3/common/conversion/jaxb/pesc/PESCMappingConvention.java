/*
 * PESCMappingConvention.java
 * Created: 02 June 2020
 *
 * Copyright 2020 Systemic Pty Ltd
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

package sif3.common.conversion.jaxb.pesc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.codehaus.jettison.Node;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;

/**
 * This is a helper class to configure the Jettison library to unmarshal PESC-JSON.
 * 
 * @author Ben Carter
 *
 */
public class PESCMappingConvention extends MappedNamespaceConvention
{
    private String       attributeKey;
    private boolean      allAttributes;
    private boolean      supressAtAttributes;
    private List<String> jsonAttributesAsElements;

    public PESCMappingConvention()
    {
        super();
    }

    public PESCMappingConvention(Configuration configuration)
    {
        super(configuration);
        this.attributeKey = configuration.getAttributeKey();
        this.supressAtAttributes = configuration.isSupressAtAttributes();
        this.allAttributes = configuration.isSupressAtAttributes() || "".equals(this.attributeKey);
    }

    @Override
    public void processAttributesAndNamespaces(Node n, JSONObject object) throws JSONException
    {
        if (allAttributes)
        {
            processAllAttributes(n, object);
        }
        else
        {
            super.processAttributesAndNamespaces(n, object);
        }
    }

    private void processAllAttributes(Node n, JSONObject object) throws JSONException
    {
        /*
         * Copied from: MappedNamespaceConvention.processAttributesAndNamespaces Main change is to
         * treat all properties as both an attribute and a property
         */
        for (Iterator<?> itr = object.keys(); itr.hasNext();)
        {
            String k = (String) itr.next();

            if (this.supressAtAttributes)
            {
                if (null == this.jsonAttributesAsElements)
                {
                    this.jsonAttributesAsElements = new ArrayList<String>();
                }
                if (!this.jsonAttributesAsElements.contains(k))
                {
                    this.jsonAttributesAsElements.add(k);
                }
            }

            Object o = object.opt(k);
            if (k.equals("xmlns"))
            {
                if (o instanceof JSONObject)
                {
                    JSONObject jo = (JSONObject) o;
                    for (Iterator<?> pitr = jo.keys(); pitr.hasNext();)
                    {
                        String prefix = (String) pitr.next();
                        String uri = jo.getString(prefix);
                        n.setNamespace(prefix, uri);
                    }
                }
                if (o instanceof String)
                {
                    String uri = o.toString();
                    QName name = new QName(XMLConstants.DEFAULT_NS_PREFIX, k);
                    n.setAttribute(name, uri);
                }
            }
            else
            {
                String strValue = o == null ? null : o.toString();
                QName name = null;
                if (k.contains(getNamespaceSeparator()))
                {
                    name = createQName(k, n);
                }
                else
                {
                    name = new QName(XMLConstants.DEFAULT_NS_PREFIX, k);
                }
                n.setAttribute(name, strValue);
            }
        }
    }
}
