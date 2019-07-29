
package sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * List of impacted countries in or through which the data transits or is stored.
 * 
 * <p>Java class for countryImpactedListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="countryImpactedListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="countryImpacted" type="{http://www.sifassociation.org/infrastructure/3.3}countryImpactedType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "countryImpactedListType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "countryImpacted"
})
public class CountryImpactedListType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<CountryImpactedType> countryImpacted;

    /**
     * Gets the value of the countryImpacted property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the countryImpacted property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountryImpacted().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CountryImpactedType }
     * 
     * 
     */
    public List<CountryImpactedType> getCountryImpacted() {
        if (countryImpacted == null) {
            countryImpacted = new ArrayList<CountryImpactedType>();
        }
        return this.countryImpacted;
    }

}
