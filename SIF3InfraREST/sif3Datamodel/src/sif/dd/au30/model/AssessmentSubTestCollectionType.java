
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssessmentSubTestCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssessmentSubTestCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssessmentSubTest" type="{http://www.sifassociation.org/au/datamodel/1.3}AssessmentSubTestType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssessmentSubTestCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "assessmentSubTest"
})
public class AssessmentSubTestCollectionType {

    @XmlElement(name = "AssessmentSubTest", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<AssessmentSubTestType> assessmentSubTest;

    /**
     * Gets the value of the assessmentSubTest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assessmentSubTest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssessmentSubTest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssessmentSubTestType }
     * 
     * 
     */
    public List<AssessmentSubTestType> getAssessmentSubTest() {
        if (assessmentSubTest == null) {
            assessmentSubTest = new ArrayList<AssessmentSubTestType>();
        }
        return this.assessmentSubTest;
    }

}
