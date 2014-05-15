
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Sif3AssessmentItemCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sif3AssessmentItemCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sif3AssessmentItem" type="{http://www.sifassociation.org/au/datamodel/1.3}Sif3AssessmentItemType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sif3AssessmentItemCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "sif3AssessmentItem"
})
public class Sif3AssessmentItemCollectionType {

    @XmlElement(name = "Sif3AssessmentItem", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<Sif3AssessmentItemType> sif3AssessmentItem;

    /**
     * Gets the value of the sif3AssessmentItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sif3AssessmentItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSif3AssessmentItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sif3AssessmentItemType }
     * 
     * 
     */
    public List<Sif3AssessmentItemType> getSif3AssessmentItem() {
        if (sif3AssessmentItem == null) {
            sif3AssessmentItem = new ArrayList<Sif3AssessmentItemType>();
        }
        return this.sif3AssessmentItem;
    }

}
