
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReportAuthorityInfoCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReportAuthorityInfoCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReportAuthorityInfo" type="{http://www.sifassociation.org/au/datamodel/1.3}ReportAuthorityInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReportAuthorityInfoCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "reportAuthorityInfo"
})
public class ReportAuthorityInfoCollectionType {

    @XmlElement(name = "ReportAuthorityInfo", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<ReportAuthorityInfoType> reportAuthorityInfo;

    /**
     * Gets the value of the reportAuthorityInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reportAuthorityInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReportAuthorityInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReportAuthorityInfoType }
     * 
     * 
     */
    public List<ReportAuthorityInfoType> getReportAuthorityInfo() {
        if (reportAuthorityInfo == null) {
            reportAuthorityInfo = new ArrayList<ReportAuthorityInfoType>();
        }
        return this.reportAuthorityInfo;
    }

}
