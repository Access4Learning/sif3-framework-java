
package sif3.infra.common.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deletes" type="{http://www.sifassociation.org/infrastructure/3.2}deleteIdCollection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteRequestType", namespace = "http://www.sifassociation.org/infrastructure/3.2", propOrder = {
    "deletes"
})
public class DeleteRequestType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2", required = true)
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

    public boolean isSetDeletes() {
        return (this.deletes!= null);
    }

}
