
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for namespaceQualifierType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="namespaceQualifierType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="prefix" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;choice>
 *           &lt;element name="static" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *           &lt;element name="startsWith" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *           &lt;element name="regularExpression" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "namespaceQualifierType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "prefix",
    "_static",
    "startsWith",
    "regularExpression"
})
public class NamespaceQualifierType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true, nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String prefix;
    @XmlElement(name = "static", namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    @XmlSchemaType(name = "anyURI")
    protected String _static;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String startsWith;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected String regularExpression;

    /**
     * Gets the value of the prefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the value of the prefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefix(String value) {
        this.prefix = value;
    }

    /**
     * Gets the value of the static property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatic() {
        return _static;
    }

    /**
     * Sets the value of the static property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatic(String value) {
        this._static = value;
    }

    /**
     * Gets the value of the startsWith property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartsWith() {
        return startsWith;
    }

    /**
     * Sets the value of the startsWith property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartsWith(String value) {
        this.startsWith = value;
    }

    /**
     * Gets the value of the regularExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegularExpression() {
        return regularExpression;
    }

    /**
     * Sets the value of the regularExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegularExpression(String value) {
        this.regularExpression = value;
    }

}
