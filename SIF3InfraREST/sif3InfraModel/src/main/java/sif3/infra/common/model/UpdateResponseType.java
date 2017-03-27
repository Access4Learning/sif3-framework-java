
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * An HTTP Response with a status of 200 (success) and a location corresponding to the URL of the first successfully modified object. It contains a payload consisting of a single “updateResponse” element.
 * 
 * <p>Java class for updateResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updates" type="{http://www.sifassociation.org/infrastructure/3.2.1}updatesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateResponseType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "updates"
})
public class UpdateResponseType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    protected UpdatesType updates;

    /**
     * Gets the value of the updates property.
     * 
     * @return
     *     possible object is
     *     {@link UpdatesType }
     *     
     */
    public UpdatesType getUpdates() {
        return updates;
    }

    /**
     * Sets the value of the updates property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdatesType }
     *     
     */
    public void setUpdates(UpdatesType value) {
        this.updates = value;
    }

}
