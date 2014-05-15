
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 *       This object defines a relationship between a contact person and a student.
 *     
 * 
 * <p>Java class for StudentContactRelationshipType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentContactRelationshipType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StudentContactRelationshipRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}Relationship" minOccurs="0"/>
 *         &lt;element name="ParentRelationshipStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="Parent1"/>
 *               &lt;enumeration value="Parent2"/>
 *               &lt;enumeration value="NotForReporting"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="HouseholdList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Household" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ContactFlags" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ParentLegalGuardian" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="PickupRights" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="LivesWith" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="AccessToRecords" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="ReceivesAssessmentReport" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="EmergencyContact" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="HasCustody" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="DisciplinaryContact" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="AttendanceContact" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="PrimaryCareProvider" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="FeesBilling" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="FamilyMail" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="InterventionOrder" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="MainlySpeaksEnglishAtHome" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="ContactSequence" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="ContactSequenceSource" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsSourceCodeTypeType" minOccurs="0"/>
 *         &lt;element name="SIF_Metadata" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="StudentPersonalRefId" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" />
 *       &lt;attribute name="StudentContactPersonalRefId" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentContactRelationshipType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "studentContactRelationshipRefId",
    "relationship",
    "parentRelationshipStatus",
    "householdList",
    "contactFlags",
    "mainlySpeaksEnglishAtHome",
    "contactSequence",
    "contactSequenceSource",
    "sifMetadata",
    "sifExtendedElements"
})
public class StudentContactRelationshipType {

