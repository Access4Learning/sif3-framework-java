
package systemic.sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="sessionToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="solutionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultZoneId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authenticationMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="instanceId" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="userToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consumerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="applicationInfo">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="applicationKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="supportedInfrastructureVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="supportedDataModel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="supportedDataModelVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="transport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="applicationProduct" type="{http://www.sifassociation.org/infrastructure/3.0}productIdentityType"/>
 *                   &lt;element name="adapterProduct" type="{http://www.sifassociation.org/infrastructure/3.0}productIdentityType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="infrastructureServices" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="infrastructureService" type="{http://www.sifassociation.org/infrastructure/3.0}propertyType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="provisionedZones" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="provisionedZone" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="services" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="service" type="{http://www.sifassociation.org/infrastructure/3.0}serviceIndexType" maxOccurs="unbounded"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
 *       &lt;attribute name="type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="DIRECT"/>
 *             &lt;enumeration value="BROKERED"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="id" type="{http://www.sifassociation.org/infrastructure/3.0}uuidType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sessionToken",
    "solutionId",
    "defaultZoneId",
    "authenticationMethod",
    "instanceId",
    "userToken",
    "consumerName",
    "applicationInfo",
    "infrastructureServices",
    "provisionedZones"
})
@XmlRootElement(name = "environment", namespace = "http://www.sifassociation.org/infrastructure/3.0")
public class Environment {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected String sessionToken;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected String solutionId;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected String defaultZoneId;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected String authenticationMethod;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String instanceId;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected String userToken;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected String consumerName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected Environment.ApplicationInfo applicationInfo;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected Environment.InfrastructureServices infrastructureServices;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected Environment.ProvisionedZones provisionedZones;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;

    /**
     * Gets the value of the sessionToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionToken() {
        return sessionToken;
    }

    /**
     * Sets the value of the sessionToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionToken(String value) {
        this.sessionToken = value;
    }

    /**
     * Gets the value of the solutionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolutionId() {
        return solutionId;
    }

    /**
     * Sets the value of the solutionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolutionId(String value) {
        this.solutionId = value;
    }

    /**
     * Gets the value of the defaultZoneId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultZoneId() {
        return defaultZoneId;
    }

    /**
     * Sets the value of the defaultZoneId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultZoneId(String value) {
        this.defaultZoneId = value;
    }

    /**
     * Gets the value of the authenticationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    /**
     * Sets the value of the authenticationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationMethod(String value) {
        this.authenticationMethod = value;
    }

    /**
     * Gets the value of the instanceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * Sets the value of the instanceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceId(String value) {
        this.instanceId = value;
    }

    /**
     * Gets the value of the userToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * Sets the value of the userToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserToken(String value) {
        this.userToken = value;
    }

    /**
     * Gets the value of the consumerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerName() {
        return consumerName;
    }

    /**
     * Sets the value of the consumerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerName(String value) {
        this.consumerName = value;
    }

    /**
     * Gets the value of the applicationInfo property.
     * 
     * @return
     *     possible object is
     *     {@link Environment.ApplicationInfo }
     *     
     */
    public Environment.ApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    /**
     * Sets the value of the applicationInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Environment.ApplicationInfo }
     *     
     */
    public void setApplicationInfo(Environment.ApplicationInfo value) {
        this.applicationInfo = value;
    }

    /**
     * Gets the value of the infrastructureServices property.
     * 
     * @return
     *     possible object is
     *     {@link Environment.InfrastructureServices }
     *     
     */
    public Environment.InfrastructureServices getInfrastructureServices() {
        return infrastructureServices;
    }

    /**
     * Sets the value of the infrastructureServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link Environment.InfrastructureServices }
     *     
     */
    public void setInfrastructureServices(Environment.InfrastructureServices value) {
        this.infrastructureServices = value;
    }

    /**
     * Gets the value of the provisionedZones property.
     * 
     * @return
     *     possible object is
     *     {@link Environment.ProvisionedZones }
     *     
     */
    public Environment.ProvisionedZones getProvisionedZones() {
        return provisionedZones;
    }

