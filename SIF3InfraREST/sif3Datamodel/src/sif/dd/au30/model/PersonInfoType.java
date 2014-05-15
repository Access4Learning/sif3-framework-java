
package sif.dd.au30.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PersonInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.sifassociation.org/au/datamodel/1.3}NameOfRecordType" minOccurs="0"/>
 *         &lt;element name="OtherNames" type="{http://www.sifassociation.org/au/datamodel/1.3}OtherNamesType" minOccurs="0"/>
 *         &lt;element name="Demographics" type="{http://www.sifassociation.org/au/datamodel/1.3}DemographicsType" minOccurs="0"/>
 *         &lt;element name="AddressList" type="{http://www.sifassociation.org/au/datamodel/1.3}AddressListType" minOccurs="0"/>
 *         &lt;element name="PhoneNumberList" type="{http://www.sifassociation.org/au/datamodel/1.3}PhoneNumberListType" minOccurs="0"/>
 *         &lt;element name="EmailList" type="{http://www.sifassociation.org/au/datamodel/1.3}EmailListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonInfoType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "name",
    "otherNames",
    "demographics",
    "addressList",
    "phoneNumberList",
    "emailList"
})
public class PersonInfoType {

    @XmlElement(name = "Name", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected NameOfRecordType name;
    @XmlElementRef(name = "OtherNames", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<OtherNamesType> otherNames;
    @XmlElementRef(name = "Demographics", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<DemographicsType> demographics;
    @XmlElementRef(name = "AddressList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AddressListType> addressList;
    @XmlElementRef(name = "PhoneNumberList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<PhoneNumberListType> phoneNumberList;
    @XmlElementRef(name = "EmailList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<EmailListType> emailList;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link NameOfRecordType }
     *     
     */
    public NameOfRecordType getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameOfRecordType }
     *     
     */
    public void setName(NameOfRecordType value) {
        this.name = value;
    }

    /**
     * Gets the value of the otherNames property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link OtherNamesType }{@code >}
     *     
     */
    public JAXBElement<OtherNamesType> getOtherNames() {
        return otherNames;
    }

    /**
     * Sets the value of the otherNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link OtherNamesType }{@code >}
     *     
     */
    public void setOtherNames(JAXBElement<OtherNamesType> value) {
        this.otherNames = value;
    }

    /**
     * Gets the value of the demographics property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DemographicsType }{@code >}
     *     
     */
    public JAXBElement<DemographicsType> getDemographics() {
        return demographics;
    }

    /**
     * Sets the value of the demographics property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DemographicsType }{@code >}
     *     
     */
    public void setDemographics(JAXBElement<DemographicsType> value) {
        this.demographics = value;
    }

    /**
     * Gets the value of the addressList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AddressListType }{@code >}
     *     
     */
    public JAXBElement<AddressListType> getAddressList() {
        return addressList;
    }

    /**
     * Sets the value of the addressList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AddressListType }{@code >}
     *     
     */
    public void setAddressList(JAXBElement<AddressListType> value) {
        this.addressList = value;
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
