
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StudentScoreSetCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentScoreSetCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StudentScoreSet" type="{http://www.sifassociation.org/au/datamodel/1.3}StudentScoreSetType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentScoreSetCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "studentScoreSet"
})
public class StudentScoreSetCollectionType {

    @XmlElement(name = "StudentScoreSet", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<StudentScoreSetType> studentScoreSet;

    /**
     * Gets the value of the studentScoreSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the studentScoreSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStudentScoreSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StudentScoreSetType }
     * 
     * 
     */
    public List<StudentScoreSetType> getStudentScoreSet() {
        if (studentScoreSet == null) {
            studentScoreSet = new ArrayList<StudentScoreSetType>();
        }
        return this.studentScoreSet;
    }

}
