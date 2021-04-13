
package sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createResponseCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createResponseCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createResponse" type="{http://www.sifassociation.org/infrastructure/3.3}createResponseType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createResponseCollectionType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "createResponse"
})
public class CreateResponseCollectionType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<CreateResponseType> createResponse;

    /**
     * Gets the value of the createResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the createResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreateResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreateResponseType }
     * 
     * 
     */
    public List<CreateResponseType> getCreateResponse() {
        if (createResponse == null) {
            createResponse = new ArrayList<CreateResponseType>();
        }
        return this.createResponse;
    }

}
