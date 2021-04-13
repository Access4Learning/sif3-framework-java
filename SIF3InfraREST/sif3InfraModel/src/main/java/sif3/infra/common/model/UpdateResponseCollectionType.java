
package sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateResponseCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateResponseCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updateResponse" type="{http://www.sifassociation.org/infrastructure/3.3}updateResponseType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateResponseCollectionType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "updateResponse"
})
public class UpdateResponseCollectionType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<UpdateResponseType> updateResponse;

    /**
     * Gets the value of the updateResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the updateResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUpdateResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UpdateResponseType }
     * 
     * 
     */
    public List<UpdateResponseType> getUpdateResponse() {
        if (updateResponse == null) {
            updateResponse = new ArrayList<UpdateResponseType>();
        }
        return this.updateResponse;
    }

}
