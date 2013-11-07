
package sif.dd.au30.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Common element used to supply information for a contact person at a school, LEA, or other institution.
 * 
 * <p>Java class for ContactInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContactInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}Name" minOccurs="0"/>
 *         &lt;element name="PositionTitle" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="Role" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="Address" type="{http://www.SIFinfo.org/au/datamodel/1.3}AddressType" minOccurs="0"/>
 *         &lt;element name="EmailList" type="{http://www.SIFinfo.org/au/datamodel/1.3}EmailListType" minOccurs="0"/>
 *         &lt;element name="PhoneNumberList" type="{http://www.SIFinfo.org/au/datamodel/1.3}PhoneNumberListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactInfoType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "name",
    "positionTitle",
    "role",
    "address",
    "emailList",
    "phoneNumberList"
})
public class ContactInfoType {

    @XmlElement(name = "Name", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected NameType name;
    @XmlElementRef(name = "PositionTitle", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> positionTitle;
    @XmlElementRef(name = "Role", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> role;
    @XmlElementRef(name = "Address", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AddressType> address;
    @XmlElementRef(name = "EmailList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<EmailListType> emailList;
    @XmlElementRef(name = "PhoneNumberList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<PhoneNumberListType> phoneNumberList;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link NameType }
     *     
     */
    public NameType getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameType }
     *     
     */
    public void setName(NameType value) {
        this.name = value;
    }

    /**
     * Gets the value of the positionTitle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPositionTitle() {
        return positionTitle;
    }

    /**
     * Sets the value of the positionTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPositionTitle(JAXBElement<String> value) {
        this.positionTitle = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRole(JAXBElement<String> value) {
        this.role = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AddressType }{@code >}
     *     
     */
    public JAXBElement<AddressType> getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AddressType }{@code >}
     *     
     */
    public void setAddress(JAXBElement<AddressType> value) {
        this.address = value;
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

}
