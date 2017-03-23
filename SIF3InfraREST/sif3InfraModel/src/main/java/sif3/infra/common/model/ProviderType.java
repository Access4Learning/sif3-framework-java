
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
 * All potentially accessible Services have an entry in the Providers Registry (including the Providers Registry Utility Service itself), although full or even partial Consumer access to that Service is determined by the access rights currently granted in the Consumer’s Environment object, and is not guaranteed.
 * 
 * <p>Java class for providerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="providerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceType" type="{http://www.sifassociation.org/infrastructure/3.2.1}serviceTypeType"/>
 *         &lt;element name="serviceName" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="contextId" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="zoneId" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="providerName" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="querySupport" type="{http://www.sifassociation.org/infrastructure/3.2.1}querySupportType"/>
 *         &lt;element name="mimeTypes" type="{http://www.sifassociation.org/infrastructure/3.2.1}mediaTypesType" minOccurs="0"/>
 *         &lt;element name="endPoint" type="{http://www.sifassociation.org/infrastructure/3.2.1}protocolType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.sifassociation.org/infrastructure/3.2.1}uuidType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "providerType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "serviceType",
    "serviceName",
    "contextId",
    "zoneId",
    "providerName",
    "querySupport",
    "mimeTypes",
    "endPoint"
})
public class ProviderType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String serviceType;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String serviceName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String contextId;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String zoneId;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String providerName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    protected QuerySupportType querySupport;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected MediaTypesType mimeTypes;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
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
     * Gets the value of the contextId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContextId() {
        return contextId;
    }

    /**
     * Sets the value of the contextId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextId(String value) {
        this.contextId = value;
    }

    /**
     * Gets the value of the zoneId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZoneId() {
        return zoneId;
    }

    /**
     * Sets the value of the zoneId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZoneId(String value) {
        this.zoneId = value;
    }

    /**
     * Gets the value of the providerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * Sets the value of the providerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderName(String value) {
        this.providerName = value;
    }

    /**
     * Gets the value of the querySupport property.
     * 
     * @return
     *     possible object is
     *     {@link QuerySupportType }
     *     
     */
    public QuerySupportType getQuerySupport() {
        return querySupport;
    }

    /**
     * Sets the value of the querySupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuerySupportType }
     *     
     */
    public void setQuerySupport(QuerySupportType value) {
        this.querySupport = value;
    }

    /**
     * Gets the value of the mimeTypes property.
     * 
     * @return
     *     possible object is
     *     {@link MediaTypesType }
     *     
     */
    public MediaTypesType getMimeTypes() {
        return mimeTypes;
    }

    /**
     * Sets the value of the mimeTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link MediaTypesType }
     *     
     */
    public void setMimeTypes(MediaTypesType value) {
        this.mimeTypes = value;
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

}
