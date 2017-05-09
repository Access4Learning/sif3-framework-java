
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * An HTTP Response with a status of 200 and a payload consisting of a single “createResponse” element. This includes, for each requested object successfully created, an internal status of 201 and the service-assigned refId. Any requested object that failed to be created is indicated by an error statusCode and a corresponding error payload.
 * 
 * <p>Java class for createResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creates" type="{http://www.sifassociation.org/infrastructure/3.2.1}createsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createResponseType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "creates"
})
public class CreateResponseType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    protected CreatesType creates;

    /**
     * Gets the value of the creates property.
     * 
     * @return
     *     possible object is
     *     {@link CreatesType }
     *     
     */
    public CreatesType getCreates() {
        return creates;
    }

    /**
     * Sets the value of the creates property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatesType }
     *     
     */
    public void setCreates(CreatesType value) {
        this.creates = value;
    }

}
