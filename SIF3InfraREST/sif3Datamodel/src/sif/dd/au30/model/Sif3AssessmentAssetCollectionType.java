
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Sif3AssessmentAssetCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sif3AssessmentAssetCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sif3AssessmentAsset" type="{http://www.sifassociation.org/au/datamodel/1.3}Sif3AssessmentAssetType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sif3AssessmentAssetCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "sif3AssessmentAsset"
})
public class Sif3AssessmentAssetCollectionType {

    @XmlElement(name = "Sif3AssessmentAsset", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<Sif3AssessmentAssetType> sif3AssessmentAsset;

    /**
     * Gets the value of the sif3AssessmentAsset property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sif3AssessmentAsset property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSif3AssessmentAsset().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sif3AssessmentAssetType }
     * 
     * 
     */
    public List<Sif3AssessmentAssetType> getSif3AssessmentAsset() {
        if (sif3AssessmentAsset == null) {
            sif3AssessmentAsset = new ArrayList<Sif3AssessmentAssetType>();
        }
        return this.sif3AssessmentAsset;
    }

}
