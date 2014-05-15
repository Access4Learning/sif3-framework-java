
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StudentSnapshotCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentSnapshotCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StudentSnapshot" type="{http://www.sifassociation.org/au/datamodel/1.3}StudentSnapshotType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentSnapshotCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "studentSnapshot"
})
public class StudentSnapshotCollectionType {

    @XmlElement(name = "StudentSnapshot", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<StudentSnapshotType> studentSnapshot;

    /**
     * Gets the value of the studentSnapshot property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the studentSnapshot property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStudentSnapshot().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StudentSnapshotType }
     * 
     * 
     */
    public List<StudentSnapshotType> getStudentSnapshot() {
        if (studentSnapshot == null) {
            studentSnapshot = new ArrayList<StudentSnapshotType>();
        }
        return this.studentSnapshot;
    }

}
