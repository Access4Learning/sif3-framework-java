
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StudentParticipationCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentParticipationCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StudentParticipation" type="{http://www.sifassociation.org/au/datamodel/1.3}StudentParticipationType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentParticipationCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "studentParticipation"
})
public class StudentParticipationCollectionType {

    @XmlElement(name = "StudentParticipation", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<StudentParticipationType> studentParticipation;

    /**
     * Gets the value of the studentParticipation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the studentParticipation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStudentParticipation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StudentParticipationType }
     * 
     * 
     */
    public List<StudentParticipationType> getStudentParticipation() {
        if (studentParticipation == null) {
            studentParticipation = new ArrayList<StudentParticipationType>();
        }
        return this.studentParticipation;
    }

}
