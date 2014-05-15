
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SIF_ReportObjectCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIF_ReportObjectCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SIF_ReportObject" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_ReportObjectType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIF_ReportObjectCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "sifReportObject"
})
public class SIFReportObjectCollectionType {

    @XmlElement(name = "SIF_ReportObject", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<SIFReportObjectType> sifReportObject;

    /**
     * Gets the value of the sifReportObject property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sifReportObject property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSIFReportObject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SIFReportObjectType }
     * 
     * 
     */
    public List<SIFReportObjectType> getSIFReportObject() {
        if (sifReportObject == null) {
            sifReportObject = new ArrayList<SIFReportObjectType>();
        }
        return this.sifReportObject;
    }

}
