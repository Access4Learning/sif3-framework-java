
package systemic.sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for xpathType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="xpathType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="path" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="namespaces">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="namespace">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="prefix" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *                             &lt;choice>
 *                               &lt;element name="static" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *                               &lt;element name="startsWith" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *                               &lt;element name="regularExpression" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;/choice>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "xpathType", namespace = "http://www.sifassociation.org/infrastructure/3.0", propOrder = {
    "path",
    "namespaces"
})
public class XpathType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String path;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected XpathType.Namespaces namespaces;

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPath(String value) {
        this.path = value;
    }

    /**
     * Gets the value of the namespaces property.
     * 
     * @return
     *     possible object is
     *     {@link XpathType.Namespaces }
     *     
     */
    public XpathType.Namespaces getNamespaces() {
        return namespaces;
    }

    /**
     * Sets the value of the namespaces property.
     * 
     * @param value
     *     allowed object is
     *     {@link XpathType.Namespaces }
     *     
     */
    public void setNamespaces(XpathType.Namespaces value) {
        this.namespaces = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="namespace">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="prefix" type="{http://www.w3.org/2001/XMLSchema}token"/>
     *                   &lt;choice>
     *                     &lt;element name="static" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
     *                     &lt;element name="startsWith" type="{http://www.w3.org/2001/XMLSchema}token"/>
     *                     &lt;element name="regularExpression" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;/choice>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "namespace"
    })
    public static class Namespaces {

        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected XpathType.Namespaces.Namespace namespace;

        /**
         * Gets the value of the namespace property.
         * 
         * @return
         *     possible object is
         *     {@link XpathType.Namespaces.Namespace }
         *     
         */
        public XpathType.Namespaces.Namespace getNamespace() {
            return namespace;
        }

        /**
         * Sets the value of the namespace property.
         * 
         * @param value
         *     allowed object is
         *     {@link XpathType.Namespaces.Namespace }
         *     
         */
        public void setNamespace(XpathType.Namespaces.Namespace value) {
            this.namespace = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="prefix" type="{http://www.w3.org/2001/XMLSchema}token"/>
         *         &lt;choice>
         *           &lt;element name="static" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
         *           &lt;element name="startsWith" type="{http://www.w3.org/2001/XMLSchema}token"/>
         *           &lt;element name="regularExpression" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;/choice>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "prefix",
            "_static",
            "startsWith",
            "regularExpression"
        })
        public static class Namespace {

            @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true, nillable = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String prefix;
            @XmlElement(name = "static", namespace = "http://www.sifassociation.org/infrastructure/3.0")
            @XmlSchemaType(name = "anyURI")
            protected String _static;
            @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String startsWith;
            @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
            protected String regularExpression;

            /**
             * Gets the value of the prefix property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPrefix() {
                return prefix;
            }

            /**
             * Sets the value of the prefix property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPrefix(String value) {
                this.prefix = value;
            }

            /**
             * Gets the value of the static property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStatic() {
                return _static;
            }

            /**
             * Sets the value of the static property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStatic(String value) {
                this._static = value;
            }

            /**
             * Gets the value of the startsWith property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStartsWith() {
                return startsWith;
            }

            /**
             * Sets the value of the startsWith property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStartsWith(String value) {
                this.startsWith = value;
            }

            /**
             * Gets the value of the regularExpression property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRegularExpression() {
                return regularExpression;
            }

            /**
             * Sets the value of the regularExpression property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRegularExpression(String value) {
                this.regularExpression = value;
            }

        }

    }

}
