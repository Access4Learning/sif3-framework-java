
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for namespaceQualifiersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="namespaceQualifiersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="namespace" type="{http://www.sifassociation.org/infrastructure/3.2.1}namespaceQualifierType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "namespaceQualifiersType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "namespace"
})
public class NamespaceQualifiersType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    protected NamespaceQualifierType namespace;

    /**
     * Gets the value of the namespace property.
     * 
     * @return
     *     possible object is
     *     {@link NamespaceQualifierType }
     *     
     */
    public NamespaceQualifierType getNamespace() {
        return namespace;
    }

    /**
     * Sets the value of the namespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link NamespaceQualifierType }
     *     
     */
    public void setNamespace(NamespaceQualifierType value) {
        this.namespace = value;
    }

}
