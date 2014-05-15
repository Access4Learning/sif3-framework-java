
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SummaryEnrollmentInfoCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SummaryEnrollmentInfoCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SummaryEnrollmentInfo" type="{http://www.sifassociation.org/au/datamodel/1.3}SummaryEnrollmentInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SummaryEnrollmentInfoCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "summaryEnrollmentInfo"
})
public class SummaryEnrollmentInfoCollectionType {

    @XmlElement(name = "SummaryEnrollmentInfo", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<SummaryEnrollmentInfoType> summaryEnrollmentInfo;

    /**
     * Gets the value of the summaryEnrollmentInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the summaryEnrollmentInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSummaryEnrollmentInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SummaryEnrollmentInfoType }
     * 
     * 
     */
    public List<SummaryEnrollmentInfoType> getSummaryEnrollmentInfo() {
        if (summaryEnrollmentInfo == null) {
            summaryEnrollmentInfo = new ArrayList<SummaryEnrollmentInfoType>();
        }
        return this.summaryEnrollmentInfo;
    }

}
