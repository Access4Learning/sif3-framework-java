
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * An HTTP Response with a status of 200 (success). It contains a payload consisting of a single “deleteResponse” element.
 * 
 * <p>Java class for deleteResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deletes" type="{http://www.sifassociation.org/infrastructure/3.2.1}deleteStatusCollection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteResponseType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "deletes"
})
public class DeleteResponseType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    protected DeleteStatusCollection deletes;

    /**
     * Gets the value of the deletes property.
     * 
     * @return
     *     possible object is
     *     {@link DeleteStatusCollection }
     *     
     */
    public DeleteStatusCollection getDeletes() {
        return deletes;
    }

    /**
     * Sets the value of the deletes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteStatusCollection }
     *     
     */
    public void setDeletes(DeleteStatusCollection value) {
        this.deletes = value;
    }

}
