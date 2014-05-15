
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Sif3AssessmentSubTestCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sif3AssessmentSubTestCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sif3AssessmentSubTest" type="{http://www.sifassociation.org/au/datamodel/1.3}Sif3AssessmentSubTestType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sif3AssessmentSubTestCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "sif3AssessmentSubTest"
})
public class Sif3AssessmentSubTestCollectionType {

    @XmlElement(name = "Sif3AssessmentSubTest", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<Sif3AssessmentSubTestType> sif3AssessmentSubTest;

    /**
     * Gets the value of the sif3AssessmentSubTest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sif3AssessmentSubTest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSif3AssessmentSubTest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sif3AssessmentSubTestType }
     * 
     * 
     */
    public List<Sif3AssessmentSubTestType> getSif3AssessmentSubTest() {
        if (sif3AssessmentSubTest == null) {
            sif3AssessmentSubTest = new ArrayList<Sif3AssessmentSubTestType>();
        }
        return this.sif3AssessmentSubTest;
    }

}
