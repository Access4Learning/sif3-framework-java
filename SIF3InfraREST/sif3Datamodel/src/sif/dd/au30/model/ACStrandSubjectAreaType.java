
package sif.dd.au30.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Subject matter.
 * 
 * <p>Java class for ACStrandSubjectAreaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ACStrandSubjectAreaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ACStrand" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsACStrandType" minOccurs="0"/>
 *         &lt;element name="SubjectArea" type="{http://www.sifassociation.org/au/datamodel/1.3}SubjectAreaType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ACStrandSubjectAreaType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "acStrand",
    "subjectArea"
})
public class ACStrandSubjectAreaType {

    @XmlElement(name = "ACStrand", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected AUCodeSetsACStrandType acStrand;
    @XmlElementRef(name = "SubjectArea", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SubjectAreaType> subjectArea;

    /**
     * Gets the value of the acStrand property.
     * 
     * @return
     *     possible object is
     *     {@link AUCodeSetsACStrandType }
     *     
     */
    public AUCodeSetsACStrandType getACStrand() {
        return acStrand;
    }

    /**
     * Sets the value of the acStrand property.
     * 
     * @param value
     *     allowed object is
     *     {@link AUCodeSetsACStrandType }
     *     
     */
    public void setACStrand(AUCodeSetsACStrandType value) {
        this.acStrand = value;
    }

    /**
     * Gets the value of the subjectArea property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}
     *     
     */
    public JAXBElement<SubjectAreaType> getSubjectArea() {
        return subjectArea;
    }

    /**
     * Sets the value of the subjectArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}
     *     
     */
    public void setSubjectArea(JAXBElement<SubjectAreaType> value) {
        this.subjectArea = value;
    }

}
