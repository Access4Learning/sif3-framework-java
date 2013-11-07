
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="namespace">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="prefix" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *                   &lt;choice>
 *                     &lt;element name="static" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *                     &lt;element name="startsWith" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *                     &lt;element name="regularExpression" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "namespace"
})
public class Namespaces {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected Namespace namespace;

    /**
     * Gets the value of the namespace property.
     * 
     * @return
     *     possible object is
     *     {@link Namespace }
     *     
     */
    public Namespace getNamespace() {
        return namespace;
    }

    /**
     * Sets the value of the namespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link Namespace }
     *     
     */
    public void setNamespace(Namespace value) {
        this.namespace = value;
    }

}
