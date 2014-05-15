
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Sif3StudentResponseSetCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sif3StudentResponseSetCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sif3StudentResponseSet" type="{http://www.sifassociation.org/au/datamodel/1.3}Sif3StudentResponseSetType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sif3StudentResponseSetCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "sif3StudentResponseSet"
})
public class Sif3StudentResponseSetCollectionType {

    @XmlElement(name = "Sif3StudentResponseSet", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<Sif3StudentResponseSetType> sif3StudentResponseSet;

    /**
     * Gets the value of the sif3StudentResponseSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sif3StudentResponseSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSif3StudentResponseSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sif3StudentResponseSetType }
     * 
     * 
     */
    public List<Sif3StudentResponseSetType> getSif3StudentResponseSet() {
        if (sif3StudentResponseSet == null) {
            sif3StudentResponseSet = new ArrayList<Sif3StudentResponseSetType>();
        }
        return this.sif3StudentResponseSet;
    }

}
