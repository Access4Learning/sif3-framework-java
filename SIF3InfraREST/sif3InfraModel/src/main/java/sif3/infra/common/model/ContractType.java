
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * The details of the contract the POD has been derived from.
 * 
 * <p>Java class for contractType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contractType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contractURI" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="contractName" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="dateValidFrom" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="dateValidTo" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="contractVersion" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="lawList" type="{http://www.sifassociation.org/infrastructure/3.3}lawListType"/>
 *         &lt;element name="studentDataIPRights" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contractType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "contractURI",
    "contractName",
    "dateValidFrom",
    "dateValidTo",
    "contractVersion",
    "lawList",
    "studentDataIPRights"
})
public class ContractType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String contractURI;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String contractName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateValidFrom;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateValidTo;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String contractVersion;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    protected LawListType lawList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String studentDataIPRights;

    /**
     * Gets the value of the contractURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractURI() {
        return contractURI;
    }

    /**
     * Sets the value of the contractURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractURI(String value) {
        this.contractURI = value;
    }

    /**
     * Gets the value of the contractName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * Sets the value of the contractName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractName(String value) {
        this.contractName = value;
    }

    /**
     * Gets the value of the dateValidFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateValidFrom() {
        return dateValidFrom;
    }

    /**
     * Sets the value of the dateValidFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateValidFrom(XMLGregorianCalendar value) {
        this.dateValidFrom = value;
    }

    /**
     * Gets the value of the dateValidTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateValidTo() {
        return dateValidTo;
    }

    /**
     * Sets the value of the dateValidTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateValidTo(XMLGregorianCalendar value) {
        this.dateValidTo = value;
    }

    /**
     * Gets the value of the contractVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractVersion() {
        return contractVersion;
    }

    /**
     * Sets the value of the contractVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractVersion(String value) {
        this.contractVersion = value;
    }

    /**
     * Gets the value of the lawList property.
     * 
     * @return
     *     possible object is
     *     {@link LawListType }
     *     
     */
    public LawListType getLawList() {
        return lawList;
    }

    /**
     * Sets the value of the lawList property.
     * 
     * @param value
     *     allowed object is
     *     {@link LawListType }
     *     
     */
    public void setLawList(LawListType value) {
        this.lawList = value;
    }

    /**
     * Gets the value of the studentDataIPRights property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentDataIPRights() {
        return studentDataIPRights;
    }

    /**
     * Sets the value of the studentDataIPRights property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentDataIPRights(String value) {
        this.studentDataIPRights = value;
    }

}
