
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SchoolProgramsCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SchoolProgramsCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SchoolPrograms" type="{http://www.sifassociation.org/au/datamodel/1.3}SchoolProgramsType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SchoolProgramsCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "schoolPrograms"
})
public class SchoolProgramsCollectionType {

    @XmlElement(name = "SchoolPrograms", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<SchoolProgramsType> schoolPrograms;

    /**
     * Gets the value of the schoolPrograms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the schoolPrograms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSchoolPrograms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SchoolProgramsType }
     * 
     * 
     */
    public List<SchoolProgramsType> getSchoolPrograms() {
        if (schoolPrograms == null) {
            schoolPrograms = new ArrayList<SchoolProgramsType>();
        }
        return this.schoolPrograms;
    }

}
