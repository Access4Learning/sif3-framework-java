
package sif.dd.au30.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Information about the campus or school principal.
 * 
 * <p>Java class for PrincipalInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrincipalInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContactName" type="{http://www.SIFinfo.org/au/datamodel/1.3}NameOfRecordType" minOccurs="0"/>
 *         &lt;element name="ContactTitle" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="PhoneNumberList" type="{http://www.SIFinfo.org/au/datamodel/1.3}PhoneNumberListType" minOccurs="0"/>
 *         &lt;element name="EmailList" type="{http://www.SIFinfo.org/au/datamodel/1.3}EmailListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrincipalInfoType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "contactName",
    "contactTitle",
    "phoneNumberList",
    "emailList"
})
public class PrincipalInfoType {

    @XmlElement(name = "ContactName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected NameOfRecordType contactName;
    @XmlElementRef(name = "ContactTitle", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> contactTitle;
    @XmlElementRef(name = "PhoneNumberList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<PhoneNumberListType> phoneNumberList;
    @XmlElementRef(name = "EmailList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<EmailListType> emailList;

    /**
     * Gets the value of the contactName property.
     * 
     * @return
     *     possible object is
     *     {@link NameOfRecordType }
     *     
     */
    public NameOfRecordType getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameOfRecordType }
     *     
     */
    public void setContactName(NameOfRecordType value) {
        this.contactName = value;
    }

    /**
     * Gets the value of the contactTitle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getContactTitle() {
        return contactTitle;
    }

    /**
     * Sets the value of the contactTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setContactTitle(JAXBElement<String> value) {
        this.contactTitle = value;
    }

    /**
     * Gets the value of the phoneNumberList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}
     *     
     */
    public JAXBElement<PhoneNumberListType> getPhoneNumberList() {
        return phoneNumberList;
    }

    /**
     * Sets the value of the phoneNumberList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}
     *     
     */
    public void setPhoneNumberList(JAXBElement<PhoneNumberListType> value) {
        this.phoneNumberList = value;
    }

    /**
     * Gets the value of the emailList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EmailListType }{@code >}
     *     
     */
    public JAXBElement<EmailListType> getEmailList() {
        return emailList;
    }

    /**
     * Sets the value of the emailList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EmailListType }{@code >}
     *     
     */
    public void setEmailList(JAXBElement<EmailListType> value) {
        this.emailList = value;
    }

}
