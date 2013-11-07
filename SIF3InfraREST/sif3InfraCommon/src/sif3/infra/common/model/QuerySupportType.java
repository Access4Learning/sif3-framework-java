
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for querySupportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="querySupportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="singular" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="formula" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="paged" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="maxPageSize" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="applicationProduct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adapterProduct" type="{http://www.sifassociation.org/infrastructure/3.0}productIdentityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "querySupportType", namespace = "http://www.sifassociation.org/infrastructure/3.0", propOrder = {
    "singular",
    "formula",
    "paged",
    "maxPageSize",
    "applicationProduct",
    "adapterProduct"
})
public class QuerySupportType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected boolean singular;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected boolean formula;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected boolean paged;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    @XmlSchemaType(name = "unsignedInt")
    protected long maxPageSize;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected String applicationProduct;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected ProductIdentityType adapterProduct;

    /**
     * Gets the value of the singular property.
     * 
     */
    public boolean isSingular() {
        return singular;
    }

    /**
     * Sets the value of the singular property.
     * 
     */
    public void setSingular(boolean value) {
        this.singular = value;
    }

    /**
     * Gets the value of the formula property.
     * 
     */
    public boolean isFormula() {
        return formula;
    }

    /**
     * Sets the value of the formula property.
     * 
     */
    public void setFormula(boolean value) {
        this.formula = value;
    }

    /**
     * Gets the value of the paged property.
     * 
     */
    public boolean isPaged() {
        return paged;
    }

    /**
     * Sets the value of the paged property.
     * 
     */
    public void setPaged(boolean value) {
        this.paged = value;
    }

    /**
     * Gets the value of the maxPageSize property.
     * 
     */
    public long getMaxPageSize() {
        return maxPageSize;
    }

    /**
     * Sets the value of the maxPageSize property.
     * 
     */
    public void setMaxPageSize(long value) {
        this.maxPageSize = value;
    }

    /**
     * Gets the value of the applicationProduct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationProduct() {
        return applicationProduct;
    }

    /**
     * Sets the value of the applicationProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationProduct(String value) {
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
