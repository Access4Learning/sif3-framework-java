
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StudentSectionEnrollmentCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentSectionEnrollmentCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StudentSectionEnrollment" type="{http://www.sifassociation.org/au/datamodel/1.3}StudentSectionEnrollmentType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentSectionEnrollmentCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "studentSectionEnrollment"
})
public class StudentSectionEnrollmentCollectionType {

    @XmlElement(name = "StudentSectionEnrollment", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<StudentSectionEnrollmentType> studentSectionEnrollment;

    /**
     * Gets the value of the studentSectionEnrollment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the studentSectionEnrollment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStudentSectionEnrollment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StudentSectionEnrollmentType }
     * 
     * 
     */
    public List<StudentSectionEnrollmentType> getStudentSectionEnrollment() {
        if (studentSectionEnrollment == null) {
            studentSectionEnrollment = new ArrayList<StudentSectionEnrollmentType>();
        }
        return this.studentSectionEnrollment;
    }

}
