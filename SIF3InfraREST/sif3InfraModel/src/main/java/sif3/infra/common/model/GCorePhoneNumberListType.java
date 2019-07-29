
package sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Lists phone numbers associated with an entity.
 * 
 * <p>Java class for gCorePhoneNumberListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gCorePhoneNumberListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="phoneNumber" type="{http://www.sifassociation.org/infrastructure/3.3}gCorePhoneNumberType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gCorePhoneNumberListType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "phoneNumber"
})
public class GCorePhoneNumberListType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    protected List<GCorePhoneNumberType> phoneNumber;

    /**
     * Gets the value of the phoneNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phoneNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhoneNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GCorePhoneNumberType }
     * 
     * 
     */
    public List<GCorePhoneNumberType> getPhoneNumber() {
        if (phoneNumber == null) {
            phoneNumber = new ArrayList<GCorePhoneNumberType>();
        }
        return this.phoneNumber;
    }

}
