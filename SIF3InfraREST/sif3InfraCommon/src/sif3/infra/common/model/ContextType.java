
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for contextType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contextType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contextName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="zoneID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="providername" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataModelNamespace" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contextType", namespace = "http://www.sifassociation.org/infrastructure/3.0", propOrder = {
    "contextName",
    "zoneID",
    "providername",
    "dataModelNamespace"
})
public class ContextType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected String contextName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected String zoneID;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected String providername;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    @XmlSchemaType(name = "anyURI")
    protected String dataModelNamespace;

    /**
     * Gets the value of the contextName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContextName() {
        return contextName;
    }

    /**
     * Sets the value of the contextName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextName(String value) {
        this.contextName = value;
    }

    /**
     * Gets the value of the zoneID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZoneID() {
        return zoneID;
    }

    /**
     * Sets the value of the zoneID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZoneID(String value) {
        this.zoneID = value;
    }

    /**
     * Gets the value of the providername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvidername() {
        return providername;
    }

    /**
     * Sets the value of the providername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvidername(String value) {
        this.providername = value;
    }

    /**
     * Gets the value of the dataModelNamespace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataModelNamespace() {
        return dataModelNamespace;
    }

    /**
     * Sets the value of the dataModelNamespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataModelNamespace(String value) {
        this.dataModelNamespace = value;
    }

}
