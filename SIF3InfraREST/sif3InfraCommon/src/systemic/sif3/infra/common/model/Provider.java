
package systemic.sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for provider complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="provider">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;pattern value="UTILITY"/>
 *               &lt;pattern value="OBJECT"/>
 *               &lt;pattern value="FUNCTION"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="serviceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="context">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="contextName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="zoneID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="providername" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="localename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="localeVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="querySupport">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="singular" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="formula" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="paged" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="maxPageSize" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="applicationProduct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="adapterProduct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="endPoint" type="{http://www.sifassociation.org/infrastructure/3.0}protocolType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.sifassociation.org/infrastructure/3.0}uuidType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "provider", namespace = "http://www.sifassociation.org/infrastructure/3.0", propOrder = {
    "serviceType",
    "serviceName",
    "context",
    "querySupport",
    "endPoint"
})
public class Provider {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String serviceType;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected String serviceName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected Provider.Context context;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected Provider.QuerySupport querySupport;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected ProtocolType endPoint;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceType(String value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceName(String value) {
        this.serviceName = value;
    }

    /**
     * Gets the value of the context property.
     * 
     * @return
     *     possible object is
     *     {@link Provider.Context }
     *     
     */
    public Provider.Context getContext() {
        return context;
    }

    /**
     * Sets the value of the context property.
     * 
     * @param value
     *     allowed object is
     *     {@link Provider.Context }
     *     
     */
    public void setContext(Provider.Context value) {
        this.context = value;
    }

    /**
     * Gets the value of the querySupport property.
     * 
     * @return
     *     possible object is
     *     {@link Provider.QuerySupport }
     *     
     */
    public Provider.QuerySupport getQuerySupport() {
        return querySupport;
    }

    /**
     * Sets the value of the querySupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link Provider.QuerySupport }
     *     
     */
    public void setQuerySupport(Provider.QuerySupport value) {
        this.querySupport = value;
    }

    /**
     * Gets the value of the endPoint property.
     * 
     * @return
     *     possible object is
     *     {@link ProtocolType }
     *     
     */
    public ProtocolType getEndPoint() {
        return endPoint;
    }

    /**
     * Sets the value of the endPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProtocolType }
     *     
     */
    public void setEndPoint(ProtocolType value) {
        this.endPoint = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
     *         &lt;element name="contextName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="zoneID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="providername" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="localename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="localeVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "contextName",
        "zoneID",
        "providername",
        "localename",
        "localeVersion"
    })
    public static class Context {

        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected String contextName;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected String zoneID;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected String providername;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
        protected String localename;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
        protected String localeVersion;

        /**
         * Gets the value of the contextName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getContextName() {
            return contextName;
        }

        /**
         * Sets the value of the contextName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setContextName(String value) {
            this.contextName = value;
        }

        /**
         * Gets the value of the zoneID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZoneID() {
            return zoneID;
        }

        /**
         * Sets the value of the zoneID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZoneID(String value) {
            this.zoneID = value;
        }

        /**
         * Gets the value of the providername property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProvidername() {
            return providername;
        }

        /**
         * Sets the value of the providername property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProvidername(String value) {
            this.providername = value;
        }

        /**
         * Gets the value of the localename property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLocalename() {
            return localename;
        }

        /**
         * Sets the value of the localename property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLocalename(String value) {
            this.localename = value;
        }

        /**
         * Gets the value of the localeVersion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLocaleVersion() {
            return localeVersion;
        }

        /**
         * Sets the value of the localeVersion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLocaleVersion(String value) {
            this.localeVersion = value;
        }

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
     *         &lt;element name="singular" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="formula" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="paged" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="maxPageSize" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="applicationProduct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="adapterProduct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "singular",
        "formula",
        "paged",
        "maxPageSize",
        "applicationProduct",
        "adapterProduct"
    })
    public static class QuerySupport {

        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected String singular;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected String formula;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected String paged;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected String maxPageSize;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
        protected String applicationProduct;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
        protected String adapterProduct;

        /**
         * Gets the value of the singular property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSingular() {
            return singular;
        }

        /**
         * Sets the value of the singular property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSingular(String value) {
            this.singular = value;
        }

        /**
         * Gets the value of the formula property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFormula() {
            return formula;
        }

        /**
         * Sets the value of the formula property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFormula(String value) {
            this.formula = value;
        }

        /**
         * Gets the value of the paged property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPaged() {
            return paged;
        }

        /**
         * Sets the value of the paged property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPaged(String value) {
            this.paged = value;
        }

        /**
         * Gets the value of the maxPageSize property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMaxPageSize() {
            return maxPageSize;
        }

        /**
         * Sets the value of the maxPageSize property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMaxPageSize(String value) {
            this.maxPageSize = value;
        }

        /**
         * Gets the value of the applicationProduct property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getApplicationProduct() {
            return applicationProduct;
        }

        /**
         * Sets the value of the applicationProduct property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setApplicationProduct(String value) {
            this.applicationProduct = value;
        }

        /**
         * Gets the value of the adapterProduct property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdapterProduct() {
            return adapterProduct;
        }

        /**
         * Sets the value of the adapterProduct property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdapterProduct(String value) {
            this.adapterProduct = value;
        }

    }

}
