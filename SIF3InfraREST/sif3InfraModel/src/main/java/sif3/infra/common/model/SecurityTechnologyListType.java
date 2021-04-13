
package sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for securityTechnologyListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="securityTechnologyListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="securityTechnology" type="{http://www.sifassociation.org/infrastructure/3.3}securityTechnologyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "securityTechnologyListType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "securityTechnology"
})
public class SecurityTechnologyListType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<SecurityTechnologyType> securityTechnology;

    /**
     * Gets the value of the securityTechnology property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the securityTechnology property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecurityTechnology().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SecurityTechnologyType }
     * 
     * 
     */
    public List<SecurityTechnologyType> getSecurityTechnology() {
        if (securityTechnology == null) {
            securityTechnology = new ArrayList<SecurityTechnologyType>();
        }
        return this.securityTechnology;
    }

}
