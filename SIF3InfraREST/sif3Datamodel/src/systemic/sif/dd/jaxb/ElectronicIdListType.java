
package systemic.sif.dd.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A list of electronic identifiers associated with an entity.
 * 
 * <p>Java class for ElectronicIdListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ElectronicIdListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}ElectronicId" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElectronicIdListType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "electronicId"
})
public class ElectronicIdListType {

    @XmlElement(name = "ElectronicId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected List<ElectronicIdType> electronicId;

    /**
     * Gets the value of the electronicId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the electronicId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElectronicId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ElectronicIdType }
     * 
     * 
     */
    public List<ElectronicIdType> getElectronicId() {
        if (electronicId == null) {
            electronicId = new ArrayList<ElectronicIdType>();
        }
        return this.electronicId;
    }

}
