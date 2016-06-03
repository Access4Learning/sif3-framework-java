
package sif3.infra.common.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for codeItemsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="codeItemsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codeItem" type="{http://www.sifassociation.org/infrastructure/3.2}codeItemType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "codeItemsType", namespace = "http://www.sifassociation.org/infrastructure/3.2", propOrder = {
    "codeItem"
})
public class CodeItemsType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected CodeItemType codeItem;

    /**
     * Gets the value of the codeItem property.
     * 
     * @return
     *     possible object is
     *     {@link CodeItemType }
     *     
     */
    public CodeItemType getCodeItem() {
        return codeItem;
    }

    /**
     * Sets the value of the codeItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeItemType }
     *     
     */
    public void setCodeItem(CodeItemType value) {
        this.codeItem = value;
    }

    public boolean isSetCodeItem() {
        return (this.codeItem!= null);
    }

}
