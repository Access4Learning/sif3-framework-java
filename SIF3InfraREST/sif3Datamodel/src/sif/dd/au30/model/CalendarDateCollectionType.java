
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CalendarDateCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CalendarDateCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CalendarDate" type="{http://www.sifassociation.org/au/datamodel/1.3}CalendarDateType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalendarDateCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "calendarDate"
})
public class CalendarDateCollectionType {

    @XmlElement(name = "CalendarDate", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<CalendarDate> calendarDate;

    /**
     * Gets the value of the calendarDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calendarDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalendarDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CalendarDate }
     * 
     * 
     */
    public List<CalendarDate> getCalendarDate() {
        if (calendarDate == null) {
            calendarDate = new ArrayList<CalendarDate>();
        }
        return this.calendarDate;
    }

}
