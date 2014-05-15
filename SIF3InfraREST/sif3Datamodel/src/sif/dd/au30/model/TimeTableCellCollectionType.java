
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TimeTableCellCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeTableCellCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimeTableCell" type="{http://www.sifassociation.org/au/datamodel/1.3}TimeTableCellType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeTableCellCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "timeTableCell"
})
public class TimeTableCellCollectionType {

    @XmlElement(name = "TimeTableCell", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<TimeTableCellType> timeTableCell;

    /**
     * Gets the value of the timeTableCell property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeTableCell property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeTableCell().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeTableCellType }
     * 
     * 
     */
    public List<TimeTableCellType> getTimeTableCell() {
        if (timeTableCell == null) {
            timeTableCell = new ArrayList<TimeTableCellType>();
        }
        return this.timeTableCell;
    }

}