    @XmlElementRef(name = "StudentContactRelationshipRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> studentContactRelationshipRefId;
    @XmlElement(name = "Relationship", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected RelationshipType relationship;
    @XmlElementRef(name = "ParentRelationshipStatus", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> parentRelationshipStatus;
    @XmlElementRef(name = "HouseholdList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentContactRelationshipType.HouseholdList> householdList;
    @XmlElementRef(name = "ContactFlags", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentContactRelationshipType.ContactFlags> contactFlags;
    @XmlElementRef(name = "MainlySpeaksEnglishAtHome", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> mainlySpeaksEnglishAtHome;
    @XmlElementRef(name = "ContactSequence", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> contactSequence;
    @XmlElementRef(name = "ContactSequenceSource", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsSourceCodeTypeType> contactSequenceSource;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "StudentPersonalRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String studentPersonalRefId;
    @XmlAttribute(name = "StudentContactPersonalRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String studentContactPersonalRefId;

    /**
     * Gets the value of the studentContactRelationshipRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStudentContactRelationshipRefId() {
        return studentContactRelationshipRefId;
    }

    /**
     * Sets the value of the studentContactRelationshipRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStudentContactRelationshipRefId(JAXBElement<String> value) {
        this.studentContactRelationshipRefId = value;
    }

    /**
     * Gets the value of the relationship property.
     * 
     * @return
     *     possible object is
     *     {@link RelationshipType }
     *     
     */
    public RelationshipType getRelationship() {
        return relationship;
    }

    /**
     * Sets the value of the relationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationshipType }
     *     
     */
    public void setRelationship(RelationshipType value) {
        this.relationship = value;
    }

    /**
     * Gets the value of the parentRelationshipStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParentRelationshipStatus() {
        return parentRelationshipStatus;
    }

    /**
     * Sets the value of the parentRelationshipStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParentRelationshipStatus(JAXBElement<String> value) {
        this.parentRelationshipStatus = value;
    }

    /**
     * Gets the value of the householdList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentContactRelationshipType.HouseholdList }{@code >}
     *     
     */
    public JAXBElement<StudentContactRelationshipType.HouseholdList> getHouseholdList() {
        return householdList;
    }

    /**
     * Sets the value of the householdList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentContactRelationshipType.HouseholdList }{@code >}
     *     
     */
    public void setHouseholdList(JAXBElement<StudentContactRelationshipType.HouseholdList> value) {
        this.householdList = value;
    }

    /**
     * Gets the value of the contactFlags property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentContactRelationshipType.ContactFlags }{@code >}
     *     
     */
    public JAXBElement<StudentContactRelationshipType.ContactFlags> getContactFlags() {
        return contactFlags;
    }

    /**
     * Sets the value of the contactFlags property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentContactRelationshipType.ContactFlags }{@code >}
     *     
     */
    public void setContactFlags(JAXBElement<StudentContactRelationshipType.ContactFlags> value) {
        this.contactFlags = value;
    }

    /**
     * Gets the value of the mainlySpeaksEnglishAtHome property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getMainlySpeaksEnglishAtHome() {
        return mainlySpeaksEnglishAtHome;
    }

    /**
     * Sets the value of the mainlySpeaksEnglishAtHome property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setMainlySpeaksEnglishAtHome(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.mainlySpeaksEnglishAtHome = value;
    }

    /**
     * Gets the value of the contactSequence property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getContactSequence() {
        return contactSequence;
    }

    /**
     * Sets the value of the contactSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setContactSequence(JAXBElement<Long> value) {
        this.contactSequence = value;
    }

    /**
     * Gets the value of the contactSequenceSource property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsSourceCodeTypeType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsSourceCodeTypeType> getContactSequenceSource() {
        return contactSequenceSource;
    }

    /**
     * Sets the value of the contactSequenceSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsSourceCodeTypeType }{@code >}
     *     
     */
    public void setContactSequenceSource(JAXBElement<AUCodeSetsSourceCodeTypeType> value) {
        this.contactSequenceSource = value;
    }

    /**
     * Gets the value of the sifMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}
     *     
     */
    public JAXBElement<SIFMetadataType> getSIFMetadata() {
        return sifMetadata;
    }

    /**
     * Sets the value of the sifMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}
     *     
     */
    public void setSIFMetadata(JAXBElement<SIFMetadataType> value) {
        this.sifMetadata = value;
    }

    /**
     * Gets the value of the sifExtendedElements property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}
     *     
     */
    public JAXBElement<SIFExtendedElementsType> getSIFExtendedElements() {
        return sifExtendedElements;
    }

    /**
     * Sets the value of the sifExtendedElements property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}
     *     
     */
    public void setSIFExtendedElements(JAXBElement<SIFExtendedElementsType> value) {
        this.sifExtendedElements = value;
    }

    /**
     * Gets the value of the studentPersonalRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentPersonalRefId() {
        return studentPersonalRefId;
    }

    /**
     * Sets the value of the studentPersonalRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentPersonalRefId(String value) {
        this.studentPersonalRefId = value;
    }

    /**
     * Gets the value of the studentContactPersonalRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentContactPersonalRefId() {
        return studentContactPersonalRefId;
    }

    /**
     * Sets the value of the studentContactPersonalRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentContactPersonalRefId(String value) {
        this.studentContactPersonalRefId = value;
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
     *         &lt;element name="ParentLegalGuardian" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="PickupRights" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="LivesWith" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="AccessToRecords" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="ReceivesAssessmentReport" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="EmergencyContact" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="HasCustody" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="DisciplinaryContact" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="AttendanceContact" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="PrimaryCareProvider" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="FeesBilling" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="FamilyMail" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="InterventionOrder" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
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
        "parentLegalGuardian",
        "pickupRights",
        "livesWith",
        "accessToRecords",
        "receivesAssessmentReport",
        "emergencyContact",
        "hasCustody",
        "disciplinaryContact",
        "attendanceContact",
        "primaryCareProvider",
        "feesBilling",
        "familyMail",
        "interventionOrder"
    })
    public static class ContactFlags {

        @XmlElementRef(name = "ParentLegalGuardian", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> parentLegalGuardian;
        @XmlElementRef(name = "PickupRights", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> pickupRights;
        @XmlElementRef(name = "LivesWith", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> livesWith;
        @XmlElementRef(name = "AccessToRecords", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> accessToRecords;
        @XmlElementRef(name = "ReceivesAssessmentReport", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> receivesAssessmentReport;
        @XmlElementRef(name = "EmergencyContact", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> emergencyContact;
        @XmlElementRef(name = "HasCustody", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> hasCustody;
        @XmlElementRef(name = "DisciplinaryContact", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> disciplinaryContact;
        @XmlElementRef(name = "AttendanceContact", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> attendanceContact;
        @XmlElementRef(name = "PrimaryCareProvider", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> primaryCareProvider;
        @XmlElementRef(name = "FeesBilling", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> feesBilling;
        @XmlElementRef(name = "FamilyMail", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> familyMail;
        @XmlElementRef(name = "InterventionOrder", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> interventionOrder;

        /**
         * Gets the value of the parentLegalGuardian property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getParentLegalGuardian() {
            return parentLegalGuardian;
        }

        /**
         * Sets the value of the parentLegalGuardian property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setParentLegalGuardian(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.parentLegalGuardian = value;
        }

        /**
         * Gets the value of the pickupRights property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getPickupRights() {
            return pickupRights;
        }

        /**
         * Sets the value of the pickupRights property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setPickupRights(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.pickupRights = value;
        }

        /**
         * Gets the value of the livesWith property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getLivesWith() {
            return livesWith;
        }

        /**
         * Sets the value of the livesWith property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setLivesWith(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.livesWith = value;
        }

        /**
         * Gets the value of the accessToRecords property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getAccessToRecords() {
            return accessToRecords;
        }

        /**
         * Sets the value of the accessToRecords property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setAccessToRecords(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.accessToRecords = value;
        }

        /**
         * Gets the value of the receivesAssessmentReport property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getReceivesAssessmentReport() {
            return receivesAssessmentReport;
        }

        /**
         * Sets the value of the receivesAssessmentReport property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setReceivesAssessmentReport(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.receivesAssessmentReport = value;
        }

        /**
         * Gets the value of the emergencyContact property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getEmergencyContact() {
            return emergencyContact;
        }

        /**
         * Sets the value of the emergencyContact property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setEmergencyContact(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.emergencyContact = value;
        }

        /**
         * Gets the value of the hasCustody property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getHasCustody() {
            return hasCustody;
        }

        /**
         * Sets the value of the hasCustody property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setHasCustody(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.hasCustody = value;
        }

        /**
         * Gets the value of the disciplinaryContact property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getDisciplinaryContact() {
            return disciplinaryContact;
        }

        /**
         * Sets the value of the disciplinaryContact property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setDisciplinaryContact(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.disciplinaryContact = value;
        }

        /**
         * Gets the value of the attendanceContact property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getAttendanceContact() {
            return attendanceContact;
        }

        /**
         * Sets the value of the attendanceContact property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setAttendanceContact(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.attendanceContact = value;
        }

        /**
         * Gets the value of the primaryCareProvider property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getPrimaryCareProvider() {
            return primaryCareProvider;
        }

        /**
         * Sets the value of the primaryCareProvider property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setPrimaryCareProvider(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.primaryCareProvider = value;
        }

        /**
         * Gets the value of the feesBilling property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getFeesBilling() {
            return feesBilling;
        }

        /**
         * Sets the value of the feesBilling property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setFeesBilling(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.feesBilling = value;
        }

        /**
         * Gets the value of the familyMail property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getFamilyMail() {
            return familyMail;
        }

        /**
         * Sets the value of the familyMail property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setFamilyMail(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.familyMail = value;
        }

        /**
         * Gets the value of the interventionOrder property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getInterventionOrder() {
            return interventionOrder;
        }

        /**
         * Sets the value of the interventionOrder property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setInterventionOrder(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.interventionOrder = value;
        }

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
     *         &lt;element name="Household" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" maxOccurs="unbounded" minOccurs="0"/>
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
        "household"
    })
    public static class HouseholdList {

        @XmlElement(name = "Household", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        protected List<String> household;

        /**
         * Gets the value of the household property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the household property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHousehold().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getHousehold() {
            if (household == null) {
                household = new ArrayList<String>();
            }
            return this.household;
        }

    }

}
