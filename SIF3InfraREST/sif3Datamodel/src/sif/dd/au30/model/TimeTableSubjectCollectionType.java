
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TimeTableSubjectCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeTableSubjectCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimeTableSubject" type="{http://www.sifassociation.org/au/datamodel/1.3}TimeTableSubjectType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeTableSubjectCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "timeTableSubject"
})
public class TimeTableSubjectCollectionType {

    @XmlElement(name = "TimeTableSubject", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<TimeTableSubjectType> timeTableSubject;

    /**
     * Gets the value of the timeTableSubject property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeTableSubject property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeTableSubject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeTableSubjectType }
     * 
     * 
     */
    public List<TimeTableSubjectType> getTimeTableSubject() {
        if (timeTableSubject == null) {
            timeTableSubject = new ArrayList<TimeTableSubjectType>();
        }
        return this.timeTableSubject;
    }

}
