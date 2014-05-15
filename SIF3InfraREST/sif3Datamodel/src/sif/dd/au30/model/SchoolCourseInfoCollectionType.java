
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SchoolCourseInfoCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SchoolCourseInfoCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SchoolCourseInfo" type="{http://www.sifassociation.org/au/datamodel/1.3}SchoolCourseInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SchoolCourseInfoCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "schoolCourseInfo"
})
public class SchoolCourseInfoCollectionType {

    @XmlElement(name = "SchoolCourseInfo", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<SchoolCourseInfoType> schoolCourseInfo;

    /**
     * Gets the value of the schoolCourseInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the schoolCourseInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSchoolCourseInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SchoolCourseInfoType }
     * 
     * 
     */
    public List<SchoolCourseInfoType> getSchoolCourseInfo() {
        if (schoolCourseInfo == null) {
            schoolCourseInfo = new ArrayList<SchoolCourseInfoType>();
        }
        return this.schoolCourseInfo;
    }

}
