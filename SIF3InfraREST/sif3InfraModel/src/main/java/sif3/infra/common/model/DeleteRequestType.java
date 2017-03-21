
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A “deleteRequest” element, which consists of multiple, “delete” object ids. Since REST conventions do not support payloads on HTTP DELETE messages, all multi-object Delete Requests are conveyed via an HTTP PUT message containing an additional HTTP Header Field value of methodOverride set to DELETE.
 * 
 * <p>Java class for deleteRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deletes" type="{http://www.sifassociation.org/infrastructure/3.2.1}deleteIdCollection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteRequestType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "deletes"
})
public class DeleteRequestType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    protected DeleteIdCollection deletes;

    /**
     * Gets the value of the deletes property.
     * 
     * @return
     *     possible object is
     *     {@link DeleteIdCollection }
     *     
     */
    public DeleteIdCollection getDeletes() {
        return deletes;
    }

    /**
     * Sets the value of the deletes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteIdCollection }
     *     
     */
    public void setDeletes(DeleteIdCollection value) {
        this.deletes = value;
    }

}