    /**
     * Sets the value of the provisionedZones property.
     * 
     * @param value
     *     allowed object is
     *     {@link Environment.ProvisionedZones }
     *     
     */
    public void setProvisionedZones(Environment.ProvisionedZones value) {
        this.provisionedZones = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
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
     *         &lt;element name="applicationKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="supportedInfrastructureVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="supportedDataModel" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="supportedDataModelVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="transport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="applicationProduct" type="{http://www.sifassociation.org/infrastructure/3.0}productIdentityType"/>
     *         &lt;element name="adapterProduct" type="{http://www.sifassociation.org/infrastructure/3.0}productIdentityType"/>
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
        "applicationKey",
        "supportedInfrastructureVersion",
        "supportedDataModel",
        "supportedDataModelVersion",
        "transport",
        "applicationProduct",
        "adapterProduct"
    })
    public static class ApplicationInfo {

        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected String applicationKey;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected String supportedInfrastructureVersion;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected String supportedDataModel;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected String supportedDataModelVersion;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
        protected String transport;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected ProductIdentityType applicationProduct;
        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected ProductIdentityType adapterProduct;

        /**
         * Gets the value of the applicationKey property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getApplicationKey() {
            return applicationKey;
        }

        /**
         * Sets the value of the applicationKey property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setApplicationKey(String value) {
            this.applicationKey = value;
        }

        /**
         * Gets the value of the supportedInfrastructureVersion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSupportedInfrastructureVersion() {
            return supportedInfrastructureVersion;
        }

        /**
         * Sets the value of the supportedInfrastructureVersion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSupportedInfrastructureVersion(String value) {
            this.supportedInfrastructureVersion = value;
        }

        /**
         * Gets the value of the supportedDataModel property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSupportedDataModel() {
            return supportedDataModel;
        }

        /**
         * Sets the value of the supportedDataModel property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSupportedDataModel(String value) {
            this.supportedDataModel = value;
        }

        /**
         * Gets the value of the supportedDataModelVersion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSupportedDataModelVersion() {
            return supportedDataModelVersion;
        }

        /**
         * Sets the value of the supportedDataModelVersion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSupportedDataModelVersion(String value) {
            this.supportedDataModelVersion = value;
        }

        /**
         * Gets the value of the transport property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTransport() {
            return transport;
        }

        /**
         * Sets the value of the transport property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTransport(String value) {
            this.transport = value;
        }

        /**
         * Gets the value of the applicationProduct property.
         * 
         * @return
         *     possible object is
         *     {@link ProductIdentityType }
         *     
         */
        public ProductIdentityType getApplicationProduct() {
            return applicationProduct;
        }

        /**
         * Sets the value of the applicationProduct property.
         * 
         * @param value
         *     allowed object is
         *     {@link ProductIdentityType }
         *     
         */
        public void setApplicationProduct(ProductIdentityType value) {
            this.applicationProduct = value;
        }

        /**
         * Gets the value of the adapterProduct property.
         * 
         * @return
         *     possible object is
         *     {@link ProductIdentityType }
         *     
         */
        public ProductIdentityType getAdapterProduct() {
            return adapterProduct;
        }

        /**
         * Sets the value of the adapterProduct property.
         * 
         * @param value
         *     allowed object is
         *     {@link ProductIdentityType }
         *     
         */
        public void setAdapterProduct(ProductIdentityType value) {
            this.adapterProduct = value;
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
     *         &lt;element name="infrastructureService" type="{http://www.sifassociation.org/infrastructure/3.0}propertyType" maxOccurs="unbounded"/>
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
        "infrastructureService"
    })
    public static class InfrastructureServices {

        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected List<PropertyType> infrastructureService;

        /**
         * Gets the value of the infrastructureService property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the infrastructureService property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInfrastructureService().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PropertyType }
         * 
         * 
         */
        public List<PropertyType> getInfrastructureService() {
            if (infrastructureService == null) {
                infrastructureService = new ArrayList<PropertyType>();
            }
            return this.infrastructureService;
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
     *         &lt;element name="provisionedZone" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="services" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="service" type="{http://www.sifassociation.org/infrastructure/3.0}serviceIndexType" maxOccurs="unbounded"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        "provisionedZone"
    })
    public static class ProvisionedZones {

        @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
        protected List<Environment.ProvisionedZones.ProvisionedZone> provisionedZone;

        /**
         * Gets the value of the provisionedZone property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the provisionedZone property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProvisionedZone().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Environment.ProvisionedZones.ProvisionedZone }
         * 
         * 
         */
        public List<Environment.ProvisionedZones.ProvisionedZone> getProvisionedZone() {
            if (provisionedZone == null) {
                provisionedZone = new ArrayList<Environment.ProvisionedZones.ProvisionedZone>();
            }
            return this.provisionedZone;
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
         *         &lt;element name="services" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="service" type="{http://www.sifassociation.org/infrastructure/3.0}serviceIndexType" maxOccurs="unbounded"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "services"
        })
        public static class ProvisionedZone {

            @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
            protected Environment.ProvisionedZones.ProvisionedZone.Services services;
            @XmlAttribute(name = "id", required = true)
            protected String id;

            /**
             * Gets the value of the services property.
             * 
             * @return
             *     possible object is
             *     {@link Environment.ProvisionedZones.ProvisionedZone.Services }
             *     
             */
            public Environment.ProvisionedZones.ProvisionedZone.Services getServices() {
                return services;
            }

            /**
             * Sets the value of the services property.
             * 
             * @param value
             *     allowed object is
             *     {@link Environment.ProvisionedZones.ProvisionedZone.Services }
             *     
             */
            public void setServices(Environment.ProvisionedZones.ProvisionedZone.Services value) {
                this.services = value;
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
             *         &lt;element name="service" type="{http://www.sifassociation.org/infrastructure/3.0}serviceIndexType" maxOccurs="unbounded"/>
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
                "service"
            })
            public static class Services {

                @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
                protected List<ServiceIndexType> service;

                /**
                 * Gets the value of the service property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the service property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getService().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ServiceIndexType }
                 * 
                 * 
                 */
                public List<ServiceIndexType> getService() {
                    if (service == null) {
                        service = new ArrayList<ServiceIndexType>();
                    }
                    return this.service;
                }

            }

        }

    }

}
