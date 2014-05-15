
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResourceUsageCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResourceUsageCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResourceUsage" type="{http://www.sifassociation.org/au/datamodel/1.3}ResourceUsageType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceUsageCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "resourceUsage"
})
public class ResourceUsageCollectionType {

    @XmlElement(name = "ResourceUsage", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<ResourceUsageType> resourceUsage;

    /**
     * Gets the value of the resourceUsage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceUsage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceUsage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceUsageType }
     * 
     * 
     */
    public List<ResourceUsageType> getResourceUsage() {
        if (resourceUsage == null) {
            resourceUsage = new ArrayList<ResourceUsageType>();
        }
        return this.resourceUsage;
    }

}
