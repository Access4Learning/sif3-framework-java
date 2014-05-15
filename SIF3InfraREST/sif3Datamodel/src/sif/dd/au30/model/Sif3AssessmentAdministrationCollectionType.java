
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Sif3AssessmentAdministrationCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sif3AssessmentAdministrationCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sif3AssessmentAdministration" type="{http://www.sifassociation.org/au/datamodel/1.3}Sif3AssessmentAdministrationType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sif3AssessmentAdministrationCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "sif3AssessmentAdministration"
})
public class Sif3AssessmentAdministrationCollectionType {

    @XmlElement(name = "Sif3AssessmentAdministration", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<Sif3AssessmentAdministrationType> sif3AssessmentAdministration;

    /**
     * Gets the value of the sif3AssessmentAdministration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sif3AssessmentAdministration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSif3AssessmentAdministration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sif3AssessmentAdministrationType }
     * 
     * 
     */
    public List<Sif3AssessmentAdministrationType> getSif3AssessmentAdministration() {
        if (sif3AssessmentAdministration == null) {
            sif3AssessmentAdministration = new ArrayList<Sif3AssessmentAdministrationType>();
        }
        return this.sif3AssessmentAdministration;
    }

}
