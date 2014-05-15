
package sif.dd.au30.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Code that defines the relationship of one person to another.
 * 
 * <p>Java class for RelationshipType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RelationshipType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Code" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsRelationshipToStudentType" minOccurs="0"/>
 *         &lt;element name="OtherCodeList" type="{http://www.sifassociation.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelationshipType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "code",
    "otherCodeList"
})
public class RelationshipType {

    @XmlElement(name = "Code", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String code;
    @XmlElementRef(name = "OtherCodeList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<OtherCodeListType> otherCodeList;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the otherCodeList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}
     *     
     */
    public JAXBElement<OtherCodeListType> getOtherCodeList() {
        return otherCodeList;
    }

    /**
     * Sets the value of the otherCodeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}
     *     
     */
    public void setOtherCodeList(JAXBElement<OtherCodeListType> value) {
        this.otherCodeList = value;
    }

}
