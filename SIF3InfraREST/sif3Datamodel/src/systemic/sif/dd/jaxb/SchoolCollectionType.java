
package systemic.sif.dd.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SchoolCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SchoolCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SchoolInfo" type="{http://www.SIFinfo.org/au/datamodel/1.3}SchoolInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SchoolCollectionType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "schoolInfo"
})
public class SchoolCollectionType {

    @XmlElement(name = "SchoolInfo", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected List<SchoolInfoType> schoolInfo;

    /**
     * Gets the value of the schoolInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the schoolInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSchoolInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SchoolInfoType }
     * 
     * 
     */
    public List<SchoolInfoType> getSchoolInfo() {
        if (schoolInfo == null) {
            schoolInfo = new ArrayList<SchoolInfoType>();
        }
        return this.schoolInfo;
    }

}
