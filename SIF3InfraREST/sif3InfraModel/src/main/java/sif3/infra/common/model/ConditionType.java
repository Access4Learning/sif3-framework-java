
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 
 * <p>Java class for conditionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="conditionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="typeOfCondition" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="conditionName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="conditionDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="propertyList" type="{http://www.sifassociation.org/infrastructure/3.3}propertyNVListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "conditionType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "typeOfCondition",
    "conditionName",
    "conditionDescription",
    "propertyList"
})
public class ConditionType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String typeOfCondition;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String conditionName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String conditionDescription;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected PropertyNVListType propertyList;

    /**
     * Gets the value of the typeOfCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOfCondition() {
        return typeOfCondition;
    }

    /**
     * Sets the value of the typeOfCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOfCondition(String value) {
        this.typeOfCondition = value;
    }

    /**
     * Gets the value of the conditionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditionName() {
        return conditionName;
    }

    /**
     * Sets the value of the conditionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditionName(String value) {
        this.conditionName = value;
    }

    /**
     * Gets the value of the conditionDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditionDescription() {
        return conditionDescription;
    }

    /**
     * Sets the value of the conditionDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditionDescription(String value) {
        this.conditionDescription = value;
    }

    /**
     * Gets the value of the propertyList property.
     * 
     * @return
     *     possible object is
     *     {@link PropertyNVListType }
     *     
     */
    public PropertyNVListType getPropertyList() {
        return propertyList;
    }

    /**
     * Sets the value of the propertyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyNVListType }
     *     
     */
    public void setPropertyList(PropertyNVListType value) {
        this.propertyList = value;
    }

}
