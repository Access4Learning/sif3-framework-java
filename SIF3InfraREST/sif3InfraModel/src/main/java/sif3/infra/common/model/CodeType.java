
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for codeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="codeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="old" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="official" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "codeType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "old",
    "official",
    "value"
})
public class CodeType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected boolean old;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected boolean official;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    protected String value;

    /**
     * Gets the value of the old property.
     * 
     */
    public boolean isOld() {
        return old;
    }

    /**
     * Sets the value of the old property.
     * 
     */
    public void setOld(boolean value) {
        this.old = value;
    }

    /**
     * Gets the value of the official property.
     * 
     */
    public boolean isOfficial() {
        return official;
    }

    /**
     * Sets the value of the official property.
     * 
     */
    public void setOfficial(boolean value) {
        this.official = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}
