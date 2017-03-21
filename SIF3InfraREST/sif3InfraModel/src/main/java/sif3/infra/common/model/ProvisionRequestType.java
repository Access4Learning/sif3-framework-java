
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * If an Environment supports self-provisioning Consumers than either this section must initially contain all the rights the Consumer has to all Services in all Zones, or the provisionRequests Infrastructure Service must be present in the Consumer’s Environment to allow additional rights to be obtained.
 * 
 * <p>Java class for provisionRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="provisionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="provisionedZones" type="{http://www.sifassociation.org/infrastructure/3.2.1}provisionedZonesType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.sifassociation.org/infrastructure/3.2.1}uuidType" />
 *       &lt;attribute name="completionStatus">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;enumeration value="ACCEPTED"/>
 *             &lt;enumeration value="MIXED"/>
 *             &lt;enumeration value="REJECTED"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "provisionRequestType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "provisionedZones"
})
public class ProvisionRequestType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    protected ProvisionedZonesType provisionedZones;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;
    @XmlAttribute(name = "completionStatus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String completionStatus;

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
     * Gets the value of the completionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompletionStatus() {
        return completionStatus;
    }

    /**
     * Sets the value of the completionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompletionStatus(String value) {
        this.completionStatus = value;
    }

}
