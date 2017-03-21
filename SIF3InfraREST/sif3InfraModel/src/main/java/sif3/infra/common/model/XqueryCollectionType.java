
package sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for xqueryCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="xqueryCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xquery" type="{http://www.sifassociation.org/infrastructure/3.2.1}xqueryType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "xqueryCollectionType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "xquery"
})
public class XqueryCollectionType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected List<XqueryType> xquery;

    /**
     * Gets the value of the xquery property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the xquery property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getXquery().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XqueryType }
     * 
     * 
     */
    public List<XqueryType> getXquery() {
        if (xquery == null) {
            xquery = new ArrayList<XqueryType>();
        }
        return this.xquery;
    }

}
