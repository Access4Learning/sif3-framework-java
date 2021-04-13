
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
 * Details of a field that can be accessed under this POD, equivalent to an element in a "green list".
 * 
 * <p>Java class for fieldType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fieldType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fieldName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="controlrights" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="R"/>
 *               &lt;enumeration value="RW"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
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
@XmlType(name = "fieldType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "fieldName",
    "controlrights"
})
public class FieldType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String fieldName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String controlrights;

    /**
     * Gets the value of the fieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets the value of the fieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldName(String value) {
        this.fieldName = value;
    }

    /**
     * Gets the value of the controlrights property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getControlrights() {
        return controlrights;
    }

    /**
     * Sets the value of the controlrights property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setControlrights(String value) {
        this.controlrights = value;
    }

}
