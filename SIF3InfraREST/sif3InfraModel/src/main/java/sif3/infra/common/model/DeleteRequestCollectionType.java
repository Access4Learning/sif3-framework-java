
package sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteRequestCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteRequestCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deleteRequest" type="{http://www.sifassociation.org/infrastructure/3.3}deleteRequestType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteRequestCollectionType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "deleteRequest"
})
public class DeleteRequestCollectionType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<DeleteRequestType> deleteRequest;

    /**
     * Gets the value of the deleteRequest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deleteRequest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeleteRequest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeleteRequestType }
     * 
     * 
     */
    public List<DeleteRequestType> getDeleteRequest() {
        if (deleteRequest == null) {
            deleteRequest = new ArrayList<DeleteRequestType>();
        }
        return this.deleteRequest;
    }

}
