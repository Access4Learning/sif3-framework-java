
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 
 * <p>Java class for securityTechnologyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="securityTechnologyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clauseList" type="{http://www.sifassociation.org/infrastructure/3.3}clauseListType" minOccurs="0"/>
 *         &lt;element name="technologyName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="technologyDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="referenceURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="versionMin" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "securityTechnologyType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "clauseList",
    "technologyName",
    "technologyDescription",
    "referenceURL",
    "versionMin"
})
public class SecurityTechnologyType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected ClauseListType clauseList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String technologyName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String technologyDescription;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String referenceURL;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String versionMin;

    /**
     * Gets the value of the clauseList property.
     * 
     * @return
     *     possible object is
     *     {@link ClauseListType }
     *     
     */
    public ClauseListType getClauseList() {
        return clauseList;
    }

    /**
     * Sets the value of the clauseList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClauseListType }
     *     
     */
    public void setClauseList(ClauseListType value) {
        this.clauseList = value;
    }

    /**
     * Gets the value of the technologyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnologyName() {
        return technologyName;
    }

    /**
     * Sets the value of the technologyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnologyName(String value) {
        this.technologyName = value;
    }

    /**
     * Gets the value of the technologyDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnologyDescription() {
        return technologyDescription;
    }

    /**
     * Sets the value of the technologyDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnologyDescription(String value) {
        this.technologyDescription = value;
    }

    /**
     * Gets the value of the referenceURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceURL() {
        return referenceURL;
    }

    /**
     * Sets the value of the referenceURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceURL(String value) {
        this.referenceURL = value;
    }

    /**
     * Gets the value of the versionMin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionMin() {
        return versionMin;
    }

    /**
     * Sets the value of the versionMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionMin(String value) {
        this.versionMin = value;
    }

}
