
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * .
 * 
 * <p>Java class for privacyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="privacyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="default">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="Y"/>
 *               &lt;enumeration value="N"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="appIDList" type="{http://www.sifassociation.org/infrastructure/3.3}appIDListType" minOccurs="0"/>
 *         &lt;element name="adapterFingerprintList" type="{http://www.sifassociation.org/infrastructure/3.3}adapterFingerprintListType" minOccurs="0"/>
 *         &lt;element name="zoneContextList" type="{http://www.sifassociation.org/infrastructure/3.3}zoneContextListType" minOccurs="0"/>
 *         &lt;element name="endpointList" type="{http://www.sifassociation.org/infrastructure/3.3}endpointListType" minOccurs="0"/>
 *         &lt;element name="dataModelNamespace" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="dataModelVersionMin" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="dataModelVersionMax" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="privacyObligationsDocument" type="{http://www.sifassociation.org/infrastructure/3.3}privacyObligationsDocumentType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "privacyType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "_default",
    "appIDList",
    "adapterFingerprintList",
    "zoneContextList",
    "endpointList",
    "dataModelNamespace",
    "dataModelVersionMin",
    "dataModelVersionMax",
    "privacyObligationsDocument"
})
public class PrivacyType {

    @XmlElement(name = "default", namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String _default;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected AppIDListType appIDList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected AdapterFingerprintListType adapterFingerprintList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected ZoneContextListType zoneContextList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected EndpointListType endpointList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String dataModelNamespace;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String dataModelVersionMin;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String dataModelVersionMax;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected PrivacyObligationsDocumentType privacyObligationsDocument;

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefault(String value) {
        this._default = value;
    }

    /**
     * Gets the value of the appIDList property.
     * 
     * @return
     *     possible object is
     *     {@link AppIDListType }
     *     
     */
    public AppIDListType getAppIDList() {
        return appIDList;
    }

    /**
     * Sets the value of the appIDList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppIDListType }
     *     
     */
    public void setAppIDList(AppIDListType value) {
        this.appIDList = value;
    }

    /**
     * Gets the value of the adapterFingerprintList property.
     * 
     * @return
     *     possible object is
     *     {@link AdapterFingerprintListType }
     *     
     */
    public AdapterFingerprintListType getAdapterFingerprintList() {
        return adapterFingerprintList;
    }

    /**
     * Sets the value of the adapterFingerprintList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdapterFingerprintListType }
     *     
     */
    public void setAdapterFingerprintList(AdapterFingerprintListType value) {
        this.adapterFingerprintList = value;
    }

    /**
     * Gets the value of the zoneContextList property.
     * 
     * @return
     *     possible object is
     *     {@link ZoneContextListType }
     *     
     */
    public ZoneContextListType getZoneContextList() {
        return zoneContextList;
    }

    /**
     * Sets the value of the zoneContextList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZoneContextListType }
     *     
     */
    public void setZoneContextList(ZoneContextListType value) {
        this.zoneContextList = value;
    }

    /**
     * Gets the value of the endpointList property.
     * 
     * @return
     *     possible object is
     *     {@link EndpointListType }
     *     
     */
    public EndpointListType getEndpointList() {
        return endpointList;
    }

    /**
     * Sets the value of the endpointList property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndpointListType }
     *     
     */
    public void setEndpointList(EndpointListType value) {
        this.endpointList = value;
    }

    /**
     * Gets the value of the dataModelNamespace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataModelNamespace() {
        return dataModelNamespace;
    }

    /**
     * Sets the value of the dataModelNamespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataModelNamespace(String value) {
        this.dataModelNamespace = value;
    }

    /**
     * Gets the value of the dataModelVersionMin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataModelVersionMin() {
        return dataModelVersionMin;
    }

    /**
     * Sets the value of the dataModelVersionMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataModelVersionMin(String value) {
        this.dataModelVersionMin = value;
    }

    /**
     * Gets the value of the dataModelVersionMax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataModelVersionMax() {
        return dataModelVersionMax;
    }

    /**
     * Sets the value of the dataModelVersionMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataModelVersionMax(String value) {
        this.dataModelVersionMax = value;
    }

    /**
     * Gets the value of the privacyObligationsDocument property.
     * 
     * @return
     *     possible object is
     *     {@link PrivacyObligationsDocumentType }
     *     
     */
    public PrivacyObligationsDocumentType getPrivacyObligationsDocument() {
        return privacyObligationsDocument;
    }

    /**
     * Sets the value of the privacyObligationsDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrivacyObligationsDocumentType }
     *     
     */
    public void setPrivacyObligationsDocument(PrivacyObligationsDocumentType value) {
        this.privacyObligationsDocument = value;
    }

}
