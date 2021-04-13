
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A type of endpoint.
 * 
 * <p>Java class for endpointType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="endpointType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="endpointURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="endpointProviderName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "endpointType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "endpointURL",
    "endpointProviderName"
})
public class EndpointType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String endpointURL;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String endpointProviderName;

    /**
     * Gets the value of the endpointURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndpointURL() {
        return endpointURL;
    }

    /**
     * Sets the value of the endpointURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndpointURL(String value) {
        this.endpointURL = value;
    }

    /**
     * Gets the value of the endpointProviderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndpointProviderName() {
        return endpointProviderName;
    }

    /**
     * Sets the value of the endpointProviderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndpointProviderName(String value) {
        this.endpointProviderName = value;
    }

}
