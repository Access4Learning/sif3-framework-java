
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
 * <p>Java class for zoneContextListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zoneContextListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zoneContext" type="{http://www.sifassociation.org/infrastructure/3.3}zoneContextType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zoneContextListType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "zoneContext"
})
public class ZoneContextListType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<ZoneContextType> zoneContext;

    /**
     * Gets the value of the zoneContext property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zoneContext property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZoneContext().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ZoneContextType }
     * 
     * 
     */
    public List<ZoneContextType> getZoneContext() {
        if (zoneContext == null) {
            zoneContext = new ArrayList<ZoneContextType>();
        }
        return this.zoneContext;
    }

}
