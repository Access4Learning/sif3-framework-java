
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A list of contact persons associated with a school.
 * 
 * <p>Java class for SchoolContactListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SchoolContactListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SchoolContact" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PublishInDirectory" type="{http://www.sifassociation.org/au/datamodel/1.3}PublishInDirectoryType" minOccurs="0"/>
 *                   &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}ContactInfo" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
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
@XmlType(name = "SchoolContactListType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "schoolContact"
})
public class SchoolContactListType {

    @XmlElement(name = "SchoolContact", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<SchoolContactListType.SchoolContact> schoolContact;

    /**
     * Gets the value of the schoolContact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the schoolContact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSchoolContact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SchoolContactListType.SchoolContact }
     * 
     * 
     */
    public List<SchoolContactListType.SchoolContact> getSchoolContact() {
        if (schoolContact == null) {
            schoolContact = new ArrayList<SchoolContactListType.SchoolContact>();
        }
        return this.schoolContact;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="PublishInDirectory" type="{http://www.sifassociation.org/au/datamodel/1.3}PublishInDirectoryType" minOccurs="0"/>
     *         &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}ContactInfo" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "publishInDirectory",
        "contactInfo"
    })
    public static class SchoolContact {

        @XmlElementRef(name = "PublishInDirectory", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> publishInDirectory;
        @XmlElement(name = "ContactInfo", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected ContactInfoType contactInfo;

        /**
         * Gets the value of the publishInDirectory property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getPublishInDirectory() {
            return publishInDirectory;
        }

        /**
         * Sets the value of the publishInDirectory property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setPublishInDirectory(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.publishInDirectory = value;
        }

        /**
         * Gets the value of the contactInfo property.
         * 
         * @return
         *     possible object is
         *     {@link ContactInfoType }
         *     
         */
        public ContactInfoType getContactInfo() {
            return contactInfo;
        }

        /**
         * Sets the value of the contactInfo property.
         * 
         * @param value
         *     allowed object is
         *     {@link ContactInfoType }
         *     
         */
        public void setContactInfo(ContactInfoType value) {
            this.contactInfo = value;
        }

    }

}
