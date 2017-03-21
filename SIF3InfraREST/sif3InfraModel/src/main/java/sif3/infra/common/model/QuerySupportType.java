
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
 *         &lt;element name="dynamicQuery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="queryByExample" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="changesSinceMarker" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="paged" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="maxPageSize" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="totalCount" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="applicationProduct" type="{http://www.sifassociation.org/infrastructure/3.2.1}productIdentityType" minOccurs="0"/>
 *         &lt;element name="adapterProduct" type="{http://www.sifassociation.org/infrastructure/3.2.1}productIdentityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "querySupportType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "dynamicQuery",
    "queryByExample",
    "changesSinceMarker",
    "paged",
    "maxPageSize",
    "totalCount",
    "applicationProduct",
    "adapterProduct"
})
public class QuerySupportType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected Boolean dynamicQuery;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected Boolean queryByExample;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected Boolean changesSinceMarker;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected Boolean paged;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    @XmlSchemaType(name = "unsignedInt")
    protected Long maxPageSize;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected Boolean totalCount;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected ProductIdentityType applicationProduct;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected ProductIdentityType adapterProduct;

    /**
     * Gets the value of the dynamicQuery property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDynamicQuery() {
        return dynamicQuery;
    }

    /**
     * Sets the value of the dynamicQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDynamicQuery(Boolean value) {
        this.dynamicQuery = value;
    }

    /**
     * Gets the value of the queryByExample property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isQueryByExample() {
        return queryByExample;
    }

    /**
     * Sets the value of the queryByExample property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQueryByExample(Boolean value) {
        this.queryByExample = value;
    }

    /**
     * Gets the value of the changesSinceMarker property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChangesSinceMarker() {
        return changesSinceMarker;
    }

    /**
     * Sets the value of the changesSinceMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChangesSinceMarker(Boolean value) {
        this.changesSinceMarker = value;
    }

    /**
     * Gets the value of the paged property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPaged() {
        return paged;
    }

    /**
     * Sets the value of the paged property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPaged(Boolean value) {
        this.paged = value;
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
