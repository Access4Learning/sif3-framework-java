
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         List of year levels.
 *       
 * 
 * <p>Java class for YearLevelsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="YearLevelsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}YearLevel" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "YearLevelsType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "yearLevel"
})
public class YearLevelsType {

    @XmlElement(name = "YearLevel", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<YearLevelType> yearLevel;

    /**
     * Gets the value of the yearLevel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the yearLevel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getYearLevel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link YearLevelType }
     * 
     * 
     */
    public List<YearLevelType> getYearLevel() {
        if (yearLevel == null) {
            yearLevel = new ArrayList<YearLevelType>();
        }
        return this.yearLevel;
    }

}
