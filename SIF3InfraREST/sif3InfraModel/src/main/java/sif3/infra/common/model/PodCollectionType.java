
package sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for podCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="podCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pod" type="{http://www.sifassociation.org/infrastructure/3.3}podType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "podCollectionType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "pod"
})
public class PodCollectionType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<PodType> pod;

    /**
     * Gets the value of the pod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PodType }
     * 
     * 
     */
    public List<PodType> getPod() {
        if (pod == null) {
            pod = new ArrayList<PodType>();
        }
        return this.pod;
    }

}
