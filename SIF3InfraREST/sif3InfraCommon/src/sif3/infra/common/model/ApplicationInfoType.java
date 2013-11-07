
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for applicationInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="applicationInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="supportedInfrastructureVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="supportedDataModel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="supportedDataModelVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applicationProduct" type="{http://www.sifassociation.org/infrastructure/3.0}productIdentityType"/>
 *         &lt;element name="adapterProduct" type="{http://www.sifassociation.org/infrastructure/3.0}productIdentityType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "applicationInfoType", namespace = "http://www.sifassociation.org/infrastructure/3.0", propOrder = {
    "applicationKey",
    "supportedInfrastructureVersion",
    "supportedDataModel",
    "supportedDataModelVersion",
    "transport",
    "applicationProduct",
    "adapterProduct"
})
public class ApplicationInfoType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected String applicationKey;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected String supportedInfrastructureVersion;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected String supportedDataModel;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected String supportedDataModelVersion;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected String transport;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected ProductIdentityType applicationProduct;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    protected ProductIdentityType adapterProduct;

    /**
     * Gets the value of the applicationKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationKey() {
        return applicationKey;
    }

    /**
     * Sets the value of the applicationKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationKey(String value) {
        this.applicationKey = value;
    }

    /**
     * Gets the value of the supportedInfrastructureVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportedInfrastructureVersion() {
        return supportedInfrastructureVersion;
    }

    /**
     * Sets the value of the supportedInfrastructureVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportedInfrastructureVersion(String value) {
        this.supportedInfrastructureVersion = value;
    }

    /**
     * Gets the value of the supportedDataModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportedDataModel() {
        return supportedDataModel;
    }

    /**
     * Sets the value of the supportedDataModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportedDataModel(String value) {
        this.supportedDataModel = value;
    }

    /**
     * Gets the value of the supportedDataModelVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportedDataModelVersion() {
        return supportedDataModelVersion;
    }

    /**
     * Sets the value of the supportedDataModelVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportedDataModelVersion(String value) {
        this.supportedDataModelVersion = value;
    }

    /**
     * Gets the value of the transport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransport() {
        return transport;
    }

    /**
     * Sets the value of the transport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransport(String value) {
        this.transport = value;
    }

    /**
     * Gets the value of the applicationProduct property.
     * 
     * @return
     *     possible object is
     *     {@link ProductIdentityType }
     *     
     */
    public ProductIdentityType getApplicationProduct() {
        return applicationProduct;
    }

    /**
     * Sets the value of the applicationProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductIdentityType }
     *     
     */
    public void setApplicationProduct(ProductIdentityType value) {
        this.applicationProduct = value;
    }

    /**
     * Gets the value of the adapterProduct property.
     * 
     * @return
     *     possible object is
     *     {@link ProductIdentityType }
     *     
     */
    public ProductIdentityType getAdapterProduct() {
        return adapterProduct;
    }

    /**
     * Sets the value of the adapterProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductIdentityType }
     *     
     */
    public void setAdapterProduct(ProductIdentityType value) {
        this.adapterProduct = value;
    }

}
