
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ScheduledActivityCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ScheduledActivityCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ScheduledActivity" type="{http://www.sifassociation.org/au/datamodel/1.3}ScheduledActivityType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScheduledActivityCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "scheduledActivity"
})
public class ScheduledActivityCollectionType {

    @XmlElement(name = "ScheduledActivity", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<ScheduledActivityType> scheduledActivity;

    /**
     * Gets the value of the scheduledActivity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scheduledActivity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScheduledActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScheduledActivityType }
     * 
     * 
     */
    public List<ScheduledActivityType> getScheduledActivity() {
        if (scheduledActivity == null) {
            scheduledActivity = new ArrayList<ScheduledActivityType>();
        }
        return this.scheduledActivity;
    }

}
