
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A list of subject areas.
 * 
 * <p>Java class for SubjectAreaListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubjectAreaListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}SubjectArea" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectAreaListType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "subjectArea"
})
public class SubjectAreaListType {

    @XmlElement(name = "SubjectArea", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<SubjectAreaType> subjectArea;

    /**
     * Gets the value of the subjectArea property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subjectArea property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubjectArea().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubjectAreaType }
     * 
     * 
     */
    public List<SubjectAreaType> getSubjectArea() {
        if (subjectArea == null) {
            subjectArea = new ArrayList<SubjectAreaType>();
        }
        return this.subjectArea;
    }

}
