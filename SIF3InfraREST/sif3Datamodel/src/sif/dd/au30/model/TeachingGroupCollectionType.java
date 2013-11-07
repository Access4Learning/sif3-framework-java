
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TeachingGroupCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TeachingGroupCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TeachingGroup" type="{http://www.SIFinfo.org/au/datamodel/1.3}TeachingGroupType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TeachingGroupCollectionType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "teachingGroup"
})
public class TeachingGroupCollectionType {

    @XmlElement(name = "TeachingGroup", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected List<TeachingGroupType> teachingGroup;

    /**
     * Gets the value of the teachingGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the teachingGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTeachingGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TeachingGroupType }
     * 
     * 
     */
    public List<TeachingGroupType> getTeachingGroup() {
        if (teachingGroup == null) {
            teachingGroup = new ArrayList<TeachingGroupType>();
        }
        return this.teachingGroup;
    }

}
