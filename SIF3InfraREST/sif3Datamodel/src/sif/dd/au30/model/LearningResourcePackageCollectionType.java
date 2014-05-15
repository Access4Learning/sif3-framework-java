
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LearningResourcePackageCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LearningResourcePackageCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LearningResourcePackage" type="{http://www.sifassociation.org/au/datamodel/1.3}LearningResourcePackageType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LearningResourcePackageCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "learningResourcePackage"
})
public class LearningResourcePackageCollectionType {

    @XmlElement(name = "LearningResourcePackage", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<LearningResourcePackageType> learningResourcePackage;

    /**
     * Gets the value of the learningResourcePackage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the learningResourcePackage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLearningResourcePackage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LearningResourcePackageType }
     * 
     * 
     */
    public List<LearningResourcePackageType> getLearningResourcePackage() {
        if (learningResourcePackage == null) {
            learningResourcePackage = new ArrayList<LearningResourcePackageType>();
        }
        return this.learningResourcePackage;
    }

}
