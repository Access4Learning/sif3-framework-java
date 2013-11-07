
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This object represents the assignment of a specific assessment to be taken by a student. A student will have multiple registrations if a given assessment is attempted more than once. This object will also track testing status information after the student has tested.
 * 
 * <p>Java class for SIF3AssessmentRegistrationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIF3AssessmentRegistrationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssessmentAdministrationRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="AssessmentSessionRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="AssessmentFormRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="CreationDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="StartDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="EndDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="AssessmentPlatform" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="DaysOfInstruction" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="RetestIndicator" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="TestAttemptIdentifier" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="StudentSpecialEvents" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="StudentSpecialEvent" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
 *                           &lt;attribute name="Code" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="TestingStatuses" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TestingStatus" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="TestingStatusCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                             &lt;element name="TestingStatusDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ScorePublishDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="StudentGradeLevel" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
 *         &lt;element name="AssessmentGradeLevel" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
 *         &lt;element name="AssessmentStudentSnapshot" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Name" type="{http://www.SIFinfo.org/au/datamodel/1.3}NameType" minOccurs="0"/>
 *                   &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}LocalId" minOccurs="0"/>
 *                   &lt;element name="StateProvinceId" type="{http://www.SIFinfo.org/au/datamodel/1.3}StateProvinceIdType" minOccurs="0"/>
 *                   &lt;element name="Address" type="{http://www.SIFinfo.org/au/datamodel/1.3}AddressType" minOccurs="0"/>
 *                   &lt;element name="Sex" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsSexCodeType" minOccurs="0"/>
 *                   &lt;element name="BirthDate" type="{http://www.SIFinfo.org/au/datamodel/1.3}BirthDateType" minOccurs="0"/>
 *                   &lt;element name="Age" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *                   &lt;element name="ProjectedGraduationYear" type="{http://www.SIFinfo.org/au/datamodel/1.3}ProjectedGraduationYearType" minOccurs="0"/>
 *                   &lt;element name="OnTimeGraduationYear" type="{http://www.SIFinfo.org/au/datamodel/1.3}OnTimeGraduationYearType" minOccurs="0"/>
 *                   &lt;element name="GraduationDate" type="{http://www.SIFinfo.org/au/datamodel/1.3}GraduationDateType" minOccurs="0"/>
 *                   &lt;element name="GraduationAward" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                         &lt;enumeration value="Diploma"/>
 *                         &lt;enumeration value="Certificate"/>
 *                         &lt;enumeration value="GED"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="GraduationOnTime" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                         &lt;enumeration value="Yes"/>
 *                         &lt;enumeration value="No"/>
 *                         &lt;enumeration value="Unavailable"/>
 *                         &lt;enumeration value="NA"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="HomeEnrollment" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="StudentSchoolEnrollmentRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *                             &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *                             &lt;element name="LocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                             &lt;element name="StateProvinceId" type="{http://www.SIFinfo.org/au/datamodel/1.3}StateProvinceIdType" minOccurs="0"/>
 *                             &lt;element name="Status" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                   &lt;enumeration value="Active"/>
 *                                   &lt;enumeration value="Inactive"/>
 *                                   &lt;enumeration value="Unreported"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="GradeLevel" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
 *                             &lt;element name="Homeroom" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                                     &lt;attribute name="SIF_RefObject" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                           &lt;enumeration value="RoomInfo"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="HomeroomNumber" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="FullYearEnrollment" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                   &lt;enumeration value="Yes"/>
 *                                   &lt;enumeration value="No"/>
 *                                   &lt;enumeration value="Unknown"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="GiftedTalented" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="EconomicDisadvantage" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="NeglectedDelinquent" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LEAInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="StaffPersonalRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="SectionInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="SIF_Metadata" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}RefIdType" />
 *       &lt;attribute name="StudentPersonalRefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
 *       &lt;attribute name="AssessmentRefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIF3AssessmentRegistrationType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "assessmentAdministrationRefId",
    "assessmentSessionRefId",
    "assessmentFormRefId",
    "creationDateTime",
    "startDateTime",
    "endDateTime",
    "assessmentPlatform",
    "daysOfInstruction",
    "retestIndicator",
    "testAttemptIdentifier",
    "studentSpecialEvents",
    "testingStatuses",
    "scorePublishDate",
    "studentGradeLevel",
    "assessmentGradeLevel",
    "assessmentStudentSnapshot",
    "leaInfoRefId",
    "schoolInfoRefId",
    "staffPersonalRefId",
    "sectionInfoRefId",
    "sifMetadata",
    "sifExtendedElements"
})
public class SIF3AssessmentRegistrationType {

