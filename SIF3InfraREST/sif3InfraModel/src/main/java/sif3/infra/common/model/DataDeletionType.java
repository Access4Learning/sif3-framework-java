
package sif3.infra.common.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Details and requirements for data deletion.
 * 
 * <p>Java class for dataDeletionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataDeletionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deleteData" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="Y"/>
 *               &lt;enumeration value="N"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dataRetention" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="deleteBy" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="partyToDeleteData" type="{http://www.sifassociation.org/infrastructure/3.3}partyToDeleteDataType" minOccurs="0"/>
 *         &lt;element name="respondInDays" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="requirePortability" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="Y"/>
 *               &lt;enumeration value="N"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
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
@XmlType(name = "dataDeletionType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "deleteData",
    "dataRetention",
    "deleteBy",
    "partyToDeleteData",
    "respondInDays",
    "requirePortability"
})
public class DataDeletionType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String deleteData;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected BigInteger dataRetention;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deleteBy;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected PartyToDeleteDataType partyToDeleteData;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected BigInteger respondInDays;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String requirePortability;

    /**
     * Gets the value of the deleteData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeleteData() {
        return deleteData;
    }

    /**
     * Sets the value of the deleteData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeleteData(String value) {
        this.deleteData = value;
    }

    /**
     * Gets the value of the dataRetention property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDataRetention() {
        return dataRetention;
    }

    /**
     * Sets the value of the dataRetention property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDataRetention(BigInteger value) {
        this.dataRetention = value;
    }

    /**
     * Gets the value of the deleteBy property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeleteBy() {
        return deleteBy;
    }

    /**
     * Sets the value of the deleteBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeleteBy(XMLGregorianCalendar value) {
        this.deleteBy = value;
    }

    /**
     * Gets the value of the partyToDeleteData property.
     * 
     * @return
     *     possible object is
     *     {@link PartyToDeleteDataType }
     *     
     */
    public PartyToDeleteDataType getPartyToDeleteData() {
        return partyToDeleteData;
    }

    /**
     * Sets the value of the partyToDeleteData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyToDeleteDataType }
     *     
     */
    public void setPartyToDeleteData(PartyToDeleteDataType value) {
        this.partyToDeleteData = value;
    }

    /**
     * Gets the value of the respondInDays property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRespondInDays() {
        return respondInDays;
    }

    /**
     * Sets the value of the respondInDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRespondInDays(BigInteger value) {
        this.respondInDays = value;
    }

    /**
     * Gets the value of the requirePortability property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequirePortability() {
        return requirePortability;
    }

    /**
     * Sets the value of the requirePortability property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequirePortability(String value) {
        this.requirePortability = value;
    }

}
