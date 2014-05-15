
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Sif3AssessmentSessionCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sif3AssessmentSessionCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sif3AssessmentSession" type="{http://www.sifassociation.org/au/datamodel/1.3}Sif3AssessmentSessionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sif3AssessmentSessionCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "sif3AssessmentSession"
})
public class Sif3AssessmentSessionCollectionType {

    @XmlElement(name = "Sif3AssessmentSession", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<Sif3AssessmentSessionType> sif3AssessmentSession;

    /**
     * Gets the value of the sif3AssessmentSession property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sif3AssessmentSession property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSif3AssessmentSession().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sif3AssessmentSessionType }
     * 
     * 
     */
    public List<Sif3AssessmentSessionType> getSif3AssessmentSession() {
        if (sif3AssessmentSession == null) {
            sif3AssessmentSession = new ArrayList<Sif3AssessmentSessionType>();
        }
        return this.sif3AssessmentSession;
    }

}