    @XmlElementRef(name = "AssessmentAdministrationRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assessmentAdministrationRefId;
    @XmlElementRef(name = "AssessmentSessionRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assessmentSessionRefId;
    @XmlElementRef(name = "AssessmentFormRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assessmentFormRefId;
    @XmlElement(name = "CreationDateTime", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDateTime;
    @XmlElementRef(name = "StartDateTime", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> startDateTime;
    @XmlElementRef(name = "EndDateTime", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> endDateTime;
    @XmlElementRef(name = "AssessmentPlatform", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assessmentPlatform;
    @XmlElementRef(name = "DaysOfInstruction", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> daysOfInstruction;
    @XmlElementRef(name = "RetestIndicator", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> retestIndicator;
    @XmlElementRef(name = "TestAttemptIdentifier", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> testAttemptIdentifier;
    @XmlElementRef(name = "StudentSpecialEvents", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIF3AssessmentRegistrationType.StudentSpecialEvents> studentSpecialEvents;
    @XmlElementRef(name = "TestingStatuses", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIF3AssessmentRegistrationType.TestingStatuses> testingStatuses;
    @XmlElementRef(name = "ScorePublishDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> scorePublishDate;
    @XmlElementRef(name = "StudentGradeLevel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelType> studentGradeLevel;
    @XmlElementRef(name = "AssessmentGradeLevel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelType> assessmentGradeLevel;
    @XmlElementRef(name = "AssessmentStudentSnapshot", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIF3AssessmentRegistrationType.AssessmentStudentSnapshot> assessmentStudentSnapshot;
    @XmlElementRef(name = "LEAInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> leaInfoRefId;
    @XmlElementRef(name = "SchoolInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolInfoRefId;
    @XmlElementRef(name = "StaffPersonalRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> staffPersonalRefId;
    @XmlElementRef(name = "SectionInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sectionInfoRefId;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;
    @XmlAttribute(name = "StudentPersonalRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String studentPersonalRefId;
    @XmlAttribute(name = "AssessmentRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String assessmentRefId;

    /**
     * Gets the value of the assessmentAdministrationRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssessmentAdministrationRefId() {
        return assessmentAdministrationRefId;
    }

    /**
     * Sets the value of the assessmentAdministrationRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssessmentAdministrationRefId(JAXBElement<String> value) {
        this.assessmentAdministrationRefId = value;
    }

    /**
     * Gets the value of the assessmentSessionRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssessmentSessionRefId() {
        return assessmentSessionRefId;
    }

    /**
     * Sets the value of the assessmentSessionRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssessmentSessionRefId(JAXBElement<String> value) {
        this.assessmentSessionRefId = value;
    }

    /**
     * Gets the value of the assessmentFormRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssessmentFormRefId() {
        return assessmentFormRefId;
    }

    /**
     * Sets the value of the assessmentFormRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssessmentFormRefId(JAXBElement<String> value) {
        this.assessmentFormRefId = value;
    }

    /**
     * Gets the value of the creationDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDateTime() {
        return creationDateTime;
    }

    /**
     * Sets the value of the creationDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDateTime(XMLGregorianCalendar value) {
        this.creationDateTime = value;
    }

    /**
     * Gets the value of the startDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the value of the startDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setStartDateTime(JAXBElement<XMLGregorianCalendar> value) {
        this.startDateTime = value;
    }

    /**
     * Gets the value of the endDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getEndDateTime() {
        return endDateTime;
    }

    /**
     * Sets the value of the endDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setEndDateTime(JAXBElement<XMLGregorianCalendar> value) {
        this.endDateTime = value;
    }

    /**
     * Gets the value of the assessmentPlatform property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssessmentPlatform() {
        return assessmentPlatform;
    }

    /**
     * Sets the value of the assessmentPlatform property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssessmentPlatform(JAXBElement<String> value) {
        this.assessmentPlatform = value;
    }

    /**
     * Gets the value of the daysOfInstruction property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getDaysOfInstruction() {
        return daysOfInstruction;
    }

    /**
     * Sets the value of the daysOfInstruction property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setDaysOfInstruction(JAXBElement<Integer> value) {
        this.daysOfInstruction = value;
    }

    /**
     * Gets the value of the retestIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRetestIndicator() {
        return retestIndicator;
    }

    /**
     * Sets the value of the retestIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRetestIndicator(JAXBElement<String> value) {
        this.retestIndicator = value;
    }

    /**
     * Gets the value of the testAttemptIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTestAttemptIdentifier() {
        return testAttemptIdentifier;
    }

    /**
     * Sets the value of the testAttemptIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTestAttemptIdentifier(JAXBElement<String> value) {
        this.testAttemptIdentifier = value;
    }

    /**
     * Gets the value of the studentSpecialEvents property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRegistrationType.StudentSpecialEvents }{@code >}
     *     
     */
    public JAXBElement<SIF3AssessmentRegistrationType.StudentSpecialEvents> getStudentSpecialEvents() {
        return studentSpecialEvents;
    }

    /**
     * Sets the value of the studentSpecialEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRegistrationType.StudentSpecialEvents }{@code >}
     *     
     */
    public void setStudentSpecialEvents(JAXBElement<SIF3AssessmentRegistrationType.StudentSpecialEvents> value) {
        this.studentSpecialEvents = value;
    }

    /**
     * Gets the value of the testingStatuses property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRegistrationType.TestingStatuses }{@code >}
     *     
     */
    public JAXBElement<SIF3AssessmentRegistrationType.TestingStatuses> getTestingStatuses() {
        return testingStatuses;
    }

    /**
     * Sets the value of the testingStatuses property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRegistrationType.TestingStatuses }{@code >}
     *     
     */
    public void setTestingStatuses(JAXBElement<SIF3AssessmentRegistrationType.TestingStatuses> value) {
        this.testingStatuses = value;
    }

    /**
     * Gets the value of the scorePublishDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getScorePublishDate() {
        return scorePublishDate;
    }

    /**
     * Sets the value of the scorePublishDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setScorePublishDate(JAXBElement<XMLGregorianCalendar> value) {
        this.scorePublishDate = value;
    }

    /**
     * Gets the value of the studentGradeLevel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}
     *     
     */
    public JAXBElement<YearLevelType> getStudentGradeLevel() {
        return studentGradeLevel;
    }

    /**
     * Sets the value of the studentGradeLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}
     *     
     */
    public void setStudentGradeLevel(JAXBElement<YearLevelType> value) {
        this.studentGradeLevel = value;
    }

    /**
     * Gets the value of the assessmentGradeLevel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}
     *     
     */
    public JAXBElement<YearLevelType> getAssessmentGradeLevel() {
        return assessmentGradeLevel;
    }

    /**
     * Sets the value of the assessmentGradeLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}
     *     
     */
    public void setAssessmentGradeLevel(JAXBElement<YearLevelType> value) {
        this.assessmentGradeLevel = value;
    }

    /**
     * Gets the value of the assessmentStudentSnapshot property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRegistrationType.AssessmentStudentSnapshot }{@code >}
     *     
     */
    public JAXBElement<SIF3AssessmentRegistrationType.AssessmentStudentSnapshot> getAssessmentStudentSnapshot() {
        return assessmentStudentSnapshot;
    }

    /**
     * Sets the value of the assessmentStudentSnapshot property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRegistrationType.AssessmentStudentSnapshot }{@code >}
     *     
     */
    public void setAssessmentStudentSnapshot(JAXBElement<SIF3AssessmentRegistrationType.AssessmentStudentSnapshot> value) {
        this.assessmentStudentSnapshot = value;
    }

    /**
     * Gets the value of the leaInfoRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLEAInfoRefId() {
        return leaInfoRefId;
    }

    /**
     * Sets the value of the leaInfoRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLEAInfoRefId(JAXBElement<String> value) {
        this.leaInfoRefId = value;
    }

    /**
     * Gets the value of the schoolInfoRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSchoolInfoRefId() {
        return schoolInfoRefId;
    }

    /**
     * Sets the value of the schoolInfoRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSchoolInfoRefId(JAXBElement<String> value) {
        this.schoolInfoRefId = value;
    }

    /**
     * Gets the value of the staffPersonalRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStaffPersonalRefId() {
        return staffPersonalRefId;
    }

    /**
     * Sets the value of the staffPersonalRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStaffPersonalRefId(JAXBElement<String> value) {
        this.staffPersonalRefId = value;
    }

    /**
     * Gets the value of the sectionInfoRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSectionInfoRefId() {
        return sectionInfoRefId;
    }

    /**
     * Sets the value of the sectionInfoRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSectionInfoRefId(JAXBElement<String> value) {
        this.sectionInfoRefId = value;
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
     * Gets the value of the refId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefId() {
        return refId;
    }

    /**
     * Sets the value of the refId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefId(String value) {
        this.refId = value;
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
     * Gets the value of the assessmentRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessmentRefId() {
        return assessmentRefId;
    }

    /**
     * Sets the value of the assessmentRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessmentRefId(String value) {
        this.assessmentRefId = value;
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
     *         &lt;element name="Name" type="{http://www.SIFinfo.org/au/datamodel/1.3}NameType" minOccurs="0"/>
     *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}LocalId" minOccurs="0"/>
     *         &lt;element name="StateProvinceId" type="{http://www.SIFinfo.org/au/datamodel/1.3}StateProvinceIdType" minOccurs="0"/>
     *         &lt;element name="Address" type="{http://www.SIFinfo.org/au/datamodel/1.3}AddressType" minOccurs="0"/>
     *         &lt;element name="Sex" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsSexCodeType" minOccurs="0"/>
     *         &lt;element name="BirthDate" type="{http://www.SIFinfo.org/au/datamodel/1.3}BirthDateType" minOccurs="0"/>
     *         &lt;element name="Age" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
     *         &lt;element name="ProjectedGraduationYear" type="{http://www.SIFinfo.org/au/datamodel/1.3}ProjectedGraduationYearType" minOccurs="0"/>
     *         &lt;element name="OnTimeGraduationYear" type="{http://www.SIFinfo.org/au/datamodel/1.3}OnTimeGraduationYearType" minOccurs="0"/>
     *         &lt;element name="GraduationDate" type="{http://www.SIFinfo.org/au/datamodel/1.3}GraduationDateType" minOccurs="0"/>
     *         &lt;element name="GraduationAward" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *               &lt;enumeration value="Diploma"/>
     *               &lt;enumeration value="Certificate"/>
     *               &lt;enumeration value="GED"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="GraduationOnTime" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *               &lt;enumeration value="Yes"/>
     *               &lt;enumeration value="No"/>
     *               &lt;enumeration value="Unavailable"/>
     *               &lt;enumeration value="NA"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="HomeEnrollment" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="StudentSchoolEnrollmentRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
     *                   &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
     *                   &lt;element name="LocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                   &lt;element name="StateProvinceId" type="{http://www.SIFinfo.org/au/datamodel/1.3}StateProvinceIdType" minOccurs="0"/>
     *                   &lt;element name="Status" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                         &lt;enumeration value="Active"/>
     *                         &lt;enumeration value="Inactive"/>
     *                         &lt;enumeration value="Unreported"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="GradeLevel" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
     *                   &lt;element name="Homeroom" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
     *                           &lt;attribute name="SIF_RefObject" use="required">
     *                             &lt;simpleType>
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                                 &lt;enumeration value="RoomInfo"/>
     *                               &lt;/restriction>
     *                             &lt;/simpleType>
     *                           &lt;/attribute>
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="HomeroomNumber" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="FullYearEnrollment" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                         &lt;enumeration value="Yes"/>
     *                         &lt;enumeration value="No"/>
     *                         &lt;enumeration value="Unknown"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="GiftedTalented" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="EconomicDisadvantage" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="NeglectedDelinquent" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
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
        "name",
        "localId",
        "stateProvinceId",
        "address",
        "sex",
        "birthDate",
        "age",
        "projectedGraduationYear",
        "onTimeGraduationYear",
        "graduationDate",
        "graduationAward",
        "graduationOnTime",
        "homeEnrollment",
        "giftedTalented",
        "economicDisadvantage",
        "neglectedDelinquent"
    })
    public static class AssessmentStudentSnapshot {

        @XmlElementRef(name = "Name", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<NameType> name;
        @XmlElement(name = "LocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        protected String localId;
        @XmlElementRef(name = "StateProvinceId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> stateProvinceId;
        @XmlElementRef(name = "Address", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AddressType> address;
        @XmlElementRef(name = "Sex", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> sex;
        @XmlElementRef(name = "BirthDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> birthDate;
        @XmlElementRef(name = "Age", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<Long> age;
        @XmlElementRef(name = "ProjectedGraduationYear", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> projectedGraduationYear;
        @XmlElementRef(name = "OnTimeGraduationYear", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> onTimeGraduationYear;
        @XmlElementRef(name = "GraduationDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> graduationDate;
        @XmlElementRef(name = "GraduationAward", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> graduationAward;
        @XmlElementRef(name = "GraduationOnTime", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> graduationOnTime;
        @XmlElement(name = "HomeEnrollment", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected SIF3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment homeEnrollment;
        @XmlElementRef(name = "GiftedTalented", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> giftedTalented;
        @XmlElementRef(name = "EconomicDisadvantage", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> economicDisadvantage;
        @XmlElementRef(name = "NeglectedDelinquent", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> neglectedDelinquent;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link NameType }{@code >}
         *     
         */
        public JAXBElement<NameType> getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link NameType }{@code >}
         *     
         */
        public void setName(JAXBElement<NameType> value) {
            this.name = value;
        }

        /**
         * Gets the value of the localId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLocalId() {
            return localId;
        }

        /**
         * Sets the value of the localId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLocalId(String value) {
            this.localId = value;
        }

        /**
         * Gets the value of the stateProvinceId property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getStateProvinceId() {
            return stateProvinceId;
        }

        /**
         * Sets the value of the stateProvinceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setStateProvinceId(JAXBElement<String> value) {
            this.stateProvinceId = value;
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
         * Gets the value of the sex property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getSex() {
            return sex;
        }

        /**
         * Sets the value of the sex property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setSex(JAXBElement<String> value) {
            this.sex = value;
        }

        /**
         * Gets the value of the birthDate property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getBirthDate() {
            return birthDate;
        }

        /**
         * Sets the value of the birthDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setBirthDate(JAXBElement<XMLGregorianCalendar> value) {
            this.birthDate = value;
        }

        /**
         * Gets the value of the age property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Long }{@code >}
         *     
         */
        public JAXBElement<Long> getAge() {
            return age;
        }

        /**
         * Sets the value of the age property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Long }{@code >}
         *     
         */
        public void setAge(JAXBElement<Long> value) {
            this.age = value;
        }

        /**
         * Gets the value of the projectedGraduationYear property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getProjectedGraduationYear() {
            return projectedGraduationYear;
        }

        /**
         * Sets the value of the projectedGraduationYear property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setProjectedGraduationYear(JAXBElement<XMLGregorianCalendar> value) {
            this.projectedGraduationYear = value;
        }

        /**
         * Gets the value of the onTimeGraduationYear property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getOnTimeGraduationYear() {
            return onTimeGraduationYear;
        }

        /**
         * Sets the value of the onTimeGraduationYear property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setOnTimeGraduationYear(JAXBElement<XMLGregorianCalendar> value) {
            this.onTimeGraduationYear = value;
        }

        /**
         * Gets the value of the graduationDate property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getGraduationDate() {
            return graduationDate;
        }

        /**
         * Sets the value of the graduationDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setGraduationDate(JAXBElement<String> value) {
            this.graduationDate = value;
        }

        /**
         * Gets the value of the graduationAward property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getGraduationAward() {
            return graduationAward;
        }

        /**
         * Sets the value of the graduationAward property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setGraduationAward(JAXBElement<String> value) {
            this.graduationAward = value;
        }

        /**
         * Gets the value of the graduationOnTime property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getGraduationOnTime() {
            return graduationOnTime;
        }

        /**
         * Sets the value of the graduationOnTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setGraduationOnTime(JAXBElement<String> value) {
            this.graduationOnTime = value;
        }

        /**
         * Gets the value of the homeEnrollment property.
         * 
         * @return
         *     possible object is
         *     {@link SIF3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment }
         *     
         */
        public SIF3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment getHomeEnrollment() {
            return homeEnrollment;
        }

        /**
         * Sets the value of the homeEnrollment property.
         * 
         * @param value
         *     allowed object is
         *     {@link SIF3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment }
         *     
         */
        public void setHomeEnrollment(SIF3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment value) {
            this.homeEnrollment = value;
        }

        /**
         * Gets the value of the giftedTalented property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getGiftedTalented() {
            return giftedTalented;
        }

        /**
         * Sets the value of the giftedTalented property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setGiftedTalented(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.giftedTalented = value;
        }

        /**
         * Gets the value of the economicDisadvantage property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getEconomicDisadvantage() {
            return economicDisadvantage;
        }

        /**
         * Sets the value of the economicDisadvantage property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setEconomicDisadvantage(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.economicDisadvantage = value;
        }

        /**
         * Gets the value of the neglectedDelinquent property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getNeglectedDelinquent() {
            return neglectedDelinquent;
        }

        /**
         * Sets the value of the neglectedDelinquent property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setNeglectedDelinquent(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.neglectedDelinquent = value;
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
         *         &lt;element name="StudentSchoolEnrollmentRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
         *         &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
         *         &lt;element name="LocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *         &lt;element name="StateProvinceId" type="{http://www.SIFinfo.org/au/datamodel/1.3}StateProvinceIdType" minOccurs="0"/>
         *         &lt;element name="Status" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *               &lt;enumeration value="Active"/>
         *               &lt;enumeration value="Inactive"/>
         *               &lt;enumeration value="Unreported"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="GradeLevel" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
         *         &lt;element name="Homeroom" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
         *                 &lt;attribute name="SIF_RefObject" use="required">
         *                   &lt;simpleType>
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *                       &lt;enumeration value="RoomInfo"/>
         *                     &lt;/restriction>
         *                   &lt;/simpleType>
         *                 &lt;/attribute>
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="HomeroomNumber" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="FullYearEnrollment" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *               &lt;enumeration value="Yes"/>
         *               &lt;enumeration value="No"/>
         *               &lt;enumeration value="Unknown"/>
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
        @XmlType(name = "", propOrder = {
            "studentSchoolEnrollmentRefId",
            "schoolName",
            "schoolInfoRefId",
            "localId",
            "stateProvinceId",
            "status",
            "gradeLevel",
            "homeroom",
            "homeroomNumber",
            "fullYearEnrollment"
        })
        public static class HomeEnrollment {

            @XmlElementRef(name = "StudentSchoolEnrollmentRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> studentSchoolEnrollmentRefId;
            @XmlElementRef(name = "SchoolName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> schoolName;
            @XmlElementRef(name = "SchoolInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> schoolInfoRefId;
            @XmlElementRef(name = "LocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> localId;
            @XmlElementRef(name = "StateProvinceId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> stateProvinceId;
            @XmlElement(name = "Status", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String status;
            @XmlElement(name = "GradeLevel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            protected YearLevelType gradeLevel;
            @XmlElementRef(name = "Homeroom", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SIF3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom> homeroom;
            @XmlElementRef(name = "HomeroomNumber", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> homeroomNumber;
            @XmlElementRef(name = "FullYearEnrollment", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> fullYearEnrollment;

            /**
             * Gets the value of the studentSchoolEnrollmentRefId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getStudentSchoolEnrollmentRefId() {
                return studentSchoolEnrollmentRefId;
            }

            /**
             * Sets the value of the studentSchoolEnrollmentRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setStudentSchoolEnrollmentRefId(JAXBElement<String> value) {
                this.studentSchoolEnrollmentRefId = value;
            }

            /**
             * Gets the value of the schoolName property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getSchoolName() {
                return schoolName;
            }

            /**
             * Sets the value of the schoolName property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setSchoolName(JAXBElement<String> value) {
                this.schoolName = value;
            }

            /**
             * Gets the value of the schoolInfoRefId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getSchoolInfoRefId() {
                return schoolInfoRefId;
            }

            /**
             * Sets the value of the schoolInfoRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setSchoolInfoRefId(JAXBElement<String> value) {
                this.schoolInfoRefId = value;
            }

            /**
             * Gets the value of the localId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getLocalId() {
                return localId;
            }

            /**
             * Sets the value of the localId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setLocalId(JAXBElement<String> value) {
                this.localId = value;
            }

            /**
             * Gets the value of the stateProvinceId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getStateProvinceId() {
                return stateProvinceId;
            }

            /**
             * Sets the value of the stateProvinceId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setStateProvinceId(JAXBElement<String> value) {
                this.stateProvinceId = value;
            }

            /**
             * Gets the value of the status property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStatus() {
                return status;
            }

            /**
             * Sets the value of the status property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStatus(String value) {
                this.status = value;
            }

            /**
             * Gets the value of the gradeLevel property.
             * 
             * @return
             *     possible object is
             *     {@link YearLevelType }
             *     
             */
            public YearLevelType getGradeLevel() {
                return gradeLevel;
            }

            /**
             * Sets the value of the gradeLevel property.
             * 
             * @param value
             *     allowed object is
             *     {@link YearLevelType }
             *     
             */
            public void setGradeLevel(YearLevelType value) {
                this.gradeLevel = value;
            }

            /**
             * Gets the value of the homeroom property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom }{@code >}
             *     
             */
            public JAXBElement<SIF3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom> getHomeroom() {
                return homeroom;
            }

            /**
             * Sets the value of the homeroom property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom }{@code >}
             *     
             */
            public void setHomeroom(JAXBElement<SIF3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom> value) {
                this.homeroom = value;
            }

            /**
             * Gets the value of the homeroomNumber property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getHomeroomNumber() {
                return homeroomNumber;
            }

            /**
             * Sets the value of the homeroomNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setHomeroomNumber(JAXBElement<String> value) {
                this.homeroomNumber = value;
            }

            /**
             * Gets the value of the fullYearEnrollment property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getFullYearEnrollment() {
                return fullYearEnrollment;
            }

            /**
             * Sets the value of the fullYearEnrollment property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setFullYearEnrollment(JAXBElement<String> value) {
                this.fullYearEnrollment = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
             *       &lt;attribute name="SIF_RefObject" use="required">
             *         &lt;simpleType>
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
             *             &lt;enumeration value="RoomInfo"/>
             *           &lt;/restriction>
             *         &lt;/simpleType>
             *       &lt;/attribute>
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class Homeroom {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "SIF_RefObject", required = true)
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String sifRefObject;

                /**
                 * Gets the value of the value property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the sifRefObject property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSIFRefObject() {
                    return sifRefObject;
                }

                /**
                 * Sets the value of the sifRefObject property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSIFRefObject(String value) {
                    this.sifRefObject = value;
                }

            }

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
     *         &lt;element name="StudentSpecialEvent" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
     *                 &lt;attribute name="Code" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
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
    @XmlType(name = "", propOrder = {
        "studentSpecialEvent"
    })
    public static class StudentSpecialEvents {

        @XmlElement(name = "StudentSpecialEvent", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<SIF3AssessmentRegistrationType.StudentSpecialEvents.StudentSpecialEvent> studentSpecialEvent;

        /**
         * Gets the value of the studentSpecialEvent property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the studentSpecialEvent property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStudentSpecialEvent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SIF3AssessmentRegistrationType.StudentSpecialEvents.StudentSpecialEvent }
         * 
         * 
         */
        public List<SIF3AssessmentRegistrationType.StudentSpecialEvents.StudentSpecialEvent> getStudentSpecialEvent() {
            if (studentSpecialEvent == null) {
                studentSpecialEvent = new ArrayList<SIF3AssessmentRegistrationType.StudentSpecialEvents.StudentSpecialEvent>();
            }
            return this.studentSpecialEvent;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
         *       &lt;attribute name="Code" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class StudentSpecialEvent {

            @XmlValue
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String value;
            @XmlAttribute(name = "Code", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String code;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the code property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCode() {
                return code;
            }

            /**
             * Sets the value of the code property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCode(String value) {
                this.code = value;
            }

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
     *         &lt;element name="TestingStatus" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="TestingStatusCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                   &lt;element name="TestingStatusDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
    @XmlType(name = "", propOrder = {
        "testingStatus"
    })
    public static class TestingStatuses {

        @XmlElement(name = "TestingStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<SIF3AssessmentRegistrationType.TestingStatuses.TestingStatus> testingStatus;

        /**
         * Gets the value of the testingStatus property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the testingStatus property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTestingStatus().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SIF3AssessmentRegistrationType.TestingStatuses.TestingStatus }
         * 
         * 
         */
        public List<SIF3AssessmentRegistrationType.TestingStatuses.TestingStatus> getTestingStatus() {
            if (testingStatus == null) {
                testingStatus = new ArrayList<SIF3AssessmentRegistrationType.TestingStatuses.TestingStatus>();
            }
            return this.testingStatus;
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
         *         &lt;element name="TestingStatusCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *         &lt;element name="TestingStatusDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
            "testingStatusCode",
            "testingStatusDescription"
        })
        public static class TestingStatus {

            @XmlElement(name = "TestingStatusCode", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String testingStatusCode;
            @XmlElementRef(name = "TestingStatusDescription", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> testingStatusDescription;

            /**
             * Gets the value of the testingStatusCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTestingStatusCode() {
                return testingStatusCode;
            }

            /**
             * Sets the value of the testingStatusCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTestingStatusCode(String value) {
                this.testingStatusCode = value;
            }

            /**
             * Gets the value of the testingStatusDescription property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getTestingStatusDescription() {
                return testingStatusDescription;
            }

            /**
             * Sets the value of the testingStatusDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setTestingStatusDescription(JAXBElement<String> value) {
                this.testingStatusDescription = value;
            }

        }

    }

}
