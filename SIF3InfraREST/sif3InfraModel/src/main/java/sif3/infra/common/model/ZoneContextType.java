
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A Zone and/or Context related to a POD.
 * 
 * <p>Java class for zoneContextType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zoneContextType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zoneId" type="{http://www.sifassociation.org/infrastructure/3.3}gCoreLocalIdType" minOccurs="0"/>
 *         &lt;element name="contextId" type="{http://www.sifassociation.org/infrastructure/3.3}gCoreLocalIdType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zoneContextType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "zoneId",
    "contextId"
})
public class ZoneContextType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String zoneId;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String contextId;

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

}
