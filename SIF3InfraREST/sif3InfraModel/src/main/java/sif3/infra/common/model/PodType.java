
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * This object contains information about privacy obligations.
 * 
 * <p>Java class for podType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="podType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="podStatus">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="Live"/>
 *               &lt;enumeration value="Draft"/>
 *               &lt;enumeration value="Pending"/>
 *               &lt;enumeration value="Expired"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="podToken" type="{http://www.sifassociation.org/infrastructure/3.3}gCoreLocalIdType"/>
 *         &lt;element name="podVersion" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="partyId" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="privacyList" type="{http://www.sifassociation.org/infrastructure/3.3}privacyListType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.sifassociation.org/infrastructure/3.3}uuidType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "podType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "podStatus",
    "podToken",
    "podVersion",
    "partyId",
    "privacyList"
})
public class PodType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String podStatus;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String podToken;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String podVersion;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String partyId;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    protected PrivacyListType privacyList;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;

    /**
     * Gets the value of the podStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodStatus() {
        return podStatus;
    }

    /**
     * Sets the value of the podStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodStatus(String value) {
        this.podStatus = value;
    }

    /**
     * Gets the value of the podToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodToken() {
        return podToken;
    }

    /**
     * Sets the value of the podToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodToken(String value) {
        this.podToken = value;
    }

    /**
     * Gets the value of the podVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodVersion() {
        return podVersion;
    }

    /**
     * Sets the value of the podVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodVersion(String value) {
        this.podVersion = value;
    }

    /**
     * Gets the value of the partyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     * Sets the value of the partyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartyId(String value) {
        this.partyId = value;
    }

    /**
     * Gets the value of the privacyList property.
     * 
     * @return
     *     possible object is
     *     {@link PrivacyListType }
     *     
     */
    public PrivacyListType getPrivacyList() {
        return privacyList;
    }

    /**
     * Sets the value of the privacyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrivacyListType }
     *     
     */
    public void setPrivacyList(PrivacyListType value) {
        this.privacyList = value;
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
