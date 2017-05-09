
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The Consumer Environment Service is the gateway to all other services. It scopes the Consumer’s possible interactions with the infrastructure and any Provider Services accessible from it.  The Environment Service provides the set of (possibly customized) information the Application needs to interoperate successfully.
 * 
 * <p>Java class for environmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="environmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fingerprint" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="sessionToken" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="solutionId" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="defaultZone" type="{http://www.sifassociation.org/infrastructure/3.2.1}zoneType" minOccurs="0"/>
 *         &lt;element name="authenticationMethod" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="instanceId" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="userToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consumerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applicationInfo" type="{http://www.sifassociation.org/infrastructure/3.2.1}applicationInfoType" minOccurs="0"/>
 *         &lt;element name="infrastructureServices" type="{http://www.sifassociation.org/infrastructure/3.2.1}infrastructureServicesType" minOccurs="0"/>
 *         &lt;element name="provisionedZones" type="{http://www.sifassociation.org/infrastructure/3.2.1}provisionedZonesType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" type="{http://www.sifassociation.org/infrastructure/3.2.1}environmentTypeType" />
 *       &lt;attribute name="id" type="{http://www.sifassociation.org/infrastructure/3.2.1}uuidType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "environmentType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "fingerprint",
    "sessionToken",
    "solutionId",
    "defaultZone",
    "authenticationMethod",
    "instanceId",
    "userToken",
    "consumerName",
    "applicationInfo",
    "infrastructureServices",
    "provisionedZones"
})
public class EnvironmentType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String fingerprint;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sessionToken;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String solutionId;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected ZoneType defaultZone;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String authenticationMethod;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String instanceId;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected String userToken;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected String consumerName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected ApplicationInfoType applicationInfo;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected InfrastructureServicesType infrastructureServices;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected ProvisionedZonesType provisionedZones;
    @XmlAttribute(name = "type")
    protected EnvironmentTypeType type;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;

    /**
     * Gets the value of the fingerprint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFingerprint() {
        return fingerprint;
    }

    /**
     * Sets the value of the fingerprint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFingerprint(String value) {
        this.fingerprint = value;
    }

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
     * Gets the value of the defaultZone property.
     * 
     * @return
     *     possible object is
     *     {@link ZoneType }
     *     
     */
    public ZoneType getDefaultZone() {
        return defaultZone;
    }

    /**
     * Sets the value of the defaultZone property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZoneType }
     *     
     */
    public void setDefaultZone(ZoneType value) {
        this.defaultZone = value;
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
     *     {@link ApplicationInfoType }
     *     
     */
    public ApplicationInfoType getApplicationInfo() {
        return applicationInfo;
    }

    /**
     * Sets the value of the applicationInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationInfoType }
     *     
     */
    public void setApplicationInfo(ApplicationInfoType value) {
        this.applicationInfo = value;
    }

    /**
     * Gets the value of the infrastructureServices property.
     * 
     * @return
     *     possible object is
     *     {@link InfrastructureServicesType }
     *     
     */
    public InfrastructureServicesType getInfrastructureServices() {
        return infrastructureServices;
    }

    /**
     * Sets the value of the infrastructureServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link InfrastructureServicesType }
     *     
     */
    public void setInfrastructureServices(InfrastructureServicesType value) {
        this.infrastructureServices = value;
    }

    /**
     * Gets the value of the provisionedZones property.
     * 
     * @return
     *     possible object is
     *     {@link ProvisionedZonesType }
     *     
     */
    public ProvisionedZonesType getProvisionedZones() {
        return provisionedZones;
    }

    /**
     * Sets the value of the provisionedZones property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProvisionedZonesType }
     *     
     */
    public void setProvisionedZones(ProvisionedZonesType value) {
        this.provisionedZones = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link EnvironmentTypeType }
     *     
     */
    public EnvironmentTypeType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvironmentTypeType }
     *     
     */
    public void setType(EnvironmentTypeType value) {
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

}
