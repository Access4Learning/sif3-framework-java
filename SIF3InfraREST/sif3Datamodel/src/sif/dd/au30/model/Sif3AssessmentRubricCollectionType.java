
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Sif3AssessmentRubricCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sif3AssessmentRubricCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sif3AssessmentRubric" type="{http://www.sifassociation.org/au/datamodel/1.3}Sif3AssessmentRubricType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sif3AssessmentRubricCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "sif3AssessmentRubric"
})
public class Sif3AssessmentRubricCollectionType {

    @XmlElement(name = "Sif3AssessmentRubric", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<Sif3AssessmentRubricType> sif3AssessmentRubric;

    /**
     * Gets the value of the sif3AssessmentRubric property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sif3AssessmentRubric property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSif3AssessmentRubric().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sif3AssessmentRubricType }
     * 
     * 
     */
    public List<Sif3AssessmentRubricType> getSif3AssessmentRubric() {
        if (sif3AssessmentRubric == null) {
            sif3AssessmentRubric = new ArrayList<Sif3AssessmentRubricType>();
        }
        return this.sif3AssessmentRubric;
    }

}
