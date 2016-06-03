
package sif3.infra.common.model;

import java.io.Serializable;
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
 *         &lt;element name="dynamicQuery" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="paged" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="maxPageSize" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="totalCount" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="applicationProduct" type="{http://www.sifassociation.org/infrastructure/3.2}productIdentityType" minOccurs="0"/>
 *         &lt;element name="adapterProduct" type="{http://www.sifassociation.org/infrastructure/3.2}productIdentityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "querySupportType", namespace = "http://www.sifassociation.org/infrastructure/3.2", propOrder = {
    "dynamicQuery",
    "paged",
    "maxPageSize",
    "totalCount",
    "applicationProduct",
    "adapterProduct"
})
public class QuerySupportType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2")
    protected boolean dynamicQuery;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2")
    protected boolean paged;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2")
    @XmlSchemaType(name = "unsignedInt")
    protected Long maxPageSize;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2")
    protected Boolean totalCount;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2")
    protected ProductIdentityType applicationProduct;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2")
    protected ProductIdentityType adapterProduct;

    /**
     * Gets the value of the dynamicQuery property.
     * 
     */
    public boolean isDynamicQuery() {
        return dynamicQuery;
    }

    /**
     * Sets the value of the dynamicQuery property.
     * 
     */
    public void setDynamicQuery(boolean value) {
        this.dynamicQuery = value;
    }

    public boolean isSetDynamicQuery() {
        return true;
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

    public boolean isSetPaged() {
        return true;
    }

    /**
     * Gets the value of the maxPageSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxPageSize() {
        return maxPageSize;
    }

    /**
     * Sets the value of the maxPageSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxPageSize(Long value) {
        this.maxPageSize = value;
    }

    public boolean isSetMaxPageSize() {
        return (this.maxPageSize!= null);
    }

    /**
     * Gets the value of the totalCount property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTotalCount() {
        return totalCount;
    }

    /**
     * Sets the value of the totalCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTotalCount(Boolean value) {
        this.totalCount = value;
    }

    public boolean isSetTotalCount() {
        return (this.totalCount!= null);
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

    public boolean isSetApplicationProduct() {
        return (this.applicationProduct!= null);
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

    public boolean isSetAdapterProduct() {
        return (this.adapterProduct!= null);
    }

}
